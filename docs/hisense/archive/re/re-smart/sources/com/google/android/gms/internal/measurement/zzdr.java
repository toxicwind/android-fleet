package com.google.android.gms.internal.measurement;

import android.os.Bundle;
import com.google.android.gms.common.internal.Preconditions;

/* JADX INFO: loaded from: classes.dex */
public final class zzdr extends zzdt {
    public final /* synthetic */ Long zza;
    public final /* synthetic */ String zzb;
    public final /* synthetic */ String zzc;
    public final /* synthetic */ Bundle zzd;
    public final /* synthetic */ boolean zze;
    public final /* synthetic */ boolean zzf;
    public final /* synthetic */ zzee zzg;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzdr(zzee zzeeVar, Long l, String str, String str2, Bundle bundle, boolean z, boolean z2) {
        super(zzeeVar, true);
        this.zzg = zzeeVar;
        this.zza = l;
        this.zzb = str;
        this.zzc = str2;
        this.zzd = bundle;
        this.zze = z;
        this.zzf = z2;
    }

    @Override // com.google.android.gms.internal.measurement.zzdt
    public final void zza() {
        Long l = this.zza;
        long jLongValue = l == null ? this.zzh : l.longValue();
        zzcc zzccVar = this.zzg.zzj;
        Preconditions.checkNotNull(zzccVar);
        zzccVar.logEvent(this.zzb, this.zzc, this.zzd, this.zze, this.zzf, jLongValue);
    }
}
