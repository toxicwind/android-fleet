package com.google.firebase.firestore.remote;

import androidx.annotation.Nullable;
import com.google.firebase.database.collection.ImmutableSortedSet;
import com.google.firebase.firestore.core.DocumentViewChange;
import com.google.firebase.firestore.core.Target;
import com.google.firebase.firestore.local.QueryPurpose;
import com.google.firebase.firestore.local.TargetData;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.MutableDocument;
import com.google.firebase.firestore.model.SnapshotVersion;
import com.google.firebase.firestore.util.Assert;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public class WatchChangeAggregator {
    public final TargetMetadataProvider targetMetadataProvider;
    public final Map<Integer, TargetState> targetStates = new HashMap();
    public Map<DocumentKey, MutableDocument> pendingDocumentUpdates = new HashMap();
    public Map<DocumentKey, Set<Integer>> pendingDocumentTargetMapping = new HashMap();
    public Set<Integer> pendingTargetResets = new HashSet();

    /* JADX INFO: renamed from: com.google.firebase.firestore.remote.WatchChangeAggregator$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        public static final /* synthetic */ int[] $SwitchMap$com$google$firebase$firestore$remote$WatchChange$WatchTargetChangeType = new int[WatchChange.WatchTargetChangeType.values().length];

        static {
            try {
                $SwitchMap$com$google$firebase$firestore$remote$WatchChange$WatchTargetChangeType[WatchChange.WatchTargetChangeType.NoChange.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$remote$WatchChange$WatchTargetChangeType[WatchChange.WatchTargetChangeType.Added.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$remote$WatchChange$WatchTargetChangeType[WatchChange.WatchTargetChangeType.Removed.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$remote$WatchChange$WatchTargetChangeType[WatchChange.WatchTargetChangeType.Current.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$remote$WatchChange$WatchTargetChangeType[WatchChange.WatchTargetChangeType.Reset.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    public interface TargetMetadataProvider {
        ImmutableSortedSet<DocumentKey> getRemoteKeysForTarget(int i);

        @Nullable
        TargetData getTargetDataForTarget(int i);
    }

    public WatchChangeAggregator(TargetMetadataProvider targetMetadataProvider) {
        this.targetMetadataProvider = targetMetadataProvider;
    }

    private void addDocumentToTarget(int i, MutableDocument mutableDocument) {
        if (queryDataForActiveTarget(i) != null) {
            ensureTargetState(i).addDocumentChange(mutableDocument.getKey(), this.targetMetadataProvider.getRemoteKeysForTarget(i).contains(mutableDocument.getKey()) ? DocumentViewChange.Type.MODIFIED : DocumentViewChange.Type.ADDED);
            this.pendingDocumentUpdates.put(mutableDocument.getKey(), mutableDocument);
            ensureDocumentTargetMapping(mutableDocument.getKey()).add(Integer.valueOf(i));
        }
    }

    private Set<Integer> ensureDocumentTargetMapping(DocumentKey documentKey) {
        Set<Integer> set = this.pendingDocumentTargetMapping.get(documentKey);
        if (set != null) {
            return set;
        }
        HashSet hashSet = new HashSet();
        this.pendingDocumentTargetMapping.put(documentKey, hashSet);
        return hashSet;
    }

    private TargetState ensureTargetState(int i) {
        TargetState targetState = this.targetStates.get(Integer.valueOf(i));
        if (targetState != null) {
            return targetState;
        }
        TargetState targetState2 = new TargetState();
        this.targetStates.put(Integer.valueOf(i), targetState2);
        return targetState2;
    }

    private int getCurrentDocumentCountForTarget(int i) {
        TargetChange targetChange = ensureTargetState(i).toTargetChange();
        return (targetChange.getAddedDocuments().size() + this.targetMetadataProvider.getRemoteKeysForTarget(i).size()) - targetChange.getRemovedDocuments().size();
    }

    private Collection<Integer> getTargetIds(WatchChange.WatchTargetChange watchTargetChange) {
        List<Integer> targetIds = watchTargetChange.getTargetIds();
        if (!targetIds.isEmpty()) {
            return targetIds;
        }
        ArrayList arrayList = new ArrayList();
        for (Integer num : this.targetStates.keySet()) {
            if (isActiveTarget(num.intValue())) {
                arrayList.add(num);
            }
        }
        return arrayList;
    }

    private boolean isActiveTarget(int i) {
        return queryDataForActiveTarget(i) != null;
    }

    @Nullable
    private TargetData queryDataForActiveTarget(int i) {
        TargetState targetState = this.targetStates.get(Integer.valueOf(i));
        if (targetState == null || !targetState.isPending()) {
            return this.targetMetadataProvider.getTargetDataForTarget(i);
        }
        return null;
    }

    private void removeDocumentFromTarget(int i, DocumentKey documentKey, @Nullable MutableDocument mutableDocument) {
        if (queryDataForActiveTarget(i) != null) {
            TargetState targetStateEnsureTargetState = ensureTargetState(i);
            if (this.targetMetadataProvider.getRemoteKeysForTarget(i).contains(documentKey)) {
                targetStateEnsureTargetState.addDocumentChange(documentKey, DocumentViewChange.Type.REMOVED);
            } else {
                targetStateEnsureTargetState.removeDocumentChange(documentKey);
            }
            ensureDocumentTargetMapping(documentKey).add(Integer.valueOf(i));
            if (mutableDocument != null) {
                this.pendingDocumentUpdates.put(documentKey, mutableDocument);
            }
        }
    }

    private void resetTarget(int i) {
        Assert.hardAssert((this.targetStates.get(Integer.valueOf(i)) == null || this.targetStates.get(Integer.valueOf(i)).isPending()) ? false : true, "Should only reset active targets", new Object[0]);
        this.targetStates.put(Integer.valueOf(i), new TargetState());
        Iterator<DocumentKey> it = this.targetMetadataProvider.getRemoteKeysForTarget(i).iterator();
        while (it.hasNext()) {
            removeDocumentFromTarget(i, it.next(), null);
        }
    }

    private boolean targetContainsDocument(int i, DocumentKey documentKey) {
        return this.targetMetadataProvider.getRemoteKeysForTarget(i).contains(documentKey);
    }

    public RemoteEvent createRemoteEvent(SnapshotVersion snapshotVersion) {
        HashMap map = new HashMap();
        for (Map.Entry<Integer, TargetState> entry : this.targetStates.entrySet()) {
            int iIntValue = entry.getKey().intValue();
            TargetState value = entry.getValue();
            TargetData targetDataQueryDataForActiveTarget = queryDataForActiveTarget(iIntValue);
            if (targetDataQueryDataForActiveTarget != null) {
                if (value.isCurrent() && targetDataQueryDataForActiveTarget.getTarget().isDocumentQuery()) {
                    DocumentKey documentKey = new DocumentKey(targetDataQueryDataForActiveTarget.getTarget().getPath());
                    if (this.pendingDocumentUpdates.get(documentKey) == null && !targetContainsDocument(iIntValue, documentKey)) {
                        removeDocumentFromTarget(iIntValue, documentKey, MutableDocument.newNoDocument(documentKey, snapshotVersion));
                    }
                }
                if (value.hasChanges()) {
                    map.put(Integer.valueOf(iIntValue), value.toTargetChange());
                    value.clearChanges();
                }
            }
        }
        HashSet hashSet = new HashSet();
        for (Map.Entry<DocumentKey, Set<Integer>> entry2 : this.pendingDocumentTargetMapping.entrySet()) {
            DocumentKey key = entry2.getKey();
            boolean z = true;
            Iterator<Integer> it = entry2.getValue().iterator();
            while (it.hasNext()) {
                TargetData targetDataQueryDataForActiveTarget2 = queryDataForActiveTarget(it.next().intValue());
                if (targetDataQueryDataForActiveTarget2 != null && !targetDataQueryDataForActiveTarget2.getPurpose().equals(QueryPurpose.LIMBO_RESOLUTION)) {
                    z = false;
                    break;
                }
            }
            if (z) {
                hashSet.add(key);
            }
        }
        RemoteEvent remoteEvent = new RemoteEvent(snapshotVersion, Collections.unmodifiableMap(map), Collections.unmodifiableSet(this.pendingTargetResets), Collections.unmodifiableMap(this.pendingDocumentUpdates), Collections.unmodifiableSet(hashSet));
        this.pendingDocumentUpdates = new HashMap();
        this.pendingDocumentTargetMapping = new HashMap();
        this.pendingTargetResets = new HashSet();
        return remoteEvent;
    }

    public void handleDocumentChange(WatchChange.DocumentChange documentChange) {
        MutableDocument newDocument = documentChange.getNewDocument();
        DocumentKey documentKey = documentChange.getDocumentKey();
        Iterator<Integer> it = documentChange.getUpdatedTargetIds().iterator();
        while (it.hasNext()) {
            int iIntValue = it.next().intValue();
            if (newDocument == null || !newDocument.isFoundDocument()) {
                removeDocumentFromTarget(iIntValue, documentKey, newDocument);
            } else {
                addDocumentToTarget(iIntValue, newDocument);
            }
        }
        Iterator<Integer> it2 = documentChange.getRemovedTargetIds().iterator();
        while (it2.hasNext()) {
            removeDocumentFromTarget(it2.next().intValue(), documentKey, documentChange.getNewDocument());
        }
    }

    public void handleExistenceFilter(WatchChange.ExistenceFilterWatchChange existenceFilterWatchChange) {
        int targetId = existenceFilterWatchChange.getTargetId();
        int count = existenceFilterWatchChange.getExistenceFilter().getCount();
        TargetData targetDataQueryDataForActiveTarget = queryDataForActiveTarget(targetId);
        if (targetDataQueryDataForActiveTarget != null) {
            Target target = targetDataQueryDataForActiveTarget.getTarget();
            if (!target.isDocumentQuery()) {
                if (getCurrentDocumentCountForTarget(targetId) != count) {
                    resetTarget(targetId);
                    this.pendingTargetResets.add(Integer.valueOf(targetId));
                    return;
                }
                return;
            }
            if (count != 0) {
                Assert.hardAssert(count == 1, "Single document existence filter with count: %d", Integer.valueOf(count));
            } else {
                DocumentKey documentKey = new DocumentKey(target.getPath());
                removeDocumentFromTarget(targetId, documentKey, MutableDocument.newNoDocument(documentKey, SnapshotVersion.NONE));
            }
        }
    }

    public void handleTargetChange(WatchChange.WatchTargetChange watchTargetChange) {
        Iterator<Integer> it = getTargetIds(watchTargetChange).iterator();
        while (it.hasNext()) {
            int iIntValue = it.next().intValue();
            TargetState targetStateEnsureTargetState = ensureTargetState(iIntValue);
            int iOrdinal = watchTargetChange.getChangeType().ordinal();
            if (iOrdinal != 0) {
                if (iOrdinal == 1) {
                    targetStateEnsureTargetState.recordTargetResponse();
                    if (!targetStateEnsureTargetState.isPending()) {
                        targetStateEnsureTargetState.clearChanges();
                    }
                    targetStateEnsureTargetState.updateResumeToken(watchTargetChange.getResumeToken());
                } else if (iOrdinal == 2) {
                    targetStateEnsureTargetState.recordTargetResponse();
                    if (!targetStateEnsureTargetState.isPending()) {
                        removeTarget(iIntValue);
                    }
                    Assert.hardAssert(watchTargetChange.getCause() == null, "WatchChangeAggregator does not handle errored targets", new Object[0]);
                } else if (iOrdinal != 3) {
                    if (iOrdinal != 4) {
                        Assert.fail("Unknown target watch change state: %s", watchTargetChange.getChangeType());
                        throw null;
                    }
                    if (isActiveTarget(iIntValue)) {
                        resetTarget(iIntValue);
                        targetStateEnsureTargetState.updateResumeToken(watchTargetChange.getResumeToken());
                    }
                } else if (isActiveTarget(iIntValue)) {
                    targetStateEnsureTargetState.markCurrent();
                    targetStateEnsureTargetState.updateResumeToken(watchTargetChange.getResumeToken());
                }
            } else if (isActiveTarget(iIntValue)) {
                targetStateEnsureTargetState.updateResumeToken(watchTargetChange.getResumeToken());
            }
        }
    }

    public void recordPendingTargetRequest(int i) {
        ensureTargetState(i).recordPendingTargetRequest();
    }

    public void removeTarget(int i) {
        this.targetStates.remove(Integer.valueOf(i));
    }
}
