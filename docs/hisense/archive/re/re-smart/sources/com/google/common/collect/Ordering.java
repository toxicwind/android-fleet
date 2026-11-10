package com.google.common.collect;

import b.a.a.a.a;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Function;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes.dex */
@GwtCompatible
public abstract class Ordering<T> implements Comparator<T> {
    public static final int LEFT_IS_GREATER = 1;
    public static final int RIGHT_IS_GREATER = -1;

    @VisibleForTesting
    static class ArbitraryOrdering extends Ordering<Object> {
        public final AtomicInteger counter = new AtomicInteger(0);
        public final ConcurrentMap<Object, Integer> uids = new MapMaker().weakKeys().makeMap();

        private Integer getUid(Object obj) {
            Integer num = this.uids.get(obj);
            if (num != null) {
                return num;
            }
            Integer numValueOf = Integer.valueOf(this.counter.getAndIncrement());
            Integer numPutIfAbsent = this.uids.putIfAbsent(obj, numValueOf);
            return numPutIfAbsent != null ? numPutIfAbsent : numValueOf;
        }

        @Override // com.google.common.collect.Ordering, java.util.Comparator
        public int compare(Object obj, Object obj2) {
            if (obj == obj2) {
                return 0;
            }
            if (obj == null) {
                return -1;
            }
            if (obj2 == null) {
                return 1;
            }
            int iIdentityHashCode = identityHashCode(obj);
            int iIdentityHashCode2 = identityHashCode(obj2);
            if (iIdentityHashCode != iIdentityHashCode2) {
                return iIdentityHashCode < iIdentityHashCode2 ? -1 : 1;
            }
            int iCompareTo = getUid(obj).compareTo(getUid(obj2));
            if (iCompareTo != 0) {
                return iCompareTo;
            }
            throw new AssertionError();
        }

        public int identityHashCode(Object obj) {
            return System.identityHashCode(obj);
        }

        public String toString() {
            return "Ordering.arbitrary()";
        }
    }

    private static class ArbitraryOrderingHolder {
        public static final Ordering<Object> ARBITRARY_ORDERING = new ArbitraryOrdering();
    }

    @VisibleForTesting
    static class IncomparableValueException extends ClassCastException {
        public static final long serialVersionUID = 0;
        public final Object value;

        /* JADX WARN: Illegal instructions before constructor call */
        public IncomparableValueException(Object obj) {
            String strValueOf = String.valueOf(obj);
            super(a.a(strValueOf.length() + 22, "Cannot compare value: ", strValueOf));
            this.value = obj;
        }
    }

    @GwtCompatible(serializable = true)
    public static Ordering<Object> allEqual() {
        return AllEqualOrdering.INSTANCE;
    }

    public static Ordering<Object> arbitrary() {
        return ArbitraryOrderingHolder.ARBITRARY_ORDERING;
    }

    @GwtCompatible(serializable = true)
    public static <T> Ordering<T> explicit(List<T> list) {
        return new ExplicitOrdering(list);
    }

    @GwtCompatible(serializable = true)
    public static <T> Ordering<T> from(Comparator<T> comparator) {
        return comparator instanceof Ordering ? (Ordering) comparator : new ComparatorOrdering(comparator);
    }

    @GwtCompatible(serializable = true)
    public static <C extends Comparable> Ordering<C> natural() {
        return NaturalOrdering.INSTANCE;
    }

    @GwtCompatible(serializable = true)
    public static Ordering<Object> usingToString() {
        return UsingToStringOrdering.INSTANCE;
    }

    @Deprecated
    public int binarySearch(List<? extends T> list, T t) {
        return Collections.binarySearch(list, t, this);
    }

    @Override // java.util.Comparator
    @CanIgnoreReturnValue
    public abstract int compare(T t, T t2);

    @GwtCompatible(serializable = true)
    public <U extends T> Ordering<U> compound(Comparator<? super U> comparator) {
        if (comparator != null) {
            return new CompoundOrdering(this, comparator);
        }
        throw new NullPointerException();
    }

    public <E extends T> List<E> greatestOf(Iterable<E> iterable, int i) {
        return reverse().leastOf(iterable, i);
    }

    public <E extends T> ImmutableList<E> immutableSortedCopy(Iterable<E> iterable) {
        return ImmutableList.sortedCopyOf(this, iterable);
    }

    public boolean isOrdered(Iterable<? extends T> iterable) {
        Iterator<? extends T> it = iterable.iterator();
        if (!it.hasNext()) {
            return true;
        }
        T next = it.next();
        while (it.hasNext()) {
            T next2 = it.next();
            if (compare(next, next2) > 0) {
                return false;
            }
            next = next2;
        }
        return true;
    }

    public boolean isStrictlyOrdered(Iterable<? extends T> iterable) {
        Iterator<? extends T> it = iterable.iterator();
        if (!it.hasNext()) {
            return true;
        }
        T next = it.next();
        while (it.hasNext()) {
            T next2 = it.next();
            if (compare(next, next2) >= 0) {
                return false;
            }
            next = next2;
        }
        return true;
    }

    public <E extends T> List<E> leastOf(Iterable<E> iterable, int i) {
        if (iterable instanceof Collection) {
            Collection collection = (Collection) iterable;
            if (collection.size() <= ((long) i) * 2) {
                Object[] array = collection.toArray();
                Arrays.sort(array, this);
                if (array.length > i) {
                    array = Arrays.copyOf(array, i);
                }
                return Collections.unmodifiableList(Arrays.asList(array));
            }
        }
        return leastOf(iterable.iterator(), i);
    }

    @GwtCompatible(serializable = true)
    public <S extends T> Ordering<Iterable<S>> lexicographical() {
        return new LexicographicalOrdering(this);
    }

    public <E extends T> E max(Iterator<E> it) {
        E next = it.next();
        while (it.hasNext()) {
            next = (E) max(next, it.next());
        }
        return next;
    }

    public <E extends T> E min(Iterator<E> it) {
        E next = it.next();
        while (it.hasNext()) {
            next = (E) min(next, it.next());
        }
        return next;
    }

    @GwtCompatible(serializable = true)
    public <S extends T> Ordering<S> nullsFirst() {
        return new NullsFirstOrdering(this);
    }

    @GwtCompatible(serializable = true)
    public <S extends T> Ordering<S> nullsLast() {
        return new NullsLastOrdering(this);
    }

    public <T2 extends T> Ordering<Map.Entry<T2, ?>> onKeys() {
        return (Ordering<Map.Entry<T2, ?>>) onResultOf(Maps.EntryFunction.KEY);
    }

    @GwtCompatible(serializable = true)
    public <F> Ordering<F> onResultOf(Function<F, ? extends T> function) {
        return new ByFunctionOrdering(function, this);
    }

    @GwtCompatible(serializable = true)
    public <S extends T> Ordering<S> reverse() {
        return new ReverseOrdering(this);
    }

    public <E extends T> List<E> sortedCopy(Iterable<E> iterable) {
        Object[] array = Iterables.toArray(iterable);
        Arrays.sort(array, this);
        return Lists.newArrayList(Arrays.asList(array));
    }

    @GwtCompatible(serializable = true)
    public static <T> Ordering<T> explicit(T t, T... tArr) {
        return new ExplicitOrdering(new Lists.OnePlusArrayList(t, tArr));
    }

    public <E extends T> List<E> greatestOf(Iterator<E> it, int i) {
        return reverse().leastOf(it, i);
    }

    @GwtCompatible(serializable = true)
    public static <T> Ordering<T> compound(Iterable<? extends Comparator<? super T>> iterable) {
        return new CompoundOrdering(iterable);
    }

    @GwtCompatible(serializable = true)
    @Deprecated
    public static <T> Ordering<T> from(Ordering<T> ordering) {
        if (ordering != null) {
            return ordering;
        }
        throw new NullPointerException();
    }

    public <E extends T> E max(Iterable<E> iterable) {
        return (E) max(iterable.iterator());
    }

    public <E extends T> E min(Iterable<E> iterable) {
        return (E) min(iterable.iterator());
    }

    /* JADX WARN: Multi-variable type inference failed */
    public <E extends T> E max(E e2, E e3) {
        return compare(e2, e3) >= 0 ? e2 : e3;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public <E extends T> E min(E e2, E e3) {
        return compare(e2, e3) <= 0 ? e2 : e3;
    }

    public <E extends T> E max(E e2, E e3, E e4, E... eArr) {
        E e5 = (E) max(max(e2, e3), e4);
        for (E e6 : eArr) {
            e5 = (E) max(e5, e6);
        }
        return e5;
    }

    public <E extends T> E min(E e2, E e3, E e4, E... eArr) {
        E e5 = (E) min(min(e2, e3), e4);
        for (E e6 : eArr) {
            e5 = (E) min(e5, e6);
        }
        return e5;
    }

    public <E extends T> List<E> leastOf(Iterator<E> it, int i) {
        if (it != null) {
            CollectPreconditions.checkNonnegative(i, "k");
            if (i == 0 || !it.hasNext()) {
                return Collections.emptyList();
            }
            if (i >= 1073741823) {
                ArrayList arrayListNewArrayList = Lists.newArrayList(it);
                Collections.sort(arrayListNewArrayList, this);
                if (arrayListNewArrayList.size() > i) {
                    arrayListNewArrayList.subList(i, arrayListNewArrayList.size()).clear();
                }
                arrayListNewArrayList.trimToSize();
                return Collections.unmodifiableList(arrayListNewArrayList);
            }
            TopKSelector topKSelector = new TopKSelector(this, i);
            topKSelector.offerAll(it);
            return topKSelector.topK();
        }
        throw new NullPointerException();
    }
}
