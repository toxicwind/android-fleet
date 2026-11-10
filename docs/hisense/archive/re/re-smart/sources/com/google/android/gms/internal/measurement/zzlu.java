package com.google.android.gms.internal.measurement;

import b.a.a.a.a;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class zzlu implements Map.Entry, Comparable<zzlu> {
    public final /* synthetic */ zzlx zza;
    public final Comparable zzb;
    public Object zzc;

    public zzlu(zzlx zzlxVar, Comparable comparable, Object obj) {
        this.zza = zzlxVar;
        this.zzb = comparable;
        this.zzc = obj;
    }

    public static final boolean zzb(Object obj, Object obj2) {
        if (obj == null) {
            return obj2 == null;
        }
        return obj.equals(obj2);
    }

    @Override // java.lang.Comparable
    public final /* bridge */ /* synthetic */ int compareTo(zzlu zzluVar) {
        return this.zzb.compareTo(zzluVar.zzb);
    }

    @Override // java.util.Map.Entry
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        return zzb(this.zzb, entry.getKey()) && zzb(this.zzc, entry.getValue());
    }

    @Override // java.util.Map.Entry
    public final /* synthetic */ Object getKey() {
        return this.zzb;
    }

    @Override // java.util.Map.Entry
    public final Object getValue() {
        return this.zzc;
    }

    @Override // java.util.Map.Entry
    public final int hashCode() {
        Comparable comparable = this.zzb;
        int iHashCode = comparable == null ? 0 : comparable.hashCode();
        Object obj = this.zzc;
        return iHashCode ^ (obj != null ? obj.hashCode() : 0);
    }

    @Override // java.util.Map.Entry
    public final Object setValue(Object obj) {
        this.zza.zzn();
        Object obj2 = this.zzc;
        this.zzc = obj;
        return obj2;
    }

    public final String toString() {
        String strValueOf = String.valueOf(this.zzb);
        String strValueOf2 = String.valueOf(this.zzc);
        return a.a(new StringBuilder(strValueOf.length() + 1 + strValueOf2.length()), strValueOf, "=", strValueOf2);
    }

    public final Comparable zza() {
        return this.zzb;
    }
}
