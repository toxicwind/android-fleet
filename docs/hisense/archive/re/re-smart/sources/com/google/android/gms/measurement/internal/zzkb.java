package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import androidx.annotation.WorkerThread;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.measurement.zzod;

/* JADX INFO: loaded from: classes.dex */
public final class zzkb {

    @VisibleForTesting
    public long zza;

    @VisibleForTesting
    public long zzb;
    public final /* synthetic */ zzkd zzc;
    public final zzam zzd;

    public zzkb(zzkd zzkdVar) {
        this.zzc = zzkdVar;
        this.zzd = new zzka(this, this.zzc.zzs);
        long jElapsedRealtime = zzkdVar.zzs.zzav().elapsedRealtime();
        this.zza = jElapsedRealtime;
        this.zzb = jElapsedRealtime;
    }

    public final void zza() {
        this.zzd.zzb();
        this.zza = 0L;
        this.zzb = 0L;
    }

    @WorkerThread
    public final void zzb(long j) {
        this.zzd.zzb();
    }

    @WorkerThread
    public final void zzc(long j) {
        this.zzc.zzg();
        this.zzd.zzb();
        this.zza = j;
        this.zzb = j;
    }

    @WorkerThread
    public final boolean zzd(boolean z, boolean z2, long j) {
        this.zzc.zzg();
        this.zzc.zza();
        zzod.zzc();
        if (!this.zzc.zzs.zzf().zzs(null, zzdy.zzai) || this.zzc.zzs.zzJ()) {
            this.zzc.zzs.zzm().zzj.zzb(this.zzc.zzs.zzav().currentTimeMillis());
        }
        long j2 = j - this.zza;
        if (!z && j2 < 1000) {
            this.zzc.zzs.zzay().zzj().zzb("Screen exposed for less than 1000 ms. Event not sent. time", Long.valueOf(j2));
            return false;
        }
        if (!z2) {
            j2 = j - this.zzb;
            this.zzb = j;
        }
        this.zzc.zzs.zzay().zzj().zzb("Recording user engagement, ms", Long.valueOf(j2));
        Bundle bundle = new Bundle();
        bundle.putLong("_et", j2);
        zzkz.zzJ(this.zzc.zzs.zzs().zzj(!this.zzc.zzs.zzf().zzu()), bundle, true);
        if (!this.zzc.zzs.zzf().zzs(null, zzdy.zzT) && z2) {
            bundle.putLong("_fr", 1L);
        }
        if (!this.zzc.zzs.zzf().zzs(null, zzdy.zzT) || !z2) {
            this.zzc.zzs.zzq().zzG("auto", "_e", bundle);
        }
        this.zza = j;
        this.zzd.zzb();
        this.zzd.zzd(3600000L);
        return true;
    }
}
