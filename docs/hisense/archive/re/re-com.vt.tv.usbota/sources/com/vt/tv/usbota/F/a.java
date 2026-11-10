package com.vt.tv.usbota.F;

/* JADX INFO: loaded from: classes.dex */
class a implements Runnable {
    final /* synthetic */ b a;

    a(b bVar) {
        this.a = bVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        if (this.a.isShowing()) {
            this.a.dismiss();
        }
    }
}
