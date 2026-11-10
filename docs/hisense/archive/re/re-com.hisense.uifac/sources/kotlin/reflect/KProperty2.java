package kotlin.reflect;

import kotlin.jvm.functions.Function2;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: KProperty.kt */
/* JADX INFO: loaded from: classes.dex */
public interface KProperty2<D, E, R> extends KProperty<R>, Function2<D, E, R> {

    /* JADX INFO: compiled from: KProperty.kt */
    public interface Getter<D, E, R> extends KProperty.Getter<R>, Function2<D, E, R> {
    }

    @Nullable
    Object getDelegate(D d, E e);

    @NotNull
    Getter<D, E, R> getGetter();
}
