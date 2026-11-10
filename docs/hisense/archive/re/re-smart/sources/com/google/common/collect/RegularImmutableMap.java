package com.google.common.collect;

import b.a.a.a.a;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.ju.lib.datalayer.database.asist.SQLStatement;
import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
@GwtCompatible(emulated = true, serializable = true)
public final class RegularImmutableMap<K, V> extends ImmutableMap<K, V> {
    public static final byte ABSENT = -1;
    public static final int BYTE_MASK = 255;
    public static final int BYTE_MAX_SIZE = 128;
    public static final ImmutableMap<Object, Object> EMPTY = new RegularImmutableMap(null, new Object[0], 0);
    public static final int SHORT_MASK = 65535;
    public static final int SHORT_MAX_SIZE = 32768;
    public static final long serialVersionUID = 0;

    @VisibleForTesting
    public final transient Object[] alternatingKeysAndValues;
    public final transient Object hashTable;
    public final transient int size;

    static class EntrySet<K, V> extends ImmutableSet<Map.Entry<K, V>> {
        public final transient Object[] alternatingKeysAndValues;
        public final transient int keyOffset;
        public final transient ImmutableMap<K, V> map;
        public final transient int size;

        public EntrySet(ImmutableMap<K, V> immutableMap, Object[] objArr, int i, int i2) {
            this.map = immutableMap;
            this.alternatingKeysAndValues = objArr;
            this.keyOffset = i;
            this.size = i2;
        }

        @Override // com.google.common.collect.ImmutableCollection, java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object obj) {
            if (!(obj instanceof Map.Entry)) {
                return false;
            }
            Map.Entry entry = (Map.Entry) obj;
            Object key = entry.getKey();
            Object value = entry.getValue();
            return value != null && value.equals(this.map.get(key));
        }

        @Override // com.google.common.collect.ImmutableCollection
        public int copyIntoArray(Object[] objArr, int i) {
            return asList().copyIntoArray(objArr, i);
        }

        @Override // com.google.common.collect.ImmutableSet
        public ImmutableList<Map.Entry<K, V>> createAsList() {
            return new ImmutableList<Map.Entry<K, V>>() { // from class: com.google.common.collect.RegularImmutableMap.EntrySet.1
                @Override // com.google.common.collect.ImmutableCollection
                public boolean isPartialView() {
                    return true;
                }

                @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
                public int size() {
                    return EntrySet.this.size;
                }

                @Override // java.util.List
                public Map.Entry<K, V> get(int i) {
                    Preconditions.checkElementIndex(i, EntrySet.this.size, FirebaseAnalytics.Param.INDEX);
                    int i2 = i * 2;
                    return new AbstractMap.SimpleImmutableEntry(EntrySet.this.alternatingKeysAndValues[EntrySet.this.keyOffset + i2], EntrySet.this.alternatingKeysAndValues[i2 + (EntrySet.this.keyOffset ^ 1)]);
                }
            };
        }

        @Override // com.google.common.collect.ImmutableCollection
        public boolean isPartialView() {
            return true;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return this.size;
        }

        @Override // com.google.common.collect.ImmutableSet, com.google.common.collect.ImmutableCollection, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set, java.util.NavigableSet, com.google.common.collect.SortedIterable
        public UnmodifiableIterator<Map.Entry<K, V>> iterator() {
            return asList().iterator();
        }
    }

    static final class KeySet<K> extends ImmutableSet<K> {
        public final transient ImmutableList<K> list;
        public final transient ImmutableMap<K, ?> map;

        public KeySet(ImmutableMap<K, ?> immutableMap, ImmutableList<K> immutableList) {
            this.map = immutableMap;
            this.list = immutableList;
        }

        @Override // com.google.common.collect.ImmutableSet, com.google.common.collect.ImmutableCollection
        public ImmutableList<K> asList() {
            return this.list;
        }

        @Override // com.google.common.collect.ImmutableCollection, java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object obj) {
            return this.map.get(obj) != null;
        }

        @Override // com.google.common.collect.ImmutableCollection
        public int copyIntoArray(Object[] objArr, int i) {
            return asList().copyIntoArray(objArr, i);
        }

        @Override // com.google.common.collect.ImmutableCollection
        public boolean isPartialView() {
            return true;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return this.map.size();
        }

        @Override // com.google.common.collect.ImmutableSet, com.google.common.collect.ImmutableCollection, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set, java.util.NavigableSet, com.google.common.collect.SortedIterable
        public UnmodifiableIterator<K> iterator() {
            return asList().iterator();
        }
    }

    static final class KeysOrValuesAsList extends ImmutableList<Object> {
        public final transient Object[] alternatingKeysAndValues;
        public final transient int offset;
        public final transient int size;

        public KeysOrValuesAsList(Object[] objArr, int i, int i2) {
            this.alternatingKeysAndValues = objArr;
            this.offset = i;
            this.size = i2;
        }

        @Override // java.util.List
        public Object get(int i) {
            Preconditions.checkElementIndex(i, this.size, FirebaseAnalytics.Param.INDEX);
            return this.alternatingKeysAndValues[(i * 2) + this.offset];
        }

        @Override // com.google.common.collect.ImmutableCollection
        public boolean isPartialView() {
            return true;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public int size() {
            return this.size;
        }
    }

    public RegularImmutableMap(Object obj, Object[] objArr, int i) {
        this.hashTable = obj;
        this.alternatingKeysAndValues = objArr;
        this.size = i;
    }

    public static <K, V> RegularImmutableMap<K, V> create(int i, Object[] objArr) {
        if (i == 0) {
            return (RegularImmutableMap) EMPTY;
        }
        if (i == 1) {
            CollectPreconditions.checkEntryNotNull(objArr[0], objArr[1]);
            return new RegularImmutableMap<>(null, objArr, 1);
        }
        Preconditions.checkPositionIndex(i, objArr.length >> 1, FirebaseAnalytics.Param.INDEX);
        return new RegularImmutableMap<>(createHashTable(objArr, i, ImmutableSet.chooseTableSize(i), 0), objArr, i);
    }

    public static Object createHashTable(Object[] objArr, int i, int i2, int i3) {
        int i4;
        int i5;
        int i6;
        if (i == 1) {
            CollectPreconditions.checkEntryNotNull(objArr[i3], objArr[i3 ^ 1]);
            return null;
        }
        int i7 = i2 - 1;
        int i8 = 0;
        if (i2 <= 128) {
            byte[] bArr = new byte[i2];
            Arrays.fill(bArr, (byte) -1);
            while (i8 < i) {
                int i9 = (i8 * 2) + i3;
                Object obj = objArr[i9];
                Object obj2 = objArr[i9 ^ 1];
                CollectPreconditions.checkEntryNotNull(obj, obj2);
                int iSmear = Hashing.smear(obj.hashCode());
                while (true) {
                    i6 = iSmear & i7;
                    int i10 = bArr[i6] & 255;
                    if (i10 == 255) {
                        break;
                    }
                    if (objArr[i10].equals(obj)) {
                        throw duplicateKeyException(obj, obj2, objArr, i10);
                    }
                    iSmear = i6 + 1;
                }
                bArr[i6] = (byte) i9;
                i8++;
            }
            return bArr;
        }
        if (i2 <= 32768) {
            short[] sArr = new short[i2];
            Arrays.fill(sArr, (short) -1);
            while (i8 < i) {
                int i11 = (i8 * 2) + i3;
                Object obj3 = objArr[i11];
                Object obj4 = objArr[i11 ^ 1];
                CollectPreconditions.checkEntryNotNull(obj3, obj4);
                int iSmear2 = Hashing.smear(obj3.hashCode());
                while (true) {
                    i5 = iSmear2 & i7;
                    int i12 = sArr[i5] & SQLStatement.NONE;
                    if (i12 == 65535) {
                        break;
                    }
                    if (objArr[i12].equals(obj3)) {
                        throw duplicateKeyException(obj3, obj4, objArr, i12);
                    }
                    iSmear2 = i5 + 1;
                }
                sArr[i5] = (short) i11;
                i8++;
            }
            return sArr;
        }
        int[] iArr = new int[i2];
        Arrays.fill(iArr, -1);
        while (i8 < i) {
            int i13 = (i8 * 2) + i3;
            Object obj5 = objArr[i13];
            Object obj6 = objArr[i13 ^ 1];
            CollectPreconditions.checkEntryNotNull(obj5, obj6);
            int iSmear3 = Hashing.smear(obj5.hashCode());
            while (true) {
                i4 = iSmear3 & i7;
                int i14 = iArr[i4];
                if (i14 == -1) {
                    break;
                }
                if (objArr[i14].equals(obj5)) {
                    throw duplicateKeyException(obj5, obj6, objArr, i14);
                }
                iSmear3 = i4 + 1;
            }
            iArr[i4] = i13;
            i8++;
        }
        return iArr;
    }

    public static IllegalArgumentException duplicateKeyException(Object obj, Object obj2, Object[] objArr, int i) {
        String strValueOf = String.valueOf(obj);
        String strValueOf2 = String.valueOf(obj2);
        String strValueOf3 = String.valueOf(objArr[i]);
        String strValueOf4 = String.valueOf(objArr[i ^ 1]);
        StringBuilder sbA = a.a(strValueOf4.length() + strValueOf3.length() + strValueOf2.length() + strValueOf.length() + 39, "Multiple entries with same key: ", strValueOf, "=", strValueOf2);
        sbA.append(" and ");
        sbA.append(strValueOf3);
        sbA.append("=");
        sbA.append(strValueOf4);
        return new IllegalArgumentException(sbA.toString());
    }

    @Override // com.google.common.collect.ImmutableMap
    public ImmutableSet<Map.Entry<K, V>> createEntrySet() {
        return new EntrySet(this, this.alternatingKeysAndValues, 0, this.size);
    }

    @Override // com.google.common.collect.ImmutableMap
    public ImmutableSet<K> createKeySet() {
        return new KeySet(this, new KeysOrValuesAsList(this.alternatingKeysAndValues, 0, this.size));
    }

    @Override // com.google.common.collect.ImmutableMap
    public ImmutableCollection<V> createValues() {
        return new KeysOrValuesAsList(this.alternatingKeysAndValues, 1, this.size);
    }

    @Override // com.google.common.collect.ImmutableMap, java.util.Map
    public V get(Object obj) {
        return (V) get(this.hashTable, this.alternatingKeysAndValues, this.size, 0, obj);
    }

    @Override // com.google.common.collect.ImmutableMap
    public boolean isPartialView() {
        return false;
    }

    @Override // java.util.Map
    public int size() {
        return this.size;
    }

    public static Object get(Object obj, Object[] objArr, int i, int i2, Object obj2) {
        if (obj2 == null) {
            return null;
        }
        if (i == 1) {
            if (objArr[i2].equals(obj2)) {
                return objArr[i2 ^ 1];
            }
            return null;
        }
        if (obj == null) {
            return null;
        }
        if (obj instanceof byte[]) {
            byte[] bArr = (byte[]) obj;
            int length = bArr.length - 1;
            int iSmear = Hashing.smear(obj2.hashCode());
            while (true) {
                int i3 = iSmear & length;
                int i4 = bArr[i3] & 255;
                if (i4 == 255) {
                    return null;
                }
                if (objArr[i4].equals(obj2)) {
                    return objArr[i4 ^ 1];
                }
                iSmear = i3 + 1;
            }
        } else if (obj instanceof short[]) {
            short[] sArr = (short[]) obj;
            int length2 = sArr.length - 1;
            int iSmear2 = Hashing.smear(obj2.hashCode());
            while (true) {
                int i5 = iSmear2 & length2;
                int i6 = sArr[i5] & SQLStatement.NONE;
                if (i6 == 65535) {
                    return null;
                }
                if (objArr[i6].equals(obj2)) {
                    return objArr[i6 ^ 1];
                }
                iSmear2 = i5 + 1;
            }
        } else {
            int[] iArr = (int[]) obj;
            int length3 = iArr.length - 1;
            int iSmear3 = Hashing.smear(obj2.hashCode());
            while (true) {
                int i7 = iSmear3 & length3;
                int i8 = iArr[i7];
                if (i8 == -1) {
                    return null;
                }
                if (objArr[i8].equals(obj2)) {
                    return objArr[i8 ^ 1];
                }
                iSmear3 = i7 + 1;
            }
        }
    }
}
