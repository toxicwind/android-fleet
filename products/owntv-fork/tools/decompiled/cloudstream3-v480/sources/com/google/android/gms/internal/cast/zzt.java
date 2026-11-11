package com.google.android.gms.internal.cast;

import com.google.android.gms.cast.MediaStatus;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
final class zzt implements com.google.android.gms.cast.framework.zzu {
    final /* synthetic */ zzx zza;

    /* synthetic */ zzt(zzx zzxVar, zzw zzwVar) {
        this.zza = zzxVar;
    }

    @Override // com.google.android.gms.cast.framework.zzu
    public final void zza() {
        zzx.zzf(this.zza, new zzcb(new zzca(3)));
    }

    @Override // com.google.android.gms.cast.framework.zzu
    public final void zzb() {
        this.zza.zzg().zze();
    }

    @Override // com.google.android.gms.cast.framework.zzu
    public final void zzc(String str, long j, int i, long j2, long j3) {
        zzz zzzVarZzg = this.zza.zzg();
        zzby zzbyVar = new zzby(str);
        zzbyVar.zzf(j);
        zzbyVar.zzh(i);
        zzbyVar.zzg(j2);
        zzbyVar.zze(j3);
        zzzVarZzg.zzb(new zzbz(zzbyVar));
    }

    @Override // com.google.android.gms.cast.framework.zzu
    public final void zzd(MediaStatus mediaStatus) {
        if (mediaStatus == null) {
            return;
        }
        this.zza.zzg().zzi(new zzr(new zzq(mediaStatus)));
    }
}
