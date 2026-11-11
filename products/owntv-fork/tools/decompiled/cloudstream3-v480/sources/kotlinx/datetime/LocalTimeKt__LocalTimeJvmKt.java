package kotlinx.datetime;

import androidx.tvprovider.media.tv.TvContractCompat;
import j$.time.format.DateTimeParseException;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.datetime.format.DateTimeFormat;

/* JADX INFO: compiled from: LocalTimeJvm.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\r\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\u001a+\u0010\u0006\u001a\u0004\u0018\u00010\u0004*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0004\b\u0006\u0010\u0007\u001a-\u0010\u000e\u001a\u00020\r*\u00020\u00042\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\n2\b\b\u0002\u0010\f\u001a\u00020\bH\u0007¢\u0006\u0004\b\u000e\u0010\u000f\u001a5\u0010\u000e\u001a\u00020\r*\u00020\u00042\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\b2\b\b\u0002\u0010\u0011\u001a\u00020\u0010H\u0007¢\u0006\u0004\b\u000e\u0010\u0012¨\u0006\u0013"}, d2 = {"Lkotlinx/datetime/LocalTime$Companion;", "", TvContractCompat.PARAM_INPUT, "Lkotlinx/datetime/format/DateTimeFormat;", "Lkotlinx/datetime/LocalTime;", "format", "parseOrNull", "(Lkotlinx/datetime/LocalTime$Companion;Ljava/lang/CharSequence;Lkotlinx/datetime/format/DateTimeFormat;)Lkotlinx/datetime/LocalTime;", "", "year", "j$/time/Month", "month", "dayOfMonth", "Lkotlinx/datetime/LocalDateTime;", "atDate", "(Lkotlinx/datetime/LocalTime;ILj$/time/Month;I)Lkotlinx/datetime/LocalDateTime;", "", "fakeArgument", "(Lkotlinx/datetime/LocalTime;ILj$/time/Month;ILkotlin/Unit;)Lkotlinx/datetime/LocalDateTime;", "kotlinx-datetime"}, k = 5, mv = {2, 1, 0}, xi = 48, xs = "kotlinx/datetime/LocalTimeKt")
final /* synthetic */ class LocalTimeKt__LocalTimeJvmKt {
    public static final LocalTime parseOrNull(LocalTime.Companion companion, CharSequence input, DateTimeFormat<LocalTime> format) {
        Intrinsics.checkNotNullParameter(companion, "<this>");
        Intrinsics.checkNotNullParameter(input, "input");
        Intrinsics.checkNotNullParameter(format, "format");
        if (format == LocalTime.Formats.INSTANCE.getISO()) {
            try {
                return new LocalTime(j$.time.LocalTime.parse(input));
            } catch (DateTimeParseException unused) {
                return null;
            }
        }
        return format.parseOrNull(input);
    }

    public static /* synthetic */ LocalDateTime atDate$default(LocalTime localTime, int i, j$.time.Month month, int i2, int i3, Object obj) {
        if ((i3 & 4) != 0) {
            i2 = 0;
        }
        return LocalTimeKt.atDate(localTime, i, month, i2);
    }

    @Deprecated(message = "Use kotlinx.datetime.Month", replaceWith = @ReplaceWith(expression = "atDate(year, month.toKotlinMonth(), dayOfMonth)", imports = {}))
    public static final LocalDateTime atDate(LocalTime localTime, int i, j$.time.Month month, int i2) {
        Intrinsics.checkNotNullParameter(localTime, "<this>");
        Intrinsics.checkNotNullParameter(month, "month");
        return LocalTimeKt.atDate(localTime, i, ConvertersKt.toKotlinMonth(month), i2);
    }

    public static /* synthetic */ LocalDateTime atDate$default(LocalTime localTime, int i, j$.time.Month month, int i2, Unit unit, int i3, Object obj) {
        if ((i3 & 8) != 0) {
            unit = Unit.INSTANCE;
        }
        return LocalTimeKt.atDate(localTime, i, month, i2, unit);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Use the overload that accepts a 'month' and a 'day' instead", replaceWith = @ReplaceWith(expression = "this.atDate(year = year, month = month.toKotlinMonth(), day = dayOfMonth)", imports = {}))
    public static final LocalDateTime atDate(LocalTime localTime, int i, j$.time.Month month, int i2, Unit fakeArgument) {
        Intrinsics.checkNotNullParameter(localTime, "<this>");
        Intrinsics.checkNotNullParameter(month, "month");
        Intrinsics.checkNotNullParameter(fakeArgument, "fakeArgument");
        return new LocalDateTime(i, ConvertersKt.toKotlinMonth(month), i2, localTime.getHour(), localTime.getMinute(), localTime.getSecond(), localTime.getNanosecond());
    }

    public static /* synthetic */ LocalTime parseOrNull$default(LocalTime.Companion companion, CharSequence charSequence, DateTimeFormat dateTimeFormat, int i, Object obj) {
        if ((i & 2) != 0) {
            dateTimeFormat = LocalTimeKt.getIsoTimeFormat();
        }
        return LocalTimeKt.parseOrNull(companion, charSequence, dateTimeFormat);
    }
}
