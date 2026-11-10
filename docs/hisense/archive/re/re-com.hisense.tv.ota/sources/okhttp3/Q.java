package okhttp3;

import java.io.IOException;
import java.net.Socket;
import javax.net.ssl.SSLSocket;

/* JADX INFO: loaded from: classes.dex */
class Q extends okhttp3.g0.a {
    Q() {
    }

    @Override // okhttp3.g0.a
    public void a(J j, String str) {
        j.b(str);
    }

    @Override // okhttp3.g0.a
    public void b(J j, String str, String str2) {
        j.c(str, str2);
    }

    @Override // okhttp3.g0.a
    public void c(C0063x c0063x, SSLSocket sSLSocket, boolean z) {
        c0063x.a(sSLSocket, z);
    }

    @Override // okhttp3.g0.a
    public int d(b0 b0Var) {
        return b0Var.c;
    }

    @Override // okhttp3.g0.a
    public boolean e(C0061v c0061v, okhttp3.internal.connection.c cVar) {
        return c0061v.b(cVar);
    }

    @Override // okhttp3.g0.a
    public Socket f(C0061v c0061v, C0029a c0029a, okhttp3.internal.connection.h hVar) {
        return c0061v.c(c0029a, hVar);
    }

    @Override // okhttp3.g0.a
    public boolean g(C0029a c0029a, C0029a c0029a2) {
        return c0029a.d(c0029a2);
    }

    @Override // okhttp3.g0.a
    public okhttp3.internal.connection.c h(C0061v c0061v, C0029a c0029a, okhttp3.internal.connection.h hVar, f0 f0Var) {
        return c0061v.d(c0029a, hVar, f0Var);
    }

    @Override // okhttp3.g0.a
    public void i(C0061v c0061v, okhttp3.internal.connection.c cVar) {
        c0061v.f(cVar);
    }

    @Override // okhttp3.g0.a
    public okhttp3.internal.connection.d j(C0061v c0061v) {
        return c0061v.e;
    }

    @Override // okhttp3.g0.a
    public IOException k(InterfaceC0053m interfaceC0053m, IOException iOException) {
        return ((W) interfaceC0053m).j(iOException);
    }
}
