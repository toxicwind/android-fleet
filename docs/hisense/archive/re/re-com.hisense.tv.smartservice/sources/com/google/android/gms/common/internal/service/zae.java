package com.google.android.gms.common.internal.service;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;

/* JADX INFO: loaded from: classes.dex */
public final class zae implements zab {
    @Override // com.google.android.gms.common.internal.service.zab
    public final PendingResult<Status> zaa(GoogleApiClient googleApiClient) {
        return googleApiClient.execute(new zad(this, googleApiClient));
    }
}
