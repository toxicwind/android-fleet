package com.google.common.util.concurrent;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationCompatJellybean;
import b.a.a.a.a;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.concurrent.GuardedBy;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.Executor;
import java.util.logging.Level;
import java.util.logging.Logger;

/* JADX INFO: loaded from: classes.dex */
@GwtIncompatible
public final class ListenerCallQueue<L> {
    public static final Logger logger = Logger.getLogger(ListenerCallQueue.class.getName());
    public final List<PerListenerQueue<L>> listeners = Collections.synchronizedList(new ArrayList());

    interface Event<L> {
        void call(L l);
    }

    private static final class PerListenerQueue<L> implements Runnable {
        public final Executor executor;

        @GuardedBy("this")
        public boolean isThreadScheduled;
        public final L listener;

        @GuardedBy("this")
        public final Queue<Event<L>> waitQueue = new ArrayDeque();

        @GuardedBy("this")
        public final Queue<Object> labelQueue = new ArrayDeque();

        public PerListenerQueue(L l, Executor executor) {
            if (l == null) {
                throw new NullPointerException();
            }
            this.listener = l;
            if (executor == null) {
                throw new NullPointerException();
            }
            this.executor = executor;
        }

        public synchronized void add(Event<L> event, Object obj) {
            this.waitQueue.add(event);
            this.labelQueue.add(obj);
        }

        public void dispatch() {
            boolean z;
            synchronized (this) {
                z = true;
                if (this.isThreadScheduled) {
                    z = false;
                } else {
                    this.isThreadScheduled = true;
                }
            }
            if (z) {
                try {
                    this.executor.execute(this);
                } catch (RuntimeException e2) {
                    synchronized (this) {
                        this.isThreadScheduled = false;
                        Logger logger = ListenerCallQueue.logger;
                        Level level = Level.SEVERE;
                        String strValueOf = String.valueOf(this.listener);
                        String strValueOf2 = String.valueOf(this.executor);
                        logger.log(level, a.b(strValueOf2.length() + strValueOf.length() + 42, "Exception while running callbacks for ", strValueOf, " on ", strValueOf2), (Throwable) e2);
                        throw e2;
                    }
                }
            }
        }

        /* JADX WARN: Bottom block not found for handler: all -> 0x005e */
        /* JADX WARN: Code restructure failed: missing block: B:12:0x0021, code lost:
        
            r2.call(r9.listener);
         */
        /* JADX WARN: Code restructure failed: missing block: B:14:0x0027, code lost:
        
            r2 = move-exception;
         */
        /* JADX WARN: Code restructure failed: missing block: B:15:0x0028, code lost:
        
            r4 = com.google.common.util.concurrent.ListenerCallQueue.logger;
            r5 = java.util.logging.Level.SEVERE;
            r6 = java.lang.String.valueOf(r9.listener);
            r3 = java.lang.String.valueOf(r3);
            r8 = new java.lang.StringBuilder((r6.length() + 37) + r3.length());
            r8.append("Exception while executing callback: ");
            r8.append(r6);
            r8.append(" ");
            r8.append(r3);
            r4.log(r5, r8.toString(), (java.lang.Throwable) r2);
         */
        /* JADX WARN: Code restructure failed: missing block: B:40:0x005f, code lost:
        
            r2 = th;
         */
        @Override // java.lang.Runnable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public void run() {
            /*
                r9 = this;
            L0:
                r0 = 0
                r1 = 1
                monitor-enter(r9)     // Catch: java.lang.Throwable -> L61
                boolean r2 = r9.isThreadScheduled     // Catch: java.lang.Throwable -> L5e
                com.google.common.base.Preconditions.checkState(r2)     // Catch: java.lang.Throwable -> L5e
                java.util.Queue<com.google.common.util.concurrent.ListenerCallQueue$Event<L>> r2 = r9.waitQueue     // Catch: java.lang.Throwable -> L5e
                java.lang.Object r2 = r2.poll()     // Catch: java.lang.Throwable -> L5e
                com.google.common.util.concurrent.ListenerCallQueue$Event r2 = (com.google.common.util.concurrent.ListenerCallQueue.Event) r2     // Catch: java.lang.Throwable -> L5e
                java.util.Queue<java.lang.Object> r3 = r9.labelQueue     // Catch: java.lang.Throwable -> L5e
                java.lang.Object r3 = r3.poll()     // Catch: java.lang.Throwable -> L5e
                if (r2 != 0) goto L20
                r9.isThreadScheduled = r0     // Catch: java.lang.Throwable -> L5e
                monitor-exit(r9)     // Catch: java.lang.Throwable -> L1c
                return
            L1c:
                r1 = move-exception
                r2 = r1
                r1 = r0
                goto L5f
            L20:
                monitor-exit(r9)     // Catch: java.lang.Throwable -> L5e
                L r4 = r9.listener     // Catch: java.lang.RuntimeException -> L27 java.lang.Throwable -> L61
                r2.call(r4)     // Catch: java.lang.RuntimeException -> L27 java.lang.Throwable -> L61
                goto L0
            L27:
                r2 = move-exception
                java.util.logging.Logger r4 = com.google.common.util.concurrent.ListenerCallQueue.logger     // Catch: java.lang.Throwable -> L61
                java.util.logging.Level r5 = java.util.logging.Level.SEVERE     // Catch: java.lang.Throwable -> L61
                L r6 = r9.listener     // Catch: java.lang.Throwable -> L61
                java.lang.String r6 = java.lang.String.valueOf(r6)     // Catch: java.lang.Throwable -> L61
                java.lang.String r3 = java.lang.String.valueOf(r3)     // Catch: java.lang.Throwable -> L61
                int r7 = r6.length()     // Catch: java.lang.Throwable -> L61
                int r7 = r7 + 37
                int r8 = r3.length()     // Catch: java.lang.Throwable -> L61
                int r7 = r7 + r8
                java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L61
                r8.<init>(r7)     // Catch: java.lang.Throwable -> L61
                java.lang.String r7 = "Exception while executing callback: "
                r8.append(r7)     // Catch: java.lang.Throwable -> L61
                r8.append(r6)     // Catch: java.lang.Throwable -> L61
                java.lang.String r6 = " "
                r8.append(r6)     // Catch: java.lang.Throwable -> L61
                r8.append(r3)     // Catch: java.lang.Throwable -> L61
                java.lang.String r3 = r8.toString()     // Catch: java.lang.Throwable -> L61
                r4.log(r5, r3, r2)     // Catch: java.lang.Throwable -> L61
                goto L0
            L5e:
                r2 = move-exception
            L5f:
                monitor-exit(r9)     // Catch: java.lang.Throwable -> L5e
                throw r2     // Catch: java.lang.Throwable -> L61
            L61:
                r2 = move-exception
                if (r1 == 0) goto L6c
                monitor-enter(r9)
                r9.isThreadScheduled = r0     // Catch: java.lang.Throwable -> L69
                monitor-exit(r9)     // Catch: java.lang.Throwable -> L69
                goto L6c
            L69:
                r0 = move-exception
                monitor-exit(r9)     // Catch: java.lang.Throwable -> L69
                throw r0
            L6c:
                throw r2
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.common.util.concurrent.ListenerCallQueue.PerListenerQueue.run():void");
        }
    }

    private void enqueueHelper(Event<L> event, Object obj) {
        Preconditions.checkNotNull(event, NotificationCompat.CATEGORY_EVENT);
        Preconditions.checkNotNull(obj, NotificationCompatJellybean.KEY_LABEL);
        synchronized (this.listeners) {
            Iterator<PerListenerQueue<L>> it = this.listeners.iterator();
            while (it.hasNext()) {
                it.next().add(event, obj);
            }
        }
    }

    public void addListener(L l, Executor executor) {
        Preconditions.checkNotNull(l, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
        Preconditions.checkNotNull(executor, "executor");
        this.listeners.add(new PerListenerQueue<>(l, executor));
    }

    public void dispatch() {
        for (int i = 0; i < this.listeners.size(); i++) {
            this.listeners.get(i).dispatch();
        }
    }

    public void enqueue(Event<L> event) {
        enqueueHelper(event, event);
    }

    public void enqueue(Event<L> event, String str) {
        enqueueHelper(event, str);
    }
}
