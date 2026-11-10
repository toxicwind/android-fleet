package c.a.f;

import c.a.U;
import c.a.V;
import c.a.ia;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public final class c extends V {
    @Override // c.a.U.b
    public U a(U.c cVar) {
        return new b(cVar);
    }

    @Override // c.a.V
    public String a() {
        return "round_robin";
    }

    @Override // c.a.V
    public int b() {
        return 5;
    }

    @Override // c.a.V
    public boolean c() {
        return true;
    }

    @Override // c.a.V
    public ia.b a(Map<String, ?> map) {
        return new ia.b("no service config");
    }
}
