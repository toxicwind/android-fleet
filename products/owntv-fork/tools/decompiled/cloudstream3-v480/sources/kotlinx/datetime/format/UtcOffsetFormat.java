package kotlinx.datetime.format;

import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.datetime.UtcOffset;
import kotlinx.datetime.internal.format.AppendableFormatStructure;
import kotlinx.datetime.internal.format.CachedFormatStructure;
import kotlinx.datetime.internal.format.FormatStructure;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: UtcOffsetFormat.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\b\u0000\u0018\u0000 \u00122\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0002\u0012\u0013B\u0015\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0004\b\u0007\u0010\bJ\u0010\u0010\u000b\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\u0002H\u0016J\u0010\u0010\r\u001a\u00020\u00022\u0006\u0010\u000e\u001a\u00020\u0003H\u0016R\u001a\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0014\u0010\u000f\u001a\u00020\u00038VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0014"}, d2 = {"Lkotlinx/datetime/format/UtcOffsetFormat;", "Lkotlinx/datetime/format/AbstractDateTimeFormat;", "Lkotlinx/datetime/UtcOffset;", "Lkotlinx/datetime/format/IncompleteUtcOffset;", "actualFormat", "Lkotlinx/datetime/internal/format/CachedFormatStructure;", "Lkotlinx/datetime/format/UtcOffsetFieldContainer;", "<init>", "(Lkotlinx/datetime/internal/format/CachedFormatStructure;)V", "getActualFormat", "()Lkotlinx/datetime/internal/format/CachedFormatStructure;", "intermediateFromValue", ES6Iterator.VALUE_PROPERTY, "valueFromIntermediate", "intermediate", "emptyIntermediate", "getEmptyIntermediate", "()Lkotlinx/datetime/format/IncompleteUtcOffset;", "Companion", "Builder", "kotlinx-datetime"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class UtcOffsetFormat extends AbstractDateTimeFormat<UtcOffset, IncompleteUtcOffset> {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final CachedFormatStructure<UtcOffsetFieldContainer> actualFormat;

    @Override // kotlinx.datetime.format.AbstractDateTimeFormat
    public CachedFormatStructure<UtcOffsetFieldContainer> getActualFormat() {
        return this.actualFormat;
    }

    /* JADX INFO: compiled from: UtcOffsetFormat.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001f\u0010\u0004\u001a\u00020\u00052\u0017\u0010\u0006\u001a\u0013\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0007¢\u0006\u0002\b\n¨\u0006\u000b"}, d2 = {"Lkotlinx/datetime/format/UtcOffsetFormat$Companion;", "", "<init>", "()V", JsonPOJOBuilder.DEFAULT_BUILD_METHOD, "Lkotlinx/datetime/format/UtcOffsetFormat;", "block", "Lkotlin/Function1;", "Lkotlinx/datetime/format/DateTimeFormatBuilder$WithUtcOffset;", "", "Lkotlin/ExtensionFunctionType;", "kotlinx-datetime"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final UtcOffsetFormat build(Function1<? super DateTimeFormatBuilder.WithUtcOffset, Unit> block) {
            Intrinsics.checkNotNullParameter(block, "block");
            Builder builder = new Builder(new AppendableFormatStructure());
            block.invoke(builder);
            return new UtcOffsetFormat(builder.build());
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public UtcOffsetFormat(CachedFormatStructure<? super UtcOffsetFieldContainer> actualFormat) {
        super(null);
        Intrinsics.checkNotNullParameter(actualFormat, "actualFormat");
        this.actualFormat = actualFormat;
    }

    /* JADX INFO: compiled from: UtcOffsetFormat.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00000\u00012\u00020\u0003B\u0015\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0016\u0010\n\u001a\u00020\u000b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00020\rH\u0016J\b\u0010\u000e\u001a\u00020\u0000H\u0016R\u001a\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\u000f"}, d2 = {"Lkotlinx/datetime/format/UtcOffsetFormat$Builder;", "Lkotlinx/datetime/format/AbstractDateTimeFormatBuilder;", "Lkotlinx/datetime/format/UtcOffsetFieldContainer;", "Lkotlinx/datetime/format/AbstractWithOffsetBuilder;", "actualBuilder", "Lkotlinx/datetime/internal/format/AppendableFormatStructure;", "<init>", "(Lkotlinx/datetime/internal/format/AppendableFormatStructure;)V", "getActualBuilder", "()Lkotlinx/datetime/internal/format/AppendableFormatStructure;", "addFormatStructureForOffset", "", "structure", "Lkotlinx/datetime/internal/format/FormatStructure;", "createEmpty", "kotlinx-datetime"}, k = 1, mv = {2, 1, 0}, xi = 48)
    private static final class Builder implements AbstractDateTimeFormatBuilder<UtcOffsetFieldContainer, Builder>, AbstractWithOffsetBuilder {
        private final AppendableFormatStructure<UtcOffsetFieldContainer> actualBuilder;

        @Override // kotlinx.datetime.format.AbstractDateTimeFormatBuilder
        public /* synthetic */ void appendAlternativeParsingImpl(Function1[] function1Arr, Function1 function1) {
            AbstractDateTimeFormatBuilder.CC.$default$appendAlternativeParsingImpl(this, function1Arr, function1);
        }

        @Override // kotlinx.datetime.format.AbstractDateTimeFormatBuilder
        public /* synthetic */ void appendOptionalImpl(String str, Function1 function1) {
            AbstractDateTimeFormatBuilder.CC.$default$appendOptionalImpl(this, str, function1);
        }

        @Override // kotlinx.datetime.format.AbstractDateTimeFormatBuilder
        public /* synthetic */ CachedFormatStructure<UtcOffsetFieldContainer> build() {
            return AbstractDateTimeFormatBuilder.CC.$default$build(this);
        }

        @Override // kotlinx.datetime.format.AbstractDateTimeFormatBuilder, kotlinx.datetime.format.DateTimeFormatBuilder
        public /* synthetic */ void chars(String str) {
            AbstractDateTimeFormatBuilder.CC.$default$chars(this, str);
        }

        @Override // kotlinx.datetime.format.AbstractWithOffsetBuilder, kotlinx.datetime.format.DateTimeFormatBuilder.WithUtcOffset
        public /* synthetic */ void offset(DateTimeFormat dateTimeFormat) {
            AbstractWithOffsetBuilder.CC.$default$offset(this, dateTimeFormat);
        }

        @Override // kotlinx.datetime.format.AbstractWithOffsetBuilder, kotlinx.datetime.format.DateTimeFormatBuilder.WithUtcOffset
        public /* synthetic */ void offsetHours(Padding padding) {
            AbstractWithOffsetBuilder.CC.$default$offsetHours(this, padding);
        }

        @Override // kotlinx.datetime.format.AbstractWithOffsetBuilder, kotlinx.datetime.format.DateTimeFormatBuilder.WithUtcOffset
        public /* synthetic */ void offsetMinutesOfHour(Padding padding) {
            AbstractWithOffsetBuilder.CC.$default$offsetMinutesOfHour(this, padding);
        }

        @Override // kotlinx.datetime.format.AbstractWithOffsetBuilder, kotlinx.datetime.format.DateTimeFormatBuilder.WithUtcOffset
        public /* synthetic */ void offsetSecondsOfMinute(Padding padding) {
            AbstractWithOffsetBuilder.CC.$default$offsetSecondsOfMinute(this, padding);
        }

        public Builder(AppendableFormatStructure<UtcOffsetFieldContainer> actualBuilder) {
            Intrinsics.checkNotNullParameter(actualBuilder, "actualBuilder");
            this.actualBuilder = actualBuilder;
        }

        @Override // kotlinx.datetime.format.AbstractDateTimeFormatBuilder
        public AppendableFormatStructure<UtcOffsetFieldContainer> getActualBuilder() {
            return this.actualBuilder;
        }

        @Override // kotlinx.datetime.format.AbstractWithOffsetBuilder
        public void addFormatStructureForOffset(FormatStructure<? super UtcOffsetFieldContainer> structure) {
            Intrinsics.checkNotNullParameter(structure, "structure");
            getActualBuilder().add(structure);
        }

        @Override // kotlinx.datetime.format.AbstractDateTimeFormatBuilder
        public Builder createEmpty() {
            return new Builder(new AppendableFormatStructure());
        }
    }

    @Override // kotlinx.datetime.format.AbstractDateTimeFormat
    public IncompleteUtcOffset intermediateFromValue(UtcOffset value) {
        Intrinsics.checkNotNullParameter(value, "value");
        IncompleteUtcOffset incompleteUtcOffset = new IncompleteUtcOffset(null, null, null, null, 15, null);
        incompleteUtcOffset.populateFrom(value);
        return incompleteUtcOffset;
    }

    @Override // kotlinx.datetime.format.AbstractDateTimeFormat
    public UtcOffset valueFromIntermediate(IncompleteUtcOffset intermediate) {
        Intrinsics.checkNotNullParameter(intermediate, "intermediate");
        return intermediate.toUtcOffset();
    }

    @Override // kotlinx.datetime.format.AbstractDateTimeFormat
    public IncompleteUtcOffset getEmptyIntermediate() {
        return UtcOffsetFormatKt.emptyIncompleteUtcOffset;
    }
}
