package com.hisense.tv.ota.ui;

/* JADX INFO: loaded from: classes.dex */
class m implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ int f61a;
    final /* synthetic */ n b;

    m(n nVar, int i) {
        this.b = nVar;
        this.f61a = i;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.b.c.setProgress(this.f61a);
        if (this.b.d != null) {
            this.b.d.setText("" + this.f61a + "%");
        }
    }
}
