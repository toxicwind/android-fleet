package com.ju.lib.datacommunication.network.http;

/* JADX INFO: loaded from: classes.dex */
class a implements e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final com.ju.lib.datacommunication.network.http.core.e f75a;

    public a(com.ju.lib.datacommunication.network.http.core.e eVar) {
        this.f75a = eVar;
    }

    @Override // com.ju.lib.datacommunication.network.http.e
    public com.ju.lib.datacommunication.network.http.core.e a() {
        return this.f75a;
    }

    @Override // com.ju.lib.datacommunication.network.http.e
    public com.ju.lib.datacommunication.network.http.h.a b() {
        return new com.ju.lib.datacommunication.network.http.h.a(this.f75a);
    }

    @Override // com.ju.lib.datacommunication.network.http.e
    public com.ju.lib.datacommunication.network.http.h.d c() {
        return new com.ju.lib.datacommunication.network.http.h.d(this.f75a);
    }
}
