package b.b.a;

import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public class e {
    public static void a(String str, String str2) {
        StringBuilder sbC = b.a.a.a.a.c(str2);
        sbC.append(c());
        Log.d(str, sbC.toString());
    }

    public static void b(String str, Throwable th) {
        StringBuilder sbC = b.a.a.a.a.c(str);
        sbC.append(c());
        Log.e("PRISM", sbC.toString(), th);
    }

    public static String c() {
        StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[4];
        return " - " + stackTraceElement.getMethodName() + "()#" + stackTraceElement.getFileName() + ":" + stackTraceElement.getLineNumber() + "(" + Thread.currentThread().getName() + "," + Thread.currentThread().getId() + ")";
    }
}
