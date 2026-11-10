package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.ConnectionResult;

/* JADX INFO: loaded from: classes.dex */
public final class zacd implements Runnable {
    public final /* synthetic */ zace zakl;

    public zacd(zace zaceVar) {
        this.zakl = zaceVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zakl.zakn.zag(new ConnectionResult(4));
    }
}
