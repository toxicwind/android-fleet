package com.hisense.avmiddleware;

import android.os.SystemProperties;
import android.util.Log;

/* JADX INFO: loaded from: classes2.dex */
public class HisLogger {
    public static final boolean DEBUG = true;
    public static final String TAG = "HISMW@Logger";

    public static void d(String str, String str2) {
        if (SystemProperties.get("vendor.hisense.loglevel").equals("")) {
            return;
        }
        Log.d(str, str2);
    }

    public static void e(String str, String str2) {
        Log.e(str, str2);
    }

    public static void i(String str, String str2) {
        if (SystemProperties.get("vendor.hisense.loglevel").equals("")) {
            return;
        }
        Log.i(str, str2);
    }

    public static void w(String str, String str2) {
        Log.w(str, str2);
    }
}
