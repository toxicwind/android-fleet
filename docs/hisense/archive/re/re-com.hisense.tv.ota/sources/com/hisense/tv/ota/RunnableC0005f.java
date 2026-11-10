package com.hisense.tv.ota;

import android.os.SystemProperties;
import android.util.Log;

/* JADX INFO: renamed from: com.hisense.tv.ota.f, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
class RunnableC0005f implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ C0019t f36a;
    final /* synthetic */ C0016q b;

    RunnableC0005f(C0016q c0016q, C0019t c0019t) {
        this.b = c0016q;
        this.f36a = c0019t;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.f36a.c(SysUpgradeState.VERIFYING);
        SystemProperties.set("sys.vt.ota.state", "4");
        if (this.b.c.e() != null) {
            C0016q c0016q = this.b;
            c0016q.O(c0016q.c.d(), this.b.c.c(), this.b.g);
        } else {
            Log.e("SysUpgradeManager", "start verify package failed, version data is null");
            this.f36a.c(SysUpgradeState.VERIFY_FAILED);
            this.b.g.a(5, new Object[0]);
        }
    }
}
