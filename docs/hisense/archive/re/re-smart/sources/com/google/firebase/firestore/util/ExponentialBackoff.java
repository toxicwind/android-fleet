package com.google.firebase.firestore.util;

import java.util.Date;

/* JADX INFO: loaded from: classes.dex */
public class ExponentialBackoff {
    public static final double DEFAULT_BACKOFF_FACTOR = 1.5d;
    public static final long DEFAULT_BACKOFF_INITIAL_DELAY_MS = 1000;
    public static final long DEFAULT_BACKOFF_MAX_DELAY_MS = 60000;
    public final double backoffFactor;
    public long currentBaseMs;
    public final long initialDelayMs;
    public long lastAttemptTime;
    public final long maxDelayMs;
    public long nextMaxDelayMs;
    public final AsyncQueue queue;
    public final AsyncQueue.TimerId timerId;
    public AsyncQueue.DelayedTask timerTask;

    public ExponentialBackoff(AsyncQueue asyncQueue, AsyncQueue.TimerId timerId, long j, double d2, long j2) {
        this.queue = asyncQueue;
        this.timerId = timerId;
        this.initialDelayMs = j;
        this.backoffFactor = d2;
        this.maxDelayMs = j2;
        this.nextMaxDelayMs = j2;
        this.lastAttemptTime = new Date().getTime();
        reset();
    }

    private long jitterDelayMs() {
        return (long) ((Math.random() - 0.5d) * this.currentBaseMs);
    }

    public /* synthetic */ void a(Runnable runnable) {
        this.lastAttemptTime = new Date().getTime();
        runnable.run();
    }

    public void backoffAndRun(final Runnable runnable) {
        cancel();
        long jJitterDelayMs = this.currentBaseMs + jitterDelayMs();
        long jMax = Math.max(0L, new Date().getTime() - this.lastAttemptTime);
        long jMax2 = Math.max(0L, jJitterDelayMs - jMax);
        if (this.currentBaseMs > 0) {
            Logger.debug(ExponentialBackoff.class.getSimpleName(), "Backing off for %d ms (base delay: %d ms, delay with jitter: %d ms, last attempt: %d ms ago)", Long.valueOf(jMax2), Long.valueOf(this.currentBaseMs), Long.valueOf(jJitterDelayMs), Long.valueOf(jMax));
        }
        this.timerTask = this.queue.enqueueAfterDelay(this.timerId, jMax2, new Runnable() { // from class: b.e.b.d.g.m
            @Override // java.lang.Runnable
            public final void run() {
                this.f554a.a(runnable);
            }
        });
        this.currentBaseMs = (long) (this.currentBaseMs * this.backoffFactor);
        long j = this.currentBaseMs;
        long j2 = this.initialDelayMs;
        if (j < j2) {
            this.currentBaseMs = j2;
        } else {
            long j3 = this.nextMaxDelayMs;
            if (j > j3) {
                this.currentBaseMs = j3;
            }
        }
        this.nextMaxDelayMs = this.maxDelayMs;
    }

    public void cancel() {
        AsyncQueue.DelayedTask delayedTask = this.timerTask;
        if (delayedTask != null) {
            delayedTask.cancel();
            this.timerTask = null;
        }
    }

    public void reset() {
        this.currentBaseMs = 0L;
    }

    public void resetToMax() {
        this.currentBaseMs = this.nextMaxDelayMs;
    }

    public void setTemporaryMaxDelay(long j) {
        this.nextMaxDelayMs = j;
    }

    public ExponentialBackoff(AsyncQueue asyncQueue, AsyncQueue.TimerId timerId) {
        this(asyncQueue, timerId, 1000L, 1.5d, 60000L);
    }
}
