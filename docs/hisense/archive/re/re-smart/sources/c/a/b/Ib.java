package c.a.b;

import c.a.AbstractC0225i;
import c.a.AbstractC0230n;
import c.a.C0086b;
import c.a.C0219ea;
import c.a.C0220f;
import c.a.C0223ga;
import c.a.C0235t;
import c.a.EnumC0234s;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.base.Stopwatch;
import com.google.common.base.Supplier;
import java.net.SocketAddress;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.logging.Level;

/* JADX INFO: loaded from: classes2.dex */
public final class Ib implements c.a.N<Object>, Rd {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final c.a.O f1008a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f1009b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final String f1010c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final InterfaceC0196w.a f1011d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final b f1012e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final P f1013f;
    public final ScheduledExecutorService g;
    public final c.a.K h;
    public final A i;
    public final AbstractC0225i j;
    public final c.a.Ea k;
    public final c l;
    public volatile List<c.a.B> m;
    public InterfaceC0196w n;
    public final Stopwatch o;
    public c.a.Ea.b p;
    public c.a.Ea.b q;
    public Cc r;
    public X u;
    public volatile Cc v;
    public c.a.za x;
    public final Collection<X> s = new ArrayList();
    public final AbstractC0193vb<X> t = new C0202xb(this);
    public volatile C0235t w = C0235t.a(EnumC0234s.IDLE);

    @VisibleForTesting
    static final class a extends Ya {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final X f1014a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final A f1015b;

        public /* synthetic */ a(X x, A a2, C0202xb c0202xb) {
            this.f1014a = x;
            this.f1015b = a2;
        }

        @Override // c.a.b.O
        public M a(C0223ga<?, ?> c0223ga, C0219ea c0219ea, C0220f c0220f, AbstractC0230n[] abstractC0230nArr) {
            return new Hb(this, b().a(c0223ga, c0219ea, c0220f, abstractC0230nArr));
        }

        @Override // c.a.b.Ya
        public X b() {
            return this.f1014a;
        }
    }

    static abstract class b {
    }

    @VisibleForTesting
    static final class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public List<c.a.B> f1016a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f1017b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f1018c;

        public c(List<c.a.B> list) {
            this.f1016a = list;
        }

        public SocketAddress a() {
            return this.f1016a.get(this.f1017b).f783b.get(this.f1018c);
        }

        public void b() {
            this.f1017b = 0;
            this.f1018c = 0;
        }
    }

    public Ib(List<c.a.B> list, String str, String str2, InterfaceC0196w.a aVar, P p, ScheduledExecutorService scheduledExecutorService, Supplier<Stopwatch> supplier, c.a.Ea ea, b bVar, c.a.K k, A a2, D d2, c.a.O o, AbstractC0225i abstractC0225i) {
        Preconditions.checkNotNull(list, "addressGroups");
        Preconditions.checkArgument(!list.isEmpty(), "addressGroups is empty");
        Iterator<c.a.B> it = list.iterator();
        while (it.hasNext()) {
            Preconditions.checkNotNull(it.next(), "addressGroups contains null entry");
        }
        List<c.a.B> listUnmodifiableList = Collections.unmodifiableList(new ArrayList(list));
        this.m = listUnmodifiableList;
        this.l = new c(listUnmodifiableList);
        this.f1009b = str;
        this.f1010c = str2;
        this.f1011d = aVar;
        this.f1013f = p;
        this.g = scheduledExecutorService;
        this.o = supplier.get();
        this.k = ea;
        this.f1012e = bVar;
        this.h = k;
        this.i = a2;
        Preconditions.checkNotNull(d2, "channelTracer");
        Preconditions.checkNotNull(o, "logId");
        this.f1008a = o;
        Preconditions.checkNotNull(abstractC0225i, "channelLogger");
        this.j = abstractC0225i;
    }

    public static /* synthetic */ void a(Ib ib) {
        SocketAddress socketAddressA;
        c.a.H h;
        ib.k.b();
        Preconditions.checkState(ib.p == null, "Should have no reconnectTask scheduled");
        c cVar = ib.l;
        if (cVar.f1017b == 0 && cVar.f1018c == 0) {
            ib.o.reset().start();
        }
        SocketAddress socketAddressA2 = ib.l.a();
        C0202xb c0202xb = null;
        if (socketAddressA2 instanceof c.a.H) {
            h = (c.a.H) socketAddressA2;
            socketAddressA = h.a();
        } else {
            socketAddressA = socketAddressA2;
            h = null;
        }
        c cVar2 = ib.l;
        C0086b c0086bA = cVar2.f1016a.get(cVar2.f1017b).a();
        String str = (String) c0086bA.a(c.a.B.f782a);
        P.a aVar = new P.a();
        if (str == null) {
            str = ib.f1009b;
        }
        Preconditions.checkNotNull(str, "authority");
        aVar.f1098a = str;
        Preconditions.checkNotNull(c0086bA, "eagAttributes");
        aVar.f1099b = c0086bA;
        aVar.f1100c = ib.f1010c;
        aVar.f1101d = h;
        e eVar = new e();
        eVar.f1022a = ib.f1008a;
        a aVar2 = new a(ib.f1013f.a(socketAddressA, aVar, eVar), ib.i, c0202xb);
        eVar.f1022a = aVar2.a();
        c.a.K.a(ib.h.f831e, aVar2);
        ib.u = aVar2;
        ib.s.add(aVar2);
        Runnable runnableA = aVar2.b().a(ib.new d(aVar2, socketAddressA));
        if (runnableA != null) {
            Queue<Runnable> queue = ib.k.f796b;
            Preconditions.checkNotNull(runnableA, "runnable is null");
            queue.add(runnableA);
        }
        ib.j.a(AbstractC0225i.a.INFO, "Started transport {0}", eVar.f1022a);
    }

    public O b() {
        Cc cc = this.v;
        if (cc != null) {
            return cc;
        }
        c.a.Ea ea = this.k;
        RunnableC0210zb runnableC0210zb = new RunnableC0210zb(this);
        b.a.a.a.a.a(runnableC0210zb, "runnable is null", ea.f796b, runnableC0210zb, ea);
        return null;
    }

    public final String c(c.a.za zaVar) {
        StringBuilder sb = new StringBuilder();
        sb.append(zaVar.p);
        if (zaVar.q != null) {
            sb.append("(");
            sb.append(zaVar.q);
            sb.append(")");
        }
        return sb.toString();
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("logId", this.f1008a.f839d).add("addressGroups", this.m).toString();
    }

    @VisibleForTesting
    static final class e extends AbstractC0225i {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public c.a.O f1022a;

        @Override // c.a.AbstractC0225i
        public void a(AbstractC0225i.a aVar, String str) {
            c.a.O o = this.f1022a;
            Level levelA = B.a(aVar);
            if (D.f927a.isLoggable(levelA)) {
                D.a(o, levelA, str);
            }
        }

        @Override // c.a.AbstractC0225i
        public void a(AbstractC0225i.a aVar, String str, Object... objArr) {
            c.a.O o = this.f1022a;
            Level levelA = B.a(aVar);
            if (D.f927a.isLoggable(levelA)) {
                D.a(o, levelA, MessageFormat.format(str, objArr));
            }
        }
    }

    public void b(c.a.za zaVar) {
        c.a.Ea ea = this.k;
        Cb cb = new Cb(this, zaVar);
        b.a.a.a.a.a(cb, "runnable is null", ea.f796b, cb, ea);
    }

    /* JADX INFO: Access modifiers changed from: private */
    class d implements Cc.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final X f1019a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public boolean f1020b = false;

        public d(X x, SocketAddress socketAddress) {
            this.f1019a = x;
        }

        @Override // c.a.b.Cc.a
        public void a() {
            Ib.this.j.a(AbstractC0225i.a.INFO, "READY");
            c.a.Ea ea = Ib.this.k;
            Jb jb = new Jb(this);
            b.a.a.a.a.a(jb, "runnable is null", ea.f796b, jb, ea);
        }

        @Override // c.a.b.Cc.a
        public void b() {
            Preconditions.checkState(this.f1020b, "transportShutdown() must be called before transportTerminated().");
            Ib.this.j.a(AbstractC0225i.a.INFO, "{0} Terminated", this.f1019a.a());
            c.a.K.b(Ib.this.h.f831e, this.f1019a);
            Ib ib = Ib.this;
            X x = this.f1019a;
            c.a.Ea ea = ib.k;
            Eb eb = new Eb(ib, x, false);
            b.a.a.a.a.a(eb, "runnable is null", ea.f796b, eb, ea);
            c.a.Ea ea2 = Ib.this.k;
            Lb lb = new Lb(this);
            b.a.a.a.a.a(lb, "runnable is null", ea2.f796b, lb, ea2);
        }

        @Override // c.a.b.Cc.a
        public void a(boolean z) {
            Ib ib = Ib.this;
            X x = this.f1019a;
            c.a.Ea ea = ib.k;
            Eb eb = new Eb(ib, x, z);
            Queue<Runnable> queue = ea.f796b;
            Preconditions.checkNotNull(eb, "runnable is null");
            queue.add(eb);
            ea.a();
        }

        @Override // c.a.b.Cc.a
        public void a(c.a.za zaVar) {
            Ib.this.j.a(AbstractC0225i.a.INFO, "{0} SHUTDOWN with {1}", this.f1019a.a(), Ib.this.c(zaVar));
            this.f1020b = true;
            c.a.Ea ea = Ib.this.k;
            Kb kb = new Kb(this, zaVar);
            b.a.a.a.a.a(kb, "runnable is null", ea.f796b, kb, ea);
        }
    }

    public static /* synthetic */ void a(Ib ib, EnumC0234s enumC0234s) {
        ib.k.b();
        ib.a(C0235t.a(enumC0234s));
    }

    public final void a(C0235t c0235t) {
        this.k.b();
        if (this.w.f1906a != c0235t.f1906a) {
            Preconditions.checkState(this.w.f1906a != EnumC0234s.SHUTDOWN, "Cannot transition out of SHUTDOWN to " + c0235t);
            this.w = c0235t;
            C0194vc c0194vc = (C0194vc) this.f1012e;
            Preconditions.checkState(c0194vc.f1494a != null, "listener is null");
            c0194vc.f1494a.a(c0235t);
            EnumC0234s enumC0234s = c0235t.f1906a;
            if (enumC0234s == EnumC0234s.TRANSIENT_FAILURE || enumC0234s == EnumC0234s.IDLE) {
                C0130ic.h hVar = c0194vc.f1495b.f1308b;
                if (hVar.f1292c || hVar.f1291b) {
                    return;
                }
                C0130ic.f1271a.log(Level.WARNING, "LoadBalancer should call Helper.refreshNameResolution() to refresh name resolution if subchannel state becomes TRANSIENT_FAILURE or IDLE. This will no longer happen automatically in the future releases");
                C0130ic.D(C0130ic.this);
                c0194vc.f1495b.f1308b.f1291b = true;
            }
        }
    }

    public void a(List<c.a.B> list) {
        Preconditions.checkNotNull(list, "newAddressGroups");
        Iterator<c.a.B> it = list.iterator();
        while (it.hasNext()) {
            Preconditions.checkNotNull(it.next(), "newAddressGroups contains null entry");
        }
        Preconditions.checkArgument(!list.isEmpty(), "newAddressGroups is empty");
        List listUnmodifiableList = Collections.unmodifiableList(new ArrayList(list));
        c.a.Ea ea = this.k;
        Bb bb = new Bb(this, listUnmodifiableList);
        b.a.a.a.a.a(bb, "runnable is null", ea.f796b, bb, ea);
    }

    public void a(c.a.za zaVar) {
        b(zaVar);
        c.a.Ea ea = this.k;
        Fb fb = new Fb(this, zaVar);
        b.a.a.a.a.a(fb, "runnable is null", ea.f796b, fb, ea);
    }

    @Override // c.a.N
    public c.a.O a() {
        return this.f1008a;
    }
}
