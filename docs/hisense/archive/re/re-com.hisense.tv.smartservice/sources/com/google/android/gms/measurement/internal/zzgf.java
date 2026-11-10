package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.Preconditions;

/* JADX INFO: loaded from: classes.dex */
public final class zzgf implements Runnable {
    public final /* synthetic */ zzp zza;
    public final /* synthetic */ zzgn zzb;

    public zzgf(zzgn zzgnVar, zzp zzpVar) {
        this.zzb = zzgnVar;
        this.zza = zzpVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzb.zza.zzA();
        zzks zzksVar = this.zzb.zza;
        zzp zzpVar = this.zza;
        zzksVar.zzaz().zzg();
        zzksVar.zzB();
        Preconditions.checkNotEmpty(zzpVar.zza);
        zzag zzagVarZzb = zzag.zzb(zzpVar.zzv);
        zzag zzagVarZzh = zzksVar.zzh(zzpVar.zza);
        zzksVar.zzay().zzj().zzc("Setting consent, package, consent", zzpVar.zza, zzagVarZzb);
        zzksVar.zzT(zzpVar.zza, zzagVarZzb);
        if (zzagVarZzb.zzm(zzagVarZzh)) {
            zzksVar.zzP(zzpVar);
        }
    }
}
