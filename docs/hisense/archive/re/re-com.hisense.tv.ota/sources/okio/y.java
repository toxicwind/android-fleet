package okio;

/* JADX INFO: loaded from: classes.dex */
final class y {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final byte[] f280a;
    int b;
    int c;
    boolean d;
    boolean e;
    y f;
    y g;

    y() {
        this.f280a = new byte[8192];
        this.e = true;
        this.d = false;
    }

    y(byte[] bArr, int i, int i2, boolean z, boolean z2) {
        this.f280a = bArr;
        this.b = i;
        this.c = i2;
        this.d = z;
        this.e = z2;
    }

    public final void a() {
        y yVar = this.g;
        if (yVar == this) {
            throw new IllegalStateException();
        }
        if (yVar.e) {
            int i = this.c - this.b;
            if (i > (8192 - yVar.c) + (yVar.d ? 0 : yVar.b)) {
                return;
            }
            f(this.g, i);
            b();
            z.a(this);
        }
    }

    public final y b() {
        y yVar = this.f;
        if (yVar == this) {
            yVar = null;
        }
        y yVar2 = this.g;
        yVar2.f = this.f;
        this.f.g = yVar2;
        this.f = null;
        this.g = null;
        return yVar;
    }

    public final y c(y yVar) {
        yVar.g = this;
        yVar.f = this.f;
        this.f.g = yVar;
        this.f = yVar;
        return yVar;
    }

    final y d() {
        this.d = true;
        return new y(this.f280a, this.b, this.c, true, false);
    }

    public final y e(int i) {
        y yVarB;
        if (i <= 0 || i > this.c - this.b) {
            throw new IllegalArgumentException();
        }
        if (i >= 1024) {
            yVarB = d();
        } else {
            yVarB = z.b();
            System.arraycopy(this.f280a, this.b, yVarB.f280a, 0, i);
        }
        yVarB.c = yVarB.b + i;
        this.b += i;
        this.g.c(yVarB);
        return yVarB;
    }

    public final void f(y yVar, int i) {
        if (!yVar.e) {
            throw new IllegalArgumentException();
        }
        int i2 = yVar.c;
        if (i2 + i > 8192) {
            if (yVar.d) {
                throw new IllegalArgumentException();
            }
            int i3 = yVar.b;
            if ((i2 + i) - i3 > 8192) {
                throw new IllegalArgumentException();
            }
            byte[] bArr = yVar.f280a;
            System.arraycopy(bArr, i3, bArr, 0, i2 - i3);
            yVar.c -= yVar.b;
            yVar.b = 0;
        }
        System.arraycopy(this.f280a, this.b, yVar.f280a, yVar.c, i);
        yVar.c += i;
        this.b += i;
    }
}
