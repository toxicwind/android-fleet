package com.google.android.gms.measurement.internal;

/* JADX INFO: loaded from: classes.dex */
public final class zzl implements Runnable {
    public final /* synthetic */ com.google.android.gms.internal.measurement.zzcf zza;
    public final /* synthetic */ String zzb;
    public final /* synthetic */ String zzc;
    public final /* synthetic */ AppMeasurementDynamiteService zzd;

    public zzl(AppMeasurementDynamiteService appMeasurementDynamiteService, com.google.android.gms.internal.measurement.zzcf zzcfVar, String str, String str2) {
        this.zzd = appMeasurementDynamiteService;
        this.zza = zzcfVar;
        this.zzb = str;
        this.zzc = str2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzd.zza.zzt().zzv(this.zza, this.zzb, this.zzc);
    }
}
