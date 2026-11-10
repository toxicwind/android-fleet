package com.ju.lib.datacommunication.network.http.impl;

import com.ju.lib.datacommunication.network.http.core.v;
import com.ju.lib.datacommunication.network.http.core.y;
import java.net.UnknownHostException;
import java.util.List;
import okhttp3.D;

/* JADX INFO: loaded from: classes.dex */
class f implements D {
    final /* synthetic */ y b;

    f(l lVar, y yVar) {
        this.b = yVar;
    }

    @Override // okhttp3.D
    public List a(String str) throws UnknownHostException {
        v vVarB = this.b.b(str);
        if (vVarB == null) {
            throw new UnknownHostException(str);
        }
        List listF = vVarB.f();
        if (listF == null || listF.isEmpty()) {
            throw new UnknownHostException(str);
        }
        List listH = vVarB.h();
        if (listH != null && !listH.isEmpty()) {
            listF.addAll(listH);
        }
        com.ju.lib.datacommunication.network.http.i.b.b("HTTP2.Stack", "inetAddresseslist=", listF);
        return listF;
    }
}
