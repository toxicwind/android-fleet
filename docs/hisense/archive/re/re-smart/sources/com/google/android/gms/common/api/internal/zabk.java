package com.google.android.gms.common.api.internal;

/* JADX INFO: loaded from: classes.dex */
public final class zabk implements Runnable {
    public final /* synthetic */ GoogleApiManager.zaa zaiq;

    public zabk(GoogleApiManager.zaa zaaVar) {
        this.zaiq = zaaVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zaiq.zabf();
    }
}
