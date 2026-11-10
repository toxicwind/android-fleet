package com.google.android.gms.tasks;

import androidx.annotation.NonNull;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes.dex */
public final class zzd<TResult, TContinuationResult> implements zzq<TResult> {
    public final Executor zza;
    public final Continuation<TResult, TContinuationResult> zzb;
    public final zzw<TContinuationResult> zzc;

    public zzd(@NonNull Executor executor, @NonNull Continuation<TResult, TContinuationResult> continuation, @NonNull zzw<TContinuationResult> zzwVar) {
        this.zza = executor;
        this.zzb = continuation;
        this.zzc = zzwVar;
    }

    @Override // com.google.android.gms.tasks.zzq
    public final void zzc() {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.android.gms.tasks.zzq
    public final void zzd(@NonNull Task<TResult> task) {
        this.zza.execute(new zzc(this, task));
    }
}
