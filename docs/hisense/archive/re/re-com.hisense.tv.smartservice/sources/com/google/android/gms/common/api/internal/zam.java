package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.Preconditions;

/* JADX INFO: loaded from: classes.dex */
public final class zam {
    public final int zadm;
    public final ConnectionResult zadn;

    public zam(ConnectionResult connectionResult, int i) {
        Preconditions.checkNotNull(connectionResult);
        this.zadn = connectionResult;
        this.zadm = i;
    }

    public final ConnectionResult getConnectionResult() {
        return this.zadn;
    }

    public final int zap() {
        return this.zadm;
    }
}
