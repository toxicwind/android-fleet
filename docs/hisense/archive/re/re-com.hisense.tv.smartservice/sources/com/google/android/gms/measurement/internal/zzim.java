package com.google.android.gms.measurement.internal;

/* JADX INFO: loaded from: classes.dex */
public final class zzim implements Runnable {
    public final /* synthetic */ zzih zza;
    public final /* synthetic */ long zzb;
    public final /* synthetic */ zzio zzc;

    public zzim(zzio zzioVar, zzih zzihVar, long j) {
        this.zzc = zzioVar;
        this.zza = zzihVar;
        this.zzb = j;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzc.zzC(this.zza, false, this.zzb);
        zzio zzioVar = this.zzc;
        zzioVar.zza = null;
        zzioVar.zzs.zzt().zzG(null);
    }
}
