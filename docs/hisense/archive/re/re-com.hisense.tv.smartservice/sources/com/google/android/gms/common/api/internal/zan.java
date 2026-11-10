package com.google.android.gms.common.api.internal;

import android.app.Dialog;

/* JADX INFO: loaded from: classes.dex */
public final class zan extends zabp {
    public final /* synthetic */ Dialog zaec;
    public final /* synthetic */ zal zaed;

    public zan(zal zalVar, Dialog dialog) {
        this.zaed = zalVar;
        this.zaec = dialog;
    }

    @Override // com.google.android.gms.common.api.internal.zabp
    public final void zas() {
        this.zaed.zadl.zao();
        if (this.zaec.isShowing()) {
            this.zaec.dismiss();
        }
    }
}
