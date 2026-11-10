package com.google.firebase.database.collection;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class ImmutableSortedSet<T> implements Iterable<T> {
    public final ImmutableSortedMap<T, Void> map;

    private static class WrappedEntryIterator<T> implements Iterator<T> {
        public final Iterator<Map.Entry<T, Void>> iterator;

        public WrappedEntryIterator(Iterator<Map.Entry<T, Void>> it) {
            this.iterator = it;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.iterator.hasNext();
        }

        @Override // java.util.Iterator
        public T next() {
            return this.iterator.next().getKey();
        }

        @Override // java.util.Iterator
        public void remove() {
            this.iterator.remove();
        }
    }

    public ImmutableSortedSet(List<T> list, Comparator<T> comparator) {
        this.map = ImmutableSortedMap.Builder.buildFrom(list, Collections.emptyMap(), ImmutableSortedMap.Builder.IDENTITY_TRANSLATOR, comparator);
    }

    public boolean contains(T t) {
        return this.map.containsKey(t);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof ImmutableSortedSet) {
            return this.map.equals(((ImmutableSortedSet) obj).map);
        }
        return false;
    }

    public T getMaxEntry() {
        return this.map.getMaxKey();
    }

    public T getMinEntry() {
        return this.map.getMinKey();
    }

    public T getPredecessorEntry(T t) {
        return this.map.getPredecessorKey(t);
    }

    public int hashCode() {
        return this.map.hashCode();
    }

    public int indexOf(T t) {
        return this.map.indexOf(t);
    }

    public ImmutableSortedSet<T> insert(T t) {
        return new ImmutableSortedSet<>(this.map.insert(t, null));
    }

    public boolean isEmpty() {
        return this.map.isEmpty();
    }

    @Override // java.lang.Iterable
    public Iterator<T> iterator() {
        return new WrappedEntryIterator(this.map.iterator());
    }

    public Iterator<T> iteratorFrom(T t) {
        return new WrappedEntryIterator(this.map.iteratorFrom(t));
    }

    public ImmutableSortedSet<T> remove(T t) {
        ImmutableSortedMap<T, Void> immutableSortedMapRemove = this.map.remove(t);
        return immutableSortedMapRemove == this.map ? this : new ImmutableSortedSet<>(immutableSortedMapRemove);
    }

    public Iterator<T> reverseIterator() {
        return new WrappedEntryIterator(this.map.reverseIterator());
    }

    public Iterator<T> reverseIteratorFrom(T t) {
        return new WrappedEntryIterator(this.map.reverseIteratorFrom(t));
    }

    public int size() {
        return this.map.size();
    }

    public ImmutableSortedSet<T> unionWith(ImmutableSortedSet<T> immutableSortedSet) {
        ImmutableSortedSet<T> immutableSortedSetInsert;
        if (size() < immutableSortedSet.size()) {
            immutableSortedSetInsert = immutableSortedSet;
            immutableSortedSet = this;
        } else {
            immutableSortedSetInsert = this;
        }
        Iterator<T> it = immutableSortedSet.iterator();
        while (it.hasNext()) {
            immutableSortedSetInsert = immutableSortedSetInsert.insert(it.next());
        }
        return immutableSortedSetInsert;
    }

    public ImmutableSortedSet(ImmutableSortedMap<T, Void> immutableSortedMap) {
        this.map = immutableSortedMap;
    }
}
