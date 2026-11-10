package com.google.android.gms.measurement.internal;

import android.content.ComponentName;
import android.content.Context;

/* JADX INFO: loaded from: classes.dex */
public final class zzjl implements Runnable {
    public final /* synthetic */ zzjn zza;

    public zzjl(zzjn zzjnVar) {
        this.zza = zzjnVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzjo zzjoVar = this.zza.zza;
        Context contextZzau = zzjoVar.zzs.zzau();
        this.zza.zza.zzs.zzaw();
        zzjo.zzo(zzjoVar, new ComponentName(contextZzau, "com.google.android.gms.measurement.AppMeasurementService"));
    }
}
