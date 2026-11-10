package okhttp3;

import java.io.Closeable;

/* JADX INFO: loaded from: classes.dex */
public final class c0 implements Closeable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final Y f177a;
    final Protocol b;
    final int c;
    final String d;
    final I e;
    final K f;
    final e0 g;
    final c0 h;
    final c0 i;
    final c0 j;
    final long k;
    final long l;
    private volatile C0052l m;

    c0(b0 b0Var) {
        this.f177a = b0Var.f175a;
        this.b = b0Var.b;
        this.c = b0Var.c;
        this.d = b0Var.d;
        this.e = b0Var.e;
        this.f = b0Var.f.d();
        this.g = b0Var.g;
        this.h = b0Var.h;
        this.i = b0Var.i;
        this.j = b0Var.j;
        this.k = b0Var.k;
        this.l = b0Var.l;
    }

    public I B() {
        return this.e;
    }

    public String M(String str) {
        return N(str, null);
    }

    public String N(String str, String str2) {
        String strC = this.f.c(str);
        return strC != null ? strC : str2;
    }

    public K O() {
        return this.f;
    }

    public boolean P() {
        int i = this.c;
        return i >= 200 && i < 300;
    }

    public String Q() {
        return this.d;
    }

    public c0 R() {
        return this.h;
    }

    public b0 S() {
        return new b0(this);
    }

    public c0 T() {
        return this.j;
    }

    public Protocol U() {
        return this.b;
    }

    public long V() {
        return this.l;
    }

    public Y W() {
        return this.f177a;
    }

    public long X() {
        return this.k;
    }

    public e0 a() {
        return this.g;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        e0 e0Var = this.g;
        if (e0Var == null) {
            throw new IllegalStateException("response is not eligible for a body and must not be closed");
        }
        e0Var.close();
    }

    public C0052l e() {
        C0052l c0052l = this.m;
        if (c0052l != null) {
            return c0052l;
        }
        C0052l c0052lK = C0052l.k(this.f);
        this.m = c0052lK;
        return c0052lK;
    }

    public int p() {
        return this.c;
    }

    public String toString() {
        return "Response{protocol=" + this.b + ", code=" + this.c + ", message=" + this.d + ", url=" + this.f177a.k() + '}';
    }
}
