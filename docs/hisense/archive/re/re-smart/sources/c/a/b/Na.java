package c.a.b;

import c.a.C0086b;
import c.a.C0219ea;
import c.a.C0238w;
import c.a.C0240y;
import c.a.InterfaceC0233q;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.google.common.base.Preconditions;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class Na implements M {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public volatile boolean f1086a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public N f1087b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public M f1088c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public c.a.za f1089d;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public a f1091f;
    public long g;
    public long h;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public List<Runnable> f1090e = new ArrayList();
    public List<Runnable> i = new ArrayList();

    @Override // c.a.b.M
    public void a(C0238w c0238w) {
        Preconditions.checkState(this.f1087b == null, "May only be called before start");
        this.i.add(new Ca(this, c0238w));
    }

    public final Runnable b(M m) {
        synchronized (this) {
            if (this.f1088c != null) {
                return null;
            }
            Preconditions.checkNotNull(m, "stream");
            a(m);
            N n = this.f1087b;
            if (n == null) {
                this.f1090e = null;
                this.f1086a = true;
            }
            if (n == null) {
                return null;
            }
            b(n);
            return new Da(this);
        }
    }

    public void b(c.a.za zaVar) {
    }

    @Override // c.a.b.M
    public void c(int i) {
        Preconditions.checkState(this.f1087b == null, "May only be called before start");
        this.i.add(new Aa(this, i));
    }

    @Override // c.a.b.M
    public void d(int i) {
        Preconditions.checkState(this.f1087b == null, "May only be called before start");
        this.i.add(new Ba(this, i));
    }

    @Override // c.a.b.Md
    public void flush() {
        Preconditions.checkState(this.f1087b != null, "May only be called after start");
        if (this.f1086a) {
            this.f1088c.flush();
        } else {
            a(new Ga(this));
        }
    }

    @Override // c.a.b.M
    public C0086b getAttributes() {
        M m;
        synchronized (this) {
            m = this.f1088c;
        }
        return m != null ? m.getAttributes() : C0086b.f876a;
    }

    @Override // c.a.b.Md
    public boolean isReady() {
        if (this.f1086a) {
            return this.f1088c.isReady();
        }
        return false;
    }

    @Override // c.a.b.M
    public void a(C0198wb c0198wb) {
        synchronized (this) {
            if (this.f1087b == null) {
                return;
            }
            if (this.f1088c != null) {
                c0198wb.a("buffered_nanos", Long.valueOf(this.h - this.g));
                this.f1088c.a(c0198wb);
            } else {
                c0198wb.a("buffered_nanos", Long.valueOf(System.nanoTime() - this.g));
                c0198wb.f1502a.add("waiting_for_connection");
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:25:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:9:0x0019  */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x0022, code lost:
    
        r0 = r1.iterator();
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x002a, code lost:
    
        if (r0.hasNext() == false) goto L24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x002c, code lost:
    
        r0.next().run();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void c() {
        /*
            r3 = this;
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
        L5:
            monitor-enter(r3)
            java.util.List<java.lang.Runnable> r1 = r3.f1090e     // Catch: java.lang.Throwable -> L3b
            boolean r1 = r1.isEmpty()     // Catch: java.lang.Throwable -> L3b
            if (r1 == 0) goto L1d
            r0 = 0
            r3.f1090e = r0     // Catch: java.lang.Throwable -> L3b
            r0 = 1
            r3.f1086a = r0     // Catch: java.lang.Throwable -> L3b
            c.a.b.Na$a r0 = r3.f1091f     // Catch: java.lang.Throwable -> L3b
            monitor-exit(r3)     // Catch: java.lang.Throwable -> L3b
            if (r0 == 0) goto L1c
            r0.b()
        L1c:
            return
        L1d:
            java.util.List<java.lang.Runnable> r1 = r3.f1090e     // Catch: java.lang.Throwable -> L3b
            r3.f1090e = r0     // Catch: java.lang.Throwable -> L3b
            monitor-exit(r3)     // Catch: java.lang.Throwable -> L3b
            java.util.Iterator r0 = r1.iterator()
        L26:
            boolean r2 = r0.hasNext()
            if (r2 == 0) goto L36
            java.lang.Object r2 = r0.next()
            java.lang.Runnable r2 = (java.lang.Runnable) r2
            r2.run()
            goto L26
        L36:
            r1.clear()
            r0 = r1
            goto L5
        L3b:
            r0 = move-exception
            monitor-exit(r3)     // Catch: java.lang.Throwable -> L3b
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: c.a.b.Na.c():void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    static class a implements N {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final N f1092a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public volatile boolean f1093b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public List<Runnable> f1094c = new ArrayList();

        public a(N n) {
            this.f1092a = n;
        }

        public final void a(Runnable runnable) {
            synchronized (this) {
                if (this.f1093b) {
                    runnable.run();
                } else {
                    this.f1094c.add(runnable);
                }
            }
        }

        public void b() {
            List<Runnable> list;
            List<Runnable> arrayList = new ArrayList<>();
            while (true) {
                synchronized (this) {
                    if (this.f1094c.isEmpty()) {
                        this.f1094c = null;
                        this.f1093b = true;
                        return;
                    } else {
                        list = this.f1094c;
                        this.f1094c = arrayList;
                    }
                }
                Iterator<Runnable> it = list.iterator();
                while (it.hasNext()) {
                    it.next().run();
                }
                list.clear();
                arrayList = list;
            }
        }

        @Override // c.a.b.Nd
        public void a(Nd.a aVar) {
            if (this.f1093b) {
                this.f1092a.a(aVar);
            } else {
                a(new Ja(this, aVar));
            }
        }

        @Override // c.a.b.Nd
        public void a() {
            if (this.f1093b) {
                this.f1092a.a();
            } else {
                a(new Ka(this));
            }
        }

        @Override // c.a.b.N
        public void a(C0219ea c0219ea) {
            a(new La(this, c0219ea));
        }

        @Override // c.a.b.N
        public void a(c.a.za zaVar, N.a aVar, C0219ea c0219ea) {
            a(new Ma(this, zaVar, aVar, c0219ea));
        }
    }

    public final void a(Runnable runnable) {
        Preconditions.checkState(this.f1087b != null, "May only be called after start");
        synchronized (this) {
            if (!this.f1086a) {
                this.f1090e.add(runnable);
            } else {
                runnable.run();
            }
        }
    }

    public final void b(N n) {
        Iterator<Runnable> it = this.i.iterator();
        while (it.hasNext()) {
            it.next().run();
        }
        this.i = null;
        this.f1088c.a(n);
    }

    @Override // c.a.b.M
    public void b() {
        Preconditions.checkState(this.f1087b != null, "May only be called after start");
        a(new Ia(this));
    }

    @Override // c.a.b.Md
    public void b(int i) {
        Preconditions.checkState(this.f1087b != null, "May only be called after start");
        if (this.f1086a) {
            this.f1088c.b(i);
        } else {
            a(new RunnableC0187ua(this, i));
        }
    }

    @Override // c.a.b.M
    public void a(String str) {
        Preconditions.checkState(this.f1087b == null, "May only be called before start");
        Preconditions.checkNotNull(str, "authority");
        this.i.add(new Ea(this, str));
    }

    @Override // c.a.b.M
    public void b(boolean z) {
        Preconditions.checkState(this.f1087b == null, "May only be called before start");
        this.i.add(new RunnableC0201xa(this, z));
    }

    @Override // c.a.b.M
    public void a(N n) {
        c.a.za zaVar;
        boolean z;
        Preconditions.checkNotNull(n, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
        Preconditions.checkState(this.f1087b == null, "already started");
        synchronized (this) {
            zaVar = this.f1089d;
            z = this.f1086a;
            if (!z) {
                a aVar = new a(n);
                this.f1091f = aVar;
                n = aVar;
            }
            this.f1087b = n;
            this.g = System.nanoTime();
        }
        if (zaVar != null) {
            n.a(zaVar, N.a.PROCESSED, new C0219ea());
        } else if (z) {
            b(n);
        }
    }

    @Override // c.a.b.Md
    public void a(InputStream inputStream) {
        Preconditions.checkState(this.f1087b != null, "May only be called after start");
        Preconditions.checkNotNull(inputStream, "message");
        if (this.f1086a) {
            this.f1088c.a(inputStream);
        } else {
            a(new Fa(this, inputStream));
        }
    }

    @Override // c.a.b.M
    public void a(c.a.za zaVar) {
        boolean z = true;
        Preconditions.checkState(this.f1087b != null, "May only be called after start");
        Preconditions.checkNotNull(zaVar, "reason");
        synchronized (this) {
            if (this.f1088c == null) {
                a(Ic.f1023a);
                this.f1089d = zaVar;
                z = false;
            }
        }
        if (z) {
            a(new Ha(this, zaVar));
            return;
        }
        c();
        b(zaVar);
        this.f1087b.a(zaVar, N.a.PROCESSED, new C0219ea());
    }

    public final void a(M m) {
        Preconditions.checkState(this.f1088c == null, "realStream already set to %s", this.f1088c);
        this.f1088c = m;
        this.h = System.nanoTime();
    }

    @Override // c.a.b.Md
    public void a() {
        Preconditions.checkState(this.f1087b == null, "May only be called before start");
        this.i.add(new RunnableC0192va(this));
    }

    @Override // c.a.b.Md
    public void a(InterfaceC0233q interfaceC0233q) {
        Preconditions.checkState(this.f1087b == null, "May only be called before start");
        Preconditions.checkNotNull(interfaceC0233q, "compressor");
        this.i.add(new RunnableC0197wa(this, interfaceC0233q));
    }

    @Override // c.a.b.M
    public void a(C0240y c0240y) {
        Preconditions.checkState(this.f1087b == null, "May only be called before start");
        Preconditions.checkNotNull(c0240y, "decompressorRegistry");
        this.i.add(new RunnableC0205ya(this, c0240y));
    }

    @Override // c.a.b.Md
    public void a(boolean z) {
        Preconditions.checkState(this.f1087b != null, "May only be called after start");
        if (this.f1086a) {
            this.f1088c.a(z);
        } else {
            a(new RunnableC0209za(this, z));
        }
    }
}
