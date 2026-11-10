package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;

/* JADX INFO: loaded from: classes.dex */
public final class zaac implements PendingResult.StatusListener {
    public final /* synthetic */ zaz zafq;
    public final /* synthetic */ BasePendingResult zafr;

    public zaac(zaz zazVar, BasePendingResult basePendingResult) {
        this.zafq = zazVar;
        this.zafr = basePendingResult;
    }

    @Override // com.google.android.gms.common.api.PendingResult.StatusListener
    public final void onComplete(Status status) {
        this.zafq.zafm.remove(this.zafr);
    }
}
