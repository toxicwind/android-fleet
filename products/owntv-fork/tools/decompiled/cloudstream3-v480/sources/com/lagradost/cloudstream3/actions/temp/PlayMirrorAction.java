package com.lagradost.cloudstream3.actions.temp;

import android.app.Activity;
import android.content.Context;
import com.lagradost.cloudstream3.R;
import com.lagradost.cloudstream3.actions.VideoClickAction;
import com.lagradost.cloudstream3.ui.player.ExtractorUri;
import com.lagradost.cloudstream3.ui.player.GeneratorPlayer;
import com.lagradost.cloudstream3.ui.player.IGeneratorKt;
import com.lagradost.cloudstream3.ui.player.SubtitleData;
import com.lagradost.cloudstream3.ui.player.VideoGenerator;
import com.lagradost.cloudstream3.ui.result.LinkLoadingResult;
import com.lagradost.cloudstream3.ui.result.ResultEpisode;
import com.lagradost.cloudstream3.utils.ExtractorLink;
import com.lagradost.cloudstream3.utils.ExtractorLinkType;
import com.lagradost.cloudstream3.utils.TextUtilKt;
import com.lagradost.cloudstream3.utils.UIHelper;
import com.lagradost.cloudstream3.utils.UiText;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: PlayMirrorAction.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u001c\u0010\u0012\u001a\u00020\t2\b\u0010\u0013\u001a\u0004\u0018\u00010\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0016J2\u0010\u0017\u001a\u00020\u00182\b\u0010\u0013\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cH\u0096@¢\u0006\u0002\u0010\u001dR\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\tX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\f\u001a\u00020\tX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u001a\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u001e"}, d2 = {"Lcom/lagradost/cloudstream3/actions/temp/PlayMirrorAction;", "Lcom/lagradost/cloudstream3/actions/VideoClickAction;", "<init>", "()V", "name", "Lcom/lagradost/cloudstream3/utils/UiText;", "getName", "()Lcom/lagradost/cloudstream3/utils/UiText;", "oneSource", "", "getOneSource", "()Z", "isPlayer", "sourceTypes", "", "Lcom/lagradost/cloudstream3/utils/ExtractorLinkType;", "getSourceTypes", "()Ljava/util/Set;", "shouldShow", "context", "Landroid/content/Context;", "video", "Lcom/lagradost/cloudstream3/ui/result/ResultEpisode;", "runAction", "", "result", "Lcom/lagradost/cloudstream3/ui/result/LinkLoadingResult;", "index", "", "(Landroid/content/Context;Lcom/lagradost/cloudstream3/ui/result/ResultEpisode;Lcom/lagradost/cloudstream3/ui/result/LinkLoadingResult;Ljava/lang/Integer;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class PlayMirrorAction extends VideoClickAction {
    private final UiText name = TextUtilKt.txt(R.string.episode_action_play_mirror, new Object[0]);
    private final boolean oneSource = true;
    private final boolean isPlayer = true;
    private final Set<ExtractorLinkType> sourceTypes = IGeneratorKt.getLOADTYPE_INAPP();

    @Override // com.lagradost.cloudstream3.actions.VideoClickAction
    public boolean shouldShow(Context context, ResultEpisode video) {
        return true;
    }

    @Override // com.lagradost.cloudstream3.actions.VideoClickAction
    public UiText getName() {
        return this.name;
    }

    @Override // com.lagradost.cloudstream3.actions.VideoClickAction
    public boolean getOneSource() {
        return this.oneSource;
    }

    @Override // com.lagradost.cloudstream3.actions.VideoClickAction
    /* JADX INFO: renamed from: isPlayer, reason: from getter */
    public boolean getIsPlayer() {
        return this.isPlayer;
    }

    @Override // com.lagradost.cloudstream3.actions.VideoClickAction
    public Set<ExtractorLinkType> getSourceTypes() {
        return this.sourceTypes;
    }

    @Override // com.lagradost.cloudstream3.actions.VideoClickAction
    public Object runAction(Context context, final ResultEpisode resultEpisode, final LinkLoadingResult linkLoadingResult, final Integer num, Continuation<? super Unit> continuation) {
        ExtractorLink extractorLink = null;
        Activity activity = context instanceof Activity ? (Activity) context : null;
        if (activity == null) {
            return Unit.INSTANCE;
        }
        if (num != null) {
            extractorLink = linkLoadingResult.getLinks().get(num.intValue());
        }
        final ExtractorLink extractorLink2 = extractorLink;
        final List listListOf = CollectionsKt.listOf(resultEpisode);
        UIHelper.navigate$default(UIHelper.INSTANCE, activity, R.id.global_to_navigation_player, GeneratorPlayer.INSTANCE.newInstance(new VideoGenerator<ResultEpisode>(listListOf) { // from class: com.lagradost.cloudstream3.actions.temp.PlayMirrorAction$runAction$generatorMirror$1
            private final boolean canSkipLoading;
            private final boolean hasCache;

            @Override // com.lagradost.cloudstream3.ui.player.VideoGenerator
            public boolean getHasCache() {
                return this.hasCache;
            }

            @Override // com.lagradost.cloudstream3.ui.player.VideoGenerator
            public boolean getCanSkipLoading() {
                return this.canSkipLoading;
            }

            @Override // com.lagradost.cloudstream3.ui.player.VideoGenerator
            public Integer getId(int index) {
                return Integer.valueOf(resultEpisode.getId());
            }

            @Override // com.lagradost.cloudstream3.ui.player.VideoGenerator
            public Object generateLinks(boolean z, Set<? extends ExtractorLinkType> set, Function1<? super Pair<? extends ExtractorLink, ExtractorUri>, Unit> function1, Function1<? super SubtitleData, Unit> function2, int i, boolean z2, Continuation<? super Boolean> continuation2) {
                Integer num2 = num;
                if (num2 != null) {
                    ExtractorLink extractorLink3 = extractorLink2;
                    num2.intValue();
                    function1.invoke(TuplesKt.to(extractorLink3, null));
                }
                Iterator<T> it = linkLoadingResult.getSubs().iterator();
                while (it.hasNext()) {
                    function2.invoke((SubtitleData) it.next());
                }
                return Boxing.boxBoolean(true);
            }
        }, 0, linkLoadingResult.getSyncData()), null, 4, null);
        return Unit.INSTANCE;
    }
}
