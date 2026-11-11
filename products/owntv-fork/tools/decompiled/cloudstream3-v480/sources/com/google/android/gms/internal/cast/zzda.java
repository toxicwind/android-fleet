package com.google.android.gms.internal.cast;

import com.google.android.gms.cast.framework.media.uicontroller.UIController;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
public abstract class zzda extends UIController {
    private boolean zza = true;

    public void zza(boolean z) {
        this.zza = z;
    }

    public abstract void zzb(long j);

    public final boolean zzc() {
        return this.zza;
    }
}
