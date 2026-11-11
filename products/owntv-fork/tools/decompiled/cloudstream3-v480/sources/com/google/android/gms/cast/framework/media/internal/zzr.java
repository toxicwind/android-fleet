package com.google.android.gms.cast.framework.media.internal;

import android.app.BroadcastOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.media.session.MediaSessionCompat;
import android.view.KeyEvent;
import com.google.android.gms.cast.MediaSeekOptions;
import com.google.android.gms.cast.framework.media.MediaIntentReceiver;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
final class zzr extends MediaSessionCompat.Callback {
    final /* synthetic */ zzu zza;

    zzr(zzu zzuVar) {
        this.zza = zzuVar;
    }

    private final void zza(long j) {
        RemoteMediaClient remoteMediaClient = this.zza.zzp;
        if (remoteMediaClient == null) {
            return;
        }
        zzb(Math.min(remoteMediaClient.getStreamDuration(), Math.max(0L, remoteMediaClient.getApproximateStreamPosition() + j)));
    }

    private final void zzb(long j) {
        RemoteMediaClient remoteMediaClient = this.zza.zzp;
        if (remoteMediaClient == null) {
            return;
        }
        MediaSeekOptions.Builder builder = new MediaSeekOptions.Builder();
        builder.setPosition(j);
        remoteMediaClient.seek(builder.build());
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    @Override // android.support.v4.media.session.MediaSessionCompat.Callback
    public final void onCustomAction(String str, Bundle bundle) {
        zzu.zzb.d("onCustomAction with action = %s", str);
        switch (str.hashCode()) {
            case -1699820260:
                if (str.equals(MediaIntentReceiver.ACTION_REWIND)) {
                    zza(-this.zza.zzg.getSkipStepMs());
                    return;
                }
                break;
            case -668151673:
                if (str.equals(MediaIntentReceiver.ACTION_STOP_CASTING)) {
                    zzu zzuVar = this.zza;
                    if (zzuVar.zzf != null) {
                        zzuVar.zzf.endCurrentSession(true);
                        return;
                    }
                    return;
                }
                break;
            case -124479363:
                if (str.equals(MediaIntentReceiver.ACTION_DISCONNECT)) {
                    zzu zzuVar2 = this.zza;
                    if (zzuVar2.zzf != null) {
                        zzuVar2.zzf.endCurrentSession(false);
                        return;
                    }
                    return;
                }
                break;
            case 1362116196:
                if (str.equals(MediaIntentReceiver.ACTION_FORWARD)) {
                    zza(this.zza.zzg.getSkipStepMs());
                    return;
                }
                break;
        }
        Intent intent = new Intent(str);
        zzu zzuVar3 = this.zza;
        intent.setComponent(zzuVar3.zzi);
        Context context = zzuVar3.zzc;
        if (Build.VERSION.SDK_INT < 34) {
            context.sendBroadcast(intent);
        } else {
            context.sendBroadcast(intent, null, BroadcastOptions.makeBasic().setShareIdentityEnabled(true).toBundle());
        }
    }

    @Override // android.support.v4.media.session.MediaSessionCompat.Callback
    public final boolean onMediaButtonEvent(Intent intent) {
        zzu.zzb.d("onMediaButtonEvent", new Object[0]);
        KeyEvent keyEvent = (KeyEvent) intent.getParcelableExtra("android.intent.extra.KEY_EVENT");
        if (keyEvent == null) {
            return true;
        }
        if (keyEvent.getKeyCode() != 127 && keyEvent.getKeyCode() != 126) {
            return true;
        }
        zzu zzuVar = this.zza;
        if (zzuVar.zzp == null) {
            return true;
        }
        zzuVar.zzp.togglePlayback();
        return true;
    }

    @Override // android.support.v4.media.session.MediaSessionCompat.Callback
    public final void onPause() {
        zzu.zzb.d("onPause", new Object[0]);
        zzu zzuVar = this.zza;
        if (zzuVar.zzp != null) {
            zzuVar.zzp.togglePlayback();
        }
    }

    @Override // android.support.v4.media.session.MediaSessionCompat.Callback
    public final void onPlay() {
        zzu.zzb.d("onPlay", new Object[0]);
        zzu zzuVar = this.zza;
        if (zzuVar.zzp != null) {
            zzuVar.zzp.togglePlayback();
        }
    }

    @Override // android.support.v4.media.session.MediaSessionCompat.Callback
    public final void onSeekTo(long j) {
        zzu.zzb.d("onSeekTo %d", Long.valueOf(j));
        zzb(j);
    }

    @Override // android.support.v4.media.session.MediaSessionCompat.Callback
    public final void onSkipToNext() {
        zzu.zzb.d("onSkipToNext", new Object[0]);
        zzu zzuVar = this.zza;
        if (zzuVar.zzp != null) {
            zzuVar.zzp.queueNext(null);
        }
    }

    @Override // android.support.v4.media.session.MediaSessionCompat.Callback
    public final void onSkipToPrevious() {
        zzu.zzb.d("onSkipToPrevious", new Object[0]);
        zzu zzuVar = this.zza;
        if (zzuVar.zzp != null) {
            zzuVar.zzp.queuePrev(null);
        }
    }
}
