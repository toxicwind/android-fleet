package androidx.compose.ui.layout;

import androidx.compose.ui.Modifier;
import androidx.compose.ui.node.DelegatableNode;
import androidx.compose.ui.node.ObserverModifierNode;
import androidx.compose.ui.node.ObserverModifierNodeKt;
import androidx.compose.ui.spatial.RelativeLayoutBounds;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.Job;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: OnFirstVisibleModifier.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0014\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u000f\b\u0002\u0018\u00002\u00020\u00012\u00020\u0002B/\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n¢\u0006\u0004\b\f\u0010\rJ \u0010>\u001a\u00020\u000b2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010?\u001a\u0002022\b\u0010@\u001a\u0004\u0018\u000102J\u0006\u0010A\u001a\u00020\u000bJ\u0006\u0010B\u001a\u00020\u000bJ\u0006\u0010C\u001a\u00020\u000bJ\u0006\u0010D\u001a\u00020\u000bJ\u0006\u0010E\u001a\u00020\u000bJ\b\u0010F\u001a\u00020\u000bH\u0016J\b\u0010G\u001a\u00020\u000bH\u0016J\b\u0010H\u001a\u00020\u000bH\u0016J\b\u0010I\u001a\u00020\u000bH\u0016R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R \u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R(\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\u0010\u001a\u001a\u0004\u0018\u00010\b@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR\u001c\u0010\u001f\u001a\u0004\u0018\u00010 X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$R\u001c\u0010%\u001a\u0004\u0018\u00010&X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b'\u0010(\"\u0004\b)\u0010*R\u001a\u0010+\u001a\u00020,X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b-\u0010.\"\u0004\b/\u00100R\u001c\u00101\u001a\u0004\u0018\u000102X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b3\u00104\"\u0004\b5\u00106R(\u00107\u001a\u0004\u0018\u0001022\b\u0010\u001a\u001a\u0004\u0018\u000102@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b8\u00104\"\u0004\b9\u00106R\u001d\u0010:\u001a\u000e\u0012\u0004\u0012\u000202\u0012\u0004\u0012\u00020\u000b0;¢\u0006\b\n\u0000\u001a\u0004\b<\u0010=¨\u0006J"}, d2 = {"Landroidx/compose/ui/layout/OnFirstVisibleNode;", "Landroidx/compose/ui/Modifier$Node;", "Landroidx/compose/ui/node/ObserverModifierNode;", "minDurationMs", "", "minFractionVisible", "", "viewportBounds", "Landroidx/compose/ui/layout/LayoutBoundsHolder;", "callback", "Lkotlin/Function0;", "", "<init>", "(JFLandroidx/compose/ui/layout/LayoutBoundsHolder;Lkotlin/jvm/functions/Function0;)V", "getMinDurationMs", "()J", "setMinDurationMs", "(J)V", "getMinFractionVisible", "()F", "setMinFractionVisible", "(F)V", "getCallback", "()Lkotlin/jvm/functions/Function0;", "setCallback", "(Lkotlin/jvm/functions/Function0;)V", ES6Iterator.VALUE_PROPERTY, "getViewportBounds", "()Landroidx/compose/ui/layout/LayoutBoundsHolder;", "setViewportBounds", "(Landroidx/compose/ui/layout/LayoutBoundsHolder;)V", "handle", "Landroidx/compose/ui/node/DelegatableNode$RegistrationHandle;", "getHandle", "()Landroidx/compose/ui/node/DelegatableNode$RegistrationHandle;", "setHandle", "(Landroidx/compose/ui/node/DelegatableNode$RegistrationHandle;)V", "job", "Lkotlinx/coroutines/Job;", "getJob", "()Lkotlinx/coroutines/Job;", "setJob", "(Lkotlinx/coroutines/Job;)V", "lastResult", "", "getLastResult", "()Z", "setLastResult", "(Z)V", "lastBounds", "Landroidx/compose/ui/spatial/RelativeLayoutBounds;", "getLastBounds", "()Landroidx/compose/ui/spatial/RelativeLayoutBounds;", "setLastBounds", "(Landroidx/compose/ui/spatial/RelativeLayoutBounds;)V", "lastViewport", "getLastViewport", "setLastViewport", "rectChanged", "Lkotlin/Function1;", "getRectChanged", "()Lkotlin/jvm/functions/Function1;", "checkVisibility", "bounds", "viewport", "startTimer", "cancelTimer", "triggerCallback", "forceUpdate", "updateViewport", "onAttach", "onReset", "onDetach", "onObservedReadsChanged", "ui_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
final class OnFirstVisibleNode extends Modifier.Node implements ObserverModifierNode {
    private Function0<Unit> callback;
    private DelegatableNode.RegistrationHandle handle;
    private Job job;
    private RelativeLayoutBounds lastBounds;
    private boolean lastResult;
    private RelativeLayoutBounds lastViewport;
    private long minDurationMs;
    private float minFractionVisible;
    private final Function1<RelativeLayoutBounds, Unit> rectChanged = new Function1<RelativeLayoutBounds, Unit>() { // from class: androidx.compose.ui.layout.OnFirstVisibleNode$rectChanged$1
        {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(RelativeLayoutBounds relativeLayoutBounds) {
            invoke2(relativeLayoutBounds);
            return Unit.INSTANCE;
        }

        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(RelativeLayoutBounds relativeLayoutBounds) {
            OnFirstVisibleNode onFirstVisibleNode = this.this$0;
            onFirstVisibleNode.checkVisibility(onFirstVisibleNode.getMinFractionVisible(), relativeLayoutBounds, this.this$0.getLastViewport());
        }
    };
    private LayoutBoundsHolder viewportBounds;

    public final long getMinDurationMs() {
        return this.minDurationMs;
    }

    public final void setMinDurationMs(long j) {
        this.minDurationMs = j;
    }

    public final float getMinFractionVisible() {
        return this.minFractionVisible;
    }

    public final void setMinFractionVisible(float f) {
        this.minFractionVisible = f;
    }

    public final Function0<Unit> getCallback() {
        return this.callback;
    }

    public final void setCallback(Function0<Unit> function0) {
        this.callback = function0;
    }

    public OnFirstVisibleNode(long j, float f, LayoutBoundsHolder layoutBoundsHolder, Function0<Unit> function0) {
        this.minDurationMs = j;
        this.minFractionVisible = f;
        this.callback = function0;
        this.viewportBounds = layoutBoundsHolder;
    }

    public final LayoutBoundsHolder getViewportBounds() {
        return this.viewportBounds;
    }

    public final void setViewportBounds(LayoutBoundsHolder layoutBoundsHolder) {
        this.viewportBounds = layoutBoundsHolder;
        updateViewport();
    }

    public final DelegatableNode.RegistrationHandle getHandle() {
        return this.handle;
    }

    public final void setHandle(DelegatableNode.RegistrationHandle registrationHandle) {
        this.handle = registrationHandle;
    }

    public final Job getJob() {
        return this.job;
    }

    public final void setJob(Job job) {
        this.job = job;
    }

    public final boolean getLastResult() {
        return this.lastResult;
    }

    public final void setLastResult(boolean z) {
        this.lastResult = z;
    }

    public final RelativeLayoutBounds getLastBounds() {
        return this.lastBounds;
    }

    public final void setLastBounds(RelativeLayoutBounds relativeLayoutBounds) {
        this.lastBounds = relativeLayoutBounds;
    }

    public final RelativeLayoutBounds getLastViewport() {
        return this.lastViewport;
    }

    public final void setLastViewport(RelativeLayoutBounds relativeLayoutBounds) {
        if (Intrinsics.areEqual(this.lastViewport, relativeLayoutBounds)) {
            return;
        }
        this.lastViewport = relativeLayoutBounds;
        forceUpdate();
    }

    public final Function1<RelativeLayoutBounds, Unit> getRectChanged() {
        return this.rectChanged;
    }

    public final void checkVisibility(float minFractionVisible, RelativeLayoutBounds bounds, RelativeLayoutBounds viewport) {
        float fFractionVisibleInWindow;
        this.lastBounds = bounds;
        if (viewport != null || this.viewportBounds == null) {
            if (viewport != null) {
                fFractionVisibleInWindow = bounds.fractionVisibleIn(viewport);
            } else {
                fFractionVisibleInWindow = bounds.fractionVisibleInWindow();
            }
            boolean z = fFractionVisibleInWindow > minFractionVisible || fFractionVisibleInWindow == 1.0f;
            if (z && !this.lastResult) {
                startTimer();
            } else if (!z && this.lastResult) {
                cancelTimer();
            }
            this.lastResult = z;
        }
    }

    public final void startTimer() {
        long j = this.minDurationMs;
        if (j == 0) {
            triggerCallback();
            return;
        }
        Job job = this.job;
        if (job != null) {
            Job.CC.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        this.job = BuildersKt__Builders_commonKt.launch$default(getCoroutineScope(), null, null, new AnonymousClass1(j, this, null), 3, null);
    }

    /* JADX INFO: renamed from: androidx.compose.ui.layout.OnFirstVisibleNode$startTimer$1, reason: invalid class name */
    /* JADX INFO: compiled from: OnFirstVisibleModifier.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.ui.layout.OnFirstVisibleNode$startTimer$1", f = "OnFirstVisibleModifier.kt", i = {}, l = {179}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ long $minDurationMs;
        int label;
        final /* synthetic */ OnFirstVisibleNode this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(long j, OnFirstVisibleNode onFirstVisibleNode, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$minDurationMs = j;
            this.this$0 = onFirstVisibleNode;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(this.$minDurationMs, this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                if (DelayKt.delay(this.$minDurationMs, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            this.this$0.triggerCallback();
            return Unit.INSTANCE;
        }
    }

    public final void cancelTimer() {
        Job job = this.job;
        if (job != null) {
            Job.CC.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
    }

    public final void triggerCallback() {
        DelegatableNode.RegistrationHandle registrationHandle = this.handle;
        if (registrationHandle != null) {
            registrationHandle.unregister();
        }
        Job job = this.job;
        if (job != null) {
            Job.CC.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        this.callback.invoke();
    }

    public final void forceUpdate() {
        RelativeLayoutBounds relativeLayoutBounds = this.lastBounds;
        if (relativeLayoutBounds != null) {
            checkVisibility(this.minFractionVisible, relativeLayoutBounds, this.lastViewport);
        }
    }

    public final void updateViewport() {
        if (this.viewportBounds == null) {
            setLastViewport(null);
        } else {
            ObserverModifierNodeKt.observeReads(this, new Function0<Unit>() { // from class: androidx.compose.ui.layout.OnFirstVisibleNode.updateViewport.1
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    OnFirstVisibleNode onFirstVisibleNode = OnFirstVisibleNode.this;
                    LayoutBoundsHolder viewportBounds = onFirstVisibleNode.getViewportBounds();
                    onFirstVisibleNode.setLastViewport(viewportBounds != null ? viewportBounds.getBounds() : null);
                }
            });
        }
    }

    @Override // androidx.compose.ui.Modifier.Node
    public void onAttach() {
        DelegatableNode.RegistrationHandle registrationHandle = this.handle;
        if (registrationHandle != null) {
            registrationHandle.unregister();
        }
        updateViewport();
        this.handle = OnLayoutRectChangedModifierKt.registerOnLayoutRectChanged(this, 0L, 0L, this.rectChanged);
    }

    @Override // androidx.compose.ui.Modifier.Node
    public void onReset() {
        Job job = this.job;
        if (job != null) {
            Job.CC.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        this.job = null;
        this.lastResult = false;
        this.lastBounds = null;
        setLastViewport(null);
    }

    @Override // androidx.compose.ui.Modifier.Node
    public void onDetach() {
        DelegatableNode.RegistrationHandle registrationHandle = this.handle;
        if (registrationHandle != null) {
            registrationHandle.unregister();
        }
    }

    @Override // androidx.compose.ui.node.ObserverModifierNode
    public void onObservedReadsChanged() {
        updateViewport();
    }
}
