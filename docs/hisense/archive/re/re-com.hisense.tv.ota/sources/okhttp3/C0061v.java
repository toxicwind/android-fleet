package okhttp3;

import java.lang.ref.Reference;
import java.net.Socket;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* JADX INFO: renamed from: okhttp3.v, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0061v {
    private static final Executor g = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60, TimeUnit.SECONDS, new SynchronousQueue(), okhttp3.g0.e.G("OkHttp ConnectionPool", true));

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final int f256a;
    private final long b;
    private final Runnable c;
    private final Deque d;
    final okhttp3.internal.connection.d e;
    boolean f;

    public C0061v() {
        this(5, 5L, TimeUnit.MINUTES);
    }

    public C0061v(int i, long j, TimeUnit timeUnit) {
        this.c = new RunnableC0060u(this);
        this.d = new ArrayDeque();
        this.e = new okhttp3.internal.connection.d();
        this.f256a = i;
        this.b = timeUnit.toNanos(j);
        if (j > 0) {
            return;
        }
        throw new IllegalArgumentException("keepAliveDuration <= 0: " + j);
    }

    private int e(okhttp3.internal.connection.c cVar, long j) {
        List list = cVar.n;
        int i = 0;
        while (i < list.size()) {
            Reference reference = (Reference) list.get(i);
            if (reference.get() != null) {
                i++;
            } else {
                okhttp3.g0.k.k.l().u("A connection to " + cVar.a().a().l() + " was leaked. Did you forget to close a response body?", ((okhttp3.internal.connection.g) reference).f224a);
                list.remove(i);
                cVar.k = true;
                if (list.isEmpty()) {
                    cVar.o = j - this.b;
                    return 0;
                }
            }
        }
        return list.size();
    }

    long a(long j) {
        synchronized (this) {
            okhttp3.internal.connection.c cVar = null;
            long j2 = Long.MIN_VALUE;
            int i = 0;
            int i2 = 0;
            for (okhttp3.internal.connection.c cVar2 : this.d) {
                if (e(cVar2, j) > 0) {
                    i2++;
                } else {
                    i++;
                    long j3 = j - cVar2.o;
                    if (j3 > j2) {
                        cVar = cVar2;
                        j2 = j3;
                    }
                }
            }
            if (j2 < this.b && i <= this.f256a) {
                if (i > 0) {
                    return this.b - j2;
                }
                if (i2 > 0) {
                    return this.b;
                }
                this.f = false;
                return -1L;
            }
            this.d.remove(cVar);
            okhttp3.g0.e.h(cVar.r());
            return 0L;
        }
    }

    boolean b(okhttp3.internal.connection.c cVar) {
        if (cVar.k || this.f256a == 0) {
            this.d.remove(cVar);
            return true;
        }
        notifyAll();
        return false;
    }

    Socket c(C0029a c0029a, okhttp3.internal.connection.h hVar) {
        for (okhttp3.internal.connection.c cVar : this.d) {
            if (cVar.n(c0029a, null) && cVar.p() && cVar != hVar.d()) {
                return hVar.m(cVar);
            }
        }
        return null;
    }

    okhttp3.internal.connection.c d(C0029a c0029a, okhttp3.internal.connection.h hVar, f0 f0Var) {
        for (okhttp3.internal.connection.c cVar : this.d) {
            if (cVar.n(c0029a, f0Var)) {
                hVar.a(cVar, true);
                return cVar;
            }
        }
        return null;
    }

    void f(okhttp3.internal.connection.c cVar) {
        if (!this.f) {
            this.f = true;
            g.execute(this.c);
        }
        this.d.add(cVar);
    }
}
