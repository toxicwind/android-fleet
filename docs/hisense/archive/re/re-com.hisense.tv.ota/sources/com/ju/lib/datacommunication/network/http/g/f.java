package com.ju.lib.datacommunication.network.http.g;

/* JADX INFO: loaded from: classes.dex */
class f implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ h f128a;

    f(h hVar) {
        this.f128a = hVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        h hVar = this.f128a;
        long jP = hVar.p(hVar.f130a.c);
        com.ju.lib.datacommunication.network.http.i.b.d(h.i, "next update time:" + jP);
        h hVar2 = this.f128a;
        hVar2.s(hVar2.h, jP);
    }
}
