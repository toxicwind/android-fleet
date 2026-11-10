package com.google.android.gms.common.api.internal;

import androidx.annotation.WorkerThread;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;

/* JADX INFO: loaded from: classes.dex */
public final class zacn implements Runnable {
    public final /* synthetic */ zack zaky;
    public final /* synthetic */ Result zakz;

    public zacn(zack zackVar, Result result) {
        this.zaky = zackVar;
        this.zakz = result;
    }

    @Override // java.lang.Runnable
    @WorkerThread
    public final void run() {
        try {
            BasePendingResult.zado.set(true);
            this.zaky.zakw.sendMessage(this.zaky.zakw.obtainMessage(0, this.zaky.zakr.onSuccess(this.zakz)));
        } catch (RuntimeException e2) {
            this.zaky.zakw.sendMessage(this.zaky.zakw.obtainMessage(1, e2));
        } finally {
            BasePendingResult.zado.set(false);
            zack zackVar = this.zaky;
            zack.zab(this.zakz);
            GoogleApiClient googleApiClient = this.zaky.zadr.get();
            if (googleApiClient != null) {
                googleApiClient.zab(this.zaky);
            }
        }
    }
}
