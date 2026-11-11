package com.google.android.gms.internal.cast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
final class zzdn extends BroadcastReceiver {
    final /* synthetic */ zzdq zza;

    /* synthetic */ zzdn(zzdq zzdqVar, zzdp zzdpVar) {
        this.zza = zzdqVar;
    }

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (action == null) {
            return;
        }
        if (action.equals("android.intent.action.SCREEN_ON")) {
            zzdq.zze(this.zza);
        } else if (action.equals("android.intent.action.SCREEN_OFF")) {
            zzdq.zzg(this.zza);
        }
    }
}
