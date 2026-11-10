package com.google.android.gms.measurement.internal;

import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class zzfl implements com.google.android.gms.internal.measurement.zzo {
    public final /* synthetic */ String zza;
    public final /* synthetic */ zzfm zzb;

    public zzfl(zzfm zzfmVar, String str) {
        this.zzb = zzfmVar;
        this.zza = str;
    }

    @Override // com.google.android.gms.internal.measurement.zzo
    public final String zza(String str) {
        Map<String, String> map = this.zzb.zze.get(this.zza);
        if (map == null || !map.containsKey(str)) {
            return null;
        }
        return map.get(str);
    }
}
