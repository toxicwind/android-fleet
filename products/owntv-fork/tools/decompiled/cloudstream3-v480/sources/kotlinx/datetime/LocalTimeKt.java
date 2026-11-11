package kotlinx.datetime;

import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlinx.datetime.format.DateTimeFormat;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"kotlinx/datetime/LocalTimeKt__LocalTimeJvmKt", "kotlinx/datetime/LocalTimeKt__LocalTimeKt"}, k = 4, mv = {2, 1, 0}, xi = 48)
public final class LocalTimeKt {
    public static final LocalDateTime atDate(LocalTime localTime, int i, int i2, int i3) {
        return LocalTimeKt__LocalTimeKt.atDate(localTime, i, i2, i3);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Use the overload that accepts a 'month' and a 'day' instead", replaceWith = @ReplaceWith(expression = "this.atDate(year = year, month = monthNumber, day = dayOfMonth)", imports = {}))
    public static final LocalDateTime atDate(LocalTime localTime, int i, int i2, int i3, Unit unit) {
        return LocalTimeKt__LocalTimeKt.atDate(localTime, i, i2, i3, unit);
    }

    @Deprecated(message = "Use kotlinx.datetime.Month", replaceWith = @ReplaceWith(expression = "atDate(year, month.toKotlinMonth(), dayOfMonth)", imports = {}))
    public static final LocalDateTime atDate(LocalTime localTime, int i, j$.time.Month month, int i2) {
        return LocalTimeKt__LocalTimeJvmKt.atDate(localTime, i, month, i2);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Use the overload that accepts a 'month' and a 'day' instead", replaceWith = @ReplaceWith(expression = "this.atDate(year = year, month = month.toKotlinMonth(), day = dayOfMonth)", imports = {}))
    public static final LocalDateTime atDate(LocalTime localTime, int i, j$.time.Month month, int i2, Unit unit) {
        return LocalTimeKt__LocalTimeJvmKt.atDate(localTime, i, month, i2, unit);
    }

    public static final LocalDateTime atDate(LocalTime localTime, int i, Month month, int i2) {
        return LocalTimeKt__LocalTimeKt.atDate(localTime, i, month, i2);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Use the overload that accepts a 'month' and a 'day' instead", replaceWith = @ReplaceWith(expression = "this.atDate(year = year, month = month, day = dayOfMonth)", imports = {}))
    public static final LocalDateTime atDate(LocalTime localTime, int i, Month month, int i2, Unit unit) {
        return LocalTimeKt__LocalTimeKt.atDate(localTime, i, month, i2, unit);
    }

    public static final LocalDateTime atDate(LocalTime localTime, LocalDate localDate) {
        return LocalTimeKt__LocalTimeKt.atDate(localTime, localDate);
    }

    public static final String format(LocalTime localTime, DateTimeFormat<LocalTime> dateTimeFormat) {
        return LocalTimeKt__LocalTimeKt.format(localTime, dateTimeFormat);
    }

    public static final DateTimeFormat<LocalTime> getIsoTimeFormat() {
        return LocalTimeKt__LocalTimeKt.getIsoTimeFormat();
    }

    public static final LocalTime parseOrNull(LocalTime.Companion companion, CharSequence charSequence, DateTimeFormat<LocalTime> dateTimeFormat) {
        return LocalTimeKt__LocalTimeJvmKt.parseOrNull(companion, charSequence, dateTimeFormat);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Removed to support more idiomatic code. See https://github.com/Kotlin/kotlinx-datetime/issues/339", replaceWith = @ReplaceWith(expression = "LocalTime.parse(this)", imports = {}))
    public static final LocalTime toLocalTime(String str) {
        return LocalTimeKt__LocalTimeKt.toLocalTime(str);
    }
}
