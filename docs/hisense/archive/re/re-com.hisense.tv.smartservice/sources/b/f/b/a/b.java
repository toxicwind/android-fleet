package b.f.b.a;

import android.util.Log;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes2.dex */
public class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f667a = "b";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static Class<?> f668b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static Method f669c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static Method f670d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static Method f671e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static Method f672f;

    public static int a(String str, int i) {
        a();
        try {
            return ((Integer) f669c.invoke(f668b, str, Integer.valueOf(i))).intValue();
        } catch (Exception e2) {
            Log.e(f667a, e2.toString());
            return i;
        }
    }

    public static void a(String str, String str2) {
        a();
        try {
            f671e.invoke(f668b, str, str2);
        } catch (Exception e2) {
            Log.e(f667a, e2.toString());
        }
    }

    public static void a() {
        try {
            if (f668b == null) {
                f668b = Class.forName("android.os.SystemProperties");
                f668b.getDeclaredMethod("get", String.class);
                f668b.getDeclaredMethod("get", String.class, String.class);
                f669c = f668b.getDeclaredMethod("getInt", String.class, Integer.TYPE);
                f670d = f668b.getDeclaredMethod("getBoolean", String.class, Boolean.TYPE);
                f672f = f668b.getDeclaredMethod("getLong", String.class, Long.TYPE);
                f671e = f668b.getDeclaredMethod("set", String.class, String.class);
            }
        } catch (Exception e2) {
            Log.e(f667a, e2.toString());
        }
    }
}
