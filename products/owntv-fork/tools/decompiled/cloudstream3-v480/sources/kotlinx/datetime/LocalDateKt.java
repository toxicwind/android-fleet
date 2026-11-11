package kotlinx.datetime;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.datetime.format.DateTimeFormat;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: LocalDate.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000R\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\u001a \u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0003H\u0007\u001a \u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0005\u001a\u00020\u0003H\u0007\u001a\u0018\u0010\b\u001a\u00020\t*\u00020\u00012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00010\n\u001a\f\u0010\u000b\u001a\u00020\u0001*\u00020\tH\u0007\u001a.\u0010\f\u001a\u00020\r*\u00020\u00012\u0006\u0010\u000e\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u00032\b\b\u0002\u0010\u0010\u001a\u00020\u00032\b\b\u0002\u0010\u0011\u001a\u00020\u0003\u001a\u0012\u0010\f\u001a\u00020\r*\u00020\u00012\u0006\u0010\u0012\u001a\u00020\u0013\u001a\u0015\u0010\u0014\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0015\u001a\u00020\u0016H\u0086\u0002\u001a\u0015\u0010\u0014\u001a\u00020\u0016*\u00020\u00012\u0006\u0010\u0017\u001a\u00020\u0001H\u0086\u0002\u001a\u0014\u0010\u0014\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0018\u001a\u00020\u0019H\u0007\u001a\u001a\u0010\u001a\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u001b\u001a\u00020\u00032\u0006\u0010\u0018\u001a\u00020\u0019\u001a\u001a\u0010\u0014\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u001b\u001a\u00020\u00032\u0006\u0010\u0018\u001a\u00020\u0019\u001a\u001a\u0010\u0014\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u0018\u001a\u00020\u0019\u001a\u000e\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00010\nH\u0000\u001a\u0012\u0010\u001e\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u001f\u001a\u00020 \u001a\u0012\u0010!\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u001f\u001a\u00020 \u001a\u0012\u0010\"\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u001f\u001a\u00020 \u001a\u0012\u0010#\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u001f\u001a\u00020 \"\u000e\u0010$\u001a\u00020\u0003X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010%\u001a\u00020\u0003X\u0080T¢\u0006\u0002\n\u0000¨\u0006&"}, d2 = {"LocalDate", "Lkotlinx/datetime/LocalDate;", "year", "", "monthNumber", "dayOfMonth", "month", "Lkotlinx/datetime/Month;", "format", "", "Lkotlinx/datetime/format/DateTimeFormat;", "toLocalDate", "atTime", "Lkotlinx/datetime/LocalDateTime;", "hour", "minute", "second", "nanosecond", "time", "Lkotlinx/datetime/LocalTime;", "minus", TypedValues.CycleType.S_WAVE_PERIOD, "Lkotlinx/datetime/DatePeriod;", "other", "unit", "Lkotlinx/datetime/DateTimeUnit$DateBased;", "plus", ES6Iterator.VALUE_PROPERTY, "", "getIsoDateFormat", "previousOrSame", "dayOfWeek", "Lkotlinx/datetime/DayOfWeek;", "nextOrSame", "previous", "next", "YEAR_MAX", "YEAR_MIN", "kotlinx-datetime"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class LocalDateKt {
    public static final int YEAR_MAX = 999999999;
    public static final int YEAR_MIN = -999999999;

    @Deprecated(level = DeprecationLevel.WARNING, message = "Use the constructor that accepts a 'month' and a 'day'", replaceWith = @ReplaceWith(expression = "LocalDate(year = year, month = monthNumber, day = dayOfMonth)", imports = {}))
    public static final LocalDate LocalDate(int i, int i2, int i3) {
        return new LocalDate(i, i2, i3);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Use the constructor that accepts a 'day'", replaceWith = @ReplaceWith(expression = "LocalDate(year = year, month = month, day = dayOfMonth)", imports = {}))
    public static final LocalDate LocalDate(int i, Month month, int i2) {
        Intrinsics.checkNotNullParameter(month, "month");
        return new LocalDate(i, month, i2);
    }

    public static final String format(LocalDate localDate, DateTimeFormat<LocalDate> format) {
        Intrinsics.checkNotNullParameter(localDate, "<this>");
        Intrinsics.checkNotNullParameter(format, "format");
        return format.format(localDate);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Removed to support more idiomatic code. See https://github.com/Kotlin/kotlinx-datetime/issues/339", replaceWith = @ReplaceWith(expression = "LocalDate.parse(this)", imports = {}))
    public static final LocalDate toLocalDate(String str) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        return LocalDate.Companion.parse$default(LocalDate.INSTANCE, str, null, 2, null);
    }

    public static /* synthetic */ LocalDateTime atTime$default(LocalDate localDate, int i, int i2, int i3, int i4, int i5, Object obj) {
        if ((i5 & 4) != 0) {
            i3 = 0;
        }
        if ((i5 & 8) != 0) {
            i4 = 0;
        }
        return atTime(localDate, i, i2, i3, i4);
    }

    public static final LocalDateTime atTime(LocalDate localDate, int i, int i2, int i3, int i4) {
        Intrinsics.checkNotNullParameter(localDate, "<this>");
        return new LocalDateTime(localDate.getYear(), localDate.m10564getMonth(), localDate.getDay(), i, i2, i3, i4);
    }

    public static final LocalDateTime atTime(LocalDate localDate, LocalTime time) {
        Intrinsics.checkNotNullParameter(localDate, "<this>");
        Intrinsics.checkNotNullParameter(time, "time");
        return new LocalDateTime(localDate, time);
    }

    public static final LocalDate minus(LocalDate localDate, DatePeriod period) {
        Intrinsics.checkNotNullParameter(localDate, "<this>");
        Intrinsics.checkNotNullParameter(period, "period");
        if (period.getDays() != Integer.MIN_VALUE && period.getMonths() != Integer.MIN_VALUE) {
            return LocalDateJvmKt.plus(localDate, new DatePeriod(-period.getYears(), -period.getMonths(), -period.getDays()));
        }
        return minus(minus(minus(localDate, period.getYears(), (DateTimeUnit.DateBased) DateTimeUnit.INSTANCE.getYEAR()), period.getMonths(), (DateTimeUnit.DateBased) DateTimeUnit.INSTANCE.getMONTH()), period.getDays(), (DateTimeUnit.DateBased) DateTimeUnit.INSTANCE.getDAY());
    }

    public static final DatePeriod minus(LocalDate localDate, LocalDate other) {
        Intrinsics.checkNotNullParameter(localDate, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        return LocalDateJvmKt.periodUntil(other, localDate);
    }

    @Deprecated(message = "Use the minus overload with an explicit number of units", replaceWith = @ReplaceWith(expression = "this.minus(1, unit)", imports = {}))
    public static final LocalDate minus(LocalDate localDate, DateTimeUnit.DateBased unit) {
        Intrinsics.checkNotNullParameter(localDate, "<this>");
        Intrinsics.checkNotNullParameter(unit, "unit");
        return plus(localDate, -1, unit);
    }

    public static final LocalDate plus(LocalDate localDate, int i, DateTimeUnit.DateBased unit) {
        Intrinsics.checkNotNullParameter(localDate, "<this>");
        Intrinsics.checkNotNullParameter(unit, "unit");
        return LocalDateJvmKt.plus(localDate, i, unit);
    }

    public static final LocalDate minus(LocalDate localDate, int i, DateTimeUnit.DateBased unit) {
        Intrinsics.checkNotNullParameter(localDate, "<this>");
        Intrinsics.checkNotNullParameter(unit, "unit");
        return LocalDateJvmKt.plus(localDate, -i, unit);
    }

    public static final LocalDate minus(LocalDate localDate, long j, DateTimeUnit.DateBased unit) {
        Intrinsics.checkNotNullParameter(localDate, "<this>");
        Intrinsics.checkNotNullParameter(unit, "unit");
        return LocalDateJvmKt.plus(localDate, -j, unit);
    }

    public static final DateTimeFormat<LocalDate> getIsoDateFormat() {
        return LocalDate.Formats.INSTANCE.getISO();
    }

    public static final LocalDate previousOrSame(LocalDate localDate, DayOfWeek dayOfWeek) {
        Intrinsics.checkNotNullParameter(localDate, "<this>");
        Intrinsics.checkNotNullParameter(dayOfWeek, "dayOfWeek");
        int isoDayNumber = (DayOfWeekKt.getIsoDayNumber(localDate.m10563getDayOfWeek()) - DayOfWeekKt.getIsoDayNumber(dayOfWeek)) % 7;
        return minus(localDate, isoDayNumber + ((((isoDayNumber ^ 7) & ((-isoDayNumber) | isoDayNumber)) >> 31) & 7), (DateTimeUnit.DateBased) DateTimeUnit.INSTANCE.getDAY());
    }

    public static final LocalDate nextOrSame(LocalDate localDate, DayOfWeek dayOfWeek) {
        Intrinsics.checkNotNullParameter(localDate, "<this>");
        Intrinsics.checkNotNullParameter(dayOfWeek, "dayOfWeek");
        int isoDayNumber = (DayOfWeekKt.getIsoDayNumber(dayOfWeek) - DayOfWeekKt.getIsoDayNumber(localDate.m10563getDayOfWeek())) % 7;
        return plus(localDate, isoDayNumber + ((((isoDayNumber ^ 7) & ((-isoDayNumber) | isoDayNumber)) >> 31) & 7), DateTimeUnit.INSTANCE.getDAY());
    }

    public static final LocalDate previous(LocalDate localDate, DayOfWeek dayOfWeek) {
        Intrinsics.checkNotNullParameter(localDate, "<this>");
        Intrinsics.checkNotNullParameter(dayOfWeek, "dayOfWeek");
        int isoDayNumber = ((DayOfWeekKt.getIsoDayNumber(localDate.m10563getDayOfWeek()) - DayOfWeekKt.getIsoDayNumber(dayOfWeek)) - 1) % 7;
        return minus(localDate, isoDayNumber + ((((isoDayNumber ^ 7) & ((-isoDayNumber) | isoDayNumber)) >> 31) & 7) + 1, (DateTimeUnit.DateBased) DateTimeUnit.INSTANCE.getDAY());
    }

    public static final LocalDate next(LocalDate localDate, DayOfWeek dayOfWeek) {
        Intrinsics.checkNotNullParameter(localDate, "<this>");
        Intrinsics.checkNotNullParameter(dayOfWeek, "dayOfWeek");
        int isoDayNumber = ((DayOfWeekKt.getIsoDayNumber(dayOfWeek) - DayOfWeekKt.getIsoDayNumber(localDate.m10563getDayOfWeek())) - 1) % 7;
        return plus(localDate, isoDayNumber + ((((isoDayNumber ^ 7) & ((-isoDayNumber) | isoDayNumber)) >> 31) & 7) + 1, DateTimeUnit.INSTANCE.getDAY());
    }
}
