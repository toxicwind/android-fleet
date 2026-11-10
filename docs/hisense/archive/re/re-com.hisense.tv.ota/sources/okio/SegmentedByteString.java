package okio;

import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
final class SegmentedByteString extends ByteString {
    final transient byte[][] e;
    final transient int[] f;

    SegmentedByteString(h hVar, int i) {
        super(null);
        E.b(hVar.b, 0L, i);
        y yVar = hVar.f268a;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        while (i3 < i) {
            int i5 = yVar.c;
            int i6 = yVar.b;
            if (i5 == i6) {
                throw new AssertionError("s.limit == s.pos");
            }
            i3 += i5 - i6;
            i4++;
            yVar = yVar.f;
        }
        this.e = new byte[i4][];
        this.f = new int[i4 * 2];
        y yVar2 = hVar.f268a;
        int i7 = 0;
        while (i2 < i) {
            this.e[i7] = yVar2.f280a;
            i2 += yVar2.c - yVar2.b;
            if (i2 > i) {
                i2 = i;
            }
            int[] iArr = this.f;
            iArr[i7] = i2;
            iArr[this.e.length + i7] = yVar2.b;
            yVar2.d = true;
            i7++;
            yVar2 = yVar2.f;
        }
    }

    private Object writeReplace() {
        return z();
    }

    private int y(int i) {
        int iBinarySearch = Arrays.binarySearch(this.f, 0, this.e.length, i + 1);
        return iBinarySearch >= 0 ? iBinarySearch : ~iBinarySearch;
    }

    private ByteString z() {
        return new ByteString(v());
    }

    @Override // okio.ByteString
    public String a() {
        return z().a();
    }

    @Override // okio.ByteString
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof ByteString) {
            ByteString byteString = (ByteString) obj;
            if (byteString.r() == r() && m(0, byteString, 0, r())) {
                return true;
            }
        }
        return false;
    }

    @Override // okio.ByteString
    public int hashCode() {
        int i = this.f260a;
        if (i != 0) {
            return i;
        }
        int length = this.e.length;
        int i2 = 0;
        int i3 = 1;
        int i4 = 0;
        while (i2 < length) {
            byte[] bArr = this.e[i2];
            int[] iArr = this.f;
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
        this.f260a = i3;
        return i3;
    }

    @Override // okio.ByteString
    public byte i(int i) {
        E.b(this.f[this.e.length - 1], i, 1L);
        int iY = y(i);
        int i2 = iY == 0 ? 0 : this.f[iY - 1];
        int[] iArr = this.f;
        byte[][] bArr = this.e;
        return bArr[iY][(i - i2) + iArr[bArr.length + iY]];
    }

    @Override // okio.ByteString
    public String j() {
        return z().j();
    }

    @Override // okio.ByteString
    public ByteString k() {
        return z().k();
    }

    @Override // okio.ByteString
    public boolean m(int i, ByteString byteString, int i2, int i3) {
        if (i < 0 || i > r() - i3) {
            return false;
        }
        int iY = y(i);
        while (i3 > 0) {
            int i4 = iY == 0 ? 0 : this.f[iY - 1];
            int iMin = Math.min(i3, ((this.f[iY] - i4) + i4) - i);
            int[] iArr = this.f;
            byte[][] bArr = this.e;
            if (!byteString.n(i2, bArr[iY], (i - i4) + iArr[bArr.length + iY], iMin)) {
                return false;
            }
            i += iMin;
            i2 += iMin;
            i3 -= iMin;
            iY++;
        }
        return true;
    }

    @Override // okio.ByteString
    public boolean n(int i, byte[] bArr, int i2, int i3) {
        if (i < 0 || i > r() - i3 || i2 < 0 || i2 > bArr.length - i3) {
            return false;
        }
        int iY = y(i);
        while (i3 > 0) {
            int i4 = iY == 0 ? 0 : this.f[iY - 1];
            int iMin = Math.min(i3, ((this.f[iY] - i4) + i4) - i);
            int[] iArr = this.f;
            byte[][] bArr2 = this.e;
            if (!E.a(bArr2[iY], (i - i4) + iArr[bArr2.length + iY], bArr, i2, iMin)) {
                return false;
            }
            i += iMin;
            i2 += iMin;
            i3 -= iMin;
            iY++;
        }
        return true;
    }

    @Override // okio.ByteString
    public ByteString p() {
        return z().p();
    }

    @Override // okio.ByteString
    public ByteString q() {
        return z().q();
    }

    @Override // okio.ByteString
    public int r() {
        return this.f[this.e.length - 1];
    }

    @Override // okio.ByteString
    public ByteString t(int i, int i2) {
        return z().t(i, i2);
    }

    @Override // okio.ByteString
    public String toString() {
        return z().toString();
    }

    @Override // okio.ByteString
    public ByteString u() {
        return z().u();
    }

    @Override // okio.ByteString
    public byte[] v() {
        int[] iArr = this.f;
        byte[][] bArr = this.e;
        byte[] bArr2 = new byte[iArr[bArr.length - 1]];
        int length = bArr.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            int[] iArr2 = this.f;
            int i3 = iArr2[length + i];
            int i4 = iArr2[i];
            System.arraycopy(this.e[i], i3, bArr2, i2, i4 - i2);
            i++;
            i2 = i4;
        }
        return bArr2;
    }

    @Override // okio.ByteString
    public String w() {
        return z().w();
    }

    @Override // okio.ByteString
    void x(h hVar) {
        int length = this.e.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            int[] iArr = this.f;
            int i3 = iArr[length + i];
            int i4 = iArr[i];
            y yVar = new y(this.e[i], i3, (i3 + i4) - i2, true, false);
            y yVar2 = hVar.f268a;
            if (yVar2 == null) {
                yVar.g = yVar;
                yVar.f = yVar;
                hVar.f268a = yVar;
            } else {
                yVar2.g.c(yVar);
            }
            i++;
            i2 = i4;
        }
        hVar.b += (long) i2;
    }
}
