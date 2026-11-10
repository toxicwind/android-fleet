package com.google.android.gms.internal.measurement;

import b.a.a.a.a;

/* JADX INFO: loaded from: classes.dex */
public final class zzid<T> implements zzib<T> {
    public volatile zzib<T> zza;
    public volatile boolean zzb;
    public T zzc;

    public zzid(zzib<T> zzibVar) {
        if (zzibVar == null) {
            throw null;
        }
        this.zza = zzibVar;
    }

    public final String toString() {
        Object objA = this.zza;
        if (objA == null) {
            String strValueOf = String.valueOf(this.zzc);
            objA = a.a(new StringBuilder(strValueOf.length() + 25), "<supplier that returned ", strValueOf, ">");
        }
        String string = objA.toString();
        return a.a(new StringBuilder(string.length() + 19), "Suppliers.memoize(", string, ")");
    }

    @Override // com.google.android.gms.internal.measurement.zzib
    public final T zza() {
        if (!this.zzb) {
            synchronized (this) {
                if (!this.zzb) {
                    zzib<T> zzibVar = this.zza;
                    zzibVar.getClass();
                    T tZza = zzibVar.zza();
                    this.zzc = tZza;
                    this.zzb = true;
                    this.zza = null;
                    return tZza;
                }
            }
        }
        return this.zzc;
    }
}
