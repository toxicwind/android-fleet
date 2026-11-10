package androidx.core.telephony;

import android.annotation.SuppressLint;
import android.os.Build;
import android.telephony.TelephonyManager;
import androidx.annotation.DoNotInline;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RequiresPermission;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes.dex */
public class TelephonyManagerCompat {
    public static Method sGetDeviceIdMethod;
    public static Method sGetSubIdMethod;

    @RequiresApi(23)
    private static class Api23Impl {
        @SuppressLint({"MissingPermission"})
        @Nullable
        @RequiresPermission("android.permission.READ_PHONE_STATE")
        @DoNotInline
        public static String getDeviceId(TelephonyManager telephonyManager, int slotIndex) {
            return telephonyManager.getDeviceId(slotIndex);
        }
    }

    @RequiresApi(26)
    private static class Api26Impl {
        @SuppressLint({"MissingPermission"})
        @Nullable
        @RequiresPermission("android.permission.READ_PHONE_STATE")
        @DoNotInline
        public static String getImei(TelephonyManager telephonyManager) {
            return telephonyManager.getImei();
        }
    }

    @RequiresApi(30)
    private static class Api30Impl {
        @DoNotInline
        public static int getSubscriptionId(TelephonyManager telephonyManager) {
            return telephonyManager.getSubscriptionId();
        }
    }

    @Nullable
    @RequiresPermission("android.permission.READ_PHONE_STATE")
    @SuppressLint({"MissingPermission"})
    public static String getImei(@NonNull TelephonyManager telephonyManager) {
        int i = Build.VERSION.SDK_INT;
        return telephonyManager.getImei();
    }

    @SuppressLint({"SoonBlockedPrivateApi"})
    public static int getSubscriptionId(@NonNull TelephonyManager telephonyManager) {
        if (Build.VERSION.SDK_INT >= 30) {
            return telephonyManager.getSubscriptionId();
        }
        try {
            if (sGetSubIdMethod == null) {
                sGetSubIdMethod = TelephonyManager.class.getDeclaredMethod("getSubId", new Class[0]);
                sGetSubIdMethod.setAccessible(true);
            }
            Integer num = (Integer) sGetSubIdMethod.invoke(telephonyManager, new Object[0]);
            if (num == null || num.intValue() == -1) {
                return Integer.MAX_VALUE;
            }
            return num.intValue();
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException unused) {
            return Integer.MAX_VALUE;
        }
    }
}
