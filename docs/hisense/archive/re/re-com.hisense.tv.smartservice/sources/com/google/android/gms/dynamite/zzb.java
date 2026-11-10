package com.google.android.gms.dynamite;

import android.os.Looper;
import android.util.Log;
import androidx.annotation.GuardedBy;
import androidx.annotation.Nullable;

/* JADX INFO: loaded from: classes.dex */
public final class zzb {

    @Nullable
    @GuardedBy("DynamiteLoaderV2ClassLoader.class")
    public static volatile ClassLoader zza;

    @Nullable
    @GuardedBy("DynamiteLoaderV2ClassLoader.class")
    public static volatile Thread zzb;

    @Nullable
    public static synchronized ClassLoader zza() {
        if (zza == null) {
            zza = zzb();
        }
        return zza;
    }

    @Nullable
    public static synchronized ClassLoader zzb() {
        ClassLoader contextClassLoader = null;
        if (zzb == null) {
            zzb = zzc();
            if (zzb == null) {
                return null;
            }
        }
        synchronized (zzb) {
            try {
                contextClassLoader = zzb.getContextClassLoader();
            } catch (SecurityException e2) {
                String strValueOf = String.valueOf(e2.getMessage());
                Log.w("DynamiteLoaderV2CL", strValueOf.length() != 0 ? "Failed to get thread context classloader ".concat(strValueOf) : new String("Failed to get thread context classloader "));
            }
        }
        return contextClassLoader;
    }

    /* JADX WARN: Code duplicated, block: B:42:0x008d A[Catch: all -> 0x0076, TryCatch #2 {, blocks: (B:11:0x0017, B:13:0x0025, B:19:0x003a, B:20:0x0041, B:22:0x004d, B:28:0x0062, B:29:0x0069, B:40:0x007b, B:42:0x008d, B:44:0x0097, B:43:0x0092, B:45:0x009a, B:16:0x0034), top: B:57:0x0017, outer: #4 }] */
    /* JADX WARN: Code duplicated, block: B:43:0x0092 A[Catch: all -> 0x0076, TryCatch #2 {, blocks: (B:11:0x0017, B:13:0x0025, B:19:0x003a, B:20:0x0041, B:22:0x004d, B:28:0x0062, B:29:0x0069, B:40:0x007b, B:42:0x008d, B:44:0x0097, B:43:0x0092, B:45:0x009a, B:16:0x0034), top: B:57:0x0017, outer: #4 }] */
    @Nullable
    public static synchronized Thread zzc() {
        Thread thread;
        SecurityException e2;
        String strValueOf;
        String str;
        ThreadGroup threadGroup;
        ThreadGroup threadGroup2 = Looper.getMainLooper().getThread().getThreadGroup();
        if (threadGroup2 == null) {
            return null;
        }
        synchronized (Void.class) {
            try {
                ThreadGroup[] threadGroupArr = new ThreadGroup[threadGroup2.activeGroupCount()];
                threadGroup2.enumerate(threadGroupArr);
                int length = threadGroupArr.length;
                int i = 0;
                int i2 = 0;
                while (true) {
                    if (i2 >= length) {
                        threadGroup = null;
                        break;
                    }
                    threadGroup = threadGroupArr[i2];
                    if ("dynamiteLoader".equals(threadGroup.getName())) {
                        break;
                    }
                    i2++;
                }
                if (threadGroup == null) {
                    threadGroup = new ThreadGroup(threadGroup2, "dynamiteLoader");
                }
                Thread[] threadArr = new Thread[threadGroup.activeCount()];
                threadGroup.enumerate(threadArr);
                int length2 = threadArr.length;
                while (true) {
                    if (i >= length2) {
                        thread = null;
                        break;
                    }
                    thread = threadArr[i];
                    if ("GmsDynamite".equals(thread.getName())) {
                        break;
                    }
                    i++;
                }
                if (thread == null) {
                    try {
                        zza zzaVar = new zza(threadGroup, "GmsDynamite");
                        try {
                            zzaVar.setContextClassLoader(null);
                            zzaVar.start();
                            thread = zzaVar;
                        } catch (SecurityException e3) {
                            e2 = e3;
                            thread = zzaVar;
                            strValueOf = String.valueOf(e2.getMessage());
                            if (strValueOf.length() != 0) {
                                str = "Failed to enumerate thread/threadgroup ".concat(strValueOf);
                            } else {
                                str = new String("Failed to enumerate thread/threadgroup ");
                            }
                            Log.w("DynamiteLoaderV2CL", str);
                        }
                    } catch (SecurityException e4) {
                        e = e4;
                        e2 = e;
                        strValueOf = String.valueOf(e2.getMessage());
                        if (strValueOf.length() != 0) {
                            str = "Failed to enumerate thread/threadgroup ".concat(strValueOf);
                        } else {
                            str = new String("Failed to enumerate thread/threadgroup ");
                        }
                        Log.w("DynamiteLoaderV2CL", str);
                        return thread;
                    }
                }
            } catch (SecurityException e5) {
                e = e5;
                thread = null;
            }
        }
        return thread;
    }
}
