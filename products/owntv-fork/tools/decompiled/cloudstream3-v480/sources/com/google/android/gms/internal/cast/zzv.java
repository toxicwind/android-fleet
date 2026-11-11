package com.google.android.gms.internal.cast;

import com.google.android.gms.cast.SessionState;
import com.google.android.gms.cast.framework.SessionTransferCallback;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
public final class zzv extends SessionTransferCallback {
    final /* synthetic */ zzx zza;

    public zzv(zzx zzxVar) {
        this.zza = zzxVar;
    }

    @Override // com.google.android.gms.cast.framework.SessionTransferCallback
    public final void onTransferFailed(int i, int i2) {
        zzca zzcaVar = new zzca(11);
        zzcaVar.zzb(Integer.valueOf(i2));
        zzx zzxVar = this.zza;
        zzcaVar.zza(Boolean.valueOf(zzxVar.zzb.zzf()));
        zzx.zzf(zzxVar, new zzcb(zzcaVar));
    }

    @Override // com.google.android.gms.cast.framework.SessionTransferCallback
    public final void onTransferred(int i, SessionState sessionState) {
    }

    @Override // com.google.android.gms.cast.framework.SessionTransferCallback
    public final void onTransferring(int i) {
        zzca zzcaVar = new zzca(10);
        zzx zzxVar = this.zza;
        zzcaVar.zza(Boolean.valueOf(zzxVar.zzb.zzf()));
        zzx.zzf(zzxVar, new zzcb(zzcaVar));
        zzxVar.zzg().zzd(new zzab(new zzaa(i)));
    }
}
