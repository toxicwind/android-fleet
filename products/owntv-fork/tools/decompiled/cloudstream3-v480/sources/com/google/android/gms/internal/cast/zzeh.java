package com.google.android.gms.internal.cast;

import android.os.RemoteException;
import com.google.android.gms.common.api.Status;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
final class zzeh extends zzem {
    final /* synthetic */ zzen zza;
    final /* synthetic */ zzei zzb;

    zzeh(zzei zzeiVar, zzen zzenVar) {
        this.zza = zzenVar;
        this.zzb = zzeiVar;
    }

    @Override // com.google.android.gms.internal.cast.zzen
    public final void zzb(int i) throws RemoteException {
        zzei.zze.d("onRemoteDisplayEnded", new Object[0]);
        zzen zzenVar = this.zza;
        if (zzenVar != null) {
            zzenVar.zzb(i);
        }
        zzei zzeiVar = this.zzb;
        if (zzeiVar.zzf != null) {
            zzeiVar.zzf.onRemoteDisplayEnded(new Status(i));
        }
    }
}
