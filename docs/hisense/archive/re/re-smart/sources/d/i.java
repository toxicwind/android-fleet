package d;

import java.io.InputStream;
import java.nio.channels.ReadableByteChannel;
import java.nio.charset.Charset;

/* JADX INFO: loaded from: classes2.dex */
public interface i extends C, ReadableByteChannel {
    long a(byte b2);

    @Deprecated
    g a();

    String a(long j);

    String a(Charset charset);

    void a(g gVar, long j);

    boolean a(long j, j jVar);

    String b();

    byte[] b(long j);

    short c();

    void c(long j);

    j d(long j);

    byte[] d();

    boolean e();

    long f();

    int g();

    g getBuffer();

    long h();

    InputStream i();

    byte readByte();

    void readFully(byte[] bArr);

    int readInt();

    long readLong();

    short readShort();

    boolean request(long j);

    void skip(long j);
}
