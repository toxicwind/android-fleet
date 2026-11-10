package com.google.common.eventbus;

import com.google.common.annotations.Beta;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes.dex */
@Beta
public class AsyncEventBus extends EventBus {
    public AsyncEventBus(String str, Executor executor) {
        super(str, executor, new Dispatcher.LegacyAsyncDispatcher(), EventBus.LoggingHandler.INSTANCE);
    }

    public AsyncEventBus(Executor executor) {
        super("default", executor, new Dispatcher.LegacyAsyncDispatcher(), EventBus.LoggingHandler.INSTANCE);
    }

    public AsyncEventBus(Executor executor, SubscriberExceptionHandler subscriberExceptionHandler) {
        super("default", executor, new Dispatcher.LegacyAsyncDispatcher(), subscriberExceptionHandler);
    }
}
