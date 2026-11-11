package com.google.android.gms.cast;

import com.google.android.gms.common.api.GoogleApiClient;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
final class zzdk implements com.google.android.gms.cast.internal.zzas {
    final /* synthetic */ RemoteMediaPlayer zza;
    private GoogleApiClient zzb;
    private long zzc = 0;

    public zzdk(RemoteMediaPlayer remoteMediaPlayer) {
        this.zza = remoteMediaPlayer;
    }

    @Override // com.google.android.gms.cast.internal.zzas
    public final long zza() {
        long j = this.zzc + 1;
        this.zzc = j;
        return j;
    }

    @Override // com.google.android.gms.cast.internal.zzas
    public final void zzb(String str, String str2, long j, String str3) {
        if (this.zzb == null) {
            throw new IllegalStateException("No GoogleApiClient available");
        }
        Cast.CastApi.sendMessage(this.zzb, str, str2).setResultCallback(new zzdj(this, j));
    }

    public final void zzc(GoogleApiClient googleApiClient) {
        this.zzb = googleApiClient;
    }
}
