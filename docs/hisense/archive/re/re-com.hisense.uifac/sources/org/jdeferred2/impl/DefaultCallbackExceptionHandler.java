package org.jdeferred2.impl;

import org.jdeferred2.CallbackExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes.dex */
public class DefaultCallbackExceptionHandler implements CallbackExceptionHandler {
    private static final Logger LOG = LoggerFactory.getLogger((Class<?>) DefaultCallbackExceptionHandler.class);

    @Override // org.jdeferred2.CallbackExceptionHandler
    public void handleException(CallbackExceptionHandler.CallbackType callbackType, Exception exc) {
        LOG.error("An uncaught exception occurred in " + callbackType, exc);
    }
}
