package com.google.common.collect;

import b.a.a.a.a;
import com.google.common.annotations.GwtCompatible;
import java.io.Serializable;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
@GwtCompatible(serializable = true)
public final class ReverseOrdering<T> extends Ordering<T> implements Serializable {
    public static final long serialVersionUID = 0;
    public final Ordering<? super T> forwardOrder;

    public ReverseOrdering(Ordering<? super T> ordering) {
        if (ordering == null) {
            throw new NullPointerException();
        }
        this.forwardOrder = ordering;
    }

    @Override // com.google.common.collect.Ordering, java.util.Comparator
    public int compare(T t, T t2) {
        return this.forwardOrder.compare(t2, t);
    }

    @Override // java.util.Comparator
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof ReverseOrdering) {
            return this.forwardOrder.equals(((ReverseOrdering) obj).forwardOrder);
        }
        return false;
    }

    public int hashCode() {
        return -this.forwardOrder.hashCode();
    }

    @Override // com.google.common.collect.Ordering
    public <E extends T> E max(E e2, E e3) {
        return (E) this.forwardOrder.min(e2, e3);
    }

    @Override // com.google.common.collect.Ordering
    public <E extends T> E min(E e2, E e3) {
        return (E) this.forwardOrder.max(e2, e3);
    }

    @Override // com.google.common.collect.Ordering
    public <S extends T> Ordering<S> reverse() {
        return this.forwardOrder;
    }

    public String toString() {
        String strValueOf = String.valueOf(this.forwardOrder);
        return a.a(strValueOf.length() + 10, strValueOf, ".reverse()");
    }

    @Override // com.google.common.collect.Ordering
    public <E extends T> E max(E e2, E e3, E e4, E... eArr) {
        return (E) this.forwardOrder.min(e2, e3, e4, eArr);
    }

    @Override // com.google.common.collect.Ordering
    public <E extends T> E min(E e2, E e3, E e4, E... eArr) {
        return (E) this.forwardOrder.max(e2, e3, e4, eArr);
    }

    @Override // com.google.common.collect.Ordering
    public <E extends T> E max(Iterator<E> it) {
        return (E) this.forwardOrder.min(it);
    }

    @Override // com.google.common.collect.Ordering
    public <E extends T> E min(Iterator<E> it) {
        return (E) this.forwardOrder.max(it);
    }

    @Override // com.google.common.collect.Ordering
    public <E extends T> E max(Iterable<E> iterable) {
        return (E) this.forwardOrder.min(iterable);
    }

    @Override // com.google.common.collect.Ordering
    public <E extends T> E min(Iterable<E> iterable) {
        return (E) this.forwardOrder.max(iterable);
    }
}
