package com.google.android.gms.internal.cast;

import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.widget.SeekBar;
import com.google.android.gms.cast.framework.CastSession;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.cast.framework.media.uicontroller.UIController;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
public final class zzcu extends UIController implements RemoteMediaClient.ProgressListener {
    private final SeekBar zza;
    private final long zzb;
    private final com.google.android.gms.cast.framework.media.uicontroller.zza zzc;
    private boolean zzd = true;
    private Boolean zze;
    private Drawable zzf;

    public zzcu(SeekBar seekBar, long j, com.google.android.gms.cast.framework.media.uicontroller.zza zzaVar) {
        this.zzf = null;
        this.zza = seekBar;
        this.zzb = j;
        this.zzc = zzaVar;
        seekBar.setEnabled(false);
        this.zzf = seekBar.getThumb();
    }

    @Override // com.google.android.gms.cast.framework.media.uicontroller.UIController
    public final void onMediaStatusUpdated() {
        zzb();
    }

    @Override // com.google.android.gms.cast.framework.media.RemoteMediaClient.ProgressListener
    public final void onProgressUpdated(long j, long j2) {
        zzb();
    }

    @Override // com.google.android.gms.cast.framework.media.uicontroller.UIController
    public final void onSessionConnected(CastSession castSession) {
        super.onSessionConnected(castSession);
        RemoteMediaClient remoteMediaClient = getRemoteMediaClient();
        if (remoteMediaClient != null) {
            remoteMediaClient.addProgressListener(this, this.zzb);
        }
        zzb();
    }

    @Override // com.google.android.gms.cast.framework.media.uicontroller.UIController
    public final void onSessionEnded() {
        RemoteMediaClient remoteMediaClient = getRemoteMediaClient();
        if (remoteMediaClient != null) {
            remoteMediaClient.removeProgressListener(this);
        }
        super.onSessionEnded();
        zzb();
    }

    public final void zza(boolean z) {
        this.zzd = z;
    }

    final void zzb() {
        RemoteMediaClient remoteMediaClient = getRemoteMediaClient();
        if (remoteMediaClient == null || !remoteMediaClient.hasMediaSession()) {
            SeekBar seekBar = this.zza;
            com.google.android.gms.cast.framework.media.uicontroller.zza zzaVar = this.zzc;
            seekBar.setMax(zzaVar.zzb());
            seekBar.setProgress(zzaVar.zza());
            seekBar.setEnabled(false);
            return;
        }
        if (this.zzd) {
            SeekBar seekBar2 = this.zza;
            com.google.android.gms.cast.framework.media.uicontroller.zza zzaVar2 = this.zzc;
            seekBar2.setMax(zzaVar2.zzb());
            if (remoteMediaClient.isLiveStream() && zzaVar2.zzm()) {
                seekBar2.setProgress(zzaVar2.zzc());
            } else {
                seekBar2.setProgress(zzaVar2.zza());
            }
            if (remoteMediaClient.isPlayingAd()) {
                seekBar2.setEnabled(false);
            } else {
                seekBar2.setEnabled(true);
            }
            RemoteMediaClient remoteMediaClient2 = getRemoteMediaClient();
            if (remoteMediaClient2 == null || !remoteMediaClient2.hasMediaSession()) {
                return;
            }
            Boolean bool = this.zze;
            if (bool == null || bool.booleanValue() != remoteMediaClient2.zzw()) {
                Boolean boolValueOf = Boolean.valueOf(remoteMediaClient2.zzw());
                this.zze = boolValueOf;
                if (!boolValueOf.booleanValue()) {
                    seekBar2.setThumb(new ColorDrawable(0));
                    seekBar2.setClickable(false);
                    seekBar2.setOnTouchListener(new zzct(this));
                } else {
                    Drawable drawable = this.zzf;
                    if (drawable != null) {
                        seekBar2.setThumb(drawable);
                    }
                    seekBar2.setClickable(true);
                    seekBar2.setOnTouchListener(null);
                }
            }
        }
    }
}
