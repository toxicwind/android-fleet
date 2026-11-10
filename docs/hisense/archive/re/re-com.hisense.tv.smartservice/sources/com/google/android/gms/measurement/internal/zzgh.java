package com.google.android.gms.measurement.internal;

/* JADX INFO: loaded from: classes.dex */
public final class zzgh implements Runnable {
    public final /* synthetic */ zzat zza;
    public final /* synthetic */ String zzb;
    public final /* synthetic */ zzgn zzc;

    public zzgh(zzgn zzgnVar, zzat zzatVar, String str) {
        this.zzc = zzgnVar;
        this.zza = zzatVar;
        this.zzb = str;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzc.zza.zzA();
        this.zzc.zza.zzE(this.zza, this.zzb);
    }
}
