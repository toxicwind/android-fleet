package com.google.android.gms.cast.framework.media.widget;

import com.google.android.gms.cast.Cast;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@22.1.0 */
/* JADX INFO: loaded from: classes5.dex */
final class zzl extends Cast.Listener {
    final /* synthetic */ ExpandedControllerActivity zza;

    zzl(ExpandedControllerActivity expandedControllerActivity) {
        this.zza = expandedControllerActivity;
    }

    @Override // com.google.android.gms.cast.Cast.Listener
    public final void onDeviceNameChanged() {
        this.zza.zzn();
    }
}
