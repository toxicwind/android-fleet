package com.google.android.gms.cast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
final class zzao extends BroadcastReceiver {
    private zzao() {
        throw null;
    }

    /* synthetic */ zzao(zzap zzapVar) {
    }

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        if ("com.google.android.gms.cast.remote_display.ACTION_NOTIFICATION_DISCONNECT".equals(intent.getAction())) {
            CastRemoteDisplayLocalService.stopService();
        } else if ("com.google.android.gms.cast.remote_display.ACTION_SESSION_ENDED".equals(intent.getAction())) {
            CastRemoteDisplayLocalService.zzw(false);
        }
    }
}
