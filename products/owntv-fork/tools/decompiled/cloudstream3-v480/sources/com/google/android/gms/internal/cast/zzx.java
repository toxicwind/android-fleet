package com.google.android.gms.internal.cast;

import com.google.android.gms.common.internal.Preconditions;
import org.checkerframework.checker.nullness.qual.EnsuresNonNull;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
public final class zzx {
    private final zzh zza;
    private final zzaf zzb;
    private final String zzc;
    private zzz zzd;
    private final zzt zze = new zzt(this, null);

    public zzx(zzh zzhVar, zzaf zzafVar, String str) {
        this.zza = zzhVar;
        this.zzb = zzafVar;
        this.zzc = str;
    }

    static /* bridge */ /* synthetic */ void zzf(zzx zzxVar, zzcb zzcbVar) {
        if (zzcbVar.zzc() == 2 && zzxVar.zzd != null) {
            zzxVar.zzh();
        }
        if (zzcbVar.zzc() == 2) {
            zzxVar.zzd = zzz.zza(zzxVar.zza, zzxVar.zzc);
        } else {
            zzxVar.zzd = zzxVar.zzg();
        }
        ((zzz) Preconditions.checkNotNull(zzxVar.zzd)).zzc(zzcbVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    @EnsuresNonNull({"SessionFlowSummary"})
    public final zzz zzg() {
        if (this.zzd == null) {
            zzz zzzVarZza = zzz.zza(this.zza, this.zzc);
            this.zzd = zzzVarZza;
            zzzVarZza.zzj(1);
        }
        return this.zzd;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzh() {
        zzz zzzVar = this.zzd;
        if (zzzVar != null) {
            zzzVar.zzf();
            this.zzd = null;
        }
    }
}
