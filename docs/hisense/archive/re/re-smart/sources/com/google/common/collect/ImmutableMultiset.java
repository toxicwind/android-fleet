package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.concurrent.LazyInit;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
@GwtCompatible(emulated = true, serializable = true)
public abstract class ImmutableMultiset<E> extends ImmutableMultisetGwtSerializationDependencies<E> implements Multiset<E> {

    @LazyInit
    public transient ImmutableList<E> asList;

    @LazyInit
    public transient ImmutableSet<Multiset.Entry<E>> entrySet;

    public static class Builder<E> extends ImmutableCollection.Builder<E> {
        public boolean buildInvoked;
        public ObjectCountHashMap<E> contents;
        public boolean isLinkedHash;

        public Builder() {
            this(4);
        }

        public static <T> ObjectCountHashMap<T> tryGetMap(Iterable<T> iterable) {
            if (iterable instanceof RegularImmutableMultiset) {
                return ((RegularImmutableMultiset) iterable).contents;
            }
            if (iterable instanceof AbstractMapBasedMultiset) {
                return ((AbstractMapBasedMultiset) iterable).backingMap;
            }
            return null;
        }

        @CanIgnoreReturnValue
        public Builder<E> addCopies(E e2, int i) {
            if (i == 0) {
                return this;
            }
            if (this.buildInvoked) {
                this.contents = new ObjectCountHashMap<>(this.contents);
                this.isLinkedHash = false;
            }
            this.buildInvoked = false;
            if (e2 == null) {
                throw new NullPointerException();
            }
            ObjectCountHashMap<E> objectCountHashMap = this.contents;
            objectCountHashMap.put(e2, objectCountHashMap.get(e2) + i);
            return this;
        }

        @CanIgnoreReturnValue
        public Builder<E> setCount(E e2, int i) {
            if (i == 0 && !this.isLinkedHash) {
                this.contents = new ObjectCountLinkedHashMap(this.contents);
                this.isLinkedHash = true;
            } else if (this.buildInvoked) {
                this.contents = new ObjectCountHashMap<>(this.contents);
                this.isLinkedHash = false;
            }
            this.buildInvoked = false;
            if (e2 == null) {
                throw new NullPointerException();
            }
            if (i == 0) {
                this.contents.remove(e2);
            } else {
                this.contents.put(e2, i);
            }
            return this;
        }

        public Builder(int i) {
            this.buildInvoked = false;
            this.isLinkedHash = false;
            this.contents = new ObjectCountHashMap<>(i);
        }

        @Override // com.google.common.collect.ImmutableCollection.Builder
        public ImmutableMultiset<E> build() {
            if (this.contents.size() == 0) {
                return ImmutableMultiset.of();
            }
            if (this.isLinkedHash) {
                this.contents = new ObjectCountHashMap<>(this.contents);
                this.isLinkedHash = false;
            }
            this.buildInvoked = true;
            return new RegularImmutableMultiset(this.contents);
        }

        @Override // com.google.common.collect.ImmutableCollection.Builder
        @CanIgnoreReturnValue
        public Builder<E> add(E e2) {
            return addCopies(e2, 1);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.common.collect.ImmutableCollection.Builder
        @CanIgnoreReturnValue
        public Builder<E> addAll(Iterable<? extends E> iterable) {
            if (iterable instanceof Multiset) {
                Multiset multiset = (Multiset) iterable;
                ObjectCountHashMap objectCountHashMapTryGetMap = tryGetMap(multiset);
                if (objectCountHashMapTryGetMap != null) {
                    ObjectCountHashMap<E> objectCountHashMap = this.contents;
                    objectCountHashMap.ensureCapacity(Math.max(objectCountHashMap.size(), objectCountHashMapTryGetMap.size()));
                    for (int iFirstIndex = objectCountHashMapTryGetMap.firstIndex(); iFirstIndex >= 0; iFirstIndex = objectCountHashMapTryGetMap.nextIndex(iFirstIndex)) {
                        addCopies(objectCountHashMapTryGetMap.getKey(iFirstIndex), objectCountHashMapTryGetMap.getValue(iFirstIndex));
                    }
                } else {
                    Set<Multiset.Entry<E>> setEntrySet = multiset.entrySet();
                    ObjectCountHashMap<E> objectCountHashMap2 = this.contents;
                    objectCountHashMap2.ensureCapacity(Math.max(objectCountHashMap2.size(), setEntrySet.size()));
                    for (Multiset.Entry<E> entry : multiset.entrySet()) {
                        addCopies(entry.getElement(), entry.getCount());
                    }
                }
            } else {
                super.addAll((Iterable) iterable);
            }
            return this;
        }

        @Override // com.google.common.collect.ImmutableCollection.Builder
        @CanIgnoreReturnValue
        public Builder<E> add(E... eArr) {
            super.add((Object[]) eArr);
            return this;
        }

        public Builder(boolean z) {
            this.buildInvoked = false;
            this.isLinkedHash = false;
            this.contents = null;
        }

        @Override // com.google.common.collect.ImmutableCollection.Builder
        @CanIgnoreReturnValue
        public Builder<E> addAll(Iterator<? extends E> it) {
            super.addAll((Iterator) it);
            return this;
        }
    }

    private final class EntrySet extends IndexedImmutableSet<Multiset.Entry<E>> {
        public static final long serialVersionUID = 0;

        public EntrySet() {
        }

        @Override // com.google.common.collect.ImmutableCollection, java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object obj) {
            if (!(obj instanceof Multiset.Entry)) {
                return false;
            }
            Multiset.Entry entry = (Multiset.Entry) obj;
            return entry.getCount() > 0 && ImmutableMultiset.this.count(entry.getElement()) == entry.getCount();
        }

        @Override // com.google.common.collect.ImmutableSet, java.util.Collection, java.util.Set
        public int hashCode() {
            return ImmutableMultiset.this.hashCode();
        }

        @Override // com.google.common.collect.ImmutableCollection
        public boolean isPartialView() {
            return ImmutableMultiset.this.isPartialView();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return ImmutableMultiset.this.elementSet().size();
        }

        @Override // com.google.common.collect.ImmutableSet, com.google.common.collect.ImmutableCollection
        @GwtIncompatible
        public Object writeReplace() {
            return new EntrySetSerializedForm(ImmutableMultiset.this);
        }

        public /* synthetic */ EntrySet(AnonymousClass1 anonymousClass1) {
        }

        @Override // com.google.common.collect.IndexedImmutableSet
        public Multiset.Entry<E> get(int i) {
            return ImmutableMultiset.this.getEntry(i);
        }
    }

    @GwtIncompatible
    static class EntrySetSerializedForm<E> implements Serializable {
        public final ImmutableMultiset<E> multiset;

        public EntrySetSerializedForm(ImmutableMultiset<E> immutableMultiset) {
            this.multiset = immutableMultiset;
        }

        public Object readResolve() {
            return this.multiset.entrySet();
        }
    }

    public static <E> Builder<E> builder() {
        return new Builder<>(4);
    }

    public static <E> ImmutableMultiset<E> copyFromElements(E... eArr) {
        return new Builder(4).add((Object[]) eArr).build();
    }

    public static <E> ImmutableMultiset<E> copyFromEntries(Collection<? extends Multiset.Entry<? extends E>> collection) {
        Builder builder = new Builder(collection.size());
        for (Multiset.Entry<? extends E> entry : collection) {
            builder.addCopies(entry.getElement(), entry.getCount());
        }
        return builder.build();
    }

    public static <E> ImmutableMultiset<E> copyOf(E[] eArr) {
        return copyFromElements(eArr);
    }

    private ImmutableSet<Multiset.Entry<E>> createEntrySet() {
        return isEmpty() ? ImmutableSet.of() : new EntrySet(null);
    }

    public static <E> ImmutableMultiset<E> of() {
        return RegularImmutableMultiset.EMPTY;
    }

    @Override // com.google.common.collect.Multiset
    @CanIgnoreReturnValue
    @Deprecated
    public final int add(E e2, int i) {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.common.collect.ImmutableCollection
    public ImmutableList<E> asList() {
        ImmutableList<E> immutableList = this.asList;
        if (immutableList != null) {
            return immutableList;
        }
        ImmutableList<E> immutableListAsList = super.asList();
        this.asList = immutableListAsList;
        return immutableListAsList;
    }

    @Override // com.google.common.collect.ImmutableCollection, java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean contains(Object obj) {
        return count(obj) > 0;
    }

    @Override // com.google.common.collect.ImmutableCollection
    @GwtIncompatible
    public int copyIntoArray(Object[] objArr, int i) {
        UnmodifiableIterator<Multiset.Entry<E>> it = entrySet().iterator();
        while (it.hasNext()) {
            Multiset.Entry<E> next = it.next();
            Arrays.fill(objArr, i, next.getCount() + i, next.getElement());
            i += next.getCount();
        }
        return i;
    }

    @Override // com.google.common.collect.Multiset
    public abstract ImmutableSet<E> elementSet();

    @Override // java.util.Collection, com.google.common.collect.Multiset
    public boolean equals(Object obj) {
        return Multisets.equalsImpl(this, obj);
    }

    public abstract Multiset.Entry<E> getEntry(int i);

    @Override // java.util.Collection, com.google.common.collect.Multiset
    public int hashCode() {
        return Sets.hashCodeImpl(entrySet());
    }

    @Override // com.google.common.collect.Multiset
    @CanIgnoreReturnValue
    @Deprecated
    public final int remove(Object obj, int i) {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.common.collect.Multiset
    @CanIgnoreReturnValue
    @Deprecated
    public final int setCount(E e2, int i) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.AbstractCollection, com.google.common.collect.Multiset
    public String toString() {
        return entrySet().toString();
    }

    @Override // com.google.common.collect.ImmutableCollection
    @GwtIncompatible
    public abstract Object writeReplace();

    public static <E> ImmutableMultiset<E> copyOf(Iterable<? extends E> iterable) {
        if (iterable instanceof ImmutableMultiset) {
            ImmutableMultiset<E> immutableMultiset = (ImmutableMultiset) iterable;
            if (!immutableMultiset.isPartialView()) {
                return immutableMultiset;
            }
        }
        Builder builder = new Builder(Multisets.inferDistinctElements(iterable));
        builder.addAll((Iterable) iterable);
        return builder.build();
    }

    public static <E> ImmutableMultiset<E> of(E e2) {
        return copyFromElements(e2);
    }

    @Override // com.google.common.collect.Multiset
    public ImmutableSet<Multiset.Entry<E>> entrySet() {
        ImmutableSet<Multiset.Entry<E>> immutableSet = this.entrySet;
        if (immutableSet != null) {
            return immutableSet;
        }
        ImmutableSet<Multiset.Entry<E>> immutableSetCreateEntrySet = createEntrySet();
        this.entrySet = immutableSetCreateEntrySet;
        return immutableSetCreateEntrySet;
    }

    @Override // com.google.common.collect.ImmutableCollection, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set, java.util.NavigableSet, com.google.common.collect.SortedIterable
    public UnmodifiableIterator<E> iterator() {
        final UnmodifiableIterator<Multiset.Entry<E>> it = entrySet().iterator();
        return new UnmodifiableIterator<E>(this) { // from class: com.google.common.collect.ImmutableMultiset.1
            public E element;
            public int remaining;

            @Override // java.util.Iterator
            public boolean hasNext() {
                return this.remaining > 0 || it.hasNext();
            }

            @Override // java.util.Iterator
            public E next() {
                if (this.remaining <= 0) {
                    Multiset.Entry entry = (Multiset.Entry) it.next();
                    this.element = (E) entry.getElement();
                    this.remaining = entry.getCount();
                }
                this.remaining--;
                return this.element;
            }
        };
    }

    @Override // com.google.common.collect.Multiset
    @CanIgnoreReturnValue
    @Deprecated
    public final boolean setCount(E e2, int i, int i2) {
        throw new UnsupportedOperationException();
    }

    public static <E> ImmutableMultiset<E> of(E e2, E e3) {
        return copyFromElements(e2, e3);
    }

    public static <E> ImmutableMultiset<E> of(E e2, E e3, E e4) {
        return copyFromElements(e2, e3, e4);
    }

    public static <E> ImmutableMultiset<E> of(E e2, E e3, E e4, E e5) {
        return copyFromElements(e2, e3, e4, e5);
    }

    public static <E> ImmutableMultiset<E> of(E e2, E e3, E e4, E e5, E e6) {
        return copyFromElements(e2, e3, e4, e5, e6);
    }

    public static <E> ImmutableMultiset<E> of(E e2, E e3, E e4, E e5, E e6, E e7, E... eArr) {
        return new Builder(4).add((Object) e2).add((Object) e3).add((Object) e4).add((Object) e5).add((Object) e6).add((Object) e7).add((Object[]) eArr).build();
    }

    public static <E> ImmutableMultiset<E> copyOf(Iterator<? extends E> it) {
        return new Builder(4).addAll((Iterator) it).build();
    }
}
