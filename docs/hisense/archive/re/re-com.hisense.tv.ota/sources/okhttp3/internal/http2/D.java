package okhttp3.internal.http2;

import java.io.EOFException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
final class D implements okio.B {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final okio.h f228a = new okio.h();
    private final okio.h b = new okio.h();
    private final long c;
    boolean d;
    boolean e;
    final /* synthetic */ F f;

    D(F f, long j) {
        this.f = f;
        this.c = j;
    }

    private void e(long j) {
        this.f.d.l0(j);
    }

    @Override // okio.B
    public long K(okio.h hVar, long j) throws StreamResetException {
        long jK;
        okhttp3.K k;
        InterfaceC0038a interfaceC0038a;
        if (j < 0) {
            throw new IllegalArgumentException("byteCount < 0: " + j);
        }
        while (true) {
            synchronized (this.f) {
                this.f.j.k();
                try {
                    ErrorCode errorCode = this.f.l != null ? this.f.l : null;
                    if (this.d) {
                        throw new IOException("stream closed");
                    }
                    if (this.f.e.isEmpty() || this.f.f == null) {
                        if (this.b.c0() > 0) {
                            jK = this.b.K(hVar, Math.min(j, this.b.c0()));
                            this.f.f230a += jK;
                            if (errorCode == null && this.f.f230a >= this.f.d.s.d() / 2) {
                                this.f.d.q0(this.f.c, this.f.f230a);
                                this.f.f230a = 0L;
                            }
                        } else if (this.e || errorCode != null) {
                            jK = -1;
                        } else {
                            this.f.t();
                            this.f.j.u();
                        }
                        k = null;
                        interfaceC0038a = null;
                    } else {
                        k = (okhttp3.K) this.f.e.removeFirst();
                        interfaceC0038a = this.f.f;
                        jK = -1;
                    }
                    this.f.j.u();
                    if (k == null || interfaceC0038a == null) {
                        if (jK != -1) {
                            e(jK);
                            return jK;
                        }
                        if (errorCode == null) {
                            return -1L;
                        }
                        throw new StreamResetException(errorCode);
                    }
                    interfaceC0038a.a(k);
                } catch (Throwable th) {
                    this.f.j.u();
                    throw th;
                }
                throw th;
            }
        }
    }

    void a(okio.j jVar, long j) {
        boolean z;
        boolean z2;
        boolean z3;
        long jC0;
        while (j > 0) {
            synchronized (this.f) {
                z = this.e;
                z2 = true;
                z3 = this.b.c0() + j > this.c;
            }
            if (z3) {
                jVar.m(j);
                this.f.h(ErrorCode.FLOW_CONTROL_ERROR);
                return;
            }
            if (z) {
                jVar.m(j);
                return;
            }
            long jK = jVar.K(this.f228a, j);
            if (jK == -1) {
                throw new EOFException();
            }
            j -= jK;
            synchronized (this.f) {
                if (this.d) {
                    jC0 = this.f228a.c0();
                    this.f228a.M();
                } else {
                    if (this.b.c0() != 0) {
                        z2 = false;
                    }
                    this.b.j0(this.f228a);
                    if (z2) {
                        this.f.notifyAll();
                    }
                    jC0 = 0;
                }
            }
            if (jC0 > 0) {
                e(jC0);
            }
        }
    }

    @Override // okio.B
    public okio.D b() {
        return this.f.j;
    }

    @Override // okio.B, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        long jC0;
        InterfaceC0038a interfaceC0038a;
        ArrayList arrayList;
        synchronized (this.f) {
            this.d = true;
            jC0 = this.b.c0();
            this.b.M();
            interfaceC0038a = null;
            if (this.f.e.isEmpty() || this.f.f == null) {
                arrayList = null;
            } else {
                ArrayList arrayList2 = new ArrayList(this.f.e);
                this.f.e.clear();
                interfaceC0038a = this.f.f;
                arrayList = arrayList2;
            }
            this.f.notifyAll();
        }
        if (jC0 > 0) {
            e(jC0);
        }
        this.f.d();
        if (interfaceC0038a != null) {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                interfaceC0038a.a((okhttp3.K) it.next());
            }
        }
    }
}
