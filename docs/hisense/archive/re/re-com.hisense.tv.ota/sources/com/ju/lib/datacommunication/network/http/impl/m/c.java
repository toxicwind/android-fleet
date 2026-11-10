package com.ju.lib.datacommunication.network.http.impl.m;

import com.ju.lib.datacommunication.network.http.g.l;
import com.ju.lib.datacommunication.network.http.impl.InterceptorPriority;
import java.net.URL;
import okhttp3.L;
import okhttp3.M;
import okhttp3.N;
import okhttp3.X;
import okhttp3.Y;
import okhttp3.c0;

/* JADX INFO: loaded from: classes.dex */
public class c extends com.ju.lib.datacommunication.network.http.impl.b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final l f152a;

    public c(l lVar) {
        this.f152a = lVar;
    }

    @Override // okhttp3.O
    public c0 a(N n) {
        String strB;
        Y yA = n.a();
        M mK = yA.k();
        if (!com.ju.lib.datacommunication.network.http.impl.b.b(mK.l())) {
            try {
                String str = mK.B() + "://" + mK.l();
                if (this.f152a != null && (strB = this.f152a.b(str)) != null && !strB.isEmpty() && !str.equals(strB)) {
                    if (!strB.contains("http://") && !strB.contains("https://")) {
                        strB = mK.B() + "://" + strB;
                    }
                    URL url = new URL(strB);
                    L lO = mK.o();
                    lO.q(url.getProtocol());
                    lO.e(url.getHost());
                    if (url.getPort() != -1) {
                        lO.l(url.getPort());
                    }
                    X xH = yA.h();
                    xH.p(lO.a());
                    xH.f("Host", url.getHost());
                    yA = xH.b();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return n.f(yA);
    }

    @Override // com.ju.lib.datacommunication.network.http.impl.b
    protected InterceptorPriority c() {
        return InterceptorPriority.DYNAMIC_DOMAIN_SELECT;
    }
}
