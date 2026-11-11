package com.google.android.gms.internal.cast;

import java.util.Iterator;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
final class zzht extends zzhk {
    static final zzht zza;
    private static final Object[] zzd;
    final transient Object[] zzb;
    final transient Object[] zzc;
    private final transient int zze;
    private final transient int zzf;
    private final transient int zzg;

    static {
        Object[] objArr = new Object[0];
        zzd = objArr;
        zza = new zzht(objArr, 0, objArr, 0, 0);
    }

    zzht(Object[] objArr, int i, Object[] objArr2, int i2, int i3) {
        this.zzb = objArr;
        this.zze = i;
        this.zzc = objArr2;
        this.zzf = i2;
        this.zzg = i3;
    }

    @Override // com.google.android.gms.internal.cast.zzhc, java.util.AbstractCollection, java.util.Collection
    public final boolean contains(Object obj) {
        if (obj != null) {
            Object[] objArr = this.zzc;
            if (objArr.length != 0) {
                int iZza = zzgz.zza(obj.hashCode());
                while (true) {
                    int i = iZza & this.zzf;
                    Object obj2 = objArr[i];
                    if (obj2 == null) {
                        return false;
                    }
                    if (obj2.equals(obj)) {
                        return true;
                    }
                    iZza = i + 1;
                }
            }
        }
        return false;
    }

    @Override // com.google.android.gms.internal.cast.zzhk, java.util.Collection, java.util.Set
    public final int hashCode() {
        return this.zze;
    }

    @Override // com.google.android.gms.internal.cast.zzhk, com.google.android.gms.internal.cast.zzhc, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    public final /* synthetic */ Iterator iterator() {
        return zzd().listIterator(0);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        return this.zzg;
    }

    @Override // com.google.android.gms.internal.cast.zzhc
    final int zza(Object[] objArr, int i) {
        Object[] objArr2 = this.zzb;
        int i2 = this.zzg;
        System.arraycopy(objArr2, 0, objArr, 0, i2);
        return i2;
    }

    @Override // com.google.android.gms.internal.cast.zzhc
    final int zzb() {
        return this.zzg;
    }

    @Override // com.google.android.gms.internal.cast.zzhc
    final int zzc() {
        return 0;
    }

    @Override // com.google.android.gms.internal.cast.zzhk, com.google.android.gms.internal.cast.zzhc
    /* JADX INFO: renamed from: zze */
    public final zzhw iterator() {
        return zzd().listIterator(0);
    }

    @Override // com.google.android.gms.internal.cast.zzhc
    final boolean zzf() {
        throw null;
    }

    @Override // com.google.android.gms.internal.cast.zzhc
    final Object[] zzg() {
        return this.zzb;
    }

    @Override // com.google.android.gms.internal.cast.zzhk
    final zzhg zzi() {
        return zzhg.zzi(this.zzb, this.zzg);
    }

    @Override // com.google.android.gms.internal.cast.zzhk
    final boolean zzl() {
        return true;
    }
}
