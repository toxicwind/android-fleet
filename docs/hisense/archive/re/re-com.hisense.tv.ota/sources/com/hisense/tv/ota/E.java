package com.hisense.tv.ota;

/* JADX INFO: loaded from: classes.dex */
class E implements InterfaceC0014o {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private com.hisense.tv.ota.ui.A f8a;
    final /* synthetic */ Q b;

    E(Q q) {
        this.b = q;
    }

    @Override // com.hisense.tv.ota.InterfaceC0014o
    public void a() {
    }

    @Override // com.hisense.tv.ota.InterfaceC0014o
    public void b(int i, int i2, VersionData versionData) {
        if (this.f8a != null) {
            this.b.b.post(new D(this));
        }
    }

    public void d(com.hisense.tv.ota.ui.A a2) {
        this.f8a = a2;
    }
}
