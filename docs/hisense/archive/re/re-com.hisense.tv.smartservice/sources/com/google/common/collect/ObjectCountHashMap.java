package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
@GwtCompatible(emulated = true, serializable = true)
public class ObjectCountHashMap<K> {
    public static final float DEFAULT_LOAD_FACTOR = 1.0f;
    public static final int DEFAULT_SIZE = 3;
    public static final long HASH_MASK = -4294967296L;
    public static final int MAXIMUM_CAPACITY = 1073741824;
    public static final long NEXT_MASK = 4294967295L;
    public static final int UNSET = -1;

    @VisibleForTesting
    public transient long[] entries;
    public transient Object[] keys;
    public transient float loadFactor;
    public transient int modCount;
    public transient int size;
    public transient int[] table;
    public transient int threshold;
    public transient int[] values;

    class MapEntry extends Multisets.AbstractEntry<K> {
        public final K key;
        public int lastKnownIndex;

        public MapEntry(int i) {
            this.key = (K) ObjectCountHashMap.this.keys[i];
            this.lastKnownIndex = i;
        }

        @Override // com.google.common.collect.Multiset.Entry
        public int getCount() {
            updateLastKnownIndex();
            int i = this.lastKnownIndex;
            if (i == -1) {
                return 0;
            }
            return ObjectCountHashMap.this.values[i];
        }

        @Override // com.google.common.collect.Multiset.Entry
        public K getElement() {
            return this.key;
        }

        @CanIgnoreReturnValue
        public int setCount(int i) {
            updateLastKnownIndex();
            int i2 = this.lastKnownIndex;
            if (i2 == -1) {
                ObjectCountHashMap.this.put(this.key, i);
                return 0;
            }
            int[] iArr = ObjectCountHashMap.this.values;
            int i3 = iArr[i2];
            iArr[i2] = i;
            return i3;
        }

        public void updateLastKnownIndex() {
            int i = this.lastKnownIndex;
            if (i == -1 || i >= ObjectCountHashMap.this.size() || !Objects.equal(this.key, ObjectCountHashMap.this.keys[this.lastKnownIndex])) {
                this.lastKnownIndex = ObjectCountHashMap.this.indexOf(this.key);
            }
        }
    }

    public ObjectCountHashMap() {
        init(3, 1.0f);
    }

    public static <K> ObjectCountHashMap<K> create() {
        return new ObjectCountHashMap<>();
    }

    public static <K> ObjectCountHashMap<K> createWithExpectedSize(int i) {
        return new ObjectCountHashMap<>(i);
    }

    public static int getHash(long j) {
        return (int) (j >>> 32);
    }

    public static int getNext(long j) {
        return (int) j;
    }

    private int hashTableMask() {
        return this.table.length - 1;
    }

    public static long[] newEntries(int i) {
        long[] jArr = new long[i];
        Arrays.fill(jArr, -1L);
        return jArr;
    }

    public static int[] newTable(int i) {
        int[] iArr = new int[i];
        Arrays.fill(iArr, -1);
        return iArr;
    }

    private void resizeMeMaybe(int i) {
        int length = this.entries.length;
        if (i > length) {
            int iMax = Math.max(1, length >>> 1) + length;
            if (iMax < 0) {
                iMax = Integer.MAX_VALUE;
            }
            if (iMax != length) {
                resizeEntries(iMax);
            }
        }
    }

    private void resizeTable(int i) {
        if (this.table.length >= 1073741824) {
            this.threshold = Integer.MAX_VALUE;
            return;
        }
        int i2 = ((int) (i * this.loadFactor)) + 1;
        int[] iArrNewTable = newTable(i);
        long[] jArr = this.entries;
        int length = iArrNewTable.length - 1;
        for (int i3 = 0; i3 < this.size; i3++) {
            int hash = getHash(jArr[i3]);
            int i4 = hash & length;
            int i5 = iArrNewTable[i4];
            iArrNewTable[i4] = i3;
            jArr[i3] = (((long) hash) << 32) | (((long) i5) & 4294967295L);
        }
        this.threshold = i2;
        this.table = iArrNewTable;
    }

    public static long swapNext(long j, int i) {
        return (j & HASH_MASK) | (((long) i) & 4294967295L);
    }

    public void clear() {
        this.modCount++;
        Arrays.fill(this.keys, 0, this.size, (Object) null);
        Arrays.fill(this.values, 0, this.size, 0);
        Arrays.fill(this.table, -1);
        Arrays.fill(this.entries, -1L);
        this.size = 0;
    }

    public boolean containsKey(Object obj) {
        return indexOf(obj) != -1;
    }

    public void ensureCapacity(int i) {
        if (i > this.entries.length) {
            resizeEntries(i);
        }
        if (i >= this.threshold) {
            resizeTable(Math.max(2, Integer.highestOneBit(i - 1) << 1));
        }
    }

    public int firstIndex() {
        return this.size == 0 ? -1 : 0;
    }

    public int get(Object obj) {
        int iIndexOf = indexOf(obj);
        if (iIndexOf == -1) {
            return 0;
        }
        return this.values[iIndexOf];
    }

    public Multiset.Entry<K> getEntry(int i) {
        Preconditions.checkElementIndex(i, this.size, FirebaseAnalytics.Param.INDEX);
        return new MapEntry(i);
    }

    public K getKey(int i) {
        Preconditions.checkElementIndex(i, this.size, FirebaseAnalytics.Param.INDEX);
        return (K) this.keys[i];
    }

    public int getValue(int i) {
        Preconditions.checkElementIndex(i, this.size, FirebaseAnalytics.Param.INDEX);
        return this.values[i];
    }

    public int indexOf(Object obj) {
        int iSmearedHash = Hashing.smearedHash(obj);
        int i = this.table[hashTableMask() & iSmearedHash];
        while (i != -1) {
            long j = this.entries[i];
            if (getHash(j) == iSmearedHash && Objects.equal(obj, this.keys[i])) {
                return i;
            }
            i = (int) j;
        }
        return -1;
    }

    public void init(int i, float f2) {
        Preconditions.checkArgument(i >= 0, "Initial capacity must be non-negative");
        Preconditions.checkArgument(f2 > 0.0f, "Illegal load factor");
        int iClosedTableSize = Hashing.closedTableSize(i, f2);
        this.table = newTable(iClosedTableSize);
        this.loadFactor = f2;
        this.keys = new Object[i];
        this.values = new int[i];
        this.entries = newEntries(i);
        this.threshold = Math.max(1, (int) (iClosedTableSize * f2));
    }

    public void insertEntry(int i, K k, int i2, int i3) {
        this.entries[i] = (((long) i3) << 32) | 4294967295L;
        this.keys[i] = k;
        this.values[i] = i2;
    }

    public void moveLastEntry(int i) {
        int size = size() - 1;
        if (i >= size) {
            this.keys[i] = null;
            this.values[i] = 0;
            this.entries[i] = -1;
            return;
        }
        Object[] objArr = this.keys;
        objArr[i] = objArr[size];
        int[] iArr = this.values;
        iArr[i] = iArr[size];
        objArr[size] = null;
        iArr[size] = 0;
        long[] jArr = this.entries;
        long j = jArr[size];
        jArr[i] = j;
        jArr[size] = -1;
        int hash = getHash(j) & hashTableMask();
        int[] iArr2 = this.table;
        int i2 = iArr2[hash];
        if (i2 == size) {
            iArr2[hash] = i;
            return;
        }
        while (true) {
            long[] jArr2 = this.entries;
            long j2 = jArr2[i2];
            int i3 = (int) j2;
            if (i3 == size) {
                jArr2[i2] = swapNext(j2, i);
                return;
            }
            i2 = i3;
        }
    }

    public int nextIndex(int i) {
        int i2 = i + 1;
        if (i2 < this.size) {
            return i2;
        }
        return -1;
    }

    public int nextIndexAfterRemove(int i, int i2) {
        return i - 1;
    }

    @CanIgnoreReturnValue
    public int put(K k, int i) {
        CollectPreconditions.checkPositive(i, "count");
        long[] jArr = this.entries;
        Object[] objArr = this.keys;
        int[] iArr = this.values;
        int iSmearedHash = Hashing.smearedHash(k);
        int iHashTableMask = hashTableMask() & iSmearedHash;
        int i2 = this.size;
        int[] iArr2 = this.table;
        int i3 = iArr2[iHashTableMask];
        if (i3 == -1) {
            iArr2[iHashTableMask] = i2;
        } else {
            while (true) {
                long j = jArr[i3];
                if (getHash(j) == iSmearedHash && Objects.equal(k, objArr[i3])) {
                    int i4 = iArr[i3];
                    iArr[i3] = i;
                    return i4;
                }
                int i5 = (int) j;
                if (i5 == -1) {
                    jArr[i3] = swapNext(j, i2);
                    break;
                }
                i3 = i5;
            }
        }
        if (i2 == Integer.MAX_VALUE) {
            throw new IllegalStateException("Cannot contain more than Integer.MAX_VALUE elements!");
        }
        int i6 = i2 + 1;
        resizeMeMaybe(i6);
        insertEntry(i2, k, i, iSmearedHash);
        this.size = i6;
        if (i2 >= this.threshold) {
            resizeTable(this.table.length * 2);
        }
        this.modCount++;
        return 0;
    }

    @CanIgnoreReturnValue
    public int remove(Object obj) {
        return remove(obj, Hashing.smearedHash(obj));
    }

    @CanIgnoreReturnValue
    public int removeEntry(int i) {
        return remove(this.keys[i], getHash(this.entries[i]));
    }

    public void resizeEntries(int i) {
        this.keys = Arrays.copyOf(this.keys, i);
        this.values = Arrays.copyOf(this.values, i);
        long[] jArr = this.entries;
        int length = jArr.length;
        long[] jArrCopyOf = Arrays.copyOf(jArr, i);
        if (i > length) {
            Arrays.fill(jArrCopyOf, length, i, -1L);
        }
        this.entries = jArrCopyOf;
    }

    public void setValue(int i, int i2) {
        Preconditions.checkElementIndex(i, this.size, FirebaseAnalytics.Param.INDEX);
        this.values[i] = i2;
    }

    public int size() {
        return this.size;
    }

    private int remove(Object obj, int i) {
        int iHashTableMask = hashTableMask() & i;
        int i2 = this.table[iHashTableMask];
        if (i2 == -1) {
            return 0;
        }
        int i3 = -1;
        while (true) {
            if (getHash(this.entries[i2]) == i && Objects.equal(obj, this.keys[i2])) {
                int i4 = this.values[i2];
                if (i3 == -1) {
                    this.table[iHashTableMask] = (int) this.entries[i2];
                } else {
                    long[] jArr = this.entries;
                    jArr[i3] = swapNext(jArr[i3], (int) jArr[i2]);
                }
                moveLastEntry(i2);
                this.size--;
                this.modCount++;
                return i4;
            }
            int i5 = (int) this.entries[i2];
            if (i5 == -1) {
                return 0;
            }
            i3 = i2;
            i2 = i5;
        }
    }

    public ObjectCountHashMap(ObjectCountHashMap<? extends K> objectCountHashMap) {
        init(objectCountHashMap.size(), 1.0f);
        int iFirstIndex = objectCountHashMap.firstIndex();
        while (iFirstIndex != -1) {
            put(objectCountHashMap.getKey(iFirstIndex), objectCountHashMap.getValue(iFirstIndex));
            iFirstIndex = objectCountHashMap.nextIndex(iFirstIndex);
        }
    }

    public ObjectCountHashMap(int i) {
        init(i, 1.0f);
    }

    public ObjectCountHashMap(int i, float f2) {
        init(i, f2);
    }
}
