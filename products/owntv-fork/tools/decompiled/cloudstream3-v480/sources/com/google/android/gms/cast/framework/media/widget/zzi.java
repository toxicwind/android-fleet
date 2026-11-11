package com.google.android.gms.cast.framework.media.widget;

import android.view.View;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
final class zzi implements View.OnClickListener {
    final /* synthetic */ ExpandedControllerActivity zza;

    zzi(ExpandedControllerActivity expandedControllerActivity) {
        this.zza = expandedControllerActivity;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        RemoteMediaClient remoteMediaClientZzj;
        ExpandedControllerActivity expandedControllerActivity = this.zza;
        if (!expandedControllerActivity.zzI.isClickable() || (remoteMediaClientZzj = expandedControllerActivity.zzj()) == null) {
            return;
        }
        remoteMediaClientZzj.skipAd();
    }
}
