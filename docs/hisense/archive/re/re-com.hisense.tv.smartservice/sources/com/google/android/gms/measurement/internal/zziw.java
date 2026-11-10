package com.google.android.gms.measurement.internal;

import android.os.RemoteException;
import b.a.a.a.a;

/* JADX INFO: loaded from: classes.dex */
public final class zziw implements Runnable {
    public final /* synthetic */ zzih zza;
    public final /* synthetic */ zzjo zzb;

    public zziw(zzjo zzjoVar, zzih zzihVar) {
        this.zzb = zzjoVar;
        this.zza = zzihVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzjo zzjoVar = this.zzb;
        zzeb zzebVar = zzjoVar.zzb;
        if (zzebVar == null) {
            a.a(zzjoVar.zzs, "Failed to send current screen to service");
            return;
        }
        try {
            zzih zzihVar = this.zza;
            if (zzihVar == null) {
                zzebVar.zzq(0L, null, null, zzjoVar.zzs.zzau().getPackageName());
            } else {
                zzebVar.zzq(zzihVar.zzc, zzihVar.zza, zzihVar.zzb, zzjoVar.zzs.zzau().getPackageName());
            }
            this.zzb.zzQ();
        } catch (RemoteException e2) {
            a.a(this.zzb.zzs, "Failed to send current screen to the service", e2);
        }
    }
}
