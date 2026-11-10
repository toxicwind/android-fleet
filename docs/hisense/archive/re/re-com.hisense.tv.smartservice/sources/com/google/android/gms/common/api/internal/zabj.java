package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.ConnectionResult;

/* JADX INFO: loaded from: classes.dex */
public final class zabj implements Runnable {
    public final /* synthetic */ GoogleApiManager.zaa zaiq;
    public final /* synthetic */ ConnectionResult zajc;

    public zabj(GoogleApiManager.zaa zaaVar, ConnectionResult connectionResult) {
        this.zaiq = zaaVar;
        this.zajc = connectionResult;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zaiq.onConnectionFailed(this.zajc);
    }
}
