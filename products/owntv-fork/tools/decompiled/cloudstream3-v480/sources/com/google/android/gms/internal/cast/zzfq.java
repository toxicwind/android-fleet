package com.google.android.gms.internal.cast;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Preconditions;
import javax.annotation.Nullable;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
public final class zzfq implements Result {
    private final Status zza;

    @Nullable
    private final zzfk zzb;

    public zzfq(Status status, @Nullable zzfk zzfkVar) {
        this.zza = status;
        this.zzb = zzfkVar;
    }

    @Override // com.google.android.gms.common.api.Result
    public final Status getStatus() {
        return this.zza;
    }

    public final String toString() {
        zzfk zzfkVar = this.zzb;
        Preconditions.checkNotNull(zzfkVar);
        return String.format("OptInOptionsResultImpl[%s]", Boolean.valueOf(zzfkVar.zza() == 1));
    }

    public final boolean zza() {
        zzfk zzfkVar = this.zzb;
        Preconditions.checkNotNull(zzfkVar);
        return zzfkVar.zza() == 1;
    }
}
