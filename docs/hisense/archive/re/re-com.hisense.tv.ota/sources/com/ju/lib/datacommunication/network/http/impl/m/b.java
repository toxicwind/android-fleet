package com.ju.lib.datacommunication.network.http.impl.m;

import android.text.TextUtils;
import com.ju.lib.datacommunication.network.http.impl.InterceptorPriority;
import okhttp3.N;
import okhttp3.X;
import okhttp3.Y;
import okhttp3.a0;
import okhttp3.c0;

/* JADX INFO: loaded from: classes.dex */
public class b extends com.ju.lib.datacommunication.network.http.impl.b {
    @Override // okhttp3.O
    public c0 a(N n) {
        Y yA = n.a();
        String strC = yA.c("Content-Length");
        a0 a0VarA = yA.a();
        if (a0VarA != null && TextUtils.isEmpty(strC)) {
            long jA = a0VarA.a();
            com.ju.lib.datacommunication.network.http.i.b.b("HTTP2.ContentLengthInterceptor", "body.length = " + jA);
            X xH = yA.h();
            xH.a("Content-Length", String.valueOf(jA));
            yA = xH.b();
        }
        return n.f(yA);
    }

    @Override // com.ju.lib.datacommunication.network.http.impl.b
    protected InterceptorPriority c() {
        return InterceptorPriority.SIGNATURE;
    }
}
