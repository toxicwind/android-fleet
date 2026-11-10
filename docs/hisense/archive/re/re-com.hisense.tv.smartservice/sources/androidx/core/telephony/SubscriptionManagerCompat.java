package androidx.core.telephony;

import android.os.Build;
import android.telephony.SubscriptionManager;
import androidx.annotation.DoNotInline;
import androidx.annotation.RequiresApi;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes.dex */
@RequiresApi(22)
public class SubscriptionManagerCompat {
    public static Method sGetSlotIndexMethod;

    @RequiresApi(29)
    private static class Api29Impl {
        @DoNotInline
        public static int getSlotIndex(int subId) {
            return SubscriptionManager.getSlotIndex(subId);
        }
    }

    public static int getSlotIndex(int subId) {
        if (subId == -1) {
            return -1;
        }
        if (Build.VERSION.SDK_INT >= 29) {
            return SubscriptionManager.getSlotIndex(subId);
        }
        try {
            if (sGetSlotIndexMethod == null) {
                sGetSlotIndexMethod = SubscriptionManager.class.getDeclaredMethod("getSlotIndex", Integer.TYPE);
                sGetSlotIndexMethod.setAccessible(true);
            }
            Integer num = (Integer) sGetSlotIndexMethod.invoke(null, Integer.valueOf(subId));
            if (num != null) {
                return num.intValue();
            }
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException unused) {
        }
        return -1;
    }
}
