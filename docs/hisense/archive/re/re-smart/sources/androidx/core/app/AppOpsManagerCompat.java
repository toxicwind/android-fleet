package androidx.core.app;

import android.app.AppOpsManager;
import android.content.Context;
import android.os.Build;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/* JADX INFO: loaded from: classes.dex */
public final class AppOpsManagerCompat {
    public static final int MODE_ALLOWED = 0;
    public static final int MODE_DEFAULT = 3;
    public static final int MODE_ERRORED = 2;
    public static final int MODE_IGNORED = 1;

    public static int noteOp(@NonNull Context context, @NonNull String op, int uid, @NonNull String packageName) {
        int i = Build.VERSION.SDK_INT;
        return ((AppOpsManager) context.getSystemService("appops")).noteOp(op, uid, packageName);
    }

    public static int noteOpNoThrow(@NonNull Context context, @NonNull String op, int uid, @NonNull String packageName) {
        int i = Build.VERSION.SDK_INT;
        return ((AppOpsManager) context.getSystemService("appops")).noteOpNoThrow(op, uid, packageName);
    }

    public static int noteProxyOp(@NonNull Context context, @NonNull String op, @NonNull String proxiedPackageName) {
        int i = Build.VERSION.SDK_INT;
        return ((AppOpsManager) context.getSystemService(AppOpsManager.class)).noteProxyOp(op, proxiedPackageName);
    }

    public static int noteProxyOpNoThrow(@NonNull Context context, @NonNull String op, @NonNull String proxiedPackageName) {
        int i = Build.VERSION.SDK_INT;
        return ((AppOpsManager) context.getSystemService(AppOpsManager.class)).noteProxyOpNoThrow(op, proxiedPackageName);
    }

    @Nullable
    public static String permissionToOp(@NonNull String permission) {
        int i = Build.VERSION.SDK_INT;
        return AppOpsManager.permissionToOp(permission);
    }
}
