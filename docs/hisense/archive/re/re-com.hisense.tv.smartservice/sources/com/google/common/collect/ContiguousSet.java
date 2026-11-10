package com.google.common.collect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import java.lang.Comparable;
import java.util.NoSuchElementException;

/* JADX INFO: loaded from: classes.dex */
@GwtCompatible(emulated = true)
public abstract class ContiguousSet<C extends Comparable> extends ImmutableSortedSet<C> {
    public final DiscreteDomain<C> domain;

    public ContiguousSet(DiscreteDomain<C> discreteDomain) {
        super(Ordering.natural());
        this.domain = discreteDomain;
    }

    @Deprecated
    public static <E> ImmutableSortedSet.Builder<E> builder() {
        throw new UnsupportedOperationException();
    }

    @Beta
    public static ContiguousSet<Integer> closed(int i, int i2) {
        return create(Range.closed(Integer.valueOf(i), Integer.valueOf(i2)), DiscreteDomain.IntegerDomain.INSTANCE);
    }

    @Beta
    public static ContiguousSet<Integer> closedOpen(int i, int i2) {
        return create(Range.closedOpen(Integer.valueOf(i), Integer.valueOf(i2)), DiscreteDomain.IntegerDomain.INSTANCE);
    }

    public static <C extends Comparable> ContiguousSet<C> create(Range<C> range, DiscreteDomain<C> discreteDomain) {
        if (range == null) {
            throw new NullPointerException();
        }
        if (discreteDomain == null) {
            throw new NullPointerException();
        }
        try {
            Range<C> rangeIntersection = !range.hasLowerBound() ? range.intersection(Range.atLeast(discreteDomain.minValue())) : range;
            if (!range.hasUpperBound()) {
                rangeIntersection = rangeIntersection.intersection(Range.atMost(discreteDomain.maxValue()));
            }
            return rangeIntersection.isEmpty() || Range.compareOrThrow(range.lowerBound.leastValueAbove(discreteDomain), range.upperBound.greatestValueBelow(discreteDomain)) > 0 ? new EmptyContiguousSet(discreteDomain) : new RegularContiguousSet(rangeIntersection, discreteDomain);
        } catch (NoSuchElementException e2) {
            throw new IllegalArgumentException(e2);
        }
    }

    @Override // com.google.common.collect.ImmutableSortedSet
    @GwtIncompatible
    public ImmutableSortedSet<C> createDescendingSet() {
        return new DescendingImmutableSortedSet(this);
    }

    @Override // com.google.common.collect.ImmutableSortedSet
    public abstract ContiguousSet<C> headSetImpl(C c2, boolean z);

    public abstract ContiguousSet<C> intersection(ContiguousSet<C> contiguousSet);

    public abstract Range<C> range();

    public abstract Range<C> range(BoundType boundType, BoundType boundType2);

    @Override // com.google.common.collect.ImmutableSortedSet
    public abstract ContiguousSet<C> subSetImpl(C c2, boolean z, C c3, boolean z2);

    @Override // com.google.common.collect.ImmutableSortedSet
    public abstract ContiguousSet<C> tailSetImpl(C c2, boolean z);

    @Override // java.util.AbstractCollection
    public String toString() {
        return range().toString();
    }

    @Beta
    public static ContiguousSet<Long> closed(long j, long j2) {
        return create(Range.closed(Long.valueOf(j), Long.valueOf(j2)), DiscreteDomain.LongDomain.INSTANCE);
    }

    @Beta
    public static ContiguousSet<Long> closedOpen(long j, long j2) {
        return create(Range.closedOpen(Long.valueOf(j), Long.valueOf(j2)), DiscreteDomain.LongDomain.INSTANCE);
    }

    @Override // com.google.common.collect.ImmutableSortedSet, java.util.NavigableSet, java.util.SortedSet
    public ContiguousSet<C> headSet(C c2) {
        if (c2 != null) {
            return headSetImpl((Comparable) c2, false);
        }
        throw new NullPointerException();
    }

    @Override // com.google.common.collect.ImmutableSortedSet, java.util.NavigableSet, java.util.SortedSet
    public ContiguousSet<C> subSet(C c2, C c3) {
        if (c2 == null) {
            throw new NullPointerException();
        }
        if (c3 != null) {
            Preconditions.checkArgument(comparator().compare(c2, c3) <= 0);
            return subSetImpl((Comparable) c2, true, (Comparable) c3, false);
        }
        throw new NullPointerException();
    }

    @Override // com.google.common.collect.ImmutableSortedSet, java.util.NavigableSet, java.util.SortedSet
    public ContiguousSet<C> tailSet(C c2) {
        if (c2 != null) {
            return tailSetImpl((Comparable) c2, true);
        }
        throw new NullPointerException();
    }

    @Override // com.google.common.collect.ImmutableSortedSet, java.util.NavigableSet
    @GwtIncompatible
    public ContiguousSet<C> headSet(C c2, boolean z) {
        if (c2 != null) {
            return headSetImpl((Comparable) c2, z);
        }
        throw new NullPointerException();
    }

    @Override // com.google.common.collect.ImmutableSortedSet, java.util.NavigableSet
    @GwtIncompatible
    public ContiguousSet<C> tailSet(C c2, boolean z) {
        if (c2 != null) {
            return tailSetImpl((Comparable) c2, z);
        }
        throw new NullPointerException();
    }

    @Override // com.google.common.collect.ImmutableSortedSet, java.util.NavigableSet
    @GwtIncompatible
    public ContiguousSet<C> subSet(C c2, boolean z, C c3, boolean z2) {
        if (c2 == null) {
            throw new NullPointerException();
        }
        if (c3 != null) {
            Preconditions.checkArgument(comparator().compare(c2, c3) <= 0);
            return subSetImpl((Comparable) c2, z, (Comparable) c3, z2);
        }
        throw new NullPointerException();
    }
}
