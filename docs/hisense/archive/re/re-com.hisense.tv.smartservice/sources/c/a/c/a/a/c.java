package c.a.c.a.a;

import java.io.Closeable;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public interface c extends Closeable {
    void a(int i, a aVar);

    void a(int i, a aVar, byte[] bArr);

    void a(i iVar);

    void a(boolean z, boolean z2, int i, int i2, List<d> list);

    void b(i iVar);

    void connectionPreface();

    void data(boolean z, int i, d.g gVar, int i2);

    void flush();

    int maxDataLength();

    void ping(boolean z, int i, int i2);

    void windowUpdate(int i, long j);
}
