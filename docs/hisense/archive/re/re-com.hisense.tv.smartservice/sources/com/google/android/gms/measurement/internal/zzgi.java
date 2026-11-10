package com.google.android.gms.measurement.internal;

import java.util.concurrent.Callable;

/* JADX INFO: loaded from: classes.dex */
public final class zzgi implements Callable<byte[]> {
    public final /* synthetic */ zzat zza;
    public final /* synthetic */ String zzb;
    public final /* synthetic */ zzgn zzc;

    public zzgi(zzgn zzgnVar, zzat zzatVar, String str) {
        this.zzc = zzgnVar;
        this.zza = zzatVar;
        this.zzb = str;
    }

    @Override // java.util.concurrent.Callable
    public final /* bridge */ /* synthetic */ byte[] call() {
        this.zzc.zza.zzA();
        zzif zzifVarZzr = this.zzc.zza.zzr();
        zzat zzatVar = this.zza;
        String str = this.zzb;
        zzifVarZzr.zzg();
        zzfv.zzO();
        throw null;
    }
}
