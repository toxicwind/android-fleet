package d;

import com.ju.lib.datacommunication.network.http.dns.Tools;
import java.io.InterruptedIOException;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes2.dex */
public class E {
    public static final E NONE = new D();
    public long deadlineNanoTime;
    public boolean hasDeadline;
    public long timeoutNanos;

    public static long minTimeout(long j, long j2) {
        if (j == 0) {
            return j2;
        }
        return (j2 != 0 && j >= j2) ? j2 : j;
    }

    public E clearDeadline() {
        this.hasDeadline = false;
        return this;
    }

    public E clearTimeout() {
        this.timeoutNanos = 0L;
        return this;
    }

    public final E deadline(long j, TimeUnit timeUnit) {
        if (j <= 0) {
            throw new IllegalArgumentException(b.a.a.a.a.a("duration <= 0: ", j));
        }
        if (timeUnit == null) {
            throw new IllegalArgumentException("unit == null");
        }
        return deadlineNanoTime(timeUnit.toNanos(j) + System.nanoTime());
    }

    public long deadlineNanoTime() {
        if (this.hasDeadline) {
            return this.deadlineNanoTime;
        }
        throw new IllegalStateException("No deadline");
    }

    public boolean hasDeadline() {
        return this.hasDeadline;
    }

    public void throwIfReached() throws InterruptedIOException {
        if (Thread.interrupted()) {
            Thread.currentThread().interrupt();
            throw new InterruptedIOException("interrupted");
        }
        if (this.hasDeadline && this.deadlineNanoTime - System.nanoTime() <= 0) {
            throw new InterruptedIOException("deadline reached");
        }
    }

    public E timeout(long j, TimeUnit timeUnit) {
        if (j < 0) {
            throw new IllegalArgumentException(b.a.a.a.a.a("timeout < 0: ", j));
        }
        if (timeUnit == null) {
            throw new IllegalArgumentException("unit == null");
        }
        this.timeoutNanos = timeUnit.toNanos(j);
        return this;
    }

    public long timeoutNanos() {
        return this.timeoutNanos;
    }

    public final void waitUntilNotified(Object obj) throws InterruptedIOException {
        try {
            boolean zHasDeadline = hasDeadline();
            long jTimeoutNanos = timeoutNanos();
            long jNanoTime = 0;
            if (!zHasDeadline && jTimeoutNanos == 0) {
                obj.wait();
                return;
            }
            long jNanoTime2 = System.nanoTime();
            if (zHasDeadline && jTimeoutNanos != 0) {
                jTimeoutNanos = Math.min(jTimeoutNanos, deadlineNanoTime() - jNanoTime2);
            } else if (zHasDeadline) {
                jTimeoutNanos = deadlineNanoTime() - jNanoTime2;
            }
            if (jTimeoutNanos > 0) {
                long j = jTimeoutNanos / 1000000;
                obj.wait(j, (int) (jTimeoutNanos - (1000000 * j)));
                jNanoTime = System.nanoTime() - jNanoTime2;
            }
            if (jNanoTime >= jTimeoutNanos) {
                throw new InterruptedIOException(Tools.KEY_TIMEOUT);
            }
        } catch (InterruptedException unused) {
            Thread.currentThread().interrupt();
            throw new InterruptedIOException("interrupted");
        }
    }

    public E deadlineNanoTime(long j) {
        this.hasDeadline = true;
        this.deadlineNanoTime = j;
        return this;
    }
}
