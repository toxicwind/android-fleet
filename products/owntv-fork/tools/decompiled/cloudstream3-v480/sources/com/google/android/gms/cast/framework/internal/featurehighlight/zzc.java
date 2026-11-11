package com.google.android.gms.cast.framework.internal.featurehighlight;

import android.view.View;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
final class zzc implements View.OnLayoutChangeListener {
    final /* synthetic */ zzh zza;

    zzc(zzh zzhVar, Runnable runnable) {
        this.zza = zzhVar;
    }

    @Override // android.view.View.OnLayoutChangeListener
    public final void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        zzh zzhVar = this.zza;
        zzhVar.zzk();
        zzhVar.removeOnLayoutChangeListener(this);
    }
}
