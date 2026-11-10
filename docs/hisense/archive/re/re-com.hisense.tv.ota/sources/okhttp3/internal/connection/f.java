package okhttp3.internal.connection;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.SocketAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import okhttp3.C0029a;
import okhttp3.H;
import okhttp3.InterfaceC0053m;
import okhttp3.M;
import okhttp3.f0;

/* JADX INFO: loaded from: classes.dex */
public final class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final C0029a f223a;
    private final d b;
    private final InterfaceC0053m c;
    private final H d;
    private int f;
    private List e = Collections.emptyList();
    private List g = Collections.emptyList();
    private final List h = new ArrayList();

    public f(C0029a c0029a, d dVar, InterfaceC0053m interfaceC0053m, H h) {
        this.f223a = c0029a;
        this.b = dVar;
        this.c = interfaceC0053m;
        this.d = h;
        h(c0029a.l(), c0029a.g());
    }

    static String b(InetSocketAddress inetSocketAddress) {
        InetAddress address = inetSocketAddress.getAddress();
        return address == null ? inetSocketAddress.getHostName() : address.getHostAddress();
    }

    private boolean d() {
        return this.f < this.e.size();
    }

    private Proxy f() throws SocketException, UnknownHostException {
        if (d()) {
            List list = this.e;
            int i = this.f;
            this.f = i + 1;
            Proxy proxy = (Proxy) list.get(i);
            g(proxy);
            return proxy;
        }
        throw new SocketException("No route to " + this.f223a.l().l() + "; exhausted proxy configurations: " + this.e);
    }

    private void g(Proxy proxy) throws SocketException, UnknownHostException {
        String strL;
        int iW;
        this.g = new ArrayList();
        if (proxy.type() == Proxy.Type.DIRECT || proxy.type() == Proxy.Type.SOCKS) {
            strL = this.f223a.l().l();
            iW = this.f223a.l().w();
        } else {
            SocketAddress socketAddressAddress = proxy.address();
            if (!(socketAddressAddress instanceof InetSocketAddress)) {
                throw new IllegalArgumentException("Proxy.address() is not an InetSocketAddress: " + socketAddressAddress.getClass());
            }
            InetSocketAddress inetSocketAddress = (InetSocketAddress) socketAddressAddress;
            strL = b(inetSocketAddress);
            iW = inetSocketAddress.getPort();
        }
        if (iW < 1 || iW > 65535) {
            throw new SocketException("No route to " + strL + ":" + iW + "; port is out of range");
        }
        if (proxy.type() == Proxy.Type.SOCKS) {
            this.g.add(InetSocketAddress.createUnresolved(strL, iW));
            return;
        }
        this.d.j(this.c, strL);
        List listA = this.f223a.c().a(strL);
        if (listA.isEmpty()) {
            throw new UnknownHostException(this.f223a.c() + " returned no addresses for " + strL);
        }
        this.d.i(this.c, strL, listA);
        int size = listA.size();
        for (int i = 0; i < size; i++) {
            this.g.add(new InetSocketAddress((InetAddress) listA.get(i), iW));
        }
    }

    private void h(M m, Proxy proxy) {
        List listU;
        if (proxy != null) {
            listU = Collections.singletonList(proxy);
        } else {
            List<Proxy> listSelect = this.f223a.i().select(m.C());
            listU = (listSelect == null || listSelect.isEmpty()) ? okhttp3.g0.e.u(Proxy.NO_PROXY) : okhttp3.g0.e.t(listSelect);
        }
        this.e = listU;
        this.f = 0;
    }

    public void a(f0 f0Var, IOException iOException) {
        if (f0Var.b().type() != Proxy.Type.DIRECT && this.f223a.i() != null) {
            this.f223a.i().connectFailed(this.f223a.l().C(), f0Var.b().address(), iOException);
        }
        this.b.b(f0Var);
    }

    public boolean c() {
        return d() || !this.h.isEmpty();
    }

    public e e() throws SocketException, UnknownHostException {
        if (!c()) {
            throw new NoSuchElementException();
        }
        ArrayList arrayList = new ArrayList();
        while (d()) {
            Proxy proxyF = f();
            int size = this.g.size();
            for (int i = 0; i < size; i++) {
                f0 f0Var = new f0(this.f223a, proxyF, (InetSocketAddress) this.g.get(i));
                if (this.b.c(f0Var)) {
                    this.h.add(f0Var);
                } else {
                    arrayList.add(f0Var);
                }
            }
            if (!arrayList.isEmpty()) {
                break;
            }
        }
        if (arrayList.isEmpty()) {
            arrayList.addAll(this.h);
            this.h.clear();
        }
        return new e(arrayList);
    }
}
