package com.google.android.gms.cast.framework;

import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import org.checkerframework.checker.initialization.qual.NotOnlyInitialized;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
public final class zzbj extends zzaz {

    @NotOnlyInitialized
    private final SessionManagerListener zza;
    private final Class zzb;

    public zzbj(SessionManagerListener sessionManagerListener, Class cls) {
        this.zza = sessionManagerListener;
        this.zzb = cls;
    }

    @Override // com.google.android.gms.cast.framework.zzba
    public final IObjectWrapper zzb() {
        return ObjectWrapper.wrap(this.zza);
    }

    @Override // com.google.android.gms.cast.framework.zzba
    public final void zzc(IObjectWrapper iObjectWrapper, int i) throws RemoteException {
        SessionManagerListener sessionManagerListener;
        Session session = (Session) ObjectWrapper.unwrap(iObjectWrapper);
        Class cls = this.zzb;
        if (!cls.isInstance(session) || (sessionManagerListener = this.zza) == null) {
            return;
        }
        sessionManagerListener.onSessionEnded((Session) cls.cast(session), i);
    }

    @Override // com.google.android.gms.cast.framework.zzba
    public final void zzd(IObjectWrapper iObjectWrapper) throws RemoteException {
        SessionManagerListener sessionManagerListener;
        Session session = (Session) ObjectWrapper.unwrap(iObjectWrapper);
        Class cls = this.zzb;
        if (!cls.isInstance(session) || (sessionManagerListener = this.zza) == null) {
            return;
        }
        sessionManagerListener.onSessionEnding((Session) cls.cast(session));
    }

    @Override // com.google.android.gms.cast.framework.zzba
    public final void zze(IObjectWrapper iObjectWrapper, int i) throws RemoteException {
        SessionManagerListener sessionManagerListener;
        Session session = (Session) ObjectWrapper.unwrap(iObjectWrapper);
        Class cls = this.zzb;
        if (!cls.isInstance(session) || (sessionManagerListener = this.zza) == null) {
            return;
        }
        sessionManagerListener.onSessionResumeFailed((Session) cls.cast(session), i);
    }

    @Override // com.google.android.gms.cast.framework.zzba
    public final void zzf(IObjectWrapper iObjectWrapper, boolean z) throws RemoteException {
        SessionManagerListener sessionManagerListener;
        Session session = (Session) ObjectWrapper.unwrap(iObjectWrapper);
        Class cls = this.zzb;
        if (!cls.isInstance(session) || (sessionManagerListener = this.zza) == null) {
            return;
        }
        sessionManagerListener.onSessionResumed((Session) cls.cast(session), z);
    }

    @Override // com.google.android.gms.cast.framework.zzba
    public final void zzg(IObjectWrapper iObjectWrapper, String str) throws RemoteException {
        SessionManagerListener sessionManagerListener;
        Session session = (Session) ObjectWrapper.unwrap(iObjectWrapper);
        Class cls = this.zzb;
        if (!cls.isInstance(session) || (sessionManagerListener = this.zza) == null) {
            return;
        }
        sessionManagerListener.onSessionResuming((Session) cls.cast(session), str);
    }

    @Override // com.google.android.gms.cast.framework.zzba
    public final void zzh(IObjectWrapper iObjectWrapper, int i) throws RemoteException {
        SessionManagerListener sessionManagerListener;
        Session session = (Session) ObjectWrapper.unwrap(iObjectWrapper);
        Class cls = this.zzb;
        if (!cls.isInstance(session) || (sessionManagerListener = this.zza) == null) {
            return;
        }
        sessionManagerListener.onSessionStartFailed((Session) cls.cast(session), i);
    }

    @Override // com.google.android.gms.cast.framework.zzba
    public final void zzi(IObjectWrapper iObjectWrapper, String str) throws RemoteException {
        SessionManagerListener sessionManagerListener;
        Session session = (Session) ObjectWrapper.unwrap(iObjectWrapper);
        Class cls = this.zzb;
        if (!cls.isInstance(session) || (sessionManagerListener = this.zza) == null) {
            return;
        }
        sessionManagerListener.onSessionStarted((Session) cls.cast(session), str);
    }

    @Override // com.google.android.gms.cast.framework.zzba
    public final void zzj(IObjectWrapper iObjectWrapper) throws RemoteException {
        SessionManagerListener sessionManagerListener;
        Session session = (Session) ObjectWrapper.unwrap(iObjectWrapper);
        Class cls = this.zzb;
        if (!cls.isInstance(session) || (sessionManagerListener = this.zza) == null) {
            return;
        }
        sessionManagerListener.onSessionStarting((Session) cls.cast(session));
    }

    @Override // com.google.android.gms.cast.framework.zzba
    public final void zzk(IObjectWrapper iObjectWrapper, int i) throws RemoteException {
        SessionManagerListener sessionManagerListener;
        Session session = (Session) ObjectWrapper.unwrap(iObjectWrapper);
        Class cls = this.zzb;
        if (!cls.isInstance(session) || (sessionManagerListener = this.zza) == null) {
            return;
        }
        sessionManagerListener.onSessionSuspended((Session) cls.cast(session), i);
    }
}
