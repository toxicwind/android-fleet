package com.google.android.gms.internal.cast;

import android.os.Handler;
import android.os.Looper;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
final class zzgd extends zzgf {
    private final Handler zza;

    public zzgd(Looper looper) {
        this.zza = new Handler(looper);
    }

    @Override // com.google.android.gms.internal.cast.zzgf
    public final void zza(zzgc zzgcVar) {
        this.zza.postDelayed(zzgcVar.zzc(), 0L);
    }
}
