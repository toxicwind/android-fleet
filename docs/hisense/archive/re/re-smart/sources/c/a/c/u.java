package c.a.c;

import c.a.b.AbstractC0102d;
import c.a.b.Tc;
import java.io.EOFException;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes2.dex */
public class u extends AbstractC0102d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final d.g f1748a;

    public u(d.g gVar) {
        this.f1748a = gVar;
    }

    @Override // c.a.b.Tc
    public void a(byte[] bArr, int i, int i2) {
        while (i2 > 0) {
            int iA = this.f1748a.a(bArr, i, i2);
            if (iA == -1) {
                throw new IndexOutOfBoundsException(b.a.a.a.a.g("EOF trying to read ", i2, " bytes"));
            }
            i2 -= iA;
            i += iA;
        }
    }

    @Override // c.a.b.AbstractC0102d, c.a.b.Tc, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.f1748a.clear();
    }

    @Override // c.a.b.Tc
    public int m() {
        return (int) this.f1748a.f2102c;
    }

    @Override // c.a.b.Tc
    public int readUnsignedByte() {
        try {
            return this.f1748a.readByte() & 255;
        } catch (EOFException e2) {
            throw new IndexOutOfBoundsException(e2.getMessage());
        }
    }

    @Override // c.a.b.Tc
    public void skipBytes(int i) {
        try {
            this.f1748a.skip(i);
        } catch (EOFException e2) {
            throw new IndexOutOfBoundsException(e2.getMessage());
        }
    }

    @Override // c.a.b.Tc
    public void a(ByteBuffer byteBuffer) {
        throw new UnsupportedOperationException();
    }

    @Override // c.a.b.Tc
    public void a(OutputStream outputStream, int i) throws IOException {
        this.f1748a.a(outputStream, i);
    }

    @Override // c.a.b.Tc
    public Tc a(int i) {
        d.g gVar = new d.g();
        gVar.write(this.f1748a, i);
        return new u(gVar);
    }
}
