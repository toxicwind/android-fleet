package okhttp3;

import java.io.Closeable;
import java.io.File;
import java.io.Flushable;
import java.io.IOException;
import okio.ByteString;

/* JADX INFO: renamed from: okhttp3.j, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0050j implements Closeable, Flushable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final okhttp3.g0.g.n f246a;
    final okhttp3.g0.g.l b;
    int c;
    int d;
    private int e;
    private int f;
    private int g;

    public C0050j(File file, long j) {
        this(file, j, okhttp3.g0.j.b.f206a);
    }

    C0050j(File file, long j, okhttp3.g0.j.b bVar) {
        this.f246a = new C0032d(this);
        this.b = okhttp3.g0.g.l.p(bVar, file, 201105, 2, j);
    }

    static int M(okio.j jVar) {
        try {
            long jI = jVar.I();
            String strE = jVar.E();
            if (jI >= 0 && jI <= 2147483647L && strE.isEmpty()) {
                return (int) jI;
            }
            throw new IOException("expected an int but was \"" + jI + strE + "\"");
        } catch (NumberFormatException e) {
            throw new IOException(e.getMessage());
        }
    }

    private void a(okhttp3.g0.g.i iVar) {
        if (iVar != null) {
            try {
                iVar.a();
            } catch (IOException unused) {
            }
        }
    }

    public static String p(M m) {
        return ByteString.h(m.toString()).k().j();
    }

    okhttp3.g0.g.c B(c0 c0Var) {
        okhttp3.g0.g.i iVarM;
        String strG = c0Var.W().g();
        if (okhttp3.g0.h.h.a(c0Var.W().g())) {
            try {
                N(c0Var.W());
            } catch (IOException unused) {
            }
            return null;
        }
        if (!strG.equals("GET") || okhttp3.g0.h.g.e(c0Var)) {
            return null;
        }
        C0037i c0037i = new C0037i(c0Var);
        try {
            iVarM = this.b.M(p(c0Var.W().k()));
            if (iVarM == null) {
                return null;
            }
            try {
                c0037i.f(iVarM);
                return new C0034f(this, iVarM);
            } catch (IOException unused2) {
                a(iVarM);
                return null;
            }
        } catch (IOException unused3) {
            iVarM = null;
        }
    }

    void N(Y y) {
        this.b.X(p(y.k()));
    }

    synchronized void O() {
        this.f++;
    }

    synchronized void P(okhttp3.g0.g.e eVar) {
        this.g++;
        if (eVar.f189a != null) {
            this.e++;
        } else if (eVar.b != null) {
            this.f++;
        }
    }

    void Q(c0 c0Var, c0 c0Var2) {
        okhttp3.g0.g.i iVarA;
        C0037i c0037i = new C0037i(c0Var2);
        try {
            iVarA = ((C0036h) c0Var.a()).f217a.a();
            if (iVarA != null) {
                try {
                    c0037i.f(iVarA);
                    iVarA.b();
                } catch (IOException unused) {
                    a(iVarA);
                }
            }
        } catch (IOException unused2) {
            iVarA = null;
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.b.close();
    }

    c0 e(Y y) {
        try {
            okhttp3.g0.g.k kVarO = this.b.O(p(y.k()));
            if (kVarO == null) {
                return null;
            }
            try {
                C0037i c0037i = new C0037i(kVarO.e(0));
                c0 c0VarD = c0037i.d(kVarO);
                if (c0037i.b(y, c0VarD)) {
                    return c0VarD;
                }
                okhttp3.g0.e.g(c0VarD.a());
                return null;
            } catch (IOException unused) {
                okhttp3.g0.e.g(kVarO);
                return null;
            }
        } catch (IOException unused2) {
        }
    }

    @Override // java.io.Flushable
    public void flush() {
        this.b.flush();
    }
}
