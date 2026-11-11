package com.google.android.gms.internal.cast;

import android.animation.Animator;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
final class zzfx extends zzgc {
    final /* synthetic */ zzfy zza;

    zzfx(zzfy zzfyVar) {
        this.zza = zzfyVar;
    }

    @Override // com.google.android.gms.internal.cast.zzgc
    public final void zza(long j) {
        zzfy zzfyVar = this.zza;
        zzfyVar.zzc++;
        Animator animator = zzfyVar.zza;
        if (zzfyVar.zza(animator) || animator.isStarted() || zzfy.zze(zzfyVar)) {
            return;
        }
        animator.start();
    }
}
