package b.c.a.a.a;

import b.d.a.a.j;

/* JADX INFO: loaded from: classes.dex */
public class a extends b.c.a.a<Boolean> {
    @Override // b.c.a.a
    public Boolean parse(b.d.a.a.g gVar) throws b.d.a.a.f {
        boolean z;
        j jVar = ((b.d.a.a.a.c) gVar).f70b;
        if (jVar == j.VALUE_NULL) {
            return null;
        }
        if (jVar == j.VALUE_TRUE) {
            z = true;
        } else {
            if (jVar != j.VALUE_FALSE) {
                throw new b.d.a.a.f(b.a.a.a.a.a("Current token (", jVar, ") not of boolean type"), gVar.p());
            }
            z = false;
        }
        return Boolean.valueOf(z);
    }

    @Override // b.c.a.a
    public void parseField(Boolean bool, String str, b.d.a.a.g gVar) {
    }

    @Override // b.c.a.a
    public void serialize(Boolean bool, b.d.a.a.d dVar, boolean z) {
        dVar.a(bool.booleanValue());
    }
}
