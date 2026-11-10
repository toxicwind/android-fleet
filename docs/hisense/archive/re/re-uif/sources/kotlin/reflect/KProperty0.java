package kotlin.reflect;

import kotlin.jvm.functions.Function0;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: KProperty.kt */
/* JADX INFO: loaded from: classes.dex */
public interface KProperty0<R> extends KProperty<R>, Function0<R> {

    /* JADX INFO: compiled from: KProperty.kt */
    public interface Getter<R> extends KProperty.Getter<R>, Function0<R> {
    }

    @Nullable
    Object getDelegate();

    @NotNull
    Getter<R> getGetter();
}
