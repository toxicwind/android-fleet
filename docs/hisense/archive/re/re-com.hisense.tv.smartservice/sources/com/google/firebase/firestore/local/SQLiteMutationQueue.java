package com.google.firebase.firestore.local;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import androidx.annotation.Nullable;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.auth.User;
import com.google.firebase.firestore.core.Query;
import com.google.firebase.firestore.local.EncodedPath;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.ResourcePath;
import com.google.firebase.firestore.model.mutation.Mutation;
import com.google.firebase.firestore.model.mutation.MutationBatch;
import com.google.firebase.firestore.proto.WriteBatch;
import com.google.firebase.firestore.remote.WriteStream;
import com.google.firebase.firestore.util.Assert;
import com.google.firebase.firestore.util.Consumer;
import com.google.firebase.firestore.util.Function;
import com.google.firebase.firestore.util.Util;
import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public final class SQLiteMutationQueue implements MutationQueue {
    public static final int BLOB_MAX_INLINE_LENGTH = 1000000;
    public final SQLitePersistence db;
    public final IndexManager indexManager;
    public ByteString lastStreamToken;
    public int nextBatchId;
    public final LocalSerializer serializer;
    public final String uid;

    private static class BlobAccumulator implements Consumer<Cursor> {
        public final ArrayList<ByteString> chunks = new ArrayList<>();
        public boolean more = true;

        public BlobAccumulator(byte[] bArr) {
            addChunk(bArr);
        }

        private void addChunk(byte[] bArr) {
            this.chunks.add(ByteString.copyFrom(bArr));
        }

        public int numChunks() {
            return this.chunks.size();
        }

        public ByteString result() {
            return ByteString.copyFrom(this.chunks);
        }

        @Override // com.google.firebase.firestore.util.Consumer
        public void accept(Cursor cursor) {
            byte[] blob = cursor.getBlob(0);
            addChunk(blob);
            if (blob.length < 1000000) {
                this.more = false;
            }
        }
    }

    public SQLiteMutationQueue(SQLitePersistence sQLitePersistence, LocalSerializer localSerializer, User user, IndexManager indexManager) {
        this.db = sQLitePersistence;
        this.serializer = localSerializer;
        this.uid = user.isAuthenticated() ? user.getUid() : "";
        this.lastStreamToken = WriteStream.EMPTY_STREAM_TOKEN;
        this.indexManager = indexManager;
    }

    private MutationBatch decodeInlineMutationBatch(int i, byte[] bArr) throws Throwable {
        try {
            if (bArr.length < 1000000) {
                return this.serializer.decodeMutationBatch(WriteBatch.parseFrom(bArr));
            }
            BlobAccumulator blobAccumulator = new BlobAccumulator(bArr);
            while (blobAccumulator.more) {
                this.db.query("SELECT SUBSTR(mutations, ?, ?) FROM mutations WHERE uid = ? AND batch_id = ?").binding(Integer.valueOf((blobAccumulator.numChunks() * 1000000) + 1), 1000000, this.uid, Integer.valueOf(i)).first(blobAccumulator);
            }
            return this.serializer.decodeMutationBatch(WriteBatch.parseFrom(blobAccumulator.result()));
        } catch (InvalidProtocolBufferException e2) {
            Assert.fail("MutationBatch failed to parse: %s", e2);
            throw null;
        }
    }

    private void loadNextBatchIdAcrossAllUsers() {
        final ArrayList arrayList = new ArrayList();
        this.db.query("SELECT uid FROM mutation_queues").forEach(new Consumer() { // from class: b.e.b.d.d.W
            @Override // com.google.firebase.firestore.util.Consumer
            public final void accept(Object obj) {
                arrayList.add(((Cursor) obj).getString(0));
            }
        });
        this.nextBatchId = 0;
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            this.db.query("SELECT MAX(batch_id) FROM mutations WHERE uid = ?").binding((String) it.next()).forEach(new Consumer() { // from class: b.e.b.d.d.T
                @Override // com.google.firebase.firestore.util.Consumer
                public final void accept(Object obj) {
                    this.f375a.c((Cursor) obj);
                }
            });
        }
        this.nextBatchId++;
    }

    private void writeMutationQueueMetadata() {
        this.db.execute("INSERT OR REPLACE INTO mutation_queues (uid, last_acknowledged_batch_id, last_stream_token) VALUES (?, ?, ?)", this.uid, -1, this.lastStreamToken.toByteArray());
    }

    public /* synthetic */ MutationBatch a(int i, Cursor cursor) {
        return decodeInlineMutationBatch(i, cursor.getBlob(0));
    }

    @Override // com.google.firebase.firestore.local.MutationQueue
    public void acknowledgeBatch(MutationBatch mutationBatch, ByteString byteString) {
        if (byteString == null) {
            throw new NullPointerException();
        }
        this.lastStreamToken = byteString;
        writeMutationQueueMetadata();
    }

    @Override // com.google.firebase.firestore.local.MutationQueue
    public MutationBatch addMutationBatch(Timestamp timestamp, List<Mutation> list, List<Mutation> list2) {
        int i = this.nextBatchId;
        this.nextBatchId = i + 1;
        MutationBatch mutationBatch = new MutationBatch(i, timestamp, list, list2);
        this.db.execute("INSERT INTO mutations (uid, batch_id, mutations) VALUES (?, ?, ?)", this.uid, Integer.valueOf(i), this.serializer.encodeMutationBatch(mutationBatch).toByteArray());
        HashSet hashSet = new HashSet();
        SQLiteStatement sQLiteStatementPrepare = this.db.prepare("INSERT INTO document_mutations (uid, path, batch_id) VALUES (?, ?, ?)");
        Iterator<Mutation> it = list2.iterator();
        while (it.hasNext()) {
            DocumentKey key = it.next().getKey();
            if (hashSet.add(key)) {
                this.db.execute(sQLiteStatementPrepare, this.uid, EncodedPath.encode(key.getPath()), Integer.valueOf(i));
                this.indexManager.addToCollectionParentIndex(key.getCollectionPath());
            }
        }
        return mutationBatch;
    }

    public /* synthetic */ MutationBatch b(Cursor cursor) {
        return decodeInlineMutationBatch(cursor.getInt(0), cursor.getBlob(1));
    }

    public /* synthetic */ void d(Cursor cursor) {
        this.lastStreamToken = ByteString.copyFrom(cursor.getBlob(0));
    }

    @Override // com.google.firebase.firestore.local.MutationQueue
    public List<MutationBatch> getAllMutationBatches() {
        final ArrayList arrayList = new ArrayList();
        this.db.query("SELECT batch_id, SUBSTR(mutations, 1, ?) FROM mutations WHERE uid = ? ORDER BY batch_id ASC").binding(1000000, this.uid).forEach(new Consumer() { // from class: b.e.b.d.d.Z
            @Override // com.google.firebase.firestore.util.Consumer
            public final void accept(Object obj) {
                this.f384a.a(arrayList, (Cursor) obj);
            }
        });
        return arrayList;
    }

    @Override // com.google.firebase.firestore.local.MutationQueue
    public List<MutationBatch> getAllMutationBatchesAffectingDocumentKey(DocumentKey documentKey) {
        String strEncode = EncodedPath.encode(documentKey.getPath());
        final ArrayList arrayList = new ArrayList();
        this.db.query("SELECT m.batch_id, SUBSTR(m.mutations, 1, ?) FROM document_mutations dm, mutations m WHERE dm.uid = ? AND dm.path = ? AND dm.uid = m.uid AND dm.batch_id = m.batch_id ORDER BY dm.batch_id").binding(1000000, this.uid, strEncode).forEach(new Consumer() { // from class: b.e.b.d.d.aa
            @Override // com.google.firebase.firestore.util.Consumer
            public final void accept(Object obj) {
                this.f387a.b(arrayList, (Cursor) obj);
            }
        });
        return arrayList;
    }

    @Override // com.google.firebase.firestore.local.MutationQueue
    public List<MutationBatch> getAllMutationBatchesAffectingDocumentKeys(Iterable<DocumentKey> iterable) {
        ArrayList arrayList = new ArrayList();
        Iterator<DocumentKey> it = iterable.iterator();
        while (it.hasNext()) {
            arrayList.add(EncodedPath.encode(it.next().getPath()));
        }
        SQLitePersistence.LongQuery longQuery = new SQLitePersistence.LongQuery(this.db, "SELECT DISTINCT dm.batch_id, SUBSTR(m.mutations, 1, ?) FROM document_mutations dm, mutations m WHERE dm.uid = ? AND dm.path IN (", Arrays.asList(1000000, this.uid), arrayList, ") AND dm.uid = m.uid AND dm.batch_id = m.batch_id ORDER BY dm.batch_id");
        final ArrayList arrayList2 = new ArrayList();
        final HashSet hashSet = new HashSet();
        while (longQuery.hasMoreSubqueries()) {
            longQuery.performNextSubquery().forEach(new Consumer() { // from class: b.e.b.d.d.ba
                @Override // com.google.firebase.firestore.util.Consumer
                public final void accept(Object obj) {
                    this.f390a.a(hashSet, arrayList2, (Cursor) obj);
                }
            });
        }
        if (longQuery.getSubqueriesPerformed() > 1) {
            Collections.sort(arrayList2, new Comparator() { // from class: b.e.b.d.d.da
                @Override // java.util.Comparator
                public final int compare(Object obj, Object obj2) {
                    return Util.compareIntegers(((MutationBatch) obj).getBatchId(), ((MutationBatch) obj2).getBatchId());
                }
            });
        }
        return arrayList2;
    }

    @Override // com.google.firebase.firestore.local.MutationQueue
    public List<MutationBatch> getAllMutationBatchesAffectingQuery(Query query) {
        Assert.hardAssert(!query.isCollectionGroupQuery(), "CollectionGroup queries should be handled in LocalDocumentsView", new Object[0]);
        ResourcePath path = query.getPath();
        final int length = path.length() + 1;
        String strEncode = EncodedPath.encode(path);
        String strPrefixSuccessor = EncodedPath.prefixSuccessor(strEncode);
        final ArrayList arrayList = new ArrayList();
        this.db.query("SELECT dm.batch_id, dm.path, SUBSTR(m.mutations, 1, ?) FROM document_mutations dm, mutations m WHERE dm.uid = ? AND dm.path >= ? AND dm.path < ? AND dm.uid = m.uid AND dm.batch_id = m.batch_id ORDER BY dm.batch_id").binding(1000000, this.uid, strEncode, strPrefixSuccessor).forEach(new Consumer() { // from class: b.e.b.d.d.U
            @Override // com.google.firebase.firestore.util.Consumer
            public final void accept(Object obj) {
                this.f376a.a(arrayList, length, (Cursor) obj);
            }
        });
        return arrayList;
    }

    @Override // com.google.firebase.firestore.local.MutationQueue
    public int getHighestUnacknowledgedBatchId() {
        return ((Integer) this.db.query("SELECT IFNULL(MAX(batch_id), ?) FROM mutations WHERE uid = ?").binding(-1, this.uid).firstValue(new Function() { // from class: b.e.b.d.d.S
            @Override // com.google.firebase.firestore.util.Function
            public final Object apply(Object obj) {
                return Integer.valueOf(((Cursor) obj).getInt(0));
            }
        })).intValue();
    }

    @Override // com.google.firebase.firestore.local.MutationQueue
    public ByteString getLastStreamToken() {
        return this.lastStreamToken;
    }

    @Override // com.google.firebase.firestore.local.MutationQueue
    @Nullable
    public MutationBatch getNextMutationBatchAfterBatchId(int i) {
        return (MutationBatch) this.db.query("SELECT batch_id, SUBSTR(mutations, 1, ?) FROM mutations WHERE uid = ? AND batch_id >= ? ORDER BY batch_id ASC LIMIT 1").binding(1000000, this.uid, Integer.valueOf(i + 1)).firstValue(new Function() { // from class: b.e.b.d.d.ca
            @Override // com.google.firebase.firestore.util.Function
            public final Object apply(Object obj) {
                return this.f394a.b((Cursor) obj);
            }
        });
    }

    @Override // com.google.firebase.firestore.local.MutationQueue
    public boolean isEmpty() {
        return this.db.query("SELECT batch_id FROM mutations WHERE uid = ? LIMIT 1").binding(this.uid).isEmpty();
    }

    @Override // com.google.firebase.firestore.local.MutationQueue
    @Nullable
    public MutationBatch lookupMutationBatch(final int i) {
        return (MutationBatch) this.db.query("SELECT SUBSTR(mutations, 1, ?) FROM mutations WHERE uid = ? AND batch_id = ?").binding(1000000, this.uid, Integer.valueOf(i)).firstValue(new Function() { // from class: b.e.b.d.d.X
            @Override // com.google.firebase.firestore.util.Function
            public final Object apply(Object obj) {
                return this.f381a.a(i, (Cursor) obj);
            }
        });
    }

    @Override // com.google.firebase.firestore.local.MutationQueue
    public void performConsistencyCheck() {
        if (isEmpty()) {
            final ArrayList arrayList = new ArrayList();
            this.db.query("SELECT path FROM document_mutations WHERE uid = ?").binding(this.uid).forEach(new Consumer() { // from class: b.e.b.d.d.Y
                @Override // com.google.firebase.firestore.util.Consumer
                public final void accept(Object obj) {
                    arrayList.add(EncodedPath.decodeResourcePath(((Cursor) obj).getString(0)));
                }
            });
            Assert.hardAssert(arrayList.isEmpty(), "Document leak -- detected dangling mutation references when queue is empty. Dangling keys: %s", arrayList);
        }
    }

    @Override // com.google.firebase.firestore.local.MutationQueue
    public void removeMutationBatch(MutationBatch mutationBatch) {
        SQLiteStatement sQLiteStatementPrepare = this.db.prepare("DELETE FROM mutations WHERE uid = ? AND batch_id = ?");
        SQLiteStatement sQLiteStatementPrepare2 = this.db.prepare("DELETE FROM document_mutations WHERE uid = ? AND path = ? AND batch_id = ?");
        int batchId = mutationBatch.getBatchId();
        Assert.hardAssert(this.db.execute(sQLiteStatementPrepare, this.uid, Integer.valueOf(batchId)) != 0, "Mutation batch (%s, %d) did not exist", this.uid, Integer.valueOf(mutationBatch.getBatchId()));
        Iterator<Mutation> it = mutationBatch.getMutations().iterator();
        while (it.hasNext()) {
            DocumentKey key = it.next().getKey();
            this.db.execute(sQLiteStatementPrepare2, this.uid, EncodedPath.encode(key.getPath()), Integer.valueOf(batchId));
            this.db.getReferenceDelegate().removeMutationReference(key);
        }
    }

    @Override // com.google.firebase.firestore.local.MutationQueue
    public void setLastStreamToken(ByteString byteString) {
        if (byteString == null) {
            throw new NullPointerException();
        }
        this.lastStreamToken = byteString;
        writeMutationQueueMetadata();
    }

    @Override // com.google.firebase.firestore.local.MutationQueue
    public void start() {
        loadNextBatchIdAcrossAllUsers();
        if (this.db.query("SELECT last_stream_token FROM mutation_queues WHERE uid = ?").binding(this.uid).first(new Consumer() { // from class: b.e.b.d.d.V
            @Override // com.google.firebase.firestore.util.Consumer
            public final void accept(Object obj) {
                this.f379a.d((Cursor) obj);
            }
        }) == 0) {
            writeMutationQueueMetadata();
        }
    }

    public /* synthetic */ void b(List list, Cursor cursor) {
        list.add(decodeInlineMutationBatch(cursor.getInt(0), cursor.getBlob(1)));
    }

    public /* synthetic */ void c(Cursor cursor) {
        this.nextBatchId = Math.max(this.nextBatchId, cursor.getInt(0));
    }

    public /* synthetic */ void a(List list, Cursor cursor) {
        list.add(decodeInlineMutationBatch(cursor.getInt(0), cursor.getBlob(1)));
    }

    public /* synthetic */ void a(Set set, List list, Cursor cursor) {
        int i = cursor.getInt(0);
        if (set.contains(Integer.valueOf(i))) {
            return;
        }
        set.add(Integer.valueOf(i));
        list.add(decodeInlineMutationBatch(i, cursor.getBlob(1)));
    }

    public /* synthetic */ void a(List list, int i, Cursor cursor) {
        int i2 = cursor.getInt(0);
        int size = list.size();
        if ((size <= 0 || i2 != ((MutationBatch) list.get(size - 1)).getBatchId()) && EncodedPath.decodeResourcePath(cursor.getString(1)).length() == i) {
            list.add(decodeInlineMutationBatch(i2, cursor.getBlob(2)));
        }
    }
}
