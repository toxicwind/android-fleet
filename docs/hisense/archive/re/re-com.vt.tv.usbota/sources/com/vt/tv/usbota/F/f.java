package com.vt.tv.usbota.F;

/* JADX INFO: loaded from: classes.dex */
class f implements Runnable {
    final /* synthetic */ int a;
    final /* synthetic */ h b;

    f(h hVar, int i) {
        this.b = hVar;
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
