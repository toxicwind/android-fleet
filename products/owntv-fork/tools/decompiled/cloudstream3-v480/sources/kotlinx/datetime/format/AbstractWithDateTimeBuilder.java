package kotlinx.datetime.format;

import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.datetime.LocalDate;
import kotlinx.datetime.LocalDateTime;
import kotlinx.datetime.LocalTime;
import kotlinx.datetime.YearMonth;
import kotlinx.datetime.internal.format.FormatStructure;

/* JADX INFO: compiled from: LocalDateTimeFormat.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b`\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003J\u0016\u0010\u0004\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H&J\u0016\u0010\t\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\n0\u0007H\u0016J\u0016\u0010\u000b\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\f0\u0007H\u0016J\u0016\u0010\r\u001a\u00020\u00052\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fH\u0016¨\u0006\u0011À\u0006\u0003"}, d2 = {"Lkotlinx/datetime/format/AbstractWithDateTimeBuilder;", "Lkotlinx/datetime/format/AbstractWithDateBuilder;", "Lkotlinx/datetime/format/AbstractWithTimeBuilder;", "Lkotlinx/datetime/format/DateTimeFormatBuilder$WithDateTime;", "addFormatStructureForDateTime", "", "structure", "Lkotlinx/datetime/internal/format/FormatStructure;", "Lkotlinx/datetime/format/DateTimeFieldContainer;", "addFormatStructureForDate", "Lkotlinx/datetime/format/DateFieldContainer;", "addFormatStructureForTime", "Lkotlinx/datetime/format/TimeFieldContainer;", "dateTime", "format", "Lkotlinx/datetime/format/DateTimeFormat;", "Lkotlinx/datetime/LocalDateTime;", "kotlinx-datetime"}, k = 1, mv = {2, 1, 0}, xi = 48)
public interface AbstractWithDateTimeBuilder extends AbstractWithDateBuilder, AbstractWithTimeBuilder, DateTimeFormatBuilder.WithDateTime {
    @Override // kotlinx.datetime.format.AbstractWithDateBuilder
    void addFormatStructureForDate(FormatStructure<? super DateFieldContainer> structure);

    void addFormatStructureForDateTime(FormatStructure<? super DateTimeFieldContainer> structure);

    @Override // kotlinx.datetime.format.AbstractWithTimeBuilder
    void addFormatStructureForTime(FormatStructure<? super TimeFieldContainer> structure);

    @Override // kotlinx.datetime.format.DateTimeFormatBuilder.WithDateTime
    void dateTime(DateTimeFormat<LocalDateTime> format);

    /* JADX INFO: renamed from: kotlinx.datetime.format.AbstractWithDateTimeBuilder$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: LocalDateTimeFormat.kt */
    public final /* synthetic */ class CC {
        public static void $default$addFormatStructureForDate(AbstractWithDateTimeBuilder _this, FormatStructure structure) {
            Intrinsics.checkNotNullParameter(structure, "structure");
            _this.addFormatStructureForDateTime(structure);
        }

        public static void $default$addFormatStructureForTime(AbstractWithDateTimeBuilder _this, FormatStructure structure) {
            Intrinsics.checkNotNullParameter(structure, "structure");
            _this.addFormatStructureForDateTime(structure);
        }

        public static void $default$dateTime(AbstractWithDateTimeBuilder _this, DateTimeFormat format) {
            Intrinsics.checkNotNullParameter(format, "format");
            _this.addFormatStructureForDateTime(((LocalDateTimeFormat) format).getActualFormat());
        }
    }

    /* JADX INFO: compiled from: LocalDateTimeFormat.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    public static final class DefaultImpls {
        @Deprecated
        public static void addFormatStructureForYearMonth(AbstractWithDateTimeBuilder abstractWithDateTimeBuilder, FormatStructure<? super YearMonthFieldContainer> structure) {
            Intrinsics.checkNotNullParameter(structure, "structure");
            AbstractWithDateBuilder.CC.$default$addFormatStructureForYearMonth(abstractWithDateTimeBuilder, structure);
        }

        @Deprecated
        public static void amPmHour(AbstractWithDateTimeBuilder abstractWithDateTimeBuilder, Padding padding) {
            Intrinsics.checkNotNullParameter(padding, "padding");
            AbstractWithTimeBuilder.CC.$default$amPmHour(abstractWithDateTimeBuilder, padding);
        }

        @Deprecated
        public static void amPmMarker(AbstractWithDateTimeBuilder abstractWithDateTimeBuilder, String am, String pm) {
            Intrinsics.checkNotNullParameter(am, "am");
            Intrinsics.checkNotNullParameter(pm, "pm");
            AbstractWithTimeBuilder.CC.$default$amPmMarker(abstractWithDateTimeBuilder, am, pm);
        }

        @Deprecated
        public static void date(AbstractWithDateTimeBuilder abstractWithDateTimeBuilder, DateTimeFormat<LocalDate> format) {
            Intrinsics.checkNotNullParameter(format, "format");
            AbstractWithDateBuilder.CC.$default$date(abstractWithDateTimeBuilder, format);
        }

        @Deprecated
        public static void day(AbstractWithDateTimeBuilder abstractWithDateTimeBuilder, Padding padding) {
            Intrinsics.checkNotNullParameter(padding, "padding");
            AbstractWithDateBuilder.CC.$default$day(abstractWithDateTimeBuilder, padding);
        }

        @Deprecated(message = "Use 'day' instead", replaceWith = @ReplaceWith(expression = "day(padding = padding)", imports = {}))
        @Deprecated
        public static void dayOfMonth(AbstractWithDateTimeBuilder abstractWithDateTimeBuilder, Padding padding) {
            Intrinsics.checkNotNullParameter(padding, "padding");
            DateTimeFormatBuilder.WithDate.CC.$default$dayOfMonth(abstractWithDateTimeBuilder, padding);
        }

        @Deprecated
        public static void dayOfWeek(AbstractWithDateTimeBuilder abstractWithDateTimeBuilder, DayOfWeekNames names) {
            Intrinsics.checkNotNullParameter(names, "names");
            AbstractWithDateBuilder.CC.$default$dayOfWeek(abstractWithDateTimeBuilder, names);
        }

        @Deprecated
        public static void dayOfYear(AbstractWithDateTimeBuilder abstractWithDateTimeBuilder, Padding padding) {
            Intrinsics.checkNotNullParameter(padding, "padding");
            AbstractWithDateBuilder.CC.$default$dayOfYear(abstractWithDateTimeBuilder, padding);
        }

        @Deprecated
        public static void hour(AbstractWithDateTimeBuilder abstractWithDateTimeBuilder, Padding padding) {
            Intrinsics.checkNotNullParameter(padding, "padding");
            AbstractWithTimeBuilder.CC.$default$hour(abstractWithDateTimeBuilder, padding);
        }

        @Deprecated
        public static void minute(AbstractWithDateTimeBuilder abstractWithDateTimeBuilder, Padding padding) {
            Intrinsics.checkNotNullParameter(padding, "padding");
            AbstractWithTimeBuilder.CC.$default$minute(abstractWithDateTimeBuilder, padding);
        }

        @Deprecated
        public static void monthName(AbstractWithDateTimeBuilder abstractWithDateTimeBuilder, MonthNames names) {
            Intrinsics.checkNotNullParameter(names, "names");
            AbstractWithYearMonthBuilder.CC.$default$monthName(abstractWithDateTimeBuilder, names);
        }

        @Deprecated
        public static void monthNumber(AbstractWithDateTimeBuilder abstractWithDateTimeBuilder, Padding padding) {
            Intrinsics.checkNotNullParameter(padding, "padding");
            AbstractWithYearMonthBuilder.CC.$default$monthNumber(abstractWithDateTimeBuilder, padding);
        }

        @Deprecated
        public static void second(AbstractWithDateTimeBuilder abstractWithDateTimeBuilder, Padding padding) {
            Intrinsics.checkNotNullParameter(padding, "padding");
            AbstractWithTimeBuilder.CC.$default$second(abstractWithDateTimeBuilder, padding);
        }

        @Deprecated
        public static void secondFraction(AbstractWithDateTimeBuilder abstractWithDateTimeBuilder, int i) {
            abstractWithDateTimeBuilder.secondFraction(i, i);
        }

        @Deprecated
        public static void secondFraction(AbstractWithDateTimeBuilder abstractWithDateTimeBuilder, int i, int i2) {
            AbstractWithTimeBuilder.CC.$default$secondFraction(abstractWithDateTimeBuilder, i, i2);
        }

        @Deprecated
        public static void time(AbstractWithDateTimeBuilder abstractWithDateTimeBuilder, DateTimeFormat<LocalTime> format) {
            Intrinsics.checkNotNullParameter(format, "format");
            AbstractWithTimeBuilder.CC.$default$time(abstractWithDateTimeBuilder, format);
        }

        @Deprecated
        public static void year(AbstractWithDateTimeBuilder abstractWithDateTimeBuilder, Padding padding) {
            Intrinsics.checkNotNullParameter(padding, "padding");
            AbstractWithYearMonthBuilder.CC.$default$year(abstractWithDateTimeBuilder, padding);
        }

        @Deprecated
        public static void yearMonth(AbstractWithDateTimeBuilder abstractWithDateTimeBuilder, DateTimeFormat<YearMonth> format) {
            Intrinsics.checkNotNullParameter(format, "format");
            AbstractWithYearMonthBuilder.CC.$default$yearMonth(abstractWithDateTimeBuilder, format);
        }

        @Deprecated
        public static void yearTwoDigits(AbstractWithDateTimeBuilder abstractWithDateTimeBuilder, int i) {
            AbstractWithYearMonthBuilder.CC.$default$yearTwoDigits(abstractWithDateTimeBuilder, i);
        }

        @Deprecated
        public static void addFormatStructureForDate(AbstractWithDateTimeBuilder abstractWithDateTimeBuilder, FormatStructure<? super DateFieldContainer> structure) {
            Intrinsics.checkNotNullParameter(structure, "structure");
            CC.$default$addFormatStructureForDate(abstractWithDateTimeBuilder, structure);
        }

        @Deprecated
        public static void addFormatStructureForTime(AbstractWithDateTimeBuilder abstractWithDateTimeBuilder, FormatStructure<? super TimeFieldContainer> structure) {
            Intrinsics.checkNotNullParameter(structure, "structure");
            CC.$default$addFormatStructureForTime(abstractWithDateTimeBuilder, structure);
        }

        @Deprecated
        public static void dateTime(AbstractWithDateTimeBuilder abstractWithDateTimeBuilder, DateTimeFormat<LocalDateTime> format) {
            Intrinsics.checkNotNullParameter(format, "format");
            CC.$default$dateTime(abstractWithDateTimeBuilder, format);
        }
    }
}
