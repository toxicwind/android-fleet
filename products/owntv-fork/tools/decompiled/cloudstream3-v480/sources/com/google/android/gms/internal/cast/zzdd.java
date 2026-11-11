package com.google.android.gms.internal.cast;

import android.widget.TextView;
import com.google.android.gms.cast.framework.CastSession;
import com.google.android.gms.cast.framework.R;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.cast.framework.media.uicontroller.UIController;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
public final class zzdd extends UIController implements RemoteMediaClient.ProgressListener {
    private final TextView zza;
    private final com.google.android.gms.cast.framework.media.uicontroller.zza zzb;

    public zzdd(TextView textView, com.google.android.gms.cast.framework.media.uicontroller.zza zzaVar) {
        this.zza = textView;
        this.zzb = zzaVar;
        textView.setText(textView.getContext().getString(R.string.cast_invalid_stream_duration_text));
    }

    @Override // com.google.android.gms.cast.framework.media.uicontroller.UIController
    public final void onMediaStatusUpdated() {
        zza();
    }

    @Override // com.google.android.gms.cast.framework.media.RemoteMediaClient.ProgressListener
    public final void onProgressUpdated(long j, long j2) {
        zza();
    }

    @Override // com.google.android.gms.cast.framework.media.uicontroller.UIController
    public final void onSessionConnected(CastSession castSession) {
        super.onSessionConnected(castSession);
        RemoteMediaClient remoteMediaClient = getRemoteMediaClient();
        if (remoteMediaClient != null) {
            remoteMediaClient.addProgressListener(this, 1000L);
        }
        zza();
    }

    @Override // com.google.android.gms.cast.framework.media.uicontroller.UIController
    public final void onSessionEnded() {
        RemoteMediaClient remoteMediaClient = getRemoteMediaClient();
        if (remoteMediaClient != null) {
            remoteMediaClient.removeProgressListener(this);
        }
        super.onSessionEnded();
        zza();
    }

    final void zza() {
        RemoteMediaClient remoteMediaClient = getRemoteMediaClient();
        if (remoteMediaClient == null || !remoteMediaClient.hasMediaSession()) {
            TextView textView = this.zza;
            textView.setText(textView.getContext().getString(R.string.cast_invalid_stream_duration_text));
        } else {
            if (remoteMediaClient.isLiveStream() && this.zzb.zzi() == null) {
                this.zza.setVisibility(8);
                return;
            }
            TextView textView2 = this.zza;
            textView2.setVisibility(0);
            com.google.android.gms.cast.framework.media.uicontroller.zza zzaVar = this.zzb;
            textView2.setText(zzaVar.zzl(((long) zzaVar.zzb()) + zzaVar.zze()));
        }
    }
}
