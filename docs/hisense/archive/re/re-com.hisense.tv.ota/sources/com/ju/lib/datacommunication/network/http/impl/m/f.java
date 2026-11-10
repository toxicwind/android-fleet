package com.ju.lib.datacommunication.network.http.impl.m;

import com.ju.lib.datacommunication.network.http.core.n;
import com.ju.lib.datacommunication.network.http.core.w;
import com.ju.lib.datacommunication.network.http.impl.InterceptorPriority;
import com.ju.lib.datacommunication.network.http.impl.k;
import java.io.IOException;
import okhttp3.N;
import okhttp3.Y;
import okhttp3.c0;

/* JADX INFO: loaded from: classes.dex */
public class f extends com.ju.lib.datacommunication.network.http.impl.b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final w f153a;

    public f(w wVar) {
        this.f153a = wVar;
    }

    private long d(int i) {
        w wVar = this.f153a;
        if (wVar == null) {
            return 0L;
        }
        return wVar.a(i);
    }

    private int e(Y y) {
        Object objI = y.i();
        if (!(objI instanceof k)) {
            return 0;
        }
        n nVar = ((k) objI).f150a;
        if (nVar.g() >= 0) {
            return nVar.g();
        }
        return 0;
    }

    @Override // okhttp3.O
    public c0 a(N n) {
        int i;
        c0 c0VarF;
        Y yA = n.a();
        int iE = e(yA);
        int i2 = 0;
        while (true) {
            try {
                try {
                    c0VarF = n.f(yA);
                    if ((c0VarF != null && c0VarF.P()) || i2 >= iE) {
                        break;
                    }
                    i = i2 + 1;
                    long jD = d(i2);
                    if (jD > 0 && i <= iE) {
                        try {
                            com.ju.lib.datacommunication.network.http.i.b.g("HTTP2.RetryInterceptor", "RetryInterceptor: currentTime = ", Integer.valueOf(i), ",times:", Integer.valueOf(iE), ", url = ", yA.k(), ",backoff=", Long.valueOf(jD));
                            Thread.sleep(jD);
                        } catch (InterruptedException unused) {
                        }
                    }
                    i2 = i;
                } catch (IOException e) {
                    com.ju.lib.datacommunication.network.http.i.b.f("HTTP2.RetryInterceptor", e, "RetryInterceptor: currentTime = ", Integer.valueOf(i2), ", url = ", yA.k());
                    if (i2 >= iE) {
                        throw e;
                    }
                    i = i2 + 1;
                    long jD2 = d(i2);
                    if (jD2 > 0 && i <= iE) {
                        com.ju.lib.datacommunication.network.http.i.b.g("HTTP2.RetryInterceptor", "RetryInterceptor: currentTime = ", Integer.valueOf(i), ",times:", Integer.valueOf(iE), ", url = ", yA.k(), ",backoff=", Long.valueOf(jD2));
                        Thread.sleep(jD2);
                    }
                }
            } catch (Throwable th) {
                int i3 = i2 + 1;
                long jD3 = d(i2);
                if (jD3 > 0 && i3 <= iE) {
                    try {
                        com.ju.lib.datacommunication.network.http.i.b.g("HTTP2.RetryInterceptor", "RetryInterceptor: currentTime = ", Integer.valueOf(i3), ",times:", Integer.valueOf(iE), ", url = ", yA.k(), ",backoff=", Long.valueOf(jD3));
                        Thread.sleep(jD3);
                    } catch (InterruptedException unused2) {
                    }
                }
                throw th;
            }
        }
        return c0VarF;
    }

    @Override // com.ju.lib.datacommunication.network.http.impl.b
    protected InterceptorPriority c() {
        return InterceptorPriority.NORMAL;
    }
}
