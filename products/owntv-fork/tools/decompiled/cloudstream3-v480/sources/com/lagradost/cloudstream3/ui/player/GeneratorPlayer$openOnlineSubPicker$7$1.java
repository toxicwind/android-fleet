package com.lagradost.cloudstream3.ui.player;

import android.app.Dialog;
import com.google.android.material.button.MaterialButton;
import com.lagradost.cloudstream3.CommonActivity;
import com.lagradost.cloudstream3.R;
import com.lagradost.cloudstream3.databinding.DialogOnlineSubtitlesBinding;
import com.lagradost.cloudstream3.mvvm.Resource;
import com.lagradost.cloudstream3.subtitles.AbstractSubtitleEntities;
import com.lagradost.cloudstream3.subtitles.SubtitleResource;
import com.lagradost.cloudstream3.syncproviders.SubtitleRepo;
import com.lagradost.cloudstream3.utils.Coroutines;
import com.lagradost.cloudstream3.utils.UIHelper;
import java.util.ArrayList;
import java.util.Arrays;
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
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: GeneratorPlayer.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "it", "Lcom/lagradost/cloudstream3/ui/player/GeneratorPlayer;"}, k = 3, mv = {2, 3, 0}, xi = 48)
@DebugMetadata(c = "com.lagradost.cloudstream3.ui.player.GeneratorPlayer$openOnlineSubPicker$7$1", f = "GeneratorPlayer.kt", i = {}, l = {811}, m = "invokeSuspend", n = {}, nl = {810}, s = {}, v = 2)
final class GeneratorPlayer$openOnlineSubPicker$7$1 extends SuspendLambda implements Function3<CoroutineScope, GeneratorPlayer, Continuation<? super Unit>, Object> {
    final /* synthetic */ SubtitleRepo $api;
    final /* synthetic */ DialogOnlineSubtitlesBinding $binding;
    final /* synthetic */ AbstractSubtitleEntities.SubtitleEntity $currentSubtitle;
    final /* synthetic */ Dialog $dialog;
    Object L$0;
    int label;
    final /* synthetic */ GeneratorPlayer this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    GeneratorPlayer$openOnlineSubPicker$7$1(SubtitleRepo subtitleRepo, AbstractSubtitleEntities.SubtitleEntity subtitleEntity, DialogOnlineSubtitlesBinding dialogOnlineSubtitlesBinding, Dialog dialog, GeneratorPlayer generatorPlayer, Continuation<? super GeneratorPlayer$openOnlineSubPicker$7$1> continuation) {
        super(3, continuation);
        this.$api = subtitleRepo;
        this.$currentSubtitle = subtitleEntity;
        this.$binding = dialogOnlineSubtitlesBinding;
        this.$dialog = dialog;
        this.this$0 = generatorPlayer;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(CoroutineScope coroutineScope, GeneratorPlayer generatorPlayer, Continuation<? super Unit> continuation) {
        return new GeneratorPlayer$openOnlineSubPicker$7$1(this.$api, this.$currentSubtitle, this.$binding, this.$dialog, this.this$0, continuation).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Resource.Companion companion;
        Object value;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            Resource.Companion companion2 = Resource.INSTANCE;
            this.L$0 = companion2;
            this.label = 1;
            Object objM8423resourcegIAlus = this.$api.m8423resourcegIAlus(this.$currentSubtitle, this);
            if (objM8423resourcegIAlus == coroutine_suspended) {
                return coroutine_suspended;
            }
            companion = companion2;
            value = objM8423resourcegIAlus;
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            companion = (Resource.Companion) this.L$0;
            ResultKt.throwOnFailure(obj);
            value = ((Result) obj).getValue();
        }
        Resource resourceFromResult = companion.fromResult(value);
        UIHelper uIHelper = UIHelper.INSTANCE;
        MaterialButton applyBtt = this.$binding.applyBtt;
        Intrinsics.checkNotNullExpressionValue(applyBtt, "applyBtt");
        uIHelper.hideProgress(applyBtt);
        if (resourceFromResult instanceof Resource.Success) {
            List<SubtitleResource.SingleSubtitleResource> subtitles = ((SubtitleResource) ((Resource.Success) resourceFromResult).getValue()).getSubtitles();
            GeneratorPlayer generatorPlayer = this.this$0;
            AbstractSubtitleEntities.SubtitleEntity subtitleEntity = this.$currentSubtitle;
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(subtitles, 10));
            for (SubtitleResource.SingleSubtitleResource singleSubtitleResource : subtitles) {
                String name = singleSubtitleResource.getName();
                if (name == null) {
                    name = generatorPlayer.getName(subtitleEntity, true);
                }
                arrayList.add(new SubtitleData(name, "", singleSubtitleResource.getUrl(), singleSubtitleResource.getOrigin(), PlayerSubtitleHelper.INSTANCE.toSubtitleMimeType(singleSubtitleResource.getUrl()), subtitleEntity.getHeaders(), subtitleEntity.getLang()));
            }
            final ArrayList arrayList2 = arrayList;
            if (arrayList2.isEmpty()) {
                CommonActivity.showToast$default(CommonActivity.INSTANCE, R.string.no_subtitles, (Integer) null, 2, (Object) null);
                return Unit.INSTANCE;
            }
            UIHelper.INSTANCE.dismissSafe(this.$dialog);
            Coroutines coroutines = Coroutines.INSTANCE;
            final GeneratorPlayer generatorPlayer2 = this.this$0;
            coroutines.runOnMainThread(new Function0() { // from class: com.lagradost.cloudstream3.ui.player.GeneratorPlayer$openOnlineSubPicker$7$1$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return GeneratorPlayer$openOnlineSubPicker$7$1.invokeSuspend$lambda$1(generatorPlayer2, arrayList2);
                }
            });
        } else if (resourceFromResult instanceof Resource.Failure) {
            CommonActivity.showToast$default(CommonActivity.INSTANCE, ((Resource.Failure) resourceFromResult).getErrorString(), (Integer) null, 2, (Object) null);
        } else if (!(resourceFromResult instanceof Resource.Loading)) {
            throw new NoWhenBranchMatchedException();
        }
        return Unit.INSTANCE;
    }

    static final Unit invokeSuspend$lambda$1(GeneratorPlayer generatorPlayer, List list) {
        SubtitleData[] subtitleDataArr = (SubtitleData[]) list.toArray(new SubtitleData[0]);
        generatorPlayer.addAndSelectSubtitles((SubtitleData[]) Arrays.copyOf(subtitleDataArr, subtitleDataArr.length));
        return Unit.INSTANCE;
    }
}
