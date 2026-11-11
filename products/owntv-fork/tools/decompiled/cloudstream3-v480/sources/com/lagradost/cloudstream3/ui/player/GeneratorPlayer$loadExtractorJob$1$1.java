package com.lagradost.cloudstream3.ui.player;

import androidx.mediarouter.media.GlobalMediaRouter;
import com.lagradost.cloudstream3.APIHolder;
import com.lagradost.cloudstream3.MainAPI;
import com.lagradost.cloudstream3.utils.ExtractorLink;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: GeneratorPlayer.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "it", "Lcom/lagradost/cloudstream3/ui/player/GeneratorPlayer;"}, k = 3, mv = {2, 3, 0}, xi = 48)
@DebugMetadata(c = "com.lagradost.cloudstream3.ui.player.GeneratorPlayer$loadExtractorJob$1$1", f = "GeneratorPlayer.kt", i = {}, l = {GlobalMediaRouter.CallbackHandler.MSG_ROUTE_PRESENTATION_DISPLAY_CHANGED}, m = "invokeSuspend", n = {}, nl = {GlobalMediaRouter.CallbackHandler.MSG_ROUTE_UNSELECTED}, s = {}, v = 2)
final class GeneratorPlayer$loadExtractorJob$1$1 extends SuspendLambda implements Function3<CoroutineScope, GeneratorPlayer, Continuation<? super Unit>, Object> {
    final /* synthetic */ ExtractorLink $link;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    GeneratorPlayer$loadExtractorJob$1$1(ExtractorLink extractorLink, Continuation<? super GeneratorPlayer$loadExtractorJob$1$1> continuation) {
        super(3, continuation);
        this.$link = extractorLink;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(CoroutineScope coroutineScope, GeneratorPlayer generatorPlayer, Continuation<? super Unit> continuation) {
        return new GeneratorPlayer$loadExtractorJob$1$1(this.$link, continuation).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        MainAPI apiFromNameNull;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            if (this.$link.getExtractorData() != null && (apiFromNameNull = APIHolder.INSTANCE.getApiFromNameNull(this.$link.getSource())) != null) {
                this.label = 1;
                if (apiFromNameNull.extractorVerifierJob(this.$link.getExtractorData(), this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return Unit.INSTANCE;
    }
}
