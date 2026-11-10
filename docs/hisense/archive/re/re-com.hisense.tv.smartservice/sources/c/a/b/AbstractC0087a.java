package c.a.b;

import androidx.core.app.NotificationCompat;
import c.a.AbstractC0230n;
import c.a.C0219ea;
import c.a.C0220f;
import c.a.C0238w;
import c.a.C0240y;
import c.a.InterfaceC0231o;
import c.a.InterfaceC0233q;
import c.a.InterfaceC0239x;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.google.common.base.Preconditions;
import com.google.common.io.ByteStreams;
import com.google.firebase.installations.remote.FirebaseInstallationServiceClient;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

/* JADX INFO: renamed from: c.a.b.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public abstract class AbstractC0087a extends AbstractC0112f implements M, Gc.c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Logger f1208a = Logger.getLogger(AbstractC0087a.class.getName());

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Td f1209b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final InterfaceC0099cb f1210c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f1211d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f1212e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public C0219ea f1213f;
    public volatile boolean g;

    /* JADX INFO: renamed from: c.a.b.a$a, reason: collision with other inner class name */
    private class C0008a implements InterfaceC0099cb {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public C0219ea f1214a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public boolean f1215b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final Ld f1216c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public byte[] f1217d;

        public C0008a(C0219ea c0219ea, Ld ld) {
            Preconditions.checkNotNull(c0219ea, "headers");
            this.f1214a = c0219ea;
            Preconditions.checkNotNull(ld, "statsTraceCtx");
            this.f1216c = ld;
        }

        @Override // c.a.b.InterfaceC0099cb
        public InterfaceC0099cb a(InterfaceC0233q interfaceC0233q) {
            return this;
        }

        @Override // c.a.b.InterfaceC0099cb
        public InterfaceC0099cb a(boolean z) {
            return this;
        }

        @Override // c.a.b.InterfaceC0099cb
        public void a(InputStream inputStream) {
            Preconditions.checkState(this.f1217d == null, "writePayload should not be called multiple times");
            try {
                this.f1217d = ByteStreams.toByteArray(inputStream);
                for (c.a.Ca ca : this.f1216c.f1069a) {
                    ca.b(0);
                }
                Ld ld = this.f1216c;
                byte[] bArr = this.f1217d;
                ld.a(0, bArr.length, bArr.length);
                Ld ld2 = this.f1216c;
                long length = this.f1217d.length;
                for (c.a.Ca ca2 : ld2.f1069a) {
                    ca2.c(length);
                }
                Ld ld3 = this.f1216c;
                long length2 = this.f1217d.length;
                for (c.a.Ca ca3 : ld3.f1069a) {
                    ca3.d(length2);
                }
            } catch (IOException e2) {
                throw new RuntimeException(e2);
            }
        }

        @Override // c.a.b.InterfaceC0099cb
        public void close() {
            this.f1215b = true;
            Preconditions.checkState(this.f1217d != null, "Lack of request message. GET request is only supported for unary requests");
            ((c.a.c.k) AbstractC0087a.this).p.a(this.f1214a, this.f1217d);
            this.f1217d = null;
            this.f1214a = null;
        }

        @Override // c.a.b.InterfaceC0099cb
        public void d(int i) {
        }

        @Override // c.a.b.InterfaceC0099cb
        public void flush() {
        }

        @Override // c.a.b.InterfaceC0099cb
        public boolean isClosed() {
            return this.f1215b;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX INFO: renamed from: c.a.b.a$b */
    public interface b {
    }

    public AbstractC0087a(Vd vd, Ld ld, Td td, C0219ea c0219ea, C0220f c0220f, boolean z) {
        Preconditions.checkNotNull(c0219ea, "headers");
        Preconditions.checkNotNull(td, "transportTracer");
        this.f1209b = td;
        this.f1211d = C0149mb.a(c0220f);
        this.f1212e = z;
        if (z) {
            this.f1210c = new C0008a(c0219ea, ld);
        } else {
            this.f1210c = new Gc(this, vd, ld);
            this.f1213f = c0219ea;
        }
    }

    @Override // c.a.b.M
    public void a(C0238w c0238w) {
        this.f1213f.a(C0149mb.f1359b);
        this.f1213f.a(C0149mb.f1359b, Long.valueOf(Math.max(0L, c0238w.a(TimeUnit.NANOSECONDS))));
    }

    @Override // c.a.b.M
    public final void b(boolean z) {
        ((c.a.c.k) this).o.k = z;
    }

    @Override // c.a.b.M
    public void d(int i) {
        this.f1210c.d(i);
    }

    @Override // c.a.b.Md
    public final boolean isReady() {
        return (this.f1210c.isClosed() ? false : ((c.a.c.k) this).o.a()) && !this.g;
    }

    @Override // c.a.b.M
    public void c(int i) {
        ((c.a.c.k) this).o.f1251a.c(i);
    }

    @Override // c.a.b.M
    public final void a(C0240y c0240y) {
        c.a.c.k.b bVar = ((c.a.c.k) this).o;
        Preconditions.checkState(bVar.j == null, "Already called start");
        Preconditions.checkNotNull(c0240y, "decompressorRegistry");
        bVar.l = c0240y;
    }

    @Override // c.a.b.M
    public final void b() {
        c.a.c.k kVar = (c.a.c.k) this;
        if (kVar.o.o) {
            return;
        }
        kVar.o.o = true;
        this.f1210c.close();
    }

    @Override // c.a.b.M
    public final void a(N n) {
        c.a.c.k kVar = (c.a.c.k) this;
        c.a.c.k.b bVar = kVar.o;
        Preconditions.checkState(bVar.j == null, "Already called setListener");
        Preconditions.checkNotNull(n, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
        bVar.j = n;
        if (this.f1212e) {
            return;
        }
        kVar.p.a(this.f1213f, null);
        this.f1213f = null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX INFO: renamed from: c.a.b.a$c */
    public static abstract class c extends AbstractC0112f.a {
        public final Ld h;
        public boolean i;
        public N j;
        public boolean k;
        public C0240y l;
        public boolean m;
        public Runnable n;
        public volatile boolean o;
        public boolean p;
        public boolean q;

        public c(int i, Ld ld, Td td) {
            super(i, ld, td);
            this.l = C0240y.f1929b;
            this.m = false;
            Preconditions.checkNotNull(ld, "statsTraceCtx");
            this.h = ld;
        }

        @Override // c.a.b.Ec.a
        public void a(boolean z) {
            Preconditions.checkState(this.p, "status should have been reported on deframer closed");
            this.m = true;
            if (this.q && z) {
                a(c.a.za.k.b("Encountered end-of-stream mid-frame"), N.a.PROCESSED, true, new C0219ea());
            }
            Runnable runnable = this.n;
            if (runnable != null) {
                runnable.run();
                this.n = null;
            }
        }

        public final boolean e() {
            return this.o;
        }

        public void a(C0219ea c0219ea) {
            boolean z;
            Preconditions.checkState(!this.p, "Received headers on closed stream");
            for (c.a.Ca ca : this.h.f1069a) {
                ((AbstractC0230n) ca).a();
            }
            String str = (String) c0219ea.b(C0149mb.f1362e);
            if (!this.k || str == null) {
                z = false;
            } else if (str.equalsIgnoreCase(FirebaseInstallationServiceClient.GZIP_CONTENT_ENCODING)) {
                C0159ob c0159ob = new C0159ob();
                Ec ec = this.f1254d;
                Preconditions.checkState(ec.f944e == InterfaceC0231o.b.f1883a, "per-message decompressor already set");
                Preconditions.checkState(ec.f945f == null, "full stream decompressor already set");
                Preconditions.checkNotNull(c0159ob, "Can't pass a null full stream decompressor");
                ec.f945f = c0159ob;
                ec.m = null;
                this.f1251a = new C0147m(this, this, this.f1254d);
                z = true;
            } else {
                if (!str.equalsIgnoreCase("identity")) {
                    ((c.a.c.k.b) this).a(c.a.za.k.b(String.format("Can't find full stream decompressor for %s", str)).b());
                    return;
                }
                z = false;
            }
            String str2 = (String) c0219ea.b(C0149mb.f1360c);
            if (str2 != null) {
                C0240y.a aVar = this.l.f1930c.get(str2);
                InterfaceC0239x interfaceC0239x = aVar != null ? aVar.f1932a : null;
                if (interfaceC0239x == null) {
                    ((c.a.c.k.b) this).a(c.a.za.k.b(String.format("Can't find decompressor for %s", str2)).b());
                    return;
                } else if (interfaceC0239x != InterfaceC0231o.b.f1883a) {
                    if (z) {
                        ((c.a.c.k.b) this).a(c.a.za.k.b(String.format("Full stream and gRPC message encoding cannot both be set", new Object[0])).b());
                        return;
                    }
                    this.f1251a.a(interfaceC0239x);
                }
            }
            this.j.a(c0219ea);
        }

        public final void a(c.a.za zaVar, boolean z, C0219ea c0219ea) {
            a(zaVar, N.a.PROCESSED, z, c0219ea);
        }

        public final void a(c.a.za zaVar, N.a aVar, boolean z, C0219ea c0219ea) {
            Preconditions.checkNotNull(zaVar, NotificationCompat.CATEGORY_STATUS);
            Preconditions.checkNotNull(c0219ea, "trailers");
            if (!this.p || z) {
                this.p = true;
                this.q = zaVar.c();
                d();
                if (this.m) {
                    this.n = null;
                    a(zaVar, aVar, c0219ea);
                    return;
                }
                this.n = new RunnableC0092b(this, zaVar, aVar, c0219ea);
                if (z) {
                    this.f1251a.close();
                } else {
                    this.f1251a.p();
                }
            }
        }

        public final void a(c.a.za zaVar, N.a aVar, C0219ea c0219ea) {
            if (this.i) {
                return;
            }
            this.i = true;
            Ld ld = this.h;
            if (ld.f1070b.compareAndSet(false, true)) {
                for (c.a.Ca ca : ld.f1069a) {
                    ca.a(zaVar);
                }
            }
            this.j.a(zaVar, aVar, c0219ea);
            Td td = this.f1253c;
            if (td != null) {
                if (zaVar.c()) {
                    td.f1160d++;
                } else {
                    td.f1161e++;
                }
            }
        }
    }

    public final void a(Ud ud, boolean z, boolean z2, int i) {
        Preconditions.checkArgument(ud != null || z, "null frame before EOS");
        ((c.a.c.k) this).p.a(ud, z, z2, i);
    }

    @Override // c.a.b.M
    public final void a(c.a.za zaVar) {
        Preconditions.checkArgument(!zaVar.c(), "Should not cancel with OK status");
        this.g = true;
        ((c.a.c.k) this).p.a(zaVar);
    }

    @Override // c.a.b.M
    public final void a(C0198wb c0198wb) {
        c0198wb.a("remote_addr", ((c.a.c.k) this).q.a(c.a.E.f792a));
    }
}
