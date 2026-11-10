package com.google.android.gms.common.api.internal;

import android.util.Log;
import com.google.android.gms.common.ConnectionResult;

/* JADX INFO: loaded from: classes.dex */
public final class zabo implements Runnable {
    public final /* synthetic */ ConnectionResult zajc;
    public final /* synthetic */ GoogleApiManager.zab zajk;

    public zabo(GoogleApiManager.zab zabVar, ConnectionResult connectionResult) {
        this.zajk = zabVar;
        this.zajc = connectionResult;
    }

    @Override // java.lang.Runnable
    public final void run() {
        GoogleApiManager.zaa zaaVar = (GoogleApiManager.zaa) GoogleApiManager.this.zaim.get(this.zajk.zaft);
        if (zaaVar == null) {
            return;
        }
        if (!this.zajc.isSuccess()) {
            zaaVar.onConnectionFailed(this.zajc);
            return;
        }
        GoogleApiManager.zab zabVar = this.zajk;
        zabVar.zajg = true;
        if (zabVar.zais.requiresSignIn()) {
            this.zajk.zabp();
            return;
        }
        try {
            this.zajk.zais.getRemoteService(null, this.zajk.zais.getScopesForConnectionlessNonSignIn());
        } catch (SecurityException e2) {
            Log.e("GoogleApiManager", "Failed to get service from broker. ", e2);
            zaaVar.onConnectionFailed(new ConnectionResult(10));
        }
    }
}
