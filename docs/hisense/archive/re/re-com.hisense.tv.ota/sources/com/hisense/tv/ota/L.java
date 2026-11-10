package com.hisense.tv.ota;

import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
class L implements com.hisense.tv.ota.W.i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private com.hisense.tv.ota.ui.A f15a;
    final /* synthetic */ Q b;

    L(Q q) {
        this.b = q;
    }

    @Override // com.hisense.tv.ota.W.i
    public void a(int i) {
        if (this.f15a != null) {
            Log.i("SysUpgradeUIManager", "SysUpgradeUIManager my installListener onInstallProgress " + i);
            this.f15a.a(i);
        }
    }

    @Override // com.hisense.tv.ota.W.i
    public void b() {
        if (this.f15a != null) {
            this.b.b.post(new K(this));
        }
    }

    @Override // com.hisense.tv.ota.W.i
    public void c(int i, Object... objArr) {
        if (this.f15a != null) {
            this.b.b.post(new J(this));
        }
    }

    public void e(com.hisense.tv.ota.ui.A a2) {
        this.f15a = a2;
    }
}
