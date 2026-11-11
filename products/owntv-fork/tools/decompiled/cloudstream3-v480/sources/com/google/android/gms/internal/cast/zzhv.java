package com.google.android.gms.internal.cast;

import java.util.Iterator;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
final class zzhv extends zzhk {
    final transient Object zza;

    zzhv(Object obj) {
        obj.getClass();
        this.zza = obj;
    }

    @Override // com.google.android.gms.internal.cast.zzhc, java.util.AbstractCollection, java.util.Collection
    public final boolean contains(Object obj) {
        return this.zza.equals(obj);
    }

    @Override // com.google.android.gms.internal.cast.zzhk, java.util.Collection, java.util.Set
    public final int hashCode() {
        return this.zza.hashCode();
    }

    @Override // com.google.android.gms.internal.cast.zzhk, com.google.android.gms.internal.cast.zzhc, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    public final /* synthetic */ Iterator iterator() {
        return new zzhl(this.zza);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        return 1;
    }

    @Override // java.util.AbstractCollection
    public final String toString() {
        return "[" + this.zza.toString() + "]";
    }

    @Override // com.google.android.gms.internal.cast.zzhc
    final int zza(Object[] objArr, int i) {
        objArr[0] = this.zza;
        return 1;
    }

    @Override // com.google.android.gms.internal.cast.zzhk, com.google.android.gms.internal.cast.zzhc
    public final zzhg zzd() {
        int i = zzhg.zzd;
        Object[] objArr = {this.zza};
        zzhm.zzb(objArr, 1);
        return zzhg.zzi(objArr, 1);
    }

    @Override // com.google.android.gms.internal.cast.zzhk, com.google.android.gms.internal.cast.zzhc
    /* JADX INFO: renamed from: zze */
    public final zzhw iterator() {
        return new zzhl(this.zza);
    }

    @Override // com.google.android.gms.internal.cast.zzhc
    final boolean zzf() {
        throw null;
    }
}
