package androidx.compose.runtime;

import androidx.collection.ScatterSet;
import androidx.collection.ScatterSetKt;
import androidx.compose.animation.core.MutatorMutex$$ExternalSyntheticBackportWithForwarding0;
import androidx.compose.runtime.internal.RememberEventDispatcher;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PausableComposition.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000~\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\n\b\u0001\u0018\u00002\u00020\u0001B`\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t\u0012\u0011\u0010\u000b\u001a\r\u0012\u0004\u0012\u00020\r0\f¢\u0006\u0002\b\u000e\u0012\u0006\u0010\u000f\u001a\u00020\u0010\u0012\n\u0010\u0011\u001a\u0006\u0012\u0002\b\u00030\u0012\u0012\n\u0010\u0013\u001a\u00060\u0014j\u0002`\u0015¢\u0006\u0004\b\u0016\u0010\u0017J\u0010\u0010=\u001a\u00020\u00102\u0006\u0010>\u001a\u00020?H\u0016J\b\u0010@\u001a\u00020\rH\u0016J\b\u0010A\u001a\u00020\rH\u0016J\r\u0010B\u001a\u00020\rH\u0000¢\u0006\u0002\bCJ\b\u0010D\u001a\u00020\rH\u0002J\b\u0010E\u001a\u00020\rH\u0002J\u0019\u0010F\u001a\u00020\r2\u0006\u0010G\u001a\u00020*2\u0006\u0010H\u001a\u00020*H\u0082\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u001e\u0010\u000b\u001a\r\u0012\u0004\u0012\u00020\r0\f¢\u0006\u0002\b\u000e¢\u0006\n\n\u0002\u0010 \u001a\u0004\b\u001e\u0010\u001fR\u0011\u0010\u000f\u001a\u00020\u0010¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R\u0015\u0010\u0011\u001a\u0006\u0012\u0002\b\u00030\u0012¢\u0006\b\n\u0000\u001a\u0004\b#\u0010$R\u0017\u0010\u0013\u001a\u00060\u0014j\u0002`\u0015¢\u0006\n\n\u0002\u0010'\u001a\u0004\b%\u0010&R \u0010(\u001a\u0012\u0012\u0004\u0012\u00020*0)j\b\u0012\u0004\u0012\u00020*`+X\u0082\u000e¢\u0006\u0004\n\u0002\u0010,R\u0014\u0010-\u001a\b\u0012\u0004\u0012\u00020/0.X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u00100\u001a\u000201X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b2\u00103R\u001c\u00104\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001405X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b6\u00107R\u0014\u00108\u001a\u00020\u00108@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b9\u0010\"R\u0014\u0010:\u001a\u00020\u00108VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b:\u0010\"R\u0014\u0010;\u001a\u00020\u00108VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b;\u0010\"R\u0014\u0010<\u001a\u00020\u00108VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b<\u0010\"¨\u0006I"}, d2 = {"Landroidx/compose/runtime/PausedCompositionImpl;", "Landroidx/compose/runtime/PausedComposition;", "composition", "Landroidx/compose/runtime/CompositionImpl;", "context", "Landroidx/compose/runtime/CompositionContext;", "composer", "Landroidx/compose/runtime/ComposerImpl;", "abandonSet", "", "Landroidx/compose/runtime/RememberObserver;", "content", "Lkotlin/Function0;", "", "Landroidx/compose/runtime/Composable;", "reusable", "", "applier", "Landroidx/compose/runtime/Applier;", "lock", "", "Landroidx/compose/runtime/platform/SynchronizedObject;", "<init>", "(Landroidx/compose/runtime/CompositionImpl;Landroidx/compose/runtime/CompositionContext;Landroidx/compose/runtime/ComposerImpl;Ljava/util/Set;Lkotlin/jvm/functions/Function2;ZLandroidx/compose/runtime/Applier;Ljava/lang/Object;)V", "getComposition", "()Landroidx/compose/runtime/CompositionImpl;", "getContext", "()Landroidx/compose/runtime/CompositionContext;", "getComposer", "()Landroidx/compose/runtime/ComposerImpl;", "getContent", "()Lkotlin/jvm/functions/Function2;", "Lkotlin/jvm/functions/Function2;", "getReusable", "()Z", "getApplier", "()Landroidx/compose/runtime/Applier;", "getLock", "()Ljava/lang/Object;", "Ljava/lang/Object;", "state", "Ljava/util/concurrent/atomic/AtomicReference;", "Landroidx/compose/runtime/PausedCompositionState;", "Landroidx/compose/runtime/internal/AtomicReference;", "Ljava/util/concurrent/atomic/AtomicReference;", "invalidScopes", "Landroidx/collection/ScatterSet;", "Landroidx/compose/runtime/RecomposeScopeImpl;", "rememberManager", "Landroidx/compose/runtime/internal/RememberEventDispatcher;", "getRememberManager$runtime", "()Landroidx/compose/runtime/internal/RememberEventDispatcher;", "pausableApplier", "Landroidx/compose/runtime/RecordingApplier;", "getPausableApplier$runtime", "()Landroidx/compose/runtime/RecordingApplier;", "isRecomposing", "isRecomposing$runtime", "isComplete", "isApplied", "isCancelled", "resume", "shouldPause", "Landroidx/compose/runtime/ShouldPauseCallback;", "apply", "cancel", "markIncomplete", "markIncomplete$runtime", "markComplete", "applyChanges", "updateState", TypedValues.TransitionType.S_FROM, TypedValues.TransitionType.S_TO, "runtime"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class PausedCompositionImpl implements PausedComposition {
    public static final int $stable = 8;
    private final Applier<?> applier;
    private final ComposerImpl composer;
    private final CompositionImpl composition;
    private final Function2<Composer, Integer, Unit> content;
    private final CompositionContext context;
    private final Object lock;
    private final RecordingApplier<Object> pausableApplier;
    private final RememberEventDispatcher rememberManager;
    private final boolean reusable;
    private AtomicReference<PausedCompositionState> state = new AtomicReference<>(PausedCompositionState.InitialPending);
    private ScatterSet<RecomposeScopeImpl> invalidScopes = ScatterSetKt.emptyScatterSet();

    /* JADX INFO: compiled from: PausableComposition.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[PausedCompositionState.values().length];
            try {
                iArr[PausedCompositionState.InitialPending.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[PausedCompositionState.RecomposePending.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[PausedCompositionState.Recomposing.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[PausedCompositionState.ApplyPending.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[PausedCompositionState.Applied.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr[PausedCompositionState.Cancelled.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                iArr[PausedCompositionState.Invalid.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public PausedCompositionImpl(CompositionImpl compositionImpl, CompositionContext compositionContext, ComposerImpl composerImpl, Set<RememberObserver> set, Function2<? super Composer, ? super Integer, Unit> function2, boolean z, Applier<?> applier, Object obj) {
        this.composition = compositionImpl;
        this.context = compositionContext;
        this.composer = composerImpl;
        this.content = function2;
        this.reusable = z;
        this.applier = applier;
        this.lock = obj;
        RememberEventDispatcher rememberEventDispatcher = new RememberEventDispatcher();
        rememberEventDispatcher.prepare(set, composerImpl.getErrorContext$runtime());
        this.rememberManager = rememberEventDispatcher;
        this.pausableApplier = new RecordingApplier<>(applier.getCurrent());
    }

    public final CompositionImpl getComposition() {
        return this.composition;
    }

    public final CompositionContext getContext() {
        return this.context;
    }

    public final ComposerImpl getComposer() {
        return this.composer;
    }

    public final Function2<Composer, Integer, Unit> getContent() {
        return this.content;
    }

    public final boolean getReusable() {
        return this.reusable;
    }

    public final Applier<?> getApplier() {
        return this.applier;
    }

    public final Object getLock() {
        return this.lock;
    }

    /* JADX INFO: renamed from: getRememberManager$runtime, reason: from getter */
    public final RememberEventDispatcher getRememberManager() {
        return this.rememberManager;
    }

    public final RecordingApplier<Object> getPausableApplier$runtime() {
        return this.pausableApplier;
    }

    public final boolean isRecomposing$runtime() {
        return this.state.get() == PausedCompositionState.Recomposing;
    }

    @Override // androidx.compose.runtime.PausedComposition
    public boolean isComplete() {
        return this.state.get().compareTo(PausedCompositionState.ApplyPending) >= 0;
    }

    @Override // androidx.compose.runtime.PausedComposition
    public boolean isApplied() {
        return this.state.get() == PausedCompositionState.Applied;
    }

    @Override // androidx.compose.runtime.PausedComposition
    public boolean isCancelled() {
        return this.state.get() == PausedCompositionState.Cancelled;
    }

    @Override // androidx.compose.runtime.PausedComposition
    public boolean resume(ShouldPauseCallback shouldPause) throws Exception {
        try {
            switch (WhenMappings.$EnumSwitchMapping$0[this.state.get().ordinal()]) {
                case 1:
                    if (this.reusable) {
                        this.composer.startReuseFromRoot();
                    }
                    try {
                        this.invalidScopes = this.context.composeInitialPaused$runtime(this.composition, shouldPause, this.content);
                        if (this.reusable) {
                            this.composer.endReuseFromRoot();
                        }
                        PausedCompositionState pausedCompositionState = PausedCompositionState.InitialPending;
                        PausedCompositionState pausedCompositionState2 = PausedCompositionState.RecomposePending;
                        if (!MutatorMutex$$ExternalSyntheticBackportWithForwarding0.m(this.state, pausedCompositionState, pausedCompositionState2)) {
                            PreconditionsKt.throwIllegalStateException("Unexpected state change from: " + pausedCompositionState + " to: " + pausedCompositionState2 + '.');
                        }
                        if (this.invalidScopes.isEmpty()) {
                            markComplete();
                        }
                        return isComplete();
                    } catch (Throwable th) {
                        if (this.reusable) {
                            this.composer.endReuseFromRoot();
                        }
                        throw th;
                    }
                case 2:
                    PausedCompositionState pausedCompositionState3 = PausedCompositionState.RecomposePending;
                    PausedCompositionState pausedCompositionState4 = PausedCompositionState.Recomposing;
                    if (!MutatorMutex$$ExternalSyntheticBackportWithForwarding0.m(this.state, pausedCompositionState3, pausedCompositionState4)) {
                        PreconditionsKt.throwIllegalStateException("Unexpected state change from: " + pausedCompositionState3 + " to: " + pausedCompositionState4 + '.');
                    }
                    try {
                        this.invalidScopes = this.context.recomposePaused$runtime(this.composition, shouldPause, this.invalidScopes);
                        PausedCompositionState pausedCompositionState5 = PausedCompositionState.Recomposing;
                        PausedCompositionState pausedCompositionState6 = PausedCompositionState.RecomposePending;
                        if (!MutatorMutex$$ExternalSyntheticBackportWithForwarding0.m(this.state, pausedCompositionState5, pausedCompositionState6)) {
                            PreconditionsKt.throwIllegalStateException("Unexpected state change from: " + pausedCompositionState5 + " to: " + pausedCompositionState6 + '.');
                        }
                        if (this.invalidScopes.isEmpty()) {
                            markComplete();
                        }
                        return isComplete();
                    } catch (Throwable th2) {
                        PausedCompositionState pausedCompositionState7 = PausedCompositionState.Recomposing;
                        PausedCompositionState pausedCompositionState8 = PausedCompositionState.RecomposePending;
                        if (!MutatorMutex$$ExternalSyntheticBackportWithForwarding0.m(this.state, pausedCompositionState7, pausedCompositionState8)) {
                            PreconditionsKt.throwIllegalStateException("Unexpected state change from: " + pausedCompositionState7 + " to: " + pausedCompositionState8 + '.');
                        }
                        throw th2;
                    }
                case 3:
                    ComposerKt.composeRuntimeError("Recursive call to resume()");
                    throw new KotlinNothingValueException();
                case 4:
                    throw new IllegalStateException("Pausable composition is complete and apply() should be applied".toString());
                case 5:
                    throw new IllegalStateException("The paused composition has been applied".toString());
                case 6:
                    throw new IllegalStateException("The paused composition has been cancelled".toString());
                case 7:
                    throw new IllegalStateException("The paused composition is invalid because of a previous exception".toString());
                default:
                    throw new NoWhenBranchMatchedException();
            }
        } catch (Exception e) {
            this.state.set(PausedCompositionState.Invalid);
            throw e;
        }
    }

    @Override // androidx.compose.runtime.PausedComposition
    public void apply() throws Exception {
        try {
            switch (WhenMappings.$EnumSwitchMapping$0[this.state.get().ordinal()]) {
                case 1:
                case 2:
                case 3:
                    throw new IllegalStateException("The paused composition has not completed yet".toString());
                case 4:
                    applyChanges();
                    PausedCompositionState pausedCompositionState = PausedCompositionState.ApplyPending;
                    PausedCompositionState pausedCompositionState2 = PausedCompositionState.Applied;
                    if (MutatorMutex$$ExternalSyntheticBackportWithForwarding0.m(this.state, pausedCompositionState, pausedCompositionState2)) {
                        return;
                    }
                    PreconditionsKt.throwIllegalStateException("Unexpected state change from: " + pausedCompositionState + " to: " + pausedCompositionState2 + '.');
                    return;
                case 5:
                    throw new IllegalStateException("The paused composition has already been applied".toString());
                case 6:
                    throw new IllegalStateException("The paused composition has been cancelled".toString());
                case 7:
                    throw new IllegalStateException("The paused composition is invalid because of a previous exception".toString());
                default:
                    throw new NoWhenBranchMatchedException();
            }
        } catch (Exception e) {
            this.state.set(PausedCompositionState.Invalid);
            throw e;
        }
    }

    @Override // androidx.compose.runtime.PausedComposition
    public void cancel() {
        this.state.set(PausedCompositionState.Cancelled);
        ScatterSet<RememberObserverHolder> scatterSetExtractRememberSet = this.rememberManager.extractRememberSet();
        this.rememberManager.dispatchAbandons();
        this.composition.pausedCompositionFinished$runtime(scatterSetExtractRememberSet);
    }

    public final void markIncomplete$runtime() {
        if (this.state.get() == PausedCompositionState.RecomposePending) {
            return;
        }
        PausedCompositionState pausedCompositionState = PausedCompositionState.ApplyPending;
        PausedCompositionState pausedCompositionState2 = PausedCompositionState.RecomposePending;
        if (MutatorMutex$$ExternalSyntheticBackportWithForwarding0.m(this.state, pausedCompositionState, pausedCompositionState2)) {
            return;
        }
        PreconditionsKt.throwIllegalStateException("Unexpected state change from: " + pausedCompositionState + " to: " + pausedCompositionState2 + '.');
    }

    private final void markComplete() {
        PausedCompositionState pausedCompositionState = PausedCompositionState.RecomposePending;
        PausedCompositionState pausedCompositionState2 = PausedCompositionState.ApplyPending;
        if (MutatorMutex$$ExternalSyntheticBackportWithForwarding0.m(this.state, pausedCompositionState, pausedCompositionState2)) {
            return;
        }
        PreconditionsKt.throwIllegalStateException("Unexpected state change from: " + pausedCompositionState + " to: " + pausedCompositionState2 + '.');
    }

    private final void applyChanges() {
        synchronized (this.lock) {
            try {
                RecordingApplier<Object> recordingApplier = this.pausableApplier;
                Applier<?> applier = this.applier;
                Intrinsics.checkNotNull(applier, "null cannot be cast to non-null type androidx.compose.runtime.Applier<kotlin.Any?>");
                recordingApplier.playTo(applier, this.rememberManager);
                this.rememberManager.dispatchRememberObservers();
                this.rememberManager.dispatchSideEffects();
                this.rememberManager.dispatchAbandons();
                this.composition.pausedCompositionFinished$runtime(null);
                Unit unit = Unit.INSTANCE;
            } catch (Throwable th) {
                this.rememberManager.dispatchAbandons();
                this.composition.pausedCompositionFinished$runtime(null);
                throw th;
            }
        }
    }

    private final void updateState(PausedCompositionState from, PausedCompositionState to) {
        if (MutatorMutex$$ExternalSyntheticBackportWithForwarding0.m(this.state, from, to)) {
            return;
        }
        PreconditionsKt.throwIllegalStateException("Unexpected state change from: " + from + " to: " + to + '.');
    }
}
