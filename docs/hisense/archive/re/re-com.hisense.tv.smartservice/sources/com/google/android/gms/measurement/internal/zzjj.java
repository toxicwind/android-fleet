package com.google.android.gms.measurement.internal;

import android.content.ComponentName;

/* JADX INFO: loaded from: classes.dex */
public final class zzjj implements Runnable {
    public final /* synthetic */ ComponentName zza;
    public final /* synthetic */ zzjn zzb;

    public zzjj(zzjn zzjnVar, ComponentName componentName) {
        this.zzb = zzjnVar;
        this.zza = componentName;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzjo.zzo(this.zzb.zza, this.zza);
    }
}
