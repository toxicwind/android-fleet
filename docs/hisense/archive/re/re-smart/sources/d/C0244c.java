package d;

import com.ju.lib.datacommunication.network.http.dns.Tools;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.concurrent.TimeUnit;

/* JADX INFO: renamed from: d.c, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public class C0244c extends E {
    public static final long IDLE_TIMEOUT_MILLIS = TimeUnit.SECONDS.toMillis(60);
    public static final long IDLE_TIMEOUT_NANOS = TimeUnit.MILLISECONDS.toNanos(IDLE_TIMEOUT_MILLIS);
    public static final int TIMEOUT_WRITE_SIZE = 65536;
    public static C0244c head;
    public boolean inQueue;
    public C0244c next;
    public long timeoutAt;

    /* JADX INFO: renamed from: d.c$a */
    private static final class a extends Thread {
        public a() {
            super("Okio Watchdog");
            setDaemon(true);
        }

        /* JADX WARN: Code restructure failed: missing block: B:14:0x0015, code lost:
        
            r1.timedOut();
         */
        @Override // java.lang.Thread, java.lang.Runnable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public void run() {
            /*
                r3 = this;
            L0:
                java.lang.Class<d.c> r0 = d.C0244c.class
                monitor-enter(r0)     // Catch: java.lang.InterruptedException -> L0
                d.c r1 = d.C0244c.awaitTimeout()     // Catch: java.lang.Throwable -> L19
                if (r1 != 0) goto Lb
                monitor-exit(r0)     // Catch: java.lang.Throwable -> L19
                goto L0
            Lb:
                d.c r2 = d.C0244c.head     // Catch: java.lang.Throwable -> L19
                if (r1 != r2) goto L14
                r1 = 0
                d.C0244c.head = r1     // Catch: java.lang.Throwable -> L19
                monitor-exit(r0)     // Catch: java.lang.Throwable -> L19
                return
            L14:
                monitor-exit(r0)     // Catch: java.lang.Throwable -> L19
                r1.timedOut()     // Catch: java.lang.InterruptedException -> L0
                goto L0
            L19:
                r1 = move-exception
                monitor-exit(r0)     // Catch: java.lang.Throwable -> L19
                throw r1     // Catch: java.lang.InterruptedException -> L0
            */
            throw new UnsupportedOperationException("Method not decompiled: d.C0244c.a.run():void");
        }
    }

    public static C0244c awaitTimeout() throws InterruptedException {
        C0244c c0244c = head.next;
        if (c0244c == null) {
            long jNanoTime = System.nanoTime();
            C0244c.class.wait(IDLE_TIMEOUT_MILLIS);
            if (head.next != null || System.nanoTime() - jNanoTime < IDLE_TIMEOUT_NANOS) {
                return null;
            }
            return head;
        }
        long jNanoTime2 = c0244c.timeoutAt - System.nanoTime();
        if (jNanoTime2 > 0) {
            long j = jNanoTime2 / 1000000;
            C0244c.class.wait(j, (int) (jNanoTime2 - (1000000 * j)));
            return null;
        }
        head.next = c0244c.next;
        c0244c.next = null;
        return c0244c;
    }

    public static synchronized boolean cancelScheduledTimeout(C0244c c0244c) {
        C0244c c0244c2 = head;
        while (c0244c2 != null) {
            C0244c c0244c3 = c0244c2.next;
            if (c0244c3 == c0244c) {
                c0244c2.next = c0244c.next;
                c0244c.next = null;
                return false;
            }
            c0244c2 = c0244c3;
        }
        return true;
    }

    private long remainingNanos(long j) {
        return this.timeoutAt - j;
    }

    public static synchronized void scheduleTimeout(C0244c c0244c, long j, boolean z) {
        if (head == null) {
            head = new C0244c();
            new a().start();
        }
        long jNanoTime = System.nanoTime();
        if (j != 0 && z) {
            c0244c.timeoutAt = Math.min(j, c0244c.deadlineNanoTime() - jNanoTime) + jNanoTime;
        } else if (j != 0) {
            c0244c.timeoutAt = j + jNanoTime;
        } else {
            if (!z) {
                throw new AssertionError();
            }
            c0244c.timeoutAt = c0244c.deadlineNanoTime();
        }
        long j2 = c0244c.timeoutAt - jNanoTime;
        C0244c c0244c2 = head;
        while (c0244c2.next != null) {
            C0244c c0244c3 = c0244c2.next;
            if (j2 < c0244c3.timeoutAt - jNanoTime) {
                break;
            } else {
                c0244c2 = c0244c3;
            }
        }
        c0244c.next = c0244c2.next;
        c0244c2.next = c0244c;
        if (c0244c2 == head) {
            C0244c.class.notify();
        }
    }

    public final void enter() {
        if (this.inQueue) {
            throw new IllegalStateException("Unbalanced enter/exit");
        }
        long jTimeoutNanos = timeoutNanos();
        boolean zHasDeadline = hasDeadline();
        if (jTimeoutNanos != 0 || zHasDeadline) {
            this.inQueue = true;
            scheduleTimeout(this, jTimeoutNanos, zHasDeadline);
        }
    }

    public final boolean exit() {
        if (!this.inQueue) {
            return false;
        }
        this.inQueue = false;
        return cancelScheduledTimeout(this);
    }

    public IOException newTimeoutException(IOException iOException) {
        InterruptedIOException interruptedIOException = new InterruptedIOException(Tools.KEY_TIMEOUT);
        if (iOException != null) {
            interruptedIOException.initCause(iOException);
        }
        return interruptedIOException;
    }

    public final B sink(B b2) {
        return new C0242a(this, b2);
    }

    public final C source(C c2) {
        return new C0243b(this, c2);
    }

    public void timedOut() {
    }

    public final void exit(boolean z) throws IOException {
        if (exit() && z) {
            throw newTimeoutException(null);
        }
    }

    public final IOException exit(IOException iOException) {
        return !exit() ? iOException : newTimeoutException(iOException);
    }
}
