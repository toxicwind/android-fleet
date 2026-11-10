package b.c.a.a.a;

import b.d.a.a.j;

/* JADX INFO: loaded from: classes.dex */
public class f extends b.c.a.a<Long> {
    @Override // b.c.a.a
    public Long parse(b.d.a.a.g gVar) {
        if (((b.d.a.a.a.c) gVar).f70b == j.VALUE_NULL) {
            return null;
        }
        return Long.valueOf(gVar.r());
    }

    @Override // b.c.a.a
    public void parseField(Long l, String str, b.d.a.a.g gVar) {
    }

    @Override // b.c.a.a
    public void serialize(Long l, b.d.a.a.d dVar, boolean z) {
        dVar.g(l.longValue());
    }
}
