package com.hisense.tv.ota;

import android.util.Log;

/* JADX INFO: renamed from: com.hisense.tv.ota.i, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
class RunnableC0008i implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ C0016q f39a;

    RunnableC0008i(C0016q c0016q) {
        this.f39a = c0016q;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            Thread.sleep(5000L);
        } catch (InterruptedException e) {
            Log.w("SysUpgradeManager", "InterruptedException", e);
        }
        if (SysUpgradeState.IDLE.equals(C0019t.a().b())) {
            this.f39a.I(false, 0, false);
        } else {
            this.f39a.b.post(this);
        }
    }
}
