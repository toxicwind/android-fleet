package com.google.common.util.concurrent;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.google.common.base.Stopwatch;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
@Beta
@GwtIncompatible
public abstract class RateLimiter {
    public volatile Object mutexDoNotUseDirectly;
    public final SleepingStopwatch stopwatch;

    static abstract class SleepingStopwatch {

        /* JADX INFO: renamed from: com.google.common.util.concurrent.RateLimiter$SleepingStopwatch$1, reason: invalid class name */
        public class AnonymousClass1 extends SleepingStopwatch {
            public final Stopwatch stopwatch = Stopwatch.createStarted();

            @Override // com.google.common.util.concurrent.RateLimiter.SleepingStopwatch
            public long readMicros() {
                return this.stopwatch.elapsed(TimeUnit.MICROSECONDS);
            }

            @Override // com.google.common.util.concurrent.RateLimiter.SleepingStopwatch
            public void sleepMicrosUninterruptibly(long j) {
                if (j > 0) {
                    Uninterruptibles.sleepUninterruptibly(j, TimeUnit.MICROSECONDS);
                }
            }
        }

        public static SleepingStopwatch createFromSystemTimer() {
            return new AnonymousClass1();
        }

        public abstract long readMicros();

        public abstract void sleepMicrosUninterruptibly(long j);
    }

    public RateLimiter(SleepingStopwatch sleepingStopwatch) {
        if (sleepingStopwatch == null) {
            throw new NullPointerException();
        }
        this.stopwatch = sleepingStopwatch;
    }

    private boolean canAcquire(long j, long j2) {
        return queryEarliestAvailable(j) - j2 <= j;
    }

    public static void checkPermits(int i) {
        Preconditions.checkArgument(i > 0, "Requested permits (%s) must be positive", i);
    }

    @VisibleForTesting
    public static RateLimiter create(double d2, SleepingStopwatch sleepingStopwatch) {
        SmoothRateLimiter.SmoothBursty smoothBursty = new SmoothRateLimiter.SmoothBursty(sleepingStopwatch, 1.0d);
        smoothBursty.setRate(d2);
        return smoothBursty;
    }

    private Object mutex() {
        Object obj = this.mutexDoNotUseDirectly;
        if (obj == null) {
            synchronized (this) {
                obj = this.mutexDoNotUseDirectly;
                if (obj == null) {
                    obj = new Object();
                    this.mutexDoNotUseDirectly = obj;
                }
            }
        }
        return obj;
    }

    @CanIgnoreReturnValue
    public double acquire() {
        return acquire(1);
    }

    public abstract double doGetRate();

    public abstract void doSetRate(double d2, long j);

    public final double getRate() {
        double dDoGetRate;
        synchronized (mutex()) {
            dDoGetRate = doGetRate();
        }
        return dDoGetRate;
    }

    public abstract long queryEarliestAvailable(long j);

    public final long reserve(int i) {
        long jReserveAndGetWaitLength;
        checkPermits(i);
        synchronized (mutex()) {
            jReserveAndGetWaitLength = reserveAndGetWaitLength(i, this.stopwatch.readMicros());
        }
        return jReserveAndGetWaitLength;
    }

    public final long reserveAndGetWaitLength(int i, long j) {
        return Math.max(reserveEarliestAvailable(i, j) - j, 0L);
    }

    public abstract long reserveEarliestAvailable(int i, long j);

    public final void setRate(double d2) {
        Preconditions.checkArgument(d2 > FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE && !Double.isNaN(d2), "rate must be positive");
        synchronized (mutex()) {
            doSetRate(d2, this.stopwatch.readMicros());
        }
    }

    public String toString() {
        return String.format(Locale.ROOT, "RateLimiter[stableRate=%3.1fqps]", Double.valueOf(getRate()));
    }

    public boolean tryAcquire(long j, TimeUnit timeUnit) {
        return tryAcquire(1, j, timeUnit);
    }

    @CanIgnoreReturnValue
    public double acquire(int i) {
        long jReserve = reserve(i);
        this.stopwatch.sleepMicrosUninterruptibly(jReserve);
        return (jReserve * 1.0d) / TimeUnit.SECONDS.toMicros(1L);
    }

    public boolean tryAcquire(int i) {
        return tryAcquire(i, 0L, TimeUnit.MICROSECONDS);
    }

    public static RateLimiter create(double d2, long j, TimeUnit timeUnit) {
        Preconditions.checkArgument(j >= 0, "warmupPeriod must not be negative: %s", j);
        return create(d2, j, timeUnit, 3.0d, new SleepingStopwatch.AnonymousClass1());
    }

    public boolean tryAcquire() {
        return tryAcquire(1, 0L, TimeUnit.MICROSECONDS);
    }

    public boolean tryAcquire(int i, long j, TimeUnit timeUnit) {
        long jMax = Math.max(timeUnit.toMicros(j), 0L);
        checkPermits(i);
        synchronized (mutex()) {
            long micros = this.stopwatch.readMicros();
            if (!canAcquire(micros, jMax)) {
                return false;
            }
            this.stopwatch.sleepMicrosUninterruptibly(reserveAndGetWaitLength(i, micros));
            return true;
        }
    }

    @VisibleForTesting
    public static RateLimiter create(double d2, long j, TimeUnit timeUnit, double d3, SleepingStopwatch sleepingStopwatch) {
        SmoothRateLimiter.SmoothWarmingUp smoothWarmingUp = new SmoothRateLimiter.SmoothWarmingUp(sleepingStopwatch, j, timeUnit, d3);
        smoothWarmingUp.setRate(d2);
        return smoothWarmingUp;
    }

    public static RateLimiter create(double d2) {
        return create(d2, new SleepingStopwatch.AnonymousClass1());
    }
}
