package com.google.android.gms.measurement.internal;

/* JADX INFO: loaded from: classes.dex */
public final class zzgd implements Runnable {
    public final /* synthetic */ zzp zza;
    public final /* synthetic */ zzgn zzb;

    public zzgd(zzgn zzgnVar, zzp zzpVar) {
        this.zzb = zzgnVar;
        this.zza = zzpVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzb.zza.zzA();
        this.zzb.zza.zzP(this.zza);
    }
}
