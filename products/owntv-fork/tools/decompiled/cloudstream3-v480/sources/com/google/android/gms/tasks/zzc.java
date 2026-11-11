package com.google.android.gms.tasks;

/* JADX INFO: compiled from: com.google.android.gms:play-services-tasks@@18.1.0 */
/* JADX INFO: loaded from: classes5.dex */
final class zzc implements Runnable {
    final /* synthetic */ Task zza;
    final /* synthetic */ zzd zzb;

    zzc(zzd zzdVar, Task task) {
        this.zzb = zzdVar;
        this.zza = task;
    }

    @Override // java.lang.Runnable
    public final void run() {
        boolean zIsCanceled = this.zza.isCanceled();
        zzd zzdVar = this.zzb;
        if (zIsCanceled) {
            zzdVar.zzc.zzc();
            return;
        }
        try {
            this.zzb.zzc.zzb(zzdVar.zzb.then(this.zza));
        } catch (RuntimeExecutionException e) {
            boolean z = e.getCause() instanceof Exception;
            zzd zzdVar2 = this.zzb;
            if (z) {
                zzdVar2.zzc.zza((Exception) e.getCause());
            } else {
                zzdVar2.zzc.zza(e);
            }
        } catch (Exception e2) {
            this.zzb.zzc.zza(e2);
        }
    }
}
