package b.c.a.a.a;

import b.d.a.a.j;

/* JADX INFO: loaded from: classes.dex */
public class b extends b.c.a.a<Double> {
    @Override // b.c.a.a
    public Double parse(b.d.a.a.g gVar) {
        if (((b.d.a.a.a.c) gVar).f70b == j.VALUE_NULL) {
            return null;
        }
        return Double.valueOf(gVar.q());
    }

    @Override // b.c.a.a
    public void parseField(Double d2, String str, b.d.a.a.g gVar) {
    }

    @Override // b.c.a.a
    public void serialize(Double d2, b.d.a.a.d dVar, boolean z) {
        dVar.a(d2.doubleValue());
    }
}
