package com.google.common.collect;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.google.common.primitives.Ints;
import java.util.Comparator;

/* JADX INFO: loaded from: classes.dex */
@GwtIncompatible
public final class RegularImmutableSortedMultiset<E> extends ImmutableSortedMultiset<E> {
    public final transient long[] cumulativeCounts;

    @VisibleForTesting
    public final transient RegularImmutableSortedSet<E> elementSet;
    public final transient int length;
    public final transient int offset;
    public static final long[] ZERO_CUMULATIVE_COUNTS = {0};
    public static final ImmutableSortedMultiset<Comparable> NATURAL_EMPTY_MULTISET = new RegularImmutableSortedMultiset(Ordering.natural());

    public RegularImmutableSortedMultiset(Comparator<? super E> comparator) {
        this.elementSet = ImmutableSortedSet.emptySet(comparator);
        this.cumulativeCounts = ZERO_CUMULATIVE_COUNTS;
        this.offset = 0;
        this.length = 0;
    }

    private int getCount(int i) {
        long[] jArr = this.cumulativeCounts;
        int i2 = this.offset;
        return (int) (jArr[(i2 + i) + 1] - jArr[i2 + i]);
    }

    @Override // com.google.common.collect.Multiset
    public int count(Object obj) {
        int iIndexOf = this.elementSet.indexOf(obj);
        if (iIndexOf >= 0) {
            return getCount(iIndexOf);
        }
        return 0;
    }

    @Override // com.google.common.collect.SortedMultiset
    public Multiset.Entry<E> firstEntry() {
        if (isEmpty()) {
            return null;
        }
        return getEntry(0);
    }

    @Override // com.google.common.collect.ImmutableMultiset
    public Multiset.Entry<E> getEntry(int i) {
        return new Multisets.ImmutableEntry(this.elementSet.asList().get(i), getCount(i));
    }

    public ImmutableSortedMultiset<E> getSubMultiset(int i, int i2) {
        Preconditions.checkPositionIndexes(i, i2, this.length);
        if (i == i2) {
            return ImmutableSortedMultiset.emptyMultiset(comparator());
        }
        return (i == 0 && i2 == this.length) ? this : new RegularImmutableSortedMultiset(this.elementSet.getSubSet(i, i2), this.cumulativeCounts, this.offset + i, i2 - i);
    }

    @Override // com.google.common.collect.ImmutableCollection
    public boolean isPartialView() {
        return this.offset > 0 || this.length < this.cumulativeCounts.length - 1;
    }

    @Override // com.google.common.collect.SortedMultiset
    public Multiset.Entry<E> lastEntry() {
        if (isEmpty()) {
            return null;
        }
        return getEntry(this.length - 1);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, com.google.common.collect.Multiset
    public int size() {
        long[] jArr = this.cumulativeCounts;
        int i = this.offset;
        return Ints.saturatedCast(jArr[this.length + i] - jArr[i]);
    }

    @Override // com.google.common.collect.ImmutableSortedMultiset, com.google.common.collect.SortedMultiset
    public ImmutableSortedMultiset<E> headMultiset(E e2, BoundType boundType) {
        RegularImmutableSortedSet<E> regularImmutableSortedSet = this.elementSet;
        if (boundType != null) {
            return getSubMultiset(0, regularImmutableSortedSet.headIndex(e2, boundType == BoundType.CLOSED));
        }
        throw new NullPointerException();
    }

    @Override // com.google.common.collect.ImmutableSortedMultiset, com.google.common.collect.SortedMultiset
    public ImmutableSortedMultiset<E> tailMultiset(E e2, BoundType boundType) {
        RegularImmutableSortedSet<E> regularImmutableSortedSet = this.elementSet;
        if (boundType != null) {
            return getSubMultiset(regularImmutableSortedSet.tailIndex(e2, boundType == BoundType.CLOSED), this.length);
        }
        throw new NullPointerException();
    }

    @Override // com.google.common.collect.ImmutableSortedMultiset, com.google.common.collect.ImmutableMultiset, com.google.common.collect.Multiset
    public ImmutableSortedSet<E> elementSet() {
        return this.elementSet;
    }

    public RegularImmutableSortedMultiset(RegularImmutableSortedSet<E> regularImmutableSortedSet, long[] jArr, int i, int i2) {
        this.elementSet = regularImmutableSortedSet;
        this.cumulativeCounts = jArr;
        this.offset = i;
        this.length = i2;
    }
}
