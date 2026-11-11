package com.google.android.gms.cast;

import android.content.Context;
import android.content.ServiceConnection;
import android.view.Display;
import com.google.android.gms.common.stats.ConnectionTracker;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
final class zzal implements OnCompleteListener {
    final /* synthetic */ CastRemoteDisplayLocalService zza;

    zzal(CastRemoteDisplayLocalService castRemoteDisplayLocalService) {
        this.zza = castRemoteDisplayLocalService;
    }

    @Override // com.google.android.gms.tasks.OnCompleteListener
    public final void onComplete(Task task) {
        if (!task.isSuccessful()) {
            CastRemoteDisplayLocalService.zza.e("Connection was not successful", new Object[0]);
            CastRemoteDisplayLocalService.zzo(this.zza);
            return;
        }
        CastRemoteDisplayLocalService.zza.d("startRemoteDisplay successful", new Object[0]);
        synchronized (CastRemoteDisplayLocalService.zzc) {
            if (CastRemoteDisplayLocalService.zze == null) {
                CastRemoteDisplayLocalService.zza.d("Remote Display started but session already cancelled", new Object[0]);
                CastRemoteDisplayLocalService.zzo(this.zza);
                return;
            }
            Display display = (Display) task.getResult();
            CastRemoteDisplayLocalService castRemoteDisplayLocalService = this.zza;
            CastRemoteDisplayLocalService.zzl(castRemoteDisplayLocalService, display);
            CastRemoteDisplayLocalService.zzd.set(false);
            Context context = castRemoteDisplayLocalService.zzo;
            ServiceConnection serviceConnection = castRemoteDisplayLocalService.zzp;
            if (context != null && serviceConnection != null) {
                try {
                    ConnectionTracker.getInstance().unbindService(context, serviceConnection);
                } catch (IllegalArgumentException unused) {
                    CastRemoteDisplayLocalService.zza.d("No need to unbind service, already unbound", new Object[0]);
                }
            }
            CastRemoteDisplayLocalService castRemoteDisplayLocalService2 = this.zza;
            castRemoteDisplayLocalService2.zzp = null;
            castRemoteDisplayLocalService2.zzo = null;
        }
    }
}
