package com.lagradost.cloudstream3.utils;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Ref;

/* JADX INFO: compiled from: FillerEpisodeCheck.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "it", ""}, k = 3, mv = {2, 3, 0}, xi = 48)
@DebugMetadata(c = "com.lagradost.cloudstream3.utils.FillerEpisodeCheck$calc$1$1", f = "FillerEpisodeCheck.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
final class FillerEpisodeCheck$calc$1$1 extends SuspendLambda implements Function2<Integer, Continuation<? super Unit>, Object> {
    final /* synthetic */ Ref.IntRef $counter;
    final /* synthetic */ int $this_calc;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    FillerEpisodeCheck$calc$1$1(Ref.IntRef intRef, int i, Continuation<? super FillerEpisodeCheck$calc$1$1> continuation) {
        super(2, continuation);
        this.$counter = intRef;
        this.$this_calc = i;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new FillerEpisodeCheck$calc$1$1(this.$counter, this.$this_calc, continuation);
    }

    public final Object invoke(int i, Continuation<? super Unit> continuation) {
        return ((FillerEpisodeCheck$calc$1$1) create(Integer.valueOf(i), continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object invoke(Integer num, Continuation<? super Unit> continuation) {
        return invoke(num.intValue(), continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        boolean z = true;
        while (z) {
            this.$counter.element++;
            if (this.$this_calc > 10) {
                z = false;
            }
        }
        return Unit.INSTANCE;
    }
}
