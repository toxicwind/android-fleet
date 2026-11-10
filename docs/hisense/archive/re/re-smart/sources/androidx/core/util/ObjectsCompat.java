package androidx.core.util;

import android.os.Build;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class ObjectsCompat {
    public static boolean equals(@Nullable Object a2, @Nullable Object b2) {
        int i = Build.VERSION.SDK_INT;
        return Objects.equals(a2, b2);
    }

    public static int hash(@Nullable Object... values) {
        int i = Build.VERSION.SDK_INT;
        return Objects.hash(values);
    }

    public static int hashCode(@Nullable Object o) {
        if (o != null) {
            return o.hashCode();
        }
        return 0;
    }

    @NonNull
    public static <T> T requireNonNull(@Nullable T obj) {
        if (obj != null) {
            return obj;
        }
        throw new NullPointerException();
    }

    @Nullable
    public static String toString(@Nullable Object o, @Nullable String nullDefault) {
        return o != null ? o.toString() : nullDefault;
    }

    @NonNull
    public static <T> T requireNonNull(@Nullable T obj, @NonNull String message) {
        if (obj != null) {
            return obj;
        }
        throw new NullPointerException(message);
    }
}
