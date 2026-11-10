package com.google.common.util.concurrent;

import com.google.common.annotations.GwtCompatible;

/* JADX INFO: loaded from: classes.dex */
@GwtCompatible(emulated = true)
public final class Platform {
    public static boolean isInstanceOfThrowableClass(Throwable th, Class<? extends Throwable> cls) {
        return cls.isInstance(th);
    }
}
