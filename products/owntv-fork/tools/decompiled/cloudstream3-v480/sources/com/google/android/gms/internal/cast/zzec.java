package com.google.android.gms.internal.cast;

import android.os.RemoteException;
import com.google.android.gms.common.api.Status;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
public final class zzec extends zzea {
    final /* synthetic */ zzed zza;

    protected zzec(zzed zzedVar) {
        this.zza = zzedVar;
    }

    @Override // com.google.android.gms.internal.cast.zzea, com.google.android.gms.internal.cast.zzek
    public final void zzd(int i) throws RemoteException {
        zzef.zza.d("onError: %d", Integer.valueOf(i));
        zzed zzedVar = this.zza;
        zzef.zzf(zzedVar.zzc);
        zzedVar.setResult(new zzee(Status.RESULT_INTERNAL_ERROR));
    }

    @Override // com.google.android.gms.internal.cast.zzea, com.google.android.gms.internal.cast.zzek
    public final void zzf() throws RemoteException {
        zzef.zza.d("onDisconnected", new Object[0]);
        zzed zzedVar = this.zza;
        zzef.zzf(zzedVar.zzc);
        zzedVar.setResult(new zzee(Status.RESULT_SUCCESS));
    }
}
