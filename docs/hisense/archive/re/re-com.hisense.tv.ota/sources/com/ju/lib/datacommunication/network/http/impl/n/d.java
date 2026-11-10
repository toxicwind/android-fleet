package com.ju.lib.datacommunication.network.http.impl.n;

import android.text.TextUtils;
import com.ju.lib.datacommunication.network.http.impl.InterceptorPriority;
import java.net.URL;
import okhttp3.N;
import okhttp3.b0;
import okhttp3.c0;

/* JADX INFO: loaded from: classes.dex */
public class d extends com.ju.lib.datacommunication.network.http.impl.a {
    public d(String str) {
        super(str);
    }

    private c0 i(c0 c0Var) {
        if (c0Var == null) {
            return null;
        }
        int iP = c0Var.p();
        String strG = c0Var.W().g();
        if (iP != 307 && iP != 308) {
            switch (iP) {
                case 300:
                case 301:
                case 302:
                case 303:
                    break;
                default:
                    return c0Var;
            }
        } else if (!strG.equals("GET") && !strG.equals("HEAD")) {
            return c0Var;
        }
        String strM = c0Var.M("Location");
        if (TextUtils.isEmpty(strM)) {
            return c0Var;
        }
        try {
            URL url = new URL(strM);
            String strReplaceFirst = strM.replaceFirst(url.getHost(), d(url.getHost()));
            com.ju.lib.datacommunication.network.http.i.b.d(e(), "handleRedirectHost(), location = ", strM, " , newLocation = ", strReplaceFirst);
            b0 b0VarS = c0Var.S();
            b0VarS.i("Location", strReplaceFirst);
            return b0VarS.c();
        } catch (Exception e) {
            e.printStackTrace();
            return c0Var;
        }
    }

    @Override // com.ju.lib.datacommunication.network.http.impl.b
    protected InterceptorPriority c() {
        return InterceptorPriority.NORMAL;
    }

    @Override // com.ju.lib.datacommunication.network.http.impl.a
    protected String e() {
        return "HTTP2.302Interceptor";
    }

    @Override // com.ju.lib.datacommunication.network.http.impl.a
    protected c0 f(N n) {
        if (com.ju.lib.datacommunication.network.http.i.b.e()) {
            com.ju.lib.datacommunication.network.http.i.b.d(e(), "interceptCustomImp(), request = ", n.a());
        }
        c0 c0VarF = n.f(n.a());
        c0 c0VarI = i(c0VarF);
        if (com.ju.lib.datacommunication.network.http.i.b.e()) {
            com.ju.lib.datacommunication.network.http.i.b.d(e(), "interceptCustomImp(), originalResponse = ", c0VarF.toString(), " ,-------- header = ", c0VarF.O().toString());
            com.ju.lib.datacommunication.network.http.i.b.d(e(), "interceptCustomImp(), response = ", c0VarI.toString(), " ,-------- header = ", c0VarI.O().toString());
        }
        return c0VarI;
    }
}
