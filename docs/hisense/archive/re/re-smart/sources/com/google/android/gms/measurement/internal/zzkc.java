package com.google.android.gms.measurement.internal;

import android.app.ActivityManager;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.annotation.WorkerThread;
import b.a.a.a.a;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.measurement.zznu;

/* JADX INFO: loaded from: classes.dex */
public final class zzkc {
    public final /* synthetic */ zzkd zza;

    public zzkc(zzkd zzkdVar) {
        this.zza = zzkdVar;
    }

    @WorkerThread
    public final void zza() {
        this.zza.zzg();
        if (this.zza.zzs.zzm().zzk(this.zza.zzs.zzav().currentTimeMillis())) {
            this.zza.zzs.zzm().zzg.zza(true);
            ActivityManager.RunningAppProcessInfo runningAppProcessInfo = new ActivityManager.RunningAppProcessInfo();
            ActivityManager.getMyMemoryState(runningAppProcessInfo);
            if (runningAppProcessInfo.importance == 100) {
                a.b(this.zza.zzs, "Detected application was in foreground");
                zzc(this.zza.zzs.zzav().currentTimeMillis(), false);
            }
        }
    }

    @WorkerThread
    public final void zzb(long j, boolean z) {
        this.zza.zzg();
        this.zza.zzm();
        if (this.zza.zzs.zzm().zzk(j)) {
            this.zza.zzs.zzm().zzg.zza(true);
        }
        this.zza.zzs.zzm().zzj.zzb(j);
        if (this.zza.zzs.zzm().zzg.zzb()) {
            zzc(j, z);
        }
    }

    @VisibleForTesting
    @WorkerThread
    public final void zzc(long j, boolean z) {
        this.zza.zzg();
        if (this.zza.zzs.zzJ()) {
            this.zza.zzs.zzm().zzj.zzb(j);
            this.zza.zzs.zzay().zzj().zzb("Session started, time", Long.valueOf(this.zza.zzs.zzav().elapsedRealtime()));
            Long lValueOf = Long.valueOf(j / 1000);
            this.zza.zzs.zzq().zzZ("auto", "_sid", lValueOf, j);
            this.zza.zzs.zzm().zzg.zza(false);
            Bundle bundle = new Bundle();
            bundle.putLong("_sid", lValueOf.longValue());
            if (this.zza.zzs.zzf().zzs(null, zzdy.zzad) && z) {
                bundle.putLong("_aib", 1L);
            }
            this.zza.zzs.zzq().zzH("auto", "_s", j, bundle);
            zznu.zzc();
            if (this.zza.zzs.zzf().zzs(null, zzdy.zzah)) {
                String strZza = this.zza.zzs.zzm().zzo.zza();
                if (TextUtils.isEmpty(strZza)) {
                    return;
                }
                Bundle bundle2 = new Bundle();
                bundle2.putString("_ffr", strZza);
                this.zza.zzs.zzq().zzH("auto", "_ssr", j, bundle2);
            }
        }
    }
}
