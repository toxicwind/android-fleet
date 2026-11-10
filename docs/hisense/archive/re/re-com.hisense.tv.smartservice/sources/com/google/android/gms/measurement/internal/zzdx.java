package com.google.android.gms.measurement.internal;

import androidx.annotation.GuardedBy;
import com.google.android.gms.common.util.VisibleForTesting;

/* JADX INFO: loaded from: classes.dex */
@VisibleForTesting
public final class zzdx<V> {
    public static final Object zza = new Object();
    public final String zzb;
    public final zzdu<V> zzc;
    public final V zzd;
    public final V zze;
    public final Object zzf = new Object();

    @GuardedBy("overrideLock")
    public volatile V zzg = null;

    @GuardedBy("cachingLock")
    public volatile V zzh = null;

    /* JADX WARN: Multi-variable type inference failed */
    public /* synthetic */ zzdx(String str, Object obj, Object obj2, zzdu zzduVar, zzdw zzdwVar) {
        this.zzb = str;
        this.zzd = obj;
        this.zze = obj2;
        this.zzc = zzduVar;
    }

    public final V zza(V v) {
        synchronized (this.zzf) {
            V v2 = this.zzg;
        }
        if (v != null) {
            return v;
        }
        if (zzdv.zza == null) {
            return this.zzd;
        }
        synchronized (zza) {
            if (zzaa.zza()) {
                return this.zzh == null ? this.zzd : this.zzh;
            }
            try {
                for (zzdx<?> zzdxVar : zzdy.zzaD) {
                    if (zzaa.zza()) {
                        throw new IllegalStateException("Refreshing flag cache must be done on a worker thread.");
                    }
                    V v3 = null;
                    try {
                        zzdu<?> zzduVar = zzdxVar.zzc;
                        if (zzduVar != null) {
                            v3 = (V) zzduVar.zza();
                        }
                    } catch (IllegalStateException unused) {
                    }
                    synchronized (zza) {
                        zzdxVar.zzh = v3;
                    }
                }
            } catch (SecurityException unused2) {
            }
            zzdu<V> zzduVar2 = this.zzc;
            if (zzduVar2 == null) {
                return this.zzd;
            }
            try {
                return zzduVar2.zza();
            } catch (IllegalStateException unused3) {
                return this.zzd;
            } catch (SecurityException unused4) {
                return this.zzd;
            }
        }
    }

    public final String zzb() {
        return this.zzb;
    }
}
