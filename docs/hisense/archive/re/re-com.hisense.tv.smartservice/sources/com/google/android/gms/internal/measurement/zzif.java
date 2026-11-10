package com.google.android.gms.internal.measurement;

import java.io.Serializable;

/* JADX INFO: loaded from: classes.dex */
public final class zzif {
    public static <T> zzib<T> zza(zzib<T> zzibVar) {
        if ((zzibVar instanceof zzid) || (zzibVar instanceof zzic)) {
            return zzibVar;
        }
        return zzibVar instanceof Serializable ? new zzic(zzibVar) : new zzid(zzibVar);
    }

    public static <T> zzib<T> zzb(T t) {
        return new zzie(t);
    }
}
