package com.google.common.util.concurrent;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;

/* JADX INFO: loaded from: classes.dex */
@Beta
@GwtCompatible
public final class Runnables {
    public static final Runnable EMPTY_RUNNABLE = new Runnable() { // from class: com.google.common.util.concurrent.Runnables.1
        @Override // java.lang.Runnable
        public void run() {
        }
    };

    public static Runnable doNothing() {
        return EMPTY_RUNNABLE;
    }
}
