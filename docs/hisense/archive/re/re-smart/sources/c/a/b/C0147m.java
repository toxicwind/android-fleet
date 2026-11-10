package c.a.b;

import c.a.InterfaceC0239x;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.google.common.base.Preconditions;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: renamed from: c.a.b.m, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public class C0147m implements InterfaceC0088aa {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Ec.a f1349a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final C0167q f1350b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Ec f1351c;

    /* JADX INFO: renamed from: c.a.b.m$a */
    private class a extends b implements Closeable {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final Closeable f1352d;

        public a(C0147m c0147m, Runnable runnable, Closeable closeable) {
            super(runnable, null);
            this.f1352d = closeable;
        }

        @Override // java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            this.f1352d.close();
        }
    }

    /* JADX INFO: renamed from: c.a.b.m$b */
    private class b implements Nd.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Runnable f1353a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public boolean f1354b = false;

        public /* synthetic */ b(Runnable runnable, RunnableC0122h runnableC0122h) {
            this.f1353a = runnable;
        }

        @Override // c.a.b.Nd.a
        public InputStream next() {
            if (!this.f1354b) {
                this.f1353a.run();
                this.f1354b = true;
            }
            return C0147m.this.f1350b.f1412c.poll();
        }
    }

    /* JADX INFO: renamed from: c.a.b.m$c */
    interface c extends C0167q.a {
    }

    public C0147m(Ec.a aVar, c cVar, Ec ec) {
        Preconditions.checkNotNull(aVar, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
        this.f1349a = new Kd(aVar);
        this.f1350b = new C0167q(this.f1349a, cVar);
        ec.f940a = this.f1350b;
        this.f1351c = ec;
    }

    @Override // c.a.b.InterfaceC0088aa
    public void a(InterfaceC0239x interfaceC0239x) {
        Ec ec = this.f1351c;
        Preconditions.checkState(ec.f945f == null, "Already set full stream decompressor");
        Preconditions.checkNotNull(interfaceC0239x, "Can't pass an empty decompressor");
        ec.f944e = interfaceC0239x;
    }

    @Override // c.a.b.InterfaceC0088aa
    public void b(int i) {
        this.f1349a.a(new b(new RunnableC0122h(this, i), null));
    }

    @Override // c.a.b.InterfaceC0088aa
    public void c(int i) {
        this.f1351c.f941b = i;
    }

    @Override // c.a.b.InterfaceC0088aa, java.lang.AutoCloseable
    public void close() {
        this.f1351c.s = true;
        this.f1349a.a(new b(new RunnableC0142l(this), null));
    }

    @Override // c.a.b.InterfaceC0088aa
    public void p() {
        this.f1349a.a(new b(new RunnableC0137k(this), null));
    }

    @Override // c.a.b.InterfaceC0088aa
    public void a(Tc tc) {
        this.f1349a.a(new a(this, new RunnableC0127i(this, tc), new C0132j(this, tc)));
    }
}
