package androidx.core.app;

import android.app.ActivityManager;
import android.os.Build;
import androidx.annotation.NonNull;

/* JADX INFO: loaded from: classes.dex */
public final class ActivityManagerCompat {
    public static boolean isLowRamDevice(@NonNull ActivityManager activityManager) {
        int i = Build.VERSION.SDK_INT;
        return activityManager.isLowRamDevice();
    }
}
