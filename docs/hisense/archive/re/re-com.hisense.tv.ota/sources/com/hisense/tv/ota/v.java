package com.hisense.tv.ota;

import android.content.Context;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
class v extends M {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ Q f68a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    v(Q q) {
        super(q);
        this.f68a = q;
    }

    @Override // com.hisense.tv.ota.M, com.hisense.tv.ota.ui.z
    public void b() {
        Log.i("SysUpgradeUIManager", "New verison UI, user choose OK ");
        C0016q c0016qT = C0016q.t((Context) this.f68a.f19a.get());
        C0002c c0002cU = c0016qT.u();
        if (c0002cU.h()) {
            Log.i("SysUpgradeUIManager", "package exists, won't download, just to verify ");
            C0019t.a().c(SysUpgradeState.DOWNLOADED);
            if (c0002cU.i()) {
                Log.i("SysUpgradeUIManager", "show FTE downloadUI ");
                this.f68a.z(c0002cU);
            } else {
                this.f68a.D(c0002cU);
            }
            c0016qT.N();
        } else {
            Log.i("SysUpgradeUIManager", "begin to show download UI and startDownload Pkg ");
            if (c0002cU.i()) {
                this.f68a.w(c0002cU);
            } else {
                this.f68a.u(c0002cU);
                Log.i("SysUpgradeUIManager", "show ui");
            }
            c0016qT.J();
        }
        this.f68a.j.dismiss();
        com.hisense.tv.ota.W.k.N((Context) this.f68a.f19a.get(), "03");
        Log.i("SysUpgradeUIManager", "user choose OK , upload EVENT_CODE_SELECT_DOWNLOAD to server ");
    }

    @Override // com.hisense.tv.ota.M, com.hisense.tv.ota.ui.z
    public void d() {
        com.hisense.tv.ota.W.k.N((Context) this.f68a.f19a.get(), "10");
        Log.i("SysUpgradeUIManager", "user Not reminder , upload EVENT_CODE_CANCEL_UPGRADE to server ");
        C0016q.t((Context) this.f68a.f19a.get()).F();
    }

    @Override // com.hisense.tv.ota.M, com.hisense.tv.ota.ui.z
    public void e() {
        com.hisense.tv.ota.W.k.N((Context) this.f68a.f19a.get(), "04");
        Log.i("SysUpgradeUIManager", "user cancel New version , upload EVENT_CODE_CANCEL_DOWNLOAD to server ");
        C0016q.t((Context) this.f68a.f19a.get()).n();
    }
}
