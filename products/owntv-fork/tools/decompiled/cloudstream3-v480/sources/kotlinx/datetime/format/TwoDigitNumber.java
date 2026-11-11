package kotlinx.datetime.format;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import kotlin.reflect.KMutableProperty0;
import kotlin.reflect.KProperty;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: DateTimeComponents.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0002\u0018\u00002\u00020\u0001B\u0017\u0012\u000e\u0010\u0002\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u0003¢\u0006\u0004\b\u0005\u0010\u0006J&\u0010\u0007\u001a\u0004\u0018\u00010\u00042\b\u0010\b\u001a\u0004\u0018\u00010\u00012\n\u0010\t\u001a\u0006\u0012\u0002\b\u00030\nH\u0086\u0002¢\u0006\u0002\u0010\u000bJ.\u0010\f\u001a\u00020\r2\b\u0010\b\u001a\u0004\u0018\u00010\u00012\n\u0010\t\u001a\u0006\u0012\u0002\b\u00030\n2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0004H\u0086\u0002¢\u0006\u0002\u0010\u000fR\u0016\u0010\u0002\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lkotlinx/datetime/format/TwoDigitNumber;", "", TypedValues.Custom.S_REFERENCE, "Lkotlin/reflect/KMutableProperty0;", "", "<init>", "(Lkotlin/reflect/KMutableProperty0;)V", "getValue", "thisRef", "property", "Lkotlin/reflect/KProperty;", "(Ljava/lang/Object;Lkotlin/reflect/KProperty;)Ljava/lang/Integer;", "setValue", "", ES6Iterator.VALUE_PROPERTY, "(Ljava/lang/Object;Lkotlin/reflect/KProperty;Ljava/lang/Integer;)V", "kotlinx-datetime"}, k = 1, mv = {2, 1, 0}, xi = 48)
final class TwoDigitNumber {
    private final KMutableProperty0<Integer> reference;

    public TwoDigitNumber(KMutableProperty0<Integer> reference) {
        Intrinsics.checkNotNullParameter(reference, "reference");
        this.reference = reference;
    }

    public final Integer getValue(Object thisRef, KProperty<?> property) {
        Intrinsics.checkNotNullParameter(property, "property");
        return this.reference.get();
    }

    public final void setValue(Object thisRef, KProperty<?> property, Integer value) {
        Intrinsics.checkNotNullParameter(property, "property");
        if (value == null || new IntRange(0, 99).contains(value.intValue())) {
            this.reference.set(value);
            return;
        }
        throw new IllegalArgumentException((property.getName() + " must be a two-digit number, got '" + value + '\'').toString());
    }
}
