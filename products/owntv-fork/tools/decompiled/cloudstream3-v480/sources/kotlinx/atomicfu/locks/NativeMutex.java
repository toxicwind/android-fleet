package kotlinx.atomicfu.locks;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.media3.extractor.text.ttml.TtmlNode;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlin.time.Duration;
import kotlin.time.TimeSource;
import kotlinx.atomicfu.AtomicFU;
import kotlinx.atomicfu.AtomicInt;
import kotlinx.atomicfu.AtomicRef;

/* JADX INFO: compiled from: NativeMutex.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0007\b\u0000\u0018\u00002\u00020\u0001:\u0005\u001f !\"#B7\u0012\u0014\b\u0002\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003\u0012\u0018\b\u0002\u0010\u0006\u001a\u0012\u0012\b\u0012\u00060\u0007j\u0002`\b\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0004\b\t\u0010\nJ\u0006\u0010\u0015\u001a\u00020\u0005J\u0015\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0004¢\u0006\u0004\b\u0019\u0010\u001aJ\u0006\u0010\u001b\u001a\u00020\u0005J\u0006\u0010\u0016\u001a\u00020\u0017J\r\u0010\u001c\u001a\u00020\u001dH\u0000¢\u0006\u0002\b\u001eR\u001d\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR!\u0010\u0006\u001a\u0012\u0012\b\u0012\u00060\u0007j\u0002`\b\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0012\u0010\u000e\u001a\u00060\u000fR\u00020\u0000X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0010\u001a\u0010\u0012\f\u0012\n\u0018\u00010\u0007j\u0004\u0018\u0001`\b0\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006$"}, d2 = {"Lkotlinx/atomicfu/locks/NativeMutex;", "", "park", "Lkotlin/Function1;", "Lkotlin/time/Duration;", "", "unpark", "Ljava/lang/Thread;", "Lkotlinx/atomicfu/locks/ParkingHandle;", "<init>", "(Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;)V", "getPark", "()Lkotlin/jvm/functions/Function1;", "getUnpark", "parkingQueue", "Lkotlinx/atomicfu/locks/NativeMutex$ParkingQueue;", "owningThread", "Lkotlinx/atomicfu/AtomicRef;", "state", "Lkotlinx/atomicfu/AtomicInt;", "holdCount", "lock", "tryLock", "", TypedValues.TransitionType.S_DURATION, "tryLock-LRDsOJo", "(J)Z", "unlock", "getQueueSize", "", "getQueueSize$atomicfu", "ParkingQueue", "Node", "Empty", "Awoken", "TimedOut", "atomicfu"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class NativeMutex {
    private final AtomicInt holdCount;
    private final AtomicRef<Thread> owningThread;
    private final Function1<Duration, Unit> park;
    private final ParkingQueue parkingQueue;
    private final AtomicInt state;
    private final Function1<Thread, Unit> unpark;

    /* JADX WARN: Multi-variable type inference failed */
    public NativeMutex() {
        this(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public NativeMutex(Function1<? super Duration, Unit> park, Function1<? super Thread, Unit> unpark) {
        Intrinsics.checkNotNullParameter(park, "park");
        Intrinsics.checkNotNullParameter(unpark, "unpark");
        this.park = park;
        this.unpark = unpark;
        this.parkingQueue = new ParkingQueue();
        this.owningThread = AtomicFU.atomic((Object) null);
        this.state = AtomicFU.atomic(0);
        this.holdCount = AtomicFU.atomic(0);
    }

    /* JADX INFO: renamed from: kotlinx.atomicfu.locks.NativeMutex$2, reason: invalid class name */
    /* JADX INFO: compiled from: NativeMutex.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    static final /* synthetic */ class AnonymousClass2 extends FunctionReferenceImpl implements Function1<Thread, Unit> {
        AnonymousClass2(Object obj) {
            super(1, obj, ParkingSupport.class, "unpark", "unpark(Ljava/lang/Thread;)V", 0);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Thread thread) {
            invoke2(thread);
            return Unit.INSTANCE;
        }

        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(Thread p0) {
            Intrinsics.checkNotNullParameter(p0, "p0");
            ((ParkingSupport) this.receiver).unpark(p0);
        }
    }

    public /* synthetic */ NativeMutex(Function1 function1, AnonymousClass2 anonymousClass2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? new Function1() { // from class: kotlinx.atomicfu.locks.NativeMutex$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return NativeMutex._init_$lambda$0((Duration) obj);
            }
        } : function1, (i & 2) != 0 ? new AnonymousClass2(ParkingSupport.INSTANCE) : anonymousClass2);
    }

    static final Unit _init_$lambda$0(Duration duration) {
        ParkingSupport.INSTANCE.m10464parkLRDsOJo(duration.getRawValue());
        return Unit.INSTANCE;
    }

    public final Function1<Duration, Unit> getPark() {
        return this.park;
    }

    public final Function1<Thread, Unit> getUnpark() {
        return this.unpark;
    }

    public final void lock() {
        m10462tryLockLRDsOJo(Duration.INSTANCE.m10410getINFINITEUwyO8pc());
    }

    /* JADX INFO: renamed from: tryLock-LRDsOJo, reason: not valid java name */
    public final boolean m10462tryLockLRDsOJo(long duration) {
        Thread threadCurrentThreadHandle = ParkingSupport.INSTANCE.currentThreadHandle();
        if (this.holdCount.getValue() > 0 && Intrinsics.areEqual(threadCurrentThreadHandle, this.owningThread.getValue())) {
            this.holdCount.incrementAndGet();
            return true;
        }
        int iIncrementAndGet = this.state.incrementAndGet();
        if (iIncrementAndGet <= 0) {
            throw new IllegalStateException("Negative mutex state should not be possible".toString());
        }
        if (iIncrementAndGet == 1) {
            this.owningThread.setValue(threadCurrentThreadHandle);
            this.holdCount.incrementAndGet();
            return true;
        }
        if (!this.parkingQueue.enqueue().m10463nodeWaitLRDsOJo(duration)) {
            return false;
        }
        this.parkingQueue.dequeue();
        this.owningThread.setValue(threadCurrentThreadHandle);
        this.holdCount.incrementAndGet();
        return true;
    }

    public final void unlock() {
        int iDecrementAndGet;
        if (!Intrinsics.areEqual(ParkingSupport.INSTANCE.currentThreadHandle(), this.owningThread.getValue())) {
            throw new IllegalStateException("Thread is not holding the lock".toString());
        }
        if (this.holdCount.getValue() <= 0) {
            throw new IllegalStateException("Thread unlocked more than it locked".toString());
        }
        if (this.holdCount.decrementAndGet() <= 0 && (iDecrementAndGet = this.state.decrementAndGet()) != 0) {
            if (iDecrementAndGet <= 0) {
                throw new IllegalStateException("Negative mutex state should not be possible".toString());
            }
            Node head = this.parkingQueue.getHead();
            while (!head.nodeWake()) {
                this.parkingQueue.dequeue();
                head = this.parkingQueue.getHead();
                if (this.state.decrementAndGet() == 0) {
                    return;
                }
            }
        }
    }

    public final boolean tryLock() {
        Thread threadCurrentThreadHandle = ParkingSupport.INSTANCE.currentThreadHandle();
        if ((this.holdCount.getValue() <= 0 || !Intrinsics.areEqual(this.owningThread.getValue(), threadCurrentThreadHandle)) && !this.state.compareAndSet(0, 1)) {
            return false;
        }
        this.owningThread.setValue(threadCurrentThreadHandle);
        this.holdCount.incrementAndGet();
        return true;
    }

    /* JADX INFO: compiled from: NativeMutex.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\n\u0010\t\u001a\u00060\u0006R\u00020\u0007J\n\u0010\n\u001a\u00060\u0006R\u00020\u0007J\u0006\u0010\u000b\u001a\u00020\fR\u0018\u0010\u0004\u001a\f\u0012\b\u0012\u00060\u0006R\u00020\u00070\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0018\u0010\b\u001a\f\u0012\b\u0012\u00060\u0006R\u00020\u00070\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lkotlinx/atomicfu/locks/NativeMutex$ParkingQueue;", "", "<init>", "(Lkotlinx/atomicfu/locks/NativeMutex;)V", TtmlNode.TAG_HEAD, "Lkotlinx/atomicfu/AtomicRef;", "Lkotlinx/atomicfu/locks/NativeMutex$Node;", "Lkotlinx/atomicfu/locks/NativeMutex;", "tail", "getHead", "enqueue", "dequeue", "", "atomicfu"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public final class ParkingQueue {
        private final AtomicRef<Node> head;
        private final AtomicRef<Node> tail;

        public ParkingQueue() {
            Node node = NativeMutex.this.new Node();
            this.head = AtomicFU.atomic(node);
            this.tail = AtomicFU.atomic(node);
        }

        public final Node getHead() {
            return this.head.getValue();
        }

        public final Node enqueue() {
            while (true) {
                Node node = NativeMutex.this.new Node();
                Node value = this.tail.getValue();
                boolean zCompareAndSet = value.getNext().compareAndSet(null, node);
                AtomicRef<Node> atomicRef = this.tail;
                if (zCompareAndSet) {
                    atomicRef.compareAndSet(value, node);
                    return value;
                }
                Node value2 = value.getNext().getValue();
                Intrinsics.checkNotNull(value2);
                atomicRef.compareAndSet(value, value2);
            }
        }

        public final void dequeue() {
            Node value;
            Node value2;
            do {
                value = this.head.getValue();
                value2 = value.getNext().getValue();
                if (value2 == null) {
                    throw new IllegalStateException("Dequeing parker but already empty, should not be possible");
                }
            } while (!this.head.compareAndSet(value, value2));
        }
    }

    /* JADX INFO: compiled from: NativeMutex.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0015\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e¢\u0006\u0004\b\u000f\u0010\u0010J\u0006\u0010\u0011\u001a\u00020\fR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u001d\u0010\b\u001a\u000e\u0012\n\u0012\b\u0018\u00010\u0000R\u00020\t0\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0007¨\u0006\u0012"}, d2 = {"Lkotlinx/atomicfu/locks/NativeMutex$Node;", "", "<init>", "(Lkotlinx/atomicfu/locks/NativeMutex;)V", "state", "Lkotlinx/atomicfu/AtomicRef;", "getState", "()Lkotlinx/atomicfu/AtomicRef;", "next", "Lkotlinx/atomicfu/locks/NativeMutex;", "getNext", "nodeWait", "", TypedValues.TransitionType.S_DURATION, "Lkotlin/time/Duration;", "nodeWait-LRDsOJo", "(J)Z", "nodeWake", "atomicfu"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public final class Node {
        private final AtomicRef<Object> state = AtomicFU.atomic(Empty.INSTANCE);
        private final AtomicRef<Node> next = AtomicFU.atomic((Object) null);

        public Node() {
        }

        public final AtomicRef<Object> getState() {
            return this.state;
        }

        public final AtomicRef<Node> getNext() {
            return this.next;
        }

        /* JADX INFO: renamed from: nodeWait-LRDsOJo, reason: not valid java name */
        public final boolean m10463nodeWaitLRDsOJo(long duration) {
            long jM10449plusLRDsOJo = TimeSource.Monotonic.ValueTimeMark.m10449plusLRDsOJo(TimeSource.Monotonic.INSTANCE.m10435markNowz9LOYto(), duration);
            while (true) {
                Object value = this.state.getValue();
                if (Intrinsics.areEqual(value, Empty.INSTANCE)) {
                    if (this.state.compareAndSet(Empty.INSTANCE, ParkingSupport.INSTANCE.currentThreadHandle())) {
                        NativeMutex.this.getPark().invoke(Duration.m10316boximpl(TimeSource.Monotonic.ValueTimeMark.m10446minus6eNON_k(jM10449plusLRDsOJo, TimeSource.Monotonic.INSTANCE.m10435markNowz9LOYto())));
                        if (TimeSource.Monotonic.ValueTimeMark.m10437compareTo6eNON_k(jM10449plusLRDsOJo, TimeSource.Monotonic.INSTANCE.m10435markNowz9LOYto()) < 0) {
                            this.state.compareAndSet(ParkingSupport.INSTANCE.currentThreadHandle(), TimedOut.INSTANCE);
                        }
                    }
                } else if (value instanceof Thread) {
                    NativeMutex.this.getPark().invoke(Duration.m10316boximpl(TimeSource.Monotonic.ValueTimeMark.m10446minus6eNON_k(jM10449plusLRDsOJo, TimeSource.Monotonic.INSTANCE.m10435markNowz9LOYto())));
                    if (TimeSource.Monotonic.ValueTimeMark.m10437compareTo6eNON_k(jM10449plusLRDsOJo, TimeSource.Monotonic.INSTANCE.m10435markNowz9LOYto()) < 0) {
                        this.state.compareAndSet(ParkingSupport.INSTANCE.currentThreadHandle(), TimedOut.INSTANCE);
                    }
                } else {
                    if (Intrinsics.areEqual(value, Awoken.INSTANCE)) {
                        return true;
                    }
                    if (Intrinsics.areEqual(value, TimedOut.INSTANCE)) {
                        return false;
                    }
                }
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        public final boolean nodeWake() {
            while (true) {
                Object value = this.state.getValue();
                if (Intrinsics.areEqual(value, Empty.INSTANCE)) {
                    if (this.state.compareAndSet(Empty.INSTANCE, Awoken.INSTANCE)) {
                        return true;
                    }
                } else if (value instanceof Thread) {
                    if (this.state.compareAndSet(value, Awoken.INSTANCE)) {
                        NativeMutex.this.getUnpark().invoke(value);
                        return true;
                    }
                } else {
                    if (Intrinsics.areEqual(value, Awoken.INSTANCE)) {
                        throw new IllegalStateException("Node is already woken");
                    }
                    if (Intrinsics.areEqual(value, TimedOut.INSTANCE)) {
                        return false;
                    }
                }
            }
        }
    }

    /* JADX INFO: compiled from: NativeMutex.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\bÂ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lkotlinx/atomicfu/locks/NativeMutex$Empty;", "", "<init>", "()V", "atomicfu"}, k = 1, mv = {2, 2, 0}, xi = 48)
    private static final class Empty {
        public static final Empty INSTANCE = new Empty();

        private Empty() {
        }
    }

    /* JADX INFO: compiled from: NativeMutex.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\bÂ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lkotlinx/atomicfu/locks/NativeMutex$Awoken;", "", "<init>", "()V", "atomicfu"}, k = 1, mv = {2, 2, 0}, xi = 48)
    private static final class Awoken {
        public static final Awoken INSTANCE = new Awoken();

        private Awoken() {
        }
    }

    /* JADX INFO: compiled from: NativeMutex.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\bÂ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lkotlinx/atomicfu/locks/NativeMutex$TimedOut;", "", "<init>", "()V", "atomicfu"}, k = 1, mv = {2, 2, 0}, xi = 48)
    private static final class TimedOut {
        public static final TimedOut INSTANCE = new TimedOut();

        private TimedOut() {
        }
    }

    public final int getQueueSize$atomicfu() {
        Node head = this.parkingQueue.getHead();
        int i = 0;
        while (head != null) {
            head = head.getNext().getValue();
            i++;
        }
        return i;
    }
}
