package com.google.android.gms.internal.measurement;

/* JADX INFO: loaded from: classes.dex */
public final class zzox implements zzow {
    public static final zzhu<Boolean> zza;
    public static final zzhu<Long> zzb;

    static {
        zzhr zzhrVar = new zzhr(zzhk.zza("com.google.android.gms.measurement"));
        zza = zzhrVar.zze("measurement.validation.internal_limits_internal_event_params", false);
        zzb = zzhrVar.zzc("measurement.id.validation.internal_limits_internal_event_params", 0L);
    }

    @Override // com.google.android.gms.internal.measurement.zzow
    public final boolean zza() {
        return zza.zzb().booleanValue();
    }
}
