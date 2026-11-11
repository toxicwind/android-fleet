package com.google.common.base;

import androidx.media3.common.C;
import androidx.media3.exoplayer.upstream.CmcdData;
import java.util.concurrent.TimeUnit;
import kotlinx.datetime.internal.DateCalculationsKt;
import org.schabi.newpipe.extractor.stream.Stream;

/* JADX INFO: loaded from: classes6.dex */
@ElementTypesAreNonnullByDefault
public final class Stopwatch {
    private long elapsedNanos;
    private boolean isRunning;
    private long startTick;
    private final Ticker ticker;

    public static Stopwatch createUnstarted() {
        return new Stopwatch();
    }

    public static Stopwatch createUnstarted(Ticker ticker) {
        return new Stopwatch(ticker);
    }

    public static Stopwatch createStarted() {
        return new Stopwatch().start();
    }

    public static Stopwatch createStarted(Ticker ticker) {
        return new Stopwatch(ticker).start();
    }

    Stopwatch() {
        this.ticker = Ticker.systemTicker();
    }

    Stopwatch(Ticker ticker) {
        this.ticker = (Ticker) Preconditions.checkNotNull(ticker, "ticker");
    }

    public boolean isRunning() {
        return this.isRunning;
    }

    public Stopwatch start() {
        Preconditions.checkState(!this.isRunning, "This stopwatch is already running.");
        this.isRunning = true;
        this.startTick = this.ticker.read();
        return this;
    }

    public Stopwatch stop() {
        long j = this.ticker.read();
        Preconditions.checkState(this.isRunning, "This stopwatch is already stopped.");
        this.isRunning = false;
        this.elapsedNanos += j - this.startTick;
        return this;
    }

    public Stopwatch reset() {
        this.elapsedNanos = 0L;
        this.isRunning = false;
        return this;
    }

    private long elapsedNanos() {
        return this.isRunning ? (this.ticker.read() - this.startTick) + this.elapsedNanos : this.elapsedNanos;
    }

    public long elapsed(TimeUnit desiredUnit) {
        return desiredUnit.convert(elapsedNanos(), TimeUnit.NANOSECONDS);
    }

    public String toString() {
        long jElapsedNanos = elapsedNanos();
        TimeUnit timeUnitChooseUnit = chooseUnit(jElapsedNanos);
        return Platform.formatCompact4Digits(jElapsedNanos / TimeUnit.NANOSECONDS.convert(1L, timeUnitChooseUnit)) + Stream.ID_UNKNOWN + abbreviate(timeUnitChooseUnit);
    }

    private static TimeUnit chooseUnit(long nanos) {
        TimeUnit timeUnit = TimeUnit.DAYS;
        TimeUnit timeUnit2 = TimeUnit.NANOSECONDS;
        if (nanos / DateCalculationsKt.NANOS_PER_DAY > 0) {
            return TimeUnit.DAYS;
        }
        TimeUnit timeUnit3 = TimeUnit.HOURS;
        TimeUnit timeUnit4 = TimeUnit.NANOSECONDS;
        if (nanos / DateCalculationsKt.NANOS_PER_HOUR > 0) {
            return TimeUnit.HOURS;
        }
        TimeUnit timeUnit5 = TimeUnit.MINUTES;
        TimeUnit timeUnit6 = TimeUnit.NANOSECONDS;
        if (nanos / DateCalculationsKt.NANOS_PER_MINUTE > 0) {
            return TimeUnit.MINUTES;
        }
        TimeUnit timeUnit7 = TimeUnit.SECONDS;
        TimeUnit timeUnit8 = TimeUnit.NANOSECONDS;
        if (nanos / C.NANOS_PER_SECOND > 0) {
            return TimeUnit.SECONDS;
        }
        TimeUnit timeUnit9 = TimeUnit.MILLISECONDS;
        TimeUnit timeUnit10 = TimeUnit.NANOSECONDS;
        if (nanos / 1000000 > 0) {
            return TimeUnit.MILLISECONDS;
        }
        TimeUnit timeUnit11 = TimeUnit.MICROSECONDS;
        TimeUnit timeUnit12 = TimeUnit.NANOSECONDS;
        if (nanos / 1000 > 0) {
            return TimeUnit.MICROSECONDS;
        }
        return TimeUnit.NANOSECONDS;
    }

    /* JADX INFO: renamed from: com.google.common.base.Stopwatch$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$java$util$concurrent$TimeUnit;

        static {
            int[] iArr = new int[TimeUnit.values().length];
            $SwitchMap$java$util$concurrent$TimeUnit = iArr;
            try {
                iArr[TimeUnit.NANOSECONDS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$java$util$concurrent$TimeUnit[TimeUnit.MICROSECONDS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$java$util$concurrent$TimeUnit[TimeUnit.MILLISECONDS.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$java$util$concurrent$TimeUnit[TimeUnit.SECONDS.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$java$util$concurrent$TimeUnit[TimeUnit.MINUTES.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$java$util$concurrent$TimeUnit[TimeUnit.HOURS.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$java$util$concurrent$TimeUnit[TimeUnit.DAYS.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    private static String abbreviate(TimeUnit unit) {
        switch (AnonymousClass1.$SwitchMap$java$util$concurrent$TimeUnit[unit.ordinal()]) {
            case 1:
                return "ns";
            case 2:
                return "μs";
            case 3:
                return "ms";
            case 4:
                return CmcdData.STREAMING_FORMAT_SS;
            case 5:
                return "min";
            case 6:
                return CmcdData.STREAMING_FORMAT_HLS;
            case 7:
                return "d";
            default:
                throw new AssertionError();
        }
    }
}
