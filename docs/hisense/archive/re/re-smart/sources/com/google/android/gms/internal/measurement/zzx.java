package com.google.android.gms.internal.measurement;

import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class zzx extends zzai {
    public zzx(zzy zzyVar, String str) {
        super("getVersion");
    }

    @Override // com.google.android.gms.internal.measurement.zzai
    public final zzap zza(zzg zzgVar, List<zzap> list) {
        return new zzah(Double.valueOf(FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE));
    }
}
