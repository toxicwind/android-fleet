package c.a.b;

import androidx.core.app.NotificationCompat;
import c.a.AbstractC0226j;
import c.a.C0086b;
import c.a.C0219ea;
import c.a.C0236u;
import c.a.C0238w;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.primitives.UnsignedLong;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/* JADX INFO: renamed from: c.a.b.ka, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public class C0138ka<ReqT, RespT> extends AbstractC0226j<ReqT, RespT> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Logger f1325a = Logger.getLogger(C0138ka.class.getName());

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final AbstractC0226j<Object, Object> f1326b = new C0133ja();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final ScheduledFuture<?> f1327c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Executor f1328d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final C0236u f1329e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public volatile boolean f1330f;
    public AbstractC0226j.a<RespT> g;
    public AbstractC0226j<ReqT, RespT> h;
    public c.a.za i;
    public List<Runnable> j = new ArrayList();
    public b<RespT> k;

    /* JADX INFO: renamed from: c.a.b.ka$a */
    private final class a extends Z {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final AbstractC0226j.a<RespT> f1331b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final c.a.za f1332c;

        public a(C0138ka c0138ka, AbstractC0226j.a<RespT> aVar, c.a.za zaVar) {
            super(c0138ka.f1329e);
            this.f1331b = aVar;
            this.f1332c = zaVar;
        }

        @Override // c.a.b.Z
        public void a() {
            this.f1331b.onClose(this.f1332c, new C0219ea());
        }
    }

    public C0138ka(Executor executor, ScheduledExecutorService scheduledExecutorService, C0238w c0238w) {
        ScheduledFuture<?> scheduledFutureSchedule;
        Preconditions.checkNotNull(executor, "callExecutor");
        this.f1328d = executor;
        Preconditions.checkNotNull(scheduledExecutorService, "scheduler");
        this.f1329e = C0236u.c();
        C0238w c0238wD = this.f1329e.d();
        if (c0238w == null && c0238wD == null) {
            scheduledFutureSchedule = null;
        } else {
            long jMin = c0238w != null ? Math.min(UnsignedLong.UNSIGNED_MASK, c0238w.a(TimeUnit.NANOSECONDS)) : UnsignedLong.UNSIGNED_MASK;
            if (c0238wD != null && c0238wD.a(TimeUnit.NANOSECONDS) < jMin) {
                jMin = c0238wD.a(TimeUnit.NANOSECONDS);
                if (f1325a.isLoggable(Level.FINE)) {
                    StringBuilder sb = new StringBuilder(String.format("Call timeout set to '%d' ns, due to context deadline.", Long.valueOf(jMin)));
                    if (c0238w == null) {
                        sb.append(" Explicit call timeout was not set.");
                    } else {
                        sb.append(String.format(" Explicit call timeout was '%d' ns.", Long.valueOf(c0238w.a(TimeUnit.NANOSECONDS))));
                    }
                    f1325a.fine(sb.toString());
                }
            }
            long jAbs = Math.abs(jMin) / TimeUnit.SECONDS.toNanos(1L);
            long jAbs2 = Math.abs(jMin) % TimeUnit.SECONDS.toNanos(1L);
            StringBuilder sb2 = new StringBuilder();
            if (jMin < 0) {
                sb2.append("ClientCall started after deadline exceeded. Deadline exceeded after -");
            } else {
                sb2.append("Deadline exceeded after ");
            }
            sb2.append(jAbs);
            sb2.append(String.format(Locale.US, ".%09d", Long.valueOf(jAbs2)));
            sb2.append("s. ");
            scheduledFutureSchedule = scheduledExecutorService.schedule(new RunnableC0098ca(this, sb2), jMin, TimeUnit.NANOSECONDS);
        }
        this.f1327c = scheduledFutureSchedule;
    }

    public void a() {
    }

    @Override // c.a.AbstractC0226j
    public final void cancel(String str, Throwable th) {
        c.a.za zaVar = c.a.za.f1937d;
        c.a.za zaVarB = str != null ? zaVar.b(str) : zaVar.b("Call cancelled without message");
        if (th != null) {
            zaVarB = zaVarB.b(th);
        }
        a(zaVarB, false);
    }

    @Override // c.a.AbstractC0226j
    public final C0086b getAttributes() {
        AbstractC0226j<ReqT, RespT> abstractC0226j;
        synchronized (this) {
            abstractC0226j = this.h;
        }
        return abstractC0226j != null ? abstractC0226j.getAttributes() : C0086b.f876a;
    }

    @Override // c.a.AbstractC0226j
    public final void halfClose() {
        a(new RunnableC0128ia(this));
    }

    @Override // c.a.AbstractC0226j
    public final boolean isReady() {
        if (this.f1330f) {
            return this.h.isReady();
        }
        return false;
    }

    @Override // c.a.AbstractC0226j
    public final void request(int i) {
        if (this.f1330f) {
            this.h.request(i);
        } else {
            a(new RunnableC0123ha(this, i));
        }
    }

    @Override // c.a.AbstractC0226j
    public final void sendMessage(ReqT reqt) {
        if (this.f1330f) {
            this.h.sendMessage(reqt);
        } else {
            a(new RunnableC0113fa(this, reqt));
        }
    }

    @Override // c.a.AbstractC0226j
    public final void setMessageCompression(boolean z) {
        if (this.f1330f) {
            this.h.setMessageCompression(z);
        } else {
            a(new RunnableC0118ga(this, z));
        }
    }

    @Override // c.a.AbstractC0226j
    public final void start(AbstractC0226j.a<RespT> aVar, C0219ea c0219ea) {
        c.a.za zaVar;
        boolean z;
        Preconditions.checkState(this.g == null, "already started");
        synchronized (this) {
            Preconditions.checkNotNull(aVar, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
            this.g = aVar;
            zaVar = this.i;
            z = this.f1330f;
            if (!z) {
                b<RespT> bVar = new b<>(aVar);
                this.k = bVar;
                aVar = bVar;
            }
        }
        if (zaVar != null) {
            this.f1328d.execute(new a(this, aVar, zaVar));
        } else if (z) {
            this.h.start(aVar, c0219ea);
        } else {
            a(new RunnableC0093ba(this, aVar, c0219ea));
        }
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("realCall", this.h).toString();
    }

    /* JADX WARN: Code duplicated, block: B:25:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:9:0x0019  */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x0029, code lost:
    
        r0 = r1.iterator();
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0031, code lost:
    
        if (r0.hasNext() == false) goto L24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0033, code lost:
    
        r0.next().run();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void b() {
        /*
            r3 = this;
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
        L5:
            monitor-enter(r3)
            java.util.List<java.lang.Runnable> r1 = r3.j     // Catch: java.lang.Throwable -> L42
            boolean r1 = r1.isEmpty()     // Catch: java.lang.Throwable -> L42
            if (r1 == 0) goto L24
            r0 = 0
            r3.j = r0     // Catch: java.lang.Throwable -> L42
            r0 = 1
            r3.f1330f = r0     // Catch: java.lang.Throwable -> L42
            c.a.b.ka$b<RespT> r0 = r3.k     // Catch: java.lang.Throwable -> L42
            monitor-exit(r3)     // Catch: java.lang.Throwable -> L42
            if (r0 == 0) goto L23
            java.util.concurrent.Executor r1 = r3.f1328d
            c.a.b.da r2 = new c.a.b.da
            r2.<init>(r3, r0)
            r1.execute(r2)
        L23:
            return
        L24:
            java.util.List<java.lang.Runnable> r1 = r3.j     // Catch: java.lang.Throwable -> L42
            r3.j = r0     // Catch: java.lang.Throwable -> L42
            monitor-exit(r3)     // Catch: java.lang.Throwable -> L42
            java.util.Iterator r0 = r1.iterator()
        L2d:
            boolean r2 = r0.hasNext()
            if (r2 == 0) goto L3d
            java.lang.Object r2 = r0.next()
            java.lang.Runnable r2 = (java.lang.Runnable) r2
            r2.run()
            goto L2d
        L3d:
            r1.clear()
            r0 = r1
            goto L5
        L42:
            r0 = move-exception
            monitor-exit(r3)     // Catch: java.lang.Throwable -> L42
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: c.a.b.C0138ka.b():void");
    }

    public final void a(AbstractC0226j<ReqT, RespT> abstractC0226j) {
        synchronized (this) {
            if (this.h != null) {
                return;
            }
            Preconditions.checkNotNull(abstractC0226j, NotificationCompat.CATEGORY_CALL);
            b(abstractC0226j);
            b();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: c.a.b.ka$b */
    static final class b<RespT> extends AbstractC0226j.a<RespT> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final AbstractC0226j.a<RespT> f1333a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public volatile boolean f1334b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public List<Runnable> f1335c = new ArrayList();

        public b(AbstractC0226j.a<RespT> aVar) {
            this.f1333a = aVar;
        }

        public final void a(Runnable runnable) {
            synchronized (this) {
                if (this.f1334b) {
                    runnable.run();
                } else {
                    this.f1335c.add(runnable);
                }
            }
        }

        @Override // c.a.AbstractC0226j.a
        public void onClose(c.a.za zaVar, C0219ea c0219ea) {
            a(new RunnableC0153na(this, zaVar, c0219ea));
        }

        @Override // c.a.AbstractC0226j.a
        public void onHeaders(C0219ea c0219ea) {
            if (this.f1334b) {
                this.f1333a.onHeaders(c0219ea);
            } else {
                a(new RunnableC0143la(this, c0219ea));
            }
        }

        @Override // c.a.AbstractC0226j.a
        public void onMessage(RespT respt) {
            if (this.f1334b) {
                this.f1333a.onMessage(respt);
            } else {
                a(new RunnableC0148ma(this, respt));
            }
        }

        @Override // c.a.AbstractC0226j.a
        public void onReady() {
            if (this.f1334b) {
                this.f1333a.onReady();
            } else {
                a(new RunnableC0158oa(this));
            }
        }

        public void a() {
            List<Runnable> list;
            List<Runnable> arrayList = new ArrayList<>();
            while (true) {
                synchronized (this) {
                    if (this.f1335c.isEmpty()) {
                        this.f1335c = null;
                        this.f1334b = true;
                        return;
                    } else {
                        list = this.f1335c;
                        this.f1335c = arrayList;
                    }
                }
                Iterator<Runnable> it = list.iterator();
                while (it.hasNext()) {
                    it.next().run();
                }
                list.clear();
                arrayList = list;
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void a(c.a.za zaVar, boolean z) {
        boolean z2;
        AbstractC0226j.a<RespT> aVar;
        synchronized (this) {
            if (this.h == null) {
                b(f1326b);
                z2 = false;
                aVar = this.g;
                this.i = zaVar;
            } else {
                if (z) {
                    return;
                }
                z2 = true;
                aVar = null;
            }
            if (z2) {
                a(new RunnableC0108ea(this, zaVar));
            } else {
                if (aVar != null) {
                    this.f1328d.execute(new a(this, aVar, zaVar));
                }
                b();
            }
            a();
        }
    }

    public final void b(AbstractC0226j<ReqT, RespT> abstractC0226j) {
        Preconditions.checkState(this.h == null, "realCall already set to %s", this.h);
        ScheduledFuture<?> scheduledFuture = this.f1327c;
        if (scheduledFuture != null) {
            scheduledFuture.cancel(false);
        }
        this.h = abstractC0226j;
    }

    public final void a(Runnable runnable) {
        synchronized (this) {
            if (!this.f1330f) {
                this.j.add(runnable);
            } else {
                runnable.run();
            }
        }
    }
}
