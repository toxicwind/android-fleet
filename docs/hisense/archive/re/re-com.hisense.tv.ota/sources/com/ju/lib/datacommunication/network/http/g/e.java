package com.ju.lib.datacommunication.network.http.g;

/* JADX INFO: loaded from: classes.dex */
class e implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ h f127a;

    e(h hVar) {
        this.f127a = hVar;
    }

    @Override // java.lang.Runnable
    public void run() throws Throwable {
        try {
            com.ju.lib.datacommunication.network.http.i.b.d(h.i, "get from local start:");
            b bVarB = this.f127a.d.b();
            if (bVarB != null) {
                this.f127a.f130a = bVarB;
                this.f127a.o();
                this.f127a.s(this.f127a.g, 30000L);
                if (com.ju.lib.datacommunication.network.http.i.b.e()) {
                    com.ju.lib.datacommunication.network.http.i.b.b(h.i, "doGetFromLocal mAddressResponse :", this.f127a.f130a);
                }
            } else {
                com.ju.lib.datacommunication.network.http.i.b.d(h.i, "doGetFromLocal mAddressResponse is null");
                this.f127a.s(this.f127a.h, 0L);
            }
        } catch (Exception e) {
            h hVar = this.f127a;
            hVar.s(hVar.h, 0L);
            com.ju.lib.datacommunication.network.http.i.b.d(h.i, "get from local fail:" + e);
        }
    }
}
