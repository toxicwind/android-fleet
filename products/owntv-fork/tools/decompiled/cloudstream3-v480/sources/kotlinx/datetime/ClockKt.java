package kotlinx.datetime;

import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.time.Clock;
import kotlin.time.Instant;
import kotlin.time.TimeSource;

/* JADX INFO: compiled from: Clock.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"kotlinx/datetime/ClockKt__ClockKt"}, k = 4, mv = {2, 1, 0}, xi = 48)
public final class ClockKt {
    public static final Clock asClock(TimeSource timeSource, Instant instant) {
        return ClockKt__ClockKt.asClock(timeSource, instant);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "This function is deprecated because Clock.System.asTimeSource can be confused with TimeSource.Monotonic, which are very different. See https://github.com/Kotlin/kotlinx-datetime/issues/372")
    public static final TimeSource.WithComparableMarks asTimeSource(Clock clock) {
        return ClockKt__ClockKt.asTimeSource(clock);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Use Clock.todayIn instead", replaceWith = @ReplaceWith(expression = "this.todayIn(timeZone)", imports = {}))
    public static final LocalDate todayAt(Clock clock, TimeZone timeZone) {
        return ClockKt__ClockKt.todayAt(clock, timeZone);
    }

    public static final LocalDate todayIn(Clock clock, TimeZone timeZone) {
        return ClockKt__ClockKt.todayIn(clock, timeZone);
    }
}
