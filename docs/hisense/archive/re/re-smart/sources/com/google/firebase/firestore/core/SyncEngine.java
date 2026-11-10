package com.google.firebase.firestore.core;

import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import c.a.za;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.database.collection.ImmutableSortedMap;
import com.google.firebase.database.collection.ImmutableSortedSet;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.LoadBundleTask;
import com.google.firebase.firestore.LoadBundleTaskProgress;
import com.google.firebase.firestore.auth.User;
import com.google.firebase.firestore.bundle.BundleElement;
import com.google.firebase.firestore.bundle.BundleLoader;
import com.google.firebase.firestore.bundle.BundleMetadata;
import com.google.firebase.firestore.bundle.BundleReader;
import com.google.firebase.firestore.local.LocalStore;
import com.google.firebase.firestore.local.LocalViewChanges;
import com.google.firebase.firestore.local.LocalWriteResult;
import com.google.firebase.firestore.local.QueryPurpose;
import com.google.firebase.firestore.local.QueryResult;
import com.google.firebase.firestore.local.ReferenceSet;
import com.google.firebase.firestore.local.TargetData;
import com.google.firebase.firestore.model.Document;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.MutableDocument;
import com.google.firebase.firestore.model.SnapshotVersion;
import com.google.firebase.firestore.model.mutation.Mutation;
import com.google.firebase.firestore.model.mutation.MutationBatchResult;
import com.google.firebase.firestore.remote.RemoteEvent;
import com.google.firebase.firestore.remote.RemoteStore;
import com.google.firebase.firestore.remote.TargetChange;
import com.google.firebase.firestore.util.Assert;
import com.google.firebase.firestore.util.AsyncQueue;
import com.google.firebase.firestore.util.Function;
import com.google.firebase.firestore.util.Logger;
import com.google.firebase.firestore.util.Util;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class SyncEngine implements RemoteStore.RemoteStoreCallback {
    public static final String TAG = "SyncEngine";
    public User currentUser;
    public final LocalStore localStore;
    public final int maxConcurrentLimboResolutions;
    public final RemoteStore remoteStore;
    public SyncEngineCallback syncEngineListener;
    public final Map<Query, QueryView> queryViewsByQuery = new HashMap();
    public final Map<Integer, List<Query>> queriesByTarget = new HashMap();
    public final LinkedHashSet<DocumentKey> enqueuedLimboResolutions = new LinkedHashSet<>();
    public final Map<DocumentKey, Integer> activeLimboTargetsByKey = new HashMap();
    public final Map<Integer, LimboResolution> activeLimboResolutionsByTarget = new HashMap();
    public final ReferenceSet limboDocumentRefs = new ReferenceSet();
    public final Map<User, Map<Integer, TaskCompletionSource<Void>>> mutationUserCallbacks = new HashMap();
    public final TargetIdGenerator targetIdGenerator = TargetIdGenerator.forSyncEngine();
    public final Map<Integer, List<TaskCompletionSource<Void>>> pendingWritesCallbacks = new HashMap();

    /* JADX INFO: renamed from: com.google.firebase.firestore.core.SyncEngine$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        public static final /* synthetic */ int[] $SwitchMap$com$google$firebase$firestore$core$LimboDocumentChange$Type = new int[LimboDocumentChange.Type.values().length];

        static {
            try {
                $SwitchMap$com$google$firebase$firestore$core$LimboDocumentChange$Type[LimboDocumentChange.Type.ADDED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$core$LimboDocumentChange$Type[LimboDocumentChange.Type.REMOVED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    private static class LimboResolution {
        public final DocumentKey key;
        public boolean receivedDocument;

        public LimboResolution(DocumentKey documentKey) {
            this.key = documentKey;
        }
    }

    interface SyncEngineCallback {
        void handleOnlineStateChange(OnlineState onlineState);

        void onError(Query query, za zaVar);

        void onViewSnapshots(List<ViewSnapshot> list);
    }

    public SyncEngine(LocalStore localStore, RemoteStore remoteStore, User user, int i) {
        this.localStore = localStore;
        this.remoteStore = remoteStore;
        this.maxConcurrentLimboResolutions = i;
        this.currentUser = user;
    }

    private void addUserCallback(int i, TaskCompletionSource<Void> taskCompletionSource) {
        Map<Integer, TaskCompletionSource<Void>> map = this.mutationUserCallbacks.get(this.currentUser);
        if (map == null) {
            map = new HashMap<>();
            this.mutationUserCallbacks.put(this.currentUser, map);
        }
        map.put(Integer.valueOf(i), taskCompletionSource);
    }

    private void assertCallback(String str) {
        Assert.hardAssert(this.syncEngineListener != null, "Trying to call %s before setting callback", str);
    }

    private void emitNewSnapsAndNotifyLocalStore(ImmutableSortedMap<DocumentKey, Document> immutableSortedMap, @Nullable RemoteEvent remoteEvent) {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        Iterator<Map.Entry<Query, QueryView>> it = this.queryViewsByQuery.entrySet().iterator();
        while (it.hasNext()) {
            QueryView value = it.next().getValue();
            View view = value.getView();
            View.DocumentChanges documentChangesComputeDocChanges = view.computeDocChanges(immutableSortedMap);
            if (documentChangesComputeDocChanges.needsRefill()) {
                documentChangesComputeDocChanges = view.computeDocChanges(this.localStore.executeQuery(value.getQuery(), false).getDocuments(), documentChangesComputeDocChanges);
            }
            ViewChange viewChangeApplyChanges = value.getView().applyChanges(documentChangesComputeDocChanges, remoteEvent == null ? null : remoteEvent.getTargetChanges().get(Integer.valueOf(value.getTargetId())));
            updateTrackedLimboDocuments(viewChangeApplyChanges.getLimboChanges(), value.getTargetId());
            if (viewChangeApplyChanges.getSnapshot() != null) {
                arrayList.add(viewChangeApplyChanges.getSnapshot());
                arrayList2.add(LocalViewChanges.fromViewSnapshot(value.getTargetId(), viewChangeApplyChanges.getSnapshot()));
            }
        }
        this.syncEngineListener.onViewSnapshots(arrayList);
        this.localStore.notifyLocalViewChanges(arrayList2);
    }

    private boolean errorIsInteresting(za zaVar) {
        za.a aVar = zaVar.p;
        String str = zaVar.q;
        if (str == null) {
            str = "";
        }
        return (aVar == za.a.FAILED_PRECONDITION && str.contains("requires an index")) || aVar == za.a.PERMISSION_DENIED;
    }

    private void failOutstandingPendingWritesAwaitingTasks() {
        Iterator<Map.Entry<Integer, List<TaskCompletionSource<Void>>>> it = this.pendingWritesCallbacks.entrySet().iterator();
        while (it.hasNext()) {
            Iterator<TaskCompletionSource<Void>> it2 = it.next().getValue().iterator();
            while (it2.hasNext()) {
                it2.next().setException(new FirebaseFirestoreException("'waitForPendingWrites' task is cancelled due to User change.", FirebaseFirestoreException.Code.CANCELLED));
            }
        }
        this.pendingWritesCallbacks.clear();
    }

    private ViewSnapshot initializeViewAndComputeSnapshot(Query query, int i) {
        TargetChange targetChangeCreateSynthesizedTargetChangeForCurrentChange;
        QueryResult queryResultExecuteQuery = this.localStore.executeQuery(query, true);
        ViewSnapshot.SyncState syncState = ViewSnapshot.SyncState.NONE;
        if (this.queriesByTarget.get(Integer.valueOf(i)) != null) {
            targetChangeCreateSynthesizedTargetChangeForCurrentChange = TargetChange.createSynthesizedTargetChangeForCurrentChange(this.queryViewsByQuery.get(this.queriesByTarget.get(Integer.valueOf(i)).get(0)).getView().getSyncState() == ViewSnapshot.SyncState.SYNCED);
        } else {
            targetChangeCreateSynthesizedTargetChangeForCurrentChange = null;
        }
        View view = new View(query, queryResultExecuteQuery.getRemoteKeys());
        ViewChange viewChangeApplyChanges = view.applyChanges(view.computeDocChanges(queryResultExecuteQuery.getDocuments()), targetChangeCreateSynthesizedTargetChangeForCurrentChange);
        updateTrackedLimboDocuments(viewChangeApplyChanges.getLimboChanges(), i);
        this.queryViewsByQuery.put(query, new QueryView(query, i, view));
        if (!this.queriesByTarget.containsKey(Integer.valueOf(i))) {
            this.queriesByTarget.put(Integer.valueOf(i), new ArrayList(1));
        }
        this.queriesByTarget.get(Integer.valueOf(i)).add(query);
        return viewChangeApplyChanges.getSnapshot();
    }

    private void logErrorIfInteresting(za zaVar, String str, Object... objArr) {
        if (errorIsInteresting(zaVar)) {
            Logger.warn("Firestore", "%s: %s", String.format(str, objArr), zaVar);
        }
    }

    private void notifyUser(int i, @Nullable za zaVar) {
        Integer numValueOf;
        TaskCompletionSource<Void> taskCompletionSource;
        Map<Integer, TaskCompletionSource<Void>> map = this.mutationUserCallbacks.get(this.currentUser);
        if (map == null || (taskCompletionSource = map.get((numValueOf = Integer.valueOf(i)))) == null) {
            return;
        }
        if (zaVar != null) {
            taskCompletionSource.setException(Util.exceptionFromStatus(zaVar));
        } else {
            taskCompletionSource.setResult(null);
        }
        map.remove(numValueOf);
    }

    private void pumpEnqueuedLimboResolutions() {
        while (!this.enqueuedLimboResolutions.isEmpty() && this.activeLimboTargetsByKey.size() < this.maxConcurrentLimboResolutions) {
            Iterator<DocumentKey> it = this.enqueuedLimboResolutions.iterator();
            DocumentKey next = it.next();
            it.remove();
            int iNextId = this.targetIdGenerator.nextId();
            this.activeLimboResolutionsByTarget.put(Integer.valueOf(iNextId), new LimboResolution(next));
            this.activeLimboTargetsByKey.put(next, Integer.valueOf(iNextId));
            this.remoteStore.listen(new TargetData(Query.atPath(next.getPath()).toTarget(), iNextId, -1L, QueryPurpose.LIMBO_RESOLUTION));
        }
    }

    private void removeAndCleanupTarget(int i, za zaVar) {
        for (Query query : this.queriesByTarget.get(Integer.valueOf(i))) {
            this.queryViewsByQuery.remove(query);
            if (!zaVar.c()) {
                this.syncEngineListener.onError(query, zaVar);
                logErrorIfInteresting(zaVar, "Listen for %s failed", query);
            }
        }
        this.queriesByTarget.remove(Integer.valueOf(i));
        ImmutableSortedSet<DocumentKey> immutableSortedSetReferencesForId = this.limboDocumentRefs.referencesForId(i);
        this.limboDocumentRefs.removeReferencesForId(i);
        for (DocumentKey documentKey : immutableSortedSetReferencesForId) {
            if (!this.limboDocumentRefs.containsKey(documentKey)) {
                removeLimboTarget(documentKey);
            }
        }
    }

    private void removeLimboTarget(DocumentKey documentKey) {
        this.enqueuedLimboResolutions.remove(documentKey);
        Integer num = this.activeLimboTargetsByKey.get(documentKey);
        if (num != null) {
            this.remoteStore.stopListening(num.intValue());
            this.activeLimboTargetsByKey.remove(documentKey);
            this.activeLimboResolutionsByTarget.remove(num);
            pumpEnqueuedLimboResolutions();
        }
    }

    private void resolvePendingWriteTasks(int i) {
        if (this.pendingWritesCallbacks.containsKey(Integer.valueOf(i))) {
            Iterator<TaskCompletionSource<Void>> it = this.pendingWritesCallbacks.get(Integer.valueOf(i)).iterator();
            while (it.hasNext()) {
                it.next().setResult(null);
            }
            this.pendingWritesCallbacks.remove(Integer.valueOf(i));
        }
    }

    private void trackLimboChange(LimboDocumentChange limboDocumentChange) {
        DocumentKey key = limboDocumentChange.getKey();
        if (this.activeLimboTargetsByKey.containsKey(key) || this.enqueuedLimboResolutions.contains(key)) {
            return;
        }
        Logger.debug(TAG, "New document in limbo: %s", key);
        this.enqueuedLimboResolutions.add(key);
        pumpEnqueuedLimboResolutions();
    }

    private void updateTrackedLimboDocuments(List<LimboDocumentChange> list, int i) {
        for (LimboDocumentChange limboDocumentChange : list) {
            int iOrdinal = limboDocumentChange.getType().ordinal();
            if (iOrdinal == 0) {
                this.limboDocumentRefs.addReference(limboDocumentChange.getKey(), i);
                trackLimboChange(limboDocumentChange);
            } else {
                if (iOrdinal != 1) {
                    Assert.fail("Unknown limbo change type: %s", limboDocumentChange.getType());
                    throw null;
                }
                Logger.debug(TAG, "Document no longer in limbo: %s", limboDocumentChange.getKey());
                DocumentKey key = limboDocumentChange.getKey();
                this.limboDocumentRefs.removeReference(key, i);
                if (!this.limboDocumentRefs.containsKey(key)) {
                    removeLimboTarget(key);
                }
            }
        }
    }

    @VisibleForTesting
    public Map<DocumentKey, Integer> getActiveLimboDocumentResolutions() {
        return new HashMap(this.activeLimboTargetsByKey);
    }

    @VisibleForTesting
    public List<DocumentKey> getEnqueuedLimboDocumentResolutions() {
        return new ArrayList(this.enqueuedLimboResolutions);
    }

    @Override // com.google.firebase.firestore.remote.RemoteStore.RemoteStoreCallback
    public ImmutableSortedSet<DocumentKey> getRemoteKeysForTarget(int i) {
        LimboResolution limboResolution = this.activeLimboResolutionsByTarget.get(Integer.valueOf(i));
        if (limboResolution != null && limboResolution.receivedDocument) {
            return DocumentKey.EMPTY_KEY_SET.insert(limboResolution.key);
        }
        ImmutableSortedSet<DocumentKey> immutableSortedSetUnionWith = DocumentKey.EMPTY_KEY_SET;
        if (this.queriesByTarget.containsKey(Integer.valueOf(i))) {
            for (Query query : this.queriesByTarget.get(Integer.valueOf(i))) {
                if (this.queryViewsByQuery.containsKey(query)) {
                    immutableSortedSetUnionWith = immutableSortedSetUnionWith.unionWith(this.queryViewsByQuery.get(query).getView().getSyncedDocuments());
                }
            }
        }
        return immutableSortedSetUnionWith;
    }

    public void handleCredentialChange(User user) {
        boolean z = !this.currentUser.equals(user);
        this.currentUser = user;
        if (z) {
            failOutstandingPendingWritesAwaitingTasks();
            emitNewSnapsAndNotifyLocalStore(this.localStore.handleUserChange(user), null);
        }
        this.remoteStore.handleCredentialChange();
    }

    @Override // com.google.firebase.firestore.remote.RemoteStore.RemoteStoreCallback
    public void handleOnlineStateChange(OnlineState onlineState) {
        assertCallback("handleOnlineStateChange");
        ArrayList arrayList = new ArrayList();
        Iterator<Map.Entry<Query, QueryView>> it = this.queryViewsByQuery.entrySet().iterator();
        while (it.hasNext()) {
            ViewChange viewChangeApplyOnlineStateChange = it.next().getValue().getView().applyOnlineStateChange(onlineState);
            Assert.hardAssert(viewChangeApplyOnlineStateChange.getLimboChanges().isEmpty(), "OnlineState should not affect limbo documents.", new Object[0]);
            if (viewChangeApplyOnlineStateChange.getSnapshot() != null) {
                arrayList.add(viewChangeApplyOnlineStateChange.getSnapshot());
            }
        }
        this.syncEngineListener.onViewSnapshots(arrayList);
        this.syncEngineListener.handleOnlineStateChange(onlineState);
    }

    @Override // com.google.firebase.firestore.remote.RemoteStore.RemoteStoreCallback
    public void handleRejectedListen(int i, za zaVar) {
        assertCallback("handleRejectedListen");
        LimboResolution limboResolution = this.activeLimboResolutionsByTarget.get(Integer.valueOf(i));
        DocumentKey documentKey = limboResolution != null ? limboResolution.key : null;
        if (documentKey == null) {
            this.localStore.releaseTarget(i);
            removeAndCleanupTarget(i, zaVar);
        } else {
            this.activeLimboTargetsByKey.remove(documentKey);
            this.activeLimboResolutionsByTarget.remove(Integer.valueOf(i));
            pumpEnqueuedLimboResolutions();
            handleRemoteEvent(new RemoteEvent(SnapshotVersion.NONE, Collections.emptyMap(), Collections.emptySet(), Collections.singletonMap(documentKey, MutableDocument.newNoDocument(documentKey, SnapshotVersion.NONE)), Collections.singleton(documentKey)));
        }
    }

    @Override // com.google.firebase.firestore.remote.RemoteStore.RemoteStoreCallback
    public void handleRejectedWrite(int i, za zaVar) {
        assertCallback("handleRejectedWrite");
        ImmutableSortedMap<DocumentKey, Document> immutableSortedMapRejectBatch = this.localStore.rejectBatch(i);
        if (!immutableSortedMapRejectBatch.isEmpty()) {
            logErrorIfInteresting(zaVar, "Write failed at %s", immutableSortedMapRejectBatch.getMinKey().getPath());
        }
        notifyUser(i, zaVar);
        resolvePendingWriteTasks(i);
        emitNewSnapsAndNotifyLocalStore(immutableSortedMapRejectBatch, null);
    }

    @Override // com.google.firebase.firestore.remote.RemoteStore.RemoteStoreCallback
    public void handleRemoteEvent(RemoteEvent remoteEvent) {
        assertCallback("handleRemoteEvent");
        for (Map.Entry<Integer, TargetChange> entry : remoteEvent.getTargetChanges().entrySet()) {
            Integer key = entry.getKey();
            TargetChange value = entry.getValue();
            LimboResolution limboResolution = this.activeLimboResolutionsByTarget.get(key);
            if (limboResolution != null) {
                Assert.hardAssert(value.getRemovedDocuments().size() + (value.getModifiedDocuments().size() + value.getAddedDocuments().size()) <= 1, "Limbo resolution for single document contains multiple changes.", new Object[0]);
                if (value.getAddedDocuments().size() > 0) {
                    limboResolution.receivedDocument = true;
                } else if (value.getModifiedDocuments().size() > 0) {
                    Assert.hardAssert(limboResolution.receivedDocument, "Received change for limbo target document without add.", new Object[0]);
                } else if (value.getRemovedDocuments().size() > 0) {
                    Assert.hardAssert(limboResolution.receivedDocument, "Received remove for limbo target document without add.", new Object[0]);
                    limboResolution.receivedDocument = false;
                }
            }
        }
        emitNewSnapsAndNotifyLocalStore(this.localStore.applyRemoteEvent(remoteEvent), remoteEvent);
    }

    @Override // com.google.firebase.firestore.remote.RemoteStore.RemoteStoreCallback
    public void handleSuccessfulWrite(MutationBatchResult mutationBatchResult) {
        assertCallback("handleSuccessfulWrite");
        notifyUser(mutationBatchResult.getBatch().getBatchId(), null);
        resolvePendingWriteTasks(mutationBatchResult.getBatch().getBatchId());
        emitNewSnapsAndNotifyLocalStore(this.localStore.acknowledgeBatch(mutationBatchResult), null);
    }

    public int listen(Query query) {
        assertCallback("listen");
        Assert.hardAssert(!this.queryViewsByQuery.containsKey(query), "We already listen to query: %s", query);
        TargetData targetDataAllocateTarget = this.localStore.allocateTarget(query.toTarget());
        this.syncEngineListener.onViewSnapshots(Collections.singletonList(initializeViewAndComputeSnapshot(query, targetDataAllocateTarget.getTargetId())));
        this.remoteStore.listen(targetDataAllocateTarget);
        return targetDataAllocateTarget.getTargetId();
    }

    public void loadBundle(BundleReader bundleReader, LoadBundleTask loadBundleTask) {
        try {
            try {
                BundleMetadata bundleMetadata = bundleReader.getBundleMetadata();
                if (this.localStore.hasNewerBundle(bundleMetadata)) {
                    loadBundleTask.setResult(LoadBundleTaskProgress.forSuccess(bundleMetadata));
                    try {
                        bundleReader.close();
                        return;
                    } catch (IOException e2) {
                        Logger.warn("SyncEngine", "Exception while closing bundle", e2);
                        return;
                    }
                }
                loadBundleTask.updateProgress(LoadBundleTaskProgress.forInitial(bundleMetadata));
                BundleLoader bundleLoader = new BundleLoader(this.localStore, bundleMetadata);
                long j = 0;
                while (true) {
                    BundleElement nextElement = bundleReader.getNextElement();
                    if (nextElement == null) {
                        emitNewSnapsAndNotifyLocalStore(bundleLoader.applyChanges(), null);
                        this.localStore.saveBundle(bundleMetadata);
                        loadBundleTask.setResult(LoadBundleTaskProgress.forSuccess(bundleMetadata));
                        try {
                            bundleReader.close();
                            return;
                        } catch (IOException e3) {
                            Logger.warn("SyncEngine", "Exception while closing bundle", e3);
                            return;
                        }
                    }
                    long bytesRead = bundleReader.getBytesRead();
                    LoadBundleTaskProgress loadBundleTaskProgressAddElement = bundleLoader.addElement(nextElement, bytesRead - j);
                    if (loadBundleTaskProgressAddElement != null) {
                        loadBundleTask.updateProgress(loadBundleTaskProgressAddElement);
                    }
                    j = bytesRead;
                }
            } catch (Exception e4) {
                Logger.warn("Firestore", "Loading bundle failed : %s", e4);
                loadBundleTask.setException(new FirebaseFirestoreException("Bundle failed to load", FirebaseFirestoreException.Code.INVALID_ARGUMENT, e4));
                try {
                    bundleReader.close();
                } catch (IOException e5) {
                    Logger.warn("SyncEngine", "Exception while closing bundle", e5);
                }
            }
        } catch (Throwable th) {
            try {
                bundleReader.close();
            } catch (IOException e6) {
                Logger.warn("SyncEngine", "Exception while closing bundle", e6);
            }
            throw th;
        }
    }

    public void registerPendingWritesTask(TaskCompletionSource<Void> taskCompletionSource) {
        if (!this.remoteStore.canUseNetwork()) {
            Logger.debug(TAG, "The network is disabled. The task returned by 'awaitPendingWrites()' will not complete until the network is enabled.", new Object[0]);
        }
        int highestUnacknowledgedBatchId = this.localStore.getHighestUnacknowledgedBatchId();
        if (highestUnacknowledgedBatchId == -1) {
            taskCompletionSource.setResult(null);
            return;
        }
        if (!this.pendingWritesCallbacks.containsKey(Integer.valueOf(highestUnacknowledgedBatchId))) {
            this.pendingWritesCallbacks.put(Integer.valueOf(highestUnacknowledgedBatchId), new ArrayList());
        }
        this.pendingWritesCallbacks.get(Integer.valueOf(highestUnacknowledgedBatchId)).add(taskCompletionSource);
    }

    public void setCallback(SyncEngineCallback syncEngineCallback) {
        this.syncEngineListener = syncEngineCallback;
    }

    public void stopListening(Query query) {
        assertCallback("stopListening");
        QueryView queryView = this.queryViewsByQuery.get(query);
        Assert.hardAssert(queryView != null, "Trying to stop listening to a query not found", new Object[0]);
        this.queryViewsByQuery.remove(query);
        int targetId = queryView.getTargetId();
        List<Query> list = this.queriesByTarget.get(Integer.valueOf(targetId));
        list.remove(query);
        if (list.isEmpty()) {
            this.localStore.releaseTarget(targetId);
            this.remoteStore.stopListening(targetId);
            removeAndCleanupTarget(targetId, za.f1936c);
        }
    }

    public <TResult> Task<TResult> transaction(AsyncQueue asyncQueue, Function<Transaction, Task<TResult>> function) {
        return new TransactionRunner(asyncQueue, this.remoteStore, function).run();
    }

    public void writeMutations(List<Mutation> list, TaskCompletionSource<Void> taskCompletionSource) {
        assertCallback("writeMutations");
        LocalWriteResult localWriteResultWriteLocally = this.localStore.writeLocally(list);
        addUserCallback(localWriteResultWriteLocally.getBatchId(), taskCompletionSource);
        emitNewSnapsAndNotifyLocalStore(localWriteResultWriteLocally.getChanges(), null);
        this.remoteStore.fillWritePipeline();
    }
}
