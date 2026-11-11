package com.google.android.gms.internal.cast;

import java.io.Serializable;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
public abstract class zzgn implements Serializable {
    zzgn() {
    }

    public static zzgn zzb() {
        return zzgj.zza;
    }

    public static zzgn zzc(Object obj) {
        return obj == null ? zzgj.zza : new zzgp(obj);
    }

    public abstract Object zza(Object obj);
}
