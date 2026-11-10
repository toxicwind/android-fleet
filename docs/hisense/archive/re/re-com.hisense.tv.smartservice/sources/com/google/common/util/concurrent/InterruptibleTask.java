package com.google.common.util.concurrent;

import b.a.a.a.a;
import com.google.common.annotations.GwtCompatible;
import com.google.j2objc.annotations.ReflectionSupport;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.LockSupport;

/* JADX INFO: loaded from: classes.dex */
@ReflectionSupport(ReflectionSupport.Level.FULL)
@GwtCompatible(emulated = true)
public abstract class InterruptibleTask<T> extends AtomicReference<Runnable> implements Runnable {
    public static final Runnable DONE;
    public static final Runnable INTERRUPTING;
    public static final int MAX_BUSY_WAIT_SPINS = 1000;
    public static final Runnable PARKED;

    private static final class DoNothingRunnable implements Runnable {
        public DoNothingRunnable() {
        }

        @Override // java.lang.Runnable
        public void run() {
        }

        public /* synthetic */ DoNothingRunnable(AnonymousClass1 anonymousClass1) {
        }
    }

    static {
        AnonymousClass1 anonymousClass1 = null;
        DONE = new DoNothingRunnable(anonymousClass1);
        INTERRUPTING = new DoNothingRunnable(anonymousClass1);
        PARKED = new DoNothingRunnable(anonymousClass1);
    }

    public abstract void afterRanInterruptibly(T t, Throwable th);

    public final void interruptTask() {
        Runnable runnable = get();
        if ((runnable instanceof Thread) && compareAndSet(runnable, INTERRUPTING)) {
            try {
                ((Thread) runnable).interrupt();
            } finally {
                if (getAndSet(DONE) == PARKED) {
                    LockSupport.unpark((Thread) runnable);
                }
            }
        }
    }

    public abstract boolean isDone();

    @Override // java.lang.Runnable
    public final void run() {
        T tRunInterruptibly;
        Thread threadCurrentThread = Thread.currentThread();
        if (compareAndSet(null, threadCurrentThread)) {
            boolean z = !isDone();
            if (z) {
                try {
                    tRunInterruptibly = runInterruptibly();
                } catch (Throwable th) {
                    if (!compareAndSet(threadCurrentThread, DONE)) {
                        Runnable runnable = get();
                        boolean z2 = false;
                        int i = 0;
                        while (true) {
                            if (runnable != INTERRUPTING && runnable != PARKED) {
                                break;
                            }
                            i++;
                            if (i > 1000) {
                                Runnable runnable2 = PARKED;
                                if (runnable == runnable2 || compareAndSet(INTERRUPTING, runnable2)) {
                                    boolean z3 = Thread.interrupted() || z2;
                                    LockSupport.park(this);
                                    z2 = z3;
                                }
                            } else {
                                Thread.yield();
                            }
                            runnable = get();
                        }
                        if (z2) {
                            threadCurrentThread.interrupt();
                        }
                    }
                    if (z) {
                        afterRanInterruptibly(null, null);
                    }
                    throw th;
                }
            } else {
                tRunInterruptibly = null;
            }
            if (!compareAndSet(threadCurrentThread, DONE)) {
                Runnable runnable3 = get();
                boolean z4 = false;
                int i2 = 0;
                while (true) {
                    if (runnable3 != INTERRUPTING && runnable3 != PARKED) {
                        break;
                    }
                    i2++;
                    if (i2 > 1000) {
                        Runnable runnable4 = PARKED;
                        if (runnable3 == runnable4 || compareAndSet(INTERRUPTING, runnable4)) {
                            boolean z5 = Thread.interrupted() || z4;
                            LockSupport.park(this);
                            z4 = z5;
                        }
                    } else {
                        Thread.yield();
                    }
                    runnable3 = get();
                }
                if (z4) {
                    threadCurrentThread.interrupt();
                }
            }
            if (z) {
                afterRanInterruptibly(tRunInterruptibly, null);
            }
        }
    }

    public abstract T runInterruptibly();

    public abstract String toPendingString();

    @Override // java.util.concurrent.atomic.AtomicReference
    public final String toString() {
        String strA;
        Runnable runnable = get();
        if (runnable == DONE) {
            strA = "running=[DONE]";
        } else if (runnable == INTERRUPTING) {
            strA = "running=[INTERRUPTED]";
        } else if (runnable instanceof Thread) {
            String name = ((Thread) runnable).getName();
            strA = a.a(a.a((Object) name, 21), "running=[RUNNING ON ", name, "]");
        } else {
            strA = "running=[NOT STARTED YET]";
        }
        String pendingString = toPendingString();
        return a.a(a.a((Object) pendingString, a.a((Object) strA, 2)), strA, ", ", pendingString);
    }
}
