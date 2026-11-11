package com.google.android.gms.internal.cast;

import android.util.Log;
import com.google.android.gms.common.api.Status;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
final class zzfs extends zzfr {
    private zzfs() {
        throw null;
    }

    /* synthetic */ zzfs(zzfu zzfuVar) {
    }

    @Override // com.google.android.gms.internal.cast.zzfr, com.google.android.gms.internal.cast.zzfn
    public final void zzo(Status status) {
        if (status.isSuccess()) {
            return;
        }
        Log.e("UsageReportingClientImp", "disconnect(): Could not unregister listener: status=".concat(String.valueOf(String.valueOf(status))));
    }
}
