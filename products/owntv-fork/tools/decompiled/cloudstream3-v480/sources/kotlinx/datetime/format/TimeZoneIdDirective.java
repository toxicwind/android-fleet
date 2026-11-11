package kotlinx.datetime.format;

import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.datetime.internal.format.Accessor;
import kotlinx.datetime.internal.format.FieldFormatDirective;
import kotlinx.datetime.internal.format.FieldSpec;
import kotlinx.datetime.internal.format.formatter.FormatterStructure;
import kotlinx.datetime.internal.format.formatter.StringFormatterStructure;
import kotlinx.datetime.internal.format.parser.ParserStructure;
import kotlinx.datetime.internal.format.parser.TimeZoneParserOperation;

/* JADX INFO: compiled from: DateTimeComponents.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\u000e\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00020\u000eH\u0016J\u000e\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00020\u0010H\u0016R \u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00070\u00068VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u0014\u0010\n\u001a\u00020\u00078VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\f¨\u0006\u0011"}, d2 = {"Lkotlinx/datetime/format/TimeZoneIdDirective;", "Lkotlinx/datetime/internal/format/FieldFormatDirective;", "Lkotlinx/datetime/format/DateTimeComponentsContents;", "<init>", "()V", "field", "Lkotlinx/datetime/internal/format/FieldSpec;", "", "getField", "()Lkotlinx/datetime/internal/format/FieldSpec;", "builderRepresentation", "getBuilderRepresentation", "()Ljava/lang/String;", "formatter", "Lkotlinx/datetime/internal/format/formatter/FormatterStructure;", "parser", "Lkotlinx/datetime/internal/format/parser/ParserStructure;", "kotlinx-datetime"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class TimeZoneIdDirective implements FieldFormatDirective<DateTimeComponentsContents> {
    @Override // kotlinx.datetime.internal.format.FieldFormatDirective
    public FieldSpec<DateTimeComponentsContents, ?> getField() {
        return DateTimeComponentsKt.getTimeZoneField();
    }

    @Override // kotlinx.datetime.internal.format.FieldFormatDirective
    public String getBuilderRepresentation() {
        return "timeZoneId()";
    }

    /* JADX INFO: renamed from: kotlinx.datetime.format.TimeZoneIdDirective$formatter$1, reason: invalid class name */
    /* JADX INFO: compiled from: DateTimeComponents.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function1<DateTimeComponentsContents, String> {
        AnonymousClass1(Object obj) {
            super(1, obj, Accessor.class, "getterNotNull", "getterNotNull(Ljava/lang/Object;)Ljava/lang/Object;", 0);
        }

        @Override // kotlin.jvm.functions.Function1
        public final String invoke(DateTimeComponentsContents p0) {
            Intrinsics.checkNotNullParameter(p0, "p0");
            return (String) ((Accessor) this.receiver).getterNotNull(p0);
        }
    }

    @Override // kotlinx.datetime.internal.format.FieldFormatDirective
    public FormatterStructure<DateTimeComponentsContents> formatter() {
        return new StringFormatterStructure(new AnonymousClass1(getField().getAccessor()));
    }

    @Override // kotlinx.datetime.internal.format.FieldFormatDirective
    public ParserStructure<DateTimeComponentsContents> parser() {
        return new ParserStructure<>(CollectionsKt.listOf(new TimeZoneParserOperation(DateTimeComponentsKt.getTimeZoneField().getAccessor())), CollectionsKt.emptyList());
    }
}
