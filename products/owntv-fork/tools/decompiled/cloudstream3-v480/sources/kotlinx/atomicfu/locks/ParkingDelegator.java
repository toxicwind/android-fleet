package kotlinx.atomicfu.locks;

import java.util.concurrent.locks.LockSupport;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: JvmParkingDelegator.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\bÀ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\n\u0010\u0004\u001a\u00060\u0005j\u0002`\u0006J \u0010\u0007\u001a\u00020\b2\n\u0010\t\u001a\u00060\u0005j\u0002`\u00062\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bJ(\u0010\r\u001a\u00020\b2\n\u0010\t\u001a\u00060\u0005j\u0002`\u00062\u0006\u0010\u000e\u001a\u00020\u000f2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bJ\u0012\u0010\u0010\u001a\u00020\b2\n\u0010\t\u001a\u00060\u0005j\u0002`\u0006J\u0012\u0010\u0011\u001a\u00020\b2\n\u0010\t\u001a\u00060\u0005j\u0002`\u0006¨\u0006\u0012"}, d2 = {"Lkotlinx/atomicfu/locks/ParkingDelegator;", "", "<init>", "()V", "createRef", "Ljava/lang/Thread;", "Lkotlinx/atomicfu/locks/ParkingData;", "wait", "", "ref", "shouldWait", "Lkotlin/Function0;", "", "timedWait", "nanos", "", "wake", "destroyRef", "atomicfu"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class ParkingDelegator {
    public static final ParkingDelegator INSTANCE = new ParkingDelegator();

    public final void destroyRef(Thread ref) {
        Intrinsics.checkNotNullParameter(ref, "ref");
    }

    private ParkingDelegator() {
    }

    public final Thread createRef() {
        Thread threadCurrentThread = Thread.currentThread();
        Intrinsics.checkNotNullExpressionValue(threadCurrentThread, "currentThread(...)");
        return threadCurrentThread;
    }

    public final void wait(Thread ref, Function0<Boolean> shouldWait) {
        Intrinsics.checkNotNullParameter(ref, "ref");
        Intrinsics.checkNotNullParameter(shouldWait, "shouldWait");
        LockSupport.park();
    }

    public final void timedWait(Thread ref, long nanos, Function0<Boolean> shouldWait) {
        Intrinsics.checkNotNullParameter(ref, "ref");
        Intrinsics.checkNotNullParameter(shouldWait, "shouldWait");
        LockSupport.parkNanos(nanos);
    }

    public final void wake(Thread ref) {
        Intrinsics.checkNotNullParameter(ref, "ref");
        LockSupport.unpark(ref);
    }
}
