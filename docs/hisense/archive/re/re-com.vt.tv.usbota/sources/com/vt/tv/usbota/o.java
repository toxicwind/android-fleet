package com.vt.tv.usbota;

import android.content.Context;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
class o extends A {
    final /* synthetic */ E a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    o(E e) {
        super(e);
        this.a = e;
    }

    @Override // com.vt.tv.usbota.A, com.vt.tv.usbota.F.m
    public void c() {
        Log.i("SysUpgradeUIManager", "user cancel New version , upload EVENT_CODE_CANCEL_DOWNLOAD to server ");
        l.k((Context) this.a.a.get()).i();
    }

    @Override // com.vt.tv.usbota.A, com.vt.tv.usbota.F.m
    public void d() {
        Log.i("SysUpgradeUIManager", "New verison UI, user choose OK ");
        l lVarK = l.k((Context) this.a.a.get());
        C0000a c0000aL = lVarK.l();
        if (!c0000aL.f()) {
            this.a.q(c0000aL);
            lVarK.w();
        } else {
            Log.i("SysUpgradeUIManager", "debug version want to OTA to Debug version,start Install directly");
            m.a().c(SysUpgradeState.VERIFIED);
            this.a.n(c0000aL);
            lVarK.u();
        }
    }
}
