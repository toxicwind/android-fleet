package kotlinx.datetime.format;

import androidx.media3.extractor.text.ttml.TtmlNode;
import kotlin.Metadata;
import kotlin.UByte$$ExternalSyntheticBackport0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlinx.datetime.internal.format.ReducedIntFieldDirective;

/* JADX INFO: compiled from: YearMonthFormat.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0002\b\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0019\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u0013\u0010\u000f\u001a\u00020\u00062\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0096\u0002J\b\u0010\u0012\u001a\u00020\u0004H\u0016R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\u00020\f8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000e¨\u0006\u0013"}, d2 = {"Lkotlinx/datetime/format/ReducedYearDirective;", "Lkotlinx/datetime/internal/format/ReducedIntFieldDirective;", "Lkotlinx/datetime/format/YearMonthFieldContainer;", TtmlNode.RUBY_BASE, "", "isYearOfEra", "", "<init>", "(IZ)V", "getBase", "()I", "builderRepresentation", "", "getBuilderRepresentation", "()Ljava/lang/String;", "equals", "other", "", "hashCode", "kotlinx-datetime"}, k = 1, mv = {2, 1, 0}, xi = 48)
final class ReducedYearDirective extends ReducedIntFieldDirective<YearMonthFieldContainer> {
    private final int base;
    private final boolean isYearOfEra;

    public /* synthetic */ ReducedYearDirective(int i, boolean z, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, (i2 & 2) != 0 ? false : z);
    }

    public final int getBase() {
        return this.base;
    }

    public ReducedYearDirective(int i, boolean z) {
        super(YearMonthFields.INSTANCE.getYear(), 2, i);
        this.base = i;
        this.isYearOfEra = z;
    }

    @Override // kotlinx.datetime.internal.format.FieldFormatDirective
    public String getBuilderRepresentation() {
        String str = "yearTwoDigits(" + this.base + ')';
        if (!this.isYearOfEra) {
            return str;
        }
        return str + " /** TODO: the original format had an `y` directive, so the behavior is different on years earlier than 1 AD. See the [kotlinx.datetime.format.byUnicodePattern] documentation for details. */";
    }

    public boolean equals(Object other) {
        if (!(other instanceof ReducedYearDirective)) {
            return false;
        }
        ReducedYearDirective reducedYearDirective = (ReducedYearDirective) other;
        return this.base == reducedYearDirective.base && this.isYearOfEra == reducedYearDirective.isYearOfEra;
    }

    public int hashCode() {
        return (this.base * 31) + UByte$$ExternalSyntheticBackport0.m(this.isYearOfEra);
    }
}
