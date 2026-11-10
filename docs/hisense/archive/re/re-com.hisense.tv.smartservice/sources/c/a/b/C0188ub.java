package c.a.b;

import com.google.common.base.Stopwatch;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/* JADX INFO: renamed from: c.a.b.ub, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public class C0188ub {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Logger f1477a = Logger.getLogger(C0188ub.class.getName());

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final long f1478b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Stopwatch f1479c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Map<O.a, Executor> f1480d = new LinkedHashMap();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f1481e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public Throwable f1482f;
    public long g;

    public C0188ub(long j, Stopwatch stopwatch) {
        this.f1478b = j;
        this.f1479c = stopwatch;
    }

    public void a(O.a aVar, Executor executor) {
        synchronized (this) {
            try {
                if (this.f1481e) {
                    a(executor, this.f1482f != null ? new RunnableC0183tb(aVar, this.f1482f) : new RunnableC0178sb(aVar, this.g));
                } else {
                    this.f1480d.put(aVar, executor);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public boolean a() {
        synchronized (this) {
            if (this.f1481e) {
                return false;
            }
            this.f1481e = true;
            long jElapsed = this.f1479c.elapsed(TimeUnit.NANOSECONDS);
            this.g = jElapsed;
            Map<O.a, Executor> map = this.f1480d;
            this.f1480d = null;
            for (Map.Entry<O.a, Executor> entry : map.entrySet()) {
                a(entry.getValue(), new RunnableC0178sb(entry.getKey(), jElapsed));
            }
            return true;
        }
    }

    public void a(Throwable th) {
        synchronized (this) {
            if (this.f1481e) {
                return;
            }
            this.f1481e = true;
            this.f1482f = th;
            Map<O.a, Executor> map = this.f1480d;
            this.f1480d = null;
            for (Map.Entry<O.a, Executor> entry : map.entrySet()) {
                a(entry.getKey(), entry.getValue(), th);
            }
        }
    }

    public static void a(Executor executor, Runnable runnable) {
        try {
            executor.execute(runnable);
        } catch (Throwable th) {
            f1477a.log(Level.SEVERE, "Failed to execute PingCallback", th);
        }
    }

    public static void a(O.a aVar, Executor executor, Throwable th) {
        a(executor, new RunnableC0183tb(aVar, th));
    }
}
