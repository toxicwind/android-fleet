package com.google.android.gms.internal.measurement;

import b.a.a.a.a;

/* JADX INFO: loaded from: classes.dex */
public final class zzia<T> extends zzhz<T> {
    public final T zza;

    public zzia(T t) {
        this.zza = t;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof zzia) {
            return this.zza.equals(((zzia) obj).zza);
        }
        return false;
    }

    public final int hashCode() {
        return this.zza.hashCode() + 1502476572;
    }

    public final String toString() {
        String string = this.zza.toString();
        return a.a(new StringBuilder(string.length() + 13), "Optional.of(", string, ")");
    }

    @Override // com.google.android.gms.internal.measurement.zzhz
    public final T zza() {
        return this.zza;
    }

    @Override // com.google.android.gms.internal.measurement.zzhz
    public final boolean zzb() {
        return true;
    }
}
