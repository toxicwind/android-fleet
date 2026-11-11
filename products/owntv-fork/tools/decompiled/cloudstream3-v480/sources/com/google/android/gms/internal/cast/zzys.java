package com.google.android.gms.internal.cast;

import j$.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
final class zzys {
    public static final /* synthetic */ int zza = 0;
    private static final zzys zzb = new zzys();
    private final ConcurrentMap zzd = new ConcurrentHashMap();
    private final zzyw zzc = new zzyd();

    private zzys() {
    }

    public static zzys zza() {
        return zzb;
    }

    public final zzyv zzb(Class cls) {
        zzxo.zzc(cls, "messageType");
        ConcurrentMap concurrentMap = this.zzd;
        zzyv zzyvVar = (zzyv) concurrentMap.get(cls);
        if (zzyvVar != null) {
            return zzyvVar;
        }
        zzyv zzyvVarZza = this.zzc.zza(cls);
        zzxo.zzc(cls, "messageType");
        zzyv zzyvVar2 = (zzyv) concurrentMap.putIfAbsent(cls, zzyvVarZza);
        return zzyvVar2 != null ? zzyvVar2 : zzyvVarZza;
    }
}
