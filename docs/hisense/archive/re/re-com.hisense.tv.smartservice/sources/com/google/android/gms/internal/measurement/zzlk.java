package com.google.android.gms.internal.measurement;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/* JADX INFO: loaded from: classes.dex */
public final class zzlk {
    public static final zzlk zza = new zzlk();
    public final ConcurrentMap<Class<?>, zzln<?>> zzc = new ConcurrentHashMap();
    public final zzlo zzb = new zzku();

    public static zzlk zza() {
        return zza;
    }

    public final <T> zzln<T> zzb(Class<T> cls) {
        zzkf.zzf(cls, "messageType");
        zzln<T> zzlnVarZza = (zzln) this.zzc.get(cls);
        if (zzlnVarZza == null) {
            zzlnVarZza = this.zzb.zza(cls);
            zzkf.zzf(cls, "messageType");
            zzkf.zzf(zzlnVarZza, "schema");
            zzln<T> zzlnVar = (zzln) this.zzc.putIfAbsent(cls, zzlnVarZza);
            if (zzlnVar != null) {
                return zzlnVar;
            }
        }
        return zzlnVarZza;
    }
}
