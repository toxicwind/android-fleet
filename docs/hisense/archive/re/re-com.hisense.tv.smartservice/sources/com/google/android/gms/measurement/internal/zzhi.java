package com.google.android.gms.measurement.internal;

import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes.dex */
public final class zzhi implements Runnable {
    public final /* synthetic */ long zza;
    public final /* synthetic */ zzia zzb;

    public zzhi(zzia zziaVar, long j) {
        this.zzb = zziaVar;
        this.zza = j;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzb.zzL(this.zza, true);
        this.zzb.zzs.zzt().zzu(new AtomicReference<>());
    }
}
