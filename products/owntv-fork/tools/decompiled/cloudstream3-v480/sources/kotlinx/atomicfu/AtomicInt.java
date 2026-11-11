package kotlinx.atomicfu;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KProperty;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: AtomicFU.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\r\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u0000 %2\u00020\u0001:\u0001%B\u0019\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u001f\u0010\f\u001a\u00020\u00032\b\u0010\u0010\u001a\u0004\u0018\u00010\u00012\n\u0010\u0011\u001a\u0006\u0012\u0002\b\u00030\u0012H\u0087\nJ'\u0010\u000e\u001a\u00020\u00132\b\u0010\u0010\u001a\u0004\u0018\u00010\u00012\n\u0010\u0011\u001a\u0006\u0012\u0002\b\u00030\u00122\u0006\u0010\u0002\u001a\u00020\u0003H\u0087\nJ\u000e\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0002\u001a\u00020\u0003J\u0016\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00032\u0006\u0010\u0018\u001a\u00020\u0003J\u000e\u0010\u0019\u001a\u00020\u00032\u0006\u0010\u0002\u001a\u00020\u0003J\u0006\u0010\u001a\u001a\u00020\u0003J\u0006\u0010\u001b\u001a\u00020\u0003J\u000e\u0010\u001c\u001a\u00020\u00032\u0006\u0010\u001d\u001a\u00020\u0003J\u000e\u0010\u001e\u001a\u00020\u00032\u0006\u0010\u001d\u001a\u00020\u0003J\u0006\u0010\u001f\u001a\u00020\u0003J\u0006\u0010 \u001a\u00020\u0003J\u0011\u0010!\u001a\u00020\u00132\u0006\u0010\u001d\u001a\u00020\u0003H\u0086\nJ\u0011\u0010\"\u001a\u00020\u00132\u0006\u0010\u001d\u001a\u00020\u0003H\u0086\nJ\b\u0010#\u001a\u00020$H\u0016R\u001c\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\b\u0010\t\u001a\u0004\b\n\u0010\u000bR$\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0002\u001a\u00020\u0003@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000f¨\u0006&"}, d2 = {"Lkotlinx/atomicfu/AtomicInt;", "", ES6Iterator.VALUE_PROPERTY, "", "trace", "Lkotlinx/atomicfu/TraceBase;", "<init>", "(ILkotlinx/atomicfu/TraceBase;)V", "getTrace$annotations", "()V", "getTrace", "()Lkotlinx/atomicfu/TraceBase;", "getValue", "()I", "setValue", "(I)V", "thisRef", "property", "Lkotlin/reflect/KProperty;", "", "lazySet", "compareAndSet", "", "expect", "update", "getAndSet", "getAndIncrement", "getAndDecrement", "getAndAdd", "delta", "addAndGet", "incrementAndGet", "decrementAndGet", "plusAssign", "minusAssign", "toString", "", "Companion", "atomicfu"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class AtomicInt {
    private static final Companion Companion = new Companion(null);
    private static final AtomicIntegerFieldUpdater<AtomicInt> FU = AtomicIntegerFieldUpdater.newUpdater(AtomicInt.class, ES6Iterator.VALUE_PROPERTY);
    private final TraceBase trace;
    private volatile int value;

    @Deprecated(message = "The property was intended for internal use only and will be hidden in the future release.")
    public static /* synthetic */ void getTrace$annotations() {
    }

    public AtomicInt(int i, TraceBase trace) {
        Intrinsics.checkNotNullParameter(trace, "trace");
        this.trace = trace;
        this.value = i;
    }

    public final TraceBase getTrace() {
        return this.trace;
    }

    public final int getValue() {
        return this.value;
    }

    public final void setValue(int i) {
        this.value = i;
        if (this.trace != TraceBase.None.INSTANCE) {
            this.trace.append("set(" + i + ')');
        }
    }

    private final int getValue(Object thisRef, KProperty<?> property) {
        Intrinsics.checkNotNullParameter(property, "property");
        return getValue();
    }

    private final void setValue(Object thisRef, KProperty<?> property, int value) {
        Intrinsics.checkNotNullParameter(property, "property");
        setValue(value);
    }

    public final void lazySet(int value) {
        FU.lazySet(this, value);
        if (this.trace != TraceBase.None.INSTANCE) {
            this.trace.append("lazySet(" + value + ')');
        }
    }

    public final boolean compareAndSet(int expect, int update) {
        boolean zCompareAndSet = FU.compareAndSet(this, expect, update);
        if (zCompareAndSet && this.trace != TraceBase.None.INSTANCE) {
            this.trace.append("CAS(" + expect + ", " + update + ')');
        }
        return zCompareAndSet;
    }

    public final int getAndSet(int value) {
        int andSet = FU.getAndSet(this, value);
        if (this.trace != TraceBase.None.INSTANCE) {
            this.trace.append("getAndSet(" + value + "):" + andSet);
        }
        return andSet;
    }

    public final int getAndIncrement() {
        int andIncrement = FU.getAndIncrement(this);
        if (this.trace != TraceBase.None.INSTANCE) {
            this.trace.append("getAndInc():" + andIncrement);
        }
        return andIncrement;
    }

    public final int getAndDecrement() {
        int andDecrement = FU.getAndDecrement(this);
        if (this.trace != TraceBase.None.INSTANCE) {
            this.trace.append("getAndDec():" + andDecrement);
        }
        return andDecrement;
    }

    public final int getAndAdd(int delta) {
        int andAdd = FU.getAndAdd(this, delta);
        if (this.trace != TraceBase.None.INSTANCE) {
            this.trace.append("getAndAdd(" + delta + "):" + andAdd);
        }
        return andAdd;
    }

    public final int addAndGet(int delta) {
        int iAddAndGet = FU.addAndGet(this, delta);
        if (this.trace != TraceBase.None.INSTANCE) {
            this.trace.append("addAndGet(" + delta + "):" + iAddAndGet);
        }
        return iAddAndGet;
    }

    public final int incrementAndGet() {
        int iIncrementAndGet = FU.incrementAndGet(this);
        if (this.trace != TraceBase.None.INSTANCE) {
            this.trace.append("incAndGet():" + iIncrementAndGet);
        }
        return iIncrementAndGet;
    }

    public final int decrementAndGet() {
        int iDecrementAndGet = FU.decrementAndGet(this);
        if (this.trace != TraceBase.None.INSTANCE) {
            this.trace.append("decAndGet():" + iDecrementAndGet);
        }
        return iDecrementAndGet;
    }

    public final void plusAssign(int delta) {
        getAndAdd(delta);
    }

    public final void minusAssign(int delta) {
        getAndAdd(-delta);
    }

    public String toString() {
        return String.valueOf(this.value);
    }

    /* JADX INFO: compiled from: AtomicFU.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R2\u0010\u0004\u001a&\u0012\f\u0012\n \u0007*\u0004\u0018\u00010\u00060\u0006 \u0007*\u0012\u0012\f\u0012\n \u0007*\u0004\u0018\u00010\u00060\u0006\u0018\u00010\u00050\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lkotlinx/atomicfu/AtomicInt$Companion;", "", "<init>", "()V", "FU", "Ljava/util/concurrent/atomic/AtomicIntegerFieldUpdater;", "Lkotlinx/atomicfu/AtomicInt;", "kotlin.jvm.PlatformType", "atomicfu"}, k = 1, mv = {2, 2, 0}, xi = 48)
    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
