package com.google.android.gms.cast;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
final class zzam implements OnCompleteListener {
    final /* synthetic */ CastRemoteDisplayLocalService zza;

    zzam(CastRemoteDisplayLocalService castRemoteDisplayLocalService) {
        this.zza = castRemoteDisplayLocalService;
    }

    @Override // com.google.android.gms.tasks.OnCompleteListener
    public final void onComplete(Task task) {
        boolean zIsSuccessful = task.isSuccessful();
        CastRemoteDisplayLocalService castRemoteDisplayLocalService = this.zza;
        if (zIsSuccessful) {
            castRemoteDisplayLocalService.zzv("remote display stopped");
        } else {
            castRemoteDisplayLocalService.zzv("Unable to stop the remote display, result unsuccessful");
            CastRemoteDisplayLocalService.Callbacks callbacks = (CastRemoteDisplayLocalService.Callbacks) castRemoteDisplayLocalService.zzg.get();
            if (callbacks != null) {
                callbacks.onRemoteDisplaySessionError(new Status(CastStatusCodes.ERROR_STOPPING_SERVICE_FAILED));
            }
        }
        this.zza.zzn = null;
    }
}
