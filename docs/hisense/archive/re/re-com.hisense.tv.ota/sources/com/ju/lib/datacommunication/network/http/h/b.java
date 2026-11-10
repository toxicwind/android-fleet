package com.ju.lib.datacommunication.network.http.h;

import com.ju.lib.datacommunication.network.http.core.l;
import com.ju.lib.datacommunication.network.http.core.s;
import java.util.Map;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: loaded from: classes.dex */
public abstract class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private com.ju.lib.datacommunication.network.http.core.e f140a;
    private final l b = new l();
    String c;

    public b(com.ju.lib.datacommunication.network.http.core.e eVar) {
        this.f140a = eVar;
    }

    public b a(String str, String str2) {
        this.b.j(str, str2);
        return c();
    }

    public s b() {
        e(this.b);
        return this.f140a.d(this.b.k());
    }

    abstract b c();

    public b d(Map map) {
        for (Map.Entry entry : map.entrySet()) {
            this.b.m((String) entry.getKey(), (String) entry.getValue());
        }
        return c();
    }

    void e(l lVar) {
        lVar.t(this.c);
    }

    public b f(com.ju.lib.datacommunication.network.http.core.signature.b bVar) {
        this.b.r(bVar);
        return c();
    }

    public b g(String str) {
        this.c = str;
        return c();
    }
}
