package kotlin.reflect;

import kotlin.jvm.functions.Function1;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: KProperty.kt */
/* JADX INFO: loaded from: classes.dex */
public interface KMutableProperty0<R> extends KProperty0<R>, Object<R> {

    /* JADX INFO: compiled from: KProperty.kt */
    public interface Setter<R> extends KMutableProperty$Setter<R>, Function1<R, Object> {
    }

    @NotNull
    Setter<R> getSetter();
}
