package com.hmct.cloud.sdk.utils;

import android.text.TextUtils;
import android.util.Log;

/* JADX INFO: loaded from: classes2.dex */
public final class a {
    public static boolean a() {
        String strA = a("ro.product.hitdeviceprefix");
        if (!TextUtils.isEmpty(strA) && strA.length() == 24) {
            String strSubstring = strA.substring(0, 12);
            String strSubstring2 = strA.substring(15, 24);
            if ("861003009000".equals(strSubstring) && "000000056".equals(strSubstring2)) {
                return true;
            }
        }
        return false;
    }

    public static boolean b() {
        String strA = a("ro.product.hitdeviceprefix");
        if (!TextUtils.isEmpty(strA) && strA.length() == 24) {
            String strSubstring = strA.substring(0, 12);
            String strSubstring2 = strA.substring(15, 24);
            if ("861003009000".equals(strSubstring) && "000000703".equals(strSubstring2)) {
                return true;
            }
            if ("861003009000".equals(strSubstring) && "000000059".equals(strSubstring2)) {
                return true;
            }
            if ("861003090000".equals(strSubstring) && "000000706".equals(strSubstring2)) {
                return true;
            }
            if ("861003009000".equals(strSubstring) && "000000705".equals(strSubstring2)) {
                return true;
            }
        }
        return false;
    }

    public static boolean c() {
        String strA = a("ro.product.hitdeviceprefix");
        if (!TextUtils.isEmpty(strA) && strA.length() == 24) {
            String strSubstring = strA.substring(0, 12);
            String strSubstring2 = strA.substring(15, 24);
            if ("861003009000".equals(strSubstring) && "000000710".equals(strSubstring2)) {
                return true;
            }
            if ("861003009000".equals(strSubstring) && "000000709".equals(strSubstring2)) {
                return true;
            }
        }
        return false;
    }

    public static boolean d() {
        String strA = a("AppStore.Silo_supported");
        if (!TextUtils.isEmpty(strA) && strA.equals("true")) {
            return true;
        }
        String strA2 = a("ReturnToVODFromSiloApp");
        return !TextUtils.isEmpty(strA2) && strA2.equals("true");
    }

    public static boolean e() {
        String strA = a("VIDAA6_AppSilo_supported");
        return !TextUtils.isEmpty(strA) && strA.equals("true");
    }

    public static boolean f() {
        String strA = a("ReturnToVODFromSiloApp");
        return !TextUtils.isEmpty(strA) && strA.equals("true");
    }

    public static boolean g() {
        String strA = a("ro.product.hitdeviceprefix");
        if (!TextUtils.isEmpty(strA) && strA.length() == 24) {
            String strSubstring = strA.substring(0, 12);
            String strSubstring2 = strA.substring(15, 24);
            if ("861003009000".equals(strSubstring) && "00000070a".equals(strSubstring2)) {
                return true;
            }
            if ("861003009000".equals(strSubstring) && "000000257".equals(strSubstring2)) {
                return true;
            }
        }
        return false;
    }

    public static boolean h() {
        String strJ = j();
        return !TextUtils.isEmpty(strJ) && strJ.startsWith("6");
    }

    public static boolean i() {
        String strJ = j();
        if (!TextUtils.isEmpty(strJ) && Float.parseFloat(strJ) >= 4.0f) {
            return true;
        }
        String strA = a("ro.product.hitdeviceprefix");
        if (!TextUtils.isEmpty(strA) && strA.length() == 24) {
            String strSubstring = strA.substring(0, 12);
            String strSubstring2 = strA.substring(15, 24);
            if (("861003009000".equals(strSubstring) && "00000060a".equals(strSubstring2)) || (("861003009000".equals(strSubstring) && "00000060b".equals(strSubstring2)) || ("861003009000".equals(strSubstring) && "000000084".equals(strSubstring2)))) {
                return true;
            }
        }
        return false;
    }

    public static String j() {
        String strA = a("ro.product.vidaa.version");
        Log.d("test", "vidaaVersion=" + strA);
        return strA;
    }

    public static final String a(String str) {
        try {
            return (String) Class.forName("android.os.SystemProperties").getMethod("get", String.class).invoke(null, str);
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }
}
