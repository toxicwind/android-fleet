package kotlinx.datetime;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.media3.common.C;
import androidx.tvprovider.media.tv.TvContractCompat;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.time.Duration;
import kotlin.time.DurationKt;
import kotlin.time.DurationUnit;
import kotlin.time.Instant;
import kotlinx.datetime.format.DateTimeComponents;
import kotlinx.datetime.format.DateTimeComponentsKt;
import kotlinx.datetime.format.DateTimeFormat;
import kotlinx.datetime.internal.DivRemResult;
import kotlinx.datetime.internal.MathKt;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: compiled from: Instant.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a \u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u001a\u001a\u0010\b\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f\u001a\u001a\u0010\r\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f\u001a\u001a\u0010\u000e\u001a\u00020\n*\u00020\u00012\u0006\u0010\u000f\u001a\u00020\u00012\u0006\u0010\u000b\u001a\u00020\f\u001a\"\u0010\u0010\u001a\u00020\u0011*\u00020\u00012\u0006\u0010\u000f\u001a\u00020\u00012\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u000b\u001a\u00020\f\u001a\u001a\u0010\u0010\u001a\u00020\u0011*\u00020\u00012\u0006\u0010\u000f\u001a\u00020\u00012\u0006\u0010\u0012\u001a\u00020\u0014\u001a\u001a\u0010\u0015\u001a\u00020\u0016*\u00020\u00012\u0006\u0010\u000f\u001a\u00020\u00012\u0006\u0010\u000b\u001a\u00020\f\u001a\u001a\u0010\u0017\u001a\u00020\u0016*\u00020\u00012\u0006\u0010\u000f\u001a\u00020\u00012\u0006\u0010\u000b\u001a\u00020\f\u001a\u001a\u0010\u0018\u001a\u00020\u0016*\u00020\u00012\u0006\u0010\u000f\u001a\u00020\u00012\u0006\u0010\u000b\u001a\u00020\f\u001a\u001a\u0010\r\u001a\u00020\n*\u00020\u00012\u0006\u0010\u000f\u001a\u00020\u00012\u0006\u0010\u000b\u001a\u00020\f\u001a\u001c\u0010\b\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u000b\u001a\u00020\fH\u0007\u001a\u001c\u0010\r\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u000b\u001a\u00020\fH\u0007\u001a\u0014\u0010\b\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0012\u001a\u00020\u0014H\u0007\u001a\u0014\u0010\r\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0012\u001a\u00020\u0014H\u0007\u001a\"\u0010\b\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0019\u001a\u00020\u00162\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u000b\u001a\u00020\f\u001a\"\u0010\r\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0019\u001a\u00020\u00162\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u000b\u001a\u00020\f\u001a\u001a\u0010\b\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0019\u001a\u00020\u00162\u0006\u0010\u0012\u001a\u00020\u0014\u001a\u001a\u0010\r\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0019\u001a\u00020\u00162\u0006\u0010\u0012\u001a\u00020\u0014\u001a\"\u0010\b\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0019\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u000b\u001a\u00020\f\u001a\"\u0010\r\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0019\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u000b\u001a\u00020\f\u001a\u001a\u0010\b\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0019\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0014\u001a\u001a\u0010\r\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0019\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0014\u001a\"\u0010\r\u001a\u00020\u0011*\u00020\u00012\u0006\u0010\u000f\u001a\u00020\u00012\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u000b\u001a\u00020\f\u001a\u001a\u0010\r\u001a\u00020\u0011*\u00020\u00012\u0006\u0010\u000f\u001a\u00020\u00012\u0006\u0010\u0012\u001a\u00020\u0014\u001a\"\u0010\u0005\u001a\u00020\u001a*\u00020\u00012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\b\b\u0002\u0010\u001b\u001a\u00020\u001c\u001a$\u0010\u001d\u001a\u0004\u0018\u00010\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u001a\u0019\u0010 \u001a\u00020!*\u00020\u00012\u0006\u0010\u001b\u001a\u00020\u001cH\u0002¢\u0006\u0002\b\"\u001a\u0019\u0010 \u001a\u00020!*\u00020\u00012\u0006\u0010\u000b\u001a\u00020\fH\u0002¢\u0006\u0002\b\"\u001a\u0019\u0010#\u001a\u00020\u0001*\u00020\u00012\u0006\u0010$\u001a\u00020\fH\u0002¢\u0006\u0002\b%\u001a!\u0010\b\u001a\u00020!*\u00020!2\u0006\u0010\u0019\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020&H\u0002¢\u0006\u0002\b'\u001a!\u0010\b\u001a\u00020!*\u00020!2\u0006\u0010\u0019\u001a\u00020\u00162\u0006\u0010\u0012\u001a\u00020&H\u0002¢\u0006\u0002\b'\"\u000e\u0010\u001e\u001a\u00020\u0011X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u001f\u001a\u00020\u0011X\u0080T¢\u0006\u0002\n\u0000¨\u0006("}, d2 = {"parse", "Lkotlin/time/Instant;", "Lkotlin/time/Instant$Companion;", TvContractCompat.PARAM_INPUT, "", "format", "Lkotlinx/datetime/format/DateTimeFormat;", "Lkotlinx/datetime/format/DateTimeComponents;", "plus", TypedValues.CycleType.S_WAVE_PERIOD, "Lkotlinx/datetime/DateTimePeriod;", "timeZone", "Lkotlinx/datetime/TimeZone;", "minus", "periodUntil", "other", "until", "", "unit", "Lkotlinx/datetime/DateTimeUnit;", "Lkotlinx/datetime/DateTimeUnit$TimeBased;", "daysUntil", "", "monthsUntil", "yearsUntil", ES6Iterator.VALUE_PROPERTY, "", TypedValues.CycleType.S_WAVE_OFFSET, "Lkotlinx/datetime/UtcOffset;", "parseOrNull", "DISTANT_PAST_SECONDS", "DISTANT_FUTURE_SECONDS", "toLocalDateTimeFailing", "Lkotlinx/datetime/LocalDateTime;", "toLocalDateTimeFailing$InstantKt__InstantKt", "check", "zone", "check$InstantKt__InstantKt", "Lkotlinx/datetime/DateTimeUnit$DateBased;", "plus$InstantKt__InstantKt", "kotlinx-datetime"}, k = 5, mv = {2, 1, 0}, xi = 48, xs = "kotlinx/datetime/InstantKt")
public final /* synthetic */ class InstantKt__InstantKt {
    public static final Instant parse(Instant.Companion companion, CharSequence input, DateTimeFormat<DateTimeComponents> format) {
        Intrinsics.checkNotNullParameter(companion, "<this>");
        Intrinsics.checkNotNullParameter(input, "input");
        Intrinsics.checkNotNullParameter(format, "format");
        try {
            return DateTimeComponents.toInstantUsingOffset$default(format.parse(input), null, 1, null);
        } catch (IllegalArgumentException e) {
            throw new DateTimeFormatException("Failed to parse an instant from '" + ((Object) input) + '\'', e);
        }
    }

    public static final Instant plus(Instant instant, DateTimePeriod period, TimeZone timeZone) {
        Intrinsics.checkNotNullParameter(instant, "<this>");
        Intrinsics.checkNotNullParameter(period, "period");
        Intrinsics.checkNotNullParameter(timeZone, "timeZone");
        try {
            UtcOffset utcOffsetOffsetIn = TimeZoneKt.offsetIn(instant, timeZone);
            LocalDateTime localDateTimeFailing$InstantKt__InstantKt = toLocalDateTimeFailing$InstantKt__InstantKt(instant, utcOffsetOffsetIn);
            if (period.getTotalMonths() != 0) {
                localDateTimeFailing$InstantKt__InstantKt = plus$InstantKt__InstantKt(localDateTimeFailing$InstantKt__InstantKt, period.getTotalMonths(), DateTimeUnit.INSTANCE.getMONTH());
            }
            if (period.getDays() != 0) {
                localDateTimeFailing$InstantKt__InstantKt = plus$InstantKt__InstantKt(localDateTimeFailing$InstantKt__InstantKt, period.getDays(), (DateTimeUnit.DateBased) DateTimeUnit.INSTANCE.getDAY());
            }
            Instant instantLocalDateTimeToInstant = TimeZoneKt.localDateTimeToInstant(localDateTimeFailing$InstantKt__InstantKt, timeZone, utcOffsetOffsetIn);
            if (period.getTotalNanoseconds() != 0) {
                Duration.Companion companion = Duration.INSTANCE;
                instantLocalDateTimeToInstant = check$InstantKt__InstantKt(instantLocalDateTimeToInstant.m10423plusLRDsOJo(DurationKt.toDuration(period.getTotalNanoseconds(), DurationUnit.NANOSECONDS)), timeZone);
            }
            return check$InstantKt__InstantKt(instantLocalDateTimeToInstant, timeZone);
        } catch (ArithmeticException e) {
            throw new DateTimeArithmeticException("Arithmetic overflow when adding CalendarPeriod to an Instant", e);
        } catch (IllegalArgumentException e2) {
            throw new DateTimeArithmeticException("Boundaries of Instant exceeded when adding CalendarPeriod", e2);
        }
    }

    public static final Instant minus(Instant instant, DateTimePeriod period, TimeZone timeZone) {
        Intrinsics.checkNotNullParameter(instant, "<this>");
        Intrinsics.checkNotNullParameter(period, "period");
        Intrinsics.checkNotNullParameter(timeZone, "timeZone");
        if (period.getTotalNanoseconds() != Long.MIN_VALUE) {
            return InstantKt.plus(instant, DateTimePeriodKt.buildDateTimePeriod(-period.getTotalMonths(), -period.getDays(), -period.getTotalNanoseconds()), timeZone);
        }
        return InstantKt.plus(InstantKt.plus(instant, DateTimePeriodKt.buildDateTimePeriod(-period.getTotalMonths(), -period.getDays(), -(period.getTotalNanoseconds() + 1)), timeZone), 1, DateTimeUnit.INSTANCE.getNANOSECOND());
    }

    public static final DateTimePeriod periodUntil(Instant instant, Instant other, TimeZone timeZone) {
        int i;
        Intrinsics.checkNotNullParameter(instant, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        Intrinsics.checkNotNullParameter(timeZone, "timeZone");
        UtcOffset utcOffsetOffsetIn = TimeZoneKt.offsetIn(instant, timeZone);
        LocalDateTime localDateTimeFailing$InstantKt__InstantKt = toLocalDateTimeFailing$InstantKt__InstantKt(instant, utcOffsetOffsetIn);
        LocalDateTime localDateTimeFailing$InstantKt__InstantKt2 = toLocalDateTimeFailing$InstantKt__InstantKt(other, timeZone);
        Instant instantLocalDateTimeToInstant = TimeZoneKt.localDateTimeToInstant(LocalDateKt.atTime(localDateTimeFailing$InstantKt__InstantKt2.getDate(), localDateTimeFailing$InstantKt__InstantKt.getTime()), timeZone, utcOffsetOffsetIn);
        if (other.compareTo(instant) <= 0 || instantLocalDateTimeToInstant.compareTo(other) <= 0) {
            i = (other.compareTo(instant) >= 0 || instantLocalDateTimeToInstant.compareTo(other) >= 0) ? 0 : 1;
        } else {
            i = -1;
        }
        LocalDate localDatePlus = LocalDateKt.plus(localDateTimeFailing$InstantKt__InstantKt2.getDate(), i, DateTimeUnit.INSTANCE.getDAY());
        long jUntil = InstantKt.until(TimeZoneKt.localDateTimeToInstant(LocalDateKt.atTime(localDatePlus, localDateTimeFailing$InstantKt__InstantKt.getTime()), timeZone, utcOffsetOffsetIn), other, DateTimeUnit.INSTANCE.getNANOSECOND());
        DatePeriod datePeriodMinus = LocalDateKt.minus(localDatePlus, localDateTimeFailing$InstantKt__InstantKt.getDate());
        return DateTimePeriodKt.buildDateTimePeriod(datePeriodMinus.getTotalMonths(), datePeriodMinus.getDays(), jUntil);
    }

    public static final long until(Instant instant, Instant other, DateTimeUnit unit, TimeZone timeZone) {
        int i;
        Intrinsics.checkNotNullParameter(instant, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        Intrinsics.checkNotNullParameter(unit, "unit");
        Intrinsics.checkNotNullParameter(timeZone, "timeZone");
        if (unit instanceof DateTimeUnit.DateBased) {
            LocalDateTime localDateTimeFailing$InstantKt__InstantKt = toLocalDateTimeFailing$InstantKt__InstantKt(instant, timeZone);
            LocalDateTime localDateTimeFailing$InstantKt__InstantKt2 = toLocalDateTimeFailing$InstantKt__InstantKt(other, timeZone);
            Instant instantLocalDateTimeToInstant = TimeZoneKt.localDateTimeToInstant(LocalDateKt.atTime(localDateTimeFailing$InstantKt__InstantKt2.getDate(), localDateTimeFailing$InstantKt__InstantKt.getTime()), timeZone, TimeZoneKt.offsetIn(instant, timeZone));
            if (other.compareTo(instant) <= 0 || instantLocalDateTimeToInstant.compareTo(other) <= 0) {
                i = (other.compareTo(instant) >= 0 || instantLocalDateTimeToInstant.compareTo(other) >= 0) ? 0 : 1;
            } else {
                i = -1;
            }
            return LocalDateJvmKt.m10566until(localDateTimeFailing$InstantKt__InstantKt.getDate(), LocalDateKt.plus(localDateTimeFailing$InstantKt__InstantKt2.getDate(), i, DateTimeUnit.INSTANCE.getDAY()), (DateTimeUnit.DateBased) unit);
        }
        if (!(unit instanceof DateTimeUnit.TimeBased)) {
            throw new NoWhenBranchMatchedException();
        }
        check$InstantKt__InstantKt(instant, timeZone);
        check$InstantKt__InstantKt(other, timeZone);
        return InstantKt.until(instant, other, (DateTimeUnit.TimeBased) unit);
    }

    public static final long until(Instant instant, Instant other, DateTimeUnit.TimeBased unit) {
        Intrinsics.checkNotNullParameter(instant, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        Intrinsics.checkNotNullParameter(unit, "unit");
        try {
            return MathKt.multiplyAddAndDivide(other.getEpochSeconds() - instant.getEpochSeconds(), C.NANOS_PER_SECOND, other.getNanosecondsOfSecond() - instant.getNanosecondsOfSecond(), unit.getNanoseconds());
        } catch (ArithmeticException unused) {
            return instant.compareTo(other) < 0 ? Long.MAX_VALUE : Long.MIN_VALUE;
        }
    }

    public static final int daysUntil(Instant instant, Instant other, TimeZone timeZone) {
        Intrinsics.checkNotNullParameter(instant, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        Intrinsics.checkNotNullParameter(timeZone, "timeZone");
        return MathKt.clampToInt(InstantKt.until(instant, other, DateTimeUnit.INSTANCE.getDAY(), timeZone));
    }

    public static final int monthsUntil(Instant instant, Instant other, TimeZone timeZone) {
        Intrinsics.checkNotNullParameter(instant, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        Intrinsics.checkNotNullParameter(timeZone, "timeZone");
        return MathKt.clampToInt(InstantKt.until(instant, other, DateTimeUnit.INSTANCE.getMONTH(), timeZone));
    }

    public static final int yearsUntil(Instant instant, Instant other, TimeZone timeZone) {
        Intrinsics.checkNotNullParameter(instant, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        Intrinsics.checkNotNullParameter(timeZone, "timeZone");
        return MathKt.clampToInt(InstantKt.until(instant, other, DateTimeUnit.INSTANCE.getYEAR(), timeZone));
    }

    public static final DateTimePeriod minus(Instant instant, Instant other, TimeZone timeZone) {
        Intrinsics.checkNotNullParameter(instant, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        Intrinsics.checkNotNullParameter(timeZone, "timeZone");
        return InstantKt.periodUntil(other, instant, timeZone);
    }

    @Deprecated(message = "Use the plus overload with an explicit number of units", replaceWith = @ReplaceWith(expression = "this.plus(1, unit, timeZone)", imports = {}))
    public static final Instant plus(Instant instant, DateTimeUnit unit, TimeZone timeZone) {
        Intrinsics.checkNotNullParameter(instant, "<this>");
        Intrinsics.checkNotNullParameter(unit, "unit");
        Intrinsics.checkNotNullParameter(timeZone, "timeZone");
        return InstantKt.plus(instant, 1L, unit, timeZone);
    }

    @Deprecated(message = "Use the minus overload with an explicit number of units", replaceWith = @ReplaceWith(expression = "this.minus(1, unit, timeZone)", imports = {}))
    public static final Instant minus(Instant instant, DateTimeUnit unit, TimeZone timeZone) {
        Intrinsics.checkNotNullParameter(instant, "<this>");
        Intrinsics.checkNotNullParameter(unit, "unit");
        Intrinsics.checkNotNullParameter(timeZone, "timeZone");
        return InstantKt.plus(instant, -1, unit, timeZone);
    }

    @Deprecated(message = "Use the plus overload with an explicit number of units", replaceWith = @ReplaceWith(expression = "this.plus(1, unit)", imports = {}))
    public static final Instant plus(Instant instant, DateTimeUnit.TimeBased unit) {
        Intrinsics.checkNotNullParameter(instant, "<this>");
        Intrinsics.checkNotNullParameter(unit, "unit");
        return InstantKt.plus(instant, 1L, unit);
    }

    @Deprecated(message = "Use the minus overload with an explicit number of units", replaceWith = @ReplaceWith(expression = "this.minus(1, unit)", imports = {}))
    public static final Instant minus(Instant instant, DateTimeUnit.TimeBased unit) {
        Intrinsics.checkNotNullParameter(instant, "<this>");
        Intrinsics.checkNotNullParameter(unit, "unit");
        return InstantKt.plus(instant, -1L, unit);
    }

    public static final Instant plus(Instant instant, int i, DateTimeUnit unit, TimeZone timeZone) {
        Intrinsics.checkNotNullParameter(instant, "<this>");
        Intrinsics.checkNotNullParameter(unit, "unit");
        Intrinsics.checkNotNullParameter(timeZone, "timeZone");
        return InstantKt.plus(instant, i, unit, timeZone);
    }

    public static final Instant minus(Instant instant, int i, DateTimeUnit unit, TimeZone timeZone) {
        Intrinsics.checkNotNullParameter(instant, "<this>");
        Intrinsics.checkNotNullParameter(unit, "unit");
        Intrinsics.checkNotNullParameter(timeZone, "timeZone");
        return InstantKt.plus(instant, -i, unit, timeZone);
    }

    public static final Instant plus(Instant instant, int i, DateTimeUnit.TimeBased unit) {
        Intrinsics.checkNotNullParameter(instant, "<this>");
        Intrinsics.checkNotNullParameter(unit, "unit");
        return InstantKt.plus(instant, i, unit);
    }

    public static final Instant minus(Instant instant, int i, DateTimeUnit.TimeBased unit) {
        Intrinsics.checkNotNullParameter(instant, "<this>");
        Intrinsics.checkNotNullParameter(unit, "unit");
        return InstantKt.minus(instant, i, unit);
    }

    public static final Instant plus(Instant instant, long j, DateTimeUnit unit, TimeZone timeZone) {
        Intrinsics.checkNotNullParameter(instant, "<this>");
        Intrinsics.checkNotNullParameter(unit, "unit");
        Intrinsics.checkNotNullParameter(timeZone, "timeZone");
        try {
            if (unit instanceof DateTimeUnit.DateBased) {
                UtcOffset utcOffsetOffsetIn = TimeZoneKt.offsetIn(instant, timeZone);
                return TimeZoneKt.localDateTimeToInstant(plus$InstantKt__InstantKt(toLocalDateTimeFailing$InstantKt__InstantKt(instant, utcOffsetOffsetIn), j, (DateTimeUnit.DateBased) unit), timeZone, utcOffsetOffsetIn);
            }
            if (!(unit instanceof DateTimeUnit.TimeBased)) {
                throw new NoWhenBranchMatchedException();
            }
            return check$InstantKt__InstantKt(InstantKt.plus(check$InstantKt__InstantKt(instant, timeZone), j, (DateTimeUnit.TimeBased) unit), timeZone);
        } catch (ArithmeticException e) {
            throw new DateTimeArithmeticException("Arithmetic overflow when adding to an Instant", e);
        } catch (IllegalArgumentException e2) {
            throw new DateTimeArithmeticException("Boundaries of Instant exceeded when adding a value", e2);
        }
    }

    public static final Instant minus(Instant instant, long j, DateTimeUnit unit, TimeZone timeZone) {
        Intrinsics.checkNotNullParameter(instant, "<this>");
        Intrinsics.checkNotNullParameter(unit, "unit");
        Intrinsics.checkNotNullParameter(timeZone, "timeZone");
        if (j != Long.MIN_VALUE) {
            return InstantKt.plus(instant, -j, unit, timeZone);
        }
        return InstantKt.plus(InstantKt.plus(instant, -(j + 1), unit, timeZone), 1, unit, timeZone);
    }

    public static final Instant plus(Instant instant, long j, DateTimeUnit.TimeBased unit) {
        long j2;
        Intrinsics.checkNotNullParameter(instant, "<this>");
        Intrinsics.checkNotNullParameter(unit, "unit");
        try {
            j2 = j;
            try {
                DivRemResult divRemResultMultiplyAndDivide = MathKt.multiplyAndDivide(j2, unit.getNanoseconds(), C.NANOS_PER_SECOND);
                long q = divRemResultMultiplyAndDivide.getQ();
                long r = divRemResultMultiplyAndDivide.getR();
                Duration.Companion companion = Duration.INSTANCE;
                Instant instantM10423plusLRDsOJo = instant.m10423plusLRDsOJo(DurationKt.toDuration(q, DurationUnit.SECONDS));
                Duration.Companion companion2 = Duration.INSTANCE;
                return instantM10423plusLRDsOJo.m10423plusLRDsOJo(DurationKt.toDuration(r, DurationUnit.NANOSECONDS));
            } catch (ArithmeticException unused) {
                return Instant.Companion.fromEpochSeconds$default(Instant.INSTANCE, j2 > 0 ? Long.MAX_VALUE : Long.MIN_VALUE, 0L, 2, null);
            } catch (IllegalArgumentException unused2) {
                return Instant.Companion.fromEpochSeconds$default(Instant.INSTANCE, j2 > 0 ? Long.MAX_VALUE : Long.MIN_VALUE, 0L, 2, null);
            }
        } catch (ArithmeticException unused3) {
            j2 = j;
        } catch (IllegalArgumentException unused4) {
            j2 = j;
        }
    }

    public static final Instant minus(Instant instant, long j, DateTimeUnit.TimeBased unit) {
        Intrinsics.checkNotNullParameter(instant, "<this>");
        Intrinsics.checkNotNullParameter(unit, "unit");
        if (j != Long.MIN_VALUE) {
            return InstantKt.plus(instant, -j, unit);
        }
        return InstantKt.plus(InstantKt.plus(instant, -(j + 1), unit), 1, unit);
    }

    public static final long minus(Instant instant, Instant other, DateTimeUnit unit, TimeZone timeZone) {
        Intrinsics.checkNotNullParameter(instant, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        Intrinsics.checkNotNullParameter(unit, "unit");
        Intrinsics.checkNotNullParameter(timeZone, "timeZone");
        return InstantKt.until(other, instant, unit, timeZone);
    }

    public static final long minus(Instant instant, Instant other, DateTimeUnit.TimeBased unit) {
        Intrinsics.checkNotNullParameter(instant, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        Intrinsics.checkNotNullParameter(unit, "unit");
        return InstantKt.until(other, instant, unit);
    }

    public static /* synthetic */ String format$default(Instant instant, DateTimeFormat dateTimeFormat, UtcOffset utcOffset, int i, Object obj) {
        if ((i & 2) != 0) {
            utcOffset = UtcOffset.INSTANCE.getZERO();
        }
        return InstantKt.format(instant, dateTimeFormat, utcOffset);
    }

    public static final String format(final Instant instant, DateTimeFormat<DateTimeComponents> format, final UtcOffset offset) {
        Intrinsics.checkNotNullParameter(instant, "<this>");
        Intrinsics.checkNotNullParameter(format, "format");
        Intrinsics.checkNotNullParameter(offset, "offset");
        return DateTimeComponentsKt.format(format, new Function1() { // from class: kotlinx.datetime.InstantKt__InstantKt$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return InstantKt__InstantKt.format$lambda$7$InstantKt__InstantKt(instant, offset, (DateTimeComponents) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit format$lambda$7$InstantKt__InstantKt(Instant instant, UtcOffset utcOffset, DateTimeComponents format) {
        Intrinsics.checkNotNullParameter(format, "$this$format");
        format.setDateTimeOffset(instant, utcOffset);
        return Unit.INSTANCE;
    }

    public static /* synthetic */ Instant parseOrNull$default(Instant.Companion companion, CharSequence charSequence, DateTimeFormat dateTimeFormat, int i, Object obj) {
        if ((i & 2) != 0) {
            dateTimeFormat = DateTimeComponents.Formats.INSTANCE.getISO_DATE_TIME_OFFSET();
        }
        return InstantKt.parseOrNull(companion, charSequence, dateTimeFormat);
    }

    public static final Instant parseOrNull(Instant.Companion companion, CharSequence input, DateTimeFormat<DateTimeComponents> format) {
        Intrinsics.checkNotNullParameter(companion, "<this>");
        Intrinsics.checkNotNullParameter(input, "input");
        Intrinsics.checkNotNullParameter(format, "format");
        try {
            DateTimeComponents orNull = format.parseOrNull(input);
            if (orNull != null) {
                return DateTimeComponents.toInstantUsingOffset$default(orNull, null, 1, null);
            }
            return null;
        } catch (IllegalArgumentException unused) {
            return null;
        }
    }

    private static final LocalDateTime toLocalDateTimeFailing$InstantKt__InstantKt(Instant instant, UtcOffset utcOffset) {
        try {
            return TimeZoneKt.toLocalDateTime(instant, utcOffset);
        } catch (IllegalArgumentException e) {
            throw new DateTimeArithmeticException("Can not convert instant " + instant + " to LocalDateTime to perform computations", e);
        }
    }

    private static final LocalDateTime toLocalDateTimeFailing$InstantKt__InstantKt(Instant instant, TimeZone timeZone) {
        return toLocalDateTimeFailing$InstantKt__InstantKt(instant, TimeZoneKt.offsetIn(instant, timeZone));
    }

    private static final Instant check$InstantKt__InstantKt(Instant instant, TimeZone timeZone) {
        toLocalDateTimeFailing$InstantKt__InstantKt(instant, timeZone);
        return instant;
    }

    private static final LocalDateTime plus$InstantKt__InstantKt(LocalDateTime localDateTime, long j, DateTimeUnit.DateBased dateBased) {
        return LocalDateKt.atTime(LocalDateJvmKt.plus(localDateTime.getDate(), j, dateBased), localDateTime.getTime());
    }

    private static final LocalDateTime plus$InstantKt__InstantKt(LocalDateTime localDateTime, int i, DateTimeUnit.DateBased dateBased) {
        return LocalDateKt.atTime(LocalDateKt.plus(localDateTime.getDate(), i, dateBased), localDateTime.getTime());
    }
}
