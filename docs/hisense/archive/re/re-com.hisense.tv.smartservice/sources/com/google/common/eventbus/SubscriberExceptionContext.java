package com.google.common.eventbus;

import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes.dex */
public class SubscriberExceptionContext {
    public final Object event;
    public final EventBus eventBus;
    public final Object subscriber;
    public final Method subscriberMethod;

    public SubscriberExceptionContext(EventBus eventBus, Object obj, Object obj2, Method method) {
        if (eventBus == null) {
            throw new NullPointerException();
        }
        this.eventBus = eventBus;
        if (obj == null) {
            throw new NullPointerException();
        }
        this.event = obj;
        if (obj2 == null) {
            throw new NullPointerException();
        }
        this.subscriber = obj2;
        if (method == null) {
            throw new NullPointerException();
        }
        this.subscriberMethod = method;
    }

    public Object getEvent() {
        return this.event;
    }

    public EventBus getEventBus() {
        return this.eventBus;
    }

    public Object getSubscriber() {
        return this.subscriber;
    }

    public Method getSubscriberMethod() {
        return this.subscriberMethod;
    }
}
