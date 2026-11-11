package com.google.android.gms.internal.cast;

import android.app.Activity;
import android.view.ViewGroup;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
final class zzar implements com.google.android.gms.cast.framework.internal.featurehighlight.zzg {
    final /* synthetic */ Activity zza;
    final /* synthetic */ com.google.android.gms.cast.framework.internal.featurehighlight.zzh zzb;
    final /* synthetic */ zzas zzc;

    zzar(zzas zzasVar, Activity activity, com.google.android.gms.cast.framework.internal.featurehighlight.zzh zzhVar) {
        this.zza = activity;
        this.zzb = zzhVar;
        this.zzc = zzasVar;
    }

    @Override // com.google.android.gms.cast.framework.internal.featurehighlight.zzg
    public final void zza() {
        if (this.zzc.zzf) {
            final Activity activity = this.zza;
            com.google.android.gms.cast.framework.zzbf.zza(activity);
            this.zzb.zzh(new Runnable() { // from class: com.google.android.gms.internal.cast.zzaq
                @Override // java.lang.Runnable
                public final void run() {
                    zzas zzasVar = this.zza.zzc;
                    if (zzasVar.zzf) {
                        ((ViewGroup) activity.getWindow().getDecorView()).removeView(zzasVar);
                        if (zzasVar.zzc != null) {
                            zzasVar.zzc.onOverlayDismissed();
                        }
                        zzasVar.zzd();
                    }
                }
            });
        }
    }

    @Override // com.google.android.gms.cast.framework.internal.featurehighlight.zzg
    public final void zzb() {
        if (this.zzc.zzf) {
            final Activity activity = this.zza;
            com.google.android.gms.cast.framework.zzbf.zza(activity);
            this.zzb.zzg(new Runnable() { // from class: com.google.android.gms.internal.cast.zzap
                @Override // java.lang.Runnable
                public final void run() {
                    zzas zzasVar = this.zza.zzc;
                    if (zzasVar.zzf) {
                        ((ViewGroup) activity.getWindow().getDecorView()).removeView(zzasVar);
                        if (zzasVar.zzc != null) {
                            zzasVar.zzc.onOverlayDismissed();
                        }
                        zzasVar.zzd();
                    }
                }
            });
        }
    }
}
