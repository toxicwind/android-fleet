package com.google.android.gms.internal.cast;

import java.util.concurrent.Callable;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
final class zzvw extends zzvn {
    final /* synthetic */ zzvx zza;
    private final Callable zzb;

    zzvw(zzvx zzvxVar, Callable callable) {
        this.zza = zzvxVar;
        callable.getClass();
        this.zzb = callable;
    }

    @Override // com.google.android.gms.internal.cast.zzvn
    final Object zza() throws Exception {
        return this.zzb.call();
    }

    @Override // com.google.android.gms.internal.cast.zzvn
    final String zzb() {
        return this.zzb.toString();
    }

    @Override // com.google.android.gms.internal.cast.zzvn
    final void zzc(Throwable th) {
        this.zza.zzg(th);
    }

    @Override // com.google.android.gms.internal.cast.zzvn
    final void zzd(Object obj) {
        this.zza.zzf(obj);
    }

    @Override // com.google.android.gms.internal.cast.zzvn
    final boolean zzf() {
        return this.zza.isDone();
    }
}
