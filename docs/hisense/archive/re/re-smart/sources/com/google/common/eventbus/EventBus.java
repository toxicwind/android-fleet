package com.google.common.eventbus;

import b.a.a.a.a;
import com.google.common.annotations.Beta;
import com.google.common.base.MoreObjects;
import com.google.common.util.concurrent.DirectExecutor;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Locale;
import java.util.concurrent.Executor;
import java.util.logging.Level;
import java.util.logging.Logger;

/* JADX INFO: loaded from: classes.dex */
@Beta
public class EventBus {
    public static final Logger logger = Logger.getLogger(EventBus.class.getName());
    public final Dispatcher dispatcher;
    public final SubscriberExceptionHandler exceptionHandler;
    public final Executor executor;
    public final String identifier;
    public final SubscriberRegistry subscribers;

    static final class LoggingHandler implements SubscriberExceptionHandler {
        public static final LoggingHandler INSTANCE = new LoggingHandler();

        public static Logger logger(SubscriberExceptionContext subscriberExceptionContext) {
            String name = EventBus.class.getName();
            String strIdentifier = subscriberExceptionContext.getEventBus().identifier();
            StringBuilder sb = new StringBuilder(a.a((Object) strIdentifier, name.length() + 1));
            sb.append(name);
            sb.append(".");
            sb.append(strIdentifier);
            return Logger.getLogger(sb.toString());
        }

        public static String message(SubscriberExceptionContext subscriberExceptionContext) {
            Method subscriberMethod = subscriberExceptionContext.getSubscriberMethod();
            String name = subscriberMethod.getName();
            String name2 = subscriberMethod.getParameterTypes()[0].getName();
            String strValueOf = String.valueOf(subscriberExceptionContext.getSubscriber());
            String strValueOf2 = String.valueOf(subscriberExceptionContext.getEvent());
            StringBuilder sb = new StringBuilder(strValueOf2.length() + strValueOf.length() + name2.length() + a.a((Object) name, 80));
            sb.append("Exception thrown by subscriber method ");
            sb.append(name);
            sb.append('(');
            sb.append(name2);
            sb.append(')');
            sb.append(" on subscriber ");
            sb.append(strValueOf);
            sb.append(" when dispatching event: ");
            sb.append(strValueOf2);
            return sb.toString();
        }

        @Override // com.google.common.eventbus.SubscriberExceptionHandler
        public void handleException(Throwable th, SubscriberExceptionContext subscriberExceptionContext) {
            Logger logger = logger(subscriberExceptionContext);
            if (logger.isLoggable(Level.SEVERE)) {
                logger.log(Level.SEVERE, message(subscriberExceptionContext), th);
            }
        }
    }

    public EventBus() {
        this("default");
    }

    public final Executor executor() {
        return this.executor;
    }

    public void handleSubscriberException(Throwable th, SubscriberExceptionContext subscriberExceptionContext) {
        if (th == null) {
            throw new NullPointerException();
        }
        if (subscriberExceptionContext == null) {
            throw new NullPointerException();
        }
        try {
            this.exceptionHandler.handleException(th, subscriberExceptionContext);
        } catch (Throwable th2) {
            logger.log(Level.SEVERE, String.format(Locale.ROOT, "Exception %s thrown while handling exception: %s", th2, th), th2);
        }
    }

    public final String identifier() {
        return this.identifier;
    }

    public void post(Object obj) {
        Iterator<Subscriber> subscribers = this.subscribers.getSubscribers(obj);
        if (subscribers.hasNext()) {
            this.dispatcher.dispatch(obj, subscribers);
        } else {
            if (obj instanceof DeadEvent) {
                return;
            }
            post(new DeadEvent(this, obj));
        }
    }

    public void register(Object obj) {
        this.subscribers.register(obj);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).addValue(this.identifier).toString();
    }

    public void unregister(Object obj) {
        this.subscribers.unregister(obj);
    }

    public EventBus(String str, Executor executor, Dispatcher dispatcher, SubscriberExceptionHandler subscriberExceptionHandler) {
        this.subscribers = new SubscriberRegistry(this);
        if (str == null) {
            throw new NullPointerException();
        }
        this.identifier = str;
        if (executor == null) {
            throw new NullPointerException();
        }
        this.executor = executor;
        if (dispatcher == null) {
            throw new NullPointerException();
        }
        this.dispatcher = dispatcher;
        if (subscriberExceptionHandler == null) {
            throw new NullPointerException();
        }
        this.exceptionHandler = subscriberExceptionHandler;
    }

    public EventBus(SubscriberExceptionHandler subscriberExceptionHandler) {
        this("default", DirectExecutor.INSTANCE, new Dispatcher.PerThreadQueuedDispatcher(), subscriberExceptionHandler);
    }

    public EventBus(String str) {
        this(str, DirectExecutor.INSTANCE, new Dispatcher.PerThreadQueuedDispatcher(), LoggingHandler.INSTANCE);
    }
}
