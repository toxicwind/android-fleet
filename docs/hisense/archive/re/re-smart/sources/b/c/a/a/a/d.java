package b.c.a.a.a;

import b.d.a.a.j;

/* JADX INFO: loaded from: classes.dex */
public class d extends b.c.a.a<Integer> {
    @Override // b.c.a.a
    public Integer parse(b.d.a.a.g gVar) throws b.d.a.a.f {
        if (((b.d.a.a.a.c) gVar).f70b == j.VALUE_NULL) {
            return null;
        }
        b.d.a.a.a.b bVar = (b.d.a.a.a.b) gVar;
        int i = bVar.x;
        if ((i & 1) == 0) {
            if (i == 0) {
                bVar.d(1);
            }
            int i2 = bVar.x;
            if ((i2 & 1) == 0) {
                if ((i2 & 2) != 0) {
                    long j = bVar.z;
                    int i3 = (int) j;
                    if (i3 != j) {
                        StringBuilder sbA = b.a.a.a.a.a("Numeric value (");
                        sbA.append(bVar.s());
                        sbA.append(") out of range of int");
                        throw bVar.b(sbA.toString());
                    }
                    bVar.y = i3;
                } else if ((i2 & 4) != 0) {
                    if (b.d.a.a.a.b.f66c.compareTo(bVar.B) > 0 || b.d.a.a.a.b.f67d.compareTo(bVar.B) < 0) {
                        bVar.D();
                        throw null;
                    }
                    bVar.y = bVar.B.intValue();
                } else if ((i2 & 8) != 0) {
                    double d2 = bVar.A;
                    if (d2 < -2.147483648E9d || d2 > 2.147483647E9d) {
                        bVar.D();
                        throw null;
                    }
                    bVar.y = (int) d2;
                } else {
                    if ((i2 & 16) == 0) {
                        b.d.a.a.e.g.a();
                        throw null;
                    }
                    if (b.d.a.a.a.b.i.compareTo(bVar.C) > 0 || b.d.a.a.a.b.j.compareTo(bVar.C) < 0) {
                        bVar.D();
                        throw null;
                    }
                    bVar.y = bVar.C.intValue();
                }
                bVar.x |= 1;
            }
        }
        return Integer.valueOf(bVar.y);
    }

    @Override // b.c.a.a
    public void parseField(Integer num, String str, b.d.a.a.g gVar) {
    }

    @Override // b.c.a.a
    public void serialize(Integer num, b.d.a.a.d dVar, boolean z) {
        dVar.b(num.intValue());
    }
}
