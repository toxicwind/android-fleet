package com.ju.lib.datacommunication.network.http.g;

/* JADX INFO: loaded from: classes.dex */
class q implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ r f137a;

    q(r rVar) {
        this.f137a = rVar;
    }

    @Override // java.lang.Runnable
    public void run() throws Throwable {
        try {
            com.ju.lib.datacommunication.network.http.i.b.d(r.i, "get from remote start:");
            n nVarB = this.f137a.d.b();
            if (nVarB == null || nVarB.f134a != 0) {
                this.f137a.s(this.f137a.h, this.f137a.e * 3 * 60 * 1000);
                if (480 > this.f137a.e) {
                    r.m(this.f137a);
                }
            } else {
                this.f137a.f138a = nVarB;
                this.f137a.o();
                this.f137a.s(this.f137a.h, 86400000L);
                if (com.ju.lib.datacommunication.network.http.i.b.e()) {
                    com.ju.lib.datacommunication.network.http.i.b.b(r.i, "doGetFromRemote mAddressResponse :", this.f137a.f138a);
                }
            }
        } catch (Exception e) {
            r rVar = this.f137a;
            rVar.s(rVar.h, this.f137a.e * 3 * 60 * 1000);
            if (480 > this.f137a.e) {
                r.m(this.f137a);
            }
            com.ju.lib.datacommunication.network.http.i.b.d(r.i, "get from remote fail:" + e);
        }
    }
}
