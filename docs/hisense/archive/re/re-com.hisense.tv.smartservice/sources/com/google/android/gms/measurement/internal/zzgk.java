package com.google.android.gms.measurement.internal;

import java.util.List;
import java.util.concurrent.Callable;

/* JADX INFO: loaded from: classes.dex */
public final class zzgk implements Callable<List<zzkx>> {
    public final /* synthetic */ String zza;
    public final /* synthetic */ zzgn zzb;

    public zzgk(zzgn zzgnVar, String str) {
        this.zzb = zzgnVar;
        this.zza = str;
    }

    @Override // java.util.concurrent.Callable
    public final /* bridge */ /* synthetic */ List<zzkx> call() {
        this.zzb.zza.zzA();
        return this.zzb.zza.zzi().zzu(this.zza);
    }
}
