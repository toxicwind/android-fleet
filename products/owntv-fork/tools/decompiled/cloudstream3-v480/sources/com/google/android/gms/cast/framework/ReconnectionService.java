package com.google.android.gms.cast.framework;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.gms.cast.internal.Logger;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
public class ReconnectionService extends Service {
    private static final Logger zza = new Logger("ReconnectionService");
    private zzat zzb;

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        zzat zzatVar = this.zzb;
        if (zzatVar != null) {
            try {
                return zzatVar.zzf(intent);
            } catch (RemoteException e) {
                zza.d(e, "Unable to call %s on %s.", "onBind", "zzat");
            }
        }
        return null;
    }

    @Override // android.app.Service
    public void onCreate() {
        CastContext sharedInstance = CastContext.getSharedInstance(this);
        zzat zzatVarZzc = com.google.android.gms.internal.cast.zzag.zzc(this, sharedInstance.getSessionManager().zzb(), sharedInstance.zzd().zza());
        this.zzb = zzatVarZzc;
        if (zzatVarZzc != null) {
            try {
                zzatVarZzc.zzg();
            } catch (RemoteException e) {
                zza.d(e, "Unable to call %s on %s.", "onCreate", "zzat");
            }
            super.onCreate();
        }
    }

    @Override // android.app.Service
    public void onDestroy() {
        zzat zzatVar = this.zzb;
        if (zzatVar != null) {
            try {
                zzatVar.zzh();
            } catch (RemoteException e) {
                zza.d(e, "Unable to call %s on %s.", "onDestroy", "zzat");
            }
            super.onDestroy();
        }
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        zzat zzatVar = this.zzb;
        if (zzatVar != null) {
            try {
                return zzatVar.zze(intent, i, i2);
            } catch (RemoteException e) {
                zza.d(e, "Unable to call %s on %s.", "onStartCommand", "zzat");
            }
        }
        return 2;
    }
}
