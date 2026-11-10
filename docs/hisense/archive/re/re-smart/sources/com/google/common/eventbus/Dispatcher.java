package com.google.common.eventbus;

import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/* JADX INFO: loaded from: classes.dex */
public abstract class Dispatcher {

    private static final class ImmediateDispatcher extends Dispatcher {
        public static final ImmediateDispatcher INSTANCE = new ImmediateDispatcher();

        @Override // com.google.common.eventbus.Dispatcher
        public void dispatch(Object obj, Iterator<Subscriber> it) {
            if (obj == null) {
                throw new NullPointerException();
            }
            while (it.hasNext()) {
                it.next().dispatchEvent(obj);
            }
        }
    }

    public static Dispatcher immediate() {
        return ImmediateDispatcher.INSTANCE;
    }

    public static Dispatcher legacyAsync() {
        return new LegacyAsyncDispatcher();
    }

    public static Dispatcher perThreadDispatchQueue() {
        return new PerThreadQueuedDispatcher();
    }

    public abstract void dispatch(Object obj, Iterator<Subscriber> it);

    /* JADX INFO: Access modifiers changed from: private */
    static final class LegacyAsyncDispatcher extends Dispatcher {
        public final ConcurrentLinkedQueue<EventWithSubscriber> queue;

        public LegacyAsyncDispatcher() {
            this.queue = new ConcurrentLinkedQueue<>();
        }

        @Override // com.google.common.eventbus.Dispatcher
        public void dispatch(Object obj, Iterator<Subscriber> it) {
            if (obj == null) {
                throw new NullPointerException();
            }
            while (it.hasNext()) {
                this.queue.add(new EventWithSubscriber(obj, it.next(), null));
            }
            while (true) {
                EventWithSubscriber eventWithSubscriberPoll = this.queue.poll();
                if (eventWithSubscriberPoll == null) {
                    return;
                } else {
                    eventWithSubscriberPoll.subscriber.dispatchEvent(eventWithSubscriberPoll.event);
                }
            }
        }

        private static final class EventWithSubscriber {
            public final Object event;
            public final Subscriber subscriber;

            public EventWithSubscriber(Object obj, Subscriber subscriber) {
                this.event = obj;
                this.subscriber = subscriber;
            }

            public /* synthetic */ EventWithSubscriber(Object obj, Subscriber subscriber, AnonymousClass1 anonymousClass1) {
                this.event = obj;
                this.subscriber = subscriber;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    static final class PerThreadQueuedDispatcher extends Dispatcher {
        public final ThreadLocal<Boolean> dispatching;
        public final ThreadLocal<Queue<Event>> queue;

        public PerThreadQueuedDispatcher() {
            this.queue = new ThreadLocal<Queue<Event>>(this) { // from class: com.google.common.eventbus.Dispatcher.PerThreadQueuedDispatcher.1
                @Override // java.lang.ThreadLocal
                public Queue<Event> initialValue() {
                    return new ArrayDeque();
                }
            };
            this.dispatching = new ThreadLocal<Boolean>(this) { // from class: com.google.common.eventbus.Dispatcher.PerThreadQueuedDispatcher.2
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // java.lang.ThreadLocal
                public Boolean initialValue() {
                    return false;
                }
            };
        }

        @Override // com.google.common.eventbus.Dispatcher
        public void dispatch(Object obj, Iterator<Subscriber> it) {
            if (obj == null) {
                throw new NullPointerException();
            }
            if (it == null) {
                throw new NullPointerException();
            }
            Queue<Event> queue = this.queue.get();
            queue.offer(new Event(obj, it, null));
            if (this.dispatching.get().booleanValue()) {
                return;
            }
            this.dispatching.set(true);
            while (true) {
                try {
                    Event eventPoll = queue.poll();
                    if (eventPoll == null) {
                        this.dispatching.remove();
                        this.queue.remove();
                        return;
                    } else {
                        while (eventPoll.subscribers.hasNext()) {
                            eventPoll.subscribers.next().dispatchEvent(eventPoll.event);
                        }
                    }
                } catch (Throwable th) {
                    this.dispatching.remove();
                    this.queue.remove();
                    throw th;
                }
            }
        }

        private static final class Event {
            public final Object event;
            public final Iterator<Subscriber> subscribers;

            public Event(Object obj, Iterator<Subscriber> it) {
                this.event = obj;
                this.subscribers = it;
            }

            public /* synthetic */ Event(Object obj, Iterator it, AnonymousClass1 anonymousClass1) {
                this.event = obj;
                this.subscribers = it;
            }
        }
    }
}
