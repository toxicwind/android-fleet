package com.google.android.gms.common.api.internal;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.ConnectionResult;

/* JADX INFO: loaded from: classes.dex */
public final class zas implements zabs {
    public final /* synthetic */ zaq zaet;

    public zas(zaq zaqVar) {
        this.zaet = zaqVar;
    }

    @Override // com.google.android.gms.common.api.internal.zabs
    public final void zab(@Nullable Bundle bundle) {
        this.zaet.zaer.lock();
        try {
            zaq.zaa(this.zaet, bundle);
            this.zaet.zaeo = ConnectionResult.RESULT_SUCCESS;
            this.zaet.zav();
        } finally {
            this.zaet.zaer.unlock();
        }
    }

    @Override // com.google.android.gms.common.api.internal.zabs
    public final void zac(@NonNull ConnectionResult connectionResult) {
        this.zaet.zaer.lock();
        try {
            this.zaet.zaeo = connectionResult;
            this.zaet.zav();
        } finally {
            this.zaet.zaer.unlock();
        }
    }

    public /* synthetic */ zas(zaq zaqVar, zat zatVar) {
        this.zaet = zaqVar;
    }

    @Override // com.google.android.gms.common.api.internal.zabs
    public final void zab(int i, boolean z) {
        this.zaet.zaer.lock();
        try {
            if (!this.zaet.zaeq && this.zaet.zaep != null && this.zaet.zaep.isSuccess()) {
                this.zaet.zaeq = true;
                this.zaet.zaej.onConnectionSuspended(i);
                return;
            }
            this.zaet.zaeq = false;
            zaq.zaa(this.zaet, i, z);
        } finally {
            this.zaet.zaer.unlock();
        }
    }
}
