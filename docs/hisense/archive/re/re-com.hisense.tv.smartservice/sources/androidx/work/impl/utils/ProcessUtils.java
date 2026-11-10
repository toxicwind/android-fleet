package androidx.work.impl.utils;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.Process;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.work.Configuration;
import androidx.work.Logger;
import java.lang.reflect.Method;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class ProcessUtils {
    public static final String TAG = Logger.tagWithPrefix("ProcessUtils");

    @Nullable
    @SuppressLint({"PrivateApi", "DiscouragedPrivateApi"})
    public static String getProcessName(@NonNull Context context) {
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses;
        if (Build.VERSION.SDK_INT >= 28) {
            return Application.getProcessName();
        }
        try {
            Class<?> cls = Class.forName("android.app.ActivityThread", false, ProcessUtils.class.getClassLoader());
            int i = Build.VERSION.SDK_INT;
            Method declaredMethod = cls.getDeclaredMethod("currentProcessName", new Class[0]);
            declaredMethod.setAccessible(true);
            Object objInvoke = declaredMethod.invoke(null, new Object[0]);
            if (objInvoke instanceof String) {
                return (String) objInvoke;
            }
        } catch (Throwable th) {
            Logger.get().debug(TAG, "Unable to check ActivityThread for processName", th);
        }
        int iMyPid = Process.myPid();
        ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
        if (activityManager != null && (runningAppProcesses = activityManager.getRunningAppProcesses()) != null && !runningAppProcesses.isEmpty()) {
            for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
                if (runningAppProcessInfo.pid == iMyPid) {
                    return runningAppProcessInfo.processName;
                }
            }
        }
        return null;
    }

    public static boolean isDefaultProcess(@NonNull Context context, @NonNull Configuration configuration) {
        String processName = getProcessName(context);
        return !TextUtils.isEmpty(configuration.getDefaultProcessName()) ? TextUtils.equals(processName, configuration.getDefaultProcessName()) : TextUtils.equals(processName, context.getApplicationInfo().processName);
    }
}
