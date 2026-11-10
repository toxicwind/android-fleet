package com.vt.tv.usbota.G;

import android.text.TextUtils;
import android.util.Log;
import java.io.File;

/* JADX INFO: loaded from: classes.dex */
public class f {
    public static boolean a(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            return new File(str).exists();
        } catch (Exception e) {
            Log.e("Utils", "isFileExist exception", e);
            return false;
        }
    }
}
