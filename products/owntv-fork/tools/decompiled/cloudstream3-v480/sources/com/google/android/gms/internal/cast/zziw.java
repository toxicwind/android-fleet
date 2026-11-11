package com.google.android.gms.internal.cast;

import java.lang.reflect.InvocationTargetException;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
final class zziw {
    private static final zziy zza = zzb(zziy.zzd);

    private static zziy zzb(String[] strArr) {
        zzjc zzjcVar;
        try {
            zzjcVar = zzjd.zza;
        } catch (NoClassDefFoundError unused) {
            zzjcVar = null;
        }
        if (zzjcVar != null) {
            return zzjcVar;
        }
        StringBuilder sb = new StringBuilder();
        for (String str : strArr) {
            try {
                return (zziy) Class.forName(str).getConstructor(null).newInstance(null);
            } catch (Throwable th) {
                th = th;
                sb.append('\n');
                sb.append(str);
                sb.append(": ");
                if (th instanceof InvocationTargetException) {
                    th = th.getCause();
                }
                sb.append(th);
            }
        }
        throw new IllegalStateException(sb.insert(0, "No logging platforms found:").toString());
    }
}
