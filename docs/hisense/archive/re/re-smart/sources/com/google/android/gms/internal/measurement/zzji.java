package com.google.android.gms.internal.measurement;

/* JADX INFO: loaded from: classes.dex */
public final class zzji {
    public final Object zza;
    public final int zzb;

    public zzji(Object obj, int i) {
        this.zza = obj;
        this.zzb = i;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof zzji)) {
            return false;
        }
        zzji zzjiVar = (zzji) obj;
        return this.zza == zzjiVar.zza && this.zzb == zzjiVar.zzb;
    }

    public final int hashCode() {
        return (System.identityHashCode(this.zza) * 65535) + this.zzb;
    }
}
