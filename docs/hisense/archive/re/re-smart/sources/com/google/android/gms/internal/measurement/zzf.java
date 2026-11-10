package com.google.android.gms.internal.measurement;

import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.util.concurrent.Callable;

/* JADX INFO: loaded from: classes.dex */
public final class zzf {
    public final zzax zza = new zzax();
    public final zzg zzc = new zzg(null, this.zza);
    public final zzg zzb = this.zzc.zza();
    public final zzj zzd = new zzj();

    public zzf() {
        this.zzc.zzg("require", new zzw(this.zzd));
        this.zzd.zza("internal.platform", new Callable() { // from class: com.google.android.gms.internal.measurement.zze
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return new zzy();
            }
        });
        this.zzc.zzg("runtime.counter", new zzah(Double.valueOf(FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE)));
    }

    public final zzap zza(zzg zzgVar, zzgt... zzgtVarArr) {
        zzap zzapVarZza = zzap.zzf;
        for (zzgt zzgtVar : zzgtVarArr) {
            zzapVarZza = zzi.zza(zzgtVar);
            zzh.zzc(this.zzc);
            if ((zzapVarZza instanceof zzaq) || (zzapVarZza instanceof zzao)) {
                zzapVarZza = this.zza.zza(zzgVar, zzapVarZza);
            }
        }
        return zzapVarZza;
    }
}
