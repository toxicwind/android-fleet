package okio;

import java.io.OutputStream;
import java.nio.channels.WritableByteChannel;

/* JADX INFO: loaded from: classes.dex */
public interface i extends A, WritableByteChannel {
    i F();

    i G(long j);

    h c();

    i f(String str);

    @Override // okio.A, java.io.Flushable
    void flush();

    i g(byte[] bArr);

    i h(int i);

    i j(byte[] bArr, int i, int i2);

    i k(long j);

    i q(int i);

    OutputStream r();

    i x(int i);
}
