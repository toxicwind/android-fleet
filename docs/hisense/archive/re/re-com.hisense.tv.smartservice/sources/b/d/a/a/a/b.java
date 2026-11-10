package b.d.a.a.a;

import b.d.a.a.c.d;
import b.d.a.a.e;
import b.d.a.a.e.f;
import b.d.a.a.g;
import b.d.a.a.j;
import com.google.common.primitives.UnsignedLong;
import java.math.BigDecimal;
import java.math.BigInteger;
import okhttp3.internal.http2.Http2Connection;

/* JADX INFO: loaded from: classes.dex */
public abstract class b extends c {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final BigInteger f66c = BigInteger.valueOf(-2147483648L);

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final BigInteger f67d = BigInteger.valueOf(2147483647L);

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final BigInteger f68e = BigInteger.valueOf(Long.MIN_VALUE);

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final BigInteger f69f = BigInteger.valueOf(UnsignedLong.UNSIGNED_MASK);
    public static final BigDecimal g = new BigDecimal(f68e);
    public static final BigDecimal h = new BigDecimal(f69f);
    public static final BigDecimal i = new BigDecimal(f66c);
    public static final BigDecimal j = new BigDecimal(f67d);
    public double A;
    public BigInteger B;
    public BigDecimal C;
    public boolean D;
    public int E;
    public final b.d.a.a.b.c k;
    public boolean l;
    public d t;
    public j u;
    public final f v;
    public int y;
    public long z;
    public int m = 0;
    public int n = 0;
    public long o = 0;
    public int p = 1;
    public int q = 0;
    public int r = 1;
    public int s = 0;
    public char[] w = null;
    public int x = 0;

    public b(b.d.a.a.b.c cVar, int i2) {
        this.f212a = i2;
        this.k = cVar;
        this.v = cVar.b();
        this.t = new d(null, (g.a.STRICT_DUPLICATE_DETECTION.m & i2) != 0 ? new b.d.a.a.c.b(this) : null, 0, 1, 0);
    }

    public void A() {
        f fVar = this.v;
        if (fVar.f207b == null) {
            fVar.j();
        } else if (fVar.i != null) {
            fVar.j();
            char[] cArr = fVar.i;
            fVar.i = null;
            fVar.f207b.f197d[2] = cArr;
        }
        char[] cArr2 = this.w;
        if (cArr2 != null) {
            this.w = null;
            this.k.b(cArr2);
        }
    }

    public abstract boolean B();

    public final void C() throws b.d.a.a.f {
        if (B()) {
            return;
        }
        x();
        throw null;
    }

    public void D() throws b.d.a.a.f {
        StringBuilder sbA = b.a.a.a.a.a("Numeric value (");
        sbA.append(s());
        sbA.append(") out of range of int (");
        sbA.append(Integer.MIN_VALUE);
        sbA.append(" - ");
        sbA.append(Integer.MAX_VALUE);
        sbA.append(")");
        throw b(sbA.toString());
    }

    public void E() throws b.d.a.a.f {
        StringBuilder sbA = b.a.a.a.a.a("Numeric value (");
        sbA.append(s());
        sbA.append(") out of range of long (");
        sbA.append(Long.MIN_VALUE);
        sbA.append(" - ");
        sbA.append(UnsignedLong.UNSIGNED_MASK);
        sbA.append(")");
        throw b(sbA.toString());
    }

    public void a(int i2, char c2) throws b.d.a.a.f {
        StringBuilder sbA = b.a.a.a.a.a("");
        sbA.append(this.t.a(this.k.f88a));
        String string = sbA.toString();
        StringBuilder sbA2 = b.a.a.a.a.a("Unexpected close marker '");
        sbA2.append((char) i2);
        sbA2.append("': expected '");
        sbA2.append(c2);
        sbA2.append("' (for ");
        sbA2.append(this.t.b());
        sbA2.append(" starting at ");
        sbA2.append(string);
        sbA2.append(")");
        throw b(sbA2.toString());
    }

    public void c(int i2, String str) throws b.d.a.a.f {
        StringBuilder sbA = b.a.a.a.a.a("Unexpected character (");
        sbA.append(c.b(i2));
        sbA.append(") in numeric value");
        String string = sbA.toString();
        if (str != null) {
            string = b.a.a.a.a.a(string, ": ", str);
        }
        throw b(string);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        if (this.l) {
            return;
        }
        this.l = true;
        try {
            z();
        } finally {
            A();
        }
    }

    /* JADX WARN: Code duplicated, block: B:52:0x00a6 A[Catch: NumberFormatException -> 0x00ba, TryCatch #1 {NumberFormatException -> 0x00ba, blocks: (B:34:0x007c, B:36:0x0080, B:38:0x0085, B:52:0x00a6, B:53:0x00af, B:45:0x0092, B:49:0x00a0, B:37:0x0083), top: B:85:0x007c }] */
    /* JADX WARN: Code duplicated, block: B:53:0x00af A[Catch: NumberFormatException -> 0x00ba, TRY_LEAVE, TryCatch #1 {NumberFormatException -> 0x00ba, blocks: (B:34:0x007c, B:36:0x0080, B:38:0x0085, B:52:0x00a6, B:53:0x00af, B:45:0x0092, B:49:0x00a0, B:37:0x0083), top: B:85:0x007c }] */
    public void d(int i2) throws b.d.a.a.f {
        BigDecimal bigDecimalA;
        char[] cArr;
        char[] cArr2;
        j jVar = this.f70b;
        boolean z = false;
        if (jVar != j.VALUE_NUMBER_INT) {
            if (jVar != j.VALUE_NUMBER_FLOAT) {
                StringBuilder sbA = b.a.a.a.a.a("Current token (");
                sbA.append(this.f70b);
                sbA.append(") not numeric, can not use numeric value accessors");
                throw b(sbA.toString());
            }
            try {
                if (i2 != 16) {
                    this.A = b.d.a.a.b.f.a(this.v.c());
                    this.x = 8;
                    return;
                }
                f fVar = this.v;
                char[] cArr3 = fVar.l;
                if (cArr3 != null) {
                    bigDecimalA = b.d.a.a.b.f.a(cArr3);
                } else {
                    int i3 = fVar.f209d;
                    if (i3 < 0 || (cArr2 = fVar.f208c) == null) {
                        bigDecimalA = (fVar.h != 0 || (cArr = fVar.i) == null) ? b.d.a.a.b.f.a(fVar.b()) : b.d.a.a.b.f.a(cArr, 0, fVar.j);
                    } else {
                        bigDecimalA = b.d.a.a.b.f.a(cArr2, i3, fVar.f210e);
                    }
                }
                this.C = bigDecimalA;
                this.x = 16;
                return;
            } catch (NumberFormatException e2) {
                StringBuilder sbA2 = b.a.a.a.a.a("Malformed numeric value '");
                sbA2.append(this.v.c());
                sbA2.append("'");
                a(sbA2.toString(), e2);
                throw null;
            }
        }
        char[] cArrH = this.v.h();
        int i4 = this.v.i();
        int i5 = this.E;
        if (this.D) {
            i4++;
        }
        if (i5 <= 9) {
            int iB = b.d.a.a.b.f.b(cArrH, i4, i5);
            if (this.D) {
                iB = -iB;
            }
            this.y = iB;
            this.x = 1;
            return;
        }
        if (i5 <= 18) {
            int i6 = i5 - 9;
            long jB = (((long) b.d.a.a.b.f.b(cArrH, i4, i6)) * Http2Connection.DEGRADED_PONG_TIMEOUT_NS) + ((long) b.d.a.a.b.f.b(cArrH, i4 + i6, 9));
            if (this.D) {
                jB = -jB;
            }
            if (i5 == 10) {
                if (this.D) {
                    if (jB >= -2147483648L) {
                        this.y = (int) jB;
                        this.x = 1;
                        return;
                    }
                } else if (jB <= 2147483647L) {
                    this.y = (int) jB;
                    this.x = 1;
                    return;
                }
            }
            this.z = jB;
            this.x = 2;
            return;
        }
        String strC = this.v.c();
        try {
            String str = this.D ? b.d.a.a.b.f.f102a : b.d.a.a.b.f.f103b;
            int length = str.length();
            if (i5 >= length) {
                if (i5 <= length) {
                    int i7 = 0;
                    while (true) {
                        if (i7 < length) {
                            int iCharAt = cArrH[i4 + i7] - str.charAt(i7);
                            if (iCharAt == 0) {
                                i7++;
                            } else if (iCharAt >= 0) {
                                break;
                            }
                        }
                    }
                }
                if (z) {
                    this.z = Long.parseLong(strC);
                    this.x = 2;
                } else {
                    this.B = new BigInteger(strC);
                    this.x = 4;
                }
            }
            z = true;
            if (z) {
                this.z = Long.parseLong(strC);
                this.x = 2;
            } else {
                this.B = new BigInteger(strC);
                this.x = 4;
            }
        } catch (NumberFormatException e3) {
            a("Malformed numeric value '" + strC + "'", e3);
            throw null;
        }
    }

    @Override // b.d.a.a.g
    public e p() {
        int i2 = this.m;
        return new e(this.k.f88a, -1L, this.o + ((long) i2), this.p, (i2 - this.q) + 1);
    }

    @Override // b.d.a.a.g
    public double q() throws b.d.a.a.f {
        int i2 = this.x;
        if ((i2 & 8) == 0) {
            if (i2 == 0) {
                d(8);
            }
            int i3 = this.x;
            if ((i3 & 8) == 0) {
                if ((i3 & 16) != 0) {
                    this.A = this.C.doubleValue();
                } else if ((i3 & 4) != 0) {
                    this.A = this.B.doubleValue();
                } else if ((i3 & 2) != 0) {
                    this.A = this.z;
                } else {
                    if ((i3 & 1) == 0) {
                        b.d.a.a.e.g.a();
                        throw null;
                    }
                    this.A = this.y;
                }
                this.x |= 8;
            }
        }
        return this.A;
    }

    @Override // b.d.a.a.g
    public long r() throws b.d.a.a.f {
        int i2 = this.x;
        if ((i2 & 2) == 0) {
            if (i2 == 0) {
                d(2);
            }
            int i3 = this.x;
            if ((i3 & 2) == 0) {
                if ((i3 & 1) != 0) {
                    this.z = this.y;
                } else if ((i3 & 4) != 0) {
                    if (f68e.compareTo(this.B) > 0 || f69f.compareTo(this.B) < 0) {
                        E();
                        throw null;
                    }
                    this.z = this.B.longValue();
                } else if ((i3 & 8) != 0) {
                    double d2 = this.A;
                    if (d2 < -9.223372036854776E18d || d2 > 9.223372036854776E18d) {
                        E();
                        throw null;
                    }
                    this.z = (long) d2;
                } else {
                    if ((i3 & 16) == 0) {
                        b.d.a.a.e.g.a();
                        throw null;
                    }
                    if (g.compareTo(this.C) > 0 || h.compareTo(this.C) < 0) {
                        E();
                        throw null;
                    }
                    this.z = this.C.longValue();
                }
                this.x |= 2;
            }
        }
        return this.z;
    }

    @Override // b.d.a.a.a.c
    public void w() throws b.d.a.a.f {
        if (this.t.e()) {
            return;
        }
        StringBuilder sbA = b.a.a.a.a.a(": expected close marker for ");
        sbA.append(this.t.b());
        sbA.append(" (from ");
        sbA.append(this.t.a(this.k.f88a));
        sbA.append(")");
        c(sbA.toString());
        throw null;
    }

    public abstract void z();

    public final j a(boolean z, int i2) {
        this.D = z;
        this.E = i2;
        this.x = 0;
        return j.VALUE_NUMBER_INT;
    }

    public final j a(String str, double d2) {
        f fVar = this.v;
        fVar.f208c = null;
        fVar.f209d = -1;
        fVar.f210e = 0;
        fVar.k = str;
        fVar.l = null;
        if (fVar.g) {
            fVar.a();
        }
        fVar.j = 0;
        this.A = d2;
        this.x = 8;
        return j.VALUE_NUMBER_FLOAT;
    }

    public void d(String str) throws b.d.a.a.f {
        throw b("Invalid numeric value: " + str);
    }
}
