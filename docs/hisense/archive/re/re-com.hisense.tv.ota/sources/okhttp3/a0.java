package okhttp3;

/* JADX INFO: loaded from: classes.dex */
public abstract class a0 {
    public static a0 c(P p, byte[] bArr) {
        return d(p, bArr, 0, bArr.length);
    }

    public static a0 d(P p, byte[] bArr, int i, int i2) {
        if (bArr == null) {
            throw new NullPointerException("content == null");
        }
        okhttp3.g0.e.f(bArr.length, i, i2);
        return new Z(p, i2, bArr, i);
    }

    public abstract long a();

    public abstract P b();

    public abstract void e(okio.i iVar);
}
