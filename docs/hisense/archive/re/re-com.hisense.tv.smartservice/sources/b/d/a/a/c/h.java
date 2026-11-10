package b.d.a.a.c;

import androidx.core.view.InputDeviceCompat;
import b.d.a.a.j;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.BitSet;

/* JADX INFO: loaded from: classes.dex */
public class h extends b.d.a.a.a.b {
    public static final int[] F = b.d.a.a.b.a.f85d;
    public static final int[] G = b.d.a.a.b.a.f84c;
    public final b.d.a.a.d.a H;
    public int[] I;
    public boolean J;
    public int K;
    public InputStream L;
    public byte[] M;
    public boolean N;

    public h(b.d.a.a.b.c cVar, int i, InputStream inputStream, b.d.a.a.d.a aVar, byte[] bArr, int i2, int i3, boolean z) {
        super(cVar, i);
        this.I = new int[16];
        this.J = false;
        this.L = inputStream;
        this.H = aVar;
        this.M = bArr;
        this.m = i2;
        this.n = i3;
        this.q = i2;
        this.o = -i2;
        this.N = z;
    }

    public static final int c(int i, int i2) {
        return i2 == 4 ? i : i | ((-1) << (i2 << 3));
    }

    @Override // b.d.a.a.a.b
    public void A() {
        byte[] bArr;
        super.A();
        b.d.a.a.d.a aVar = this.H;
        b.d.a.a.d.a aVar2 = aVar.f151a;
        if (aVar2 != null && (!aVar.o)) {
            aVar2.a(new b.d.a.a.d.a.b(aVar));
            aVar.o = true;
            aVar.p = true;
            aVar.q = true;
        }
        if (!this.N || (bArr = this.M) == null) {
            return;
        }
        this.M = b.d.a.a.e.b.f198a;
        this.k.a(bArr);
    }

    @Override // b.d.a.a.a.b
    public final boolean B() throws IOException {
        byte[] bArr;
        int length;
        long j = this.o;
        int i = this.n;
        this.o = j + ((long) i);
        this.q -= i;
        InputStream inputStream = this.L;
        if (inputStream == null || (length = (bArr = this.M).length) == 0) {
            return false;
        }
        int i2 = inputStream.read(bArr, 0, length);
        if (i2 > 0) {
            this.m = 0;
            this.n = i2;
            return true;
        }
        if (this.L != null) {
            if (this.k.f90c || a(b.d.a.a.g.a.AUTO_CLOSE_SOURCE)) {
                this.L.close();
            }
            this.L = null;
        }
        if (i2 == 0) {
            throw new IOException(b.a.a.a.a.a(b.a.a.a.a.a("InputStream.read() returned 0 characters when trying to read "), this.M.length, " bytes"));
        }
        return false;
    }

    public char F() throws b.d.a.a.f {
        if (this.m >= this.n && !B()) {
            c(" in character escape sequence");
            throw null;
        }
        byte[] bArr = this.M;
        int i = this.m;
        this.m = i + 1;
        byte b2 = bArr[i];
        if (b2 == 34 || b2 == 47 || b2 == 92) {
            return (char) b2;
        }
        if (b2 == 98) {
            return '\b';
        }
        if (b2 == 102) {
            return '\f';
        }
        if (b2 == 110) {
            return '\n';
        }
        if (b2 == 114) {
            return '\r';
        }
        if (b2 == 116) {
            return '\t';
        }
        if (b2 != 117) {
            char cE = (char) e(b2);
            a(cE);
            return cE;
        }
        int i2 = 0;
        for (int i3 = 0; i3 < 4; i3++) {
            if (this.m >= this.n && !B()) {
                c(" in character escape sequence");
                throw null;
            }
            byte[] bArr2 = this.M;
            int i4 = this.m;
            this.m = i4 + 1;
            byte b3 = bArr2[i4];
            int iA = b.d.a.a.b.a.a(b3);
            if (iA < 0) {
                a(b3, "expected a hex-digit for character escape sequence");
                throw null;
            }
            i2 = (i2 << 4) | iA;
        }
        return (char) i2;
    }

    public j G() throws b.d.a.a.f {
        char[] cArrD = this.v.d();
        cArrD[0] = '-';
        if (this.m >= this.n) {
            C();
        }
        byte[] bArr = this.M;
        int i = this.m;
        this.m = i + 1;
        int iN = bArr[i] & 255;
        if (iN < 48 || iN > 57) {
            return a(iN, true);
        }
        if (iN == 48) {
            iN = N();
        }
        int i2 = 2;
        cArrD[1] = (char) iN;
        int length = (this.m + cArrD.length) - 2;
        int i3 = this.n;
        if (length > i3) {
            length = i3;
        }
        int i4 = 1;
        while (true) {
            int i5 = this.m;
            if (i5 >= length) {
                return a(cArrD, i2, true, i4);
            }
            byte[] bArr2 = this.M;
            this.m = i5 + 1;
            int i6 = bArr2[i5] & 255;
            if (i6 < 48 || i6 > 57) {
                if (i6 == 46 || i6 == 101 || i6 == 69) {
                    return a(cArrD, i2, i6, true, i4);
                }
                this.m--;
                this.v.j = i2;
                if (this.t.e()) {
                    r(i6);
                }
                return a(true, i4);
            }
            i4++;
            cArrD[i2] = (char) i6;
            i2++;
        }
    }

    public final void H() {
        if (this.m < this.n || B()) {
            byte[] bArr = this.M;
            int i = this.m;
            if (bArr[i] == 10) {
                this.m = i + 1;
            }
        }
        this.p++;
        this.q = this.m;
    }

    public final void I() throws b.d.a.a.f {
        if (!a(b.d.a.a.g.a.ALLOW_COMMENTS)) {
            a(47, "maybe a (non-standard) comment? (not recognized as one since Feature 'ALLOW_COMMENTS' not enabled for parser)");
            throw null;
        }
        if (this.m >= this.n && !B()) {
            c(" in a comment");
            throw null;
        }
        byte[] bArr = this.M;
        int i = this.m;
        this.m = i + 1;
        int i2 = bArr[i] & 255;
        if (i2 == 47) {
            J();
            return;
        }
        if (i2 != 42) {
            a(i2, "was expecting either '*' or '/' for a comment");
            throw null;
        }
        int[] iArrA = b.d.a.a.b.a.a();
        while (true) {
            if (this.m >= this.n && !B()) {
                break;
            }
            byte[] bArr2 = this.M;
            int i3 = this.m;
            this.m = i3 + 1;
            int i4 = bArr2[i3] & 255;
            int i5 = iArrA[i4];
            if (i5 != 0) {
                if (i5 == 2) {
                    o(i4);
                } else if (i5 == 3) {
                    p(i4);
                } else if (i5 == 4) {
                    q(i4);
                } else if (i5 == 10) {
                    this.p++;
                    this.q = this.m;
                } else if (i5 == 13) {
                    H();
                } else {
                    if (i5 != 42) {
                        l(i4);
                        throw null;
                    }
                    if (this.m >= this.n && !B()) {
                        break;
                    }
                    byte[] bArr3 = this.M;
                    int i6 = this.m;
                    if (bArr3[i6] == 47) {
                        this.m = i6 + 1;
                        return;
                    }
                }
            }
        }
        c(" in a comment");
        throw null;
    }

    public final void J() throws b.d.a.a.f {
        int[] iArr = b.d.a.a.b.a.g;
        while (true) {
            if (this.m >= this.n && !B()) {
                return;
            }
            byte[] bArr = this.M;
            int i = this.m;
            this.m = i + 1;
            int i2 = bArr[i] & 255;
            int i3 = iArr[i2];
            if (i3 != 0) {
                if (i3 == 2) {
                    o(i2);
                } else if (i3 == 3) {
                    p(i2);
                } else if (i3 == 4) {
                    q(i2);
                } else if (i3 == 10) {
                    this.p++;
                    this.q = this.m;
                    return;
                } else if (i3 == 13) {
                    H();
                    return;
                } else if (i3 != 42 && i3 < 0) {
                    l(i2);
                    throw null;
                }
            }
        }
    }

    public final int K() throws b.d.a.a.f {
        while (true) {
            if (this.m >= this.n && !B()) {
                StringBuilder sbA = b.a.a.a.a.a("Unexpected end-of-input within/between ");
                sbA.append(this.t.b());
                sbA.append(" entries");
                throw b(sbA.toString());
            }
            byte[] bArr = this.M;
            int i = this.m;
            this.m = i + 1;
            int i2 = bArr[i] & 255;
            if (i2 > 32) {
                if (i2 == 47) {
                    I();
                } else if (i2 != 35 || !M()) {
                    return i2;
                }
            } else if (i2 == 32) {
                continue;
            } else if (i2 == 10) {
                this.p++;
                this.q = this.m;
            } else if (i2 == 13) {
                H();
            } else if (i2 != 9) {
                c(i2);
                throw null;
            }
        }
    }

    public final int L() throws b.d.a.a.f {
        while (true) {
            if (this.m >= this.n && !B()) {
                w();
                return -1;
            }
            byte[] bArr = this.M;
            int i = this.m;
            this.m = i + 1;
            int i2 = bArr[i] & 255;
            if (i2 > 32) {
                if (i2 == 47) {
                    I();
                } else if (i2 != 35 || !M()) {
                    return i2;
                }
            } else if (i2 == 32) {
                continue;
            } else if (i2 == 10) {
                this.p++;
                this.q = this.m;
            } else if (i2 == 13) {
                H();
            } else if (i2 != 9) {
                c(i2);
                throw null;
            }
        }
    }

    public final boolean M() throws b.d.a.a.f {
        if (!a(b.d.a.a.g.a.ALLOW_YAML_COMMENTS)) {
            return false;
        }
        J();
        return true;
    }

    public final int N() throws b.d.a.a.f {
        int i;
        if ((this.m >= this.n && !B()) || (i = this.M[this.m] & 255) < 48 || i > 57) {
            return 48;
        }
        if (!a(b.d.a.a.g.a.ALLOW_NUMERIC_LEADING_ZEROS)) {
            d("Leading zeroes not allowed");
            throw null;
        }
        this.m++;
        if (i == 48) {
            do {
                if (this.m >= this.n && !B()) {
                    break;
                }
                byte[] bArr = this.M;
                int i2 = this.m;
                i = bArr[i2] & 255;
                if (i < 48 || i > 57) {
                    return 48;
                }
                this.m = i2 + 1;
            } while (i == 48);
        }
        return i;
    }

    public final int O() throws b.d.a.a.f {
        if (this.m >= this.n) {
            C();
        }
        byte[] bArr = this.M;
        int i = this.m;
        this.m = i + 1;
        return bArr[i] & 255;
    }

    public final j a(char[] cArr, int i, boolean z, int i2) throws b.d.a.a.f {
        char[] cArrF = cArr;
        int i3 = i;
        int i4 = i2;
        while (true) {
            int i5 = 0;
            if (this.m >= this.n && !B()) {
                this.v.j = i3;
                this.D = z;
                this.E = i4;
                this.x = 0;
                return j.VALUE_NUMBER_INT;
            }
            byte[] bArr = this.M;
            int i6 = this.m;
            this.m = i6 + 1;
            int i7 = bArr[i6] & 255;
            if (i7 > 57 || i7 < 48) {
                if (i7 == 46 || i7 == 101 || i7 == 69) {
                    return a(cArrF, i3, i7, z, i4);
                }
                this.m--;
                this.v.j = i3;
                if (this.t.e()) {
                    byte[] bArr2 = this.M;
                    int i8 = this.m;
                    this.m = i8 + 1;
                    r(bArr2[i8] & 255);
                }
                this.D = z;
                this.E = i4;
                this.x = 0;
                return j.VALUE_NUMBER_INT;
            }
            if (i3 >= cArrF.length) {
                cArrF = this.v.f();
            } else {
                i5 = i3;
            }
            i3 = i5 + 1;
            cArrF[i5] = (char) i7;
            i4++;
        }
    }

    public final b.d.a.a.d.f b(int i, int i2, int i3) {
        return a(this.I, 0, i, i2, i3);
    }

    public int e(int i) throws b.d.a.a.f {
        int i2;
        char c2;
        int i3 = i & 255;
        if (i3 <= 127) {
            return i3;
        }
        if ((i3 & 224) == 192) {
            i2 = i3 & 31;
            c2 = 1;
        } else if ((i3 & 240) == 224) {
            i2 = i3 & 15;
            c2 = 2;
        } else {
            if ((i3 & 248) != 240) {
                m(i3 & 255);
                throw null;
            }
            i2 = i3 & 7;
            c2 = 3;
        }
        int iO = O();
        if ((iO & 192) != 128) {
            n(iO & 255);
            throw null;
        }
        int i4 = (i2 << 6) | (iO & 63);
        if (c2 <= 1) {
            return i4;
        }
        int iO2 = O();
        if ((iO2 & 192) != 128) {
            n(iO2 & 255);
            throw null;
        }
        int i5 = (i4 << 6) | (iO2 & 63);
        if (c2 <= 2) {
            return i5;
        }
        int iO3 = O();
        if ((iO3 & 192) == 128) {
            return (i5 << 6) | (iO3 & 63);
        }
        n(iO3 & 255);
        throw null;
    }

    public final int f(int i) throws b.d.a.a.f {
        if (this.m >= this.n) {
            C();
        }
        byte[] bArr = this.M;
        int i2 = this.m;
        this.m = i2 + 1;
        byte b2 = bArr[i2];
        if ((b2 & 192) == 128) {
            return ((i & 31) << 6) | (b2 & 63);
        }
        a(b2 & 255, this.m);
        throw null;
    }

    public final int g(int i) throws b.d.a.a.f {
        if (this.m >= this.n) {
            C();
        }
        int i2 = i & 15;
        byte[] bArr = this.M;
        int i3 = this.m;
        this.m = i3 + 1;
        byte b2 = bArr[i3];
        if ((b2 & 192) != 128) {
            a(b2 & 255, this.m);
            throw null;
        }
        int i4 = (i2 << 6) | (b2 & 63);
        if (this.m >= this.n) {
            C();
        }
        byte[] bArr2 = this.M;
        int i5 = this.m;
        this.m = i5 + 1;
        byte b3 = bArr2[i5];
        if ((b3 & 192) == 128) {
            return (i4 << 6) | (b3 & 63);
        }
        a(b3 & 255, this.m);
        throw null;
    }

    public final int h(int i) throws b.d.a.a.f {
        int i2 = i & 15;
        byte[] bArr = this.M;
        int i3 = this.m;
        this.m = i3 + 1;
        byte b2 = bArr[i3];
        if ((b2 & 192) != 128) {
            a(b2 & 255, this.m);
            throw null;
        }
        int i4 = (i2 << 6) | (b2 & 63);
        int i5 = this.m;
        this.m = i5 + 1;
        byte b3 = bArr[i5];
        if ((b3 & 192) == 128) {
            return (i4 << 6) | (b3 & 63);
        }
        a(b3 & 255, this.m);
        throw null;
    }

    public final int i(int i) throws b.d.a.a.f {
        if (this.m >= this.n) {
            C();
        }
        byte[] bArr = this.M;
        int i2 = this.m;
        this.m = i2 + 1;
        byte b2 = bArr[i2];
        if ((b2 & 192) != 128) {
            a(b2 & 255, this.m);
            throw null;
        }
        int i3 = ((i & 7) << 6) | (b2 & 63);
        if (this.m >= this.n) {
            C();
        }
        byte[] bArr2 = this.M;
        int i4 = this.m;
        this.m = i4 + 1;
        byte b3 = bArr2[i4];
        if ((b3 & 192) != 128) {
            a(b3 & 255, this.m);
            throw null;
        }
        int i5 = (i3 << 6) | (b3 & 63);
        if (this.m >= this.n) {
            C();
        }
        byte[] bArr3 = this.M;
        int i6 = this.m;
        this.m = i6 + 1;
        byte b4 = bArr3[i6];
        if ((b4 & 192) == 128) {
            return ((i5 << 6) | (b4 & 63)) - 65536;
        }
        a(b4 & 255, this.m);
        throw null;
    }

    public j j(int i) throws b.d.a.a.f {
        if (i == 39) {
            if (a(b.d.a.a.g.a.ALLOW_SINGLE_QUOTES)) {
                char[] cArrD = this.v.d();
                int[] iArr = F;
                byte[] bArr = this.M;
                char[] cArrF = cArrD;
                int i2 = 0;
                while (true) {
                    if (this.m >= this.n) {
                        C();
                    }
                    if (i2 >= cArrF.length) {
                        cArrF = this.v.f();
                        i2 = 0;
                    }
                    int i3 = this.n;
                    int length = this.m + (cArrF.length - i2);
                    if (length < i3) {
                        i3 = length;
                    }
                    while (true) {
                        int i4 = this.m;
                        if (i4 >= i3) {
                            break;
                        }
                        this.m = i4 + 1;
                        int iF = bArr[i4] & 255;
                        if (iF == 39 || iArr[iF] != 0) {
                            if (iF != 39) {
                                int i5 = iArr[iF];
                                if (i5 != 1) {
                                    if (i5 == 2) {
                                        iF = f(iF);
                                    } else if (i5 == 3) {
                                        iF = this.n - this.m >= 2 ? h(iF) : g(iF);
                                    } else {
                                        if (i5 != 4) {
                                            if (iF < 32) {
                                                b(iF, "string value");
                                            }
                                            l(iF);
                                            throw null;
                                        }
                                        int i6 = i(iF);
                                        int i7 = i2 + 1;
                                        cArrF[i2] = (char) (55296 | (i6 >> 10));
                                        if (i7 >= cArrF.length) {
                                            cArrF = this.v.f();
                                            i7 = 0;
                                        }
                                        int i8 = i7;
                                        iF = 56320 | (i6 & 1023);
                                        i2 = i8;
                                    }
                                } else if (iF != 39) {
                                    iF = F();
                                }
                                if (i2 >= cArrF.length) {
                                    cArrF = this.v.f();
                                    i2 = 0;
                                }
                                cArrF[i2] = (char) iF;
                                i2++;
                                break;
                            }
                            this.v.j = i2;
                            return j.VALUE_STRING;
                        }
                        cArrF[i2] = (char) iF;
                        i2++;
                    }
                }
            }
        } else {
            if (i == 43) {
                if (this.m >= this.n && !B()) {
                    y();
                    throw null;
                }
                byte[] bArr2 = this.M;
                int i9 = this.m;
                this.m = i9 + 1;
                return a(bArr2[i9] & 255, false);
            }
            if (i == 73) {
                a("Infinity", 1);
                if (a(b.d.a.a.g.a.ALLOW_NON_NUMERIC_NUMBERS)) {
                    return a("Infinity", Double.POSITIVE_INFINITY);
                }
                throw b("Non-standard token 'Infinity': enable JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS to allow");
            }
            if (i == 78) {
                a("NaN", 1);
                if (a(b.d.a.a.g.a.ALLOW_NON_NUMERIC_NUMBERS)) {
                    return a("NaN", Double.NaN);
                }
                throw b("Non-standard token 'NaN': enable JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS to allow");
            }
            if (i == 93 || i == 125) {
                a(i, "expected a value");
                throw null;
            }
        }
        if (!Character.isJavaIdentifierStart(i)) {
            a(i, "expected a valid value (number, String, array, object, 'true', 'false' or 'null')");
            throw null;
        }
        StringBuilder sbA = b.a.a.a.a.a("");
        sbA.append((char) i);
        a(sbA.toString(), "('true', 'false' or 'null')");
        throw null;
    }

    public j k(int i) throws b.d.a.a.f {
        int i2;
        int i3;
        int i4;
        char[] cArrD = this.v.d();
        if (i == 48) {
            i = N();
        }
        cArrD[0] = (char) i;
        int length = (this.m + cArrD.length) - 1;
        int i5 = this.n;
        if (length > i5) {
            i3 = 1;
            length = i5;
            i2 = 1;
        } else {
            i2 = 1;
            i3 = 1;
        }
        while (true) {
            int i6 = this.m;
            if (i6 >= length) {
                return a(cArrD, i2, false, i3);
            }
            byte[] bArr = this.M;
            this.m = i6 + 1;
            i4 = bArr[i6] & 255;
            if (i4 < 48 || i4 > 57) {
                break;
            }
            i3++;
            cArrD[i2] = (char) i4;
            i2++;
        }
        if (i4 == 46 || i4 == 101 || i4 == 69) {
            return a(cArrD, i2, i4, false, i3);
        }
        this.m--;
        this.v.j = i2;
        if (this.t.e()) {
            r(i4);
        }
        return a(false, i3);
    }

    public void l(int i) throws b.d.a.a.f {
        if (i < 32) {
            c(i);
            throw null;
        }
        m(i);
        throw null;
    }

    public void m(int i) throws b.d.a.a.f {
        StringBuilder sbA = b.a.a.a.a.a("Invalid UTF-8 start byte 0x");
        sbA.append(Integer.toHexString(i));
        throw b(sbA.toString());
    }

    public void n(int i) throws b.d.a.a.f {
        StringBuilder sbA = b.a.a.a.a.a("Invalid UTF-8 middle byte 0x");
        sbA.append(Integer.toHexString(i));
        throw b(sbA.toString());
    }

    public final void o(int i) throws b.d.a.a.f {
        if (this.m >= this.n) {
            C();
        }
        byte[] bArr = this.M;
        int i2 = this.m;
        this.m = i2 + 1;
        byte b2 = bArr[i2];
        if ((b2 & 192) == 128) {
            return;
        }
        a(b2 & 255, this.m);
        throw null;
    }

    @Override // b.d.a.a.a.b, b.d.a.a.g
    public b.d.a.a.e p() {
        int i = this.m;
        return new b.d.a.a.e(this.k.f88a, this.o + ((long) i), -1L, this.p, (i - this.q) + 1);
    }

    public final void q(int i) throws b.d.a.a.f {
        if (this.m >= this.n) {
            C();
        }
        byte[] bArr = this.M;
        int i2 = this.m;
        this.m = i2 + 1;
        byte b2 = bArr[i2];
        if ((b2 & 192) != 128) {
            a(b2 & 255, this.m);
            throw null;
        }
        if (this.m >= this.n) {
            C();
        }
        byte[] bArr2 = this.M;
        int i3 = this.m;
        this.m = i3 + 1;
        byte b3 = bArr2[i3];
        if ((b3 & 192) != 128) {
            a(b3 & 255, this.m);
            throw null;
        }
        if (this.m >= this.n) {
            C();
        }
        byte[] bArr3 = this.M;
        int i4 = this.m;
        this.m = i4 + 1;
        byte b4 = bArr3[i4];
        if ((b4 & 192) == 128) {
            return;
        }
        a(b4 & 255, this.m);
        throw null;
    }

    public final void r(int i) throws b.d.a.a.f {
        this.m++;
        if (i != 9) {
            if (i == 10) {
                this.p++;
                this.q = this.m;
            } else if (i == 13) {
                H();
            } else {
                if (i == 32) {
                    return;
                }
                a(i, "Expected space separating root-level values");
                throw null;
            }
        }
    }

    @Override // b.d.a.a.g
    public String s() throws b.d.a.a.f {
        j jVar = this.f70b;
        if (jVar != j.VALUE_STRING) {
            if (jVar == null) {
                return null;
            }
            int i = jVar.r;
            if (i != 5) {
                return (i == 6 || i == 7 || i == 8) ? this.v.c() : jVar.a();
            }
            return this.t.f139f;
        }
        if (this.J) {
            this.J = false;
            int i2 = this.m;
            if (i2 >= this.n) {
                C();
                i2 = this.m;
            }
            char[] cArrD = this.v.d();
            int[] iArr = F;
            int iMin = Math.min(this.n, cArrD.length + i2);
            byte[] bArr = this.M;
            int i3 = 0;
            while (i2 < iMin) {
                int i4 = bArr[i2] & 255;
                if (iArr[i4] == 0) {
                    i2++;
                    cArrD[i3] = (char) i4;
                    i3++;
                } else if (i4 == 34) {
                    this.m = i2 + 1;
                    this.v.j = i3;
                }
            }
            this.m = i2;
            int[] iArr2 = F;
            byte[] bArr2 = this.M;
            while (true) {
                int i5 = this.m;
                if (i5 >= this.n) {
                    C();
                    i5 = this.m;
                }
                if (i3 >= cArrD.length) {
                    cArrD = this.v.f();
                    i3 = 0;
                }
                int iMin2 = Math.min(this.n, (cArrD.length - i3) + i5);
                while (true) {
                    if (i5 >= iMin2) {
                        this.m = i5;
                        break;
                    }
                    int i6 = i5 + 1;
                    int iF = bArr2[i5] & 255;
                    if (iArr2[iF] != 0) {
                        this.m = i6;
                        if (iF != 34) {
                            int i7 = iArr2[iF];
                            if (i7 == 1) {
                                iF = F();
                            } else if (i7 == 2) {
                                iF = f(iF);
                            } else if (i7 == 3) {
                                iF = this.n - this.m >= 2 ? h(iF) : g(iF);
                            } else if (i7 == 4) {
                                int i8 = i(iF);
                                int i9 = i3 + 1;
                                cArrD[i3] = (char) (55296 | (i8 >> 10));
                                if (i9 >= cArrD.length) {
                                    cArrD = this.v.f();
                                    i9 = 0;
                                }
                                iF = (i8 & 1023) | 56320;
                                i3 = i9;
                            } else {
                                if (iF >= 32) {
                                    l(iF);
                                    throw null;
                                }
                                b(iF, "string value");
                            }
                            if (i3 >= cArrD.length) {
                                cArrD = this.v.f();
                                i3 = 0;
                            }
                            cArrD[i3] = (char) iF;
                            i3++;
                            break;
                        }
                        this.v.j = i3;
                        break;
                    }
                    cArrD[i3] = (char) iF;
                    i5 = i6;
                    i3++;
                }
            }
        }
        return this.v.c();
    }

    /* JADX WARN: Code duplicated, block: B:169:0x02c4 A[EDGE_INSN: B:169:0x02c4->B:258:0x041b BREAK  A[LOOP:4: B:218:0x0386->B:242:0x03dd], PHI: r1
      0x02c4: PHI (r1v75 b.d.a.a.d.f) = (r1v74 b.d.a.a.d.f), (r1v77 b.d.a.a.d.f) binds: [B:240:0x03d6, B:167:0x02bc] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:335:0x0640  */
    /* JADX WARN: Code duplicated, block: B:336:0x0647  */
    /* JADX WARN: Code duplicated, block: B:338:0x064f  */
    /* JADX WARN: Code duplicated, block: B:340:0x0656  */
    /* JADX WARN: Code duplicated, block: B:345:0x0664  */
    /* JADX WARN: Code duplicated, block: B:346:0x066a  */
    /* JADX WARN: Code duplicated, block: B:349:0x0670  */
    /* JADX WARN: Code duplicated, block: B:351:0x067b A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:357:0x068e A[ADDED_TO_REGION, REMOVE] */
    /* JADX WARN: Code duplicated, block: B:358:0x0693  */
    /* JADX WARN: Code duplicated, block: B:361:0x0699  */
    /* JADX WARN: Code duplicated, block: B:363:0x06a4  */
    /* JADX WARN: Code duplicated, block: B:365:0x06af  */
    /* JADX WARN: Code duplicated, block: B:370:0x06bc  */
    /* JADX WARN: Code duplicated, block: B:371:0x06c1  */
    /* JADX WARN: Code duplicated, block: B:374:0x06c7  */
    /* JADX WARN: Code duplicated, block: B:376:0x06d2 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:382:0x06e4 A[ADDED_TO_REGION, REMOVE] */
    /* JADX WARN: Code duplicated, block: B:383:0x06e9  */
    /* JADX WARN: Code duplicated, block: B:385:0x06f0  */
    /* JADX WARN: Code duplicated, block: B:387:0x06f9 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:388:0x06fb  */
    /* JADX WARN: Code duplicated, block: B:390:0x06ff  */
    /* JADX WARN: Code duplicated, block: B:392:0x0703  */
    /* JADX WARN: Code duplicated, block: B:394:0x0707  */
    /* JADX WARN: Code duplicated, block: B:396:0x070b  */
    /* JADX WARN: Code duplicated, block: B:398:0x070f  */
    /* JADX WARN: Code duplicated, block: B:399:0x0712  */
    /* JADX WARN: Code duplicated, block: B:400:0x0717  */
    /* JADX WARN: Code duplicated, block: B:401:0x071c  */
    /* JADX WARN: Code duplicated, block: B:402:0x071f  */
    /* JADX WARN: Code duplicated, block: B:403:0x0727  */
    /* JADX WARN: Code duplicated, block: B:404:0x072f  */
    /* JADX WARN: Code duplicated, block: B:405:0x0735  */
    /* JADX WARN: Code duplicated, block: B:406:0x0738  */
    @Override // b.d.a.a.g
    public j u() throws b.d.a.a.f {
        int iL;
        int i;
        b.d.a.a.d.f fVarB;
        int i2;
        byte[] bArr;
        byte b2;
        int iA;
        j jVarG;
        int i3;
        b.d.a.a.d.f fVarB2;
        int i4;
        int i5;
        int i6;
        int i7;
        this.x = 0;
        if (this.f70b == j.FIELD_NAME) {
            j jVar = this.u;
            this.u = null;
            if (jVar == j.START_ARRAY) {
                this.t = this.t.a(this.r, this.s);
            } else if (jVar == j.START_OBJECT) {
                this.t = this.t.b(this.r, this.s);
            }
            this.f70b = jVar;
            return jVar;
        }
        if (this.J) {
            this.J = false;
            int[] iArr = F;
            byte[] bArr2 = this.M;
            while (true) {
                int i8 = this.m;
                int i9 = this.n;
                if (i8 >= i9) {
                    C();
                    i8 = this.m;
                    i9 = this.n;
                }
                while (true) {
                    if (i8 >= i9) {
                        this.m = i8;
                        break;
                    }
                    int i10 = i8 + 1;
                    int i11 = bArr2[i8] & 255;
                    if (iArr[i11] != 0) {
                        this.m = i10;
                        if (i11 != 34) {
                            int i12 = iArr[i11];
                            if (i12 == 1) {
                                F();
                                break;
                            }
                            if (i12 == 2) {
                                o(i11);
                                break;
                            }
                            if (i12 == 3) {
                                p(i11);
                                break;
                            }
                            if (i12 == 4) {
                                q(i11);
                                break;
                            }
                            if (i11 < 32) {
                                b(i11, "string value");
                                break;
                            }
                            l(i11);
                            throw null;
                        }
                        break;
                    }
                    i8 = i10;
                }
            }
        }
        if (this.m < this.n || B()) {
            byte[] bArr3 = this.M;
            int i13 = this.m;
            this.m = i13 + 1;
            iL = bArr3[i13] & 255;
            if (iL <= 32) {
                if (iL != 32) {
                    if (iL == 10) {
                        this.p++;
                        this.q = this.m;
                    } else if (iL == 13) {
                        H();
                    } else if (iL != 9) {
                        c(iL);
                        throw null;
                    }
                }
                while (true) {
                    int i14 = this.m;
                    if (i14 >= this.n) {
                        iL = L();
                        break;
                    }
                    byte[] bArr4 = this.M;
                    this.m = i14 + 1;
                    iL = bArr4[i14] & 255;
                    if (iL > 32) {
                        if (iL != 47 && iL != 35) {
                            break;
                        }
                        this.m--;
                        iL = L();
                        break;
                    }
                    if (iL != 32) {
                        if (iL == 10) {
                            this.p++;
                            this.q = this.m;
                        } else if (iL == 13) {
                            H();
                        } else if (iL != 9) {
                            c(iL);
                            throw null;
                        }
                    }
                }
            } else if (iL == 47 || iL == 35) {
                this.m--;
                iL = L();
            }
        } else {
            w();
            iL = -1;
        }
        if (iL < 0) {
            close();
            this.f70b = null;
            return null;
        }
        long j = this.o;
        int i15 = this.m;
        this.r = this.p;
        this.s = (i15 - this.q) - 1;
        if (iL == 93) {
            if (!this.t.c()) {
                a(iL, '}');
                throw null;
            }
            this.t = this.t.f136c;
            j jVar2 = j.END_ARRAY;
            this.f70b = jVar2;
            return jVar2;
        }
        if (iL == 125) {
            if (!this.t.d()) {
                a(iL, ']');
                throw null;
            }
            this.t = this.t.f136c;
            j jVar3 = j.END_OBJECT;
            this.f70b = jVar3;
            return jVar3;
        }
        d dVar = this.t;
        int i16 = dVar.f221b + 1;
        dVar.f221b = i16;
        if (dVar.f220a != 0 && i16 > 0) {
            if (iL != 44) {
                StringBuilder sbA = b.a.a.a.a.a("was expecting comma to separate ");
                sbA.append(this.t.b());
                sbA.append(" entries");
                a(iL, sbA.toString());
                throw null;
            }
            while (true) {
                int i17 = this.m;
                if (i17 >= this.n) {
                    iL = K();
                    break;
                }
                byte[] bArr5 = this.M;
                this.m = i17 + 1;
                iL = bArr5[i17] & 255;
                if (iL > 32) {
                    if (iL != 47 && iL != 35) {
                        break;
                    }
                    this.m--;
                    iL = K();
                    break;
                }
                if (iL != 32) {
                    if (iL == 10) {
                        this.p++;
                        this.q = this.m;
                    } else if (iL == 13) {
                        H();
                    } else if (iL != 9) {
                        c(iL);
                        throw null;
                    }
                }
            }
        }
        if (!this.t.d()) {
            if (iL == 34) {
                this.J = true;
                j jVar4 = j.VALUE_STRING;
                this.f70b = jVar4;
                return jVar4;
            }
            if (iL == 45) {
                j jVarG2 = G();
                this.f70b = jVarG2;
                return jVarG2;
            }
            if (iL == 91) {
                this.t = this.t.a(this.r, this.s);
                j jVar5 = j.START_ARRAY;
                this.f70b = jVar5;
                return jVar5;
            }
            if (iL == 102) {
                a("false", 1);
                j jVar6 = j.VALUE_FALSE;
                this.f70b = jVar6;
                return jVar6;
            }
            if (iL == 110) {
                a("null", 1);
                j jVar7 = j.VALUE_NULL;
                this.f70b = jVar7;
                return jVar7;
            }
            if (iL == 116) {
                a("true", 1);
                j jVar8 = j.VALUE_TRUE;
                this.f70b = jVar8;
                return jVar8;
            }
            if (iL == 123) {
                this.t = this.t.b(this.r, this.s);
                j jVar9 = j.START_OBJECT;
                this.f70b = jVar9;
                return jVar9;
            }
            switch (iL) {
                case 48:
                case 49:
                case 50:
                case 51:
                case 52:
                case 53:
                case 54:
                case 55:
                case 56:
                case 57:
                    j jVarK = k(iL);
                    this.f70b = jVarK;
                    return jVarK;
                default:
                    j jVarJ = j(iL);
                    this.f70b = jVarJ;
                    return jVarJ;
            }
        }
        if (iL == 34) {
            int i18 = this.m;
            int i19 = i18 + 9;
            int i20 = this.n;
            if (i19 <= i20) {
                i = 45;
                byte[] bArr6 = this.M;
                int[] iArr2 = G;
                this.m = i18 + 1;
                int i21 = bArr6[i18] & 255;
                if (iArr2[i21] == 0) {
                    int i22 = this.m;
                    this.m = i22 + 1;
                    int i23 = bArr6[i22] & 255;
                    if (iArr2[i23] == 0) {
                        int i24 = (i21 << 8) | i23;
                        int i25 = this.m;
                        this.m = i25 + 1;
                        int i26 = bArr6[i25] & 255;
                        if (iArr2[i26] == 0) {
                            int i27 = (i24 << 8) | i26;
                            int i28 = this.m;
                            this.m = i28 + 1;
                            int i29 = bArr6[i28] & 255;
                            if (iArr2[i29] == 0) {
                                int i30 = (i27 << 8) | i29;
                                int i31 = this.m;
                                this.m = i31 + 1;
                                int i32 = bArr6[i31] & 255;
                                if (iArr2[i32] == 0) {
                                    this.K = i30;
                                    int i33 = this.m;
                                    this.m = i33 + 1;
                                    int i34 = bArr6[i33] & 255;
                                    if (iArr2[i34] == 0) {
                                        int i35 = i34 | (i32 << 8);
                                        int i36 = this.m;
                                        this.m = i36 + 1;
                                        int i37 = bArr6[i36] & 255;
                                        if (iArr2[i37] == 0) {
                                            int i38 = (i35 << 8) | i37;
                                            int i39 = this.m;
                                            this.m = i39 + 1;
                                            int i40 = bArr6[i39] & 255;
                                            if (iArr2[i40] == 0) {
                                                int i41 = (i38 << 8) | i40;
                                                int i42 = this.m;
                                                this.m = i42 + 1;
                                                int i43 = bArr6[i42] & 255;
                                                if (iArr2[i43] == 0) {
                                                    int[] iArr3 = this.I;
                                                    iArr3[0] = this.K;
                                                    iArr3[1] = i41;
                                                    int i44 = i43;
                                                    int i45 = 2;
                                                    while (true) {
                                                        int i46 = this.m;
                                                        if (i46 + 4 > this.n) {
                                                            fVarB = a(this.I, i45, 0, i44, 0);
                                                            break;
                                                        }
                                                        this.m = i46 + 1;
                                                        int i47 = bArr6[i46] & 255;
                                                        if (iArr2[i47] != 0) {
                                                            if (i47 != 34) {
                                                                fVarB = a(this.I, i45, i44, i47, 1);
                                                                break;
                                                            }
                                                            fVarB = a(this.I, i45, i44, 1);
                                                            break;
                                                        }
                                                        int i48 = (i44 << 8) | i47;
                                                        int i49 = this.m;
                                                        this.m = i49 + 1;
                                                        int i50 = bArr6[i49] & 255;
                                                        if (iArr2[i50] != 0) {
                                                            if (i50 != 34) {
                                                                fVarB = a(this.I, i45, i48, i50, 2);
                                                                break;
                                                            }
                                                            fVarB = a(this.I, i45, i48, 2);
                                                            break;
                                                        }
                                                        int i51 = (i48 << 8) | i50;
                                                        int i52 = this.m;
                                                        this.m = i52 + 1;
                                                        int i53 = bArr6[i52] & 255;
                                                        if (iArr2[i53] != 0) {
                                                            if (i53 != 34) {
                                                                fVarB = a(this.I, i45, i51, i53, 3);
                                                                break;
                                                            }
                                                            fVarB = a(this.I, i45, i51, 3);
                                                            break;
                                                        }
                                                        int i54 = (i51 << 8) | i53;
                                                        int i55 = this.m;
                                                        this.m = i55 + 1;
                                                        int i56 = bArr6[i55] & 255;
                                                        if (iArr2[i56] != 0) {
                                                            if (i56 != 34) {
                                                                fVarB = a(this.I, i45, i54, i56, 4);
                                                                break;
                                                            }
                                                            fVarB = a(this.I, i45, i54, 4);
                                                            break;
                                                        }
                                                        int[] iArr4 = this.I;
                                                        if (i45 >= iArr4.length) {
                                                            this.I = a(iArr4, i45);
                                                        }
                                                        this.I[i45] = i54;
                                                        i44 = i56;
                                                        i45++;
                                                    }
                                                } else {
                                                    fVarB = i43 == 34 ? a(this.K, i41, 4) : a(this.K, i41, i43, 4);
                                                }
                                            } else {
                                                fVarB = i40 == 34 ? a(this.K, i38, 3) : a(this.K, i38, i40, 3);
                                            }
                                        } else {
                                            fVarB = i37 == 34 ? a(this.K, i35, 2) : a(this.K, i35, i37, 2);
                                        }
                                    } else {
                                        fVarB = i34 == 34 ? a(this.K, i32, 1) : a(this.K, i32, i34, 1);
                                    }
                                } else {
                                    fVarB = i32 == 34 ? b(i30, 4) : b(i30, i32, 4);
                                }
                            } else {
                                fVarB = i29 == 34 ? b(i27, 3) : b(i27, i29, 3);
                            }
                        } else {
                            fVarB = i26 == 34 ? b(i24, 2) : b(i24, i26, 2);
                        }
                    } else {
                        fVarB = i23 == 34 ? b(i21, 1) : b(i21, i23, 1);
                    }
                } else {
                    fVarB = i21 == 34 ? b.d.a.a.d.c.f176c : b(0, i21, 0);
                }
            } else {
                if (i18 >= i20 && !B()) {
                    c(": was expecting closing '\"' for name");
                    throw null;
                }
                byte[] bArr7 = this.M;
                int i57 = this.m;
                this.m = i57 + 1;
                int i58 = bArr7[i57] & 255;
                if (i58 == 34) {
                    fVarB = b.d.a.a.d.c.f176c;
                } else {
                    i = 45;
                    fVarB = a(this.I, 0, 0, i58, 0);
                }
            }
            this.t.a(fVarB.f183a);
            this.f70b = j.FIELD_NAME;
            i2 = this.m;
            if (i2 + 4 >= this.n) {
                iA = a(false);
            } else {
                bArr = this.M;
                b2 = bArr[i2];
                if (b2 == 58) {
                    int i59 = i2 + 1;
                    this.m = i59;
                    iA = bArr[i59];
                    if (iA > 32) {
                        if (iA != 47 || iA == 35) {
                            iA = a(true);
                        } else {
                            this.m++;
                        }
                    } else if (iA != 32 || iA == 9) {
                        byte[] bArr8 = this.M;
                        int i60 = this.m + 1;
                        this.m = i60;
                        iA = bArr8[i60];
                        if (iA > 32 || iA == 47 || iA == 35) {
                            iA = a(true);
                        } else {
                            this.m++;
                        }
                    } else {
                        iA = a(true);
                    }
                } else {
                    if (b2 != 32 || b2 == 9) {
                        byte[] bArr9 = this.M;
                        int i61 = this.m + 1;
                        this.m = i61;
                        b2 = bArr9[i61];
                    }
                    if (b2 == 58) {
                        byte[] bArr10 = this.M;
                        int i62 = this.m + 1;
                        this.m = i62;
                        iA = bArr10[i62];
                        if (iA > 32) {
                            if (iA != 47 || iA == 35) {
                                iA = a(true);
                            } else {
                                this.m++;
                            }
                        } else if (iA != 32 || iA == 9) {
                            byte[] bArr11 = this.M;
                            int i63 = this.m + 1;
                            this.m = i63;
                            iA = bArr11[i63];
                            if (iA > 32 || iA == 47 || iA == 35) {
                                iA = a(true);
                            } else {
                                this.m++;
                            }
                        } else {
                            iA = a(true);
                        }
                    } else {
                        iA = a(false);
                    }
                }
            }
            if (iA == 34) {
                this.J = true;
                this.u = j.VALUE_STRING;
                return this.f70b;
            }
            if (iA != i) {
                jVarG = G();
            } else if (iA != 91) {
                jVarG = j.START_ARRAY;
            } else if (iA != 102) {
                a("false", 1);
                jVarG = j.VALUE_FALSE;
            } else if (iA != 110) {
                a("null", 1);
                jVarG = j.VALUE_NULL;
            } else if (iA != 116) {
                a("true", 1);
                jVarG = j.VALUE_TRUE;
            } else if (iA != 123) {
                switch (iA) {
                    case 48:
                    case 49:
                    case 50:
                    case 51:
                    case 52:
                    case 53:
                    case 54:
                    case 55:
                    case 56:
                    case 57:
                        jVarG = k(iA);
                        break;
                    default:
                        jVarG = j(iA);
                        break;
                }
            } else {
                jVarG = j.START_OBJECT;
            }
            this.u = jVarG;
            return this.f70b;
        }
        if (iL == 39 && a(b.d.a.a.g.a.ALLOW_SINGLE_QUOTES)) {
            if (this.m >= this.n && !B()) {
                c(": was expecting closing ''' for name");
                throw null;
            }
            byte[] bArr12 = this.M;
            int i64 = this.m;
            this.m = i64 + 1;
            int iF = bArr12[i64] & 255;
            if (iF != 39) {
                int[] iArrA = this.I;
                int[] iArr5 = G;
                int i65 = 0;
                int i66 = 0;
                int i67 = 0;
                for (int i68 = 39; iF != i68; i68 = 39) {
                    if (iF != 34 && iArr5[iF] != 0) {
                        if (iF != 92) {
                            b(iF, "name");
                        } else {
                            iF = F();
                        }
                        if (iF > 127) {
                            if (i65 >= 4) {
                                if (i67 >= iArrA.length) {
                                    iArrA = a(iArrA, iArrA.length);
                                    this.I = iArrA;
                                }
                                i5 = i67 + 1;
                                iArrA[i67] = i66;
                                i65 = 0;
                                i66 = 0;
                            } else {
                                i5 = i67;
                            }
                            if (iF < 2048) {
                                i66 = (i66 << 8) | (iF >> 6) | 192;
                                i65++;
                                i67 = i5;
                            } else {
                                int i69 = (i66 << 8) | (iF >> 12) | 224;
                                int i70 = i65 + 1;
                                if (i70 >= 4) {
                                    if (i5 >= iArrA.length) {
                                        iArrA = a(iArrA, iArrA.length);
                                        this.I = iArrA;
                                    }
                                    i6 = i5 + 1;
                                    iArrA[i5] = i69;
                                    i7 = 0;
                                    i69 = 0;
                                } else {
                                    i6 = i5;
                                    i7 = i70;
                                }
                                i66 = (i69 << 8) | ((iF >> 6) & 63) | 128;
                                i67 = i6;
                                i65 = i7 + 1;
                            }
                            iF = (iF & 63) | 128;
                        }
                    }
                    if (i65 < 4) {
                        i66 = iF | (i66 << 8);
                        i65++;
                    } else {
                        if (i67 >= iArrA.length) {
                            iArrA = a(iArrA, iArrA.length);
                            this.I = iArrA;
                        }
                        iArrA[i67] = i66;
                        i66 = iF;
                        i67++;
                        i65 = 1;
                    }
                    if (this.m >= this.n && !B()) {
                        c(" in field name");
                        throw null;
                    }
                    byte[] bArr13 = this.M;
                    int i71 = this.m;
                    this.m = i71 + 1;
                    iF = bArr13[i71] & 255;
                }
                if (i65 > 0) {
                    if (i67 >= iArrA.length) {
                        int[] iArrA2 = a(iArrA, iArrA.length);
                        this.I = iArrA2;
                        iArrA = iArrA2;
                    }
                    i4 = i67 + 1;
                    iArrA[i67] = c(i66, i65);
                } else {
                    i4 = i67;
                }
                fVarB2 = this.H.b(iArrA, i4);
                if (fVarB2 != null) {
                    fVarB = fVarB2;
                    break;
                }
                fVarB = a(iArrA, i4, i65);
            } else {
                fVarB = b.d.a.a.d.c.f176c;
            }
        } else {
            if (!a(b.d.a.a.g.a.ALLOW_UNQUOTED_FIELD_NAMES)) {
                a((char) e(iL), "was expecting double-quote to start field name");
                throw null;
            }
            int[] iArr6 = b.d.a.a.b.a.f87f;
            if (iArr6[iL] != 0) {
                a(iL, "was expecting either valid name character (for unquoted name) or double-quote (for quoted) to start field name");
                throw null;
            }
            int[] iArrA3 = this.I;
            int i72 = 0;
            int i73 = 4;
            int i74 = 0;
            int i75 = 0;
            while (true) {
                if (i72 < i73) {
                    i72++;
                    i75 = iL | (i75 << 8);
                } else {
                    if (i74 >= iArrA3.length) {
                        iArrA3 = a(iArrA3, iArrA3.length);
                        this.I = iArrA3;
                    }
                    iArrA3[i74] = i75;
                    i75 = iL;
                    i74++;
                    i72 = 1;
                }
                if (this.m >= this.n && !B()) {
                    c(" in field name");
                    throw null;
                }
                byte[] bArr14 = this.M;
                int i76 = this.m;
                iL = bArr14[i76] & 255;
                if (iArr6[iL] != 0) {
                    if (i72 > 0) {
                        if (i74 >= iArrA3.length) {
                            int[] iArrA4 = a(iArrA3, iArrA3.length);
                            this.I = iArrA4;
                            iArrA3 = iArrA4;
                        }
                        i3 = i74 + 1;
                        iArrA3[i74] = i75;
                    } else {
                        i3 = i74;
                    }
                    fVarB2 = this.H.b(iArrA3, i3);
                    if (fVarB2 != null) {
                        fVarB = fVarB2;
                        break;
                    }
                    fVarB = a(iArrA3, i3, i72);
                    break;
                }
                this.m = i76 + 1;
                i73 = 4;
            }
        }
        i = 45;
        this.t.a(fVarB.f183a);
        this.f70b = j.FIELD_NAME;
        i2 = this.m;
        if (i2 + 4 >= this.n) {
            iA = a(false);
        } else {
            bArr = this.M;
            b2 = bArr[i2];
            if (b2 == 58) {
                int i510 = i2 + 1;
                this.m = i510;
                iA = bArr[i510];
                if (iA > 32) {
                    if (iA != 47) {
                        iA = a(true);
                    } else {
                        iA = a(true);
                    }
                } else if (iA != 32) {
                    byte[] bArr15 = this.M;
                    int i610 = this.m + 1;
                    this.m = i610;
                    iA = bArr15[i610];
                    if (iA > 32) {
                        iA = a(true);
                    } else {
                        iA = a(true);
                    }
                } else {
                    byte[] bArr16 = this.M;
                    int i611 = this.m + 1;
                    this.m = i611;
                    iA = bArr16[i611];
                    if (iA > 32) {
                        iA = a(true);
                    } else {
                        iA = a(true);
                    }
                }
            } else {
                if (b2 != 32) {
                    byte[] bArr17 = this.M;
                    int i612 = this.m + 1;
                    this.m = i612;
                    b2 = bArr17[i612];
                } else {
                    byte[] bArr18 = this.M;
                    int i613 = this.m + 1;
                    this.m = i613;
                    b2 = bArr18[i613];
                }
                if (b2 == 58) {
                    byte[] bArr19 = this.M;
                    int i614 = this.m + 1;
                    this.m = i614;
                    iA = bArr19[i614];
                    if (iA > 32) {
                        if (iA != 47) {
                            iA = a(true);
                        } else {
                            iA = a(true);
                        }
                    } else if (iA != 32) {
                        byte[] bArr110 = this.M;
                        int i615 = this.m + 1;
                        this.m = i615;
                        iA = bArr110[i615];
                        if (iA > 32) {
                            iA = a(true);
                        } else {
                            iA = a(true);
                        }
                    } else {
                        byte[] bArr111 = this.M;
                        int i616 = this.m + 1;
                        this.m = i616;
                        iA = bArr111[i616];
                        if (iA > 32) {
                            iA = a(true);
                        } else {
                            iA = a(true);
                        }
                    }
                } else {
                    iA = a(false);
                }
            }
        }
        if (iA == 34) {
            this.J = true;
            this.u = j.VALUE_STRING;
            return this.f70b;
        }
        if (iA != i) {
            jVarG = G();
        } else if (iA != 91) {
            jVarG = j.START_ARRAY;
        } else if (iA != 102) {
            a("false", 1);
            jVarG = j.VALUE_FALSE;
        } else if (iA != 110) {
            a("null", 1);
            jVarG = j.VALUE_NULL;
        } else if (iA != 116) {
            a("true", 1);
            jVarG = j.VALUE_TRUE;
        } else if (iA != 123) {
            switch (iA) {
                case 48:
                case 49:
                case 50:
                case 51:
                case 52:
                case 53:
                case 54:
                case 55:
                case 56:
                case 57:
                    jVarG = k(iA);
                    break;
                default:
                    jVarG = j(iA);
                    break;
            }
        } else {
            jVarG = j.START_OBJECT;
        }
        this.u = jVarG;
        return this.f70b;
    }

    @Override // b.d.a.a.a.b
    public void z() throws IOException {
        if (this.L != null) {
            if (this.k.f90c || a(b.d.a.a.g.a.AUTO_CLOSE_SOURCE)) {
                this.L.close();
            }
            this.L = null;
        }
    }

    /* JADX WARN: Code duplicated, block: B:12:0x0031  */
    /* JADX WARN: Code duplicated, block: B:14:0x0035  */
    /* JADX WARN: Code duplicated, block: B:16:0x003d  */
    public final b.d.a.a.d.f b(int i, int i2) {
        int i3;
        b.d.a.a.d.a.C0004a c0004a;
        int iC = c(i, i2);
        b.d.a.a.d.a aVar = this.H;
        int i4 = aVar.f153c ^ iC;
        int i5 = i4 + (i4 >>> 15);
        int i6 = i5 ^ (i5 >>> 9);
        int i7 = aVar.h & i6;
        int i8 = aVar.i[i7];
        b.d.a.a.d.f fVarA = null;
        if ((((i8 >> 8) ^ i6) << 8) == 0) {
            b.d.a.a.d.f fVar = aVar.j[i7];
            if (fVar != null) {
                if (fVar.a(iC)) {
                    fVarA = fVar;
                } else {
                    i3 = i8 & 255;
                    if (i3 > 0) {
                        c0004a = aVar.k[i3 - 1];
                        if (c0004a != null) {
                            fVarA = c0004a.a(i6, iC, 0);
                        }
                    }
                }
            }
        } else if (i8 != 0) {
            i3 = i8 & 255;
            if (i3 > 0) {
                c0004a = aVar.k[i3 - 1];
                if (c0004a != null) {
                    fVarA = c0004a.a(i6, iC, 0);
                }
            }
        }
        if (fVarA != null) {
            return fVarA;
        }
        int[] iArr = this.I;
        iArr[0] = iC;
        return a(iArr, 1, i2);
    }

    public final void p(int i) throws b.d.a.a.f {
        if (this.m >= this.n) {
            C();
        }
        byte[] bArr = this.M;
        int i2 = this.m;
        this.m = i2 + 1;
        byte b2 = bArr[i2];
        if ((b2 & 192) == 128) {
            if (this.m >= this.n) {
                C();
            }
            byte[] bArr2 = this.M;
            int i3 = this.m;
            this.m = i3 + 1;
            byte b3 = bArr2[i3];
            if ((b3 & 192) == 128) {
                return;
            }
            a(b3 & 255, this.m);
            throw null;
        }
        a(b2 & 255, this.m);
        throw null;
    }

    public void e(String str) throws b.d.a.a.f {
        a(str, "'null', 'true', 'false' or NaN");
        throw null;
    }

    public final j a(char[] cArr, int i, int i2, boolean z, int i3) throws b.d.a.a.f {
        boolean z2;
        if (i2 == 46) {
            cArr[i] = (char) i2;
            i++;
            int i4 = i2;
            char[] cArrF = cArr;
            int i5 = 0;
            while (true) {
                if (this.m >= this.n && !B()) {
                    z2 = true;
                    break;
                }
                byte[] bArr = this.M;
                int i6 = this.m;
                this.m = i6 + 1;
                i4 = bArr[i6] & 255;
                if (i4 < 48 || i4 > 57) {
                    z2 = false;
                    break;
                }
                i5++;
                if (i >= cArrF.length) {
                    cArrF = this.v.f();
                    i = 0;
                }
                cArrF[i] = (char) i4;
                i++;
            }
            if (i5 == 0) {
                c(i4, "Decimal point not followed by a digit");
                throw null;
            }
            cArr = cArrF;
            i2 = i4;
        } else {
            z2 = false;
        }
        if (i2 == 101 || i2 == 69) {
            if (i >= cArr.length) {
                cArr = this.v.f();
                i = 0;
            }
            int i7 = i + 1;
            cArr[i] = (char) i2;
            if (this.m >= this.n) {
                C();
            }
            byte[] bArr2 = this.M;
            int i8 = this.m;
            this.m = i8 + 1;
            int i9 = bArr2[i8] & 255;
            if (i9 == 45 || i9 == 43) {
                if (i7 >= cArr.length) {
                    cArr = this.v.f();
                    i7 = 0;
                }
                int i10 = i7 + 1;
                cArr[i7] = (char) i9;
                if (this.m >= this.n) {
                    C();
                }
                byte[] bArr3 = this.M;
                int i11 = this.m;
                this.m = i11 + 1;
                i9 = bArr3[i11] & 255;
                i7 = i10;
            }
            i2 = i9;
            char[] cArrF2 = cArr;
            int i12 = 0;
            while (true) {
                if (i2 <= 57 && i2 >= 48) {
                    i12++;
                    if (i7 >= cArrF2.length) {
                        cArrF2 = this.v.f();
                        i7 = 0;
                    }
                    int i13 = i7 + 1;
                    cArrF2[i7] = (char) i2;
                    if (this.m >= this.n && !B()) {
                        z2 = true;
                        i = i13;
                        break;
                    }
                    byte[] bArr4 = this.M;
                    int i14 = this.m;
                    this.m = i14 + 1;
                    i2 = bArr4[i14] & 255;
                    i7 = i13;
                } else {
                    i = i7;
                    break;
                }
            }
            if (i12 == 0) {
                c(i2, "Exponent indicator not followed by a digit");
                throw null;
            }
        }
        if (!z2) {
            this.m--;
            if (this.t.e()) {
                r(i2);
            }
        }
        this.v.j = i;
        this.D = z;
        this.E = i3;
        this.x = 0;
        return j.VALUE_NUMBER_FLOAT;
    }

    public final b.d.a.a.d.f a(int i, int i2, int i3, int i4) {
        int[] iArr = this.I;
        iArr[0] = i;
        return a(iArr, 1, i2, i3, i4);
    }

    public final b.d.a.a.d.f a(int[] iArr, int i, int i2, int i3, int i4) throws b.d.a.a.f {
        int[] iArr2 = G;
        while (true) {
            if (iArr2[i3] != 0) {
                if (i3 == 34) {
                    if (i4 > 0) {
                        if (i >= iArr.length) {
                            iArr = a(iArr, iArr.length);
                            this.I = iArr;
                        }
                        iArr[i] = c(i2, i4);
                        i++;
                    }
                    b.d.a.a.d.f fVarB = this.H.b(iArr, i);
                    return fVarB == null ? a(iArr, i, i4) : fVarB;
                }
                if (i3 != 92) {
                    b(i3, "name");
                } else {
                    i3 = F();
                }
                if (i3 > 127) {
                    if (i4 >= 4) {
                        if (i >= iArr.length) {
                            iArr = a(iArr, iArr.length);
                            this.I = iArr;
                        }
                        iArr[i] = i2;
                        i++;
                        i2 = 0;
                        i4 = 0;
                    }
                    if (i3 < 2048) {
                        i2 = (i2 << 8) | (i3 >> 6) | 192;
                        i4++;
                    } else {
                        int i5 = (i2 << 8) | (i3 >> 12) | 224;
                        int i6 = i4 + 1;
                        if (i6 >= 4) {
                            if (i >= iArr.length) {
                                iArr = a(iArr, iArr.length);
                                this.I = iArr;
                            }
                            iArr[i] = i5;
                            i++;
                            i5 = 0;
                            i6 = 0;
                        }
                        i2 = (i5 << 8) | ((i3 >> 6) & 63) | 128;
                        i4 = i6 + 1;
                    }
                    i3 = (i3 & 63) | 128;
                }
            }
            if (i4 < 4) {
                i4++;
                i2 = (i2 << 8) | i3;
            } else {
                if (i >= iArr.length) {
                    iArr = a(iArr, iArr.length);
                    this.I = iArr;
                }
                iArr[i] = i2;
                i2 = i3;
                i++;
                i4 = 1;
            }
            if (this.m >= this.n && !B()) {
                c(" in field name");
                throw null;
            }
            byte[] bArr = this.M;
            int i7 = this.m;
            this.m = i7 + 1;
            i3 = bArr[i7] & 255;
        }
    }

    public final b.d.a.a.d.f a(int i, int i2, int i3) {
        int iC = c(i2, i3);
        b.d.a.a.d.f fVarB = this.H.b(i, iC);
        if (fVarB != null) {
            return fVarB;
        }
        int[] iArr = this.I;
        iArr[0] = i;
        iArr[1] = iC;
        return a(iArr, 2, i3);
    }

    public final b.d.a.a.d.f a(int[] iArr, int i, int i2, int i3) {
        if (i >= iArr.length) {
            iArr = a(iArr, iArr.length);
            this.I = iArr;
        }
        int i4 = i + 1;
        iArr[i] = c(i2, i3);
        b.d.a.a.d.f fVarB = this.H.b(iArr, i4);
        return fVarB == null ? a(iArr, i4, i3) : fVarB;
    }

    /* JADX WARN: Code duplicated, block: B:174:0x037f  */
    /* JADX WARN: Code duplicated, block: B:77:0x0160  */
    /* JADX WARN: Code duplicated, block: B:79:0x0164  */
    /* JADX WARN: Code duplicated, block: B:81:0x0171  */
    /* JADX WARN: Code duplicated, block: B:82:0x0176  */
    /* JADX WARN: Type inference failed for: r1v32 */
    /* JADX WARN: Type inference failed for: r1v7 */
    /* JADX WARN: Type inference failed for: r1v8, types: [boolean, int] */
    public final b.d.a.a.d.f a(int[] iArr, int i, int i2) throws b.d.a.a.f {
        int i3;
        int iA;
        int[] iArrCopyOfRange;
        b.d.a.a.d.f gVar;
        ?? r1;
        int iB;
        int iB2;
        int i4;
        int i5;
        int i6;
        int i7 = ((i << 2) - 4) + i2;
        if (i2 < 4) {
            int i8 = i - 1;
            i3 = iArr[i8];
            iArr[i8] = i3 << ((4 - i2) << 3);
        } else {
            i3 = 0;
        }
        char[] cArrD = this.v.d();
        int i9 = 0;
        int i10 = 0;
        while (i9 < i7) {
            int i11 = (iArr[i9 >> 2] >> ((3 - (i9 & 3)) << 3)) & 255;
            i9++;
            if (i11 > 127) {
                if ((i11 & 224) == 192) {
                    i4 = i11 & 31;
                    i5 = 1;
                } else if ((i11 & 240) == 224) {
                    i4 = i11 & 15;
                    i5 = 2;
                } else {
                    if ((i11 & 248) != 240) {
                        m(i11);
                        throw null;
                    }
                    i4 = i11 & 7;
                    i5 = 3;
                }
                if (i9 + i5 <= i7) {
                    int i12 = iArr[i9 >> 2] >> ((3 - (i9 & 3)) << 3);
                    i9++;
                    if ((i12 & 192) != 128) {
                        n(i12);
                        throw null;
                    }
                    i11 = (i4 << 6) | (i12 & 63);
                    if (i5 > 1) {
                        int i13 = iArr[i9 >> 2] >> ((3 - (i9 & 3)) << 3);
                        i9++;
                        if ((i13 & 192) != 128) {
                            n(i13);
                            throw null;
                        }
                        int i14 = (i13 & 63) | (i11 << 6);
                        if (i5 > 2) {
                            int i15 = iArr[i9 >> 2] >> ((3 - (i9 & 3)) << 3);
                            i9++;
                            if ((i15 & 192) != 128) {
                                n(i15 & 255);
                                throw null;
                            }
                            i11 = (i15 & 63) | (i14 << 6);
                            i6 = 2;
                        } else {
                            i11 = i14;
                            i6 = 2;
                        }
                    } else {
                        i6 = 2;
                    }
                    if (i5 > i6) {
                        int i16 = i11 - 65536;
                        if (i10 >= cArrD.length) {
                            cArrD = this.v.e();
                        }
                        cArrD[i10] = (char) ((i16 >> 10) + 55296);
                        i11 = (i16 & 1023) | 56320;
                        i10++;
                    }
                } else {
                    c(" in field name");
                    throw null;
                }
            }
            if (i10 >= cArrD.length) {
                cArrD = this.v.e();
            }
            cArrD[i10] = (char) i11;
            i10++;
        }
        String str = new String(cArrD, 0, i10);
        if (i2 < 4) {
            iArr[i - 1] = i3;
        }
        b.d.a.a.d.a aVar = this.H;
        String strA = aVar.f154d ? b.d.a.a.e.e.f204a.a(str) : str;
        if (i < 3) {
            iA = i == 1 ? aVar.a(iArr[0]) : aVar.a(iArr[0], iArr[1]);
        } else {
            iA = aVar.a(iArr, i);
        }
        int i17 = iA;
        if (i >= 4) {
            if (i >= 4) {
                int i18 = iArr[0];
                int i19 = iArr[1];
                int i20 = iArr[2];
                int i21 = iArr[3];
                if (i - 4 > 0) {
                    iArrCopyOfRange = Arrays.copyOfRange(iArr, 4, i);
                } else {
                    iArrCopyOfRange = null;
                }
                gVar = new b.d.a.a.d.g(strA, i17, i18, i19, i20, i21, iArrCopyOfRange, i);
            } else {
                throw new IllegalArgumentException();
            }
        } else if (i == 1) {
            gVar = new b.d.a.a.d.c(strA, i17, iArr[0]);
        } else if (i == 2) {
            gVar = new b.d.a.a.d.d(strA, i17, iArr[0], iArr[1]);
        } else if (i == 3) {
            gVar = new b.d.a.a.d.e(strA, i17, iArr[0], iArr[1], iArr[2]);
        } else if (i >= 4) {
            int i110 = iArr[0];
            int i111 = iArr[1];
            int i22 = iArr[2];
            int i23 = iArr[3];
            if (i - 4 > 0) {
                iArrCopyOfRange = Arrays.copyOfRange(iArr, 4, i);
            } else {
                iArrCopyOfRange = null;
            }
            gVar = new b.d.a.a.d.g(strA, i17, i110, i111, i22, i23, iArrCopyOfRange, i);
        } else {
            throw new IllegalArgumentException();
        }
        if (aVar.o) {
            int[] iArr2 = aVar.i;
            aVar.i = Arrays.copyOf(iArr2, iArr2.length);
            r1 = 0;
            aVar.o = false;
        } else {
            r1 = 0;
        }
        if (aVar.n) {
            aVar.n = r1;
            aVar.p = r1;
            int[] iArr3 = aVar.i;
            int length = iArr3.length;
            int i24 = length + length;
            if (i24 > 65536) {
                aVar.f156f = r1;
                aVar.g = r1;
                Arrays.fill(iArr3, (int) r1);
                Arrays.fill(aVar.j, (Object) null);
                Arrays.fill(aVar.k, (Object) null);
                aVar.l = r1;
                aVar.m = r1;
            } else {
                aVar.i = new int[i24];
                aVar.h = i24 - 1;
                b.d.a.a.d.f[] fVarArr = aVar.j;
                aVar.j = new b.d.a.a.d.f[i24];
                int i25 = 0;
                for (int i26 = 0; i26 < length; i26++) {
                    b.d.a.a.d.f fVar = fVarArr[i26];
                    if (fVar != null) {
                        i25++;
                        int i27 = fVar.f184b;
                        int i28 = aVar.h & i27;
                        aVar.j[i28] = fVar;
                        aVar.i[i28] = i27 << 8;
                    }
                }
                int i29 = aVar.m;
                if (i29 == 0) {
                    aVar.g = 0;
                } else {
                    aVar.l = 0;
                    aVar.m = 0;
                    aVar.q = false;
                    b.d.a.a.d.a.C0004a[] c0004aArr = aVar.k;
                    aVar.k = new b.d.a.a.d.a.C0004a[c0004aArr.length];
                    int iMax = 0;
                    for (int i30 = 0; i30 < i29; i30++) {
                        for (b.d.a.a.d.a.C0004a c0004a = c0004aArr[i30]; c0004a != null; c0004a = c0004a.f158b) {
                            i25++;
                            b.d.a.a.d.f fVar2 = c0004a.f157a;
                            int i31 = fVar2.f184b;
                            int i32 = aVar.h & i31;
                            int[] iArr4 = aVar.i;
                            int i33 = iArr4[i32];
                            b.d.a.a.d.f[] fVarArr2 = aVar.j;
                            if (fVarArr2[i32] == null) {
                                iArr4[i32] = i31 << 8;
                                fVarArr2[i32] = fVar2;
                            } else {
                                aVar.l++;
                                int i34 = i33 & 255;
                                if (i34 == 0) {
                                    iB2 = aVar.m;
                                    if (iB2 <= 254) {
                                        aVar.m = iB2 + 1;
                                        if (iB2 >= aVar.k.length) {
                                            aVar.a();
                                        }
                                    } else {
                                        iB2 = aVar.b();
                                    }
                                    aVar.i[i32] = (i33 & InputDeviceCompat.SOURCE_ANY) | (iB2 + 1);
                                } else {
                                    iB2 = i34 - 1;
                                }
                                b.d.a.a.d.a.C0004a c0004a2 = new b.d.a.a.d.a.C0004a(fVar2, aVar.k[iB2]);
                                aVar.k[iB2] = c0004a2;
                                iMax = Math.max(iMax, c0004a2.f160d);
                            }
                        }
                    }
                    aVar.g = iMax;
                    if (i25 != aVar.f156f) {
                        StringBuilder sbA = b.a.a.a.a.a("Internal error: count after rehash ", i25, "; should be ");
                        sbA.append(aVar.f156f);
                        throw new RuntimeException(sbA.toString());
                    }
                }
            }
        }
        aVar.f156f++;
        int i35 = aVar.h & i17;
        b.d.a.a.d.f[] fVarArr3 = aVar.j;
        if (fVarArr3[i35] == null) {
            aVar.i[i35] = i17 << 8;
            if (aVar.p) {
                aVar.j = (b.d.a.a.d.f[]) Arrays.copyOf(fVarArr3, fVarArr3.length);
                aVar.p = false;
            }
            aVar.j[i35] = gVar;
        } else {
            if (aVar.q) {
                b.d.a.a.d.a.C0004a[] c0004aArr2 = aVar.k;
                if (c0004aArr2 == null) {
                    aVar.k = new b.d.a.a.d.a.C0004a[32];
                } else {
                    aVar.k = (b.d.a.a.d.a.C0004a[]) Arrays.copyOf(c0004aArr2, c0004aArr2.length);
                }
                aVar.q = false;
            }
            aVar.l++;
            int i36 = aVar.i[i35];
            int i37 = i36 & 255;
            if (i37 == 0) {
                iB = aVar.m;
                if (iB <= 254) {
                    aVar.m = iB + 1;
                    if (iB >= aVar.k.length) {
                        aVar.a();
                    }
                } else {
                    iB = aVar.b();
                }
                aVar.i[i35] = (i36 & InputDeviceCompat.SOURCE_ANY) | (iB + 1);
            } else {
                iB = i37 - 1;
            }
            b.d.a.a.d.a.C0004a c0004a3 = new b.d.a.a.d.a.C0004a(gVar, aVar.k[iB]);
            int i38 = c0004a3.f160d;
            if (i38 > 100) {
                BitSet bitSet = aVar.r;
                if (bitSet == null) {
                    aVar.r = new BitSet();
                    aVar.r.set(iB);
                } else if (bitSet.get(iB)) {
                    if (!aVar.f155e) {
                        aVar.f154d = false;
                    } else {
                        throw new IllegalStateException(b.a.a.a.a.a(b.a.a.a.a.a("Longest collision chain in symbol table (of size "), aVar.f156f, ") now exceeds maximum, ", 100, " -- suspect a DoS attack based on hash collisions"));
                    }
                } else {
                    aVar.r.set(iB);
                }
                aVar.k[iB] = null;
                aVar.f156f -= c0004a3.f160d;
                aVar.g = -1;
            } else {
                aVar.k[iB] = c0004a3;
                aVar.g = Math.max(i38, aVar.g);
            }
        }
        int length2 = aVar.i.length;
        int i39 = aVar.f156f;
        if (i39 > (length2 >> 1)) {
            int i40 = length2 >> 2;
            if (i39 > length2 - i40 || aVar.l >= i40) {
                aVar.n = true;
            }
        }
        return gVar;
    }

    public j a(int i, boolean z) throws b.d.a.a.f {
        String str;
        if (i == 73) {
            if (this.m >= this.n && !B()) {
                y();
                throw null;
            }
            byte[] bArr = this.M;
            int i2 = this.m;
            this.m = i2 + 1;
            i = bArr[i2];
            if (i == 78) {
                str = z ? "-INF" : "+INF";
            } else if (i == 110) {
                str = z ? "-Infinity" : "+Infinity";
            }
            a(str, 3);
            if (a(b.d.a.a.g.a.ALLOW_NON_NUMERIC_NUMBERS)) {
                return a(str, z ? Double.NEGATIVE_INFINITY : Double.POSITIVE_INFINITY);
            }
            throw b("Non-standard token '" + str + "': enable JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS to allow");
        }
        c(i, "expected digit (0-9) to follow minus sign, for valid numeric value");
        throw null;
    }

    public final void a(String str, int i) throws b.d.a.a.f {
        int i2;
        int length = str.length();
        if (this.m + length >= this.n) {
            int length2 = str.length();
            do {
                if ((this.m < this.n || B()) && this.M[this.m] == str.charAt(i)) {
                    this.m++;
                    i++;
                } else {
                    e(str.substring(0, i));
                    throw null;
                }
            } while (i < length2);
            if ((this.m < this.n || B()) && (i2 = this.M[this.m] & 255) >= 48 && i2 != 93 && i2 != 125) {
                a(str, i, i2);
                return;
            }
            return;
        }
        while (this.M[this.m] == str.charAt(i)) {
            this.m++;
            i++;
            if (i >= length) {
                int i3 = this.M[this.m] & 255;
                if (i3 < 48 || i3 == 93 || i3 == 125) {
                    return;
                }
                a(str, i, i3);
                return;
            }
        }
        e(str.substring(0, i));
        throw null;
    }

    public final void a(String str, int i, int i2) throws b.d.a.a.f {
        if (Character.isJavaIdentifierPart((char) e(i2))) {
            e(str.substring(0, i));
            throw null;
        }
    }

    public final int a(boolean z) throws b.d.a.a.f {
        while (true) {
            if (this.m >= this.n && !B()) {
                StringBuilder sbA = b.a.a.a.a.a("Unexpected end-of-input within/between ");
                sbA.append(this.t.b());
                sbA.append(" entries");
                throw b(sbA.toString());
            }
            byte[] bArr = this.M;
            int i = this.m;
            this.m = i + 1;
            int i2 = bArr[i] & 255;
            if (i2 > 32) {
                if (i2 == 47) {
                    I();
                } else if (i2 != 35 || !M()) {
                    if (z) {
                        return i2;
                    }
                    if (i2 != 58) {
                        if (i2 < 32) {
                            c(i2);
                            throw null;
                        }
                        a(i2, "was expecting a colon to separate field name and value");
                        throw null;
                    }
                    z = true;
                }
            } else if (i2 == 32) {
                continue;
            } else if (i2 == 10) {
                this.p++;
                this.q = this.m;
            } else if (i2 == 13) {
                H();
            } else if (i2 != 9) {
                c(i2);
                throw null;
            }
        }
    }

    public void a(String str, String str2) throws b.d.a.a.f {
        StringBuilder sb = new StringBuilder(str);
        while (true) {
            if (this.m >= this.n && !B()) {
                break;
            }
            byte[] bArr = this.M;
            int i = this.m;
            this.m = i + 1;
            char cE = (char) e(bArr[i]);
            if (!Character.isJavaIdentifierPart(cE)) {
                break;
            } else {
                sb.append(cE);
            }
        }
        StringBuilder sbA = b.a.a.a.a.a("Unrecognized token '");
        sbA.append(sb.toString());
        sbA.append("': was expecting ");
        sbA.append(str2);
        throw b(sbA.toString());
    }

    public void a(int i, int i2) throws b.d.a.a.f {
        this.m = i2;
        n(i);
        throw null;
    }

    public static int[] a(int[] iArr, int i) {
        if (iArr == null) {
            return new int[i];
        }
        return Arrays.copyOf(iArr, iArr.length + i);
    }
}
