package com.ju.lib.datacommunication.network.http.impl.m;

import com.ju.lib.datacommunication.network.http.core.v;
import com.ju.lib.datacommunication.network.http.core.y;
import com.ju.lib.datacommunication.network.http.impl.InterceptorPriority;
import java.net.InetAddress;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import okhttp3.N;
import okhttp3.X;
import okhttp3.Y;
import okhttp3.c0;

/* JADX INFO: loaded from: classes.dex */
public class a extends com.ju.lib.datacommunication.network.http.impl.b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final y f151a;

    public a(y yVar) {
        this.f151a = yVar;
    }

    @Override // okhttp3.O
    public c0 a(N n) throws SocketTimeoutException, UnknownHostException {
        y yVar;
        Y yA = n.a();
        String strL = yA.k().l();
        if (com.ju.lib.datacommunication.network.http.impl.b.b(strL)) {
            return n.f(yA);
        }
        v vVarB = this.f151a.b(strL);
        if (vVarB == null || vVarB.f() == null || vVarB.f().isEmpty()) {
            throw new UnknownHostException(strL);
        }
        String hostAddress = ((InetAddress) vVarB.f().get(0)).getHostAddress();
        try {
            X xH = yA.h();
            xH.f("X_REAL_HOST", hostAddress);
            return n.f(xH.b());
        } catch (SocketTimeoutException e) {
            if (e.getMessage() != null && e.getMessage().contains("failed to connect to") && (yVar = this.f151a) != null) {
                yVar.a(yA.k().l(), null);
            }
            throw e;
        }
    }

    @Override // com.ju.lib.datacommunication.network.http.impl.b
    protected InterceptorPriority c() {
        return InterceptorPriority.NORMAL;
    }
}
