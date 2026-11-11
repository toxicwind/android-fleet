package com.google.android.gms.internal.cast;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
public abstract class zzgf {
    private static final ThreadLocal zza = new zzfz();

    public static zzgf zzb() {
        return (zzgf) zza.get();
    }

    public abstract void zza(zzgc zzgcVar);
}
