package b.d.a.a;

import java.io.Closeable;
import java.io.Flushable;

/* JADX INFO: loaded from: classes.dex */
public abstract class d implements Closeable, Flushable, n {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public l f144a;

    public enum a {
        AUTO_CLOSE_TARGET(true),
        AUTO_CLOSE_JSON_CONTENT(true),
        QUOTE_FIELD_NAMES(true),
        QUOTE_NON_NUMERIC_NUMBERS(true),
        WRITE_NUMBERS_AS_STRINGS(false),
        WRITE_BIGDECIMAL_AS_PLAIN(false),
        FLUSH_PASSED_TO_STREAM(true),
        ESCAPE_NON_ASCII(false),
        STRICT_DUPLICATE_DETECTION(false),
        IGNORE_UNKNOWN(false);

        public final boolean l;
        public final int m = 1 << ordinal();

        a(boolean z) {
            this.l = z;
        }
    }

    public abstract void a(double d2);

    public abstract void a(float f2);

    public abstract void a(boolean z);

    public abstract void b(int i);

    public abstract void b(String str);

    public abstract void c(String str);

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public abstract void close();

    public abstract void g(long j);

    public final void p() {
        b.d.a.a.e.g.a();
        throw null;
    }

    public abstract void q();

    public abstract void r();

    public abstract void s();

    public abstract void t();

    public abstract void u();
}
