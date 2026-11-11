package androidx.lifecycle.compose;

import androidx.core.app.NotificationCompat;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;
import kotlin.Metadata;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: RememberLifecycleOwner.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014J\b\u0010\u0015\u001a\u00020\u0012H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\u00020\u00058VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R$\u0010\f\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\n@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010¨\u0006\u0016"}, d2 = {"Landroidx/lifecycle/compose/ComposeLifecycleOwner;", "Landroidx/lifecycle/LifecycleOwner;", "<init>", "()V", "lifecycleRegistry", "Landroidx/lifecycle/LifecycleRegistry;", "lifecycle", "getLifecycle", "()Landroidx/lifecycle/LifecycleRegistry;", "parentLifecycleState", "Landroidx/lifecycle/Lifecycle$State;", ES6Iterator.VALUE_PROPERTY, "maxLifecycleState", "getMaxLifecycleState", "()Landroidx/lifecycle/Lifecycle$State;", "setMaxLifecycleState", "(Landroidx/lifecycle/Lifecycle$State;)V", "handleLifecycleEvent", "", NotificationCompat.CATEGORY_EVENT, "Landroidx/lifecycle/Lifecycle$Event;", "updateLifecycleState", "lifecycle-runtime-compose"}, k = 1, mv = {2, 0, 0}, xi = 48)
final class ComposeLifecycleOwner implements LifecycleOwner {
    private final LifecycleRegistry lifecycleRegistry = new LifecycleRegistry(this);
    private Lifecycle.State parentLifecycleState = Lifecycle.State.INITIALIZED;
    private Lifecycle.State maxLifecycleState = Lifecycle.State.INITIALIZED;

    @Override // androidx.lifecycle.LifecycleOwner
    /* JADX INFO: renamed from: getLifecycle, reason: from getter */
    public LifecycleRegistry getLifecycleRegistry() {
        return this.lifecycleRegistry;
    }

    public final Lifecycle.State getMaxLifecycleState() {
        return this.maxLifecycleState;
    }

    public final void setMaxLifecycleState(Lifecycle.State state) {
        this.maxLifecycleState = state;
        updateLifecycleState();
    }

    public final void handleLifecycleEvent(Lifecycle.Event event) {
        this.parentLifecycleState = event.getTargetState();
        updateLifecycleState();
    }

    private final void updateLifecycleState() {
        Lifecycle.State state;
        if (this.parentLifecycleState.ordinal() < this.maxLifecycleState.ordinal()) {
            state = this.parentLifecycleState;
        } else {
            state = this.maxLifecycleState;
        }
        if (this.lifecycleRegistry.getState() == Lifecycle.State.INITIALIZED && state == Lifecycle.State.DESTROYED) {
            return;
        }
        this.lifecycleRegistry.setCurrentState(state);
    }
}
