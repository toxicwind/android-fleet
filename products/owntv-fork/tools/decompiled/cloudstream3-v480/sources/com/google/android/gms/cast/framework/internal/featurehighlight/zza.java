package com.google.android.gms.cast.framework.internal.featurehighlight;

import android.view.GestureDetector;
import android.view.MotionEvent;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
final class zza extends GestureDetector.SimpleOnGestureListener {
    final /* synthetic */ zzh zza;

    zza(zzh zzhVar) {
        this.zza = zzhVar;
    }

    @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
    public final boolean onSingleTapUp(MotionEvent motionEvent) {
        zzh zzhVar = this.zza;
        float x = motionEvent.getX();
        float y = motionEvent.getY();
        if (zzhVar.zzk == null) {
            return true;
        }
        if (zzhVar.zzd.contains(Math.round(x), Math.round(y)) && zzhVar.zze.zzg(x, y)) {
            return true;
        }
        zzhVar.zzk.zza();
        return true;
    }
}
