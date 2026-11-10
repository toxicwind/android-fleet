package com.hisense.tv.ota;

/* JADX INFO: renamed from: com.hisense.tv.ota.d, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
class RunnableC0003d implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ boolean f34a;
    final /* synthetic */ int b;
    final /* synthetic */ C0016q c;

    RunnableC0003d(C0016q c0016q, boolean z, int i) {
        this.c = c0016q;
        this.f34a = z;
        this.b = i;
    }

    @Override // java.lang.Runnable
    public void run() {
        C0016q c0016q = this.c;
        c0016q.o(this.f34a, this.b, c0016q.i);
    }
}
