package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Releasable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.DataHolder;

/* JADX INFO: loaded from: classes.dex */
@KeepForSdk
public class DataHolderResult implements Releasable, Result {

    @KeepForSdk
    public final DataHolder mDataHolder;

    @KeepForSdk
    public final Status mStatus;

    @KeepForSdk
    public DataHolderResult(DataHolder dataHolder) {
        this.mStatus = new Status(dataHolder.getStatusCode());
        this.mDataHolder = dataHolder;
    }

    @Override // com.google.android.gms.common.api.Result
    @KeepForSdk
    public Status getStatus() {
        return this.mStatus;
    }

    @Override // com.google.android.gms.common.api.Releasable
    @KeepForSdk
    public void release() {
        DataHolder dataHolder = this.mDataHolder;
        if (dataHolder != null) {
            dataHolder.close();
        }
    }

    @KeepForSdk
    public DataHolderResult(DataHolder dataHolder, Status status) {
        this.mStatus = status;
        this.mDataHolder = dataHolder;
    }
}
