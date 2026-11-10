package okhttp3.internal.http2;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.RejectedExecutionException;
import okio.ByteString;

/* JADX INFO: loaded from: classes.dex */
class x extends okhttp3.g0.b implements A {
    final B b;
    final /* synthetic */ y c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    x(y yVar, B b) {
        super("OkHttp %s", yVar.d);
        this.c = yVar;
        this.b = b;
    }

    @Override // okhttp3.internal.http2.A
    public void a(int i, ErrorCode errorCode, ByteString byteString) {
        F[] fArr;
        byteString.r();
        synchronized (this.c) {
            fArr = (F[]) this.c.c.values().toArray(new F[this.c.c.size()]);
            this.c.g = true;
        }
        for (F f : fArr) {
            if (f.i() > i && f.l()) {
                f.r(ErrorCode.REFUSED_STREAM);
                this.c.g0(f.i());
            }
        }
    }

    @Override // okhttp3.internal.http2.A
    public void b(int i, int i2, List list) {
        this.c.d0(i2, list);
    }

    @Override // okhttp3.internal.http2.A
    public void c(int i, int i2, int i3, boolean z) {
    }

    @Override // okhttp3.internal.http2.A
    public void d(boolean z, int i, int i2, List list) {
        if (this.c.f0(i)) {
            this.c.c0(i, list, z);
            return;
        }
        synchronized (this.c) {
            F fV = this.c.V(i);
            if (fV != null) {
                fV.q(list);
                if (z) {
                    fV.p();
                    return;
                }
                return;
            }
            if (this.c.g) {
                return;
            }
            if (i <= this.c.e) {
                return;
            }
            if (i % 2 == this.c.f % 2) {
                return;
            }
            F f = new F(i, this.c, false, z, okhttp3.g0.e.H(list));
            this.c.e = i;
            this.c.c.put(Integer.valueOf(i), f);
            y.y.execute(new u(this, "OkHttp %s stream %d", new Object[]{this.c.d, Integer.valueOf(i)}, f));
        }
    }

    @Override // okhttp3.internal.http2.A
    public void e() {
    }

    @Override // okhttp3.internal.http2.A
    public void f(boolean z, int i, okio.j jVar, int i2) throws IOException {
        if (this.c.f0(i)) {
            this.c.a0(i, jVar, i2, z);
            return;
        }
        F fV = this.c.V(i);
        if (fV == null) {
            this.c.p0(i, ErrorCode.PROTOCOL_ERROR);
            long j = i2;
            this.c.l0(j);
            jVar.m(j);
            return;
        }
        fV.o(jVar, i2);
        if (z) {
            fV.p();
        }
    }

    @Override // okhttp3.internal.http2.A
    public void g(int i, ErrorCode errorCode) {
        boolean zF0 = this.c.f0(i);
        y yVar = this.c;
        if (zF0) {
            yVar.e0(i, errorCode);
            return;
        }
        F fG0 = yVar.g0(i);
        if (fG0 != null) {
            fG0.r(errorCode);
        }
    }

    @Override // okhttp3.internal.http2.A
    public void h(boolean z, int i, int i2) {
        if (!z) {
            try {
                this.c.h.execute(new t(this.c, true, i, i2));
                return;
            } catch (RejectedExecutionException unused) {
                return;
            }
        }
        synchronized (this.c) {
            try {
                if (i == 1) {
                    y.p(this.c);
                } else if (i == 2) {
                    y.R(this.c);
                } else if (i == 3) {
                    y.S(this.c);
                    this.c.notifyAll();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // okhttp3.internal.http2.A
    public void i(int i, long j) {
        if (i == 0) {
            synchronized (this.c) {
                this.c.r += j;
                this.c.notifyAll();
            }
            return;
        }
        F fV = this.c.V(i);
        if (fV != null) {
            synchronized (fV) {
                fV.c(j);
            }
        }
    }

    @Override // okhttp3.internal.http2.A
    public void j(boolean z, L l) {
        try {
            this.c.h.execute(new v(this, "OkHttp %s ACK Settings", new Object[]{this.c.d}, z, l));
        } catch (RejectedExecutionException unused) {
        }
    }

    @Override // okhttp3.g0.b
    protected void k() throws Throwable {
        ErrorCode errorCode;
        y yVar;
        ErrorCode errorCode2 = ErrorCode.INTERNAL_ERROR;
        try {
            try {
                try {
                    this.b.p(this);
                    while (this.b.e(false, this)) {
                    }
                    errorCode = ErrorCode.NO_ERROR;
                    try {
                        errorCode2 = ErrorCode.CANCEL;
                        yVar = this.c;
                    } catch (IOException unused) {
                        errorCode = ErrorCode.PROTOCOL_ERROR;
                        errorCode2 = ErrorCode.PROTOCOL_ERROR;
                        yVar = this.c;
                    }
                } catch (IOException unused2) {
                }
            } catch (IOException unused3) {
            } catch (Throwable th) {
                th = th;
                errorCode = errorCode2;
                try {
                    this.c.T(errorCode, errorCode2);
                } catch (IOException unused4) {
                }
                okhttp3.g0.e.g(this.b);
                throw th;
            }
            yVar.T(errorCode, errorCode2);
            okhttp3.g0.e.g(this.b);
        } catch (Throwable th2) {
            th = th2;
        }
    }

    void l(boolean z, L l) {
        F[] fArr;
        long j;
        synchronized (this.c.v) {
            synchronized (this.c) {
                int iD = this.c.t.d();
                if (z) {
                    this.c.t.a();
                }
                this.c.t.h(l);
                int iD2 = this.c.t.d();
                fArr = null;
                if (iD2 == -1 || iD2 == iD) {
                    j = 0;
                } else {
                    j = iD2 - iD;
                    if (!this.c.c.isEmpty()) {
                        fArr = (F[]) this.c.c.values().toArray(new F[this.c.c.size()]);
                    }
                }
            }
            try {
                this.c.v.a(this.c.t);
            } catch (IOException unused) {
                this.c.U();
            }
        }
        if (fArr != null) {
            for (F f : fArr) {
                synchronized (f) {
                    f.c(j);
                }
            }
        }
        y.y.execute(new w(this, "OkHttp %s settings", this.c.d));
    }
}
