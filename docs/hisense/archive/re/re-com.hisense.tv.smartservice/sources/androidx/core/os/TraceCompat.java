package androidx.core.os;

import android.os.Build;
import android.os.Trace;
import android.util.Log;
import androidx.annotation.NonNull;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
public final class TraceCompat {
    public static final String TAG = "TraceCompat";
    public static Method sAsyncTraceBeginMethod;
    public static Method sAsyncTraceEndMethod;
    public static Method sIsTagEnabledMethod;
    public static Method sTraceCounterMethod;
    public static long sTraceTagApp;

    static {
        if (Build.VERSION.SDK_INT < 29) {
            try {
                sTraceTagApp = Trace.class.getField("TRACE_TAG_APP").getLong(null);
                sIsTagEnabledMethod = Trace.class.getMethod("isTagEnabled", Long.TYPE);
                sAsyncTraceBeginMethod = Trace.class.getMethod("asyncTraceBegin", Long.TYPE, String.class, Integer.TYPE);
                sAsyncTraceEndMethod = Trace.class.getMethod("asyncTraceEnd", Long.TYPE, String.class, Integer.TYPE);
                sTraceCounterMethod = Trace.class.getMethod("traceCounter", Long.TYPE, String.class, Integer.TYPE);
            } catch (Exception e2) {
                Log.i(TAG, "Unable to initialize via reflection.", e2);
            }
        }
    }

    public static void beginAsyncSection(@NonNull String methodName, int cookie) {
        if (Build.VERSION.SDK_INT >= 29) {
            Trace.beginAsyncSection(methodName, cookie);
            return;
        }
        try {
            sAsyncTraceBeginMethod.invoke(null, Long.valueOf(sTraceTagApp), methodName, Integer.valueOf(cookie));
        } catch (Exception unused) {
            Log.v(TAG, "Unable to invoke asyncTraceBegin() via reflection.");
        }
    }

    public static void beginSection(@NonNull String sectionName) {
        int i = Build.VERSION.SDK_INT;
        Trace.beginSection(sectionName);
    }

    public static void endAsyncSection(@NonNull String methodName, int cookie) {
        if (Build.VERSION.SDK_INT >= 29) {
            Trace.endAsyncSection(methodName, cookie);
            return;
        }
        try {
            sAsyncTraceEndMethod.invoke(null, Long.valueOf(sTraceTagApp), methodName, Integer.valueOf(cookie));
        } catch (Exception unused) {
            Log.v(TAG, "Unable to invoke endAsyncSection() via reflection.");
        }
    }

    public static void endSection() {
        int i = Build.VERSION.SDK_INT;
        Trace.endSection();
    }

    public static boolean isEnabled() {
        if (Build.VERSION.SDK_INT >= 29) {
            return Trace.isEnabled();
        }
        try {
            return ((Boolean) sIsTagEnabledMethod.invoke(null, Long.valueOf(sTraceTagApp))).booleanValue();
        } catch (Exception unused) {
            Log.v(TAG, "Unable to invoke isTagEnabled() via reflection.");
            return false;
        }
    }

    public static void setCounter(@NonNull String counterName, int counterValue) {
        if (Build.VERSION.SDK_INT >= 29) {
            Trace.setCounter(counterName, counterValue);
            return;
        }
        try {
            sTraceCounterMethod.invoke(null, Long.valueOf(sTraceTagApp), counterName, Integer.valueOf(counterValue));
        } catch (Exception unused) {
            Log.v(TAG, "Unable to invoke traceCounter() via reflection.");
        }
    }
}
