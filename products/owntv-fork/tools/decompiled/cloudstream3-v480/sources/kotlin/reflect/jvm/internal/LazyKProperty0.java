package kotlin.reflect.jvm.internal;

import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KProperty0;

/* JADX INFO: compiled from: LazyKProperty.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0000\n\u0002\b\u0002\b\u0010\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u0001*\u0010\b\u0001\u0010\u0002 \u0001*\b\u0012\u0004\u0012\u0002H\u00010\u00032\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\u00042\b\u0012\u0004\u0012\u0002H\u00010\u0003B\u0015\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00010\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u000f\u0010\r\u001a\u00028\u0000H\u0096\u0080\u0004¢\u0006\u0002\u0010\u000eJ\f\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0096\u0080\u0004J\u000f\u0010\u0011\u001a\u00028\u0000H\u0096\u0082\u0004¢\u0006\u0002\u0010\u000eR\u001b\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00000\n8VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b\u000b\u0010\f¨\u0006\u0012"}, d2 = {"Lkotlin/reflect/jvm/internal/LazyKProperty0;", ExifInterface.GPS_MEASUREMENT_INTERRUPTED, "D", "Lkotlin/reflect/KProperty0;", "Lkotlin/reflect/jvm/internal/LazyKProperty;", "computeProperty", "Lkotlin/Function0;", "<init>", "(Lkotlin/jvm/functions/Function0;)V", "getter", "Lkotlin/reflect/KProperty0$Getter;", "getGetter", "()Lkotlin/reflect/KProperty0$Getter;", "get", "()Ljava/lang/Object;", "getDelegate", "", "invoke", "kotlin-reflection"}, k = 1, mv = {2, 3, 0}, xi = 48)
public class LazyKProperty0<V, D extends KProperty0<? extends V>> extends LazyKProperty<V, D> implements KProperty0<V> {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LazyKProperty0(Function0<? extends D> computeProperty) {
        super(computeProperty);
        Intrinsics.checkNotNullParameter(computeProperty, "computeProperty");
    }

    @Override // kotlin.reflect.KProperty
    public KProperty0.Getter<V> getGetter() {
        return ((KProperty0) getDelegate()).getGetter();
    }

    @Override // kotlin.reflect.KProperty0
    public V get() {
        return (V) ((KProperty0) getDelegate()).get();
    }

    @Override // kotlin.reflect.KProperty0
    public Object getDelegate() {
        return ((KProperty0) getDelegate()).getDelegate();
    }

    @Override // kotlin.jvm.functions.Function0
    public V invoke() {
        return ((KProperty0) getDelegate()).invoke();
    }
}
