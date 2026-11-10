package c.a.c;

import c.a.b.Qd;
import java.util.List;
import java.util.logging.Logger;

/* JADX INFO: loaded from: classes2.dex */
public class A {
    static {
        Logger.getLogger(A.class.getName());
    }

    public static byte[][] a(List<c.a.c.a.a.d> list) {
        byte[][] bArr = new byte[list.size() * 2][];
        int i = 0;
        for (c.a.c.a.a.d dVar : list) {
            int i2 = i + 1;
            bArr[i] = dVar.f1571f.i();
            i = i2 + 1;
            bArr[i2] = dVar.g.i();
        }
        return Qd.a(bArr);
    }
}
