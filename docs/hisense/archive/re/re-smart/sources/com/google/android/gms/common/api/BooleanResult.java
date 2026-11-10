package com.google.android.gms.common.api;

import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ShowFirstParty;

/* JADX INFO: loaded from: classes.dex */
@KeepForSdk
public class BooleanResult implements Result {
    public final Status mStatus;
    public final boolean zabi;

    @ShowFirstParty
    @KeepForSdk
    public BooleanResult(Status status, boolean z) {
        Preconditions.checkNotNull(status, "Status must not be null");
        this.mStatus = status;
        this.zabi = z;
    }

    @KeepForSdk
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof BooleanResult)) {
            return false;
        }
        BooleanResult booleanResult = (BooleanResult) obj;
        return this.mStatus.equals(booleanResult.mStatus) && this.zabi == booleanResult.zabi;
    }

    @Override // com.google.android.gms.common.api.Result
    @KeepForSdk
    public Status getStatus() {
        return this.mStatus;
    }

    @KeepForSdk
    public boolean getValue() {
        return this.zabi;
    }

    @KeepForSdk
    public final int hashCode() {
        return ((this.mStatus.hashCode() + 527) * 31) + (this.zabi ? 1 : 0);
    }
}
