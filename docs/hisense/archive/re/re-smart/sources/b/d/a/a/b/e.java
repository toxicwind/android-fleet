package b.d.a.a.b;

import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: classes.dex */
public final class e extends InputStream {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final c f97a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final InputStream f98b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public byte[] f99c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f100d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f101e;

    public e(c cVar, InputStream inputStream, byte[] bArr, int i, int i2) {
        this.f97a = cVar;
        this.f98b = inputStream;
        this.f99c = bArr;
        this.f100d = i;
        this.f101e = i2;
    }

    @Override // java.io.InputStream
    public int available() {
        return this.f99c != null ? this.f101e - this.f100d : this.f98b.available();
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        p();
        this.f98b.close();
    }

    @Override // java.io.InputStream
    public void mark(int i) {
        if (this.f99c == null) {
            this.f98b.mark(i);
        }
    }

    @Override // java.io.InputStream
    public boolean markSupported() {
        return this.f99c == null && this.f98b.markSupported();
    }

    public final void p() {
        byte[] bArr = this.f99c;
        if (bArr != null) {
            this.f99c = null;
            c cVar = this.f97a;
            if (cVar != null) {
                cVar.a(bArr);
            }
        }
    }

    @Override // java.io.InputStream
    public int read() {
        byte[] bArr = this.f99c;
        if (bArr == null) {
            return this.f98b.read();
        }
        int i = this.f100d;
        this.f100d = i + 1;
        int i2 = bArr[i] & 255;
        if (this.f100d >= this.f101e) {
            p();
        }
        return i2;
    }

    @Override // java.io.InputStream
    public void reset() throws IOException {
        if (this.f99c == null) {
            this.f98b.reset();
        }
    }

    @Override // java.io.InputStream
    public long skip(long j) {
        long j2;
        if (this.f99c != null) {
            int i = this.f101e;
            int i2 = this.f100d;
            long j3 = i - i2;
            if (j3 > j) {
                this.f100d = i2 + ((int) j);
                return j;
            }
            p();
            j2 = j3 + 0;
            j -= j3;
        } else {
            j2 = 0;
        }
        return j > 0 ? j2 + this.f98b.skip(j) : j2;
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr) {
        int length = bArr.length;
        if (this.f99c != null) {
            int i = this.f101e - this.f100d;
            if (length > i) {
                length = i;
            }
            System.arraycopy(this.f99c, this.f100d, bArr, 0, length);
            this.f100d += length;
            if (this.f100d < this.f101e) {
                return length;
            }
            p();
            return length;
        }
        return this.f98b.read(bArr, 0, length);
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i, int i2) {
        if (this.f99c != null) {
            int i3 = this.f101e - this.f100d;
            if (i2 > i3) {
                i2 = i3;
            }
            System.arraycopy(this.f99c, this.f100d, bArr, i, i2);
            this.f100d += i2;
            if (this.f100d >= this.f101e) {
                p();
            }
            return i2;
        }
        return this.f98b.read(bArr, i, i2);
    }
}
