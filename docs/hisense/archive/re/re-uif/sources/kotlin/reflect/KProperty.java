package kotlin.reflect;

/* JADX INFO: compiled from: KProperty.kt */
/* JADX INFO: loaded from: classes.dex */
public interface KProperty<R> extends KCallable<R> {

    /* JADX WARN: Unexpected interfaces in signature: [kotlin.reflect.KFunction<R>] */
    /* JADX INFO: compiled from: KProperty.kt */
    public interface Getter<R> extends Object<R> {
    }

    boolean isConst();

    boolean isLateinit();
}
