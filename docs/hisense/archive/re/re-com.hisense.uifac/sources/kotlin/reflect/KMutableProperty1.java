package kotlin.reflect;

import kotlin.jvm.functions.Function2;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: KProperty.kt */
/* JADX INFO: loaded from: classes.dex */
public interface KMutableProperty1<T, R> extends KProperty1<T, R>, Object<R> {

    /* JADX INFO: compiled from: KProperty.kt */
    public interface Setter<T, R> extends KMutableProperty$Setter<R>, Function2<T, R, Object> {
    }

    @NotNull
    Setter<T, R> getSetter();
}
