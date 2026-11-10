package com.google.firebase.firestore.local;

import android.util.SparseArray;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.google.firebase.Timestamp;
import com.google.firebase.database.collection.ImmutableSortedMap;
import com.google.firebase.database.collection.ImmutableSortedSet;
import com.google.firebase.firestore.auth.User;
import com.google.firebase.firestore.bundle.BundleCallback;
import com.google.firebase.firestore.bundle.BundleMetadata;
import com.google.firebase.firestore.bundle.NamedQuery;
import com.google.firebase.firestore.core.Query;
import com.google.firebase.firestore.core.Target;
import com.google.firebase.firestore.core.TargetIdGenerator;
import com.google.firebase.firestore.model.Document;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.FieldIndex;
import com.google.firebase.firestore.model.MutableDocument;
import com.google.firebase.firestore.model.ObjectValue;
import com.google.firebase.firestore.model.ResourcePath;
import com.google.firebase.firestore.model.SnapshotVersion;
import com.google.firebase.firestore.model.mutation.Mutation;
import com.google.firebase.firestore.model.mutation.MutationBatch;
import com.google.firebase.firestore.model.mutation.MutationBatchResult;
import com.google.firebase.firestore.model.mutation.PatchMutation;
import com.google.firebase.firestore.model.mutation.Precondition;
import com.google.firebase.firestore.remote.RemoteEvent;
import com.google.firebase.firestore.remote.TargetChange;
import com.google.firebase.firestore.util.Assert;
import com.google.firebase.firestore.util.Consumer;
import com.google.firebase.firestore.util.Logger;
import com.google.firebase.firestore.util.Supplier;
import com.google.firebase.firestore.util.Util;
import com.google.protobuf.ByteString;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public final class LocalStore implements BundleCallback {
    public static final long RESUME_TOKEN_MAX_AGE_SECONDS = TimeUnit.MINUTES.toSeconds(5);
    public final BundleCache bundleCache;
    public DocumentOverlayCache documentOverlayCache;
    public final IndexBackfiller indexBackfiller;
    public IndexManager indexManager;
    public LocalDocumentsView localDocuments;
    public final ReferenceSet localViewReferences;
    public MutationQueue mutationQueue;
    public final Persistence persistence;
    public final SparseArray<TargetData> queryDataByTarget;
    public final QueryEngine queryEngine;
    public final RemoteDocumentCache remoteDocuments;
    public final TargetCache targetCache;
    public final Map<Target, Integer> targetIdByTarget;
    public final TargetIdGenerator targetIdGenerator;

    /* JADX INFO: Access modifiers changed from: private */
    public static class AllocateQueryHolder {
        public TargetData cached;
        public int targetId;

        public AllocateQueryHolder() {
        }

        public /* synthetic */ AllocateQueryHolder(AnonymousClass1 anonymousClass1) {
        }
    }

    public LocalStore(Persistence persistence, IndexBackfiller indexBackfiller, QueryEngine queryEngine, User user) {
        Assert.hardAssert(persistence.isStarted(), "LocalStore was passed an unstarted persistence implementation", new Object[0]);
        this.persistence = persistence;
        this.targetCache = persistence.getTargetCache();
        this.bundleCache = persistence.getBundleCache();
        this.targetIdGenerator = TargetIdGenerator.forTargetCache(this.targetCache.getHighestTargetId());
        this.indexManager = persistence.getIndexManager(user);
        this.mutationQueue = persistence.getMutationQueue(user, this.indexManager);
        this.documentOverlayCache = persistence.getDocumentOverlay(user);
        this.remoteDocuments = persistence.getRemoteDocumentCache();
        this.localDocuments = new LocalDocumentsView(this.remoteDocuments, this.mutationQueue, this.documentOverlayCache, this.indexManager);
        this.queryEngine = queryEngine;
        this.indexBackfiller = indexBackfiller;
        queryEngine.initialize(this.localDocuments, this.indexManager);
        this.localViewReferences = new ReferenceSet();
        persistence.getReferenceDelegate().setInMemoryPins(this.localViewReferences);
        this.remoteDocuments.setIndexManager(this.indexManager);
        indexBackfiller.setIndexManager(this.indexManager);
        indexBackfiller.setLocalDocumentsView(this.localDocuments);
        this.queryDataByTarget = new SparseArray<>();
        this.targetIdByTarget = new HashMap();
    }

    private void applyWriteToRemoteDocuments(MutationBatchResult mutationBatchResult) {
        MutationBatch batch = mutationBatchResult.getBatch();
        for (DocumentKey documentKey : batch.getKeys()) {
            MutableDocument mutableDocument = this.remoteDocuments.get(documentKey);
            SnapshotVersion snapshotVersion = mutationBatchResult.getDocVersions().get(documentKey);
            Assert.hardAssert(snapshotVersion != null, "docVersions should contain every doc in the write.", new Object[0]);
            if (mutableDocument.getVersion().compareTo(snapshotVersion) < 0) {
                batch.applyToRemoteDocument(mutableDocument, mutationBatchResult);
                if (mutableDocument.isValidDocument()) {
                    this.remoteDocuments.add(mutableDocument, mutationBatchResult.getCommitVersion());
                }
            }
        }
        this.mutationQueue.removeMutationBatch(batch);
    }

    @NonNull
    private Set<DocumentKey> getKeysWithTransformResults(MutationBatchResult mutationBatchResult) {
        HashSet hashSet = new HashSet();
        for (int i = 0; i < mutationBatchResult.getMutationResults().size(); i++) {
            if (!mutationBatchResult.getMutationResults().get(i).getTransformResults().isEmpty()) {
                hashSet.add(mutationBatchResult.getBatch().getMutations().get(i).getKey());
            }
        }
        return hashSet;
    }

    public static Target newUmbrellaTarget(String str) {
        return Query.atPath(ResourcePath.fromString("__bundle__/docs/" + str)).toTarget();
    }

    private DocumentChangeResult populateDocumentChanges(Map<DocumentKey, MutableDocument> map, @Nullable Map<DocumentKey, SnapshotVersion> map2, SnapshotVersion snapshotVersion) {
        HashMap map3 = new HashMap();
        ArrayList arrayList = new ArrayList();
        HashSet hashSet = new HashSet();
        Map<DocumentKey, MutableDocument> all = this.remoteDocuments.getAll(map.keySet());
        for (Map.Entry<DocumentKey, MutableDocument> entry : map.entrySet()) {
            DocumentKey key = entry.getKey();
            MutableDocument value = entry.getValue();
            MutableDocument mutableDocument = all.get(key);
            SnapshotVersion snapshotVersion2 = map2 != null ? map2.get(key) : snapshotVersion;
            if (value.isFoundDocument() != mutableDocument.isFoundDocument()) {
                hashSet.add(key);
            }
            if (value.isNoDocument() && value.getVersion().equals(SnapshotVersion.NONE)) {
                arrayList.add(value.getKey());
                map3.put(key, value);
            } else if (!mutableDocument.isValidDocument() || value.getVersion().compareTo(mutableDocument.getVersion()) > 0 || (value.getVersion().compareTo(mutableDocument.getVersion()) == 0 && mutableDocument.hasPendingWrites())) {
                Assert.hardAssert(!SnapshotVersion.NONE.equals(snapshotVersion2), "Cannot add a document when the remote version is zero", new Object[0]);
                this.remoteDocuments.add(value, snapshotVersion2);
                map3.put(key, value);
            } else {
                Logger.debug("LocalStore", "Ignoring outdated watch update for %s.Current version: %s  Watch version: %s", key, mutableDocument.getVersion(), value.getVersion());
            }
        }
        this.remoteDocuments.removeAll(arrayList);
        return new DocumentChangeResult(map3, hashSet, null);
    }

    public static boolean shouldPersistTargetData(TargetData targetData, TargetData targetData2, TargetChange targetChange) {
        if (!targetData.getResumeToken().isEmpty() && targetData2.getSnapshotVersion().getTimestamp().getSeconds() - targetData.getSnapshotVersion().getTimestamp().getSeconds() < RESUME_TOKEN_MAX_AGE_SECONDS) {
            return targetChange.getRemovedDocuments().size() + (targetChange.getModifiedDocuments().size() + targetChange.getAddedDocuments().size()) > 0;
        }
        return true;
    }

    private void startIndexManager() {
        this.persistence.runTransaction("Start IndexManager", new Runnable() { // from class: b.e.b.d.d.i
            @Override // java.lang.Runnable
            public final void run() {
                this.f407a.b();
            }
        });
    }

    private void startMutationQueue() {
        this.persistence.runTransaction("Start MutationQueue", new Runnable() { // from class: b.e.b.d.d.p
            @Override // java.lang.Runnable
            public final void run() {
                this.f437a.c();
            }
        });
    }

    public /* synthetic */ ImmutableSortedMap a(ImmutableSortedMap immutableSortedMap, TargetData targetData) {
        ImmutableSortedSet<DocumentKey> immutableSortedSetInsert = DocumentKey.EMPTY_KEY_SET;
        HashMap map = new HashMap();
        HashMap map2 = new HashMap();
        Iterator it = immutableSortedMap.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            DocumentKey documentKey = (DocumentKey) entry.getKey();
            MutableDocument mutableDocument = (MutableDocument) entry.getValue();
            if (mutableDocument.isFoundDocument()) {
                immutableSortedSetInsert = immutableSortedSetInsert.insert(documentKey);
            }
            map.put(documentKey, mutableDocument);
            map2.put(documentKey, mutableDocument.getVersion());
        }
        this.targetCache.removeMatchingKeysForTargetId(targetData.getTargetId());
        this.targetCache.addMatchingKeys(immutableSortedSetInsert, targetData.getTargetId());
        DocumentChangeResult documentChangeResultPopulateDocumentChanges = populateDocumentChanges(map, map2, SnapshotVersion.NONE);
        return this.localDocuments.getLocalViewOfDocuments(documentChangeResultPopulateDocumentChanges.changedDocuments, documentChangeResultPopulateDocumentChanges.existenceChangedKeys);
    }

    public ImmutableSortedMap<DocumentKey, Document> acknowledgeBatch(final MutationBatchResult mutationBatchResult) {
        return (ImmutableSortedMap) this.persistence.runTransaction("Acknowledge batch", new Supplier() { // from class: b.e.b.d.d.j
            @Override // com.google.firebase.firestore.util.Supplier
            public final Object get() {
                return this.f409a.a(mutationBatchResult);
            }
        });
    }

    public TargetData allocateTarget(final Target target) {
        int targetId;
        TargetData targetData = this.targetCache.getTargetData(target);
        if (targetData != null) {
            targetId = targetData.getTargetId();
        } else {
            final AllocateQueryHolder allocateQueryHolder = new AllocateQueryHolder(null);
            this.persistence.runTransaction("Allocate target", new Runnable() { // from class: b.e.b.d.d.r
                @Override // java.lang.Runnable
                public final void run() {
                    this.f442a.a(allocateQueryHolder, target);
                }
            });
            targetId = allocateQueryHolder.targetId;
            targetData = allocateQueryHolder.cached;
        }
        if (this.queryDataByTarget.get(targetId) == null) {
            this.queryDataByTarget.put(targetId, targetData);
            this.targetIdByTarget.put(target, Integer.valueOf(targetId));
        }
        return targetData;
    }

    @Override // com.google.firebase.firestore.bundle.BundleCallback
    public ImmutableSortedMap<DocumentKey, Document> applyBundledDocuments(final ImmutableSortedMap<DocumentKey, MutableDocument> immutableSortedMap, String str) {
        final TargetData targetDataAllocateTarget = allocateTarget(newUmbrellaTarget(str));
        return (ImmutableSortedMap) this.persistence.runTransaction("Apply bundle documents", new Supplier() { // from class: b.e.b.d.d.v
            @Override // com.google.firebase.firestore.util.Supplier
            public final Object get() {
                return this.f459a.a(immutableSortedMap, targetDataAllocateTarget);
            }
        });
    }

    public ImmutableSortedMap<DocumentKey, Document> applyRemoteEvent(final RemoteEvent remoteEvent) {
        final SnapshotVersion snapshotVersion = remoteEvent.getSnapshotVersion();
        return (ImmutableSortedMap) this.persistence.runTransaction("Apply remote event", new Supplier() { // from class: b.e.b.d.d.w
            @Override // com.google.firebase.firestore.util.Supplier
            public final Object get() {
                return this.f463a.a(remoteEvent, snapshotVersion);
            }
        });
    }

    public /* synthetic */ void b() {
        this.indexManager.start();
    }

    public /* synthetic */ void c() {
        this.mutationQueue.start();
    }

    public LruGarbageCollector.Results collectGarbage(final LruGarbageCollector lruGarbageCollector) {
        return (LruGarbageCollector.Results) this.persistence.runTransaction("Collect garbage", new Supplier() { // from class: b.e.b.d.d.u
            @Override // com.google.firebase.firestore.util.Supplier
            public final Object get() {
                return this.f455a.a(lruGarbageCollector);
            }
        });
    }

    public void configureFieldIndexes(final List<FieldIndex> list) {
        this.persistence.runTransaction("Configure indexes", new Runnable() { // from class: b.e.b.d.d.h
            @Override // java.lang.Runnable
            public final void run() {
                this.f404a.a(list);
            }
        });
    }

    public QueryResult executeQuery(Query query, boolean z) {
        TargetData targetData = getTargetData(query.toTarget());
        SnapshotVersion lastLimboFreeSnapshotVersion = SnapshotVersion.NONE;
        ImmutableSortedSet<DocumentKey> matchingKeysForTargetId = DocumentKey.EMPTY_KEY_SET;
        if (targetData != null) {
            lastLimboFreeSnapshotVersion = targetData.getLastLimboFreeSnapshotVersion();
            matchingKeysForTargetId = this.targetCache.getMatchingKeysForTargetId(targetData.getTargetId());
        }
        QueryEngine queryEngine = this.queryEngine;
        if (!z) {
            lastLimboFreeSnapshotVersion = SnapshotVersion.NONE;
        }
        return new QueryResult(queryEngine.getDocumentsMatchingQuery(query, lastLimboFreeSnapshotVersion, matchingKeysForTargetId), matchingKeysForTargetId);
    }

    @VisibleForTesting
    public Collection<FieldIndex> getFieldIndexes() {
        return (Collection) this.persistence.runTransaction("Get indexes", new Supplier() { // from class: b.e.b.d.d.f
            @Override // com.google.firebase.firestore.util.Supplier
            public final Object get() {
                return this.f399a.a();
            }
        });
    }

    public int getHighestUnacknowledgedBatchId() {
        return this.mutationQueue.getHighestUnacknowledgedBatchId();
    }

    public SnapshotVersion getLastRemoteSnapshotVersion() {
        return this.targetCache.getLastRemoteSnapshotVersion();
    }

    public ByteString getLastStreamToken() {
        return this.mutationQueue.getLastStreamToken();
    }

    @Nullable
    public NamedQuery getNamedQuery(final String str) {
        return (NamedQuery) this.persistence.runTransaction("Get named query", new Supplier() { // from class: b.e.b.d.d.l
            @Override // com.google.firebase.firestore.util.Supplier
            public final Object get() {
                return this.f418a.a(str);
            }
        });
    }

    @Nullable
    public MutationBatch getNextMutationBatch(int i) {
        return this.mutationQueue.getNextMutationBatchAfterBatchId(i);
    }

    public ImmutableSortedSet<DocumentKey> getRemoteDocumentKeys(int i) {
        return this.targetCache.getMatchingKeysForTargetId(i);
    }

    @Nullable
    @VisibleForTesting
    public TargetData getTargetData(Target target) {
        Integer num = this.targetIdByTarget.get(target);
        return num != null ? this.queryDataByTarget.get(num.intValue()) : this.targetCache.getTargetData(target);
    }

    public ImmutableSortedMap<DocumentKey, Document> handleUserChange(User user) {
        List<MutationBatch> allMutationBatches = this.mutationQueue.getAllMutationBatches();
        this.indexManager = this.persistence.getIndexManager(user);
        this.mutationQueue = this.persistence.getMutationQueue(user, this.indexManager);
        this.documentOverlayCache = this.persistence.getDocumentOverlay(user);
        startIndexManager();
        startMutationQueue();
        List<MutationBatch> allMutationBatches2 = this.mutationQueue.getAllMutationBatches();
        this.localDocuments = new LocalDocumentsView(this.remoteDocuments, this.mutationQueue, this.documentOverlayCache, this.indexManager);
        this.queryEngine.initialize(this.localDocuments, this.indexManager);
        this.remoteDocuments.setIndexManager(this.indexManager);
        this.indexBackfiller.setIndexManager(this.indexManager);
        this.indexBackfiller.setLocalDocumentsView(this.localDocuments);
        ImmutableSortedSet<DocumentKey> immutableSortedSetInsert = DocumentKey.EMPTY_KEY_SET;
        Iterator it = Arrays.asList(allMutationBatches, allMutationBatches2).iterator();
        while (it.hasNext()) {
            Iterator it2 = ((List) it.next()).iterator();
            while (it2.hasNext()) {
                Iterator<Mutation> it3 = ((MutationBatch) it2.next()).getMutations().iterator();
                while (it3.hasNext()) {
                    immutableSortedSetInsert = immutableSortedSetInsert.insert(it3.next().getKey());
                }
            }
        }
        return this.localDocuments.getDocuments(immutableSortedSetInsert);
    }

    public boolean hasNewerBundle(final BundleMetadata bundleMetadata) {
        return ((Boolean) this.persistence.runTransaction("Has newer bundle", new Supplier() { // from class: b.e.b.d.d.q
            @Override // com.google.firebase.firestore.util.Supplier
            public final Object get() {
                return this.f439a.a(bundleMetadata);
            }
        })).booleanValue();
    }

    public void notifyLocalViewChanges(final List<LocalViewChanges> list) {
        this.persistence.runTransaction("notifyLocalViewChanges", new Runnable() { // from class: b.e.b.d.d.t
            @Override // java.lang.Runnable
            public final void run() {
                this.f452a.b(list);
            }
        });
    }

    public Document readDocument(DocumentKey documentKey) {
        return this.localDocuments.getDocument(documentKey);
    }

    public ImmutableSortedMap<DocumentKey, Document> rejectBatch(final int i) {
        return (ImmutableSortedMap) this.persistence.runTransaction("Reject batch", new Supplier() { // from class: b.e.b.d.d.g
            @Override // com.google.firebase.firestore.util.Supplier
            public final Object get() {
                return this.f401a.a(i);
            }
        });
    }

    public void releaseTarget(final int i) {
        this.persistence.runTransaction("Release target", new Runnable() { // from class: b.e.b.d.d.m
            @Override // java.lang.Runnable
            public final void run() {
                this.f425a.b(i);
            }
        });
    }

    @Override // com.google.firebase.firestore.bundle.BundleCallback
    public void saveBundle(final BundleMetadata bundleMetadata) {
        this.persistence.runTransaction("Save bundle", new Runnable() { // from class: b.e.b.d.d.o
            @Override // java.lang.Runnable
            public final void run() {
                this.f433a.b(bundleMetadata);
            }
        });
    }

    @Override // com.google.firebase.firestore.bundle.BundleCallback
    public void saveNamedQuery(final NamedQuery namedQuery, final ImmutableSortedSet<DocumentKey> immutableSortedSet) {
        final TargetData targetDataAllocateTarget = allocateTarget(namedQuery.getBundledQuery().getTarget());
        final int targetId = targetDataAllocateTarget.getTargetId();
        this.persistence.runTransaction("Saved named query", new Runnable() { // from class: b.e.b.d.d.k
            @Override // java.lang.Runnable
            public final void run() {
                this.f412a.a(namedQuery, targetDataAllocateTarget, targetId, immutableSortedSet);
            }
        });
    }

    public void setLastStreamToken(final ByteString byteString) {
        this.persistence.runTransaction("Set stream token", new Runnable() { // from class: b.e.b.d.d.n
            @Override // java.lang.Runnable
            public final void run() {
                this.f430a.a(byteString);
            }
        });
    }

    public void start() {
        this.persistence.getOverlayMigrationManager().run();
        startIndexManager();
        startMutationQueue();
    }

    public LocalWriteResult writeLocally(final List<Mutation> list) {
        final Timestamp timestampNow = Timestamp.now();
        final HashSet hashSet = new HashSet();
        Iterator<Mutation> it = list.iterator();
        while (it.hasNext()) {
            hashSet.add(it.next().getKey());
        }
        return (LocalWriteResult) this.persistence.runTransaction("Locally write mutations", new Supplier() { // from class: b.e.b.d.d.s
            @Override // com.google.firebase.firestore.util.Supplier
            public final Object get() {
                return this.f446a.a(hashSet, list, timestampNow);
            }
        });
    }

    public /* synthetic */ void b(List list) {
        Iterator it = list.iterator();
        while (it.hasNext()) {
            LocalViewChanges localViewChanges = (LocalViewChanges) it.next();
            int targetId = localViewChanges.getTargetId();
            this.localViewReferences.addReferences(localViewChanges.getAdded(), targetId);
            ImmutableSortedSet<DocumentKey> removed = localViewChanges.getRemoved();
            Iterator<DocumentKey> it2 = removed.iterator();
            while (it2.hasNext()) {
                this.persistence.getReferenceDelegate().removeReference(it2.next());
            }
            this.localViewReferences.removeReferences(removed, targetId);
            if (!localViewChanges.isFromCache()) {
                TargetData targetData = this.queryDataByTarget.get(targetId);
                Assert.hardAssert(targetData != null, "Can't set limbo-free snapshot version for unknown target: %s", Integer.valueOf(targetId));
                this.queryDataByTarget.put(targetId, targetData.withLastLimboFreeSnapshotVersion(targetData.getSnapshotVersion()));
            }
        }
    }

    private static class DocumentChangeResult {
        public final Map<DocumentKey, MutableDocument> changedDocuments;
        public final Set<DocumentKey> existenceChangedKeys;

        public DocumentChangeResult(Map<DocumentKey, MutableDocument> map, Set<DocumentKey> set) {
            this.changedDocuments = map;
            this.existenceChangedKeys = set;
        }

        public /* synthetic */ DocumentChangeResult(Map map, Set set, AnonymousClass1 anonymousClass1) {
            this.changedDocuments = map;
            this.existenceChangedKeys = set;
        }
    }

    public /* synthetic */ void b(BundleMetadata bundleMetadata) {
        this.bundleCache.saveBundleMetadata(bundleMetadata);
    }

    public /* synthetic */ void b(int i) {
        TargetData targetData = this.queryDataByTarget.get(i);
        Assert.hardAssert(targetData != null, "Tried to release nonexistent target: %s", Integer.valueOf(i));
        Iterator<DocumentKey> it = this.localViewReferences.removeReferencesForId(i).iterator();
        while (it.hasNext()) {
            this.persistence.getReferenceDelegate().removeReference(it.next());
        }
        this.persistence.getReferenceDelegate().removeTarget(targetData);
        this.queryDataByTarget.remove(i);
        this.targetIdByTarget.remove(targetData.getTarget());
    }

    public /* synthetic */ LocalWriteResult a(Set set, List list, Timestamp timestamp) {
        ImmutableSortedMap<DocumentKey, Document> documents = this.localDocuments.getDocuments(set);
        ArrayList arrayList = new ArrayList();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            Mutation mutation = (Mutation) it.next();
            ObjectValue objectValueExtractTransformBaseValue = mutation.extractTransformBaseValue(documents.get(mutation.getKey()));
            if (objectValueExtractTransformBaseValue != null) {
                arrayList.add(new PatchMutation(mutation.getKey(), objectValueExtractTransformBaseValue, objectValueExtractTransformBaseValue.getFieldMask(), Precondition.exists(true)));
            }
        }
        MutationBatch mutationBatchAddMutationBatch = this.mutationQueue.addMutationBatch(timestamp, arrayList, list);
        this.documentOverlayCache.saveOverlays(mutationBatchAddMutationBatch.getBatchId(), mutationBatchAddMutationBatch.applyToLocalDocumentSet(documents));
        return new LocalWriteResult(mutationBatchAddMutationBatch.getBatchId(), documents);
    }

    public /* synthetic */ ImmutableSortedMap a(MutationBatchResult mutationBatchResult) {
        MutationBatch batch = mutationBatchResult.getBatch();
        this.mutationQueue.acknowledgeBatch(batch, mutationBatchResult.getStreamToken());
        applyWriteToRemoteDocuments(mutationBatchResult);
        this.mutationQueue.performConsistencyCheck();
        this.documentOverlayCache.removeOverlaysForBatchId(mutationBatchResult.getBatch().getBatchId());
        this.localDocuments.recalculateAndSaveOverlays(getKeysWithTransformResults(mutationBatchResult));
        return this.localDocuments.getDocuments(batch.getKeys());
    }

    public /* synthetic */ ImmutableSortedMap a(int i) {
        MutationBatch mutationBatchLookupMutationBatch = this.mutationQueue.lookupMutationBatch(i);
        Assert.hardAssert(mutationBatchLookupMutationBatch != null, "Attempt to reject nonexistent batch!", new Object[0]);
        this.mutationQueue.removeMutationBatch(mutationBatchLookupMutationBatch);
        this.mutationQueue.performConsistencyCheck();
        this.documentOverlayCache.removeOverlaysForBatchId(i);
        this.localDocuments.recalculateAndSaveOverlays(mutationBatchLookupMutationBatch.getKeys());
        return this.localDocuments.getDocuments(mutationBatchLookupMutationBatch.getKeys());
    }

    public /* synthetic */ void a(ByteString byteString) {
        this.mutationQueue.setLastStreamToken(byteString);
    }

    public /* synthetic */ ImmutableSortedMap a(RemoteEvent remoteEvent, SnapshotVersion snapshotVersion) {
        Map<Integer, TargetChange> targetChanges = remoteEvent.getTargetChanges();
        long currentSequenceNumber = this.persistence.getReferenceDelegate().getCurrentSequenceNumber();
        for (Map.Entry<Integer, TargetChange> entry : targetChanges.entrySet()) {
            int iIntValue = entry.getKey().intValue();
            TargetChange value = entry.getValue();
            TargetData targetData = this.queryDataByTarget.get(iIntValue);
            if (targetData != null) {
                this.targetCache.removeMatchingKeys(value.getRemovedDocuments(), iIntValue);
                this.targetCache.addMatchingKeys(value.getAddedDocuments(), iIntValue);
                TargetData targetDataWithSequenceNumber = targetData.withSequenceNumber(currentSequenceNumber);
                if (remoteEvent.getTargetMismatches().contains(Integer.valueOf(iIntValue))) {
                    targetDataWithSequenceNumber = targetDataWithSequenceNumber.withResumeToken(ByteString.EMPTY, SnapshotVersion.NONE).withLastLimboFreeSnapshotVersion(SnapshotVersion.NONE);
                } else if (!value.getResumeToken().isEmpty()) {
                    targetDataWithSequenceNumber = targetDataWithSequenceNumber.withResumeToken(value.getResumeToken(), remoteEvent.getSnapshotVersion());
                }
                this.queryDataByTarget.put(iIntValue, targetDataWithSequenceNumber);
                if (shouldPersistTargetData(targetData, targetDataWithSequenceNumber, value)) {
                    this.targetCache.updateTargetData(targetDataWithSequenceNumber);
                }
            }
        }
        Map<DocumentKey, MutableDocument> documentUpdates = remoteEvent.getDocumentUpdates();
        Set<DocumentKey> resolvedLimboDocuments = remoteEvent.getResolvedLimboDocuments();
        for (DocumentKey documentKey : documentUpdates.keySet()) {
            if (resolvedLimboDocuments.contains(documentKey)) {
                this.persistence.getReferenceDelegate().updateLimboDocument(documentKey);
            }
        }
        DocumentChangeResult documentChangeResultPopulateDocumentChanges = populateDocumentChanges(documentUpdates, null, remoteEvent.getSnapshotVersion());
        Map<DocumentKey, MutableDocument> map = documentChangeResultPopulateDocumentChanges.changedDocuments;
        SnapshotVersion lastRemoteSnapshotVersion = this.targetCache.getLastRemoteSnapshotVersion();
        if (!snapshotVersion.equals(SnapshotVersion.NONE)) {
            Assert.hardAssert(snapshotVersion.compareTo(lastRemoteSnapshotVersion) >= 0, "Watch stream reverted to previous snapshot?? (%s < %s)", snapshotVersion, lastRemoteSnapshotVersion);
            this.targetCache.setLastRemoteSnapshotVersion(snapshotVersion);
        }
        return this.localDocuments.getLocalViewOfDocuments(map, documentChangeResultPopulateDocumentChanges.existenceChangedKeys);
    }

    public /* synthetic */ void a(AllocateQueryHolder allocateQueryHolder, Target target) {
        allocateQueryHolder.targetId = this.targetIdGenerator.nextId();
        allocateQueryHolder.cached = new TargetData(target, allocateQueryHolder.targetId, this.persistence.getReferenceDelegate().getCurrentSequenceNumber(), QueryPurpose.LISTEN);
        this.targetCache.addTargetData(allocateQueryHolder.cached);
    }

    public /* synthetic */ Boolean a(BundleMetadata bundleMetadata) {
        BundleMetadata bundleMetadata2 = this.bundleCache.getBundleMetadata(bundleMetadata.getBundleId());
        return Boolean.valueOf(bundleMetadata2 != null && bundleMetadata2.getCreateTime().compareTo(bundleMetadata.getCreateTime()) >= 0);
    }

    public /* synthetic */ void a(NamedQuery namedQuery, TargetData targetData, int i, ImmutableSortedSet immutableSortedSet) {
        if (namedQuery.getReadTime().compareTo(targetData.getSnapshotVersion()) > 0) {
            TargetData targetDataWithResumeToken = targetData.withResumeToken(ByteString.EMPTY, namedQuery.getReadTime());
            this.queryDataByTarget.append(i, targetDataWithResumeToken);
            this.targetCache.updateTargetData(targetDataWithResumeToken);
            this.targetCache.removeMatchingKeysForTargetId(i);
            this.targetCache.addMatchingKeys(immutableSortedSet, i);
        }
        this.bundleCache.saveNamedQuery(namedQuery);
    }

    public /* synthetic */ NamedQuery a(String str) {
        return this.bundleCache.getNamedQuery(str);
    }

    public /* synthetic */ Collection a() {
        return this.indexManager.getFieldIndexes();
    }

    public /* synthetic */ void a(List list) {
        Collection<FieldIndex> fieldIndexes = this.indexManager.getFieldIndexes();
        Comparator<FieldIndex> comparator = FieldIndex.SEMANTIC_COMPARATOR;
        final IndexManager indexManager = this.indexManager;
        Objects.requireNonNull(indexManager);
        Consumer consumer = new Consumer() { // from class: b.e.b.d.d.Na
            @Override // com.google.firebase.firestore.util.Consumer
            public final void accept(Object obj) {
                indexManager.addFieldIndex((FieldIndex) obj);
            }
        };
        final IndexManager indexManager2 = this.indexManager;
        Objects.requireNonNull(indexManager2);
        Util.diffCollections(fieldIndexes, list, comparator, consumer, new Consumer() { // from class: b.e.b.d.d.Ma
            @Override // com.google.firebase.firestore.util.Consumer
            public final void accept(Object obj) {
                indexManager2.deleteFieldIndex((FieldIndex) obj);
            }
        });
    }

    public /* synthetic */ LruGarbageCollector.Results a(LruGarbageCollector lruGarbageCollector) {
        return lruGarbageCollector.collect(this.queryDataByTarget);
    }
}
