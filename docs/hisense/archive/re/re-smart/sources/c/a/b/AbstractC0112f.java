package c.a.b;

import c.a.InterfaceC0231o;
import c.a.InterfaceC0233q;
import com.google.common.base.Preconditions;
import java.io.InputStream;

/* JADX INFO: renamed from: c.a.b.f, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public abstract class AbstractC0112f implements Md {
    @Override // c.a.b.Md
    public void a() {
        c.a.c.k.b bVar = ((c.a.c.k) this).o;
        bVar.f1254d.a(bVar);
        bVar.f1251a = bVar.f1254d;
    }

    @Override // c.a.b.Md
    public final void b(int i) {
        c.a.c.k.b bVar = ((c.a.c.k) this).o;
        InterfaceC0088aa interfaceC0088aa = bVar.f1251a;
        bVar.a(new RunnableC0107e(bVar, c.b.c.a(), i));
    }

    @Override // c.a.b.Md
    public final void flush() {
        AbstractC0087a abstractC0087a = (AbstractC0087a) this;
        if (abstractC0087a.f1210c.isClosed()) {
            return;
        }
        abstractC0087a.f1210c.flush();
    }

    /* JADX INFO: renamed from: c.a.b.f$a */
    public static abstract class a implements C0147m.c, Ec.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public InterfaceC0088aa f1251a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final Object f1252b = new Object();

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final Td f1253c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final Ec f1254d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f1255e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public boolean f1256f;
        public boolean g;

        public a(int i, Ld ld, Td td) {
            Preconditions.checkNotNull(ld, "statsTraceCtx");
            Preconditions.checkNotNull(td, "transportTracer");
            this.f1253c = td;
            this.f1254d = new Ec(this, InterfaceC0231o.b.f1883a, i, ld, td);
            this.f1251a = this.f1254d;
        }

        @Override // c.a.b.Ec.a
        public void a(Nd.a aVar) {
            ((AbstractC0087a.c) this).j.a(aVar);
        }

        public final void b(int i) {
            synchronized (this.f1252b) {
                this.f1255e += i;
            }
        }

        public void c() {
            Preconditions.checkState(((AbstractC0087a.c) this).j != null);
            synchronized (this.f1252b) {
                Preconditions.checkState(this.f1256f ? false : true, "Already allocated");
                this.f1256f = true;
            }
            b();
        }

        public final void d() {
            synchronized (this.f1252b) {
                this.g = true;
            }
        }

        public final boolean a() {
            boolean z;
            synchronized (this.f1252b) {
                z = this.f1256f && this.f1255e < 32768 && !this.g;
            }
            return z;
        }

        public final void b() {
            boolean zA;
            synchronized (this.f1252b) {
                zA = a();
            }
            if (zA) {
                ((AbstractC0087a.c) this).j.a();
            }
        }

        public final void c(int i) {
            boolean z;
            synchronized (this.f1252b) {
                Preconditions.checkState(this.f1256f, "onStreamAllocated was not called, but it seems the stream is active");
                z = true;
                boolean z2 = this.f1255e < 32768;
                this.f1255e -= i;
                boolean z3 = this.f1255e < 32768;
                if (z2 || !z3) {
                    z = false;
                }
            }
            if (z) {
                b();
            }
        }
    }

    @Override // c.a.b.Md
    public final void a(boolean z) {
        ((AbstractC0087a) this).f1210c.a(z);
    }

    @Override // c.a.b.Md
    public final void a(InputStream inputStream) {
        Preconditions.checkNotNull(inputStream, "message");
        try {
            if (!((AbstractC0087a) this).f1210c.isClosed()) {
                ((AbstractC0087a) this).f1210c.a(inputStream);
            }
        } finally {
            C0149mb.a(inputStream);
        }
    }

    @Override // c.a.b.Md
    public final void a(InterfaceC0233q interfaceC0233q) {
        InterfaceC0099cb interfaceC0099cb = ((AbstractC0087a) this).f1210c;
        Preconditions.checkNotNull(interfaceC0233q, "compressor");
        interfaceC0099cb.a(interfaceC0233q);
    }

    public final void a(int i) {
        ((c.a.c.k) this).o.b(i);
    }
}
