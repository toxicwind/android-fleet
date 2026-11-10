package com.google.android.gms.internal.measurement;

import b.a.a.a.a;
import java.io.Serializable;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public final class zzie<T> implements Serializable, zzib {
    public final T zza;

    public zzie(T t) {
        this.zza = t;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof zzie)) {
            return false;
        }
        T t = this.zza;
        T t2 = ((zzie) obj).zza;
        return t == t2 || t.equals(t2);
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.zza});
    }

    public final String toString() {
        String string = this.zza.toString();
        return a.a(new StringBuilder(string.length() + 22), "Suppliers.ofInstance(", string, ")");
    }

    @Override // com.google.android.gms.internal.measurement.zzib
    public final T zza() {
        return this.zza;
    }
}
