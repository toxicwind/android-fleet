package com.ju.lib.datacommunication.network.http.impl;

import com.ju.lib.datacommunication.network.http.core.n;
import java.io.IOException;
import okhttp3.InterfaceC0053m;
import okhttp3.InterfaceC0054n;
import okhttp3.c0;

/* JADX INFO: loaded from: classes.dex */
class d implements InterfaceC0054n {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ n f145a;
    final /* synthetic */ k b;

    d(l lVar, n nVar, k kVar) {
        this.f145a = nVar;
        this.b = kVar;
    }

    @Override // okhttp3.InterfaceC0054n
    public void a(InterfaceC0053m interfaceC0053m, c0 c0Var) {
        com.ju.lib.datacommunication.network.http.i.b.d("HTTP2.Stack", "response: ", Integer.valueOf(this.f145a.d()), ", ", Integer.valueOf(c0Var.p()));
        if (c0Var.a() != null) {
            c0Var.a().close();
        }
    }

    @Override // okhttp3.InterfaceC0054n
    public void b(InterfaceC0053m interfaceC0053m, IOException iOException) {
        com.ju.lib.datacommunication.network.http.i.b.f("HTTP2.Stack", iOException, "failure: ", interfaceC0053m.a().k());
        com.ju.lib.datacommunication.network.http.error.c.a().b(this.f145a, this.b.c, iOException);
    }
}
