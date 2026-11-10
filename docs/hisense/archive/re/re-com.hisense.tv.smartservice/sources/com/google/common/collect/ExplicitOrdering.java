package com.google.common.collect;

import b.a.a.a.a;
import com.google.common.annotations.GwtCompatible;
import java.io.Serializable;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
@GwtCompatible(serializable = true)
public final class ExplicitOrdering<T> extends Ordering<T> implements Serializable {
    public static final long serialVersionUID = 0;
    public final ImmutableMap<T, Integer> rankMap;

    public ExplicitOrdering(List<T> list) {
        this.rankMap = Maps.indexMap(list);
    }

    private int rank(T t) {
        Integer num = this.rankMap.get(t);
        if (num != null) {
            return num.intValue();
        }
        throw new Ordering.IncomparableValueException(t);
    }

    @Override // com.google.common.collect.Ordering, java.util.Comparator
    public int compare(T t, T t2) {
        return rank(t) - rank(t2);
    }

    @Override // java.util.Comparator
    public boolean equals(Object obj) {
        if (obj instanceof ExplicitOrdering) {
            return this.rankMap.equals(((ExplicitOrdering) obj).rankMap);
        }
        return false;
    }

    public int hashCode() {
        return this.rankMap.hashCode();
    }

    public String toString() {
        String strValueOf = String.valueOf(this.rankMap.keySet());
        return a.a(strValueOf.length() + 19, "Ordering.explicit(", strValueOf, ")");
    }

    public ExplicitOrdering(ImmutableMap<T, Integer> immutableMap) {
        this.rankMap = immutableMap;
    }
}
