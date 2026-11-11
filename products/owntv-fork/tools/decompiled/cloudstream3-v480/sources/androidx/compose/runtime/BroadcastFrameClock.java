package androidx.compose.runtime;

import androidx.collection.MutableObjectList;
import androidx.compose.runtime.internal.AtomicInt;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.JvmInline;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CancellableContinuationImpl;
import org.mozilla.javascript.ES6Iterator;
import org.schabi.newpipe.extractor.services.peertube.PeertubeParsingHelper;

/* JADX INFO: compiled from: BroadcastFrameClock.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001:\u0002&'B\u0019\u0012\u0010\b\u0002\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u000e\u0010\u0018\u001a\u00020\u00042\u0006\u0010\u0019\u001a\u00020\u001aJ(\u0010\u001b\u001a\u0002H\u001c\"\u0004\b\u0000\u0010\u001c2\u0012\u0010\u001d\u001a\u000e\u0012\u0004\u0012\u00020\u001a\u0012\u0004\u0012\u0002H\u001c0\u001eH\u0096@¢\u0006\u0002\u0010\u001fJ\u0010\u0010 \u001a\u00020\u00042\u0006\u0010!\u001a\u00020\fH\u0002J\u0014\u0010\"\u001a\u00020\u00042\f\b\u0002\u0010#\u001a\u00060$j\u0002`%R\u0016\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\u00060\bj\u0002`\tX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\nR\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u000fR\u0018\u0010\u0010\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00120\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u0018\u0010\u0013\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00120\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0014\u001a\u00020\u00158F¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017¨\u0006("}, d2 = {"Landroidx/compose/runtime/BroadcastFrameClock;", "Landroidx/compose/runtime/MonotonicFrameClock;", "onNewAwaiters", "Lkotlin/Function0;", "", "<init>", "(Lkotlin/jvm/functions/Function0;)V", "lock", "", "Landroidx/compose/runtime/platform/SynchronizedObject;", "Ljava/lang/Object;", "failureCause", "", "pendingAwaitersCountUnlocked", "Landroidx/compose/runtime/BroadcastFrameClock$AtomicAwaitersCount;", "Landroidx/compose/runtime/internal/AtomicInt;", "awaiters", "Landroidx/collection/MutableObjectList;", "Landroidx/compose/runtime/BroadcastFrameClock$FrameAwaiter;", "spareList", "hasAwaiters", "", "getHasAwaiters", "()Z", "sendFrame", "timeNanos", "", "withFrameNanos", "R", "onFrame", "Lkotlin/Function1;", "(Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "fail", "cause", "cancel", "cancellationException", "Ljava/util/concurrent/CancellationException;", "Lkotlinx/coroutines/CancellationException;", "FrameAwaiter", "AtomicAwaitersCount", "runtime"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class BroadcastFrameClock implements MonotonicFrameClock {
    public static final int $stable = 8;
    private MutableObjectList<FrameAwaiter<?>> awaiters;
    private Throwable failureCause;
    private final Object lock;
    private final Function0<Unit> onNewAwaiters;
    private final AtomicInt pendingAwaitersCountUnlocked;
    private MutableObjectList<FrameAwaiter<?>> spareList;

    /* JADX WARN: Multi-variable type inference failed */
    public BroadcastFrameClock() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    @Override // androidx.compose.runtime.MonotonicFrameClock, kotlin.coroutines.CoroutineContext.Element
    public /* synthetic */ CoroutineContext.Key getKey() {
        return MonotonicFrameClock.CC.$default$getKey(this);
    }

    public BroadcastFrameClock(Function0<Unit> function0) {
        this.onNewAwaiters = function0;
        this.lock = new Object();
        this.pendingAwaitersCountUnlocked = AtomicAwaitersCount.m3369constructorimpl();
        this.awaiters = new MutableObjectList<>(0, 1, null);
        this.spareList = new MutableObjectList<>(0, 1, null);
    }

    public /* synthetic */ BroadcastFrameClock(Function0 function0, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : function0);
    }

    @Override // kotlin.coroutines.CoroutineContext.Element, kotlin.coroutines.CoroutineContext
    public <R> R fold(R r, Function2<? super R, ? super CoroutineContext.Element, ? extends R> function2) {
        return (R) MonotonicFrameClock.DefaultImpls.fold(this, r, function2);
    }

    @Override // kotlin.coroutines.CoroutineContext.Element, kotlin.coroutines.CoroutineContext
    public <E extends CoroutineContext.Element> E get(CoroutineContext.Key<E> key) {
        return (E) MonotonicFrameClock.DefaultImpls.get(this, key);
    }

    @Override // kotlin.coroutines.CoroutineContext.Element, kotlin.coroutines.CoroutineContext
    public CoroutineContext minusKey(CoroutineContext.Key<?> key) {
        return MonotonicFrameClock.DefaultImpls.minusKey(this, key);
    }

    @Override // kotlin.coroutines.CoroutineContext
    public CoroutineContext plus(CoroutineContext coroutineContext) {
        return MonotonicFrameClock.DefaultImpls.plus(this, coroutineContext);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: compiled from: BroadcastFrameClock.kt */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u0003\n\u0000\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B)\u0012\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00028\u00000\u0004\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u0007¢\u0006\u0004\b\b\u0010\tJ\u0006\u0010\n\u001a\u00020\u000bJ\u000e\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u0005J\u000e\u0010\u000e\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u0010R\u001c\u0010\u0003\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u0006\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Landroidx/compose/runtime/BroadcastFrameClock$FrameAwaiter;", "R", "", "onFrame", "Lkotlin/Function1;", "", "continuation", "Lkotlinx/coroutines/CancellableContinuation;", "<init>", "(Lkotlin/jvm/functions/Function1;Lkotlinx/coroutines/CancellableContinuation;)V", "cancel", "", "resume", "timeNanos", "resumeWithException", "exception", "", "runtime"}, k = 1, mv = {2, 0, 0}, xi = 48)
    static final class FrameAwaiter<R> {
        private CancellableContinuation<? super R> continuation;
        private Function1<? super Long, ? extends R> onFrame;

        public FrameAwaiter(Function1<? super Long, ? extends R> function1, CancellableContinuation<? super R> cancellableContinuation) {
            this.onFrame = function1;
            this.continuation = cancellableContinuation;
        }

        public final void cancel() {
            this.onFrame = null;
            this.continuation = null;
        }

        public final void resume(long timeNanos) {
            CancellableContinuation<? super R> cancellableContinuation;
            Object objM8936constructorimpl;
            Function1<? super Long, ? extends R> function1 = this.onFrame;
            if (function1 == null || (cancellableContinuation = this.continuation) == null) {
                return;
            }
            try {
                Result.Companion companion = Result.INSTANCE;
                FrameAwaiter<R> frameAwaiter = this;
                objM8936constructorimpl = Result.m8936constructorimpl(function1.invoke(Long.valueOf(timeNanos)));
            } catch (Throwable th) {
                Result.Companion companion2 = Result.INSTANCE;
                objM8936constructorimpl = Result.m8936constructorimpl(ResultKt.createFailure(th));
            }
            cancellableContinuation.resumeWith(objM8936constructorimpl);
        }

        public final void resumeWithException(Throwable exception) {
            CancellableContinuation<? super R> cancellableContinuation = this.continuation;
            if (cancellableContinuation != null) {
                Result.Companion companion = Result.INSTANCE;
                cancellableContinuation.resumeWith(Result.m8936constructorimpl(ResultKt.createFailure(exception)));
            }
        }
    }

    public final boolean getHasAwaiters() {
        return (this.pendingAwaitersCountUnlocked.get() & 134217727) > 0;
    }

    public final void sendFrame(long timeNanos) {
        int i;
        int i2;
        synchronized (this.lock) {
            MutableObjectList<FrameAwaiter<?>> mutableObjectList = this.awaiters;
            this.awaiters = this.spareList;
            this.spareList = mutableObjectList;
            AtomicInt atomicInt = this.pendingAwaitersCountUnlocked;
            do {
                i = atomicInt.get();
            } while (!atomicInt.compareAndSet(i, AtomicAwaitersCount.m3380packimpl(atomicInt, ((i >>> 27) & 15) + 1, 0)));
            int size = mutableObjectList.getSize();
            for (i2 = 0; i2 < size; i2++) {
                mutableObjectList.get(i2).resume(timeNanos);
            }
            mutableObjectList.clear();
            Unit unit = Unit.INSTANCE;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void fail(Throwable cause) {
        int i;
        synchronized (this.lock) {
            if (this.failureCause != null) {
                return;
            }
            this.failureCause = cause;
            MutableObjectList<FrameAwaiter<?>> mutableObjectList = this.awaiters;
            Object[] objArr = mutableObjectList.content;
            int i2 = mutableObjectList._size;
            for (int i3 = 0; i3 < i2; i3++) {
                ((FrameAwaiter) objArr[i3]).resumeWithException(cause);
            }
            this.awaiters.clear();
            AtomicInt atomicInt = this.pendingAwaitersCountUnlocked;
            do {
                i = atomicInt.get();
            } while (!atomicInt.compareAndSet(i, AtomicAwaitersCount.m3380packimpl(atomicInt, ((i >>> 27) & 15) + 1, 0)));
            Unit unit = Unit.INSTANCE;
        }
    }

    public static /* synthetic */ void cancel$default(BroadcastFrameClock broadcastFrameClock, CancellationException cancellationException, int i, Object obj) {
        if ((i & 1) != 0) {
            cancellationException = new CancellationException("clock cancelled");
        }
        broadcastFrameClock.cancel(cancellationException);
    }

    public final void cancel(CancellationException cancellationException) {
        fail(cancellationException);
    }

    /* JADX INFO: compiled from: BroadcastFrameClock.kt */
    @Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000e\n\u0002\b\u0007\b\u0083@\u0018\u0000 ,2\u00020\u0001:\u0001,B\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005B\t\b\u0016¢\u0006\u0004\b\u0004\u0010\u0006J\u0010\u0010\u0007\u001a\u00020\bH\u0086\b¢\u0006\u0004\b\t\u0010\nJ\u0010\u0010\u000b\u001a\u00020\fH\u0086\b¢\u0006\u0004\b\r\u0010\u000eJ+\u0010\u000f\u001a\u00020\u00102\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\f0\u0012H\u0086\b\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0000¢\u0006\u0004\b\u0013\u0010\u0014J\u0018\u0010\u0015\u001a\u00020\f2\u0006\u0010\u0016\u001a\u00020\u0010H\u0086\b¢\u0006\u0004\b\u0017\u0010\u0018J$\u0010\u0019\u001a\u00020\u00102\u0012\u0010\u001a\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00100\u001bH\u0082\b¢\u0006\u0004\b\u001c\u0010\u001dJ\u001f\u0010\u001e\u001a\u00020\u00102\u0006\u0010\u0016\u001a\u00020\u00102\u0006\u0010\u001f\u001a\u00020\u0010H\u0002¢\u0006\u0004\b \u0010!J\u000f\u0010%\u001a\u00020&H\u0016¢\u0006\u0004\b'\u0010(J\u0013\u0010)\u001a\u00020\b2\b\u0010*\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010+\u001a\u00020\u0010HÖ\u0001R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0019\u0010\u0016\u001a\u00020\u0010*\u00020\u00108Â\u0002X\u0082\u0004¢\u0006\u0006\u001a\u0004\b\"\u0010#R\u0019\u0010\u001f\u001a\u00020\u0010*\u00020\u00108Â\u0002X\u0082\u0004¢\u0006\u0006\u001a\u0004\b$\u0010#\u0088\u0001\u0002\u0092\u0001\u00020\u0003¨\u0006-"}, d2 = {"Landroidx/compose/runtime/BroadcastFrameClock$AtomicAwaitersCount;", "", ES6Iterator.VALUE_PROPERTY, "Landroidx/compose/runtime/internal/AtomicInt;", "constructor-impl", "(Landroidx/compose/runtime/internal/AtomicInt;)Landroidx/compose/runtime/internal/AtomicInt;", "()Landroidx/compose/runtime/internal/AtomicInt;", "hasAwaiters", "", "hasAwaiters-impl", "(Landroidx/compose/runtime/internal/AtomicInt;)Z", "incrementVersionAndResetCount", "", "incrementVersionAndResetCount-impl", "(Landroidx/compose/runtime/internal/AtomicInt;)V", "incrementCountAndGetVersion", "", "ifFirstAwaiter", "Lkotlin/Function0;", "incrementCountAndGetVersion-impl", "(Landroidx/compose/runtime/internal/AtomicInt;Lkotlin/jvm/functions/Function0;)I", "decrementCount", "version", "decrementCount-impl", "(Landroidx/compose/runtime/internal/AtomicInt;I)V", "update", "calculation", "Lkotlin/Function1;", "update-impl", "(Landroidx/compose/runtime/internal/AtomicInt;Lkotlin/jvm/functions/Function1;)I", "pack", PeertubeParsingHelper.COUNT_KEY, "pack-impl", "(Landroidx/compose/runtime/internal/AtomicInt;II)I", "getVersion-impl", "(Landroidx/compose/runtime/internal/AtomicInt;I)I", "getCount-impl", "toString", "", "toString-impl", "(Landroidx/compose/runtime/internal/AtomicInt;)Ljava/lang/String;", "equals", "other", "hashCode", "Companion", "runtime"}, k = 1, mv = {2, 0, 0}, xi = 48)
    @JvmInline
    private static final class AtomicAwaitersCount {
        private static final int COUNT_BITS = 27;
        private static final int VERSION_BITS = 4;
        private final AtomicInt value;

        /* JADX INFO: renamed from: box-impl, reason: not valid java name */
        public static final /* synthetic */ AtomicAwaitersCount m3368boximpl(AtomicInt atomicInt) {
            return new AtomicAwaitersCount(atomicInt);
        }

        /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
        private static AtomicInt m3370constructorimpl(AtomicInt atomicInt) {
            return atomicInt;
        }

        /* JADX INFO: renamed from: equals-impl, reason: not valid java name */
        public static boolean m3372equalsimpl(AtomicInt atomicInt, Object obj) {
            return (obj instanceof AtomicAwaitersCount) && Intrinsics.areEqual(atomicInt, ((AtomicAwaitersCount) obj).getValue());
        }

        /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
        public static final boolean m3373equalsimpl0(AtomicInt atomicInt, AtomicInt atomicInt2) {
            return Intrinsics.areEqual(atomicInt, atomicInt2);
        }

        /* JADX INFO: renamed from: getCount-impl, reason: not valid java name */
        private static final int m3374getCountimpl(AtomicInt atomicInt, int i) {
            return 134217727 & i;
        }

        /* JADX INFO: renamed from: getVersion-impl, reason: not valid java name */
        private static final int m3375getVersionimpl(AtomicInt atomicInt, int i) {
            return (i >>> 27) & 15;
        }

        /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
        public static int m3377hashCodeimpl(AtomicInt atomicInt) {
            return atomicInt.hashCode();
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: pack-impl, reason: not valid java name */
        public static final int m3380packimpl(AtomicInt atomicInt, int i, int i2) {
            return ((i & 15) << 27) | (134217727 & i2);
        }

        public boolean equals(Object other) {
            return m3372equalsimpl(this.value, other);
        }

        public int hashCode() {
            return m3377hashCodeimpl(this.value);
        }

        /* JADX INFO: renamed from: unbox-impl, reason: not valid java name and from getter */
        public final /* synthetic */ AtomicInt getValue() {
            return this.value;
        }

        private /* synthetic */ AtomicAwaitersCount(AtomicInt atomicInt) {
            this.value = atomicInt;
        }

        /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
        public static AtomicInt m3369constructorimpl() {
            return m3370constructorimpl(new AtomicInt(0));
        }

        /* JADX INFO: renamed from: hasAwaiters-impl, reason: not valid java name */
        public static final boolean m3376hasAwaitersimpl(AtomicInt atomicInt) {
            return (atomicInt.get() & 134217727) > 0;
        }

        /* JADX INFO: renamed from: update-impl, reason: not valid java name */
        private static final int m3382updateimpl(AtomicInt atomicInt, Function1<? super Integer, Integer> function1) {
            int i;
            int iIntValue;
            do {
                i = atomicInt.get();
                iIntValue = function1.invoke(Integer.valueOf(i)).intValue();
            } while (!atomicInt.compareAndSet(i, iIntValue));
            return iIntValue;
        }

        public String toString() {
            return m3381toStringimpl(this.value);
        }

        /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
        public static String m3381toStringimpl(AtomicInt atomicInt) {
            int i = atomicInt.get();
            return "AtomicAwaitersCount(version = " + ((i >>> 27) & 15) + ", count = " + (i & 134217727) + ')';
        }

        /* JADX INFO: renamed from: incrementVersionAndResetCount-impl, reason: not valid java name */
        public static final void m3379incrementVersionAndResetCountimpl(AtomicInt atomicInt) {
            int i;
            do {
                i = atomicInt.get();
            } while (!atomicInt.compareAndSet(i, m3380packimpl(atomicInt, ((i >>> 27) & 15) + 1, 0)));
        }

        /* JADX INFO: renamed from: incrementCountAndGetVersion-impl, reason: not valid java name */
        public static final int m3378incrementCountAndGetVersionimpl(AtomicInt atomicInt, Function0<Unit> function0) {
            int i;
            int i2;
            do {
                i = atomicInt.get();
                i2 = i + 1;
            } while (!atomicInt.compareAndSet(i, i2));
            if ((134217727 & i2) == 1) {
                function0.invoke();
            }
            return (i2 >>> 27) & 15;
        }

        /* JADX INFO: renamed from: decrementCount-impl, reason: not valid java name */
        public static final void m3371decrementCountimpl(AtomicInt atomicInt, int i) {
            int i2;
            do {
                i2 = atomicInt.get();
            } while (!atomicInt.compareAndSet(i2, ((i2 >>> 27) & 15) == i ? i2 - 1 : i2));
        }
    }

    @Override // androidx.compose.runtime.MonotonicFrameClock
    public <R> Object withFrameNanos(Function1<? super Long, ? extends R> function1, Continuation<? super R> continuation) {
        int i;
        int i2;
        boolean z = true;
        CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt.intercepted(continuation), 1);
        cancellableContinuationImpl.initCancellability();
        CancellableContinuationImpl cancellableContinuationImpl2 = cancellableContinuationImpl;
        final FrameAwaiter frameAwaiter = new FrameAwaiter(function1, cancellableContinuationImpl2);
        final Ref.IntRef intRef = new Ref.IntRef();
        intRef.element = -1;
        synchronized (this.lock) {
            Throwable th = this.failureCause;
            if (th == null) {
                AtomicInt atomicInt = this.pendingAwaitersCountUnlocked;
                do {
                    i = atomicInt.get();
                    i2 = i + 1;
                } while (!atomicInt.compareAndSet(i, i2));
                if ((134217727 & i2) != 1) {
                    z = false;
                }
                intRef.element = (i2 >>> 27) & 15;
                this.awaiters.add(frameAwaiter);
                cancellableContinuationImpl2.invokeOnCancellation(new Function1<Throwable, Unit>() { // from class: androidx.compose.runtime.BroadcastFrameClock$withFrameNanos$2$2
                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(Throwable th2) {
                        invoke2(th2);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(Throwable th2) {
                        int i3;
                        frameAwaiter.cancel();
                        AtomicInt atomicInt2 = this.pendingAwaitersCountUnlocked;
                        int i4 = intRef.element;
                        do {
                            i3 = atomicInt2.get();
                        } while (!atomicInt2.compareAndSet(i3, ((i3 >>> 27) & 15) == i4 ? i3 - 1 : i3));
                    }
                });
                if (z && this.onNewAwaiters != null) {
                    try {
                        this.onNewAwaiters.invoke();
                    } catch (Throwable th2) {
                        fail(th2);
                    }
                }
            } else {
                Result.Companion companion = Result.INSTANCE;
                cancellableContinuationImpl2.resumeWith(Result.m8936constructorimpl(ResultKt.createFailure(th)));
            }
        }
        Object result = cancellableContinuationImpl.getResult();
        if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return result;
    }
}
