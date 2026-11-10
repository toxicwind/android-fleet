package com.google.android.gms.measurement.internal;

import androidx.annotation.WorkerThread;

/* JADX INFO: loaded from: classes.dex */
public final class zzjz {
    public final /* synthetic */ zzkd zza;
    public zzjy zzb;

    public zzjz(zzkd zzkdVar) {
        this.zza = zzkdVar;
    }

    @WorkerThread
    public final void zza(long j) {
        this.zzb = new zzjy(this, this.zza.zzs.zzav().currentTimeMillis(), j);
        this.zza.zzd.postDelayed(this.zzb, 2000L);
    }

    @WorkerThread
    public final void zzb() {
        this.zza.zzg();
        zzjy zzjyVar = this.zzb;
        if (zzjyVar != null) {
            this.zza.zzd.removeCallbacks(zzjyVar);
        }
        this.zza.zzs.zzm().zzl.zza(false);
    }
}
