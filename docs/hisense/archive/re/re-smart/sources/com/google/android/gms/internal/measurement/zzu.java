package com.google.android.gms.internal.measurement;

import java.util.List;
import java.util.concurrent.Callable;

/* JADX INFO: loaded from: classes.dex */
public final class zzu extends zzai {
    public final Callable<Object> zza;

    public zzu(String str, Callable<Object> callable) {
        super("internal.appMetadata");
        this.zza = callable;
    }

    @Override // com.google.android.gms.internal.measurement.zzai
    public final zzap zza(zzg zzgVar, List<zzap> list) {
        try {
            return zzi.zzb(this.zza.call());
        } catch (Exception unused) {
            return zzap.zzf;
        }
    }
}
