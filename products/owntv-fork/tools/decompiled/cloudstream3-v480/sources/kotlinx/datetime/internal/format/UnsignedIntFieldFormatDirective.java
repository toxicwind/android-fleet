package kotlinx.datetime.internal.format;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.datetime.internal.format.formatter.FormatterStructure;
import kotlinx.datetime.internal.format.formatter.SpacePaddedFormatter;
import kotlinx.datetime.internal.format.formatter.UnsignedIntFormatterStructure;
import kotlinx.datetime.internal.format.parser.ParserOperationKt;
import kotlinx.datetime.internal.format.parser.ParserStructure;

/* JADX INFO: compiled from: FieldFormatDirective.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b \u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00002\b\u0012\u0004\u0012\u0002H\u00010\u0002B'\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006¢\u0006\u0004\b\b\u0010\tJ\u000e\u0010\u000e\u001a\b\u0012\u0004\u0012\u00028\u00000\u000fH\u0016J\u000e\u0010\u0010\u001a\b\u0012\u0004\u0012\u00028\u00000\u0011H\u0016R\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u0007\u001a\u0004\u0018\u00010\u0006X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\fR\u000e\u0010\r\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lkotlinx/datetime/internal/format/UnsignedIntFieldFormatDirective;", "Target", "Lkotlinx/datetime/internal/format/FieldFormatDirective;", "field", "Lkotlinx/datetime/internal/format/UnsignedFieldSpec;", "minDigits", "", "spacePadding", "<init>", "(Lkotlinx/datetime/internal/format/UnsignedFieldSpec;ILjava/lang/Integer;)V", "getField", "()Lkotlinx/datetime/internal/format/UnsignedFieldSpec;", "Ljava/lang/Integer;", "maxDigits", "formatter", "Lkotlinx/datetime/internal/format/formatter/FormatterStructure;", "parser", "Lkotlinx/datetime/internal/format/parser/ParserStructure;", "kotlinx-datetime"}, k = 1, mv = {2, 1, 0}, xi = 48)
public abstract class UnsignedIntFieldFormatDirective<Target> implements FieldFormatDirective<Target> {
    private final UnsignedFieldSpec<Target> field;
    private final int maxDigits;
    private final int minDigits;
    private final Integer spacePadding;

    /* JADX WARN: Multi-variable type inference failed */
    public UnsignedIntFieldFormatDirective(UnsignedFieldSpec<? super Target> field, int i, Integer num) {
        Intrinsics.checkNotNullParameter(field, "field");
        this.field = field;
        this.minDigits = i;
        this.spacePadding = num;
        int maxDigits = field.getMaxDigits();
        this.maxDigits = maxDigits;
        if (i < 0) {
            throw new IllegalArgumentException(("The minimum number of digits (" + i + ") is negative").toString());
        }
        if (maxDigits < i) {
            throw new IllegalArgumentException(("The maximum number of digits (" + maxDigits + ") is less than the minimum number of digits (" + i + ')').toString());
        }
        if (num == null || num.intValue() > i) {
            return;
        }
        throw new IllegalArgumentException(("The space padding (" + num + ") should be more than the minimum number of digits (" + i + ')').toString());
    }

    @Override // kotlinx.datetime.internal.format.FieldFormatDirective
    public /* bridge */ /* synthetic */ FieldSpec getField() {
        return this.field;
    }

    @Override // kotlinx.datetime.internal.format.FieldFormatDirective
    public final UnsignedFieldSpec<Target> getField() {
        return this.field;
    }

    @Override // kotlinx.datetime.internal.format.FieldFormatDirective
    public FormatterStructure<Target> formatter() {
        UnsignedIntFormatterStructure unsignedIntFormatterStructure = new UnsignedIntFormatterStructure(new UnsignedIntFieldFormatDirective$formatter$formatter$1(this.field.getAccessor()), this.minDigits);
        return this.spacePadding != null ? new SpacePaddedFormatter(unsignedIntFormatterStructure, this.spacePadding.intValue()) : unsignedIntFormatterStructure;
    }

    @Override // kotlinx.datetime.internal.format.FieldFormatDirective
    public ParserStructure<Target> parser() {
        return ParserOperationKt.spaceAndZeroPaddedUnsignedInt$default(Integer.valueOf(this.minDigits), Integer.valueOf(this.maxDigits), this.spacePadding, this.field.getAccessor(), this.field.getName(), false, 32, null);
    }
}
