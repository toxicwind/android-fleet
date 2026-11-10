package com.google.android.gms.measurement.internal;

import com.google.android.gms.internal.measurement.zzpe;

/* JADX INFO: loaded from: classes.dex */
public final class zzgg implements Runnable {
    public final /* synthetic */ zzat zza;
    public final /* synthetic */ zzp zzb;
    public final /* synthetic */ zzgn zzc;

    public zzgg(zzgn zzgnVar, zzat zzatVar, zzp zzpVar) {
        this.zzc = zzgnVar;
        this.zza = zzatVar;
        this.zzb = zzpVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzat zzatVarZzb = this.zzc.zzb(this.zza, this.zzb);
        zzpe.zzc();
        if (this.zzc.zza.zzg().zzs(null, zzdy.zzat)) {
            this.zzc.zzw(zzatVarZzb, this.zzb);
        } else {
            zzgn.zzv(this.zzc, zzatVarZzb, this.zzb);
        }
    }
}
