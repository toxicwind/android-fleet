package com.google.android.gms.cast.framework.media;

import android.os.Handler;
import java.util.HashSet;
import java.util.Set;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
final class zzbk {
    final /* synthetic */ RemoteMediaClient zza;
    private final long zzc;
    private boolean zze;
    private final Set zzb = new HashSet();
    private final Runnable zzd = new zzbj(this);

    public zzbk(RemoteMediaClient remoteMediaClient, long j) {
        this.zza = remoteMediaClient;
        this.zzc = j;
    }

    public final long zzb() {
        return this.zzc;
    }

    public final void zzd(RemoteMediaClient.ProgressListener progressListener) {
        this.zzb.add(progressListener);
    }

    public final void zze(RemoteMediaClient.ProgressListener progressListener) {
        this.zzb.remove(progressListener);
    }

    public final void zzf() {
        RemoteMediaClient remoteMediaClient = this.zza;
        Handler handler = remoteMediaClient.zzc;
        Runnable runnable = this.zzd;
        handler.removeCallbacks(runnable);
        this.zze = true;
        remoteMediaClient.zzc.postDelayed(runnable, this.zzc);
    }

    public final void zzg() {
        this.zza.zzc.removeCallbacks(this.zzd);
        this.zze = false;
    }

    public final boolean zzh() {
        return !this.zzb.isEmpty();
    }

    public final boolean zzi() {
        return this.zze;
    }
}
