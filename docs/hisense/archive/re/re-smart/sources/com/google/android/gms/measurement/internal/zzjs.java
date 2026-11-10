package com.google.android.gms.measurement.internal;

/* JADX INFO: loaded from: classes.dex */
public final class zzjs implements Runnable {
    public final /* synthetic */ zzks zza;
    public final /* synthetic */ Runnable zzb;

    public zzjs(zzju zzjuVar, zzks zzksVar, Runnable runnable) {
        this.zza = zzksVar;
        this.zzb = runnable;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zza.zzA();
        this.zza.zzz(this.zzb);
        this.zza.zzV();
    }
}
