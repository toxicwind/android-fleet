package com.hisense.tv.ota;

/* JADX INFO: loaded from: classes.dex */
class P implements com.hisense.tv.ota.W.j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private com.hisense.tv.ota.ui.A f18a;
    final /* synthetic */ Q b;

    P(Q q) {
        this.b = q;
    }

    @Override // com.hisense.tv.ota.W.j
    public void a(int i, Object... objArr) {
        if (this.f18a != null) {
            this.b.b.post(new N(this));
        }
    }

    @Override // com.hisense.tv.ota.W.j
    public void b() {
        if (this.f18a != null) {
            this.b.b.post(new O(this));
        }
    }

    public void d(com.hisense.tv.ota.ui.A a2) {
        this.f18a = a2;
    }

    @Override // com.hisense.tv.ota.W.j
    public void onProgress(int i) {
        com.hisense.tv.ota.ui.A a2 = this.f18a;
        if (a2 != null) {
            a2.a(i);
        }
    }
}
