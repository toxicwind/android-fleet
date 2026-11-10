package com.hisense.tv.ota;

import android.content.Context;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
class A extends M {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ Q f4a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    A(Q q) {
        super(q);
        this.f4a = q;
    }

    @Override // com.hisense.tv.ota.M, com.hisense.tv.ota.ui.z
    public void a() {
        C0019t.a().c(SysUpgradeState.DOWNLOAD_RETRY);
        C0019t.a().c(SysUpgradeState.FOUND_NEW_VERSION);
        C0016q c0016qT = C0016q.t((Context) this.f4a.f19a.get());
        C0002c c0002cU = c0016qT.u();
        if (c0002cU == null) {
            Log.e("SysUpgradeUIManager", "download retry failed, session is null");
            C0019t.a().c(SysUpgradeState.IDLE);
            return;
        }
        C0016q.t((Context) this.f4a.f19a.get()).u().m(false);
        if (c0002cU.i()) {
            Log.e("SysUpgradeUIManager", "user choose retry,show FTE download UI");
            this.f4a.w(c0002cU);
        } else {
            this.f4a.u(c0002cU);
        }
        c0016qT.J();
    }

    @Override // com.hisense.tv.ota.M, com.hisense.tv.ota.ui.z
    public void b() {
        C0019t.a().c(SysUpgradeState.IDLE);
        C0016q.t((Context) this.f4a.f19a.get()).u().m(false);
    }
}
