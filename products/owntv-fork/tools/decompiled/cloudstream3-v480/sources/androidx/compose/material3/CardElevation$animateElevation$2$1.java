package androidx.compose.material3;

import androidx.compose.animation.core.Animatable;
import androidx.compose.animation.core.AnimationVector1D;
import androidx.compose.foundation.interaction.Interaction;
import androidx.compose.ui.unit.Dp;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: Card.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "androidx.compose.material3.CardElevation$animateElevation$2$1", f = "Card.kt", i = {}, l = {731, 741}, m = "invokeSuspend", n = {}, s = {})
final class CardElevation$animateElevation$2$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Animatable<Dp, AnimationVector1D> $animatable;
    final /* synthetic */ boolean $enabled;
    final /* synthetic */ Interaction $interaction;
    final /* synthetic */ float $target;
    int label;
    final /* synthetic */ CardElevation this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    CardElevation$animateElevation$2$1(Animatable<Dp, AnimationVector1D> animatable, float f, boolean z, CardElevation cardElevation, Interaction interaction, Continuation<? super CardElevation$animateElevation$2$1> continuation) {
        super(2, continuation);
        this.$animatable = animatable;
        this.$target = f;
        this.$enabled = z;
        this.this$0 = cardElevation;
        this.$interaction = interaction;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new CardElevation$animateElevation$2$1(this.$animatable, this.$target, this.$enabled, this.this$0, this.$interaction, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((CardElevation$animateElevation$2$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x0048, code lost:
    
        if (r1.snapTo(androidx.compose.ui.unit.Dp.m6888boximpl(r6.$target), r6) == r0) goto L31;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x00bf, code lost:
    
        if (androidx.compose.material3.internal.ElevationKt.m2618animateElevationrAjV9yQ(r6.$animatable, r6.$target, r3, r6.$interaction, r6) == r0) goto L31;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x00c1, code lost:
    
        return r0;
     */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r7) {
        /*
            r6 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r6.label
            r2 = 2
            r3 = 1
            if (r1 == 0) goto L1c
            if (r1 == r3) goto L17
            if (r1 != r2) goto Lf
            goto L17
        Lf:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L17:
            kotlin.ResultKt.throwOnFailure(r7)
            goto Lc2
        L1c:
            kotlin.ResultKt.throwOnFailure(r7)
            androidx.compose.animation.core.Animatable<androidx.compose.ui.unit.Dp, androidx.compose.animation.core.AnimationVector1D> r7 = r6.$animatable
            java.lang.Object r7 = r7.getTargetValue()
            androidx.compose.ui.unit.Dp r7 = (androidx.compose.ui.unit.Dp) r7
            float r7 = r7.m6904unboximpl()
            float r1 = r6.$target
            boolean r7 = androidx.compose.ui.unit.Dp.m6895equalsimpl0(r7, r1)
            if (r7 != 0) goto Lc2
            boolean r7 = r6.$enabled
            androidx.compose.animation.core.Animatable<androidx.compose.ui.unit.Dp, androidx.compose.animation.core.AnimationVector1D> r1 = r6.$animatable
            if (r7 != 0) goto L4c
            float r7 = r6.$target
            androidx.compose.ui.unit.Dp r7 = androidx.compose.ui.unit.Dp.m6888boximpl(r7)
            r2 = r6
            kotlin.coroutines.Continuation r2 = (kotlin.coroutines.Continuation) r2
            r6.label = r3
            java.lang.Object r6 = r1.snapTo(r7, r2)
            if (r6 != r0) goto Lc2
            goto Lc1
        L4c:
            java.lang.Object r7 = r1.getTargetValue()
            androidx.compose.ui.unit.Dp r7 = (androidx.compose.ui.unit.Dp) r7
            float r7 = r7.m6904unboximpl()
            androidx.compose.material3.CardElevation r1 = r6.this$0
            float r1 = androidx.compose.material3.CardElevation.access$getPressedElevation$p(r1)
            boolean r1 = androidx.compose.ui.unit.Dp.m6895equalsimpl0(r7, r1)
            r3 = 0
            if (r1 == 0) goto L72
            androidx.compose.foundation.interaction.PressInteraction$Press r7 = new androidx.compose.foundation.interaction.PressInteraction$Press
            androidx.compose.ui.geometry.Offset$Companion r1 = androidx.compose.ui.geometry.Offset.INSTANCE
            long r4 = r1.m3886getZeroF1C5BW0()
            r7.<init>(r4, r3)
            r3 = r7
            androidx.compose.foundation.interaction.Interaction r3 = (androidx.compose.foundation.interaction.Interaction) r3
            goto Lb0
        L72:
            androidx.compose.material3.CardElevation r1 = r6.this$0
            float r1 = androidx.compose.material3.CardElevation.access$getHoveredElevation$p(r1)
            boolean r1 = androidx.compose.ui.unit.Dp.m6895equalsimpl0(r7, r1)
            if (r1 == 0) goto L87
            androidx.compose.foundation.interaction.HoverInteraction$Enter r7 = new androidx.compose.foundation.interaction.HoverInteraction$Enter
            r7.<init>()
            r3 = r7
            androidx.compose.foundation.interaction.Interaction r3 = (androidx.compose.foundation.interaction.Interaction) r3
            goto Lb0
        L87:
            androidx.compose.material3.CardElevation r1 = r6.this$0
            float r1 = androidx.compose.material3.CardElevation.access$getFocusedElevation$p(r1)
            boolean r1 = androidx.compose.ui.unit.Dp.m6895equalsimpl0(r7, r1)
            if (r1 == 0) goto L9c
            androidx.compose.foundation.interaction.FocusInteraction$Focus r7 = new androidx.compose.foundation.interaction.FocusInteraction$Focus
            r7.<init>()
            r3 = r7
            androidx.compose.foundation.interaction.Interaction r3 = (androidx.compose.foundation.interaction.Interaction) r3
            goto Lb0
        L9c:
            androidx.compose.material3.CardElevation r1 = r6.this$0
            float r1 = androidx.compose.material3.CardElevation.access$getDraggedElevation$p(r1)
            boolean r7 = androidx.compose.ui.unit.Dp.m6895equalsimpl0(r7, r1)
            if (r7 == 0) goto Lb0
            androidx.compose.foundation.interaction.DragInteraction$Start r7 = new androidx.compose.foundation.interaction.DragInteraction$Start
            r7.<init>()
            r3 = r7
            androidx.compose.foundation.interaction.Interaction r3 = (androidx.compose.foundation.interaction.Interaction) r3
        Lb0:
            androidx.compose.animation.core.Animatable<androidx.compose.ui.unit.Dp, androidx.compose.animation.core.AnimationVector1D> r7 = r6.$animatable
            float r1 = r6.$target
            androidx.compose.foundation.interaction.Interaction r4 = r6.$interaction
            r5 = r6
            kotlin.coroutines.Continuation r5 = (kotlin.coroutines.Continuation) r5
            r6.label = r2
            java.lang.Object r6 = androidx.compose.material3.internal.ElevationKt.m2618animateElevationrAjV9yQ(r7, r1, r3, r4, r5)
            if (r6 != r0) goto Lc2
        Lc1:
            return r0
        Lc2:
            kotlin.Unit r6 = kotlin.Unit.INSTANCE
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.CardElevation$animateElevation$2$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
