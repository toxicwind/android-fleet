package com.google.android.gms.cast.framework;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.RemoteException;
import android.widget.Toast;
import com.google.android.gms.cast.internal.Logger;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
public class SessionManager {
    private static final Logger zza = new Logger("SessionManager");
    private final zzay zzb;
    private final Context zzc;

    public SessionManager(zzay zzayVar, Context context) {
        this.zzb = zzayVar;
        this.zzc = context;
    }

    public void addSessionManagerListener(SessionManagerListener<Session> sessionManagerListener) throws NullPointerException {
        Preconditions.checkMainThread("Must be called from the main thread.");
        addSessionManagerListener(sessionManagerListener, Session.class);
    }

    public void endCurrentSession(boolean z) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        try {
            zza.i("End session for %s", this.zzc.getPackageName());
            this.zzb.zzj(true, z);
        } catch (RemoteException e) {
            zza.d(e, "Unable to call %s on %s.", "endCurrentSession", "zzay");
        }
    }

    public CastSession getCurrentCastSession() {
        Preconditions.checkMainThread("Must be called from the main thread.");
        Session currentSession = getCurrentSession();
        if (currentSession == null || !(currentSession instanceof CastSession)) {
            return null;
        }
        return (CastSession) currentSession;
    }

    public Session getCurrentSession() {
        Preconditions.checkMainThread("Must be called from the main thread.");
        try {
            return (Session) ObjectWrapper.unwrap(this.zzb.zzf());
        } catch (RemoteException e) {
            zza.d(e, "Unable to call %s on %s.", "getWrappedCurrentSession", "zzay");
            return null;
        }
    }

    public void removeSessionManagerListener(SessionManagerListener<Session> sessionManagerListener) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        removeSessionManagerListener(sessionManagerListener, Session.class);
    }

    public void startSession(Intent intent) {
        try {
            Logger logger = zza;
            Context context = this.zzc;
            logger.i("Start session for %s", context.getPackageName());
            Bundle extras = intent.getExtras();
            if (extras != null && extras.getString("CAST_INTENT_TO_CAST_ROUTE_ID_KEY") != null) {
                String string = extras.getString("CAST_INTENT_TO_CAST_DEVICE_NAME_KEY");
                if (!extras.getBoolean("CAST_INTENT_TO_CAST_NO_TOAST_KEY")) {
                    Toast.makeText(context, context.getString(R.string.cast_connecting_to_device, string), 0).show();
                }
                this.zzb.zzm(new Bundle(extras));
                intent.removeExtra("CAST_INTENT_TO_CAST_ROUTE_ID_KEY");
            }
        } catch (RemoteException e) {
            zza.d(e, "Unable to call %s on %s.", "startSession", "zzay");
        }
    }

    final int zza() {
        try {
            return this.zzb.zze();
        } catch (RemoteException e) {
            zza.d(e, "Unable to call %s on %s.", "addCastStateListener", "zzay");
            return 1;
        }
    }

    public final IObjectWrapper zzb() {
        try {
            return this.zzb.zzg();
        } catch (RemoteException e) {
            zza.d(e, "Unable to call %s on %s.", "getWrappedThis", "zzay");
            return null;
        }
    }

    final void zzc(CastStateListener castStateListener) throws NullPointerException {
        Preconditions.checkNotNull(castStateListener);
        try {
            this.zzb.zzh(new zzab(castStateListener));
        } catch (RemoteException e) {
            zza.d(e, "Unable to call %s on %s.", "addCastStateListener", "zzay");
        }
    }

    final void zzd(CastStateListener castStateListener) {
        try {
            this.zzb.zzk(new zzab(castStateListener));
        } catch (RemoteException e) {
            zza.d(e, "Unable to call %s on %s.", "removeCastStateListener", "zzay");
        }
    }

    public <T extends Session> void removeSessionManagerListener(SessionManagerListener<T> sessionManagerListener, Class<T> cls) {
        Preconditions.checkNotNull(cls);
        Preconditions.checkMainThread("Must be called from the main thread.");
        if (sessionManagerListener == null) {
            return;
        }
        try {
            this.zzb.zzl(new zzbj(sessionManagerListener, cls));
        } catch (RemoteException e) {
            zza.d(e, "Unable to call %s on %s.", "removeSessionManagerListener", "zzay");
        }
    }

    public <T extends Session> void addSessionManagerListener(SessionManagerListener<T> sessionManagerListener, Class<T> cls) throws NullPointerException {
        if (sessionManagerListener == null) {
            throw new NullPointerException("SessionManagerListener can't be null");
        }
        Preconditions.checkNotNull(cls);
        Preconditions.checkMainThread("Must be called from the main thread.");
        try {
            this.zzb.zzi(new zzbj(sessionManagerListener, cls));
        } catch (RemoteException e) {
            zza.d(e, "Unable to call %s on %s.", "addSessionManagerListener", "zzay");
        }
    }
}
