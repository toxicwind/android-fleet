package com.google.android.gms.measurement.internal;

/* JADX INFO: loaded from: classes.dex */
public final class zzfy implements Runnable {
    public final /* synthetic */ zzab zza;
    public final /* synthetic */ zzgn zzb;

    public zzfy(zzgn zzgnVar, zzab zzabVar) {
        this.zzb = zzgnVar;
        this.zza = zzabVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzb.zza.zzA();
        if (this.zza.zzc.zza() == null) {
            this.zzb.zza.zzM(this.zza);
        } else {
            this.zzb.zza.zzR(this.zza);
        }
    }
}
