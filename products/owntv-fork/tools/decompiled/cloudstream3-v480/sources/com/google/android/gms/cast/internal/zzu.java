package com.google.android.gms.cast.internal;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
final class zzu implements Runnable {
    final /* synthetic */ zzx zza;
    final /* synthetic */ zza zzb;

    zzu(zzw zzwVar, zzx zzxVar, zza zzaVar) {
        this.zza = zzxVar;
        this.zzb = zzaVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzx.zzH(this.zza, this.zzb);
    }
}
