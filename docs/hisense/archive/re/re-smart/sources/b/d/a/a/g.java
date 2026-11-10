package b.d.a.a;

import java.io.Closeable;

/* JADX INFO: loaded from: classes.dex */
public abstract class g implements Closeable, n {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f212a;

    public enum a {
        AUTO_CLOSE_SOURCE(true),
        ALLOW_COMMENTS(false),
        ALLOW_YAML_COMMENTS(false),
        ALLOW_UNQUOTED_FIELD_NAMES(false),
        ALLOW_SINGLE_QUOTES(false),
        ALLOW_UNQUOTED_CONTROL_CHARS(false),
        ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER(false),
        ALLOW_NUMERIC_LEADING_ZEROS(false),
        ALLOW_NON_NUMERIC_NUMBERS(false),
        STRICT_DUPLICATE_DETECTION(false);

        public final boolean l;
        public final int m = 1 << ordinal();

        a(boolean z) {
            this.l = z;
        }
    }

    public boolean a(a aVar) {
        return (aVar.m & this.f212a) != 0;
    }

    public f b(String str) {
        return new f(str, p());
    }

    public abstract e p();

    public abstract double q();

    public abstract long r();

    public abstract String s();

    public long t() {
        b.d.a.a.a.c cVar = (b.d.a.a.a.c) this;
        j jVar = cVar.f70b;
        if (jVar == null) {
            return 0L;
        }
        switch (jVar.r) {
            case 6:
                String strS = cVar.s();
                if ("null".equals(strS)) {
                    return 0L;
                }
                return b.d.a.a.b.f.a(strS, 0L);
            case 7:
            case 8:
                return cVar.r();
            case 9:
                return 1L;
            case 10:
            case 11:
            default:
                return 0L;
            case 12:
                return 0L;
        }
    }

    public abstract j u();

    public abstract g v();
}
