package com.lagradost.cloudstream3.ui.player;

import androidx.fragment.app.FragmentActivity;
import com.lagradost.cloudstream3.LoadResponse;
import com.lagradost.cloudstream3.ParCollectionsKt;
import com.lagradost.cloudstream3.databinding.DialogOnlineSubtitlesBinding;
import com.lagradost.cloudstream3.subtitles.AbstractSubtitleEntities;
import com.lagradost.cloudstream3.syncproviders.SubtitleRepo;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: GeneratorPlayer.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u0011\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0000*\u0001\u0004\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "it", "com/lagradost/cloudstream3/ui/player/GeneratorPlayer$openOnlineSubPicker$5"}, k = 3, mv = {2, 3, 0}, xi = 48)
@DebugMetadata(c = "com.lagradost.cloudstream3.ui.player.GeneratorPlayer$openOnlineSubPicker$5$onQueryTextSubmit$1", f = "GeneratorPlayer.kt", i = {0}, l = {732}, m = "invokeSuspend", n = {"search"}, nl = {748}, s = {"L$0"}, v = 2)
final class GeneratorPlayer$openOnlineSubPicker$5$onQueryTextSubmit$1 extends SuspendLambda implements Function3<CoroutineScope, GeneratorPlayer.AnonymousClass5, Continuation<? super Unit>, Object> {
    final /* synthetic */ GeneratorPlayer$openOnlineSubPicker$arrayAdapter$1 $arrayAdapter;
    final /* synthetic */ DialogOnlineSubtitlesBinding $binding;
    final /* synthetic */ Ref.ObjectRef<String> $currentLanguageTagIETF;
    final /* synthetic */ Ref.ObjectRef<List<AbstractSubtitleEntities.SubtitleEntity>> $currentSubtitles;
    final /* synthetic */ GeneratorPlayer.TempMetaData $currentTempMeta;
    final /* synthetic */ LoadResponse $loadResponse;
    final /* synthetic */ List<SubtitleRepo> $providers;
    final /* synthetic */ String $query;
    Object L$0;
    int label;
    final /* synthetic */ GeneratorPlayer this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    GeneratorPlayer$openOnlineSubPicker$5$onQueryTextSubmit$1(String str, LoadResponse loadResponse, GeneratorPlayer.TempMetaData tempMetaData, Ref.ObjectRef<String> objectRef, GeneratorPlayer generatorPlayer, List<SubtitleRepo> list, DialogOnlineSubtitlesBinding dialogOnlineSubtitlesBinding, Ref.ObjectRef<List<AbstractSubtitleEntities.SubtitleEntity>> objectRef2, GeneratorPlayer$openOnlineSubPicker$arrayAdapter$1 generatorPlayer$openOnlineSubPicker$arrayAdapter$1, Continuation<? super GeneratorPlayer$openOnlineSubPicker$5$onQueryTextSubmit$1> continuation) {
        super(3, continuation);
        this.$query = str;
        this.$loadResponse = loadResponse;
        this.$currentTempMeta = tempMetaData;
        this.$currentLanguageTagIETF = objectRef;
        this.this$0 = generatorPlayer;
        this.$providers = list;
        this.$binding = dialogOnlineSubtitlesBinding;
        this.$currentSubtitles = objectRef2;
        this.$arrayAdapter = generatorPlayer$openOnlineSubPicker$arrayAdapter$1;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(CoroutineScope coroutineScope, GeneratorPlayer.AnonymousClass5 anonymousClass5, Continuation<? super Unit> continuation) {
        return new GeneratorPlayer$openOnlineSubPicker$5$onQueryTextSubmit$1(this.$query, this.$loadResponse, this.$currentTempMeta, this.$currentLanguageTagIETF, this.this$0, this.$providers, this.$binding, this.$currentSubtitles, this.$arrayAdapter, continuation).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        String aniListId;
        String malId;
        String tMDbId;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        Integer num = null;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            String str = this.$query;
            if (str == null) {
                return Unit.INSTANCE;
            }
            LoadResponse loadResponse = this.$loadResponse;
            String imdbId = loadResponse != null ? LoadResponse.INSTANCE.getImdbId(loadResponse) : null;
            LoadResponse loadResponse2 = this.$loadResponse;
            Integer numBoxInt = (loadResponse2 == null || (tMDbId = LoadResponse.INSTANCE.getTMDbId(loadResponse2)) == null) ? null : Boxing.boxInt(Integer.parseInt(tMDbId));
            LoadResponse loadResponse3 = this.$loadResponse;
            Integer numBoxInt2 = (loadResponse3 == null || (malId = LoadResponse.INSTANCE.getMalId(loadResponse3)) == null) ? null : Boxing.boxInt(Integer.parseInt(malId));
            LoadResponse loadResponse4 = this.$loadResponse;
            Integer numBoxInt3 = (loadResponse4 == null || (aniListId = LoadResponse.INSTANCE.getAniListId(loadResponse4)) == null) ? null : Boxing.boxInt(Integer.parseInt(aniListId));
            Integer episode = this.$currentTempMeta.getEpisode();
            Integer season = this.$currentTempMeta.getSeason();
            String str2 = this.$currentLanguageTagIETF.element;
            if (StringsKt.isBlank(str2)) {
                str2 = null;
            }
            String str3 = str2;
            PlayerGeneratorViewModel playerGeneratorViewModel = this.this$0.viewModel;
            if (playerGeneratorViewModel == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                playerGeneratorViewModel = null;
            }
            AbstractSubtitleEntities.SubtitleSearch subtitleSearch = new AbstractSubtitleEntities.SubtitleSearch(str, str3, imdbId, numBoxInt, numBoxInt2, numBoxInt3, episode, season, playerGeneratorViewModel.getCurrentSubtitleYear().getValue());
            this.L$0 = SpillingKt.nullOutSpilledVariable(subtitleSearch);
            this.label = 1;
            obj = ParCollectionsKt.amap(this.$providers, new GeneratorPlayer$openOnlineSubPicker$5$onQueryTextSubmit$1$results$1(subtitleSearch, null), this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        List list = (List) obj;
        Iterator it = list.iterator();
        if (it.hasNext()) {
            Integer numBoxInt4 = Boxing.boxInt(((List) it.next()).size());
            loop0: while (true) {
                num = numBoxInt4;
                do {
                    if (!it.hasNext()) {
                        break loop0;
                    }
                    numBoxInt4 = Boxing.boxInt(((List) it.next()).size());
                } while (num.compareTo(numBoxInt4) >= 0);
            }
        }
        Integer num2 = num;
        if (num2 == null) {
            return Unit.INSTANCE;
        }
        int iIntValue = num2.intValue();
        final ArrayList arrayList = new ArrayList();
        int size = list.size();
        for (int i2 = 0; i2 < iIntValue; i2++) {
            for (int i3 = 0; i3 < size; i3++) {
                AbstractSubtitleEntities.SubtitleEntity subtitleEntity = (AbstractSubtitleEntities.SubtitleEntity) CollectionsKt.getOrNull((List) list.get(i3), i2);
                if (subtitleEntity != null) {
                    arrayList.add(subtitleEntity);
                }
            }
        }
        FragmentActivity activity = this.this$0.getActivity();
        if (activity != null) {
            final DialogOnlineSubtitlesBinding dialogOnlineSubtitlesBinding = this.$binding;
            final Ref.ObjectRef<List<AbstractSubtitleEntities.SubtitleEntity>> objectRef = this.$currentSubtitles;
            final GeneratorPlayer$openOnlineSubPicker$arrayAdapter$1 generatorPlayer$openOnlineSubPicker$arrayAdapter$1 = this.$arrayAdapter;
            activity.runOnUiThread(new Runnable() { // from class: com.lagradost.cloudstream3.ui.player.GeneratorPlayer$openOnlineSubPicker$5$onQueryTextSubmit$1$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    GeneratorPlayer$openOnlineSubPicker$5$onQueryTextSubmit$1.invokeSuspend$lambda$2(arrayList, dialogOnlineSubtitlesBinding, objectRef, generatorPlayer$openOnlineSubPicker$arrayAdapter$1);
                }
            });
        }
        return Unit.INSTANCE;
    }

    static final void invokeSuspend$lambda$2(ArrayList arrayList, DialogOnlineSubtitlesBinding dialogOnlineSubtitlesBinding, Ref.ObjectRef objectRef, GeneratorPlayer$openOnlineSubPicker$arrayAdapter$1 generatorPlayer$openOnlineSubPicker$arrayAdapter$1) {
        GeneratorPlayer.openOnlineSubPicker$setSubtitlesList(objectRef, generatorPlayer$openOnlineSubPicker$arrayAdapter$1, arrayList);
        dialogOnlineSubtitlesBinding.searchLoadingBar.hide();
    }
}
