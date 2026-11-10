package okio;

import java.io.OutputStream;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes.dex */
final class v implements i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final h f277a = new h();
    public final A b;
    boolean c;

    v(A a2) {
        if (a2 == null) {
            throw new NullPointerException("sink == null");
        }
        this.b = a2;
    }

    @Override // okio.i
    public i F() {
        if (this.c) {
            throw new IllegalStateException("closed");
        }
        long jO = this.f277a.O();
        if (jO > 0) {
            this.b.o(this.f277a, jO);
        }
        return this;
    }

    @Override // okio.i
    public i G(long j) {
        if (this.c) {
            throw new IllegalStateException("closed");
        }
        this.f277a.m0(j);
        return F();
    }

    @Override // okio.A
    public D b() {
        return this.b.b();
    }

    @Override // okio.i
    public h c() {
        return this.f277a;
    }

    @Override // okio.A, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws Throwable {
        if (this.c) {
            return;
        }
        if (this.f277a.b > 0) {
            this.b.o(this.f277a, this.f277a.b);
        }
        th = null;
        try {
            this.b.close();
        } catch (Throwable th) {
            if (th == null) {
                th = th;
            }
        }
        this.c = true;
        if (th == null) {
            return;
        }
        E.e(th);
        throw null;
    }

    @Override // okio.i
    public i f(String str) {
        if (this.c) {
            throw new IllegalStateException("closed");
        }
        this.f277a.r0(str);
        F();
        return this;
    }

    @Override // okio.i, okio.A, java.io.Flushable
    public void flush() {
        if (this.c) {
            throw new IllegalStateException("closed");
        }
        h hVar = this.f277a;
        long j = hVar.b;
        if (j > 0) {
            this.b.o(hVar, j);
        }
        this.b.flush();
    }

    @Override // okio.i
    public i g(byte[] bArr) {
        if (this.c) {
            throw new IllegalStateException("closed");
        }
        this.f277a.h0(bArr);
        F();
        return this;
    }

    @Override // okio.i
    public i h(int i) {
        if (this.c) {
            throw new IllegalStateException("closed");
        }
        this.f277a.n0(i);
        return F();
    }

    @Override // java.nio.channels.Channel
    public boolean isOpen() {
        return !this.c;
    }

    @Override // okio.i
    public i j(byte[] bArr, int i, int i2) {
        if (this.c) {
            throw new IllegalStateException("closed");
        }
        this.f277a.i0(bArr, i, i2);
        F();
        return this;
    }

    @Override // okio.i
    public i k(long j) {
        if (this.c) {
            throw new IllegalStateException("closed");
        }
        this.f277a.l0(j);
        F();
        return this;
    }

    @Override // okio.A
    public void o(h hVar, long j) {
        if (this.c) {
            throw new IllegalStateException("closed");
        }
        this.f277a.o(hVar, j);
        F();
    }

    @Override // okio.i
    public i q(int i) {
        if (this.c) {
            throw new IllegalStateException("closed");
        }
        this.f277a.k0(i);
        F();
        return this;
    }

    @Override // okio.i
    public OutputStream r() {
        return new u(this);
    }

    public String toString() {
        return "buffer(" + this.b + ")";
    }

    @Override // java.nio.channels.WritableByteChannel
    public int write(ByteBuffer byteBuffer) {
        if (this.c) {
            throw new IllegalStateException("closed");
        }
        int iWrite = this.f277a.write(byteBuffer);
        F();
        return iWrite;
    }

    @Override // okio.i
    public i x(int i) {
        if (this.c) {
            throw new IllegalStateException("closed");
        }
        this.f277a.o0(i);
        F();
        return this;
    }
}
