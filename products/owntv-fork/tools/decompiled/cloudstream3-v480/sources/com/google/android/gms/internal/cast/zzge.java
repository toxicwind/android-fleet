package com.google.android.gms.internal.cast;

import android.view.Choreographer;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
final class zzge extends zzgf {
    private final Choreographer zza = Choreographer.getInstance();

    @Override // com.google.android.gms.internal.cast.zzgf
    public final void zza(zzgc zzgcVar) {
        this.zza.postFrameCallback(zzgcVar.zzb());
    }
}
