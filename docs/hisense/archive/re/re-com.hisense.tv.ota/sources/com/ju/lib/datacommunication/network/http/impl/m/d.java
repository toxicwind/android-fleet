package com.ju.lib.datacommunication.network.http.impl.m;

import com.ju.lib.datacommunication.network.http.impl.InterceptorPriority;
import okhttp3.L;
import okhttp3.M;
import okhttp3.N;
import okhttp3.X;
import okhttp3.Y;
import okhttp3.c0;

/* JADX INFO: loaded from: classes.dex */
public class d extends com.ju.lib.datacommunication.network.http.impl.a {
    public d(String str) {
        super(str);
    }

    @Override // com.ju.lib.datacommunication.network.http.impl.b
    protected InterceptorPriority c() {
        return InterceptorPriority.THIRD_PARTY_DOMAIN_ADAPT;
    }

    @Override // com.ju.lib.datacommunication.network.http.impl.a
    protected String e() {
        return "HTTP2.HostReplaceInterceptor";
    }

    @Override // com.ju.lib.datacommunication.network.http.impl.a
    protected c0 f(N n) {
        Y yA = n.a();
        try {
            M mK = yA.k();
            String strL = mK.l();
            if (!g(strL) && h(strL)) {
                L lO = mK.o();
                lO.e(d(strL));
                M mA = lO.a();
                X xH = yA.h();
                xH.p(mA);
                xH.f("Host", mA.l());
                yA = xH.b();
                com.ju.lib.datacommunication.network.http.i.b.d(e(), "intercept(), url = ", mA);
                return n.f(yA);
            }
            return n.f(yA);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
