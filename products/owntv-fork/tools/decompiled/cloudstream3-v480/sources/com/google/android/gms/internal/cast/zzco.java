package com.google.android.gms.internal.cast;

import com.google.android.gms.cast.Cast;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
final class zzco extends Cast.Listener {
    final /* synthetic */ zzcp zza;

    zzco(zzcp zzcpVar) {
        this.zza = zzcpVar;
    }

    @Override // com.google.android.gms.cast.Cast.Listener
    public final void onVolumeChanged() {
        this.zza.zza();
    }
}
