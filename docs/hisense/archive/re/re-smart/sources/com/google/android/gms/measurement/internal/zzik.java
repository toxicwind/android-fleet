package com.google.android.gms.measurement.internal;

/* JADX INFO: loaded from: classes.dex */
public final class zzik implements Runnable {
    public final /* synthetic */ zzio zza;

    public zzik(zzio zzioVar) {
        this.zza = zzioVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzio zzioVar = this.zza;
        zzioVar.zza = zzioVar.zzh;
    }
}
