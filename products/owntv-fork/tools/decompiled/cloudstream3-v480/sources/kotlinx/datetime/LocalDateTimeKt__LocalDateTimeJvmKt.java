package kotlinx.datetime;

import androidx.tvprovider.media.tv.TvContractCompat;
import j$.time.format.DateTimeParseException;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.datetime.format.DateTimeFormat;
import kotlinx.datetime.internal.UtilKt;

/* JADX INFO: compiled from: LocalDateTimeJvm.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\r\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\b\n\u001a+\u0010\u0006\u001a\u0004\u0018\u00010\u0004*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0004\b\u0006\u0010\u0007\u001aK\u0010\u0011\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\b2\b\b\u0002\u0010\u000f\u001a\u00020\b2\b\b\u0002\u0010\u0010\u001a\u00020\bH\u0007¢\u0006\u0004\b\u0011\u0010\u0012¨\u0006\u0013"}, d2 = {"Lkotlinx/datetime/LocalDateTime$Companion;", "", TvContractCompat.PARAM_INPUT, "Lkotlinx/datetime/format/DateTimeFormat;", "Lkotlinx/datetime/LocalDateTime;", "format", "parseOrNull", "(Lkotlinx/datetime/LocalDateTime$Companion;Ljava/lang/CharSequence;Lkotlinx/datetime/format/DateTimeFormat;)Lkotlinx/datetime/LocalDateTime;", "", "year", "j$/time/Month", "month", "dayOfMonth", "hour", "minute", "second", "nanosecond", "LocalDateTime", "(ILj$/time/Month;IIIII)Lkotlinx/datetime/LocalDateTime;", "kotlinx-datetime"}, k = 5, mv = {2, 1, 0}, xi = 48, xs = "kotlinx/datetime/LocalDateTimeKt")
final /* synthetic */ class LocalDateTimeKt__LocalDateTimeJvmKt {
    public static final LocalDateTime parseOrNull(LocalDateTime.Companion companion, CharSequence input, DateTimeFormat<LocalDateTime> format) {
        Intrinsics.checkNotNullParameter(companion, "<this>");
        Intrinsics.checkNotNullParameter(input, "input");
        Intrinsics.checkNotNullParameter(format, "format");
        if (format == LocalDateTime.Formats.INSTANCE.getISO()) {
            try {
                return new LocalDateTime(j$.time.LocalDateTime.parse(UtilKt.removeLeadingZerosFromLongYearFormLocalDateTime(input.toString())));
            } catch (DateTimeParseException unused) {
                return null;
            }
        }
        return format.parseOrNull(input);
    }

    public static /* synthetic */ LocalDateTime LocalDateTime$default(int i, j$.time.Month month, int i2, int i3, int i4, int i5, int i6, int i7, Object obj) {
        if ((i7 & 32) != 0) {
            i5 = 0;
        }
        if ((i7 & 64) != 0) {
            i6 = 0;
        }
        return LocalDateTimeKt.LocalDateTime(i, month, i2, i3, i4, i5, i6);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Use the constructor that accepts a 'day'", replaceWith = @ReplaceWith(expression = "LocalDateTime(year = year, month = month.toKotlinMonth(), day = dayOfMonth, hour = hour, minute = minute, second = second, nanosecond = nanosecond)", imports = {}))
    public static final LocalDateTime LocalDateTime(int i, j$.time.Month month, int i2, int i3, int i4, int i5, int i6) {
        Intrinsics.checkNotNullParameter(month, "month");
        return new LocalDateTime(i, ConvertersKt.toKotlinMonth(month), i2, i3, i4, i5, i6);
    }

    public static /* synthetic */ LocalDateTime parseOrNull$default(LocalDateTime.Companion companion, CharSequence charSequence, DateTimeFormat dateTimeFormat, int i, Object obj) {
        if ((i & 2) != 0) {
            dateTimeFormat = LocalDateTimeKt.getIsoDateTimeFormat();
        }
        return LocalDateTimeKt.parseOrNull(companion, charSequence, dateTimeFormat);
    }
}
