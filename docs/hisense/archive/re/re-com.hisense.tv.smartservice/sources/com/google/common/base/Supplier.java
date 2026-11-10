package com.google.common.base;

import com.google.common.annotations.GwtCompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;

/* JADX INFO: loaded from: classes.dex */
@GwtCompatible
public interface Supplier<T> {
    @CanIgnoreReturnValue
    T get();
}
