package com.hisense.avmiddleware;

import android.os.SystemProperties;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public class HisLogger {
    public static void d(String str, String str2) {
        if (SystemProperties.get("vendor.hisense.loglevel").equals("")) {
            return;
        }
        Log.d(str, str2);
    }

    public static void e(String str, String str2) {
        Log.e(str, str2);
    }
}
