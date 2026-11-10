package com.google.firebase.firestore.local;

import android.database.Cursor;
import android.util.SparseArray;
import com.google.firebase.firestore.core.ListenSequence;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.util.Assert;
import com.google.firebase.firestore.util.Consumer;
import com.google.firebase.firestore.util.Function;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class SQLiteLruReferenceDelegate implements ReferenceDelegate, LruDelegate {
    public static final int REMOVE_ORPHANED_DOCUMENTS_BATCH_SIZE = 100;
    public long currentSequenceNumber = -1;
    public final LruGarbageCollector garbageCollector;
    public ReferenceSet inMemoryPins;
    public ListenSequence listenSequence;
    public final SQLitePersistence persistence;

    public SQLiteLruReferenceDelegate(SQLitePersistence sQLitePersistence, LruGarbageCollector.Params params) {
        this.persistence = sQLitePersistence;
        this.garbageCollector = new LruGarbageCollector(this, params);
    }

    private boolean isPinned(DocumentKey documentKey) {
        if (this.inMemoryPins.containsKey(documentKey)) {
            return true;
        }
        return mutationQueuesContainKey(documentKey);
    }

    private boolean mutationQueuesContainKey(DocumentKey documentKey) {
        return !this.persistence.query("SELECT 1 FROM document_mutations WHERE path = ?").binding(EncodedPath.encode(documentKey.getPath())).isEmpty();
    }

    private void removeSentinel(DocumentKey documentKey) {
        this.persistence.execute("DELETE FROM target_documents WHERE path = ? AND target_id = 0", EncodedPath.encode(documentKey.getPath()));
    }

    private void writeSentinel(DocumentKey documentKey) {
        this.persistence.execute("INSERT OR REPLACE INTO target_documents (target_id, path, sequence_number) VALUES (0, ?, ?)", EncodedPath.encode(documentKey.getPath()), Long.valueOf(getCurrentSequenceNumber()));
    }

    @Override // com.google.firebase.firestore.local.ReferenceDelegate
    public void addReference(DocumentKey documentKey) {
        writeSentinel(documentKey);
    }

    @Override // com.google.firebase.firestore.local.LruDelegate
    public void forEachOrphanedDocumentSequenceNumber(final Consumer<Long> consumer) {
        this.persistence.query("select sequence_number from target_documents group by path having COUNT(*) = 1 AND target_id = 0").forEach(new Consumer() { // from class: b.e.b.d.d.P
            @Override // com.google.firebase.firestore.util.Consumer
            public final void accept(Object obj) {
                consumer.accept(Long.valueOf(((Cursor) obj).getLong(0)));
            }
        });
    }

    @Override // com.google.firebase.firestore.local.LruDelegate
    public void forEachTarget(Consumer<TargetData> consumer) {
        this.persistence.getTargetCache().forEachTarget(consumer);
    }

    @Override // com.google.firebase.firestore.local.LruDelegate
    public long getByteSize() {
        return this.persistence.getByteSize();
    }

    @Override // com.google.firebase.firestore.local.ReferenceDelegate
    public long getCurrentSequenceNumber() {
        Assert.hardAssert(this.currentSequenceNumber != -1, "Attempting to get a sequence number outside of a transaction", new Object[0]);
        return this.currentSequenceNumber;
    }

    @Override // com.google.firebase.firestore.local.LruDelegate
    public LruGarbageCollector getGarbageCollector() {
        return this.garbageCollector;
    }

    @Override // com.google.firebase.firestore.local.LruDelegate
    public long getSequenceNumberCount() {
        return ((Long) this.persistence.query("SELECT COUNT(*) FROM (SELECT sequence_number FROM target_documents GROUP BY path HAVING COUNT(*) = 1 AND target_id = 0)").firstValue(new Function() { // from class: b.e.b.d.d.Q
            @Override // com.google.firebase.firestore.util.Function
            public final Object apply(Object obj) {
                return Long.valueOf(((Cursor) obj).getLong(0));
            }
        })).longValue() + this.persistence.getTargetCache().getTargetCount();
    }

    @Override // com.google.firebase.firestore.local.ReferenceDelegate
    public void onTransactionCommitted() {
        Assert.hardAssert(this.currentSequenceNumber != -1, "Committing a transaction without having started one", new Object[0]);
        this.currentSequenceNumber = -1L;
    }

    @Override // com.google.firebase.firestore.local.ReferenceDelegate
    public void onTransactionStarted() {
        Assert.hardAssert(this.currentSequenceNumber == -1, "Starting a transaction without committing the previous one", new Object[0]);
        this.currentSequenceNumber = this.listenSequence.next();
    }

    @Override // com.google.firebase.firestore.local.ReferenceDelegate
    public void removeMutationReference(DocumentKey documentKey) {
        writeSentinel(documentKey);
    }

    @Override // com.google.firebase.firestore.local.LruDelegate
    public int removeOrphanedDocuments(long j) {
        final int[] iArr = new int[1];
        final ArrayList arrayList = new ArrayList();
        while (true) {
            boolean z = true;
            while (true) {
                if (!z) {
                    this.persistence.getRemoteDocumentCache().removeAll(arrayList);
                    return iArr[0];
                }
                if (this.persistence.query("select path from target_documents group by path having COUNT(*) = 1 AND target_id = 0 AND sequence_number <= ? LIMIT ?").binding(Long.valueOf(j), 100).forEach(new Consumer() { // from class: b.e.b.d.d.O
                    @Override // com.google.firebase.firestore.util.Consumer
                    public final void accept(Object obj) {
                        this.f369a.a(iArr, arrayList, (Cursor) obj);
                    }
                }) == 100) {
                    break;
                }
                z = false;
            }
        }
    }

    @Override // com.google.firebase.firestore.local.ReferenceDelegate
    public void removeReference(DocumentKey documentKey) {
        writeSentinel(documentKey);
    }

    @Override // com.google.firebase.firestore.local.ReferenceDelegate
    public void removeTarget(TargetData targetData) {
        this.persistence.getTargetCache().updateTargetData(targetData.withSequenceNumber(getCurrentSequenceNumber()));
    }

    @Override // com.google.firebase.firestore.local.LruDelegate
    public int removeTargets(long j, SparseArray<?> sparseArray) {
        return this.persistence.getTargetCache().removeQueries(j, sparseArray);
    }

    @Override // com.google.firebase.firestore.local.ReferenceDelegate
    public void setInMemoryPins(ReferenceSet referenceSet) {
        this.inMemoryPins = referenceSet;
    }

    public void start(long j) {
        this.listenSequence = new ListenSequence(j);
    }

    @Override // com.google.firebase.firestore.local.ReferenceDelegate
    public void updateLimboDocument(DocumentKey documentKey) {
        writeSentinel(documentKey);
    }

    public /* synthetic */ void a(int[] iArr, List list, Cursor cursor) {
        DocumentKey documentKey = new DocumentKey(EncodedPath.decodeResourcePath(cursor.getString(0)));
        if (isPinned(documentKey)) {
            return;
        }
        iArr[0] = iArr[0] + 1;
        list.add(documentKey);
        removeSentinel(documentKey);
    }
}
