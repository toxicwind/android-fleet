package okhttp3.g0.h;

import java.util.List;
import okhttp3.H;
import okhttp3.InterfaceC0053m;
import okhttp3.InterfaceC0059t;
import okhttp3.N;
import okhttp3.O;
import okhttp3.Y;
import okhttp3.c0;

/* JADX INFO: loaded from: classes.dex */
public final class i implements N {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final List f198a;
    private final okhttp3.internal.connection.h b;
    private final d c;
    private final okhttp3.internal.connection.c d;
    private final int e;
    private final Y f;
    private final InterfaceC0053m g;
    private final H h;
    private final int i;
    private final int j;
    private final int k;
    private int l;

    public i(List list, okhttp3.internal.connection.h hVar, d dVar, okhttp3.internal.connection.c cVar, int i, Y y, InterfaceC0053m interfaceC0053m, H h, int i2, int i3, int i4) {
        this.f198a = list;
        this.d = cVar;
        this.b = hVar;
        this.c = dVar;
        this.e = i;
        this.f = y;
        this.g = interfaceC0053m;
        this.h = h;
        this.i = i2;
        this.j = i3;
        this.k = i4;
    }

    @Override // okhttp3.N
    public Y a() {
        return this.f;
    }

    @Override // okhttp3.N
    public int b() {
        return this.k;
    }

    @Override // okhttp3.N
    public int c() {
        return this.j;
    }

    @Override // okhttp3.N
    public int d() {
        return this.i;
    }

    @Override // okhttp3.N
    public InterfaceC0059t e() {
        return this.d;
    }

    @Override // okhttp3.N
    public c0 f(Y y) {
        return j(y, this.b, this.c, this.d);
    }

    public InterfaceC0053m g() {
        return this.g;
    }

    public H h() {
        return this.h;
    }

    public d i() {
        return this.c;
    }

    public c0 j(Y y, okhttp3.internal.connection.h hVar, d dVar, okhttp3.internal.connection.c cVar) {
        if (this.e >= this.f198a.size()) {
            throw new AssertionError();
        }
        this.l++;
        if (this.c != null && !this.d.t(y.k())) {
            throw new IllegalStateException("network interceptor " + this.f198a.get(this.e - 1) + " must retain the same host and port");
        }
        if (this.c != null && this.l > 1) {
            throw new IllegalStateException("network interceptor " + this.f198a.get(this.e - 1) + " must call proceed() exactly once");
        }
        i iVar = new i(this.f198a, hVar, dVar, cVar, this.e + 1, y, this.g, this.h, this.i, this.j, this.k);
        O o = (O) this.f198a.get(this.e);
        c0 c0VarA = o.a(iVar);
        if (dVar != null && this.e + 1 < this.f198a.size() && iVar.l != 1) {
            throw new IllegalStateException("network interceptor " + o + " must call proceed() exactly once");
        }
        if (c0VarA == null) {
            throw new NullPointerException("interceptor " + o + " returned null");
        }
        if (c0VarA.a() != null) {
            return c0VarA;
        }
        throw new IllegalStateException("interceptor " + o + " returned a response with no body");
    }

    public okhttp3.internal.connection.h k() {
        return this.b;
    }
}
