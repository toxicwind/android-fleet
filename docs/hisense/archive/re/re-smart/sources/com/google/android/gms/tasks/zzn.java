package com.google.android.gms.tasks;

import androidx.annotation.NonNull;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes.dex */
public final class zzn<TResult> implements zzq<TResult> {
    public final Executor zza;
    public final Object zzb = new Object();
    public OnSuccessListener<? super TResult> zzc;

    public zzn(@NonNull Executor executor, @NonNull OnSuccessListener<? super TResult> onSuccessListener) {
        this.zza = executor;
        this.zzc = onSuccessListener;
    }

    @Override // com.google.android.gms.tasks.zzq
    public final void zzc() {
        synchronized (this.zzb) {
            this.zzc = null;
        }
    }

    @Override // com.google.android.gms.tasks.zzq
    public final void zzd(@NonNull Task<TResult> task) {
        if (task.isSuccessful()) {
            synchronized (this.zzb) {
                if (this.zzc == null) {
                    return;
                }
                this.zza.execute(new zzm(this, task));
            }
        }
    }
}
