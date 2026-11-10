package com.google.android.gms.measurement.internal;

/* JADX INFO: loaded from: classes.dex */
public final class zzht implements Runnable {
    public final /* synthetic */ Boolean zza;
    public final /* synthetic */ zzia zzb;

    public zzht(zzia zziaVar, Boolean bool) {
        this.zzb = zziaVar;
        this.zza = bool;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzb.zzac(this.zza, true);
    }
}
