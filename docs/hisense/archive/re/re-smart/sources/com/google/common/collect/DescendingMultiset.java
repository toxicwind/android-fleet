package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NavigableSet;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
@GwtCompatible(emulated = true)
public abstract class DescendingMultiset<E> extends ForwardingMultiset<E> implements SortedMultiset<E> {
    public transient Comparator<? super E> comparator;
    public transient NavigableSet<E> elementSet;
    public transient Set<Multiset.Entry<E>> entrySet;

    @Override // com.google.common.collect.SortedMultiset, com.google.common.collect.SortedIterable
    public Comparator<? super E> comparator() {
        Comparator<? super E> comparator = this.comparator;
        if (comparator != null) {
            return comparator;
        }
        Ordering orderingReverse = Ordering.from(forwardMultiset().comparator()).reverse();
        this.comparator = orderingReverse;
        return orderingReverse;
    }

    public Set<Multiset.Entry<E>> createEntrySet() {
        return new Multisets.EntrySet<E>() { // from class: com.google.common.collect.DescendingMultiset.1EntrySetImpl
            @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
            public Iterator<Multiset.Entry<E>> iterator() {
                return DescendingMultiset.this.entryIterator();
            }

            @Override // com.google.common.collect.Multisets.EntrySet
            public Multiset<E> multiset() {
                return DescendingMultiset.this;
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
            public int size() {
                return DescendingMultiset.this.forwardMultiset().entrySet().size();
            }
        };
    }

    @Override // com.google.common.collect.SortedMultiset
    public SortedMultiset<E> descendingMultiset() {
        return forwardMultiset();
    }

    public abstract Iterator<Multiset.Entry<E>> entryIterator();

    @Override // com.google.common.collect.ForwardingMultiset, com.google.common.collect.Multiset
    public Set<Multiset.Entry<E>> entrySet() {
        Set<Multiset.Entry<E>> set = this.entrySet;
        if (set != null) {
            return set;
        }
        Set<Multiset.Entry<E>> setCreateEntrySet = createEntrySet();
        this.entrySet = setCreateEntrySet;
        return setCreateEntrySet;
    }

    @Override // com.google.common.collect.SortedMultiset
    public Multiset.Entry<E> firstEntry() {
        return forwardMultiset().lastEntry();
    }

    public abstract SortedMultiset<E> forwardMultiset();

    @Override // com.google.common.collect.SortedMultiset
    public SortedMultiset<E> headMultiset(E e2, BoundType boundType) {
        return forwardMultiset().tailMultiset(e2, boundType).descendingMultiset();
    }

    @Override // com.google.common.collect.ForwardingCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public Iterator<E> iterator() {
        return Multisets.iteratorImpl(this);
    }

    @Override // com.google.common.collect.SortedMultiset
    public Multiset.Entry<E> lastEntry() {
        return forwardMultiset().firstEntry();
    }

    @Override // com.google.common.collect.SortedMultiset
    public Multiset.Entry<E> pollFirstEntry() {
        return forwardMultiset().pollLastEntry();
    }

    @Override // com.google.common.collect.SortedMultiset
    public Multiset.Entry<E> pollLastEntry() {
        return forwardMultiset().pollFirstEntry();
    }

    @Override // com.google.common.collect.SortedMultiset
    public SortedMultiset<E> subMultiset(E e2, BoundType boundType, E e3, BoundType boundType2) {
        return forwardMultiset().subMultiset(e3, boundType2, e2, boundType).descendingMultiset();
    }

    @Override // com.google.common.collect.SortedMultiset
    public SortedMultiset<E> tailMultiset(E e2, BoundType boundType) {
        return forwardMultiset().headMultiset(e2, boundType).descendingMultiset();
    }

    @Override // com.google.common.collect.ForwardingCollection, java.util.Collection, java.util.Set
    public Object[] toArray() {
        return standardToArray();
    }

    @Override // com.google.common.collect.ForwardingObject
    public String toString() {
        return entrySet().toString();
    }

    @Override // com.google.common.collect.ForwardingCollection, java.util.Collection, java.util.Set
    public <T> T[] toArray(T[] tArr) {
        return (T[]) standardToArray(tArr);
    }

    @Override // com.google.common.collect.ForwardingMultiset, com.google.common.collect.ForwardingCollection, com.google.common.collect.ForwardingObject
    public Multiset<E> delegate() {
        return forwardMultiset();
    }

    @Override // com.google.common.collect.ForwardingMultiset, com.google.common.collect.Multiset
    public NavigableSet<E> elementSet() {
        NavigableSet<E> navigableSet = this.elementSet;
        if (navigableSet != null) {
            return navigableSet;
        }
        SortedMultisets.NavigableElementSet navigableElementSet = new SortedMultisets.NavigableElementSet(this);
        this.elementSet = navigableElementSet;
        return navigableElementSet;
    }
}
