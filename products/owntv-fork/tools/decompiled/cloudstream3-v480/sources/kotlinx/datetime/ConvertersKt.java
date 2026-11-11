package kotlinx.datetime;

import j$.time.Period;
import j$.time.ZoneId;
import j$.time.ZoneOffset;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Converters.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a\u0011\u0010\u0002\u001a\u00020\u0001*\u00020\u0000¢\u0006\u0004\b\u0002\u0010\u0003\u001a\u0011\u0010\u0004\u001a\u00020\u0000*\u00020\u0001¢\u0006\u0004\b\u0004\u0010\u0005\u001a\u0011\u0010\b\u001a\u00020\u0007*\u00020\u0006¢\u0006\u0004\b\b\u0010\t\u001a\u0011\u0010\n\u001a\u00020\u0006*\u00020\u0007¢\u0006\u0004\b\n\u0010\u000b\u001a\u0011\u0010\u000e\u001a\u00020\r*\u00020\f¢\u0006\u0004\b\u000e\u0010\u000f\u001a\u0011\u0010\u0010\u001a\u00020\f*\u00020\r¢\u0006\u0004\b\u0010\u0010\u0011\u001a\u0011\u0010\u0014\u001a\u00020\u0013*\u00020\u0012¢\u0006\u0004\b\u0014\u0010\u0015\u001a\u0011\u0010\u0016\u001a\u00020\u0012*\u00020\u0013¢\u0006\u0004\b\u0016\u0010\u0017\u001a\u0011\u0010\u001a\u001a\u00020\u0019*\u00020\u0018¢\u0006\u0004\b\u001a\u0010\u001b\u001a\u0011\u0010\u001c\u001a\u00020\u0018*\u00020\u0019¢\u0006\u0004\b\u001c\u0010\u001d\u001a\u0011\u0010 \u001a\u00020\u001f*\u00020\u001e¢\u0006\u0004\b \u0010!\u001a\u0011\u0010\"\u001a\u00020\u001e*\u00020\u001f¢\u0006\u0004\b\"\u0010#\u001a\u0013\u0010$\u001a\u00020\u001e*\u00020\u001fH\u0007¢\u0006\u0004\b$\u0010#\u001a\u0011\u0010 \u001a\u00020\u001f*\u00020%¢\u0006\u0004\b \u0010&\u001a\u0011\u0010'\u001a\u00020%*\u00020\u001f¢\u0006\u0004\b'\u0010(\u001a\u0011\u0010+\u001a\u00020**\u00020)¢\u0006\u0004\b+\u0010,\u001a\u0011\u0010-\u001a\u00020)*\u00020*¢\u0006\u0004\b-\u0010.\u001a\u0011\u00101\u001a\u000200*\u00020/¢\u0006\u0004\b1\u00102\u001a\u0011\u00103\u001a\u00020/*\u000200¢\u0006\u0004\b3\u00104\u001a\u0011\u00107\u001a\u000206*\u000205¢\u0006\u0004\b7\u00108\u001a\u0011\u00109\u001a\u000205*\u000206¢\u0006\u0004\b9\u0010:\u001a\u0011\u0010\u000e\u001a\u00020\r*\u00020;¢\u0006\u0004\b\u000e\u0010<\u001a\u0011\u0010=\u001a\u00020;*\u00020\r¢\u0006\u0004\b=\u0010>¨\u0006?"}, d2 = {"Lkotlinx/datetime/LocalDateTime;", "j$/time/LocalDateTime", "toJavaLocalDateTime", "(Lkotlinx/datetime/LocalDateTime;)Lj$/time/LocalDateTime;", "toKotlinLocalDateTime", "(Lj$/time/LocalDateTime;)Lkotlinx/datetime/LocalDateTime;", "Lkotlinx/datetime/LocalTime;", "j$/time/LocalTime", "toJavaLocalTime", "(Lkotlinx/datetime/LocalTime;)Lj$/time/LocalTime;", "toKotlinLocalTime", "(Lj$/time/LocalTime;)Lkotlinx/datetime/LocalTime;", "Lkotlinx/datetime/LocalDate;", "j$/time/LocalDate", "toJavaLocalDate", "(Lkotlinx/datetime/LocalDate;)Lj$/time/LocalDate;", "toKotlinLocalDate", "(Lj$/time/LocalDate;)Lkotlinx/datetime/LocalDate;", "Lkotlinx/datetime/DatePeriod;", "j$/time/Period", "toJavaPeriod", "(Lkotlinx/datetime/DatePeriod;)Lj$/time/Period;", "toKotlinDatePeriod", "(Lj$/time/Period;)Lkotlinx/datetime/DatePeriod;", "Lkotlinx/datetime/TimeZone;", "j$/time/ZoneId", "toJavaZoneId", "(Lkotlinx/datetime/TimeZone;)Lj$/time/ZoneId;", "toKotlinTimeZone", "(Lj$/time/ZoneId;)Lkotlinx/datetime/TimeZone;", "Lkotlinx/datetime/FixedOffsetTimeZone;", "j$/time/ZoneOffset", "toJavaZoneOffset", "(Lkotlinx/datetime/FixedOffsetTimeZone;)Lj$/time/ZoneOffset;", "toKotlinFixedOffsetTimeZone", "(Lj$/time/ZoneOffset;)Lkotlinx/datetime/FixedOffsetTimeZone;", "toKotlinZoneOffset", "Lkotlinx/datetime/UtcOffset;", "(Lkotlinx/datetime/UtcOffset;)Lj$/time/ZoneOffset;", "toKotlinUtcOffset", "(Lj$/time/ZoneOffset;)Lkotlinx/datetime/UtcOffset;", "Lkotlinx/datetime/Month;", "j$/time/Month", "toJavaMonth", "(Lkotlinx/datetime/Month;)Lj$/time/Month;", "toKotlinMonth", "(Lj$/time/Month;)Lkotlinx/datetime/Month;", "Lkotlinx/datetime/DayOfWeek;", "j$/time/DayOfWeek", "toJavaDayOfWeek", "(Lkotlinx/datetime/DayOfWeek;)Lj$/time/DayOfWeek;", "toKotlinDayOfWeek", "(Lj$/time/DayOfWeek;)Lkotlinx/datetime/DayOfWeek;", "Lkotlinx/datetime/YearMonth;", "j$/time/YearMonth", "toJavaYearMonth", "(Lkotlinx/datetime/YearMonth;)Lj$/time/YearMonth;", "toKotlinYearMonth", "(Lj$/time/YearMonth;)Lkotlinx/datetime/YearMonth;", "Lkotlinx/datetime/LocalIsoWeekDate;", "(Lkotlinx/datetime/LocalIsoWeekDate;)Lj$/time/LocalDate;", "toKotlinLocalIsoWeekDate", "(Lj$/time/LocalDate;)Lkotlinx/datetime/LocalIsoWeekDate;", "kotlinx-datetime"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class ConvertersKt {
    public static final j$.time.LocalDateTime toJavaLocalDateTime(LocalDateTime localDateTime) {
        Intrinsics.checkNotNullParameter(localDateTime, "<this>");
        return localDateTime.getValue();
    }

    public static final LocalDateTime toKotlinLocalDateTime(j$.time.LocalDateTime localDateTime) {
        Intrinsics.checkNotNullParameter(localDateTime, "<this>");
        return new LocalDateTime(localDateTime);
    }

    public static final j$.time.LocalTime toJavaLocalTime(LocalTime localTime) {
        Intrinsics.checkNotNullParameter(localTime, "<this>");
        return localTime.getValue();
    }

    public static final LocalTime toKotlinLocalTime(j$.time.LocalTime localTime) {
        Intrinsics.checkNotNullParameter(localTime, "<this>");
        return new LocalTime(localTime);
    }

    public static final j$.time.LocalDate toJavaLocalDate(LocalDate localDate) {
        Intrinsics.checkNotNullParameter(localDate, "<this>");
        return localDate.getValue();
    }

    public static final LocalDate toKotlinLocalDate(j$.time.LocalDate localDate) {
        Intrinsics.checkNotNullParameter(localDate, "<this>");
        return new LocalDate(localDate);
    }

    public static final Period toJavaPeriod(DatePeriod datePeriod) {
        Intrinsics.checkNotNullParameter(datePeriod, "<this>");
        Period periodOf = Period.of(datePeriod.getYears(), datePeriod.getMonths(), datePeriod.getDays());
        Intrinsics.checkNotNullExpressionValue(periodOf, "of(...)");
        return periodOf;
    }

    public static final DatePeriod toKotlinDatePeriod(Period period) {
        Intrinsics.checkNotNullParameter(period, "<this>");
        return new DatePeriod(period.getYears(), period.getMonths(), period.getDays());
    }

    public static final ZoneId toJavaZoneId(TimeZone timeZone) {
        Intrinsics.checkNotNullParameter(timeZone, "<this>");
        return timeZone.getZoneId();
    }

    public static final TimeZone toKotlinTimeZone(ZoneId zoneId) {
        Intrinsics.checkNotNullParameter(zoneId, "<this>");
        return TimeZone.INSTANCE.ofZone$kotlinx_datetime(zoneId);
    }

    public static final ZoneOffset toJavaZoneOffset(FixedOffsetTimeZone fixedOffsetTimeZone) {
        Intrinsics.checkNotNullParameter(fixedOffsetTimeZone, "<this>");
        return fixedOffsetTimeZone.getOffset().getZoneOffset();
    }

    public static final FixedOffsetTimeZone toKotlinFixedOffsetTimeZone(ZoneOffset zoneOffset) {
        Intrinsics.checkNotNullParameter(zoneOffset, "<this>");
        return new FixedOffsetTimeZone(new UtcOffset(zoneOffset));
    }

    @Deprecated(message = "Use toKotlinFixedOffsetTimeZone() instead.", replaceWith = @ReplaceWith(expression = "this.toKotlinFixedOffsetTimeZone()", imports = {}))
    public static final FixedOffsetTimeZone toKotlinZoneOffset(ZoneOffset zoneOffset) {
        Intrinsics.checkNotNullParameter(zoneOffset, "<this>");
        return toKotlinFixedOffsetTimeZone(zoneOffset);
    }

    public static final ZoneOffset toJavaZoneOffset(UtcOffset utcOffset) {
        Intrinsics.checkNotNullParameter(utcOffset, "<this>");
        return utcOffset.getZoneOffset();
    }

    public static final UtcOffset toKotlinUtcOffset(ZoneOffset zoneOffset) {
        Intrinsics.checkNotNullParameter(zoneOffset, "<this>");
        return new UtcOffset(zoneOffset);
    }

    public static final j$.time.Month toJavaMonth(Month month) {
        Intrinsics.checkNotNullParameter(month, "<this>");
        j$.time.Month monthOf = j$.time.Month.of(MonthKt.getNumber(month));
        Intrinsics.checkNotNullExpressionValue(monthOf, "of(...)");
        return monthOf;
    }

    public static final Month toKotlinMonth(j$.time.Month month) {
        Intrinsics.checkNotNullParameter(month, "<this>");
        return Month.getEntries().get(month.getValue() - 1);
    }

    public static final j$.time.DayOfWeek toJavaDayOfWeek(DayOfWeek dayOfWeek) {
        Intrinsics.checkNotNullParameter(dayOfWeek, "<this>");
        j$.time.DayOfWeek dayOfWeekOf = j$.time.DayOfWeek.of(DayOfWeekKt.getIsoDayNumber(dayOfWeek));
        Intrinsics.checkNotNullExpressionValue(dayOfWeekOf, "of(...)");
        return dayOfWeekOf;
    }

    public static final DayOfWeek toKotlinDayOfWeek(j$.time.DayOfWeek dayOfWeek) {
        Intrinsics.checkNotNullParameter(dayOfWeek, "<this>");
        return DayOfWeek.getEntries().get(dayOfWeek.getValue() - 1);
    }

    public static final j$.time.YearMonth toJavaYearMonth(YearMonth yearMonth) {
        Intrinsics.checkNotNullParameter(yearMonth, "<this>");
        return yearMonth.getValue();
    }

    public static final YearMonth toKotlinYearMonth(j$.time.YearMonth yearMonth) {
        Intrinsics.checkNotNullParameter(yearMonth, "<this>");
        return new YearMonth(yearMonth);
    }

    public static final j$.time.LocalDate toJavaLocalDate(LocalIsoWeekDate localIsoWeekDate) {
        Intrinsics.checkNotNullParameter(localIsoWeekDate, "<this>");
        return toJavaLocalDate(localIsoWeekDate.toLocalDate());
    }

    public static final LocalIsoWeekDate toKotlinLocalIsoWeekDate(j$.time.LocalDate localDate) {
        Intrinsics.checkNotNullParameter(localDate, "<this>");
        return LocalIsoWeekDateKt.toLocalIsoWeekDate(toKotlinLocalDate(localDate));
    }
}
