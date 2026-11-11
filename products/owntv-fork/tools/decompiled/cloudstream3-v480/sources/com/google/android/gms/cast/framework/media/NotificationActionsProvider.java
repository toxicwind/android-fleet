package com.google.android.gms.cast.framework.media;

import android.content.Context;
import java.util.List;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
public abstract class NotificationActionsProvider {
    private final Context zza;
    private final zzg zzb = new zzu(this, null);

    public NotificationActionsProvider(Context context) {
        this.zza = context.getApplicationContext();
    }

    public Context getApplicationContext() {
        return this.zza;
    }

    public abstract int[] getCompactViewActionIndices();

    public abstract List<NotificationAction> getNotificationActions();

    public final zzg zza() {
        return this.zzb;
    }
}
