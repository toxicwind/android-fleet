package com.google.firebase.firestore.local;

import android.database.Cursor;
import androidx.annotation.Nullable;
import b.a.a.a.a;
import com.google.firebase.Timestamp;
import com.google.firebase.database.collection.ImmutableSortedMap;
import com.google.firebase.firestore.auth.User;
import com.google.firebase.firestore.core.Bound;
import com.google.firebase.firestore.core.FieldFilter;
import com.google.firebase.firestore.core.Filter;
import com.google.firebase.firestore.core.Target;
import com.google.firebase.firestore.index.FirestoreIndexValueWriter;
import com.google.firebase.firestore.index.IndexByteEncoder;
import com.google.firebase.firestore.index.IndexEntry;
import com.google.firebase.firestore.local.EncodedPath;
import com.google.firebase.firestore.model.AutoValue_FieldIndex_IndexState;
import com.google.firebase.firestore.model.Document;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.FieldIndex;
import com.google.firebase.firestore.model.FieldPath;
import com.google.firebase.firestore.model.ResourcePath;
import com.google.firebase.firestore.model.SnapshotVersion;
import com.google.firebase.firestore.model.TargetIndexMatcher;
import com.google.firebase.firestore.model.Values;
import com.google.firebase.firestore.util.Assert;
import com.google.firebase.firestore.util.Consumer;
import com.google.firebase.firestore.util.Logger;
import com.google.firebase.firestore.util.Util;
import com.google.firestore.admin.v1.Index;
import com.google.firestore.v1.Value;
import com.google.protobuf.InvalidProtocolBufferException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

/* JADX INFO: loaded from: classes.dex */
public final class SQLiteIndexManager implements IndexManager {
    public static final String TAG = "SQLiteIndexManager";
    public final SQLitePersistence db;
    public final LocalSerializer serializer;
    public final String uid;
    public final MemoryIndexManager.MemoryCollectionParentIndex collectionParentsCache = new MemoryIndexManager.MemoryCollectionParentIndex();
    public final Map<String, Map<Integer, FieldIndex>> memoizedIndexes = new HashMap();
    public final Queue<FieldIndex> nextIndexToUpdate = new PriorityQueue(10, new Comparator() { // from class: b.e.b.d.d.L
        @Override // java.util.Comparator
        public final int compare(Object obj, Object obj2) {
            return Long.compare(((FieldIndex) obj).getIndexState().getSequenceNumber(), ((FieldIndex) obj2).getIndexState().getSequenceNumber());
        }
    });
    public boolean started = false;
    public int memoizedMaxIndexId = -1;
    public long memoizedMaxSequenceNumber = -1;

    public SQLiteIndexManager(SQLitePersistence sQLitePersistence, LocalSerializer localSerializer, User user) {
        this.db = sQLitePersistence;
        this.serializer = localSerializer;
        this.uid = user.isAuthenticated() ? user.getUid() : "";
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: addIndexEntry, reason: merged with bridge method [inline-methods] */
    public void a(Document document, IndexEntry indexEntry) {
        this.db.execute("INSERT INTO index_entries (index_id, uid, array_value, directional_value, document_name) VALUES(?, ?, ?, ?, ?)", Integer.valueOf(indexEntry.getIndexId()), this.uid, indexEntry.getArrayValue(), indexEntry.getDirectionalValue(), document.getKey().toString());
    }

    private SortedSet<IndexEntry> computeIndexEntries(Document document, FieldIndex fieldIndex) {
        TreeSet treeSet = new TreeSet();
        byte[] bArrEncodeDirectionalElements = encodeDirectionalElements(fieldIndex, document);
        if (bArrEncodeDirectionalElements == null) {
            return treeSet;
        }
        FieldIndex.Segment arraySegment = fieldIndex.getArraySegment();
        if (arraySegment != null) {
            Value field = document.getField(arraySegment.getFieldPath());
            if (Values.isArray(field)) {
                Iterator<Value> it = field.getArrayValue().getValuesList().iterator();
                while (it.hasNext()) {
                    treeSet.add(IndexEntry.create(fieldIndex.getIndexId(), document.getKey(), encodeSingleElement(it.next()), bArrEncodeDirectionalElements));
                }
            }
        } else {
            treeSet.add(IndexEntry.create(fieldIndex.getIndexId(), document.getKey(), new byte[0], bArrEncodeDirectionalElements));
        }
        return treeSet;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: deleteIndexEntry, reason: merged with bridge method [inline-methods] */
    public void b(Document document, IndexEntry indexEntry) {
        this.db.execute("DELETE FROM index_entries WHERE index_id = ? AND uid = ? AND array_value = ? AND directional_value = ? AND document_name = ?", Integer.valueOf(indexEntry.getIndexId()), this.uid, indexEntry.getArrayValue(), indexEntry.getDirectionalValue(), document.getKey().toString());
    }

    @Nullable
    private Object[] encodeBound(FieldIndex fieldIndex, Target target, @Nullable Bound bound) {
        if (bound == null) {
            return null;
        }
        return encodeValues(fieldIndex, target, bound.getPosition());
    }

    @Nullable
    private byte[] encodeDirectionalElements(FieldIndex fieldIndex, Document document) {
        IndexByteEncoder indexByteEncoder = new IndexByteEncoder();
        for (FieldIndex.Segment segment : fieldIndex.getDirectionalSegments()) {
            Value field = document.getField(segment.getFieldPath());
            if (field == null) {
                return null;
            }
            FirestoreIndexValueWriter.INSTANCE.writeIndexValue(field, indexByteEncoder.forKind(segment.getKind()));
        }
        return indexByteEncoder.getEncodedBytes();
    }

    private byte[] encodeSegments(FieldIndex fieldIndex) {
        return this.serializer.encodeFieldIndexSegments(fieldIndex.getSegments()).toByteArray();
    }

    private byte[] encodeSingleElement(Value value) {
        IndexByteEncoder indexByteEncoder = new IndexByteEncoder();
        FirestoreIndexValueWriter.INSTANCE.writeIndexValue(value, indexByteEncoder.forKind(FieldIndex.Segment.Kind.ASCENDING));
        return indexByteEncoder.getEncodedBytes();
    }

    @Nullable
    private Object[] encodeValues(FieldIndex fieldIndex, Target target, @Nullable List<Value> list) {
        if (list == null) {
            return null;
        }
        List<IndexByteEncoder> arrayList = new ArrayList<>();
        arrayList.add(new IndexByteEncoder());
        Iterator<Value> it = list.iterator();
        for (FieldIndex.Segment segment : fieldIndex.getDirectionalSegments()) {
            Value next = it.next();
            for (IndexByteEncoder indexByteEncoder : arrayList) {
                if (isInFilter(target, segment.getFieldPath()) && Values.isArray(next)) {
                    arrayList = expandIndexValues(arrayList, segment, next);
                } else {
                    FirestoreIndexValueWriter.INSTANCE.writeIndexValue(next, indexByteEncoder.forKind(segment.getKind()));
                }
            }
        }
        return getEncodedBytes(arrayList);
    }

    private List<IndexByteEncoder> expandIndexValues(List<IndexByteEncoder> list, FieldIndex.Segment segment, Value value) {
        ArrayList<IndexByteEncoder> arrayList = new ArrayList(list);
        ArrayList arrayList2 = new ArrayList();
        for (Value value2 : value.getArrayValue().getValuesList()) {
            for (IndexByteEncoder indexByteEncoder : arrayList) {
                IndexByteEncoder indexByteEncoder2 = new IndexByteEncoder();
                indexByteEncoder2.seed(indexByteEncoder.getEncodedBytes());
                FirestoreIndexValueWriter.INSTANCE.writeIndexValue(value2, indexByteEncoder2.forKind(segment.getKind()));
                arrayList2.add(indexByteEncoder2);
            }
        }
        return arrayList2;
    }

    private Object[] fillBounds(int i, int i2, @Nullable List<Value> list, @Nullable Object[] objArr, @Nullable Object[] objArr2, @Nullable Object[] objArr3) {
        int i3 = 0;
        int i4 = (list != null ? 1 : 0) + 2 + (objArr != null ? 1 : 0) + (objArr2 != null ? 1 : 0);
        int size = i / (list != null ? list.size() : 1);
        Object[] objArr4 = new Object[(i4 * i) + (objArr3 != null ? objArr3.length : 0)];
        int i5 = 0;
        for (int i6 = 0; i6 < i; i6++) {
            int i7 = i5 + 1;
            objArr4[i5] = Integer.valueOf(i2);
            i5 = i7 + 1;
            objArr4[i7] = this.uid;
            if (list != null) {
                objArr4[i5] = encodeSingleElement(list.get(i6 / size));
                i5++;
            }
            if (objArr != null) {
                objArr4[i5] = objArr[i6 % size];
                i5++;
            }
            if (objArr2 != null) {
                objArr4[i5] = objArr2[i6 % size];
                i5++;
            }
        }
        if (objArr3 != null) {
            int length = objArr3.length;
            while (i3 < length) {
                objArr4[i5] = objArr3[i3];
                i3++;
                i5++;
            }
        }
        return objArr4;
    }

    private SQLitePersistence.Query generateQuery(Target target, int i, @Nullable List<Value> list, @Nullable Object[] objArr, String str, @Nullable Object[] objArr2, String str2, @Nullable Object[] objArr3) {
        StringBuilder sb;
        int size = (list != null ? list.size() : 1) * Math.max(objArr != null ? objArr.length : 1, objArr2 != null ? objArr2.length : 1);
        StringBuilder sbB = a.b("SELECT document_name, directional_value FROM index_entries ", "WHERE index_id = ? AND uid = ? ");
        if (list != null) {
            sbB.append("AND array_value = ? ");
        }
        if (objArr != null) {
            sbB.append("AND directional_value ");
            sbB.append(str);
            sbB.append(" ? ");
        }
        if (objArr2 != null) {
            sbB.append("AND directional_value ");
            sbB.append(str2);
            sbB.append(" ? ");
        }
        StringBuilder sbRepeatSequence = Util.repeatSequence(sbB, size, " UNION ");
        sbRepeatSequence.append(" ORDER BY directional_value, document_name ");
        if (target.getLimit() != -1) {
            sbRepeatSequence.append("LIMIT ");
            sbRepeatSequence.append(target.getLimit());
            sbRepeatSequence.append(" ");
        }
        if (objArr3 != null) {
            StringBuilder sb2 = new StringBuilder("SELECT document_name, directional_value FROM (");
            sb2.append((CharSequence) sbRepeatSequence);
            sb2.append(") WHERE directional_value NOT IN (");
            sb2.append((CharSequence) Util.repeatSequence("?", objArr3.length, ", "));
            sb2.append(")");
            sb = sb2;
        } else {
            sb = sbRepeatSequence;
        }
        return this.db.query(sb.toString()).binding(fillBounds(size, i, list, objArr, objArr2, objArr3));
    }

    private Object[] getEncodedBytes(List<IndexByteEncoder> list) {
        Object[] objArr = new Object[list.size()];
        for (int i = 0; i < list.size(); i++) {
            objArr[i] = list.get(i).getEncodedBytes();
        }
        return objArr;
    }

    private SortedSet<IndexEntry> getExistingIndexEntries(final DocumentKey documentKey, final FieldIndex fieldIndex) {
        final TreeSet treeSet = new TreeSet();
        this.db.query("SELECT array_value, directional_value FROM index_entries WHERE index_id = ? AND document_name = ? AND uid = ?").binding(Integer.valueOf(fieldIndex.getIndexId()), documentKey.toString(), this.uid).forEach(new Consumer() { // from class: b.e.b.d.d.I
            @Override // com.google.firebase.firestore.util.Consumer
            public final void accept(Object obj) {
                Cursor cursor = (Cursor) obj;
                treeSet.add(IndexEntry.create(fieldIndex.getIndexId(), documentKey, cursor.getBlob(0), cursor.getBlob(1)));
            }
        });
        return treeSet;
    }

    private boolean isInFilter(Target target, FieldPath fieldPath) {
        for (Filter filter : target.getFilters()) {
            if (filter instanceof FieldFilter) {
                FieldFilter fieldFilter = (FieldFilter) filter;
                if (fieldFilter.getField().equals(fieldPath)) {
                    FieldFilter.Operator operator = fieldFilter.getOperator();
                    return operator.equals(FieldFilter.Operator.IN) || operator.equals(FieldFilter.Operator.NOT_IN);
                }
            }
        }
        return false;
    }

    private void memoizeIndex(FieldIndex fieldIndex) {
        Map<Integer, FieldIndex> map = this.memoizedIndexes.get(fieldIndex.getCollectionGroup());
        if (map == null) {
            map = new HashMap<>();
            this.memoizedIndexes.put(fieldIndex.getCollectionGroup(), map);
        }
        FieldIndex fieldIndex2 = map.get(Integer.valueOf(fieldIndex.getIndexId()));
        if (fieldIndex2 != null) {
            this.nextIndexToUpdate.remove(fieldIndex2);
        }
        map.put(Integer.valueOf(fieldIndex.getIndexId()), fieldIndex);
        this.nextIndexToUpdate.add(fieldIndex);
        this.memoizedMaxIndexId = Math.max(this.memoizedMaxIndexId, fieldIndex.getIndexId());
        this.memoizedMaxSequenceNumber = Math.max(this.memoizedMaxSequenceNumber, fieldIndex.getIndexState().getSequenceNumber());
    }

    private void updateEntries(final Document document, SortedSet<IndexEntry> sortedSet, SortedSet<IndexEntry> sortedSet2) {
        Logger.debug(TAG, "Updating index entries for document '%s'", document.getKey());
        Util.diffCollections(sortedSet, sortedSet2, new Consumer() { // from class: b.e.b.d.d.N
            @Override // com.google.firebase.firestore.util.Consumer
            public final void accept(Object obj) {
                this.f366a.a(document, (IndexEntry) obj);
            }
        }, new Consumer() { // from class: b.e.b.d.d.G
            @Override // com.google.firebase.firestore.util.Consumer
            public final void accept(Object obj) {
                this.f343a.b(document, (IndexEntry) obj);
            }
        });
    }

    @Override // com.google.firebase.firestore.local.IndexManager
    public void addFieldIndex(FieldIndex fieldIndex) {
        Assert.hardAssert(this.started, "IndexManager not started", new Object[0]);
        int i = this.memoizedMaxIndexId + 1;
        FieldIndex fieldIndexCreate = FieldIndex.create(i, fieldIndex.getCollectionGroup(), fieldIndex.getSegments(), fieldIndex.getIndexState());
        this.db.execute("INSERT INTO index_configuration (index_id, collection_group, index_proto) VALUES(?, ?, ?)", Integer.valueOf(i), fieldIndexCreate.getCollectionGroup(), encodeSegments(fieldIndexCreate));
        memoizeIndex(fieldIndexCreate);
    }

    @Override // com.google.firebase.firestore.local.IndexManager
    public void addToCollectionParentIndex(ResourcePath resourcePath) {
        Assert.hardAssert(this.started, "IndexManager not started", new Object[0]);
        Assert.hardAssert(resourcePath.length() % 2 == 1, "Expected a collection path.", new Object[0]);
        if (this.collectionParentsCache.add(resourcePath)) {
            this.db.execute("INSERT OR REPLACE INTO collection_parents (collection_id, parent) VALUES (?, ?)", resourcePath.getLastSegment(), EncodedPath.encode(resourcePath.popLast()));
        }
    }

    @Override // com.google.firebase.firestore.local.IndexManager
    public void deleteFieldIndex(FieldIndex fieldIndex) {
        this.db.execute("DELETE FROM index_configuration WHERE index_id = ?", Integer.valueOf(fieldIndex.getIndexId()));
        this.db.execute("DELETE FROM index_entries WHERE index_id = ?", Integer.valueOf(fieldIndex.getIndexId()));
        this.db.execute("DELETE FROM index_state WHERE index_id = ?", fieldIndex.getIndexState());
        this.nextIndexToUpdate.remove(fieldIndex);
        Map<Integer, FieldIndex> map = this.memoizedIndexes.get(fieldIndex.getCollectionGroup());
        if (map != null) {
            map.remove(Integer.valueOf(fieldIndex.getIndexId()));
        }
    }

    @Override // com.google.firebase.firestore.local.IndexManager
    public List<ResourcePath> getCollectionParents(String str) {
        Assert.hardAssert(this.started, "IndexManager not started", new Object[0]);
        final ArrayList arrayList = new ArrayList();
        this.db.query("SELECT parent FROM collection_parents WHERE collection_id = ?").binding(str).forEach(new Consumer() { // from class: b.e.b.d.d.J
            @Override // com.google.firebase.firestore.util.Consumer
            public final void accept(Object obj) {
                arrayList.add(EncodedPath.decodeResourcePath(((Cursor) obj).getString(0)));
            }
        });
        return arrayList;
    }

    @Override // com.google.firebase.firestore.local.IndexManager
    public Set<DocumentKey> getDocumentsMatchingTarget(FieldIndex fieldIndex, Target target) {
        Assert.hardAssert(this.started, "IndexManager not started", new Object[0]);
        List<Value> arrayValues = target.getArrayValues(fieldIndex);
        List<Value> notInValues = target.getNotInValues(fieldIndex);
        Bound lowerBound = target.getLowerBound(fieldIndex);
        Bound upperBound = target.getUpperBound(fieldIndex);
        if (Logger.isDebugEnabled()) {
            Logger.debug(TAG, "Using index '%s' to execute '%s' (Arrays: %s, Lower bound: %s, Upper bound: %s)", fieldIndex, target, arrayValues, lowerBound, upperBound);
        }
        SQLitePersistence.Query queryGenerateQuery = generateQuery(target, fieldIndex.getIndexId(), arrayValues, encodeBound(fieldIndex, target, lowerBound), (lowerBound == null || !lowerBound.isInclusive()) ? ">" : ">=", encodeBound(fieldIndex, target, upperBound), (upperBound == null || !upperBound.isInclusive()) ? "<" : "<=", encodeValues(fieldIndex, target, notInValues));
        final HashSet hashSet = new HashSet();
        queryGenerateQuery.forEach(new Consumer() { // from class: b.e.b.d.d.M
            @Override // com.google.firebase.firestore.util.Consumer
            public final void accept(Object obj) {
                hashSet.add(new DocumentKey(ResourcePath.fromString(((Cursor) obj).getString(0))));
            }
        });
        Logger.debug(TAG, "Index scan returned %s documents", Integer.valueOf(hashSet.size()));
        return hashSet;
    }

    @Override // com.google.firebase.firestore.local.IndexManager
    @Nullable
    public FieldIndex getFieldIndex(Target target) {
        Assert.hardAssert(this.started, "IndexManager not started", new Object[0]);
        if (!Persistence.INDEXING_SUPPORT_ENABLED) {
            return null;
        }
        TargetIndexMatcher targetIndexMatcher = new TargetIndexMatcher(target);
        Collection<FieldIndex> fieldIndexes = getFieldIndexes(target.getCollectionGroup() != null ? target.getCollectionGroup() : target.getPath().getLastSegment());
        if (fieldIndexes.isEmpty()) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (FieldIndex fieldIndex : fieldIndexes) {
            if (targetIndexMatcher.servedByIndex(fieldIndex)) {
                arrayList.add(fieldIndex);
            }
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        return (FieldIndex) Collections.max(arrayList, new Comparator() { // from class: b.e.b.d.d.K
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return Integer.compare(((FieldIndex) obj).getSegments().size(), ((FieldIndex) obj2).getSegments().size());
            }
        });
    }

    @Override // com.google.firebase.firestore.local.IndexManager
    public Collection<FieldIndex> getFieldIndexes(String str) {
        Assert.hardAssert(this.started, "IndexManager not started", new Object[0]);
        Map<Integer, FieldIndex> map = this.memoizedIndexes.get(str);
        return map == null ? Collections.emptyList() : map.values();
    }

    @Override // com.google.firebase.firestore.local.IndexManager
    @Nullable
    public String getNextCollectionGroupToUpdate() {
        Assert.hardAssert(this.started, "IndexManager not started", new Object[0]);
        FieldIndex fieldIndexPeek = this.nextIndexToUpdate.peek();
        if (fieldIndexPeek != null) {
            return fieldIndexPeek.getCollectionGroup();
        }
        return null;
    }

    @Override // com.google.firebase.firestore.local.IndexManager
    public void start() {
        if (!Persistence.INDEXING_SUPPORT_ENABLED) {
            this.started = true;
            return;
        }
        final HashMap map = new HashMap();
        this.db.query("SELECT index_id, sequence_number, read_time_seconds, read_time_nanos, document_key FROM index_state WHERE uid = ?").binding(this.uid).forEach(new Consumer() { // from class: b.e.b.d.d.F
            @Override // com.google.firebase.firestore.util.Consumer
            public final void accept(Object obj) {
                Cursor cursor = (Cursor) obj;
                map.put(Integer.valueOf(cursor.getInt(0)), FieldIndex.IndexState.create(cursor.getLong(1), new SnapshotVersion(new Timestamp(cursor.getLong(2), cursor.getInt(3))), new DocumentKey(EncodedPath.decodeResourcePath(cursor.getString(4)))));
            }
        });
        this.db.query("SELECT index_id, collection_group, index_proto FROM index_configuration").forEach(new Consumer() { // from class: b.e.b.d.d.H
            @Override // com.google.firebase.firestore.util.Consumer
            public final void accept(Object obj) {
                this.f346a.b(map, (Cursor) obj);
            }
        });
        this.started = true;
    }

    @Override // com.google.firebase.firestore.local.IndexManager
    public void updateCollectionGroup(String str, FieldIndex.IndexOffset indexOffset) {
        Assert.hardAssert(this.started, "IndexManager not started", new Object[0]);
        this.memoizedMaxSequenceNumber++;
        for (FieldIndex fieldIndex : getFieldIndexes(str)) {
            FieldIndex fieldIndexCreate = FieldIndex.create(fieldIndex.getIndexId(), fieldIndex.getCollectionGroup(), fieldIndex.getSegments(), new AutoValue_FieldIndex_IndexState(this.memoizedMaxSequenceNumber, indexOffset));
            this.db.execute("REPLACE INTO index_state (index_id, uid,  sequence_number, read_time_seconds, read_time_nanos, document_key) VALUES(?, ?, ?, ?, ?, ?)", Integer.valueOf(fieldIndex.getIndexId()), this.uid, Long.valueOf(this.memoizedMaxSequenceNumber), Long.valueOf(indexOffset.getReadTime().getTimestamp().getSeconds()), Integer.valueOf(indexOffset.getReadTime().getTimestamp().getNanoseconds()), EncodedPath.encode(indexOffset.getDocumentKey().getPath()));
            memoizeIndex(fieldIndexCreate);
        }
    }

    @Override // com.google.firebase.firestore.local.IndexManager
    public void updateIndexEntries(ImmutableSortedMap<DocumentKey, Document> immutableSortedMap) {
        Assert.hardAssert(this.started, "IndexManager not started", new Object[0]);
        if (Persistence.INDEXING_SUPPORT_ENABLED) {
            for (Map.Entry<DocumentKey, Document> entry : immutableSortedMap) {
                for (FieldIndex fieldIndex : getFieldIndexes(entry.getKey().getCollectionGroup())) {
                    SortedSet<IndexEntry> existingIndexEntries = getExistingIndexEntries(entry.getKey(), fieldIndex);
                    SortedSet<IndexEntry> sortedSetComputeIndexEntries = computeIndexEntries(entry.getValue(), fieldIndex);
                    if (!existingIndexEntries.equals(sortedSetComputeIndexEntries)) {
                        updateEntries(entry.getValue(), existingIndexEntries, sortedSetComputeIndexEntries);
                    }
                }
            }
        }
    }

    public /* synthetic */ void b(Map map, Cursor cursor) {
        FieldIndex.IndexState indexState;
        try {
            int i = cursor.getInt(0);
            String string = cursor.getString(1);
            List<FieldIndex.Segment> listDecodeFieldIndexSegments = this.serializer.decodeFieldIndexSegments(Index.parseFrom(cursor.getBlob(2)));
            if (map.containsKey(Integer.valueOf(i))) {
                indexState = (FieldIndex.IndexState) map.get(Integer.valueOf(i));
            } else {
                indexState = FieldIndex.INITIAL_STATE;
            }
            memoizeIndex(FieldIndex.create(i, string, listDecodeFieldIndexSegments, indexState));
        } catch (InvalidProtocolBufferException e2) {
            Assert.fail(a.a("Failed to decode index: ", e2), new Object[0]);
            throw null;
        }
    }

    @Override // com.google.firebase.firestore.local.IndexManager
    public Collection<FieldIndex> getFieldIndexes() {
        ArrayList arrayList = new ArrayList();
        Iterator<Map<Integer, FieldIndex>> it = this.memoizedIndexes.values().iterator();
        while (it.hasNext()) {
            arrayList.addAll(it.next().values());
        }
        return arrayList;
    }
}
