package d;

/* JADX INFO: loaded from: classes2.dex */
public final class y {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final byte[] f2138a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f2139b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f2140c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f2141d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f2142e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public y f2143f;
    public y g;

    public y() {
        this.f2138a = new byte[8192];
        this.f2142e = true;
        this.f2141d = false;
    }

    public final y a() {
        y yVar = this.f2143f;
        if (yVar == this) {
            yVar = null;
        }
        y yVar2 = this.g;
        yVar2.f2143f = this.f2143f;
        this.f2143f.g = yVar2;
        this.f2143f = null;
        this.g = null;
        return yVar;
    }

    public final y b() {
        this.f2141d = true;
        return new y(this.f2138a, this.f2139b, this.f2140c, true, false);
    }

    public y(byte[] bArr, int i, int i2, boolean z, boolean z2) {
        this.f2138a = bArr;
        this.f2139b = i;
        this.f2140c = i2;
        this.f2141d = z;
        this.f2142e = z2;
    }

    public final y a(y yVar) {
        yVar.g = this;
        yVar.f2143f = this.f2143f;
        this.f2143f.g = yVar;
        this.f2143f = yVar;
        return yVar;
    }

    public final y a(int i) {
        y yVarA;
        if (i > 0 && i <= this.f2140c - this.f2139b) {
            if (i >= 1024) {
                yVarA = b();
            } else {
                yVarA = z.a();
                System.arraycopy(this.f2138a, this.f2139b, yVarA.f2138a, 0, i);
            }
            yVarA.f2140c = yVarA.f2139b + i;
            this.f2139b += i;
            this.g.a(yVarA);
            return yVarA;
        }
        throw new IllegalArgumentException();
    }

    public final void a(y yVar, int i) {
        if (yVar.f2142e) {
            int i2 = yVar.f2140c;
            if (i2 + i > 8192) {
                if (!yVar.f2141d) {
                    int i3 = yVar.f2139b;
                    if ((i2 + i) - i3 <= 8192) {
                        byte[] bArr = yVar.f2138a;
                        System.arraycopy(bArr, i3, bArr, 0, i2 - i3);
                        yVar.f2140c -= yVar.f2139b;
                        yVar.f2139b = 0;
                    } else {
                        throw new IllegalArgumentException();
                    }
                } else {
                    throw new IllegalArgumentException();
                }
            }
            System.arraycopy(this.f2138a, this.f2139b, yVar.f2138a, yVar.f2140c, i);
            yVar.f2140c += i;
            this.f2139b += i;
            return;
        }
        throw new IllegalArgumentException();
    }
}
