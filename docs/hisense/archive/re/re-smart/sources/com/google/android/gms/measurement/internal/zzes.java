package com.google.android.gms.measurement.internal;

/* JADX INFO: loaded from: classes.dex */
public final class zzes implements Runnable {
    public final /* synthetic */ boolean zza;
    public final /* synthetic */ zzet zzb;

    public zzes(zzet zzetVar, boolean z) {
        this.zzb = zzetVar;
        this.zza = z;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzb.zzb.zzI(this.zza);
    }
}
