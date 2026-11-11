package kotlinx.datetime.internal.format;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.media3.extractor.text.ttml.TtmlNode;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.datetime.internal.format.formatter.FormatterStructure;
import kotlinx.datetime.internal.format.formatter.StringFormatterStructure;
import kotlinx.datetime.internal.format.parser.AssignableField;
import kotlinx.datetime.internal.format.parser.ParserStructure;
import kotlinx.datetime.internal.format.parser.StringSetParserOperation;

/* JADX INFO: compiled from: FieldFormatDirective.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b \u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00002\b\u0012\u0004\u0012\u0002H\u00010\u0002:\u0001\u0014B+\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0006\u0010\b\u001a\u00020\u0007¢\u0006\u0004\b\t\u0010\nJ\u0015\u0010\r\u001a\u00020\u00072\u0006\u0010\u000e\u001a\u00028\u0000H\u0002¢\u0006\u0002\u0010\u000fJ\u000e\u0010\u0010\u001a\b\u0012\u0004\u0012\u00028\u00000\u0011H\u0016J\u000e\u0010\u0012\u001a\b\u0012\u0004\u0012\u00028\u00000\u0013H\u0016R\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lkotlinx/datetime/internal/format/NamedUnsignedIntFieldFormatDirective;", "Target", "Lkotlinx/datetime/internal/format/FieldFormatDirective;", "field", "Lkotlinx/datetime/internal/format/UnsignedFieldSpec;", "values", "", "", "name", "<init>", "(Lkotlinx/datetime/internal/format/UnsignedFieldSpec;Ljava/util/List;Ljava/lang/String;)V", "getField", "()Lkotlinx/datetime/internal/format/UnsignedFieldSpec;", "getStringValue", TypedValues.AttributesType.S_TARGET, "(Ljava/lang/Object;)Ljava/lang/String;", "formatter", "Lkotlinx/datetime/internal/format/formatter/FormatterStructure;", "parser", "Lkotlinx/datetime/internal/format/parser/ParserStructure;", "AssignableString", "kotlinx-datetime"}, k = 1, mv = {2, 1, 0}, xi = 48)
public abstract class NamedUnsignedIntFieldFormatDirective<Target> implements FieldFormatDirective<Target> {
    private final UnsignedFieldSpec<Target> field;
    private final String name;
    private final List<String> values;

    /* JADX WARN: Multi-variable type inference failed */
    public NamedUnsignedIntFieldFormatDirective(UnsignedFieldSpec<? super Target> field, List<String> values, String name) {
        Intrinsics.checkNotNullParameter(field, "field");
        Intrinsics.checkNotNullParameter(values, "values");
        Intrinsics.checkNotNullParameter(name, "name");
        this.field = field;
        this.values = values;
        this.name = name;
        if (values.size() == (field.getMaxValue() - field.getMinValue()) + 1) {
            return;
        }
        throw new IllegalArgumentException(("The number of values (" + values.size() + ") in " + values + " does not match the range of the field (" + ((field.getMaxValue() - field.getMinValue()) + 1) + ')').toString());
    }

    @Override // kotlinx.datetime.internal.format.FieldFormatDirective
    public /* bridge */ /* synthetic */ FieldSpec getField() {
        return this.field;
    }

    @Override // kotlinx.datetime.internal.format.FieldFormatDirective
    public final UnsignedFieldSpec<Target> getField() {
        return this.field;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String getStringValue(Target target) {
        int iIntValue = this.field.getAccessor().getterNotNull(target).intValue();
        String str = (String) CollectionsKt.getOrNull(this.values, iIntValue - this.field.getMinValue());
        if (str != null) {
            return str;
        }
        return "The value " + iIntValue + " of " + this.field.getName() + " does not have a corresponding string representation";
    }

    /* JADX INFO: compiled from: FieldFormatDirective.kt */
    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\n\b\u0082\u0004\u0018\u00002\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00020\u0001B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\u001f\u0010\u0005\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0006\u001a\u00028\u00002\u0006\u0010\u0007\u001a\u00020\u0002H\u0016¢\u0006\u0002\u0010\bR\u0014\u0010\t\u001a\u00020\u00028VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Lkotlinx/datetime/internal/format/NamedUnsignedIntFieldFormatDirective$AssignableString;", "Lkotlinx/datetime/internal/format/parser/AssignableField;", "", "<init>", "(Lkotlinx/datetime/internal/format/NamedUnsignedIntFieldFormatDirective;)V", "trySetWithoutReassigning", TtmlNode.RUBY_CONTAINER, "newValue", "(Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/String;", "name", "getName", "()Ljava/lang/String;", "kotlinx-datetime"}, k = 1, mv = {2, 1, 0}, xi = 48)
    private final class AssignableString implements AssignableField<Target, String> {
        public AssignableString() {
        }

        @Override // kotlinx.datetime.internal.format.parser.AssignableField
        public String trySetWithoutReassigning(Target container, String newValue) {
            Intrinsics.checkNotNullParameter(newValue, "newValue");
            Integer numTrySetWithoutReassigning = NamedUnsignedIntFieldFormatDirective.this.getField().getAccessor().trySetWithoutReassigning(container, Integer.valueOf(((NamedUnsignedIntFieldFormatDirective) NamedUnsignedIntFieldFormatDirective.this).values.indexOf(newValue) + NamedUnsignedIntFieldFormatDirective.this.getField().getMinValue()));
            if (numTrySetWithoutReassigning == null) {
                return null;
            }
            NamedUnsignedIntFieldFormatDirective<Target> namedUnsignedIntFieldFormatDirective = NamedUnsignedIntFieldFormatDirective.this;
            return (String) ((NamedUnsignedIntFieldFormatDirective) namedUnsignedIntFieldFormatDirective).values.get(numTrySetWithoutReassigning.intValue() - namedUnsignedIntFieldFormatDirective.getField().getMinValue());
        }

        @Override // kotlinx.datetime.internal.format.parser.AssignableField
        public String getName() {
            return ((NamedUnsignedIntFieldFormatDirective) NamedUnsignedIntFieldFormatDirective.this).name;
        }
    }

    /* JADX INFO: renamed from: kotlinx.datetime.internal.format.NamedUnsignedIntFieldFormatDirective$formatter$1, reason: invalid class name */
    /* JADX INFO: compiled from: FieldFormatDirective.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function1<Target, String> {
        AnonymousClass1(Object obj) {
            super(1, obj, NamedUnsignedIntFieldFormatDirective.class, "getStringValue", "getStringValue(Ljava/lang/Object;)Ljava/lang/String;", 0);
        }

        @Override // kotlin.jvm.functions.Function1
        public final String invoke(Target target) {
            return ((NamedUnsignedIntFieldFormatDirective) this.receiver).getStringValue(target);
        }
    }

    @Override // kotlinx.datetime.internal.format.FieldFormatDirective
    public FormatterStructure<Target> formatter() {
        return new StringFormatterStructure(new AnonymousClass1(this));
    }

    @Override // kotlinx.datetime.internal.format.FieldFormatDirective
    public ParserStructure<Target> parser() {
        return new ParserStructure<>(CollectionsKt.listOf(new StringSetParserOperation(this.values, new AssignableString(), "one of " + this.values + " for " + this.name)), CollectionsKt.emptyList());
    }
}
