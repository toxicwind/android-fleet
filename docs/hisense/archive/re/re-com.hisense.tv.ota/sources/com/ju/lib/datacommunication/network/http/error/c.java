package com.ju.lib.datacommunication.network.http.error;

import com.ju.lib.datacommunication.network.http.core.C.d;
import com.ju.lib.datacommunication.network.http.core.C.e;
import com.ju.lib.datacommunication.network.http.core.HiRequest$Method;
import com.ju.lib.datacommunication.network.http.core.k;
import com.ju.lib.datacommunication.network.http.core.n;
import com.ju.lib.datacommunication.network.http.core.r;
import com.ju.lib.datacommunication.network.http.core.signature.SignatureException;
import com.ju.lib.datacommunication.network.http.f;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.HashMap;
import javax.net.ssl.SSLException;

/* JADX INFO: loaded from: classes.dex */
public class c {
    static volatile c c;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private f f124a;
    a b;

    private c() {
    }

    public static c a() {
        if (c == null) {
            synchronized (c.class) {
                if (c == null) {
                    c = new c();
                }
            }
        }
        return c;
    }

    private NetworkErrorEnum d(Exception exc) {
        if (exc == null) {
            return NetworkErrorEnum.UnknownError;
        }
        com.ju.lib.datacommunication.network.http.i.b.b("RequestFailedManager", " parseCode class: ", exc.getClass());
        NetworkErrorEnum networkErrorEnum = NetworkErrorEnum.UnknownError;
        if (exc instanceof UnknownHostException) {
            return NetworkErrorEnum.UnknownHostException;
        }
        if (exc instanceof ConnectException) {
            return NetworkErrorEnum.ConnectException;
        }
        if (exc instanceof SocketTimeoutException) {
            return NetworkErrorEnum.SocketTimeoutException;
        }
        if (exc instanceof SSLException) {
            return NetworkErrorEnum.SSLException;
        }
        return exc instanceof SignatureException ? NetworkErrorEnum.SignatureException : networkErrorEnum;
    }

    private String e(r rVar) {
        if (rVar == null || rVar.a() == null || rVar.a().getAddress() == null) {
            return null;
        }
        return rVar.a().getAddress().getHostAddress() + ":" + rVar.a().getPort();
    }

    public void b(n nVar, r rVar, Exception exc) {
        c(nVar, rVar, exc, null);
    }

    public void c(n nVar, r rVar, Exception exc, b bVar) {
        if (nVar == null) {
            com.ju.lib.datacommunication.network.http.i.b.c("RequestFailedManager", " onRequestFailed request should not be null ");
            return;
        }
        if (bVar == null) {
            bVar = new b(d(exc), nVar.i());
            bVar.b(exc != null ? exc.getMessage() : "Unknown");
        }
        f fVar = this.f124a;
        if (fVar != null && !fVar.a()) {
            bVar.a(NetworkErrorEnum.LocalNetworkDisConnect);
        }
        bVar.d(e(rVar));
        if (nVar.e() == HiRequest$Method.POST) {
            bVar.f(f(nVar));
        }
        a aVar = this.b;
        if (aVar != null) {
            aVar.a(bVar);
        } else {
            com.ju.lib.datacommunication.network.http.i.b.g("RequestFailedManager", " requestFailedListener is null， requestFailedBean: ", bVar);
        }
    }

    public HashMap f(n nVar) {
        k kVarA = nVar.a();
        if (kVarA == null) {
            return null;
        }
        if (kVarA instanceof d) {
            return ((d) kVarA).d();
        }
        if (kVarA instanceof e) {
            return ((e) kVarA).d();
        }
        return null;
    }

    public void g(f fVar) {
        if (fVar != null) {
            this.f124a = fVar;
        } else {
            com.ju.lib.datacommunication.network.http.i.b.c("RequestFailedManager", " mNetworkConnectDetector should not be null ");
        }
    }

    public void h(a aVar) {
        this.b = aVar;
    }
}
