package com.hisense.tv.ota.ui;

/* JADX INFO: renamed from: com.hisense.tv.ota.ui.c, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
class RunnableC0023c implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ int f56a;
    final /* synthetic */ ViewOnClickListenerC0025e b;

    RunnableC0023c(ViewOnClickListenerC0025e viewOnClickListenerC0025e, int i) {
        this.b = viewOnClickListenerC0025e;
        this.f56a = i;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.b.d.setProgress(this.f56a);
        if (this.b.f != null) {
            this.b.f.setText("" + this.f56a + "%");
        }
    }
}
