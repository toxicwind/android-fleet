package com.google.android.gms.measurement.internal;

/* JADX INFO: loaded from: classes.dex */
public final class zzc implements Runnable {
    public final /* synthetic */ long zza;
    public final /* synthetic */ zzd zzb;

    public zzc(zzd zzdVar, long j) {
        this.zzb = zzdVar;
        this.zza = j;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzb.zzj(this.zza);
    }
}
