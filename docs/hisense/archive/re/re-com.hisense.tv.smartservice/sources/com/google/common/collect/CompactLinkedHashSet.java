package com.google.common.collect;

import com.google.common.annotations.GwtIncompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
@GwtIncompatible
public class CompactLinkedHashSet<E> extends CompactHashSet<E> {
    public static final int ENDPOINT = -2;
    public transient int firstEntry;
    public transient int lastEntry;
    public transient int[] predecessor;
    public transient int[] successor;

    public CompactLinkedHashSet() {
    }

    public static <E> CompactLinkedHashSet<E> create() {
        return new CompactLinkedHashSet<>();
    }

    public static <E> CompactLinkedHashSet<E> createWithExpectedSize(int i) {
        return new CompactLinkedHashSet<>(i);
    }

    private int getPredecessor(int i) {
        return this.predecessor[i] - 1;
    }

    private void setPredecessor(int i, int i2) {
        this.predecessor[i] = i2 + 1;
    }

    private void setSucceeds(int i, int i2) {
        if (i == -2) {
            this.firstEntry = i2;
        } else {
            this.successor[i] = i2 + 1;
        }
        if (i2 == -2) {
            this.lastEntry = i;
        } else {
            this.predecessor[i2] = i + 1;
        }
    }

    private void setSuccessor(int i, int i2) {
        this.successor[i] = i2 + 1;
    }

    @Override // com.google.common.collect.CompactHashSet
    public int adjustAfterRemove(int i, int i2) {
        return i >= size() ? i2 : i;
    }

    @Override // com.google.common.collect.CompactHashSet
    public int allocArrays() {
        int iAllocArrays = super.allocArrays();
        this.predecessor = new int[iAllocArrays];
        this.successor = new int[iAllocArrays];
        return iAllocArrays;
    }

    @Override // com.google.common.collect.CompactHashSet, java.util.AbstractCollection, java.util.Collection, java.util.Set
    public void clear() {
        if (needsAllocArrays()) {
            return;
        }
        this.firstEntry = -2;
        this.lastEntry = -2;
        int[] iArr = this.predecessor;
        if (iArr != null) {
            Arrays.fill(iArr, 0, size(), 0);
            Arrays.fill(this.successor, 0, size(), 0);
        }
        super.clear();
    }

    @Override // com.google.common.collect.CompactHashSet
    @CanIgnoreReturnValue
    public Set<E> convertToHashFloodingResistantImplementation() {
        Set<E> setConvertToHashFloodingResistantImplementation = super.convertToHashFloodingResistantImplementation();
        this.predecessor = null;
        this.successor = null;
        return setConvertToHashFloodingResistantImplementation;
    }

    @Override // com.google.common.collect.CompactHashSet
    public int firstEntryIndex() {
        return this.firstEntry;
    }

    @Override // com.google.common.collect.CompactHashSet
    public int getSuccessor(int i) {
        return this.successor[i] - 1;
    }

    @Override // com.google.common.collect.CompactHashSet
    public void init(int i) {
        super.init(i);
        this.firstEntry = -2;
        this.lastEntry = -2;
    }

    @Override // com.google.common.collect.CompactHashSet
    public void insertEntry(int i, E e2, int i2, int i3) {
        super.insertEntry(i, e2, i2, i3);
        setSucceeds(this.lastEntry, i);
        setSucceeds(i, -2);
    }

    @Override // com.google.common.collect.CompactHashSet
    public void moveLastEntry(int i, int i2) {
        int size = size() - 1;
        super.moveLastEntry(i, i2);
        setSucceeds(this.predecessor[i] - 1, getSuccessor(i));
        if (i < size) {
            setSucceeds(getPredecessor(size), i);
            setSucceeds(i, getSuccessor(size));
        }
        this.predecessor[size] = 0;
        this.successor[size] = 0;
    }

    @Override // com.google.common.collect.CompactHashSet
    public void resizeEntries(int i) {
        super.resizeEntries(i);
        this.predecessor = Arrays.copyOf(this.predecessor, i);
        this.successor = Arrays.copyOf(this.successor, i);
    }

    @Override // com.google.common.collect.CompactHashSet, java.util.AbstractCollection, java.util.Collection, java.util.Set
    public Object[] toArray() {
        Object[] objArr = new Object[size()];
        ObjectArrays.fillArray(this, objArr);
        return objArr;
    }

    public CompactLinkedHashSet(int i) {
        super(i);
    }

    public static <E> CompactLinkedHashSet<E> create(Collection<? extends E> collection) {
        CompactLinkedHashSet<E> compactLinkedHashSet = new CompactLinkedHashSet<>(collection.size());
        compactLinkedHashSet.addAll(collection);
        return compactLinkedHashSet;
    }

    @Override // com.google.common.collect.CompactHashSet, java.util.AbstractCollection, java.util.Collection, java.util.Set
    public <T> T[] toArray(T[] tArr) {
        return (T[]) ObjectArrays.toArrayImpl(this, tArr);
    }

    @SafeVarargs
    public static <E> CompactLinkedHashSet<E> create(E... eArr) {
        CompactLinkedHashSet<E> compactLinkedHashSet = new CompactLinkedHashSet<>(eArr.length);
        Collections.addAll(compactLinkedHashSet, eArr);
        return compactLinkedHashSet;
    }
}
