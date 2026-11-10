package com.google.android.gms.measurement.internal;

import android.os.RemoteException;
import b.a.a.a.a;
import com.google.android.gms.common.internal.Preconditions;

/* JADX INFO: loaded from: classes.dex */
public final class zzis implements Runnable {
    public final /* synthetic */ zzp zza;
    public final /* synthetic */ zzjo zzb;

    public zzis(zzjo zzjoVar, zzp zzpVar) {
        this.zzb = zzjoVar;
        this.zza = zzpVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzjo zzjoVar = this.zzb;
        zzeb zzebVar = zzjoVar.zzb;
        if (zzebVar == null) {
            a.a(zzjoVar.zzs, "Failed to reset data on the service: not connected to service");
            return;
        }
        try {
            Preconditions.checkNotNull(this.zza);
            zzebVar.zzm(this.zza);
        } catch (RemoteException e2) {
            a.a(this.zzb.zzs, "Failed to reset data on the service: remote exception", e2);
        }
        this.zzb.zzQ();
    }
}
