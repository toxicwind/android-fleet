package com.google.android.gms.measurement.internal;

/* JADX INFO: loaded from: classes.dex */
public final class zzij implements Runnable {
    public final /* synthetic */ zzih zza;
    public final /* synthetic */ zzih zzb;
    public final /* synthetic */ long zzc;
    public final /* synthetic */ boolean zzd;
    public final /* synthetic */ zzio zze;

    public zzij(zzio zzioVar, zzih zzihVar, zzih zzihVar2, long j, boolean z) {
        this.zze = zzioVar;
        this.zza = zzihVar;
        this.zzb = zzihVar2;
        this.zzc = j;
        this.zzd = z;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zze.zzB(this.zza, this.zzb, this.zzc, this.zzd, null);
    }
}
