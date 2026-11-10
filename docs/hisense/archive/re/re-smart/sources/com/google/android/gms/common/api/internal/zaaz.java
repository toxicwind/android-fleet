package com.google.android.gms.common.api.internal;

import android.os.Looper;
import android.os.Message;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public final class zaaz extends com.google.android.gms.internal.base.zar {
    public final /* synthetic */ zaaw zagv;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zaaz(zaaw zaawVar, Looper looper) {
        super(looper);
        this.zagv = zaawVar;
    }

    @Override // android.os.Handler
    public final void handleMessage(Message message) {
        int i = message.what;
        if (i == 1) {
            this.zagv.zaat();
            return;
        }
        if (i == 2) {
            this.zagv.resume();
            return;
        }
        StringBuilder sb = new StringBuilder(31);
        sb.append("Unknown message id: ");
        sb.append(i);
        Log.w("GoogleApiClientImpl", sb.toString());
    }
}
