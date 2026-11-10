package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.ConnectionResult;

/* JADX INFO: loaded from: classes.dex */
public final class zaao extends zabd {
    public final /* synthetic */ ConnectionResult zagq;
    public final /* synthetic */ zaal zagr;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zaao(zaal zaalVar, zabb zabbVar, ConnectionResult connectionResult) {
        super(zabbVar);
        this.zagr = zaalVar;
        this.zagq = connectionResult;
    }

    @Override // com.google.android.gms.common.api.internal.zabd
    public final void zaal() {
        this.zagr.zafz.zae(this.zagq);
    }
}
