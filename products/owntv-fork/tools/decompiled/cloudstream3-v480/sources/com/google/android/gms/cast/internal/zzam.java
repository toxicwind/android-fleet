package com.google.android.gms.cast.internal;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
final class zzam implements zzat {
    final /* synthetic */ zzat zza;
    final /* synthetic */ zzar zzb;

    zzam(zzar zzarVar, zzat zzatVar) {
        this.zza = zzatVar;
        this.zzb = zzarVar;
    }

    @Override // com.google.android.gms.cast.internal.zzat
    public final void zza(String str, long j, int i, Object obj, long j2, long j3) {
        this.zzb.zzx = null;
        zzat zzatVar = this.zza;
        if (zzatVar != null) {
            zzatVar.zza(str, j, i, obj, j2, j3);
        }
    }

    @Override // com.google.android.gms.cast.internal.zzat
    public final void zzb(String str, long j, long j2, long j3) {
        zzat zzatVar = this.zza;
        if (zzatVar != null) {
            zzatVar.zzb(str, j, j2, j3);
        }
    }
}
