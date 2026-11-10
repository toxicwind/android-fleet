package com.google.android.gms.measurement.internal;

import android.os.Bundle;

/* JADX INFO: loaded from: classes.dex */
public final class zzhf implements Runnable {
    public final /* synthetic */ String zza;
    public final /* synthetic */ String zzb;
    public final /* synthetic */ long zzc;
    public final /* synthetic */ Bundle zzd;
    public final /* synthetic */ boolean zze;
    public final /* synthetic */ boolean zzf;
    public final /* synthetic */ boolean zzg;
    public final /* synthetic */ String zzh;
    public final /* synthetic */ zzia zzi;

    public zzhf(zzia zziaVar, String str, String str2, long j, Bundle bundle, boolean z, boolean z2, boolean z3, String str3) {
        this.zzi = zziaVar;
        this.zza = str;
        this.zzb = str2;
        this.zzc = j;
        this.zzd = bundle;
        this.zze = z;
        this.zzf = z2;
        this.zzg = z3;
        this.zzh = str3;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzi.zzI(this.zza, this.zzb, this.zzc, this.zzd, this.zze, this.zzf, this.zzg, this.zzh);
    }
}
