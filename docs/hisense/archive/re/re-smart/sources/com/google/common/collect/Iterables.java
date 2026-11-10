package com.google.common.collect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.RandomAccess;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
@GwtCompatible(emulated = true)
public final class Iterables {

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: renamed from: com.google.common.collect.Iterables$1, reason: invalid class name */
    public class AnonymousClass1<T> extends FluentIterable<T> {
        public final /* synthetic */ Iterable val$iterable;

        public AnonymousClass1(Iterable iterable) {
            this.val$iterable = iterable;
        }

        @Override // java.lang.Iterable
        public Iterator<T> iterator() {
            return Iterators.cycle(this.val$iterable);
        }

        @Override // com.google.common.collect.FluentIterable
        public String toString() {
            return String.valueOf(this.val$iterable.toString()).concat(" (cycled)");
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: renamed from: com.google.common.collect.Iterables$10, reason: invalid class name */
    public class AnonymousClass10<T> implements Function<Iterable<? extends T>, Iterator<? extends T>> {
        @Override // com.google.common.base.Function
        public Iterator<? extends T> apply(Iterable<? extends T> iterable) {
            return iterable.iterator();
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: renamed from: com.google.common.collect.Iterables$4, reason: invalid class name */
    public class AnonymousClass4<T> extends FluentIterable<T> {
        public final /* synthetic */ Predicate val$retainIfTrue;
        public final /* synthetic */ Iterable val$unfiltered;

        public AnonymousClass4(Iterable iterable, Predicate predicate) {
            this.val$unfiltered = iterable;
            this.val$retainIfTrue = predicate;
        }

        @Override // java.lang.Iterable
        public Iterator<T> iterator() {
            return Iterators.filter(this.val$unfiltered.iterator(), this.val$retainIfTrue);
        }
    }

    @CanIgnoreReturnValue
    public static <T> boolean addAll(Collection<T> collection, Iterable<? extends T> iterable) {
        if (iterable instanceof Collection) {
            return collection.addAll((Collection) iterable);
        }
        if (iterable != null) {
            return Iterators.addAll(collection, iterable.iterator());
        }
        throw new NullPointerException();
    }

    public static <T> boolean all(Iterable<T> iterable, Predicate<? super T> predicate) {
        return Iterators.all(iterable.iterator(), predicate);
    }

    public static <T> boolean any(Iterable<T> iterable, Predicate<? super T> predicate) {
        return Iterators.any(iterable.iterator(), predicate);
    }

    public static <E> Collection<E> castOrCopyToCollection(Iterable<E> iterable) {
        return iterable instanceof Collection ? (Collection) iterable : Lists.newArrayList(iterable.iterator());
    }

    public static <T> Iterable<T> concat(Iterable<? extends T> iterable, Iterable<? extends T> iterable2) {
        return FluentIterable.concat(iterable, iterable2);
    }

    public static <T> Iterable<T> consumingIterable(final Iterable<T> iterable) {
        if (iterable != null) {
            return new FluentIterable<T>() { // from class: com.google.common.collect.Iterables.8
                @Override // java.lang.Iterable
                public Iterator<T> iterator() {
                    Iterable iterable2 = iterable;
                    return iterable2 instanceof Queue ? new ConsumingQueueIterator((Queue) iterable2) : Iterators.consumingIterator(iterable2.iterator());
                }

                @Override // com.google.common.collect.FluentIterable
                public String toString() {
                    return "Iterables.consumingIterable(...)";
                }
            };
        }
        throw new NullPointerException();
    }

    public static boolean contains(Iterable<?> iterable, Object obj) {
        return iterable instanceof Collection ? Collections2.safeContains((Collection) iterable, obj) : Iterators.contains(iterable.iterator(), obj);
    }

    public static <T> Iterable<T> cycle(Iterable<T> iterable) {
        if (iterable != null) {
            return new AnonymousClass1(iterable);
        }
        throw new NullPointerException();
    }

    public static boolean elementsEqual(Iterable<?> iterable, Iterable<?> iterable2) {
        if ((iterable instanceof Collection) && (iterable2 instanceof Collection) && ((Collection) iterable).size() != ((Collection) iterable2).size()) {
            return false;
        }
        return Iterators.elementsEqual(iterable.iterator(), iterable2.iterator());
    }

    public static <T> Iterable<T> filter(Iterable<T> iterable, Predicate<? super T> predicate) {
        if (iterable == null) {
            throw new NullPointerException();
        }
        if (predicate != null) {
            return new AnonymousClass4(iterable, predicate);
        }
        throw new NullPointerException();
    }

    public static <T> T find(Iterable<T> iterable, Predicate<? super T> predicate) {
        return (T) Iterators.find(iterable.iterator(), predicate);
    }

    public static int frequency(Iterable<?> iterable, Object obj) {
        if (iterable instanceof Multiset) {
            return ((Multiset) iterable).count(obj);
        }
        return iterable instanceof Set ? ((Set) iterable).contains(obj) ? 1 : 0 : Iterators.frequency(iterable.iterator(), obj);
    }

    public static <T> T get(Iterable<T> iterable, int i) {
        if (iterable != null) {
            return iterable instanceof List ? (T) ((List) iterable).get(i) : (T) Iterators.get(iterable.iterator(), i);
        }
        throw new NullPointerException();
    }

    public static <T> T getFirst(Iterable<? extends T> iterable, T t) {
        return (T) Iterators.getNext(iterable.iterator(), t);
    }

    public static <T> T getLast(Iterable<T> iterable) {
        if (!(iterable instanceof List)) {
            return (T) Iterators.getLast(iterable.iterator());
        }
        List list = (List) iterable;
        if (list.isEmpty()) {
            throw new NoSuchElementException();
        }
        return (T) list.get(list.size() - 1);
    }

    public static <T> T getLastInNonemptyList(List<T> list) {
        return list.get(list.size() - 1);
    }

    public static <T> T getOnlyElement(Iterable<T> iterable) {
        return (T) Iterators.getOnlyElement(iterable.iterator());
    }

    public static <T> int indexOf(Iterable<T> iterable, Predicate<? super T> predicate) {
        return Iterators.indexOf(iterable.iterator(), predicate);
    }

    public static boolean isEmpty(Iterable<?> iterable) {
        return iterable instanceof Collection ? ((Collection) iterable).isEmpty() : !iterable.iterator().hasNext();
    }

    public static <T> Iterable<T> limit(final Iterable<T> iterable, final int i) {
        if (iterable == null) {
            throw new NullPointerException();
        }
        Preconditions.checkArgument(i >= 0, "limit is negative");
        return new FluentIterable<T>() { // from class: com.google.common.collect.Iterables.7
            @Override // java.lang.Iterable
            public Iterator<T> iterator() {
                return Iterators.limit(iterable.iterator(), i);
            }
        };
    }

    @Beta
    public static <T> Iterable<T> mergeSorted(final Iterable<? extends Iterable<? extends T>> iterable, final Comparator<? super T> comparator) {
        Preconditions.checkNotNull(iterable, "iterables");
        Preconditions.checkNotNull(comparator, "comparator");
        return new UnmodifiableIterable(new FluentIterable<T>() { // from class: com.google.common.collect.Iterables.9
            @Override // java.lang.Iterable
            public Iterator<T> iterator() {
                return Iterators.mergeSorted(Iterables.transform(iterable, new AnonymousClass10()), comparator);
            }
        }, null);
    }

    public static <T> Iterable<List<T>> paddedPartition(final Iterable<T> iterable, final int i) {
        if (iterable == null) {
            throw new NullPointerException();
        }
        Preconditions.checkArgument(i > 0);
        return new FluentIterable<List<T>>() { // from class: com.google.common.collect.Iterables.3
            @Override // java.lang.Iterable
            public Iterator<List<T>> iterator() {
                return Iterators.paddedPartition(iterable.iterator(), i);
            }
        };
    }

    public static <T> Iterable<List<T>> partition(final Iterable<T> iterable, final int i) {
        if (iterable == null) {
            throw new NullPointerException();
        }
        Preconditions.checkArgument(i > 0);
        return new FluentIterable<List<T>>() { // from class: com.google.common.collect.Iterables.2
            @Override // java.lang.Iterable
            public Iterator<List<T>> iterator() {
                return Iterators.partition(iterable.iterator(), i);
            }
        };
    }

    @CanIgnoreReturnValue
    public static boolean removeAll(Iterable<?> iterable, Collection<?> collection) {
        if (!(iterable instanceof Collection)) {
            return Iterators.removeAll(iterable.iterator(), collection);
        }
        Collection collection2 = (Collection) iterable;
        if (collection != null) {
            return collection2.removeAll(collection);
        }
        throw new NullPointerException();
    }

    public static <T> T removeFirstMatching(Iterable<T> iterable, Predicate<? super T> predicate) {
        if (predicate == null) {
            throw new NullPointerException();
        }
        Iterator<T> it = iterable.iterator();
        while (it.hasNext()) {
            T next = it.next();
            if (predicate.apply(next)) {
                it.remove();
                return next;
            }
        }
        return null;
    }

    @CanIgnoreReturnValue
    public static <T> boolean removeIf(Iterable<T> iterable, Predicate<? super T> predicate) {
        if (!(iterable instanceof RandomAccess) || !(iterable instanceof List)) {
            return Iterators.removeIf(iterable.iterator(), predicate);
        }
        List list = (List) iterable;
        if (predicate != null) {
            return removeIfFromRandomAccessList(list, predicate);
        }
        throw new NullPointerException();
    }

    public static <T> boolean removeIfFromRandomAccessList(List<T> list, Predicate<? super T> predicate) {
        int i = 0;
        int i2 = 0;
        while (i < list.size()) {
            T t = list.get(i);
            if (!predicate.apply(t)) {
                if (i > i2) {
                    try {
                        list.set(i2, t);
                    } catch (IllegalArgumentException unused) {
                        slowRemoveIfForRemainingElements(list, predicate, i2, i);
                        return true;
                    } catch (UnsupportedOperationException unused2) {
                        slowRemoveIfForRemainingElements(list, predicate, i2, i);
                        return true;
                    }
                }
                i2++;
            }
            i++;
        }
        list.subList(i2, list.size()).clear();
        return i != i2;
    }

    @CanIgnoreReturnValue
    public static boolean retainAll(Iterable<?> iterable, Collection<?> collection) {
        if (!(iterable instanceof Collection)) {
            return Iterators.retainAll(iterable.iterator(), collection);
        }
        Collection collection2 = (Collection) iterable;
        if (collection != null) {
            return collection2.retainAll(collection);
        }
        throw new NullPointerException();
    }

    public static int size(Iterable<?> iterable) {
        return iterable instanceof Collection ? ((Collection) iterable).size() : Iterators.size(iterable.iterator());
    }

    public static <T> Iterable<T> skip(final Iterable<T> iterable, final int i) {
        if (iterable == null) {
            throw new NullPointerException();
        }
        Preconditions.checkArgument(i >= 0, "number to skip cannot be negative");
        return new FluentIterable<T>() { // from class: com.google.common.collect.Iterables.6
            @Override // java.lang.Iterable
            public Iterator<T> iterator() {
                Iterable iterable2 = iterable;
                if (iterable2 instanceof List) {
                    List list = (List) iterable2;
                    return list.subList(Math.min(list.size(), i), list.size()).iterator();
                }
                final Iterator<T> it = iterable2.iterator();
                Iterators.advance(it, i);
                return new Iterator<T>(this) { // from class: com.google.common.collect.Iterables.6.1
                    public boolean atStart = true;

                    @Override // java.util.Iterator
                    public boolean hasNext() {
                        return it.hasNext();
                    }

                    @Override // java.util.Iterator
                    public T next() {
                        T t = (T) it.next();
                        this.atStart = false;
                        return t;
                    }

                    @Override // java.util.Iterator
                    public void remove() {
                        Preconditions.checkState(!this.atStart, "no calls to next() since the last call to remove()");
                        it.remove();
                    }
                };
            }
        };
    }

    public static <T> void slowRemoveIfForRemainingElements(List<T> list, Predicate<? super T> predicate, int i, int i2) {
        for (int size = list.size() - 1; size > i2; size--) {
            if (predicate.apply(list.get(size))) {
                list.remove(size);
            }
        }
        for (int i3 = i2 - 1; i3 >= i; i3--) {
            list.remove(i3);
        }
    }

    @GwtIncompatible
    public static <T> T[] toArray(Iterable<? extends T> iterable, Class<T> cls) {
        return (T[]) toArray(iterable, ObjectArrays.newArray(cls, 0));
    }

    public static <T> Function<Iterable<? extends T>, Iterator<? extends T>> toIterator() {
        return new AnonymousClass10();
    }

    public static String toString(Iterable<?> iterable) {
        return Iterators.toString(iterable.iterator());
    }

    public static <F, T> Iterable<T> transform(final Iterable<F> iterable, final Function<? super F, ? extends T> function) {
        if (iterable == null) {
            throw new NullPointerException();
        }
        if (function != null) {
            return new FluentIterable<T>() { // from class: com.google.common.collect.Iterables.5
                @Override // java.lang.Iterable
                public Iterator<T> iterator() {
                    return Iterators.transform(iterable.iterator(), function);
                }
            };
        }
        throw new NullPointerException();
    }

    public static <T> Optional<T> tryFind(Iterable<T> iterable, Predicate<? super T> predicate) {
        return Iterators.tryFind(iterable.iterator(), predicate);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static <T> Iterable<T> unmodifiableIterable(Iterable<? extends T> iterable) {
        if (iterable != 0) {
            return ((iterable instanceof UnmodifiableIterable) || (iterable instanceof ImmutableCollection)) ? iterable : new UnmodifiableIterable(iterable, null);
        }
        throw new NullPointerException();
    }

    private static final class UnmodifiableIterable<T> extends FluentIterable<T> {
        public final Iterable<? extends T> iterable;

        public UnmodifiableIterable(Iterable<? extends T> iterable) {
            this.iterable = iterable;
        }

        @Override // java.lang.Iterable
        public Iterator<T> iterator() {
            return Iterators.unmodifiableIterator(this.iterable.iterator());
        }

        @Override // com.google.common.collect.FluentIterable
        public String toString() {
            return this.iterable.toString();
        }

        public /* synthetic */ UnmodifiableIterable(Iterable iterable, AnonymousClass1 anonymousClass1) {
            this.iterable = iterable;
        }
    }

    public static <T> Iterable<T> concat(Iterable<? extends T> iterable, Iterable<? extends T> iterable2, Iterable<? extends T> iterable3) {
        return FluentIterable.concat(iterable, iterable2, iterable3);
    }

    public static <T> T find(Iterable<? extends T> iterable, Predicate<? super T> predicate, T t) {
        return (T) Iterators.find(iterable.iterator(), predicate, t);
    }

    public static <T> T getOnlyElement(Iterable<? extends T> iterable, T t) {
        return (T) Iterators.getOnlyElement(iterable.iterator(), t);
    }

    public static <T> T[] toArray(Iterable<? extends T> iterable, T[] tArr) {
        return (T[]) castOrCopyToCollection(iterable).toArray(tArr);
    }

    public static <T> Iterable<T> concat(Iterable<? extends T> iterable, Iterable<? extends T> iterable2, Iterable<? extends T> iterable3, Iterable<? extends T> iterable4) {
        return FluentIterable.concat(iterable, iterable2, iterable3, iterable4);
    }

    @SafeVarargs
    public static <T> Iterable<T> cycle(T... tArr) {
        return new AnonymousClass1(Lists.newArrayList(tArr));
    }

    @SafeVarargs
    public static <T> Iterable<T> concat(Iterable<? extends T>... iterableArr) {
        return FluentIterable.concat(iterableArr);
    }

    @GwtIncompatible
    public static <T> Iterable<T> filter(Iterable<?> iterable, Class<T> cls) {
        if (iterable == null) {
            throw new NullPointerException();
        }
        if (cls != null) {
            return new AnonymousClass4(iterable, Predicates.instanceOf(cls));
        }
        throw new NullPointerException();
    }

    public static Object[] toArray(Iterable<?> iterable) {
        return castOrCopyToCollection(iterable).toArray();
    }

    @Deprecated
    public static <E> Iterable<E> unmodifiableIterable(ImmutableCollection<E> immutableCollection) {
        if (immutableCollection != null) {
            return immutableCollection;
        }
        throw new NullPointerException();
    }

    public static <T> Iterable<T> concat(Iterable<? extends Iterable<? extends T>> iterable) {
        return FluentIterable.concat(iterable);
    }

    public static <T> T get(Iterable<? extends T> iterable, int i, T t) {
        if (iterable != null) {
            Iterators.checkNonnegative(i);
            if (iterable instanceof List) {
                List list = (List) iterable;
                return i < list.size() ? (T) list.get(i) : t;
            }
            Iterator<? extends T> it = iterable.iterator();
            Iterators.advance(it, i);
            return it.hasNext() ? it.next() : t;
        }
        throw new NullPointerException();
    }

    public static <T> T getLast(Iterable<? extends T> iterable, T t) {
        if (iterable instanceof Collection) {
            if (((Collection) iterable).isEmpty()) {
                return t;
            }
            if (iterable instanceof List) {
                return (T) getLastInNonemptyList((List) iterable);
            }
        }
        return (T) Iterators.getLast(iterable.iterator(), t);
    }
}
