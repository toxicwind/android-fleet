package com.google.common.collect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.math.IntMath;
import com.google.common.primitives.Ints;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
@GwtCompatible
public final class Multisets {

    /* JADX INFO: Add missing generic type declarations: [E] */
    /* JADX INFO: renamed from: com.google.common.collect.Multisets$5, reason: invalid class name */
    public class AnonymousClass5<E> extends TransformedIterator<Multiset.Entry<E>, E> {
        public AnonymousClass5(Iterator it) {
            super(it);
        }

        @Override // com.google.common.collect.TransformedIterator
        public E transform(Multiset.Entry<E> entry) {
            return entry.getElement();
        }
    }

    static abstract class AbstractEntry<E> implements Multiset.Entry<E> {
        @Override // com.google.common.collect.Multiset.Entry
        public boolean equals(Object obj) {
            if (!(obj instanceof Multiset.Entry)) {
                return false;
            }
            Multiset.Entry entry = (Multiset.Entry) obj;
            return getCount() == entry.getCount() && Objects.equal(getElement(), entry.getElement());
        }

        @Override // com.google.common.collect.Multiset.Entry
        public int hashCode() {
            E element = getElement();
            return (element == null ? 0 : element.hashCode()) ^ getCount();
        }

        @Override // com.google.common.collect.Multiset.Entry
        public String toString() {
            String strValueOf = String.valueOf(getElement());
            int count = getCount();
            if (count == 1) {
                return strValueOf;
            }
            StringBuilder sb = new StringBuilder(strValueOf.length() + 14);
            sb.append(strValueOf);
            sb.append(" x ");
            sb.append(count);
            return sb.toString();
        }
    }

    private static final class DecreasingCount implements Comparator<Multiset.Entry<?>> {
        public static final DecreasingCount INSTANCE = new DecreasingCount();

        @Override // java.util.Comparator
        public int compare(Multiset.Entry<?> entry, Multiset.Entry<?> entry2) {
            return entry2.getCount() - entry.getCount();
        }
    }

    static abstract class ElementSet<E> extends Sets.ImprovedAbstractSet<E> {
        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public void clear() {
            multiset().clear();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object obj) {
            return multiset().contains(obj);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean containsAll(Collection<?> collection) {
            return multiset().containsAll(collection);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean isEmpty() {
            return multiset().isEmpty();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public abstract Iterator<E> iterator();

        public abstract Multiset<E> multiset();

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(Object obj) {
            return multiset().remove(obj, Integer.MAX_VALUE) > 0;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return multiset().entrySet().size();
        }
    }

    static abstract class EntrySet<E> extends Sets.ImprovedAbstractSet<Multiset.Entry<E>> {
        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public void clear() {
            multiset().clear();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object obj) {
            if (!(obj instanceof Multiset.Entry)) {
                return false;
            }
            Multiset.Entry entry = (Multiset.Entry) obj;
            return entry.getCount() > 0 && multiset().count(entry.getElement()) == entry.getCount();
        }

        public abstract Multiset<E> multiset();

        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(Object obj) {
            if (obj instanceof Multiset.Entry) {
                Multiset.Entry entry = (Multiset.Entry) obj;
                Object element = entry.getElement();
                int count = entry.getCount();
                if (count != 0) {
                    return multiset().setCount(element, count, 0);
                }
            }
            return false;
        }
    }

    private static final class FilteredMultiset<E> extends ViewMultiset<E> {
        public final Predicate<? super E> predicate;
        public final Multiset<E> unfiltered;

        public FilteredMultiset(Multiset<E> multiset, Predicate<? super E> predicate) {
            super(null);
            if (multiset == null) {
                throw new NullPointerException();
            }
            this.unfiltered = multiset;
            if (predicate == null) {
                throw new NullPointerException();
            }
            this.predicate = predicate;
        }

        @Override // com.google.common.collect.AbstractMultiset, com.google.common.collect.Multiset
        public int add(E e2, int i) {
            Preconditions.checkArgument(this.predicate.apply(e2), "Element %s does not match predicate %s", e2, this.predicate);
            return this.unfiltered.add(e2, i);
        }

        @Override // com.google.common.collect.Multiset
        public int count(Object obj) {
            int iCount = this.unfiltered.count(obj);
            if (iCount <= 0 || !this.predicate.apply(obj)) {
                return 0;
            }
            return iCount;
        }

        @Override // com.google.common.collect.AbstractMultiset
        public Set<E> createElementSet() {
            return Sets.filter(this.unfiltered.elementSet(), this.predicate);
        }

        @Override // com.google.common.collect.AbstractMultiset
        public Set<Multiset.Entry<E>> createEntrySet() {
            return Sets.filter(this.unfiltered.entrySet(), new Predicate<Multiset.Entry<E>>() { // from class: com.google.common.collect.Multisets.FilteredMultiset.1
                @Override // com.google.common.base.Predicate
                public boolean apply(Multiset.Entry<E> entry) {
                    return FilteredMultiset.this.predicate.apply(entry.getElement());
                }
            });
        }

        @Override // com.google.common.collect.AbstractMultiset
        public Iterator<E> elementIterator() {
            throw new AssertionError("should never be called");
        }

        @Override // com.google.common.collect.AbstractMultiset
        public Iterator<Multiset.Entry<E>> entryIterator() {
            throw new AssertionError("should never be called");
        }

        @Override // com.google.common.collect.AbstractMultiset, com.google.common.collect.Multiset
        public int remove(Object obj, int i) {
            CollectPreconditions.checkNonnegative(i, "occurrences");
            if (i == 0) {
                return count(obj);
            }
            if (contains(obj)) {
                return this.unfiltered.remove(obj, i);
            }
            return 0;
        }

        @Override // com.google.common.collect.Multisets.ViewMultiset, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, com.google.common.collect.Multiset
        public UnmodifiableIterator<E> iterator() {
            return Iterators.filter(this.unfiltered.iterator(), this.predicate);
        }
    }

    static class ImmutableEntry<E> extends AbstractEntry<E> implements Serializable {
        public static final long serialVersionUID = 0;
        public final int count;
        public final E element;

        public ImmutableEntry(E e2, int i) {
            this.element = e2;
            this.count = i;
            CollectPreconditions.checkNonnegative(i, "count");
        }

        @Override // com.google.common.collect.Multiset.Entry
        public final int getCount() {
            return this.count;
        }

        @Override // com.google.common.collect.Multiset.Entry
        public final E getElement() {
            return this.element;
        }

        public ImmutableEntry<E> nextInBucket() {
            return null;
        }
    }

    static final class MultisetIteratorImpl<E> implements Iterator<E> {
        public boolean canRemove;
        public Multiset.Entry<E> currentEntry;
        public final Iterator<Multiset.Entry<E>> entryIterator;
        public int laterCount;
        public final Multiset<E> multiset;
        public int totalCount;

        public MultisetIteratorImpl(Multiset<E> multiset, Iterator<Multiset.Entry<E>> it) {
            this.multiset = multiset;
            this.entryIterator = it;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.laterCount > 0 || this.entryIterator.hasNext();
        }

        @Override // java.util.Iterator
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            if (this.laterCount == 0) {
                this.currentEntry = this.entryIterator.next();
                int count = this.currentEntry.getCount();
                this.laterCount = count;
                this.totalCount = count;
            }
            this.laterCount--;
            this.canRemove = true;
            return this.currentEntry.getElement();
        }

        @Override // java.util.Iterator
        public void remove() {
            Preconditions.checkState(this.canRemove, "no calls to next() since the last call to remove()");
            if (this.totalCount == 1) {
                this.entryIterator.remove();
            } else {
                this.multiset.remove(this.currentEntry.getElement());
            }
            this.totalCount--;
            this.canRemove = false;
        }
    }

    static class UnmodifiableMultiset<E> extends ForwardingMultiset<E> implements Serializable {
        public static final long serialVersionUID = 0;
        public final Multiset<? extends E> delegate;
        public transient Set<E> elementSet;
        public transient Set<Multiset.Entry<E>> entrySet;

        public UnmodifiableMultiset(Multiset<? extends E> multiset) {
            this.delegate = multiset;
        }

        @Override // com.google.common.collect.ForwardingCollection, java.util.Collection, java.util.Queue
        public boolean add(E e2) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.ForwardingCollection, java.util.Collection
        public boolean addAll(Collection<? extends E> collection) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.ForwardingCollection, java.util.Collection, java.util.Set
        public void clear() {
            throw new UnsupportedOperationException();
        }

        public Set<E> createElementSet() {
            return Collections.unmodifiableSet(this.delegate.elementSet());
        }

        @Override // com.google.common.collect.ForwardingMultiset, com.google.common.collect.Multiset
        public Set<E> elementSet() {
            Set<E> set = this.elementSet;
            if (set != null) {
                return set;
            }
            Set<E> setCreateElementSet = createElementSet();
            this.elementSet = setCreateElementSet;
            return setCreateElementSet;
        }

        @Override // com.google.common.collect.ForwardingMultiset, com.google.common.collect.Multiset
        public Set<Multiset.Entry<E>> entrySet() {
            Set<Multiset.Entry<E>> set = this.entrySet;
            if (set != null) {
                return set;
            }
            Set<Multiset.Entry<E>> setUnmodifiableSet = Collections.unmodifiableSet(this.delegate.entrySet());
            this.entrySet = setUnmodifiableSet;
            return setUnmodifiableSet;
        }

        @Override // com.google.common.collect.ForwardingCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<E> iterator() {
            return Iterators.unmodifiableIterator(this.delegate.iterator());
        }

        @Override // com.google.common.collect.ForwardingCollection, java.util.Collection, java.util.Set
        public boolean remove(Object obj) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.ForwardingCollection, java.util.Collection, java.util.Set
        public boolean removeAll(Collection<?> collection) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.ForwardingCollection, java.util.Collection, java.util.Set
        public boolean retainAll(Collection<?> collection) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.ForwardingMultiset, com.google.common.collect.Multiset
        public int setCount(E e2, int i) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.ForwardingMultiset, com.google.common.collect.Multiset
        public int add(E e2, int i) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.ForwardingMultiset, com.google.common.collect.Multiset
        public int remove(Object obj, int i) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.ForwardingMultiset, com.google.common.collect.Multiset
        public boolean setCount(E e2, int i, int i2) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.ForwardingMultiset, com.google.common.collect.ForwardingCollection, com.google.common.collect.ForwardingObject
        public Multiset<E> delegate() {
            return this.delegate;
        }
    }

    private static abstract class ViewMultiset<E> extends AbstractMultiset<E> {
        public ViewMultiset() {
        }

        @Override // com.google.common.collect.AbstractMultiset, java.util.AbstractCollection, java.util.Collection
        public void clear() {
            elementSet().clear();
        }

        @Override // com.google.common.collect.AbstractMultiset
        public int distinctElements() {
            return elementSet().size();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, com.google.common.collect.Multiset
        public Iterator<E> iterator() {
            return Multisets.iteratorImpl(this);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, com.google.common.collect.Multiset
        public int size() {
            return Multisets.linearTimeSizeImpl(this);
        }

        public /* synthetic */ ViewMultiset(AnonymousClass1 anonymousClass1) {
        }
    }

    public static <E> boolean addAllImpl(Multiset<E> multiset, Collection<? extends E> collection) {
        if (multiset == null) {
            throw new NullPointerException();
        }
        if (collection == null) {
            throw new NullPointerException();
        }
        if (collection instanceof Multiset) {
            return addAllImpl((Multiset) multiset, (Multiset) collection);
        }
        if (collection.isEmpty()) {
            return false;
        }
        return Iterators.addAll(multiset, collection.iterator());
    }

    public static <T> Multiset<T> cast(Iterable<T> iterable) {
        return (Multiset) iterable;
    }

    @CanIgnoreReturnValue
    public static boolean containsOccurrences(Multiset<?> multiset, Multiset<?> multiset2) {
        if (multiset == null) {
            throw new NullPointerException();
        }
        if (multiset2 == null) {
            throw new NullPointerException();
        }
        for (Multiset.Entry<?> entry : multiset2.entrySet()) {
            if (multiset.count(entry.getElement()) < entry.getCount()) {
                return false;
            }
        }
        return true;
    }

    @Beta
    public static <E> ImmutableMultiset<E> copyHighestCountFirst(Multiset<E> multiset) {
        Multiset.Entry[] entryArr = (Multiset.Entry[]) multiset.entrySet().toArray(new Multiset.Entry[0]);
        Arrays.sort(entryArr, DecreasingCount.INSTANCE);
        return ImmutableMultiset.copyFromEntries(Arrays.asList(entryArr));
    }

    @Beta
    public static <E> Multiset<E> difference(final Multiset<E> multiset, final Multiset<?> multiset2) {
        if (multiset == null) {
            throw new NullPointerException();
        }
        if (multiset2 != null) {
            return new ViewMultiset<E>() { // from class: com.google.common.collect.Multisets.4
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(null);
                }

                @Override // com.google.common.collect.Multisets.ViewMultiset, com.google.common.collect.AbstractMultiset, java.util.AbstractCollection, java.util.Collection
                public void clear() {
                    throw new UnsupportedOperationException();
                }

                @Override // com.google.common.collect.Multiset
                public int count(Object obj) {
                    int iCount = multiset.count(obj);
                    if (iCount == 0) {
                        return 0;
                    }
                    return Math.max(0, iCount - multiset2.count(obj));
                }

                @Override // com.google.common.collect.Multisets.ViewMultiset, com.google.common.collect.AbstractMultiset
                public int distinctElements() {
                    return Iterators.size(entryIterator());
                }

                @Override // com.google.common.collect.AbstractMultiset
                public Iterator<E> elementIterator() {
                    final Iterator<Multiset.Entry<E>> it = multiset.entrySet().iterator();
                    return new AbstractIterator<E>() { // from class: com.google.common.collect.Multisets.4.1
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
                        public E computeNext() {
                            while (it.hasNext()) {
                                Multiset.Entry entry = (Multiset.Entry) it.next();
                                E e2 = (E) entry.getElement();
                                if (entry.getCount() > multiset2.count(e2)) {
                                    return e2;
                                }
                            }
                            return endOfData();
                        }
                    };
                }

                @Override // com.google.common.collect.AbstractMultiset
                public Iterator<Multiset.Entry<E>> entryIterator() {
                    final Iterator<Multiset.Entry<E>> it = multiset.entrySet().iterator();
                    return new AbstractIterator<Multiset.Entry<E>>() { // from class: com.google.common.collect.Multisets.4.2
                        @Override // com.google.common.collect.AbstractIterator
                        public Multiset.Entry<E> computeNext() {
                            while (it.hasNext()) {
                                Multiset.Entry entry = (Multiset.Entry) it.next();
                                Object element = entry.getElement();
                                int count = entry.getCount() - multiset2.count(element);
                                if (count > 0) {
                                    return new ImmutableEntry(element, count);
                                }
                            }
                            return endOfData();
                        }
                    };
                }
            };
        }
        throw new NullPointerException();
    }

    public static <E> Iterator<E> elementIterator(Iterator<Multiset.Entry<E>> it) {
        return new AnonymousClass5(it);
    }

    public static boolean equalsImpl(Multiset<?> multiset, Object obj) {
        if (obj == multiset) {
            return true;
        }
        if (obj instanceof Multiset) {
            Multiset multiset2 = (Multiset) obj;
            if (multiset.size() == multiset2.size() && multiset.entrySet().size() == multiset2.entrySet().size()) {
                for (Multiset.Entry entry : multiset2.entrySet()) {
                    if (multiset.count(entry.getElement()) != entry.getCount()) {
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }

    @Beta
    public static <E> Multiset<E> filter(Multiset<E> multiset, Predicate<? super E> predicate) {
        if (!(multiset instanceof FilteredMultiset)) {
            return new FilteredMultiset(multiset, predicate);
        }
        FilteredMultiset filteredMultiset = (FilteredMultiset) multiset;
        return new FilteredMultiset(filteredMultiset.unfiltered, Predicates.and(filteredMultiset.predicate, predicate));
    }

    public static <E> Multiset.Entry<E> immutableEntry(E e2, int i) {
        return new ImmutableEntry(e2, i);
    }

    public static int inferDistinctElements(Iterable<?> iterable) {
        if (iterable instanceof Multiset) {
            return ((Multiset) iterable).elementSet().size();
        }
        return 11;
    }

    public static <E> Multiset<E> intersection(final Multiset<E> multiset, final Multiset<?> multiset2) {
        if (multiset == null) {
            throw new NullPointerException();
        }
        if (multiset2 != null) {
            return new ViewMultiset<E>() { // from class: com.google.common.collect.Multisets.2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(null);
                }

                @Override // com.google.common.collect.Multiset
                public int count(Object obj) {
                    int iCount = multiset.count(obj);
                    if (iCount == 0) {
                        return 0;
                    }
                    return Math.min(iCount, multiset2.count(obj));
                }

                @Override // com.google.common.collect.AbstractMultiset
                public Set<E> createElementSet() {
                    return Sets.intersection(multiset.elementSet(), multiset2.elementSet());
                }

                @Override // com.google.common.collect.AbstractMultiset
                public Iterator<E> elementIterator() {
                    throw new AssertionError("should never be called");
                }

                @Override // com.google.common.collect.AbstractMultiset
                public Iterator<Multiset.Entry<E>> entryIterator() {
                    final Iterator<Multiset.Entry<E>> it = multiset.entrySet().iterator();
                    return new AbstractIterator<Multiset.Entry<E>>() { // from class: com.google.common.collect.Multisets.2.1
                        @Override // com.google.common.collect.AbstractIterator
                        public Multiset.Entry<E> computeNext() {
                            while (it.hasNext()) {
                                Multiset.Entry entry = (Multiset.Entry) it.next();
                                Object element = entry.getElement();
                                int iMin = Math.min(entry.getCount(), multiset2.count(element));
                                if (iMin > 0) {
                                    return new ImmutableEntry(element, iMin);
                                }
                            }
                            return endOfData();
                        }
                    };
                }
            };
        }
        throw new NullPointerException();
    }

    public static <E> Iterator<E> iteratorImpl(Multiset<E> multiset) {
        return new MultisetIteratorImpl(multiset, multiset.entrySet().iterator());
    }

    public static int linearTimeSizeImpl(Multiset<?> multiset) {
        Iterator<Multiset.Entry<?>> it = multiset.entrySet().iterator();
        long count = 0;
        while (it.hasNext()) {
            count += (long) it.next().getCount();
        }
        return Ints.saturatedCast(count);
    }

    public static boolean removeAllImpl(Multiset<?> multiset, Collection<?> collection) {
        if (collection instanceof Multiset) {
            collection = ((Multiset) collection).elementSet();
        }
        return multiset.elementSet().removeAll(collection);
    }

    @CanIgnoreReturnValue
    public static boolean removeOccurrences(Multiset<?> multiset, Iterable<?> iterable) {
        if (iterable instanceof Multiset) {
            return removeOccurrences(multiset, (Multiset<?>) iterable);
        }
        if (multiset == null) {
            throw new NullPointerException();
        }
        if (iterable == null) {
            throw new NullPointerException();
        }
        boolean zRemove = false;
        Iterator<?> it = iterable.iterator();
        while (it.hasNext()) {
            zRemove |= multiset.remove(it.next());
        }
        return zRemove;
    }

    public static boolean retainAllImpl(Multiset<?> multiset, Collection<?> collection) {
        if (collection == null) {
            throw new NullPointerException();
        }
        if (collection instanceof Multiset) {
            collection = ((Multiset) collection).elementSet();
        }
        return multiset.elementSet().retainAll(collection);
    }

    @CanIgnoreReturnValue
    public static boolean retainOccurrences(Multiset<?> multiset, Multiset<?> multiset2) {
        return retainOccurrencesImpl(multiset, multiset2);
    }

    public static <E> boolean retainOccurrencesImpl(Multiset<E> multiset, Multiset<?> multiset2) {
        if (multiset == null) {
            throw new NullPointerException();
        }
        if (multiset2 == null) {
            throw new NullPointerException();
        }
        Iterator<Multiset.Entry<E>> it = multiset.entrySet().iterator();
        boolean z = false;
        while (it.hasNext()) {
            Multiset.Entry<E> next = it.next();
            int iCount = multiset2.count(next.getElement());
            if (iCount == 0) {
                it.remove();
            } else if (iCount < next.getCount()) {
                multiset.setCount(next.getElement(), iCount);
            }
            z = true;
        }
        return z;
    }

    public static <E> int setCountImpl(Multiset<E> multiset, E e2, int i) {
        CollectPreconditions.checkNonnegative(i, "count");
        int iCount = multiset.count(e2);
        int i2 = i - iCount;
        if (i2 > 0) {
            multiset.add(e2, i2);
        } else if (i2 < 0) {
            multiset.remove(e2, -i2);
        }
        return iCount;
    }

    @Beta
    public static <E> Multiset<E> sum(final Multiset<? extends E> multiset, final Multiset<? extends E> multiset2) {
        if (multiset == null) {
            throw new NullPointerException();
        }
        if (multiset2 != null) {
            return new ViewMultiset<E>() { // from class: com.google.common.collect.Multisets.3
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(null);
                }

                @Override // com.google.common.collect.AbstractMultiset, java.util.AbstractCollection, java.util.Collection, com.google.common.collect.Multiset
                public boolean contains(Object obj) {
                    return multiset.contains(obj) || multiset2.contains(obj);
                }

                @Override // com.google.common.collect.Multiset
                public int count(Object obj) {
                    return multiset2.count(obj) + multiset.count(obj);
                }

                @Override // com.google.common.collect.AbstractMultiset
                public Set<E> createElementSet() {
                    return Sets.union(multiset.elementSet(), multiset2.elementSet());
                }

                @Override // com.google.common.collect.AbstractMultiset
                public Iterator<E> elementIterator() {
                    throw new AssertionError("should never be called");
                }

                @Override // com.google.common.collect.AbstractMultiset
                public Iterator<Multiset.Entry<E>> entryIterator() {
                    final Iterator<Multiset.Entry<E>> it = multiset.entrySet().iterator();
                    final Iterator<Multiset.Entry<E>> it2 = multiset2.entrySet().iterator();
                    return new AbstractIterator<Multiset.Entry<E>>() { // from class: com.google.common.collect.Multisets.3.1
                        @Override // com.google.common.collect.AbstractIterator
                        public Multiset.Entry<E> computeNext() {
                            if (it.hasNext()) {
                                Multiset.Entry entry = (Multiset.Entry) it.next();
                                Object element = entry.getElement();
                                return new ImmutableEntry(element, multiset2.count(element) + entry.getCount());
                            }
                            while (it2.hasNext()) {
                                Multiset.Entry entry2 = (Multiset.Entry) it2.next();
                                Object element2 = entry2.getElement();
                                if (!multiset.contains(element2)) {
                                    return new ImmutableEntry(element2, entry2.getCount());
                                }
                            }
                            return endOfData();
                        }
                    };
                }

                @Override // com.google.common.collect.AbstractMultiset, java.util.AbstractCollection, java.util.Collection
                public boolean isEmpty() {
                    return multiset.isEmpty() && multiset2.isEmpty();
                }

                @Override // com.google.common.collect.Multisets.ViewMultiset, java.util.AbstractCollection, java.util.Collection, com.google.common.collect.Multiset
                public int size() {
                    return IntMath.saturatedAdd(multiset.size(), multiset2.size());
                }
            };
        }
        throw new NullPointerException();
    }

    @Beta
    public static <E> Multiset<E> union(final Multiset<? extends E> multiset, final Multiset<? extends E> multiset2) {
        if (multiset == null) {
            throw new NullPointerException();
        }
        if (multiset2 != null) {
            return new ViewMultiset<E>() { // from class: com.google.common.collect.Multisets.1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(null);
                }

                @Override // com.google.common.collect.AbstractMultiset, java.util.AbstractCollection, java.util.Collection, com.google.common.collect.Multiset
                public boolean contains(Object obj) {
                    return multiset.contains(obj) || multiset2.contains(obj);
                }

                @Override // com.google.common.collect.Multiset
                public int count(Object obj) {
                    return Math.max(multiset.count(obj), multiset2.count(obj));
                }

                @Override // com.google.common.collect.AbstractMultiset
                public Set<E> createElementSet() {
                    return Sets.union(multiset.elementSet(), multiset2.elementSet());
                }

                @Override // com.google.common.collect.AbstractMultiset
                public Iterator<E> elementIterator() {
                    throw new AssertionError("should never be called");
                }

                @Override // com.google.common.collect.AbstractMultiset
                public Iterator<Multiset.Entry<E>> entryIterator() {
                    final Iterator<Multiset.Entry<E>> it = multiset.entrySet().iterator();
                    final Iterator<Multiset.Entry<E>> it2 = multiset2.entrySet().iterator();
                    return new AbstractIterator<Multiset.Entry<E>>() { // from class: com.google.common.collect.Multisets.1.1
                        @Override // com.google.common.collect.AbstractIterator
                        public Multiset.Entry<E> computeNext() {
                            if (it.hasNext()) {
                                Multiset.Entry entry = (Multiset.Entry) it.next();
                                Object element = entry.getElement();
                                return new ImmutableEntry(element, Math.max(entry.getCount(), multiset2.count(element)));
                            }
                            while (it2.hasNext()) {
                                Multiset.Entry entry2 = (Multiset.Entry) it2.next();
                                Object element2 = entry2.getElement();
                                if (!multiset.contains(element2)) {
                                    return new ImmutableEntry(element2, entry2.getCount());
                                }
                            }
                            return endOfData();
                        }
                    };
                }

                @Override // com.google.common.collect.AbstractMultiset, java.util.AbstractCollection, java.util.Collection
                public boolean isEmpty() {
                    return multiset.isEmpty() && multiset2.isEmpty();
                }
            };
        }
        throw new NullPointerException();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static <E> Multiset<E> unmodifiableMultiset(Multiset<? extends E> multiset) {
        if ((multiset instanceof UnmodifiableMultiset) || (multiset instanceof ImmutableMultiset)) {
            return multiset;
        }
        if (multiset != 0) {
            return new UnmodifiableMultiset(multiset);
        }
        throw new NullPointerException();
    }

    @Beta
    public static <E> SortedMultiset<E> unmodifiableSortedMultiset(SortedMultiset<E> sortedMultiset) {
        if (sortedMultiset != null) {
            return new UnmodifiableSortedMultiset(sortedMultiset);
        }
        throw new NullPointerException();
    }

    @Deprecated
    public static <E> Multiset<E> unmodifiableMultiset(ImmutableMultiset<E> immutableMultiset) {
        if (immutableMultiset != null) {
            return immutableMultiset;
        }
        throw new NullPointerException();
    }

    public static <E> boolean setCountImpl(Multiset<E> multiset, E e2, int i, int i2) {
        CollectPreconditions.checkNonnegative(i, "oldCount");
        CollectPreconditions.checkNonnegative(i2, "newCount");
        if (multiset.count(e2) != i) {
            return false;
        }
        multiset.setCount(e2, i2);
        return true;
    }

    @CanIgnoreReturnValue
    public static boolean removeOccurrences(Multiset<?> multiset, Multiset<?> multiset2) {
        if (multiset == null) {
            throw new NullPointerException();
        }
        if (multiset2 != null) {
            boolean z = false;
            Iterator<Multiset.Entry<?>> it = multiset.entrySet().iterator();
            while (it.hasNext()) {
                Multiset.Entry<?> next = it.next();
                int iCount = multiset2.count(next.getElement());
                if (iCount >= next.getCount()) {
                    it.remove();
                } else if (iCount > 0) {
                    multiset.remove(next.getElement(), iCount);
                }
                z = true;
            }
            return z;
        }
        throw new NullPointerException();
    }

    public static <E> boolean addAllImpl(Multiset<E> multiset, Multiset<? extends E> multiset2) {
        if (multiset2 instanceof AbstractMapBasedMultiset) {
            return addAllImpl((Multiset) multiset, (AbstractMapBasedMultiset) multiset2);
        }
        if (multiset2.isEmpty()) {
            return false;
        }
        for (Multiset.Entry<? extends E> entry : multiset2.entrySet()) {
            multiset.add(entry.getElement(), entry.getCount());
        }
        return true;
    }

    public static <E> boolean addAllImpl(Multiset<E> multiset, AbstractMapBasedMultiset<? extends E> abstractMapBasedMultiset) {
        if (abstractMapBasedMultiset.isEmpty()) {
            return false;
        }
        abstractMapBasedMultiset.addTo(multiset);
        return true;
    }
}
