package com.google.android.gms.cast.framework.media.internal;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
final class zzc extends zzi {
    final /* synthetic */ zze zza;

    /* synthetic */ zzc(zze zzeVar, zzd zzdVar) {
        this.zza = zzeVar;
    }

    @Override // com.google.android.gms.cast.framework.media.internal.zzj
    public final void zzb(long j, long j2) {
        this.zza.publishProgress(Long.valueOf(j), Long.valueOf(j2));
    }
}
