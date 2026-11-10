package com.google.common.collect;

import b.a.a.a.a;
import com.google.common.annotations.GwtCompatible;
import com.google.errorprone.annotations.concurrent.LazyInit;

/* JADX INFO: loaded from: classes.dex */
@GwtCompatible(emulated = true, serializable = true)
public final class SingletonImmutableSet<E> extends ImmutableSet<E> {

    @LazyInit
    public transient int cachedHashCode;
    public final transient E element;

    public SingletonImmutableSet(E e2) {
        if (e2 == null) {
            throw new NullPointerException();
        }
        this.element = e2;
    }

    @Override // com.google.common.collect.ImmutableCollection, java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean contains(Object obj) {
        return this.element.equals(obj);
    }

    @Override // com.google.common.collect.ImmutableCollection
    public int copyIntoArray(Object[] objArr, int i) {
        objArr[i] = this.element;
        return i + 1;
    }

    @Override // com.google.common.collect.ImmutableSet
    public ImmutableList<E> createAsList() {
        return ImmutableList.of((Object) this.element);
    }

    @Override // com.google.common.collect.ImmutableSet, java.util.Collection, java.util.Set
    public final int hashCode() {
        int i = this.cachedHashCode;
        if (i != 0) {
            return i;
        }
        int iHashCode = this.element.hashCode();
        this.cachedHashCode = iHashCode;
        return iHashCode;
    }

    @Override // com.google.common.collect.ImmutableSet
    public boolean isHashCodeFast() {
        return this.cachedHashCode != 0;
    }

    @Override // com.google.common.collect.ImmutableCollection
    public boolean isPartialView() {
        return false;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public int size() {
        return 1;
    }

    @Override // java.util.AbstractCollection
    public String toString() {
        String string = this.element.toString();
        StringBuilder sb = new StringBuilder(a.a((Object) string, 2));
        sb.append('[');
        sb.append(string);
        sb.append(']');
        return sb.toString();
    }

    @Override // com.google.common.collect.ImmutableSet, com.google.common.collect.ImmutableCollection, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set, java.util.NavigableSet, com.google.common.collect.SortedIterable
    public UnmodifiableIterator<E> iterator() {
        return new Iterators.AnonymousClass9(this.element);
    }

    public SingletonImmutableSet(E e2, int i) {
        this.element = e2;
        this.cachedHashCode = i;
    }
}
