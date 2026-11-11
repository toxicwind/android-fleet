package com.google.android.gms.internal.cast;

import android.content.Context;
import androidx.mediarouter.media.MediaRouteSelector;
import androidx.mediarouter.media.MediaRouter;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
public final class zzbg {
    public MediaRouter zza;
    private final Context zzb;

    public zzbg(Context context) {
        this.zzb = context;
    }

    public final MediaRouter zza() {
        if (this.zza == null) {
            this.zza = MediaRouter.getInstance(this.zzb);
        }
        return this.zza;
    }

    public final void zzb(MediaRouteSelector mediaRouteSelector, MediaRouter.Callback callback, int i) {
        zza().addCallback(mediaRouteSelector, callback, 4);
    }

    public final void zzc(MediaRouter.Callback callback) {
        MediaRouter mediaRouterZza = zza();
        if (mediaRouterZza != null) {
            mediaRouterZza.removeCallback(callback);
        }
    }
}
