package com.mediatek.twoworlds.tv;

import android.util.Log;
import b.a.a.a.a;
import java.io.File;

/* JADX INFO: loaded from: classes2.dex */
public final class TvDebugLog {
    public static final String TAG = "TvDebugLog:";
    public static boolean logOnFlag = false;

    static {
        if (new File("/data/mtk_tvapi_debug").exists()) {
            logOnFlag = true;
        } else {
            Log.i(TAG, "not print log");
        }
    }

    public static void d(String str) {
        if (logOnFlag) {
            Log.d(TAG, str);
        }
    }

    public static void e(String str) {
        if (logOnFlag) {
            Log.e(TAG, str);
        }
    }

    public static void i(String str) {
        if (logOnFlag) {
            Log.i(TAG, str);
        }
    }

    public static void printStackTrace() {
        Throwable th = new Throwable();
        Log.getStackTraceString(th);
        th.printStackTrace();
    }

    public static void v(String str) {
        if (logOnFlag) {
            Log.v(TAG, str);
        }
    }

    public static void w(String str) {
        if (logOnFlag) {
            Log.w(TAG, str);
        }
    }

    public static void d(String str, String str2) {
        if (logOnFlag) {
            a.c(TAG, str2, str);
        }
    }

    public static void e(String str, String str2) {
        if (logOnFlag) {
            Log.e(str, TAG + str2);
        }
    }

    public static void i(String str, String str2) {
        if (logOnFlag) {
            Log.i(str, TAG + str2);
        }
    }

    public static void v(String str, String str2) {
        if (logOnFlag) {
            Log.v(str, TAG + str2);
        }
    }

    public static void w(String str, String str2) {
        if (logOnFlag) {
            Log.w(str, TAG + str2);
        }
    }

    public static void d(String str, String str2, Throwable th) {
        if (logOnFlag) {
            Log.d(str, TAG + str2, th);
        }
    }

    public static void e(String str, String str2, Throwable th) {
        if (logOnFlag) {
            Log.e(str, TAG + str2, th);
        }
    }

    public static void i(String str, String str2, Throwable th) {
        if (logOnFlag) {
            Log.i(str, TAG + str2, th);
        }
    }

    public static void v(String str, String str2, Throwable th) {
        if (logOnFlag) {
            Log.v(str, TAG + str2, th);
        }
    }

    public static void w(String str, String str2, Throwable th) {
        if (logOnFlag) {
            Log.w(str, TAG + str2, th);
        }
    }
}
