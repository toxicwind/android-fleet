package b.d.a.a.e;

import java.io.OutputStream;
import java.util.LinkedList;

/* JADX INFO: loaded from: classes.dex */
public final class b extends OutputStream {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final byte[] f198a = new byte[0];

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final LinkedList<byte[]> f199b = new LinkedList<>();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f200c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public byte[] f201d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f202e;

    public b(a aVar) {
        this.f201d = aVar == null ? new byte[500] : aVar.a(2);
    }

    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
    }

    @Override // java.io.OutputStream, java.io.Flushable
    public void flush() {
    }

    public final void p() {
        this.f200c += this.f201d.length;
        int iMax = Math.max(this.f200c >> 1, 1000);
        if (iMax > 262144) {
            iMax = 262144;
        }
        this.f199b.add(this.f201d);
        this.f201d = new byte[iMax];
        this.f202e = 0;
    }

    public void reset() {
        this.f200c = 0;
        this.f202e = 0;
        if (this.f199b.isEmpty()) {
            return;
        }
        this.f199b.clear();
    }

    @Override // java.io.OutputStream
    public void write(int i) {
        if (this.f202e >= this.f201d.length) {
            p();
        }
        byte[] bArr = this.f201d;
        int i2 = this.f202e;
        this.f202e = i2 + 1;
        bArr[i2] = (byte) i;
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr) {
        int length = bArr.length;
        int i = 0;
        while (true) {
            int iMin = Math.min(this.f201d.length - this.f202e, length);
            if (iMin > 0) {
                System.arraycopy(bArr, i, this.f201d, this.f202e, iMin);
                i += iMin;
                this.f202e += iMin;
                length -= iMin;
            }
            if (length <= 0) {
                return;
            } else {
                p();
            }
        }
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr, int i, int i2) {
        while (true) {
            int iMin = Math.min(this.f201d.length - this.f202e, i2);
            if (iMin > 0) {
                System.arraycopy(bArr, i, this.f201d, this.f202e, iMin);
                i += iMin;
                this.f202e += iMin;
                i2 -= iMin;
            }
            if (i2 <= 0) {
                return;
            } else {
                p();
            }
        }
    }
}
