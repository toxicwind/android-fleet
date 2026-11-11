package com.google.android.gms.cast.framework.media.widget;

import com.google.android.gms.cast.framework.R;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
final class zzn implements RemoteMediaClient.Listener {
    final /* synthetic */ ExpandedControllerActivity zza;

    /* synthetic */ zzn(ExpandedControllerActivity expandedControllerActivity, zzp zzpVar) {
        this.zza = expandedControllerActivity;
    }

    @Override // com.google.android.gms.cast.framework.media.RemoteMediaClient.Listener
    public final void onAdBreakStatusUpdated() {
        this.zza.zzp();
    }

    @Override // com.google.android.gms.cast.framework.media.RemoteMediaClient.Listener
    public final void onMetadataUpdated() {
        this.zza.zzo();
    }

    @Override // com.google.android.gms.cast.framework.media.RemoteMediaClient.Listener
    public final void onPreloadStatusUpdated() {
    }

    @Override // com.google.android.gms.cast.framework.media.RemoteMediaClient.Listener
    public final void onQueueStatusUpdated() {
    }

    @Override // com.google.android.gms.cast.framework.media.RemoteMediaClient.Listener
    public final void onSendingRemoteMediaRequest() {
        ExpandedControllerActivity expandedControllerActivity = this.zza;
        expandedControllerActivity.zzw.setText(expandedControllerActivity.getResources().getString(R.string.cast_expanded_controller_loading));
    }

    @Override // com.google.android.gms.cast.framework.media.RemoteMediaClient.Listener
    public final void onStatusUpdated() {
        ExpandedControllerActivity expandedControllerActivity = this.zza;
        RemoteMediaClient remoteMediaClientZzj = expandedControllerActivity.zzj();
        if (remoteMediaClientZzj == null || !remoteMediaClientZzj.hasMediaSession()) {
            if (expandedControllerActivity.zzd) {
                return;
            }
            expandedControllerActivity.finish();
        } else {
            expandedControllerActivity.zzd = false;
            expandedControllerActivity.zzn();
            expandedControllerActivity.zzp();
        }
    }
}
