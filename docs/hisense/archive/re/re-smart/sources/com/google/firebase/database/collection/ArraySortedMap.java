package com.google.firebase.database.collection;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class ArraySortedMap<K, V> extends ImmutableSortedMap<K, V> {
    public final Comparator<K> comparator;
    public final K[] keys;
    public final V[] values;

    /* JADX INFO: renamed from: com.google.firebase.database.collection.ArraySortedMap$1, reason: invalid class name */
    public class AnonymousClass1 implements Iterator<Map.Entry<K, V>> {
        public int currentPos;
        public final /* synthetic */ int val$pos;
        public final /* synthetic */ boolean val$reverse;

        public AnonymousClass1(int i, boolean z) {
            this.val$pos = i;
            this.val$reverse = z;
            this.currentPos = this.val$pos;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            if (this.val$reverse) {
                if (this.currentPos >= 0) {
                    return true;
                }
            } else if (this.currentPos < ArraySortedMap.this.keys.length) {
                return true;
            }
            return false;
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException("Can't remove elements from ImmutableSortedMap");
        }

        @Override // java.util.Iterator
        public Map.Entry<K, V> next() {
            Object obj = ArraySortedMap.this.keys[this.currentPos];
            Object[] objArr = ArraySortedMap.this.values;
            int i = this.currentPos;
            Object obj2 = objArr[i];
            this.currentPos = this.val$reverse ? i - 1 : i + 1;
            return new AbstractMap.SimpleImmutableEntry(obj, obj2);
        }
    }

    public ArraySortedMap(Comparator<K> comparator) {
        this.keys = (K[]) new Object[0];
        this.values = (V[]) new Object[0];
        this.comparator = comparator;
    }

    public static <T> T[] addToArray(T[] tArr, int i, T t) {
        int length = tArr.length + 1;
        T[] tArr2 = (T[]) new Object[length];
        System.arraycopy(tArr, 0, tArr2, 0, i);
        tArr2[i] = t;
        System.arraycopy(tArr, i, tArr2, i + 1, (length - i) - 1);
        return tArr2;
    }

    public static <A, B, C> ArraySortedMap<A, C> buildFrom(List<A> list, Map<B, C> map, ImmutableSortedMap.Builder.KeyTranslator<A, B> keyTranslator, Comparator<A> comparator) {
        Collections.sort(list, comparator);
        int size = list.size();
        Object[] objArr = new Object[size];
        Object[] objArr2 = new Object[size];
        int i = 0;
        for (A a2 : list) {
            objArr[i] = a2;
            objArr2[i] = map.get(keyTranslator.translate(a2));
            i++;
        }
        return new ArraySortedMap<>(comparator, objArr, objArr2);
    }

    private int findKey(K k) {
        int i = 0;
        for (K k2 : this.keys) {
            if (this.comparator.compare(k, k2) == 0) {
                return i;
            }
            i++;
        }
        return -1;
    }

    private int findKeyOrInsertPosition(K k) {
        int i = 0;
        while (true) {
            K[] kArr = this.keys;
            if (i >= kArr.length || this.comparator.compare(kArr[i], k) >= 0) {
                break;
            }
            i++;
        }
        return i;
    }

    public static <K, V> ArraySortedMap<K, V> fromMap(Map<K, V> map, Comparator<K> comparator) {
        return buildFrom(new ArrayList(map.keySet()), map, ImmutableSortedMap.Builder.IDENTITY_TRANSLATOR, comparator);
    }

    public static <T> T[] removeFromArray(T[] tArr, int i) {
        int length = tArr.length - 1;
        T[] tArr2 = (T[]) new Object[length];
        System.arraycopy(tArr, 0, tArr2, 0, i);
        System.arraycopy(tArr, i + 1, tArr2, i, length - i);
        return tArr2;
    }

    public static <T> T[] replaceInArray(T[] tArr, int i, T t) {
        int length = tArr.length;
        T[] tArr2 = (T[]) new Object[length];
        System.arraycopy(tArr, 0, tArr2, 0, length);
        tArr2[i] = t;
        return tArr2;
    }

    @Override // com.google.firebase.database.collection.ImmutableSortedMap
    public boolean containsKey(K k) {
        return findKey(k) != -1;
    }

    @Override // com.google.firebase.database.collection.ImmutableSortedMap
    public V get(K k) {
        int iFindKey = findKey(k);
        if (iFindKey != -1) {
            return this.values[iFindKey];
        }
        return null;
    }

    @Override // com.google.firebase.database.collection.ImmutableSortedMap
    public Comparator<K> getComparator() {
        return this.comparator;
    }

    @Override // com.google.firebase.database.collection.ImmutableSortedMap
    public K getMaxKey() {
        K[] kArr = this.keys;
        if (kArr.length > 0) {
            return kArr[kArr.length - 1];
        }
        return null;
    }

    @Override // com.google.firebase.database.collection.ImmutableSortedMap
    public K getMinKey() {
        K[] kArr = this.keys;
        if (kArr.length > 0) {
            return kArr[0];
        }
        return null;
    }

    @Override // com.google.firebase.database.collection.ImmutableSortedMap
    public K getPredecessorKey(K k) {
        int iFindKey = findKey(k);
        if (iFindKey == -1) {
            throw new IllegalArgumentException("Can't find predecessor of nonexistent key");
        }
        if (iFindKey > 0) {
            return this.keys[iFindKey - 1];
        }
        return null;
    }

    @Override // com.google.firebase.database.collection.ImmutableSortedMap
    public K getSuccessorKey(K k) {
        int iFindKey = findKey(k);
        if (iFindKey == -1) {
            throw new IllegalArgumentException("Can't find successor of nonexistent key");
        }
        K[] kArr = this.keys;
        if (iFindKey < kArr.length - 1) {
            return kArr[iFindKey + 1];
        }
        return null;
    }

    @Override // com.google.firebase.database.collection.ImmutableSortedMap
    public void inOrderTraversal(LLRBNode.NodeVisitor<K, V> nodeVisitor) {
        int i = 0;
        while (true) {
            K[] kArr = this.keys;
            if (i >= kArr.length) {
                return;
            }
            nodeVisitor.visitEntry(kArr[i], this.values[i]);
            i++;
        }
    }

    @Override // com.google.firebase.database.collection.ImmutableSortedMap
    public int indexOf(K k) {
        return findKey(k);
    }

    @Override // com.google.firebase.database.collection.ImmutableSortedMap
    public ImmutableSortedMap<K, V> insert(K k, V v) {
        int iFindKey = findKey(k);
        if (iFindKey != -1) {
            if (this.keys[iFindKey] == k && this.values[iFindKey] == v) {
                return this;
            }
            return new ArraySortedMap(this.comparator, replaceInArray(this.keys, iFindKey, k), replaceInArray(this.values, iFindKey, v));
        }
        K[] kArr = this.keys;
        if (kArr.length <= 25) {
            int iFindKeyOrInsertPosition = findKeyOrInsertPosition(k);
            return new ArraySortedMap(this.comparator, addToArray(this.keys, iFindKeyOrInsertPosition, k), addToArray(this.values, iFindKeyOrInsertPosition, v));
        }
        HashMap map = new HashMap(kArr.length + 1);
        int i = 0;
        while (true) {
            K[] kArr2 = this.keys;
            if (i >= kArr2.length) {
                map.put(k, v);
                return RBTreeSortedMap.fromMap(map, this.comparator);
            }
            map.put(kArr2[i], this.values[i]);
            i++;
        }
    }

    @Override // com.google.firebase.database.collection.ImmutableSortedMap
    public boolean isEmpty() {
        return this.keys.length == 0;
    }

    @Override // com.google.firebase.database.collection.ImmutableSortedMap, java.lang.Iterable
    public Iterator<Map.Entry<K, V>> iterator() {
        return new AnonymousClass1(0, false);
    }

    @Override // com.google.firebase.database.collection.ImmutableSortedMap
    public Iterator<Map.Entry<K, V>> iteratorFrom(K k) {
        return new AnonymousClass1(findKeyOrInsertPosition(k), false);
    }

    @Override // com.google.firebase.database.collection.ImmutableSortedMap
    public ImmutableSortedMap<K, V> remove(K k) {
        int iFindKey = findKey(k);
        if (iFindKey == -1) {
            return this;
        }
        return new ArraySortedMap(this.comparator, removeFromArray(this.keys, iFindKey), removeFromArray(this.values, iFindKey));
    }

    @Override // com.google.firebase.database.collection.ImmutableSortedMap
    public Iterator<Map.Entry<K, V>> reverseIterator() {
        return new AnonymousClass1(this.keys.length - 1, true);
    }

    @Override // com.google.firebase.database.collection.ImmutableSortedMap
    public Iterator<Map.Entry<K, V>> reverseIteratorFrom(K k) {
        int iFindKeyOrInsertPosition = findKeyOrInsertPosition(k);
        K[] kArr = this.keys;
        return (iFindKeyOrInsertPosition >= kArr.length || this.comparator.compare(kArr[iFindKeyOrInsertPosition], k) != 0) ? new AnonymousClass1(iFindKeyOrInsertPosition - 1, true) : new AnonymousClass1(iFindKeyOrInsertPosition, true);
    }

    @Override // com.google.firebase.database.collection.ImmutableSortedMap
    public int size() {
        return this.keys.length;
    }

    private Iterator<Map.Entry<K, V>> iterator(int i, boolean z) {
        return new AnonymousClass1(i, z);
    }

    public ArraySortedMap(Comparator<K> comparator, K[] kArr, V[] vArr) {
        this.keys = kArr;
        this.values = vArr;
        this.comparator = comparator;
    }
}
