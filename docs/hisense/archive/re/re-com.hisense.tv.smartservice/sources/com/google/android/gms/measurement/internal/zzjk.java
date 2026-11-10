package com.google.android.gms.measurement.internal;

/* JADX INFO: loaded from: classes.dex */
public final class zzjk implements Runnable {
    public final /* synthetic */ zzeb zza;
    public final /* synthetic */ zzjn zzb;

    public zzjk(zzjn zzjnVar, zzeb zzebVar) {
        this.zzb = zzjnVar;
        this.zza = zzebVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        synchronized (this.zzb) {
            this.zzb.zzb = false;
            if (!this.zzb.zza.zzL()) {
                this.zzb.zza.zzs.zzay().zzc().zza("Connected to remote service");
                this.zzb.zza.zzJ(this.zza);
            }
        }
    }
}
