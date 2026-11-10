package com.vt.tv.usbota.F;

/* JADX INFO: loaded from: classes.dex */
class o implements Runnable {
    final /* synthetic */ int a;
    final /* synthetic */ q b;

    o(q qVar, int i) {
        this.b = qVar;
        this.a = i;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.b.d.setProgress(this.a);
        if (this.b.f != null) {
            this.b.f.setText("" + this.a + "%");
        }
    }
}
