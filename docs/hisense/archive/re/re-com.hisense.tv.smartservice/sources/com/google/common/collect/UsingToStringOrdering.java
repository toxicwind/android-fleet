package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import java.io.Serializable;

/* JADX INFO: loaded from: classes.dex */
@GwtCompatible(serializable = true)
public final class UsingToStringOrdering extends Ordering<Object> implements Serializable {
    public static final UsingToStringOrdering INSTANCE = new UsingToStringOrdering();
    public static final long serialVersionUID = 0;

    private Object readResolve() {
        return INSTANCE;
    }

    @Override // com.google.common.collect.Ordering, java.util.Comparator
    public int compare(Object obj, Object obj2) {
        return obj.toString().compareTo(obj2.toString());
    }

    public String toString() {
        return "Ordering.usingToString()";
    }
}
