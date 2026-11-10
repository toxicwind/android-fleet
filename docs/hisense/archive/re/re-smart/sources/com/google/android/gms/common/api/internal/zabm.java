package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.internal.BaseGmsClient;

/* JADX INFO: loaded from: classes.dex */
public final class zabm implements BaseGmsClient.SignOutCallbacks {
    public final /* synthetic */ GoogleApiManager.zaa zaiq;

    public zabm(GoogleApiManager.zaa zaaVar) {
        this.zaiq = zaaVar;
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient.SignOutCallbacks
    public final void onSignOutComplete() {
        GoogleApiManager.this.handler.post(new zabl(this));
    }
}
