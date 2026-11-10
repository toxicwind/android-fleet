package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;

/* JADX INFO: loaded from: classes.dex */
@GwtCompatible
@Deprecated
public class ComputationException extends RuntimeException {
    public static final long serialVersionUID = 0;

    public ComputationException(Throwable th) {
        super(th);
    }
}
