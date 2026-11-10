package c.a.b;

import java.io.Closeable;
import java.io.OutputStream;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes2.dex */
public interface Tc extends Closeable {
    Tc a(int i);

    void a(OutputStream outputStream, int i);

    void a(ByteBuffer byteBuffer);

    void a(byte[] bArr, int i, int i2);

    @Override // java.io.Closeable, java.lang.AutoCloseable
    void close();

    int m();

    boolean markSupported();

    void o();

    int readUnsignedByte();

    void reset();

    void skipBytes(int i);
}
