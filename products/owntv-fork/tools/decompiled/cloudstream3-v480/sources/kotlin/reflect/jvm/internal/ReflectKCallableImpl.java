package kotlin.reflect.jvm.internal;

import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KParameter;
import kotlin.reflect.full.IllegalCallableAccessException;

/* JADX INFO: compiled from: ReflectKCallableImpl.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0002\b\u0004\b \u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\u0015\u0010\n\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0007H\u0016¢\u0006\u0002\u0010\u000bR,\u0010\u0005\u001a \u0012\u001c\u0012\u001a\u0012\u0006\u0012\u0004\u0018\u00010\b \t*\f\u0012\u0006\u0012\u0004\u0018\u00010\b\u0018\u00010\u00070\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lkotlin/reflect/jvm/internal/ReflectKCallableImpl;", "R", "Lkotlin/reflect/jvm/internal/ReflectKCallable;", "<init>", "()V", "_absentArguments", "Lkotlin/reflect/jvm/internal/ReflectProperties$LazySoftVal;", "", "", "kotlin.jvm.PlatformType", "getAbsentArguments", "()[Ljava/lang/Object;", "kotlin-reflection"}, k = 1, mv = {2, 3, 0}, xi = 48)
public abstract class ReflectKCallableImpl<R> implements ReflectKCallable<R> {
    private final ReflectProperties.LazySoftVal<Object[]> _absentArguments;

    public ReflectKCallableImpl() {
        ReflectProperties.LazySoftVal<Object[]> lazySoftValLazySoft = ReflectProperties.lazySoft(new ReflectKCallableImpl$_absentArguments$1(this));
        Intrinsics.checkNotNullExpressionValue(lazySoftValLazySoft, "lazySoft(...)");
        this._absentArguments = lazySoftValLazySoft;
    }

    @Override // kotlin.reflect.KCallable
    public /* bridge */ R call(Object... objArr) {
        return default$call(objArr);
    }

    @Override // kotlin.reflect.KCallable
    public /* bridge */ R callBy(Map<KParameter, ? extends Object> map) {
        return default$callBy(map);
    }

    @Override // kotlin.reflect.jvm.internal.ReflectKCallable
    public Object[] getAbsentArguments() {
        return (Object[]) this._absentArguments.invoke().clone();
    }

    public R default$call(Object... args) throws IllegalCallableAccessException {
        Intrinsics.checkNotNullParameter(args, "args");
        try {
            return (R) getCaller().call(args);
        } catch (IllegalAccessException e) {
            throw new IllegalCallableAccessException(e);
        }
    }

    public R default$callBy(Map<KParameter, ? extends Object> args) {
        Intrinsics.checkNotNullParameter(args, "args");
        return ReflectKCallableKt.isAnnotationConstructor(this) ? (R) ReflectKCallableKt.callAnnotationConstructor(this, args) : (R) ReflectKCallableKt.callDefaultMethod(this, args, null);
    }
}
