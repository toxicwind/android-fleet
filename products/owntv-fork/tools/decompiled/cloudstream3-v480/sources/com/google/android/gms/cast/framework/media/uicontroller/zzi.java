package com.google.android.gms.cast.framework.media.uicontroller;

import android.widget.SeekBar;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
final class zzi implements SeekBar.OnSeekBarChangeListener {
    final /* synthetic */ SeekBar zza;
    final /* synthetic */ UIMediaController zzb;

    zzi(UIMediaController uIMediaController, SeekBar seekBar) {
        this.zza = seekBar;
        this.zzb = uIMediaController;
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public final void onProgressChanged(SeekBar seekBar, int i, boolean z) {
        UIMediaController uIMediaController = this.zzb;
        RemoteMediaClient remoteMediaClient = uIMediaController.getRemoteMediaClient();
        if (remoteMediaClient != null && remoteMediaClient.hasMediaSession() && remoteMediaClient.zzw()) {
            if (z) {
                zza zzaVar = uIMediaController.zza;
                if (i < zzaVar.zzd()) {
                    SeekBar seekBar2 = this.zza;
                    int iZzd = zzaVar.zzd();
                    seekBar2.setProgress(iZzd);
                    uIMediaController.onSeekBarProgressChanged(seekBar, iZzd, true);
                    return;
                }
            }
            if (z) {
                zza zzaVar2 = uIMediaController.zza;
                if (i > zzaVar2.zzc()) {
                    SeekBar seekBar3 = this.zza;
                    int iZzc = zzaVar2.zzc();
                    seekBar3.setProgress(iZzc);
                    uIMediaController.onSeekBarProgressChanged(seekBar, iZzc, true);
                    return;
                }
            }
        }
        uIMediaController.onSeekBarProgressChanged(seekBar, i, z);
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public final void onStartTrackingTouch(SeekBar seekBar) {
        this.zzb.onSeekBarStartTrackingTouch(seekBar);
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public final void onStopTrackingTouch(SeekBar seekBar) {
        this.zzb.onSeekBarStopTrackingTouch(seekBar);
    }
}
