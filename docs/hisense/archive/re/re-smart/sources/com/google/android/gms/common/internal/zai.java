package com.google.android.gms.common.internal;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Status;

/* JADX INFO: loaded from: classes.dex */
public final class zai implements PendingResultUtil.zaa {
    @Override // com.google.android.gms.common.internal.PendingResultUtil.zaa
    public final ApiException zaf(Status status) {
        return ApiExceptionUtil.fromStatus(status);
    }
}
