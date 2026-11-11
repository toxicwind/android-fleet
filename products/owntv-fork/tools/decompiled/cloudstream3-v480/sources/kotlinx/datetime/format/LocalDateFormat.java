package kotlinx.datetime.format;

import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.datetime.LocalDate;
import kotlinx.datetime.internal.format.AppendableFormatStructure;
import kotlinx.datetime.internal.format.CachedFormatStructure;
import kotlinx.datetime.internal.format.FormatStructure;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: LocalDateFormat.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\b\u0000\u0018\u0000 \u00122\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0002\u0012\u0013B\u0015\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0004\b\u0007\u0010\bJ\u0010\u0010\u000b\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\u0002H\u0016J\u0010\u0010\r\u001a\u00020\u00022\u0006\u0010\u000e\u001a\u00020\u0003H\u0016R\u001a\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0014\u0010\u000f\u001a\u00020\u00038VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0014"}, d2 = {"Lkotlinx/datetime/format/LocalDateFormat;", "Lkotlinx/datetime/format/AbstractDateTimeFormat;", "Lkotlinx/datetime/LocalDate;", "Lkotlinx/datetime/format/IncompleteLocalDate;", "actualFormat", "Lkotlinx/datetime/internal/format/CachedFormatStructure;", "Lkotlinx/datetime/format/DateFieldContainer;", "<init>", "(Lkotlinx/datetime/internal/format/CachedFormatStructure;)V", "getActualFormat", "()Lkotlinx/datetime/internal/format/CachedFormatStructure;", "intermediateFromValue", ES6Iterator.VALUE_PROPERTY, "valueFromIntermediate", "intermediate", "emptyIntermediate", "getEmptyIntermediate", "()Lkotlinx/datetime/format/IncompleteLocalDate;", "Companion", "Builder", "kotlinx-datetime"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class LocalDateFormat extends AbstractDateTimeFormat<LocalDate, IncompleteLocalDate> {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final CachedFormatStructure<DateFieldContainer> actualFormat;

    @Override // kotlinx.datetime.format.AbstractDateTimeFormat
    public CachedFormatStructure<DateFieldContainer> getActualFormat() {
        return this.actualFormat;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public LocalDateFormat(CachedFormatStructure<? super DateFieldContainer> actualFormat) {
        super(null);
        Intrinsics.checkNotNullParameter(actualFormat, "actualFormat");
        this.actualFormat = actualFormat;
    }

    @Override // kotlinx.datetime.format.AbstractDateTimeFormat
    public IncompleteLocalDate intermediateFromValue(LocalDate value) {
        Intrinsics.checkNotNullParameter(value, "value");
        IncompleteLocalDate incompleteLocalDate = new IncompleteLocalDate(null, null, null, null, 15, null);
        incompleteLocalDate.populateFrom(value);
        return incompleteLocalDate;
    }

    @Override // kotlinx.datetime.format.AbstractDateTimeFormat
    public LocalDate valueFromIntermediate(IncompleteLocalDate intermediate) {
        Intrinsics.checkNotNullParameter(intermediate, "intermediate");
        return intermediate.toLocalDate();
    }

    @Override // kotlinx.datetime.format.AbstractDateTimeFormat
    public IncompleteLocalDate getEmptyIntermediate() {
        return LocalDateFormatKt.emptyIncompleteLocalDate;
    }

    /* JADX INFO: compiled from: LocalDateFormat.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J%\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0017\u0010\u0007\u001a\u0013\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\b¢\u0006\u0002\b\u000b¨\u0006\f"}, d2 = {"Lkotlinx/datetime/format/LocalDateFormat$Companion;", "", "<init>", "()V", JsonPOJOBuilder.DEFAULT_BUILD_METHOD, "Lkotlinx/datetime/format/DateTimeFormat;", "Lkotlinx/datetime/LocalDate;", "block", "Lkotlin/Function1;", "Lkotlinx/datetime/format/DateTimeFormatBuilder$WithDate;", "", "Lkotlin/ExtensionFunctionType;", "kotlinx-datetime"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final DateTimeFormat<LocalDate> build(Function1<? super DateTimeFormatBuilder.WithDate, Unit> block) {
            Intrinsics.checkNotNullParameter(block, "block");
            Builder builder = new Builder(new AppendableFormatStructure());
            block.invoke(builder);
            return new LocalDateFormat(builder.build());
        }
    }

    /* JADX INFO: compiled from: LocalDateFormat.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00000\u00012\u00020\u0003B\u0015\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0016\u0010\n\u001a\u00020\u000b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00020\rH\u0016J\b\u0010\u000e\u001a\u00020\u0000H\u0016R\u001a\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\u000f"}, d2 = {"Lkotlinx/datetime/format/LocalDateFormat$Builder;", "Lkotlinx/datetime/format/AbstractDateTimeFormatBuilder;", "Lkotlinx/datetime/format/DateFieldContainer;", "Lkotlinx/datetime/format/AbstractWithDateBuilder;", "actualBuilder", "Lkotlinx/datetime/internal/format/AppendableFormatStructure;", "<init>", "(Lkotlinx/datetime/internal/format/AppendableFormatStructure;)V", "getActualBuilder", "()Lkotlinx/datetime/internal/format/AppendableFormatStructure;", "addFormatStructureForDate", "", "structure", "Lkotlinx/datetime/internal/format/FormatStructure;", "createEmpty", "kotlinx-datetime"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Builder implements AbstractDateTimeFormatBuilder<DateFieldContainer, Builder>, AbstractWithDateBuilder {
        private final AppendableFormatStructure<DateFieldContainer> actualBuilder;

        @Override // kotlinx.datetime.format.AbstractWithDateBuilder, kotlinx.datetime.format.AbstractWithYearMonthBuilder
        public /* synthetic */ void addFormatStructureForYearMonth(FormatStructure formatStructure) {
            AbstractWithDateBuilder.CC.$default$addFormatStructureForYearMonth(this, formatStructure);
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
        public /* synthetic */ CachedFormatStructure<DateFieldContainer> build() {
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

        @Override // kotlinx.datetime.format.AbstractWithYearMonthBuilder, kotlinx.datetime.format.DateTimeFormatBuilder.WithYearMonth
        public /* synthetic */ void monthName(MonthNames monthNames) {
            AbstractWithYearMonthBuilder.CC.$default$monthName(this, monthNames);
        }

        @Override // kotlinx.datetime.format.AbstractWithYearMonthBuilder, kotlinx.datetime.format.DateTimeFormatBuilder.WithYearMonth
        public /* synthetic */ void monthNumber(Padding padding) {
            AbstractWithYearMonthBuilder.CC.$default$monthNumber(this, padding);
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

        public Builder(AppendableFormatStructure<DateFieldContainer> actualBuilder) {
            Intrinsics.checkNotNullParameter(actualBuilder, "actualBuilder");
            this.actualBuilder = actualBuilder;
        }

        @Override // kotlinx.datetime.format.AbstractDateTimeFormatBuilder
        public AppendableFormatStructure<DateFieldContainer> getActualBuilder() {
            return this.actualBuilder;
        }

        @Override // kotlinx.datetime.format.AbstractWithDateBuilder
        public void addFormatStructureForDate(FormatStructure<? super DateFieldContainer> structure) {
            Intrinsics.checkNotNullParameter(structure, "structure");
            getActualBuilder().add(structure);
        }

        @Override // kotlinx.datetime.format.AbstractDateTimeFormatBuilder
        public Builder createEmpty() {
            return new Builder(new AppendableFormatStructure());
        }
    }
}
