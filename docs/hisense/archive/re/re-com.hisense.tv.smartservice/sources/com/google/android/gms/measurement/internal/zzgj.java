package com.google.android.gms.measurement.internal;

/* JADX INFO: loaded from: classes.dex */
public final class zzgj implements Runnable {
    public final /* synthetic */ zzkv zza;
    public final /* synthetic */ zzp zzb;
    public final /* synthetic */ zzgn zzc;

    public zzgj(zzgn zzgnVar, zzkv zzkvVar, zzp zzpVar) {
        this.zzc = zzgnVar;
        this.zza = zzkvVar;
        this.zzb = zzpVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzc.zza.zzA();
        if (this.zza.zza() == null) {
            this.zzc.zza.zzO(this.zza, this.zzb);
        } else {
            this.zzc.zza.zzU(this.zza, this.zzb);
        }
    }
}
