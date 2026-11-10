package okio;

import java.io.Closeable;
import java.io.Flushable;

/* JADX INFO: loaded from: classes.dex */
public interface A extends Closeable, Flushable {
    D b();

    @Override // java.io.Closeable, java.lang.AutoCloseable
    void close();

    void flush();

    void o(h hVar, long j);
}
