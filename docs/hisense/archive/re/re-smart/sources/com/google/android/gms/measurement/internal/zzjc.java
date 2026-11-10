package com.google.android.gms.measurement.internal;

import android.os.RemoteException;
import b.a.a.a.a;
import com.google.android.gms.common.internal.Preconditions;

/* JADX INFO: loaded from: classes.dex */
public final class zzjc implements Runnable {
    public final /* synthetic */ zzp zza;
    public final /* synthetic */ zzjo zzb;

    public zzjc(zzjo zzjoVar, zzp zzpVar) {
        this.zzb = zzjoVar;
        this.zza = zzpVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzjo zzjoVar = this.zzb;
        zzeb zzebVar = zzjoVar.zzb;
        if (zzebVar == null) {
            a.a(zzjoVar.zzs, "Failed to send consent settings to service");
            return;
        }
        try {
            Preconditions.checkNotNull(this.zza);
            zzebVar.zzp(this.zza);
            this.zzb.zzQ();
        } catch (RemoteException e2) {
            a.a(this.zzb.zzs, "Failed to send consent settings to the service", e2);
        }
    }
}
