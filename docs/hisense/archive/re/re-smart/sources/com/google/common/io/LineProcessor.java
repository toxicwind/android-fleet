package com.google.common.io;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;

/* JADX INFO: loaded from: classes.dex */
@Beta
@GwtIncompatible
public interface LineProcessor<T> {
    T getResult();

    @CanIgnoreReturnValue
    boolean processLine(String str);
}
