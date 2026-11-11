package kotlinx.datetime;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.media3.extractor.text.ttml.TtmlNode;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.time.Instant;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: Instant.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a-\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0001¢\u0006\u0002\b\t\u001a%\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u00012\u0006\u0010\r\u001a\u00020\u00012\u0006\u0010\u0007\u001a\u00020\bH\u0001¢\u0006\u0002\b\u000e\u001a-\u0010\u000f\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0001¢\u0006\u0002\b\u0010\u001a%\u0010\u000f\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00112\u0006\u0010\u0005\u001a\u00020\u0012H\u0001¢\u0006\u0002\b\u0010\u001a-\u0010\u000f\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00112\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0001¢\u0006\u0002\b\u0010\u001a%\u0010\u000f\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0013\u001a\u00020\u000b2\u0006\u0010\u0007\u001a\u00020\bH\u0001¢\u0006\u0002\b\u0010\u001a%\u0010\u000f\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0001¢\u0006\u0002\b\u0010\u001a-\u0010\u0014\u001a\u00020\u00112\u0006\u0010\f\u001a\u00020\u00012\u0006\u0010\r\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0001¢\u0006\u0002\b\u0015¨\u0006\u0016"}, d2 = {"minusJvm", "Lkotlin/time/Instant;", "instant", ES6Iterator.VALUE_PROPERTY, "", "unit", "Lkotlinx/datetime/DateTimeUnit;", "timeZone", "Lkotlinx/datetime/TimeZone;", "minus", "periodUntilJvm", "Lkotlinx/datetime/DateTimePeriod;", "start", TtmlNode.END, "periodUntil", "plusJvm", "plus", "", "Lkotlinx/datetime/DateTimeUnit$TimeBased;", TypedValues.CycleType.S_WAVE_PERIOD, "untilJvm", "until", "kotlinx-datetime"}, k = 5, mv = {2, 1, 0}, xi = 48, xs = "kotlinx/datetime/InstantJvmKt")
final /* synthetic */ class InstantJvmKt__InstantKt {
    public static final Instant minus(Instant instant, int i, DateTimeUnit unit, TimeZone timeZone) {
        Intrinsics.checkNotNullParameter(instant, "instant");
        Intrinsics.checkNotNullParameter(unit, "unit");
        Intrinsics.checkNotNullParameter(timeZone, "timeZone");
        return InstantKt.minus(instant, i, unit, timeZone);
    }

    public static final DateTimePeriod periodUntil(Instant start, Instant end, TimeZone timeZone) {
        Intrinsics.checkNotNullParameter(start, "start");
        Intrinsics.checkNotNullParameter(end, "end");
        Intrinsics.checkNotNullParameter(timeZone, "timeZone");
        return InstantKt.periodUntil(start, end, timeZone);
    }

    public static final Instant plus(Instant instant, int i, DateTimeUnit unit, TimeZone timeZone) {
        Intrinsics.checkNotNullParameter(instant, "instant");
        Intrinsics.checkNotNullParameter(unit, "unit");
        Intrinsics.checkNotNullParameter(timeZone, "timeZone");
        return InstantKt.plus(instant, i, unit, timeZone);
    }

    public static final Instant plus(Instant instant, long j, DateTimeUnit.TimeBased unit) {
        Intrinsics.checkNotNullParameter(instant, "instant");
        Intrinsics.checkNotNullParameter(unit, "unit");
        return InstantKt.plus(instant, j, unit);
    }

    public static final Instant plus(Instant instant, long j, DateTimeUnit unit, TimeZone timeZone) {
        Intrinsics.checkNotNullParameter(instant, "instant");
        Intrinsics.checkNotNullParameter(unit, "unit");
        Intrinsics.checkNotNullParameter(timeZone, "timeZone");
        return InstantKt.plus(instant, j, unit, timeZone);
    }

    public static final Instant plus(Instant instant, DateTimePeriod period, TimeZone timeZone) {
        Intrinsics.checkNotNullParameter(instant, "instant");
        Intrinsics.checkNotNullParameter(period, "period");
        Intrinsics.checkNotNullParameter(timeZone, "timeZone");
        return InstantKt.plus(instant, period, timeZone);
    }

    public static final Instant plus(Instant instant, DateTimeUnit unit, TimeZone timeZone) {
        Intrinsics.checkNotNullParameter(instant, "instant");
        Intrinsics.checkNotNullParameter(unit, "unit");
        Intrinsics.checkNotNullParameter(timeZone, "timeZone");
        return InstantKt.plus(instant, 1, unit, timeZone);
    }

    public static final long until(Instant start, Instant end, DateTimeUnit unit, TimeZone timeZone) {
        Intrinsics.checkNotNullParameter(start, "start");
        Intrinsics.checkNotNullParameter(end, "end");
        Intrinsics.checkNotNullParameter(unit, "unit");
        Intrinsics.checkNotNullParameter(timeZone, "timeZone");
        return InstantKt.until(start, end, unit, timeZone);
    }
}
