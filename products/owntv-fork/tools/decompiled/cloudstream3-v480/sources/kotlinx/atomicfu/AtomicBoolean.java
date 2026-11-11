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
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\t\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u0000 \u001e2\u00020\u0001:\u0001\u001eB\u0019\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u001f\u0010\u000e\u001a\u00020\u00032\b\u0010\u000f\u001a\u0004\u0018\u00010\u00012\n\u0010\u0010\u001a\u0006\u0012\u0002\b\u00030\u0011H\u0087\nJ'\u0010\u0012\u001a\u00020\u00132\b\u0010\u000f\u001a\u0004\u0018\u00010\u00012\n\u0010\u0010\u001a\u0006\u0012\u0002\b\u00030\u00112\u0006\u0010\u0014\u001a\u00020\u0003H\u0087\nJ\u000e\u0010\u0017\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0003J\u0016\u0010\u0018\u001a\u00020\u00032\u0006\u0010\u0019\u001a\u00020\u00032\u0006\u0010\u001a\u001a\u00020\u0003J\u000e\u0010\u001b\u001a\u00020\u00032\u0006\u0010\u0014\u001a\u00020\u0003J\b\u0010\u001c\u001a\u00020\u001dH\u0016R\u001c\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\b\u0010\t\u001a\u0004\b\n\u0010\u000bR\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R$\u0010\u0014\u001a\u00020\u00032\u0006\u0010\u0014\u001a\u00020\u00038F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u000e\u0010\u0015\"\u0004\b\u0012\u0010\u0016¨\u0006\u001f"}, d2 = {"Lkotlinx/atomicfu/AtomicBoolean;", "", "v", "", "trace", "Lkotlinx/atomicfu/TraceBase;", "<init>", "(ZLkotlinx/atomicfu/TraceBase;)V", "getTrace$annotations", "()V", "getTrace", "()Lkotlinx/atomicfu/TraceBase;", "_value", "", "getValue", "thisRef", "property", "Lkotlin/reflect/KProperty;", "setValue", "", ES6Iterator.VALUE_PROPERTY, "()Z", "(Z)V", "lazySet", "compareAndSet", "expect", "update", "getAndSet", "toString", "", "Companion", "atomicfu"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class AtomicBoolean {
    private static final Companion Companion = new Companion(null);
    private static final AtomicIntegerFieldUpdater<AtomicBoolean> FU = AtomicIntegerFieldUpdater.newUpdater(AtomicBoolean.class, "_value");
    private volatile int _value;
    private final TraceBase trace;

    @Deprecated(message = "The property was intended for internal use only and will be hidden in the future release.")
    public static /* synthetic */ void getTrace$annotations() {
    }

    public AtomicBoolean(boolean z, TraceBase trace) {
        Intrinsics.checkNotNullParameter(trace, "trace");
        this.trace = trace;
        this._value = z ? 1 : 0;
    }

    public final TraceBase getTrace() {
        return this.trace;
    }

    private final boolean getValue(Object thisRef, KProperty<?> property) {
        Intrinsics.checkNotNullParameter(property, "property");
        return getValue();
    }

    private final void setValue(Object thisRef, KProperty<?> property, boolean value) {
        Intrinsics.checkNotNullParameter(property, "property");
        setValue(value);
    }

    public final boolean getValue() {
        return this._value != 0;
    }

    public final void setValue(boolean z) {
        this._value = z ? 1 : 0;
        if (this.trace != TraceBase.None.INSTANCE) {
            this.trace.append("set(" + z + ')');
        }
    }

    public final void lazySet(boolean value) {
        FU.lazySet(this, value ? 1 : 0);
        if (this.trace != TraceBase.None.INSTANCE) {
            this.trace.append("lazySet(" + value + ')');
        }
    }

    public final boolean compareAndSet(boolean expect, boolean update) {
        boolean zCompareAndSet = FU.compareAndSet(this, expect ? 1 : 0, update ? 1 : 0);
        if (zCompareAndSet && this.trace != TraceBase.None.INSTANCE) {
            this.trace.append("CAS(" + expect + ", " + update + ')');
        }
        return zCompareAndSet;
    }

    public final boolean getAndSet(boolean value) {
        int andSet = FU.getAndSet(this, value ? 1 : 0);
        if (this.trace != TraceBase.None.INSTANCE) {
            this.trace.append("getAndSet(" + value + "):" + andSet);
        }
        return andSet == 1;
    }

    public String toString() {
        return String.valueOf(getValue());
    }

    /* JADX INFO: compiled from: AtomicFU.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R2\u0010\u0004\u001a&\u0012\f\u0012\n \u0007*\u0004\u0018\u00010\u00060\u0006 \u0007*\u0012\u0012\f\u0012\n \u0007*\u0004\u0018\u00010\u00060\u0006\u0018\u00010\u00050\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lkotlinx/atomicfu/AtomicBoolean$Companion;", "", "<init>", "()V", "FU", "Ljava/util/concurrent/atomic/AtomicIntegerFieldUpdater;", "Lkotlinx/atomicfu/AtomicBoolean;", "kotlin.jvm.PlatformType", "atomicfu"}, k = 1, mv = {2, 2, 0}, xi = 48)
    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
