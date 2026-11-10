package com.vt.tv.usbota;

import android.content.Context;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
class s extends A {
    final /* synthetic */ E a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    s(E e) {
        super(e);
        this.a = e;
    }

    @Override // com.vt.tv.usbota.A, com.vt.tv.usbota.F.m
    public void b() {
        m.a().c(SysUpgradeState.FOUND_NEW_VERSION);
        l lVarK = l.k((Context) this.a.a.get());
        C0000a c0000aL = lVarK.l();
        if (c0000aL == null) {
            Log.e("SysUpgradeUIManager", "download retry failed, session is null");
            m.a().c(SysUpgradeState.IDLE);
        } else {
            this.a.q(c0000aL);
            lVarK.w();
        }
    }

    @Override // com.vt.tv.usbota.A, com.vt.tv.usbota.F.m
    public void d() {
        m.a().c(SysUpgradeState.IDLE);
        l.k((Context) this.a.a.get()).x();
    }
}
