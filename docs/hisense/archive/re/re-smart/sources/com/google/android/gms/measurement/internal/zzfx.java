package com.google.android.gms.measurement.internal;

/* JADX INFO: loaded from: classes.dex */
public final class zzfx implements Runnable {
    public final /* synthetic */ zzab zza;
    public final /* synthetic */ zzp zzb;
    public final /* synthetic */ zzgn zzc;

    public zzfx(zzgn zzgnVar, zzab zzabVar, zzp zzpVar) {
        this.zzc = zzgnVar;
        this.zza = zzabVar;
        this.zzb = zzpVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzc.zza.zzA();
        if (this.zza.zzc.zza() == null) {
            this.zzc.zza.zzN(this.zza, this.zzb);
        } else {
            this.zzc.zza.zzS(this.zza, this.zzb);
        }
    }
}
