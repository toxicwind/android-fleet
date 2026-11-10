package com.hisense.tv.ota.ui;

/* JADX INFO: loaded from: classes.dex */
class B implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ int f51a;
    final /* synthetic */ D b;

    B(D d, int i) {
        this.b = d;
        this.f51a = i;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.b.d.setProgress(this.f51a);
        if (this.b.f != null) {
            this.b.f.setText("" + this.f51a + "%");
        }
    }
}
