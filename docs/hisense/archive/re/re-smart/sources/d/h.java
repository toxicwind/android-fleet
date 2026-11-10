package d;

import java.io.OutputStream;
import java.nio.channels.WritableByteChannel;

/* JADX INFO: loaded from: classes2.dex */
public interface h extends B, WritableByteChannel {
    long a(C c2);

    g a();

    h a(j jVar);

    h a(String str);

    h e(long j);

    h f(long j);

    @Override // d.B, java.io.Flushable
    void flush();

    h j();

    h k();

    OutputStream l();

    h write(byte[] bArr);

    h write(byte[] bArr, int i, int i2);

    h writeByte(int i);

    h writeInt(int i);

    h writeShort(int i);
}
