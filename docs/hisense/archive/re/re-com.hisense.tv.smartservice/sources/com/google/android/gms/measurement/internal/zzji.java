package com.google.android.gms.measurement.internal;

/* JADX INFO: loaded from: classes.dex */
public final class zzji implements Runnable {
    public final /* synthetic */ zzeb zza;
    public final /* synthetic */ zzjn zzb;

    public zzji(zzjn zzjnVar, zzeb zzebVar) {
        this.zzb = zzjnVar;
        this.zza = zzebVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        synchronized (this.zzb) {
            this.zzb.zzb = false;
            if (!this.zzb.zza.zzL()) {
                this.zzb.zza.zzs.zzay().zzj().zza("Connected to service");
                this.zzb.zza.zzJ(this.zza);
            }
        }
    }
}
