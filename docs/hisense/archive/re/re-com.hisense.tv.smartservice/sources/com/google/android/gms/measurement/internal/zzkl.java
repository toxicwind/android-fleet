package com.google.android.gms.measurement.internal;

import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class zzkl implements zzen {
    public final /* synthetic */ String zza;
    public final /* synthetic */ zzks zzb;

    public zzkl(zzks zzksVar, String str) {
        this.zzb = zzksVar;
        this.zza = str;
    }

    @Override // com.google.android.gms.measurement.internal.zzen
    public final void zza(String str, int i, Throwable th, byte[] bArr, Map<String, List<String>> map) {
        this.zzb.zzJ(i, th, bArr, this.zza);
    }
}
