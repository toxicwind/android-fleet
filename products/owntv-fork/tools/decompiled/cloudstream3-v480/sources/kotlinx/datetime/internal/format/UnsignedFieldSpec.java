package kotlinx.datetime.internal.format;

import com.google.android.gms.cast.MediaTrack;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: FieldSpec.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0011\b\u0000\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00002\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u00020\u00030\u0002BS\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00030\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0003\u0012\u0010\b\u0002\u0010\u000b\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\f¢\u0006\u0004\b\r\u0010\u000eR \u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00030\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0012R\u0014\u0010\b\u001a\u00020\tX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0018\u0010\n\u001a\u0004\u0018\u00010\u0003X\u0096\u0004¢\u0006\n\n\u0002\u0010\u0018\u001a\u0004\b\u0016\u0010\u0017R\u001c\u0010\u000b\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\fX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0011\u0010\u001b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0012¨\u0006\u001d"}, d2 = {"Lkotlinx/datetime/internal/format/UnsignedFieldSpec;", "Target", "Lkotlinx/datetime/internal/format/AbstractFieldSpec;", "", "accessor", "Lkotlinx/datetime/internal/format/Accessor;", "minValue", "maxValue", "name", "", "defaultValue", MediaTrack.ROLE_SIGN, "Lkotlinx/datetime/internal/format/FieldSign;", "<init>", "(Lkotlinx/datetime/internal/format/Accessor;IILjava/lang/String;Ljava/lang/Integer;Lkotlinx/datetime/internal/format/FieldSign;)V", "getAccessor", "()Lkotlinx/datetime/internal/format/Accessor;", "getMinValue", "()I", "getMaxValue", "getName", "()Ljava/lang/String;", "getDefaultValue", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getSign", "()Lkotlinx/datetime/internal/format/FieldSign;", "maxDigits", "getMaxDigits", "kotlinx-datetime"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class UnsignedFieldSpec<Target> extends AbstractFieldSpec<Target, Integer> {
    private final Accessor<Target, Integer> accessor;
    private final Integer defaultValue;
    private final int maxDigits;
    private final int maxValue;
    private final int minValue;
    private final String name;
    private final FieldSign<Target> sign;

    @Override // kotlinx.datetime.internal.format.FieldSpec
    public Accessor<Target, Integer> getAccessor() {
        return this.accessor;
    }

    public final int getMinValue() {
        return this.minValue;
    }

    public final int getMaxValue() {
        return this.maxValue;
    }

    public /* synthetic */ UnsignedFieldSpec(Accessor accessor, int i, int i2, String str, Integer num, FieldSign fieldSign, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(accessor, i, i2, (i3 & 8) != 0 ? accessor.getName() : str, (i3 & 16) != 0 ? null : num, (i3 & 32) != 0 ? null : fieldSign);
    }

    @Override // kotlinx.datetime.internal.format.FieldSpec
    public String getName() {
        return this.name;
    }

    @Override // kotlinx.datetime.internal.format.FieldSpec
    public Integer getDefaultValue() {
        return this.defaultValue;
    }

    @Override // kotlinx.datetime.internal.format.FieldSpec
    public FieldSign<Target> getSign() {
        return this.sign;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public UnsignedFieldSpec(Accessor<? super Target, Integer> accessor, int i, int i2, String name, Integer num, FieldSign<? super Target> fieldSign) {
        int i3;
        Intrinsics.checkNotNullParameter(accessor, "accessor");
        Intrinsics.checkNotNullParameter(name, "name");
        this.accessor = accessor;
        this.minValue = i;
        this.maxValue = i2;
        this.name = name;
        this.defaultValue = num;
        this.sign = fieldSign;
        if (i2 < 10) {
            i3 = 1;
        } else if (i2 < 100) {
            i3 = 2;
        } else {
            if (i2 >= 1000) {
                throw new IllegalArgumentException("Max value " + i2 + " is too large");
            }
            i3 = 3;
        }
        this.maxDigits = i3;
    }

    public final int getMaxDigits() {
        return this.maxDigits;
    }
}
