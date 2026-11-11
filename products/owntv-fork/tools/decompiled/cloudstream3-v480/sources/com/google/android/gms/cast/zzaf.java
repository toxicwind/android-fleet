package com.google.android.gms.cast;

import androidx.mediarouter.media.MediaRouter;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
final class zzaf extends MediaRouter.Callback {
    final /* synthetic */ CastRemoteDisplayLocalService zza;

    zzaf(CastRemoteDisplayLocalService castRemoteDisplayLocalService) {
        this.zza = castRemoteDisplayLocalService;
    }

    @Override // androidx.mediarouter.media.MediaRouter.Callback
    public final void onRouteUnselected(MediaRouter mediaRouter, MediaRouter.RouteInfo routeInfo) {
        CastRemoteDisplayLocalService castRemoteDisplayLocalService = this.zza;
        castRemoteDisplayLocalService.zzv("onRouteUnselected");
        if (castRemoteDisplayLocalService.zzm == null) {
            castRemoteDisplayLocalService.zzv("onRouteUnselected, no device was selected");
            return;
        }
        CastDevice fromBundle = CastDevice.getFromBundle(routeInfo.getExtras());
        if (fromBundle == null || !fromBundle.getDeviceId().equals(castRemoteDisplayLocalService.zzm.getDeviceId())) {
            castRemoteDisplayLocalService.zzv("onRouteUnselected, device does not match");
        } else {
            CastRemoteDisplayLocalService.stopService();
        }
    }
}
