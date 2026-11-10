package androidx.tracing;

import android.annotation.SuppressLint;
import android.os.Build;
import android.util.Log;
import androidx.annotation.NonNull;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes.dex */
public final class Trace {
    public static final String TAG = "Trace";
    public static Method sAsyncTraceBeginMethod;
    public static Method sAsyncTraceEndMethod;
    public static Method sIsTagEnabledMethod;
    public static Method sTraceCounterMethod;
    public static long sTraceTagApp;

    @SuppressLint({"NewApi"})
    public static void beginAsyncSection(@NonNull String str, int i) {
        try {
            if (sAsyncTraceBeginMethod == null) {
                android.os.Trace.beginAsyncSection(str, i);
                return;
            }
        } catch (NoClassDefFoundError | NoSuchMethodError unused) {
        }
        beginAsyncSectionFallback(str, i);
    }

    public static void beginAsyncSectionFallback(@NonNull String str, int i) {
        int i2 = Build.VERSION.SDK_INT;
        try {
            if (sAsyncTraceBeginMethod == null) {
                sAsyncTraceBeginMethod = android.os.Trace.class.getMethod("asyncTraceBegin", Long.TYPE, String.class, Integer.TYPE);
            }
            sAsyncTraceBeginMethod.invoke(null, Long.valueOf(sTraceTagApp), str, Integer.valueOf(i));
        } catch (Exception e2) {
            handleException("asyncTraceBegin", e2);
        }
    }

    public static void beginSection(@NonNull String str) {
        int i = Build.VERSION.SDK_INT;
        android.os.Trace.beginSection(str);
    }

    @SuppressLint({"NewApi"})
    public static void endAsyncSection(@NonNull String str, int i) {
        try {
            if (sAsyncTraceEndMethod == null) {
                android.os.Trace.endAsyncSection(str, i);
                return;
            }
        } catch (NoClassDefFoundError | NoSuchMethodError unused) {
        }
        endAsyncSectionFallback(str, i);
    }

    public static void endAsyncSectionFallback(@NonNull String str, int i) {
        int i2 = Build.VERSION.SDK_INT;
        try {
            if (sAsyncTraceEndMethod == null) {
                sAsyncTraceEndMethod = android.os.Trace.class.getMethod("asyncTraceEnd", Long.TYPE, String.class, Integer.TYPE);
            }
            sAsyncTraceEndMethod.invoke(null, Long.valueOf(sTraceTagApp), str, Integer.valueOf(i));
        } catch (Exception e2) {
            handleException("asyncTraceEnd", e2);
        }
    }

    public static void endSection() {
        int i = Build.VERSION.SDK_INT;
        android.os.Trace.endSection();
    }

    public static void handleException(@NonNull String str, @NonNull Exception exc) {
        if (exc instanceof InvocationTargetException) {
            Throwable cause = exc.getCause();
            if (!(cause instanceof RuntimeException)) {
                throw new RuntimeException(cause);
            }
            throw ((RuntimeException) cause);
        }
        Log.v(TAG, "Unable to call " + str + " via reflection", exc);
    }

    @SuppressLint({"NewApi"})
    public static boolean isEnabled() {
        try {
            if (sIsTagEnabledMethod == null) {
                return android.os.Trace.isEnabled();
            }
        } catch (NoClassDefFoundError | NoSuchMethodError unused) {
        }
        return isEnabledFallback();
    }

    public static boolean isEnabledFallback() {
        int i = Build.VERSION.SDK_INT;
        try {
            if (sIsTagEnabledMethod == null) {
                sTraceTagApp = android.os.Trace.class.getField("TRACE_TAG_APP").getLong(null);
                sIsTagEnabledMethod = android.os.Trace.class.getMethod("isTagEnabled", Long.TYPE);
            }
            return ((Boolean) sIsTagEnabledMethod.invoke(null, Long.valueOf(sTraceTagApp))).booleanValue();
        } catch (Exception e2) {
            handleException("isTagEnabled", e2);
            return false;
        }
    }

    @SuppressLint({"NewApi"})
    public static void setCounter(@NonNull String str, int i) {
        try {
            if (sTraceCounterMethod == null) {
                android.os.Trace.setCounter(str, i);
                return;
            }
        } catch (NoClassDefFoundError | NoSuchMethodError unused) {
        }
        setCounterFallback(str, i);
    }

    public static void setCounterFallback(@NonNull String str, int i) {
        int i2 = Build.VERSION.SDK_INT;
        try {
            if (sTraceCounterMethod == null) {
                sTraceCounterMethod = android.os.Trace.class.getMethod("traceCounter", Long.TYPE, String.class, Integer.TYPE);
            }
            sTraceCounterMethod.invoke(null, Long.valueOf(sTraceTagApp), str, Integer.valueOf(i));
        } catch (Exception e2) {
            handleException("traceCounter", e2);
        }
    }
}
