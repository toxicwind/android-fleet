package com.lagradost.cloudstream3.ui;

import com.google.android.gms.cast.MediaError;
import com.lagradost.cloudstream3.ui.player.ExtractorUri;
import com.lagradost.cloudstream3.ui.player.IGeneratorKt;
import com.lagradost.cloudstream3.ui.player.RepoLinkGenerator;
import com.lagradost.cloudstream3.ui.player.SubtitleData;
import com.lagradost.cloudstream3.utils.ExtractorLink;
import com.lagradost.cloudstream3.utils.ExtractorLinkType;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: ControllerActivity.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u0006\n\u0000\n\u0002\u0010\u000b\u0010\u0000\u001a\u00020\u0001H\n"}, d2 = {"<anonymous>", ""}, k = 3, mv = {2, 3, 0}, xi = 48)
@DebugMetadata(c = "com.lagradost.cloudstream3.ui.SelectSourceController$onMediaStatusUpdated$1$isSuccessful$1", f = "ControllerActivity.kt", i = {}, l = {MediaError.DetailedErrorCode.DASH_NETWORK}, m = "invokeSuspend", n = {}, nl = {333}, s = {}, v = 2)
final class SelectSourceController$onMediaStatusUpdated$1$isSuccessful$1 extends SuspendLambda implements Function1<Continuation<? super Boolean>, Object> {
    final /* synthetic */ Set<ExtractorLink> $currentLinks;
    final /* synthetic */ Set<SubtitleData> $currentSubs;
    final /* synthetic */ RepoLinkGenerator $generator;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    SelectSourceController$onMediaStatusUpdated$1$isSuccessful$1(RepoLinkGenerator repoLinkGenerator, Set<ExtractorLink> set, Set<SubtitleData> set2, Continuation<? super SelectSourceController$onMediaStatusUpdated$1$isSuccessful$1> continuation) {
        super(1, continuation);
        this.$generator = repoLinkGenerator;
        this.$currentLinks = set;
        this.$currentSubs = set2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Continuation<?> continuation) {
        return new SelectSourceController$onMediaStatusUpdated$1$isSuccessful$1(this.$generator, this.$currentLinks, this.$currentSubs, continuation);
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Continuation<? super Boolean> continuation) {
        return ((SelectSourceController$onMediaStatusUpdated$1$isSuccessful$1) create(continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i != 0) {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            return obj;
        }
        ResultKt.throwOnFailure(obj);
        RepoLinkGenerator repoLinkGenerator = this.$generator;
        Set<ExtractorLinkType> loadtype_chromecast = IGeneratorKt.getLOADTYPE_CHROMECAST();
        final Set<ExtractorLink> set = this.$currentLinks;
        Function1<? super Pair<? extends ExtractorLink, ExtractorUri>, Unit> function1 = new Function1() { // from class: com.lagradost.cloudstream3.ui.SelectSourceController$onMediaStatusUpdated$1$isSuccessful$1$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj2) {
                return SelectSourceController$onMediaStatusUpdated$1$isSuccessful$1.invokeSuspend$lambda$0(set, (Pair) obj2);
            }
        };
        final Set<SubtitleData> set2 = this.$currentSubs;
        this.label = 1;
        Object objGenerateLinks = repoLinkGenerator.generateLinks(false, loadtype_chromecast, function1, new Function1() { // from class: com.lagradost.cloudstream3.ui.SelectSourceController$onMediaStatusUpdated$1$isSuccessful$1$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj2) {
                return SelectSourceController$onMediaStatusUpdated$1$isSuccessful$1.invokeSuspend$lambda$1(set2, (SubtitleData) obj2);
            }
        }, 0, true, this);
        return objGenerateLinks == coroutine_suspended ? coroutine_suspended : objGenerateLinks;
    }

    static final Unit invokeSuspend$lambda$0(Set set, Pair pair) {
        ExtractorLink extractorLink = (ExtractorLink) pair.getFirst();
        if (extractorLink != null) {
            set.add(extractorLink);
        }
        return Unit.INSTANCE;
    }

    static final Unit invokeSuspend$lambda$1(Set set, SubtitleData subtitleData) {
        set.add(subtitleData);
        return Unit.INSTANCE;
    }
}
