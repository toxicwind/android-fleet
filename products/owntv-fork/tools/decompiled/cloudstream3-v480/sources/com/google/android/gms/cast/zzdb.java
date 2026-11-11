package com.google.android.gms.cast;

import com.google.android.gms.common.api.GoogleApiClient;
import org.json.JSONObject;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
final class zzdb extends zzdn {
    final /* synthetic */ MediaInfo zza;
    final /* synthetic */ boolean zzb;
    final /* synthetic */ long zzc;
    final /* synthetic */ long[] zzd;
    final /* synthetic */ JSONObject zze;
    final /* synthetic */ RemoteMediaPlayer zzf;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzdb(RemoteMediaPlayer remoteMediaPlayer, GoogleApiClient googleApiClient, MediaInfo mediaInfo, boolean z, long j, long[] jArr, JSONObject jSONObject) {
        super(remoteMediaPlayer, googleApiClient);
        this.zza = mediaInfo;
        this.zzb = z;
        this.zzc = j;
        this.zzd = jArr;
        this.zze = jSONObject;
        this.zzf = remoteMediaPlayer;
    }

    @Override // com.google.android.gms.cast.zzdn
    protected final void zza(com.google.android.gms.cast.internal.zzx zzxVar) {
        RemoteMediaPlayer remoteMediaPlayer = this.zzf;
        synchronized (remoteMediaPlayer.zza) {
            com.google.android.gms.cast.internal.zzar zzarVar = remoteMediaPlayer.zzb;
            com.google.android.gms.cast.internal.zzat zzatVarZzb = zzb();
            MediaLoadRequestData.Builder builder = new MediaLoadRequestData.Builder();
            builder.setMediaInfo(this.zza);
            builder.setAutoplay(Boolean.valueOf(this.zzb));
            builder.setCurrentTime(this.zzc);
            builder.setActiveTrackIds(this.zzd);
            builder.setCustomData(this.zze);
            zzarVar.zzp(zzatVarZzb, builder.build());
        }
    }
}
