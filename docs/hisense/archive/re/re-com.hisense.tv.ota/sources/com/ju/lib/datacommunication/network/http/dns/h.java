package com.ju.lib.datacommunication.network.http.dns;

/* JADX INFO: loaded from: classes.dex */
class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private com.ju.lib.datacommunication.network.http.f f116a;

    public h(com.ju.lib.datacommunication.network.http.f fVar) {
        this.f116a = fVar;
        j.f("networkConnectDetector = ", fVar);
    }

    public boolean a() {
        com.ju.lib.datacommunication.network.http.f fVar = this.f116a;
        if (fVar == null) {
            return true;
        }
        boolean zA = fVar.a();
        j.f("localNetworkConnected = ", Boolean.valueOf(zA));
        return zA;
    }
}
