package com.lagradost.cloudstream3.ui.player;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.lagradost.cloudstream3.actions.temp.CloudStreamPackage;
import com.lagradost.cloudstream3.utils.ExtractorLink;
import com.lagradost.cloudstream3.utils.ExtractorLinkType;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ExtractorLinkGenerator.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\u0018\u00002\u00020\u0001B#\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003¢\u0006\u0004\b\u0007\u0010\bJl\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\n2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r2\"\u0010\u000f\u001a\u001e\u0012\u0014\u0012\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00120\u0011\u0012\u0004\u0012\u00020\u00130\u00102\u0012\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00130\u00102\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\nH\u0096@¢\u0006\u0002\u0010\u0018R\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lcom/lagradost/cloudstream3/ui/player/ExtractorLinkGenerator;", "Lcom/lagradost/cloudstream3/ui/player/NoVideoGenerator;", CloudStreamPackage.LINKS_EXTRA, "", "Lcom/lagradost/cloudstream3/utils/ExtractorLink;", "subtitles", "Lcom/lagradost/cloudstream3/ui/player/SubtitleData;", "<init>", "(Ljava/util/List;Ljava/util/List;)V", "generateLinks", "", "clearCache", "sourceTypes", "", "Lcom/lagradost/cloudstream3/utils/ExtractorLinkType;", "callback", "Lkotlin/Function1;", "Lkotlin/Pair;", "Lcom/lagradost/cloudstream3/ui/player/ExtractorUri;", "", "subtitleCallback", TypedValues.CycleType.S_WAVE_OFFSET, "", "isCasting", "(ZLjava/util/Set;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;IZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class ExtractorLinkGenerator extends NoVideoGenerator {
    private final List<ExtractorLink> links;
    private final List<SubtitleData> subtitles;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public ExtractorLinkGenerator(List<? extends ExtractorLink> links, List<SubtitleData> subtitles) {
        super(null);
        Intrinsics.checkNotNullParameter(links, "links");
        Intrinsics.checkNotNullParameter(subtitles, "subtitles");
        this.links = links;
        this.subtitles = subtitles;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.lagradost.cloudstream3.ui.player.VideoGenerator
    public Object generateLinks(boolean z, Set<? extends ExtractorLinkType> set, Function1<? super Pair<? extends ExtractorLink, ExtractorUri>, Unit> function1, Function1<? super SubtitleData, Unit> function2, int i, boolean z2, Continuation<? super Boolean> continuation) {
        Iterator<T> it = this.subtitles.iterator();
        while (it.hasNext()) {
            function2.invoke(it.next());
        }
        for (ExtractorLink extractorLink : this.links) {
            if (set.contains(extractorLink.getType())) {
                function1.invoke(TuplesKt.to(extractorLink, null));
            }
        }
        return Boxing.boxBoolean(true);
    }
}
