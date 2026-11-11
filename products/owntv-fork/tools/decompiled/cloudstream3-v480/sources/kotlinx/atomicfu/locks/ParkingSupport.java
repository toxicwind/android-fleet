package kotlinx.atomicfu.locks;

import java.util.concurrent.locks.LockSupport;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.time.Duration;
import kotlin.time.DurationUnit;
import kotlin.time.TimeMark;

/* JADX INFO: compiled from: ParkingSupport.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0015\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\u000e\u0010\n\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\fJ\u0012\u0010\r\u001a\u00020\u00052\n\u0010\u000e\u001a\u00060\u000fj\u0002`\u0010J\n\u0010\u0011\u001a\u00060\u000fj\u0002`\u0010¨\u0006\u0012"}, d2 = {"Lkotlinx/atomicfu/locks/ParkingSupport;", "", "<init>", "()V", "park", "", "timeout", "Lkotlin/time/Duration;", "park-LRDsOJo", "(J)V", "parkUntil", "deadline", "Lkotlin/time/TimeMark;", "unpark", "handle", "Ljava/lang/Thread;", "Lkotlinx/atomicfu/locks/ParkingHandle;", "currentThreadHandle", "atomicfu"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class ParkingSupport {
    public static final ParkingSupport INSTANCE = new ParkingSupport();

    private ParkingSupport() {
    }

    /* JADX INFO: renamed from: park-LRDsOJo, reason: not valid java name */
    public final void m10464parkLRDsOJo(long timeout) {
        if (Duration.m10323equalsimpl0(timeout, Duration.INSTANCE.m10410getINFINITEUwyO8pc())) {
            LockSupport.park();
        } else {
            LockSupport.parkNanos(Duration.m10357toLongimpl(timeout, DurationUnit.NANOSECONDS));
        }
    }

    public final void parkUntil(TimeMark deadline) {
        Intrinsics.checkNotNullParameter(deadline, "deadline");
        m10464parkLRDsOJo(Duration.m10349timesUwyO8pc(deadline.mo10306elapsedNowUwyO8pc(), -1));
    }

    public final void unpark(Thread handle) {
        Intrinsics.checkNotNullParameter(handle, "handle");
        LockSupport.unpark(handle);
    }

    public final Thread currentThreadHandle() {
        Thread threadCurrentThread = Thread.currentThread();
        Intrinsics.checkNotNullExpressionValue(threadCurrentThread, "currentThread(...)");
        return threadCurrentThread;
    }
}
