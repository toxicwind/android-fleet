package com.google.android.gms.measurement.internal;

/* JADX INFO: loaded from: classes.dex */
public final class zzhu implements Runnable {
    public final /* synthetic */ zzag zza;
    public final /* synthetic */ long zzb;
    public final /* synthetic */ int zzc;
    public final /* synthetic */ long zzd;
    public final /* synthetic */ boolean zze;
    public final /* synthetic */ zzia zzf;

    public zzhu(zzia zziaVar, zzag zzagVar, long j, int i, long j2, boolean z) {
        this.zzf = zziaVar;
        this.zza = zzagVar;
        this.zzb = j;
        this.zzc = i;
        this.zzd = j2;
        this.zze = z;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzf.zzW(this.zza);
        this.zzf.zzL(this.zzb, false);
        zzia.zzv(this.zzf, this.zza, this.zzc, this.zzd, true, this.zze);
    }
}
