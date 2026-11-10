package com.google.common.collect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.concurrent.LazyInit;
import com.google.j2objc.annotations.RetainedWith;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import java.util.SortedSet;

/* JADX INFO: loaded from: classes.dex */
@GwtCompatible(emulated = true, serializable = true)
public abstract class ImmutableSet<E> extends ImmutableCollection<E> implements Set<E> {
    public static final int CUTOFF = 751619276;
    public static final double DESIRED_LOAD_FACTOR = 0.7d;
    public static final int MAX_TABLE_SIZE = 1073741824;

    @RetainedWith
    @LazyInit
    public transient ImmutableList<E> asList;

    public static class Builder<E> extends ImmutableCollection.ArrayBasedBuilder<E> {
        public int hashCode;

        @VisibleForTesting
        public Object[] hashTable;

        public Builder() {
            super(4);
        }

        private void addDeduping(E e2) {
            int length = this.hashTable.length - 1;
            int iHashCode = e2.hashCode();
            int iSmear = Hashing.smear(iHashCode);
            while (true) {
                int i = iSmear & length;
                Object[] objArr = this.hashTable;
                Object obj = objArr[i];
                if (obj == null) {
                    objArr[i] = e2;
                    this.hashCode += iHashCode;
                    super.add((Object) e2);
                    return;
                } else if (obj.equals(e2)) {
                    return;
                } else {
                    iSmear = i + 1;
                }
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        @CanIgnoreReturnValue
        public Builder<E> combine(Builder<E> builder) {
            if (this.hashTable != null) {
                for (int i = 0; i < builder.size; i++) {
                    add(builder.contents[i]);
                }
            } else {
                addAll(builder.contents, builder.size);
            }
            return this;
        }

        public Builder(int i) {
            super(i);
            this.hashTable = new Object[ImmutableSet.chooseTableSize(i)];
        }

        @Override // com.google.common.collect.ImmutableCollection.Builder
        public ImmutableSet<E> build() {
            ImmutableSet<E> immutableSetConstruct;
            int i = this.size;
            if (i == 0) {
                return ImmutableSet.of();
            }
            if (i == 1) {
                return ImmutableSet.of(this.contents[0]);
            }
            if (this.hashTable == null || ImmutableSet.chooseTableSize(i) != this.hashTable.length) {
                immutableSetConstruct = ImmutableSet.construct(this.size, this.contents);
                this.size = immutableSetConstruct.size();
            } else {
                Object[] objArrCopyOf = ImmutableSet.shouldTrim(this.size, this.contents.length) ? Arrays.copyOf(this.contents, this.size) : this.contents;
                int i2 = this.hashCode;
                Object[] objArr = this.hashTable;
                immutableSetConstruct = new RegularImmutableSet<>(objArrCopyOf, i2, objArr, objArr.length - 1, this.size);
            }
            this.forceCopy = true;
            this.hashTable = null;
            return immutableSetConstruct;
        }

        @Override // com.google.common.collect.ImmutableCollection.ArrayBasedBuilder, com.google.common.collect.ImmutableCollection.Builder
        @CanIgnoreReturnValue
        public Builder<E> addAll(Iterable<? extends E> iterable) {
            if (iterable != null) {
                if (this.hashTable != null) {
                    Iterator<? extends E> it = iterable.iterator();
                    while (it.hasNext()) {
                        add((Object) it.next());
                    }
                } else {
                    super.addAll((Iterable) iterable);
                }
                return this;
            }
            throw new NullPointerException();
        }

        @Override // com.google.common.collect.ImmutableCollection.ArrayBasedBuilder, com.google.common.collect.ImmutableCollection.Builder
        @CanIgnoreReturnValue
        public Builder<E> add(E e2) {
            if (e2 != null) {
                if (this.hashTable != null && ImmutableSet.chooseTableSize(this.size) <= this.hashTable.length) {
                    addDeduping(e2);
                    return this;
                }
                this.hashTable = null;
                super.add((Object) e2);
                return this;
            }
            throw new NullPointerException();
        }

        @Override // com.google.common.collect.ImmutableCollection.Builder
        @CanIgnoreReturnValue
        public Builder<E> addAll(Iterator<? extends E> it) {
            if (it != null) {
                while (it.hasNext()) {
                    add((Object) it.next());
                }
                return this;
            }
            throw new NullPointerException();
        }

        @Override // com.google.common.collect.ImmutableCollection.ArrayBasedBuilder, com.google.common.collect.ImmutableCollection.Builder
        @CanIgnoreReturnValue
        public Builder<E> add(E... eArr) {
            if (this.hashTable != null) {
                for (E e2 : eArr) {
                    add((Object) e2);
                }
            } else {
                addAll(eArr, eArr.length);
            }
            return this;
        }
    }

    private static class SerializedForm implements Serializable {
        public static final long serialVersionUID = 0;
        public final Object[] elements;

        public SerializedForm(Object[] objArr) {
            this.elements = objArr;
        }

        public Object readResolve() {
            return ImmutableSet.copyOf(this.elements);
        }
    }

    public static <E> Builder<E> builder() {
        return new Builder<>();
    }

    @Beta
    public static <E> Builder<E> builderWithExpectedSize(int i) {
        CollectPreconditions.checkNonnegative(i, "expectedSize");
        return new Builder<>(i);
    }

    @VisibleForTesting
    public static int chooseTableSize(int i) {
        int iMax = Math.max(i, 2);
        if (iMax >= 751619276) {
            Preconditions.checkArgument(iMax < 1073741824, "collection too large");
            return 1073741824;
        }
        int iHighestOneBit = Integer.highestOneBit(iMax - 1) << 1;
        while (((double) iHighestOneBit) * 0.7d < iMax) {
            iHighestOneBit <<= 1;
        }
        return iHighestOneBit;
    }

    public static <E> ImmutableSet<E> construct(int i, Object... objArr) {
        if (i == 0) {
            return RegularImmutableSet.EMPTY;
        }
        if (i == 1) {
            return new SingletonImmutableSet(objArr[0]);
        }
        int iChooseTableSize = chooseTableSize(i);
        Object[] objArr2 = new Object[iChooseTableSize];
        int i2 = iChooseTableSize - 1;
        int i3 = 0;
        int i4 = 0;
        for (int i5 = 0; i5 < i; i5++) {
            Object obj = objArr[i5];
            ObjectArrays.checkElementNotNull(obj, i5);
            int iHashCode = obj.hashCode();
            int iSmear = Hashing.smear(iHashCode);
            while (true) {
                int i6 = iSmear & i2;
                Object obj2 = objArr2[i6];
                if (obj2 == null) {
                    objArr[i4] = obj;
                    objArr2[i6] = obj;
                    i3 += iHashCode;
                    i4++;
                    break;
                }
                if (obj2.equals(obj)) {
                    break;
                }
                iSmear++;
            }
        }
        Arrays.fill(objArr, i4, i, (Object) null);
        if (i4 == 1) {
            return new SingletonImmutableSet(objArr[0], i3);
        }
        if (chooseTableSize(i4) < iChooseTableSize / 2) {
            return construct(i4, objArr);
        }
        if (shouldTrim(i4, objArr.length)) {
            objArr = Arrays.copyOf(objArr, i4);
        }
        return new RegularImmutableSet(objArr, i3, objArr2, i2, i4);
    }

    public static <E> ImmutableSet<E> copyOf(Collection<? extends E> collection) {
        if ((collection instanceof ImmutableSet) && !(collection instanceof SortedSet)) {
            ImmutableSet<E> immutableSet = (ImmutableSet) collection;
            if (!immutableSet.isPartialView()) {
                return immutableSet;
            }
        }
        Object[] array = collection.toArray();
        return construct(array.length, array);
    }

    public static <E> ImmutableSet<E> of() {
        return RegularImmutableSet.EMPTY;
    }

    public static boolean shouldTrim(int i, int i2) {
        return i < (i2 >> 1) + (i2 >> 2);
    }

    @Override // com.google.common.collect.ImmutableCollection
    public ImmutableList<E> asList() {
        ImmutableList<E> immutableList = this.asList;
        if (immutableList != null) {
            return immutableList;
        }
        ImmutableList<E> immutableListCreateAsList = createAsList();
        this.asList = immutableListCreateAsList;
        return immutableListCreateAsList;
    }

    public ImmutableList<E> createAsList() {
        return ImmutableList.asImmutableList(toArray());
    }

    @Override // java.util.Collection, java.util.Set
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if ((obj instanceof ImmutableSet) && isHashCodeFast() && ((ImmutableSet) obj).isHashCodeFast() && hashCode() != obj.hashCode()) {
            return false;
        }
        return Sets.equalsImpl(this, obj);
    }

    @Override // java.util.Collection, java.util.Set
    public int hashCode() {
        return Sets.hashCodeImpl(this);
    }

    public boolean isHashCodeFast() {
        return false;
    }

    @Override // com.google.common.collect.ImmutableCollection, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set, java.util.NavigableSet, com.google.common.collect.SortedIterable
    public abstract UnmodifiableIterator<E> iterator();

    @Override // com.google.common.collect.ImmutableCollection
    public Object writeReplace() {
        return new SerializedForm(toArray());
    }

    public static <E> ImmutableSet<E> of(E e2) {
        return new SingletonImmutableSet(e2);
    }

    public static <E> ImmutableSet<E> of(E e2, E e3) {
        return construct(2, e2, e3);
    }

    public static <E> ImmutableSet<E> of(E e2, E e3, E e4) {
        return construct(3, e2, e3, e4);
    }

    public static <E> ImmutableSet<E> of(E e2, E e3, E e4, E e5) {
        return construct(4, e2, e3, e4, e5);
    }

    public static <E> ImmutableSet<E> copyOf(Iterable<? extends E> iterable) {
        if (iterable instanceof Collection) {
            return copyOf((Collection) iterable);
        }
        return copyOf(iterable.iterator());
    }

    public static <E> ImmutableSet<E> of(E e2, E e3, E e4, E e5, E e6) {
        return construct(5, e2, e3, e4, e5, e6);
    }

    @SafeVarargs
    public static <E> ImmutableSet<E> of(E e2, E e3, E e4, E e5, E e6, E e7, E... eArr) {
        Preconditions.checkArgument(eArr.length <= 2147483641, "the total number of elements must fit in an int");
        Object[] objArr = new Object[eArr.length + 6];
        objArr[0] = e2;
        objArr[1] = e3;
        objArr[2] = e4;
        objArr[3] = e5;
        objArr[4] = e6;
        objArr[5] = e7;
        System.arraycopy(eArr, 0, objArr, 6, eArr.length);
        return construct(objArr.length, objArr);
    }

    public static <E> ImmutableSet<E> copyOf(Iterator<? extends E> it) {
        if (!it.hasNext()) {
            return RegularImmutableSet.EMPTY;
        }
        E next = it.next();
        if (!it.hasNext()) {
            return new SingletonImmutableSet(next);
        }
        return new Builder().add((Object) next).addAll((Iterator) it).build();
    }

    public static <E> ImmutableSet<E> copyOf(E[] eArr) {
        int length = eArr.length;
        if (length == 0) {
            return RegularImmutableSet.EMPTY;
        }
        if (length != 1) {
            return construct(eArr.length, (Object[]) eArr.clone());
        }
        return new SingletonImmutableSet(eArr[0]);
    }
}
