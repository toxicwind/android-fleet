package kotlinx.atomicfu.locks;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import kotlin.Metadata;
import kotlin.time.Duration;

/* JADX INFO: compiled from: SynchronousMutex.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0015\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n¢\u0006\u0004\b\u000b\u0010\fJ\u0006\u0010\u0007\u001a\u00020\bJ\u0006\u0010\r\u001a\u00020\u000eJ\u0006\u0010\u000f\u001a\u00020\u000eR\u0012\u0010\u0004\u001a\u00060\u0005j\u0002`\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lkotlinx/atomicfu/locks/SynchronousMutex;", "", "<init>", "()V", "reentrantLock", "Ljava/util/concurrent/locks/ReentrantLock;", "Lkotlinx/atomicfu/locks/ReentrantLock;", "tryLock", "", "timeout", "Lkotlin/time/Duration;", "tryLock-LRDsOJo", "(J)Z", "lock", "", "unlock", "atomicfu"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class SynchronousMutex {
    private final ReentrantLock reentrantLock = new ReentrantLock();

    /* JADX INFO: renamed from: tryLock-LRDsOJo, reason: not valid java name */
    public final boolean m10466tryLockLRDsOJo(long timeout) {
        return this.reentrantLock.tryLock(Duration.m10331getInWholeNanosecondsimpl(timeout), TimeUnit.NANOSECONDS);
    }

    public final boolean tryLock() {
        return this.reentrantLock.tryLock();
    }

    public final void lock() {
        this.reentrantLock.lock();
    }

    public final void unlock() {
        this.reentrantLock.unlock();
    }
}
