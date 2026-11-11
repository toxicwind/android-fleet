package kotlinx.datetime;

import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlinx.datetime.format.DateTimeFormat;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"kotlinx/datetime/LocalDateTimeKt__LocalDateTimeJvmKt", "kotlinx/datetime/LocalDateTimeKt__LocalDateTimeKt"}, k = 4, mv = {2, 1, 0}, xi = 48)
public final class LocalDateTimeKt {
    @Deprecated(level = DeprecationLevel.WARNING, message = "Use the constructor that accepts a 'month' and a 'day'", replaceWith = @ReplaceWith(expression = "LocalDateTime(year = year, month = monthNumber, day = dayOfMonth, hour = hour, minute = minute, second = second, nanosecond = nanosecond)", imports = {}))
    public static final LocalDateTime LocalDateTime(int i, int i2, int i3, int i4, int i5, int i6, int i7) {
        return LocalDateTimeKt__LocalDateTimeKt.LocalDateTime(i, i2, i3, i4, i5, i6, i7);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Use the constructor that accepts a 'day'", replaceWith = @ReplaceWith(expression = "LocalDateTime(year = year, month = month.toKotlinMonth(), day = dayOfMonth, hour = hour, minute = minute, second = second, nanosecond = nanosecond)", imports = {}))
    public static final LocalDateTime LocalDateTime(int i, j$.time.Month month, int i2, int i3, int i4, int i5, int i6) {
        return LocalDateTimeKt__LocalDateTimeJvmKt.LocalDateTime(i, month, i2, i3, i4, i5, i6);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Use the constructor that accepts a 'day'", replaceWith = @ReplaceWith(expression = "LocalDateTime(year = year, month = month, day = dayOfMonth, hour = hour, minute = minute, second = second, nanosecond = nanosecond)", imports = {}))
    public static final LocalDateTime LocalDateTime(int i, Month month, int i2, int i3, int i4, int i5, int i6) {
        return LocalDateTimeKt__LocalDateTimeKt.LocalDateTime(i, month, i2, i3, i4, i5, i6);
    }

    public static final String format(LocalDateTime localDateTime, DateTimeFormat<LocalDateTime> dateTimeFormat) {
        return LocalDateTimeKt__LocalDateTimeKt.format(localDateTime, dateTimeFormat);
    }

    public static final DateTimeFormat<LocalDateTime> getIsoDateTimeFormat() {
        return LocalDateTimeKt__LocalDateTimeKt.getIsoDateTimeFormat();
    }

    public static final LocalDateTime parseOrNull(LocalDateTime.Companion companion, CharSequence charSequence, DateTimeFormat<LocalDateTime> dateTimeFormat) {
        return LocalDateTimeKt__LocalDateTimeJvmKt.parseOrNull(companion, charSequence, dateTimeFormat);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Removed to support more idiomatic code. See https://github.com/Kotlin/kotlinx-datetime/issues/339", replaceWith = @ReplaceWith(expression = "LocalDateTime.parse(this)", imports = {}))
    public static final LocalDateTime toLocalDateTime(String str) {
        return LocalDateTimeKt__LocalDateTimeKt.toLocalDateTime(str);
    }
}
