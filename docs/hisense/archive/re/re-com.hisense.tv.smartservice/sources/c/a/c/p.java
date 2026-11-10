package c.a.c;

import c.a.Aa;
import c.a.C0086b;
import c.a.E;
import c.a.K;
import c.a.b.C0104db;
import c.a.pa;
import c.a.za;
import com.google.common.base.Preconditions;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.security.cert.Certificate;
import java.util.concurrent.CountDownLatch;
import java.util.logging.Level;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;

/* JADX INFO: loaded from: classes2.dex */
public class p implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ CountDownLatch f1716a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ d f1717b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ c.a.c.a.a.j f1718c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ r f1719d;

    public p(r rVar, CountDownLatch countDownLatch, d dVar, c.a.c.a.a.j jVar) {
        this.f1719d = rVar;
        this.f1716a = countDownLatch;
        this.f1717b = dVar;
        this.f1718c = jVar;
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    @Override // java.lang.Runnable
    public void run() {
        r rVar;
        r.a aVar;
        Socket socketA;
        try {
            this.f1716a.await();
        } catch (InterruptedException unused) {
            Thread.currentThread().interrupt();
        }
        d.i iVarA = d.t.a(new o(this));
        SSLSession session = null;
        try {
            try {
                if (this.f1719d.V == null) {
                    socketA = this.f1719d.D.createSocket(this.f1719d.f1724d.getAddress(), this.f1719d.f1724d.getPort());
                } else {
                    if (!(this.f1719d.V.f809a instanceof InetSocketAddress)) {
                        throw new Aa(za.k.b("Unsupported SocketAddress implementation " + this.f1719d.V.f809a.getClass()));
                    }
                    socketA = this.f1719d.a(this.f1719d.V.f810b, (InetSocketAddress) this.f1719d.V.f809a, this.f1719d.V.f811c, this.f1719d.V.f812d);
                }
                Socket socket = socketA;
                Socket socket2 = socket;
                if (this.f1719d.E != null) {
                    SSLSocket sSLSocketA = v.a(this.f1719d.E, this.f1719d.F, socket, this.f1719d.c(), this.f1719d.d(), this.f1719d.I);
                    session = sSLSocketA.getSession();
                    socket2 = sSLSocketA;
                }
                socket2.setTcpNoDelay(true);
                d.i iVarA2 = d.t.a(d.t.b(socket2));
                this.f1717b.a(d.t.a(socket2), socket2);
                r rVar2 = this.f1719d;
                C0086b.a aVarB = this.f1719d.x.b();
                aVarB.a(E.f792a, socket2.getRemoteSocketAddress());
                aVarB.a(E.f793b, socket2.getLocalSocketAddress());
                aVarB.a(E.f794c, session);
                aVarB.a(C0104db.f1241a, session == null ? pa.NONE : pa.PRIVACY_AND_INTEGRITY);
                rVar2.x = aVarB.a();
                r rVar3 = this.f1719d;
                rVar3.w = new r.a(rVar3, ((c.a.c.a.a.g) this.f1718c).a(iVarA2, true));
                synchronized (this.f1719d.o) {
                    r rVar4 = this.f1719d;
                    Preconditions.checkNotNull(socket2, "socket");
                    r.a(rVar4, socket2);
                    if (session != null) {
                        r rVar5 = this.f1719d;
                        session.getCipherSuite();
                        Certificate[] localCertificates = session.getLocalCertificates();
                        if (localCertificates != null) {
                            Certificate certificate = localCertificates[0];
                        }
                        try {
                            Certificate[] peerCertificates = session.getPeerCertificates();
                            if (peerCertificates != null) {
                                Certificate certificate2 = peerCertificates[0];
                            }
                        } catch (SSLPeerUnverifiedException e2) {
                            K.f827a.log(Level.FINE, String.format("Peer cert not available for peerHost=%s", session.getPeerHost()), (Throwable) e2);
                        }
                    }
                }
            } catch (Throwable th) {
                r rVar6 = this.f1719d;
                rVar6.w = new r.a(rVar6, ((c.a.c.a.a.g) this.f1718c).a(iVarA, true));
                throw th;
            }
        } catch (Aa e3) {
            this.f1719d.a(0, c.a.c.a.a.a.INTERNAL_ERROR, e3.f779a);
            rVar = this.f1719d;
            aVar = new r.a(rVar, ((c.a.c.a.a.g) this.f1718c).a(iVarA, true));
            rVar.w = aVar;
        } catch (Exception e4) {
            this.f1719d.a(e4);
            rVar = this.f1719d;
            aVar = new r.a(rVar, ((c.a.c.a.a.g) this.f1718c).a(iVarA, true));
            rVar.w = aVar;
        }
    }
}
