package c.a.b;

/* JADX INFO: renamed from: c.a.b.qb, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public class C0169qb implements c.a.P.a<Integer> {
    @Override // c.a.C0219ea.h
    public byte[] a(Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // c.a.C0219ea.h
    public Object a(byte[] bArr) {
        if (bArr.length >= 3) {
            return Integer.valueOf((bArr[2] - 48) + ((bArr[1] - 48) * 10) + ((bArr[0] - 48) * 100));
        }
        StringBuilder sbA = b.a.a.a.a.a("Malformed status code ");
        sbA.append(new String(bArr, c.a.P.f840a));
        throw new NumberFormatException(sbA.toString());
    }
}
