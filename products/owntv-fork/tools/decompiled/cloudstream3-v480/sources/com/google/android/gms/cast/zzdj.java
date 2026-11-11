package com.google.android.gms.cast;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
final class zzdj implements ResultCallback {
    final /* synthetic */ zzdk zza;
    private final long zzb;

    zzdj(zzdk zzdkVar, long j) {
        this.zza = zzdkVar;
        this.zzb = j;
    }

    @Override // com.google.android.gms.common.api.ResultCallback
    public final /* bridge */ /* synthetic */ void onResult(Result result) {
        Status status = (Status) result;
        if (status.isSuccess()) {
            return;
        }
        zzdk zzdkVar = this.zza;
        zzdkVar.zza.zzb.zzP(this.zzb, status.getStatusCode());
    }
}
