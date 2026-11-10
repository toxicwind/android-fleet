package com.hisense.tv.ota.W;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public class d {
    public static String a(int i, Context context) {
        String strA;
        if (i == 1) {
            strA = a.a.a.a.a.a.a.a(context, "auth", "base", "base-auth.intsmarthub.com");
        } else if (i != 2) {
            Log.e("HiCloudUtils", "get domain failed, unknown domain type: " + i);
            strA = "intsmarthub.com";
        } else {
            strA = a.a.a.a.a.a.a.b(context, "tv", "ota", "ota-tv.intsmarthub.com", false);
        }
        Log.i("HiCloudUtils", "domainName i: " + strA);
        return strA;
    }

    public static String b(Context context) {
        return c.a(context);
    }

    public static a.b.a.a.c.c c(String str, Context context) {
        if (!TextUtils.isEmpty(str)) {
            return a.b.a.a.b.a.b();
        }
        Log.e("HiCloudUtils", "getUpgradeService failed, token is empty");
        return null;
    }
}
