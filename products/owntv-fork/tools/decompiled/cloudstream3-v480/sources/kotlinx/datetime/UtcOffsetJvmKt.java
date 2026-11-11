package kotlinx.datetime;

import androidx.tvprovider.media.tv.TvContractCompat;
import j$.time.DateTimeException;
import j$.time.ZoneOffset;
import j$.time.format.DateTimeFormatter;
import j$.time.format.DateTimeFormatterBuilder;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.datetime.format.DateTimeFormat;

/* JADX INFO: compiled from: UtcOffsetJvm.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\r\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\b\u0011\u001a1\u0010\u0005\u001a\u00020\u00042\n\b\u0002\u0010\u0001\u001a\u0004\u0018\u00010\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00002\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0000¢\u0006\u0004\b\u0005\u0010\u0006\u001a+\u0010\f\u001a\u0004\u0018\u00010\u0004*\u00020\u00072\u0006\u0010\t\u001a\u00020\b2\u000e\b\u0002\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00040\n¢\u0006\u0004\b\f\u0010\r\u001a\u001f\u0010\u000f\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\u000eH\u0002¢\u0006\u0004\b\u000f\u0010\u0010\u001a!\u0010\u0011\u001a\u0004\u0018\u00010\u00042\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\u000eH\u0002¢\u0006\u0004\b\u0011\u0010\u0010\"#\u0010\u0017\u001a\n \u0012*\u0004\u0018\u00010\u000e0\u000e8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u0015\u0010\u0016\"#\u0010\u001a\u001a\n \u0012*\u0004\u0018\u00010\u000e0\u000e8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0018\u0010\u0014\u001a\u0004\b\u0019\u0010\u0016\"#\u0010\u001d\u001a\n \u0012*\u0004\u0018\u00010\u000e0\u000e8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u001b\u0010\u0014\u001a\u0004\b\u001c\u0010\u0016¨\u0006\u001e"}, d2 = {"", "hours", "minutes", "seconds", "Lkotlinx/datetime/UtcOffset;", "UtcOffset", "(Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;)Lkotlinx/datetime/UtcOffset;", "Lkotlinx/datetime/UtcOffset$Companion;", "", TvContractCompat.PARAM_INPUT, "Lkotlinx/datetime/format/DateTimeFormat;", "format", "parseOrNull", "(Lkotlinx/datetime/UtcOffset$Companion;Ljava/lang/CharSequence;Lkotlinx/datetime/format/DateTimeFormat;)Lkotlinx/datetime/UtcOffset;", "j$/time/format/DateTimeFormatter", "parseWithFormat", "(Ljava/lang/CharSequence;Lj$/time/format/DateTimeFormatter;)Lkotlinx/datetime/UtcOffset;", "parseWithFormatOrNull", "kotlin.jvm.PlatformType", "isoFormat$delegate", "Lkotlin/Lazy;", "getIsoFormat", "()Lj$/time/format/DateTimeFormatter;", "isoFormat", "isoBasicFormat$delegate", "getIsoBasicFormat", "isoBasicFormat", "fourDigitsFormat$delegate", "getFourDigitsFormat", "fourDigitsFormat", "kotlinx-datetime"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class UtcOffsetJvmKt {
    private static final Lazy isoFormat$delegate = LazyKt.lazy(new Function0() { // from class: kotlinx.datetime.UtcOffsetJvmKt$$ExternalSyntheticLambda0
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return new DateTimeFormatterBuilder().parseCaseInsensitive().appendOffsetId().toFormatter();
        }
    });
    private static final Lazy isoBasicFormat$delegate = LazyKt.lazy(new Function0() { // from class: kotlinx.datetime.UtcOffsetJvmKt$$ExternalSyntheticLambda1
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return new DateTimeFormatterBuilder().parseCaseInsensitive().appendOffset("+HHmmss", "Z").toFormatter();
        }
    });
    private static final Lazy fourDigitsFormat$delegate = LazyKt.lazy(new Function0() { // from class: kotlinx.datetime.UtcOffsetJvmKt$$ExternalSyntheticLambda2
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return new DateTimeFormatterBuilder().parseCaseInsensitive().appendOffset("+HHMM", "+0000").toFormatter();
        }
    });

    public static /* synthetic */ UtcOffset UtcOffset$default(Integer num, Integer num2, Integer num3, int i, Object obj) {
        if ((i & 1) != 0) {
            num = null;
        }
        if ((i & 2) != 0) {
            num2 = null;
        }
        if ((i & 4) != 0) {
            num3 = null;
        }
        return UtcOffset(num, num2, num3);
    }

    public static final UtcOffset UtcOffset(Integer num, Integer num2, Integer num3) {
        try {
            if (num != null) {
                ZoneOffset zoneOffsetOfHoursMinutesSeconds = ZoneOffset.ofHoursMinutesSeconds(num.intValue(), num2 != null ? num2.intValue() : 0, num3 != null ? num3.intValue() : 0);
                Intrinsics.checkNotNullExpressionValue(zoneOffsetOfHoursMinutesSeconds, "ofHoursMinutesSeconds(...)");
                return new UtcOffset(zoneOffsetOfHoursMinutesSeconds);
            }
            if (num2 != null) {
                ZoneOffset zoneOffsetOfHoursMinutesSeconds2 = ZoneOffset.ofHoursMinutesSeconds(num2.intValue() / 60, num2.intValue() % 60, num3 != null ? num3.intValue() : 0);
                Intrinsics.checkNotNullExpressionValue(zoneOffsetOfHoursMinutesSeconds2, "ofHoursMinutesSeconds(...)");
                return new UtcOffset(zoneOffsetOfHoursMinutesSeconds2);
            }
            ZoneOffset zoneOffsetOfTotalSeconds = ZoneOffset.ofTotalSeconds(num3 != null ? num3.intValue() : 0);
            Intrinsics.checkNotNullExpressionValue(zoneOffsetOfTotalSeconds, "ofTotalSeconds(...)");
            return new UtcOffset(zoneOffsetOfTotalSeconds);
        } catch (DateTimeException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static final UtcOffset parseOrNull(UtcOffset.Companion companion, CharSequence input, DateTimeFormat<UtcOffset> format) {
        Intrinsics.checkNotNullParameter(companion, "<this>");
        Intrinsics.checkNotNullParameter(input, "input");
        Intrinsics.checkNotNullParameter(format, "format");
        if (format == UtcOffset.Formats.INSTANCE.getISO()) {
            DateTimeFormatter isoFormat = getIsoFormat();
            Intrinsics.checkNotNullExpressionValue(isoFormat, "<get-isoFormat>(...)");
            return parseWithFormatOrNull(input, isoFormat);
        }
        if (format == UtcOffset.Formats.INSTANCE.getISO_BASIC()) {
            DateTimeFormatter isoBasicFormat = getIsoBasicFormat();
            Intrinsics.checkNotNullExpressionValue(isoBasicFormat, "<get-isoBasicFormat>(...)");
            return parseWithFormatOrNull(input, isoBasicFormat);
        }
        if (format != UtcOffset.Formats.INSTANCE.getFOUR_DIGITS()) {
            return format.parseOrNull(input);
        }
        DateTimeFormatter fourDigitsFormat = getFourDigitsFormat();
        Intrinsics.checkNotNullExpressionValue(fourDigitsFormat, "<get-fourDigitsFormat>(...)");
        return parseWithFormatOrNull(input, fourDigitsFormat);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final DateTimeFormatter getIsoFormat() {
        return (DateTimeFormatter) isoFormat$delegate.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final DateTimeFormatter getIsoBasicFormat() {
        return (DateTimeFormatter) isoBasicFormat$delegate.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final DateTimeFormatter getFourDigitsFormat() {
        return (DateTimeFormatter) fourDigitsFormat$delegate.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final UtcOffset parseWithFormat(CharSequence charSequence, DateTimeFormatter dateTimeFormatter) {
        try {
            return new UtcOffset((ZoneOffset) dateTimeFormatter.parse(charSequence, new UtcOffsetJvmKt$$ExternalSyntheticLambda3()));
        } catch (DateTimeException e) {
            throw new DateTimeFormatException(e);
        }
    }

    private static final UtcOffset parseWithFormatOrNull(CharSequence charSequence, DateTimeFormatter dateTimeFormatter) {
        try {
            return new UtcOffset((ZoneOffset) dateTimeFormatter.parse(charSequence, new UtcOffsetJvmKt$$ExternalSyntheticLambda3()));
        } catch (DateTimeException unused) {
            return null;
        }
    }

    public static /* synthetic */ UtcOffset parseOrNull$default(UtcOffset.Companion companion, CharSequence charSequence, DateTimeFormat dateTimeFormat, int i, Object obj) {
        if ((i & 2) != 0) {
            dateTimeFormat = UtcOffsetKt.getIsoUtcOffsetFormat();
        }
        return parseOrNull(companion, charSequence, dateTimeFormat);
    }
}
