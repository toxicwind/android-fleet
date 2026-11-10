package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import java.io.Serializable;
import java.util.Comparator;

/* JADX INFO: loaded from: classes.dex */
@GwtCompatible(serializable = true)
public final class ComparatorOrdering<T> extends Ordering<T> implements Serializable {
    public static final long serialVersionUID = 0;
    public final Comparator<T> comparator;

    public ComparatorOrdering(Comparator<T> comparator) {
        if (comparator == null) {
            throw new NullPointerException();
        }
        this.comparator = comparator;
    }

    @Override // com.google.common.collect.Ordering, java.util.Comparator
    public int compare(T t, T t2) {
        return this.comparator.compare(t, t2);
    }

    @Override // java.util.Comparator
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof ComparatorOrdering) {
            return this.comparator.equals(((ComparatorOrdering) obj).comparator);
        }
        return false;
    }

    public int hashCode() {
        return this.comparator.hashCode();
    }

    public String toString() {
        return this.comparator.toString();
    }
}
