package okio;

import java.io.InterruptedIOException;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public class D {
    public static final D d = new C();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private boolean f261a;
    private long b;
    private long c;

    public D a() {
        this.f261a = false;
        return this;
    }

    public D b() {
        this.c = 0L;
        return this;
    }

    public long c() {
        if (this.f261a) {
            return this.b;
        }
        throw new IllegalStateException("No deadline");
    }

    public D d(long j) {
        this.f261a = true;
        this.b = j;
        return this;
    }

    public boolean e() {
        return this.f261a;
    }

    public void f() throws InterruptedIOException {
        if (Thread.interrupted()) {
            Thread.currentThread().interrupt();
            throw new InterruptedIOException("interrupted");
        }
        if (this.f261a && this.b - System.nanoTime() <= 0) {
            throw new InterruptedIOException("deadline reached");
        }
    }

    public D g(long j, TimeUnit timeUnit) {
        if (j >= 0) {
            if (timeUnit == null) {
                throw new IllegalArgumentException("unit == null");
            }
            this.c = timeUnit.toNanos(j);
            return this;
        }
        throw new IllegalArgumentException("timeout < 0: " + j);
    }

    public long h() {
        return this.c;
    }
}
