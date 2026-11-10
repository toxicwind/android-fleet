package c.a.b;

import androidx.core.app.NotificationCompat;
import com.google.common.base.Preconditions;
import com.google.common.base.Stopwatch;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes2.dex */
public class Rb {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final long f1126a = TimeUnit.SECONDS.toNanos(10);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final ScheduledExecutorService f1127b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Stopwatch f1128c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final b f1129d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final boolean f1130e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public c f1131f;
    public ScheduledFuture<?> g;
    public ScheduledFuture<?> h;
    public final Runnable i;
    public final Runnable j;
    public final long k;
    public final long l;

    public static final class a implements b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final X f1132a;

        public a(X x) {
            this.f1132a = x;
        }
    }

    public interface b {
    }

    /* JADX INFO: Access modifiers changed from: private */
    enum c {
        IDLE,
        PING_SCHEDULED,
        PING_DELAYED,
        PING_SENT,
        IDLE_AND_PING_SENT,
        DISCONNECTED
    }

    static {
        TimeUnit.MILLISECONDS.toNanos(10L);
    }

    public Rb(b bVar, ScheduledExecutorService scheduledExecutorService, long j, long j2, boolean z) {
        Stopwatch stopwatch = new Stopwatch();
        this.f1131f = c.IDLE;
        this.i = new Sb(new Ob(this));
        this.j = new Sb(new Pb(this));
        Preconditions.checkNotNull(bVar, "keepAlivePinger");
        this.f1129d = bVar;
        Preconditions.checkNotNull(scheduledExecutorService, "scheduler");
        this.f1127b = scheduledExecutorService;
        Preconditions.checkNotNull(stopwatch, NotificationCompat.CATEGORY_STOPWATCH);
        this.f1128c = stopwatch;
        this.k = j;
        this.l = j2;
        this.f1130e = z;
        stopwatch.reset().start();
    }

    public synchronized void c() {
        if (this.f1130e) {
            return;
        }
        if (this.f1131f == c.PING_SCHEDULED || this.f1131f == c.PING_DELAYED) {
            this.f1131f = c.IDLE;
        }
        if (this.f1131f == c.PING_SENT) {
            this.f1131f = c.IDLE_AND_PING_SENT;
        }
    }

    public synchronized void d() {
        if (this.f1130e) {
            b();
        }
    }

    public synchronized void e() {
        if (this.f1131f != c.DISCONNECTED) {
            this.f1131f = c.DISCONNECTED;
            if (this.g != null) {
                this.g.cancel(false);
            }
            if (this.h != null) {
                this.h.cancel(false);
                this.h = null;
            }
        }
    }

    public synchronized void b() {
        if (this.f1131f == c.IDLE) {
            this.f1131f = c.PING_SCHEDULED;
            if (this.h == null) {
                this.h = this.f1127b.schedule(this.j, this.k - this.f1128c.elapsed(TimeUnit.NANOSECONDS), TimeUnit.NANOSECONDS);
            }
        } else if (this.f1131f == c.IDLE_AND_PING_SENT) {
            this.f1131f = c.PING_SENT;
        }
    }

    public synchronized void a() {
        this.f1128c.reset().start();
        if (this.f1131f == c.PING_SCHEDULED) {
            this.f1131f = c.PING_DELAYED;
        } else if (this.f1131f == c.PING_SENT || this.f1131f == c.IDLE_AND_PING_SENT) {
            if (this.g != null) {
                this.g.cancel(false);
            }
            if (this.f1131f == c.IDLE_AND_PING_SENT) {
                this.f1131f = c.IDLE;
            } else {
                this.f1131f = c.PING_SCHEDULED;
                Preconditions.checkState(this.h == null, "There should be no outstanding pingFuture");
                this.h = this.f1127b.schedule(this.j, this.k, TimeUnit.NANOSECONDS);
            }
        }
    }
}
