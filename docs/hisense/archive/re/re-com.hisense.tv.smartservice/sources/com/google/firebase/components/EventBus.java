package com.google.firebase.components;

import androidx.annotation.GuardedBy;
import com.google.firebase.events.Event;
import com.google.firebase.events.EventHandler;
import com.google.firebase.events.Publisher;
import com.google.firebase.events.Subscriber;
import java.util.ArrayDeque;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes.dex */
public class EventBus implements Subscriber, Publisher {
    public final Executor defaultExecutor;

    @GuardedBy("this")
    public final Map<Class<?>, ConcurrentHashMap<EventHandler<Object>, Executor>> handlerMap = new HashMap();

    @GuardedBy("this")
    public Queue<Event<?>> pendingEvents = new ArrayDeque();

    public EventBus(Executor executor) {
        this.defaultExecutor = executor;
    }

    private synchronized Set<Map.Entry<EventHandler<Object>, Executor>> getHandlers(Event<?> event) {
        ConcurrentHashMap<EventHandler<Object>, Executor> concurrentHashMap;
        concurrentHashMap = this.handlerMap.get(event.getType());
        return concurrentHashMap == null ? Collections.emptySet() : concurrentHashMap.entrySet();
    }

    public void enablePublishingAndFlushPending() {
        Queue<Event<?>> queue;
        synchronized (this) {
            if (this.pendingEvents != null) {
                queue = this.pendingEvents;
                this.pendingEvents = null;
            } else {
                queue = null;
            }
        }
        if (queue != null) {
            Iterator<Event<?>> it = queue.iterator();
            while (it.hasNext()) {
                publish(it.next());
            }
        }
    }

    @Override // com.google.firebase.events.Publisher
    public void publish(final Event<?> event) {
        if (event == null) {
            throw new NullPointerException();
        }
        synchronized (this) {
            if (this.pendingEvents != null) {
                this.pendingEvents.add(event);
                return;
            }
            for (final Map.Entry<EventHandler<Object>, Executor> entry : getHandlers(event)) {
                entry.getValue().execute(new Runnable() { // from class: b.e.b.b.j
                    @Override // java.lang.Runnable
                    public final void run() {
                        ((EventHandler) entry.getKey()).handle(event);
                    }
                });
            }
        }
    }

    @Override // com.google.firebase.events.Subscriber
    public synchronized <T> void subscribe(Class<T> cls, Executor executor, EventHandler<? super T> eventHandler) {
        try {
            if (cls == null) {
                throw new NullPointerException();
            }
            if (eventHandler == null) {
                throw new NullPointerException();
            }
            if (executor == null) {
                throw new NullPointerException();
            }
            if (!this.handlerMap.containsKey(cls)) {
                this.handlerMap.put(cls, new ConcurrentHashMap<>());
            }
            this.handlerMap.get(cls).put(eventHandler, executor);
        } catch (Throwable th) {
            throw th;
        }
    }

    @Override // com.google.firebase.events.Subscriber
    public synchronized <T> void unsubscribe(Class<T> cls, EventHandler<? super T> eventHandler) {
        try {
            if (cls == null) {
                throw new NullPointerException();
            }
            if (eventHandler == null) {
                throw new NullPointerException();
            }
            if (this.handlerMap.containsKey(cls)) {
                ConcurrentHashMap<EventHandler<Object>, Executor> concurrentHashMap = this.handlerMap.get(cls);
                concurrentHashMap.remove(eventHandler);
                if (concurrentHashMap.isEmpty()) {
                    this.handlerMap.remove(cls);
                }
            }
        } catch (Throwable th) {
            throw th;
        }
    }

    @Override // com.google.firebase.events.Subscriber
    public <T> void subscribe(Class<T> cls, EventHandler<? super T> eventHandler) {
        subscribe(cls, this.defaultExecutor, eventHandler);
    }
}
