package org.jdeferred2;

/* JADX INFO: loaded from: classes.dex */
public interface CallbackExceptionHandler {

    public enum CallbackType {
        DONE_CALLBACK,
        FAIL_CALLBACK,
        PROGRESS_CALLBACK,
        ALWAYS_CALLBACK
    }

    void handleException(CallbackType callbackType, Exception exc);
}
