package kotlinx.datetime;

import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.datetime.format.DateTimeFormat;

/* JADX INFO: compiled from: LocalTime.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00006\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0018\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\f\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0003\u001a\f\u0010\u0004\u001a\u00020\u0002*\u00020\u0001H\u0007\u001a\"\u0010\u0005\u001a\u00020\u0006*\u00020\u00022\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\b\u001a3\u0010\u0005\u001a\u00020\u0006*\u00020\u00022\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\b2\b\b\u0002\u0010\r\u001a\u00020\u000eH\u0007¢\u0006\u0002\u0010\u000f\u001a\"\u0010\u0005\u001a\u00020\u0006*\u00020\u00022\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020\b\u001a3\u0010\u0005\u001a\u00020\u0006*\u00020\u00022\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u00102\u0006\u0010\f\u001a\u00020\b2\b\b\u0002\u0010\r\u001a\u00020\u000eH\u0007¢\u0006\u0002\u0010\u0011\u001a\u0012\u0010\u0005\u001a\u00020\u0006*\u00020\u00022\u0006\u0010\u0012\u001a\u00020\u0013\u001a\u000e\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00020\u0003H\u0000¨\u0006\u0015"}, d2 = {"format", "", "Lkotlinx/datetime/LocalTime;", "Lkotlinx/datetime/format/DateTimeFormat;", "toLocalTime", "atDate", "Lkotlinx/datetime/LocalDateTime;", "year", "", "month", "day", "monthNumber", "dayOfMonth", "fakeArgument", "", "(Lkotlinx/datetime/LocalTime;IIILkotlin/Unit;)Lkotlinx/datetime/LocalDateTime;", "Lkotlinx/datetime/Month;", "(Lkotlinx/datetime/LocalTime;ILkotlinx/datetime/Month;ILkotlin/Unit;)Lkotlinx/datetime/LocalDateTime;", "date", "Lkotlinx/datetime/LocalDate;", "getIsoTimeFormat", "kotlinx-datetime"}, k = 5, mv = {2, 1, 0}, xi = 48, xs = "kotlinx/datetime/LocalTimeKt")
final /* synthetic */ class LocalTimeKt__LocalTimeKt {
    public static final String format(LocalTime localTime, DateTimeFormat<LocalTime> format) {
        Intrinsics.checkNotNullParameter(localTime, "<this>");
        Intrinsics.checkNotNullParameter(format, "format");
        return format.format(localTime);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Removed to support more idiomatic code. See https://github.com/Kotlin/kotlinx-datetime/issues/339", replaceWith = @ReplaceWith(expression = "LocalTime.parse(this)", imports = {}))
    public static final LocalTime toLocalTime(String str) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        return LocalTime.Companion.parse$default(LocalTime.INSTANCE, str, null, 2, null);
    }

    public static final LocalDateTime atDate(LocalTime localTime, int i, int i2, int i3) {
        Intrinsics.checkNotNullParameter(localTime, "<this>");
        return new LocalDateTime(i, i2, i3, localTime.getHour(), localTime.getMinute(), localTime.getSecond(), localTime.getNanosecond());
    }

    public static /* synthetic */ LocalDateTime atDate$default(LocalTime localTime, int i, int i2, int i3, Unit unit, int i4, Object obj) {
        if ((i4 & 8) != 0) {
            unit = Unit.INSTANCE;
        }
        return LocalTimeKt.atDate(localTime, i, i2, i3, unit);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Use the overload that accepts a 'month' and a 'day' instead", replaceWith = @ReplaceWith(expression = "this.atDate(year = year, month = monthNumber, day = dayOfMonth)", imports = {}))
    public static final LocalDateTime atDate(LocalTime localTime, int i, int i2, int i3, Unit fakeArgument) {
        Intrinsics.checkNotNullParameter(localTime, "<this>");
        Intrinsics.checkNotNullParameter(fakeArgument, "fakeArgument");
        return new LocalDateTime(i, i2, i3, localTime.getHour(), localTime.getMinute(), localTime.getSecond(), localTime.getNanosecond());
    }

    public static final LocalDateTime atDate(LocalTime localTime, int i, Month month, int i2) {
        Intrinsics.checkNotNullParameter(localTime, "<this>");
        Intrinsics.checkNotNullParameter(month, "month");
        return new LocalDateTime(i, month, i2, localTime.getHour(), localTime.getMinute(), localTime.getSecond(), localTime.getNanosecond());
    }

    public static /* synthetic */ LocalDateTime atDate$default(LocalTime localTime, int i, Month month, int i2, Unit unit, int i3, Object obj) {
        if ((i3 & 8) != 0) {
            unit = Unit.INSTANCE;
        }
        return LocalTimeKt.atDate(localTime, i, month, i2, unit);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Use the overload that accepts a 'month' and a 'day' instead", replaceWith = @ReplaceWith(expression = "this.atDate(year = year, month = month, day = dayOfMonth)", imports = {}))
    public static final LocalDateTime atDate(LocalTime localTime, int i, Month month, int i2, Unit fakeArgument) {
        Intrinsics.checkNotNullParameter(localTime, "<this>");
        Intrinsics.checkNotNullParameter(month, "month");
        Intrinsics.checkNotNullParameter(fakeArgument, "fakeArgument");
        return new LocalDateTime(i, month, i2, localTime.getHour(), localTime.getMinute(), localTime.getSecond(), localTime.getNanosecond());
    }

    public static final LocalDateTime atDate(LocalTime localTime, LocalDate date) {
        Intrinsics.checkNotNullParameter(localTime, "<this>");
        Intrinsics.checkNotNullParameter(date, "date");
        return new LocalDateTime(date, localTime);
    }

    public static final DateTimeFormat<LocalTime> getIsoTimeFormat() {
        return LocalTime.Formats.INSTANCE.getISO();
    }
}
