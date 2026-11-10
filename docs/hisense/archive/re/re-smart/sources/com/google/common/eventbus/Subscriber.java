package com.google.common.eventbus;

import b.a.a.a.a;
import com.google.common.annotations.VisibleForTesting;
import com.google.j2objc.annotations.Weak;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes.dex */
public class Subscriber {

    @Weak
    public EventBus bus;
    public final Executor executor;
    public final Method method;

    @VisibleForTesting
    public final Object target;

    @VisibleForTesting
    static final class SynchronizedSubscriber extends Subscriber {
        public SynchronizedSubscriber(EventBus eventBus, Object obj, Method method) {
            super(eventBus, obj, method);
        }

        @Override // com.google.common.eventbus.Subscriber
        public void invokeSubscriberMethod(Object obj) {
            synchronized (this) {
                super.invokeSubscriberMethod(obj);
            }
        }

        public /* synthetic */ SynchronizedSubscriber(EventBus eventBus, Object obj, Method method, AnonymousClass1 anonymousClass1) {
            super(eventBus, obj, method);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public SubscriberExceptionContext context(Object obj) {
        return new SubscriberExceptionContext(this.bus, obj, this.target, this.method);
    }

    public static Subscriber create(EventBus eventBus, Object obj, Method method) {
        return isDeclaredThreadSafe(method) ? new Subscriber(eventBus, obj, method) : new SynchronizedSubscriber(eventBus, obj, method, null);
    }

    public static boolean isDeclaredThreadSafe(Method method) {
        return method.getAnnotation(AllowConcurrentEvents.class) != null;
    }

    public final void dispatchEvent(final Object obj) {
        this.executor.execute(new Runnable() { // from class: com.google.common.eventbus.Subscriber.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    Subscriber.this.invokeSubscriberMethod(obj);
                } catch (InvocationTargetException e2) {
                    Subscriber.this.bus.handleSubscriberException(e2.getCause(), Subscriber.this.context(obj));
                }
            }
        });
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof Subscriber)) {
            return false;
        }
        Subscriber subscriber = (Subscriber) obj;
        return this.target == subscriber.target && this.method.equals(subscriber.method);
    }

    public final int hashCode() {
        return System.identityHashCode(this.target) + ((this.method.hashCode() + 31) * 31);
    }

    @VisibleForTesting
    public void invokeSubscriberMethod(Object obj) throws InvocationTargetException {
        try {
            Method method = this.method;
            Object obj2 = this.target;
            Object[] objArr = new Object[1];
            if (obj == null) {
                throw new NullPointerException();
            }
            objArr[0] = obj;
            method.invoke(obj2, objArr);
        } catch (IllegalAccessException e2) {
            String strValueOf = String.valueOf(obj);
            throw new Error(a.a(strValueOf.length() + 28, "Method became inaccessible: ", strValueOf), e2);
        } catch (IllegalArgumentException e3) {
            String strValueOf2 = String.valueOf(obj);
            throw new Error(a.a(strValueOf2.length() + 33, "Method rejected target/argument: ", strValueOf2), e3);
        } catch (InvocationTargetException e4) {
            if (!(e4.getCause() instanceof Error)) {
                throw e4;
            }
            throw ((Error) e4.getCause());
        }
    }

    public Subscriber(EventBus eventBus, Object obj, Method method) {
        this.bus = eventBus;
        if (obj == null) {
            throw new NullPointerException();
        }
        this.target = obj;
        this.method = method;
        method.setAccessible(true);
        this.executor = eventBus.executor();
    }
}
