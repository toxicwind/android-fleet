package com.google.android.gms.internal.cast;

import android.graphics.Bitmap;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
final class zzcf implements com.google.android.gms.cast.framework.media.internal.zza {
    final /* synthetic */ zzcg zza;

    zzcf(zzcg zzcgVar) {
        this.zza = zzcgVar;
    }

    @Override // com.google.android.gms.cast.framework.media.internal.zza
    public final void zza(Bitmap bitmap) {
        if (bitmap != null) {
            this.zza.zza.setImageBitmap(bitmap);
        }
    }
}
