package com.google.android.gms.common.internal.service;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation;

/* JADX INFO: loaded from: classes.dex */
public final class zag extends zaa {
    public final BaseImplementation.ResultHolder<Status> mResultHolder;

    public zag(BaseImplementation.ResultHolder<Status> resultHolder) {
        this.mResultHolder = resultHolder;
    }

    @Override // com.google.android.gms.common.internal.service.zaa, com.google.android.gms.common.internal.service.zak
    public final void zaj(int i) {
        this.mResultHolder.setResult(new Status(1, i, null, null));
    }
}
