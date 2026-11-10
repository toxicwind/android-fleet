package kotlin.reflect;

import kotlin.jvm.functions.Function1;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: KProperty.kt */
/* JADX INFO: loaded from: classes.dex */
public interface KProperty1<T, R> extends KProperty<R>, Function1<T, R> {

    /* JADX INFO: compiled from: KProperty.kt */
    public interface Getter<T, R> extends KProperty.Getter<R>, Function1<T, R> {
    }

    @Nullable
    Object getDelegate(T t);

    @NotNull
    Getter<T, R> getGetter();
}
