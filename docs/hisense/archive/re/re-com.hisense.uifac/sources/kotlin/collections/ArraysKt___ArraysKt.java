package kotlin.collections;

import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: compiled from: _Arrays.kt */
/* JADX INFO: loaded from: classes.dex */
public class ArraysKt___ArraysKt extends ArraysKt___ArraysJvmKt {
    public static final <T> boolean contains(@NotNull T[] contains, T t) {
        Intrinsics.checkParameterIsNotNull(contains, "$this$contains");
        return indexOf(contains, t) >= 0;
    }

    public static final <T> int indexOf(@NotNull T[] indexOf, T t) {
        Intrinsics.checkParameterIsNotNull(indexOf, "$this$indexOf");
        int i = 0;
        if (t == null) {
            int length = indexOf.length;
            while (i < length) {
                if (indexOf[i] == null) {
                    return i;
                }
                i++;
            }
            return -1;
        }
        int length2 = indexOf.length;
        while (i < length2) {
            if (Intrinsics.areEqual(t, indexOf[i])) {
                return i;
            }
            i++;
        }
        return -1;
    }

    @NotNull
    public static <T> List<T> toList(@NotNull T[] toList) {
        Intrinsics.checkParameterIsNotNull(toList, "$this$toList");
        int length = toList.length;
        if (length == 0) {
            return CollectionsKt__CollectionsKt.emptyList();
        }
        if (length == 1) {
            return CollectionsKt__CollectionsJVMKt.listOf(toList[0]);
        }
        return toMutableList(toList);
    }

    @NotNull
    public static final <T> List<T> toMutableList(@NotNull T[] toMutableList) {
        Intrinsics.checkParameterIsNotNull(toMutableList, "$this$toMutableList");
        return new ArrayList(CollectionsKt__CollectionsKt.asCollection(toMutableList));
    }
}
