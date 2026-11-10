package com.google.android.gms.tasks;

/* JADX INFO: loaded from: classes.dex */
public final class zze implements Runnable {
    public final /* synthetic */ Task zza;
    public final /* synthetic */ zzf zzb;

    public zze(zzf zzfVar, Task task) {
        this.zzb = zzfVar;
        this.zza = task;
    }

    /* JADX WARN: Type inference incomplete: some casts might be missing */
    @Override // java.lang.Runnable
    public final void run() {
        try {
            Task task = (Task) this.zzb.zzb.then((Task<TResult>) this.zza);
            if (task == null) {
                this.zzb.onFailure(new NullPointerException("Continuation returned null"));
                return;
            }
            task.addOnSuccessListener(TaskExecutors.zza, this.zzb);
            task.addOnFailureListener(TaskExecutors.zza, this.zzb);
            task.addOnCanceledListener(TaskExecutors.zza, this.zzb);
        } catch (RuntimeExecutionException e2) {
            if (e2.getCause() instanceof Exception) {
                this.zzb.zzc.zza((Exception) e2.getCause());
            } else {
                this.zzb.zzc.zza(e2);
            }
        } catch (Exception e3) {
            this.zzb.zzc.zza(e3);
        }
    }
}
