package c.a.b;

import com.google.common.annotations.VisibleForTesting;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

/* JADX INFO: renamed from: c.a.b.zc, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public final class C0211zc extends _a {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final ReferenceQueue<C0211zc> f1537b = new ReferenceQueue<>();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final ConcurrentMap<a, a> f1538c = new ConcurrentHashMap();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final Logger f1539d = Logger.getLogger(C0211zc.class.getName());

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final a f1540e;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0211zc(c.a.X x) {
        super(x);
        ReferenceQueue<C0211zc> referenceQueue = f1537b;
        ConcurrentMap<a, a> concurrentMap = f1538c;
        this.f1540e = new a(this, x, referenceQueue, concurrentMap);
    }

    @Override // c.a.X
    public c.a.X d() {
        a.a(this.f1540e);
        return this.f1205a.d();
    }

    @Override // c.a.X
    public c.a.X e() {
        a.a(this.f1540e);
        return this.f1205a.e();
    }

    /* JADX INFO: renamed from: c.a.b.zc$a */
    @VisibleForTesting
    static final class a extends WeakReference<C0211zc> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final boolean f1541a = Boolean.parseBoolean(System.getProperty("io.grpc.ManagedChannel.enableAllocationTracking", "true"));

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public static final RuntimeException f1542b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final ReferenceQueue<C0211zc> f1543c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final ConcurrentMap<a, a> f1544d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final String f1545e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public final Reference<RuntimeException> f1546f;
        public final AtomicBoolean g;

        static {
            RuntimeException runtimeException = new RuntimeException("ManagedChannel allocation site not recorded.  Set -Dio.grpc.ManagedChannel.enableAllocationTracking=true to enable it");
            runtimeException.setStackTrace(new StackTraceElement[0]);
            f1542b = runtimeException;
        }

        public a(C0211zc c0211zc, c.a.X x, ReferenceQueue<C0211zc> referenceQueue, ConcurrentMap<a, a> concurrentMap) {
            super(c0211zc, referenceQueue);
            this.g = new AtomicBoolean();
            this.f1546f = new SoftReference(f1541a ? new RuntimeException("ManagedChannel allocation site") : f1542b);
            this.f1545e = x.toString();
            this.f1543c = referenceQueue;
            this.f1544d = concurrentMap;
            this.f1544d.put(this, this);
            a(referenceQueue);
        }

        public static /* synthetic */ void a(a aVar) {
            if (aVar.g.getAndSet(true)) {
                return;
            }
            aVar.a();
            a(aVar.f1543c);
        }

        @Override // java.lang.ref.Reference
        public void clear() {
            super.clear();
            this.f1544d.remove(this);
            this.f1546f.clear();
            a(this.f1543c);
        }

        public final void a() {
            super.clear();
            this.f1544d.remove(this);
            this.f1546f.clear();
        }

        @VisibleForTesting
        public static int a(ReferenceQueue<C0211zc> referenceQueue) {
            int i = 0;
            while (true) {
                a aVar = (a) referenceQueue.poll();
                if (aVar == null) {
                    return i;
                }
                RuntimeException runtimeException = aVar.f1546f.get();
                super.clear();
                aVar.f1544d.remove(aVar);
                aVar.f1546f.clear();
                if (!aVar.g.get()) {
                    i++;
                    Level level = Level.SEVERE;
                    if (C0211zc.f1539d.isLoggable(level)) {
                        StringBuilder sbA = b.a.a.a.a.a("*~*~*~ Channel {0} was not shutdown properly!!! ~*~*~*");
                        sbA.append(System.getProperty("line.separator"));
                        sbA.append("    Make sure to call shutdown()/shutdownNow() and wait until awaitTermination() returns true.");
                        LogRecord logRecord = new LogRecord(level, sbA.toString());
                        logRecord.setLoggerName(C0211zc.f1539d.getName());
                        logRecord.setParameters(new Object[]{aVar.f1545e});
                        logRecord.setThrown(runtimeException);
                        C0211zc.f1539d.log(logRecord);
                    }
                }
            }
        }
    }
}
