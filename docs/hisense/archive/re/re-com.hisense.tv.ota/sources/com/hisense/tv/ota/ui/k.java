package com.hisense.tv.ota.ui;

/* JADX INFO: loaded from: classes.dex */
class k implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ int f60a;
    final /* synthetic */ l b;

    k(l lVar, int i) {
        this.b = lVar;
        this.f60a = i;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.b.c.setProgress(this.f60a);
        if (this.b.d != null) {
            this.b.d.setText("" + this.f60a + "%");
        }
    }
}
