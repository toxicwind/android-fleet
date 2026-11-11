package com.google.android.gms.cast.framework.media;

import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.util.List;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
final class zzu extends zzf {
    final /* synthetic */ NotificationActionsProvider zza;

    /* synthetic */ zzu(NotificationActionsProvider notificationActionsProvider, zzv zzvVar) {
        this.zza = notificationActionsProvider;
    }

    @Override // com.google.android.gms.cast.framework.media.zzg
    public final IObjectWrapper zze() {
        return ObjectWrapper.wrap(this.zza);
    }

    @Override // com.google.android.gms.cast.framework.media.zzg
    public final List zzf() {
        return this.zza.getNotificationActions();
    }

    @Override // com.google.android.gms.cast.framework.media.zzg
    public final int[] zzg() {
        return this.zza.getCompactViewActionIndices();
    }
}
