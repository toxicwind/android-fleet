package b.c.a.a.a;

import b.d.a.a.j;

/* JADX INFO: loaded from: classes.dex */
public class c extends b.c.a.a<Float> {
    @Override // b.c.a.a
    public Float parse(b.d.a.a.g gVar) {
        if (((b.d.a.a.a.c) gVar).f70b == j.VALUE_NULL) {
            return null;
        }
        return Float.valueOf((float) ((b.d.a.a.a.b) gVar).q());
    }

    @Override // b.c.a.a
    public void parseField(Float f2, String str, b.d.a.a.g gVar) {
    }

    @Override // b.c.a.a
    public void serialize(Float f2, b.d.a.a.d dVar, boolean z) {
        dVar.a(f2.floatValue());
    }
}
