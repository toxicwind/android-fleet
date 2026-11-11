package androidx.navigation.internal;

import java.util.concurrent.atomic.AtomicInteger;
import kotlin.Metadata;

/* JADX INFO: compiled from: AtomicInt.android.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\r\u0010\b\u001a\u00020\u0003H\u0000¢\u0006\u0002\b\tJ\r\u0010\n\u001a\u00020\u0003H\u0000¢\u0006\u0002\b\u000bJ\r\u0010\f\u001a\u00020\u0003H\u0000¢\u0006\u0002\b\rR\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Landroidx/navigation/internal/AtomicInt;", "", "initial", "", "<init>", "(I)V", "atomicInt", "Ljava/util/concurrent/atomic/AtomicInteger;", "incrementAndGet", "incrementAndGet$navigation_runtime_release", "decrementAndGet", "decrementAndGet$navigation_runtime_release", "get", "get$navigation_runtime_release", "navigation-runtime_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class AtomicInt {
    private final AtomicInteger atomicInt;

    public AtomicInt(int i) {
        this.atomicInt = new AtomicInteger(i);
    }

    public final int incrementAndGet$navigation_runtime_release() {
        return this.atomicInt.incrementAndGet();
    }

    public final int decrementAndGet$navigation_runtime_release() {
        return this.atomicInt.decrementAndGet();
    }

    public final int get$navigation_runtime_release() {
        return this.atomicInt.get();
    }
}
