package com.hisense.tv.ota;

import android.os.SystemProperties;
import android.util.Log;

/* JADX INFO: renamed from: com.hisense.tv.ota.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public class C0000a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static int f32a;
    public static boolean b = SystemProperties.getBoolean("ota.test", false);
    public static String c = "his";
    public static String d = "USA";
    private static boolean e = true;

    public static boolean a() {
        return e || Log.isLoggable("OTA", 3);
    }
}
