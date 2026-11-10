package b.f.b.b.a.b;

import android.os.Build;
import androidx.annotation.NonNull;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes2.dex */
public class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f679a = "c";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final int f680b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final int f681c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final int f682d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static volatile ExecutorService f683e;

    /* JADX INFO: Access modifiers changed from: private */
    static final class a implements ThreadFactory {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final b f684a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final AtomicInteger f685b = new AtomicInteger(1);

        public a(b bVar) {
            this.f684a = bVar;
        }

        @Override // java.util.concurrent.ThreadFactory
        public synchronized Thread newThread(@NonNull Runnable runnable) {
            return new b.f.b.b.a.b.b(this, runnable, "Smartsense#" + this.f685b.getAndIncrement());
        }
    }

    public interface b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final b f686a;

        static {
            new d();
            f686a = new e();
            new f();
            b bVar = f686a;
        }

        void a(Throwable th);
    }

    static {
        int iAvailableProcessors = Runtime.getRuntime().availableProcessors();
        int i = Build.VERSION.SDK_INT;
        f680b = iAvailableProcessors;
        int i2 = f680b;
        f681c = i2 + 1;
        f682d = (i2 * 2) + 1;
    }

    public static ExecutorService a() {
        if (f683e == null) {
            synchronized (c.class) {
                if (f683e == null) {
                    f683e = new ThreadPoolExecutor(f681c, f682d, 1L, TimeUnit.SECONDS, new LinkedBlockingQueue(128), new a(b.f686a), new ThreadPoolExecutor.AbortPolicy());
                }
            }
        }
        return f683e;
    }
}
