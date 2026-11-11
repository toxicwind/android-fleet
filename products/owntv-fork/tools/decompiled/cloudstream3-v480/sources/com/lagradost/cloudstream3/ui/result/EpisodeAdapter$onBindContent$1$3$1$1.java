package com.lagradost.cloudstream3.ui.result;

import android.widget.ImageView;
import coil3.request.ImageRequest;
import com.lagradost.cloudstream3.databinding.ResultEpisodeLargeBinding;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: EpisodeAdapter.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "it", "Lcoil3/request/ImageRequest$Builder;"}, k = 3, mv = {2, 3, 0}, xi = 48)
@DebugMetadata(c = "com.lagradost.cloudstream3.ui.result.EpisodeAdapter$onBindContent$1$3$1$1", f = "EpisodeAdapter.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
final class EpisodeAdapter$onBindContent$1$3$1$1 extends SuspendLambda implements Function2<ImageRequest.Builder, Continuation<? super Unit>, Object> {
    final /* synthetic */ ResultEpisodeLargeBinding $this_apply;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    EpisodeAdapter$onBindContent$1$3$1$1(ResultEpisodeLargeBinding resultEpisodeLargeBinding, Continuation<? super EpisodeAdapter$onBindContent$1$3$1$1> continuation) {
        super(2, continuation);
        this.$this_apply = resultEpisodeLargeBinding;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new EpisodeAdapter$onBindContent$1$3$1$1(this.$this_apply, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(ImageRequest.Builder builder, Continuation<? super Unit> continuation) {
        return ((EpisodeAdapter$onBindContent$1$3$1$1) create(builder, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        ImageView episodeUpcomingIcon = this.$this_apply.episodeUpcomingIcon;
        Intrinsics.checkNotNullExpressionValue(episodeUpcomingIcon, "episodeUpcomingIcon");
        episodeUpcomingIcon.setVisibility(0);
        return Unit.INSTANCE;
    }
}
