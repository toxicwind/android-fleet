package com.google.common.util.concurrent;

import com.google.common.annotations.GwtCompatible;

/* JADX INFO: loaded from: classes.dex */
@GwtCompatible
public class ExecutionError extends Error {
    public static final long serialVersionUID = 0;

    public ExecutionError() {
    }

    public ExecutionError(String str) {
        super(str);
    }

    public ExecutionError(String str, Error error) {
        super(str, error);
    }

    public ExecutionError(Error error) {
        super(error);
    }
}
