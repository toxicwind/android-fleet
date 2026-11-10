package com.ju.lib.datacommunication.network.http.h;

import com.ju.lib.datacommunication.network.http.core.l;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class d extends c {
    private com.ju.lib.datacommunication.network.http.core.C.b d;

    public d(com.ju.lib.datacommunication.network.http.core.e eVar) {
        super(eVar);
        this.d = new com.ju.lib.datacommunication.network.http.core.C.b();
    }

    @Override // com.ju.lib.datacommunication.network.http.h.b
    /* bridge */ /* synthetic */ b c() {
        h();
        return this;
    }

    @Override // com.ju.lib.datacommunication.network.http.h.b
    void e(l lVar) {
        super.e(lVar);
        lVar.p(this.d.d());
    }

    d h() {
        return this;
    }

    public d i(Map map) {
        this.d.c(map);
        return this;
    }
}
