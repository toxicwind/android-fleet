package com.hisense.serialInfoTest.util;

import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public class Logger {
    static String tag = "HisFactory";

    public static void d(String str) {
        Log.d(tag, str);
    }

    public static void e(String str) {
        Log.e(tag, str);
    }

    public static void i(String str) {
        Log.i(tag, str);
    }
}
