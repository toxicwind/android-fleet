package com.google.android.gms.common.api.internal;

/* JADX INFO: loaded from: classes.dex */
public final class zabi implements Runnable {
    public final /* synthetic */ GoogleApiManager.zaa zaiq;

    public zabi(GoogleApiManager.zaa zaaVar) {
        this.zaiq = zaaVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zaiq.zabe();
    }
}
