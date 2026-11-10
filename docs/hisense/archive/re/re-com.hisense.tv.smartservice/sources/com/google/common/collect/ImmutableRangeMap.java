package com.google.common.collect;

import b.a.a.a.a;
import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.DoNotMock;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.io.Serializable;
import java.lang.Comparable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

/* JADX INFO: loaded from: classes.dex */
@Beta
@GwtIncompatible
public class ImmutableRangeMap<K extends Comparable<?>, V> implements RangeMap<K, V>, Serializable {
    public static final ImmutableRangeMap<Comparable<?>, Object> EMPTY = new ImmutableRangeMap<>(ImmutableList.of(), ImmutableList.of());
    public static final long serialVersionUID = 0;
    public final transient ImmutableList<Range<K>> ranges;
    public final transient ImmutableList<V> values;

    @DoNotMock
    public static final class Builder<K extends Comparable<?>, V> {
        public final List<Map.Entry<Range<K>, V>> entries = new ArrayList();

        public ImmutableRangeMap<K, V> build() {
            Collections.sort(this.entries, Range.rangeLexOrdering().onKeys());
            ImmutableList.Builder builder = new ImmutableList.Builder(this.entries.size());
            ImmutableList.Builder builder2 = new ImmutableList.Builder(this.entries.size());
            for (int i = 0; i < this.entries.size(); i++) {
                Range<K> key = this.entries.get(i).getKey();
                if (i > 0) {
                    Range<K> key2 = this.entries.get(i - 1).getKey();
                    if (key.isConnected(key2) && !key.intersection(key2).isEmpty()) {
                        String strValueOf = String.valueOf(key2);
                        String strValueOf2 = String.valueOf(key);
                        throw new IllegalArgumentException(a.b(strValueOf2.length() + strValueOf.length() + 47, "Overlapping ranges: range ", strValueOf, " overlaps with entry ", strValueOf2));
                    }
                }
                builder.add(key);
                builder2.add(this.entries.get(i).getValue());
            }
            return new ImmutableRangeMap<>(builder.build(), builder2.build());
        }

        @CanIgnoreReturnValue
        public Builder<K, V> combine(Builder<K, V> builder) {
            this.entries.addAll(builder.entries);
            return this;
        }

        @CanIgnoreReturnValue
        public Builder<K, V> put(Range<K> range, V v) {
            if (range == null) {
                throw new NullPointerException();
            }
            if (v == null) {
                throw new NullPointerException();
            }
            Preconditions.checkArgument(!range.isEmpty(), "Range must not be empty, but was %s", range);
            this.entries.add(new ImmutableEntry(range, v));
            return this;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @CanIgnoreReturnValue
        public Builder<K, V> putAll(RangeMap<K, ? extends V> rangeMap) {
            for (Map.Entry entry : rangeMap.asMapOfRanges().entrySet()) {
                put((Range) entry.getKey(), entry.getValue());
            }
            return this;
        }
    }

    private static class SerializedForm<K extends Comparable<?>, V> implements Serializable {
        public static final long serialVersionUID = 0;
        public final ImmutableMap<Range<K>, V> mapOfRanges;

        public SerializedForm(ImmutableMap<Range<K>, V> immutableMap) {
            this.mapOfRanges = immutableMap;
        }

        public Object createRangeMap() {
            Builder builder = new Builder();
            UnmodifiableIterator<Map.Entry<Range<K>, V>> it = this.mapOfRanges.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<Range<K>, V> next = it.next();
                builder.put(next.getKey(), next.getValue());
            }
            return builder.build();
        }

        public Object readResolve() {
            return this.mapOfRanges.isEmpty() ? ImmutableRangeMap.EMPTY : createRangeMap();
        }
    }

    public ImmutableRangeMap(ImmutableList<Range<K>> immutableList, ImmutableList<V> immutableList2) {
        this.ranges = immutableList;
        this.values = immutableList2;
    }

    public static <K extends Comparable<?>, V> Builder<K, V> builder() {
        return new Builder<>();
    }

    public static <K extends Comparable<?>, V> ImmutableRangeMap<K, V> copyOf(RangeMap<K, ? extends V> rangeMap) {
        if (rangeMap instanceof ImmutableRangeMap) {
            return (ImmutableRangeMap) rangeMap;
        }
        Map<Range<K>, ? extends V> mapAsMapOfRanges = rangeMap.asMapOfRanges();
        ImmutableList.Builder builder = new ImmutableList.Builder(mapAsMapOfRanges.size());
        ImmutableList.Builder builder2 = new ImmutableList.Builder(mapAsMapOfRanges.size());
        for (Map.Entry entry : mapAsMapOfRanges.entrySet()) {
            builder.add(entry.getKey());
            builder2.add(entry.getValue());
        }
        return new ImmutableRangeMap<>(builder.build(), builder2.build());
    }

    public static <K extends Comparable<?>, V> ImmutableRangeMap<K, V> of() {
        return (ImmutableRangeMap<K, V>) EMPTY;
    }

    @Override // com.google.common.collect.RangeMap
    @Deprecated
    public void clear() {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.common.collect.RangeMap
    public boolean equals(Object obj) {
        if (obj instanceof RangeMap) {
            return asMapOfRanges().equals(((RangeMap) obj).asMapOfRanges());
        }
        return false;
    }

    @Override // com.google.common.collect.RangeMap
    public V get(K k) {
        int iBinarySearch = SortedLists.binarySearch(this.ranges, (Function<? super E, Cut>) Range.lowerBoundFn(), Cut.belowValue(k), SortedLists.KeyPresentBehavior.ANY_PRESENT, SortedLists.KeyAbsentBehavior.NEXT_LOWER);
        if (iBinarySearch != -1 && this.ranges.get(iBinarySearch).contains(k)) {
            return this.values.get(iBinarySearch);
        }
        return null;
    }

    @Override // com.google.common.collect.RangeMap
    public Map.Entry<Range<K>, V> getEntry(K k) {
        int iBinarySearch = SortedLists.binarySearch(this.ranges, (Function<? super E, Cut>) Range.lowerBoundFn(), Cut.belowValue(k), SortedLists.KeyPresentBehavior.ANY_PRESENT, SortedLists.KeyAbsentBehavior.NEXT_LOWER);
        if (iBinarySearch == -1) {
            return null;
        }
        Range<K> range = this.ranges.get(iBinarySearch);
        if (range.contains(k)) {
            return new ImmutableEntry(range, this.values.get(iBinarySearch));
        }
        return null;
    }

    @Override // com.google.common.collect.RangeMap
    public int hashCode() {
        return asMapOfRanges().hashCode();
    }

    @Override // com.google.common.collect.RangeMap
    @Deprecated
    public void put(Range<K> range, V v) {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.common.collect.RangeMap
    @Deprecated
    public void putAll(RangeMap<K, V> rangeMap) {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.common.collect.RangeMap
    @Deprecated
    public void putCoalescing(Range<K> range, V v) {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.common.collect.RangeMap
    @Deprecated
    public void remove(Range<K> range) {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.common.collect.RangeMap
    public Range<K> span() {
        if (this.ranges.isEmpty()) {
            throw new NoSuchElementException();
        }
        Range<K> range = this.ranges.get(0);
        ImmutableList<Range<K>> immutableList = this.ranges;
        return new Range<>(range.lowerBound, immutableList.get(immutableList.size() - 1).upperBound);
    }

    @Override // com.google.common.collect.RangeMap
    public String toString() {
        return asMapOfRanges().toString();
    }

    public Object writeReplace() {
        return new SerializedForm(asMapOfRanges());
    }

    public static <K extends Comparable<?>, V> ImmutableRangeMap<K, V> of(Range<K> range, V v) {
        return new ImmutableRangeMap<>(ImmutableList.of(range), ImmutableList.of(v));
    }

    @Override // com.google.common.collect.RangeMap
    public ImmutableMap<Range<K>, V> asDescendingMapOfRanges() {
        return this.ranges.isEmpty() ? ImmutableMap.of() : new ImmutableSortedMap(new RegularImmutableSortedSet(this.ranges.reverse(), Range.rangeLexOrdering().reverse()), this.values.reverse());
    }

    @Override // com.google.common.collect.RangeMap
    public ImmutableMap<Range<K>, V> asMapOfRanges() {
        return this.ranges.isEmpty() ? ImmutableMap.of() : new ImmutableSortedMap(new RegularImmutableSortedSet(this.ranges, Range.rangeLexOrdering()), this.values);
    }

    @Override // com.google.common.collect.RangeMap
    public ImmutableRangeMap<K, V> subRangeMap(final Range<K> range) {
        if (range == null) {
            throw new NullPointerException();
        }
        if (range.isEmpty()) {
            return (ImmutableRangeMap<K, V>) EMPTY;
        }
        if (this.ranges.isEmpty() || range.encloses(span())) {
            return this;
        }
        final int iBinarySearch = SortedLists.binarySearch(this.ranges, (Function<? super E, Comparable>) Range.upperBoundFn(), range.lowerBound, SortedLists.KeyPresentBehavior.FIRST_AFTER, SortedLists.KeyAbsentBehavior.NEXT_HIGHER);
        int iBinarySearch2 = SortedLists.binarySearch(this.ranges, (Function<? super E, Comparable>) Range.lowerBoundFn(), range.upperBound, SortedLists.KeyPresentBehavior.ANY_PRESENT, SortedLists.KeyAbsentBehavior.NEXT_HIGHER);
        if (iBinarySearch >= iBinarySearch2) {
            return (ImmutableRangeMap<K, V>) EMPTY;
        }
        final int i = iBinarySearch2 - iBinarySearch;
        return (ImmutableRangeMap<K, V>) new ImmutableRangeMap<K, V>(this, new ImmutableList<Range<K>>() { // from class: com.google.common.collect.ImmutableRangeMap.1
            @Override // com.google.common.collect.ImmutableCollection
            public boolean isPartialView() {
                return true;
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
            public int size() {
                return i;
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.List
            public Range<K> get(int i2) {
                Preconditions.checkElementIndex(i2, i, FirebaseAnalytics.Param.INDEX);
                return (i2 == 0 || i2 == i + (-1)) ? ((Range) ImmutableRangeMap.this.ranges.get(i2 + iBinarySearch)).intersection(range) : (Range) ImmutableRangeMap.this.ranges.get(i2 + iBinarySearch);
            }
        }, this.values.subList(iBinarySearch, iBinarySearch2)) { // from class: com.google.common.collect.ImmutableRangeMap.2
            @Override // com.google.common.collect.ImmutableRangeMap, com.google.common.collect.RangeMap
            public /* bridge */ /* synthetic */ Map asDescendingMapOfRanges() {
                return super.asDescendingMapOfRanges();
            }

            @Override // com.google.common.collect.ImmutableRangeMap, com.google.common.collect.RangeMap
            public /* bridge */ /* synthetic */ Map asMapOfRanges() {
                return super.asMapOfRanges();
            }

            /* JADX WARN: Multi-variable type inference failed */
            /* JADX WARN: Type inference fix 'apply assigned field type' failed
            java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
            	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
            	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
            	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
             */
            @Override // com.google.common.collect.ImmutableRangeMap, com.google.common.collect.RangeMap
            public ImmutableRangeMap<K, V> subRangeMap(Range<K> range2) {
                return range.isConnected(range2) ? this.subRangeMap((Range) range2.intersection(range)) : (ImmutableRangeMap<K, V>) ImmutableRangeMap.EMPTY;
            }
        };
    }
}
