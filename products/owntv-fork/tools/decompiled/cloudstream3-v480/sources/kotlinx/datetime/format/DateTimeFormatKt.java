package kotlinx.datetime.format;

import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.datetime.LocalDate;
import kotlinx.datetime.LocalDateTime;
import kotlinx.datetime.LocalTime;
import kotlinx.datetime.UtcOffset;
import kotlinx.datetime.YearMonth;
import kotlinx.datetime.internal.format.CachedFormatStructure;

/* JADX INFO: compiled from: DateTimeFormat.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000$\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a\f\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0000\u001a\u0015\u0010\u0003\u001a\u00020\u0004*\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004H\u0080\b\u001a\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u0004*\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004H\u0080\b¢\u0006\u0002\u0010\u0007\"1\u0010\b\u001a\u0018\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020\u0001\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u000b0\n0\t8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\f\u0010\r¨\u0006\u0010"}, d2 = {"toKotlinCode", "", "Lkotlinx/datetime/format/Padding;", "minDigits", "", "width", "spaces", "(Lkotlinx/datetime/format/Padding;I)Ljava/lang/Integer;", "allFormatConstants", "", "Lkotlin/Pair;", "Lkotlinx/datetime/internal/format/CachedFormatStructure;", "getAllFormatConstants", "()Ljava/util/List;", "allFormatConstants$delegate", "Lkotlin/Lazy;", "kotlinx-datetime"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class DateTimeFormatKt {
    private static final Lazy allFormatConstants$delegate = LazyKt.lazy(new Function0() { // from class: kotlinx.datetime.format.DateTimeFormatKt$$ExternalSyntheticLambda0
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return CollectionsKt.listOf((Object[]) new Pair[]{TuplesKt.to("dateTimeComponents(DateTimeComponents.Formats.RFC_1123)", DateTimeFormatKt.allFormatConstants_delegate$lambda$0$unwrap(DateTimeComponents.Formats.INSTANCE.getRFC_1123())), TuplesKt.to("dateTimeComponents(DateTimeComponents.Formats.ISO_DATE_TIME_OFFSET)", DateTimeFormatKt.allFormatConstants_delegate$lambda$0$unwrap(DateTimeComponents.Formats.INSTANCE.getISO_DATE_TIME_OFFSET())), TuplesKt.to("date(LocalDateTime.Formats.ISO)", DateTimeFormatKt.allFormatConstants_delegate$lambda$0$unwrap(LocalDateTime.Formats.INSTANCE.getISO())), TuplesKt.to("date(LocalDate.Formats.ISO)", DateTimeFormatKt.allFormatConstants_delegate$lambda$0$unwrap(LocalDate.Formats.INSTANCE.getISO())), TuplesKt.to("date(LocalDate.Formats.ISO_BASIC)", DateTimeFormatKt.allFormatConstants_delegate$lambda$0$unwrap(LocalDate.Formats.INSTANCE.getISO_BASIC())), TuplesKt.to("time(LocalTime.Formats.ISO)", DateTimeFormatKt.allFormatConstants_delegate$lambda$0$unwrap(LocalTime.Formats.INSTANCE.getISO())), TuplesKt.to("offset(UtcOffset.Formats.ISO)", DateTimeFormatKt.allFormatConstants_delegate$lambda$0$unwrap(UtcOffset.Formats.INSTANCE.getISO())), TuplesKt.to("offset(UtcOffset.Formats.ISO_BASIC)", DateTimeFormatKt.allFormatConstants_delegate$lambda$0$unwrap(UtcOffset.Formats.INSTANCE.getISO_BASIC())), TuplesKt.to("offset(UtcOffset.Formats.FOUR_DIGITS)", DateTimeFormatKt.allFormatConstants_delegate$lambda$0$unwrap(UtcOffset.Formats.INSTANCE.getFOUR_DIGITS())), TuplesKt.to("yearMonth(YearMonth.Formats.ISO)", DateTimeFormatKt.allFormatConstants_delegate$lambda$0$unwrap(YearMonth.Formats.INSTANCE.getISO()))});
        }
    });

    /* JADX INFO: compiled from: DateTimeFormat.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[Padding.values().length];
            try {
                iArr[Padding.NONE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[Padding.ZERO.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[Padding.SPACE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public static final String toKotlinCode(Padding padding) {
        Intrinsics.checkNotNullParameter(padding, "<this>");
        int i = WhenMappings.$EnumSwitchMapping$0[padding.ordinal()];
        if (i == 1) {
            return "Padding.NONE";
        }
        if (i == 2) {
            return "Padding.ZERO";
        }
        if (i != 3) {
            throw new NoWhenBranchMatchedException();
        }
        return "Padding.SPACE";
    }

    public static final int minDigits(Padding padding, int i) {
        Intrinsics.checkNotNullParameter(padding, "<this>");
        if (padding == Padding.ZERO) {
            return i;
        }
        return 1;
    }

    public static final Integer spaces(Padding padding, int i) {
        Intrinsics.checkNotNullParameter(padding, "<this>");
        if (padding == Padding.SPACE) {
            return Integer.valueOf(i);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List<Pair<String, CachedFormatStructure<?>>> getAllFormatConstants() {
        return (List) allFormatConstants$delegate.getValue();
    }

    private static final CachedFormatStructure<?> allFormatConstants_delegate$lambda$0$unwrap(DateTimeFormat<?> dateTimeFormat) {
        Intrinsics.checkNotNull(dateTimeFormat, "null cannot be cast to non-null type kotlinx.datetime.format.AbstractDateTimeFormat<*, *>");
        return ((AbstractDateTimeFormat) dateTimeFormat).getActualFormat();
    }
}
