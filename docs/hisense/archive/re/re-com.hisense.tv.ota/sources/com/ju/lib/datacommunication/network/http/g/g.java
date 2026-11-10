package com.ju.lib.datacommunication.network.http.g;

/* JADX INFO: loaded from: classes.dex */
class g implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ h f129a;

    g(h hVar) {
        this.f129a = hVar;
    }

    @Override // java.lang.Runnable
    public void run() throws Throwable {
        try {
            com.ju.lib.datacommunication.network.http.i.b.d(h.i, "get from remote start:");
            String str = "0";
            if (this.f129a.f130a != null && this.f129a.f130a.d != null && !"".equals(this.f129a.f130a.d)) {
                str = this.f129a.f130a.d;
            }
            b bVarA = this.f129a.d.a(str);
            if (bVarA == null || bVarA.f126a != 0) {
                this.f129a.s(this.f129a.h, this.f129a.e * 3 * 60 * 1000);
                if (480 > this.f129a.e) {
                    h.m(this.f129a);
                    return;
                }
                return;
            }
            this.f129a.f130a = bVarA;
            this.f129a.o();
            this.f129a.s(this.f129a.h, 86400000L);
            if (com.ju.lib.datacommunication.network.http.i.b.e()) {
                com.ju.lib.datacommunication.network.http.i.b.b(h.i, "doGetFromRemote mAddressResponse :", this.f129a.f130a);
            }
        } catch (Exception e) {
            h hVar = this.f129a;
            hVar.s(hVar.h, this.f129a.e * 3 * 60 * 1000);
            if (480 > this.f129a.e) {
                h.m(this.f129a);
            }
            com.ju.lib.datacommunication.network.http.i.b.d(h.i, "get from remote fail:" + e);
        }
    }
}
