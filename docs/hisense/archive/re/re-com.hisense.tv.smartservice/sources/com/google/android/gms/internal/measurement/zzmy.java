package com.google.android.gms.internal.measurement;

/* JADX INFO: loaded from: classes.dex */
public final class zzmy implements zzmx {
    public static final zzhu<Boolean> zza;
    public static final zzhu<Boolean> zzb;

    static {
        zzhr zzhrVar = new zzhr(zzhk.zza("com.google.android.gms.measurement"));
        zza = zzhrVar.zze("measurement.androidId.delete_feature", true);
        zzb = zzhrVar.zze("measurement.log_androidId_enabled", false);
    }

    @Override // com.google.android.gms.internal.measurement.zzmx
    public final boolean zza() {
        return zza.zzb().booleanValue();
    }
}
