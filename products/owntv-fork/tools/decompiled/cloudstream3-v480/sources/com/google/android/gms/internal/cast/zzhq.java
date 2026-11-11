package com.google.android.gms.internal.cast;

import java.util.Iterator;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
final class zzhq extends zzhk {
    private final transient zzhj zza;
    private final transient zzhg zzb;

    zzhq(zzhj zzhjVar, zzhg zzhgVar) {
        this.zza = zzhjVar;
        this.zzb = zzhgVar;
    }

    @Override // com.google.android.gms.internal.cast.zzhc, java.util.AbstractCollection, java.util.Collection
    public final boolean contains(Object obj) {
        return this.zza.get(obj) != null;
    }

    @Override // com.google.android.gms.internal.cast.zzhk, com.google.android.gms.internal.cast.zzhc, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    public final /* synthetic */ Iterator iterator() {
        return this.zzb.listIterator(0);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        return this.zza.size();
    }

    @Override // com.google.android.gms.internal.cast.zzhc
    final int zza(Object[] objArr, int i) {
        return this.zzb.zza(objArr, 0);
    }

    @Override // com.google.android.gms.internal.cast.zzhk, com.google.android.gms.internal.cast.zzhc
    public final zzhg zzd() {
        return this.zzb;
    }

    @Override // com.google.android.gms.internal.cast.zzhk, com.google.android.gms.internal.cast.zzhc
    /* JADX INFO: renamed from: zze */
    public final zzhw iterator() {
        return this.zzb.listIterator(0);
    }

    @Override // com.google.android.gms.internal.cast.zzhc
    final boolean zzf() {
        throw null;
    }
}
