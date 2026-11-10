package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.j2objc.annotations.Weak;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NavigableSet;
import java.util.NoSuchElementException;
import java.util.SortedSet;

/* JADX INFO: loaded from: classes.dex */
@GwtCompatible(emulated = true)
public final class SortedMultisets {

    static class ElementSet<E> extends Multisets.ElementSet<E> implements SortedSet<E> {

        @Weak
        public final SortedMultiset<E> multiset;

        public ElementSet(SortedMultiset<E> sortedMultiset) {
            this.multiset = sortedMultiset;
        }

        @Override // java.util.SortedSet
        public Comparator<? super E> comparator() {
            return multiset().comparator();
        }

        @Override // java.util.SortedSet
        public E first() {
            return (E) SortedMultisets.getElementOrThrow(multiset().firstEntry());
        }

        @Override // java.util.SortedSet
        public SortedSet<E> headSet(E e2) {
            return multiset().headMultiset(e2, BoundType.OPEN).elementSet();
        }

        @Override // com.google.common.collect.Multisets.ElementSet, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<E> iterator() {
            return new Multisets.AnonymousClass5(multiset().entrySet().iterator());
        }

        @Override // java.util.SortedSet
        public E last() {
            return (E) SortedMultisets.getElementOrThrow(multiset().lastEntry());
        }

        @Override // java.util.SortedSet
        public SortedSet<E> subSet(E e2, E e3) {
            return multiset().subMultiset(e2, BoundType.CLOSED, e3, BoundType.OPEN).elementSet();
        }

        @Override // java.util.SortedSet
        public SortedSet<E> tailSet(E e2) {
            return multiset().tailMultiset(e2, BoundType.CLOSED).elementSet();
        }

        @Override // com.google.common.collect.Multisets.ElementSet
        public final SortedMultiset<E> multiset() {
            return this.multiset;
        }
    }

    @GwtIncompatible
    static class NavigableElementSet<E> extends ElementSet<E> implements NavigableSet<E> {
        public NavigableElementSet(SortedMultiset<E> sortedMultiset) {
            super(sortedMultiset);
        }

        @Override // java.util.NavigableSet
        public E ceiling(E e2) {
            return (E) SortedMultisets.getElementOrNull(multiset().tailMultiset(e2, BoundType.CLOSED).firstEntry());
        }

        @Override // java.util.NavigableSet
        public Iterator<E> descendingIterator() {
            return descendingSet().iterator();
        }

        @Override // java.util.NavigableSet
        public NavigableSet<E> descendingSet() {
            return new NavigableElementSet(multiset().descendingMultiset());
        }

        @Override // java.util.NavigableSet
        public E floor(E e2) {
            return (E) SortedMultisets.getElementOrNull(multiset().headMultiset(e2, BoundType.CLOSED).lastEntry());
        }

        @Override // java.util.NavigableSet
        public NavigableSet<E> headSet(E e2, boolean z) {
            return new NavigableElementSet(multiset().headMultiset(e2, BoundType.forBoolean(z)));
        }

        @Override // java.util.NavigableSet
        public E higher(E e2) {
            return (E) SortedMultisets.getElementOrNull(multiset().tailMultiset(e2, BoundType.OPEN).firstEntry());
        }

        @Override // java.util.NavigableSet
        public E lower(E e2) {
            return (E) SortedMultisets.getElementOrNull(multiset().headMultiset(e2, BoundType.OPEN).lastEntry());
        }

        @Override // java.util.NavigableSet
        public E pollFirst() {
            return (E) SortedMultisets.getElementOrNull(multiset().pollFirstEntry());
        }

        @Override // java.util.NavigableSet
        public E pollLast() {
            return (E) SortedMultisets.getElementOrNull(multiset().pollLastEntry());
        }

        @Override // java.util.NavigableSet
        public NavigableSet<E> subSet(E e2, boolean z, E e3, boolean z2) {
            return new NavigableElementSet(multiset().subMultiset(e2, BoundType.forBoolean(z), e3, BoundType.forBoolean(z2)));
        }

        @Override // java.util.NavigableSet
        public NavigableSet<E> tailSet(E e2, boolean z) {
            return new NavigableElementSet(multiset().tailMultiset(e2, BoundType.forBoolean(z)));
        }
    }

    public static <E> E getElementOrNull(Multiset.Entry<E> entry) {
        if (entry == null) {
            return null;
        }
        return entry.getElement();
    }

    public static <E> E getElementOrThrow(Multiset.Entry<E> entry) {
        if (entry != null) {
            return entry.getElement();
        }
        throw new NoSuchElementException();
    }
}
