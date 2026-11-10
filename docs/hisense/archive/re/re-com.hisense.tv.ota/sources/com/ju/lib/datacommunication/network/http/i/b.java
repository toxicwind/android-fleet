package com.ju.lib.datacommunication.network.http.i;

import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static String f142a = "HTTP2Stack";

    private static String a(Object[] objArr, String... strArr) {
        if (objArr == null || objArr.length == 0) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        if (strArr != null) {
            for (String str : strArr) {
                sb.append(str);
            }
        }
        for (Object obj : objArr) {
            sb.append(obj);
        }
        return sb.toString();
    }

    public static void b(String str, Object... objArr) {
        int length;
        String strA = a(objArr, "|");
        if (strA != null) {
            if (strA.length() > 4000) {
                do {
                    Log.d(str, strA.substring(0, 4000));
                    strA = strA.substring(4000);
                    length = strA.length();
                } while (length > 4000);
                if (length <= 0) {
                    return;
                }
            }
            Log.d(str, strA);
        }
    }

    public static void c(String str, Object... objArr) {
        int length;
        String strA = a(objArr, "|");
        if (strA != null) {
            if (strA.length() > 4000) {
                do {
                    Log.e(str, strA.substring(0, 4000));
                    strA = strA.substring(4000);
                    length = strA.length();
                } while (length > 4000);
                if (length <= 0) {
                    return;
                }
            }
            Log.e(str, strA);
        }
    }

    public static void d(String str, Object... objArr) {
        int length;
        String strA = a(objArr, "|");
        if (strA != null) {
            if (strA.length() > 4000) {
                do {
                    Log.i(str, strA.substring(0, 4000));
                    strA = strA.substring(4000);
                    length = strA.length();
                } while (length > 4000);
                if (length <= 0) {
                    return;
                }
            }
            Log.i(str, strA);
        }
    }

    public static boolean e() {
        return Log.isLoggable(f142a, 3);
    }

    public static void f(String str, Throwable th, Object... objArr) {
        int length;
        if (!e()) {
            StringBuffer stringBuffer = new StringBuffer();
            if (th != null) {
                stringBuffer.append("message:");
                stringBuffer.append(th.getMessage());
                if (th.getCause() != null) {
                    stringBuffer.append(",cause by:");
                    stringBuffer.append(th.getCause().getMessage());
                }
            }
            g(str, stringBuffer.toString(), objArr);
            return;
        }
        String strA = a(objArr, "|");
        if (strA != null) {
            if (strA.length() > 4000) {
                do {
                    Log.w(str, strA.substring(0, 4000));
                    strA = strA.substring(4000);
                    length = strA.length();
                } while (length > 4000);
                if (length > 0) {
                    Log.w(str, strA);
                }
                strA = "";
            }
            Log.w(str, strA, th);
        }
    }

    public static void g(String str, Object... objArr) {
        int length;
        String strA = a(objArr, "|");
        if (strA != null) {
            if (strA.length() > 4000) {
                do {
                    Log.w(str, strA.substring(0, 4000));
                    strA = strA.substring(4000);
                    length = strA.length();
                } while (length > 4000);
                if (length <= 0) {
                    return;
                }
            }
            Log.w(str, strA);
        }
    }
}
