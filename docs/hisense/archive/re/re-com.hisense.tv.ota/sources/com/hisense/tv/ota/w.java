package com.hisense.tv.ota;

import android.content.Context;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
class w extends M {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ Q f69a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    w(Q q) {
        super(q);
        this.f69a = q;
    }

    @Override // com.hisense.tv.ota.M, com.hisense.tv.ota.ui.z
    public void c() {
        C0016q.t((Context) this.f69a.f19a.get()).E(true);
    }

    @Override // com.hisense.tv.ota.M, com.hisense.tv.ota.ui.z
    public void e() {
        com.hisense.tv.ota.W.k.N((Context) this.f69a.f19a.get(), "04");
        Log.i("SysUpgradeUIManager", "user cancel download, upload EVENT_CODE_CANCEL_DOWNLOAD to server ");
        C0016q.t((Context) this.f69a.f19a.get()).m();
    }
}
