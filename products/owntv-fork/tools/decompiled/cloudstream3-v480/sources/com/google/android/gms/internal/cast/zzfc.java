package com.google.android.gms.internal.cast;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.TaskUtil;
import com.google.android.gms.tasks.TaskCompletionSource;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
final class zzfc extends zzfr {
    final /* synthetic */ TaskCompletionSource zza;

    zzfc(zzfd zzfdVar, TaskCompletionSource taskCompletionSource) {
        this.zza = taskCompletionSource;
    }

    @Override // com.google.android.gms.internal.cast.zzfr, com.google.android.gms.internal.cast.zzfn
    public final void zzb(Status status, zzfk zzfkVar) {
        TaskUtil.setResultOrApiException(status, new zzfe(new zzfq(Status.RESULT_SUCCESS, zzfkVar)), this.zza);
    }
}
