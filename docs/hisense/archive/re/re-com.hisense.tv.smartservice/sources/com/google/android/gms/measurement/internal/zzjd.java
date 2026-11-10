package com.google.android.gms.measurement.internal;

import b.a.a.a.a;
import com.google.android.gms.common.internal.Preconditions;

/* JADX INFO: loaded from: classes.dex */
public final class zzjd implements Runnable {
    public final /* synthetic */ zzp zza;
    public final /* synthetic */ boolean zzb;
    public final /* synthetic */ zzat zzc;
    public final /* synthetic */ String zzd;
    public final /* synthetic */ zzjo zze;

    public zzjd(zzjo zzjoVar, boolean z, zzp zzpVar, boolean z2, zzat zzatVar, String str) {
        this.zze = zzjoVar;
        this.zza = zzpVar;
        this.zzb = z2;
        this.zzc = zzatVar;
        this.zzd = str;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzjo zzjoVar = this.zze;
        zzeb zzebVar = zzjoVar.zzb;
        if (zzebVar == null) {
            a.a(zzjoVar.zzs, "Discarding data. Failed to send event to service");
            return;
        }
        Preconditions.checkNotNull(this.zza);
        this.zze.zzD(zzebVar, this.zzb ? null : this.zzc, this.zza);
        this.zze.zzQ();
    }
}
