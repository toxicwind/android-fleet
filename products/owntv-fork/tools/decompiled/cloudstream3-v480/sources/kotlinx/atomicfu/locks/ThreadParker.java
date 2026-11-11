package kotlinx.atomicfu.locks;

import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.time.Duration;
import kotlin.time.DurationUnit;
import kotlin.time.TimeSource;
import kotlinx.atomicfu.AtomicFU;
import kotlinx.atomicfu.AtomicRef;

/* JADX INFO: compiled from: ThreadParker.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\t\u001a\u00020\nJ\u000e\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\rJ \u0010\u000e\u001a\u00020\n2\u0016\u0010\u000f\u001a\u0012\u0012\b\u0012\u00060\u0011j\u0002`\u0012\u0012\u0004\u0012\u00020\n0\u0010H\u0002J\u0006\u0010\u0013\u001a\u00020\nR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lkotlinx/atomicfu/locks/ThreadParker;", "", "<init>", "()V", "delegator", "Lkotlinx/atomicfu/locks/ParkingDelegator;", "state", "Lkotlinx/atomicfu/AtomicRef;", "Lkotlinx/atomicfu/locks/ParkingState;", "park", "", "parkNanos", "nanos", "", "parkWith", "invokeWait", "Lkotlin/Function1;", "Ljava/lang/Thread;", "Lkotlinx/atomicfu/locks/ParkingData;", "unpark", "atomicfu"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class ThreadParker {
    private final ParkingDelegator delegator = ParkingDelegator.INSTANCE;
    private final AtomicRef<ParkingState> state = AtomicFU.atomic(Free.INSTANCE);

    public final void park() {
        parkWith(new Function1() { // from class: kotlinx.atomicfu.locks.ThreadParker$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ThreadParker.park$lambda$0(this.f$0, (Thread) obj);
            }
        });
    }

    static final Unit park$lambda$0(final ThreadParker threadParker, Thread data) {
        Intrinsics.checkNotNullParameter(data, "data");
        threadParker.delegator.wait(data, new Function0() { // from class: kotlinx.atomicfu.locks.ThreadParker$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Boolean.valueOf(ThreadParker.park$lambda$0$0(this.f$0));
            }
        });
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean park$lambda$0$0(ThreadParker threadParker) {
        return threadParker.state.getValue() instanceof Parked;
    }

    public final void parkNanos(final long nanos) {
        final long jM10435markNowz9LOYto = TimeSource.Monotonic.INSTANCE.m10435markNowz9LOYto();
        parkWith(new Function1() { // from class: kotlinx.atomicfu.locks.ThreadParker$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ThreadParker.parkNanos$lambda$0(nanos, jM10435markNowz9LOYto, this, (Thread) obj);
            }
        });
    }

    static final Unit parkNanos$lambda$0(long j, long j2, final ThreadParker threadParker, Thread data) {
        Intrinsics.checkNotNullParameter(data, "data");
        long jM10357toLongimpl = j - Duration.m10357toLongimpl(TimeSource.Monotonic.ValueTimeMark.m10440elapsedNowUwyO8pc(j2), DurationUnit.NANOSECONDS);
        if (jM10357toLongimpl > 0) {
            threadParker.delegator.timedWait(data, jM10357toLongimpl, new Function0() { // from class: kotlinx.atomicfu.locks.ThreadParker$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return Boolean.valueOf(ThreadParker.parkNanos$lambda$0$0(this.f$0));
                }
            });
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean parkNanos$lambda$0$0(ThreadParker threadParker) {
        return threadParker.state.getValue() instanceof Parked;
    }

    private final void parkWith(Function1<? super Thread, Unit> invokeWait) {
        while (true) {
            ParkingState value = this.state.getValue();
            if (Intrinsics.areEqual(value, Free.INSTANCE)) {
                Thread threadCreateRef = this.delegator.createRef();
                if (!this.state.compareAndSet(Free.INSTANCE, new Parked(threadCreateRef))) {
                    this.delegator.destroyRef(threadCreateRef);
                } else {
                    invokeWait.invoke(threadCreateRef);
                    while (true) {
                        ParkingState value2 = this.state.getValue();
                        if (value2 instanceof Parked) {
                            if (this.state.compareAndSet(value2, Free.INSTANCE)) {
                                this.delegator.destroyRef(threadCreateRef);
                                return;
                            }
                        } else {
                            if (!(value2 instanceof Unparking)) {
                                if (Intrinsics.areEqual(value2, Free.INSTANCE)) {
                                    this.delegator.destroyRef(threadCreateRef);
                                    return;
                                } else {
                                    if (!Intrinsics.areEqual(value2, Unparked.INSTANCE)) {
                                        throw new NoWhenBranchMatchedException();
                                    }
                                    this.delegator.destroyRef(threadCreateRef);
                                    return;
                                }
                            }
                            if (this.state.compareAndSet(value2, Free.INSTANCE)) {
                                return;
                            }
                        }
                    }
                }
            } else {
                if (!Intrinsics.areEqual(value, Unparked.INSTANCE)) {
                    if (value instanceof Parked) {
                        throw new IllegalStateException("Thread should not be able to call park when it is already parked");
                    }
                    if (!(value instanceof Unparking)) {
                        throw new NoWhenBranchMatchedException();
                    }
                    throw new IllegalStateException("Thread should not be able to call park when it is already parked");
                }
                if (this.state.compareAndSet(Unparked.INSTANCE, Free.INSTANCE)) {
                    return;
                }
            }
        }
    }

    public final void unpark() {
        Unparking unparking = new Unparking();
        while (true) {
            ParkingState value = this.state.getValue();
            if (Intrinsics.areEqual(value, Unparked.INSTANCE) || (value instanceof Unparking)) {
                return;
            }
            if (Intrinsics.areEqual(value, Free.INSTANCE)) {
                if (this.state.compareAndSet(Free.INSTANCE, Unparked.INSTANCE)) {
                    return;
                }
            } else {
                if (!(value instanceof Parked)) {
                    throw new NoWhenBranchMatchedException();
                }
                if (this.state.compareAndSet(value, unparking)) {
                    Parked parked = (Parked) value;
                    this.delegator.wake(parked.getData());
                    if (this.state.compareAndSet(unparking, Free.INSTANCE)) {
                        return;
                    }
                    this.delegator.destroyRef(parked.getData());
                    return;
                }
            }
        }
    }
}
