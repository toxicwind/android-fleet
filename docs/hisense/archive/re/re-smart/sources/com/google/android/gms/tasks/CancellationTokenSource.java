package com.google.android.gms.tasks;

import androidx.annotation.NonNull;

/* JADX INFO: loaded from: classes.dex */
public class CancellationTokenSource {
    public final zzb zza = new zzb();

    public void cancel() {
        this.zza.zza();
    }

    @NonNull
    public CancellationToken getToken() {
        return this.zza;
    }
}
