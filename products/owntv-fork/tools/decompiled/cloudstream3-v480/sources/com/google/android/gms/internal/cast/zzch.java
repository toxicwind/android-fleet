package com.google.android.gms.internal.cast;

import android.graphics.Bitmap;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
final class zzch implements com.google.android.gms.cast.framework.media.internal.zza {
    final /* synthetic */ zzcj zza;

    zzch(zzcj zzcjVar) {
        this.zza = zzcjVar;
    }

    @Override // com.google.android.gms.cast.framework.media.internal.zza
    public final void zza(Bitmap bitmap) {
        if (bitmap != null) {
            zzcj zzcjVar = this.zza;
            if (zzcjVar.zzd != null) {
                zzcjVar.zzd.setVisibility(4);
            }
            zzcjVar.zza.setVisibility(0);
            zzcjVar.zza.setImageBitmap(bitmap);
            if (zzcjVar.zzf != null) {
                zzcjVar.zzf.zza();
            }
        }
    }
}
