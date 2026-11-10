package com.google.android.gms.internal.measurement;

import com.google.android.gms.common.internal.Preconditions;

/* JADX INFO: loaded from: classes.dex */
public final class zzdj extends zzdt {
    public final /* synthetic */ String zza;
    public final /* synthetic */ zzbz zzb;
    public final /* synthetic */ zzee zzc;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzdj(zzee zzeeVar, String str, zzbz zzbzVar) {
        super(zzeeVar, true);
        this.zzc = zzeeVar;
        this.zza = str;
        this.zzb = zzbzVar;
    }

    @Override // com.google.android.gms.internal.measurement.zzdt
    public final void zza() {
        zzcc zzccVar = this.zzc.zzj;
        Preconditions.checkNotNull(zzccVar);
        zzccVar.getMaxUserProperties(this.zza, this.zzb);
    }

    @Override // com.google.android.gms.internal.measurement.zzdt
    public final void zzb() {
        this.zzb.zzd(null);
    }
}
