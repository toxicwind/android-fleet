package com.ju.lib.datacommunication.network.http.impl;

import android.os.SystemProperties;
import android.text.TextUtils;
import com.ju.lib.datacommunication.network.http.core.A;
import com.ju.lib.datacommunication.network.http.core.B;
import com.ju.lib.datacommunication.network.http.core.HttpException;
import com.ju.lib.datacommunication.network.http.core.n;
import com.ju.lib.datacommunication.network.http.core.p;
import com.ju.lib.datacommunication.network.http.core.q;
import com.ju.lib.datacommunication.network.http.core.r;
import com.ju.lib.datacommunication.network.http.core.s;
import com.ju.lib.datacommunication.network.http.core.signature.SignatureException;
import com.ju.lib.datacommunication.network.http.core.y;
import com.ju.lib.datacommunication.network.http.core.z;
import com.ju.lib.datacommunication.network.http.error.NetworkErrorEnum;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import okhttp3.D;
import okhttp3.InterfaceC0053m;
import okhttp3.K;
import okhttp3.O;
import okhttp3.P;
import okhttp3.S;
import okhttp3.T;
import okhttp3.X;
import okhttp3.Y;
import okhttp3.a0;
import okhttp3.b0;
import okhttp3.c0;
import okhttp3.e0;

/* JADX INFO: loaded from: classes.dex */
public class l extends A {
    private final T b;
    private final y c;
    private final com.ju.lib.datacommunication.network.http.g.l d;
    private final String e;
    private final com.ju.lib.datacommunication.network.http.f f;

    public l(z zVar, l lVar) {
        S sT;
        com.ju.lib.datacommunication.network.http.i.b.b("HTTP2.Stack", zVar);
        this.f78a = zVar;
        if (lVar == null) {
            sT = new S();
        } else {
            sT = lVar.b.t();
            sT.g().clear();
            sT.h().clear();
        }
        String strN = n();
        this.e = o(strN) ? strN : null;
        this.d = zVar.h();
        this.f = zVar.j();
        y yVarG = zVar.g();
        this.c = yVarG;
        sT.e(yVarG != null ? j(yVarG) : D.f157a);
        if (zVar.b() == null || zVar.c() <= 0) {
            sT.c(null);
        } else {
            sT.c(com.ju.lib.datacommunication.network.http.i.a.a(zVar.b(), zVar.c()));
        }
        if (zVar.d() > 0) {
            sT.d(zVar.d(), TimeUnit.MILLISECONDS);
        }
        if (zVar.k() > 0) {
            sT.i(zVar.k(), TimeUnit.MILLISECONDS);
        }
        if (zVar.l() != null && zVar.m() != null) {
            sT.j(zVar.l(), zVar.m());
        }
        if (zVar.i() != null) {
            sT.f(zVar.i());
        }
        r(zVar, sT);
        s(zVar, sT);
        this.b = sT.b();
    }

    private p h(e0 e0Var) {
        if (e0Var == null) {
            return null;
        }
        return new i(e0Var);
    }

    private s i(c0 c0Var, n nVar) {
        q qVar = new q();
        qVar.l(nVar);
        qVar.j(c0Var.p());
        qVar.k(c0Var.Q());
        K kO = c0Var.O();
        for (int i = 0; i < kO.g(); i++) {
            qVar.g(kO.e(i), kO.h(i));
        }
        Object objI = c0Var.W().i();
        if (objI instanceof k) {
            qVar.m(((k) objI).c);
        }
        if (c0Var.a() != null) {
            qVar.h(h(c0Var.a()));
        }
        return qVar.i();
    }

    private D j(y yVar) {
        return new f(this, yVar);
    }

    private Y k(n nVar, k kVar) {
        X x = new X();
        x.o(nVar.i());
        x.n(kVar);
        com.ju.lib.datacommunication.network.http.core.i iVarC = nVar.c();
        for (int i = 0; i < iVarC.c(); i++) {
            x.a(iVarC.a(i), iVarC.b(i));
        }
        switch (g.f147a[nVar.e().ordinal()]) {
            case 1:
                x.d();
                break;
            case 2:
                x.j(l(nVar.a()));
                break;
            case 3:
                x.e();
                break;
            case 4:
                x.k(l(nVar.a()));
                break;
            case 5:
                x.c(l(nVar.a()));
                break;
            case 6:
                x.i(l(nVar.a()));
                break;
            default:
                throw new IllegalArgumentException("METHOD = " + nVar.e());
        }
        return x.b();
    }

    private a0 l(com.ju.lib.datacommunication.network.http.core.k kVar) {
        if (kVar == null) {
            return null;
        }
        return new j(kVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void m(IOException iOException) {
        com.ju.lib.datacommunication.network.http.f fVar;
        if (iOException == null || (fVar = this.f) == null || fVar.a()) {
            return;
        }
        String message = iOException.getMessage();
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(message);
        stringBuffer.append(" ;; LOCAL_NETWORK_NOT_CONNECTED");
        try {
            com.ju.lib.datacommunication.network.http.i.c.a(Throwable.class, iOException, "detailMessage", stringBuffer.toString());
        } catch (Exception e) {
            com.ju.lib.datacommunication.network.http.i.b.c("HTTP2.Stack", e.getMessage());
        }
    }

    private String n() {
        String str = SystemProperties.get("persist.his.domain", "");
        com.ju.lib.datacommunication.network.http.i.b.d("HTTP2.Stack", "persist.his.domain = ", str);
        return str;
    }

    private boolean o(String str) {
        String[] strArr = {"~", "!", "@", "#", "$", "%", "^", "&", "*", "(", ")", "?", "|", "/", ";", ":", ",", "=", "+", "{", "}", "[", "]"};
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        for (int i = 0; i < 23; i++) {
            if (str.contains(strArr[i])) {
                return false;
            }
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public s p(n nVar, c0 c0Var, k kVar) throws Exception {
        try {
            return q(nVar, c0Var, kVar);
        } catch (Exception e) {
            com.ju.lib.datacommunication.network.http.error.c.a().b(nVar, kVar.c, e);
            throw e;
        }
    }

    private s q(n nVar, c0 c0Var, k kVar) throws SignatureException {
        com.ju.lib.datacommunication.network.http.core.signature.b bVarH = nVar.h();
        com.ju.lib.datacommunication.network.http.i.b.d("HTTP2.Stack", "response: ", Integer.valueOf(nVar.d()), ", ", Integer.valueOf(c0Var.p()));
        if (!c0Var.P()) {
            com.ju.lib.datacommunication.network.http.error.c cVarA = com.ju.lib.datacommunication.network.http.error.c.a();
            r rVar = kVar.c;
            com.ju.lib.datacommunication.network.http.error.b bVar = new com.ju.lib.datacommunication.network.http.error.b(NetworkErrorEnum.UnhappyResponse, nVar.i());
            bVar.g(String.valueOf(c0Var.p()));
            bVar.b(c0Var.Q());
            cVarA.c(nVar, rVar, null, bVar);
        } else if (bVarH != null) {
            String strQ = c0Var.a().Q();
            String strM = c0Var.M("X-Sign-For");
            if (TextUtils.isEmpty(strM)) {
                strQ = bVarH.a(strQ);
            } else {
                bVarH.b(strQ, strM);
            }
            if (TextUtils.isEmpty(strQ)) {
                throw new SignatureException(strQ);
            }
            com.ju.lib.datacommunication.network.http.i.b.d("HTTP2.Stack", "Signer checked! ");
            P pB = c0Var.a().B();
            b0 b0VarS = c0Var.S();
            b0VarS.b(e0.N(pB, strQ));
            c0Var = b0VarS.c();
        }
        if (com.ju.lib.datacommunication.network.http.debug.f.k()) {
            com.ju.lib.datacommunication.network.http.debug.f.g().i(c0Var, kVar.b);
        }
        return i(c0Var, nVar);
    }

    private void r(z zVar, S s) {
        ArrayList arrayList = new ArrayList();
        com.ju.lib.datacommunication.network.http.g.l lVar = this.d;
        if (lVar != null) {
            arrayList.add(new com.ju.lib.datacommunication.network.http.impl.m.c(lVar));
        }
        if (this.e != null) {
            com.ju.lib.datacommunication.network.http.i.b.d("HTTP2.Stack", "setInterceptor(), has set host domain");
            arrayList.add(new com.ju.lib.datacommunication.network.http.impl.m.d(this.e));
        } else {
            com.ju.lib.datacommunication.network.http.i.b.d("HTTP2.Stack", "setInterceptor(), has not set valid host domain");
        }
        arrayList.add(new com.ju.lib.datacommunication.network.http.impl.m.e());
        arrayList.add(new com.ju.lib.datacommunication.network.http.impl.m.b());
        if (zVar.o()) {
            arrayList.add(new com.ju.lib.datacommunication.network.http.impl.m.f(zVar.a()));
        }
        y yVar = this.c;
        if (yVar != null) {
            arrayList.add(new com.ju.lib.datacommunication.network.http.impl.m.a(yVar));
        }
        List listE = zVar.e();
        if (listE != null && !listE.isEmpty()) {
            arrayList.addAll(listE);
        }
        Collections.sort(arrayList, new c(this));
        s.g().addAll(arrayList);
        com.ju.lib.datacommunication.network.http.i.b.d("HTTP2.Stack", " interceptors: " + s.g());
    }

    private void s(z zVar, S s) {
        if (zVar.n()) {
            s.a(new com.ju.lib.datacommunication.network.http.impl.n.c());
        }
        s.a(new com.ju.lib.datacommunication.network.http.impl.n.a());
        s.a(new com.ju.lib.datacommunication.network.http.impl.n.e());
        if (this.e != null) {
            com.ju.lib.datacommunication.network.http.i.b.d("HTTP2.Stack", "setNetworkInterceptor(), has set host domain");
            s.a(new com.ju.lib.datacommunication.network.http.impl.n.d(this.e));
        } else {
            com.ju.lib.datacommunication.network.http.i.b.d("HTTP2.Stack", "setNetworkInterceptor(), has not set valid host domain");
        }
        List<O> listF = zVar.f();
        if (listF == null || listF.isEmpty()) {
            return;
        }
        for (O o : listF) {
            com.ju.lib.datacommunication.network.http.i.b.d("HTTP2.Stack", "networkInterceptor: ", o);
            s.a(o);
        }
    }

    @Override // com.ju.lib.datacommunication.network.http.core.A
    public void b(n nVar, B b) {
        com.ju.lib.datacommunication.network.http.i.b.d("HTTP2.Stack", "enqueue: ", Integer.valueOf(nVar.d()), ", ", nVar.i());
        k kVar = new k(nVar);
        if (com.ju.lib.datacommunication.network.http.debug.f.k()) {
            kVar.b = com.ju.lib.datacommunication.network.http.debug.f.g().c(nVar, b);
        }
        InterfaceC0053m interfaceC0053mU = this.b.u(k(nVar, kVar));
        a(nVar, new h(interfaceC0053mU));
        if (b == null) {
            interfaceC0053mU.B(new d(this, nVar, kVar));
        } else {
            interfaceC0053mU.B(new e(this, b, nVar, kVar));
        }
    }

    @Override // com.ju.lib.datacommunication.network.http.core.A
    public s c(n nVar) throws HttpException {
        String strI = nVar.i();
        if (TextUtils.isEmpty(strI) || strI.startsWith("null") || strI.startsWith("NULL")) {
            throw new HttpException(-1, "url is invalid");
        }
        com.ju.lib.datacommunication.network.http.i.b.d("HTTP2.Stack", " execute: ", Integer.valueOf(nVar.d()), ", ", nVar.i());
        k kVar = new k(nVar);
        if (com.ju.lib.datacommunication.network.http.debug.f.k()) {
            kVar.b = com.ju.lib.datacommunication.network.http.debug.f.g().c(nVar, null);
        }
        try {
            try {
                InterfaceC0053m interfaceC0053mU = this.b.u(k(nVar, kVar));
                a(nVar, new h(interfaceC0053mU));
                try {
                    return p(nVar, interfaceC0053mU.e(), kVar);
                } catch (IOException e) {
                    com.ju.lib.datacommunication.network.http.error.c.a().b(nVar, kVar.c, e);
                    throw e;
                }
            } catch (SignatureException e2) {
                throw new HttpException(1003, "Verify signature error! ", e2);
            }
        } catch (IOException e3) {
            m(e3);
            HttpException httpException = new HttpException(1001, "Connect error! ", e3);
            httpException.b(kVar.c);
            throw httpException;
        }
    }

    @Override // com.ju.lib.datacommunication.network.http.core.A
    public void e() {
        com.ju.lib.datacommunication.network.http.g.l lVar = this.d;
        if (lVar != null) {
            lVar.a();
        }
    }
}
