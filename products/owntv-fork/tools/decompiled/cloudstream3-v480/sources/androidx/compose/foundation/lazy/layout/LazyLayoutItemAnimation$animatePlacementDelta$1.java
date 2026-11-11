package androidx.compose.foundation.lazy.layout;

import androidx.compose.animation.core.FiniteAnimationSpec;
import androidx.compose.ui.unit.IntOffset;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: LazyLayoutItemAnimation.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "androidx.compose.foundation.lazy.layout.LazyLayoutItemAnimation$animatePlacementDelta$1", f = "LazyLayoutItemAnimation.kt", i = {0}, l = {151, 158}, m = "invokeSuspend", n = {"finalSpec"}, s = {"L$0"})
final class LazyLayoutItemAnimation$animatePlacementDelta$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ FiniteAnimationSpec<IntOffset> $spec;
    final /* synthetic */ long $totalDelta;
    Object L$0;
    int label;
    final /* synthetic */ LazyLayoutItemAnimation this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    LazyLayoutItemAnimation$animatePlacementDelta$1(LazyLayoutItemAnimation lazyLayoutItemAnimation, FiniteAnimationSpec<IntOffset> finiteAnimationSpec, long j, Continuation<? super LazyLayoutItemAnimation$animatePlacementDelta$1> continuation) {
        super(2, continuation);
        this.this$0 = lazyLayoutItemAnimation;
        this.$spec = finiteAnimationSpec;
        this.$totalDelta = j;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new LazyLayoutItemAnimation$animatePlacementDelta$1(this.this$0, this.$spec, this.$totalDelta, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((LazyLayoutItemAnimation$animatePlacementDelta$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code restructure failed: missing block: B:29:0x00ac, code lost:
    
        if (androidx.compose.animation.core.Animatable.animateTo$default(r5, r6, r7, null, new androidx.compose.foundation.lazy.layout.LazyLayoutItemAnimation$animatePlacementDelta$1.AnonymousClass1(), r13, 4, null) == r0) goto L30;
     */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r14) {
        /*
            r13 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r13.label
            r2 = 2
            r3 = 1
            if (r1 == 0) goto L23
            if (r1 == r3) goto L1b
            if (r1 != r2) goto L13
            kotlin.ResultKt.throwOnFailure(r14)     // Catch: java.util.concurrent.CancellationException -> Lba
            goto Laf
        L13:
            java.lang.IllegalStateException r13 = new java.lang.IllegalStateException
            java.lang.String r14 = "call to 'resume' before 'invoke' with coroutine"
            r13.<init>(r14)
            throw r13
        L1b:
            java.lang.Object r1 = r13.L$0
            androidx.compose.animation.core.FiniteAnimationSpec r1 = (androidx.compose.animation.core.FiniteAnimationSpec) r1
            kotlin.ResultKt.throwOnFailure(r14)     // Catch: java.util.concurrent.CancellationException -> Lba
            goto L67
        L23:
            kotlin.ResultKt.throwOnFailure(r14)
            androidx.compose.foundation.lazy.layout.LazyLayoutItemAnimation r14 = r13.this$0     // Catch: java.util.concurrent.CancellationException -> Lba
            androidx.compose.animation.core.Animatable r14 = androidx.compose.foundation.lazy.layout.LazyLayoutItemAnimation.access$getPlacementDeltaAnimation$p(r14)     // Catch: java.util.concurrent.CancellationException -> Lba
            boolean r14 = r14.isRunning()     // Catch: java.util.concurrent.CancellationException -> Lba
            androidx.compose.animation.core.FiniteAnimationSpec<androidx.compose.ui.unit.IntOffset> r1 = r13.$spec
            if (r14 == 0) goto L41
            boolean r14 = r1 instanceof androidx.compose.animation.core.SpringSpec     // Catch: java.util.concurrent.CancellationException -> Lba
            if (r14 == 0) goto L3b
            androidx.compose.animation.core.SpringSpec r1 = (androidx.compose.animation.core.SpringSpec) r1     // Catch: java.util.concurrent.CancellationException -> Lba
            goto L3f
        L3b:
            androidx.compose.animation.core.SpringSpec r1 = androidx.compose.foundation.lazy.layout.LazyLayoutItemAnimationKt.access$getInterruptionSpec$p()     // Catch: java.util.concurrent.CancellationException -> Lba
        L3f:
            androidx.compose.animation.core.FiniteAnimationSpec r1 = (androidx.compose.animation.core.FiniteAnimationSpec) r1     // Catch: java.util.concurrent.CancellationException -> Lba
        L41:
            androidx.compose.foundation.lazy.layout.LazyLayoutItemAnimation r14 = r13.this$0     // Catch: java.util.concurrent.CancellationException -> Lba
            androidx.compose.animation.core.Animatable r14 = androidx.compose.foundation.lazy.layout.LazyLayoutItemAnimation.access$getPlacementDeltaAnimation$p(r14)     // Catch: java.util.concurrent.CancellationException -> Lba
            boolean r14 = r14.isRunning()     // Catch: java.util.concurrent.CancellationException -> Lba
            if (r14 != 0) goto L70
            androidx.compose.foundation.lazy.layout.LazyLayoutItemAnimation r14 = r13.this$0     // Catch: java.util.concurrent.CancellationException -> Lba
            androidx.compose.animation.core.Animatable r14 = androidx.compose.foundation.lazy.layout.LazyLayoutItemAnimation.access$getPlacementDeltaAnimation$p(r14)     // Catch: java.util.concurrent.CancellationException -> Lba
            long r4 = r13.$totalDelta     // Catch: java.util.concurrent.CancellationException -> Lba
            androidx.compose.ui.unit.IntOffset r4 = androidx.compose.ui.unit.IntOffset.m7013boximpl(r4)     // Catch: java.util.concurrent.CancellationException -> Lba
            r5 = r13
            kotlin.coroutines.Continuation r5 = (kotlin.coroutines.Continuation) r5     // Catch: java.util.concurrent.CancellationException -> Lba
            r13.L$0 = r1     // Catch: java.util.concurrent.CancellationException -> Lba
            r13.label = r3     // Catch: java.util.concurrent.CancellationException -> Lba
            java.lang.Object r14 = r14.snapTo(r4, r5)     // Catch: java.util.concurrent.CancellationException -> Lba
            if (r14 != r0) goto L67
            goto Lae
        L67:
            androidx.compose.foundation.lazy.layout.LazyLayoutItemAnimation r14 = r13.this$0     // Catch: java.util.concurrent.CancellationException -> Lba
            kotlin.jvm.functions.Function0 r14 = androidx.compose.foundation.lazy.layout.LazyLayoutItemAnimation.access$getOnLayerPropertyChanged$p(r14)     // Catch: java.util.concurrent.CancellationException -> Lba
            r14.invoke()     // Catch: java.util.concurrent.CancellationException -> Lba
        L70:
            androidx.compose.foundation.lazy.layout.LazyLayoutItemAnimation r14 = r13.this$0     // Catch: java.util.concurrent.CancellationException -> Lba
            androidx.compose.animation.core.Animatable r14 = androidx.compose.foundation.lazy.layout.LazyLayoutItemAnimation.access$getPlacementDeltaAnimation$p(r14)     // Catch: java.util.concurrent.CancellationException -> Lba
            java.lang.Object r14 = r14.getValue()     // Catch: java.util.concurrent.CancellationException -> Lba
            androidx.compose.ui.unit.IntOffset r14 = (androidx.compose.ui.unit.IntOffset) r14     // Catch: java.util.concurrent.CancellationException -> Lba
            long r3 = r14.m7031unboximpl()     // Catch: java.util.concurrent.CancellationException -> Lba
            long r5 = r13.$totalDelta     // Catch: java.util.concurrent.CancellationException -> Lba
            long r3 = androidx.compose.ui.unit.IntOffset.m7025minusqkQi6aY(r3, r5)     // Catch: java.util.concurrent.CancellationException -> Lba
            androidx.compose.foundation.lazy.layout.LazyLayoutItemAnimation r14 = r13.this$0     // Catch: java.util.concurrent.CancellationException -> Lba
            androidx.compose.animation.core.Animatable r5 = androidx.compose.foundation.lazy.layout.LazyLayoutItemAnimation.access$getPlacementDeltaAnimation$p(r14)     // Catch: java.util.concurrent.CancellationException -> Lba
            androidx.compose.ui.unit.IntOffset r6 = androidx.compose.ui.unit.IntOffset.m7013boximpl(r3)     // Catch: java.util.concurrent.CancellationException -> Lba
            r7 = r1
            androidx.compose.animation.core.AnimationSpec r7 = (androidx.compose.animation.core.AnimationSpec) r7     // Catch: java.util.concurrent.CancellationException -> Lba
            androidx.compose.foundation.lazy.layout.LazyLayoutItemAnimation$animatePlacementDelta$1$1 r14 = new androidx.compose.foundation.lazy.layout.LazyLayoutItemAnimation$animatePlacementDelta$1$1     // Catch: java.util.concurrent.CancellationException -> Lba
            androidx.compose.foundation.lazy.layout.LazyLayoutItemAnimation r1 = r13.this$0     // Catch: java.util.concurrent.CancellationException -> Lba
            r14.<init>()     // Catch: java.util.concurrent.CancellationException -> Lba
            r9 = r14
            kotlin.jvm.functions.Function1 r9 = (kotlin.jvm.functions.Function1) r9     // Catch: java.util.concurrent.CancellationException -> Lba
            r10 = r13
            kotlin.coroutines.Continuation r10 = (kotlin.coroutines.Continuation) r10     // Catch: java.util.concurrent.CancellationException -> Lba
            r14 = 0
            r13.L$0 = r14     // Catch: java.util.concurrent.CancellationException -> Lba
            r13.label = r2     // Catch: java.util.concurrent.CancellationException -> Lba
            r8 = 0
            r11 = 4
            r12 = 0
            java.lang.Object r14 = androidx.compose.animation.core.Animatable.animateTo$default(r5, r6, r7, r8, r9, r10, r11, r12)     // Catch: java.util.concurrent.CancellationException -> Lba
            if (r14 != r0) goto Laf
        Lae:
            return r0
        Laf:
            androidx.compose.foundation.lazy.layout.LazyLayoutItemAnimation r14 = r13.this$0     // Catch: java.util.concurrent.CancellationException -> Lba
            r0 = 0
            androidx.compose.foundation.lazy.layout.LazyLayoutItemAnimation.access$setPlacementAnimationInProgress(r14, r0)     // Catch: java.util.concurrent.CancellationException -> Lba
            androidx.compose.foundation.lazy.layout.LazyLayoutItemAnimation r13 = r13.this$0     // Catch: java.util.concurrent.CancellationException -> Lba
            androidx.compose.foundation.lazy.layout.LazyLayoutItemAnimation.access$setRunningMovingAwayAnimation$p(r13, r0)     // Catch: java.util.concurrent.CancellationException -> Lba
        Lba:
            kotlin.Unit r13 = kotlin.Unit.INSTANCE
            return r13
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.lazy.layout.LazyLayoutItemAnimation$animatePlacementDelta$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
