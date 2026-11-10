package com.google.firebase.firestore.local;

import android.database.Cursor;
import androidx.annotation.VisibleForTesting;
import com.google.firebase.Timestamp;
import com.google.firebase.database.collection.ImmutableSortedMap;
import com.google.firebase.firestore.local.SQLiteRemoteDocumentCache;
import com.google.firebase.firestore.model.DocumentCollections;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.FieldIndex;
import com.google.firebase.firestore.model.MutableDocument;
import com.google.firebase.firestore.model.ResourcePath;
import com.google.firebase.firestore.model.SnapshotVersion;
import com.google.firebase.firestore.proto.MaybeDocument;
import com.google.firebase.firestore.util.Assert;
import com.google.firebase.firestore.util.BackgroundQueue;
import com.google.firebase.firestore.util.Consumer;
import com.google.firebase.firestore.util.Executors;
import com.google.firebase.firestore.util.Function;
import com.google.firebase.firestore.util.Util;
import com.google.protobuf.InvalidProtocolBufferException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes.dex */
public final class SQLiteRemoteDocumentCache implements RemoteDocumentCache {

    @VisibleForTesting
    public static final int BINDS_PER_STATEMENT = 9;
    public final SQLitePersistence db;
    public IndexManager indexManager;
    public final LocalSerializer serializer;

    public SQLiteRemoteDocumentCache(SQLitePersistence sQLitePersistence, LocalSerializer localSerializer) {
        this.db = sQLitePersistence;
        this.serializer = localSerializer;
    }

    public static /* synthetic */ SnapshotVersion b(Cursor cursor) {
        return new SnapshotVersion(new Timestamp(cursor.getLong(0), cursor.getInt(1)));
    }

    private MutableDocument decodeMaybeDocument(byte[] bArr, int i, int i2) {
        try {
            return this.serializer.decodeMaybeDocument(MaybeDocument.parseFrom(bArr)).withReadTime(new SnapshotVersion(new Timestamp(i, i2)));
        } catch (InvalidProtocolBufferException e2) {
            Assert.fail("MaybeDocument failed to parse: %s", e2);
            throw null;
        }
    }

    public /* synthetic */ MutableDocument a(Cursor cursor) {
        return decodeMaybeDocument(cursor.getBlob(0), cursor.getInt(1), cursor.getInt(2));
    }

    @Override // com.google.firebase.firestore.local.RemoteDocumentCache
    public void add(MutableDocument mutableDocument, SnapshotVersion snapshotVersion) {
        Assert.hardAssert(!snapshotVersion.equals(SnapshotVersion.NONE), "Cannot add document to the RemoteDocumentCache with a read time of zero", new Object[0]);
        DocumentKey key = mutableDocument.getKey();
        Timestamp timestamp = snapshotVersion.getTimestamp();
        this.db.execute("INSERT OR REPLACE INTO remote_documents (path, path_length, read_time_seconds, read_time_nanos, contents) VALUES (?, ?, ?, ?, ?)", EncodedPath.encode(key.getPath()), Integer.valueOf(key.getPath().length()), Long.valueOf(timestamp.getSeconds()), Integer.valueOf(timestamp.getNanoseconds()), this.serializer.encodeMaybeDocument(mutableDocument).toByteArray());
        this.indexManager.addToCollectionParentIndex(mutableDocument.getKey().getCollectionPath());
    }

    @Override // com.google.firebase.firestore.local.RemoteDocumentCache
    public MutableDocument get(DocumentKey documentKey) {
        MutableDocument mutableDocument = (MutableDocument) this.db.query("SELECT contents, read_time_seconds, read_time_nanos FROM remote_documents WHERE path = ?").binding(EncodedPath.encode(documentKey.getPath())).firstValue(new Function() { // from class: b.e.b.d.d.ka
            @Override // com.google.firebase.firestore.util.Function
            public final Object apply(Object obj) {
                return this.f417a.a((Cursor) obj);
            }
        });
        return mutableDocument != null ? mutableDocument : MutableDocument.newInvalidDocument(documentKey);
    }

    @Override // com.google.firebase.firestore.local.RemoteDocumentCache
    public Map<DocumentKey, MutableDocument> getAll(Iterable<DocumentKey> iterable) {
        final HashMap map = new HashMap();
        ArrayList arrayList = new ArrayList();
        for (DocumentKey documentKey : iterable) {
            arrayList.add(EncodedPath.encode(documentKey.getPath()));
            map.put(documentKey, MutableDocument.newInvalidDocument(documentKey));
        }
        SQLitePersistence.LongQuery longQuery = new SQLitePersistence.LongQuery(this.db, "SELECT contents, read_time_seconds, read_time_nanos FROM remote_documents WHERE path IN (", arrayList, ") ORDER BY path");
        while (longQuery.hasMoreSubqueries()) {
            longQuery.performNextSubquery().forEach(new Consumer() { // from class: b.e.b.d.d.oa
                @Override // com.google.firebase.firestore.util.Consumer
                public final void accept(Object obj) {
                    this.f435a.a(map, (Cursor) obj);
                }
            });
        }
        return map;
    }

    @Override // com.google.firebase.firestore.local.RemoteDocumentCache
    public SnapshotVersion getLatestReadTime() {
        SnapshotVersion snapshotVersion = (SnapshotVersion) this.db.query("SELECT read_time_seconds, read_time_nanos FROM remote_documents ORDER BY read_time_seconds DESC, read_time_nanos DESC LIMIT 1").firstValue(new Function() { // from class: b.e.b.d.d.na
            @Override // com.google.firebase.firestore.util.Function
            public final Object apply(Object obj) {
                return SQLiteRemoteDocumentCache.b((Cursor) obj);
            }
        });
        return snapshotVersion != null ? snapshotVersion : SnapshotVersion.NONE;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.firebase.firestore.local.RemoteDocumentCache
    public void removeAll(Collection<DocumentKey> collection) {
        if (collection.isEmpty()) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        ImmutableSortedMap<DocumentKey, ?> immutableSortedMapInsert = DocumentCollections.EMPTY_DOCUMENT_MAP;
        for (DocumentKey documentKey : collection) {
            arrayList.add(EncodedPath.encode(documentKey.getPath()));
            immutableSortedMapInsert = immutableSortedMapInsert.insert(documentKey, MutableDocument.newNoDocument(documentKey, SnapshotVersion.NONE));
        }
        SQLitePersistence.LongQuery longQuery = new SQLitePersistence.LongQuery(this.db, "DELETE FROM remote_documents WHERE path IN (", arrayList, ")");
        while (longQuery.hasMoreSubqueries()) {
            longQuery.executeNextSubquery();
        }
        this.indexManager.updateIndexEntries(immutableSortedMapInsert);
    }

    @Override // com.google.firebase.firestore.local.RemoteDocumentCache
    public void setIndexManager(IndexManager indexManager) {
        this.indexManager = indexManager;
    }

    public /* synthetic */ void a(Map map, Cursor cursor) {
        MutableDocument mutableDocumentDecodeMaybeDocument = decodeMaybeDocument(cursor.getBlob(0), cursor.getInt(1), cursor.getInt(2));
        map.put(mutableDocumentDecodeMaybeDocument.getKey(), mutableDocumentDecodeMaybeDocument);
    }

    public /* synthetic */ void a(BackgroundQueue backgroundQueue, final Map[] mapArr, Cursor cursor) {
        final byte[] blob = cursor.getBlob(0);
        final int[] iArr = {cursor.getInt(1)};
        final int[] iArr2 = {cursor.getInt(2)};
        Executor executor = backgroundQueue;
        if (cursor.isLast()) {
            executor = Executors.DIRECT_EXECUTOR;
        }
        executor.execute(new Runnable() { // from class: b.e.b.d.d.la
            @Override // java.lang.Runnable
            public final void run() {
                this.f420a.a(blob, iArr, iArr2, mapArr);
            }
        });
    }

    public /* synthetic */ void a(byte[] bArr, int[] iArr, int[] iArr2, Map[] mapArr) {
        MutableDocument mutableDocumentDecodeMaybeDocument = decodeMaybeDocument(bArr, iArr[0], iArr2[0]);
        synchronized (this) {
            mapArr[0].put(mutableDocumentDecodeMaybeDocument.getKey(), mutableDocumentDecodeMaybeDocument);
        }
    }

    @Override // com.google.firebase.firestore.local.RemoteDocumentCache
    public Map<DocumentKey, MutableDocument> getAll(String str, FieldIndex.IndexOffset indexOffset, int i) {
        List<ResourcePath> collectionParents = this.indexManager.getCollectionParents(str);
        ArrayList arrayList = new ArrayList(collectionParents.size());
        Iterator<ResourcePath> it = collectionParents.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().append(str));
        }
        if (arrayList.isEmpty()) {
            return Collections.emptyMap();
        }
        if (arrayList.size() * 9 < 900) {
            return getAll(arrayList, indexOffset, i);
        }
        HashMap map = new HashMap();
        int i2 = 0;
        while (i2 < arrayList.size()) {
            int i3 = i2 + 100;
            map.putAll(getAll(arrayList.subList(i2, Math.min(arrayList.size(), i3)), indexOffset, i));
            i2 = i3;
        }
        return Util.firstNEntries(map, i, FieldIndex.IndexOffset.DOCUMENT_COMPARATOR);
    }

    private Map<DocumentKey, MutableDocument> getAll(List<ResourcePath> list, FieldIndex.IndexOffset indexOffset, int i) {
        Timestamp timestamp = indexOffset.getReadTime().getTimestamp();
        DocumentKey documentKey = indexOffset.getDocumentKey();
        StringBuilder sbRepeatSequence = Util.repeatSequence("SELECT contents, read_time_seconds, read_time_nanos, path FROM remote_documents WHERE path >= ? AND path < ? AND path_length = ? AND (read_time_seconds > ? OR ( read_time_seconds = ? AND read_time_nanos > ?) OR ( read_time_seconds = ? AND read_time_nanos = ? and path > ?)) ", list.size(), " UNION ");
        sbRepeatSequence.append("ORDER BY read_time_seconds, read_time_nanos, path LIMIT ?");
        Object[] objArr = new Object[(list.size() * 9) + 1];
        int i2 = 0;
        for (ResourcePath resourcePath : list) {
            String strEncode = EncodedPath.encode(resourcePath);
            int i3 = i2 + 1;
            objArr[i2] = strEncode;
            int i4 = i3 + 1;
            objArr[i3] = EncodedPath.prefixSuccessor(strEncode);
            int i5 = i4 + 1;
            objArr[i4] = Integer.valueOf(resourcePath.length() + 1);
            int i6 = i5 + 1;
            objArr[i5] = Long.valueOf(timestamp.getSeconds());
            int i7 = i6 + 1;
            objArr[i6] = Long.valueOf(timestamp.getSeconds());
            int i8 = i7 + 1;
            objArr[i7] = Integer.valueOf(timestamp.getNanoseconds());
            int i9 = i8 + 1;
            objArr[i8] = Long.valueOf(timestamp.getSeconds());
            int i10 = i9 + 1;
            objArr[i9] = Integer.valueOf(timestamp.getNanoseconds());
            objArr[i10] = EncodedPath.encode(documentKey.getPath());
            i2 = i10 + 1;
        }
        objArr[i2] = Integer.valueOf(i);
        final BackgroundQueue backgroundQueue = new BackgroundQueue();
        final HashMap[] mapArr = {new HashMap()};
        this.db.query(sbRepeatSequence.toString()).binding(objArr).forEach(new Consumer() { // from class: b.e.b.d.d.ma
            @Override // com.google.firebase.firestore.util.Consumer
            public final void accept(Object obj) {
                this.f427a.a(backgroundQueue, mapArr, (Cursor) obj);
            }
        });
        try {
            backgroundQueue.drain();
            return mapArr[0];
        } catch (InterruptedException e2) {
            Assert.fail("Interrupted while deserializing documents", e2);
            throw null;
        }
    }

    @Override // com.google.firebase.firestore.local.RemoteDocumentCache
    public Map<DocumentKey, MutableDocument> getAll(ResourcePath resourcePath, FieldIndex.IndexOffset indexOffset) {
        return getAll(Collections.singletonList(resourcePath), indexOffset, Integer.MAX_VALUE);
    }
}
