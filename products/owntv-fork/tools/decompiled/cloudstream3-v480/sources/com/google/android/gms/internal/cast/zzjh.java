package com.google.android.gms.internal.cast;

import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Level;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
final class zzjh extends zzjm {
    private static final zzjh zza = new zzjh(zzjm.zze());
    private final AtomicReference zzb;

    zzjh(zzjm zzjmVar) {
        this.zzb = new AtomicReference(zzjmVar);
    }

    public static final zzjh zzb() {
        return zza;
    }

    @Override // com.google.android.gms.internal.cast.zzjm
    public final zzik zza() {
        return ((zzjm) this.zzb.get()).zza();
    }

    @Override // com.google.android.gms.internal.cast.zzjm
    public final zzjy zzc() {
        return ((zzjm) this.zzb.get()).zzc();
    }

    @Override // com.google.android.gms.internal.cast.zzjm
    public final boolean zzd(String str, Level level, boolean z) {
        ((zzjm) this.zzb.get()).zzd(str, level, z);
        return false;
    }
}
