package com.ju.lib.datacommunication.network.http.h;

import com.ju.lib.datacommunication.network.http.core.l;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: loaded from: classes.dex */
public abstract class e extends b {
    private Map d;

    public e(com.ju.lib.datacommunication.network.http.core.e eVar) {
        super(eVar);
        this.d = new HashMap();
    }

    @Override // com.ju.lib.datacommunication.network.http.h.b
    void e(l lVar) {
        lVar.l();
        lVar.u(this.c, this.d);
    }

    public e h(String str, String str2) {
        this.d.put(str, str2);
        c();
        return this;
    }

    public e i(Map map) {
        this.d.putAll(map);
        c();
        return this;
    }
}
