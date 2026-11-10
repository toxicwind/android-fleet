package d;

import com.google.common.primitives.UnsignedLong;
import com.google.gson.stream.JsonReader;
import java.io.EOFException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;

/* JADX INFO: loaded from: classes2.dex */
public final class x implements i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final g f2135a = new g();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final C f2136b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f2137c;

    public x(C c2) {
        if (c2 == null) {
            throw new NullPointerException("source == null");
        }
        this.f2136b = c2;
    }

    @Override // d.i, d.h
    public g a() {
        return this.f2135a;
    }

    @Override // d.i
    public byte[] b(long j) throws EOFException {
        if (request(j)) {
            return this.f2135a.b(j);
        }
        throw new EOFException();
    }

    @Override // d.i
    public void c(long j) throws EOFException {
        if (!request(j)) {
            throw new EOFException();
        }
    }

    @Override // d.C, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        if (this.f2137c) {
            return;
        }
        this.f2137c = true;
        this.f2136b.close();
        this.f2135a.clear();
    }

    @Override // d.i
    public j d(long j) throws EOFException {
        if (request(j)) {
            return this.f2135a.d(j);
        }
        throw new EOFException();
    }

    @Override // d.i
    public boolean e() {
        if (this.f2137c) {
            throw new IllegalStateException("closed");
        }
        return this.f2135a.e() && this.f2136b.read(this.f2135a, 8192L) == -1;
    }

    @Override // d.i
    public long f() throws EOFException {
        byte b2;
        byte b3;
        byte b4;
        c(1L);
        boolean z = false;
        int i = 0;
        while (true) {
            int i2 = i + 1;
            b2 = 57;
            b3 = 48;
            if (!request(i2)) {
                break;
            }
            byte bG = this.f2135a.g(i);
            if ((bG < 48 || bG > 57) && !(i == 0 && bG == 45)) {
                if (i != 0) {
                    break;
                }
                throw new NumberFormatException(String.format("Expected leading [0-9] or '-' character but was %#x", Byte.valueOf(bG)));
            }
            i = i2;
        }
        g gVar = this.f2135a;
        long j = 0;
        if (gVar.f2102c == 0) {
            throw new IllegalStateException("size == 0");
        }
        long j2 = -7;
        int i3 = 0;
        boolean z2 = false;
        loop1: while (true) {
            y yVar = gVar.f2101b;
            byte[] bArr = yVar.f2138a;
            int i4 = yVar.f2139b;
            int i5 = yVar.f2140c;
            while (i4 < i5) {
                b4 = bArr[i4];
                if (b4 < b3 || b4 > b2) {
                    z = z;
                    if (b4 != 45 || i3 != 0) {
                        if (i3 != 0) {
                            z = true;
                            break;
                        }
                        StringBuilder sbA = b.a.a.a.a.a("Expected leading [0-9] or '-' character but was 0x");
                        sbA.append(Integer.toHexString(b4));
                        throw new NumberFormatException(sbA.toString());
                    }
                    j2--;
                    z2 = true;
                } else {
                    int i6 = 48 - b4;
                    if (j < JsonReader.MIN_INCOMPLETE_INTEGER || (j == JsonReader.MIN_INCOMPLETE_INTEGER && i6 < j2)) {
                        break loop1;
                    }
                    j = (j * 10) + ((long) i6);
                }
                i4++;
                i3++;
                b2 = 57;
                z = z;
                b3 = 48;
            }
            if (i4 == i5) {
                gVar.f2101b = yVar.a();
                z.a(yVar);
            } else {
                yVar.f2139b = i4;
            }
            if (z || gVar.f2101b == null) {
                gVar.f2102c -= (long) i3;
                return z2 ? j : -j;
            }
            b2 = 57;
            b3 = 48;
        }
        g gVar2 = new g();
        gVar2.e(j);
        gVar2.writeByte((int) b4);
        if (!z2) {
            gVar2.readByte();
        }
        StringBuilder sbA2 = b.a.a.a.a.a("Number too large: ");
        sbA2.append(gVar2.o());
        throw new NumberFormatException(sbA2.toString());
    }

    @Override // d.i
    public int g() throws EOFException {
        c(4L);
        return F.a(this.f2135a.readInt());
    }

    @Override // d.i
    public g getBuffer() {
        return this.f2135a;
    }

    @Override // d.i
    public long h() throws EOFException {
        byte b2;
        byte b3;
        byte b4;
        int i;
        int i2;
        c(1L);
        boolean z = false;
        int i3 = 0;
        while (true) {
            int i4 = i3 + 1;
            b2 = 102;
            b3 = 65;
            b4 = 57;
            if (!request(i4)) {
                break;
            }
            byte bG = this.f2135a.g(i3);
            if ((bG < 48 || bG > 57) && ((bG < 97 || bG > 102) && (bG < 65 || bG > 70))) {
                if (i3 != 0) {
                    break;
                }
                throw new NumberFormatException(String.format("Expected leading [0-9a-fA-F] character but was %#x", Byte.valueOf(bG)));
            }
            i3 = i4;
        }
        g gVar = this.f2135a;
        long j = 0;
        if (gVar.f2102c == 0) {
            throw new IllegalStateException("size == 0");
        }
        int i5 = 0;
        while (true) {
            y yVar = gVar.f2101b;
            byte[] bArr = yVar.f2138a;
            int i6 = yVar.f2139b;
            int i7 = yVar.f2140c;
            while (i6 < i7) {
                byte b5 = bArr[i6];
                if (b5 < 48 || b5 > b4) {
                    if (b5 >= 97 && b5 <= b2) {
                        i = b5 - 97;
                    } else {
                        if (b5 < b3 || b5 > 70) {
                            if (i5 != 0) {
                                z = true;
                                break;
                            }
                            StringBuilder sbA = b.a.a.a.a.a("Expected leading [0-9a-fA-F] character but was 0x");
                            sbA.append(Integer.toHexString(b5));
                            throw new NumberFormatException(sbA.toString());
                        }
                        i = b5 - 65;
                    }
                    i2 = i + 10;
                } else {
                    i2 = b5 - 48;
                }
                if ((j & (-1152921504606846976L)) != 0) {
                    g gVar2 = new g();
                    gVar2.f(j);
                    gVar2.writeByte((int) b5);
                    StringBuilder sbA2 = b.a.a.a.a.a("Number too large: ");
                    sbA2.append(gVar2.o());
                    throw new NumberFormatException(sbA2.toString());
                }
                j = (j << 4) | ((long) i2);
                i6++;
                i5++;
                b2 = 102;
                b3 = 65;
                b4 = 57;
            }
            if (i6 == i7) {
                gVar.f2101b = yVar.a();
                z.a(yVar);
            } else {
                yVar.f2139b = i6;
            }
            if (z || gVar.f2101b == null) {
                gVar.f2102c -= (long) i5;
                return j;
            }
            b2 = 102;
            b3 = 65;
            b4 = 57;
        }
    }

    @Override // d.i
    public InputStream i() {
        return new w(this);
    }

    @Override // java.nio.channels.Channel
    public boolean isOpen() {
        return !this.f2137c;
    }

    @Override // d.C
    public long read(g gVar, long j) {
        if (gVar == null) {
            throw new IllegalArgumentException("sink == null");
        }
        if (j < 0) {
            throw new IllegalArgumentException(b.a.a.a.a.a("byteCount < 0: ", j));
        }
        if (this.f2137c) {
            throw new IllegalStateException("closed");
        }
        g gVar2 = this.f2135a;
        if (gVar2.f2102c == 0 && this.f2136b.read(gVar2, 8192L) == -1) {
            return -1L;
        }
        return this.f2135a.read(gVar, Math.min(j, this.f2135a.f2102c));
    }

    @Override // d.i
    public byte readByte() throws EOFException {
        c(1L);
        return this.f2135a.readByte();
    }

    @Override // d.i
    public void readFully(byte[] bArr) throws EOFException {
        try {
            c(bArr.length);
            this.f2135a.readFully(bArr);
        } catch (EOFException e2) {
            int i = 0;
            while (true) {
                g gVar = this.f2135a;
                long j = gVar.f2102c;
                if (j <= 0) {
                    throw e2;
                }
                int iA = gVar.a(bArr, i, (int) j);
                if (iA == -1) {
                    throw new AssertionError();
                }
                i += iA;
            }
        }
    }

    @Override // d.i
    public int readInt() throws EOFException {
        c(4L);
        return this.f2135a.readInt();
    }

    @Override // d.i
    public long readLong() throws EOFException {
        c(8L);
        return this.f2135a.readLong();
    }

    @Override // d.i
    public short readShort() throws EOFException {
        c(2L);
        return this.f2135a.readShort();
    }

    @Override // d.i
    public boolean request(long j) {
        g gVar;
        if (j < 0) {
            throw new IllegalArgumentException(b.a.a.a.a.a("byteCount < 0: ", j));
        }
        if (this.f2137c) {
            throw new IllegalStateException("closed");
        }
        do {
            gVar = this.f2135a;
            if (gVar.f2102c >= j) {
                return true;
            }
        } while (this.f2136b.read(gVar, 8192L) != -1);
        return false;
    }

    @Override // d.i
    public void skip(long j) throws EOFException {
        if (this.f2137c) {
            throw new IllegalStateException("closed");
        }
        while (j > 0) {
            g gVar = this.f2135a;
            if (gVar.f2102c == 0 && this.f2136b.read(gVar, 8192L) == -1) {
                throw new EOFException();
            }
            long jMin = Math.min(j, this.f2135a.f2102c);
            this.f2135a.skip(jMin);
            j -= jMin;
        }
    }

    @Override // d.C
    public E timeout() {
        return this.f2136b.timeout();
    }

    public String toString() {
        return b.a.a.a.a.a(b.a.a.a.a.a("buffer("), this.f2136b, ")");
    }

    @Override // d.i
    public void a(g gVar, long j) throws EOFException {
        try {
            if (!request(j)) {
                throw new EOFException();
            }
            g gVar2 = this.f2135a;
            long j2 = gVar2.f2102c;
            if (j2 >= j) {
                gVar.write(gVar2, j);
            } else {
                gVar.write(gVar2, j2);
                throw new EOFException();
            }
        } catch (EOFException e2) {
            gVar.a(this.f2135a);
            throw e2;
        }
    }

    @Override // d.i
    public short c() throws EOFException {
        c(2L);
        return F.a(this.f2135a.readShort());
    }

    @Override // d.i
    public String b() throws EOFException {
        long jA = a((byte) 10, 0L, UnsignedLong.UNSIGNED_MASK);
        if (jA != -1) {
            return this.f2135a.i(jA);
        }
        g gVar = new g();
        g gVar2 = this.f2135a;
        gVar2.a(gVar, 0L, Math.min(32L, gVar2.f2102c));
        StringBuilder sbA = b.a.a.a.a.a("\\n not found: limit=");
        sbA.append(Math.min(this.f2135a.f2102c, UnsignedLong.UNSIGNED_MASK));
        sbA.append(" content=");
        sbA.append(gVar.n().b());
        sbA.append((char) 8230);
        throw new EOFException(sbA.toString());
    }

    @Override // d.i
    public byte[] d() {
        this.f2135a.a(this.f2136b);
        return this.f2135a.d();
    }

    @Override // java.nio.channels.ReadableByteChannel
    public int read(ByteBuffer byteBuffer) {
        g gVar = this.f2135a;
        int iMin = -1;
        if (gVar.f2102c == 0 && this.f2136b.read(gVar, 8192L) == -1) {
            return -1;
        }
        g gVar2 = this.f2135a;
        y yVar = gVar2.f2101b;
        if (yVar != null) {
            iMin = Math.min(byteBuffer.remaining(), yVar.f2140c - yVar.f2139b);
            byteBuffer.put(yVar.f2138a, yVar.f2139b, iMin);
            yVar.f2139b += iMin;
            gVar2.f2102c -= (long) iMin;
            if (yVar.f2139b == yVar.f2140c) {
                gVar2.f2101b = yVar.a();
                z.a(yVar);
            }
        }
        return iMin;
    }

    @Override // d.i
    public String a(Charset charset) {
        if (charset != null) {
            this.f2135a.a(this.f2136b);
            return this.f2135a.a(charset);
        }
        throw new IllegalArgumentException("charset == null");
    }

    @Override // d.i
    public String a(long j) throws EOFException {
        if (j >= 0) {
            long j2 = j == UnsignedLong.UNSIGNED_MASK ? Long.MAX_VALUE : j + 1;
            long jA = a((byte) 10, 0L, j2);
            if (jA != -1) {
                return this.f2135a.i(jA);
            }
            if (j2 < UnsignedLong.UNSIGNED_MASK && request(j2) && this.f2135a.g(j2 - 1) == 13 && request(1 + j2) && this.f2135a.g(j2) == 10) {
                return this.f2135a.i(j2);
            }
            g gVar = new g();
            g gVar2 = this.f2135a;
            gVar2.a(gVar, 0L, Math.min(32L, gVar2.f2102c));
            StringBuilder sbA = b.a.a.a.a.a("\\n not found: limit=");
            sbA.append(Math.min(this.f2135a.f2102c, j));
            sbA.append(" content=");
            sbA.append(gVar.n().b());
            sbA.append((char) 8230);
            throw new EOFException(sbA.toString());
        }
        throw new IllegalArgumentException(b.a.a.a.a.a("limit < 0: ", j));
    }

    @Override // d.i
    public long a(byte b2) {
        return a(b2, 0L, UnsignedLong.UNSIGNED_MASK);
    }

    public long a(byte b2, long j, long j2) {
        if (this.f2137c) {
            throw new IllegalStateException("closed");
        }
        if (j < 0 || j2 < j) {
            throw new IllegalArgumentException(String.format("fromIndex=%s toIndex=%s", Long.valueOf(j), Long.valueOf(j2)));
        }
        while (j < j2) {
            long jA = this.f2135a.a(b2, j, j2);
            if (jA != -1) {
                return jA;
            }
            g gVar = this.f2135a;
            long j3 = gVar.f2102c;
            if (j3 >= j2 || this.f2136b.read(gVar, 8192L) == -1) {
                break;
            }
            j = Math.max(j, j3);
        }
        return -1L;
    }

    @Override // d.i
    public boolean a(long j, j jVar) {
        int iG = jVar.g();
        if (!this.f2137c) {
            if (j < 0 || iG < 0 || jVar.g() - 0 < iG) {
                return false;
            }
            for (int i = 0; i < iG; i++) {
                long j2 = ((long) i) + j;
                if (!request(1 + j2) || this.f2135a.g(j2) != jVar.a(0 + i)) {
                    return false;
                }
            }
            return true;
        }
        throw new IllegalStateException("closed");
    }
}
