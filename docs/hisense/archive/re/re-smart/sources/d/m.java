package d;

import java.io.InterruptedIOException;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes2.dex */
public class m extends E {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public E f2114a;

    public m(E e2) {
        if (e2 == null) {
            throw new IllegalArgumentException("delegate == null");
        }
        this.f2114a = e2;
    }

    @Override // d.E
    public E clearDeadline() {
        return this.f2114a.clearDeadline();
    }

    @Override // d.E
    public E clearTimeout() {
        return this.f2114a.clearTimeout();
    }

    @Override // d.E
    public long deadlineNanoTime() {
        return this.f2114a.deadlineNanoTime();
    }

    @Override // d.E
    public boolean hasDeadline() {
        return this.f2114a.hasDeadline();
    }

    @Override // d.E
    public void throwIfReached() throws InterruptedIOException {
        this.f2114a.throwIfReached();
    }

    @Override // d.E
    public E timeout(long j, TimeUnit timeUnit) {
        return this.f2114a.timeout(j, timeUnit);
    }

    @Override // d.E
    public long timeoutNanos() {
        return this.f2114a.timeoutNanos();
    }

    @Override // d.E
    public E deadlineNanoTime(long j) {
        return this.f2114a.deadlineNanoTime(j);
    }
}
