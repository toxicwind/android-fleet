package org.jdeferred2.impl;

import org.jdeferred2.CallbackExceptionHandler;

/* JADX INFO: loaded from: classes.dex */
public final class GlobalConfiguration {
    private static CallbackExceptionHandler globalCallbackExceptionHandler = new DefaultCallbackExceptionHandler();

    public static CallbackExceptionHandler getGlobalCallbackExceptionHandler() {
        return globalCallbackExceptionHandler;
    }
}
