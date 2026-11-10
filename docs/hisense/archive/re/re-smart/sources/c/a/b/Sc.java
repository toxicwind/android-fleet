package c.a.b;

import com.google.common.base.Supplier;
import com.google.firebase.installations.Utils;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.PasswordAuthentication;
import java.net.Proxy;
import java.net.ProxySelector;
import java.net.SocketAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/* JADX INFO: loaded from: classes2.dex */
public class Sc implements c.a.oa {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Logger f1147a = Logger.getLogger(Sc.class.getName());

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final a f1148b = new Qc();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final Supplier<ProxySelector> f1149c = new Rc();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Supplier<ProxySelector> f1150d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final a f1151e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final InetSocketAddress f1152f;

    interface a {
    }

    public Sc() {
        Supplier<ProxySelector> supplier = f1149c;
        a aVar = f1148b;
        String str = System.getenv("GRPC_PROXY_EXP");
        if (supplier == null) {
            throw new NullPointerException();
        }
        this.f1150d = supplier;
        if (aVar == null) {
            throw new NullPointerException();
        }
        this.f1151e = aVar;
        if (str == null) {
            this.f1152f = null;
            return;
        }
        String[] strArrSplit = str.split(Utils.APP_ID_IDENTIFICATION_SUBSTRING, 2);
        int i = strArrSplit.length > 1 ? Integer.parseInt(strArrSplit[1]) : 80;
        f1147a.warning("Detected GRPC_PROXY_EXP and will honor it, but this feature will be removed in a future release. Use the JVM flags \"-Dhttps.proxyHost=HOST -Dhttps.proxyPort=PORT\" to set the https proxy for this JVM.");
        this.f1152f = new InetSocketAddress(strArrSplit[0], i);
    }

    @Override // c.a.oa
    public c.a.na a(SocketAddress socketAddress) {
        if (!(socketAddress instanceof InetSocketAddress)) {
            return null;
        }
        if (this.f1152f != null) {
            c.a.H.a aVarB = c.a.H.b();
            aVarB.a((SocketAddress) this.f1152f);
            aVarB.a((InetSocketAddress) socketAddress);
            return aVarB.a();
        }
        InetSocketAddress inetSocketAddress = (InetSocketAddress) socketAddress;
        try {
            try {
                URI uri = new URI("https", null, C0149mb.a(inetSocketAddress), inetSocketAddress.getPort(), null, null, null);
                ProxySelector proxySelector = this.f1150d.get();
                if (proxySelector == null) {
                    f1147a.log(Level.FINE, "proxy selector is null, so continuing without proxy lookup");
                    return null;
                }
                List<Proxy> listSelect = proxySelector.select(uri);
                if (listSelect.size() > 1) {
                    f1147a.warning("More than 1 proxy detected, gRPC will select the first one");
                }
                Proxy proxy = listSelect.get(0);
                if (proxy.type() == Proxy.Type.DIRECT) {
                    return null;
                }
                InetSocketAddress inetSocketAddress2 = (InetSocketAddress) proxy.address();
                PasswordAuthentication passwordAuthenticationA = ((Qc) this.f1151e).a(C0149mb.a(inetSocketAddress2), inetSocketAddress2.getAddress(), inetSocketAddress2.getPort(), "https", "", null);
                if (inetSocketAddress2.isUnresolved()) {
                    inetSocketAddress2 = new InetSocketAddress(InetAddress.getByName(inetSocketAddress2.getHostName()), inetSocketAddress2.getPort());
                }
                c.a.H.a aVarB2 = c.a.H.b();
                aVarB2.a(inetSocketAddress);
                aVarB2.a((SocketAddress) inetSocketAddress2);
                if (passwordAuthenticationA == null) {
                    return aVarB2.a();
                }
                aVarB2.f815c = passwordAuthenticationA.getUserName();
                aVarB2.f816d = passwordAuthenticationA.getPassword() != null ? new String(passwordAuthenticationA.getPassword()) : null;
                return aVarB2.a();
            } catch (URISyntaxException e2) {
                f1147a.log(Level.WARNING, "Failed to construct URI for proxy lookup, proceeding without proxy", (Throwable) e2);
                return null;
            }
        } catch (Throwable th) {
            f1147a.log(Level.WARNING, "Failed to get host for proxy lookup, proceeding without proxy", th);
            return null;
        }
    }
}
