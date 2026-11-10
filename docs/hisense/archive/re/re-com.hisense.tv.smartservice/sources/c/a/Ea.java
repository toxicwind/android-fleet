package c.a;

import com.google.common.base.Preconditions;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes2.dex */
public final class Ea implements Executor {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Thread.UncaughtExceptionHandler f795a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Queue<Runnable> f796b = new ConcurrentLinkedQueue();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final AtomicReference<Thread> f797c = new AtomicReference<>();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX INFO: Access modifiers changed from: private */
    public static class a implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Runnable f798a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public boolean f799b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public boolean f800c;

        public a(Runnable runnable) {
            Preconditions.checkNotNull(runnable, "task");
            this.f798a = runnable;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.f799b) {
                return;
            }
            this.f800c = true;
            this.f798a.run();
        }
    }

    public static final class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final a f801a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final ScheduledFuture<?> f802b;

        public /* synthetic */ b(a aVar, ScheduledFuture scheduledFuture, Da da) {
            Preconditions.checkNotNull(aVar, "runnable");
            this.f801a = aVar;
            Preconditions.checkNotNull(scheduledFuture, "future");
            this.f802b = scheduledFuture;
        }

        public void a() {
            this.f801a.f799b = true;
            this.f802b.cancel(false);
        }
    }

    public Ea(Thread.UncaughtExceptionHandler uncaughtExceptionHandler) {
        Preconditions.checkNotNull(uncaughtExceptionHandler, "uncaughtExceptionHandler");
        this.f795a = uncaughtExceptionHandler;
    }

    public final void a() {
        while (this.f797c.compareAndSet(null, Thread.currentThread())) {
            while (true) {
                try {
                    Runnable runnablePoll = this.f796b.poll();
                    if (runnablePoll == null) {
                        break;
                    }
                    try {
                        runnablePoll.run();
                    } catch (Throwable th) {
                        this.f795a.uncaughtException(Thread.currentThread(), th);
                    }
                } catch (Throwable th2) {
                    this.f797c.set(null);
                    throw th2;
                }
            }
            this.f797c.set(null);
            if (this.f796b.isEmpty()) {
                return;
            }
        }
    }

    public void b() {
        Preconditions.checkState(Thread.currentThread() == this.f797c.get(), "Not called from the SynchronizationContext");
    }

    @Override // java.util.concurrent.Executor
    public final void execute(Runnable runnable) {
        Queue<Runnable> queue = this.f796b;
        Preconditions.checkNotNull(runnable, "runnable is null");
        queue.add(runnable);
        a();
    }

    public final void a(Runnable runnable) {
        Queue<Runnable> queue = this.f796b;
        Preconditions.checkNotNull(runnable, "runnable is null");
        queue.add(runnable);
    }

    public final b a(Runnable runnable, long j, TimeUnit timeUnit, ScheduledExecutorService scheduledExecutorService) {
        a aVar = new a(runnable);
        return new b(aVar, scheduledExecutorService.schedule(new Da(this, aVar, runnable), j, timeUnit), null);
    }
}
