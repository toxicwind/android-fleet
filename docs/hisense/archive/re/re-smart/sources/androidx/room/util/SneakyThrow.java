package androidx.room.util;

import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;

/* JADX INFO: loaded from: classes.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class SneakyThrow {
    public static void reThrow(@NonNull Exception exc) throws Exception {
        throw exc;
    }

    public static <E extends Throwable> void sneakyThrow(@NonNull Throwable th) throws Throwable {
        throw th;
    }
}
