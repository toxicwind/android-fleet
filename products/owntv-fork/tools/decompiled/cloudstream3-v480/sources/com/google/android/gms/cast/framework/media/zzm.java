package com.google.android.gms.cast.framework.media;

import java.util.TimerTask;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
final class zzm extends TimerTask {
    final /* synthetic */ MediaQueue zza;

    zzm(MediaQueue mediaQueue) {
        this.zza = mediaQueue;
    }

    @Override // java.util.TimerTask, java.lang.Runnable
    public final void run() {
        MediaQueue.zzj(this.zza);
    }
}
