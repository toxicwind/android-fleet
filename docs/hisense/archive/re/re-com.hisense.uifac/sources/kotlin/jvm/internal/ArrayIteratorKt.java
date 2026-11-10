package kotlin.jvm.internal;

import java.util.Iterator;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: ArrayIterator.kt */
/* JADX INFO: loaded from: classes.dex */
public final class ArrayIteratorKt {
    @NotNull
    public static final <T> Iterator<T> iterator(@NotNull T[] array) {
        Intrinsics.checkParameterIsNotNull(array, "array");
        return new ArrayIterator(array);
    }
}
