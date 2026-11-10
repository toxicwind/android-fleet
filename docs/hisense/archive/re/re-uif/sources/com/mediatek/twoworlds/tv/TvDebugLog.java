package com.mediatek.twoworlds.tv;

import android.util.Log;
import java.io.File;

/* JADX INFO: loaded from: classes.dex */
public final class TvDebugLog {
    private static final String TAG = "TvDebugLog:";
    private static boolean logOnFlag = false;

    static {
        if (new File("/data/mtk_tvapi_debug").exists()) {
            logOnFlag = true;
        } else {
            Log.i(TAG, "not print log");
        }
    }

    protected static void v(String str) {
        if (logOnFlag) {
            Log.v(TAG, str);
        }
    }

    protected static void v(String str, String str2) {
        if (logOnFlag) {
            Log.v(str, TAG + str2);
        }
    }

    protected static void v(String str, String str2, Throwable th) {
        if (logOnFlag) {
            Log.v(str, TAG + str2, th);
        }
    }

    protected static void d(String str) {
        if (logOnFlag) {
            Log.d(TAG, str);
        }
    }

    protected static void d(String str, String str2) {
        if (logOnFlag) {
            Log.d(str, TAG + str2);
        }
    }

    protected static void d(String str, String str2, Throwable th) {
        if (logOnFlag) {
            Log.d(str, TAG + str2, th);
        }
    }

    protected static void i(String str) {
        if (logOnFlag) {
            Log.i(TAG, str);
        }
    }

    protected static void i(String str, String str2) {
        if (logOnFlag) {
            Log.i(str, TAG + str2);
        }
    }

    protected static void i(String str, String str2, Throwable th) {
        if (logOnFlag) {
            Log.i(str, TAG + str2, th);
        }
    }

    protected static void w(String str) {
        if (logOnFlag) {
            Log.w(TAG, str);
        }
    }

    protected static void w(String str, String str2) {
        if (logOnFlag) {
            Log.w(str, TAG + str2);
        }
    }

    protected static void w(String str, String str2, Throwable th) {
        if (logOnFlag) {
            Log.w(str, TAG + str2, th);
        }
    }

    protected static void e(String str) {
        if (logOnFlag) {
            Log.e(TAG, str);
        }
    }

    protected static void e(String str, String str2) {
        if (logOnFlag) {
            Log.e(str, TAG + str2);
        }
    }

    protected static void e(String str, String str2, Throwable th) {
        if (logOnFlag) {
            Log.e(str, TAG + str2, th);
        }
    }

    public static void printStackTrace() {
        Throwable th = new Throwable();
        Log.getStackTraceString(th);
        th.printStackTrace();
    }
}
