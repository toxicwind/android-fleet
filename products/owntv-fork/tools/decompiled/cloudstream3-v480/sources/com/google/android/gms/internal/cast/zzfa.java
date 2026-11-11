package com.google.android.gms.internal.cast;

import com.google.android.gms.common.Feature;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
public final class zzfa {
    public static final Feature zza;
    public static final Feature zzb;
    public static final Feature zzc;
    public static final Feature zzd;
    public static final Feature zze;
    public static final Feature[] zzf;

    static {
        Feature feature = new Feature("usage_and_diagnostics_listener", 1L);
        zza = feature;
        Feature feature2 = new Feature("usage_and_diagnostics_consents", 1L);
        zzb = feature2;
        Feature feature3 = new Feature("usage_and_diagnostics_check_consents", 1L);
        zzc = feature3;
        Feature feature4 = new Feature("usage_and_diagnostics_settings_access", 1L);
        zzd = feature4;
        Feature feature5 = new Feature("el_capitan", 1L);
        zze = feature5;
        zzf = new Feature[]{feature, feature2, feature3, feature4, feature5};
    }
}
