package com.google.android.gms.internal.cast;

import android.view.Display;
import com.google.android.gms.cast.CastRemoteDisplay;
import com.google.android.gms.common.api.Status;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
final class zzee implements CastRemoteDisplay.CastRemoteDisplaySessionResult {
    private final Status zza;
    private final Display zzb;

    public zzee(Display display) {
        this.zza = Status.RESULT_SUCCESS;
        this.zzb = display;
    }

    public zzee(Status status) {
        this.zza = status;
        this.zzb = null;
    }

    @Override // com.google.android.gms.cast.CastRemoteDisplay.CastRemoteDisplaySessionResult
    public final Display getPresentationDisplay() {
        return this.zzb;
    }

    @Override // com.google.android.gms.common.api.Result
    public final Status getStatus() {
        return this.zza;
    }
}
