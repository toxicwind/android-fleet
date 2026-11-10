package com.google.android.gms.common.api.internal;

/* JADX INFO: loaded from: classes.dex */
public final class zaaj implements Runnable {
    public final /* synthetic */ zaak zafz;

    public zaaj(zaak zaakVar) {
        this.zafz = zaakVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zaak zaakVar = this.zafz;
        zaakVar.zaey.cancelAvailabilityErrorNotifications(zaakVar.mContext);
    }
}
