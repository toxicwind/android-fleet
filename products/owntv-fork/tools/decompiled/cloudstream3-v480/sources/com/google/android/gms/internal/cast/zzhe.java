package com.google.android.gms.internal.cast;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
final class zzhe extends zzgw {
    private final zzhg zza;

    zzhe(zzhg zzhgVar, int i) {
        super(zzhgVar.size(), i);
        this.zza = zzhgVar;
    }

    @Override // com.google.android.gms.internal.cast.zzgw
    protected final Object zza(int i) {
        return this.zza.get(i);
    }
}
