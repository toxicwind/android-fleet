package com.vt.tv.usbota;

import android.content.Context;
import android.util.Log;

/* JADX INFO: renamed from: com.vt.tv.usbota.c, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
class RunnableC0002c implements Runnable {
    final /* synthetic */ m a;
    final /* synthetic */ l b;

    RunnableC0002c(l lVar, m mVar) {
        this.b = lVar;
        this.a = mVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        if (this.b.c == null) {
            Log.e("SysUpgradeManager", "start install failed, version data is null");
            this.a.c(SysUpgradeState.INSTALE_FAILED);
            E.j((Context) this.b.a.get()).m(11);
        } else {
            l lVar = this.b;
            if (lVar.n(lVar.c.c(), this.b.g)) {
                Log.i("SysUpgradeManager", "!!!### Installing now ###!!!");
            } else {
                this.a.c(SysUpgradeState.INSTALE_FAILED);
                E.j((Context) this.b.a.get()).m(11);
            }
        }
    }
}
