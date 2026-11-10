package com.hisense.tv.ota;

import android.content.Context;

/* JADX INFO: loaded from: classes.dex */
class z extends M {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ Q f72a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    z(Q q) {
        super(q);
        this.f72a = q;
    }

    @Override // com.hisense.tv.ota.M, com.hisense.tv.ota.ui.z
    public void b() {
        if (C0016q.t((Context) this.f72a.f19a.get()).M() || this.f72a.m == null || !this.f72a.m.isShowing()) {
            return;
        }
        this.f72a.m.dismiss();
    }
}
