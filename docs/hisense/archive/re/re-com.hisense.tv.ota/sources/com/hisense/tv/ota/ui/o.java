package com.hisense.tv.ota.ui;

/* JADX INFO: loaded from: classes.dex */
class o implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ int f62a;
    final /* synthetic */ p b;

    o(p pVar, int i) {
        this.b = pVar;
        this.f62a = i;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.b.c.setProgress(this.f62a);
        if (this.b.d != null) {
            this.b.d.setText("" + this.f62a + "%");
        }
    }
}
