package okio;

import java.io.InterruptedIOException;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public class m extends D {
    private D e;

    public m(D d) {
        if (d == null) {
            throw new IllegalArgumentException("delegate == null");
        }
        this.e = d;
    }

    @Override // okio.D
    public D a() {
        return this.e.a();
    }

    @Override // okio.D
    public D b() {
        return this.e.b();
    }

    @Override // okio.D
    public long c() {
        return this.e.c();
    }

    @Override // okio.D
    public D d(long j) {
        return this.e.d(j);
    }

    @Override // okio.D
    public boolean e() {
        return this.e.e();
    }

    @Override // okio.D
    public void f() throws InterruptedIOException {
        this.e.f();
    }

    @Override // okio.D
    public D g(long j, TimeUnit timeUnit) {
        return this.e.g(j, timeUnit);
    }

    @Override // okio.D
    public long h() {
        return this.e.h();
    }

    public final D i() {
        return this.e;
    }

    public final m j(D d) {
        if (d == null) {
            throw new IllegalArgumentException("delegate == null");
        }
        this.e = d;
        return this;
    }
}
