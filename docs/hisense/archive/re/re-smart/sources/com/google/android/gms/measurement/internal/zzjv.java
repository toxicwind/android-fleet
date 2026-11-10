package com.google.android.gms.measurement.internal;

/* JADX INFO: loaded from: classes.dex */
public final class zzjv implements Runnable {
    public final /* synthetic */ long zza;
    public final /* synthetic */ zzkd zzb;

    public zzjv(zzkd zzkdVar, long j) {
        this.zzb = zzkdVar;
        this.zza = j;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzkd.zzl(this.zzb, this.zza);
    }
}
