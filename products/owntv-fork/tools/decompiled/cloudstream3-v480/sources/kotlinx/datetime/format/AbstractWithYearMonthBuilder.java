package kotlinx.datetime.format;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.datetime.YearMonth;
import kotlinx.datetime.internal.format.BasicFormatStructure;
import kotlinx.datetime.internal.format.FormatStructure;

/* JADX INFO: compiled from: YearMonthFormat.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b`\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H&J\u0010\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\tH\u0016J\u0010\u0010\n\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u0010\u0010\r\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\tH\u0016J\u0010\u0010\u000e\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u0016\u0010\u0011\u001a\u00020\u00032\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013H\u0016¨\u0006\u0015À\u0006\u0003"}, d2 = {"Lkotlinx/datetime/format/AbstractWithYearMonthBuilder;", "Lkotlinx/datetime/format/DateTimeFormatBuilder$WithYearMonth;", "addFormatStructureForYearMonth", "", "structure", "Lkotlinx/datetime/internal/format/FormatStructure;", "Lkotlinx/datetime/format/YearMonthFieldContainer;", "year", "padding", "Lkotlinx/datetime/format/Padding;", "yearTwoDigits", "baseYear", "", "monthNumber", "monthName", "names", "Lkotlinx/datetime/format/MonthNames;", "yearMonth", "format", "Lkotlinx/datetime/format/DateTimeFormat;", "Lkotlinx/datetime/YearMonth;", "kotlinx-datetime"}, k = 1, mv = {2, 1, 0}, xi = 48)
public interface AbstractWithYearMonthBuilder extends DateTimeFormatBuilder.WithYearMonth {
    void addFormatStructureForYearMonth(FormatStructure<? super YearMonthFieldContainer> structure);

    @Override // kotlinx.datetime.format.DateTimeFormatBuilder.WithYearMonth
    void monthName(MonthNames names);

    @Override // kotlinx.datetime.format.DateTimeFormatBuilder.WithYearMonth
    void monthNumber(Padding padding);

    @Override // kotlinx.datetime.format.DateTimeFormatBuilder.WithYearMonth
    void year(Padding padding);

    @Override // kotlinx.datetime.format.DateTimeFormatBuilder.WithYearMonth
    void yearMonth(DateTimeFormat<YearMonth> format);

    @Override // kotlinx.datetime.format.DateTimeFormatBuilder.WithYearMonth
    void yearTwoDigits(int baseYear);

    /* JADX INFO: renamed from: kotlinx.datetime.format.AbstractWithYearMonthBuilder$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: YearMonthFormat.kt */
    public final /* synthetic */ class CC {
        public static void $default$year(AbstractWithYearMonthBuilder _this, Padding padding) {
            Intrinsics.checkNotNullParameter(padding, "padding");
            _this.addFormatStructureForYearMonth(new BasicFormatStructure(new YearDirective(padding, false, 2, null)));
        }

        public static void $default$yearTwoDigits(AbstractWithYearMonthBuilder _this, int i) {
            _this.addFormatStructureForYearMonth(new BasicFormatStructure(new ReducedYearDirective(i, false, 2, null)));
        }

        public static void $default$monthNumber(AbstractWithYearMonthBuilder _this, Padding padding) {
            Intrinsics.checkNotNullParameter(padding, "padding");
            _this.addFormatStructureForYearMonth(new BasicFormatStructure(new MonthDirective(padding)));
        }

        public static void $default$monthName(AbstractWithYearMonthBuilder _this, MonthNames names) {
            Intrinsics.checkNotNullParameter(names, "names");
            _this.addFormatStructureForYearMonth(new BasicFormatStructure(new MonthNameDirective(names)));
        }

        public static void $default$yearMonth(AbstractWithYearMonthBuilder _this, DateTimeFormat format) {
            Intrinsics.checkNotNullParameter(format, "format");
            _this.addFormatStructureForYearMonth(((YearMonthFormat) format).getActualFormat());
        }
    }

    /* JADX INFO: compiled from: YearMonthFormat.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    public static final class DefaultImpls {
        @Deprecated
        public static void year(AbstractWithYearMonthBuilder abstractWithYearMonthBuilder, Padding padding) {
            Intrinsics.checkNotNullParameter(padding, "padding");
            CC.$default$year(abstractWithYearMonthBuilder, padding);
        }

        @Deprecated
        public static void yearTwoDigits(AbstractWithYearMonthBuilder abstractWithYearMonthBuilder, int i) {
            CC.$default$yearTwoDigits(abstractWithYearMonthBuilder, i);
        }

        @Deprecated
        public static void monthNumber(AbstractWithYearMonthBuilder abstractWithYearMonthBuilder, Padding padding) {
            Intrinsics.checkNotNullParameter(padding, "padding");
            CC.$default$monthNumber(abstractWithYearMonthBuilder, padding);
        }

        @Deprecated
        public static void monthName(AbstractWithYearMonthBuilder abstractWithYearMonthBuilder, MonthNames names) {
            Intrinsics.checkNotNullParameter(names, "names");
            CC.$default$monthName(abstractWithYearMonthBuilder, names);
        }

        @Deprecated
        public static void yearMonth(AbstractWithYearMonthBuilder abstractWithYearMonthBuilder, DateTimeFormat<YearMonth> format) {
            Intrinsics.checkNotNullParameter(format, "format");
            CC.$default$yearMonth(abstractWithYearMonthBuilder, format);
        }
    }
}
