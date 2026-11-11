package com.google.android.gms.internal.cast;

import android.content.Context;
import android.os.Bundle;
import android.os.PowerManager;
import androidx.mediarouter.media.MediaRouteSelector;
import com.google.android.gms.cast.CastDevice;
import com.google.android.gms.cast.CastMediaControlIntent;
import com.google.android.gms.cast.framework.CastOptions;
import com.google.android.gms.cast.internal.Logger;
import j$.util.concurrent.ConcurrentHashMap;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
public final class zzdq {
    public static final /* synthetic */ int zza = 0;
    private static zzdq zzb;
    private static final Logger zzc = new Logger("RemoteConnectionManager");
    private final CastOptions zzd;
    private final zzaf zze;
    private final zzbg zzf;
    private final zzdo zzg;
    private final zzdm zzi;
    private final Map zzj;
    private final Map zzk;
    private final PowerManager zzl;
    private boolean zzo;
    private final Object zzm = new Object();
    private final Object zzn = new Object();
    private final Set zzh = Collections.newSetFromMap(new ConcurrentHashMap());

    private zzdq(Context context, CastOptions castOptions, zzaf zzafVar, zzbg zzbgVar) {
        this.zzd = castOptions;
        this.zzf = zzbgVar;
        zzdp zzdpVar = null;
        this.zzg = new zzdo(this, zzdpVar);
        zzdm zzdmVar = new zzdm(this, zzdpVar);
        this.zzi = zzdmVar;
        this.zze = zzafVar;
        zzafVar.zze(zzdmVar);
        this.zzj = new ConcurrentHashMap();
        this.zzk = new ConcurrentHashMap();
        this.zzl = (PowerManager) context.getSystemService("power");
        new zzdn(this, zzdpVar);
    }

    public static zzdq zza(Context context, CastOptions castOptions, zzaf zzafVar) {
        if (zzb == null) {
            zzb = new zzdq(context, castOptions, zzafVar, new zzbg(context));
        }
        return zzb;
    }

    static /* bridge */ /* synthetic */ void zzd(zzdq zzdqVar, Bundle bundle) {
        final CastDevice fromBundle;
        if (bundle == null || (fromBundle = CastDevice.getFromBundle(bundle)) == null) {
            return;
        }
        final String string = bundle.getString("com.google.android.gms.cast.EXTRA_RUNNING_RECEIVER_APP_ID");
        Map map = zzdqVar.zzj;
        if (map.containsKey(fromBundle.getDeviceId()) && ((zzdi) map.get(fromBundle.getDeviceId())) != null) {
            throw null;
        }
        String receiverApplicationId = zzdqVar.zzd.getReceiverApplicationId();
        if (string == null || receiverApplicationId.isEmpty() || !string.equals(receiverApplicationId)) {
            zzdqVar.zzj(fromBundle);
            return;
        }
        Set set = zzdqVar.zzh;
        if (set.isEmpty()) {
            return;
        }
        synchronized (zzdqVar.zzm) {
            Iterator it = set.iterator();
            if (it.hasNext()) {
                throw null;
            }
        }
    }

    static /* bridge */ /* synthetic */ void zze(zzdq zzdqVar) {
        synchronized (zzdqVar.zzn) {
            Iterator it = zzdqVar.zzj.values().iterator();
            if (it.hasNext()) {
                throw null;
            }
        }
    }

    static /* bridge */ /* synthetic */ void zzg(zzdq zzdqVar) {
        synchronized (zzdqVar.zzn) {
            for (zzdi zzdiVar : zzdqVar.zzj.values()) {
                boolean zZzf = zzdqVar.zze.zzf();
                PowerManager powerManager = zzdqVar.zzl;
                boolean z = false;
                if (powerManager != null && !powerManager.isInteractive()) {
                    z = true;
                }
                zzdiVar.zzb(zZzf, z);
            }
        }
    }

    static /* bridge */ /* synthetic */ void zzh(zzdq zzdqVar) {
        MediaRouteSelector mediaRouteSelectorBuild;
        boolean zIsEmpty = zzdqVar.zzh.isEmpty();
        if (zzdqVar.zze.zzf() || zIsEmpty) {
            if (zzdqVar.zzo) {
                zzdqVar.zzo = false;
                zzc.d("Stopping RemoteConnectionManager discovery.", new Object[0]);
                zzdqVar.zzf.zzc(zzdqVar.zzg);
                return;
            }
            return;
        }
        if (zzdqVar.zzo) {
            return;
        }
        Logger logger = zzc;
        logger.d("Starting RemoteConnectionManager discovery.", new Object[0]);
        zzbg zzbgVar = zzdqVar.zzf;
        zzdo zzdoVar = zzdqVar.zzg;
        zzbgVar.zzc(zzdoVar);
        String receiverApplicationId = zzdqVar.zzd.getReceiverApplicationId();
        if (receiverApplicationId.isEmpty()) {
            logger.d("Failed to create MediaRouteSelector. No target receiver app ID has been set.", new Object[0]);
            mediaRouteSelectorBuild = null;
        } else {
            mediaRouteSelectorBuild = new MediaRouteSelector.Builder().addControlCategory(CastMediaControlIntent.categoryForCast(receiverApplicationId)).build();
        }
        if (mediaRouteSelectorBuild == null) {
            logger.d("Skipping starting discovery. No target receiver app ID has been set.", new Object[0]);
            return;
        }
        zzdqVar.zzo = true;
        logger.d("Adding mediaRouter callback for control category ".concat(String.valueOf(String.valueOf(mediaRouteSelectorBuild.getControlCategories()))), new Object[0]);
        zzbgVar.zzb(mediaRouteSelectorBuild, zzdoVar, 4);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzj(CastDevice castDevice) {
        if (((zzdk) this.zzk.remove(castDevice.getDeviceId())) != null) {
            synchronized (this.zzm) {
                Iterator it = this.zzh.iterator();
                if (it.hasNext()) {
                    throw null;
                }
            }
        }
    }

    public final void zzi(com.google.android.gms.cast.framework.zzq zzqVar) {
        synchronized (this.zzn) {
            Iterator it = this.zzj.entrySet().iterator();
            if (it.hasNext()) {
                throw null;
            }
        }
    }
}
