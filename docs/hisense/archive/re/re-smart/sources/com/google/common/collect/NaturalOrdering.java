package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import java.io.Serializable;

/* JADX INFO: loaded from: classes.dex */
@GwtCompatible(serializable = true)
public final class NaturalOrdering extends Ordering<Comparable> implements Serializable {
    public static final NaturalOrdering INSTANCE = new NaturalOrdering();
    public static final long serialVersionUID = 0;
    public transient Ordering<Comparable> nullsFirst;
    public transient Ordering<Comparable> nullsLast;

    private Object readResolve() {
        return INSTANCE;
    }

    @Override // com.google.common.collect.Ordering
    public <S extends Comparable> Ordering<S> nullsFirst() {
        Ordering<S> ordering = (Ordering<S>) this.nullsFirst;
        if (ordering != null) {
            return ordering;
        }
        NullsFirstOrdering nullsFirstOrdering = new NullsFirstOrdering(this);
        this.nullsFirst = nullsFirstOrdering;
        return nullsFirstOrdering;
    }

    @Override // com.google.common.collect.Ordering
    public <S extends Comparable> Ordering<S> nullsLast() {
        Ordering<S> ordering = (Ordering<S>) this.nullsLast;
        if (ordering != null) {
            return ordering;
        }
        NullsLastOrdering nullsLastOrdering = new NullsLastOrdering(this);
        this.nullsLast = nullsLastOrdering;
        return nullsLastOrdering;
    }

    @Override // com.google.common.collect.Ordering
    public <S extends Comparable> Ordering<S> reverse() {
        return ReverseNaturalOrdering.INSTANCE;
    }

    public String toString() {
        return "Ordering.natural()";
    }

    @Override // com.google.common.collect.Ordering, java.util.Comparator
    public int compare(Comparable comparable, Comparable comparable2) {
        if (comparable == null) {
            throw new NullPointerException();
        }
        if (comparable2 != null) {
            return comparable.compareTo(comparable2);
        }
        throw new NullPointerException();
    }
}
