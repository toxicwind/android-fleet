package com.google.android.gms.cast;

import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.TaskUtil;
import com.google.android.gms.tasks.TaskCompletionSource;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
final class zzac extends zzad {
    final /* synthetic */ TaskCompletionSource zza;
    final /* synthetic */ CastRemoteDisplayClient zzb;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzac(CastRemoteDisplayClient castRemoteDisplayClient, TaskCompletionSource taskCompletionSource) {
        super(null);
        this.zza = taskCompletionSource;
        this.zzb = castRemoteDisplayClient;
    }

    @Override // com.google.android.gms.cast.zzad, com.google.android.gms.internal.cast.zzek
    public final void zzd(int i) throws RemoteException {
        CastRemoteDisplayClient castRemoteDisplayClient = this.zzb;
        castRemoteDisplayClient.zzc.d("onError: %d", Integer.valueOf(i));
        CastRemoteDisplayClient.zzd(castRemoteDisplayClient);
        TaskUtil.setResultOrApiException(Status.RESULT_INTERNAL_ERROR, this.zza);
    }

    @Override // com.google.android.gms.cast.zzad, com.google.android.gms.internal.cast.zzek
    public final void zzf() throws RemoteException {
        CastRemoteDisplayClient castRemoteDisplayClient = this.zzb;
        castRemoteDisplayClient.zzc.d("onDisconnected", new Object[0]);
        CastRemoteDisplayClient.zzd(castRemoteDisplayClient);
        TaskUtil.setResultOrApiException(Status.RESULT_SUCCESS, this.zza);
    }
}
