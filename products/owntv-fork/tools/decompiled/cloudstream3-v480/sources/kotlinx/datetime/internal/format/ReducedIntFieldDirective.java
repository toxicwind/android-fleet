package kotlinx.datetime.internal.format;

import androidx.media3.extractor.text.ttml.TtmlNode;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.datetime.internal.format.formatter.FormatterStructure;
import kotlinx.datetime.internal.format.formatter.ReducedIntFormatterStructure;
import kotlinx.datetime.internal.format.parser.ParserOperationKt;
import kotlinx.datetime.internal.format.parser.ParserStructure;

/* JADX INFO: compiled from: FieldFormatDirective.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b \u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00002\b\u0012\u0004\u0012\u0002H\u00010\u0002B+\u0012\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005¢\u0006\u0004\b\b\u0010\tJ\u000e\u0010\f\u001a\b\u0012\u0004\u0012\u00028\u00000\rH\u0016J\u000e\u0010\u000e\u001a\b\u0012\u0004\u0012\u00028\u00000\u000fH\u0016R\u001d\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u000e\u0010\u0006\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lkotlinx/datetime/internal/format/ReducedIntFieldDirective;", "Target", "Lkotlinx/datetime/internal/format/FieldFormatDirective;", "field", "Lkotlinx/datetime/internal/format/FieldSpec;", "", "digits", TtmlNode.RUBY_BASE, "<init>", "(Lkotlinx/datetime/internal/format/FieldSpec;II)V", "getField", "()Lkotlinx/datetime/internal/format/FieldSpec;", "formatter", "Lkotlinx/datetime/internal/format/formatter/FormatterStructure;", "parser", "Lkotlinx/datetime/internal/format/parser/ParserStructure;", "kotlinx-datetime"}, k = 1, mv = {2, 1, 0}, xi = 48)
public abstract class ReducedIntFieldDirective<Target> implements FieldFormatDirective<Target> {
    private final int base;
    private final int digits;
    private final FieldSpec<Target, Integer> field;

    /* JADX WARN: Multi-variable type inference failed */
    public ReducedIntFieldDirective(FieldSpec<? super Target, Integer> field, int i, int i2) {
        Intrinsics.checkNotNullParameter(field, "field");
        this.field = field;
        this.digits = i;
        this.base = i2;
    }

    @Override // kotlinx.datetime.internal.format.FieldFormatDirective
    public final FieldSpec<Target, Integer> getField() {
        return this.field;
    }

    /* JADX INFO: renamed from: kotlinx.datetime.internal.format.ReducedIntFieldDirective$formatter$1, reason: invalid class name */
    /* JADX INFO: compiled from: FieldFormatDirective.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function1<Target, Integer> {
        AnonymousClass1(Object obj) {
            super(1, obj, Accessor.class, "getterNotNull", "getterNotNull(Ljava/lang/Object;)Ljava/lang/Object;", 0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function1
        public final Integer invoke(Target target) {
            return (Integer) ((Accessor) this.receiver).getterNotNull(target);
        }
    }

    @Override // kotlinx.datetime.internal.format.FieldFormatDirective
    public FormatterStructure<Target> formatter() {
        return new ReducedIntFormatterStructure(new AnonymousClass1(this.field.getAccessor()), this.digits, this.base);
    }

    @Override // kotlinx.datetime.internal.format.FieldFormatDirective
    public ParserStructure<Target> parser() {
        return ParserOperationKt.ReducedIntParser(this.digits, this.base, this.field.getAccessor(), this.field.getName());
    }
}
