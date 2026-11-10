package com.hisense.tv.ota.ui;

/* JADX INFO: renamed from: com.hisense.tv.ota.ui.d, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
class RunnableC0024d implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ ViewOnClickListenerC0025e f57a;

    RunnableC0024d(ViewOnClickListenerC0025e viewOnClickListenerC0025e) {
        this.f57a = viewOnClickListenerC0025e;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.f57a.f55a.c();
    }
}
