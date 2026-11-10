package c.a.b;

import androidx.core.app.NotificationCompat;
import c.a.AbstractC0218e;
import c.a.AbstractC0225i;
import c.a.AbstractC0230n;
import c.a.C0219ea;
import c.a.C0220f;
import c.a.C0223ga;
import c.a.C0232p;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import java.net.SocketAddress;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: renamed from: c.a.b.z, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public final class C0208z implements P {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final P f1525a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final AbstractC0218e f1526b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Executor f1527c;

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: c.a.b.z$a */
    class a extends Ya {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final X f1528a;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public volatile c.a.za f1530c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public c.a.za f1531d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public c.a.za f1532e;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final AtomicInteger f1529b = new AtomicInteger(-2147483647);

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public final Hc.a f1533f = new C0200x(this);

        public a(X x, String str) {
            Preconditions.checkNotNull(x, "delegate");
            this.f1528a = x;
            Preconditions.checkNotNull(str, "authority");
        }

        @Override // c.a.b.Ya, c.a.b.Cc
        public void a(c.a.za zaVar) {
            Preconditions.checkNotNull(zaVar, NotificationCompat.CATEGORY_STATUS);
            synchronized (this) {
                if (this.f1529b.get() < 0) {
                    this.f1530c = zaVar;
                    this.f1529b.addAndGet(Integer.MAX_VALUE);
                } else if (this.f1532e != null) {
                    return;
                }
                if (this.f1529b.get() != 0) {
                    this.f1532e = zaVar;
                } else {
                    b().a(zaVar);
                }
            }
        }

        @Override // c.a.b.Ya
        public X b() {
            return this.f1528a;
        }

        public final void c() {
            synchronized (this) {
                if (this.f1529b.get() != 0) {
                    return;
                }
                c.a.za zaVar = this.f1531d;
                c.a.za zaVar2 = this.f1532e;
                this.f1531d = null;
                this.f1532e = null;
                if (zaVar != null) {
                    b().b(zaVar);
                }
                if (zaVar2 != null) {
                    b().a(zaVar2);
                }
            }
        }

        @Override // c.a.b.Ya, c.a.b.Cc
        public void b(c.a.za zaVar) {
            Preconditions.checkNotNull(zaVar, NotificationCompat.CATEGORY_STATUS);
            synchronized (this) {
                if (this.f1529b.get() < 0) {
                    this.f1530c = zaVar;
                    this.f1529b.addAndGet(Integer.MAX_VALUE);
                    if (this.f1529b.get() != 0) {
                        this.f1531d = zaVar;
                    } else {
                        b().b(zaVar);
                    }
                }
            }
        }

        @Override // c.a.b.O
        public M a(C0223ga<?, ?> c0223ga, C0219ea c0219ea, C0220f c0220f, AbstractC0230n[] abstractC0230nArr) {
            AbstractC0218e c0232p = c0220f.f1816e;
            if (c0232p == null) {
                c0232p = C0208z.this.f1526b;
            } else if (C0208z.this.f1526b != null) {
                c0232p = new C0232p(C0208z.this.f1526b, c0232p);
            }
            if (c0232p != null) {
                Hc hc = new Hc(this.f1528a, c0223ga, c0219ea, c0220f, this.f1533f, abstractC0230nArr);
                if (this.f1529b.incrementAndGet() > 0) {
                    ((C0200x) this.f1533f).a();
                    return new Ta(this.f1530c, N.a.PROCESSED, abstractC0230nArr);
                }
                try {
                    c0232p.applyRequestMetadata(new C0204y(this, c0223ga, c0220f), (Executor) MoreObjects.firstNonNull(c0220f.f1814c, C0208z.this.f1527c), hc);
                } catch (Throwable th) {
                    c.a.za zaVarB = c.a.za.h.b("Credentials should use fail() instead of throwing exceptions").b(th);
                    Preconditions.checkArgument(!zaVarB.c(), "Cannot fail with OK status");
                    Preconditions.checkState(!hc.j, "apply() or fail() already called");
                    hc.a(new Ta(zaVarB, N.a.PROCESSED, hc.g));
                }
                return hc.a();
            }
            if (this.f1529b.get() >= 0) {
                return new Ta(this.f1530c, N.a.PROCESSED, abstractC0230nArr);
            }
            return this.f1528a.a(c0223ga, c0219ea, c0220f, abstractC0230nArr);
        }
    }

    public C0208z(P p, AbstractC0218e abstractC0218e, Executor executor) {
        Preconditions.checkNotNull(p, "delegate");
        this.f1525a = p;
        this.f1526b = abstractC0218e;
        Preconditions.checkNotNull(executor, "appExecutor");
        this.f1527c = executor;
    }

    @Override // c.a.b.P, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.f1525a.close();
    }

    @Override // c.a.b.P
    public ScheduledExecutorService n() {
        return this.f1525a.n();
    }

    @Override // c.a.b.P
    public X a(SocketAddress socketAddress, P.a aVar, AbstractC0225i abstractC0225i) {
        return new a(this.f1525a.a(socketAddress, aVar, abstractC0225i), aVar.f1098a);
    }
}
