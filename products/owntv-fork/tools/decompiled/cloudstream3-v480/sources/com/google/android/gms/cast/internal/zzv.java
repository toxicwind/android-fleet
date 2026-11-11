package com.google.android.gms.cast.internal;

import com.google.android.gms.cast.Cast;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
final class zzv implements Runnable {
    final /* synthetic */ zzx zza;
    final /* synthetic */ String zzb;
    final /* synthetic */ String zzc;

    zzv(zzw zzwVar, zzx zzxVar, String str, String str2) {
        this.zza = zzxVar;
        this.zzb = str;
        this.zzc = str2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        Cast.MessageReceivedCallback messageReceivedCallback;
        zzx zzxVar = this.zza;
        synchronized (zzxVar.zzk) {
            messageReceivedCallback = (Cast.MessageReceivedCallback) zzxVar.zzk.get(this.zzb);
        }
        if (messageReceivedCallback != null) {
            messageReceivedCallback.onMessageReceived(this.zza.zzi, this.zzb, this.zzc);
        } else {
            zzx.zze.d("Discarded message for unknown namespace '%s'", this.zzb);
        }
    }
}
