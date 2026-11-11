package kotlinx.datetime.format;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.datetime.internal.format.UnsignedIntFieldFormatDirective;

/* JADX INFO: compiled from: LocalDateFormat.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\u0013\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0096\u0002J\b\u0010\u000f\u001a\u00020\u0010H\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\u00020\b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\n¨\u0006\u0011"}, d2 = {"Lkotlinx/datetime/format/DayDirective;", "Lkotlinx/datetime/internal/format/UnsignedIntFieldFormatDirective;", "Lkotlinx/datetime/format/DateFieldContainer;", "padding", "Lkotlinx/datetime/format/Padding;", "<init>", "(Lkotlinx/datetime/format/Padding;)V", "builderRepresentation", "", "getBuilderRepresentation", "()Ljava/lang/String;", "equals", "", "other", "", "hashCode", "", "kotlinx-datetime"}, k = 1, mv = {2, 1, 0}, xi = 48)
final class DayDirective extends UnsignedIntFieldFormatDirective<DateFieldContainer> {
    private final Padding padding;

    /* JADX INFO: compiled from: LocalDateFormat.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[Padding.values().length];
            try {
                iArr[Padding.ZERO.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DayDirective(Padding padding) {
        super(DateFields.INSTANCE.getDay(), padding == Padding.ZERO ? 2 : 1, padding == Padding.SPACE ? 2 : null);
        Intrinsics.checkNotNullParameter(padding, "padding");
        this.padding = padding;
    }

    @Override // kotlinx.datetime.internal.format.FieldFormatDirective
    public String getBuilderRepresentation() {
        if (WhenMappings.$EnumSwitchMapping$0[this.padding.ordinal()] == 1) {
            return "day()";
        }
        return "day(" + DateTimeFormatKt.toKotlinCode(this.padding) + ')';
    }

    public boolean equals(Object other) {
        return (other instanceof DayDirective) && this.padding == ((DayDirective) other).padding;
    }

    public int hashCode() {
        return this.padding.hashCode();
    }
}
