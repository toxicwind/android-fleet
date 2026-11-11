package com.google.android.gms.internal.cast;

import android.content.Context;
import android.os.Looper;
import androidx.mediarouter.media.MediaRouteSelector;
import androidx.mediarouter.media.MediaRouter;
import com.google.android.gms.cast.CastMediaControlIntent;
import com.google.android.gms.cast.internal.Logger;
import j$.util.DesugarCollections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
public final class zzay extends MediaRouter.Callback {
    private static final Logger zzb = new Logger("MRDiscoveryCallback");
    private final zzbg zzf;
    private final Map zzd = DesugarCollections.synchronizedMap(new HashMap());
    private final LinkedHashSet zze = new LinkedHashSet();
    private final Set zzc = DesugarCollections.synchronizedSet(new LinkedHashSet());
    public final zzax zza = new zzax(this);

    public zzay(Context context) {
        this.zzf = new zzbg(context);
    }

    @Override // androidx.mediarouter.media.MediaRouter.Callback
    public final void onRouteAdded(MediaRouter mediaRouter, MediaRouter.RouteInfo routeInfo) {
        zzb.d("MediaRouterDiscoveryCallback.onRouteAdded.", new Object[0]);
        zzf(routeInfo, true);
    }

    @Override // androidx.mediarouter.media.MediaRouter.Callback
    public final void onRouteChanged(MediaRouter mediaRouter, MediaRouter.RouteInfo routeInfo) {
        zzb.d("MediaRouterDiscoveryCallback.onRouteChanged.", new Object[0]);
        zzf(routeInfo, true);
    }

    @Override // androidx.mediarouter.media.MediaRouter.Callback
    public final void onRouteRemoved(MediaRouter mediaRouter, MediaRouter.RouteInfo routeInfo) {
        zzb.d("MediaRouterDiscoveryCallback.onRouteRemoved.", new Object[0]);
        zzf(routeInfo, false);
    }

    public final void zza(List list) {
        Logger logger = zzb;
        logger.d("SetRouteDiscovery for " + list.size() + " IDs", new Object[0]);
        LinkedHashSet<String> linkedHashSet = new LinkedHashSet();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            linkedHashSet.add(zzgk.zza((String) it.next()));
        }
        Map map = this.zzd;
        logger.d("resetting routes. appIdToRouteInfo has these appId route keys: ".concat(String.valueOf(String.valueOf(map.keySet()))), new Object[0]);
        HashMap map2 = new HashMap();
        synchronized (map) {
            for (String str : linkedHashSet) {
                zzaw zzawVar = (zzaw) map.get(zzgk.zza(str));
                if (zzawVar != null) {
                    map2.put(str, zzawVar);
                }
            }
            map.clear();
            map.putAll(map2);
        }
        logger.d("Routes reset. appIdToRouteInfo has these appId route keys: ".concat(String.valueOf(String.valueOf(map.keySet()))), new Object[0]);
        LinkedHashSet linkedHashSet2 = this.zze;
        synchronized (linkedHashSet2) {
            linkedHashSet2.clear();
            linkedHashSet2.addAll(linkedHashSet);
        }
        zzb();
    }

    final void zzb() {
        LinkedHashSet linkedHashSet = this.zze;
        Logger logger = zzb;
        logger.d("Starting RouteDiscovery with " + linkedHashSet.size() + " IDs", new Object[0]);
        logger.d("appIdToRouteInfo has these appId route keys: ".concat(String.valueOf(String.valueOf(this.zzd.keySet()))), new Object[0]);
        if (Looper.myLooper() == Looper.getMainLooper()) {
            zzc();
        } else {
            new zzet(Looper.getMainLooper()).post(new Runnable() { // from class: com.google.android.gms.internal.cast.zzav
                @Override // java.lang.Runnable
                public final void run() {
                    this.zza.zzc();
                }
            });
        }
    }

    final void zzc() {
        zzbg zzbgVar = this.zzf;
        zzbgVar.zzc(this);
        LinkedHashSet<String> linkedHashSet = this.zze;
        synchronized (linkedHashSet) {
            for (String str : linkedHashSet) {
                MediaRouteSelector mediaRouteSelectorBuild = new MediaRouteSelector.Builder().addControlCategory(CastMediaControlIntent.categoryForCast(str)).build();
                Map map = this.zzd;
                if (((zzaw) map.get(str)) == null) {
                    map.put(str, new zzaw(mediaRouteSelectorBuild));
                }
                zzb.d("Adding mediaRouter callback for control category " + CastMediaControlIntent.categoryForCast(str), new Object[0]);
                zzbgVar.zzb(mediaRouteSelectorBuild, this, 4);
            }
        }
        zzb.d("appIdToRouteInfo has these appId route keys: ".concat(String.valueOf(String.valueOf(this.zzd.keySet()))), new Object[0]);
    }

    public final void zzd() {
        zzb.d("Stopping RouteDiscovery.", new Object[0]);
        this.zzd.clear();
        if (Looper.myLooper() == Looper.getMainLooper()) {
            this.zzf.zzc(this);
        } else {
            new zzet(Looper.getMainLooper()).post(new Runnable() { // from class: com.google.android.gms.internal.cast.zzau
                @Override // java.lang.Runnable
                public final void run() {
                    this.zza.zze();
                }
            });
        }
    }

    final void zze() {
        this.zzf.zzc(this);
    }

    public final void zzf(MediaRouter.RouteInfo routeInfo, boolean z) {
        boolean zAdd;
        Logger logger = zzb;
        logger.d("MediaRouterDiscoveryCallback.updateRouteToAppIds (add=%b) route %s", Boolean.valueOf(z), routeInfo);
        Map map = this.zzd;
        synchronized (map) {
            logger.d("appIdToRouteInfo has these appId route keys: " + String.valueOf(map.keySet()), new Object[0]);
            zAdd = false;
            for (Map.Entry entry : map.entrySet()) {
                String str = (String) entry.getKey();
                zzaw zzawVar = (zzaw) entry.getValue();
                if (routeInfo.matchesSelector(zzawVar.zzb)) {
                    if (z) {
                        logger.d("Adding/updating route for appId " + str, new Object[0]);
                        zAdd = zzawVar.zza.add(routeInfo);
                        if (!zAdd) {
                            logger.w("Route " + String.valueOf(routeInfo) + " already exists for appId " + str, new Object[0]);
                        }
                    } else {
                        logger.d("Removing route for appId " + str, new Object[0]);
                        zAdd = zzawVar.zza.remove(routeInfo);
                        if (!zAdd) {
                            logger.w("Route " + String.valueOf(routeInfo) + " already removed from appId " + str, new Object[0]);
                        }
                    }
                }
            }
        }
        if (zAdd) {
            zzb.d("Invoking callback.onRouteUpdated.", new Object[0]);
            synchronized (this.zzc) {
                HashMap map2 = new HashMap();
                Map map3 = this.zzd;
                synchronized (map3) {
                    for (String str2 : map3.keySet()) {
                        zzaw zzawVar2 = (zzaw) map3.get(zzgk.zza(str2));
                        zzhk zzhkVarZzk = zzawVar2 == null ? zzhk.zzk() : zzhk.zzj(zzawVar2.zza);
                        if (!zzhkVarZzk.isEmpty()) {
                            map2.put(str2, zzhkVarZzk);
                        }
                    }
                }
                zzhj.zzc(map2.entrySet());
                Iterator it = this.zzc.iterator();
                while (it.hasNext()) {
                    ((com.google.android.gms.cast.framework.zzbg) it.next()).zza();
                }
            }
        }
    }
}
