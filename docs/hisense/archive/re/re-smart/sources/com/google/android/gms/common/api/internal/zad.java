package com.google.android.gms.common.api.internal;

import android.os.DeadObjectException;
import androidx.annotation.NonNull;
import b.a.a.a.a;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl;

/* JADX INFO: loaded from: classes.dex */
public final class zad<A extends BaseImplementation.ApiMethodImpl<? extends Result, Api.AnyClient>> extends zac {
    public final A zacp;

    public zad(int i, A a2) {
        super(i);
        this.zacp = a2;
    }

    @Override // com.google.android.gms.common.api.internal.zac
    public final void zaa(@NonNull Status status) {
        this.zacp.setFailedResult(status);
    }

    @Override // com.google.android.gms.common.api.internal.zac
    public final void zac(GoogleApiManager.zaa<?> zaaVar) throws DeadObjectException {
        try {
            this.zacp.run(zaaVar.zaad());
        } catch (RuntimeException e2) {
            zaa(e2);
        }
    }

    @Override // com.google.android.gms.common.api.internal.zac
    public final void zaa(@NonNull RuntimeException runtimeException) {
        String simpleName = runtimeException.getClass().getSimpleName();
        String localizedMessage = runtimeException.getLocalizedMessage();
        this.zacp.setFailedResult(new Status(10, a.a(a.a((Object) localizedMessage, simpleName.length() + 2), simpleName, ": ", localizedMessage)));
    }

    @Override // com.google.android.gms.common.api.internal.zac
    public final void zaa(@NonNull zaz zazVar, boolean z) {
        zazVar.zaa(this.zacp, z);
    }
}
