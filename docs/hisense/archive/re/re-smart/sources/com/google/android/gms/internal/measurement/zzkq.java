package com.google.android.gms.internal.measurement;

/* JADX INFO: loaded from: classes.dex */
public abstract class zzkq {
    public static final zzkq zza;
    public static final zzkq zzb;

    static {
        zzkn zzknVar = null;
        zza = new zzko(zzknVar);
        zzb = new zzkp(zzknVar);
    }

    public /* synthetic */ zzkq(zzkn zzknVar) {
    }

    public static zzkq zzc() {
        return zza;
    }

    public static zzkq zzd() {
        return zzb;
    }

    public abstract void zza(Object obj, long j);

    public abstract <L> void zzb(Object obj, Object obj2, long j);
}
