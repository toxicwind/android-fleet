package kotlin.collections;

import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: _ArraysJvm.kt */
/* JADX INFO: loaded from: classes.dex */
class ArraysKt___ArraysJvmKt extends ArraysKt__ArraysKt {
    @NotNull
    public static final <T> List<T> asList(@NotNull T[] asList) {
        Intrinsics.checkParameterIsNotNull(asList, "$this$asList");
        List<T> listAsList = ArraysUtilJVM.asList(asList);
        Intrinsics.checkExpressionValueIsNotNull(listAsList, "ArraysUtilJVM.asList(this)");
        return listAsList;
    }
}
