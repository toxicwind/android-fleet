package com.google.android.gms.internal.measurement;

import com.google.android.gms.common.internal.Preconditions;

/* JADX INFO: loaded from: classes.dex */
public final class zzcv extends zzdt {
    public final /* synthetic */ zzee zza;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzcv(zzee zzeeVar) {
        super(zzeeVar, true);
        this.zza = zzeeVar;
    }

    @Override // com.google.android.gms.internal.measurement.zzdt
    public final void zza() {
        zzcc zzccVar = this.zza.zzj;
        Preconditions.checkNotNull(zzccVar);
        zzccVar.resetAnalyticsData(this.zzh);
    }
}
