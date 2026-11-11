package com.google.android.gms.internal.cast;

import android.os.Handler;
import android.os.Looper;
import androidx.concurrent.futures.CallbackToFutureAdapter;
import androidx.mediarouter.media.MediaRouter;
import androidx.mediarouter.media.RouteListingPreference;
import com.google.android.gms.cast.CastDevice;
import com.google.android.gms.cast.SessionState;
import com.google.android.gms.cast.framework.CastOptions;
import com.google.android.gms.cast.framework.CastSession;
import com.google.android.gms.cast.framework.SessionManager;
import com.google.android.gms.cast.framework.SessionTransferCallback;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.cast.internal.Logger;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import j$.util.DesugarCollections;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
public final class zzbn {
    private static final Logger zza = new Logger("SessionTransController");
    private final CastOptions zzb;
    private boolean zzg;
    private SessionManager zzh;
    private CallbackToFutureAdapter.Completer zzi;
    private SessionState zzj;
    private RouteListingPreference zzk;
    private final Set zzc = DesugarCollections.synchronizedSet(new HashSet());
    private int zzf = 0;
    private final Handler zzd = new zzet(Looper.getMainLooper());
    private final Runnable zze = new Runnable() { // from class: com.google.android.gms.internal.cast.zzbj
        @Override // java.lang.Runnable
        public final void run() {
            zzbn.zze(this.zza);
        }
    };

    public zzbn(CastOptions castOptions) {
        this.zzb = castOptions;
    }

    public static /* synthetic */ void zzc(zzbn zzbnVar, SessionState sessionState) {
        zzbnVar.zzj = sessionState;
        CallbackToFutureAdapter.Completer completer = zzbnVar.zzi;
        if (completer != null) {
            completer.set(null);
        }
    }

    public static /* synthetic */ void zzd(zzbn zzbnVar, Exception exc) {
        zza.w(exc, "Fail to store SessionState", new Object[0]);
        zzbnVar.zzr(100);
    }

    public static /* synthetic */ void zze(zzbn zzbnVar) {
        zza.i("transfer with type = %d has timed out", Integer.valueOf(zzbnVar.zzf));
        zzbnVar.zzr(101);
    }

    static /* bridge */ /* synthetic */ void zzg(zzbn zzbnVar) {
        int i = zzbnVar.zzf;
        if (i == 0) {
            zza.d("No need to notify transferred if the transfer type is unknown", new Object[0]);
            return;
        }
        SessionState sessionState = zzbnVar.zzj;
        if (sessionState == null) {
            zza.d("No need to notify with null sessionState", new Object[0]);
            return;
        }
        zza.d("notify transferred with type = %d, sessionState = %s", Integer.valueOf(i), zzbnVar.zzj);
        Iterator it = new HashSet(zzbnVar.zzc).iterator();
        while (it.hasNext()) {
            ((SessionTransferCallback) it.next()).onTransferred(zzbnVar.zzf, sessionState);
        }
    }

    static /* bridge */ /* synthetic */ void zzi(zzbn zzbnVar) {
        if (zzbnVar.zzj == null) {
            zza.d("skip restoring session state due to null SessionState", new Object[0]);
            return;
        }
        RemoteMediaClient remoteMediaClientZzq = zzbnVar.zzq();
        if (remoteMediaClientZzq == null) {
            zza.d("skip restoring session state due to null RemoteMediaClient", new Object[0]);
        } else {
            zza.d("resume SessionState to current session", new Object[0]);
            remoteMediaClientZzq.zzr(zzbnVar.zzj);
        }
    }

    private final RemoteMediaClient zzq() {
        SessionManager sessionManager = this.zzh;
        if (sessionManager == null) {
            zza.d("skip transferring as SessionManager is null", new Object[0]);
            return null;
        }
        CastSession currentCastSession = sessionManager.getCurrentCastSession();
        if (currentCastSession != null) {
            return currentCastSession.getRemoteMediaClient();
        }
        zza.d("skip transferring as CastSession is null", new Object[0]);
        return null;
    }

    private final void zzr(int i) {
        CallbackToFutureAdapter.Completer completer = this.zzi;
        if (completer != null) {
            completer.setCancelled();
        }
        zza.d("notify failed transfer with type = %d, reason = %d", Integer.valueOf(this.zzf), Integer.valueOf(i));
        Iterator it = new HashSet(this.zzc).iterator();
        while (it.hasNext()) {
            ((SessionTransferCallback) it.next()).onTransferFailed(this.zzf, i);
        }
        zzs();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzs() {
        ((Handler) Preconditions.checkNotNull(this.zzd)).removeCallbacks((Runnable) Preconditions.checkNotNull(this.zze));
        this.zzf = 0;
        this.zzj = null;
    }

    public final void zzj(SessionManager sessionManager) {
        this.zzh = sessionManager;
        ((Handler) Preconditions.checkNotNull(this.zzd)).post(new Runnable() { // from class: com.google.android.gms.internal.cast.zzbk
            @Override // java.lang.Runnable
            public final void run() {
                zzbn zzbnVar = this.zza;
                ((SessionManager) Preconditions.checkNotNull(zzbnVar.zzh)).addSessionManagerListener(new zzbl(zzbnVar, null), CastSession.class);
            }
        });
    }

    public final void zzk(MediaRouter.RouteInfo routeInfo, MediaRouter.RouteInfo routeInfo2, CallbackToFutureAdapter.Completer completer) {
        int i;
        Set set = this.zzc;
        if (new HashSet(set).isEmpty()) {
            zza.d("No need to prepare transfer without any callback", new Object[0]);
            completer.set(null);
            return;
        }
        if (routeInfo.getPlaybackType() != 1) {
            zza.d("No need to prepare transfer when transferring from local", new Object[0]);
            completer.set(null);
            return;
        }
        RemoteMediaClient remoteMediaClientZzq = zzq();
        if (remoteMediaClientZzq == null || !remoteMediaClientZzq.hasMediaSession()) {
            zza.d("No need to prepare transfer when there is no media session", new Object[0]);
            completer.set(null);
            return;
        }
        Logger logger = zza;
        logger.d("Prepare route transfer for changing endpoint", new Object[0]);
        if (routeInfo2.getPlaybackType() == 0) {
            zzp.zzd(zzpb.CAST_TRANSFER_TO_LOCAL_USED);
            i = 1;
        } else {
            i = CastDevice.getFromBundle(routeInfo2.getExtras()) == null ? 3 : 2;
        }
        this.zzf = i;
        this.zzi = completer;
        logger.d("notify transferring with type = %d", Integer.valueOf(i));
        Iterator it = new HashSet(set).iterator();
        while (it.hasNext()) {
            ((SessionTransferCallback) it.next()).onTransferring(this.zzf);
        }
        this.zzj = null;
        remoteMediaClientZzq.zzk(null).addOnSuccessListener(new OnSuccessListener() { // from class: com.google.android.gms.internal.cast.zzbh
            @Override // com.google.android.gms.tasks.OnSuccessListener
            public final void onSuccess(Object obj) {
                zzbn.zzc(this.zza, (SessionState) obj);
            }
        }).addOnFailureListener(new OnFailureListener() { // from class: com.google.android.gms.internal.cast.zzbi
            @Override // com.google.android.gms.tasks.OnFailureListener
            public final void onFailure(Exception exc) {
                zzbn.zzd(this.zza, exc);
            }
        });
        ((Handler) Preconditions.checkNotNull(this.zzd)).postDelayed((Runnable) Preconditions.checkNotNull(this.zze), 20000L);
    }

    public final void zzl(SessionTransferCallback sessionTransferCallback) {
        zza.d("register callback = %s", sessionTransferCallback);
        Preconditions.checkMainThread("Must be called from the main thread.");
        Preconditions.checkNotNull(sessionTransferCallback);
        this.zzc.add(sessionTransferCallback);
    }

    public final void zzm(boolean z) {
        this.zzg = z;
    }

    public final void zzn(SessionTransferCallback sessionTransferCallback) {
        zza.d("unregister callback = %s", sessionTransferCallback);
        Preconditions.checkMainThread("Must be called from the main thread.");
        if (sessionTransferCallback != null) {
            this.zzc.remove(sessionTransferCallback);
        }
    }

    public final void zzo(MediaRouter mediaRouter) {
        if (zzp()) {
            SessionManager sessionManager = this.zzh;
            CastSession currentCastSession = sessionManager != null ? sessionManager.getCurrentCastSession() : null;
            CastDevice castDevice = currentCastSession != null ? currentCastSession.getCastDevice() : null;
            if (castDevice != null) {
                ArrayList arrayList = new ArrayList();
                for (MediaRouter.RouteInfo routeInfo : mediaRouter.getRoutes()) {
                    CastDevice fromBundle = CastDevice.getFromBundle(routeInfo.getExtras());
                    if (fromBundle != null && !fromBundle.isSameDevice(castDevice)) {
                        arrayList.add(new RouteListingPreference.Item.Builder(routeInfo.getId()).setFlags(0).build());
                    }
                }
                zza.d("updateRouteListingPreference with %d available routes", Integer.valueOf(arrayList.size()));
                RouteListingPreference routeListingPreferenceBuild = new RouteListingPreference.Builder().setItems(arrayList).build();
                this.zzk = routeListingPreferenceBuild;
                mediaRouter.setRouteListingPreference(routeListingPreferenceBuild);
            }
        }
    }

    public final boolean zzp() {
        return this.zzg && this.zzb.zzh();
    }
}
