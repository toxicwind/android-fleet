package c.a.e;

import c.a.Aa;
import c.a.AbstractC0222g;
import c.a.AbstractC0226j;
import c.a.Ba;
import c.a.C0219ea;
import c.a.C0220f;
import c.a.C0223ga;
import c.a.za;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.util.concurrent.AbstractFuture;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.locks.LockSupport;
import java.util.logging.Level;
import java.util.logging.Logger;

/* JADX INFO: loaded from: classes2.dex */
public final class g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Logger f1771a = Logger.getLogger(g.class.getName());

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final C0220f.a<f> f1772b = C0220f.a.a("internal-stub-type");

    private static final class a<T> implements Iterator<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final BlockingQueue<Object> f1773a = new ArrayBlockingQueue(3);

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final d<T> f1774b = new C0010a();

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final AbstractC0226j<?, T> f1775c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final ExecutorC0011g f1776d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public Object f1777e;

        /* JADX INFO: renamed from: c.a.e.g$a$a, reason: collision with other inner class name */
        private final class C0010a extends d<T> {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public boolean f1778a;

            public C0010a() {
                super(null);
                this.f1778a = false;
            }

            @Override // c.a.e.g.d
            public void a() {
                a.this.f1775c.request(1);
            }

            @Override // c.a.AbstractC0226j.a
            public void onClose(za zaVar, C0219ea c0219ea) {
                Preconditions.checkState(!this.f1778a, "ClientCall already closed");
                if (zaVar.c()) {
                    a aVar = a.this;
                    aVar.f1773a.add(aVar);
                } else {
                    a.this.f1773a.add(new Ba(zaVar, c0219ea));
                }
                this.f1778a = true;
            }

            @Override // c.a.AbstractC0226j.a
            public void onHeaders(C0219ea c0219ea) {
            }

            @Override // c.a.AbstractC0226j.a
            public void onMessage(T t) {
                Preconditions.checkState(!this.f1778a, "ClientCall already closed");
                a.this.f1773a.add(t);
            }
        }

        public a(AbstractC0226j<?, T> abstractC0226j, ExecutorC0011g executorC0011g) {
            this.f1775c = abstractC0226j;
            this.f1776d = executorC0011g;
        }

        /* JADX WARN: Code duplicated, block: B:23:0x0037 A[PHI: r0
          0x0037: PHI (r0v11 java.lang.Object) = (r0v9 java.lang.Object), (r0v14 java.lang.Object) binds: [B:8:0x0012, B:22:0x0035] A[DONT_GENERATE, DONT_INLINE]] */
        @Override // java.util.Iterator
        public boolean hasNext() throws Throwable {
            Object obj;
            Object objTake;
            while (true) {
                obj = this.f1777e;
                boolean z = false;
                boolean z2 = true;
                if (obj != null) {
                    break;
                }
                try {
                    if (this.f1776d == null) {
                        while (true) {
                            try {
                                objTake = this.f1773a.take();
                                break;
                            } catch (InterruptedException e2) {
                                try {
                                    this.f1775c.cancel("Thread interrupted", e2);
                                    z = true;
                                } catch (Throwable th) {
                                    th = th;
                                    if (z2) {
                                        Thread.currentThread().interrupt();
                                    }
                                    throw th;
                                }
                            }
                        }
                        if (z) {
                            Thread.currentThread().interrupt();
                        }
                    } else {
                        while (true) {
                            objTake = this.f1773a.poll();
                            if (objTake != null) {
                                break;
                            }
                            try {
                                this.f1776d.a();
                            } catch (InterruptedException e3) {
                                this.f1775c.cancel("Thread interrupted", e3);
                                z = true;
                            }
                        }
                        if (z) {
                            Thread.currentThread().interrupt();
                        }
                    }
                    this.f1777e = objTake;
                } catch (Throwable th2) {
                    th = th2;
                    z2 = false;
                }
            }
            if (!(obj instanceof Ba)) {
                return obj != this;
            }
            Ba ba = (Ba) obj;
            throw ba.f786a.a(ba.f787b);
        }

        /* JADX WARN: Code duplicated, block: B:27:0x0044 A[PHI: r0
          0x0044: PHI (r0v14 java.lang.Object) = (r0v12 java.lang.Object), (r0v17 java.lang.Object) binds: [B:12:0x001f, B:26:0x0042] A[DONT_GENERATE, DONT_INLINE]] */
        @Override // java.util.Iterator
        public T next() throws Throwable {
            Object obj;
            Object objTake;
            Object obj2 = this.f1777e;
            boolean z = true;
            if (!(obj2 instanceof Ba) && obj2 != this) {
                this.f1775c.request(1);
            }
            while (true) {
                obj = this.f1777e;
                boolean z2 = false;
                if (obj != null) {
                    break;
                }
                try {
                    if (this.f1776d == null) {
                        while (true) {
                            try {
                                objTake = this.f1773a.take();
                                break;
                            } catch (InterruptedException e2) {
                                try {
                                    this.f1775c.cancel("Thread interrupted", e2);
                                    z2 = true;
                                } catch (Throwable th) {
                                    th = th;
                                    if (z) {
                                        Thread.currentThread().interrupt();
                                    }
                                    throw th;
                                }
                            }
                        }
                        if (z2) {
                            Thread.currentThread().interrupt();
                        }
                    } else {
                        while (true) {
                            objTake = this.f1773a.poll();
                            if (objTake != null) {
                                break;
                            }
                            try {
                                this.f1776d.a();
                            } catch (InterruptedException e3) {
                                this.f1775c.cancel("Thread interrupted", e3);
                                z2 = true;
                            }
                        }
                        if (z2) {
                            Thread.currentThread().interrupt();
                        }
                    }
                    this.f1777e = objTake;
                } catch (Throwable th2) {
                    th = th2;
                    z = false;
                }
            }
            if (obj instanceof Ba) {
                Ba ba = (Ba) obj;
                throw ba.f786a.a(ba.a());
            }
            if (!(obj != this)) {
                throw new NoSuchElementException();
            }
            T t = (T) this.f1777e;
            this.f1777e = null;
            return t;
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    private static final class c<RespT> extends AbstractFuture<RespT> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final AbstractC0226j<?, RespT> f1786a;

        public c(AbstractC0226j<?, RespT> abstractC0226j) {
            this.f1786a = abstractC0226j;
        }

        @Override // com.google.common.util.concurrent.AbstractFuture
        public void interruptTask() {
            this.f1786a.cancel("GrpcFuture was cancelled", null);
        }

        @Override // com.google.common.util.concurrent.AbstractFuture
        public String pendingToString() {
            return MoreObjects.toStringHelper(this).add("clientCall", this.f1786a).toString();
        }

        @Override // com.google.common.util.concurrent.AbstractFuture
        public boolean set(RespT respt) {
            return super.set(respt);
        }

        @Override // com.google.common.util.concurrent.AbstractFuture
        public boolean setException(Throwable th) {
            return super.setException(th);
        }
    }

    private static abstract class d<T> extends AbstractC0226j.a<T> {
        public /* synthetic */ d(c.a.e.f fVar) {
        }

        public abstract void a();
    }

    private static final class e<ReqT, RespT> extends d<RespT> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final r<RespT> f1787a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final b<ReqT> f1788b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public boolean f1789c;

        public e(r<RespT> rVar, b<ReqT> bVar) {
            super(null);
            this.f1787a = rVar;
            this.f1788b = bVar;
            if (rVar instanceof c.a.e.h) {
                ((c.a.e.h) rVar).a(bVar);
            }
            bVar.b();
        }

        @Override // c.a.e.g.d
        public void a() {
            b<ReqT> bVar = this.f1788b;
            int i = bVar.f1783d;
            if (i > 0) {
                bVar.a(i);
            }
        }

        @Override // c.a.AbstractC0226j.a
        public void onClose(za zaVar, C0219ea c0219ea) {
            if (zaVar.c()) {
                this.f1787a.a();
            } else {
                this.f1787a.onError(new Ba(zaVar, c0219ea));
            }
        }

        @Override // c.a.AbstractC0226j.a
        public void onHeaders(C0219ea c0219ea) {
        }

        @Override // c.a.AbstractC0226j.a
        public void onMessage(RespT respt) {
            if (this.f1789c && !this.f1788b.f1781b) {
                throw za.k.b("More than one responses received for unary or client-streaming call").b();
            }
            this.f1789c = true;
            this.f1787a.onNext(respt);
            b<ReqT> bVar = this.f1788b;
            if (bVar.f1781b && bVar.f1784e) {
                bVar.a(1);
            }
        }

        @Override // c.a.AbstractC0226j.a
        public void onReady() {
            Runnable runnable = this.f1788b.f1782c;
            if (runnable != null) {
                runnable.run();
            }
        }
    }

    enum f {
        BLOCKING,
        FUTURE,
        ASYNC
    }

    /* JADX INFO: renamed from: c.a.e.g$g, reason: collision with other inner class name */
    private static final class ExecutorC0011g extends ConcurrentLinkedQueue<Runnable> implements Executor {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final Logger f1794a = Logger.getLogger(ExecutorC0011g.class.getName());

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public volatile Thread f1795b;

        public void a() throws InterruptedException {
            if (Thread.interrupted()) {
                throw new InterruptedException();
            }
            Runnable runnablePoll = poll();
            if (runnablePoll == null) {
                this.f1795b = Thread.currentThread();
                while (true) {
                    try {
                        Runnable runnablePoll2 = poll();
                        if (runnablePoll2 != null) {
                            this.f1795b = null;
                            runnablePoll = runnablePoll2;
                            break;
                        } else {
                            LockSupport.park(this);
                            if (Thread.interrupted()) {
                                throw new InterruptedException();
                            }
                        }
                    } catch (Throwable th) {
                        this.f1795b = null;
                        throw th;
                    }
                }
            }
            do {
                try {
                    runnablePoll.run();
                } catch (Throwable th2) {
                    f1794a.log(Level.WARNING, "Runnable threw exception", th2);
                }
                runnablePoll = poll();
            } while (runnablePoll != null);
        }

        @Override // java.util.concurrent.Executor
        public void execute(Runnable runnable) {
            add(runnable);
            LockSupport.unpark(this.f1795b);
        }
    }

    private static final class h<RespT> extends d<RespT> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final c<RespT> f1796a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public RespT f1797b;

        public h(c<RespT> cVar) {
            super(null);
            this.f1796a = cVar;
        }

        @Override // c.a.e.g.d
        public void a() {
            this.f1796a.f1786a.request(2);
        }

        @Override // c.a.AbstractC0226j.a
        public void onClose(za zaVar, C0219ea c0219ea) {
            if (!zaVar.c()) {
                this.f1796a.setException(new Ba(zaVar, c0219ea));
                return;
            }
            if (this.f1797b == null) {
                this.f1796a.setException(new Ba(za.k.b("No value received for unary call"), c0219ea));
            }
            this.f1796a.set(this.f1797b);
        }

        @Override // c.a.AbstractC0226j.a
        public void onHeaders(C0219ea c0219ea) {
        }

        @Override // c.a.AbstractC0226j.a
        public void onMessage(RespT respt) {
            if (this.f1797b != null) {
                throw za.k.b("More than one value received for unary call").b();
            }
            this.f1797b = respt;
        }
    }

    public static <ReqT, RespT> void a(AbstractC0226j<ReqT, RespT> abstractC0226j, ReqT reqt, r<RespT> rVar) {
        a((AbstractC0226j) abstractC0226j, (Object) reqt, (r) rVar, true);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v1 */
    /* JADX WARN: Type inference failed for: r1v2 */
    /* JADX WARN: Type inference failed for: r1v3 */
    /* JADX WARN: Type inference failed for: r1v5 */
    public static <ReqT, RespT> RespT b(AbstractC0222g abstractC0222g, C0223ga<ReqT, RespT> c0223ga, C0220f c0220f, ReqT reqt) throws Throwable {
        ?? r1;
        RuntimeException e2;
        Error e3;
        ExecutorC0011g executorC0011g = new ExecutorC0011g();
        AbstractC0226j abstractC0226jA = abstractC0222g.a(c0223ga, c0220f.a(f1772b, f.BLOCKING).a(executorC0011g));
        boolean z = false;
        try {
            try {
                ListenableFuture listenableFutureA = a(abstractC0226jA, reqt);
                while (!listenableFutureA.isDone()) {
                    try {
                        executorC0011g.a();
                    } catch (InterruptedException e4) {
                        r1 = 1;
                        try {
                            abstractC0226jA.cancel("Thread interrupted", e4);
                            z = true;
                        } catch (Error e5) {
                            e3 = e5;
                            a((AbstractC0226j<?, ?>) abstractC0226jA, (Throwable) e3);
                            throw null;
                        } catch (RuntimeException e6) {
                            e2 = e6;
                            a((AbstractC0226j<?, ?>) abstractC0226jA, (Throwable) e2);
                            throw null;
                        } catch (Throwable th) {
                            th = th;
                            if (r1 != 0) {
                                Thread.currentThread().interrupt();
                            }
                            throw th;
                        }
                    }
                }
                RespT respt = (RespT) a(listenableFutureA);
                if (z) {
                    Thread.currentThread().interrupt();
                }
                return respt;
            } catch (Throwable th2) {
                th = th2;
                r1 = reqt;
            }
        } catch (Error e7) {
            e3 = e7;
        } catch (RuntimeException e8) {
            e2 = e8;
        } catch (Throwable th3) {
            th = th3;
            r1 = z;
        }
    }

    private static final class b<T> extends c.a.e.e<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final AbstractC0226j<T, ?> f1780a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final boolean f1781b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public Runnable f1782c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f1783d = 1;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public boolean f1784e = true;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public boolean f1785f = false;
        public boolean g = false;

        public b(AbstractC0226j<T, ?> abstractC0226j, boolean z) {
            this.f1780a = abstractC0226j;
            this.f1781b = z;
        }

        @Override // c.a.e.r
        public void a() {
            this.f1780a.halfClose();
            this.g = true;
        }

        public final void b() {
        }

        @Override // c.a.e.r
        public void onError(Throwable th) {
            this.f1780a.cancel("Cancelled by client with StreamObserver.onError()", th);
            this.f1785f = true;
        }

        @Override // c.a.e.r
        public void onNext(T t) {
            Preconditions.checkState(!this.f1785f, "Stream was terminated by error, no further calls are allowed");
            Preconditions.checkState(!this.g, "Stream is already completed, no further calls are allowed");
            this.f1780a.sendMessage(t);
        }

        public void a(int i) {
            if (!this.f1781b && i == 1) {
                this.f1780a.request(2);
            } else {
                this.f1780a.request(i);
            }
        }
    }

    public static <ReqT, RespT> Iterator<RespT> a(AbstractC0222g abstractC0222g, C0223ga<ReqT, RespT> c0223ga, C0220f c0220f, ReqT reqt) {
        ExecutorC0011g executorC0011g = new ExecutorC0011g();
        AbstractC0226j abstractC0226jA = abstractC0222g.a(c0223ga, c0220f.a(f1772b, f.BLOCKING).a(executorC0011g));
        a aVar = new a(abstractC0226jA, executorC0011g);
        a(abstractC0226jA, reqt, aVar.f1774b);
        return aVar;
    }

    public static <ReqT, RespT> ListenableFuture<RespT> a(AbstractC0226j<ReqT, RespT> abstractC0226j, ReqT reqt) {
        c cVar = new c(abstractC0226j);
        a(abstractC0226j, reqt, new h(cVar));
        return cVar;
    }

    public static <V> V a(Future<V> future) {
        try {
            return future.get();
        } catch (InterruptedException e2) {
            Thread.currentThread().interrupt();
            throw za.f1937d.b("Thread interrupted").b(e2).b();
        } catch (ExecutionException e3) {
            Throwable cause = e3.getCause();
            Preconditions.checkNotNull(cause, "t");
            for (Throwable cause2 = cause; cause2 != null; cause2 = cause2.getCause()) {
                if (!(cause2 instanceof Aa)) {
                    if (cause2 instanceof Ba) {
                        Ba ba = (Ba) cause2;
                        throw new Ba(ba.f786a, ba.a());
                    }
                } else {
                    Aa aa = (Aa) cause2;
                    throw new Ba(aa.f779a, aa.f780b);
                }
            }
            throw za.f1938e.b("unexpected exception").b(cause).b();
        }
    }

    public static RuntimeException a(AbstractC0226j<?, ?> abstractC0226j, Throwable th) {
        try {
            abstractC0226j.cancel(null, th);
        } catch (Throwable th2) {
            f1771a.log(Level.SEVERE, "RuntimeException encountered while closing call", th2);
        }
        if (!(th instanceof RuntimeException)) {
            if (th instanceof Error) {
                throw ((Error) th);
            }
            throw new AssertionError(th);
        }
        throw ((RuntimeException) th);
    }

    public static <ReqT, RespT> void a(AbstractC0226j<ReqT, RespT> abstractC0226j, ReqT reqt, r<RespT> rVar, boolean z) {
        a(abstractC0226j, reqt, new e(rVar, new b(abstractC0226j, z)));
    }

    public static <ReqT, RespT> r<ReqT> a(AbstractC0226j<ReqT, RespT> abstractC0226j, r<RespT> rVar) {
        b bVar = new b(abstractC0226j, true);
        e eVar = new e(rVar, bVar);
        abstractC0226j.start(eVar, new C0219ea());
        eVar.a();
        return bVar;
    }

    public static <ReqT, RespT> void a(AbstractC0226j<ReqT, RespT> abstractC0226j, ReqT reqt, d<RespT> dVar) {
        abstractC0226j.start(dVar, new C0219ea());
        dVar.a();
        try {
            abstractC0226j.sendMessage(reqt);
            abstractC0226j.halfClose();
        } catch (Error e2) {
            a((AbstractC0226j<?, ?>) abstractC0226j, (Throwable) e2);
            throw null;
        } catch (RuntimeException e3) {
            a((AbstractC0226j<?, ?>) abstractC0226j, (Throwable) e3);
            throw null;
        }
    }
}
