package kotlinx.datetime.format;

import androidx.exifinterface.media.ExifInterface;
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
import kotlinx.datetime.DateTimeFormatException;
import kotlinx.datetime.YearMonth;
import kotlinx.datetime.internal.ToKotlinCodeKt;
import kotlinx.datetime.internal.format.BasicFormatStructure;

/* JADX INFO: compiled from: YearMonthFormat.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u00008\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a\f\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0002\u001a%\u0010\u0003\u001a\u0002H\u0004\"\u0004\b\u0000\u0010\u00042\b\u0010\u0005\u001a\u0004\u0018\u0001H\u00042\u0006\u0010\u0006\u001a\u00020\u0001H\u0000¢\u0006\u0002\u0010\u0007\u001a\u0014\u0010\t\u001a\u00020\n*\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0000\u001a\u0014\u0010\u000e\u001a\u00020\n*\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u0010H\u0000\"\u000e\u0010\b\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0002\n\u0000\"!\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150\u00148@X\u0080\u0084\u0002¢\u0006\f\n\u0004\b\u0018\u0010\u0019\u001a\u0004\b\u0016\u0010\u0017¨\u0006\u001a"}, d2 = {"toKotlinCode", "", "Lkotlinx/datetime/format/MonthNames;", "requireParsedField", ExifInterface.GPS_DIRECTION_TRUE, "field", "name", "(Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/Object;", "YEAR_OF_ERA_COMMENT", "yearOfEra", "", "Lkotlinx/datetime/format/DateTimeFormatBuilder$WithYearMonth;", "padding", "Lkotlinx/datetime/format/Padding;", "yearOfEraTwoDigits", "baseYear", "", "emptyIncompleteYearMonth", "Lkotlinx/datetime/format/IncompleteYearMonth;", "ISO_YEAR_MONTH", "Lkotlinx/datetime/format/DateTimeFormat;", "Lkotlinx/datetime/YearMonth;", "getISO_YEAR_MONTH", "()Lkotlinx/datetime/format/DateTimeFormat;", "ISO_YEAR_MONTH$delegate", "Lkotlin/Lazy;", "kotlinx-datetime"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class YearMonthFormatKt {
    private static final String YEAR_OF_ERA_COMMENT = " /** TODO: the original format had an `y` directive, so the behavior is different on years earlier than 1 AD. See the [kotlinx.datetime.format.byUnicodePattern] documentation for details. */";
    private static final IncompleteYearMonth emptyIncompleteYearMonth = new IncompleteYearMonth(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
    private static final Lazy ISO_YEAR_MONTH$delegate = LazyKt.lazy(new Function0() { // from class: kotlinx.datetime.format.YearMonthFormatKt$$ExternalSyntheticLambda0
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return YearMonthFormat.INSTANCE.build(new Function1() { // from class: kotlinx.datetime.format.YearMonthFormatKt$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return YearMonthFormatKt.ISO_YEAR_MONTH_delegate$lambda$1$lambda$0((DateTimeFormatBuilder.WithYearMonth) obj);
                }
            });
        }
    });

    /* JADX INFO: Access modifiers changed from: private */
    public static final String toKotlinCode(MonthNames monthNames) {
        List<String> names = monthNames.getNames();
        if (Intrinsics.areEqual(names, MonthNames.INSTANCE.getENGLISH_FULL().getNames())) {
            MonthNames.Companion companion = MonthNames.INSTANCE;
            return "MonthNames.ENGLISH_FULL";
        }
        if (!Intrinsics.areEqual(names, MonthNames.INSTANCE.getENGLISH_ABBREVIATED().getNames())) {
            return CollectionsKt.joinToString$default(monthNames.getNames(), ", ", "MonthNames(", ")", 0, null, AnonymousClass1.INSTANCE, 24, null);
        }
        MonthNames.Companion companion2 = MonthNames.INSTANCE;
        return "MonthNames.ENGLISH_ABBREVIATED";
    }

    /* JADX INFO: renamed from: kotlinx.datetime.format.YearMonthFormatKt$toKotlinCode$1, reason: invalid class name */
    /* JADX INFO: compiled from: YearMonthFormat.kt */
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

    public static final <T> T requireParsedField(T t, String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        if (t != null) {
            return t;
        }
        throw new DateTimeFormatException("Can not create a " + name + " from the given input: the field " + name + " is missing");
    }

    public static final void yearOfEra(DateTimeFormatBuilder.WithYearMonth withYearMonth, Padding padding) {
        Intrinsics.checkNotNullParameter(withYearMonth, "<this>");
        Intrinsics.checkNotNullParameter(padding, "padding");
        ((AbstractWithYearMonthBuilder) withYearMonth).addFormatStructureForYearMonth(new BasicFormatStructure(new YearDirective(padding, true)));
    }

    public static final void yearOfEraTwoDigits(DateTimeFormatBuilder.WithYearMonth withYearMonth, int i) {
        Intrinsics.checkNotNullParameter(withYearMonth, "<this>");
        ((AbstractWithYearMonthBuilder) withYearMonth).addFormatStructureForYearMonth(new BasicFormatStructure(new ReducedYearDirective(i, true)));
    }

    public static final DateTimeFormat<YearMonth> getISO_YEAR_MONTH() {
        return (DateTimeFormat) ISO_YEAR_MONTH$delegate.getValue();
    }

    static final Unit ISO_YEAR_MONTH_delegate$lambda$1$lambda$0(DateTimeFormatBuilder.WithYearMonth build) {
        Intrinsics.checkNotNullParameter(build, "$this$build");
        DateTimeFormatBuilder.WithYearMonth.CC.year$default(build, null, 1, null);
        DateTimeFormatBuilderKt.m10570char(build, '-');
        DateTimeFormatBuilder.WithYearMonth.CC.monthNumber$default(build, null, 1, null);
        return Unit.INSTANCE;
    }
}
