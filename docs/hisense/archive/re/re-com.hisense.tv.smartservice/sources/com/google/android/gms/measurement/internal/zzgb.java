package com.google.android.gms.measurement.internal;

import java.util.List;
import java.util.concurrent.Callable;

/* JADX INFO: loaded from: classes.dex */
public final class zzgb implements Callable<List<zzab>> {
    public final /* synthetic */ String zza;
    public final /* synthetic */ String zzb;
    public final /* synthetic */ String zzc;
    public final /* synthetic */ zzgn zzd;

    public zzgb(zzgn zzgnVar, String str, String str2, String str3) {
        this.zzd = zzgnVar;
        this.zza = str;
        this.zzb = str2;
        this.zzc = str3;
    }

    @Override // java.util.concurrent.Callable
    public final /* bridge */ /* synthetic */ List<zzab> call() {
        this.zzd.zza.zzA();
        return this.zzd.zza.zzi().zzs(this.zza, this.zzb, this.zzc);
    }
}
