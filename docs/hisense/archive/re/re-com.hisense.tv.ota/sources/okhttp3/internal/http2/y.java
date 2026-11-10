package okhttp3.internal.http2;

import java.io.Closeable;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.Socket;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public final class y implements Closeable {
    private static final ExecutorService y = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60, TimeUnit.SECONDS, new SynchronousQueue(), okhttp3.g0.e.G("OkHttp Http2Connection", true));

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final boolean f244a;
    final s b;
    final String d;
    int e;
    int f;
    private boolean g;
    private final ScheduledExecutorService h;
    private final ExecutorService i;
    final K j;
    long r;
    final Socket u;
    final G v;
    final x w;
    final Map c = new LinkedHashMap();
    private long k = 0;
    private long l = 0;
    private long m = 0;
    private long n = 0;
    private long o = 0;
    private long p = 0;
    long q = 0;
    L s = new L();
    final L t = new L();
    final Set x = new LinkedHashSet();

    y(p pVar) {
        this.j = pVar.f;
        boolean z = pVar.g;
        this.f244a = z;
        this.b = pVar.e;
        int i = z ? 1 : 2;
        this.f = i;
        if (pVar.g) {
            this.f = i + 2;
        }
        if (pVar.g) {
            this.s.i(7, 16777216);
        }
        this.d = pVar.b;
        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(1, okhttp3.g0.e.G(okhttp3.g0.e.r("OkHttp %s Writer", this.d), false));
        this.h = scheduledThreadPoolExecutor;
        if (pVar.h != 0) {
            q qVar = new q(this);
            int i2 = pVar.h;
            scheduledThreadPoolExecutor.scheduleAtFixedRate(qVar, i2, i2, TimeUnit.MILLISECONDS);
        }
        this.i = new ThreadPoolExecutor(0, 1, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue(), okhttp3.g0.e.G(okhttp3.g0.e.r("OkHttp %s Push Observer", this.d), true));
        this.t.i(7, 65535);
        this.t.i(5, 16384);
        this.r = this.t.d();
        this.u = pVar.f242a;
        this.v = new G(pVar.d, this.f244a);
        this.w = new x(this, new B(pVar.c, this.f244a));
    }

    static /* synthetic */ long M(y yVar) {
        long j = yVar.k;
        yVar.k = 1 + j;
        return j;
    }

    static /* synthetic */ long R(y yVar) {
        long j = yVar.n;
        yVar.n = 1 + j;
        return j;
    }

    static /* synthetic */ long S(y yVar) {
        long j = yVar.o;
        yVar.o = 1 + j;
        return j;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void U() {
        try {
            T(ErrorCode.PROTOCOL_ERROR, ErrorCode.PROTOCOL_ERROR);
        } catch (IOException unused) {
        }
    }

    private F Y(int i, List list, boolean z) {
        int i2;
        F f;
        boolean z2;
        boolean z3 = !z;
        synchronized (this.v) {
            synchronized (this) {
                if (this.f > 1073741823) {
                    i0(ErrorCode.REFUSED_STREAM);
                }
                if (this.g) {
                    throw new ConnectionShutdownException();
                }
                i2 = this.f;
                this.f += 2;
                f = new F(i2, this, z3, false, null);
                z2 = !z || this.r == 0 || f.b == 0;
                if (f.m()) {
                    this.c.put(Integer.valueOf(i2), f);
                }
            }
            if (i == 0) {
                this.v.U(z3, i2, i, list);
            } else {
                if (this.f244a) {
                    throw new IllegalArgumentException("client streams shouldn't have associated stream IDs");
                }
                this.v.R(i, i2, list);
            }
        }
        if (z2) {
            this.v.flush();
        }
        return f;
    }

    private synchronized void b0(okhttp3.g0.b bVar) {
        if (!this.g) {
            this.i.execute(bVar);
        }
    }

    static /* synthetic */ long p(y yVar) {
        long j = yVar.l;
        yVar.l = 1 + j;
        return j;
    }

    void T(ErrorCode errorCode, ErrorCode errorCode2) throws IOException {
        F[] fArr = null;
        try {
            i0(errorCode);
            e = null;
        } catch (IOException e) {
            e = e;
        }
        synchronized (this) {
            if (!this.c.isEmpty()) {
                fArr = (F[]) this.c.values().toArray(new F[this.c.size()]);
                this.c.clear();
            }
        }
        if (fArr != null) {
            for (F f : fArr) {
                try {
                    f.f(errorCode2);
                } catch (IOException e2) {
                    if (e != null) {
                        e = e2;
                    }
                }
            }
        }
        try {
            this.v.close();
        } catch (IOException e3) {
            if (e == null) {
                e = e3;
            }
        }
        try {
            this.u.close();
        } catch (IOException e4) {
            e = e4;
        }
        this.h.shutdown();
        this.i.shutdown();
        if (e != null) {
            throw e;
        }
    }

    synchronized F V(int i) {
        return (F) this.c.get(Integer.valueOf(i));
    }

    public synchronized boolean W(long j) {
        if (this.g) {
            return false;
        }
        return this.n >= this.m || j < this.p;
    }

    public synchronized int X() {
        return this.t.e(Integer.MAX_VALUE);
    }

    public F Z(List list, boolean z) {
        return Y(0, list, z);
    }

    void a0(int i, okio.j jVar, int i2, boolean z) throws IOException {
        okio.h hVar = new okio.h();
        long j = i2;
        jVar.J(j);
        jVar.K(hVar, j);
        if (hVar.c0() == j) {
            b0(new n(this, "OkHttp %s Push Data[%s]", new Object[]{this.d, Integer.valueOf(i)}, i, hVar, i2, z));
            return;
        }
        throw new IOException(hVar.c0() + " != " + i2);
    }

    void c0(int i, List list, boolean z) {
        try {
            b0(new m(this, "OkHttp %s Push Headers[%s]", new Object[]{this.d, Integer.valueOf(i)}, i, list, z));
        } catch (RejectedExecutionException unused) {
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        T(ErrorCode.NO_ERROR, ErrorCode.CANCEL);
    }

    void d0(int i, List list) {
        synchronized (this) {
            if (this.x.contains(Integer.valueOf(i))) {
                p0(i, ErrorCode.PROTOCOL_ERROR);
                return;
            }
            this.x.add(Integer.valueOf(i));
            try {
                b0(new C0049l(this, "OkHttp %s Push Request[%s]", new Object[]{this.d, Integer.valueOf(i)}, i, list));
            } catch (RejectedExecutionException unused) {
            }
        }
    }

    void e0(int i, ErrorCode errorCode) {
        b0(new o(this, "OkHttp %s Push Reset[%s]", new Object[]{this.d, Integer.valueOf(i)}, i, errorCode));
    }

    boolean f0(int i) {
        return i != 0 && (i & 1) == 0;
    }

    public void flush() {
        this.v.flush();
    }

    synchronized F g0(int i) {
        F f;
        f = (F) this.c.remove(Integer.valueOf(i));
        notifyAll();
        return f;
    }

    void h0() {
        synchronized (this) {
            if (this.n < this.m) {
                return;
            }
            this.m++;
            this.p = System.nanoTime() + 1000000000;
            try {
                this.h.execute(new C0048k(this, "OkHttp %s ping", this.d));
            } catch (RejectedExecutionException unused) {
            }
        }
    }

    public void i0(ErrorCode errorCode) {
        synchronized (this.v) {
            synchronized (this) {
                if (this.g) {
                    return;
                }
                this.g = true;
                this.v.N(this.e, errorCode, okhttp3.g0.e.f185a);
            }
        }
    }

    public void j0() {
        k0(true);
    }

    void k0(boolean z) {
        if (z) {
            this.v.e();
            this.v.T(this.s);
            int iD = this.s.d();
            if (iD != 65535) {
                this.v.V(0, iD - 65535);
            }
        }
        new Thread(this.w).start();
    }

    synchronized void l0(long j) {
        long j2 = this.q + j;
        this.q = j2;
        if (j2 >= this.s.d() / 2) {
            q0(0, this.q);
            this.q = 0L;
        }
    }

    public void m0(int i, boolean z, okio.h hVar, long j) {
        int iMin;
        long j2;
        if (j == 0) {
            this.v.p(z, i, hVar, 0);
            return;
        }
        while (j > 0) {
            synchronized (this) {
                while (this.r <= 0) {
                    try {
                        if (!this.c.containsKey(Integer.valueOf(i))) {
                            throw new IOException("stream closed");
                        }
                        wait();
                    } catch (InterruptedException unused) {
                        Thread.currentThread().interrupt();
                        throw new InterruptedIOException();
                    }
                }
                iMin = Math.min((int) Math.min(j, this.r), this.v.P());
                j2 = iMin;
                this.r -= j2;
            }
            j -= j2;
            this.v.p(z && j == 0, i, hVar, iMin);
        }
    }

    void n0(boolean z, int i, int i2) {
        try {
            this.v.Q(z, i, i2);
        } catch (IOException unused) {
            U();
        }
    }

    void o0(int i, ErrorCode errorCode) {
        this.v.S(i, errorCode);
    }

    void p0(int i, ErrorCode errorCode) {
        try {
            this.h.execute(new C0046i(this, "OkHttp %s stream %d", new Object[]{this.d, Integer.valueOf(i)}, i, errorCode));
        } catch (RejectedExecutionException unused) {
        }
    }

    void q0(int i, long j) {
        try {
            this.h.execute(new C0047j(this, "OkHttp Window Update %s stream %d", new Object[]{this.d, Integer.valueOf(i)}, i, j));
        } catch (RejectedExecutionException unused) {
        }
    }
}
