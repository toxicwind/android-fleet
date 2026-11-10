package com.google.android.gms.common.api.internal;

/* JADX INFO: loaded from: classes.dex */
public final class zabl implements Runnable {
    public final /* synthetic */ zabm zajd;

    public zabl(zabm zabmVar) {
        this.zajd = zabmVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zajd.zaiq.zais.disconnect();
    }
}
