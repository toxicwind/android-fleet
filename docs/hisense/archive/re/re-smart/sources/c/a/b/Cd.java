package c.a.b;

import com.google.common.base.Preconditions;
import java.util.ArrayDeque;
import java.util.concurrent.Executor;
import java.util.logging.Level;
import java.util.logging.Logger;

/* JADX INFO: loaded from: classes2.dex */
public class Cd implements Executor {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Logger f924a = Logger.getLogger(Cd.class.getName());

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean f925b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public ArrayDeque<Runnable> f926c;

    public final void a() {
        while (true) {
            Runnable runnablePoll = this.f926c.poll();
            if (runnablePoll == null) {
                return;
            }
            try {
                runnablePoll.run();
            } catch (Throwable th) {
                f924a.log(Level.SEVERE, "Exception while executing runnable " + runnablePoll, th);
            }
        }
    }

    @Override // java.util.concurrent.Executor
    public void execute(Runnable runnable) {
        Preconditions.checkNotNull(runnable, "'task' must not be null.");
        if (this.f925b) {
            if (this.f926c == null) {
                this.f926c = new ArrayDeque<>(4);
            }
            this.f926c.add(runnable);
            return;
        }
        this.f925b = true;
        try {
            runnable.run();
        } catch (Throwable th) {
            f924a.log(Level.SEVERE, "Exception while executing runnable " + runnable, th);
        } finally {
            if (this.f926c != null) {
                a();
            }
            this.f925b = false;
        }
    }
}
