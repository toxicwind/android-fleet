package kotlin.reflect.jvm.internal;

import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KProperty1;

/* JADX INFO: compiled from: LazyKProperty.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0000\n\u0002\b\u0002\b\u0010\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0006\b\u0001\u0010\u0002 \u0001*\u0016\b\u0002\u0010\u0003 \u0001*\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\u00042\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00052\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\u0004B\u0015\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\u0017\u0010\u000e\u001a\u00028\u00012\u0006\u0010\u000f\u001a\u00028\u0000H\u0096\u0080\u0004¢\u0006\u0002\u0010\u0010J\u0019\u0010\u0011\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u000f\u001a\u00028\u0000H\u0096\u0080\u0004¢\u0006\u0002\u0010\u0010J\u0017\u0010\u0013\u001a\u00028\u00012\u0006\u0010\u000f\u001a\u00028\u0000H\u0096\u0082\u0004¢\u0006\u0002\u0010\u0010R!\u0010\n\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u000b8VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b\f\u0010\r¨\u0006\u0014"}, d2 = {"Lkotlin/reflect/jvm/internal/LazyKProperty1;", ExifInterface.GPS_DIRECTION_TRUE, ExifInterface.GPS_MEASUREMENT_INTERRUPTED, "D", "Lkotlin/reflect/KProperty1;", "Lkotlin/reflect/jvm/internal/LazyKProperty;", "computeProperty", "Lkotlin/Function0;", "<init>", "(Lkotlin/jvm/functions/Function0;)V", "getter", "Lkotlin/reflect/KProperty1$Getter;", "getGetter", "()Lkotlin/reflect/KProperty1$Getter;", "get", "receiver", "(Ljava/lang/Object;)Ljava/lang/Object;", "getDelegate", "", "invoke", "kotlin-reflection"}, k = 1, mv = {2, 3, 0}, xi = 48)
public class LazyKProperty1<T, V, D extends KProperty1<T, ? extends V>> extends LazyKProperty<V, D> implements KProperty1<T, V> {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LazyKProperty1(Function0<? extends D> computeProperty) {
        super(computeProperty);
        Intrinsics.checkNotNullParameter(computeProperty, "computeProperty");
    }

    @Override // kotlin.reflect.KProperty
    public KProperty1.Getter<T, V> getGetter() {
        return getDelegate().getGetter();
    }

    @Override // kotlin.reflect.KProperty1
    public V get(T receiver) {
        return (V) getDelegate().get(receiver);
    }

    @Override // kotlin.reflect.KProperty1
    public Object getDelegate(T receiver) {
        return getDelegate().getDelegate(receiver);
    }

    @Override // kotlin.jvm.functions.Function1
    public V invoke(T receiver) {
        return (V) getDelegate().invoke(receiver);
    }
}
