package com.google.android.gms.internal.cast;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.support.v4.media.session.MediaSessionCompat;
import androidx.mediarouter.media.MediaRouteSelector;
import androidx.mediarouter.media.MediaRouter;
import androidx.mediarouter.media.MediaRouterParams;
import androidx.mediarouter.media.MediaTransferReceiver;
import com.google.android.gms.cast.framework.CastOptions;
import com.google.android.gms.cast.framework.SessionTransferCallback;
import com.google.android.gms.cast.internal.Logger;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
public final class zzbf extends zzal {
    private static final Logger zza = new Logger("MediaRouterProxy");
    private final MediaRouter zzb;
    private final CastOptions zzc;
    private final Map zzd = new HashMap();
    private zzbn zze;
    private boolean zzf;
    private boolean zzg;
    private boolean zzh;

    public zzbf(Context context, MediaRouter mediaRouter, CastOptions castOptions, com.google.android.gms.cast.internal.zzn zznVar) {
        this.zzb = mediaRouter;
        this.zzc = castOptions;
        if (Build.VERSION.SDK_INT < 33) {
            return;
        }
        zza.d("Set up MediaRouterParams based on module flag and CastOptions for Android T or above", new Object[0]);
        this.zze = new zzbn(castOptions);
        Intent intent = new Intent(context, (Class<?>) MediaTransferReceiver.class);
        intent.setPackage(context.getPackageName());
        this.zzf = !context.getPackageManager().queryBroadcastReceivers(intent, 0).isEmpty();
        this.zzg = true;
        zznVar.zza(new String[]{"com.google.android.gms.cast.FLAG_OUTPUT_SWITCHER_ENABLED"}).addOnCompleteListener(new OnCompleteListener() { // from class: com.google.android.gms.internal.cast.zzbc
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public final void onComplete(Task task) {
                zzbf.zzp(this.zza, task);
            }
        });
    }

    public static /* synthetic */ void zzo(zzbf zzbfVar, MediaRouteSelector mediaRouteSelector, int i) {
        synchronized (zzbfVar.zzd) {
            zzbfVar.zzx(mediaRouteSelector, i);
        }
    }

    public static /* synthetic */ void zzp(zzbf zzbfVar, Task task) {
        CastOptions castOptions;
        if (task.isSuccessful()) {
            Bundle bundle = (Bundle) task.getResult();
            boolean z = bundle != null && bundle.containsKey("com.google.android.gms.cast.FLAG_OUTPUT_SWITCHER_ENABLED");
            zza.d("The module-to-client output switcher flag %s", true != z ? "not existed" : "existed");
            if (z) {
                zzbfVar.zzg = bundle.getBoolean("com.google.android.gms.cast.FLAG_OUTPUT_SWITCHER_ENABLED");
            }
        }
        boolean z2 = zzbfVar.zzg;
        MediaRouter mediaRouter = zzbfVar.zzb;
        if (mediaRouter == null || (castOptions = zzbfVar.zzc) == null) {
            return;
        }
        boolean zZzg = castOptions.zzg();
        boolean zZze = castOptions.zze();
        boolean z3 = z2 && castOptions.zzi();
        mediaRouter.setRouterParams(new MediaRouterParams.Builder().setMediaTransferReceiverEnabled(z3).setTransferToLocalEnabled(zZzg).setOutputSwitcherEnabled(zZze).setMediaTransferRestrictedToSelfProviders(castOptions.zzf()).build());
        zza.i("media transfer = %b, session transfer = %b, transfer to local = %b, in-app output switcher = %b", Boolean.valueOf(zzbfVar.zzf), Boolean.valueOf(z3), Boolean.valueOf(zZzg), Boolean.valueOf(zZze));
        zzbn zzbnVar = zzbfVar.zze;
        if (zzbnVar != null) {
            zzbnVar.zzm(zzbfVar.zzf && z3);
        }
        if (zzbfVar.zzf && z3) {
            zzp.zzd(zzpb.CAST_OUTPUT_SWITCHER_ENABLED);
        }
        if (zZzg) {
            zzp.zzd(zzpb.CAST_TRANSFER_TO_LOCAL_ENABLED);
        }
    }

    private final void zzx(MediaRouteSelector mediaRouteSelector, int i) {
        Set set = (Set) this.zzd.get(mediaRouteSelector);
        if (set == null) {
            return;
        }
        Iterator it = set.iterator();
        while (it.hasNext()) {
            this.zzb.addCallback(mediaRouteSelector, (MediaRouter.Callback) it.next(), i);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzy(MediaRouteSelector mediaRouteSelector) {
        Set set = (Set) this.zzd.get(mediaRouteSelector);
        if (set == null) {
            return;
        }
        Iterator it = set.iterator();
        while (it.hasNext()) {
            this.zzb.removeCallback((MediaRouter.Callback) it.next());
        }
    }

    @Override // com.google.android.gms.internal.cast.zzam
    public final Bundle zzb(String str) {
        for (MediaRouter.RouteInfo routeInfo : this.zzb.getRoutes()) {
            if (routeInfo.getId().equals(str)) {
                return routeInfo.getExtras();
            }
        }
        return null;
    }

    @Override // com.google.android.gms.internal.cast.zzam
    public final String zzc() {
        return this.zzb.getSelectedRoute().getId();
    }

    @Override // com.google.android.gms.internal.cast.zzam
    public final void zzd(Bundle bundle, final int i) {
        final MediaRouteSelector mediaRouteSelectorFromBundle = MediaRouteSelector.fromBundle(bundle);
        if (mediaRouteSelectorFromBundle == null) {
            return;
        }
        if (Looper.myLooper() == Looper.getMainLooper()) {
            zzx(mediaRouteSelectorFromBundle, i);
        } else {
            new zzet(Looper.getMainLooper()).post(new Runnable() { // from class: com.google.android.gms.internal.cast.zzbe
                @Override // java.lang.Runnable
                public final void run() {
                    zzbf.zzo(this.zza, mediaRouteSelectorFromBundle, i);
                }
            });
        }
    }

    @Override // com.google.android.gms.internal.cast.zzam
    public final void zze(Bundle bundle, zzao zzaoVar) {
        MediaRouteSelector mediaRouteSelectorFromBundle = MediaRouteSelector.fromBundle(bundle);
        if (mediaRouteSelectorFromBundle == null) {
            return;
        }
        Map map = this.zzd;
        if (!map.containsKey(mediaRouteSelectorFromBundle)) {
            map.put(mediaRouteSelectorFromBundle, new HashSet());
        }
        ((Set) map.get(mediaRouteSelectorFromBundle)).add(new zzat(zzaoVar, this, this.zze));
    }

    @Override // com.google.android.gms.internal.cast.zzam
    public final void zzf() {
        Map map = this.zzd;
        Iterator it = map.values().iterator();
        while (it.hasNext()) {
            Iterator it2 = ((Set) it.next()).iterator();
            while (it2.hasNext()) {
                this.zzb.removeCallback((MediaRouter.Callback) it2.next());
            }
        }
        map.clear();
    }

    @Override // com.google.android.gms.internal.cast.zzam
    public final void zzg(Bundle bundle) {
        final MediaRouteSelector mediaRouteSelectorFromBundle = MediaRouteSelector.fromBundle(bundle);
        if (mediaRouteSelectorFromBundle == null) {
            return;
        }
        if (Looper.myLooper() == Looper.getMainLooper()) {
            zzy(mediaRouteSelectorFromBundle);
        } else {
            new zzet(Looper.getMainLooper()).post(new Runnable() { // from class: com.google.android.gms.internal.cast.zzbd
                @Override // java.lang.Runnable
                public final void run() {
                    this.zza.zzy(mediaRouteSelectorFromBundle);
                }
            });
        }
    }

    @Override // com.google.android.gms.internal.cast.zzam
    public final void zzh() {
        MediaRouter mediaRouter = this.zzb;
        mediaRouter.selectRoute(mediaRouter.getDefaultRoute());
    }

    @Override // com.google.android.gms.internal.cast.zzam
    public final void zzi(String str) {
        Logger logger = zza;
        logger.d("select route with routeId = %s", str);
        MediaRouter mediaRouter = this.zzb;
        for (MediaRouter.RouteInfo routeInfo : mediaRouter.getRoutes()) {
            if (routeInfo.getId().equals(str)) {
                logger.d("media route is found and selected", new Object[0]);
                mediaRouter.selectRoute(routeInfo);
                return;
            }
        }
    }

    @Override // com.google.android.gms.internal.cast.zzam
    public final void zzj(int i) {
        this.zzb.unselect(i);
    }

    @Override // com.google.android.gms.internal.cast.zzam
    public final boolean zzk() {
        MediaRouter mediaRouter = this.zzb;
        MediaRouter.RouteInfo bluetoothRoute = mediaRouter.getBluetoothRoute();
        return bluetoothRoute != null && mediaRouter.getSelectedRoute().getId().equals(bluetoothRoute.getId());
    }

    @Override // com.google.android.gms.internal.cast.zzam
    public final boolean zzl() {
        MediaRouter mediaRouter = this.zzb;
        MediaRouter.RouteInfo defaultRoute = mediaRouter.getDefaultRoute();
        return defaultRoute != null && mediaRouter.getSelectedRoute().getId().equals(defaultRoute.getId());
    }

    @Override // com.google.android.gms.internal.cast.zzam
    public final boolean zzm(Bundle bundle, int i) {
        MediaRouteSelector mediaRouteSelectorFromBundle = MediaRouteSelector.fromBundle(bundle);
        if (mediaRouteSelectorFromBundle == null) {
            return false;
        }
        return this.zzb.isRouteAvailable(mediaRouteSelectorFromBundle, i);
    }

    public final zzbn zzn() {
        return this.zze;
    }

    public final void zzr(SessionTransferCallback sessionTransferCallback) {
        zzbn zzbnVar = this.zze;
        if (zzbnVar != null) {
            zzbnVar.zzl(sessionTransferCallback);
            this.zzb.setOnPrepareTransferListener(new zzbb((zzbn) Preconditions.checkNotNull(this.zze)));
        }
    }

    public final void zzs(SessionTransferCallback sessionTransferCallback) {
        zzbn zzbnVar = this.zze;
        if (zzbnVar != null) {
            zzbnVar.zzn(sessionTransferCallback);
            this.zzb.setOnPrepareTransferListener(null);
        }
    }

    public final void zzt(MediaSessionCompat mediaSessionCompat) {
        this.zzb.setMediaSessionCompat(mediaSessionCompat);
    }

    public final void zzu(boolean z) {
        this.zzh = z;
    }

    public final boolean zzv() {
        return this.zzh;
    }

    public final boolean zzw() {
        CastOptions castOptions;
        return this.zzf && this.zzg && (castOptions = this.zzc) != null && castOptions.zzi();
    }
}
