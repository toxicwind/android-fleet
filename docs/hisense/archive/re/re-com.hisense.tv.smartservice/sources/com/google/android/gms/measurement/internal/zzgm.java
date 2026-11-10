package com.google.android.gms.measurement.internal;

/* JADX INFO: loaded from: classes.dex */
public final class zzgm implements Runnable {
    public final /* synthetic */ String zza;
    public final /* synthetic */ String zzb;
    public final /* synthetic */ String zzc;
    public final /* synthetic */ long zzd;
    public final /* synthetic */ zzgn zze;

    public zzgm(zzgn zzgnVar, String str, String str2, String str3, long j) {
        this.zze = zzgnVar;
        this.zza = str;
        this.zzb = str2;
        this.zzc = str3;
        this.zzd = j;
    }

    @Override // java.lang.Runnable
    public final void run() {
        String str = this.zza;
        if (str == null) {
            this.zze.zza.zzq().zzs().zzy(this.zzb, null);
        } else {
            this.zze.zza.zzq().zzs().zzy(this.zzb, new zzih(this.zzc, str, this.zzd));
        }
    }
}
