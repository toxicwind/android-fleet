package com.google.android.gms.internal.cast;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
final class zzyd implements zzyw {
    private static final zzyj zza = new zzyb();
    private final zzyj zzb;

    public zzyd() {
        zzyj zzyjVar = zza;
        int i = zzys.zza;
        zzyc zzycVar = new zzyc(zzxe.zza(), zzyjVar);
        byte[] bArr = zzxo.zzb;
        this.zzb = zzycVar;
    }

    @Override // com.google.android.gms.internal.cast.zzyw
    public final zzyv zza(Class cls) {
        int i = zzyx.zza;
        if (!zzxi.class.isAssignableFrom(cls)) {
            int i2 = zzys.zza;
        }
        zzyi zzyiVarZzb = this.zzb.zzb(cls);
        if (zzyiVarZzb.zzb()) {
            int i3 = zzys.zza;
            return zzyp.zzi(zzyx.zzm(), zzwz.zza(), zzyiVarZzb.zza());
        }
        int i4 = zzys.zza;
        return zzyo.zzi(cls, zzyiVarZzb, zzyr.zza(), zzxz.zza(), zzyx.zzm(), zzyiVarZzb.zzc() + (-1) != 1 ? zzwz.zza() : null, zzyh.zza());
    }
}
