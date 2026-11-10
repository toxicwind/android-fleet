package com.google.android.gms.tasks;

import androidx.annotation.NonNull;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes.dex */
public final class zzp<TResult, TContinuationResult> implements OnSuccessListener<TContinuationResult>, OnFailureListener, OnCanceledListener, zzq {
    public final Executor zza;
    public final SuccessContinuation<TResult, TContinuationResult> zzb;
    public final zzw<TContinuationResult> zzc;

    public zzp(@NonNull Executor executor, @NonNull SuccessContinuation<TResult, TContinuationResult> successContinuation, @NonNull zzw<TContinuationResult> zzwVar) {
        this.zza = executor;
        this.zzb = successContinuation;
        this.zzc = zzwVar;
    }

    @Override // com.google.android.gms.tasks.OnCanceledListener
    public final void onCanceled() {
        this.zzc.zzc();
    }

    @Override // com.google.android.gms.tasks.OnFailureListener
    public final void onFailure(@NonNull Exception exc) {
        this.zzc.zza(exc);
    }

    @Override // com.google.android.gms.tasks.OnSuccessListener
    public final void onSuccess(TContinuationResult tcontinuationresult) {
        this.zzc.zzb(tcontinuationresult);
    }

    @Override // com.google.android.gms.tasks.zzq
    public final void zzc() {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.android.gms.tasks.zzq
    public final void zzd(@NonNull Task<TResult> task) {
        this.zza.execute(new zzo(this, task));
    }
}
