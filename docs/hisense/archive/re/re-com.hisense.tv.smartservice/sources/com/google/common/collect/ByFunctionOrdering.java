package com.google.common.collect;

import b.a.a.a.a;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Function;
import java.io.Serializable;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
@GwtCompatible(serializable = true)
public final class ByFunctionOrdering<F, T> extends Ordering<F> implements Serializable {
    public static final long serialVersionUID = 0;
    public final Function<F, ? extends T> function;
    public final Ordering<T> ordering;

    public ByFunctionOrdering(Function<F, ? extends T> function, Ordering<T> ordering) {
        if (function == null) {
            throw new NullPointerException();
        }
        this.function = function;
        if (ordering == null) {
            throw new NullPointerException();
        }
        this.ordering = ordering;
    }

    @Override // com.google.common.collect.Ordering, java.util.Comparator
    public int compare(F f2, F f3) {
        return this.ordering.compare(this.function.apply(f2), this.function.apply(f3));
    }

    @Override // java.util.Comparator
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ByFunctionOrdering)) {
            return false;
        }
        ByFunctionOrdering byFunctionOrdering = (ByFunctionOrdering) obj;
        return this.function.equals(byFunctionOrdering.function) && this.ordering.equals(byFunctionOrdering.ordering);
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.function, this.ordering});
    }

    public String toString() {
        String strValueOf = String.valueOf(this.ordering);
        String strValueOf2 = String.valueOf(this.function);
        return a.b(strValueOf2.length() + strValueOf.length() + 13, strValueOf, ".onResultOf(", strValueOf2, ")");
    }
}
