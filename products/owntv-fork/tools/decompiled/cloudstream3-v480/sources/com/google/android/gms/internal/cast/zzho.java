package com.google.android.gms.internal.cast;

import j$.util.Objects;
import java.util.AbstractMap;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
final class zzho extends zzhg {
    final /* synthetic */ zzhp zza;

    zzho(zzhp zzhpVar) {
        this.zza = zzhpVar;
    }

    @Override // java.util.List
    public final /* bridge */ /* synthetic */ Object get(int i) {
        zzhp zzhpVar = this.zza;
        zzgo.zza(i, zzhpVar.zzc, "index");
        int i2 = i + i;
        return new AbstractMap.SimpleImmutableEntry(Objects.requireNonNull(zzhpVar.zzb[i2]), Objects.requireNonNull(zzhpVar.zzb[i2 + 1]));
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zza.zzc;
    }

    @Override // com.google.android.gms.internal.cast.zzhc
    public final boolean zzf() {
        return true;
    }
}
