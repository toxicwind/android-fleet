package com.ju.lib.datacommunication.network.http.impl.n;

import com.ju.lib.datacommunication.network.http.core.m;
import com.ju.lib.datacommunication.network.http.impl.InterceptorPriority;
import com.ju.lib.datacommunication.network.http.impl.k;
import okhttp3.N;
import okhttp3.X;
import okhttp3.Y;
import okhttp3.c0;

/* JADX INFO: loaded from: classes.dex */
public class a extends com.ju.lib.datacommunication.network.http.impl.b {
    @Override // okhttp3.O
    public c0 a(N n) {
        Y yA = n.a();
        Object objI = yA.i();
        if (objI instanceof k) {
            m mVarF = ((k) objI).f150a.f();
            if (mVarF == null || !yA.k().l().equals(mVarF.b)) {
                String strC = yA.c("Host");
                if (!com.ju.lib.datacommunication.network.http.impl.b.b(yA.k().l()) && !yA.k().l().equals(strC)) {
                    X xH = yA.h();
                    xH.f("Host", yA.k().l());
                    yA = xH.b();
                }
            } else {
                X xH2 = yA.h();
                xH2.f("Host", mVarF.f91a);
                yA = xH2.b();
            }
        }
        return n.f(yA);
    }

    @Override // com.ju.lib.datacommunication.network.http.impl.b
    protected InterceptorPriority c() {
        return InterceptorPriority.NORMAL;
    }
}
