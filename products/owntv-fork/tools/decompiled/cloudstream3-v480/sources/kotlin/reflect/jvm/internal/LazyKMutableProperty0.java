package kotlin.reflect.jvm.internal;

import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KMutableProperty0;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: LazyKProperty.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0000\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u000e\b\u0001\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00010\u00032\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\u00042\b\u0012\u0004\u0012\u0002H\u00010\u0003B\u0015\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00010\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u0017\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00028\u0000H\u0096\u0080\u0004¢\u0006\u0002\u0010\u0010R\u001b\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00000\n8VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b\u000b\u0010\f¨\u0006\u0011"}, d2 = {"Lkotlin/reflect/jvm/internal/LazyKMutableProperty0;", ExifInterface.GPS_MEASUREMENT_INTERRUPTED, "D", "Lkotlin/reflect/KMutableProperty0;", "Lkotlin/reflect/jvm/internal/LazyKProperty0;", "computeProperty", "Lkotlin/Function0;", "<init>", "(Lkotlin/jvm/functions/Function0;)V", "setter", "Lkotlin/reflect/KMutableProperty0$Setter;", "getSetter", "()Lkotlin/reflect/KMutableProperty0$Setter;", "set", "", ES6Iterator.VALUE_PROPERTY, "(Ljava/lang/Object;)V", "kotlin-reflection"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class LazyKMutableProperty0<V, D extends KMutableProperty0<V>> extends LazyKProperty0<V, D> implements KMutableProperty0<V> {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LazyKMutableProperty0(Function0<? extends D> computeProperty) {
        super(computeProperty);
        Intrinsics.checkNotNullParameter(computeProperty, "computeProperty");
    }

    @Override // kotlin.reflect.KMutableProperty
    public KMutableProperty0.Setter<V> getSetter() {
        return ((KMutableProperty0) getDelegate()).getSetter();
    }

    @Override // kotlin.reflect.KMutableProperty0
    public void set(V value) {
        ((KMutableProperty0) getDelegate()).set(value);
    }
}
