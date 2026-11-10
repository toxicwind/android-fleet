package com.google.common.util.concurrent;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.math.LongMath;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
@GwtIncompatible
public abstract class SmoothRateLimiter extends RateLimiter {
    public double maxPermits;
    public long nextFreeTicketMicros;
    public double stableIntervalMicros;
    public double storedPermits;

    static final class SmoothBursty extends SmoothRateLimiter {
        public final double maxBurstSeconds;

        public SmoothBursty(RateLimiter.SleepingStopwatch sleepingStopwatch, double d2) {
            super(sleepingStopwatch, null);
            this.maxBurstSeconds = d2;
        }

        @Override // com.google.common.util.concurrent.SmoothRateLimiter
        public double coolDownIntervalMicros() {
            return this.stableIntervalMicros;
        }

        @Override // com.google.common.util.concurrent.SmoothRateLimiter
        public void doSetRate(double d2, double d3) {
            double d4 = this.maxPermits;
            this.maxPermits = this.maxBurstSeconds * d2;
            if (d4 == Double.POSITIVE_INFINITY) {
                this.storedPermits = this.maxPermits;
                return;
            }
            double d5 = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
            if (d4 != FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
                d5 = (this.storedPermits * this.maxPermits) / d4;
            }
            this.storedPermits = d5;
        }

        @Override // com.google.common.util.concurrent.SmoothRateLimiter
        public long storedPermitsToWaitTime(double d2, double d3) {
            return 0L;
        }
    }

    static final class SmoothWarmingUp extends SmoothRateLimiter {
        public double coldFactor;
        public double slope;
        public double thresholdPermits;
        public final long warmupPeriodMicros;

        public SmoothWarmingUp(RateLimiter.SleepingStopwatch sleepingStopwatch, long j, TimeUnit timeUnit, double d2) {
            super(sleepingStopwatch, null);
            this.warmupPeriodMicros = timeUnit.toMicros(j);
            this.coldFactor = d2;
        }

        private double permitsToTime(double d2) {
            return (d2 * this.slope) + this.stableIntervalMicros;
        }

        @Override // com.google.common.util.concurrent.SmoothRateLimiter
        public double coolDownIntervalMicros() {
            return this.warmupPeriodMicros / this.maxPermits;
        }

        @Override // com.google.common.util.concurrent.SmoothRateLimiter
        public void doSetRate(double d2, double d3) {
            double d4 = this.maxPermits;
            double d5 = this.coldFactor * d3;
            long j = this.warmupPeriodMicros;
            this.thresholdPermits = (j * 0.5d) / d3;
            double d6 = this.thresholdPermits;
            this.maxPermits = ((j * 2.0d) / (d3 + d5)) + d6;
            double d7 = d5 - d3;
            double d8 = this.maxPermits;
            this.slope = d7 / (d8 - d6);
            if (d4 == Double.POSITIVE_INFINITY) {
                this.storedPermits = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
                return;
            }
            if (d4 != FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
                d8 = (this.storedPermits * d8) / d4;
            }
            this.storedPermits = d8;
        }

        @Override // com.google.common.util.concurrent.SmoothRateLimiter
        public long storedPermitsToWaitTime(double d2, double d3) {
            long jPermitsToTime;
            double d4 = d2 - this.thresholdPermits;
            if (d4 > FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
                double dMin = Math.min(d4, d3);
                jPermitsToTime = (long) (((permitsToTime(d4) + permitsToTime(d4 - dMin)) * dMin) / 2.0d);
                d3 -= dMin;
            } else {
                jPermitsToTime = 0;
            }
            return jPermitsToTime + ((long) (this.stableIntervalMicros * d3));
        }
    }

    public SmoothRateLimiter(RateLimiter.SleepingStopwatch sleepingStopwatch) {
        super(sleepingStopwatch);
        this.nextFreeTicketMicros = 0L;
    }

    public abstract double coolDownIntervalMicros();

    @Override // com.google.common.util.concurrent.RateLimiter
    public final double doGetRate() {
        return TimeUnit.SECONDS.toMicros(1L) / this.stableIntervalMicros;
    }

    public abstract void doSetRate(double d2, double d3);

    @Override // com.google.common.util.concurrent.RateLimiter
    public final void doSetRate(double d2, long j) {
        resync(j);
        double micros = TimeUnit.SECONDS.toMicros(1L) / d2;
        this.stableIntervalMicros = micros;
        doSetRate(d2, micros);
    }

    @Override // com.google.common.util.concurrent.RateLimiter
    public final long queryEarliestAvailable(long j) {
        return this.nextFreeTicketMicros;
    }

    @Override // com.google.common.util.concurrent.RateLimiter
    public final long reserveEarliestAvailable(int i, long j) {
        resync(j);
        long j2 = this.nextFreeTicketMicros;
        double d2 = i;
        double dMin = Math.min(d2, this.storedPermits);
        this.nextFreeTicketMicros = LongMath.saturatedAdd(this.nextFreeTicketMicros, storedPermitsToWaitTime(this.storedPermits, dMin) + ((long) ((d2 - dMin) * this.stableIntervalMicros)));
        this.storedPermits -= dMin;
        return j2;
    }

    public void resync(long j) {
        long j2 = this.nextFreeTicketMicros;
        if (j > j2) {
            this.storedPermits = Math.min(this.maxPermits, this.storedPermits + ((j - j2) / coolDownIntervalMicros()));
            this.nextFreeTicketMicros = j;
        }
    }

    public abstract long storedPermitsToWaitTime(double d2, double d3);

    public /* synthetic */ SmoothRateLimiter(RateLimiter.SleepingStopwatch sleepingStopwatch, AnonymousClass1 anonymousClass1) {
        super(sleepingStopwatch);
        this.nextFreeTicketMicros = 0L;
    }
}
