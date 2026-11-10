package com.google.android.gms.internal.measurement;

/* JADX INFO: loaded from: classes.dex */
public final class zzpg implements zzpf {
    public static final zzhu<Boolean> zza;
    public static final zzhu<Boolean> zzb;

    static {
        zzhr zzhrVar = new zzhr(zzhk.zza("com.google.android.gms.measurement"));
        zza = zzhrVar.zze("measurement.module.pixie.ees", true);
        zzb = zzhrVar.zze("measurement.module.pixie.fix_array", true);
    }

    @Override // com.google.android.gms.internal.measurement.zzpf
    public final boolean zza() {
        return true;
    }

    @Override // com.google.android.gms.internal.measurement.zzpf
    public final boolean zzb() {
        return zza.zzb().booleanValue();
    }

    @Override // com.google.android.gms.internal.measurement.zzpf
    public final boolean zzc() {
        return zzb.zzb().booleanValue();
    }
}
