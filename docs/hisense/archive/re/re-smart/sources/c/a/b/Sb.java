package c.a.b;

import com.google.common.base.Preconditions;
import com.google.common.base.Throwables;
import java.util.logging.Level;
import java.util.logging.Logger;

/* JADX INFO: loaded from: classes2.dex */
public final class Sb implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Logger f1145a = Logger.getLogger(Sb.class.getName());

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Runnable f1146b;

    public Sb(Runnable runnable) {
        Preconditions.checkNotNull(runnable, "task");
        this.f1146b = runnable;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            this.f1146b.run();
        } catch (Throwable th) {
            Logger logger = f1145a;
            Level level = Level.SEVERE;
            StringBuilder sbA = b.a.a.a.a.a("Exception while executing runnable ");
            sbA.append(this.f1146b);
            logger.log(level, sbA.toString(), th);
            Throwables.throwIfUnchecked(th);
            throw new AssertionError(th);
        }
    }

    public String toString() {
        return b.a.a.a.a.a(b.a.a.a.a.a("LogExceptionRunnable("), this.f1146b, ")");
    }
}
