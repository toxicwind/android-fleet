package okio;

import java.io.InputStream;
import java.nio.channels.ReadableByteChannel;
import java.nio.charset.Charset;

/* JADX INFO: loaded from: classes.dex */
public interface j extends B, ReadableByteChannel {
    long A();

    String C(Charset charset);

    InputStream D();

    String E();

    byte H();

    long I();

    void J(long j);

    int L();

    h c();

    ByteString d(long j);

    int i();

    String l(long j);

    void m(long j);

    boolean n();

    void s(byte[] bArr);

    short t();

    long u(byte b);

    short v();

    byte[] w(long j);

    boolean y(long j, ByteString byteString);

    boolean z(long j);
}
