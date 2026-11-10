package com.google.android.gms.internal.measurement;

import com.google.android.gms.common.internal.Preconditions;

/* JADX INFO: loaded from: classes.dex */
public final class zzcs extends zzdt {
    public final /* synthetic */ Boolean zza;
    public final /* synthetic */ zzee zzb;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzcs(zzee zzeeVar, Boolean bool) {
        super(zzeeVar, true);
        this.zzb = zzeeVar;
        this.zza = bool;
    }

    @Override // com.google.android.gms.internal.measurement.zzdt
    public final void zza() {
        if (this.zza != null) {
            zzcc zzccVar = this.zzb.zzj;
            Preconditions.checkNotNull(zzccVar);
            zzccVar.setMeasurementEnabled(this.zza.booleanValue(), this.zzh);
        } else {
            zzcc zzccVar2 = this.zzb.zzj;
            Preconditions.checkNotNull(zzccVar2);
            zzccVar2.clearMeasurementEnabled(this.zzh);
        }
    }
}
