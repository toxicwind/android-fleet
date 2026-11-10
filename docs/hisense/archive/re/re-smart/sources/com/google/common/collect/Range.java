package com.google.common.collect;

import b.a.a.a.a;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import java.io.Serializable;
import java.lang.Comparable;
import java.util.Comparator;
import java.util.Iterator;
import java.util.SortedSet;

/* JADX INFO: loaded from: classes.dex */
@GwtCompatible
public final class Range<C extends Comparable> extends RangeGwtSerializationDependencies implements Predicate<C>, Serializable {
    public static final Range<Comparable> ALL = new Range<>(Cut.belowAll(), Cut.aboveAll());
    public static final long serialVersionUID = 0;
    public final Cut<C> lowerBound;
    public final Cut<C> upperBound;

    /* JADX INFO: renamed from: com.google.common.collect.Range$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        public static final /* synthetic */ int[] $SwitchMap$com$google$common$collect$BoundType = new int[BoundType.values().length];

        static {
            try {
                $SwitchMap$com$google$common$collect$BoundType[BoundType.OPEN.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$common$collect$BoundType[BoundType.CLOSED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    static class LowerBoundFn implements Function<Range, Cut> {
        public static final LowerBoundFn INSTANCE = new LowerBoundFn();

        @Override // com.google.common.base.Function
        public Cut apply(Range range) {
            return range.lowerBound;
        }
    }

    private static class RangeLexOrdering extends Ordering<Range<?>> implements Serializable {
        public static final Ordering<Range<?>> INSTANCE = new RangeLexOrdering();
        public static final long serialVersionUID = 0;

        @Override // com.google.common.collect.Ordering, java.util.Comparator
        public int compare(Range<?> range, Range<?> range2) {
            return ComparisonChain.ACTIVE.compare(range.lowerBound, range2.lowerBound).compare(range.upperBound, range2.upperBound).result();
        }
    }

    static class UpperBoundFn implements Function<Range, Cut> {
        public static final UpperBoundFn INSTANCE = new UpperBoundFn();

        @Override // com.google.common.base.Function
        public Cut apply(Range range) {
            return range.upperBound;
        }
    }

    public Range(Cut<C> cut, Cut<C> cut2) {
        if (cut == null) {
            throw new NullPointerException();
        }
        this.lowerBound = cut;
        if (cut2 == null) {
            throw new NullPointerException();
        }
        this.upperBound = cut2;
        if (cut.compareTo((Cut) cut2) > 0 || cut == Cut.aboveAll() || cut2 == Cut.belowAll()) {
            String strValueOf = String.valueOf(toString(cut, cut2));
            throw new IllegalArgumentException(strValueOf.length() != 0 ? "Invalid range: ".concat(strValueOf) : new String("Invalid range: "));
        }
    }

    public static <C extends Comparable<?>> Range<C> all() {
        return (Range<C>) ALL;
    }

    public static <C extends Comparable<?>> Range<C> atLeast(C c2) {
        return new Range<>(Cut.belowValue(c2), Cut.aboveAll());
    }

    public static <C extends Comparable<?>> Range<C> atMost(C c2) {
        return new Range<>(Cut.belowAll(), Cut.aboveValue(c2));
    }

    public static <T> SortedSet<T> cast(Iterable<T> iterable) {
        return (SortedSet) iterable;
    }

    public static <C extends Comparable<?>> Range<C> closed(C c2, C c3) {
        return new Range<>(Cut.belowValue(c2), Cut.aboveValue(c3));
    }

    public static <C extends Comparable<?>> Range<C> closedOpen(C c2, C c3) {
        return new Range<>(Cut.belowValue(c2), Cut.belowValue(c3));
    }

    public static int compareOrThrow(Comparable comparable, Comparable comparable2) {
        return comparable.compareTo(comparable2);
    }

    public static <C extends Comparable<?>> Range<C> create(Cut<C> cut, Cut<C> cut2) {
        return new Range<>(cut, cut2);
    }

    public static <C extends Comparable<?>> Range<C> downTo(C c2, BoundType boundType) {
        int iOrdinal = boundType.ordinal();
        if (iOrdinal == 0) {
            return greaterThan(c2);
        }
        if (iOrdinal == 1) {
            return atLeast(c2);
        }
        throw new AssertionError();
    }

    public static <C extends Comparable<?>> Range<C> encloseAll(Iterable<C> iterable) {
        if (iterable == null) {
            throw new NullPointerException();
        }
        if (iterable instanceof SortedSet) {
            SortedSet sortedSet = (SortedSet) iterable;
            Comparator comparator = sortedSet.comparator();
            if (Ordering.natural().equals(comparator) || comparator == null) {
                return closed((Comparable) sortedSet.first(), (Comparable) sortedSet.last());
            }
        }
        Iterator<C> it = iterable.iterator();
        C next = it.next();
        Preconditions.checkNotNull(next);
        C c2 = next;
        Comparable comparable = c2;
        while (it.hasNext()) {
            C next2 = it.next();
            Preconditions.checkNotNull(next2);
            C c3 = next2;
            c2 = (Comparable) Ordering.natural().min(c2, c3);
            comparable = (Comparable) Ordering.natural().max(comparable, c3);
        }
        return closed(c2, comparable);
    }

    public static <C extends Comparable<?>> Range<C> greaterThan(C c2) {
        return new Range<>(Cut.aboveValue(c2), Cut.aboveAll());
    }

    public static <C extends Comparable<?>> Range<C> lessThan(C c2) {
        return new Range<>(Cut.belowAll(), Cut.belowValue(c2));
    }

    public static <C extends Comparable<?>> Function<Range<C>, Cut<C>> lowerBoundFn() {
        return LowerBoundFn.INSTANCE;
    }

    public static <C extends Comparable<?>> Range<C> open(C c2, C c3) {
        return new Range<>(Cut.aboveValue(c2), Cut.belowValue(c3));
    }

    public static <C extends Comparable<?>> Range<C> openClosed(C c2, C c3) {
        return new Range<>(Cut.aboveValue(c2), Cut.aboveValue(c3));
    }

    public static <C extends Comparable<?>> Range<C> range(C c2, BoundType boundType, C c3, BoundType boundType2) {
        if (boundType == null) {
            throw new NullPointerException();
        }
        if (boundType2 != null) {
            return new Range<>(boundType == BoundType.OPEN ? Cut.aboveValue(c2) : Cut.belowValue(c2), boundType2 == BoundType.OPEN ? Cut.belowValue(c3) : Cut.aboveValue(c3));
        }
        throw new NullPointerException();
    }

    public static <C extends Comparable<?>> Ordering<Range<C>> rangeLexOrdering() {
        return (Ordering<Range<C>>) RangeLexOrdering.INSTANCE;
    }

    public static <C extends Comparable<?>> Range<C> singleton(C c2) {
        return closed(c2, c2);
    }

    public static <C extends Comparable<?>> Range<C> upTo(C c2, BoundType boundType) {
        int iOrdinal = boundType.ordinal();
        if (iOrdinal == 0) {
            return lessThan(c2);
        }
        if (iOrdinal == 1) {
            return atMost(c2);
        }
        throw new AssertionError();
    }

    public static <C extends Comparable<?>> Function<Range<C>, Cut<C>> upperBoundFn() {
        return UpperBoundFn.INSTANCE;
    }

    public Range<C> canonical(DiscreteDomain<C> discreteDomain) {
        if (discreteDomain == null) {
            throw new NullPointerException();
        }
        Cut<C> cutCanonical = this.lowerBound.canonical(discreteDomain);
        Cut<C> cutCanonical2 = this.upperBound.canonical(discreteDomain);
        return (cutCanonical == this.lowerBound && cutCanonical2 == this.upperBound) ? this : new Range<>(cutCanonical, cutCanonical2);
    }

    public boolean contains(C c2) {
        if (c2 != null) {
            return this.lowerBound.isLessThan(c2) && !this.upperBound.isLessThan(c2);
        }
        throw new NullPointerException();
    }

    public boolean containsAll(Iterable<? extends C> iterable) {
        if (Iterables.isEmpty(iterable)) {
            return true;
        }
        if (iterable instanceof SortedSet) {
            SortedSet sortedSet = (SortedSet) iterable;
            Comparator comparator = sortedSet.comparator();
            if (Ordering.natural().equals(comparator) || comparator == null) {
                return contains((Comparable) sortedSet.first()) && contains((Comparable) sortedSet.last());
            }
        }
        Iterator<? extends C> it = iterable.iterator();
        while (it.hasNext()) {
            if (!contains(it.next())) {
                return false;
            }
        }
        return true;
    }

    public boolean encloses(Range<C> range) {
        return this.lowerBound.compareTo((Cut) range.lowerBound) <= 0 && this.upperBound.compareTo((Cut) range.upperBound) >= 0;
    }

    @Override // com.google.common.base.Predicate
    public boolean equals(Object obj) {
        if (!(obj instanceof Range)) {
            return false;
        }
        Range range = (Range) obj;
        return this.lowerBound.equals(range.lowerBound) && this.upperBound.equals(range.upperBound);
    }

    public Range<C> gap(Range<C> range) {
        if (this.lowerBound.compareTo((Cut) range.upperBound) < 0 && range.lowerBound.compareTo((Cut) this.upperBound) < 0) {
            String strValueOf = String.valueOf(this);
            String strValueOf2 = String.valueOf(range);
            throw new IllegalArgumentException(a.b(strValueOf2.length() + strValueOf.length() + 39, "Ranges have a nonempty intersection: ", strValueOf, ", ", strValueOf2));
        }
        boolean z = this.lowerBound.compareTo((Cut) range.lowerBound) < 0;
        Range<C> range2 = z ? this : range;
        if (!z) {
            range = this;
        }
        return new Range<>(range2.upperBound, range.lowerBound);
    }

    public boolean hasLowerBound() {
        return this.lowerBound != Cut.belowAll();
    }

    public boolean hasUpperBound() {
        return this.upperBound != Cut.aboveAll();
    }

    public int hashCode() {
        return this.upperBound.hashCode() + (this.lowerBound.hashCode() * 31);
    }

    public Range<C> intersection(Range<C> range) {
        int iCompareTo = this.lowerBound.compareTo((Cut) range.lowerBound);
        int iCompareTo2 = this.upperBound.compareTo((Cut) range.upperBound);
        if (iCompareTo >= 0 && iCompareTo2 <= 0) {
            return this;
        }
        if (iCompareTo > 0 || iCompareTo2 < 0) {
            return new Range<>(iCompareTo >= 0 ? this.lowerBound : range.lowerBound, iCompareTo2 <= 0 ? this.upperBound : range.upperBound);
        }
        return range;
    }

    public boolean isConnected(Range<C> range) {
        return this.lowerBound.compareTo((Cut) range.upperBound) <= 0 && range.lowerBound.compareTo((Cut) this.upperBound) <= 0;
    }

    public boolean isEmpty() {
        return this.lowerBound.equals(this.upperBound);
    }

    public BoundType lowerBoundType() {
        return this.lowerBound.typeAsLowerBound();
    }

    public C lowerEndpoint() {
        return (C) this.lowerBound.endpoint();
    }

    public Object readResolve() {
        return equals(ALL) ? ALL : this;
    }

    public Range<C> span(Range<C> range) {
        int iCompareTo = this.lowerBound.compareTo((Cut) range.lowerBound);
        int iCompareTo2 = this.upperBound.compareTo((Cut) range.upperBound);
        if (iCompareTo <= 0 && iCompareTo2 >= 0) {
            return this;
        }
        if (iCompareTo < 0 || iCompareTo2 > 0) {
            return new Range<>(iCompareTo <= 0 ? this.lowerBound : range.lowerBound, iCompareTo2 >= 0 ? this.upperBound : range.upperBound);
        }
        return range;
    }

    public String toString() {
        return toString(this.lowerBound, this.upperBound);
    }

    public BoundType upperBoundType() {
        return this.upperBound.typeAsUpperBound();
    }

    public C upperEndpoint() {
        return (C) this.upperBound.endpoint();
    }

    public static String toString(Cut<?> cut, Cut<?> cut2) {
        StringBuilder sb = new StringBuilder(16);
        cut.describeAsLowerBound(sb);
        sb.append("..");
        cut2.describeAsUpperBound(sb);
        return sb.toString();
    }

    @Override // com.google.common.base.Predicate
    @Deprecated
    public boolean apply(C c2) {
        return contains(c2);
    }
}
