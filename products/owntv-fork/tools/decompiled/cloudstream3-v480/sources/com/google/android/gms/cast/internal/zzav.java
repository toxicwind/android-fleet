package com.google.android.gms.cast.internal;

import android.os.Handler;
import android.os.Looper;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.DefaultClock;
import com.google.android.gms.internal.cast.zzet;
import java.util.Locale;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
public final class zzav {
    public static final Object zza = new Object();
    protected final Logger zzb;
    zzat zze;
    Runnable zzf;
    private final long zzg;
    private final String zzh;
    private final Clock zzj = DefaultClock.getInstance();
    long zzc = -1;
    long zzd = 0;
    private final Handler zzi = new zzet(Looper.getMainLooper());

    public zzav(long j, String str) {
        this.zzg = j;
        this.zzh = str;
        this.zzb = new Logger("RequestTracker", str);
    }

    public static /* synthetic */ void zza(zzav zzavVar) {
        synchronized (zza) {
            if (zzavVar.zzf()) {
                zzavVar.zzh(15, null);
            }
        }
    }

    private final void zzg(int i, Object obj, String str) {
        this.zzb.d(str, new Object[0]);
        Object obj2 = zza;
        synchronized (obj2) {
            if (this.zze != null) {
                ((zzat) Preconditions.checkNotNull(this.zze)).zza(this.zzh, this.zzc, i, obj, this.zzd, this.zzj.currentTimeMillis());
            }
            this.zzc = -1L;
            this.zze = null;
            synchronized (obj2) {
                Runnable runnable = this.zzf;
                if (runnable != null) {
                    this.zzi.removeCallbacks(runnable);
                    this.zzf = null;
                }
            }
        }
    }

    private final boolean zzh(int i, Object obj) {
        synchronized (zza) {
            if (!zzf()) {
                return false;
            }
            zzg(i, null, String.format(Locale.ROOT, "clearing request %d", Long.valueOf(this.zzc)));
            return true;
        }
    }

    public final void zzb(long j, zzat zzatVar) {
        zzat zzatVar2;
        long j2;
        long j3;
        long jCurrentTimeMillis = this.zzj.currentTimeMillis();
        Object obj = zza;
        synchronized (obj) {
            zzatVar2 = this.zze;
            j2 = this.zzc;
            j3 = this.zzd;
            this.zzc = j;
            this.zze = zzatVar;
            this.zzd = jCurrentTimeMillis;
        }
        if (zzatVar2 != null) {
            zzatVar2.zzb(this.zzh, j2, j3, jCurrentTimeMillis);
        }
        synchronized (obj) {
            Runnable runnable = this.zzf;
            if (runnable != null) {
                this.zzi.removeCallbacks(runnable);
            }
            Runnable runnable2 = new Runnable() { // from class: com.google.android.gms.cast.internal.zzau
                @Override // java.lang.Runnable
                public final void run() {
                    zzav.zza(this.zza);
                }
            };
            this.zzf = runnable2;
            this.zzi.postDelayed(runnable2, this.zzg);
        }
    }

    public final boolean zzc(int i) {
        return zzh(2002, null);
    }

    public final boolean zzd(long j, int i, Object obj) {
        synchronized (zza) {
            if (!zze(j)) {
                return false;
            }
            zzg(i, obj, String.format(Locale.ROOT, "request %d completed", Long.valueOf(j)));
            return true;
        }
    }

    public final boolean zze(long j) {
        boolean z;
        synchronized (zza) {
            long j2 = this.zzc;
            z = false;
            if (j2 != -1 && j2 == j) {
                z = true;
            }
        }
        return z;
    }

    public final boolean zzf() {
        boolean z;
        synchronized (zza) {
            z = this.zzc != -1;
        }
        return z;
    }
}
