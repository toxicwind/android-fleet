package okhttp3.g0;

import java.io.IOException;
import java.net.Socket;
import javax.net.ssl.SSLSocket;
import okhttp3.C0029a;
import okhttp3.C0061v;
import okhttp3.C0063x;
import okhttp3.InterfaceC0053m;
import okhttp3.J;
import okhttp3.b0;
import okhttp3.f0;
import okhttp3.internal.connection.h;

/* JADX INFO: loaded from: classes.dex */
public abstract class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static a f182a;

    public abstract void a(J j, String str);

    public abstract void b(J j, String str, String str2);

    public abstract void c(C0063x c0063x, SSLSocket sSLSocket, boolean z);

    public abstract int d(b0 b0Var);

    public abstract boolean e(C0061v c0061v, okhttp3.internal.connection.c cVar);

    public abstract Socket f(C0061v c0061v, C0029a c0029a, h hVar);

    public abstract boolean g(C0029a c0029a, C0029a c0029a2);

    public abstract okhttp3.internal.connection.c h(C0061v c0061v, C0029a c0029a, h hVar, f0 f0Var);

    public abstract void i(C0061v c0061v, okhttp3.internal.connection.c cVar);

    public abstract okhttp3.internal.connection.d j(C0061v c0061v);

    public abstract IOException k(InterfaceC0053m interfaceC0053m, IOException iOException);
}
