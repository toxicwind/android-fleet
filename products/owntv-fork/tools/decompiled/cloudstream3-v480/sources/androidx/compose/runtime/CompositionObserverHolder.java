package androidx.compose.runtime;

import androidx.compose.runtime.tooling.CompositionObserver;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Composition.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\b\u0001\u0018\u00002\u00020\u0001B%\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\b\u0010\u0012\u001a\u0004\u0018\u00010\u0003R\u001c\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Landroidx/compose/runtime/CompositionObserverHolder;", "", "observer", "Landroidx/compose/runtime/tooling/CompositionObserver;", "root", "", "parent", "Landroidx/compose/runtime/CompositionContext;", "<init>", "(Landroidx/compose/runtime/tooling/CompositionObserver;ZLandroidx/compose/runtime/CompositionContext;)V", "getObserver", "()Landroidx/compose/runtime/tooling/CompositionObserver;", "setObserver", "(Landroidx/compose/runtime/tooling/CompositionObserver;)V", "getRoot", "()Z", "setRoot", "(Z)V", "current", "runtime"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class CompositionObserverHolder {
    public static final int $stable = 8;
    private CompositionObserver observer;
    private final CompositionContext parent;
    private boolean root;

    public CompositionObserverHolder(CompositionObserver compositionObserver, boolean z, CompositionContext compositionContext) {
        this.observer = compositionObserver;
        this.root = z;
        this.parent = compositionContext;
    }

    public /* synthetic */ CompositionObserverHolder(CompositionObserver compositionObserver, boolean z, CompositionContext compositionContext, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : compositionObserver, (i & 2) != 0 ? false : z, compositionContext);
    }

    public final CompositionObserver getObserver() {
        return this.observer;
    }

    public final void setObserver(CompositionObserver compositionObserver) {
        this.observer = compositionObserver;
    }

    public final boolean getRoot() {
        return this.root;
    }

    public final void setRoot(boolean z) {
        this.root = z;
    }

    public final CompositionObserver current() {
        if (this.root) {
            return this.observer;
        }
        CompositionObserverHolder observerHolder$runtime = this.parent.getObserverHolder$runtime();
        CompositionObserver compositionObserver = observerHolder$runtime != null ? observerHolder$runtime.observer : null;
        if (!Intrinsics.areEqual(compositionObserver, this.observer)) {
            this.observer = compositionObserver;
        }
        return compositionObserver;
    }
}
