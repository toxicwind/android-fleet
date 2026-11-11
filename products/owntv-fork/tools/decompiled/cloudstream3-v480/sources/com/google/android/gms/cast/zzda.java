package com.google.android.gms.cast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import java.util.Locale;
import org.json.JSONObject;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
final class zzda extends zzdn {
    final /* synthetic */ int zza;
    final /* synthetic */ int zzb;
    final /* synthetic */ JSONObject zzc;
    final /* synthetic */ RemoteMediaPlayer zzd;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzda(RemoteMediaPlayer remoteMediaPlayer, GoogleApiClient googleApiClient, int i, int i2, JSONObject jSONObject) {
        super(remoteMediaPlayer, googleApiClient);
        this.zza = i;
        this.zzb = i2;
        this.zzc = jSONObject;
        this.zzd = remoteMediaPlayer;
    }

    @Override // com.google.android.gms.cast.zzdn
    protected final void zza(com.google.android.gms.cast.internal.zzx zzxVar) throws com.google.android.gms.cast.internal.zzap {
        RemoteMediaPlayer remoteMediaPlayer = this.zzd;
        int i = this.zza;
        int iZza = RemoteMediaPlayer.zza(remoteMediaPlayer, i);
        if (iZza == -1) {
            setResult(new zzdm(this, new Status(0)));
            return;
        }
        int i2 = this.zzb;
        if (i2 < 0) {
            setResult(new zzdm(this, new Status(2001, String.format(Locale.ROOT, "Invalid request: Invalid newIndex %d.", Integer.valueOf(i2)))));
            return;
        }
        if (iZza == i2) {
            setResult(new zzdm(this, new Status(0)));
            return;
        }
        MediaStatus mediaStatus = remoteMediaPlayer.getMediaStatus();
        if (mediaStatus == null) {
            setResult(new zzdm(this, new Status(2001, String.format(Locale.ROOT, "Invalid request: Invalid MediaStatus", new Object[0]))));
            return;
        }
        if (i2 > iZza) {
            i2++;
        }
        MediaQueueItem queueItem = mediaStatus.getQueueItem(i2);
        remoteMediaPlayer.zzb.zzz(zzb(), new int[]{i}, queueItem != null ? queueItem.getItemId() : 0, this.zzc);
    }
}
