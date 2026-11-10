package com.google.android.gms.internal.measurement;

/* JADX INFO: loaded from: classes.dex */
public final class zzmv implements zzmu {
    public static final zzhu<Boolean> zza;
    public static final zzhu<Boolean> zzb;
    public static final zzhu<Boolean> zzc;
    public static final zzhu<Long> zzd;
    public static final zzhu<Boolean> zze;
    public static final zzhu<Boolean> zzf;

    static {
        zzhr zzhrVarZza = new zzhr(zzhk.zza("com.google.android.gms.measurement")).zza();
        zza = zzhrVarZza.zze("measurement.adid_zero.app_instance_id_fix", true);
        zzb = zzhrVarZza.zze("measurement.adid_zero.service", false);
        zzc = zzhrVarZza.zze("measurement.adid_zero.adid_uid", false);
        zzd = zzhrVarZza.zzc("measurement.id.adid_zero.service", 0L);
        zze = zzhrVarZza.zze("measurement.adid_zero.remove_lair_if_adidzero_false", true);
        zzf = zzhrVarZza.zze("measurement.adid_zero.remove_lair_if_userid_cleared", true);
    }

    @Override // com.google.android.gms.internal.measurement.zzmu
    public final boolean zza() {
        return true;
    }

    @Override // com.google.android.gms.internal.measurement.zzmu
    public final boolean zzb() {
        return zza.zzb().booleanValue();
    }

    @Override // com.google.android.gms.internal.measurement.zzmu
    public final boolean zzc() {
        return zzb.zzb().booleanValue();
    }

    @Override // com.google.android.gms.internal.measurement.zzmu
    public final boolean zzd() {
        return zzc.zzb().booleanValue();
    }

    @Override // com.google.android.gms.internal.measurement.zzmu
    public final boolean zze() {
        return zze.zzb().booleanValue();
    }

    @Override // com.google.android.gms.internal.measurement.zzmu
    public final boolean zzf() {
        return zzf.zzb().booleanValue();
    }
}
