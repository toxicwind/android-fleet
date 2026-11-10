package com.google.android.gms.tasks;

/* JADX INFO: loaded from: classes.dex */
public final class zzm implements Runnable {
    public final /* synthetic */ Task zza;
    public final /* synthetic */ zzn zzb;

    public zzm(zzn zznVar, Task task) {
        this.zzb = zznVar;
        this.zza = task;
    }

    /* JADX WARN: Type inference incomplete: some casts might be missing */
    @Override // java.lang.Runnable
    public final void run() {
        synchronized (this.zzb.zzb) {
            try {
                zzn zznVar = this.zzb;
                if (zznVar.zzc != 0) {
                    zznVar.zzc.onSuccess((Object) this.zza.getResult());
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
