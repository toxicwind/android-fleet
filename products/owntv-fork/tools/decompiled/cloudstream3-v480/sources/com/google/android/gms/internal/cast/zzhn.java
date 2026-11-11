package com.google.android.gms.internal.cast;

import j$.util.Objects;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
final class zzhn extends zzhg {
    static final zzhg zza = new zzhn(new Object[0], 0);
    final transient Object[] zzb;
    private final transient int zzc;

    zzhn(Object[] objArr, int i) {
        this.zzb = objArr;
        this.zzc = i;
    }

    @Override // java.util.List
    public final Object get(int i) {
        zzgo.zza(i, this.zzc, "index");
        return Objects.requireNonNull(this.zzb[i]);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zzc;
    }

    @Override // com.google.android.gms.internal.cast.zzhg, com.google.android.gms.internal.cast.zzhc
    final int zza(Object[] objArr, int i) {
        Object[] objArr2 = this.zzb;
        int i2 = this.zzc;
        System.arraycopy(objArr2, 0, objArr, 0, i2);
        return i2;
    }

    @Override // com.google.android.gms.internal.cast.zzhc
    final int zzb() {
        return this.zzc;
    }

    @Override // com.google.android.gms.internal.cast.zzhc
    final int zzc() {
        return 0;
    }

    @Override // com.google.android.gms.internal.cast.zzhc
    final boolean zzf() {
        return false;
    }

    @Override // com.google.android.gms.internal.cast.zzhc
    final Object[] zzg() {
        return this.zzb;
    }
}
