package com.ju.lib.datacommunication.network.http.impl.n;

import com.ju.lib.datacommunication.network.http.core.g;
import com.ju.lib.datacommunication.network.http.core.i;
import com.ju.lib.datacommunication.network.http.core.r;
import com.ju.lib.datacommunication.network.http.impl.k;
import java.io.IOException;
import okhttp3.K;
import okhttp3.N;
import okhttp3.O;
import okhttp3.Y;
import okhttp3.c0;

/* JADX INFO: loaded from: classes.dex */
public class e implements O {
    private i b(K k) {
        g gVar = new g();
        for (int i = 0; i < k.g(); i++) {
            gVar.b(k.e(i), k.h(i));
        }
        return gVar.c();
    }

    @Override // okhttp3.O
    public c0 a(N n) throws IOException {
        r rVarB;
        Y yA = n.a();
        Object objI = yA.i();
        if (objI instanceof k) {
            k kVar = (k) objI;
            r rVarB2 = kVar.c;
            if (rVarB2 == null) {
                rVarB = new r();
                kVar.c = rVarB;
            } else {
                while (rVarB2.b() != null) {
                    rVarB2 = rVarB2.b();
                }
                rVarB2.f(new r());
                rVarB = rVarB2.b();
            }
            if (n.e().a() != null) {
                rVarB.c(n.e().a().d());
            }
            rVarB.l(yA.k().toString());
            rVarB.g(b(yA.e()));
            rVarB.h(System.currentTimeMillis());
        } else {
            rVarB = null;
        }
        try {
            c0 c0VarF = n.f(yA);
            if (rVarB != null) {
                rVarB.j(System.currentTimeMillis());
                rVarB.k(true);
                rVarB.d(c0VarF.p());
                rVarB.i(b(c0VarF.O()));
            }
            return c0VarF;
        } catch (IOException e) {
            if (rVarB != null) {
                rVarB.j(System.currentTimeMillis());
                rVarB.k(false);
                rVarB.e(e);
            }
            throw e;
        } catch (RuntimeException e2) {
            if (rVarB != null) {
                rVarB.j(System.currentTimeMillis());
                rVarB.k(false);
                rVarB.e(new IOException(e2));
            }
            throw e2;
        }
    }
}
