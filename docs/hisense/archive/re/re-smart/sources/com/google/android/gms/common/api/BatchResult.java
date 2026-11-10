package com.google.android.gms.common.api;

import com.google.android.gms.common.internal.Preconditions;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public final class BatchResult implements Result {
    public final Status mStatus;
    public final PendingResult<?>[] zabf;

    public BatchResult(Status status, PendingResult<?>[] pendingResultArr) {
        this.mStatus = status;
        this.zabf = pendingResultArr;
    }

    @Override // com.google.android.gms.common.api.Result
    public final Status getStatus() {
        return this.mStatus;
    }

    public final <R extends Result> R take(BatchResultToken<R> batchResultToken) {
        Preconditions.checkArgument(batchResultToken.mId < this.zabf.length, "The result token does not belong to this batch");
        return (R) this.zabf[batchResultToken.mId].await(0L, TimeUnit.MILLISECONDS);
    }
}
