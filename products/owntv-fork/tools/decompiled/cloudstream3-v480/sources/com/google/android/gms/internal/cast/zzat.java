package com.google.android.gms.internal.cast;

import android.os.RemoteException;
import androidx.mediarouter.media.MediaRouter;
import com.google.android.gms.cast.internal.Logger;
import com.google.android.gms.common.internal.Preconditions;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
public final class zzat extends MediaRouter.Callback {
    private static final Logger zza = new Logger("MediaRouterCallback");
    private final zzao zzb;
    private final zzbf zzc;
    private final zzbn zzd;

    public zzat(zzao zzaoVar, zzbf zzbfVar, zzbn zzbnVar) {
        this.zzb = (zzao) Preconditions.checkNotNull(zzaoVar);
        this.zzc = zzbfVar;
        this.zzd = zzbnVar;
    }

    private final void zza(MediaRouter mediaRouter) {
        zzbn zzbnVar = this.zzd;
        if (zzbnVar != null) {
            zzbnVar.zzo(mediaRouter);
        }
    }

    @Override // androidx.mediarouter.media.MediaRouter.Callback
    public final void onRouteAdded(MediaRouter mediaRouter, MediaRouter.RouteInfo routeInfo) {
        try {
            this.zzb.zzf(routeInfo.getId(), routeInfo.getExtras());
        } catch (RemoteException e) {
            zza.d(e, "Unable to call %s on %s.", "onRouteAdded", "zzao");
        }
        zza(mediaRouter);
    }

    @Override // androidx.mediarouter.media.MediaRouter.Callback
    public final void onRouteChanged(MediaRouter mediaRouter, MediaRouter.RouteInfo routeInfo) {
        if (routeInfo.isSelected()) {
            try {
                this.zzb.zzg(routeInfo.getId(), routeInfo.getExtras());
            } catch (RemoteException e) {
                zza.d(e, "Unable to call %s on %s.", "onRouteChanged", "zzao");
            }
            zza(mediaRouter);
        }
    }

    @Override // androidx.mediarouter.media.MediaRouter.Callback
    public final void onRouteConnected(MediaRouter mediaRouter, MediaRouter.RouteInfo routeInfo, MediaRouter.RouteInfo routeInfo2) {
        if (routeInfo.getPlaybackType() != 1) {
            zza.i("ignore onRouteConnected for non-remote connected routeId: %s", routeInfo.getId());
            return;
        }
        zza.i("onRouteConnected with connectedRouteId = %s", routeInfo.getId());
        this.zzc.zzu(true);
        try {
            zzao zzaoVar = this.zzb;
            if (zzaoVar.zze() >= 251600000) {
                zzaoVar.zzh(routeInfo2.getId(), routeInfo.getId(), routeInfo.getExtras());
            } else {
                zzaoVar.zzl(routeInfo2.getId(), routeInfo.getId(), routeInfo.getExtras());
            }
        } catch (RemoteException e) {
            zza.d(e, "Unable to call %s on %s.", "onRouteConnected", "zzao");
        }
    }

    @Override // androidx.mediarouter.media.MediaRouter.Callback
    public final void onRouteDisconnected(MediaRouter mediaRouter, MediaRouter.RouteInfo routeInfo, MediaRouter.RouteInfo routeInfo2, int i) {
        if (routeInfo == null || routeInfo.getPlaybackType() != 1) {
            zza.i("ignore onRouteDisconnected for invalid or non-remote disconnected route", new Object[0]);
            return;
        }
        zza.i("onRouteDisconnected with disconnectedRouteId = %s, requestedRouteId = %s, reason = %d", ((MediaRouter.RouteInfo) Preconditions.checkNotNull(routeInfo)).getId(), routeInfo2.getId(), Integer.valueOf(i));
        this.zzc.zzu(false);
        try {
            zzao zzaoVar = this.zzb;
            if (zzaoVar.zze() >= 251600000) {
                zzaoVar.zzi(routeInfo2.getId(), routeInfo.getId(), routeInfo.getExtras(), i);
            } else {
                zzaoVar.zzm(routeInfo.getId(), routeInfo.getExtras(), i);
            }
        } catch (RemoteException e) {
            zza.d(e, "Unable to call %s on %s.", "onRouteDisconnected", "zzao");
        }
    }

    @Override // androidx.mediarouter.media.MediaRouter.Callback
    public final void onRouteRemoved(MediaRouter mediaRouter, MediaRouter.RouteInfo routeInfo) {
        try {
            this.zzb.zzj(routeInfo.getId(), routeInfo.getExtras());
        } catch (RemoteException e) {
            zza.d(e, "Unable to call %s on %s.", "onRouteRemoved", "zzao");
        }
        zza(mediaRouter);
    }

    @Override // androidx.mediarouter.media.MediaRouter.Callback
    public final void onRouteSelected(MediaRouter mediaRouter, MediaRouter.RouteInfo routeInfo, int i, MediaRouter.RouteInfo routeInfo2) {
        if (routeInfo.getPlaybackType() != 1) {
            zza.i("ignore onRouteSelected for non-remote selected routeId: %s", routeInfo.getId());
            return;
        }
        zza.i("onRouteSelected with reason = %d, routeId = %s", Integer.valueOf(i), routeInfo.getId());
        try {
            zzao zzaoVar = this.zzb;
            if (zzaoVar.zze() >= 220400000) {
                zzaoVar.zzl(routeInfo2.getId(), routeInfo.getId(), routeInfo.getExtras());
            } else {
                zzaoVar.zzk(routeInfo2.getId(), routeInfo.getExtras());
            }
        } catch (RemoteException e) {
            zza.d(e, "Unable to call %s on %s.", "onRouteSelected", "zzao");
        }
        zza(mediaRouter);
    }

    @Override // androidx.mediarouter.media.MediaRouter.Callback
    public final void onRouteUnselected(MediaRouter mediaRouter, MediaRouter.RouteInfo routeInfo, int i) {
        if (routeInfo.getPlaybackType() != 1) {
            zza.i("ignore onRouteUnselected for non-remote routeId: %s", routeInfo.getId());
            return;
        }
        zza.i("onRouteUnselected with reason = %d, routeId = %s", Integer.valueOf(i), routeInfo.getId());
        try {
            this.zzb.zzm(routeInfo.getId(), routeInfo.getExtras(), i);
        } catch (RemoteException e) {
            zza.d(e, "Unable to call %s on %s.", "onRouteUnselected", "zzao");
        }
        zza(mediaRouter);
    }
}
