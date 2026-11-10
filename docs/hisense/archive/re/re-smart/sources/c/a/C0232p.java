package c.a;

import com.google.common.base.Preconditions;
import java.util.concurrent.Executor;

/* JADX INFO: renamed from: c.a.p, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public final class C0232p extends AbstractC0218e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final AbstractC0218e f1884a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final AbstractC0218e f1885b;

    public C0232p(AbstractC0218e abstractC0218e, AbstractC0218e abstractC0218e2) {
        Preconditions.checkNotNull(abstractC0218e, "creds1");
        this.f1884a = abstractC0218e;
        Preconditions.checkNotNull(abstractC0218e2, "creds2");
        this.f1885b = abstractC0218e2;
    }

    @Override // c.a.AbstractC0218e
    public void applyRequestMetadata(AbstractC0218e.b bVar, Executor executor, AbstractC0218e.a aVar) {
        this.f1884a.applyRequestMetadata(bVar, executor, new b(bVar, executor, aVar, C0236u.c()));
    }

    @Override // c.a.AbstractC0218e
    public void thisUsesUnstableApi() {
    }

    /* JADX INFO: renamed from: c.a.p$a */
    private static final class a extends AbstractC0218e.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final AbstractC0218e.a f1886a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final C0219ea f1887b;

        public a(AbstractC0218e.a aVar, C0219ea c0219ea) {
            this.f1886a = aVar;
            this.f1887b = c0219ea;
        }

        @Override // c.a.AbstractC0218e.a
        public void a(C0219ea c0219ea) {
            Preconditions.checkNotNull(c0219ea, "headers");
            C0219ea c0219ea2 = new C0219ea();
            c0219ea2.a(this.f1887b);
            c0219ea2.a(c0219ea);
            this.f1886a.a(c0219ea2);
        }

        @Override // c.a.AbstractC0218e.a
        public void a(za zaVar) {
            this.f1886a.a(zaVar);
        }
    }

    /* JADX INFO: renamed from: c.a.p$b */
    private final class b extends AbstractC0218e.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final AbstractC0218e.b f1888a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final Executor f1889b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final AbstractC0218e.a f1890c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final C0236u f1891d;

        public b(AbstractC0218e.b bVar, Executor executor, AbstractC0218e.a aVar, C0236u c0236u) {
            this.f1888a = bVar;
            this.f1889b = executor;
            Preconditions.checkNotNull(aVar, "delegate");
            this.f1890c = aVar;
            Preconditions.checkNotNull(c0236u, "context");
            this.f1891d = c0236u;
        }

        @Override // c.a.AbstractC0218e.a
        public void a(C0219ea c0219ea) {
            Preconditions.checkNotNull(c0219ea, "headers");
            C0236u c0236uA = this.f1891d.a();
            try {
                C0232p.this.f1885b.applyRequestMetadata(this.f1888a, this.f1889b, new a(this.f1890c, c0219ea));
            } finally {
                this.f1891d.a(c0236uA);
            }
        }

        @Override // c.a.AbstractC0218e.a
        public void a(za zaVar) {
            this.f1890c.a(zaVar);
        }
    }
}
