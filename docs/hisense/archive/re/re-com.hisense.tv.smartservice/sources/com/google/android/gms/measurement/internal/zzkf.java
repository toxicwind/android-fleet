package com.google.android.gms.measurement.internal;

import b.a.a.a.a;

/* JADX INFO: loaded from: classes.dex */
public final class zzkf extends zzam {
    public final /* synthetic */ zzkg zza;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzkf(zzkg zzkgVar, zzgq zzgqVar) {
        super(zzgqVar);
        this.zza = zzkgVar;
    }

    @Override // com.google.android.gms.measurement.internal.zzam
    public final void zzc() {
        this.zza.zza();
        a.b(this.zza.zzs, "Starting upload from DelayedRunnable");
        this.zza.zzf.zzV();
    }
}
