package okhttp3.internal.http2;

import java.net.ProtocolException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import okhttp3.N;
import okhttp3.Protocol;
import okhttp3.T;
import okhttp3.Y;
import okhttp3.b0;
import okhttp3.c0;
import okhttp3.e0;
import okio.ByteString;

/* JADX INFO: renamed from: okhttp3.internal.http2.h, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0045h implements okhttp3.g0.h.d {
    private static final List f = okhttp3.g0.e.u("connection", "host", "keep-alive", "proxy-connection", "te", "transfer-encoding", "encoding", "upgrade", ":method", ":path", ":scheme", ":authority");
    private static final List g = okhttp3.g0.e.u("connection", "host", "keep-alive", "proxy-connection", "te", "transfer-encoding", "encoding", "upgrade");

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final N f241a;
    final okhttp3.internal.connection.h b;
    private final y c;
    private F d;
    private final Protocol e;

    public C0045h(T t, N n, okhttp3.internal.connection.h hVar, y yVar) {
        this.f241a = n;
        this.b = hVar;
        this.c = yVar;
        this.e = t.x().contains(Protocol.H2_PRIOR_KNOWLEDGE) ? Protocol.H2_PRIOR_KNOWLEDGE : Protocol.HTTP_2;
    }

    public static List g(Y y) {
        okhttp3.K kE = y.e();
        ArrayList arrayList = new ArrayList(kE.g() + 4);
        arrayList.add(new C0039b(C0039b.f, y.g()));
        arrayList.add(new C0039b(C0039b.g, okhttp3.g0.h.k.c(y.k())));
        String strC = y.c("Host");
        if (strC != null) {
            arrayList.add(new C0039b(C0039b.i, strC));
        }
        arrayList.add(new C0039b(C0039b.h, y.k().B()));
        int iG = kE.g();
        for (int i = 0; i < iG; i++) {
            ByteString byteStringH = ByteString.h(kE.e(i).toLowerCase(Locale.US));
            if (!f.contains(byteStringH.w())) {
                arrayList.add(new C0039b(byteStringH, kE.h(i)));
            }
        }
        return arrayList;
    }

    public static b0 h(okhttp3.K k, Protocol protocol) throws ProtocolException {
        okhttp3.J j = new okhttp3.J();
        int iG = k.g();
        okhttp3.g0.h.m mVarA = null;
        for (int i = 0; i < iG; i++) {
            String strE = k.e(i);
            String strH = k.h(i);
            if (strE.equals(":status")) {
                mVarA = okhttp3.g0.h.m.a("HTTP/1.1 " + strH);
            } else if (!g.contains(strE)) {
                okhttp3.g0.a.f182a.b(j, strE, strH);
            }
        }
        if (mVarA == null) {
            throw new ProtocolException("Expected ':status' header not present");
        }
        b0 b0Var = new b0();
        b0Var.n(protocol);
        b0Var.g(mVarA.b);
        b0Var.k(mVarA.c);
        b0Var.j(j.d());
        return b0Var;
    }

    @Override // okhttp3.g0.h.d
    public b0 a(boolean z) throws ProtocolException {
        b0 b0VarH = h(this.d.s(), this.e);
        if (z && okhttp3.g0.a.f182a.d(b0VarH) == 100) {
            return null;
        }
        return b0VarH;
    }

    @Override // okhttp3.g0.h.d
    public void b() {
        this.d.j().close();
    }

    @Override // okhttp3.g0.h.d
    public void c(Y y) {
        if (this.d != null) {
            return;
        }
        F fZ = this.c.Z(g(y), y.a() != null);
        this.d = fZ;
        fZ.n().g(this.f241a.c(), TimeUnit.MILLISECONDS);
        this.d.u().g(this.f241a.b(), TimeUnit.MILLISECONDS);
    }

    @Override // okhttp3.g0.h.d
    public void cancel() {
        F f2 = this.d;
        if (f2 != null) {
            f2.h(ErrorCode.CANCEL);
        }
    }

    @Override // okhttp3.g0.h.d
    public e0 d(c0 c0Var) {
        okhttp3.internal.connection.h hVar = this.b;
        hVar.f.q(hVar.e);
        return new okhttp3.g0.h.j(c0Var.M("Content-Type"), okhttp3.g0.h.g.b(c0Var), okio.t.d(new C0044g(this, this.d.k())));
    }

    @Override // okhttp3.g0.h.d
    public void e() {
        this.c.flush();
    }

    @Override // okhttp3.g0.h.d
    public okio.A f(Y y, long j) {
        return this.d.j();
    }
}
