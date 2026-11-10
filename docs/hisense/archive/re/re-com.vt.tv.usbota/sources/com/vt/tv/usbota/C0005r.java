package com.vt.tv.usbota;

import android.content.Context;

/* JADX INFO: renamed from: com.vt.tv.usbota.r, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
class C0005r extends A {
    final /* synthetic */ E a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    C0005r(E e) {
        super(e);
        this.a = e;
    }

    @Override // com.vt.tv.usbota.A, com.vt.tv.usbota.F.m
    public void d() {
        if (l.k((Context) this.a.a.get()).v() || this.a.h == null || !this.a.h.isShowing()) {
            return;
        }
        this.a.h.dismiss();
    }
}
