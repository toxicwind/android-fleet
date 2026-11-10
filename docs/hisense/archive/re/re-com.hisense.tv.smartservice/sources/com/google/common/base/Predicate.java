package com.google.common.base;

import com.google.common.annotations.GwtCompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;

/* JADX INFO: loaded from: classes.dex */
@GwtCompatible
public interface Predicate<T> {
    @CanIgnoreReturnValue
    boolean apply(T t);

    boolean equals(Object obj);
}
