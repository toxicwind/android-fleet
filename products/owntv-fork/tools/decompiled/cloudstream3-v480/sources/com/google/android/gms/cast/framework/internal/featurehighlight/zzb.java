package com.google.android.gms.cast.framework.internal.featurehighlight;

import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
final class zzb extends GestureDetector.SimpleOnGestureListener {
    final /* synthetic */ View zza;
    final /* synthetic */ zzg zzb;

    zzb(zzh zzhVar, View view, boolean z, zzg zzgVar) {
        this.zza = view;
        this.zzb = zzgVar;
    }

    @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
    public final boolean onSingleTapUp(MotionEvent motionEvent) {
        View view = this.zza;
        if (view.getParent() != null) {
            view.performClick();
        }
        this.zzb.zzb();
        return true;
    }
}
