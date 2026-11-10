package com.hisense.tv.ota;

import android.content.Context;

/* JADX INFO: loaded from: classes.dex */
class B extends M {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ Q f5a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    B(Q q) {
        super(q);
        this.f5a = q;
    }

    @Override // com.hisense.tv.ota.M, com.hisense.tv.ota.ui.z
    public void c() {
        C0016q.t((Context) this.f5a.f19a.get()).u().m(true);
    }

    @Override // com.hisense.tv.ota.M, com.hisense.tv.ota.ui.z
    public void e() {
        C0016q.t((Context) this.f5a.f19a.get()).l();
    }
}
