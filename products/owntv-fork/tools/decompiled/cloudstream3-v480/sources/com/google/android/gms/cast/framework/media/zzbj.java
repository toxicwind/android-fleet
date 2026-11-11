package com.google.android.gms.cast.framework.media;

import java.util.TimerTask;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
final class zzbj extends TimerTask {
    final /* synthetic */ zzbk zza;

    zzbj(zzbk zzbkVar) {
        this.zza = zzbkVar;
    }

    @Override // java.util.TimerTask, java.lang.Runnable
    public final void run() {
        zzbk zzbkVar = this.zza;
        RemoteMediaClient remoteMediaClient = zzbkVar.zza;
        remoteMediaClient.zzx(zzbkVar.zzb);
        remoteMediaClient.zzc.postDelayed(this, zzbkVar.zzc);
    }
}
