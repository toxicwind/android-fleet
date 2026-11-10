package com.google.android.gms.tasks;

import com.google.android.gms.common.internal.Preconditions;

/* JADX INFO: loaded from: classes.dex */
public final class zzk implements Runnable {
    public final /* synthetic */ Task zza;
    public final /* synthetic */ zzl zzb;

    public zzk(zzl zzlVar, Task task) {
        this.zzb = zzlVar;
        this.zza = task;
    }

    @Override // java.lang.Runnable
    public final void run() {
        synchronized (this.zzb.zzb) {
            try {
                zzl zzlVar = this.zzb;
                if (zzlVar.zzc != null) {
                    OnFailureListener onFailureListener = zzlVar.zzc;
                    Exception exception = this.zza.getException();
                    Preconditions.checkNotNull(exception);
                    onFailureListener.onFailure(exception);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
