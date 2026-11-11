package com.google.android.gms.cast.framework.media.widget;

import com.google.android.gms.cast.framework.media.RemoteMediaClient;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
final class zzj implements Runnable {
    final /* synthetic */ RemoteMediaClient zza;
    final /* synthetic */ zzk zzb;

    zzj(zzk zzkVar, RemoteMediaClient remoteMediaClient) {
        this.zza = remoteMediaClient;
        this.zzb = zzkVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzb.zzb.zzm(this.zza);
    }
}
