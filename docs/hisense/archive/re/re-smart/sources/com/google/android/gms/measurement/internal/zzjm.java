package com.google.android.gms.measurement.internal;

/* JADX INFO: loaded from: classes.dex */
public final class zzjm implements Runnable {
    public final /* synthetic */ zzjn zza;

    public zzjm(zzjn zzjnVar) {
        this.zza = zzjnVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzjo zzjoVar = this.zza.zza;
        zzjoVar.zzb = null;
        zzjoVar.zzP();
    }
}
