package com.hisense.tv.ota;

/* JADX INFO: renamed from: com.hisense.tv.ota.e, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
class RunnableC0004e implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ boolean f35a;
    final /* synthetic */ int b;
    final /* synthetic */ C0016q c;

    RunnableC0004e(C0016q c0016q, boolean z, int i) {
        this.c = c0016q;
        this.f35a = z;
        this.b = i;
    }

    @Override // java.lang.Runnable
    public void run() {
        C0016q c0016q = this.c;
        c0016q.o(this.f35a, this.b, c0016q.i);
    }
}
