package c.a.c.a.a;

import c.a.b.C0149mb;
import c.a.c.r;
import c.a.c.s;
import c.a.za;
import com.google.common.base.Ascii;
import com.google.errorprone.annotations.FormatMethod;
import d.C;
import d.E;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/* JADX INFO: loaded from: classes2.dex */
public final class g implements j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Logger f1592a = Logger.getLogger(b.class.getName());

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final d.j f1593b = d.j.d("PRI * HTTP/2.0\r\n\r\nSM\r\n\r\n");

    static final class a implements C {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final d.i f1594a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f1595b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public byte f1596c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f1597d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f1598e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public short f1599f;

        public a(d.i iVar) {
            this.f1594a = iVar;
        }

        @Override // d.C, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
        }

        @Override // d.C
        public long read(d.g gVar, long j) throws IOException {
            int i;
            do {
                int i2 = this.f1598e;
                if (i2 != 0) {
                    long j2 = this.f1594a.read(gVar, Math.min(j, i2));
                    if (j2 == -1) {
                        return -1L;
                    }
                    this.f1598e -= (int) j2;
                    return j2;
                }
                this.f1594a.skip(this.f1599f);
                this.f1599f = (short) 0;
                if ((this.f1596c & 4) != 0) {
                    return -1L;
                }
                i = this.f1597d;
                int iA = g.a(this.f1594a);
                this.f1598e = iA;
                this.f1595b = iA;
                byte b2 = (byte) (this.f1594a.readByte() & 255);
                this.f1596c = (byte) (this.f1594a.readByte() & 255);
                if (g.f1592a.isLoggable(Level.FINE)) {
                    g.f1592a.fine(b.a(true, this.f1597d, this.f1595b, b2, this.f1596c));
                }
                this.f1597d = this.f1594a.readInt() & Integer.MAX_VALUE;
                if (b2 != 9) {
                    g.b("%s != TYPE_CONTINUATION", Byte.valueOf(b2));
                    throw null;
                }
            } while (this.f1597d == i);
            g.b("TYPE_CONTINUATION streamId changed", new Object[0]);
            throw null;
        }

        @Override // d.C
        public E timeout() {
            return this.f1594a.timeout();
        }
    }

    static final class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final String[] f1600a = {"DATA", "HEADERS", "PRIORITY", "RST_STREAM", "SETTINGS", "PUSH_PROMISE", "PING", "GOAWAY", "WINDOW_UPDATE", "CONTINUATION"};

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public static final String[] f1601b = new String[64];

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public static final String[] f1602c = new String[256];

        static {
            int i = 0;
            int i2 = 0;
            while (true) {
                String[] strArr = f1602c;
                if (i2 >= strArr.length) {
                    break;
                }
                strArr[i2] = String.format("%8s", Integer.toBinaryString(i2)).replace(Ascii.CASE_MASK, '0');
                i2++;
            }
            String[] strArr2 = f1601b;
            strArr2[0] = "";
            strArr2[1] = "END_STREAM";
            int[] iArr = {1};
            strArr2[8] = "PADDED";
            for (int i3 : iArr) {
                f1601b[i3 | 8] = b.a.a.a.a.a(new StringBuilder(), f1601b[i3], "|PADDED");
            }
            String[] strArr3 = f1601b;
            strArr3[4] = "END_HEADERS";
            strArr3[32] = "PRIORITY";
            strArr3[36] = "END_HEADERS|PRIORITY";
            for (int i4 : new int[]{4, 32, 36}) {
                for (int i5 : iArr) {
                    int i6 = i5 | i4;
                    f1601b[i6] = f1601b[i5] + '|' + f1601b[i4];
                    StringBuilder sb = new StringBuilder();
                    sb.append(f1601b[i5]);
                    sb.append('|');
                    f1601b[i6 | 8] = b.a.a.a.a.a(sb, f1601b[i4], "|PADDED");
                }
            }
            while (true) {
                String[] strArr4 = f1601b;
                if (i >= strArr4.length) {
                    return;
                }
                if (strArr4[i] == null) {
                    strArr4[i] = f1602c[i];
                }
                i++;
            }
        }

        /* JADX WARN: Code duplicated, block: B:34:0x0067  */
        public static String a(boolean z, int i, int i2, byte b2, byte b3) {
            String strReplace;
            String[] strArr = f1600a;
            String str = b2 < strArr.length ? strArr[b2] : String.format("0x%02x", Byte.valueOf(b2));
            if (b3 == 0) {
                strReplace = "";
            } else if (b2 == 2 || b2 == 3) {
                strReplace = f1602c[b3];
            } else if (b2 == 4 || b2 == 6) {
                strReplace = b3 == 1 ? "ACK" : f1602c[b3];
            } else if (b2 == 7 || b2 == 8) {
                strReplace = f1602c[b3];
            } else {
                String[] strArr2 = f1601b;
                String str2 = b3 < strArr2.length ? strArr2[b3] : f1602c[b3];
                if (b2 != 5 || (b3 & 4) == 0) {
                    strReplace = (b2 != 0 || (b3 & 32) == 0) ? str2 : str2.replace("PRIORITY", "COMPRESSED");
                } else {
                    strReplace = str2.replace("HEADERS", "PUSH_PROMISE");
                }
            }
            Object[] objArr = new Object[5];
            objArr[0] = z ? "<<" : ">>";
            objArr[1] = Integer.valueOf(i);
            objArr[2] = Integer.valueOf(i2);
            objArr[3] = str;
            objArr[4] = strReplace;
            return String.format("%s 0x%08x %5d %-13s %s", objArr);
        }
    }

    @FormatMethod
    public static IOException b(String str, Object... objArr) throws IOException {
        throw new IOException(String.format(str, objArr));
    }

    public c.a.c.a.a.b a(d.i iVar, boolean z) {
        return new c(iVar, 4096, z);
    }

    public static /* synthetic */ IllegalArgumentException a(String str, Object[] objArr) {
        throw new IllegalArgumentException(String.format(str, objArr));
    }

    public static /* synthetic */ int a(int i, byte b2, short s) throws IOException {
        if ((b2 & 8) != 0) {
            i--;
        }
        if (s <= i) {
            return (short) (i - s);
        }
        b("PROTOCOL_ERROR padding %s > remaining length %s", Short.valueOf(s), Integer.valueOf(i));
        throw null;
    }

    public static /* synthetic */ int a(d.i iVar) {
        return (iVar.readByte() & 255) | ((iVar.readByte() & 255) << 16) | ((iVar.readByte() & 255) << 8);
    }

    public static /* synthetic */ void a(d.h hVar, int i) {
        hVar.writeByte((i >>> 16) & 255);
        hVar.writeByte((i >>> 8) & 255);
        hVar.writeByte(i & 255);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final class d implements c.a.c.a.a.c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final d.h f1606a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final boolean f1607b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final d.g f1608c = new d.g();

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final f.b f1609d = new f.b(this.f1608c);

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f1610e = 16384;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public boolean f1611f;

        public d(d.h hVar, boolean z) {
            this.f1606a = hVar;
            this.f1607b = z;
        }

        public void a(int i, int i2, byte b2, byte b3) {
            if (g.f1592a.isLoggable(Level.FINE)) {
                g.f1592a.fine(b.a(false, i, i2, b2, b3));
            }
            int i3 = this.f1610e;
            if (i2 > i3) {
                g.a("FRAME_SIZE_ERROR length > %d: %d", new Object[]{Integer.valueOf(i3), Integer.valueOf(i2)});
                throw null;
            }
            if ((Integer.MIN_VALUE & i) != 0) {
                g.a("reserved bit set: %s", new Object[]{Integer.valueOf(i)});
                throw null;
            }
            g.a(this.f1606a, i2);
            this.f1606a.writeByte(b2 & 255);
            this.f1606a.writeByte(b3 & 255);
            this.f1606a.writeInt(i & Integer.MAX_VALUE);
        }

        @Override // c.a.c.a.a.c
        public synchronized void b(i iVar) {
            int i;
            if (this.f1611f) {
                throw new IOException("closed");
            }
            int i2 = 0;
            a(0, Integer.bitCount(iVar.f1619a) * 6, (byte) 4, (byte) 0);
            while (i2 < 10) {
                if (iVar.a(i2)) {
                    if (i2 == 4) {
                        i = 3;
                    } else {
                        i = i2 == 7 ? 4 : i2;
                    }
                    this.f1606a.writeShort(i);
                    this.f1606a.writeInt(iVar.f1622d[i2]);
                }
                i2++;
            }
            this.f1606a.flush();
        }

        @Override // java.io.Closeable, java.lang.AutoCloseable
        public synchronized void close() {
            this.f1611f = true;
            this.f1606a.close();
        }

        @Override // c.a.c.a.a.c
        public synchronized void connectionPreface() {
            if (this.f1611f) {
                throw new IOException("closed");
            }
            if (this.f1607b) {
                if (g.f1592a.isLoggable(Level.FINE)) {
                    g.f1592a.fine(String.format(">> CONNECTION %s", g.f1593b.b()));
                }
                this.f1606a.write(g.f1593b.i());
                this.f1606a.flush();
            }
        }

        @Override // c.a.c.a.a.c
        public synchronized void data(boolean z, int i, d.g gVar, int i2) {
            if (this.f1611f) {
                throw new IOException("closed");
            }
            a(i, i2, (byte) 0, z ? (byte) 1 : (byte) 0);
            if (i2 > 0) {
                this.f1606a.write(gVar, i2);
            }
        }

        @Override // c.a.c.a.a.c
        public synchronized void flush() {
            if (this.f1611f) {
                throw new IOException("closed");
            }
            this.f1606a.flush();
        }

        @Override // c.a.c.a.a.c
        public int maxDataLength() {
            return this.f1610e;
        }

        @Override // c.a.c.a.a.c
        public synchronized void ping(boolean z, int i, int i2) {
            if (this.f1611f) {
                throw new IOException("closed");
            }
            a(0, 8, (byte) 6, z ? (byte) 1 : (byte) 0);
            this.f1606a.writeInt(i);
            this.f1606a.writeInt(i2);
            this.f1606a.flush();
        }

        @Override // c.a.c.a.a.c
        public synchronized void windowUpdate(int i, long j) {
            if (this.f1611f) {
                throw new IOException("closed");
            }
            if (j == 0 || j > 2147483647L) {
                g.a("windowSizeIncrement == 0 || windowSizeIncrement > 0x7fffffffL: %s", new Object[]{Long.valueOf(j)});
                throw null;
            }
            a(i, 4, (byte) 8, (byte) 0);
            this.f1606a.writeInt((int) j);
            this.f1606a.flush();
        }

        @Override // c.a.c.a.a.c
        public synchronized void a(i iVar) {
            if (!this.f1611f) {
                int i = this.f1610e;
                if ((iVar.f1619a & 32) != 0) {
                    i = iVar.f1622d[5];
                }
                this.f1610e = i;
                a(0, 0, (byte) 4, (byte) 1);
                this.f1606a.flush();
            } else {
                throw new IOException("closed");
            }
        }

        @Override // c.a.c.a.a.c
        public synchronized void a(boolean z, boolean z2, int i, int i2, List<c.a.c.a.a.d> list) {
            try {
                if (!z2) {
                    if (!this.f1611f) {
                        a(z, i, list);
                    } else {
                        throw new IOException("closed");
                    }
                } else {
                    throw new UnsupportedOperationException();
                }
            } catch (Throwable th) {
                throw th;
            }
        }

        /* JADX WARN: Code duplicated, block: B:25:0x0074  */
        /* JADX WARN: Code duplicated, block: B:29:0x007a  */
        /* JADX WARN: Code duplicated, block: B:32:0x0082  */
        /* JADX WARN: Code duplicated, block: B:34:0x008c  */
        /* JADX WARN: Code duplicated, block: B:37:0x00a1 A[DONT_INVERT] */
        /* JADX WARN: Code duplicated, block: B:38:0x00a3  */
        /* JADX WARN: Code duplicated, block: B:41:0x00b0  */
        /* JADX WARN: Code duplicated, block: B:42:0x00b8  */
        /* JADX WARN: Code duplicated, block: B:44:0x00bc  */
        /* JADX WARN: Code duplicated, block: B:45:0x00cb  */
        /* JADX WARN: Code duplicated, block: B:50:0x00e4  */
        /* JADX WARN: Code duplicated, block: B:76:0x0098 A[SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:77:0x00ae A[EDGE_INSN: B:77:0x00ae->B:40:0x00ae BREAK  A[LOOP:1: B:30:0x007c->B:39:0x00ac], SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:79:0x00ac A[SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:80:0x00ac A[SYNTHETIC] */
        public void a(boolean z, int i, List<c.a.c.a.a.d> list) throws IOException {
            int length;
            int length2;
            int i2;
            c.a.c.a.a.d[] dVarArr;
            if (!this.f1611f) {
                f.b bVar = this.f1609d;
                if (bVar.f1589d) {
                    int i3 = bVar.f1588c;
                    if (i3 < bVar.f1590e) {
                        bVar.a(i3, 31, 32);
                    }
                    bVar.f1589d = false;
                    bVar.f1588c = Integer.MAX_VALUE;
                    bVar.a(bVar.f1590e, 31, 32);
                }
                int size = list.size();
                int i4 = 0;
                while (true) {
                    int i5 = 1;
                    if (i4 >= size) {
                        break;
                    }
                    c.a.c.a.a.d dVar = list.get(i4);
                    d.j jVarH = dVar.f1571f.h();
                    d.j jVar = dVar.g;
                    Integer num = f.f1579c.get(jVarH);
                    if (num != null) {
                        length = num.intValue() + 1;
                        if (length < 2 || length > 7) {
                            length2 = -1;
                        } else if (!f.f1578b[length - 1].g.equals(jVar)) {
                            if (f.f1578b[length].g.equals(jVar)) {
                                length2 = length + 1;
                            } else {
                                length2 = -1;
                            }
                        }
                        if (length2 == -1) {
                            i2 = bVar.h;
                            while (true) {
                                i2 += i5;
                                dVarArr = bVar.f1591f;
                                if (i2 >= dVarArr.length) {
                                    break;
                                }
                                if (!dVarArr[i2].f1571f.equals(jVarH)) {
                                    if (bVar.f1591f[i2].g.equals(jVar)) {
                                        length2 = f.f1578b.length + (i2 - bVar.h);
                                        break;
                                    } else if (length == -1) {
                                        length = (i2 - bVar.h) + f.f1578b.length;
                                    }
                                }
                                i5 = 1;
                            }
                        }
                        if (length2 != -1) {
                            bVar.a(length2, 127, 128);
                        } else if (length == -1) {
                            bVar.f1586a.writeByte(64);
                            bVar.a(jVarH);
                            bVar.a(jVar);
                            bVar.a(dVar);
                        } else if (!jVarH.a(f.f1577a) && !c.a.c.a.a.d.f1570e.equals(jVarH)) {
                            bVar.a(length, 15, 0);
                            bVar.a(jVar);
                        } else {
                            bVar.a(length, 63, 64);
                            bVar.a(jVar);
                            bVar.a(dVar);
                        }
                        i4++;
                    } else {
                        length = -1;
                    }
                    length2 = length;
                    if (length2 == -1) {
                        i2 = bVar.h;
                        while (true) {
                            i2 += i5;
                            dVarArr = bVar.f1591f;
                            if (i2 >= dVarArr.length) {
                                break;
                                break;
                            }
                            if (!dVarArr[i2].f1571f.equals(jVarH)) {
                                if (bVar.f1591f[i2].g.equals(jVar)) {
                                    length2 = f.f1578b.length + (i2 - bVar.h);
                                    break;
                                } else if (length == -1) {
                                    length = (i2 - bVar.h) + f.f1578b.length;
                                }
                            }
                            i5 = 1;
                        }
                    }
                    if (length2 != -1) {
                        bVar.a(length2, 127, 128);
                    } else if (length == -1) {
                        bVar.f1586a.writeByte(64);
                        bVar.a(jVarH);
                        bVar.a(jVar);
                        bVar.a(dVar);
                    } else if (!jVarH.a(f.f1577a)) {
                        bVar.a(length, 63, 64);
                        bVar.a(jVar);
                        bVar.a(dVar);
                    } else {
                        bVar.a(length, 63, 64);
                        bVar.a(jVar);
                        bVar.a(dVar);
                    }
                    i4++;
                }
                long j = this.f1608c.f2102c;
                int iMin = (int) Math.min(this.f1610e, j);
                long j2 = iMin;
                byte b2 = j == j2 ? (byte) 4 : (byte) 0;
                if (z) {
                    b2 = (byte) (b2 | 1);
                }
                a(i, iMin, (byte) 1, b2);
                this.f1606a.write(this.f1608c, j2);
                if (j > j2) {
                    long j3 = j - j2;
                    while (j3 > 0) {
                        int iMin2 = (int) Math.min(this.f1610e, j3);
                        long j4 = iMin2;
                        j3 -= j4;
                        a(i, iMin2, (byte) 9, j3 == 0 ? (byte) 4 : (byte) 0);
                        this.f1606a.write(this.f1608c, j4);
                    }
                    return;
                }
                return;
            }
            throw new IOException("closed");
        }

        @Override // c.a.c.a.a.c
        public synchronized void a(int i, c.a.c.a.a.a aVar) {
            if (!this.f1611f) {
                if (aVar.t != -1) {
                    a(i, 4, (byte) 3, (byte) 0);
                    this.f1606a.writeInt(aVar.t);
                    this.f1606a.flush();
                } else {
                    throw new IllegalArgumentException();
                }
            } else {
                throw new IOException("closed");
            }
        }

        @Override // c.a.c.a.a.c
        public synchronized void a(int i, c.a.c.a.a.a aVar, byte[] bArr) {
            if (!this.f1611f) {
                if (aVar.t != -1) {
                    a(0, bArr.length + 8, (byte) 7, (byte) 0);
                    this.f1606a.writeInt(i);
                    this.f1606a.writeInt(aVar.t);
                    if (bArr.length > 0) {
                        this.f1606a.write(bArr);
                    }
                    this.f1606a.flush();
                } else {
                    g.a("errorCode.httpCode == -1", new Object[0]);
                    throw null;
                }
            } else {
                throw new IOException("closed");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final class c implements c.a.c.a.a.b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final d.i f1603a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final a f1604b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final f.a f1605c;

        public c(d.i iVar, int i, boolean z) {
            this.f1603a = iVar;
            this.f1604b = new a(this.f1603a);
            this.f1605c = new f.a(i, this.f1604b);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public boolean a(c.a.c.a.a.b.a aVar) throws IOException {
            try {
                this.f1603a.c(9L);
                int iA = g.a(this.f1603a);
                if (iA < 0 || iA > 16384) {
                    g.b("FRAME_SIZE_ERROR: %s", Integer.valueOf(iA));
                    throw null;
                }
                byte b2 = (byte) (this.f1603a.readByte() & 255);
                byte b3 = (byte) (this.f1603a.readByte() & 255);
                int i = this.f1603a.readInt() & Integer.MAX_VALUE;
                if (g.f1592a.isLoggable(Level.FINE)) {
                    g.f1592a.fine(b.a(true, i, iA, b2, b3));
                }
                switch (b2) {
                    case 0:
                        boolean z = (b3 & 1) != 0;
                        if (((b3 & 32) != 0) == true) {
                            g.b("PROTOCOL_ERROR: FLAG_COMPRESSED without SETTINGS_COMPRESS_DATA", new Object[0]);
                            throw null;
                        }
                        short s = (b3 & 8) != 0 ? (short) (this.f1603a.readByte() & 255) : (short) 0;
                        ((r.a) aVar).a(z, i, this.f1603a, g.a(iA, b3, s));
                        this.f1603a.skip(s);
                        return true;
                    case 1:
                        if (i == 0) {
                            g.b("PROTOCOL_ERROR: TYPE_HEADERS streamId == 0", new Object[0]);
                            throw null;
                        }
                        boolean z2 = (b3 & 1) != 0;
                        short s2 = (b3 & 8) != 0 ? (short) (this.f1603a.readByte() & 255) : (short) 0;
                        if ((b3 & 32) != 0) {
                            a(aVar, i);
                            iA -= 5;
                        }
                        ((r.a) aVar).a(false, z2, i, -1, a(g.a(iA, b3, s2), s2, b3, i), e.HTTP_20_HEADERS);
                        return true;
                    case 2:
                        if (iA != 5) {
                            g.b("TYPE_PRIORITY length: %d != 5", Integer.valueOf(iA));
                            throw null;
                        }
                        if (i != 0) {
                            a(aVar, i);
                            return true;
                        }
                        g.b("TYPE_PRIORITY streamId == 0", new Object[0]);
                        throw null;
                    case 3:
                        if (iA != 4) {
                            g.b("TYPE_RST_STREAM length: %d != 4", Integer.valueOf(iA));
                            throw null;
                        }
                        if (i == 0) {
                            g.b("TYPE_RST_STREAM streamId == 0", new Object[0]);
                            throw null;
                        }
                        int i2 = this.f1603a.readInt();
                        c.a.c.a.a.a aVarA = c.a.c.a.a.a.a(i2);
                        if (aVarA != null) {
                            ((r.a) aVar).a(i, aVarA);
                            return true;
                        }
                        g.b("TYPE_RST_STREAM unexpected error code: %d", Integer.valueOf(i2));
                        throw null;
                    case 4:
                        if (i != 0) {
                            g.b("TYPE_SETTINGS streamId != 0", new Object[0]);
                            throw null;
                        }
                        if ((b3 & 1) != 0) {
                            if (iA != 0) {
                                g.b("FRAME_SIZE_ERROR ack frame should be empty!", new Object[0]);
                                throw null;
                            }
                            ((r.a) aVar).a();
                        } else {
                            if (iA % 6 != 0) {
                                g.b("TYPE_SETTINGS length %% 6 != 0: %s", Integer.valueOf(iA));
                                throw null;
                            }
                            i iVar = new i();
                            for (int i3 = 0; i3 < iA; i3 += 6) {
                                int i4 = this.f1603a.readShort();
                                int i5 = this.f1603a.readInt();
                                switch (i4) {
                                    case 1:
                                    case 6:
                                        break;
                                    case 2:
                                        if (i5 != 0 && i5 != 1) {
                                            g.b("PROTOCOL_ERROR SETTINGS_ENABLE_PUSH != 0 or 1", new Object[0]);
                                            throw null;
                                        }
                                        break;
                                    case 3:
                                        i4 = 4;
                                        break;
                                    case 4:
                                        i4 = 7;
                                        if (i5 < 0) {
                                            g.b("PROTOCOL_ERROR SETTINGS_INITIAL_WINDOW_SIZE > 2^31 - 1", new Object[0]);
                                            throw null;
                                        }
                                        break;
                                    case 5:
                                        if (i5 < 16384 || i5 > 16777215) {
                                            g.b("PROTOCOL_ERROR SETTINGS_MAX_FRAME_SIZE: %s", Integer.valueOf(i5));
                                            throw null;
                                        }
                                        break;
                                    default:
                                        continue;
                                        break;
                                }
                                iVar.a(i4, 0, i5);
                            }
                            ((r.a) aVar).a(false, iVar);
                            if (((iVar.f1619a & 2) != 0 ? iVar.f1622d[1] : -1) >= 0) {
                                f.a aVar2 = this.f1605c;
                                int i6 = (iVar.f1619a & 2) != 0 ? iVar.f1622d[1] : -1;
                                aVar2.f1582c = i6;
                                aVar2.f1583d = i6;
                                int i7 = aVar2.f1583d;
                                int i8 = aVar2.h;
                                if (i7 < i8) {
                                    if (i7 == 0) {
                                        aVar2.b();
                                    } else {
                                        aVar2.b(i8 - i7);
                                    }
                                }
                            }
                        }
                        return true;
                    case 5:
                        if (i == 0) {
                            g.b("PROTOCOL_ERROR: TYPE_PUSH_PROMISE streamId == 0", new Object[0]);
                            throw null;
                        }
                        short s3 = (b3 & 8) != 0 ? (short) (this.f1603a.readByte() & 255) : (short) 0;
                        ((r.a) aVar).a(i, this.f1603a.readInt() & Integer.MAX_VALUE, a(g.a(iA - 4, b3, s3), s3, b3, i));
                        return true;
                    case 6:
                        if (iA != 8) {
                            g.b("TYPE_PING length != 8: %s", Integer.valueOf(iA));
                            throw null;
                        }
                        if (i == 0) {
                            ((r.a) aVar).a((b3 & 1) != 0, this.f1603a.readInt(), this.f1603a.readInt());
                            return true;
                        }
                        g.b("TYPE_PING streamId != 0", new Object[0]);
                        throw null;
                    case 7:
                        if (iA < 8) {
                            g.b("TYPE_GOAWAY length < 8: %s", Integer.valueOf(iA));
                            throw null;
                        }
                        if (i != 0) {
                            g.b("TYPE_GOAWAY streamId != 0", new Object[0]);
                            throw null;
                        }
                        int i9 = this.f1603a.readInt();
                        int i10 = this.f1603a.readInt();
                        int i11 = iA - 8;
                        c.a.c.a.a.a aVarA2 = c.a.c.a.a.a.a(i10);
                        if (aVarA2 == null) {
                            g.b("TYPE_GOAWAY unexpected error code: %d", Integer.valueOf(i10));
                            throw null;
                        }
                        d.j jVarD = d.j.f2110b;
                        if (i11 > 0) {
                            jVarD = this.f1603a.d(i11);
                        }
                        r.a aVar3 = (r.a) aVar;
                        aVar3.f1727a.a(s.a.INBOUND, i9, aVarA2, jVarD);
                        if (aVarA2 == c.a.c.a.a.a.ENHANCE_YOUR_CALM) {
                            String strJ = jVarD.j();
                            r.f1722b.log(Level.WARNING, String.format("%s: Received GOAWAY with ENHANCE_YOUR_CALM. Debug data: %s", aVar3, strJ));
                            if ("too_many_pings".equals(strJ)) {
                                r.this.Q.run();
                            }
                        }
                        long j = aVarA2.t;
                        C0149mb.b[] bVarArr = C0149mb.b.o;
                        C0149mb.b bVar = (j >= ((long) bVarArr.length) || j < 0) ? null : bVarArr[(int) j];
                        za zaVarA = (bVar == null ? za.a(C0149mb.b.INTERNAL_ERROR.r.p.s).b("Unrecognized HTTP/2 error code: " + j) : bVar.r).a("Received Goaway");
                        if (jVarD.g() > 0) {
                            zaVarA = zaVarA.a(jVarD.j());
                        }
                        r.this.a(i9, (c.a.c.a.a.a) null, zaVarA);
                        return true;
                    case 8:
                        if (iA != 4) {
                            g.b("TYPE_WINDOW_UPDATE length !=4: %s", Integer.valueOf(iA));
                            throw null;
                        }
                        long j2 = ((long) this.f1603a.readInt()) & 2147483647L;
                        if (j2 != 0) {
                            ((r.a) aVar).a(i, j2);
                            return true;
                        }
                        g.b("windowSizeIncrement was 0", new Object[0]);
                        throw null;
                    default:
                        this.f1603a.skip(iA);
                        return true;
                }
            } catch (IOException unused) {
                return false;
            }
        }

        @Override // java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            this.f1603a.close();
        }

        public final List<c.a.c.a.a.d> a(int i, short s, byte b2, int i2) throws IOException {
            a aVar = this.f1604b;
            aVar.f1598e = i;
            aVar.f1595b = i;
            aVar.f1599f = s;
            aVar.f1596c = b2;
            aVar.f1597d = i2;
            f.a aVar2 = this.f1605c;
            while (!aVar2.f1581b.e()) {
                int i3 = aVar2.f1581b.readByte() & 255;
                if (i3 == 128) {
                    throw new IOException("index == 0");
                }
                if ((i3 & 128) == 128) {
                    int iA = aVar2.a(i3, 127) - 1;
                    if (aVar2.d(iA)) {
                        aVar2.f1580a.add(f.f1578b[iA]);
                    } else {
                        int iA2 = aVar2.a(iA - f.f1578b.length);
                        if (iA2 >= 0) {
                            c.a.c.a.a.d[] dVarArr = aVar2.f1584e;
                            if (iA2 <= dVarArr.length - 1) {
                                aVar2.f1580a.add(dVarArr[iA2]);
                            }
                        }
                        StringBuilder sbA = b.a.a.a.a.a("Header index too large ");
                        sbA.append(iA + 1);
                        throw new IOException(sbA.toString());
                    }
                } else if (i3 == 64) {
                    d.j jVarD = aVar2.d();
                    f.a(jVarD);
                    aVar2.a(-1, new c.a.c.a.a.d(jVarD, aVar2.d()));
                } else if ((i3 & 64) == 64) {
                    aVar2.a(-1, new c.a.c.a.a.d(aVar2.c(aVar2.a(i3, 63) - 1), aVar2.d()));
                } else if ((i3 & 32) == 32) {
                    aVar2.f1583d = aVar2.a(i3, 31);
                    int i4 = aVar2.f1583d;
                    if (i4 >= 0 && i4 <= aVar2.f1582c) {
                        aVar2.a();
                    } else {
                        StringBuilder sbA2 = b.a.a.a.a.a("Invalid dynamic table size update ");
                        sbA2.append(aVar2.f1583d);
                        throw new IOException(sbA2.toString());
                    }
                } else if (i3 != 16 && i3 != 0) {
                    aVar2.f1580a.add(new c.a.c.a.a.d(aVar2.c(aVar2.a(i3, 15) - 1), aVar2.d()));
                } else {
                    d.j jVarD2 = aVar2.d();
                    f.a(jVarD2);
                    aVar2.f1580a.add(new c.a.c.a.a.d(jVarD2, aVar2.d()));
                }
            }
            return this.f1605c.c();
        }

        public final void a(c.a.c.a.a.b.a aVar, int i) {
            int i2 = this.f1603a.readInt();
            ((r.a) aVar).a(i, i2 & Integer.MAX_VALUE, (this.f1603a.readByte() & 255) + 1, (Integer.MIN_VALUE & i2) != 0);
        }
    }
}
