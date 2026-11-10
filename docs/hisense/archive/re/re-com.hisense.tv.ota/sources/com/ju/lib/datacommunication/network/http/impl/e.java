package com.ju.lib.datacommunication.network.http.impl;

import com.ju.lib.datacommunication.network.http.core.B;
import com.ju.lib.datacommunication.network.http.core.HttpException;
import com.ju.lib.datacommunication.network.http.core.n;
import com.ju.lib.datacommunication.network.http.core.r;
import com.ju.lib.datacommunication.network.http.core.signature.SignatureException;
import com.ju.lib.datacommunication.network.http.error.NetworkErrorEnum;
import java.io.IOException;
import okhttp3.InterfaceC0053m;
import okhttp3.InterfaceC0054n;
import okhttp3.c0;

/* JADX INFO: loaded from: classes.dex */
class e implements InterfaceC0054n {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ B f146a;
    final /* synthetic */ n b;
    final /* synthetic */ k c;
    final /* synthetic */ l d;

    e(l lVar, B b, n nVar, k kVar) {
        this.d = lVar;
        this.f146a = b;
        this.b = nVar;
        this.c = kVar;
    }

    @Override // okhttp3.InterfaceC0054n
    public void a(InterfaceC0053m interfaceC0053m, c0 c0Var) {
        try {
            this.f146a.b(this.d.p(this.b, c0Var, this.c));
        } catch (HttpException e) {
            com.ju.lib.datacommunication.network.http.i.b.f("HTTP2.Stack", e, "Async http error! ");
            if (e.a() == 1002) {
                com.ju.lib.datacommunication.network.http.error.c cVarA = com.ju.lib.datacommunication.network.http.error.c.a();
                n nVar = this.b;
                r rVar = this.c.c;
                com.ju.lib.datacommunication.network.http.error.b bVar = new com.ju.lib.datacommunication.network.http.error.b(NetworkErrorEnum.DeserializeException, nVar.i());
                bVar.b(e.getMessage());
                cVarA.c(nVar, rVar, null, bVar);
            }
        } catch (SignatureException e2) {
            this.f146a.a(this.b, this.c.c, new HttpException(1003, "Verify signature error! ", e2));
        } catch (Exception e3) {
            this.f146a.a(this.b, this.c.c, e3);
        }
    }

    @Override // okhttp3.InterfaceC0054n
    public void b(InterfaceC0053m interfaceC0053m, IOException iOException) {
        com.ju.lib.datacommunication.network.http.i.b.f("HTTP2.Stack", iOException, "failure: ", interfaceC0053m.a().k());
        this.d.m(iOException);
        this.f146a.a(this.b, this.c.c, iOException);
        com.ju.lib.datacommunication.network.http.error.c.a().b(this.b, this.c.c, iOException);
    }
}
