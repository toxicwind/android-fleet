package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import com.google.common.primitives.Ints;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/* JADX INFO: loaded from: classes.dex */
@GwtCompatible(emulated = true)
public abstract class AbstractMapBasedMultiset<E> extends AbstractMultiset<E> implements Serializable {

    @GwtIncompatible
    public static final long serialVersionUID = 0;
    public transient ObjectCountHashMap<E> backingMap;
    public transient long size;

    abstract class Itr<T> implements Iterator<T> {
        public int entryIndex;
        public int expectedModCount;
        public int toRemove = -1;

        public Itr() {
            this.entryIndex = AbstractMapBasedMultiset.this.backingMap.firstIndex();
            this.expectedModCount = AbstractMapBasedMultiset.this.backingMap.modCount;
        }

        private void checkForConcurrentModification() {
            if (AbstractMapBasedMultiset.this.backingMap.modCount != this.expectedModCount) {
                throw new ConcurrentModificationException();
            }
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            checkForConcurrentModification();
            return this.entryIndex >= 0;
        }

        @Override // java.util.Iterator
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            T tResult = result(this.entryIndex);
            int i = this.entryIndex;
            this.toRemove = i;
            this.entryIndex = AbstractMapBasedMultiset.this.backingMap.nextIndex(i);
            return tResult;
        }

        @Override // java.util.Iterator
        public void remove() {
            checkForConcurrentModification();
            Preconditions.checkState(this.toRemove != -1, "no calls to next() since the last call to remove()");
            AbstractMapBasedMultiset abstractMapBasedMultiset = AbstractMapBasedMultiset.this;
            abstractMapBasedMultiset.size -= (long) abstractMapBasedMultiset.backingMap.removeEntry(this.toRemove);
            this.entryIndex = AbstractMapBasedMultiset.this.backingMap.nextIndexAfterRemove(this.entryIndex, this.toRemove);
            this.toRemove = -1;
            this.expectedModCount = AbstractMapBasedMultiset.this.backingMap.modCount;
        }

        public abstract T result(int i);
    }

    public AbstractMapBasedMultiset(int i) {
        init(i);
    }

    @GwtIncompatible
    private void readObject(ObjectInputStream objectInputStream) throws ClassNotFoundException, IOException {
        objectInputStream.defaultReadObject();
        int i = objectInputStream.readInt();
        init(3);
        Serialization.populateMultiset(this, objectInputStream, i);
    }

    @GwtIncompatible
    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        Serialization.writeMultiset(this, objectOutputStream);
    }

    @Override // com.google.common.collect.AbstractMultiset, com.google.common.collect.Multiset
    @CanIgnoreReturnValue
    public final int add(E e2, int i) {
        if (i == 0) {
            return count(e2);
        }
        Preconditions.checkArgument(i > 0, "occurrences cannot be negative: %s", i);
        int iIndexOf = this.backingMap.indexOf(e2);
        if (iIndexOf == -1) {
            this.backingMap.put(e2, i);
            this.size += (long) i;
            return 0;
        }
        int value = this.backingMap.getValue(iIndexOf);
        long j = i;
        long j2 = ((long) value) + j;
        Preconditions.checkArgument(j2 <= 2147483647L, "too many occurrences: %s", j2);
        this.backingMap.setValue(iIndexOf, (int) j2);
        this.size += j;
        return value;
    }

    public void addTo(Multiset<? super E> multiset) {
        if (multiset == null) {
            throw new NullPointerException();
        }
        int iFirstIndex = this.backingMap.firstIndex();
        while (iFirstIndex >= 0) {
            multiset.add(this.backingMap.getKey(iFirstIndex), this.backingMap.getValue(iFirstIndex));
            iFirstIndex = this.backingMap.nextIndex(iFirstIndex);
        }
    }

    @Override // com.google.common.collect.AbstractMultiset, java.util.AbstractCollection, java.util.Collection
    public final void clear() {
        this.backingMap.clear();
        this.size = 0L;
    }

    @Override // com.google.common.collect.Multiset
    public final int count(Object obj) {
        return this.backingMap.get(obj);
    }

    @Override // com.google.common.collect.AbstractMultiset
    public final int distinctElements() {
        return this.backingMap.size();
    }

    @Override // com.google.common.collect.AbstractMultiset
    public final Iterator<E> elementIterator() {
        return new AbstractMapBasedMultiset<E>.Itr<E>() { // from class: com.google.common.collect.AbstractMapBasedMultiset.1
            @Override // com.google.common.collect.AbstractMapBasedMultiset.Itr
            public E result(int i) {
                return AbstractMapBasedMultiset.this.backingMap.getKey(i);
            }
        };
    }

    @Override // com.google.common.collect.AbstractMultiset
    public final Iterator<Multiset.Entry<E>> entryIterator() {
        return new AbstractMapBasedMultiset<E>.Itr<Multiset.Entry<E>>() { // from class: com.google.common.collect.AbstractMapBasedMultiset.2
            @Override // com.google.common.collect.AbstractMapBasedMultiset.Itr
            public Multiset.Entry<E> result(int i) {
                return AbstractMapBasedMultiset.this.backingMap.getEntry(i);
            }
        };
    }

    public abstract void init(int i);

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, com.google.common.collect.Multiset
    public final Iterator<E> iterator() {
        return Multisets.iteratorImpl(this);
    }

    @Override // com.google.common.collect.AbstractMultiset, com.google.common.collect.Multiset
    @CanIgnoreReturnValue
    public final int remove(Object obj, int i) {
        if (i == 0) {
            return count(obj);
        }
        Preconditions.checkArgument(i > 0, "occurrences cannot be negative: %s", i);
        int iIndexOf = this.backingMap.indexOf(obj);
        if (iIndexOf == -1) {
            return 0;
        }
        int value = this.backingMap.getValue(iIndexOf);
        if (value > i) {
            this.backingMap.setValue(iIndexOf, value - i);
        } else {
            this.backingMap.removeEntry(iIndexOf);
            i = value;
        }
        this.size -= (long) i;
        return value;
    }

    @Override // com.google.common.collect.AbstractMultiset, com.google.common.collect.Multiset
    @CanIgnoreReturnValue
    public final int setCount(E e2, int i) {
        CollectPreconditions.checkNonnegative(i, "count");
        int iRemove = i == 0 ? this.backingMap.remove(e2) : this.backingMap.put(e2, i);
        this.size += (long) (i - iRemove);
        return iRemove;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, com.google.common.collect.Multiset
    public final int size() {
        return Ints.saturatedCast(this.size);
    }

    @Override // com.google.common.collect.AbstractMultiset, com.google.common.collect.Multiset
    public final boolean setCount(E e2, int i, int i2) {
        CollectPreconditions.checkNonnegative(i, "oldCount");
        CollectPreconditions.checkNonnegative(i2, "newCount");
        int iIndexOf = this.backingMap.indexOf(e2);
        if (iIndexOf == -1) {
            if (i != 0) {
                return false;
            }
            if (i2 > 0) {
                this.backingMap.put(e2, i2);
                this.size += (long) i2;
            }
            return true;
        }
        if (this.backingMap.getValue(iIndexOf) != i) {
            return false;
        }
        if (i2 == 0) {
            this.backingMap.removeEntry(iIndexOf);
            this.size -= (long) i;
        } else {
            this.backingMap.setValue(iIndexOf, i2);
            this.size += (long) (i2 - i);
        }
        return true;
    }
}
