package com.hisense.tv.ota.ui;

/* JADX INFO: loaded from: classes.dex */
class r implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ int f63a;
    final /* synthetic */ t b;

    r(t tVar, int i) {
        this.b = tVar;
        this.f63a = i;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.b.d.setProgress(this.f63a);
        if (this.b.f != null) {
            this.b.f.setText("" + this.f63a + "%");
        }
    }
}
