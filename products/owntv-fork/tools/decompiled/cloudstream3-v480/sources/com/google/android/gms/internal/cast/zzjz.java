package com.google.android.gms.internal.cast;

import androidx.constraintlayout.core.motion.utils.TypedValues;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
public final class zzjz {
    private static final String[] zza = {"com.google.common.flogger.util.StackWalkerStackGetter", "com.google.common.flogger.util.JavaLangAccessStackGetter"};
    private static final zzkd zzb;

    static {
        zzkd zzkeVar;
        for (int i = 0; i < 2; i++) {
            zzkeVar = null;
            try {
                zzkeVar = (zzkd) Class.forName(zza[i]).asSubclass(zzkd.class).getDeclaredConstructor(null).newInstance(null);
            } catch (Throwable unused) {
            }
            if (zzkeVar != null) {
                zzb = zzkeVar;
            }
        }
        zzkeVar = new zzke();
        zzb = zzkeVar;
    }

    public static StackTraceElement zza(Class cls, int i) {
        zzka.zza(cls, TypedValues.AttributesType.S_TARGET);
        return zzb.zza(cls, 2);
    }
}
