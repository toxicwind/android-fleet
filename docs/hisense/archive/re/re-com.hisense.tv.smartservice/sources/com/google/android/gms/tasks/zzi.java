package com.google.android.gms.tasks;

/* JADX INFO: loaded from: classes.dex */
public final class zzi implements Runnable {
    public final /* synthetic */ Task zza;
    public final /* synthetic */ zzj zzb;

    public zzi(zzj zzjVar, Task task) {
        this.zzb = zzjVar;
        this.zza = task;
    }

    /* JADX WARN: Type inference incomplete: some casts might be missing */
    @Override // java.lang.Runnable
    public final void run() {
        synchronized (this.zzb.zzb) {
            try {
                zzj zzjVar = this.zzb;
                if (zzjVar.zzc != 0) {
                    zzjVar.zzc.onComplete((Task<TResult>) this.zza);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
