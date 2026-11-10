package com.vt.tv.usbota.F;

/* JADX INFO: loaded from: classes.dex */
class i implements Runnable {
    final /* synthetic */ j a;

    i(j jVar) {
        this.a = jVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.a.c.requestFocus();
    }
}
