package com.google.android.gms.cast.internal;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
final class zzt implements Runnable {
    final /* synthetic */ zzx zza;
    final /* synthetic */ zzac zzb;

    zzt(zzw zzwVar, zzx zzxVar, zzac zzacVar) {
        this.zza = zzxVar;
        this.zzb = zzacVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzx.zzI(this.zza, this.zzb);
    }
}
