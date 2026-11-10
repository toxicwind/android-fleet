package com.google.firebase.analytics;

import androidx.annotation.Nullable;
import java.util.concurrent.Callable;

/* JADX INFO: loaded from: classes.dex */
public final class zzb implements Callable<String> {
    public final /* synthetic */ FirebaseAnalytics zza;

    public zzb(FirebaseAnalytics firebaseAnalytics) {
        this.zza = firebaseAnalytics;
    }

    @Override // java.util.concurrent.Callable
    @Nullable
    public final String call() {
        return this.zza.zzb.zzk();
    }
}
