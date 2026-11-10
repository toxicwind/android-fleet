package d;

import java.util.Arrays;

/* JADX INFO: loaded from: classes2.dex */
public final class A extends j {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final transient byte[][] f2091f;
    public final transient int[] g;

    public A(g gVar, int i) {
        super(null);
        F.a(gVar.f2102c, 0L, i);
        int i2 = 0;
        y yVar = gVar.f2101b;
        int i3 = 0;
        int i4 = 0;
        while (i3 < i) {
            int i5 = yVar.f2140c;
            int i6 = yVar.f2139b;
            if (i5 == i6) {
                throw new AssertionError("s.limit == s.pos");
            }
            i3 += i5 - i6;
            i4++;
            yVar = yVar.f2143f;
        }
        this.f2091f = new byte[i4][];
        this.g = new int[i4 * 2];
        y yVar2 = gVar.f2101b;
        int i7 = 0;
        while (i2 < i) {
            this.f2091f[i7] = yVar2.f2138a;
            i2 += yVar2.f2140c - yVar2.f2139b;
            if (i2 > i) {
                i2 = i;
            }
            int[] iArr = this.g;
            iArr[i7] = i2;
            iArr[this.f2091f.length + i7] = yVar2.f2139b;
            yVar2.f2141d = true;
            i7++;
            yVar2 = yVar2.f2143f;
        }
    }

    @Override // d.j
    public String a() {
        return k().a();
    }

    @Override // d.j
    public String b() {
        return k().b();
    }

    @Override // d.j
    public byte[] c() {
        return i();
    }

    @Override // d.j
    public j d() {
        return k().d();
    }

    @Override // d.j
    public j e() {
        return k().e();
    }

    @Override // d.j
    public boolean equals(Object obj) {
        boolean z;
        if (obj == this) {
            return true;
        }
        if (obj instanceof j) {
            j jVar = (j) obj;
            if (jVar.g() == g()) {
                int iG = g();
                if (g() - iG >= 0) {
                    int iB = b(0);
                    int i = 0;
                    int i2 = 0;
                    while (true) {
                        if (iG <= 0) {
                            z = true;
                            break;
                        }
                        int i3 = iB == 0 ? 0 : this.g[iB - 1];
                        int iMin = Math.min(iG, ((this.g[iB] - i3) + i3) - i);
                        int[] iArr = this.g;
                        byte[][] bArr = this.f2091f;
                        if (!jVar.a(i2, bArr[iB], (i - i3) + iArr[bArr.length + iB], iMin)) {
                            z = false;
                            break;
                        }
                        i += iMin;
                        i2 += iMin;
                        iG -= iMin;
                        iB++;
                    }
                } else {
                    z = false;
                    break;
                }
                if (z) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override // d.j
    public j f() {
        return k().f();
    }

    @Override // d.j
    public int g() {
        return this.g[this.f2091f.length - 1];
    }

    @Override // d.j
    public j h() {
        return k().h();
    }

    @Override // d.j
    public int hashCode() {
        int i = this.f2112d;
        if (i != 0) {
            return i;
        }
        int length = this.f2091f.length;
        int i2 = 0;
        int i3 = 1;
        int i4 = 0;
        while (i2 < length) {
            byte[] bArr = this.f2091f[i2];
            int[] iArr = this.g;
            int i5 = iArr[length + i2];
            int i6 = iArr[i2];
            int i7 = (i6 - i4) + i5;
            while (i5 < i7) {
                i3 = (i3 * 31) + bArr[i5];
                i5++;
            }
            i2++;
            i4 = i6;
        }
        this.f2112d = i3;
        return i3;
    }

    @Override // d.j
    public byte[] i() {
        int[] iArr = this.g;
        byte[][] bArr = this.f2091f;
        byte[] bArr2 = new byte[iArr[bArr.length - 1]];
        int length = bArr.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            int[] iArr2 = this.g;
            int i3 = iArr2[length + i];
            int i4 = iArr2[i];
            System.arraycopy(this.f2091f[i], i3, bArr2, i2, i4 - i2);
            i++;
            i2 = i4;
        }
        return bArr2;
    }

    @Override // d.j
    public String j() {
        return k().j();
    }

    public final j k() {
        return new j(i());
    }

    @Override // d.j
    public String toString() {
        return k().toString();
    }

    @Override // d.j
    public j a(int i, int i2) {
        return k().a(i, i2);
    }

    public final int b(int i) {
        int iBinarySearch = Arrays.binarySearch(this.g, 0, this.f2091f.length, i + 1);
        return iBinarySearch >= 0 ? iBinarySearch : ~iBinarySearch;
    }

    @Override // d.j
    public byte a(int i) {
        F.a(this.g[this.f2091f.length - 1], i, 1L);
        int iB = b(i);
        int i2 = iB == 0 ? 0 : this.g[iB - 1];
        int[] iArr = this.g;
        byte[][] bArr = this.f2091f;
        return bArr[iB][(i - i2) + iArr[bArr.length + iB]];
    }

    @Override // d.j
    public void a(g gVar) {
        int length = this.f2091f.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            int[] iArr = this.g;
            int i3 = iArr[length + i];
            int i4 = iArr[i];
            y yVar = new y(this.f2091f[i], i3, (i3 + i4) - i2, true, false);
            y yVar2 = gVar.f2101b;
            if (yVar2 == null) {
                yVar.g = yVar;
                yVar.f2143f = yVar;
                gVar.f2101b = yVar;
            } else {
                yVar2.g.a(yVar);
            }
            i++;
            i2 = i4;
        }
        gVar.f2102c += (long) i2;
    }

    @Override // d.j
    public boolean a(int i, j jVar, int i2, int i3) {
        if (i < 0 || i > g() - i3) {
            return false;
        }
        int iB = b(i);
        while (i3 > 0) {
            int i4 = iB == 0 ? 0 : this.g[iB - 1];
            int iMin = Math.min(i3, ((this.g[iB] - i4) + i4) - i);
            int[] iArr = this.g;
            byte[][] bArr = this.f2091f;
            if (!jVar.a(i2, bArr[iB], (i - i4) + iArr[bArr.length + iB], iMin)) {
                return false;
            }
            i += iMin;
            i2 += iMin;
            i3 -= iMin;
            iB++;
        }
        return true;
    }

    @Override // d.j
    public boolean a(int i, byte[] bArr, int i2, int i3) {
        if (i < 0 || i > g() - i3 || i2 < 0 || i2 > bArr.length - i3) {
            return false;
        }
        int iB = b(i);
        while (i3 > 0) {
            int i4 = iB == 0 ? 0 : this.g[iB - 1];
            int iMin = Math.min(i3, ((this.g[iB] - i4) + i4) - i);
            int[] iArr = this.g;
            byte[][] bArr2 = this.f2091f;
            if (!F.a(bArr2[iB], (i - i4) + iArr[bArr2.length + iB], bArr, i2, iMin)) {
                return false;
            }
            i += iMin;
            i2 += iMin;
            i3 -= iMin;
            iB++;
        }
        return true;
    }
}
