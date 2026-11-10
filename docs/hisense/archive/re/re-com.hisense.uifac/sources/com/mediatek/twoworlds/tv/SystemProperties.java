package com.mediatek.twoworlds.tv;

import android.text.TextUtils;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public final class SystemProperties {
    private static final String TAG = "SystemProperties";

    public static String get(String str) {
        return get(str, "");
    }

    public static String get(String str, String str2) {
        try {
            String str3 = (String) Class.forName("android.os.SystemProperties").getDeclaredMethod("get", String.class).invoke(null, str);
            TvDebugLog.d(TAG, "get " + str + ", " + str2);
            return !TextUtils.isEmpty(str3) ? str3 : str2;
        } catch (Exception e) {
            Log.d(TAG, "Exception " + e);
        }
    }

    public static int getInt(String str, int i) {
        try {
            int iIntValue = ((Integer) Class.forName("android.os.SystemProperties").getDeclaredMethod("getInt", String.class, Integer.TYPE).invoke(null, str, Integer.valueOf(i))).intValue();
            TvDebugLog.d(TAG, "getInt " + str + ", " + iIntValue);
            return iIntValue;
        } catch (Exception e) {
            Log.d(TAG, "Exception " + e);
            return i;
        }
    }

    public static long getLong(String str, long j) {
        try {
            long jLongValue = ((Long) Class.forName("android.os.SystemProperties").getDeclaredMethod("getLong", String.class, Long.TYPE).invoke(null, str, Long.valueOf(j))).longValue();
            TvDebugLog.d(TAG, "getLong " + str + ", " + jLongValue);
            return jLongValue;
        } catch (Exception e) {
            Log.d(TAG, "Exception " + e);
            return j;
        }
    }

    public static boolean getBoolean(String str, boolean z) {
        try {
            boolean zBooleanValue = ((Boolean) Class.forName("android.os.SystemProperties").getDeclaredMethod("getBoolean", String.class, Boolean.TYPE).invoke(null, str, Boolean.valueOf(z))).booleanValue();
            TvDebugLog.d(TAG, "getLong " + str + ", " + zBooleanValue);
            return zBooleanValue;
        } catch (Exception e) {
            Log.d(TAG, "Exception " + e);
            return z;
        }
    }

    public static void set(String str, String str2) {
        TvDebugLog.d(TAG, "set " + str + ", " + str2);
        MtkTvUtilBase.setSystemProperties(str, str2);
    }
}
