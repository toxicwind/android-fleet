package com.lagradost.cloudstream3.ui.player;

import com.lagradost.cloudstream3.CommonActivity;
import com.lagradost.cloudstream3.mvvm.Resource;
import com.lagradost.cloudstream3.subtitles.AbstractSubtitleEntities;
import com.lagradost.cloudstream3.syncproviders.SubtitleRepo;
import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

/* JADX INFO: compiled from: GeneratorPlayer.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\n"}, d2 = {"<anonymous>", "", "Lcom/lagradost/cloudstream3/subtitles/AbstractSubtitleEntities$SubtitleEntity;", "it", "Lcom/lagradost/cloudstream3/syncproviders/SubtitleRepo;"}, k = 3, mv = {2, 3, 0}, xi = 48)
@DebugMetadata(c = "com.lagradost.cloudstream3.ui.player.GeneratorPlayer$openOnlineSubPicker$5$onQueryTextSubmit$1$results$1", f = "GeneratorPlayer.kt", i = {0}, l = {733}, m = "invokeSuspend", n = {"it"}, nl = {734}, s = {"L$0"}, v = 2)
final class GeneratorPlayer$openOnlineSubPicker$5$onQueryTextSubmit$1$results$1 extends SuspendLambda implements Function2<SubtitleRepo, Continuation<? super List<? extends AbstractSubtitleEntities.SubtitleEntity>>, Object> {
    final /* synthetic */ AbstractSubtitleEntities.SubtitleSearch $search;
    /* synthetic */ Object L$0;
    Object L$1;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    GeneratorPlayer$openOnlineSubPicker$5$onQueryTextSubmit$1$results$1(AbstractSubtitleEntities.SubtitleSearch subtitleSearch, Continuation<? super GeneratorPlayer$openOnlineSubPicker$5$onQueryTextSubmit$1$results$1> continuation) {
        super(2, continuation);
        this.$search = subtitleSearch;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        GeneratorPlayer$openOnlineSubPicker$5$onQueryTextSubmit$1$results$1 generatorPlayer$openOnlineSubPicker$5$onQueryTextSubmit$1$results$1 = new GeneratorPlayer$openOnlineSubPicker$5$onQueryTextSubmit$1$results$1(this.$search, continuation);
        generatorPlayer$openOnlineSubPicker$5$onQueryTextSubmit$1$results$1.L$0 = obj;
        return generatorPlayer$openOnlineSubPicker$5$onQueryTextSubmit$1$results$1;
    }

    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
    public final Object invoke2(SubtitleRepo subtitleRepo, Continuation<? super List<AbstractSubtitleEntities.SubtitleEntity>> continuation) {
        return ((GeneratorPlayer$openOnlineSubPicker$5$onQueryTextSubmit$1$results$1) create(subtitleRepo, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object invoke(SubtitleRepo subtitleRepo, Continuation<? super List<? extends AbstractSubtitleEntities.SubtitleEntity>> continuation) {
        return invoke2(subtitleRepo, (Continuation<? super List<AbstractSubtitleEntities.SubtitleEntity>>) continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object value;
        Resource.Companion companion;
        SubtitleRepo subtitleRepo = (SubtitleRepo) this.L$0;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            Resource.Companion companion2 = Resource.INSTANCE;
            this.L$0 = SpillingKt.nullOutSpilledVariable(subtitleRepo);
            this.L$1 = companion2;
            this.label = 1;
            Object objM8424searchgIAlus = subtitleRepo.m8424searchgIAlus(this.$search, this);
            if (objM8424searchgIAlus == coroutine_suspended) {
                return coroutine_suspended;
            }
            value = objM8424searchgIAlus;
            companion = companion2;
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            companion = (Resource.Companion) this.L$1;
            ResultKt.throwOnFailure(obj);
            value = ((Result) obj).getValue();
        }
        Resource resourceFromResult = companion.fromResult(value);
        if (resourceFromResult instanceof Resource.Success) {
            return (List) ((Resource.Success) resourceFromResult).getValue();
        }
        if (resourceFromResult instanceof Resource.Loading) {
            return CollectionsKt.emptyList();
        }
        if (!(resourceFromResult instanceof Resource.Failure)) {
            throw new NoWhenBranchMatchedException();
        }
        CommonActivity.showToast$default(CommonActivity.INSTANCE, ((Resource.Failure) resourceFromResult).getErrorString(), (Integer) null, 2, (Object) null);
        return CollectionsKt.emptyList();
    }
}
