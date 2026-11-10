package com.google.common.util.concurrent;

import b.a.a.a.a;
import com.google.common.annotations.GwtIncompatible;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* JADX INFO: loaded from: classes.dex */
@GwtIncompatible
public final class TimeoutFuture<V> extends FluentFuture.TrustedFuture<V> {
    public ListenableFuture<V> delegateRef;
    public ScheduledFuture<?> timer;

    private static final class Fire<V> implements Runnable {
        public TimeoutFuture<V> timeoutFutureRef;

        public Fire(TimeoutFuture<V> timeoutFuture) {
            this.timeoutFutureRef = timeoutFuture;
        }

        @Override // java.lang.Runnable
        public void run() {
            ListenableFuture<? extends V> listenableFuture;
            TimeoutFuture<V> timeoutFuture = this.timeoutFutureRef;
            if (timeoutFuture == null || (listenableFuture = timeoutFuture.delegateRef) == null) {
                return;
            }
            AnonymousClass1 anonymousClass1 = null;
            this.timeoutFutureRef = null;
            if (listenableFuture.isDone()) {
                timeoutFuture.setFuture(listenableFuture);
                return;
            }
            try {
                ScheduledFuture scheduledFuture = timeoutFuture.timer;
                timeoutFuture.timer = null;
                String string = "Timed out";
                if (scheduledFuture != null) {
                    try {
                        long jAbs = Math.abs(scheduledFuture.getDelay(TimeUnit.MILLISECONDS));
                        if (jAbs > 10) {
                            StringBuilder sb = new StringBuilder("Timed out".length() + 66);
                            sb.append("Timed out");
                            sb.append(" (timeout delayed by ");
                            sb.append(jAbs);
                            sb.append(" ms after scheduled time)");
                            string = sb.toString();
                        }
                    } catch (Throwable th) {
                        timeoutFuture.setException(new TimeoutFutureException(string, anonymousClass1));
                        throw th;
                    }
                }
                String strValueOf = String.valueOf(string);
                String strValueOf2 = String.valueOf(listenableFuture);
                StringBuilder sb2 = new StringBuilder(strValueOf.length() + 2 + strValueOf2.length());
                sb2.append(strValueOf);
                sb2.append(": ");
                sb2.append(strValueOf2);
                timeoutFuture.setException(new TimeoutFutureException(sb2.toString(), anonymousClass1));
                listenableFuture.cancel(true);
            } catch (Throwable th2) {
                listenableFuture.cancel(true);
                throw th2;
            }
        }
    }

    private static final class TimeoutFutureException extends TimeoutException {
        public TimeoutFutureException(String str) {
            super(str);
        }

        @Override // java.lang.Throwable
        public synchronized Throwable fillInStackTrace() {
            setStackTrace(new StackTraceElement[0]);
            return this;
        }

        public /* synthetic */ TimeoutFutureException(String str, AnonymousClass1 anonymousClass1) {
            super(str);
        }
    }

    public TimeoutFuture(ListenableFuture<V> listenableFuture) {
        if (listenableFuture == null) {
            throw new NullPointerException();
        }
        this.delegateRef = listenableFuture;
    }

    public static <V> ListenableFuture<V> create(ListenableFuture<V> listenableFuture, long j, TimeUnit timeUnit, ScheduledExecutorService scheduledExecutorService) {
        TimeoutFuture timeoutFuture = new TimeoutFuture(listenableFuture);
        Fire fire = new Fire(timeoutFuture);
        timeoutFuture.timer = scheduledExecutorService.schedule(fire, j, timeUnit);
        listenableFuture.addListener(fire, DirectExecutor.INSTANCE);
        return timeoutFuture;
    }

    @Override // com.google.common.util.concurrent.AbstractFuture
    public void afterDone() {
        maybePropagateCancellationTo(this.delegateRef);
        ScheduledFuture<?> scheduledFuture = this.timer;
        if (scheduledFuture != null) {
            scheduledFuture.cancel(false);
        }
        this.delegateRef = null;
        this.timer = null;
    }

    @Override // com.google.common.util.concurrent.AbstractFuture
    public String pendingToString() {
        ListenableFuture<V> listenableFuture = this.delegateRef;
        ScheduledFuture<?> scheduledFuture = this.timer;
        if (listenableFuture == null) {
            return null;
        }
        String strValueOf = String.valueOf(listenableFuture);
        String strA = a.a(strValueOf.length() + 14, "inputFuture=[", strValueOf, "]");
        if (scheduledFuture == null) {
            return strA;
        }
        long delay = scheduledFuture.getDelay(TimeUnit.MILLISECONDS);
        if (delay <= 0) {
            return strA;
        }
        String strValueOf2 = String.valueOf(strA);
        StringBuilder sb = new StringBuilder(strValueOf2.length() + 43);
        sb.append(strValueOf2);
        sb.append(", remaining delay=[");
        sb.append(delay);
        sb.append(" ms]");
        return sb.toString();
    }
}
