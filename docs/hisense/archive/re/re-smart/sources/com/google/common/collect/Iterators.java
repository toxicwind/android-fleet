package com.google.common.collect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Absent;
import com.google.common.base.Function;
import com.google.common.base.Objects;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.primitives.Ints;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Deque;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;
import java.util.Queue;

/* JADX INFO: loaded from: classes.dex */
@GwtCompatible(emulated = true)
public final class Iterators {

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: renamed from: com.google.common.collect.Iterators$2, reason: invalid class name */
    public class AnonymousClass2<T> implements Iterator<T> {
        public Iterator<T> iterator = EmptyModifiableIterator.INSTANCE;
        public final /* synthetic */ Iterable val$iterable;

        public AnonymousClass2(Iterable iterable) {
            this.val$iterable = iterable;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.iterator.hasNext() || this.val$iterable.iterator().hasNext();
        }

        @Override // java.util.Iterator
        public T next() {
            if (!this.iterator.hasNext()) {
                this.iterator = this.val$iterable.iterator();
                if (!this.iterator.hasNext()) {
                    throw new NoSuchElementException();
                }
            }
            return this.iterator.next();
        }

        @Override // java.util.Iterator
        public void remove() {
            this.iterator.remove();
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: renamed from: com.google.common.collect.Iterators$3, reason: invalid class name */
    public class AnonymousClass3<T> extends UnmodifiableIterator<T> {
        public int index = 0;
        public final /* synthetic */ Object[] val$elements;

        public AnonymousClass3(Object[] objArr) {
            this.val$elements = objArr;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.index < this.val$elements.length;
        }

        @Override // java.util.Iterator
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Object[] objArr = this.val$elements;
            int i = this.index;
            T t = (T) objArr[i];
            objArr[i] = null;
            this.index = i + 1;
            return t;
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: renamed from: com.google.common.collect.Iterators$5, reason: invalid class name */
    public class AnonymousClass5<T> extends AbstractIterator<T> {
        public final /* synthetic */ Predicate val$retainIfTrue;
        public final /* synthetic */ Iterator val$unfiltered;

        public AnonymousClass5(Iterator it, Predicate predicate) {
            this.val$unfiltered = it;
            this.val$retainIfTrue = predicate;
        }

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
        @Override // com.google.common.collect.AbstractIterator
        public T computeNext() {
            while (this.val$unfiltered.hasNext()) {
                T t = (T) this.val$unfiltered.next();
                if (this.val$retainIfTrue.apply(t)) {
                    return t;
                }
            }
            return endOfData();
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T, F] */
    /* JADX INFO: renamed from: com.google.common.collect.Iterators$6, reason: invalid class name */
    public class AnonymousClass6<F, T> extends TransformedIterator<F, T> {
        public final /* synthetic */ Function val$function;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass6(Iterator it, Function function) {
            super(it);
            this.val$function = function;
        }

        @Override // com.google.common.collect.TransformedIterator
        public T transform(F f2) {
            return (T) this.val$function.apply(f2);
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: renamed from: com.google.common.collect.Iterators$9, reason: invalid class name */
    public class AnonymousClass9<T> extends UnmodifiableIterator<T> {
        public boolean done;
        public final /* synthetic */ Object val$value;

        public AnonymousClass9(Object obj) {
            this.val$value = obj;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return !this.done;
        }

        @Override // java.util.Iterator
        public T next() {
            if (this.done) {
                throw new NoSuchElementException();
            }
            this.done = true;
            return (T) this.val$value;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    static final class ArrayItr<T> extends AbstractIndexedListIterator<T> {
        public static final UnmodifiableListIterator<Object> EMPTY = new ArrayItr(new Object[0], 0, 0, 0);
        public final T[] array;
        public final int offset;

        public ArrayItr(T[] tArr, int i, int i2, int i3) {
            super(i2, i3);
            this.array = tArr;
            this.offset = i;
        }

        @Override // com.google.common.collect.AbstractIndexedListIterator
        public T get(int i) {
            return this.array[this.offset + i];
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX INFO: Access modifiers changed from: private */
    public static class ConcatenatedIterator<T> implements Iterator<T> {
        public Iterator<? extends T> iterator = ArrayItr.EMPTY;
        public Deque<Iterator<? extends Iterator<? extends T>>> metaIterators;
        public Iterator<? extends T> toRemove;
        public Iterator<? extends Iterator<? extends T>> topMetaIterator;

        public ConcatenatedIterator(Iterator<? extends Iterator<? extends T>> it) {
            if (it == null) {
                throw new NullPointerException();
            }
            this.topMetaIterator = it;
        }

        private Iterator<? extends Iterator<? extends T>> getTopMetaIterator() {
            while (true) {
                Iterator<? extends Iterator<? extends T>> it = this.topMetaIterator;
                if (it != null && it.hasNext()) {
                    return this.topMetaIterator;
                }
                Deque<Iterator<? extends Iterator<? extends T>>> deque = this.metaIterators;
                if (deque == null || deque.isEmpty()) {
                    return null;
                }
                this.topMetaIterator = this.metaIterators.removeFirst();
            }
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            while (true) {
                Iterator<? extends T> it = this.iterator;
                Preconditions.checkNotNull(it);
                if (it.hasNext()) {
                    return true;
                }
                this.topMetaIterator = getTopMetaIterator();
                Iterator<? extends Iterator<? extends T>> it2 = this.topMetaIterator;
                if (it2 == null) {
                    return false;
                }
                this.iterator = it2.next();
                Iterator<? extends T> it3 = this.iterator;
                if (it3 instanceof ConcatenatedIterator) {
                    ConcatenatedIterator concatenatedIterator = (ConcatenatedIterator) it3;
                    this.iterator = concatenatedIterator.iterator;
                    if (this.metaIterators == null) {
                        this.metaIterators = new ArrayDeque();
                    }
                    this.metaIterators.addFirst(this.topMetaIterator);
                    if (concatenatedIterator.metaIterators != null) {
                        while (!concatenatedIterator.metaIterators.isEmpty()) {
                            this.metaIterators.addFirst(concatenatedIterator.metaIterators.removeLast());
                        }
                    }
                    this.topMetaIterator = concatenatedIterator.topMetaIterator;
                }
            }
        }

        @Override // java.util.Iterator
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Iterator<? extends T> it = this.iterator;
            this.toRemove = it;
            return it.next();
        }

        @Override // java.util.Iterator
        public void remove() {
            Preconditions.checkState(this.toRemove != null, "no calls to next() since the last call to remove()");
            this.toRemove.remove();
            this.toRemove = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    enum EmptyModifiableIterator implements Iterator<Object> {
        INSTANCE;

        @Override // java.util.Iterator
        public boolean hasNext() {
            return false;
        }

        @Override // java.util.Iterator
        public Object next() {
            throw new NoSuchElementException();
        }

        @Override // java.util.Iterator
        public void remove() {
            Preconditions.checkState(false, "no calls to next() since the last call to remove()");
        }
    }

    private static class MergingIterator<T> extends UnmodifiableIterator<T> {
        public final Queue<PeekingIterator<T>> queue;

        public MergingIterator(Iterable<? extends Iterator<? extends T>> iterable, final Comparator<? super T> comparator) {
            this.queue = new PriorityQueue(2, new Comparator<PeekingIterator<T>>(this) { // from class: com.google.common.collect.Iterators.MergingIterator.1
                @Override // java.util.Comparator
                public int compare(PeekingIterator<T> peekingIterator, PeekingIterator<T> peekingIterator2) {
                    return comparator.compare(peekingIterator.peek(), peekingIterator2.peek());
                }
            });
            for (Iterator<? extends T> it : iterable) {
                if (it.hasNext()) {
                    this.queue.add(Iterators.peekingIterator(it));
                }
            }
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return !this.queue.isEmpty();
        }

        @Override // java.util.Iterator
        public T next() {
            PeekingIterator<T> peekingIteratorRemove = this.queue.remove();
            T next = peekingIteratorRemove.next();
            if (peekingIteratorRemove.hasNext()) {
                this.queue.add(peekingIteratorRemove);
            }
            return next;
        }
    }

    private static class PeekingImpl<E> implements PeekingIterator<E> {
        public boolean hasPeeked;
        public final Iterator<? extends E> iterator;
        public E peekedElement;

        public PeekingImpl(Iterator<? extends E> it) {
            if (it == null) {
                throw new NullPointerException();
            }
            this.iterator = it;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.hasPeeked || this.iterator.hasNext();
        }

        @Override // com.google.common.collect.PeekingIterator, java.util.Iterator
        public E next() {
            if (!this.hasPeeked) {
                return this.iterator.next();
            }
            E e2 = this.peekedElement;
            this.hasPeeked = false;
            this.peekedElement = null;
            return e2;
        }

        @Override // com.google.common.collect.PeekingIterator
        public E peek() {
            if (!this.hasPeeked) {
                this.peekedElement = this.iterator.next();
                this.hasPeeked = true;
            }
            return this.peekedElement;
        }

        @Override // com.google.common.collect.PeekingIterator, java.util.Iterator
        public void remove() {
            Preconditions.checkState(!this.hasPeeked, "Can't remove after you've peeked at next");
            this.iterator.remove();
        }
    }

    @CanIgnoreReturnValue
    public static <T> boolean addAll(Collection<T> collection, Iterator<? extends T> it) {
        if (collection == null) {
            throw new NullPointerException();
        }
        if (it == null) {
            throw new NullPointerException();
        }
        boolean zAdd = false;
        while (it.hasNext()) {
            zAdd |= collection.add(it.next());
        }
        return zAdd;
    }

    @CanIgnoreReturnValue
    public static int advance(Iterator<?> it, int i) {
        if (it == null) {
            throw new NullPointerException();
        }
        int i2 = 0;
        Preconditions.checkArgument(i >= 0, "numberToAdvance must be nonnegative");
        while (i2 < i && it.hasNext()) {
            it.next();
            i2++;
        }
        return i2;
    }

    public static <T> boolean all(Iterator<T> it, Predicate<? super T> predicate) {
        if (predicate == null) {
            throw new NullPointerException();
        }
        while (it.hasNext()) {
            if (!predicate.apply(it.next())) {
                return false;
            }
        }
        return true;
    }

    public static <T> boolean any(Iterator<T> it, Predicate<? super T> predicate) {
        return indexOf(it, predicate) != -1;
    }

    public static <T> Enumeration<T> asEnumeration(final Iterator<T> it) {
        if (it != null) {
            return new Enumeration<T>() { // from class: com.google.common.collect.Iterators.11
                @Override // java.util.Enumeration
                public boolean hasMoreElements() {
                    return it.hasNext();
                }

                @Override // java.util.Enumeration
                public T nextElement() {
                    return (T) it.next();
                }
            };
        }
        throw new NullPointerException();
    }

    public static <T> ListIterator<T> cast(Iterator<T> it) {
        return (ListIterator) it;
    }

    public static void checkNonnegative(int i) {
        if (i >= 0) {
            return;
        }
        StringBuilder sb = new StringBuilder(43);
        sb.append("position (");
        sb.append(i);
        sb.append(") must not be negative");
        throw new IndexOutOfBoundsException(sb.toString());
    }

    public static void clear(Iterator<?> it) {
        if (it == null) {
            throw new NullPointerException();
        }
        while (it.hasNext()) {
            it.next();
            it.remove();
        }
    }

    public static <T> Iterator<T> concat(Iterator<? extends T> it, Iterator<? extends T> it2) {
        if (it == null) {
            throw new NullPointerException();
        }
        if (it2 != null) {
            return new ConcatenatedIterator(new AnonymousClass3(new Iterator[]{it, it2}));
        }
        throw new NullPointerException();
    }

    public static <T> Iterator<T> concatNoDefensiveCopy(Iterator<? extends T>... itArr) {
        if (itArr == null) {
            throw new NullPointerException();
        }
        for (Iterator<? extends T> it : itArr) {
            Preconditions.checkNotNull(it);
        }
        return new ConcatenatedIterator(new AnonymousClass3(itArr));
    }

    public static <T> Iterator<T> consumingForArray(T... tArr) {
        return new AnonymousClass3(tArr);
    }

    public static <T> Iterator<T> consumingIterator(final Iterator<T> it) {
        if (it != null) {
            return new UnmodifiableIterator<T>() { // from class: com.google.common.collect.Iterators.8
                @Override // java.util.Iterator
                public boolean hasNext() {
                    return it.hasNext();
                }

                @Override // java.util.Iterator
                public T next() {
                    T t = (T) it.next();
                    it.remove();
                    return t;
                }

                public String toString() {
                    return "Iterators.consumingIterator(...)";
                }
            };
        }
        throw new NullPointerException();
    }

    public static boolean contains(Iterator<?> it, Object obj) {
        if (obj == null) {
            while (it.hasNext()) {
                if (it.next() == null) {
                    return true;
                }
            }
            return false;
        }
        while (it.hasNext()) {
            if (obj.equals(it.next())) {
                return true;
            }
        }
        return false;
    }

    public static <T> Iterator<T> cycle(Iterable<T> iterable) {
        if (iterable != null) {
            return new AnonymousClass2(iterable);
        }
        throw new NullPointerException();
    }

    public static boolean elementsEqual(Iterator<?> it, Iterator<?> it2) {
        while (it.hasNext()) {
            if (!it2.hasNext() || !Objects.equal(it.next(), it2.next())) {
                return false;
            }
        }
        return !it2.hasNext();
    }

    public static <T> UnmodifiableIterator<T> emptyIterator() {
        return ArrayItr.EMPTY;
    }

    public static <T> UnmodifiableListIterator<T> emptyListIterator() {
        return (UnmodifiableListIterator<T>) ArrayItr.EMPTY;
    }

    public static <T> Iterator<T> emptyModifiableIterator() {
        return EmptyModifiableIterator.INSTANCE;
    }

    public static <T> UnmodifiableIterator<T> filter(Iterator<T> it, Predicate<? super T> predicate) {
        if (it == null) {
            throw new NullPointerException();
        }
        if (predicate != null) {
            return new AnonymousClass5(it, predicate);
        }
        throw new NullPointerException();
    }

    public static <T> T find(Iterator<T> it, Predicate<? super T> predicate) {
        if (it == null) {
            throw new NullPointerException();
        }
        if (predicate == null) {
            throw new NullPointerException();
        }
        while (it.hasNext()) {
            T next = it.next();
            if (predicate.apply(next)) {
                return next;
            }
        }
        throw new NoSuchElementException();
    }

    @SafeVarargs
    public static <T> UnmodifiableIterator<T> forArray(T... tArr) {
        return forArray(tArr, 0, tArr.length, 0);
    }

    public static <T> UnmodifiableIterator<T> forEnumeration(final Enumeration<T> enumeration) {
        if (enumeration != null) {
            return new UnmodifiableIterator<T>() { // from class: com.google.common.collect.Iterators.10
                @Override // java.util.Iterator
                public boolean hasNext() {
                    return enumeration.hasMoreElements();
                }

                @Override // java.util.Iterator
                public T next() {
                    return (T) enumeration.nextElement();
                }
            };
        }
        throw new NullPointerException();
    }

    public static int frequency(Iterator<?> it, Object obj) {
        int i = 0;
        while (contains(it, obj)) {
            i++;
        }
        return i;
    }

    public static <T> T get(Iterator<T> it, int i) {
        checkNonnegative(i);
        int iAdvance = advance(it, i);
        if (it.hasNext()) {
            return it.next();
        }
        StringBuilder sb = new StringBuilder(91);
        sb.append("position (");
        sb.append(i);
        sb.append(") must be less than the number of elements that remained (");
        sb.append(iAdvance);
        sb.append(")");
        throw new IndexOutOfBoundsException(sb.toString());
    }

    public static <T> T getLast(Iterator<T> it) {
        T next;
        do {
            next = it.next();
        } while (it.hasNext());
        return next;
    }

    public static <T> T getNext(Iterator<? extends T> it, T t) {
        return it.hasNext() ? it.next() : t;
    }

    public static <T> T getOnlyElement(Iterator<T> it) {
        T next = it.next();
        if (!it.hasNext()) {
            return next;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("expected one element but was: <");
        sb.append(next);
        for (int i = 0; i < 4 && it.hasNext(); i++) {
            sb.append(", ");
            sb.append(it.next());
        }
        if (it.hasNext()) {
            sb.append(", ...");
        }
        sb.append('>');
        throw new IllegalArgumentException(sb.toString());
    }

    public static <T> int indexOf(Iterator<T> it, Predicate<? super T> predicate) {
        Preconditions.checkNotNull(predicate, "predicate");
        int i = 0;
        while (it.hasNext()) {
            if (predicate.apply(it.next())) {
                return i;
            }
            i++;
        }
        return -1;
    }

    public static <T> Iterator<T> limit(final Iterator<T> it, final int i) {
        if (it == null) {
            throw new NullPointerException();
        }
        Preconditions.checkArgument(i >= 0, "limit is negative");
        return new Iterator<T>() { // from class: com.google.common.collect.Iterators.7
            public int count;

            @Override // java.util.Iterator
            public boolean hasNext() {
                return this.count < i && it.hasNext();
            }

            @Override // java.util.Iterator
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                this.count++;
                return (T) it.next();
            }

            @Override // java.util.Iterator
            public void remove() {
                it.remove();
            }
        };
    }

    @Beta
    public static <T> UnmodifiableIterator<T> mergeSorted(Iterable<? extends Iterator<? extends T>> iterable, Comparator<? super T> comparator) {
        Preconditions.checkNotNull(iterable, "iterators");
        Preconditions.checkNotNull(comparator, "comparator");
        return new MergingIterator(iterable, comparator);
    }

    public static <T> UnmodifiableIterator<List<T>> paddedPartition(Iterator<T> it, int i) {
        return partitionImpl(it, i, true);
    }

    public static <T> UnmodifiableIterator<List<T>> partition(Iterator<T> it, int i) {
        return partitionImpl(it, i, false);
    }

    public static <T> UnmodifiableIterator<List<T>> partitionImpl(final Iterator<T> it, final int i, final boolean z) {
        if (it == null) {
            throw new NullPointerException();
        }
        Preconditions.checkArgument(i > 0);
        return new UnmodifiableIterator<List<T>>() { // from class: com.google.common.collect.Iterators.4
            @Override // java.util.Iterator
            public boolean hasNext() {
                return it.hasNext();
            }

            @Override // java.util.Iterator
            public List<T> next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                Object[] objArr = new Object[i];
                int i2 = 0;
                while (i2 < i && it.hasNext()) {
                    objArr[i2] = it.next();
                    i2++;
                }
                for (int i3 = i2; i3 < i; i3++) {
                    objArr[i3] = null;
                }
                List<T> listUnmodifiableList = Collections.unmodifiableList(Arrays.asList(objArr));
                return (z || i2 == i) ? listUnmodifiableList : listUnmodifiableList.subList(0, i2);
            }
        };
    }

    @Deprecated
    public static <T> PeekingIterator<T> peekingIterator(PeekingIterator<T> peekingIterator) {
        if (peekingIterator != null) {
            return peekingIterator;
        }
        throw new NullPointerException();
    }

    public static <T> T pollNext(Iterator<T> it) {
        if (!it.hasNext()) {
            return null;
        }
        T next = it.next();
        it.remove();
        return next;
    }

    @CanIgnoreReturnValue
    public static boolean removeAll(Iterator<?> it, Collection<?> collection) {
        if (collection == null) {
            throw new NullPointerException();
        }
        boolean z = false;
        while (it.hasNext()) {
            if (collection.contains(it.next())) {
                it.remove();
                z = true;
            }
        }
        return z;
    }

    @CanIgnoreReturnValue
    public static <T> boolean removeIf(Iterator<T> it, Predicate<? super T> predicate) {
        if (predicate == null) {
            throw new NullPointerException();
        }
        boolean z = false;
        while (it.hasNext()) {
            if (predicate.apply(it.next())) {
                it.remove();
                z = true;
            }
        }
        return z;
    }

    @CanIgnoreReturnValue
    public static boolean retainAll(Iterator<?> it, Collection<?> collection) {
        if (collection == null) {
            throw new NullPointerException();
        }
        boolean z = false;
        while (it.hasNext()) {
            if (!collection.contains(it.next())) {
                it.remove();
                z = true;
            }
        }
        return z;
    }

    public static <T> UnmodifiableIterator<T> singletonIterator(T t) {
        return new AnonymousClass9(t);
    }

    public static int size(Iterator<?> it) {
        long j = 0;
        while (it.hasNext()) {
            it.next();
            j++;
        }
        return Ints.saturatedCast(j);
    }

    @GwtIncompatible
    public static <T> T[] toArray(Iterator<? extends T> it, Class<T> cls) {
        return (T[]) Iterables.toArray(Lists.newArrayList(it), cls);
    }

    public static String toString(Iterator<?> it) {
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        boolean z = true;
        while (it.hasNext()) {
            if (!z) {
                sb.append(", ");
            }
            z = false;
            sb.append(it.next());
        }
        sb.append(']');
        return sb.toString();
    }

    public static <F, T> Iterator<T> transform(Iterator<F> it, Function<? super F, ? extends T> function) {
        if (function != null) {
            return new AnonymousClass6(it, function);
        }
        throw new NullPointerException();
    }

    public static <T> Optional<T> tryFind(Iterator<T> it, Predicate<? super T> predicate) {
        if (it == null) {
            throw new NullPointerException();
        }
        if (predicate == null) {
            throw new NullPointerException();
        }
        while (it.hasNext()) {
            T next = it.next();
            if (predicate.apply(next)) {
                return Optional.of(next);
            }
        }
        return Absent.INSTANCE;
    }

    public static <T> UnmodifiableIterator<T> unmodifiableIterator(final Iterator<? extends T> it) {
        if (it != null) {
            return it instanceof UnmodifiableIterator ? (UnmodifiableIterator) it : new UnmodifiableIterator<T>() { // from class: com.google.common.collect.Iterators.1
                @Override // java.util.Iterator
                public boolean hasNext() {
                    return it.hasNext();
                }

                @Override // java.util.Iterator
                public T next() {
                    return (T) it.next();
                }
            };
        }
        throw new NullPointerException();
    }

    public static <T> UnmodifiableListIterator<T> forArray(T[] tArr, int i, int i2, int i3) {
        Preconditions.checkArgument(i2 >= 0);
        Preconditions.checkPositionIndexes(i, i + i2, tArr.length);
        Preconditions.checkPositionIndex(i3, i2, FirebaseAnalytics.Param.INDEX);
        return i2 == 0 ? (UnmodifiableListIterator<T>) ArrayItr.EMPTY : new ArrayItr(tArr, i, i2, i3);
    }

    public static <T> PeekingIterator<T> peekingIterator(Iterator<? extends T> it) {
        return it instanceof PeekingImpl ? (PeekingImpl) it : new PeekingImpl(it);
    }

    @SafeVarargs
    public static <T> Iterator<T> cycle(T... tArr) {
        return new AnonymousClass2(Lists.newArrayList(tArr));
    }

    public static <T> T getLast(Iterator<? extends T> it, T t) {
        if (it.hasNext()) {
            do {
                t = it.next();
            } while (it.hasNext());
        }
        return t;
    }

    @GwtIncompatible
    public static <T> UnmodifiableIterator<T> filter(Iterator<?> it, Class<T> cls) {
        Predicate<Object> predicateInstanceOf = Predicates.instanceOf(cls);
        if (it != null) {
            return new AnonymousClass5(it, predicateInstanceOf);
        }
        throw new NullPointerException();
    }

    @Deprecated
    public static <T> UnmodifiableIterator<T> unmodifiableIterator(UnmodifiableIterator<T> unmodifiableIterator) {
        if (unmodifiableIterator != null) {
            return unmodifiableIterator;
        }
        throw new NullPointerException();
    }

    public static <T> Iterator<T> concat(Iterator<? extends T> it, Iterator<? extends T> it2, Iterator<? extends T> it3) {
        if (it == null) {
            throw new NullPointerException();
        }
        if (it2 == null) {
            throw new NullPointerException();
        }
        if (it3 != null) {
            return new ConcatenatedIterator(new AnonymousClass3(new Iterator[]{it, it2, it3}));
        }
        throw new NullPointerException();
    }

    public static <T> T get(Iterator<? extends T> it, int i, T t) {
        checkNonnegative(i);
        advance(it, i);
        return it.hasNext() ? it.next() : t;
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [T, java.lang.Object] */
    public static <T> T find(Iterator<? extends T> it, Predicate<? super T> predicate, T t) {
        if (it == null) {
            throw new NullPointerException();
        }
        if (predicate != null) {
            while (it.hasNext()) {
                T next = it.next();
                if (predicate.apply(next)) {
                    return next;
                }
            }
            return t;
        }
        throw new NullPointerException();
    }

    public static <T> T getOnlyElement(Iterator<? extends T> it, T t) {
        return it.hasNext() ? (T) getOnlyElement(it) : t;
    }

    public static <T> Iterator<T> concat(Iterator<? extends T> it, Iterator<? extends T> it2, Iterator<? extends T> it3, Iterator<? extends T> it4) {
        if (it == null) {
            throw new NullPointerException();
        }
        if (it2 == null) {
            throw new NullPointerException();
        }
        if (it3 == null) {
            throw new NullPointerException();
        }
        if (it4 != null) {
            return new ConcatenatedIterator(new AnonymousClass3(new Iterator[]{it, it2, it3, it4}));
        }
        throw new NullPointerException();
    }

    public static <T> Iterator<T> concat(Iterator<? extends T>... itArr) {
        return concatNoDefensiveCopy((Iterator[]) Arrays.copyOf(itArr, itArr.length));
    }

    public static <T> Iterator<T> concat(Iterator<? extends Iterator<? extends T>> it) {
        return new ConcatenatedIterator(it);
    }
}
