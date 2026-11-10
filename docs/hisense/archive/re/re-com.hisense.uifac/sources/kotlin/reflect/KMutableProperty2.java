package kotlin.reflect;

import kotlin.jvm.functions.Function3;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: KProperty.kt */
/* JADX INFO: loaded from: classes.dex */
public interface KMutableProperty2<D, E, R> extends KProperty2<D, E, R>, Object<R> {

    /* JADX INFO: compiled from: KProperty.kt */
    public interface Setter<D, E, R> extends KMutableProperty$Setter<R>, Function3<D, E, R, Object> {
    }

    @NotNull
    Setter<D, E, R> getSetter();
}
