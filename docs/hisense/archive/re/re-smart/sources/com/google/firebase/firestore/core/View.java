package com.google.firebase.firestore.core;

import androidx.annotation.Nullable;
import b.a.a.a.a;
import com.google.firebase.database.collection.ImmutableSortedMap;
import com.google.firebase.database.collection.ImmutableSortedSet;
import com.google.firebase.firestore.core.DocumentViewChange;
import com.google.firebase.firestore.model.Document;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.DocumentSet;
import com.google.firebase.firestore.remote.TargetChange;
import com.google.firebase.firestore.util.Assert;
import com.google.firebase.firestore.util.Util;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class View {
    public boolean current;
    public DocumentSet documentSet;
    public ImmutableSortedSet<DocumentKey> limboDocuments;
    public ImmutableSortedSet<DocumentKey> mutatedKeys;
    public final Query query;
    public ViewSnapshot.SyncState syncState = ViewSnapshot.SyncState.NONE;
    public ImmutableSortedSet<DocumentKey> syncedDocuments;

    /* JADX INFO: renamed from: com.google.firebase.firestore.core.View$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        public static final /* synthetic */ int[] $SwitchMap$com$google$firebase$firestore$core$DocumentViewChange$Type = new int[DocumentViewChange.Type.values().length];

        static {
            try {
                $SwitchMap$com$google$firebase$firestore$core$DocumentViewChange$Type[DocumentViewChange.Type.ADDED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$core$DocumentViewChange$Type[DocumentViewChange.Type.MODIFIED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$core$DocumentViewChange$Type[DocumentViewChange.Type.METADATA.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$core$DocumentViewChange$Type[DocumentViewChange.Type.REMOVED.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    public View(Query query, ImmutableSortedSet<DocumentKey> immutableSortedSet) {
        this.query = query;
        this.documentSet = DocumentSet.emptySet(query.comparator());
        this.syncedDocuments = immutableSortedSet;
        ImmutableSortedSet<DocumentKey> immutableSortedSet2 = DocumentKey.EMPTY_KEY_SET;
        this.limboDocuments = immutableSortedSet2;
        this.mutatedKeys = immutableSortedSet2;
    }

    private void applyTargetChange(TargetChange targetChange) {
        if (targetChange != null) {
            Iterator<DocumentKey> it = targetChange.getAddedDocuments().iterator();
            while (it.hasNext()) {
                this.syncedDocuments = this.syncedDocuments.insert(it.next());
            }
            for (DocumentKey documentKey : targetChange.getModifiedDocuments()) {
                Assert.hardAssert(this.syncedDocuments.contains(documentKey), "Modified document %s not found in view.", documentKey);
            }
            Iterator<DocumentKey> it2 = targetChange.getRemovedDocuments().iterator();
            while (it2.hasNext()) {
                this.syncedDocuments = this.syncedDocuments.remove(it2.next());
            }
            this.current = targetChange.isCurrent();
        }
    }

    public static int changeTypeOrder(DocumentViewChange documentViewChange) {
        int iOrdinal = documentViewChange.getType().ordinal();
        if (iOrdinal == 0) {
            return 0;
        }
        int i = 1;
        if (iOrdinal != 1) {
            i = 2;
            if (iOrdinal != 2) {
                if (iOrdinal == 3) {
                    return 2;
                }
                StringBuilder sbA = a.a("Unknown change type: ");
                sbA.append(documentViewChange.getType());
                throw new IllegalArgumentException(sbA.toString());
            }
        }
        return i;
    }

    private boolean shouldBeLimboDoc(DocumentKey documentKey) {
        Document document;
        return (this.syncedDocuments.contains(documentKey) || (document = this.documentSet.getDocument(documentKey)) == null || document.hasLocalMutations()) ? false : true;
    }

    private boolean shouldWaitForSyncedDocument(Document document, Document document2) {
        return document.hasLocalMutations() && document2.hasCommittedMutations() && !document2.hasLocalMutations();
    }

    private List<LimboDocumentChange> updateLimboDocuments() {
        if (!this.current) {
            return Collections.emptyList();
        }
        ImmutableSortedSet<DocumentKey> immutableSortedSet = this.limboDocuments;
        this.limboDocuments = DocumentKey.EMPTY_KEY_SET;
        for (Document document : this.documentSet) {
            if (shouldBeLimboDoc(document.getKey())) {
                this.limboDocuments = this.limboDocuments.insert(document.getKey());
            }
        }
        ArrayList arrayList = new ArrayList(this.limboDocuments.size() + immutableSortedSet.size());
        for (DocumentKey documentKey : immutableSortedSet) {
            if (!this.limboDocuments.contains(documentKey)) {
                arrayList.add(new LimboDocumentChange(LimboDocumentChange.Type.REMOVED, documentKey));
            }
        }
        for (DocumentKey documentKey2 : this.limboDocuments) {
            if (!immutableSortedSet.contains(documentKey2)) {
                arrayList.add(new LimboDocumentChange(LimboDocumentChange.Type.ADDED, documentKey2));
            }
        }
        return arrayList;
    }

    public /* synthetic */ int a(DocumentViewChange documentViewChange, DocumentViewChange documentViewChange2) {
        int iCompareIntegers = Util.compareIntegers(changeTypeOrder(documentViewChange), changeTypeOrder(documentViewChange2));
        documentViewChange.getType().compareTo(documentViewChange2.getType());
        return iCompareIntegers != 0 ? iCompareIntegers : this.query.comparator().compare(documentViewChange.getDocument(), documentViewChange2.getDocument());
    }

    public ViewChange applyChanges(DocumentChanges documentChanges, TargetChange targetChange) {
        ViewSnapshot viewSnapshot;
        Assert.hardAssert(!documentChanges.needsRefill, "Cannot apply changes that need a refill", new Object[0]);
        DocumentSet documentSet = this.documentSet;
        this.documentSet = documentChanges.documentSet;
        this.mutatedKeys = documentChanges.mutatedKeys;
        List<DocumentViewChange> changes = documentChanges.changeSet.getChanges();
        Collections.sort(changes, new Comparator() { // from class: b.e.b.d.b.D
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return this.f269a.a((DocumentViewChange) obj, (DocumentViewChange) obj2);
            }
        });
        applyTargetChange(targetChange);
        List<LimboDocumentChange> listUpdateLimboDocuments = updateLimboDocuments();
        ViewSnapshot.SyncState syncState = this.limboDocuments.size() == 0 && this.current ? ViewSnapshot.SyncState.SYNCED : ViewSnapshot.SyncState.LOCAL;
        boolean z = syncState != this.syncState;
        this.syncState = syncState;
        if (changes.size() != 0 || z) {
            viewSnapshot = new ViewSnapshot(this.query, documentChanges.documentSet, documentSet, changes, syncState == ViewSnapshot.SyncState.LOCAL, documentChanges.mutatedKeys, z, false);
        } else {
            viewSnapshot = null;
        }
        return new ViewChange(viewSnapshot, listUpdateLimboDocuments);
    }

    public ViewChange applyOnlineStateChange(OnlineState onlineState) {
        if (!this.current || onlineState != OnlineState.OFFLINE) {
            return new ViewChange(null, Collections.emptyList());
        }
        this.current = false;
        return applyChanges(new DocumentChanges(this.documentSet, new DocumentViewChangeSet(), this.mutatedKeys, false, null));
    }

    public DocumentChanges computeDocChanges(ImmutableSortedMap<DocumentKey, Document> immutableSortedMap) {
        return computeDocChanges(immutableSortedMap, null);
    }

    public ImmutableSortedSet<DocumentKey> getLimboDocuments() {
        return this.limboDocuments;
    }

    public ViewSnapshot.SyncState getSyncState() {
        return this.syncState;
    }

    public ImmutableSortedSet<DocumentKey> getSyncedDocuments() {
        return this.syncedDocuments;
    }

    /* JADX WARN: Code duplicated, block: B:72:0x012c A[PHI: r17
      0x012c: PHI (r17v4 com.google.firebase.firestore.model.DocumentSet) = 
      (r17v0 com.google.firebase.firestore.model.DocumentSet)
      (r17v0 com.google.firebase.firestore.model.DocumentSet)
      (r17v5 com.google.firebase.firestore.model.DocumentSet)
      (r17v5 com.google.firebase.firestore.model.DocumentSet)
     binds: [B:70:0x0128, B:71:0x012a, B:58:0x00f8, B:55:0x00ea] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:73:0x012f A[PHI: r17
      0x012f: PHI (r17v1 com.google.firebase.firestore.model.DocumentSet) = 
      (r17v0 com.google.firebase.firestore.model.DocumentSet)
      (r17v0 com.google.firebase.firestore.model.DocumentSet)
      (r17v5 com.google.firebase.firestore.model.DocumentSet)
      (r17v5 com.google.firebase.firestore.model.DocumentSet)
     binds: [B:67:0x011a, B:68:0x011c, B:60:0x00fb, B:51:0x00d2] A[DONT_GENERATE, DONT_INLINE]] */
    public DocumentChanges computeDocChanges(ImmutableSortedMap<DocumentKey, Document> immutableSortedMap, @Nullable DocumentChanges documentChanges) {
        DocumentSet documentSet;
        boolean z;
        DocumentSet documentSetRemove;
        ImmutableSortedSet<DocumentKey> immutableSortedSetRemove;
        DocumentViewChangeSet documentViewChangeSet = documentChanges != null ? documentChanges.changeSet : new DocumentViewChangeSet();
        DocumentSet documentSet2 = documentChanges != null ? documentChanges.documentSet : this.documentSet;
        ImmutableSortedSet<DocumentKey> immutableSortedSet = documentChanges != null ? documentChanges.mutatedKeys : this.mutatedKeys;
        Document lastDocument = (this.query.hasLimitToFirst() && ((long) documentSet2.size()) == this.query.getLimitToFirst()) ? documentSet2.getLastDocument() : null;
        Document firstDocument = (this.query.hasLimitToLast() && ((long) documentSet2.size()) == this.query.getLimitToLast()) ? documentSet2.getFirstDocument() : null;
        ImmutableSortedSet<DocumentKey> immutableSortedSetRemove2 = immutableSortedSet;
        boolean z2 = false;
        DocumentSet documentSetRemove2 = documentSet2;
        for (Map.Entry<DocumentKey, Document> entry : immutableSortedMap) {
            DocumentKey key = entry.getKey();
            Document document = documentSet2.getDocument(key);
            Document value = this.query.matches(entry.getValue()) ? entry.getValue() : null;
            boolean z3 = document != null && this.mutatedKeys.contains(document.getKey());
            boolean z4 = value != null && (value.hasLocalMutations() || (this.mutatedKeys.contains(value.getKey()) && value.hasCommittedMutations()));
            if (document == null || value == null) {
                documentSet = documentSet2;
                if (document == null && value != null) {
                    documentViewChangeSet.addChange(new DocumentViewChange(DocumentViewChange.Type.ADDED, value));
                } else if (document == null || value != null) {
                    z = false;
                } else {
                    documentViewChangeSet.addChange(new DocumentViewChange(DocumentViewChange.Type.REMOVED, document));
                    if (lastDocument != null || firstDocument != null) {
                        z = true;
                        z2 = true;
                    }
                }
                z = true;
            } else {
                documentSet = documentSet2;
                if (document.getData().equals(value.getData())) {
                    if (z3 != z4) {
                        documentViewChangeSet.addChange(new DocumentViewChange(DocumentViewChange.Type.METADATA, value));
                        z = true;
                    } else {
                        z = false;
                    }
                } else if (shouldWaitForSyncedDocument(document, value)) {
                    z = false;
                } else {
                    documentViewChangeSet.addChange(new DocumentViewChange(DocumentViewChange.Type.MODIFIED, value));
                    if ((lastDocument == null || this.query.comparator().compare(value, lastDocument) <= 0) && (firstDocument == null || this.query.comparator().compare(value, firstDocument) >= 0)) {
                        z = true;
                    } else {
                        z = true;
                        z2 = true;
                    }
                }
            }
            if (z) {
                if (value != null) {
                    documentSetRemove = documentSetRemove2.add(value);
                    immutableSortedSetRemove = value.hasLocalMutations() ? immutableSortedSetRemove2.insert(value.getKey()) : immutableSortedSetRemove2.remove(value.getKey());
                } else {
                    documentSetRemove = documentSetRemove2.remove(key);
                    immutableSortedSetRemove = immutableSortedSetRemove2.remove(key);
                }
                immutableSortedSetRemove2 = immutableSortedSetRemove;
                documentSetRemove2 = documentSetRemove;
            }
            documentSet2 = documentSet;
        }
        if (this.query.hasLimitToFirst() || this.query.hasLimitToLast()) {
            long limitToFirst = this.query.hasLimitToFirst() ? this.query.getLimitToFirst() : this.query.getLimitToLast();
            long size = documentSetRemove2.size();
            while (true) {
                size -= limitToFirst;
                if (size <= 0) {
                    break;
                }
                Document lastDocument2 = this.query.hasLimitToFirst() ? documentSetRemove2.getLastDocument() : documentSetRemove2.getFirstDocument();
                documentSetRemove2 = documentSetRemove2.remove(lastDocument2.getKey());
                immutableSortedSetRemove2 = immutableSortedSetRemove2.remove(lastDocument2.getKey());
                documentViewChangeSet.addChange(new DocumentViewChange(DocumentViewChange.Type.REMOVED, lastDocument2));
                limitToFirst = 1;
            }
        }
        DocumentSet documentSet3 = documentSetRemove2;
        ImmutableSortedSet<DocumentKey> immutableSortedSet2 = immutableSortedSetRemove2;
        Assert.hardAssert(!z2 || documentChanges == null, "View was refilled using docs that themselves needed refilling.", new Object[0]);
        return new DocumentChanges(documentSet3, documentViewChangeSet, immutableSortedSet2, z2, null);
    }

    public static class DocumentChanges {
        public final DocumentViewChangeSet changeSet;
        public final DocumentSet documentSet;
        public final ImmutableSortedSet<DocumentKey> mutatedKeys;
        public final boolean needsRefill;

        public DocumentChanges(DocumentSet documentSet, DocumentViewChangeSet documentViewChangeSet, ImmutableSortedSet<DocumentKey> immutableSortedSet, boolean z) {
            this.documentSet = documentSet;
            this.changeSet = documentViewChangeSet;
            this.mutatedKeys = immutableSortedSet;
            this.needsRefill = z;
        }

        public boolean needsRefill() {
            return this.needsRefill;
        }

        public /* synthetic */ DocumentChanges(DocumentSet documentSet, DocumentViewChangeSet documentViewChangeSet, ImmutableSortedSet immutableSortedSet, boolean z, AnonymousClass1 anonymousClass1) {
            this.documentSet = documentSet;
            this.changeSet = documentViewChangeSet;
            this.mutatedKeys = immutableSortedSet;
            this.needsRefill = z;
        }
    }

    public ViewChange applyChanges(DocumentChanges documentChanges) {
        return applyChanges(documentChanges, null);
    }
}
