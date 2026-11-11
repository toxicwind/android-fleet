package com.google.android.gms.cast;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
final class zzag implements Runnable {
    final /* synthetic */ CastRemoteDisplayLocalService zza;

    zzag(CastRemoteDisplayLocalService castRemoteDisplayLocalService) {
        this.zza = castRemoteDisplayLocalService;
    }

    @Override // java.lang.Runnable
    public final void run() {
        CastRemoteDisplayLocalService castRemoteDisplayLocalService = this.zza;
        castRemoteDisplayLocalService.zzv("onCreate after delay. The local service been started: " + castRemoteDisplayLocalService.zzs);
        if (castRemoteDisplayLocalService.zzs) {
            return;
        }
        CastRemoteDisplayLocalService.zza.e("[Instance: %s] %s", castRemoteDisplayLocalService, "The local service has not been been started, stopping it");
        castRemoteDisplayLocalService.stopSelf();
    }
}
