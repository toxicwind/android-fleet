package c.a.b;

import com.google.common.base.Preconditions;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.logging.Level;
import java.util.logging.Logger;

/* JADX INFO: loaded from: classes2.dex */
public final class Ed implements Executor, Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Logger f955a = Logger.getLogger(Ed.class.getName());

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final a f956b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Executor f957c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Queue<Runnable> f958d = new ConcurrentLinkedQueue();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public volatile int f959e = 0;

    private static abstract class a {
        public /* synthetic */ a(Dd dd) {
        }

        public abstract void a(Ed ed, int i);

        public abstract boolean a(Ed ed, int i, int i2);
    }

    private static final class b extends a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final AtomicIntegerFieldUpdater<Ed> f960a;

        public /* synthetic */ b(AtomicIntegerFieldUpdater atomicIntegerFieldUpdater, Dd dd) {
            super(null);
            this.f960a = atomicIntegerFieldUpdater;
        }

        @Override // c.a.b.Ed.a
        public boolean a(Ed ed, int i, int i2) {
            return this.f960a.compareAndSet(ed, i, i2);
        }

        @Override // c.a.b.Ed.a
        public void a(Ed ed, int i) {
            this.f960a.set(ed, i);
        }
    }

    static {
        a cVar;
        Dd dd = null;
        try {
            cVar = new b(AtomicIntegerFieldUpdater.newUpdater(Ed.class, "e"), dd);
        } catch (Throwable th) {
            f955a.log(Level.SEVERE, "FieldUpdaterAtomicHelper failed", th);
            cVar = new c(dd);
        }
        f956b = cVar;
    }

    public Ed(Executor executor) {
        Preconditions.checkNotNull(executor, "'executor' must not be null.");
        this.f957c = executor;
    }

    @Override // java.util.concurrent.Executor
    public void execute(Runnable runnable) {
        Queue<Runnable> queue = this.f958d;
        Preconditions.checkNotNull(runnable, "'r' must not be null.");
        queue.add(runnable);
        a(runnable);
    }

    @Override // java.lang.Runnable
    public void run() {
        Runnable runnablePoll;
        try {
            Executor executor = this.f957c;
            while (executor == this.f957c && (runnablePoll = this.f958d.poll()) != null) {
                try {
                    runnablePoll.run();
                } catch (RuntimeException e2) {
                    f955a.log(Level.SEVERE, "Exception while executing runnable " + runnablePoll, (Throwable) e2);
                }
            }
            f956b.a(this, 0);
            if (this.f958d.isEmpty()) {
                return;
            }
            a((Runnable) null);
        } catch (Throwable th) {
            f956b.a(this, 0);
            throw th;
        }
    }

    public final void a(Runnable runnable) {
        if (f956b.a(this, 0, -1)) {
            try {
                this.f957c.execute(this);
            } catch (Throwable th) {
                if (runnable != null) {
                    this.f958d.remove(runnable);
                }
                f956b.a(this, 0);
                throw th;
            }
        }
    }

    private static final class c extends a {
        public /* synthetic */ c(Dd dd) {
            super(null);
        }

        @Override // c.a.b.Ed.a
        public boolean a(Ed ed, int i, int i2) {
            synchronized (ed) {
                if (ed.f959e != i) {
                    return false;
                }
                ed.f959e = i2;
                return true;
            }
        }

        @Override // c.a.b.Ed.a
        public void a(Ed ed, int i) {
            synchronized (ed) {
                ed.f959e = i;
            }
        }
    }
}
