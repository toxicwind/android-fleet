package com.google.android.gms.measurement.internal;

import b.a.a.a.a;
import com.google.android.gms.common.internal.Preconditions;

/* JADX INFO: loaded from: classes.dex */
public final class zziq implements Runnable {
    public final /* synthetic */ zzp zza;
    public final /* synthetic */ boolean zzb;
    public final /* synthetic */ zzkv zzc;
    public final /* synthetic */ zzjo zzd;

    public zziq(zzjo zzjoVar, zzp zzpVar, boolean z, zzkv zzkvVar) {
        this.zzd = zzjoVar;
        this.zza = zzpVar;
        this.zzb = z;
        this.zzc = zzkvVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzjo zzjoVar = this.zzd;
        zzeb zzebVar = zzjoVar.zzb;
        if (zzebVar == null) {
            a.a(zzjoVar.zzs, "Discarding data. Failed to set user property");
            return;
        }
        Preconditions.checkNotNull(this.zza);
        this.zzd.zzD(zzebVar, this.zzb ? null : this.zzc, this.zza);
        this.zzd.zzQ();
    }
}
