package com.hisense.tv.ota;

import android.util.Log;

/* JADX INFO: renamed from: com.hisense.tv.ota.g, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
class RunnableC0006g implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ C0019t f37a;
    final /* synthetic */ C0016q b;

    RunnableC0006g(C0016q c0016q, C0019t c0019t) {
        this.b = c0016q;
        this.f37a = c0019t;
    }

    @Override // java.lang.Runnable
    public void run() {
        if (this.b.c.e() == null) {
            Log.e("SysUpgradeManager", "start install failed, version data is null");
            this.f37a.c(SysUpgradeState.INSTALE_FAILED);
            this.b.h.c(-6, new Object[0]);
            return;
        }
        C0016q c0016q = this.b;
        if (c0016q.w(c0016q.c.d(), this.b.c.c(), this.b.h)) {
            Log.i("SysUpgradeManager", "!!!### Installing now ###!!!");
            return;
        }
        Log.i("SysUpgradeManager", "call installPkg failed");
        this.f37a.c(SysUpgradeState.INSTALE_FAILED);
        this.b.h.c(-6, new Object[0]);
    }
}
