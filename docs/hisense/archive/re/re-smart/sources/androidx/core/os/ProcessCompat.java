package androidx.core.os;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Process;
import android.os.UserHandle;
import androidx.annotation.RequiresApi;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes.dex */
public final class ProcessCompat {

    @RequiresApi(16)
    static class Api16Impl {
        public static Method sMethodUserIdIsAppMethod;
        public static boolean sResolved;
        public static final Object sResolvedLock = new Object();

        @SuppressLint({"PrivateApi"})
        public static boolean isApplicationUid(int uid) {
            try {
                synchronized (sResolvedLock) {
                    if (!sResolved) {
                        sResolved = true;
                        sMethodUserIdIsAppMethod = Class.forName("android.os.UserId").getDeclaredMethod("isApp", Integer.TYPE);
                    }
                }
                if (sMethodUserIdIsAppMethod != null) {
                    Boolean bool = (Boolean) sMethodUserIdIsAppMethod.invoke(null, Integer.valueOf(uid));
                    if (bool != null) {
                        return bool.booleanValue();
                    }
                    throw new NullPointerException();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            return true;
        }
    }

    @RequiresApi(17)
    static class Api17Impl {
        public static Method sMethodUserHandleIsAppMethod;
        public static boolean sResolved;
        public static final Object sResolvedLock = new Object();

        @SuppressLint({"DiscouragedPrivateApi"})
        public static boolean isApplicationUid(int uid) {
            try {
                synchronized (sResolvedLock) {
                    if (!sResolved) {
                        sResolved = true;
                        sMethodUserHandleIsAppMethod = UserHandle.class.getDeclaredMethod("isApp", Integer.TYPE);
                    }
                }
                if (sMethodUserHandleIsAppMethod != null && ((Boolean) sMethodUserHandleIsAppMethod.invoke(null, Integer.valueOf(uid))) == null) {
                    throw new NullPointerException();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            return true;
        }
    }

    @RequiresApi(24)
    static class Api24Impl {
        public static boolean isApplicationUid(int uid) {
            return Process.isApplicationUid(uid);
        }
    }

    public static boolean isApplicationUid(int uid) {
        int i = Build.VERSION.SDK_INT;
        return Process.isApplicationUid(uid);
    }
}
