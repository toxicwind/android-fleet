package kotlinx.datetime;

import androidx.tvprovider.media.tv.TvContractCompat;
import j$.time.format.DateTimeFormatter;
import j$.time.format.DateTimeFormatterBuilder;
import j$.time.format.DateTimeParseException;
import j$.time.format.SignStyle;
import j$.time.temporal.ChronoField;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.datetime.format.DateTimeFormat;
import kotlinx.datetime.internal.UtilKt;

/* JADX INFO: compiled from: YearMonthJvm.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\r\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\b\b\u001a\u0013\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u0000¢\u0006\u0004\b\u0002\u0010\u0003\u001a\u001b\u0010\u0006\u001a\u00020\u0000*\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0001H\u0000¢\u0006\u0004\b\u0006\u0010\u0007\u001a+\u0010\f\u001a\u0004\u0018\u00010\u0000*\u00020\u00042\u0006\u0010\t\u001a\u00020\b2\u000e\b\u0002\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00000\n¢\u0006\u0004\b\f\u0010\r\"#\u0010\u0014\u001a\n \u000f*\u0004\u0018\u00010\u000e0\u000e8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u0015"}, d2 = {"Lkotlinx/datetime/YearMonth;", "", "toEpochMonths", "(Lkotlinx/datetime/YearMonth;)J", "Lkotlinx/datetime/YearMonth$Companion;", "months", "fromEpochMonths", "(Lkotlinx/datetime/YearMonth$Companion;J)Lkotlinx/datetime/YearMonth;", "", TvContractCompat.PARAM_INPUT, "Lkotlinx/datetime/format/DateTimeFormat;", "format", "parseOrNull", "(Lkotlinx/datetime/YearMonth$Companion;Ljava/lang/CharSequence;Lkotlinx/datetime/format/DateTimeFormat;)Lkotlinx/datetime/YearMonth;", "j$/time/format/DateTimeFormatter", "kotlin.jvm.PlatformType", "isoFormat$delegate", "Lkotlin/Lazy;", "getIsoFormat", "()Lj$/time/format/DateTimeFormatter;", "isoFormat", "kotlinx-datetime"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class YearMonthJvmKt {
    private static final Lazy isoFormat$delegate = LazyKt.lazy(new Function0() { // from class: kotlinx.datetime.YearMonthJvmKt$$ExternalSyntheticLambda0
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return new DateTimeFormatterBuilder().parseCaseInsensitive().appendValue(ChronoField.YEAR, 4, 10, SignStyle.EXCEEDS_PAD).appendLiteral('-').appendValue(ChronoField.MONTH_OF_YEAR, 2).toFormatter();
        }
    });

    public static final long toEpochMonths(YearMonth yearMonth) {
        Intrinsics.checkNotNullParameter(yearMonth, "<this>");
        return (((((long) yearMonth.getYear()) - 1970) * 12) + ((long) yearMonth.getMonthNumber$kotlinx_datetime())) - 1;
    }

    public static final YearMonth fromEpochMonths(YearMonth.Companion companion, long j) {
        Intrinsics.checkNotNullParameter(companion, "<this>");
        long j2 = j / 12;
        if ((j ^ 12) < 0 && j2 * 12 != j) {
            j2--;
        }
        long j3 = j % 12;
        return new YearMonth((int) (j2 + 1970), ((int) (j3 + (12 & (((j3 ^ 12) & ((-j3) | j3)) >> 63)))) + 1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final DateTimeFormatter getIsoFormat() {
        return (DateTimeFormatter) isoFormat$delegate.getValue();
    }

    public static final YearMonth parseOrNull(YearMonth.Companion companion, CharSequence input, DateTimeFormat<YearMonth> format) {
        Intrinsics.checkNotNullParameter(companion, "<this>");
        Intrinsics.checkNotNullParameter(input, "input");
        Intrinsics.checkNotNullParameter(format, "format");
        if (format == YearMonth.Formats.INSTANCE.getISO()) {
            try {
                return new YearMonth(j$.time.YearMonth.parse(UtilKt.removeLeadingZerosFromLongYearFormYearMonth(input.toString())));
            } catch (DateTimeParseException unused) {
                return null;
            }
        }
        return format.parseOrNull(input);
    }

    public static /* synthetic */ YearMonth parseOrNull$default(YearMonth.Companion companion, CharSequence charSequence, DateTimeFormat dateTimeFormat, int i, Object obj) {
        if ((i & 2) != 0) {
            dateTimeFormat = YearMonth.Formats.INSTANCE.getISO();
        }
        return parseOrNull(companion, charSequence, dateTimeFormat);
    }
}
