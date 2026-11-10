package com.google.android.gms.internal.measurement;

/* JADX INFO: loaded from: classes.dex */
public final class zzbg implements zzbf {
    public final zzg zza;
    public final String zzb;

    public zzbg(zzg zzgVar, String str) {
        this.zza = zzgVar;
        this.zzb = str;
    }

    @Override // com.google.android.gms.internal.measurement.zzbf
    public final zzg zza(zzap zzapVar) {
        this.zza.zze(this.zzb, zzapVar);
        return this.zza;
    }
}
