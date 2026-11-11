package com.google.android.gms.cast.framework.media.internal;

import com.google.android.gms.cast.framework.media.RemoteMediaClient;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
final class zzs extends RemoteMediaClient.Callback {
    final /* synthetic */ zzu zza;

    /* synthetic */ zzs(zzu zzuVar, zzt zztVar) {
        this.zza = zzuVar;
    }

    @Override // com.google.android.gms.cast.framework.media.RemoteMediaClient.Callback
    public final void onAdBreakStatusUpdated() {
        this.zza.zzl(false);
    }

    @Override // com.google.android.gms.cast.framework.media.RemoteMediaClient.Callback
    public final void onMetadataUpdated() {
        this.zza.zzl(false);
    }

    @Override // com.google.android.gms.cast.framework.media.RemoteMediaClient.Callback
    public final void onPreloadStatusUpdated() {
        this.zza.zzl(false);
    }

    @Override // com.google.android.gms.cast.framework.media.RemoteMediaClient.Callback
    public final void onQueueStatusUpdated() {
        this.zza.zzl(false);
    }

    @Override // com.google.android.gms.cast.framework.media.RemoteMediaClient.Callback
    public final void onStatusUpdated() {
        this.zza.zzl(false);
    }
}
