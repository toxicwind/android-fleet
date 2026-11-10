package com.ju.lib.datacommunication.network.http.impl;

import okhttp3.P;
import okhttp3.a0;

/* JADX INFO: loaded from: classes.dex */
final class j extends a0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private com.ju.lib.datacommunication.network.http.core.k f149a;

    public j(com.ju.lib.datacommunication.network.http.core.k kVar) {
        this.f149a = kVar;
    }

    @Override // okhttp3.a0
    public long a() {
        return this.f149a.a();
    }

    @Override // okhttp3.a0
    public P b() {
        String strB = this.f149a.b();
        if (strB == null) {
            return null;
        }
        return P.d(strB);
    }

    @Override // okhttp3.a0
    public void e(okio.i iVar) {
        this.f149a.c(iVar.r());
    }
}
