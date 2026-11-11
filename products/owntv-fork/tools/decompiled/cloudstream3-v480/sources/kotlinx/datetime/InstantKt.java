package kotlinx.datetime;

import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.time.Instant;
import kotlinx.datetime.format.DateTimeComponents;
import kotlinx.datetime.format.DateTimeFormat;

/* JADX INFO: compiled from: Instant.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"kotlinx/datetime/InstantKt__InstantKt"}, k = 4, mv = {2, 1, 0}, xi = 48)
public final class InstantKt {
    public static final long DISTANT_FUTURE_SECONDS = 3093527980800L;
    public static final long DISTANT_PAST_SECONDS = -3217862419201L;

    public static final int daysUntil(Instant instant, Instant instant2, TimeZone timeZone) {
        return InstantKt__InstantKt.daysUntil(instant, instant2, timeZone);
    }

    public static final String format(Instant instant, DateTimeFormat<DateTimeComponents> dateTimeFormat, UtcOffset utcOffset) {
        return InstantKt__InstantKt.format(instant, dateTimeFormat, utcOffset);
    }

    public static final long minus(Instant instant, Instant instant2, DateTimeUnit.TimeBased timeBased) {
        return InstantKt__InstantKt.minus(instant, instant2, timeBased);
    }

    public static final long minus(Instant instant, Instant instant2, DateTimeUnit dateTimeUnit, TimeZone timeZone) {
        return InstantKt__InstantKt.minus(instant, instant2, dateTimeUnit, timeZone);
    }

    public static final Instant minus(Instant instant, int i, DateTimeUnit.TimeBased timeBased) {
        return InstantKt__InstantKt.minus(instant, i, timeBased);
    }

    public static final Instant minus(Instant instant, int i, DateTimeUnit dateTimeUnit, TimeZone timeZone) {
        return InstantKt__InstantKt.minus(instant, i, dateTimeUnit, timeZone);
    }

    public static final Instant minus(Instant instant, long j, DateTimeUnit.TimeBased timeBased) {
        return InstantKt__InstantKt.minus(instant, j, timeBased);
    }

    public static final Instant minus(Instant instant, long j, DateTimeUnit dateTimeUnit, TimeZone timeZone) {
        return InstantKt__InstantKt.minus(instant, j, dateTimeUnit, timeZone);
    }

    public static final Instant minus(Instant instant, DateTimePeriod dateTimePeriod, TimeZone timeZone) {
        return InstantKt__InstantKt.minus(instant, dateTimePeriod, timeZone);
    }

    @Deprecated(message = "Use the minus overload with an explicit number of units", replaceWith = @ReplaceWith(expression = "this.minus(1, unit)", imports = {}))
    public static final Instant minus(Instant instant, DateTimeUnit.TimeBased timeBased) {
        return InstantKt__InstantKt.minus(instant, timeBased);
    }

    @Deprecated(message = "Use the minus overload with an explicit number of units", replaceWith = @ReplaceWith(expression = "this.minus(1, unit, timeZone)", imports = {}))
    public static final Instant minus(Instant instant, DateTimeUnit dateTimeUnit, TimeZone timeZone) {
        return InstantKt__InstantKt.minus(instant, dateTimeUnit, timeZone);
    }

    public static final DateTimePeriod minus(Instant instant, Instant instant2, TimeZone timeZone) {
        return InstantKt__InstantKt.minus(instant, instant2, timeZone);
    }

    public static final int monthsUntil(Instant instant, Instant instant2, TimeZone timeZone) {
        return InstantKt__InstantKt.monthsUntil(instant, instant2, timeZone);
    }

    public static final Instant parse(Instant.Companion companion, CharSequence charSequence, DateTimeFormat<DateTimeComponents> dateTimeFormat) {
        return InstantKt__InstantKt.parse(companion, charSequence, dateTimeFormat);
    }

    public static final Instant parseOrNull(Instant.Companion companion, CharSequence charSequence, DateTimeFormat<DateTimeComponents> dateTimeFormat) {
        return InstantKt__InstantKt.parseOrNull(companion, charSequence, dateTimeFormat);
    }

    public static final DateTimePeriod periodUntil(Instant instant, Instant instant2, TimeZone timeZone) {
        return InstantKt__InstantKt.periodUntil(instant, instant2, timeZone);
    }

    public static final Instant plus(Instant instant, int i, DateTimeUnit.TimeBased timeBased) {
        return InstantKt__InstantKt.plus(instant, i, timeBased);
    }

    public static final Instant plus(Instant instant, int i, DateTimeUnit dateTimeUnit, TimeZone timeZone) {
        return InstantKt__InstantKt.plus(instant, i, dateTimeUnit, timeZone);
    }

    public static final Instant plus(Instant instant, long j, DateTimeUnit.TimeBased timeBased) {
        return InstantKt__InstantKt.plus(instant, j, timeBased);
    }

    public static final Instant plus(Instant instant, long j, DateTimeUnit dateTimeUnit, TimeZone timeZone) {
        return InstantKt__InstantKt.plus(instant, j, dateTimeUnit, timeZone);
    }

    public static final Instant plus(Instant instant, DateTimePeriod dateTimePeriod, TimeZone timeZone) {
        return InstantKt__InstantKt.plus(instant, dateTimePeriod, timeZone);
    }

    @Deprecated(message = "Use the plus overload with an explicit number of units", replaceWith = @ReplaceWith(expression = "this.plus(1, unit)", imports = {}))
    public static final Instant plus(Instant instant, DateTimeUnit.TimeBased timeBased) {
        return InstantKt__InstantKt.plus(instant, timeBased);
    }

    @Deprecated(message = "Use the plus overload with an explicit number of units", replaceWith = @ReplaceWith(expression = "this.plus(1, unit, timeZone)", imports = {}))
    public static final Instant plus(Instant instant, DateTimeUnit dateTimeUnit, TimeZone timeZone) {
        return InstantKt__InstantKt.plus(instant, dateTimeUnit, timeZone);
    }

    public static final long until(Instant instant, Instant instant2, DateTimeUnit.TimeBased timeBased) {
        return InstantKt__InstantKt.until(instant, instant2, timeBased);
    }

    public static final long until(Instant instant, Instant instant2, DateTimeUnit dateTimeUnit, TimeZone timeZone) {
        return InstantKt__InstantKt.until(instant, instant2, dateTimeUnit, timeZone);
    }

    public static final int yearsUntil(Instant instant, Instant instant2, TimeZone timeZone) {
        return InstantKt__InstantKt.yearsUntil(instant, instant2, timeZone);
    }
}
