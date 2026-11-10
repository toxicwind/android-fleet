package com.google.common.util.concurrent;

import com.google.common.annotations.GwtCompatible;

/* JADX INFO: loaded from: classes.dex */
@GwtCompatible
public class UncheckedExecutionException extends RuntimeException {
    public static final long serialVersionUID = 0;

    public UncheckedExecutionException() {
    }

    public UncheckedExecutionException(String str) {
        super(str);
    }

    public UncheckedExecutionException(String str, Throwable th) {
        super(str, th);
    }

    public UncheckedExecutionException(Throwable th) {
        super(th);
    }
}
