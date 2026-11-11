package com.google.android.gms.cast.framework;

import android.os.RemoteException;
import com.google.android.gms.common.ConnectionResult;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
final class zzz extends com.google.android.gms.cast.zzq {
    final /* synthetic */ CastSession zza;

    /* synthetic */ zzz(CastSession castSession, zzaa zzaaVar) {
        this.zza = castSession;
    }

    @Override // com.google.android.gms.cast.zzq
    public final void zza() {
        CastSession castSession = this.zza;
        if (castSession.zze == null) {
            return;
        }
        try {
            if (castSession.zzj != null) {
                castSession.zzj.zzq();
            }
            castSession.zze.zzh(null);
        } catch (RemoteException e) {
            CastSession.zzb.d(e, "Unable to call %s on %s.", "onConnected", "zzam");
        }
        CastSession castSession2 = this.zza;
        if (castSession2.zzm != null) {
            castSession2.zzm.zza();
        }
    }

    @Override // com.google.android.gms.cast.zzq
    public final void zzb(int i) {
        CastSession castSession = this.zza;
        if (castSession.zze == null) {
            return;
        }
        try {
            castSession.zze.zzi(new ConnectionResult(i));
        } catch (RemoteException e) {
            CastSession.zzb.d(e, "Unable to call %s on %s.", "onConnectionFailed", "zzam");
        }
    }

    @Override // com.google.android.gms.cast.zzq
    public final void zzc(int i) {
        CastSession castSession = this.zza;
        if (castSession.zze == null) {
            return;
        }
        try {
            castSession.zze.zzj(i);
        } catch (RemoteException e) {
            CastSession.zzb.d(e, "Unable to call %s on %s.", "onConnectionSuspended", "zzam");
        }
    }

    @Override // com.google.android.gms.cast.zzq
    public final void zzd(int i) {
        CastSession castSession = this.zza;
        if (castSession.zze == null) {
            return;
        }
        try {
            castSession.zze.zzi(new ConnectionResult(i));
        } catch (RemoteException e) {
            CastSession.zzb.d(e, "Unable to call %s on %s.", "onDisconnected", "zzam");
        }
    }
}
