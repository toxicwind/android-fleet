package com.google.android.gms.internal.measurement;

/* JADX INFO: loaded from: classes.dex */
public abstract class zzdt implements Runnable {
    public final long zzh;
    public final long zzi;
    public final boolean zzj;
    public final /* synthetic */ zzee zzk;

    public zzdt(zzee zzeeVar, boolean z) {
        this.zzk = zzeeVar;
        this.zzh = zzeeVar.zza.currentTimeMillis();
        this.zzi = zzeeVar.zza.elapsedRealtime();
        this.zzj = z;
    }

    @Override // java.lang.Runnable
    public final void run() {
        if (this.zzk.zzh) {
            zzb();
            return;
        }
        try {
            zza();
        } catch (Exception e2) {
            this.zzk.zzS(e2, false, this.zzj);
            zzb();
        }
    }

    public abstract void zza();

    public void zzb() {
    }
}
