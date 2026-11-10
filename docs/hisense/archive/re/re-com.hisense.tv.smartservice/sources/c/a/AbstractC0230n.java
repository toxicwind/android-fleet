package c.a;

import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;

/* JADX INFO: renamed from: c.a.n, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public abstract class AbstractC0230n extends Ca {

    /* JADX INFO: renamed from: c.a.n$a */
    public static abstract class a {
    }

    /* JADX INFO: renamed from: c.a.n$b */
    public static abstract class b extends a {
    }

    /* JADX INFO: renamed from: c.a.n$c */
    public static final class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final C0086b f1875a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final C0220f f1876b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final int f1877c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final boolean f1878d;

        /* JADX INFO: renamed from: c.a.n$c$a */
        public static final class a {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public C0086b f1879a = C0086b.f876a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public C0220f f1880b = C0220f.f1812a;

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            public int f1881c;

            /* JADX INFO: renamed from: d, reason: collision with root package name */
            public boolean f1882d;

            public a a(C0220f c0220f) {
                Preconditions.checkNotNull(c0220f, "callOptions cannot be null");
                this.f1880b = c0220f;
                return this;
            }

            public c a() {
                return new c(this.f1879a, this.f1880b, this.f1881c, this.f1882d);
            }
        }

        public c(C0086b c0086b, C0220f c0220f, int i, boolean z) {
            Preconditions.checkNotNull(c0086b, "transportAttrs");
            this.f1875a = c0086b;
            Preconditions.checkNotNull(c0220f, "callOptions");
            this.f1876b = c0220f;
            this.f1877c = i;
            this.f1878d = z;
        }

        public static a a() {
            return new a();
        }

        public a b() {
            a aVar = new a();
            aVar.a(this.f1876b);
            C0086b c0086b = this.f1875a;
            Preconditions.checkNotNull(c0086b, "transportAttrs cannot be null");
            aVar.f1879a = c0086b;
            aVar.f1881c = this.f1877c;
            aVar.f1882d = this.f1878d;
            return aVar;
        }

        public String toString() {
            return MoreObjects.toStringHelper(this).add("transportAttrs", this.f1875a).add("callOptions", this.f1876b).add("previousAttempts", this.f1877c).add("isTransparentRetry", this.f1878d).toString();
        }
    }

    public void a() {
    }

    public void a(C0086b c0086b, C0219ea c0219ea) {
    }

    public void a(C0219ea c0219ea) {
    }

    public void b() {
    }
}
