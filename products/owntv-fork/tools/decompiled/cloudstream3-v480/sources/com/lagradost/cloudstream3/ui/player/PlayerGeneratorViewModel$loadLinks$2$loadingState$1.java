package com.lagradost.cloudstream3.ui.player;

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
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;

/* JADX INFO: compiled from: PlayerGeneratorViewModel.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u0006\n\u0000\n\u0002\u0010\u0002\u0010\u0000\u001a\u00020\u0001H\n"}, d2 = {"<anonymous>", ""}, k = 3, mv = {2, 3, 0}, xi = 48)
@DebugMetadata(c = "com.lagradost.cloudstream3.ui.player.PlayerGeneratorViewModel$loadLinks$2$loadingState$1", f = "PlayerGeneratorViewModel.kt", i = {}, l = {362}, m = "invokeSuspend", n = {}, nl = {379}, s = {}, v = 2)
final class PlayerGeneratorViewModel$loadLinks$2$loadingState$1 extends SuspendLambda implements Function1<Continuation<? super Unit>, Object> {
    final /* synthetic */ CoroutineScope $$this$launchSafe;
    final /* synthetic */ int $index;
    final /* synthetic */ Set<ExtractorLinkType> $sourceTypes;
    int label;
    final /* synthetic */ PlayerGeneratorViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    PlayerGeneratorViewModel$loadLinks$2$loadingState$1(PlayerGeneratorViewModel playerGeneratorViewModel, Set<? extends ExtractorLinkType> set, int i, CoroutineScope coroutineScope, Continuation<? super PlayerGeneratorViewModel$loadLinks$2$loadingState$1> continuation) {
        super(1, continuation);
        this.this$0 = playerGeneratorViewModel;
        this.$sourceTypes = set;
        this.$index = i;
        this.$$this$launchSafe = coroutineScope;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Continuation<?> continuation) {
        return new PlayerGeneratorViewModel$loadLinks$2$loadingState$1(this.this$0, this.$sourceTypes, this.$index, this.$$this$launchSafe, continuation);
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Continuation<? super Unit> continuation) {
        return ((PlayerGeneratorViewModel$loadLinks$2$loadingState$1) create(continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            VideoGenerator<?> generator = this.this$0.getGenerator();
            if (generator != null) {
                boolean forceClearCache = this.this$0.getForceClearCache();
                Set<ExtractorLinkType> set = this.$sourceTypes;
                final CoroutineScope coroutineScope = this.$$this$launchSafe;
                final PlayerGeneratorViewModel playerGeneratorViewModel = this.this$0;
                Function1<? super Pair<? extends ExtractorLink, ExtractorUri>, Unit> function1 = new Function1() { // from class: com.lagradost.cloudstream3.ui.player.PlayerGeneratorViewModel$loadLinks$2$loadingState$1$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj2) {
                        return PlayerGeneratorViewModel$loadLinks$2$loadingState$1.invokeSuspend$lambda$0(coroutineScope, playerGeneratorViewModel, (Pair) obj2);
                    }
                };
                final CoroutineScope coroutineScope2 = this.$$this$launchSafe;
                final PlayerGeneratorViewModel playerGeneratorViewModel2 = this.this$0;
                this.label = 1;
                obj = generator.generateLinks(forceClearCache, set, function1, new Function1() { // from class: com.lagradost.cloudstream3.ui.player.PlayerGeneratorViewModel$loadLinks$2$loadingState$1$$ExternalSyntheticLambda1
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj2) {
                        return PlayerGeneratorViewModel$loadLinks$2$loadingState$1.invokeSuspend$lambda$1(coroutineScope2, playerGeneratorViewModel2, (SubtitleData) obj2);
                    }
                }, this.$index, false, this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            }
            return Unit.INSTANCE;
        }
        if (i != 1) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        return Unit.INSTANCE;
    }

    static final Unit invokeSuspend$lambda$0(CoroutineScope coroutineScope, PlayerGeneratorViewModel playerGeneratorViewModel, final Pair pair) {
        if (CoroutineScopeKt.isActive(coroutineScope)) {
            playerGeneratorViewModel.modifyState(new Function1() { // from class: com.lagradost.cloudstream3.ui.player.PlayerGeneratorViewModel$loadLinks$2$loadingState$1$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return PlayerGeneratorViewModel$loadLinks$2$loadingState$1.invokeSuspend$lambda$0$0(pair, (VideoState) obj);
                }
            });
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final VideoState invokeSuspend$lambda$0$0(Pair pair, VideoState videoState) {
        return videoState.add((Pair<? extends ExtractorLink, ExtractorUri>) pair);
    }

    static final Unit invokeSuspend$lambda$1(CoroutineScope coroutineScope, PlayerGeneratorViewModel playerGeneratorViewModel, final SubtitleData subtitleData) {
        if (CoroutineScopeKt.isActive(coroutineScope) && playerGeneratorViewModel.isValidSubtitle(subtitleData)) {
            playerGeneratorViewModel.modifyState(new Function1() { // from class: com.lagradost.cloudstream3.ui.player.PlayerGeneratorViewModel$loadLinks$2$loadingState$1$$ExternalSyntheticLambda3
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return PlayerGeneratorViewModel$loadLinks$2$loadingState$1.invokeSuspend$lambda$1$0(subtitleData, (VideoState) obj);
                }
            });
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final VideoState invokeSuspend$lambda$1$0(SubtitleData subtitleData, VideoState videoState) {
        return videoState.add(subtitleData);
    }
}
