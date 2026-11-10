package kotlin.collections;

import java.util.Collection;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: compiled from: Collections.kt */
/* JADX INFO: loaded from: classes.dex */
public class CollectionsKt__CollectionsKt extends CollectionsKt__CollectionsJVMKt {
    @NotNull
    public static final <T> Collection<T> asCollection(@NotNull T[] asCollection) {
        Intrinsics.checkParameterIsNotNull(asCollection, "$this$asCollection");
        return new ArrayAsCollection(asCollection, false);
    }

    @NotNull
    public static <T> List<T> emptyList() {
        return EmptyList.INSTANCE;
    }

    @NotNull
    public static <T> List<T> listOf(@NotNull T... elements) {
        Intrinsics.checkParameterIsNotNull(elements, "elements");
        return elements.length > 0 ? ArraysKt___ArraysJvmKt.asList(elements) : emptyList();
    }
}
