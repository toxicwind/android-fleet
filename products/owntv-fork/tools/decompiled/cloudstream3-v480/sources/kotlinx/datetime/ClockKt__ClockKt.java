package kotlinx.datetime;

import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.internal.Intrinsics;
import kotlin.time.Clock;
import kotlin.time.ComparableTimeMark;
import kotlin.time.Instant;
import kotlin.time.TimeMark;
import kotlin.time.TimeSource;

/* JADX INFO: compiled from: Clock.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000&\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0012\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004\u001a\f\u0010\u0005\u001a\u00020\u0006*\u00020\u0002H\u0007\u001a\u0012\u0010\u0007\u001a\u00020\u0002*\u00020\b2\u0006\u0010\t\u001a\u00020\n\u001a\u0014\u0010\u000b\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0007¨\u0006\f"}, d2 = {"todayIn", "Lkotlinx/datetime/LocalDate;", "Lkotlin/time/Clock;", "timeZone", "Lkotlinx/datetime/TimeZone;", "asTimeSource", "Lkotlin/time/TimeSource$WithComparableMarks;", "asClock", "Lkotlin/time/TimeSource;", "origin", "Lkotlin/time/Instant;", "todayAt", "kotlinx-datetime"}, k = 5, mv = {2, 1, 0}, xi = 48, xs = "kotlinx/datetime/ClockKt")
final /* synthetic */ class ClockKt__ClockKt {
    public static final LocalDate todayIn(Clock clock, TimeZone timeZone) {
        Intrinsics.checkNotNullParameter(clock, "<this>");
        Intrinsics.checkNotNullParameter(timeZone, "timeZone");
        return TimeZoneKt.toLocalDateTime(clock.now(), timeZone).getDate();
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "This function is deprecated because Clock.System.asTimeSource can be confused with TimeSource.Monotonic, which are very different. See https://github.com/Kotlin/kotlinx-datetime/issues/372")
    public static final TimeSource.WithComparableMarks asTimeSource(final Clock clock) {
        Intrinsics.checkNotNullParameter(clock, "<this>");
        return new TimeSource.WithComparableMarks() { // from class: kotlinx.datetime.ClockKt__ClockKt.asTimeSource.1
            @Override // kotlin.time.TimeSource
            public ComparableTimeMark markNow() {
                return new InstantTimeMark(clock.now(), clock);
            }
        };
    }

    public static final Clock asClock(TimeSource timeSource, Instant origin) {
        Intrinsics.checkNotNullParameter(timeSource, "<this>");
        Intrinsics.checkNotNullParameter(origin, "origin");
        return new Clock(timeSource, origin) { // from class: kotlinx.datetime.ClockKt__ClockKt.asClock.1
            final /* synthetic */ Instant $origin;
            private final TimeMark startMark;

            {
                this.$origin = origin;
                this.startMark = timeSource.markNow();
            }

            @Override // kotlin.time.Clock
            public Instant now() {
                return this.$origin.m10423plusLRDsOJo(this.startMark.mo10306elapsedNowUwyO8pc());
            }
        };
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Use Clock.todayIn instead", replaceWith = @ReplaceWith(expression = "this.todayIn(timeZone)", imports = {}))
    public static final LocalDate todayAt(Clock clock, TimeZone timeZone) {
        Intrinsics.checkNotNullParameter(clock, "<this>");
        Intrinsics.checkNotNullParameter(timeZone, "timeZone");
        return ClockKt.todayIn(clock, timeZone);
    }
}
