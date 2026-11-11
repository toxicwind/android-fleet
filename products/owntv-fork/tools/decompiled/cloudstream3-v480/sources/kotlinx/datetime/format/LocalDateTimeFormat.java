package kotlinx.datetime.format;

import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.datetime.LocalDateTime;
import kotlinx.datetime.internal.format.AppendableFormatStructure;
import kotlinx.datetime.internal.format.CachedFormatStructure;
import kotlinx.datetime.internal.format.FormatStructure;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: LocalDateTimeFormat.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\b\u0000\u0018\u0000 \u00122\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0002\u0012\u0013B\u0015\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0004\b\u0007\u0010\bJ\u0010\u0010\u000b\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\u0002H\u0016J\u0010\u0010\r\u001a\u00020\u00022\u0006\u0010\u000e\u001a\u00020\u0003H\u0016R\u001a\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0014\u0010\u000f\u001a\u00020\u00038VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0014"}, d2 = {"Lkotlinx/datetime/format/LocalDateTimeFormat;", "Lkotlinx/datetime/format/AbstractDateTimeFormat;", "Lkotlinx/datetime/LocalDateTime;", "Lkotlinx/datetime/format/IncompleteLocalDateTime;", "actualFormat", "Lkotlinx/datetime/internal/format/CachedFormatStructure;", "Lkotlinx/datetime/format/DateTimeFieldContainer;", "<init>", "(Lkotlinx/datetime/internal/format/CachedFormatStructure;)V", "getActualFormat", "()Lkotlinx/datetime/internal/format/CachedFormatStructure;", "intermediateFromValue", ES6Iterator.VALUE_PROPERTY, "valueFromIntermediate", "intermediate", "emptyIntermediate", "getEmptyIntermediate", "()Lkotlinx/datetime/format/IncompleteLocalDateTime;", "Companion", "Builder", "kotlinx-datetime"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class LocalDateTimeFormat extends AbstractDateTimeFormat<LocalDateTime, IncompleteLocalDateTime> {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final CachedFormatStructure<DateTimeFieldContainer> actualFormat;

    @Override // kotlinx.datetime.format.AbstractDateTimeFormat
    public CachedFormatStructure<DateTimeFieldContainer> getActualFormat() {
        return this.actualFormat;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public LocalDateTimeFormat(CachedFormatStructure<? super DateTimeFieldContainer> actualFormat) {
        super(null);
        Intrinsics.checkNotNullParameter(actualFormat, "actualFormat");
        this.actualFormat = actualFormat;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlinx.datetime.format.AbstractDateTimeFormat
    public IncompleteLocalDateTime intermediateFromValue(LocalDateTime value) {
        Intrinsics.checkNotNullParameter(value, "value");
        IncompleteLocalDateTime incompleteLocalDateTime = new IncompleteLocalDateTime(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
        incompleteLocalDateTime.populateFrom(value);
        return incompleteLocalDateTime;
    }

    @Override // kotlinx.datetime.format.AbstractDateTimeFormat
    public LocalDateTime valueFromIntermediate(IncompleteLocalDateTime intermediate) {
        Intrinsics.checkNotNullParameter(intermediate, "intermediate");
        return intermediate.toLocalDateTime();
    }

    @Override // kotlinx.datetime.format.AbstractDateTimeFormat
    public IncompleteLocalDateTime getEmptyIntermediate() {
        return LocalDateTimeFormatKt.emptyIncompleteLocalDateTime;
    }

    /* JADX INFO: compiled from: LocalDateTimeFormat.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001f\u0010\u0004\u001a\u00020\u00052\u0017\u0010\u0006\u001a\u0013\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0007¢\u0006\u0002\b\n¨\u0006\u000b"}, d2 = {"Lkotlinx/datetime/format/LocalDateTimeFormat$Companion;", "", "<init>", "()V", JsonPOJOBuilder.DEFAULT_BUILD_METHOD, "Lkotlinx/datetime/format/LocalDateTimeFormat;", "block", "Lkotlin/Function1;", "Lkotlinx/datetime/format/DateTimeFormatBuilder$WithDateTime;", "", "Lkotlin/ExtensionFunctionType;", "kotlinx-datetime"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final LocalDateTimeFormat build(Function1<? super DateTimeFormatBuilder.WithDateTime, Unit> block) {
            Intrinsics.checkNotNullParameter(block, "block");
            Builder builder = new Builder(new AppendableFormatStructure());
            block.invoke(builder);
            return new LocalDateTimeFormat(builder.build());
        }
    }

    /* JADX INFO: compiled from: LocalDateTimeFormat.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00000\u00012\u00020\u0003B\u0015\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0016\u0010\n\u001a\u00020\u000b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00020\rH\u0016J\b\u0010\u000e\u001a\u00020\u0000H\u0016R\u001a\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\u000f"}, d2 = {"Lkotlinx/datetime/format/LocalDateTimeFormat$Builder;", "Lkotlinx/datetime/format/AbstractDateTimeFormatBuilder;", "Lkotlinx/datetime/format/DateTimeFieldContainer;", "Lkotlinx/datetime/format/AbstractWithDateTimeBuilder;", "actualBuilder", "Lkotlinx/datetime/internal/format/AppendableFormatStructure;", "<init>", "(Lkotlinx/datetime/internal/format/AppendableFormatStructure;)V", "getActualBuilder", "()Lkotlinx/datetime/internal/format/AppendableFormatStructure;", "addFormatStructureForDateTime", "", "structure", "Lkotlinx/datetime/internal/format/FormatStructure;", "createEmpty", "kotlinx-datetime"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Builder implements AbstractDateTimeFormatBuilder<DateTimeFieldContainer, Builder>, AbstractWithDateTimeBuilder {
        private final AppendableFormatStructure<DateTimeFieldContainer> actualBuilder;

        @Override // kotlinx.datetime.format.AbstractWithDateTimeBuilder, kotlinx.datetime.format.AbstractWithDateBuilder
        public /* synthetic */ void addFormatStructureForDate(FormatStructure formatStructure) {
            AbstractWithDateTimeBuilder.CC.$default$addFormatStructureForDate(this, formatStructure);
        }

        @Override // kotlinx.datetime.format.AbstractWithDateTimeBuilder, kotlinx.datetime.format.AbstractWithTimeBuilder
        public /* synthetic */ void addFormatStructureForTime(FormatStructure formatStructure) {
            AbstractWithDateTimeBuilder.CC.$default$addFormatStructureForTime(this, formatStructure);
        }

        @Override // kotlinx.datetime.format.AbstractWithDateBuilder, kotlinx.datetime.format.AbstractWithYearMonthBuilder
        public /* synthetic */ void addFormatStructureForYearMonth(FormatStructure formatStructure) {
            AbstractWithDateBuilder.CC.$default$addFormatStructureForYearMonth(this, formatStructure);
        }

        @Override // kotlinx.datetime.format.AbstractWithTimeBuilder, kotlinx.datetime.format.DateTimeFormatBuilder.WithTime
        public /* synthetic */ void amPmHour(Padding padding) {
            AbstractWithTimeBuilder.CC.$default$amPmHour(this, padding);
        }

        @Override // kotlinx.datetime.format.AbstractWithTimeBuilder, kotlinx.datetime.format.DateTimeFormatBuilder.WithTime
        public /* synthetic */ void amPmMarker(String str, String str2) {
            AbstractWithTimeBuilder.CC.$default$amPmMarker(this, str, str2);
        }

        @Override // kotlinx.datetime.format.AbstractDateTimeFormatBuilder
        public /* synthetic */ void appendAlternativeParsingImpl(Function1[] function1Arr, Function1 function1) {
            AbstractDateTimeFormatBuilder.CC.$default$appendAlternativeParsingImpl(this, function1Arr, function1);
        }

        @Override // kotlinx.datetime.format.AbstractDateTimeFormatBuilder
        public /* synthetic */ void appendOptionalImpl(String str, Function1 function1) {
            AbstractDateTimeFormatBuilder.CC.$default$appendOptionalImpl(this, str, function1);
        }

        @Override // kotlinx.datetime.format.AbstractDateTimeFormatBuilder
        public /* synthetic */ CachedFormatStructure<DateTimeFieldContainer> build() {
            return AbstractDateTimeFormatBuilder.CC.$default$build(this);
        }

        @Override // kotlinx.datetime.format.AbstractDateTimeFormatBuilder, kotlinx.datetime.format.DateTimeFormatBuilder
        public /* synthetic */ void chars(String str) {
            AbstractDateTimeFormatBuilder.CC.$default$chars(this, str);
        }

        @Override // kotlinx.datetime.format.AbstractWithDateBuilder, kotlinx.datetime.format.DateTimeFormatBuilder.WithDate
        public /* synthetic */ void date(DateTimeFormat dateTimeFormat) {
            AbstractWithDateBuilder.CC.$default$date(this, dateTimeFormat);
        }

        @Override // kotlinx.datetime.format.AbstractWithDateTimeBuilder, kotlinx.datetime.format.DateTimeFormatBuilder.WithDateTime
        public /* synthetic */ void dateTime(DateTimeFormat dateTimeFormat) {
            AbstractWithDateTimeBuilder.CC.$default$dateTime(this, dateTimeFormat);
        }

        @Override // kotlinx.datetime.format.AbstractWithDateBuilder, kotlinx.datetime.format.DateTimeFormatBuilder.WithDate
        public /* synthetic */ void day(Padding padding) {
            AbstractWithDateBuilder.CC.$default$day(this, padding);
        }

        @Override // kotlinx.datetime.format.DateTimeFormatBuilder.WithDate
        public /* synthetic */ void dayOfMonth(Padding padding) {
            DateTimeFormatBuilder.WithDate.CC.$default$dayOfMonth(this, padding);
        }

        @Override // kotlinx.datetime.format.AbstractWithDateBuilder, kotlinx.datetime.format.DateTimeFormatBuilder.WithDate
        public /* synthetic */ void dayOfWeek(DayOfWeekNames dayOfWeekNames) {
            AbstractWithDateBuilder.CC.$default$dayOfWeek(this, dayOfWeekNames);
        }

        @Override // kotlinx.datetime.format.AbstractWithDateBuilder, kotlinx.datetime.format.DateTimeFormatBuilder.WithDate
        public /* synthetic */ void dayOfYear(Padding padding) {
            AbstractWithDateBuilder.CC.$default$dayOfYear(this, padding);
        }

        @Override // kotlinx.datetime.format.AbstractWithTimeBuilder, kotlinx.datetime.format.DateTimeFormatBuilder.WithTime
        public /* synthetic */ void hour(Padding padding) {
            AbstractWithTimeBuilder.CC.$default$hour(this, padding);
        }

        @Override // kotlinx.datetime.format.AbstractWithTimeBuilder, kotlinx.datetime.format.DateTimeFormatBuilder.WithTime
        public /* synthetic */ void minute(Padding padding) {
            AbstractWithTimeBuilder.CC.$default$minute(this, padding);
        }

        @Override // kotlinx.datetime.format.AbstractWithYearMonthBuilder, kotlinx.datetime.format.DateTimeFormatBuilder.WithYearMonth
        public /* synthetic */ void monthName(MonthNames monthNames) {
            AbstractWithYearMonthBuilder.CC.$default$monthName(this, monthNames);
        }

        @Override // kotlinx.datetime.format.AbstractWithYearMonthBuilder, kotlinx.datetime.format.DateTimeFormatBuilder.WithYearMonth
        public /* synthetic */ void monthNumber(Padding padding) {
            AbstractWithYearMonthBuilder.CC.$default$monthNumber(this, padding);
        }

        @Override // kotlinx.datetime.format.AbstractWithTimeBuilder, kotlinx.datetime.format.DateTimeFormatBuilder.WithTime
        public /* synthetic */ void second(Padding padding) {
            AbstractWithTimeBuilder.CC.$default$second(this, padding);
        }

        @Override // kotlinx.datetime.format.DateTimeFormatBuilder.WithTime
        public /* synthetic */ void secondFraction(int i) {
            secondFraction(i, i);
        }

        @Override // kotlinx.datetime.format.AbstractWithTimeBuilder, kotlinx.datetime.format.DateTimeFormatBuilder.WithTime
        public /* synthetic */ void secondFraction(int i, int i2) {
            AbstractWithTimeBuilder.CC.$default$secondFraction(this, i, i2);
        }

        @Override // kotlinx.datetime.format.AbstractWithTimeBuilder, kotlinx.datetime.format.DateTimeFormatBuilder.WithTime
        public /* synthetic */ void time(DateTimeFormat dateTimeFormat) {
            AbstractWithTimeBuilder.CC.$default$time(this, dateTimeFormat);
        }

        @Override // kotlinx.datetime.format.AbstractWithYearMonthBuilder, kotlinx.datetime.format.DateTimeFormatBuilder.WithYearMonth
        public /* synthetic */ void year(Padding padding) {
            AbstractWithYearMonthBuilder.CC.$default$year(this, padding);
        }

        @Override // kotlinx.datetime.format.AbstractWithYearMonthBuilder, kotlinx.datetime.format.DateTimeFormatBuilder.WithYearMonth
        public /* synthetic */ void yearMonth(DateTimeFormat dateTimeFormat) {
            AbstractWithYearMonthBuilder.CC.$default$yearMonth(this, dateTimeFormat);
        }

        @Override // kotlinx.datetime.format.AbstractWithYearMonthBuilder, kotlinx.datetime.format.DateTimeFormatBuilder.WithYearMonth
        public /* synthetic */ void yearTwoDigits(int i) {
            AbstractWithYearMonthBuilder.CC.$default$yearTwoDigits(this, i);
        }

        public Builder(AppendableFormatStructure<DateTimeFieldContainer> actualBuilder) {
            Intrinsics.checkNotNullParameter(actualBuilder, "actualBuilder");
            this.actualBuilder = actualBuilder;
        }

        @Override // kotlinx.datetime.format.AbstractDateTimeFormatBuilder
        public AppendableFormatStructure<DateTimeFieldContainer> getActualBuilder() {
            return this.actualBuilder;
        }

        @Override // kotlinx.datetime.format.AbstractWithDateTimeBuilder
        public void addFormatStructureForDateTime(FormatStructure<? super DateTimeFieldContainer> structure) {
            Intrinsics.checkNotNullParameter(structure, "structure");
            getActualBuilder().add(structure);
        }

        @Override // kotlinx.datetime.format.AbstractDateTimeFormatBuilder
        public Builder createEmpty() {
            return new Builder(new AppendableFormatStructure());
        }
    }
}
