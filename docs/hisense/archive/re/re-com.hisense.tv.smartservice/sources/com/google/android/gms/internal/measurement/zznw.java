package com.google.android.gms.internal.measurement;

/* JADX INFO: loaded from: classes.dex */
public final class zznw implements zznv {
    public static final zzhu<Boolean> zza = new zzhr(zzhk.zza("com.google.android.gms.measurement")).zze("measurement.client.firebase_feature_rollout.v1.enable", true);

    @Override // com.google.android.gms.internal.measurement.zznv
    public final boolean zza() {
        return true;
    }

    @Override // com.google.android.gms.internal.measurement.zznv
    public final boolean zzb() {
        return zza.zzb().booleanValue();
    }
}
