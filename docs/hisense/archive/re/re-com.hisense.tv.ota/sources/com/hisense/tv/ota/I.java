package com.hisense.tv.ota;

import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
class I implements com.hisense.tv.ota.V.c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private com.hisense.tv.ota.ui.A f12a;
    final /* synthetic */ Q b;

    I(Q q) {
        this.b = q;
    }

    @Override // com.hisense.tv.ota.V.c
    public void a(String str) {
        if (this.f12a != null) {
            this.b.b.post(new H(this));
        }
    }

    @Override // com.hisense.tv.ota.V.c
    public void b(String str, int i) {
        if (this.f12a != null) {
            this.b.b.post(new F(this));
        }
    }

    @Override // com.hisense.tv.ota.V.c
    public void c(String str) {
    }

    @Override // com.hisense.tv.ota.V.c
    public void d(String str) {
        Log.e("SysUpgradeUIManager", "MyDownloadListener onFinish");
        if (this.f12a != null) {
            this.b.b.post(new G(this));
        }
    }

    @Override // com.hisense.tv.ota.V.c
    public void e(String str, int i) {
        com.hisense.tv.ota.ui.A a2 = this.f12a;
        if (a2 != null) {
            a2.a(i);
        }
    }

    public void g(com.hisense.tv.ota.ui.A a2) {
        this.f12a = a2;
    }
}
