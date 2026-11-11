package kotlinx.datetime;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.tvprovider.media.tv.TvContractCompat;
import j$.time.DateTimeException;
import j$.time.format.DateTimeParseException;
import j$.time.temporal.ChronoUnit;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.ReplaceWith;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.datetime.format.DateTimeFormat;
import kotlinx.datetime.internal.MathJvmKt;
import kotlinx.datetime.internal.MathKt;
import kotlinx.datetime.internal.UtilKt;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: LocalDate.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\r\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0011\u001a+\u0010\u0006\u001a\u0004\u0018\u00010\u0004*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0004\b\u0006\u0010\u0007\u001a'\u0010\r\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\bH\u0007¢\u0006\u0004\b\r\u0010\u000e\u001a\u001b\u0010\u0011\u001a\u00020\u0004*\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u000fH\u0007¢\u0006\u0004\b\u0011\u0010\u0012\u001a#\u0010\u0011\u001a\u00020\u0004*\u00020\u00042\u0006\u0010\u0013\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00020\u000fH\u0001¢\u0006\u0004\b\u0011\u0010\u0014\u001a#\u0010\u0015\u001a\u00020\u0004*\u00020\u00042\u0006\u0010\u0013\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00020\u000fH\u0001¢\u0006\u0004\b\u0015\u0010\u0014\u001a!\u0010\u0011\u001a\u00020\u0004*\u00020\u00042\u0006\u0010\u0013\u001a\u00020\u00162\u0006\u0010\u0010\u001a\u00020\u000f¢\u0006\u0004\b\u0011\u0010\u0017\u001a\u0017\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u0018\u001a\u00020\u0016H\u0002¢\u0006\u0004\b\u001a\u0010\u001b\u001a\u001c\u0010\u0011\u001a\u00020\u0004*\u00020\u00042\u0006\u0010\u001d\u001a\u00020\u001cH\u0086\u0002¢\u0006\u0004\b\u0011\u0010\u001e\u001a\u0019\u0010 \u001a\u00020\u001c*\u00020\u00042\u0006\u0010\u001f\u001a\u00020\u0004¢\u0006\u0004\b \u0010!\u001a!\u0010\"\u001a\u00020\u0016*\u00020\u00042\u0006\u0010\u001f\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u000f¢\u0006\u0004\b\"\u0010#\u001a#\u0010%\u001a\u00020\b*\u00020\u00042\u0006\u0010\u001f\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u000fH\u0001¢\u0006\u0004\b\"\u0010$\u001a\u0019\u0010&\u001a\u00020\b*\u00020\u00042\u0006\u0010\u001f\u001a\u00020\u0004¢\u0006\u0004\b&\u0010'\u001a\u0019\u0010(\u001a\u00020\b*\u00020\u00042\u0006\u0010\u001f\u001a\u00020\u0004¢\u0006\u0004\b(\u0010'\u001a\u0019\u0010)\u001a\u00020\b*\u00020\u00042\u0006\u0010\u001f\u001a\u00020\u0004¢\u0006\u0004\b)\u0010'\"\u0014\u0010*\u001a\u00020\u00168\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b*\u0010+\"\u0014\u0010,\u001a\u00020\u00168\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b,\u0010+¨\u0006-"}, d2 = {"Lkotlinx/datetime/LocalDate$Companion;", "", TvContractCompat.PARAM_INPUT, "Lkotlinx/datetime/format/DateTimeFormat;", "Lkotlinx/datetime/LocalDate;", "format", "parseOrNull", "(Lkotlinx/datetime/LocalDate$Companion;Ljava/lang/CharSequence;Lkotlinx/datetime/format/DateTimeFormat;)Lkotlinx/datetime/LocalDate;", "", "year", "j$/time/Month", "month", "dayOfMonth", "LocalDate", "(ILj$/time/Month;I)Lkotlinx/datetime/LocalDate;", "Lkotlinx/datetime/DateTimeUnit$DateBased;", "unit", "plus", "(Lkotlinx/datetime/LocalDate;Lkotlinx/datetime/DateTimeUnit$DateBased;)Lkotlinx/datetime/LocalDate;", ES6Iterator.VALUE_PROPERTY, "(Lkotlinx/datetime/LocalDate;ILkotlinx/datetime/DateTimeUnit$DateBased;)Lkotlinx/datetime/LocalDate;", "minus", "", "(Lkotlinx/datetime/LocalDate;JLkotlinx/datetime/DateTimeUnit$DateBased;)Lkotlinx/datetime/LocalDate;", "epochDay", "j$/time/LocalDate", "ofEpochDayChecked", "(J)Lj$/time/LocalDate;", "Lkotlinx/datetime/DatePeriod;", TypedValues.CycleType.S_WAVE_PERIOD, "(Lkotlinx/datetime/LocalDate;Lkotlinx/datetime/DatePeriod;)Lkotlinx/datetime/LocalDate;", "other", "periodUntil", "(Lkotlinx/datetime/LocalDate;Lkotlinx/datetime/LocalDate;)Lkotlinx/datetime/DatePeriod;", "until", "(Lkotlinx/datetime/LocalDate;Lkotlinx/datetime/LocalDate;Lkotlinx/datetime/DateTimeUnit$DateBased;)J", "(Lkotlinx/datetime/LocalDate;Lkotlinx/datetime/LocalDate;Lkotlinx/datetime/DateTimeUnit$DateBased;)I", "untilJvm", "daysUntil", "(Lkotlinx/datetime/LocalDate;Lkotlinx/datetime/LocalDate;)I", "monthsUntil", "yearsUntil", "minEpochDay", "J", "maxEpochDay", "kotlinx-datetime"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class LocalDateJvmKt {
    private static final long minEpochDay = j$.time.LocalDate.MIN.toEpochDay();
    private static final long maxEpochDay = j$.time.LocalDate.MAX.toEpochDay();

    public static final LocalDate parseOrNull(LocalDate.Companion companion, CharSequence input, DateTimeFormat<LocalDate> format) {
        Intrinsics.checkNotNullParameter(companion, "<this>");
        Intrinsics.checkNotNullParameter(input, "input");
        Intrinsics.checkNotNullParameter(format, "format");
        if (format == LocalDate.Formats.INSTANCE.getISO()) {
            try {
                return new LocalDate(j$.time.LocalDate.parse(UtilKt.removeLeadingZerosFromLongYearFormLocalDate(input.toString())));
            } catch (DateTimeParseException unused) {
                return null;
            }
        }
        return format.parseOrNull(input);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Use the constructor that accepts a 'day'", replaceWith = @ReplaceWith(expression = "LocalDate(year = year, month = month.toKotlinMonth(), day = dayOfMonth)", imports = {}))
    public static final LocalDate LocalDate(int i, j$.time.Month month, int i2) {
        Intrinsics.checkNotNullParameter(month, "month");
        return new LocalDate(i, ConvertersKt.toKotlinMonth(month), i2);
    }

    @Deprecated(message = "Use the plus overload with an explicit number of units", replaceWith = @ReplaceWith(expression = "this.plus(1, unit)", imports = {}))
    public static final LocalDate plus(LocalDate localDate, DateTimeUnit.DateBased unit) {
        Intrinsics.checkNotNullParameter(localDate, "<this>");
        Intrinsics.checkNotNullParameter(unit, "unit");
        return plus(localDate, 1L, unit);
    }

    public static final LocalDate plus(LocalDate localDate, int i, DateTimeUnit.DateBased unit) {
        Intrinsics.checkNotNullParameter(localDate, "<this>");
        Intrinsics.checkNotNullParameter(unit, "unit");
        return plus(localDate, i, unit);
    }

    public static final LocalDate minus(LocalDate localDate, int i, DateTimeUnit.DateBased unit) {
        Intrinsics.checkNotNullParameter(localDate, "<this>");
        Intrinsics.checkNotNullParameter(unit, "unit");
        return plus(localDate, -i, unit);
    }

    public static final LocalDate plus(LocalDate localDate, long j, DateTimeUnit.DateBased unit) throws Exception {
        j$.time.LocalDate localDatePlusMonths;
        Intrinsics.checkNotNullParameter(localDate, "<this>");
        Intrinsics.checkNotNullParameter(unit, "unit");
        try {
            if (unit instanceof DateTimeUnit.DayBased) {
                localDatePlusMonths = ofEpochDayChecked(MathJvmKt.safeAdd(localDate.getValue().toEpochDay(), MathJvmKt.safeMultiply(j, ((DateTimeUnit.DayBased) unit).getDays())));
            } else {
                if (!(unit instanceof DateTimeUnit.MonthBased)) {
                    throw new NoWhenBranchMatchedException();
                }
                localDatePlusMonths = localDate.getValue().plusMonths(MathJvmKt.safeMultiply(j, ((DateTimeUnit.MonthBased) unit).getMonths()));
            }
            return new LocalDate(localDatePlusMonths);
        } catch (Exception e) {
            if (!(e instanceof DateTimeException) && !(e instanceof ArithmeticException)) {
                throw e;
            }
            throw new DateTimeArithmeticException("The result of adding " + j + " of " + unit + " to " + localDate + " is out of LocalDate range.", e);
        }
    }

    private static final j$.time.LocalDate ofEpochDayChecked(long j) {
        long j2 = minEpochDay;
        if (j > maxEpochDay || j2 > j) {
            throw new DateTimeException("The resulting day " + j + " is out of supported LocalDate range.");
        }
        j$.time.LocalDate localDateOfEpochDay = j$.time.LocalDate.ofEpochDay(j);
        Intrinsics.checkNotNullExpressionValue(localDateOfEpochDay, "ofEpochDay(...)");
        return localDateOfEpochDay;
    }

    public static final LocalDate plus(LocalDate localDate, DatePeriod period) {
        Intrinsics.checkNotNullParameter(localDate, "<this>");
        Intrinsics.checkNotNullParameter(period, "period");
        try {
            j$.time.LocalDate value = localDate.getValue();
            if (period.getTotalMonths() != 0) {
                value = value.plusMonths(period.getTotalMonths());
            }
            if (period.getDays() != 0) {
                value = value.plusDays(period.getDays());
            }
            return new LocalDate(value);
        } catch (DateTimeException unused) {
            throw new DateTimeArithmeticException("The result of adding " + localDate.getValue() + " to " + localDate + " is out of LocalDate range.");
        }
    }

    public static final DatePeriod periodUntil(LocalDate localDate, LocalDate other) {
        Intrinsics.checkNotNullParameter(localDate, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        j$.time.LocalDate value = localDate.getValue();
        j$.time.LocalDate value2 = other.getValue();
        long jUntil = value.until(value2, ChronoUnit.MONTHS);
        j$.time.LocalDate localDatePlusMonths = value.plusMonths(jUntil);
        Intrinsics.checkNotNullExpressionValue(localDatePlusMonths, "plusMonths(...)");
        return new DatePeriod(jUntil, (int) localDatePlusMonths.until(value2, ChronoUnit.DAYS));
    }

    /* JADX INFO: renamed from: until, reason: collision with other method in class */
    public static final long m10566until(LocalDate localDate, LocalDate other, DateTimeUnit.DateBased unit) {
        Intrinsics.checkNotNullParameter(localDate, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        Intrinsics.checkNotNullParameter(unit, "unit");
        if (unit instanceof DateTimeUnit.MonthBased) {
            return localDate.getValue().until(other.getValue(), ChronoUnit.MONTHS) / ((long) ((DateTimeUnit.MonthBased) unit).getMonths());
        }
        if (unit instanceof DateTimeUnit.DayBased) {
            return localDate.getValue().until(other.getValue(), ChronoUnit.DAYS) / ((long) ((DateTimeUnit.DayBased) unit).getDays());
        }
        throw new NoWhenBranchMatchedException();
    }

    public static final int until(LocalDate localDate, LocalDate other, DateTimeUnit.DateBased unit) {
        Intrinsics.checkNotNullParameter(localDate, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        Intrinsics.checkNotNullParameter(unit, "unit");
        return MathKt.clampToInt(m10566until(localDate, other, unit));
    }

    public static final int daysUntil(LocalDate localDate, LocalDate other) {
        Intrinsics.checkNotNullParameter(localDate, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        return MathKt.clampToInt(localDate.getValue().until(other.getValue(), ChronoUnit.DAYS));
    }

    public static final int monthsUntil(LocalDate localDate, LocalDate other) {
        Intrinsics.checkNotNullParameter(localDate, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        return MathKt.clampToInt(localDate.getValue().until(other.getValue(), ChronoUnit.MONTHS));
    }

    public static final int yearsUntil(LocalDate localDate, LocalDate other) {
        Intrinsics.checkNotNullParameter(localDate, "<this>");
        Intrinsics.checkNotNullParameter(other, "other");
        return (int) localDate.getValue().until(other.getValue(), ChronoUnit.YEARS);
    }

    public static /* synthetic */ LocalDate parseOrNull$default(LocalDate.Companion companion, CharSequence charSequence, DateTimeFormat dateTimeFormat, int i, Object obj) {
        if ((i & 2) != 0) {
            dateTimeFormat = LocalDateKt.getIsoDateFormat();
        }
        return parseOrNull(companion, charSequence, dateTimeFormat);
    }
}
