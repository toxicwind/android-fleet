package com.ju.lib.datacommunication.network.http.impl.m;

import com.ju.lib.datacommunication.network.http.impl.InterceptorPriority;
import okhttp3.L;
import okhttp3.M;
import okhttp3.N;
import okhttp3.X;
import okhttp3.Y;
import okhttp3.c0;

/* JADX INFO: loaded from: classes.dex */
public class e extends com.ju.lib.datacommunication.network.http.impl.b {
    @Override // okhttp3.O
    public c0 a(N n) {
        Y yA = n.a();
        M mK = yA.k();
        if (!mK.B().equals("https") || System.currentTimeMillis() / 1000 >= 1531989526) {
            return n.f(yA);
        }
        L lO = mK.o();
        lO.q("http");
        lO.l(80);
        M mA = lO.a();
        X xH = yA.h();
        xH.p(mA);
        Y yB = xH.b();
        com.ju.lib.datacommunication.network.http.i.b.b("HTTP2.HttpsReplaceInterceptor", "newRequest.url() ", yB.k());
        return n.f(yB);
    }

    @Override // com.ju.lib.datacommunication.network.http.impl.b
    protected InterceptorPriority c() {
        return InterceptorPriority.HTTPS_DEGRADE;
    }
}
