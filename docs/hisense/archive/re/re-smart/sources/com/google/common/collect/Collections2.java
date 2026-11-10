package com.google.common.collect;

import b.a.a.a.a;
import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.math.IntMath;
import com.google.firebase.storage.StorageMetadata;
import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
@GwtCompatible
public final class Collections2 {

    static class FilteredCollection<E> extends AbstractCollection<E> {
        public final Predicate<? super E> predicate;
        public final Collection<E> unfiltered;

        public FilteredCollection(Collection<E> collection, Predicate<? super E> predicate) {
            this.unfiltered = collection;
            this.predicate = predicate;
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean add(E e2) {
            Preconditions.checkArgument(this.predicate.apply(e2));
            return this.unfiltered.add(e2);
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean addAll(Collection<? extends E> collection) {
            Iterator<? extends E> it = collection.iterator();
            while (it.hasNext()) {
                Preconditions.checkArgument(this.predicate.apply(it.next()));
            }
            return this.unfiltered.addAll(collection);
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public void clear() {
            Iterables.removeIf(this.unfiltered, this.predicate);
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean contains(Object obj) {
            if (Collections2.safeContains(this.unfiltered, obj)) {
                return this.predicate.apply(obj);
            }
            return false;
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean containsAll(Collection<?> collection) {
            return Collections2.containsAllImpl(this, collection);
        }

        public FilteredCollection<E> createCombined(Predicate<? super E> predicate) {
            return new FilteredCollection<>(this.unfiltered, Predicates.and(this.predicate, predicate));
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean isEmpty() {
            return !Iterables.any(this.unfiltered, this.predicate);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
        public Iterator<E> iterator() {
            return Iterators.filter(this.unfiltered.iterator(), this.predicate);
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean remove(Object obj) {
            return contains(obj) && this.unfiltered.remove(obj);
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean removeAll(Collection<?> collection) {
            Iterator<E> it = this.unfiltered.iterator();
            boolean z = false;
            while (it.hasNext()) {
                E next = it.next();
                if (this.predicate.apply(next) && collection.contains(next)) {
                    it.remove();
                    z = true;
                }
            }
            return z;
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean retainAll(Collection<?> collection) {
            Iterator<E> it = this.unfiltered.iterator();
            boolean z = false;
            while (it.hasNext()) {
                E next = it.next();
                if (this.predicate.apply(next) && !collection.contains(next)) {
                    it.remove();
                    z = true;
                }
            }
            return z;
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public int size() {
            Iterator<E> it = this.unfiltered.iterator();
            int i = 0;
            while (it.hasNext()) {
                if (this.predicate.apply(it.next())) {
                    i++;
                }
            }
            return i;
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public Object[] toArray() {
            return Lists.newArrayList(iterator()).toArray();
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public <T> T[] toArray(T[] tArr) {
            return (T[]) Lists.newArrayList(iterator()).toArray(tArr);
        }
    }

    private static final class OrderedPermutationCollection<E> extends AbstractCollection<List<E>> {
        public final Comparator<? super E> comparator;
        public final ImmutableList<E> inputList;
        public final int size;

        public OrderedPermutationCollection(Iterable<E> iterable, Comparator<? super E> comparator) {
            this.inputList = ImmutableList.sortedCopyOf(comparator, iterable);
            this.comparator = comparator;
            this.size = calculateSize(this.inputList, comparator);
        }

        public static <E> int calculateSize(List<E> list, Comparator<? super E> comparator) {
            int i = 1;
            int iSaturatedMultiply = 1;
            int i2 = 1;
            while (i < list.size()) {
                if (comparator.compare(list.get(i - 1), list.get(i)) < 0) {
                    iSaturatedMultiply = IntMath.saturatedMultiply(iSaturatedMultiply, IntMath.binomial(i, i2));
                    i2 = 0;
                    if (iSaturatedMultiply == Integer.MAX_VALUE) {
                        return Integer.MAX_VALUE;
                    }
                }
                i++;
                i2++;
            }
            return IntMath.saturatedMultiply(iSaturatedMultiply, IntMath.binomial(i, i2));
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean contains(Object obj) {
            if (!(obj instanceof List)) {
                return false;
            }
            return Collections2.isPermutation(this.inputList, (List) obj);
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean isEmpty() {
            return false;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
        public Iterator<List<E>> iterator() {
            return new OrderedPermutationIterator(this.inputList, this.comparator);
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public int size() {
            return this.size;
        }

        @Override // java.util.AbstractCollection
        public String toString() {
            String strValueOf = String.valueOf(this.inputList);
            return a.a(strValueOf.length() + 30, "orderedPermutationCollection(", strValueOf, ")");
        }
    }

    private static final class OrderedPermutationIterator<E> extends AbstractIterator<List<E>> {
        public final Comparator<? super E> comparator;
        public List<E> nextPermutation;

        public OrderedPermutationIterator(List<E> list, Comparator<? super E> comparator) {
            this.nextPermutation = Lists.newArrayList(list);
            this.comparator = comparator;
        }

        public void calculateNextPermutation() {
            int iFindNextJ = findNextJ();
            if (iFindNextJ == -1) {
                this.nextPermutation = null;
                return;
            }
            Collections.swap(this.nextPermutation, iFindNextJ, findNextL(iFindNextJ));
            Collections.reverse(this.nextPermutation.subList(iFindNextJ + 1, this.nextPermutation.size()));
        }

        public int findNextJ() {
            for (int size = this.nextPermutation.size() - 2; size >= 0; size--) {
                if (this.comparator.compare(this.nextPermutation.get(size), this.nextPermutation.get(size + 1)) < 0) {
                    return size;
                }
            }
            return -1;
        }

        public int findNextL(int i) {
            E e2 = this.nextPermutation.get(i);
            for (int size = this.nextPermutation.size() - 1; size > i; size--) {
                if (this.comparator.compare(e2, this.nextPermutation.get(size)) < 0) {
                    return size;
                }
            }
            throw new AssertionError("this statement should be unreachable");
        }

        @Override // com.google.common.collect.AbstractIterator
        public List<E> computeNext() {
            List<E> list = this.nextPermutation;
            if (list == null) {
                return endOfData();
            }
            ImmutableList immutableListCopyOf = ImmutableList.copyOf((Collection) list);
            calculateNextPermutation();
            return immutableListCopyOf;
        }
    }

    private static final class PermutationCollection<E> extends AbstractCollection<List<E>> {
        public final ImmutableList<E> inputList;

        public PermutationCollection(ImmutableList<E> immutableList) {
            this.inputList = immutableList;
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean contains(Object obj) {
            if (!(obj instanceof List)) {
                return false;
            }
            return Collections2.isPermutation(this.inputList, (List) obj);
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean isEmpty() {
            return false;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
        public Iterator<List<E>> iterator() {
            return new PermutationIterator(this.inputList);
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public int size() {
            return IntMath.factorial(this.inputList.size());
        }

        @Override // java.util.AbstractCollection
        public String toString() {
            String strValueOf = String.valueOf(this.inputList);
            return a.a(strValueOf.length() + 14, "permutations(", strValueOf, ")");
        }
    }

    private static class PermutationIterator<E> extends AbstractIterator<List<E>> {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final int[] f1952c;
        public int j;
        public final List<E> list;
        public final int[] o;

        public PermutationIterator(List<E> list) {
            this.list = new ArrayList(list);
            int size = list.size();
            this.f1952c = new int[size];
            this.o = new int[size];
            Arrays.fill(this.f1952c, 0);
            Arrays.fill(this.o, 1);
            this.j = Integer.MAX_VALUE;
        }

        public void calculateNextPermutation() {
            this.j = this.list.size() - 1;
            if (this.j == -1) {
                return;
            }
            int i = 0;
            while (true) {
                int[] iArr = this.f1952c;
                int i2 = this.j;
                int i3 = iArr[i2] + this.o[i2];
                if (i3 < 0) {
                    switchDirection();
                } else if (i3 != i2 + 1) {
                    Collections.swap(this.list, (i2 - iArr[i2]) + i, (i2 - i3) + i);
                    this.f1952c[this.j] = i3;
                    return;
                } else {
                    if (i2 == 0) {
                        return;
                    }
                    i++;
                    switchDirection();
                }
            }
        }

        public void switchDirection() {
            int[] iArr = this.o;
            int i = this.j;
            iArr[i] = -iArr[i];
            this.j = i - 1;
        }

        @Override // com.google.common.collect.AbstractIterator
        public List<E> computeNext() {
            if (this.j <= 0) {
                return endOfData();
            }
            ImmutableList immutableListCopyOf = ImmutableList.copyOf((Collection) this.list);
            calculateNextPermutation();
            return immutableListCopyOf;
        }
    }

    static class TransformedCollection<F, T> extends AbstractCollection<T> {
        public final Collection<F> fromCollection;
        public final Function<? super F, ? extends T> function;

        public TransformedCollection(Collection<F> collection, Function<? super F, ? extends T> function) {
            if (collection == null) {
                throw new NullPointerException();
            }
            this.fromCollection = collection;
            if (function == null) {
                throw new NullPointerException();
            }
            this.function = function;
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public void clear() {
            this.fromCollection.clear();
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean isEmpty() {
            return this.fromCollection.isEmpty();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
        public Iterator<T> iterator() {
            return Iterators.transform(this.fromCollection.iterator(), this.function);
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public int size() {
            return this.fromCollection.size();
        }
    }

    public static boolean containsAllImpl(Collection<?> collection, Collection<?> collection2) {
        Iterator<?> it = collection2.iterator();
        while (it.hasNext()) {
            if (!collection.contains(it.next())) {
                return false;
            }
        }
        return true;
    }

    public static <E> ObjectCountHashMap<E> counts(Collection<E> collection) {
        ObjectCountHashMap<E> objectCountHashMap = new ObjectCountHashMap<>();
        for (E e2 : collection) {
            objectCountHashMap.put(e2, objectCountHashMap.get(e2) + 1);
        }
        return objectCountHashMap;
    }

    public static <E> Collection<E> filter(Collection<E> collection, Predicate<? super E> predicate) {
        if (collection instanceof FilteredCollection) {
            return ((FilteredCollection) collection).createCombined(predicate);
        }
        if (collection == null) {
            throw new NullPointerException();
        }
        if (predicate != null) {
            return new FilteredCollection(collection, predicate);
        }
        throw new NullPointerException();
    }

    public static boolean isPermutation(List<?> list, List<?> list2) {
        if (list.size() != list2.size()) {
            return false;
        }
        ObjectCountHashMap objectCountHashMapCounts = counts(list);
        ObjectCountHashMap objectCountHashMapCounts2 = counts(list2);
        if (list.size() != list2.size()) {
            return false;
        }
        for (int i = 0; i < list.size(); i++) {
            if (objectCountHashMapCounts.getValue(i) != objectCountHashMapCounts2.get(objectCountHashMapCounts.getKey(i))) {
                return false;
            }
        }
        return true;
    }

    public static StringBuilder newStringBuilderForCollection(int i) {
        CollectPreconditions.checkNonnegative(i, StorageMetadata.SIZE_KEY);
        return new StringBuilder((int) Math.min(((long) i) * 8, 1073741824L));
    }

    @Beta
    public static <E extends Comparable<? super E>> Collection<List<E>> orderedPermutations(Iterable<E> iterable) {
        return new OrderedPermutationCollection(iterable, Ordering.natural());
    }

    @Beta
    public static <E> Collection<List<E>> permutations(Collection<E> collection) {
        return new PermutationCollection(ImmutableList.copyOf((Collection) collection));
    }

    public static boolean safeContains(Collection<?> collection, Object obj) {
        if (collection == null) {
            throw new NullPointerException();
        }
        try {
            return collection.contains(obj);
        } catch (ClassCastException | NullPointerException unused) {
            return false;
        }
    }

    public static boolean safeRemove(Collection<?> collection, Object obj) {
        if (collection == null) {
            throw new NullPointerException();
        }
        try {
            return collection.remove(obj);
        } catch (ClassCastException | NullPointerException unused) {
            return false;
        }
    }

    public static String toStringImpl(Collection<?> collection) {
        StringBuilder sbNewStringBuilderForCollection = newStringBuilderForCollection(collection.size());
        sbNewStringBuilderForCollection.append('[');
        boolean z = true;
        for (Object obj : collection) {
            if (!z) {
                sbNewStringBuilderForCollection.append(", ");
            }
            z = false;
            if (obj == collection) {
                sbNewStringBuilderForCollection.append("(this Collection)");
            } else {
                sbNewStringBuilderForCollection.append(obj);
            }
        }
        sbNewStringBuilderForCollection.append(']');
        return sbNewStringBuilderForCollection.toString();
    }

    public static <F, T> Collection<T> transform(Collection<F> collection, Function<? super F, T> function) {
        return new TransformedCollection(collection, function);
    }

    @Beta
    public static <E> Collection<List<E>> orderedPermutations(Iterable<E> iterable, Comparator<? super E> comparator) {
        return new OrderedPermutationCollection(iterable, comparator);
    }
}
