package com.google.android.gms.internal.cast;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
final class zzyc implements zzyj {
    private final zzyj[] zza;

    zzyc(zzyj... zzyjVarArr) {
        this.zza = zzyjVarArr;
    }

    @Override // com.google.android.gms.internal.cast.zzyj
    public final zzyi zzb(Class cls) {
        for (int i = 0; i < 2; i++) {
            zzyj zzyjVar = this.zza[i];
            if (zzyjVar.zzc(cls)) {
                return zzyjVar.zzb(cls);
            }
        }
        throw new UnsupportedOperationException("No factory is available for message type: ".concat(String.valueOf(cls.getName())));
    }

    @Override // com.google.android.gms.internal.cast.zzyj
    public final boolean zzc(Class cls) {
        for (int i = 0; i < 2; i++) {
            if (this.zza[i].zzc(cls)) {
                return true;
            }
        }
        return false;
    }
}
