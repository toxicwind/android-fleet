package d;

import java.io.Closeable;
import java.io.Flushable;

/* JADX INFO: loaded from: classes2.dex */
public interface B extends Closeable, Flushable {
    @Override // java.io.Closeable, java.lang.AutoCloseable
    void close();

    void flush();

    E timeout();

    void write(g gVar, long j);
}
