package com.google.android.gms.common.api.internal;

import android.os.Bundle;
import androidx.annotation.NonNull;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

/* JADX INFO: loaded from: classes.dex */
public final class zaar implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
    public final /* synthetic */ zaak zafz;

    public zaar(zaak zaakVar) {
        this.zafz = zaakVar;
    }

    @Override // com.google.android.gms.common.api.internal.ConnectionCallbacks
    public final void onConnected(Bundle bundle) {
        if (!this.zafz.zafa.isSignInClientDisconnectFixEnabled()) {
            zaak zaakVar = this.zafz;
            zaakVar.zagf.zaa(new zaap(zaakVar));
            return;
        }
        this.zafz.zaer.lock();
        try {
            if (this.zafz.zagf == null) {
                return;
            }
            this.zafz.zagf.zaa(new zaap(this.zafz));
        } finally {
            this.zafz.zaer.unlock();
        }
    }

    @Override // com.google.android.gms.common.api.internal.OnConnectionFailedListener
    public final void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        this.zafz.zaer.lock();
        try {
            if (this.zafz.zad(connectionResult)) {
                this.zafz.zaap();
                this.zafz.zaan();
            } else {
                this.zafz.zae(connectionResult);
            }
        } finally {
            this.zafz.zaer.unlock();
        }
    }

    @Override // com.google.android.gms.common.api.internal.ConnectionCallbacks
    public final void onConnectionSuspended(int i) {
    }

    public /* synthetic */ zaar(zaak zaakVar, zaaj zaajVar) {
        this.zafz = zaakVar;
    }
}
