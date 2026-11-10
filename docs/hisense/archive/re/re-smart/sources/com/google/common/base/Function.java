package com.google.common.base;

import com.google.common.annotations.GwtCompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;

/* JADX INFO: loaded from: classes.dex */
@GwtCompatible
public interface Function<F, T> {
    @CanIgnoreReturnValue
    T apply(F f2);

    boolean equals(Object obj);
}
