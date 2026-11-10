package d;

import java.io.Closeable;

/* JADX INFO: loaded from: classes2.dex */
public interface C extends Closeable {
    @Override // java.io.Closeable, java.lang.AutoCloseable
    void close();

    long read(g gVar, long j);

    E timeout();
}
