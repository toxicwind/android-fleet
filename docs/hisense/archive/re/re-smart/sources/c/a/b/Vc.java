package c.a.b;

import c.a.InterfaceC0241z;
import com.google.common.base.Preconditions;
import com.google.common.net.MediaType;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.InvalidMarkException;
import java.nio.charset.Charset;

/* JADX INFO: loaded from: classes2.dex */
public final class Vc {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Tc f1167a;

    static {
        byte[] bArr = new byte[0];
        f1167a = new b(bArr, 0, bArr.length);
    }

    public static Tc a(byte[] bArr, int i, int i2) {
        return new b(bArr, i, i2);
    }

    private static final class a extends InputStream implements c.a.S, c.a.F, InterfaceC0241z {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public Tc f1168a;

        public a(Tc tc) {
            Preconditions.checkNotNull(tc, "buffer");
            this.f1168a = tc;
        }

        @Override // java.io.InputStream
        public int available() {
            return this.f1168a.m();
        }

        @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            this.f1168a.close();
        }

        @Override // java.io.InputStream
        public void mark(int i) {
            this.f1168a.o();
        }

        @Override // java.io.InputStream
        public boolean markSupported() {
            return this.f1168a.markSupported();
        }

        @Override // java.io.InputStream
        public int read() {
            if (this.f1168a.m() == 0) {
                return -1;
            }
            return this.f1168a.readUnsignedByte();
        }

        @Override // java.io.InputStream
        public void reset() {
            this.f1168a.reset();
        }

        @Override // java.io.InputStream
        public long skip(long j) {
            int iMin = (int) Math.min(this.f1168a.m(), j);
            this.f1168a.skipBytes(iMin);
            return iMin;
        }

        @Override // java.io.InputStream
        public int read(byte[] bArr, int i, int i2) {
            if (this.f1168a.m() == 0) {
                return -1;
            }
            int iMin = Math.min(this.f1168a.m(), i2);
            this.f1168a.a(bArr, i, iMin);
            return iMin;
        }
    }

    public static String a(Tc tc, Charset charset) {
        Preconditions.checkNotNull(charset, MediaType.CHARSET_ATTRIBUTE);
        Preconditions.checkNotNull(tc, "buffer");
        int iM = tc.m();
        byte[] bArr = new byte[iM];
        tc.a(bArr, 0, iM);
        return new String(bArr, charset);
    }

    private static class b extends AbstractC0102d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f1169a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final int f1170b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final byte[] f1171c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f1172d = -1;

        public b(byte[] bArr, int i, int i2) {
            Preconditions.checkArgument(i >= 0, "offset must be >= 0");
            Preconditions.checkArgument(i2 >= 0, "length must be >= 0");
            int i3 = i2 + i;
            Preconditions.checkArgument(i3 <= bArr.length, "offset + length exceeds array boundary");
            Preconditions.checkNotNull(bArr, "bytes");
            this.f1171c = bArr;
            this.f1169a = i;
            this.f1170b = i3;
        }

        @Override // c.a.b.Tc
        public Tc a(int i) {
            if (m() < i) {
                throw new IndexOutOfBoundsException();
            }
            int i2 = this.f1169a;
            this.f1169a = i2 + i;
            return new b(this.f1171c, i2, i);
        }

        @Override // c.a.b.Tc
        public int m() {
            return this.f1170b - this.f1169a;
        }

        @Override // c.a.b.AbstractC0102d, c.a.b.Tc
        public boolean markSupported() {
            return true;
        }

        @Override // c.a.b.AbstractC0102d, c.a.b.Tc
        public void o() {
            this.f1172d = this.f1169a;
        }

        @Override // c.a.b.Tc
        public int readUnsignedByte() {
            b(1);
            byte[] bArr = this.f1171c;
            int i = this.f1169a;
            this.f1169a = i + 1;
            return bArr[i] & 255;
        }

        @Override // c.a.b.AbstractC0102d, c.a.b.Tc
        public void reset() {
            int i = this.f1172d;
            if (i == -1) {
                throw new InvalidMarkException();
            }
            this.f1169a = i;
        }

        @Override // c.a.b.Tc
        public void skipBytes(int i) {
            if (m() < i) {
                throw new IndexOutOfBoundsException();
            }
            this.f1169a += i;
        }

        @Override // c.a.b.Tc
        public void a(OutputStream outputStream, int i) throws IOException {
            if (m() >= i) {
                outputStream.write(this.f1171c, this.f1169a, i);
                this.f1169a += i;
                return;
            }
            throw new IndexOutOfBoundsException();
        }

        @Override // c.a.b.Tc
        public void a(byte[] bArr, int i, int i2) {
            System.arraycopy(this.f1171c, this.f1169a, bArr, i, i2);
            this.f1169a += i2;
        }

        @Override // c.a.b.Tc
        public void a(ByteBuffer byteBuffer) {
            Preconditions.checkNotNull(byteBuffer, "dest");
            int iRemaining = byteBuffer.remaining();
            b(iRemaining);
            byteBuffer.put(this.f1171c, this.f1169a, iRemaining);
            this.f1169a += iRemaining;
        }
    }

    public static InputStream a(Tc tc, boolean z) {
        if (!z) {
            tc = new Uc(tc);
        }
        return new a(tc);
    }
}
