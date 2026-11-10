package com.google.android.gms.measurement.internal;

/* JADX INFO: loaded from: classes.dex */
public final class zzkk implements Runnable {
    public final /* synthetic */ zzkt zza;
    public final /* synthetic */ zzks zzb;

    public zzkk(zzks zzksVar, zzkt zzktVar) {
        this.zzb = zzksVar;
        this.zza = zzktVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzks.zzy(this.zzb, this.zza);
        this.zzb.zzQ();
    }
}
