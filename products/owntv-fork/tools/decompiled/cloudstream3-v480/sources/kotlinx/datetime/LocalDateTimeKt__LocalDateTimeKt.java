package kotlinx.datetime;

import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.datetime.format.DateTimeFormat;

/* JADX INFO: compiled from: LocalDateTime.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\"\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\u001aD\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u0003H\u0007\u001aD\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0005\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u0003H\u0007\u001a\u0018\u0010\f\u001a\u00020\r*\u00020\u00012\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00010\u000e\u001a\f\u0010\u000f\u001a\u00020\u0001*\u00020\rH\u0007\u001a\u000e\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00010\u000eH\u0000¨\u0006\u0011"}, d2 = {"LocalDateTime", "Lkotlinx/datetime/LocalDateTime;", "year", "", "monthNumber", "dayOfMonth", "hour", "minute", "second", "nanosecond", "month", "Lkotlinx/datetime/Month;", "format", "", "Lkotlinx/datetime/format/DateTimeFormat;", "toLocalDateTime", "getIsoDateTimeFormat", "kotlinx-datetime"}, k = 5, mv = {2, 1, 0}, xi = 48, xs = "kotlinx/datetime/LocalDateTimeKt")
final /* synthetic */ class LocalDateTimeKt__LocalDateTimeKt {
    public static /* synthetic */ LocalDateTime LocalDateTime$default(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, Object obj) {
        if ((i8 & 32) != 0) {
            i6 = 0;
        }
        if ((i8 & 64) != 0) {
            i7 = 0;
        }
        return LocalDateTimeKt.LocalDateTime(i, i2, i3, i4, i5, i6, i7);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Use the constructor that accepts a 'month' and a 'day'", replaceWith = @ReplaceWith(expression = "LocalDateTime(year = year, month = monthNumber, day = dayOfMonth, hour = hour, minute = minute, second = second, nanosecond = nanosecond)", imports = {}))
    public static final LocalDateTime LocalDateTime(int i, int i2, int i3, int i4, int i5, int i6, int i7) {
        return new LocalDateTime(i, i2, i3, i4, i5, i6, i7);
    }

    public static /* synthetic */ LocalDateTime LocalDateTime$default(int i, Month month, int i2, int i3, int i4, int i5, int i6, int i7, Object obj) {
        if ((i7 & 32) != 0) {
            i5 = 0;
        }
        if ((i7 & 64) != 0) {
            i6 = 0;
        }
        return LocalDateTimeKt.LocalDateTime(i, month, i2, i3, i4, i5, i6);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Use the constructor that accepts a 'day'", replaceWith = @ReplaceWith(expression = "LocalDateTime(year = year, month = month, day = dayOfMonth, hour = hour, minute = minute, second = second, nanosecond = nanosecond)", imports = {}))
    public static final LocalDateTime LocalDateTime(int i, Month month, int i2, int i3, int i4, int i5, int i6) {
        Intrinsics.checkNotNullParameter(month, "month");
        return new LocalDateTime(i, month, i2, i3, i4, i5, i6);
    }

    public static final String format(LocalDateTime localDateTime, DateTimeFormat<LocalDateTime> format) {
        Intrinsics.checkNotNullParameter(localDateTime, "<this>");
        Intrinsics.checkNotNullParameter(format, "format");
        return format.format(localDateTime);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Removed to support more idiomatic code. See https://github.com/Kotlin/kotlinx-datetime/issues/339", replaceWith = @ReplaceWith(expression = "LocalDateTime.parse(this)", imports = {}))
    public static final LocalDateTime toLocalDateTime(String str) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        return LocalDateTime.Companion.parse$default(LocalDateTime.INSTANCE, str, null, 2, null);
    }

    public static final DateTimeFormat<LocalDateTime> getIsoDateTimeFormat() {
        return LocalDateTime.Formats.INSTANCE.getISO();
    }
}
