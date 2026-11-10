package com.ju.lib.datacommunication.network.http.g;

/* JADX INFO: loaded from: classes.dex */
class p implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ r f136a;

    p(r rVar) {
        this.f136a = rVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        r rVar = this.f136a;
        long jP = rVar.p(rVar.f138a.e);
        com.ju.lib.datacommunication.network.http.i.b.d(r.i, "next update time:" + jP);
        r rVar2 = this.f136a;
        rVar2.s(rVar2.h, jP);
    }
}
