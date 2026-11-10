package d;

import com.google.common.base.Ascii;
import com.google.common.primitives.UnsignedBytes;
import com.google.common.primitives.UnsignedLong;
import com.google.gson.stream.JsonReader;
import java.io.Closeable;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.ByteChannel;
import java.nio.charset.Charset;
import okhttp3.internal.http2.Http2Connection;

/* JADX INFO: loaded from: classes2.dex */
public final class g implements i, h, Cloneable, ByteChannel {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final byte[] f2100a = {48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 97, 98, 99, 100, 101, 102};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public y f2101b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public long f2102c;

    public static final class a implements Closeable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public g f2103a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public boolean f2104b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public y f2105c;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public byte[] f2107e;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public long f2106d = -1;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public int f2108f = -1;
        public int g = -1;

        @Override // java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            if (this.f2103a == null) {
                throw new IllegalStateException("not attached to a buffer");
            }
            this.f2103a = null;
            this.f2105c = null;
            this.f2106d = -1L;
            this.f2107e = null;
            this.f2108f = -1;
            this.g = -1;
        }

        public final int g(long j) {
            y yVar;
            if (j >= -1) {
                g gVar = this.f2103a;
                long j2 = gVar.f2102c;
                if (j <= j2) {
                    if (j == -1 || j == j2) {
                        this.f2105c = null;
                        this.f2106d = j;
                        this.f2107e = null;
                        this.f2108f = -1;
                        this.g = -1;
                        return -1;
                    }
                    long j3 = 0;
                    y yVar2 = gVar.f2101b;
                    y yVar3 = this.f2105c;
                    if (yVar3 != null) {
                        long j4 = this.f2106d - ((long) (this.f2108f - yVar3.f2139b));
                        if (j4 > j) {
                            j2 = j4;
                            yVar3 = yVar2;
                            yVar2 = yVar3;
                        } else {
                            j3 = j4;
                        }
                    } else {
                        yVar3 = yVar2;
                    }
                    if (j2 - j > j - j3) {
                        while (true) {
                            int i = yVar3.f2140c;
                            int i2 = yVar3.f2139b;
                            if (j < ((long) (i - i2)) + j3) {
                                break;
                            }
                            j3 += (long) (i - i2);
                            yVar3 = yVar3.f2143f;
                        }
                    } else {
                        yVar3 = yVar2;
                        j3 = j2;
                        while (j3 > j) {
                            yVar3 = yVar3.g;
                            j3 -= (long) (yVar3.f2140c - yVar3.f2139b);
                        }
                    }
                    if (this.f2104b && yVar3.f2141d) {
                        yVar = new y((byte[]) yVar3.f2138a.clone(), yVar3.f2139b, yVar3.f2140c, false, true);
                        g gVar2 = this.f2103a;
                        if (gVar2.f2101b == yVar3) {
                            gVar2.f2101b = yVar;
                        }
                        yVar3.a(yVar);
                        yVar.g.a();
                    } else {
                        yVar = yVar3;
                    }
                    this.f2105c = yVar;
                    this.f2106d = j;
                    this.f2107e = yVar.f2138a;
                    this.f2108f = yVar.f2139b + ((int) (j - j3));
                    this.g = yVar.f2140c;
                    return this.g - this.f2108f;
                }
            }
            throw new ArrayIndexOutOfBoundsException(String.format("offset=%s > size=%s", Long.valueOf(j), Long.valueOf(this.f2103a.f2102c)));
        }
    }

    @Override // d.i, d.h
    public g a() {
        return this;
    }

    @Override // d.h
    public /* bridge */ /* synthetic */ h a(j jVar) {
        a(jVar);
        return this;
    }

    @Override // d.i
    public String b() throws EOFException {
        long jA = a((byte) 10, 0L, UnsignedLong.UNSIGNED_MASK);
        if (jA != -1) {
            return i(jA);
        }
        if (UnsignedLong.UNSIGNED_MASK < this.f2102c && g(9223372036854775806L) == 13 && g(UnsignedLong.UNSIGNED_MASK) == 10) {
            return i(UnsignedLong.UNSIGNED_MASK);
        }
        g gVar = new g();
        a(gVar, 0L, Math.min(32L, this.f2102c));
        StringBuilder sbA = b.a.a.a.a.a("\\n not found: limit=");
        sbA.append(Math.min(this.f2102c, UnsignedLong.UNSIGNED_MASK));
        sbA.append(" content=");
        sbA.append(gVar.n().b());
        sbA.append((char) 8230);
        throw new EOFException(sbA.toString());
    }

    @Override // d.i
    public void c(long j) throws EOFException {
        if (this.f2102c < j) {
            throw new EOFException();
        }
    }

    public final void clear() {
        try {
            skip(this.f2102c);
        } catch (EOFException e2) {
            throw new AssertionError(e2);
        }
    }

    @Override // d.C, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
    }

    @Override // d.i
    public j d(long j) {
        return new j(b(j));
    }

    @Override // d.h
    public /* bridge */ /* synthetic */ h e(long j) {
        e(j);
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof g)) {
            return false;
        }
        g gVar = (g) obj;
        long j = this.f2102c;
        if (j != gVar.f2102c) {
            return false;
        }
        long j2 = 0;
        if (j == 0) {
            return true;
        }
        y yVar = this.f2101b;
        y yVar2 = gVar.f2101b;
        int i = yVar.f2139b;
        int i2 = yVar2.f2139b;
        while (j2 < this.f2102c) {
            long jMin = Math.min(yVar.f2140c - i, yVar2.f2140c - i2);
            int i3 = i2;
            int i4 = i;
            int i5 = 0;
            while (i5 < jMin) {
                int i6 = i4 + 1;
                int i7 = i3 + 1;
                if (yVar.f2138a[i4] != yVar2.f2138a[i3]) {
                    return false;
                }
                i5++;
                i4 = i6;
                i3 = i7;
            }
            if (i4 == yVar.f2140c) {
                yVar = yVar.f2143f;
                i = yVar.f2139b;
            } else {
                i = i4;
            }
            if (i3 == yVar2.f2140c) {
                yVar2 = yVar2.f2143f;
                i2 = yVar2.f2139b;
            } else {
                i2 = i3;
            }
            j2 += jMin;
        }
        return true;
    }

    @Override // d.h
    public /* bridge */ /* synthetic */ h f(long j) {
        f(j);
        return this;
    }

    @Override // d.h, d.B, java.io.Flushable
    public void flush() {
    }

    public final byte g(long j) {
        int i;
        F.a(this.f2102c, j, 1L);
        long j2 = this.f2102c;
        if (j2 - j <= j) {
            long j3 = j - j2;
            y yVar = this.f2101b;
            do {
                yVar = yVar.g;
                int i2 = yVar.f2140c;
                i = yVar.f2139b;
                j3 += (long) (i2 - i);
            } while (j3 < 0);
            return yVar.f2138a[i + ((int) j3)];
        }
        y yVar2 = this.f2101b;
        while (true) {
            int i3 = yVar2.f2140c;
            int i4 = yVar2.f2139b;
            long j4 = i3 - i4;
            if (j < j4) {
                return yVar2.f2138a[i4 + ((int) j)];
            }
            j -= j4;
            yVar2 = yVar2.f2143f;
        }
    }

    @Override // d.i
    public g getBuffer() {
        return this;
    }

    @Override // d.i
    public long h() {
        int i;
        int i2;
        if (this.f2102c == 0) {
            throw new IllegalStateException("size == 0");
        }
        boolean z = false;
        int i3 = 0;
        long j = 0;
        do {
            y yVar = this.f2101b;
            byte[] bArr = yVar.f2138a;
            int i4 = yVar.f2139b;
            int i5 = yVar.f2140c;
            while (i4 < i5) {
                byte b2 = bArr[i4];
                if (b2 < 48 || b2 > 57) {
                    if (b2 >= 97 && b2 <= 102) {
                        i = b2 - 97;
                    } else {
                        if (b2 < 65 || b2 > 70) {
                            if (i3 != 0) {
                                z = true;
                                break;
                            }
                            StringBuilder sbA = b.a.a.a.a.a("Expected leading [0-9a-fA-F] character but was 0x");
                            sbA.append(Integer.toHexString(b2));
                            throw new NumberFormatException(sbA.toString());
                        }
                        i = b2 - 65;
                    }
                    i2 = i + 10;
                } else {
                    i2 = b2 - 48;
                }
                if (((-1152921504606846976L) & j) != 0) {
                    g gVar = new g();
                    gVar.f(j);
                    gVar.writeByte((int) b2);
                    StringBuilder sbA2 = b.a.a.a.a.a("Number too large: ");
                    sbA2.append(gVar.o());
                    throw new NumberFormatException(sbA2.toString());
                }
                j = (j << 4) | ((long) i2);
                i4++;
                i3++;
            }
            if (i4 == i5) {
                this.f2101b = yVar.a();
                z.a(yVar);
            } else {
                yVar.f2139b = i4;
            }
            if (z) {
                break;
            }
        } while (this.f2101b != null);
        this.f2102c -= (long) i3;
        return j;
    }

    public int hashCode() {
        y yVar = this.f2101b;
        if (yVar == null) {
            return 0;
        }
        int i = 1;
        do {
            int i2 = yVar.f2140c;
            for (int i3 = yVar.f2139b; i3 < i2; i3++) {
                i = (i * 31) + yVar.f2138a[i3];
            }
            yVar = yVar.f2143f;
        } while (yVar != this.f2101b);
        return i;
    }

    @Override // d.i
    public InputStream i() {
        return new C0247f(this);
    }

    @Override // java.nio.channels.Channel
    public boolean isOpen() {
        return true;
    }

    public g j(long j) {
        y yVarA = a(8);
        byte[] bArr = yVarA.f2138a;
        int i = yVarA.f2140c;
        int i2 = i + 1;
        bArr[i] = (byte) ((j >>> 56) & 255);
        int i3 = i2 + 1;
        bArr[i2] = (byte) ((j >>> 48) & 255);
        int i4 = i3 + 1;
        bArr[i3] = (byte) ((j >>> 40) & 255);
        int i5 = i4 + 1;
        bArr[i4] = (byte) ((j >>> 32) & 255);
        int i6 = i5 + 1;
        bArr[i5] = (byte) ((j >>> 24) & 255);
        int i7 = i6 + 1;
        bArr[i6] = (byte) ((j >>> 16) & 255);
        int i8 = i7 + 1;
        bArr[i7] = (byte) ((j >>> 8) & 255);
        bArr[i8] = (byte) (j & 255);
        yVarA.f2140c = i8 + 1;
        this.f2102c += 8;
        return this;
    }

    @Override // d.h
    public h j() {
        return this;
    }

    @Override // d.h
    public h k() {
        return this;
    }

    @Override // d.h
    public OutputStream l() {
        return new C0246e(this);
    }

    public final long m() {
        long j = this.f2102c;
        if (j == 0) {
            return 0L;
        }
        y yVar = this.f2101b.g;
        int i = yVar.f2140c;
        return (i >= 8192 || !yVar.f2142e) ? j : j - ((long) (i - yVar.f2139b));
    }

    public j n() {
        return new j(d());
    }

    public String o() {
        try {
            return a(this.f2102c, F.f2092a);
        } catch (EOFException e2) {
            throw new AssertionError(e2);
        }
    }

    public int p() throws EOFException {
        int i;
        int i2;
        int i3;
        if (this.f2102c == 0) {
            throw new EOFException();
        }
        byte bG = g(0L);
        if ((bG & UnsignedBytes.MAX_POWER_OF_TWO) == 0) {
            i = bG & Ascii.DEL;
            i3 = 0;
            i2 = 1;
        } else if ((bG & 224) == 192) {
            i = bG & Ascii.US;
            i2 = 2;
            i3 = 128;
        } else if ((bG & 240) == 224) {
            i = bG & Ascii.SI;
            i2 = 3;
            i3 = 2048;
        } else {
            if ((bG & 248) != 240) {
                skip(1L);
                return 65533;
            }
            i = bG & 7;
            i2 = 4;
            i3 = 65536;
        }
        long j = i2;
        if (this.f2102c < j) {
            StringBuilder sbA = b.a.a.a.a.a("size < ", i2, ": ");
            sbA.append(this.f2102c);
            sbA.append(" (to read code point prefixed 0x");
            sbA.append(Integer.toHexString(bG));
            sbA.append(")");
            throw new EOFException(sbA.toString());
        }
        for (int i4 = 1; i4 < i2; i4++) {
            long j2 = i4;
            byte bG2 = g(j2);
            if ((bG2 & 192) != 128) {
                skip(j2);
                return 65533;
            }
            i = (i << 6) | (bG2 & 63);
        }
        skip(j);
        if (i > 1114111) {
            return 65533;
        }
        if ((i < 55296 || i > 57343) && i >= i3) {
            return i;
        }
        return 65533;
    }

    public final j q() {
        long j = this.f2102c;
        if (j <= 2147483647L) {
            int i = (int) j;
            return i == 0 ? j.f2110b : new A(this, i);
        }
        StringBuilder sbA = b.a.a.a.a.a("size > Integer.MAX_VALUE: ");
        sbA.append(this.f2102c);
        throw new IllegalArgumentException(sbA.toString());
    }

    @Override // java.nio.channels.ReadableByteChannel
    public int read(ByteBuffer byteBuffer) {
        y yVar = this.f2101b;
        if (yVar == null) {
            return -1;
        }
        int iMin = Math.min(byteBuffer.remaining(), yVar.f2140c - yVar.f2139b);
        byteBuffer.put(yVar.f2138a, yVar.f2139b, iMin);
        yVar.f2139b += iMin;
        this.f2102c -= (long) iMin;
        if (yVar.f2139b == yVar.f2140c) {
            this.f2101b = yVar.a();
            z.a(yVar);
        }
        return iMin;
    }

    @Override // d.i
    public byte readByte() {
        long j = this.f2102c;
        if (j == 0) {
            throw new IllegalStateException("size == 0");
        }
        y yVar = this.f2101b;
        int i = yVar.f2139b;
        int i2 = yVar.f2140c;
        int i3 = i + 1;
        byte b2 = yVar.f2138a[i];
        this.f2102c = j - 1;
        if (i3 == i2) {
            this.f2101b = yVar.a();
            z.a(yVar);
        } else {
            yVar.f2139b = i3;
        }
        return b2;
    }

    @Override // d.i
    public void readFully(byte[] bArr) throws EOFException {
        int i = 0;
        while (i < bArr.length) {
            int iA = a(bArr, i, bArr.length - i);
            if (iA == -1) {
                throw new EOFException();
            }
            i += iA;
        }
    }

    @Override // d.i
    public int readInt() {
        long j = this.f2102c;
        if (j < 4) {
            StringBuilder sbA = b.a.a.a.a.a("size < 4: ");
            sbA.append(this.f2102c);
            throw new IllegalStateException(sbA.toString());
        }
        y yVar = this.f2101b;
        int i = yVar.f2139b;
        int i2 = yVar.f2140c;
        if (i2 - i < 4) {
            return ((readByte() & 255) << 24) | ((readByte() & 255) << 16) | ((readByte() & 255) << 8) | (readByte() & 255);
        }
        byte[] bArr = yVar.f2138a;
        int i3 = i + 1;
        int i4 = i3 + 1;
        int i5 = ((bArr[i] & 255) << 24) | ((bArr[i3] & 255) << 16);
        int i6 = i4 + 1;
        int i7 = i5 | ((bArr[i4] & 255) << 8);
        int i8 = i6 + 1;
        int i9 = i7 | (bArr[i6] & 255);
        this.f2102c = j - 4;
        if (i8 == i2) {
            this.f2101b = yVar.a();
            z.a(yVar);
        } else {
            yVar.f2139b = i8;
        }
        return i9;
    }

    @Override // d.i
    public long readLong() {
        long j = this.f2102c;
        if (j < 8) {
            StringBuilder sbA = b.a.a.a.a.a("size < 8: ");
            sbA.append(this.f2102c);
            throw new IllegalStateException(sbA.toString());
        }
        y yVar = this.f2101b;
        int i = yVar.f2139b;
        int i2 = yVar.f2140c;
        if (i2 - i < 8) {
            return ((((long) readInt()) & 4294967295L) << 32) | (4294967295L & ((long) readInt()));
        }
        byte[] bArr = yVar.f2138a;
        int i3 = i + 1;
        long j2 = (((long) bArr[i]) & 255) << 56;
        int i4 = i3 + 1;
        int i5 = i4 + 1;
        long j3 = ((((long) bArr[i3]) & 255) << 48) | j2 | ((((long) bArr[i4]) & 255) << 40);
        int i6 = i5 + 1;
        int i7 = i6 + 1;
        long j4 = j3 | ((((long) bArr[i5]) & 255) << 32) | ((((long) bArr[i6]) & 255) << 24);
        int i8 = i7 + 1;
        long j5 = j4 | ((((long) bArr[i7]) & 255) << 16);
        int i9 = i8 + 1;
        long j6 = j5 | ((((long) bArr[i8]) & 255) << 8);
        int i10 = i9 + 1;
        long j7 = (((long) bArr[i9]) & 255) | j6;
        this.f2102c = j - 8;
        if (i10 == i2) {
            this.f2101b = yVar.a();
            z.a(yVar);
        } else {
            yVar.f2139b = i10;
        }
        return j7;
    }

    @Override // d.i
    public short readShort() {
        long j = this.f2102c;
        if (j < 2) {
            StringBuilder sbA = b.a.a.a.a.a("size < 2: ");
            sbA.append(this.f2102c);
            throw new IllegalStateException(sbA.toString());
        }
        y yVar = this.f2101b;
        int i = yVar.f2139b;
        int i2 = yVar.f2140c;
        if (i2 - i < 2) {
            return (short) (((readByte() & 255) << 8) | (readByte() & 255));
        }
        byte[] bArr = yVar.f2138a;
        int i3 = i + 1;
        int i4 = i3 + 1;
        int i5 = ((bArr[i] & 255) << 8) | (bArr[i3] & 255);
        this.f2102c = j - 2;
        if (i4 == i2) {
            this.f2101b = yVar.a();
            z.a(yVar);
        } else {
            yVar.f2139b = i4;
        }
        return (short) i5;
    }

    @Override // d.i
    public boolean request(long j) {
        return this.f2102c >= j;
    }

    @Override // d.i
    public void skip(long j) throws EOFException {
        while (j > 0) {
            y yVar = this.f2101b;
            if (yVar == null) {
                throw new EOFException();
            }
            int iMin = (int) Math.min(j, yVar.f2140c - yVar.f2139b);
            long j2 = iMin;
            this.f2102c -= j2;
            j -= j2;
            y yVar2 = this.f2101b;
            yVar2.f2139b += iMin;
            if (yVar2.f2139b == yVar2.f2140c) {
                this.f2101b = yVar2.a();
                z.a(yVar2);
            }
        }
    }

    @Override // d.C
    public E timeout() {
        return E.NONE;
    }

    public String toString() {
        return q().toString();
    }

    @Override // d.h
    public /* bridge */ /* synthetic */ h write(byte[] bArr) {
        write(bArr);
        return this;
    }

    @Override // d.h
    public /* bridge */ /* synthetic */ h writeByte(int i) {
        writeByte(i);
        return this;
    }

    @Override // d.h
    public /* bridge */ /* synthetic */ h writeInt(int i) {
        writeInt(i);
        return this;
    }

    @Override // d.h
    public /* bridge */ /* synthetic */ h writeShort(int i) {
        writeShort(i);
        return this;
    }

    @Override // d.h
    public /* bridge */ /* synthetic */ h a(String str) {
        a(str);
        return this;
    }

    @Override // d.i
    public short c() {
        return F.a(readShort());
    }

    /* JADX INFO: renamed from: clone, reason: merged with bridge method [inline-methods] */
    public g m8clone() {
        g gVar = new g();
        if (this.f2102c == 0) {
            return gVar;
        }
        gVar.f2101b = this.f2101b.b();
        y yVar = gVar.f2101b;
        yVar.g = yVar;
        yVar.f2143f = yVar;
        y yVar2 = this.f2101b;
        while (true) {
            yVar2 = yVar2.f2143f;
            if (yVar2 == this.f2101b) {
                gVar.f2102c = this.f2102c;
                return gVar;
            }
            gVar.f2101b.g.a(yVar2.b());
        }
    }

    @Override // d.i
    public byte[] d() {
        try {
            return b(this.f2102c);
        } catch (EOFException e2) {
            throw new AssertionError(e2);
        }
    }

    @Override // d.i
    public boolean e() {
        return this.f2102c == 0;
    }

    @Override // d.i
    public long f() {
        long j = 0;
        if (this.f2102c == 0) {
            throw new IllegalStateException("size == 0");
        }
        boolean z = false;
        long j2 = -7;
        boolean z2 = false;
        int i = 0;
        do {
            y yVar = this.f2101b;
            byte[] bArr = yVar.f2138a;
            int i2 = yVar.f2139b;
            int i3 = yVar.f2140c;
            while (i2 < i3) {
                byte b2 = bArr[i2];
                if (b2 >= 48 && b2 <= 57) {
                    int i4 = 48 - b2;
                    if (j < JsonReader.MIN_INCOMPLETE_INTEGER || (j == JsonReader.MIN_INCOMPLETE_INTEGER && i4 < j2)) {
                        g gVar = new g();
                        gVar.e(j);
                        gVar.writeByte((int) b2);
                        if (!z) {
                            gVar.readByte();
                        }
                        StringBuilder sbA = b.a.a.a.a.a("Number too large: ");
                        sbA.append(gVar.o());
                        throw new NumberFormatException(sbA.toString());
                    }
                    j = (j * 10) + ((long) i4);
                } else {
                    if (b2 != 45 || i != 0) {
                        if (i != 0) {
                            z2 = true;
                            break;
                        }
                        StringBuilder sbA2 = b.a.a.a.a.a("Expected leading [0-9] or '-' character but was 0x");
                        sbA2.append(Integer.toHexString(b2));
                        throw new NumberFormatException(sbA2.toString());
                    }
                    j2--;
                    z = true;
                }
                i2++;
                i++;
            }
            if (i2 == i3) {
                this.f2101b = yVar.a();
                z.a(yVar);
            } else {
                yVar.f2139b = i2;
            }
            if (z2) {
                break;
            }
        } while (this.f2101b != null);
        this.f2102c -= (long) i;
        return z ? j : -j;
    }

    public String i(long j) throws EOFException {
        if (j > 0) {
            long j2 = j - 1;
            if (g(j2) == 13) {
                String strH = h(j2);
                skip(2L);
                return strH;
            }
        }
        String strH2 = h(j);
        skip(1L);
        return strH2;
    }

    @Override // d.h
    public /* bridge */ /* synthetic */ h write(byte[] bArr, int i, int i2) {
        write(bArr, i, i2);
        return this;
    }

    @Override // d.h
    public g writeByte(int i) {
        y yVarA = a(1);
        byte[] bArr = yVarA.f2138a;
        int i2 = yVarA.f2140c;
        yVarA.f2140c = i2 + 1;
        bArr[i2] = (byte) i;
        this.f2102c++;
        return this;
    }

    @Override // d.h
    public g writeInt(int i) {
        y yVarA = a(4);
        byte[] bArr = yVarA.f2138a;
        int i2 = yVarA.f2140c;
        int i3 = i2 + 1;
        bArr[i2] = (byte) ((i >>> 24) & 255);
        int i4 = i3 + 1;
        bArr[i3] = (byte) ((i >>> 16) & 255);
        int i5 = i4 + 1;
        bArr[i4] = (byte) ((i >>> 8) & 255);
        bArr[i5] = (byte) (i & 255);
        yVarA.f2140c = i5 + 1;
        this.f2102c += 4;
        return this;
    }

    @Override // d.h
    public g writeShort(int i) {
        y yVarA = a(2);
        byte[] bArr = yVarA.f2138a;
        int i2 = yVarA.f2140c;
        int i3 = i2 + 1;
        bArr[i2] = (byte) ((i >>> 8) & 255);
        bArr[i3] = (byte) (i & 255);
        yVarA.f2140c = i3 + 1;
        this.f2102c += 2;
        return this;
    }

    public final g a(g gVar, long j, long j2) {
        if (gVar != null) {
            F.a(this.f2102c, j, j2);
            if (j2 == 0) {
                return this;
            }
            gVar.f2102c += j2;
            y yVar = this.f2101b;
            while (true) {
                int i = yVar.f2140c;
                int i2 = yVar.f2139b;
                if (j < i - i2) {
                    break;
                }
                j -= (long) (i - i2);
                yVar = yVar.f2143f;
            }
            while (j2 > 0) {
                y yVarB = yVar.b();
                yVarB.f2139b = (int) (((long) yVarB.f2139b) + j);
                yVarB.f2140c = Math.min(yVarB.f2139b + ((int) j2), yVarB.f2140c);
                y yVar2 = gVar.f2101b;
                if (yVar2 == null) {
                    yVarB.g = yVarB;
                    yVarB.f2143f = yVarB;
                    gVar.f2101b = yVarB;
                } else {
                    yVar2.g.a(yVarB);
                }
                j2 -= (long) (yVarB.f2140c - yVarB.f2139b);
                yVar = yVar.f2143f;
                j = 0;
            }
            return this;
        }
        throw new IllegalArgumentException("out == null");
    }

    @Override // d.h
    public g e(long j) {
        if (j == 0) {
            writeByte(48);
            return this;
        }
        boolean z = false;
        int i = 1;
        if (j < 0) {
            j = -j;
            if (j < 0) {
                a("-9223372036854775808");
                return this;
            }
            z = true;
        }
        if (j < 100000000) {
            if (j < 10000) {
                if (j >= 100) {
                    i = j < 1000 ? 3 : 4;
                } else if (j >= 10) {
                    i = 2;
                }
            } else if (j < 1000000) {
                i = j < 100000 ? 5 : 6;
            } else {
                i = j < 10000000 ? 7 : 8;
            }
        } else if (j < 1000000000000L) {
            if (j < 10000000000L) {
                i = j < Http2Connection.DEGRADED_PONG_TIMEOUT_NS ? 9 : 10;
            } else {
                i = j < 100000000000L ? 11 : 12;
            }
        } else if (j < 1000000000000000L) {
            if (j < 10000000000000L) {
                i = 13;
            } else {
                i = j < 100000000000000L ? 14 : 15;
            }
        } else if (j < 100000000000000000L) {
            i = j < 10000000000000000L ? 16 : 17;
        } else {
            i = j < 1000000000000000000L ? 18 : 19;
        }
        if (z) {
            i++;
        }
        y yVarA = a(i);
        byte[] bArr = yVarA.f2138a;
        int i2 = yVarA.f2140c + i;
        while (j != 0) {
            i2--;
            bArr[i2] = f2100a[(int) (j % 10)];
            j /= 10;
        }
        if (z) {
            bArr[i2 - 1] = 45;
        }
        yVarA.f2140c += i;
        this.f2102c += (long) i;
        return this;
    }

    @Override // d.h
    public g write(byte[] bArr) {
        if (bArr != null) {
            write(bArr, 0, bArr.length);
            return this;
        }
        throw new IllegalArgumentException("source == null");
    }

    @Override // d.h
    public g write(byte[] bArr, int i, int i2) {
        if (bArr != null) {
            long j = i2;
            F.a(bArr.length, i, j);
            int i3 = i2 + i;
            while (i < i3) {
                y yVarA = a(1);
                int iMin = Math.min(i3 - i, 8192 - yVarA.f2140c);
                System.arraycopy(bArr, i, yVarA.f2138a, yVarA.f2140c, iMin);
                i += iMin;
                yVarA.f2140c += iMin;
            }
            this.f2102c += j;
            return this;
        }
        throw new IllegalArgumentException("source == null");
    }

    @Override // d.C
    public long read(g gVar, long j) {
        if (gVar == null) {
            throw new IllegalArgumentException("sink == null");
        }
        if (j >= 0) {
            long j2 = this.f2102c;
            if (j2 == 0) {
                return -1L;
            }
            if (j > j2) {
                j = j2;
            }
            gVar.write(this, j);
            return j;
        }
        throw new IllegalArgumentException(b.a.a.a.a.a("byteCount < 0: ", j));
    }

    @Override // d.i
    public int g() {
        return F.a(readInt());
    }

    @Override // java.nio.channels.WritableByteChannel
    public int write(ByteBuffer byteBuffer) {
        if (byteBuffer != null) {
            int iRemaining = byteBuffer.remaining();
            int i = iRemaining;
            while (i > 0) {
                y yVarA = a(1);
                int iMin = Math.min(i, 8192 - yVarA.f2140c);
                byteBuffer.get(yVarA.f2138a, yVarA.f2140c, iMin);
                i -= iMin;
                yVarA.f2140c += iMin;
            }
            this.f2102c += (long) iRemaining;
            return iRemaining;
        }
        throw new IllegalArgumentException("source == null");
    }

    @Override // d.i
    public byte[] b(long j) throws EOFException {
        F.a(this.f2102c, 0L, j);
        if (j <= 2147483647L) {
            byte[] bArr = new byte[(int) j];
            readFully(bArr);
            return bArr;
        }
        throw new IllegalArgumentException(b.a.a.a.a.a("byteCount > Integer.MAX_VALUE: ", j));
    }

    public final g a(OutputStream outputStream, long j) throws IOException {
        if (outputStream != null) {
            F.a(this.f2102c, 0L, j);
            y yVar = this.f2101b;
            while (j > 0) {
                int iMin = (int) Math.min(j, yVar.f2140c - yVar.f2139b);
                outputStream.write(yVar.f2138a, yVar.f2139b, iMin);
                yVar.f2139b += iMin;
                long j2 = iMin;
                this.f2102c -= j2;
                j -= j2;
                if (yVar.f2139b == yVar.f2140c) {
                    y yVarA = yVar.a();
                    this.f2101b = yVarA;
                    z.a(yVar);
                    yVar = yVarA;
                }
            }
            return this;
        }
        throw new IllegalArgumentException("out == null");
    }

    public g b(int i) {
        if (i < 128) {
            writeByte(i);
        } else if (i < 2048) {
            writeByte((i >> 6) | 192);
            writeByte((i & 63) | 128);
        } else if (i < 65536) {
            if (i >= 55296 && i <= 57343) {
                writeByte(63);
            } else {
                writeByte((i >> 12) | 224);
                writeByte(((i >> 6) & 63) | 128);
                writeByte((i & 63) | 128);
            }
        } else if (i <= 1114111) {
            writeByte((i >> 18) | 240);
            writeByte(((i >> 12) & 63) | 128);
            writeByte(((i >> 6) & 63) | 128);
            writeByte((i & 63) | 128);
        } else {
            StringBuilder sbA = b.a.a.a.a.a("Unexpected code point: ");
            sbA.append(Integer.toHexString(i));
            throw new IllegalArgumentException(sbA.toString());
        }
        return this;
    }

    public String h(long j) {
        return a(j, F.f2092a);
    }

    @Override // d.h
    public g f(long j) {
        if (j == 0) {
            writeByte(48);
            return this;
        }
        int iNumberOfTrailingZeros = (Long.numberOfTrailingZeros(Long.highestOneBit(j)) / 4) + 1;
        y yVarA = a(iNumberOfTrailingZeros);
        byte[] bArr = yVarA.f2138a;
        int i = yVarA.f2140c;
        for (int i2 = (i + iNumberOfTrailingZeros) - 1; i2 >= i; i2--) {
            bArr[i2] = f2100a[(int) (15 & j)];
            j >>>= 4;
        }
        yVarA.f2140c += iNumberOfTrailingZeros;
        this.f2102c += (long) iNumberOfTrailingZeros;
        return this;
    }

    @Override // d.B
    public void write(g gVar, long j) {
        if (gVar == null) {
            throw new IllegalArgumentException("source == null");
        }
        if (gVar != this) {
            F.a(gVar.f2102c, 0L, j);
            while (j > 0) {
                y yVar = gVar.f2101b;
                if (j < yVar.f2140c - yVar.f2139b) {
                    y yVar2 = this.f2101b;
                    y yVar3 = yVar2 != null ? yVar2.g : null;
                    if (yVar3 != null && yVar3.f2142e) {
                        if ((((long) yVar3.f2140c) + j) - ((long) (yVar3.f2141d ? 0 : yVar3.f2139b)) <= 8192) {
                            gVar.f2101b.a(yVar3, (int) j);
                            gVar.f2102c -= j;
                            this.f2102c += j;
                            return;
                        }
                    }
                    gVar.f2101b = gVar.f2101b.a((int) j);
                }
                y yVar4 = gVar.f2101b;
                long j2 = yVar4.f2140c - yVar4.f2139b;
                gVar.f2101b = yVar4.a();
                y yVar5 = this.f2101b;
                if (yVar5 == null) {
                    this.f2101b = yVar4;
                    y yVar6 = this.f2101b;
                    yVar6.g = yVar6;
                    yVar6.f2143f = yVar6;
                } else {
                    yVar5.g.a(yVar4);
                    y yVar7 = yVar4.g;
                    if (yVar7 != yVar4) {
                        if (yVar7.f2142e) {
                            int i = yVar4.f2140c - yVar4.f2139b;
                            if (i <= (8192 - yVar7.f2140c) + (yVar7.f2141d ? 0 : yVar7.f2139b)) {
                                yVar4.a(yVar4.g, i);
                                yVar4.a();
                                z.a(yVar4);
                            }
                        }
                    } else {
                        throw new IllegalStateException();
                    }
                }
                gVar.f2102c -= j2;
                this.f2102c += j2;
                j -= j2;
            }
            return;
        }
        throw new IllegalArgumentException("source == this");
    }

    @Override // d.i
    public void a(g gVar, long j) throws EOFException {
        long j2 = this.f2102c;
        if (j2 >= j) {
            gVar.write(this, j);
        } else {
            gVar.write(this, j2);
            throw new EOFException();
        }
    }

    @Override // d.i
    public String a(Charset charset) {
        try {
            return a(this.f2102c, charset);
        } catch (EOFException e2) {
            throw new AssertionError(e2);
        }
    }

    public long b(j jVar) {
        int i;
        int i2;
        y yVar = this.f2101b;
        if (yVar == null) {
            return -1L;
        }
        long j = this.f2102c;
        long j2 = 0;
        if (j - 0 >= 0) {
            j = 0;
            while (true) {
                long j3 = ((long) (yVar.f2140c - yVar.f2139b)) + j;
                if (j3 >= 0) {
                    break;
                }
                yVar = yVar.f2143f;
                j = j3;
            }
        } else {
            while (j > 0) {
                yVar = yVar.g;
                j -= (long) (yVar.f2140c - yVar.f2139b);
            }
        }
        if (jVar.g() == 2) {
            byte bA = jVar.a(0);
            byte bA2 = jVar.a(1);
            while (j < this.f2102c) {
                byte[] bArr = yVar.f2138a;
                i = (int) ((((long) yVar.f2139b) + j2) - j);
                int i3 = yVar.f2140c;
                while (i < i3) {
                    byte b2 = bArr[i];
                    if (b2 == bA || b2 == bA2) {
                        i2 = yVar.f2139b;
                    } else {
                        i++;
                    }
                }
                j2 = ((long) (yVar.f2140c - yVar.f2139b)) + j;
                yVar = yVar.f2143f;
                j = j2;
            }
            return -1L;
        }
        byte[] bArrC = jVar.c();
        while (j < this.f2102c) {
            byte[] bArr2 = yVar.f2138a;
            i = (int) ((((long) yVar.f2139b) + j2) - j);
            int i4 = yVar.f2140c;
            while (i < i4) {
                byte b3 = bArr2[i];
                for (byte b4 : bArrC) {
                    if (b3 == b4) {
                        i2 = yVar.f2139b;
                    }
                }
                i++;
            }
            j2 = ((long) (yVar.f2140c - yVar.f2139b)) + j;
            yVar = yVar.f2143f;
            j = j2;
        }
        return -1L;
        return ((long) (i - i2)) + j;
    }

    public String a(long j, Charset charset) {
        F.a(this.f2102c, 0L, j);
        if (charset == null) {
            throw new IllegalArgumentException("charset == null");
        }
        if (j > 2147483647L) {
            throw new IllegalArgumentException(b.a.a.a.a.a("byteCount > Integer.MAX_VALUE: ", j));
        }
        if (j == 0) {
            return "";
        }
        y yVar = this.f2101b;
        int i = yVar.f2139b;
        if (((long) i) + j > yVar.f2140c) {
            return new String(b(j), charset);
        }
        String str = new String(yVar.f2138a, i, (int) j, charset);
        yVar.f2139b = (int) (((long) yVar.f2139b) + j);
        this.f2102c -= j;
        if (yVar.f2139b == yVar.f2140c) {
            this.f2101b = yVar.a();
            z.a(yVar);
        }
        return str;
    }

    @Override // d.i
    public String a(long j) throws EOFException {
        if (j >= 0) {
            long j2 = UnsignedLong.UNSIGNED_MASK;
            if (j != UnsignedLong.UNSIGNED_MASK) {
                j2 = j + 1;
            }
            long jA = a((byte) 10, 0L, j2);
            if (jA != -1) {
                return i(jA);
            }
            if (j2 < this.f2102c && g(j2 - 1) == 13 && g(j2) == 10) {
                return i(j2);
            }
            g gVar = new g();
            a(gVar, 0L, Math.min(32L, this.f2102c));
            StringBuilder sbA = b.a.a.a.a.a("\\n not found: limit=");
            sbA.append(Math.min(this.f2102c, j));
            sbA.append(" content=");
            sbA.append(gVar.n().b());
            sbA.append((char) 8230);
            throw new EOFException(sbA.toString());
        }
        throw new IllegalArgumentException(b.a.a.a.a.a("limit < 0: ", j));
    }

    public int a(byte[] bArr, int i, int i2) {
        F.a(bArr.length, i, i2);
        y yVar = this.f2101b;
        if (yVar == null) {
            return -1;
        }
        int iMin = Math.min(i2, yVar.f2140c - yVar.f2139b);
        System.arraycopy(yVar.f2138a, yVar.f2139b, bArr, i, iMin);
        yVar.f2139b += iMin;
        this.f2102c -= (long) iMin;
        if (yVar.f2139b == yVar.f2140c) {
            this.f2101b = yVar.a();
            z.a(yVar);
        }
        return iMin;
    }

    @Override // d.h
    public g a(j jVar) {
        if (jVar != null) {
            jVar.a(this);
            return this;
        }
        throw new IllegalArgumentException("byteString == null");
    }

    @Override // d.h
    public g a(String str) {
        a(str, 0, str.length());
        return this;
    }

    public g a(String str, int i, int i2) {
        char cCharAt;
        if (str == null) {
            throw new IllegalArgumentException("string == null");
        }
        if (i < 0) {
            throw new IllegalArgumentException(b.a.a.a.a.a("beginIndex < 0: ", i));
        }
        if (i2 >= i) {
            if (i2 > str.length()) {
                StringBuilder sbA = b.a.a.a.a.a("endIndex > string.length: ", i2, " > ");
                sbA.append(str.length());
                throw new IllegalArgumentException(sbA.toString());
            }
            while (i < i2) {
                char cCharAt2 = str.charAt(i);
                if (cCharAt2 < 128) {
                    y yVarA = a(1);
                    byte[] bArr = yVarA.f2138a;
                    int i3 = yVarA.f2140c - i;
                    int iMin = Math.min(i2, 8192 - i3);
                    int i4 = i + 1;
                    bArr[i + i3] = (byte) cCharAt2;
                    while (true) {
                        i = i4;
                        if (i >= iMin || (cCharAt = str.charAt(i)) >= 128) {
                            break;
                        }
                        i4 = i + 1;
                        bArr[i + i3] = (byte) cCharAt;
                    }
                    int i5 = yVarA.f2140c;
                    int i6 = (i3 + i) - i5;
                    yVarA.f2140c = i5 + i6;
                    this.f2102c += (long) i6;
                } else {
                    if (cCharAt2 < 2048) {
                        writeByte((cCharAt2 >> 6) | 192);
                        writeByte((cCharAt2 & '?') | 128);
                    } else if (cCharAt2 >= 55296 && cCharAt2 <= 57343) {
                        int i7 = i + 1;
                        char cCharAt3 = i7 < i2 ? str.charAt(i7) : (char) 0;
                        if (cCharAt2 <= 56319 && cCharAt3 >= 56320 && cCharAt3 <= 57343) {
                            int i8 = (((cCharAt2 & 10239) << 10) | (9215 & cCharAt3)) + 65536;
                            writeByte((i8 >> 18) | 240);
                            writeByte(((i8 >> 12) & 63) | 128);
                            writeByte(((i8 >> 6) & 63) | 128);
                            writeByte((i8 & 63) | 128);
                            i += 2;
                        } else {
                            writeByte(63);
                            i = i7;
                        }
                    } else {
                        writeByte((cCharAt2 >> '\f') | 224);
                        writeByte(((cCharAt2 >> 6) & 63) | 128);
                        writeByte((cCharAt2 & '?') | 128);
                    }
                    i++;
                }
            }
            return this;
        }
        throw new IllegalArgumentException("endIndex < beginIndex: " + i2 + " < " + i);
    }

    public g a(String str, int i, int i2, Charset charset) {
        if (str == null) {
            throw new IllegalArgumentException("string == null");
        }
        if (i < 0) {
            throw new IllegalAccessError(b.a.a.a.a.a("beginIndex < 0: ", i));
        }
        if (i2 >= i) {
            if (i2 > str.length()) {
                StringBuilder sbA = b.a.a.a.a.a("endIndex > string.length: ", i2, " > ");
                sbA.append(str.length());
                throw new IllegalArgumentException(sbA.toString());
            }
            if (charset != null) {
                if (charset.equals(F.f2092a)) {
                    a(str, i, i2);
                    return this;
                }
                byte[] bytes = str.substring(i, i2).getBytes(charset);
                write(bytes, 0, bytes.length);
                return this;
            }
            throw new IllegalArgumentException("charset == null");
        }
        throw new IllegalArgumentException("endIndex < beginIndex: " + i2 + " < " + i);
    }

    @Override // d.h
    public long a(C c2) {
        if (c2 == null) {
            throw new IllegalArgumentException("source == null");
        }
        long j = 0;
        while (true) {
            long j2 = c2.read(this, 8192L);
            if (j2 == -1) {
                return j;
            }
            j += j2;
        }
    }

    public y a(int i) {
        if (i >= 1 && i <= 8192) {
            y yVar = this.f2101b;
            if (yVar == null) {
                this.f2101b = z.a();
                y yVar2 = this.f2101b;
                yVar2.g = yVar2;
                yVar2.f2143f = yVar2;
                return yVar2;
            }
            y yVar3 = yVar.g;
            if (yVar3.f2140c + i <= 8192 && yVar3.f2142e) {
                return yVar3;
            }
            y yVarA = z.a();
            yVar3.a(yVarA);
            return yVarA;
        }
        throw new IllegalArgumentException();
    }

    @Override // d.i
    public long a(byte b2) {
        return a(b2, 0L, UnsignedLong.UNSIGNED_MASK);
    }

    public long a(byte b2, long j, long j2) {
        y yVar;
        long j3 = 0;
        if (j >= 0 && j2 >= j) {
            long j4 = this.f2102c;
            if (j2 <= j4) {
                j4 = j2;
            }
            if (j == j4 || (yVar = this.f2101b) == null) {
                return -1L;
            }
            long j5 = this.f2102c;
            if (j5 - j >= j) {
                while (true) {
                    j5 = j3;
                    j3 = ((long) (yVar.f2140c - yVar.f2139b)) + j5;
                    if (j3 >= j) {
                        break;
                    }
                    yVar = yVar.f2143f;
                }
            } else {
                while (j5 > j) {
                    yVar = yVar.g;
                    j5 -= (long) (yVar.f2140c - yVar.f2139b);
                }
            }
            long j6 = j;
            while (j5 < j4) {
                byte[] bArr = yVar.f2138a;
                int iMin = (int) Math.min(yVar.f2140c, (((long) yVar.f2139b) + j4) - j5);
                for (int i = (int) ((((long) yVar.f2139b) + j6) - j5); i < iMin; i++) {
                    if (bArr[i] == b2) {
                        return ((long) (i - yVar.f2139b)) + j5;
                    }
                }
                j6 = ((long) (yVar.f2140c - yVar.f2139b)) + j5;
                yVar = yVar.f2143f;
                j5 = j6;
            }
            return -1L;
        }
        throw new IllegalArgumentException(String.format("size=%s fromIndex=%s toIndex=%s", Long.valueOf(this.f2102c), Long.valueOf(j), Long.valueOf(j2)));
    }

    @Override // d.i
    public boolean a(long j, j jVar) {
        int iG = jVar.g();
        if (j < 0 || iG < 0 || this.f2102c - j < iG || jVar.g() - 0 < iG) {
            return false;
        }
        for (int i = 0; i < iG; i++) {
            if (g(((long) i) + j) != jVar.a(0 + i)) {
                return false;
            }
        }
        return true;
    }

    public final a a(a aVar) {
        if (aVar.f2103a == null) {
            aVar.f2103a = this;
            aVar.f2104b = true;
            return aVar;
        }
        throw new IllegalStateException("already attached to a buffer");
    }
}
