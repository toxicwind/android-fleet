package com.google.android.gms.cast.framework;

import com.google.android.gms.cast.MediaStatus;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
final class zzt extends RemoteMediaClient.Callback {
    final /* synthetic */ CastSession zza;

    zzt(CastSession castSession) {
        this.zza = castSession;
    }

    @Override // com.google.android.gms.cast.framework.media.RemoteMediaClient.Callback
    public final void onStatusUpdated() {
        CastSession castSession = this.zza;
        MediaStatus mediaStatus = castSession.zzj != null ? castSession.zzj.getMediaStatus() : null;
        if (castSession.zzm != null) {
            castSession.zzm.zzd(mediaStatus);
        }
    }

    @Override // com.google.android.gms.cast.framework.media.RemoteMediaClient.Callback
    public final void zza(String str, long j, int i, long j2, long j3) {
        CastSession castSession = this.zza;
        if (castSession.zzm != null) {
            castSession.zzm.zzc(str, j, i, j2, j3);
        }
    }
}
