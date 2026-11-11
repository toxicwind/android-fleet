package androidx.compose.material3;

import androidx.compose.animation.core.AnimationSpec;
import androidx.compose.animation.core.DecayAnimationSpec;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection;
import androidx.compose.ui.unit.Velocity;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;

/* JADX INFO: compiled from: AppBar.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000!\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J#\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0003H\u0096@ø\u0001\u0000¢\u0006\u0004\b\u0006\u0010\u0007J*\u0010\b\u001a\u00020\t2\u0006\u0010\u0004\u001a\u00020\t2\u0006\u0010\u0005\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016ø\u0001\u0000¢\u0006\u0004\b\f\u0010\r\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\u000e"}, d2 = {"androidx/compose/material3/ExitAlwaysScrollBehavior$nestedScrollConnection$1", "Landroidx/compose/ui/input/nestedscroll/NestedScrollConnection;", "onPostFling", "Landroidx/compose/ui/unit/Velocity;", "consumed", "available", "onPostFling-RZ2iAVY", "(JJLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onPostScroll", "Landroidx/compose/ui/geometry/Offset;", "source", "Landroidx/compose/ui/input/nestedscroll/NestedScrollSource;", "onPostScroll-DzOQY0M", "(JJI)J", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class ExitAlwaysScrollBehavior$nestedScrollConnection$1 implements NestedScrollConnection {
    final /* synthetic */ ExitAlwaysScrollBehavior this$0;

    @Override // androidx.compose.ui.input.nestedscroll.NestedScrollConnection
    /* JADX INFO: renamed from: onPreFling-QWom1Mo */
    public /* synthetic */ Object mo517onPreFlingQWom1Mo(long j, Continuation continuation) {
        return NestedScrollConnection.CC.m5319onPreFlingQWom1Mo$suspendImpl(this, j, continuation);
    }

    @Override // androidx.compose.ui.input.nestedscroll.NestedScrollConnection
    /* JADX INFO: renamed from: onPreScroll-OzD1aCk */
    public /* synthetic */ long mo518onPreScrollOzD1aCk(long j, int i) {
        return NestedScrollConnection.CC.m5313$default$onPreScrollOzD1aCk(this, j, i);
    }

    ExitAlwaysScrollBehavior$nestedScrollConnection$1(ExitAlwaysScrollBehavior exitAlwaysScrollBehavior) {
        this.this$0 = exitAlwaysScrollBehavior;
    }

    @Override // androidx.compose.ui.input.nestedscroll.NestedScrollConnection
    /* JADX INFO: renamed from: onPostScroll-DzOQY0M */
    public long mo516onPostScrollDzOQY0M(long consumed, long available, int source) {
        if (!this.this$0.getCanScroll().invoke().booleanValue()) {
            return Offset.INSTANCE.m3886getZeroF1C5BW0();
        }
        BottomAppBarState state = this.this$0.getState();
        state.setContentOffset(state.getContentOffset() + Offset.m3871getYimpl(consumed));
        if ((this.this$0.getState().getHeightOffset() == 0.0f || this.this$0.getState().getHeightOffset() == this.this$0.getState().getHeightOffsetLimit()) && Offset.m3871getYimpl(consumed) == 0.0f && Offset.m3871getYimpl(available) > 0.0f) {
            this.this$0.getState().setContentOffset(0.0f);
        }
        this.this$0.getState().setHeightOffset(this.this$0.getState().getHeightOffset() + Offset.m3871getYimpl(consumed));
        return Offset.INSTANCE.m3886getZeroF1C5BW0();
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    @Override // androidx.compose.ui.input.nestedscroll.NestedScrollConnection
    /* JADX INFO: renamed from: onPostFling-RZ2iAVY */
    public Object mo515onPostFlingRZ2iAVY(long j, long j2, Continuation<? super Velocity> continuation) {
        ExitAlwaysScrollBehavior$nestedScrollConnection$1$onPostFling$1 exitAlwaysScrollBehavior$nestedScrollConnection$1$onPostFling$1;
        long j3;
        if (continuation instanceof ExitAlwaysScrollBehavior$nestedScrollConnection$1$onPostFling$1) {
            exitAlwaysScrollBehavior$nestedScrollConnection$1$onPostFling$1 = (ExitAlwaysScrollBehavior$nestedScrollConnection$1$onPostFling$1) continuation;
            if ((exitAlwaysScrollBehavior$nestedScrollConnection$1$onPostFling$1.label & Integer.MIN_VALUE) != 0) {
                exitAlwaysScrollBehavior$nestedScrollConnection$1$onPostFling$1.label -= Integer.MIN_VALUE;
            } else {
                exitAlwaysScrollBehavior$nestedScrollConnection$1$onPostFling$1 = new ExitAlwaysScrollBehavior$nestedScrollConnection$1$onPostFling$1(this, continuation);
            }
        } else {
            exitAlwaysScrollBehavior$nestedScrollConnection$1$onPostFling$1 = new ExitAlwaysScrollBehavior$nestedScrollConnection$1$onPostFling$1(this, continuation);
        }
        ExitAlwaysScrollBehavior$nestedScrollConnection$1$onPostFling$1 exitAlwaysScrollBehavior$nestedScrollConnection$1$onPostFling$2 = exitAlwaysScrollBehavior$nestedScrollConnection$1$onPostFling$1;
        Object objM5318onPostFlingRZ2iAVY$suspendImpl = exitAlwaysScrollBehavior$nestedScrollConnection$1$onPostFling$2.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = exitAlwaysScrollBehavior$nestedScrollConnection$1$onPostFling$2.label;
        if (i == 0) {
            ResultKt.throwOnFailure(objM5318onPostFlingRZ2iAVY$suspendImpl);
            exitAlwaysScrollBehavior$nestedScrollConnection$1$onPostFling$2.L$0 = this;
            exitAlwaysScrollBehavior$nestedScrollConnection$1$onPostFling$2.J$0 = j2;
            exitAlwaysScrollBehavior$nestedScrollConnection$1$onPostFling$2.label = 1;
            objM5318onPostFlingRZ2iAVY$suspendImpl = NestedScrollConnection.CC.m5318onPostFlingRZ2iAVY$suspendImpl(this, j, j2, exitAlwaysScrollBehavior$nestedScrollConnection$1$onPostFling$2);
            if (objM5318onPostFlingRZ2iAVY$suspendImpl != coroutine_suspended) {
            }
            return coroutine_suspended;
        }
        if (i == 1) {
            j2 = exitAlwaysScrollBehavior$nestedScrollConnection$1$onPostFling$2.J$0;
            this = (ExitAlwaysScrollBehavior$nestedScrollConnection$1) exitAlwaysScrollBehavior$nestedScrollConnection$1$onPostFling$2.L$0;
            ResultKt.throwOnFailure(objM5318onPostFlingRZ2iAVY$suspendImpl);
        } else {
            if (i != 2) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            j3 = exitAlwaysScrollBehavior$nestedScrollConnection$1$onPostFling$2.J$0;
            ResultKt.throwOnFailure(objM5318onPostFlingRZ2iAVY$suspendImpl);
        }
        return Velocity.m7123boximpl(Velocity.m7136plusAH228Gc(j3, ((Velocity) objM5318onPostFlingRZ2iAVY$suspendImpl).getPackedValue()));
        long packedValue = ((Velocity) objM5318onPostFlingRZ2iAVY$suspendImpl).getPackedValue();
        BottomAppBarState state = this.this$0.getState();
        float fM7133getYimpl = Velocity.m7133getYimpl(j2);
        DecayAnimationSpec<Float> flingAnimationSpec = this.this$0.getFlingAnimationSpec();
        AnimationSpec<Float> snapAnimationSpec = this.this$0.getSnapAnimationSpec();
        exitAlwaysScrollBehavior$nestedScrollConnection$1$onPostFling$2.L$0 = null;
        exitAlwaysScrollBehavior$nestedScrollConnection$1$onPostFling$2.J$0 = packedValue;
        exitAlwaysScrollBehavior$nestedScrollConnection$1$onPostFling$2.label = 2;
        objM5318onPostFlingRZ2iAVY$suspendImpl = AppBarKt.settleAppBarBottom(state, fM7133getYimpl, flingAnimationSpec, snapAnimationSpec, exitAlwaysScrollBehavior$nestedScrollConnection$1$onPostFling$2);
        if (objM5318onPostFlingRZ2iAVY$suspendImpl != coroutine_suspended) {
            j3 = packedValue;
            return Velocity.m7123boximpl(Velocity.m7136plusAH228Gc(j3, ((Velocity) objM5318onPostFlingRZ2iAVY$suspendImpl).getPackedValue()));
        }
        return coroutine_suspended;
    }
}
