package com.vt.tv.usbota;

import android.content.Context;
import android.util.Log;

/* JADX INFO: renamed from: com.vt.tv.usbota.b, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
class RunnableC0001b implements Runnable {
    final /* synthetic */ m a;
    final /* synthetic */ l b;

    RunnableC0001b(l lVar, m mVar) {
        this.b = lVar;
        this.a = mVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.a.c(SysUpgradeState.VERIFYING);
        if (this.b.c != null) {
            l lVar = this.b;
            lVar.y(lVar.c.c(), this.b.f);
        } else {
            Log.e("SysUpgradeManager", "start verify package failed, version data is null");
            this.a.c(SysUpgradeState.VERIFY_FAILED);
            E.j((Context) this.b.a.get()).m(7);
        }
    }
}
