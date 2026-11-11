package kotlin.reflect.jvm.internal;

import androidx.core.app.NotificationCompat;
import androidx.exifinterface.media.ExifInterface;
import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KParameter;
import kotlin.reflect.KProperty;
import kotlin.reflect.KType;
import kotlin.reflect.KTypeParameter;
import kotlin.reflect.KVisibility;

/* JADX INFO: compiled from: LazyKProperty.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\u001b\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\b \u0018\u0000*\u0006\b\u0000\u0010\u0001 \u0001*\u0010\b\u0001\u0010\u0002 \u0001*\b\u0012\u0004\u0012\u0002H\u00010\u00032\b\u0012\u0004\u0012\u0002H\u00010\u0003B\u0015\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007J'\u0010\u001d\u001a\u00028\u00002\u0016\u0010\u001e\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010 0\u001f\"\u0004\u0018\u00010 H\u0096\u0080\u0004¢\u0006\u0002\u0010!J%\u0010\"\u001a\u00028\u00002\u0014\u0010\u001e\u001a\u0010\u0012\u0004\u0012\u00020\u0013\u0012\u0006\u0012\u0004\u0018\u00010 0#H\u0096\u0080\u0004¢\u0006\u0002\u0010$J\u0014\u00104\u001a\u00020*2\b\u00105\u001a\u0004\u0018\u00010 H\u0096\u0082\u0004J\n\u00106\u001a\u000207H\u0096\u0080\u0004J\n\u00108\u001a\u00020\u000eH\u0096\u0080\u0004R\u001b\u0010\b\u001a\u00028\u00018FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\t\u0010\nR\u0015\u0010\r\u001a\u00020\u000e8VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010R\u001b\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u00128VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015R\u0015\u0010\u0016\u001a\u00020\u00178VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0019R\u001b\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001b0\u00128VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u0015R\u0017\u0010%\u001a\u0004\u0018\u00010&8VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b'\u0010(R\u0015\u0010)\u001a\u00020*8VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b)\u0010+R\u0015\u0010,\u001a\u00020*8VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b,\u0010+R\u0015\u0010-\u001a\u00020*8VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b-\u0010+R\u0015\u0010.\u001a\u00020*8VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b.\u0010+R\u0015\u0010/\u001a\u00020*8VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b/\u0010+R\u0015\u00100\u001a\u00020*8VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b0\u0010+R\u001b\u00101\u001a\b\u0012\u0004\u0012\u0002020\u00128VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b3\u0010\u0015¨\u00069"}, d2 = {"Lkotlin/reflect/jvm/internal/LazyKProperty;", ExifInterface.GPS_MEASUREMENT_INTERRUPTED, "D", "Lkotlin/reflect/KProperty;", "computeProperty", "Lkotlin/Function0;", "<init>", "(Lkotlin/jvm/functions/Function0;)V", "delegate", "getDelegate", "()Lkotlin/reflect/KProperty;", "delegate$delegate", "Lkotlin/Lazy;", "name", "", "getName", "()Ljava/lang/String;", "parameters", "", "Lkotlin/reflect/KParameter;", "getParameters", "()Ljava/util/List;", "returnType", "Lkotlin/reflect/KType;", "getReturnType", "()Lkotlin/reflect/KType;", "typeParameters", "Lkotlin/reflect/KTypeParameter;", "getTypeParameters", NotificationCompat.CATEGORY_CALL, "args", "", "", "([Ljava/lang/Object;)Ljava/lang/Object;", "callBy", "", "(Ljava/util/Map;)Ljava/lang/Object;", "visibility", "Lkotlin/reflect/KVisibility;", "getVisibility", "()Lkotlin/reflect/KVisibility;", "isFinal", "", "()Z", "isOpen", "isAbstract", "isSuspend", "isLateinit", "isConst", "annotations", "", "getAnnotations", "equals", "other", "hashCode", "", "toString", "kotlin-reflection"}, k = 1, mv = {2, 3, 0}, xi = 48)
public abstract class LazyKProperty<V, D extends KProperty<? extends V>> implements KProperty<V> {

    /* JADX INFO: renamed from: delegate$delegate, reason: from kotlin metadata */
    private final Lazy delegate;

    public LazyKProperty(Function0<? extends D> computeProperty) {
        Intrinsics.checkNotNullParameter(computeProperty, "computeProperty");
        this.delegate = LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, (Function0) computeProperty);
    }

    public final D getDelegate() {
        return (D) this.delegate.getValue();
    }

    @Override // kotlin.reflect.KCallable
    public String getName() {
        return getDelegate().getName();
    }

    @Override // kotlin.reflect.KCallable
    public List<KParameter> getParameters() {
        return getDelegate().getParameters();
    }

    @Override // kotlin.reflect.KCallable
    public KType getReturnType() {
        return getDelegate().getReturnType();
    }

    @Override // kotlin.reflect.KCallable
    public List<KTypeParameter> getTypeParameters() {
        return getDelegate().getTypeParameters();
    }

    @Override // kotlin.reflect.KCallable
    public V call(Object... args) {
        Intrinsics.checkNotNullParameter(args, "args");
        return getDelegate().call(Arrays.copyOf(args, args.length));
    }

    @Override // kotlin.reflect.KCallable
    public V callBy(Map<KParameter, ? extends Object> args) {
        Intrinsics.checkNotNullParameter(args, "args");
        return getDelegate().callBy(args);
    }

    @Override // kotlin.reflect.KCallable
    public KVisibility getVisibility() {
        return getDelegate().getVisibility();
    }

    @Override // kotlin.reflect.KCallable
    public boolean isFinal() {
        return getDelegate().isFinal();
    }

    @Override // kotlin.reflect.KCallable
    public boolean isOpen() {
        return getDelegate().isOpen();
    }

    @Override // kotlin.reflect.KCallable
    public boolean isAbstract() {
        return getDelegate().isAbstract();
    }

    @Override // kotlin.reflect.KCallable
    public boolean isSuspend() {
        return getDelegate().isSuspend();
    }

    @Override // kotlin.reflect.KProperty
    public boolean isLateinit() {
        return getDelegate().isLateinit();
    }

    @Override // kotlin.reflect.KProperty
    public boolean isConst() {
        return getDelegate().isConst();
    }

    @Override // kotlin.reflect.KAnnotatedElement
    public List<Annotation> getAnnotations() {
        return getDelegate().getAnnotations();
    }

    public boolean equals(Object other) {
        return Intrinsics.areEqual(getDelegate(), other);
    }

    public int hashCode() {
        return getDelegate().hashCode();
    }

    public String toString() {
        return getDelegate().toString();
    }
}
