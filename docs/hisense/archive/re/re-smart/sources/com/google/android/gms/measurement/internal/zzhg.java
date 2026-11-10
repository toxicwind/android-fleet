package com.google.android.gms.measurement.internal;

/* JADX INFO: loaded from: classes.dex */
public final class zzhg implements Runnable {
    public final /* synthetic */ String zza;
    public final /* synthetic */ String zzb;
    public final /* synthetic */ Object zzc;
    public final /* synthetic */ long zzd;
    public final /* synthetic */ zzia zze;

    public zzhg(zzia zziaVar, String str, String str2, Object obj, long j) {
        this.zze = zziaVar;
        this.zza = str;
        this.zzb = str2;
        this.zzc = obj;
        this.zzd = j;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zze.zzZ(this.zza, this.zzb, this.zzc, this.zzd);
    }
}
