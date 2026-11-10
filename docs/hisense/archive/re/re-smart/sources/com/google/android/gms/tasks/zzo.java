package com.google.android.gms.tasks;

import java.util.concurrent.CancellationException;

/* JADX INFO: loaded from: classes.dex */
public final class zzo implements Runnable {
    public final /* synthetic */ Task zza;
    public final /* synthetic */ zzp zzb;

    public zzo(zzp zzpVar, Task task) {
        this.zzb = zzpVar;
        this.zza = task;
    }

    /* JADX WARN: Type inference incomplete: some casts might be missing */
    @Override // java.lang.Runnable
    public final void run() {
        try {
            Task taskThen = this.zzb.zzb.then((TResult) this.zza.getResult());
            if (taskThen == null) {
                this.zzb.onFailure(new NullPointerException("Continuation returned null"));
                return;
            }
            taskThen.addOnSuccessListener(TaskExecutors.zza, this.zzb);
            taskThen.addOnFailureListener(TaskExecutors.zza, this.zzb);
            taskThen.addOnCanceledListener(TaskExecutors.zza, this.zzb);
        } catch (RuntimeExecutionException e2) {
            if (e2.getCause() instanceof Exception) {
                this.zzb.onFailure((Exception) e2.getCause());
            } else {
                this.zzb.onFailure(e2);
            }
        } catch (CancellationException unused) {
            this.zzb.onCanceled();
        } catch (Exception e3) {
            this.zzb.onFailure(e3);
        }
    }
}
