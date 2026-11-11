package com.google.android.gms.internal.cast;

import android.os.Bundle;
import androidx.mediarouter.media.MediaRouter;
import com.google.android.gms.cast.CastDevice;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
final class zzdo extends MediaRouter.Callback {
    final /* synthetic */ zzdq zza;

    /* synthetic */ zzdo(zzdq zzdqVar, zzdp zzdpVar) {
        this.zza = zzdqVar;
    }

    @Override // androidx.mediarouter.media.MediaRouter.Callback
    public final void onRouteAdded(MediaRouter mediaRouter, MediaRouter.RouteInfo routeInfo) {
        zzdq.zzc.d("RemoteConnectionMediaRouterCallback.onRouteAdded.", new Object[0]);
        zzdq.zzd(this.zza, routeInfo.getExtras());
    }

    @Override // androidx.mediarouter.media.MediaRouter.Callback
    public final void onRouteChanged(MediaRouter mediaRouter, MediaRouter.RouteInfo routeInfo) {
        zzdq.zzc.d("RemoteConnectionMediaRouterCallback.onRouteChanged.", new Object[0]);
        zzdq.zzd(this.zza, routeInfo.getExtras());
    }

    @Override // androidx.mediarouter.media.MediaRouter.Callback
    public final void onRouteRemoved(MediaRouter mediaRouter, MediaRouter.RouteInfo routeInfo) {
        CastDevice fromBundle;
        zzdq.zzc.d("RemoteConnectionMediaRouterCallback.onRouteRemoved.", new Object[0]);
        Bundle extras = routeInfo.getExtras();
        if (extras == null || (fromBundle = CastDevice.getFromBundle(extras)) == null) {
            return;
        }
        String string = extras.getString("com.google.android.gms.cast.EXTRA_RUNNING_RECEIVER_APP_ID");
        zzdq zzdqVar = this.zza;
        zzdi zzdiVar = (zzdi) zzdqVar.zzj.get(fromBundle.getDeviceId());
        if (zzdiVar != null && string != null) {
            throw null;
        }
        if (zzdiVar != null) {
            com.google.android.gms.cast.framework.zzo zzoVar = new com.google.android.gms.cast.framework.zzo();
            zzoVar.zzb(2422);
            zzdiVar.zza(zzoVar.zzc());
        }
        if (zzdiVar != null) {
            throw null;
        }
        zzdqVar.zzj(fromBundle);
    }
}
