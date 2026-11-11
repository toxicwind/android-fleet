package com.google.android.gms.internal.cast;

import java.util.Iterator;
import java.util.Map;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
final class zzhp extends zzhk {
    private final transient zzhj zza;
    private final transient Object[] zzb;
    private final transient int zzc;

    zzhp(zzhj zzhjVar, Object[] objArr, int i, int i2) {
        this.zza = zzhjVar;
        this.zzb = objArr;
        this.zzc = i2;
    }

    @Override // com.google.android.gms.internal.cast.zzhc, java.util.AbstractCollection, java.util.Collection
    public final boolean contains(Object obj) {
        if (obj instanceof Map.Entry) {
            Map.Entry entry = (Map.Entry) obj;
            Object key = entry.getKey();
            Object value = entry.getValue();
            if (value != null && value.equals(this.zza.get(key))) {
                return true;
            }
        }
        return false;
    }

    @Override // com.google.android.gms.internal.cast.zzhk, com.google.android.gms.internal.cast.zzhc, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    public final /* synthetic */ Iterator iterator() {
        return zzd().listIterator(0);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        return this.zzc;
    }

    @Override // com.google.android.gms.internal.cast.zzhc
    final int zza(Object[] objArr, int i) {
        return zzd().zza(objArr, 0);
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

    @Override // com.google.android.gms.internal.cast.zzhk
    final zzhg zzi() {
        return new zzho(this);
    }
}
