package com.google.android.gms.internal.measurement;

import b.a.a.a.a;
import java.io.Serializable;

/* JADX INFO: loaded from: classes.dex */
public final class zzic<T> implements Serializable, zzib {
    public final zzib<T> zza;
    public volatile transient boolean zzb;
    public transient T zzc;

    public zzic(zzib<T> zzibVar) {
        if (zzibVar == null) {
            throw null;
        }
        this.zza = zzibVar;
    }

    public final String toString() {
        Object objA;
        if (this.zzb) {
            String strValueOf = String.valueOf(this.zzc);
            objA = a.a(new StringBuilder(strValueOf.length() + 25), "<supplier that returned ", strValueOf, ">");
        } else {
            objA = this.zza;
        }
        String strValueOf2 = String.valueOf(objA);
        return a.a(new StringBuilder(strValueOf2.length() + 19), "Suppliers.memoize(", strValueOf2, ")");
    }

    @Override // com.google.android.gms.internal.measurement.zzib
    public final T zza() {
        if (!this.zzb) {
            synchronized (this) {
                if (!this.zzb) {
                    T tZza = this.zza.zza();
                    this.zzc = tZza;
                    this.zzb = true;
                    return tZza;
                }
            }
        }
        return this.zzc;
    }
}
