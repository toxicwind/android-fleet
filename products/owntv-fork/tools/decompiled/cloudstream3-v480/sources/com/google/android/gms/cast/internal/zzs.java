package com.google.android.gms.cast.internal;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
final class zzs implements Runnable {
    final /* synthetic */ zzx zza;
    final /* synthetic */ int zzb;

    zzs(zzw zzwVar, zzx zzxVar, int i) {
        this.zza = zzxVar;
        this.zzb = i;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zza.zzj.onApplicationDisconnected(this.zzb);
    }
}
