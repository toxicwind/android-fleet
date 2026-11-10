package com.google.common.collect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Function;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.math.IntMath;
import com.google.common.primitives.Ints;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.io.Serializable;
import java.math.RoundingMode;
import java.util.AbstractList;
import java.util.AbstractSequentialList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.RandomAccess;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: loaded from: classes.dex */
@GwtCompatible(emulated = true)
public final class Lists {

    private static class AbstractListWrapper<E> extends AbstractList<E> {
        public final List<E> backingList;

        public AbstractListWrapper(List<E> list) {
            if (list == null) {
                throw new NullPointerException();
            }
            this.backingList = list;
        }

        @Override // java.util.AbstractList, java.util.List
        public void add(int i, E e2) {
            this.backingList.add(i, e2);
        }

        @Override // java.util.AbstractList, java.util.List
        public boolean addAll(int i, Collection<? extends E> collection) {
            return this.backingList.addAll(i, collection);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public boolean contains(Object obj) {
            return this.backingList.contains(obj);
        }

        @Override // java.util.AbstractList, java.util.List
        public E get(int i) {
            return this.backingList.get(i);
        }

        @Override // java.util.AbstractList, java.util.List
        public E remove(int i) {
            return this.backingList.remove(i);
        }

        @Override // java.util.AbstractList, java.util.List
        public E set(int i, E e2) {
            return this.backingList.set(i, e2);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public int size() {
            return this.backingList.size();
        }
    }

    private static final class CharSequenceAsList extends AbstractList<Character> {
        public final CharSequence sequence;

        public CharSequenceAsList(CharSequence charSequence) {
            this.sequence = charSequence;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public int size() {
            return this.sequence.length();
        }

        @Override // java.util.AbstractList, java.util.List
        public Character get(int i) {
            Preconditions.checkElementIndex(i, size(), FirebaseAnalytics.Param.INDEX);
            return Character.valueOf(this.sequence.charAt(i));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX INFO: Access modifiers changed from: private */
    public static class OnePlusArrayList<E> extends AbstractList<E> implements Serializable, RandomAccess {
        public static final long serialVersionUID = 0;
        public final E first;
        public final E[] rest;

        public OnePlusArrayList(E e2, E[] eArr) {
            this.first = e2;
            if (eArr == null) {
                throw new NullPointerException();
            }
            this.rest = eArr;
        }

        @Override // java.util.AbstractList, java.util.List
        public E get(int i) {
            Preconditions.checkElementIndex(i, size(), FirebaseAnalytics.Param.INDEX);
            return i == 0 ? this.first : this.rest[i - 1];
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public int size() {
            return IntMath.saturatedAdd(this.rest.length, 1);
        }
    }

    private static class Partition<T> extends AbstractList<List<T>> {
        public final List<T> list;
        public final int size;

        public Partition(List<T> list, int i) {
            this.list = list;
            this.size = i;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public boolean isEmpty() {
            return this.list.isEmpty();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public int size() {
            return IntMath.divide(this.list.size(), this.size, RoundingMode.CEILING);
        }

        @Override // java.util.AbstractList, java.util.List
        public List<T> get(int i) {
            Preconditions.checkElementIndex(i, size(), FirebaseAnalytics.Param.INDEX);
            int i2 = this.size;
            int i3 = i * i2;
            return this.list.subList(i3, Math.min(i2 + i3, this.list.size()));
        }
    }

    private static class RandomAccessListWrapper<E> extends AbstractListWrapper<E> implements RandomAccess {
        public RandomAccessListWrapper(List<E> list) {
            super(list);
        }
    }

    private static class RandomAccessPartition<T> extends Partition<T> implements RandomAccess {
        public RandomAccessPartition(List<T> list, int i) {
            super(list, i);
        }
    }

    private static class RandomAccessReverseList<T> extends ReverseList<T> implements RandomAccess {
        public RandomAccessReverseList(List<T> list) {
            super(list);
        }
    }

    private static class ReverseList<T> extends AbstractList<T> {
        public final List<T> forwardList;

        public ReverseList(List<T> list) {
            if (list == null) {
                throw new NullPointerException();
            }
            this.forwardList = list;
        }

        public static /* synthetic */ int access$000(ReverseList reverseList, int i) {
            int size = reverseList.size();
            Preconditions.checkPositionIndex(i, size, FirebaseAnalytics.Param.INDEX);
            return size - i;
        }

        private int reverseIndex(int i) {
            int size = size();
            Preconditions.checkElementIndex(i, size, FirebaseAnalytics.Param.INDEX);
            return (size - 1) - i;
        }

        private int reversePosition(int i) {
            int size = size();
            Preconditions.checkPositionIndex(i, size, FirebaseAnalytics.Param.INDEX);
            return size - i;
        }

        @Override // java.util.AbstractList, java.util.List
        public void add(int i, T t) {
            List<T> list = this.forwardList;
            int size = size();
            Preconditions.checkPositionIndex(i, size, FirebaseAnalytics.Param.INDEX);
            list.add(size - i, t);
        }

        @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
        public void clear() {
            this.forwardList.clear();
        }

        @Override // java.util.AbstractList, java.util.List
        public T get(int i) {
            List<T> list = this.forwardList;
            int size = size();
            Preconditions.checkElementIndex(i, size, FirebaseAnalytics.Param.INDEX);
            return list.get((size - 1) - i);
        }

        public List<T> getForwardList() {
            return this.forwardList;
        }

        @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.List
        public Iterator<T> iterator() {
            return listIterator();
        }

        @Override // java.util.AbstractList, java.util.List
        public ListIterator<T> listIterator(int i) {
            int size = size();
            Preconditions.checkPositionIndex(i, size, FirebaseAnalytics.Param.INDEX);
            final ListIterator<T> listIterator = this.forwardList.listIterator(size - i);
            return new ListIterator<T>() { // from class: com.google.common.collect.Lists.ReverseList.1
                public boolean canRemoveOrSet;

                @Override // java.util.ListIterator
                public void add(T t) {
                    listIterator.add(t);
                    listIterator.previous();
                    this.canRemoveOrSet = false;
                }

                @Override // java.util.ListIterator, java.util.Iterator
                public boolean hasNext() {
                    return listIterator.hasPrevious();
                }

                @Override // java.util.ListIterator
                public boolean hasPrevious() {
                    return listIterator.hasNext();
                }

                @Override // java.util.ListIterator, java.util.Iterator
                public T next() {
                    if (!hasNext()) {
                        throw new NoSuchElementException();
                    }
                    this.canRemoveOrSet = true;
                    return (T) listIterator.previous();
                }

                @Override // java.util.ListIterator
                public int nextIndex() {
                    return ReverseList.access$000(ReverseList.this, listIterator.nextIndex());
                }

                @Override // java.util.ListIterator
                public T previous() {
                    if (!hasPrevious()) {
                        throw new NoSuchElementException();
                    }
                    this.canRemoveOrSet = true;
                    return (T) listIterator.next();
                }

                @Override // java.util.ListIterator
                public int previousIndex() {
                    return nextIndex() - 1;
                }

                @Override // java.util.ListIterator, java.util.Iterator
                public void remove() {
                    Preconditions.checkState(this.canRemoveOrSet, "no calls to next() since the last call to remove()");
                    listIterator.remove();
                    this.canRemoveOrSet = false;
                }

                @Override // java.util.ListIterator
                public void set(T t) {
                    Preconditions.checkState(this.canRemoveOrSet);
                    listIterator.set(t);
                }
            };
        }

        @Override // java.util.AbstractList, java.util.List
        public T remove(int i) {
            List<T> list = this.forwardList;
            int size = size();
            Preconditions.checkElementIndex(i, size, FirebaseAnalytics.Param.INDEX);
            return list.remove((size - 1) - i);
        }

        @Override // java.util.AbstractList
        public void removeRange(int i, int i2) {
            subList(i, i2).clear();
        }

        @Override // java.util.AbstractList, java.util.List
        public T set(int i, T t) {
            List<T> list = this.forwardList;
            int size = size();
            Preconditions.checkElementIndex(i, size, FirebaseAnalytics.Param.INDEX);
            return list.set((size - 1) - i, t);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public int size() {
            return this.forwardList.size();
        }

        @Override // java.util.AbstractList, java.util.List
        public List<T> subList(int i, int i2) {
            Preconditions.checkPositionIndexes(i, i2, size());
            List<T> list = this.forwardList;
            int size = size();
            Preconditions.checkPositionIndex(i2, size, FirebaseAnalytics.Param.INDEX);
            int i3 = size - i2;
            int size2 = size();
            Preconditions.checkPositionIndex(i, size2, FirebaseAnalytics.Param.INDEX);
            return Lists.reverse(list.subList(i3, size2 - i));
        }
    }

    private static final class StringAsImmutableList extends ImmutableList<Character> {
        public final String string;

        public StringAsImmutableList(String str) {
            this.string = str;
        }

        @Override // com.google.common.collect.ImmutableList, java.util.List
        public int indexOf(Object obj) {
            if (obj instanceof Character) {
                return this.string.indexOf(((Character) obj).charValue());
            }
            return -1;
        }

        @Override // com.google.common.collect.ImmutableCollection
        public boolean isPartialView() {
            return false;
        }

        @Override // com.google.common.collect.ImmutableList, java.util.List
        public int lastIndexOf(Object obj) {
            if (obj instanceof Character) {
                return this.string.lastIndexOf(((Character) obj).charValue());
            }
            return -1;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public int size() {
            return this.string.length();
        }

        @Override // java.util.List
        public Character get(int i) {
            Preconditions.checkElementIndex(i, size(), FirebaseAnalytics.Param.INDEX);
            return Character.valueOf(this.string.charAt(i));
        }

        @Override // com.google.common.collect.ImmutableList, java.util.List
        public ImmutableList<Character> subList(int i, int i2) {
            Preconditions.checkPositionIndexes(i, i2, size());
            return Lists.charactersOf(this.string.substring(i, i2));
        }
    }

    private static class TransformingRandomAccessList<F, T> extends AbstractList<T> implements RandomAccess, Serializable {
        public static final long serialVersionUID = 0;
        public final List<F> fromList;
        public final Function<? super F, ? extends T> function;

        public TransformingRandomAccessList(List<F> list, Function<? super F, ? extends T> function) {
            if (list == null) {
                throw new NullPointerException();
            }
            this.fromList = list;
            if (function == null) {
                throw new NullPointerException();
            }
            this.function = function;
        }

        @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
        public void clear() {
            this.fromList.clear();
        }

        @Override // java.util.AbstractList, java.util.List
        public T get(int i) {
            return this.function.apply(this.fromList.get(i));
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public boolean isEmpty() {
            return this.fromList.isEmpty();
        }

        @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.List
        public Iterator<T> iterator() {
            return listIterator();
        }

        @Override // java.util.AbstractList, java.util.List
        public ListIterator<T> listIterator(int i) {
            return new TransformedListIterator<F, T>(this.fromList.listIterator(i)) { // from class: com.google.common.collect.Lists.TransformingRandomAccessList.1
                @Override // com.google.common.collect.TransformedIterator
                public T transform(F f2) {
                    return TransformingRandomAccessList.this.function.apply(f2);
                }
            };
        }

        @Override // java.util.AbstractList, java.util.List
        public T remove(int i) {
            return this.function.apply(this.fromList.remove(i));
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public int size() {
            return this.fromList.size();
        }
    }

    private static class TransformingSequentialList<F, T> extends AbstractSequentialList<T> implements Serializable {
        public static final long serialVersionUID = 0;
        public final List<F> fromList;
        public final Function<? super F, ? extends T> function;

        public TransformingSequentialList(List<F> list, Function<? super F, ? extends T> function) {
            if (list == null) {
                throw new NullPointerException();
            }
            this.fromList = list;
            if (function == null) {
                throw new NullPointerException();
            }
            this.function = function;
        }

        @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
        public void clear() {
            this.fromList.clear();
        }

        @Override // java.util.AbstractSequentialList, java.util.AbstractList, java.util.List
        public ListIterator<T> listIterator(int i) {
            return new TransformedListIterator<F, T>(this.fromList.listIterator(i)) { // from class: com.google.common.collect.Lists.TransformingSequentialList.1
                @Override // com.google.common.collect.TransformedIterator
                public T transform(F f2) {
                    return TransformingSequentialList.this.function.apply(f2);
                }
            };
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public int size() {
            return this.fromList.size();
        }
    }

    private static class TwoPlusArrayList<E> extends AbstractList<E> implements Serializable, RandomAccess {
        public static final long serialVersionUID = 0;
        public final E first;
        public final E[] rest;
        public final E second;

        public TwoPlusArrayList(E e2, E e3, E[] eArr) {
            this.first = e2;
            this.second = e3;
            if (eArr == null) {
                throw new NullPointerException();
            }
            this.rest = eArr;
        }

        @Override // java.util.AbstractList, java.util.List
        public E get(int i) {
            if (i == 0) {
                return this.first;
            }
            if (i == 1) {
                return this.second;
            }
            Preconditions.checkElementIndex(i, size(), FirebaseAnalytics.Param.INDEX);
            return this.rest[i - 2];
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public int size() {
            return IntMath.saturatedAdd(this.rest.length, 2);
        }
    }

    public static <E> boolean addAllImpl(List<E> list, int i, Iterable<? extends E> iterable) {
        ListIterator<E> listIterator = list.listIterator(i);
        Iterator<? extends E> it = iterable.iterator();
        boolean z = false;
        while (it.hasNext()) {
            listIterator.add(it.next());
            z = true;
        }
        return z;
    }

    public static <E> List<E> asList(E e2, E[] eArr) {
        return new OnePlusArrayList(e2, eArr);
    }

    public static <B> List<List<B>> cartesianProduct(List<? extends List<? extends B>> list) {
        return CartesianList.create(list);
    }

    public static <T> List<T> cast(Iterable<T> iterable) {
        return (List) iterable;
    }

    public static ImmutableList<Character> charactersOf(String str) {
        if (str != null) {
            return new StringAsImmutableList(str);
        }
        throw new NullPointerException();
    }

    @VisibleForTesting
    public static int computeArrayListCapacity(int i) {
        CollectPreconditions.checkNonnegative(i, "arraySize");
        return Ints.saturatedCast(((long) i) + 5 + ((long) (i / 10)));
    }

    public static boolean equalsImpl(List<?> list, Object obj) {
        if (list == null) {
            throw new NullPointerException();
        }
        if (obj == list) {
            return true;
        }
        if (!(obj instanceof List)) {
            return false;
        }
        List list2 = (List) obj;
        int size = list.size();
        if (size != list2.size()) {
            return false;
        }
        if (!(list instanceof RandomAccess) || !(list2 instanceof RandomAccess)) {
            return Iterators.elementsEqual(list.iterator(), list2.iterator());
        }
        for (int i = 0; i < size; i++) {
            if (!Objects.equal(list.get(i), list2.get(i))) {
                return false;
            }
        }
        return true;
    }

    public static int hashCodeImpl(List<?> list) {
        Iterator<?> it = list.iterator();
        int i = 1;
        while (it.hasNext()) {
            Object next = it.next();
            i = ~(~((i * 31) + (next == null ? 0 : next.hashCode())));
        }
        return i;
    }

    public static int indexOfImpl(List<?> list, Object obj) {
        if (list instanceof RandomAccess) {
            return indexOfRandomAccess(list, obj);
        }
        ListIterator<?> listIterator = list.listIterator();
        while (listIterator.hasNext()) {
            if (Objects.equal(obj, listIterator.next())) {
                return listIterator.previousIndex();
            }
        }
        return -1;
    }

    public static int indexOfRandomAccess(List<?> list, Object obj) {
        int size = list.size();
        int i = 0;
        if (obj == null) {
            while (i < size) {
                if (list.get(i) == null) {
                    return i;
                }
                i++;
            }
            return -1;
        }
        while (i < size) {
            if (obj.equals(list.get(i))) {
                return i;
            }
            i++;
        }
        return -1;
    }

    public static int lastIndexOfImpl(List<?> list, Object obj) {
        if (list instanceof RandomAccess) {
            return lastIndexOfRandomAccess(list, obj);
        }
        ListIterator<?> listIterator = list.listIterator(list.size());
        while (listIterator.hasPrevious()) {
            if (Objects.equal(obj, listIterator.previous())) {
                return listIterator.nextIndex();
            }
        }
        return -1;
    }

    public static int lastIndexOfRandomAccess(List<?> list, Object obj) {
        if (obj == null) {
            for (int size = list.size() - 1; size >= 0; size--) {
                if (list.get(size) == null) {
                    return size;
                }
            }
            return -1;
        }
        for (int size2 = list.size() - 1; size2 >= 0; size2--) {
            if (obj.equals(list.get(size2))) {
                return size2;
            }
        }
        return -1;
    }

    public static <E> ListIterator<E> listIteratorImpl(List<E> list, int i) {
        return new AbstractListWrapper(list).listIterator(i);
    }

    @GwtCompatible(serializable = true)
    public static <E> ArrayList<E> newArrayList() {
        return new ArrayList<>();
    }

    @GwtCompatible(serializable = true)
    public static <E> ArrayList<E> newArrayListWithCapacity(int i) {
        CollectPreconditions.checkNonnegative(i, "initialArraySize");
        return new ArrayList<>(i);
    }

    @GwtCompatible(serializable = true)
    public static <E> ArrayList<E> newArrayListWithExpectedSize(int i) {
        return new ArrayList<>(computeArrayListCapacity(i));
    }

    @GwtIncompatible
    public static <E> CopyOnWriteArrayList<E> newCopyOnWriteArrayList() {
        return new CopyOnWriteArrayList<>();
    }

    @GwtCompatible(serializable = true)
    public static <E> LinkedList<E> newLinkedList() {
        return new LinkedList<>();
    }

    public static <T> List<List<T>> partition(List<T> list, int i) {
        if (list == null) {
            throw new NullPointerException();
        }
        Preconditions.checkArgument(i > 0);
        return list instanceof RandomAccess ? new RandomAccessPartition(list, i) : new Partition(list, i);
    }

    public static <T> List<T> reverse(List<T> list) {
        if (list instanceof ImmutableList) {
            return ((ImmutableList) list).reverse();
        }
        if (list instanceof ReverseList) {
            return ((ReverseList) list).getForwardList();
        }
        return list instanceof RandomAccess ? new RandomAccessReverseList(list) : new ReverseList(list);
    }

    public static <E> List<E> subListImpl(List<E> list, int i, int i2) {
        return (list instanceof RandomAccess ? new RandomAccessListWrapper<E>(list) { // from class: com.google.common.collect.Lists.1
            public static final long serialVersionUID = 0;

            @Override // java.util.AbstractList, java.util.List
            public ListIterator<E> listIterator(int i3) {
                return this.backingList.listIterator(i3);
            }
        } : new AbstractListWrapper<E>(list) { // from class: com.google.common.collect.Lists.2
            public static final long serialVersionUID = 0;

            @Override // java.util.AbstractList, java.util.List
            public ListIterator<E> listIterator(int i3) {
                return this.backingList.listIterator(i3);
            }
        }).subList(i, i2);
    }

    public static <F, T> List<T> transform(List<F> list, Function<? super F, ? extends T> function) {
        return list instanceof RandomAccess ? new TransformingRandomAccessList(list, function) : new TransformingSequentialList(list, function);
    }

    public static <E> List<E> asList(E e2, E e3, E[] eArr) {
        return new TwoPlusArrayList(e2, e3, eArr);
    }

    @SafeVarargs
    public static <B> List<List<B>> cartesianProduct(List<? extends B>... listArr) {
        return CartesianList.create(Arrays.asList(listArr));
    }

    @GwtCompatible(serializable = true)
    public static <E> ArrayList<E> newArrayList(Iterator<? extends E> it) {
        ArrayList<E> arrayList = new ArrayList<>();
        Iterators.addAll(arrayList, it);
        return arrayList;
    }

    @GwtIncompatible
    public static <E> CopyOnWriteArrayList<E> newCopyOnWriteArrayList(Iterable<? extends E> iterable) {
        return new CopyOnWriteArrayList<>(iterable instanceof Collection ? (Collection) iterable : newArrayList(iterable));
    }

    @GwtCompatible(serializable = true)
    public static <E> LinkedList<E> newLinkedList(Iterable<? extends E> iterable) {
        LinkedList<E> linkedList = new LinkedList<>();
        Iterables.addAll(linkedList, iterable);
        return linkedList;
    }

    @Beta
    public static List<Character> charactersOf(CharSequence charSequence) {
        if (charSequence != null) {
            return new CharSequenceAsList(charSequence);
        }
        throw new NullPointerException();
    }

    @SafeVarargs
    @GwtCompatible(serializable = true)
    public static <E> ArrayList<E> newArrayList(E... eArr) {
        if (eArr != null) {
            ArrayList<E> arrayList = new ArrayList<>(computeArrayListCapacity(eArr.length));
            Collections.addAll(arrayList, eArr);
            return arrayList;
        }
        throw new NullPointerException();
    }

    @GwtCompatible(serializable = true)
    public static <E> ArrayList<E> newArrayList(Iterable<? extends E> iterable) {
        if (iterable != null) {
            if (iterable instanceof Collection) {
                return new ArrayList<>((Collection) iterable);
            }
            return newArrayList(iterable.iterator());
        }
        throw new NullPointerException();
    }
}
