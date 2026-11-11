package com.google.android.gms.cast.framework.media;

import com.google.android.gms.cast.MediaQueueItem;
import org.json.JSONObject;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
final class zzac extends zzbg {
    final /* synthetic */ MediaQueueItem[] zza;
    final /* synthetic */ int zzb;
    final /* synthetic */ JSONObject zzc;
    final /* synthetic */ RemoteMediaClient zzd;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzac(RemoteMediaClient remoteMediaClient, MediaQueueItem[] mediaQueueItemArr, int i, JSONObject jSONObject) {
        super(remoteMediaClient, false);
        this.zza = mediaQueueItemArr;
        this.zzb = i;
        this.zzc = jSONObject;
        this.zzd = remoteMediaClient;
    }

    @Override // com.google.android.gms.cast.framework.media.zzbg
    protected final void zza() throws com.google.android.gms.cast.internal.zzap {
        this.zzd.zzd.zzw(zzb(), this.zza, this.zzb, 0, -1, -1L, this.zzc);
    }
}
