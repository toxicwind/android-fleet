package c.a.b;

import com.google.common.base.Stopwatch;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes2.dex */
public final class Yc {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ScheduledExecutorService f1195a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Executor f1196b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Runnable f1197c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Stopwatch f1198d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public long f1199e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f1200f;
    public ScheduledFuture<?> g;

    private final class a implements Runnable {
        public /* synthetic */ a(Xc xc) {
        }

        @Override // java.lang.Runnable
        public void run() {
            Yc yc = Yc.this;
            Xc xc = null;
            if (!yc.f1200f) {
                yc.g = null;
                return;
            }
            long jElapsed = yc.f1198d.elapsed(TimeUnit.NANOSECONDS);
            Yc yc2 = Yc.this;
            if (yc2.f1199e - jElapsed > 0) {
                yc2.g = yc2.f1195a.schedule(yc2.new b(xc), Yc.this.f1199e - jElapsed, TimeUnit.NANOSECONDS);
                return;
            }
            yc2.f1200f = false;
            yc2.g = null;
            yc2.f1197c.run();
        }
    }

    private final class b implements Runnable {
        public /* synthetic */ b(Xc xc) {
        }

        @Override // java.lang.Runnable
        public void run() {
            Yc yc = Yc.this;
            yc.f1196b.execute(yc.new a(null));
        }
    }

    public Yc(Runnable runnable, Executor executor, ScheduledExecutorService scheduledExecutorService, Stopwatch stopwatch) {
        this.f1197c = runnable;
        this.f1196b = executor;
        this.f1195a = scheduledExecutorService;
        this.f1198d = stopwatch;
        stopwatch.start();
    }

    public void a(long j, TimeUnit timeUnit) {
        long nanos = timeUnit.toNanos(j);
        long jElapsed = this.f1198d.elapsed(TimeUnit.NANOSECONDS) + nanos;
        this.f1200f = true;
        if (jElapsed - this.f1199e < 0 || this.g == null) {
            ScheduledFuture<?> scheduledFuture = this.g;
            if (scheduledFuture != null) {
                scheduledFuture.cancel(false);
            }
            this.g = this.f1195a.schedule(new b(null), nanos, TimeUnit.NANOSECONDS);
        }
        this.f1199e = jElapsed;
    }
}
