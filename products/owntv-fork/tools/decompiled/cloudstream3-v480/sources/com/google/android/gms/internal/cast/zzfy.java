package com.google.android.gms.internal.cast;

import android.animation.Animator;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
public final class zzfy extends zzfw {
    protected final Animator zza;
    private int zzc;
    private final zzgc zzd = new zzfx(this);
    private final int zzb = -1;

    private zzfy(Animator animator, int i, Runnable runnable) {
        this.zza = animator;
    }

    public static void zzd(Animator animator, int i, Runnable runnable) {
        animator.addListener(new zzfy(animator, -1, null));
    }

    static /* bridge */ /* synthetic */ boolean zze(zzfy zzfyVar) {
        return zzfyVar.zzb != -1 && zzfyVar.zzc >= 0;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        if (zza(animator)) {
            return;
        }
        zzgf.zzb().zza(this.zzd);
    }
}
