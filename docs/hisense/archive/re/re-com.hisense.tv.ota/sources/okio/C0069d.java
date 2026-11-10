package okio;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.concurrent.TimeUnit;

/* JADX INFO: renamed from: okio.d, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public class C0069d extends D {
    private static final long h = TimeUnit.SECONDS.toMillis(60);
    private static final long i = TimeUnit.MILLISECONDS.toNanos(h);
    static C0069d j;
    private boolean e;
    private C0069d f;
    private long g;

    static C0069d i() throws InterruptedException {
        C0069d c0069d = j.f;
        long jNanoTime = System.nanoTime();
        if (c0069d == null) {
            C0069d.class.wait(h);
            if (j.f != null || System.nanoTime() - jNanoTime < i) {
                return null;
            }
            return j;
        }
        long jP = c0069d.p(jNanoTime);
        if (jP > 0) {
            long j2 = jP / 1000000;
            C0069d.class.wait(j2, (int) (jP - (1000000 * j2)));
            return null;
        }
        j.f = c0069d.f;
        c0069d.f = null;
        return c0069d;
    }

    private static synchronized boolean j(C0069d c0069d) {
        boolean z;
        for (C0069d c0069d2 = j; c0069d2 != null; c0069d2 = c0069d2.f) {
            if (c0069d2.f == c0069d) {
                c0069d2.f = c0069d.f;
                c0069d.f = null;
                z = false;
            }
        }
        z = true;
        return z;
    }

    private long p(long j2) {
        return this.g - j2;
    }

    /* JADX WARN: Code duplicated, block: B:25:0x005c A[Catch: all -> 0x0067, TRY_LEAVE, TryCatch #0 {, blocks: (B:4:0x0003, B:6:0x0007, B:7:0x0016, B:10:0x0022, B:11:0x002b, B:16:0x003a, B:17:0x0040, B:19:0x0044, B:22:0x004f, B:23:0x0052, B:25:0x005c, B:15:0x0034, B:28:0x0061, B:29:0x0066), top: B:33:0x0003 }] */
    private static synchronized void q(C0069d c0069d, long j2, boolean z) {
        C0069d c0069d2;
        if (j == null) {
            j = new C0069d();
            new C0068c().start();
        }
        long jNanoTime = System.nanoTime();
        if (j2 == 0 || !z) {
            if (j2 == 0) {
                if (!z) {
                    throw new AssertionError();
                }
                c0069d.g = c0069d.c();
            }
            long jP = c0069d.p(jNanoTime);
            c0069d2 = j;
            while (c0069d2.f != null && jP >= c0069d2.f.p(jNanoTime)) {
                c0069d2 = c0069d2.f;
            }
            c0069d.f = c0069d2.f;
            c0069d2.f = c0069d;
            if (c0069d2 == j) {
                C0069d.class.notify();
            }
        } else {
            j2 = Math.min(j2, c0069d.c() - jNanoTime);
        }
        c0069d.g = j2 + jNanoTime;
        long jP2 = c0069d.p(jNanoTime);
        c0069d2 = j;
        while (c0069d2.f != null) {
            c0069d2 = c0069d2.f;
        }
        c0069d.f = c0069d2.f;
        c0069d2.f = c0069d;
        if (c0069d2 == j) {
            C0069d.class.notify();
        }
    }

    public final void k() {
        if (this.e) {
            throw new IllegalStateException("Unbalanced enter/exit");
        }
        long jH = h();
        boolean zE = e();
        if (jH != 0 || zE) {
            this.e = true;
            q(this, jH, zE);
        }
    }

    final IOException l(IOException iOException) {
        return !n() ? iOException : o(iOException);
    }

    final void m(boolean z) {
        if (n() && z) {
            throw o(null);
        }
    }

    public final boolean n() {
        if (!this.e) {
            return false;
        }
        this.e = false;
        return j(this);
    }

    protected IOException o(IOException iOException) {
        InterruptedIOException interruptedIOException = new InterruptedIOException("timeout");
        if (iOException != null) {
            interruptedIOException.initCause(iOException);
        }
        return interruptedIOException;
    }

    public final A r(A a2) {
        return new C0066a(this, a2);
    }

    public final B s(B b) {
        return new C0067b(this, b);
    }

    protected void t() {
    }
}
