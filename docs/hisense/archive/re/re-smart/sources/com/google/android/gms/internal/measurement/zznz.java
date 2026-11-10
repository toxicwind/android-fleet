package com.google.android.gms.internal.measurement;

/* JADX INFO: loaded from: classes.dex */
public final class zznz implements zzny {
    public static final zzhu<Boolean> zza;
    public static final zzhu<Boolean> zzb;

    static {
        zzhr zzhrVar = new zzhr(zzhk.zza("com.google.android.gms.measurement"));
        zza = zzhrVar.zze("measurement.client.consent.suppress_1p_in_ga4f_install", true);
        zzb = zzhrVar.zze("measurement.client.consent.gmpappid_worker_thread_fix", true);
    }

    @Override // com.google.android.gms.internal.measurement.zzny
    public final boolean zza() {
        return true;
    }

    @Override // com.google.android.gms.internal.measurement.zzny
    public final boolean zzb() {
        return zza.zzb().booleanValue();
    }

    @Override // com.google.android.gms.internal.measurement.zzny
    public final boolean zzc() {
        return zzb.zzb().booleanValue();
    }
}
