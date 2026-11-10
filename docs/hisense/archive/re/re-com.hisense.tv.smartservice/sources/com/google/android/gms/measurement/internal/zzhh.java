package com.google.android.gms.measurement.internal;

import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes.dex */
public final class zzhh implements Runnable {
    public final /* synthetic */ AtomicReference zza;
    public final /* synthetic */ boolean zzb;
    public final /* synthetic */ zzia zzc;

    public zzhh(zzia zziaVar, AtomicReference atomicReference, boolean z) {
        this.zzc = zziaVar;
        this.zza = atomicReference;
        this.zzb = z;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzc.zzs.zzt().zzx(this.zza, this.zzb);
    }
}
