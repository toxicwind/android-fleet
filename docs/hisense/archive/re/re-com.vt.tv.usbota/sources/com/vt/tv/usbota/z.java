package com.vt.tv.usbota;

import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
class z implements com.vt.tv.usbota.G.c {
    private com.vt.tv.usbota.F.n a;
    final /* synthetic */ E b;

    z(E e) {
        this.b = e;
    }

    @Override // com.vt.tv.usbota.G.c
    public void a(int i, Object... objArr) {
        if (this.a != null) {
            this.b.b.post(new x(this));
        }
    }

    @Override // com.vt.tv.usbota.G.c
    public void b() {
        if (this.a != null) {
            this.b.b.post(new y(this));
        }
    }

    @Override // com.vt.tv.usbota.G.c
    public void c(int i) {
        if (this.a != null) {
            Log.i("SysUpgradeUIManager", "SysUpgradeUIManager my installListener onInstallProgress " + i);
            this.a.a(i);
        }
    }

    public void e(com.vt.tv.usbota.F.n nVar) {
        this.a = nVar;
    }
}
