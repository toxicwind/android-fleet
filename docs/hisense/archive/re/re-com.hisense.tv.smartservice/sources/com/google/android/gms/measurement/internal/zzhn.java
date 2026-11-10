package com.google.android.gms.measurement.internal;

import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes.dex */
public final class zzhn implements Runnable {
    public final /* synthetic */ AtomicReference zza;
    public final /* synthetic */ String zzb;
    public final /* synthetic */ String zzc;
    public final /* synthetic */ boolean zzd;
    public final /* synthetic */ zzia zze;

    public zzhn(zzia zziaVar, AtomicReference atomicReference, String str, String str2, String str3, boolean z) {
        this.zze = zziaVar;
        this.zza = atomicReference;
        this.zzb = str2;
        this.zzc = str3;
        this.zzd = z;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zze.zzs.zzt().zzz(this.zza, null, this.zzb, this.zzc, this.zzd);
    }
}
