package com.google.android.gms.common.internal;

import androidx.annotation.NonNull;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.internal.OnConnectionFailedListener;

/* JADX INFO: loaded from: classes.dex */
public final class zag implements BaseGmsClient.BaseOnConnectionFailedListener {
    public final /* synthetic */ OnConnectionFailedListener zapc;

    public zag(OnConnectionFailedListener onConnectionFailedListener) {
        this.zapc = onConnectionFailedListener;
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient.BaseOnConnectionFailedListener
    public final void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        this.zapc.onConnectionFailed(connectionResult);
    }
}
