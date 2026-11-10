package com.google.android.gms.internal.measurement;

import com.google.android.gms.common.internal.Preconditions;

/* JADX INFO: loaded from: classes.dex */
public final class zzdk extends zzdt {
    public final /* synthetic */ zzbz zza;
    public final /* synthetic */ zzee zzb;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzdk(zzee zzeeVar, zzbz zzbzVar) {
        super(zzeeVar, true);
        this.zzb = zzeeVar;
        this.zza = zzbzVar;
    }

    @Override // com.google.android.gms.internal.measurement.zzdt
    public final void zza() {
        zzcc zzccVar = this.zzb.zzj;
        Preconditions.checkNotNull(zzccVar);
        zzccVar.getAppInstanceId(this.zza);
    }

    @Override // com.google.android.gms.internal.measurement.zzdt
    public final void zzb() {
        this.zza.zzd(null);
    }
}
