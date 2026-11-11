package kotlinx.datetime.format;

import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.datetime.LocalDate;
import kotlinx.datetime.internal.ToKotlinCodeKt;

/* JADX INFO: compiled from: LocalDateFormat.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\u001a\f\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0002\"!\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048@X\u0080\u0084\u0002¢\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\u0006\u0010\u0007\"!\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048@X\u0080\u0084\u0002¢\u0006\f\n\u0004\b\f\u0010\t\u001a\u0004\b\u000b\u0010\u0007\"\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"toKotlinCode", "", "Lkotlinx/datetime/format/DayOfWeekNames;", "ISO_DATE", "Lkotlinx/datetime/format/DateTimeFormat;", "Lkotlinx/datetime/LocalDate;", "getISO_DATE", "()Lkotlinx/datetime/format/DateTimeFormat;", "ISO_DATE$delegate", "Lkotlin/Lazy;", "ISO_DATE_BASIC", "getISO_DATE_BASIC", "ISO_DATE_BASIC$delegate", "emptyIncompleteLocalDate", "Lkotlinx/datetime/format/IncompleteLocalDate;", "kotlinx-datetime"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class LocalDateFormatKt {
    private static final Lazy ISO_DATE$delegate = LazyKt.lazy(new Function0() { // from class: kotlinx.datetime.format.LocalDateFormatKt$$ExternalSyntheticLambda1
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return LocalDateFormat.INSTANCE.build(new Function1() { // from class: kotlinx.datetime.format.LocalDateFormatKt$$ExternalSyntheticLambda3
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return LocalDateFormatKt.ISO_DATE_delegate$lambda$1$lambda$0((DateTimeFormatBuilder.WithDate) obj);
                }
            });
        }
    });
    private static final Lazy ISO_DATE_BASIC$delegate = LazyKt.lazy(new Function0() { // from class: kotlinx.datetime.format.LocalDateFormatKt$$ExternalSyntheticLambda2
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return LocalDateFormat.INSTANCE.build(new Function1() { // from class: kotlinx.datetime.format.LocalDateFormatKt$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return LocalDateFormatKt.ISO_DATE_BASIC_delegate$lambda$3$lambda$2((DateTimeFormatBuilder.WithDate) obj);
                }
            });
        }
    });
    private static final IncompleteLocalDate emptyIncompleteLocalDate = new IncompleteLocalDate(null, null, null, null, 15, null);

    /* JADX INFO: Access modifiers changed from: private */
    public static final String toKotlinCode(DayOfWeekNames dayOfWeekNames) {
        List<String> names = dayOfWeekNames.getNames();
        if (Intrinsics.areEqual(names, DayOfWeekNames.INSTANCE.getENGLISH_FULL().getNames())) {
            DayOfWeekNames.Companion companion = DayOfWeekNames.INSTANCE;
            return "DayOfWeekNames.ENGLISH_FULL";
        }
        if (!Intrinsics.areEqual(names, DayOfWeekNames.INSTANCE.getENGLISH_ABBREVIATED().getNames())) {
            return CollectionsKt.joinToString$default(dayOfWeekNames.getNames(), ", ", "DayOfWeekNames(", ")", 0, null, AnonymousClass1.INSTANCE, 24, null);
        }
        DayOfWeekNames.Companion companion2 = DayOfWeekNames.INSTANCE;
        return "DayOfWeekNames.ENGLISH_ABBREVIATED";
    }

    /* JADX INFO: renamed from: kotlinx.datetime.format.LocalDateFormatKt$toKotlinCode$1, reason: invalid class name */
    /* JADX INFO: compiled from: LocalDateFormat.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function1<String, String> {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

        AnonymousClass1() {
            super(1, ToKotlinCodeKt.class, "toKotlinCode", "toKotlinCode(Ljava/lang/String;)Ljava/lang/String;", 1);
        }

        @Override // kotlin.jvm.functions.Function1
        public final String invoke(String p0) {
            Intrinsics.checkNotNullParameter(p0, "p0");
            return ToKotlinCodeKt.toKotlinCode(p0);
        }
    }

    public static final DateTimeFormat<LocalDate> getISO_DATE() {
        return (DateTimeFormat) ISO_DATE$delegate.getValue();
    }

    static final Unit ISO_DATE_delegate$lambda$1$lambda$0(DateTimeFormatBuilder.WithDate build) {
        Intrinsics.checkNotNullParameter(build, "$this$build");
        DateTimeFormatBuilder.WithDate withDate = build;
        DateTimeFormatBuilder.WithYearMonth.CC.year$default(withDate, null, 1, null);
        DateTimeFormatBuilder.WithDate withDate2 = build;
        DateTimeFormatBuilderKt.m10570char(withDate2, '-');
        DateTimeFormatBuilder.WithYearMonth.CC.monthNumber$default(withDate, null, 1, null);
        DateTimeFormatBuilderKt.m10570char(withDate2, '-');
        DateTimeFormatBuilder.WithDate.CC.day$default(build, null, 1, null);
        return Unit.INSTANCE;
    }

    public static final DateTimeFormat<LocalDate> getISO_DATE_BASIC() {
        return (DateTimeFormat) ISO_DATE_BASIC$delegate.getValue();
    }

    static final Unit ISO_DATE_BASIC_delegate$lambda$3$lambda$2(DateTimeFormatBuilder.WithDate build) {
        Intrinsics.checkNotNullParameter(build, "$this$build");
        DateTimeFormatBuilder.WithDate withDate = build;
        DateTimeFormatBuilder.WithYearMonth.CC.year$default(withDate, null, 1, null);
        DateTimeFormatBuilder.WithYearMonth.CC.monthNumber$default(withDate, null, 1, null);
        DateTimeFormatBuilder.WithDate.CC.day$default(build, null, 1, null);
        return Unit.INSTANCE;
    }
}
