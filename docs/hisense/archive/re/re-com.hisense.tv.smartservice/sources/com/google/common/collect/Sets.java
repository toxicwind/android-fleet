package com.google.common.collect;

import b.a.a.a.a;
import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.math.IntMath;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.firebase.storage.StorageMetadata;
import java.io.Serializable;
import java.util.AbstractSet;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.NavigableSet;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

/* JADX INFO: loaded from: classes.dex */
@GwtCompatible(emulated = true)
public final class Sets {

    /* JADX INFO: Add missing generic type declarations: [E] */
    /* JADX INFO: renamed from: com.google.common.collect.Sets$5, reason: invalid class name */
    public class AnonymousClass5<E> extends AbstractSet<Set<E>> {
        public final /* synthetic */ ImmutableMap val$index;
        public final /* synthetic */ int val$size;

        /* JADX INFO: renamed from: com.google.common.collect.Sets$5$1, reason: invalid class name */
        public class AnonymousClass1 extends AbstractIterator<Set<E>> {
            public final BitSet bits;

            public AnonymousClass1() {
                this.bits = new BitSet(AnonymousClass5.this.val$index.size());
            }

            @Override // com.google.common.collect.AbstractIterator
            public Set<E> computeNext() {
                if (this.bits.isEmpty()) {
                    this.bits.set(0, AnonymousClass5.this.val$size);
                } else {
                    int iNextSetBit = this.bits.nextSetBit(0);
                    int iNextClearBit = this.bits.nextClearBit(iNextSetBit);
                    if (iNextClearBit == AnonymousClass5.this.val$index.size()) {
                        return endOfData();
                    }
                    int i = (iNextClearBit - iNextSetBit) - 1;
                    this.bits.set(0, i);
                    this.bits.clear(i, iNextClearBit);
                    this.bits.set(iNextClearBit);
                }
                final BitSet bitSet = (BitSet) this.bits.clone();
                return new AbstractSet<E>() { // from class: com.google.common.collect.Sets.5.1.1
                    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
                    public boolean contains(Object obj) {
                        Integer num = (Integer) AnonymousClass5.this.val$index.get(obj);
                        return num != null && bitSet.get(num.intValue());
                    }

                    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
                    public Iterator<E> iterator() {
                        return new AbstractIterator<E>() { // from class: com.google.common.collect.Sets.5.1.1.1
                            public int i = -1;

                            @Override // com.google.common.collect.AbstractIterator
                            public E computeNext() {
                                this.i = bitSet.nextSetBit(this.i + 1);
                                return this.i == -1 ? endOfData() : AnonymousClass5.this.val$index.keySet().asList().get(this.i);
                            }
                        };
                    }

                    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
                    public int size() {
                        return AnonymousClass5.this.val$size;
                    }
                };
            }
        }

        public AnonymousClass5(int i, ImmutableMap immutableMap) {
            this.val$size = i;
            this.val$index = immutableMap;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object obj) {
            if (!(obj instanceof Set)) {
                return false;
            }
            Set set = (Set) obj;
            return set.size() == this.val$size && this.val$index.keySet().containsAll(set);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<Set<E>> iterator() {
            return new AnonymousClass1();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return IntMath.binomial(this.val$index.size(), this.val$size);
        }

        @Override // java.util.AbstractCollection
        public String toString() {
            String strValueOf = String.valueOf(this.val$index.keySet());
            int i = this.val$size;
            StringBuilder sb = new StringBuilder(strValueOf.length() + 32);
            sb.append("Sets.combinations(");
            sb.append(strValueOf);
            sb.append(", ");
            sb.append(i);
            sb.append(")");
            return sb.toString();
        }
    }

    private static final class CartesianSet<E> extends ForwardingCollection<List<E>> implements Set<List<E>> {
        public final transient ImmutableList<ImmutableSet<E>> axes;
        public final transient CartesianList<E> delegate;

        public CartesianSet(ImmutableList<ImmutableSet<E>> immutableList, CartesianList<E> cartesianList) {
            this.axes = immutableList;
            this.delegate = cartesianList;
        }

        public static <E> Set<List<E>> create(List<? extends Set<? extends E>> list) {
            ImmutableList.Builder builder = new ImmutableList.Builder(list.size());
            Iterator<? extends Set<? extends E>> it = list.iterator();
            while (it.hasNext()) {
                ImmutableSet immutableSetCopyOf = ImmutableSet.copyOf((Collection) it.next());
                if (immutableSetCopyOf.isEmpty()) {
                    return ImmutableSet.of();
                }
                builder.add(immutableSetCopyOf);
            }
            final ImmutableList<E> immutableListBuild = builder.build();
            return new CartesianSet(immutableListBuild, new CartesianList(new ImmutableList<List<E>>() { // from class: com.google.common.collect.Sets.CartesianSet.1
                @Override // com.google.common.collect.ImmutableCollection
                public boolean isPartialView() {
                    return true;
                }

                @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
                public int size() {
                    return immutableListBuild.size();
                }

                @Override // java.util.List
                public List<E> get(int i) {
                    return ((ImmutableSet) immutableListBuild.get(i)).asList();
                }
            }));
        }

        @Override // com.google.common.collect.ForwardingCollection, java.util.Collection, java.util.Set
        public boolean contains(Object obj) {
            if (!(obj instanceof List)) {
                return false;
            }
            List list = (List) obj;
            if (list.size() != this.axes.size()) {
                return false;
            }
            Iterator<E> it = list.iterator();
            int i = 0;
            while (it.hasNext()) {
                if (!this.axes.get(i).contains(it.next())) {
                    return false;
                }
                i++;
            }
            return true;
        }

        @Override // java.util.Collection, java.util.Set
        public boolean equals(Object obj) {
            return obj instanceof CartesianSet ? this.axes.equals(((CartesianSet) obj).axes) : super.equals(obj);
        }

        @Override // java.util.Collection, java.util.Set
        public int hashCode() {
            int i = 1;
            int size = size() - 1;
            for (int i2 = 0; i2 < this.axes.size(); i2++) {
                size = ~(~(size * 31));
            }
            UnmodifiableIterator<ImmutableSet<E>> it = this.axes.iterator();
            while (it.hasNext()) {
                ImmutableSet<E> next = it.next();
                i = ~(~((next.hashCode() * (size() / next.size())) + (i * 31)));
            }
            return ~(~(i + size));
        }

        @Override // com.google.common.collect.ForwardingCollection, com.google.common.collect.ForwardingObject
        public Collection<List<E>> delegate() {
            return this.delegate;
        }
    }

    @GwtIncompatible
    static class DescendingSet<E> extends ForwardingNavigableSet<E> {
        public final NavigableSet<E> forward;

        public DescendingSet(NavigableSet<E> navigableSet) {
            this.forward = navigableSet;
        }

        public static <T> Ordering<T> reverse(Comparator<T> comparator) {
            return Ordering.from(comparator).reverse();
        }

        @Override // com.google.common.collect.ForwardingNavigableSet, java.util.NavigableSet
        public E ceiling(E e2) {
            return this.forward.floor(e2);
        }

        @Override // com.google.common.collect.ForwardingSortedSet, java.util.SortedSet
        public Comparator<? super E> comparator() {
            Comparator<? super E> comparator = this.forward.comparator();
            return comparator == null ? Ordering.natural().reverse() : reverse(comparator);
        }

        @Override // com.google.common.collect.ForwardingNavigableSet, java.util.NavigableSet
        public Iterator<E> descendingIterator() {
            return this.forward.iterator();
        }

        @Override // com.google.common.collect.ForwardingNavigableSet, java.util.NavigableSet
        public NavigableSet<E> descendingSet() {
            return this.forward;
        }

        @Override // com.google.common.collect.ForwardingSortedSet, java.util.SortedSet
        public E first() {
            return this.forward.last();
        }

        @Override // com.google.common.collect.ForwardingNavigableSet, java.util.NavigableSet
        public E floor(E e2) {
            return this.forward.ceiling(e2);
        }

        @Override // com.google.common.collect.ForwardingNavigableSet, java.util.NavigableSet
        public NavigableSet<E> headSet(E e2, boolean z) {
            return this.forward.tailSet(e2, z).descendingSet();
        }

        @Override // com.google.common.collect.ForwardingNavigableSet, java.util.NavigableSet
        public E higher(E e2) {
            return this.forward.lower(e2);
        }

        @Override // com.google.common.collect.ForwardingCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<E> iterator() {
            return this.forward.descendingIterator();
        }

        @Override // com.google.common.collect.ForwardingSortedSet, java.util.SortedSet
        public E last() {
            return this.forward.first();
        }

        @Override // com.google.common.collect.ForwardingNavigableSet, java.util.NavigableSet
        public E lower(E e2) {
            return this.forward.higher(e2);
        }

        @Override // com.google.common.collect.ForwardingNavigableSet, java.util.NavigableSet
        public E pollFirst() {
            return this.forward.pollLast();
        }

        @Override // com.google.common.collect.ForwardingNavigableSet, java.util.NavigableSet
        public E pollLast() {
            return this.forward.pollFirst();
        }

        @Override // com.google.common.collect.ForwardingNavigableSet, java.util.NavigableSet
        public NavigableSet<E> subSet(E e2, boolean z, E e3, boolean z2) {
            return this.forward.subSet(e3, z2, e2, z).descendingSet();
        }

        @Override // com.google.common.collect.ForwardingNavigableSet, java.util.NavigableSet
        public NavigableSet<E> tailSet(E e2, boolean z) {
            return this.forward.headSet(e2, z).descendingSet();
        }

        @Override // com.google.common.collect.ForwardingCollection, java.util.Collection, java.util.Set
        public Object[] toArray() {
            return standardToArray();
        }

        @Override // com.google.common.collect.ForwardingObject
        public String toString() {
            return standardToString();
        }

        @Override // com.google.common.collect.ForwardingSortedSet, java.util.SortedSet
        public SortedSet<E> headSet(E e2) {
            return standardHeadSet(e2);
        }

        @Override // com.google.common.collect.ForwardingSortedSet, java.util.SortedSet
        public SortedSet<E> subSet(E e2, E e3) {
            return standardSubSet(e2, e3);
        }

        @Override // com.google.common.collect.ForwardingSortedSet, java.util.SortedSet
        public SortedSet<E> tailSet(E e2) {
            return standardTailSet(e2);
        }

        @Override // com.google.common.collect.ForwardingCollection, java.util.Collection, java.util.Set
        public <T> T[] toArray(T[] tArr) {
            return (T[]) standardToArray(tArr);
        }

        @Override // com.google.common.collect.ForwardingNavigableSet, com.google.common.collect.ForwardingSortedSet, com.google.common.collect.ForwardingSet, com.google.common.collect.ForwardingCollection, com.google.common.collect.ForwardingObject
        public NavigableSet<E> delegate() {
            return this.forward;
        }
    }

    @GwtIncompatible
    private static class FilteredNavigableSet<E> extends FilteredSortedSet<E> implements NavigableSet<E> {
        public FilteredNavigableSet(NavigableSet<E> navigableSet, Predicate<? super E> predicate) {
            super(navigableSet, predicate);
        }

        @Override // java.util.NavigableSet
        public E ceiling(E e2) {
            return (E) Iterables.find(unfiltered().tailSet(e2, true), this.predicate, null);
        }

        @Override // java.util.NavigableSet
        public Iterator<E> descendingIterator() {
            return Iterators.filter(unfiltered().descendingIterator(), this.predicate);
        }

        @Override // java.util.NavigableSet
        public NavigableSet<E> descendingSet() {
            return Sets.filter((NavigableSet) unfiltered().descendingSet(), (Predicate) this.predicate);
        }

        @Override // java.util.NavigableSet
        public E floor(E e2) {
            return (E) Iterators.find(unfiltered().headSet(e2, true).descendingIterator(), this.predicate, null);
        }

        @Override // java.util.NavigableSet
        public NavigableSet<E> headSet(E e2, boolean z) {
            return Sets.filter((NavigableSet) unfiltered().headSet(e2, z), (Predicate) this.predicate);
        }

        @Override // java.util.NavigableSet
        public E higher(E e2) {
            return (E) Iterables.find(unfiltered().tailSet(e2, false), this.predicate, null);
        }

        @Override // com.google.common.collect.Sets.FilteredSortedSet, java.util.SortedSet
        public E last() {
            return (E) Iterators.find(unfiltered().descendingIterator(), this.predicate);
        }

        @Override // java.util.NavigableSet
        public E lower(E e2) {
            return (E) Iterators.find(unfiltered().headSet(e2, false).descendingIterator(), this.predicate, null);
        }

        @Override // java.util.NavigableSet
        public E pollFirst() {
            return (E) Iterables.removeFirstMatching(unfiltered(), this.predicate);
        }

        @Override // java.util.NavigableSet
        public E pollLast() {
            return (E) Iterables.removeFirstMatching(unfiltered().descendingSet(), this.predicate);
        }

        @Override // java.util.NavigableSet
        public NavigableSet<E> subSet(E e2, boolean z, E e3, boolean z2) {
            return Sets.filter((NavigableSet) unfiltered().subSet(e2, z, e3, z2), (Predicate) this.predicate);
        }

        @Override // java.util.NavigableSet
        public NavigableSet<E> tailSet(E e2, boolean z) {
            return Sets.filter((NavigableSet) unfiltered().tailSet(e2, z), (Predicate) this.predicate);
        }

        public NavigableSet<E> unfiltered() {
            return (NavigableSet) this.unfiltered;
        }
    }

    private static class FilteredSet<E> extends Collections2.FilteredCollection<E> implements Set<E> {
        public FilteredSet(Set<E> set, Predicate<? super E> predicate) {
            super(set, predicate);
        }

        @Override // java.util.Collection, java.util.Set
        public boolean equals(Object obj) {
            return Sets.equalsImpl(this, obj);
        }

        @Override // java.util.Collection, java.util.Set
        public int hashCode() {
            return Sets.hashCodeImpl(this);
        }
    }

    private static class FilteredSortedSet<E> extends FilteredSet<E> implements SortedSet<E> {
        public FilteredSortedSet(SortedSet<E> sortedSet, Predicate<? super E> predicate) {
            super(sortedSet, predicate);
        }

        @Override // java.util.SortedSet
        public Comparator<? super E> comparator() {
            return ((SortedSet) this.unfiltered).comparator();
        }

        @Override // java.util.SortedSet
        public E first() {
            return (E) Iterators.find(this.unfiltered.iterator(), this.predicate);
        }

        @Override // java.util.SortedSet
        public SortedSet<E> headSet(E e2) {
            return new FilteredSortedSet(((SortedSet) this.unfiltered).headSet(e2), this.predicate);
        }

        public E last() {
            SortedSet sortedSetHeadSet = (SortedSet) this.unfiltered;
            while (true) {
                E e2 = (Object) sortedSetHeadSet.last();
                if (this.predicate.apply(e2)) {
                    return e2;
                }
                sortedSetHeadSet = sortedSetHeadSet.headSet(e2);
            }
        }

        @Override // java.util.SortedSet
        public SortedSet<E> subSet(E e2, E e3) {
            return new FilteredSortedSet(((SortedSet) this.unfiltered).subSet(e2, e3), this.predicate);
        }

        @Override // java.util.SortedSet
        public SortedSet<E> tailSet(E e2) {
            return new FilteredSortedSet(((SortedSet) this.unfiltered).tailSet(e2), this.predicate);
        }
    }

    static abstract class ImprovedAbstractSet<E> extends AbstractSet<E> {
        @Override // java.util.AbstractSet, java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean removeAll(Collection<?> collection) {
            return Sets.removeAllImpl(this, collection);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean retainAll(Collection<?> collection) {
            if (collection != null) {
                return super.retainAll(collection);
            }
            throw new NullPointerException();
        }
    }

    private static final class PowerSet<E> extends AbstractSet<Set<E>> {
        public final ImmutableMap<E, Integer> inputSet;

        public PowerSet(Set<E> set) {
            Preconditions.checkArgument(set.size() <= 30, "Too many elements to create power set: %s > 30", set.size());
            this.inputSet = Maps.indexMap(set);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object obj) {
            if (!(obj instanceof Set)) {
                return false;
            }
            return this.inputSet.keySet().containsAll((Set) obj);
        }

        @Override // java.util.AbstractSet, java.util.Collection, java.util.Set
        public boolean equals(Object obj) {
            return obj instanceof PowerSet ? this.inputSet.keySet().equals(((PowerSet) obj).inputSet.keySet()) : super.equals(obj);
        }

        @Override // java.util.AbstractSet, java.util.Collection, java.util.Set
        public int hashCode() {
            return this.inputSet.keySet().hashCode() << (this.inputSet.size() - 1);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean isEmpty() {
            return false;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<Set<E>> iterator() {
            return new AbstractIndexedListIterator<Set<E>>(size()) { // from class: com.google.common.collect.Sets.PowerSet.1
                @Override // com.google.common.collect.AbstractIndexedListIterator
                public Set<E> get(int i) {
                    return new SubSet(PowerSet.this.inputSet, i);
                }
            };
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return 1 << this.inputSet.size();
        }

        @Override // java.util.AbstractCollection
        public String toString() {
            String strValueOf = String.valueOf(this.inputSet);
            return a.a(strValueOf.length() + 10, "powerSet(", strValueOf, ")");
        }
    }

    public static abstract class SetView<E> extends AbstractSet<E> {
        public SetView() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        @CanIgnoreReturnValue
        @Deprecated
        public final boolean add(E e2) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        @CanIgnoreReturnValue
        @Deprecated
        public final boolean addAll(Collection<? extends E> collection) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        @Deprecated
        public final void clear() {
            throw new UnsupportedOperationException();
        }

        @CanIgnoreReturnValue
        public <S extends Set<E>> S copyInto(S s) {
            s.addAll(this);
            return s;
        }

        public ImmutableSet<E> immutableCopy() {
            return ImmutableSet.copyOf((Collection) this);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public abstract UnmodifiableIterator<E> iterator();

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        @CanIgnoreReturnValue
        @Deprecated
        public final boolean remove(Object obj) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.AbstractSet, java.util.AbstractCollection, java.util.Collection, java.util.Set
        @CanIgnoreReturnValue
        @Deprecated
        public final boolean removeAll(Collection<?> collection) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        @CanIgnoreReturnValue
        @Deprecated
        public final boolean retainAll(Collection<?> collection) {
            throw new UnsupportedOperationException();
        }

        public /* synthetic */ SetView(AnonymousClass1 anonymousClass1) {
        }
    }

    private static final class SubSet<E> extends AbstractSet<E> {
        public final ImmutableMap<E, Integer> inputSet;
        public final int mask;

        public SubSet(ImmutableMap<E, Integer> immutableMap, int i) {
            this.inputSet = immutableMap;
            this.mask = i;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object obj) {
            Integer num = this.inputSet.get(obj);
            if (num != null) {
                if (((1 << num.intValue()) & this.mask) != 0) {
                    return true;
                }
            }
            return false;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<E> iterator() {
            return new UnmodifiableIterator<E>() { // from class: com.google.common.collect.Sets.SubSet.1
                public final ImmutableList<E> elements;
                public int remainingSetBits;

                {
                    this.elements = SubSet.this.inputSet.keySet().asList();
                    this.remainingSetBits = SubSet.this.mask;
                }

                @Override // java.util.Iterator
                public boolean hasNext() {
                    return this.remainingSetBits != 0;
                }

                @Override // java.util.Iterator
                public E next() {
                    int iNumberOfTrailingZeros = Integer.numberOfTrailingZeros(this.remainingSetBits);
                    if (iNumberOfTrailingZeros == 32) {
                        throw new NoSuchElementException();
                    }
                    this.remainingSetBits &= ~(1 << iNumberOfTrailingZeros);
                    return this.elements.get(iNumberOfTrailingZeros);
                }
            };
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return Integer.bitCount(this.mask);
        }
    }

    public static <B> Set<List<B>> cartesianProduct(List<? extends Set<? extends B>> list) {
        return CartesianSet.create(list);
    }

    @Beta
    public static <E> Set<Set<E>> combinations(Set<E> set, int i) {
        ImmutableMap immutableMapIndexMap = Maps.indexMap(set);
        CollectPreconditions.checkNonnegative(i, StorageMetadata.SIZE_KEY);
        Preconditions.checkArgument(i <= immutableMapIndexMap.size(), "size (%s) must be <= set.size() (%s)", i, immutableMapIndexMap.size());
        if (i == 0) {
            return ImmutableSet.of(ImmutableSet.of());
        }
        return i == immutableMapIndexMap.size() ? ImmutableSet.of(immutableMapIndexMap.keySet()) : new AnonymousClass5(i, immutableMapIndexMap);
    }

    public static <E extends Enum<E>> EnumSet<E> complementOf(Collection<E> collection) {
        if (collection instanceof EnumSet) {
            return EnumSet.complementOf((EnumSet) collection);
        }
        Preconditions.checkArgument(!collection.isEmpty(), "collection is empty; use the other version of this method");
        return makeComplementByHand(collection, collection.iterator().next().getDeclaringClass());
    }

    public static <E> SetView<E> difference(final Set<E> set, final Set<?> set2) {
        Preconditions.checkNotNull(set, "set1");
        Preconditions.checkNotNull(set2, "set2");
        return new SetView<E>() { // from class: com.google.common.collect.Sets.3
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(null);
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
            public boolean contains(Object obj) {
                return set.contains(obj) && !set2.contains(obj);
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
            public boolean isEmpty() {
                return set2.containsAll(set);
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
            public int size() {
                Iterator<E> it = set.iterator();
                int i = 0;
                while (it.hasNext()) {
                    if (!set2.contains(it.next())) {
                        i++;
                    }
                }
                return i;
            }

            @Override // com.google.common.collect.Sets.SetView, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
            public UnmodifiableIterator<E> iterator() {
                return new AbstractIterator<E>() { // from class: com.google.common.collect.Sets.3.1
                    public final Iterator<E> itr;

                    {
                        this.itr = set.iterator();
                    }

                    @Override // com.google.common.collect.AbstractIterator
                    public E computeNext() {
                        while (this.itr.hasNext()) {
                            E next = this.itr.next();
                            if (!set2.contains(next)) {
                                return next;
                            }
                        }
                        return endOfData();
                    }
                };
            }
        };
    }

    public static boolean equalsImpl(Set<?> set, Object obj) {
        if (set == obj) {
            return true;
        }
        if (obj instanceof Set) {
            Set set2 = (Set) obj;
            try {
                return set.size() == set2.size() && set.containsAll(set2);
            } catch (ClassCastException | NullPointerException unused) {
            }
        }
        return false;
    }

    public static <E> Set<E> filter(Set<E> set, Predicate<? super E> predicate) {
        if (set instanceof SortedSet) {
            return filter((SortedSet) set, (Predicate) predicate);
        }
        if (set instanceof FilteredSet) {
            FilteredSet filteredSet = (FilteredSet) set;
            return new FilteredSet((Set) filteredSet.unfiltered, Predicates.and(filteredSet.predicate, predicate));
        }
        if (set == null) {
            throw new NullPointerException();
        }
        if (predicate != null) {
            return new FilteredSet(set, predicate);
        }
        throw new NullPointerException();
    }

    public static int hashCodeImpl(Set<?> set) {
        Iterator<?> it = set.iterator();
        int i = 0;
        while (it.hasNext()) {
            Object next = it.next();
            i = ~(~(i + (next != null ? next.hashCode() : 0)));
        }
        return i;
    }

    @GwtCompatible(serializable = true)
    public static <E extends Enum<E>> ImmutableSet<E> immutableEnumSet(E e2, E... eArr) {
        return ImmutableEnumSet.asImmutable(EnumSet.of((Enum) e2, (Enum[]) eArr));
    }

    public static <E> SetView<E> intersection(final Set<E> set, final Set<?> set2) {
        Preconditions.checkNotNull(set, "set1");
        Preconditions.checkNotNull(set2, "set2");
        return new SetView<E>() { // from class: com.google.common.collect.Sets.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(null);
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
            public boolean contains(Object obj) {
                return set.contains(obj) && set2.contains(obj);
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
            public boolean containsAll(Collection<?> collection) {
                return set.containsAll(collection) && set2.containsAll(collection);
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
            public boolean isEmpty() {
                return Collections.disjoint(set2, set);
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
            public int size() {
                Iterator<E> it = set.iterator();
                int i = 0;
                while (it.hasNext()) {
                    if (set2.contains(it.next())) {
                        i++;
                    }
                }
                return i;
            }

            @Override // com.google.common.collect.Sets.SetView, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
            public UnmodifiableIterator<E> iterator() {
                return new AbstractIterator<E>() { // from class: com.google.common.collect.Sets.2.1
                    public final Iterator<E> itr;

                    {
                        this.itr = set.iterator();
                    }

                    @Override // com.google.common.collect.AbstractIterator
                    public E computeNext() {
                        while (this.itr.hasNext()) {
                            E next = this.itr.next();
                            if (set2.contains(next)) {
                                return next;
                            }
                        }
                        return endOfData();
                    }
                };
            }
        };
    }

    public static <E extends Enum<E>> EnumSet<E> makeComplementByHand(Collection<E> collection, Class<E> cls) {
        EnumSet<E> enumSetAllOf = EnumSet.allOf(cls);
        enumSetAllOf.removeAll(collection);
        return enumSetAllOf;
    }

    public static <E> Set<E> newConcurrentHashSet() {
        return Collections.newSetFromMap(new ConcurrentHashMap());
    }

    @GwtIncompatible
    public static <E> CopyOnWriteArraySet<E> newCopyOnWriteArraySet() {
        return new CopyOnWriteArraySet<>();
    }

    public static <E extends Enum<E>> EnumSet<E> newEnumSet(Iterable<E> iterable, Class<E> cls) {
        EnumSet<E> enumSetNoneOf = EnumSet.noneOf(cls);
        Iterables.addAll(enumSetNoneOf, iterable);
        return enumSetNoneOf;
    }

    public static <E> HashSet<E> newHashSet() {
        return new HashSet<>();
    }

    public static <E> HashSet<E> newHashSetWithExpectedSize(int i) {
        return new HashSet<>(Maps.capacity(i));
    }

    public static <E> Set<E> newIdentityHashSet() {
        return Collections.newSetFromMap(new IdentityHashMap());
    }

    public static <E> LinkedHashSet<E> newLinkedHashSet() {
        return new LinkedHashSet<>();
    }

    public static <E> LinkedHashSet<E> newLinkedHashSetWithExpectedSize(int i) {
        return new LinkedHashSet<>(Maps.capacity(i));
    }

    @Deprecated
    public static <E> Set<E> newSetFromMap(Map<E, Boolean> map) {
        return Collections.newSetFromMap(map);
    }

    public static <E extends Comparable> TreeSet<E> newTreeSet() {
        return new TreeSet<>();
    }

    @GwtCompatible(serializable = false)
    public static <E> Set<Set<E>> powerSet(Set<E> set) {
        return new PowerSet(set);
    }

    public static boolean removeAllImpl(Set<?> set, Iterator<?> it) {
        boolean zRemove = false;
        while (it.hasNext()) {
            zRemove |= set.remove(it.next());
        }
        return zRemove;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Beta
    @GwtIncompatible
    public static <K extends Comparable<? super K>> NavigableSet<K> subSet(NavigableSet<K> navigableSet, Range<K> range) {
        if (navigableSet.comparator() != null && navigableSet.comparator() != Ordering.natural() && range.hasLowerBound() && range.hasUpperBound()) {
            Preconditions.checkArgument(navigableSet.comparator().compare(range.lowerEndpoint(), range.upperEndpoint()) <= 0, "set is using a custom comparator which is inconsistent with the natural ordering.");
        }
        if (range.hasLowerBound() && range.hasUpperBound()) {
            return navigableSet.subSet(range.lowerEndpoint(), range.lowerBoundType() == BoundType.CLOSED, range.upperEndpoint(), range.upperBoundType() == BoundType.CLOSED);
        }
        if (range.hasLowerBound()) {
            return navigableSet.tailSet(range.lowerEndpoint(), range.lowerBoundType() == BoundType.CLOSED);
        }
        if (range.hasUpperBound()) {
            return navigableSet.headSet(range.upperEndpoint(), range.upperBoundType() == BoundType.CLOSED);
        }
        return navigableSet;
    }

    public static <E> SetView<E> symmetricDifference(final Set<? extends E> set, final Set<? extends E> set2) {
        Preconditions.checkNotNull(set, "set1");
        Preconditions.checkNotNull(set2, "set2");
        return new SetView<E>() { // from class: com.google.common.collect.Sets.4
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(null);
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
            public boolean contains(Object obj) {
                return set2.contains(obj) ^ set.contains(obj);
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
            public boolean isEmpty() {
                return set.equals(set2);
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
            public int size() {
                Iterator<E> it = set.iterator();
                int i = 0;
                while (it.hasNext()) {
                    if (!set2.contains(it.next())) {
                        i++;
                    }
                }
                Iterator<E> it2 = set2.iterator();
                while (it2.hasNext()) {
                    if (!set.contains(it2.next())) {
                        i++;
                    }
                }
                return i;
            }

            @Override // com.google.common.collect.Sets.SetView, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
            public UnmodifiableIterator<E> iterator() {
                final Iterator<E> it = set.iterator();
                final Iterator<E> it2 = set2.iterator();
                return new AbstractIterator<E>() { // from class: com.google.common.collect.Sets.4.1
                    @Override // com.google.common.collect.AbstractIterator
                    public E computeNext() {
                        while (it.hasNext()) {
                            E e2 = (E) it.next();
                            if (!set2.contains(e2)) {
                                return e2;
                            }
                        }
                        while (it2.hasNext()) {
                            E e3 = (E) it2.next();
                            if (!set.contains(e3)) {
                                return e3;
                            }
                        }
                        return endOfData();
                    }
                };
            }
        };
    }

    @GwtIncompatible
    public static <E> NavigableSet<E> synchronizedNavigableSet(NavigableSet<E> navigableSet) {
        return Synchronized.navigableSet(navigableSet);
    }

    public static <E> SetView<E> union(final Set<? extends E> set, final Set<? extends E> set2) {
        Preconditions.checkNotNull(set, "set1");
        Preconditions.checkNotNull(set2, "set2");
        return new SetView<E>() { // from class: com.google.common.collect.Sets.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(null);
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
            public boolean contains(Object obj) {
                return set.contains(obj) || set2.contains(obj);
            }

            @Override // com.google.common.collect.Sets.SetView
            public <S extends Set<E>> S copyInto(S s) {
                s.addAll(set);
                s.addAll(set2);
                return s;
            }

            @Override // com.google.common.collect.Sets.SetView
            public ImmutableSet<E> immutableCopy() {
                return new ImmutableSet.Builder().addAll((Iterable) set).addAll((Iterable) set2).build();
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
            public boolean isEmpty() {
                return set.isEmpty() && set2.isEmpty();
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
            public int size() {
                int size = set.size();
                Iterator<E> it = set2.iterator();
                while (it.hasNext()) {
                    if (!set.contains(it.next())) {
                        size++;
                    }
                }
                return size;
            }

            @Override // com.google.common.collect.Sets.SetView, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
            public UnmodifiableIterator<E> iterator() {
                return new AbstractIterator<E>() { // from class: com.google.common.collect.Sets.1.1
                    public final Iterator<? extends E> itr1;
                    public final Iterator<? extends E> itr2;

                    {
                        this.itr1 = set.iterator();
                        this.itr2 = set2.iterator();
                    }

                    @Override // com.google.common.collect.AbstractIterator
                    public E computeNext() {
                        if (this.itr1.hasNext()) {
                            return this.itr1.next();
                        }
                        while (this.itr2.hasNext()) {
                            E next = this.itr2.next();
                            if (!set.contains(next)) {
                                return next;
                            }
                        }
                        return endOfData();
                    }
                };
            }
        };
    }

    public static <E> NavigableSet<E> unmodifiableNavigableSet(NavigableSet<E> navigableSet) {
        return ((navigableSet instanceof ImmutableCollection) || (navigableSet instanceof UnmodifiableNavigableSet)) ? navigableSet : new UnmodifiableNavigableSet(navigableSet);
    }

    @SafeVarargs
    public static <B> Set<List<B>> cartesianProduct(Set<? extends B>... setArr) {
        return CartesianSet.create(Arrays.asList(setArr));
    }

    @GwtCompatible(serializable = true)
    public static <E extends Enum<E>> ImmutableSet<E> immutableEnumSet(Iterable<E> iterable) {
        if (iterable instanceof ImmutableEnumSet) {
            return (ImmutableEnumSet) iterable;
        }
        if (iterable instanceof Collection) {
            Collection collection = (Collection) iterable;
            return collection.isEmpty() ? ImmutableSet.of() : ImmutableEnumSet.asImmutable(EnumSet.copyOf(collection));
        }
        Iterator<E> it = iterable.iterator();
        if (!it.hasNext()) {
            return ImmutableSet.of();
        }
        EnumSet enumSetOf = EnumSet.of((Enum) it.next());
        Iterators.addAll(enumSetOf, it);
        return ImmutableEnumSet.asImmutable(enumSetOf);
    }

    public static <E> Set<E> newConcurrentHashSet(Iterable<? extends E> iterable) {
        Set<E> setNewConcurrentHashSet = newConcurrentHashSet();
        Iterables.addAll(setNewConcurrentHashSet, iterable);
        return setNewConcurrentHashSet;
    }

    @GwtIncompatible
    public static <E> CopyOnWriteArraySet<E> newCopyOnWriteArraySet(Iterable<? extends E> iterable) {
        return new CopyOnWriteArraySet<>(iterable instanceof Collection ? (Collection) iterable : Lists.newArrayList(iterable));
    }

    public static <E> HashSet<E> newHashSet(Iterator<? extends E> it) {
        HashSet<E> hashSet = new HashSet<>();
        Iterators.addAll(hashSet, it);
        return hashSet;
    }

    public static <E> LinkedHashSet<E> newLinkedHashSet(Iterable<? extends E> iterable) {
        if (iterable instanceof Collection) {
            return new LinkedHashSet<>((Collection) iterable);
        }
        LinkedHashSet<E> linkedHashSet = new LinkedHashSet<>();
        Iterables.addAll(linkedHashSet, iterable);
        return linkedHashSet;
    }

    public static <E extends Comparable> TreeSet<E> newTreeSet(Iterable<? extends E> iterable) {
        TreeSet<E> treeSet = new TreeSet<>();
        Iterables.addAll(treeSet, iterable);
        return treeSet;
    }

    static final class UnmodifiableNavigableSet<E> extends ForwardingSortedSet<E> implements NavigableSet<E>, Serializable {
        public static final long serialVersionUID = 0;
        public final NavigableSet<E> delegate;
        public transient UnmodifiableNavigableSet<E> descendingSet;
        public final SortedSet<E> unmodifiableDelegate;

        public UnmodifiableNavigableSet(NavigableSet<E> navigableSet) {
            if (navigableSet == null) {
                throw new NullPointerException();
            }
            this.delegate = navigableSet;
            this.unmodifiableDelegate = Collections.unmodifiableSortedSet(navigableSet);
        }

        @Override // java.util.NavigableSet
        public E ceiling(E e2) {
            return this.delegate.ceiling(e2);
        }

        @Override // java.util.NavigableSet
        public Iterator<E> descendingIterator() {
            return Iterators.unmodifiableIterator(this.delegate.descendingIterator());
        }

        @Override // java.util.NavigableSet
        public NavigableSet<E> descendingSet() {
            UnmodifiableNavigableSet<E> unmodifiableNavigableSet = this.descendingSet;
            if (unmodifiableNavigableSet != null) {
                return unmodifiableNavigableSet;
            }
            UnmodifiableNavigableSet<E> unmodifiableNavigableSet2 = new UnmodifiableNavigableSet<>(this.delegate.descendingSet());
            this.descendingSet = unmodifiableNavigableSet2;
            unmodifiableNavigableSet2.descendingSet = this;
            return unmodifiableNavigableSet2;
        }

        @Override // java.util.NavigableSet
        public E floor(E e2) {
            return this.delegate.floor(e2);
        }

        @Override // java.util.NavigableSet
        public NavigableSet<E> headSet(E e2, boolean z) {
            return Sets.unmodifiableNavigableSet(this.delegate.headSet(e2, z));
        }

        @Override // java.util.NavigableSet
        public E higher(E e2) {
            return this.delegate.higher(e2);
        }

        @Override // java.util.NavigableSet
        public E lower(E e2) {
            return this.delegate.lower(e2);
        }

        @Override // java.util.NavigableSet
        public E pollFirst() {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.NavigableSet
        public E pollLast() {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.NavigableSet
        public NavigableSet<E> subSet(E e2, boolean z, E e3, boolean z2) {
            return Sets.unmodifiableNavigableSet(this.delegate.subSet(e2, z, e3, z2));
        }

        @Override // java.util.NavigableSet
        public NavigableSet<E> tailSet(E e2, boolean z) {
            return Sets.unmodifiableNavigableSet(this.delegate.tailSet(e2, z));
        }

        @Override // com.google.common.collect.ForwardingSortedSet, com.google.common.collect.ForwardingSet, com.google.common.collect.ForwardingCollection, com.google.common.collect.ForwardingObject
        public SortedSet<E> delegate() {
            return this.unmodifiableDelegate;
        }
    }

    public static boolean removeAllImpl(Set<?> set, Collection<?> collection) {
        if (collection != null) {
            if (collection instanceof Multiset) {
                collection = ((Multiset) collection).elementSet();
            }
            if ((collection instanceof Set) && collection.size() > set.size()) {
                return Iterators.removeAll(set.iterator(), collection);
            }
            return removeAllImpl(set, collection.iterator());
        }
        throw new NullPointerException();
    }

    public static <E> HashSet<E> newHashSet(E... eArr) {
        HashSet<E> hashSetNewHashSetWithExpectedSize = newHashSetWithExpectedSize(eArr.length);
        Collections.addAll(hashSetNewHashSetWithExpectedSize, eArr);
        return hashSetNewHashSetWithExpectedSize;
    }

    public static <E> TreeSet<E> newTreeSet(Comparator<? super E> comparator) {
        if (comparator != null) {
            return new TreeSet<>(comparator);
        }
        throw new NullPointerException();
    }

    public static <E> HashSet<E> newHashSet(Iterable<? extends E> iterable) {
        if (iterable instanceof Collection) {
            return new HashSet<>((Collection) iterable);
        }
        return newHashSet(iterable.iterator());
    }

    public static <E extends Enum<E>> EnumSet<E> complementOf(Collection<E> collection, Class<E> cls) {
        if (collection != null) {
            if (collection instanceof EnumSet) {
                return EnumSet.complementOf((EnumSet) collection);
            }
            return makeComplementByHand(collection, cls);
        }
        throw new NullPointerException();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static <E> SortedSet<E> filter(SortedSet<E> sortedSet, Predicate<? super E> predicate) {
        if (sortedSet instanceof FilteredSet) {
            FilteredSet filteredSet = (FilteredSet) sortedSet;
            return new FilteredSortedSet((SortedSet) filteredSet.unfiltered, Predicates.and(filteredSet.predicate, predicate));
        }
        if (sortedSet == 0) {
            throw new NullPointerException();
        }
        if (predicate != null) {
            return new FilteredSortedSet(sortedSet, predicate);
        }
        throw new NullPointerException();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @GwtIncompatible
    public static <E> NavigableSet<E> filter(NavigableSet<E> navigableSet, Predicate<? super E> predicate) {
        if (navigableSet instanceof FilteredSet) {
            FilteredSet filteredSet = (FilteredSet) navigableSet;
            return new FilteredNavigableSet((NavigableSet) filteredSet.unfiltered, Predicates.and(filteredSet.predicate, predicate));
        }
        if (navigableSet == 0) {
            throw new NullPointerException();
        }
        if (predicate != null) {
            return new FilteredNavigableSet(navigableSet, predicate);
        }
        throw new NullPointerException();
    }
}
