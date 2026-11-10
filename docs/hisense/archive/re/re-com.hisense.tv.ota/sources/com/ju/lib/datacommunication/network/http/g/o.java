package com.ju.lib.datacommunication.network.http.g;

/* JADX INFO: loaded from: classes.dex */
class o implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ r f135a;

    o(r rVar) {
        this.f135a = rVar;
    }

    @Override // java.lang.Runnable
    public void run() throws Throwable {
        try {
            com.ju.lib.datacommunication.network.http.i.b.d(r.i, "get from local start:");
            n nVarC = this.f135a.d.c();
            if (nVarC != null) {
                this.f135a.f138a = nVarC;
                this.f135a.o();
                this.f135a.s(this.f135a.g, 30000L);
                if (com.ju.lib.datacommunication.network.http.i.b.e()) {
                    com.ju.lib.datacommunication.network.http.i.b.b(r.i, "doGetFromLocal mAddressResponse :", this.f135a.f138a);
                }
            } else {
                com.ju.lib.datacommunication.network.http.i.b.d(r.i, "doGetFromLocal mAddressResponse is null");
                this.f135a.s(this.f135a.h, 0L);
            }
        } catch (Exception e) {
            r rVar = this.f135a;
            rVar.s(rVar.h, 0L);
            com.ju.lib.datacommunication.network.http.i.b.d(r.i, "get from local fail:" + e);
        }
    }
}
