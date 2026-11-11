package com.lagradost.cloudstream3.actions.temp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import androidx.mediarouter.media.MediaControlIntent;
import com.lagradost.cloudstream3.MainAPIKt;
import com.lagradost.cloudstream3.actions.OpenInAppAction;
import com.lagradost.cloudstream3.ui.player.SubtitleData;
import com.lagradost.cloudstream3.ui.result.LinkLoadingResult;
import com.lagradost.cloudstream3.ui.result.ResultEpisode;
import com.lagradost.cloudstream3.utils.ExtractorLink;
import com.lagradost.cloudstream3.utils.ExtractorLinkType;
import com.lagradost.cloudstream3.utils.TextUtilKt;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: WebVideoCastPackage.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J8\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0096@¢\u0006\u0002\u0010\u0019J\u001a\u0010\u001a\u001a\u00020\u000e2\u0006\u0010\u001b\u001a\u00020\u001c2\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0016R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u001a\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u001d"}, d2 = {"Lcom/lagradost/cloudstream3/actions/temp/WebVideoCastPackage;", "Lcom/lagradost/cloudstream3/actions/OpenInAppAction;", "<init>", "()V", "oneSource", "", "getOneSource", "()Z", "sourceTypes", "", "Lcom/lagradost/cloudstream3/utils/ExtractorLinkType;", "getSourceTypes", "()Ljava/util/Set;", "putExtra", "", "context", "Landroid/content/Context;", "intent", "Landroid/content/Intent;", "video", "Lcom/lagradost/cloudstream3/ui/result/ResultEpisode;", "result", "Lcom/lagradost/cloudstream3/ui/result/LinkLoadingResult;", "index", "", "(Landroid/content/Context;Landroid/content/Intent;Lcom/lagradost/cloudstream3/ui/result/ResultEpisode;Lcom/lagradost/cloudstream3/ui/result/LinkLoadingResult;Ljava/lang/Integer;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onResult", "activity", "Landroid/app/Activity;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class WebVideoCastPackage extends OpenInAppAction {
    private final boolean oneSource;
    private final Set<ExtractorLinkType> sourceTypes;

    @Override // com.lagradost.cloudstream3.actions.OpenInAppAction
    public void onResult(Activity activity, Intent intent) {
        Intrinsics.checkNotNullParameter(activity, "activity");
    }

    public WebVideoCastPackage() {
        super(TextUtilKt.txt("Web Video Cast"), "com.instantbits.cast.webvideo", null, null, 12, null);
        this.oneSource = true;
        this.sourceTypes = SetsKt.setOf((Object[]) new ExtractorLinkType[]{ExtractorLinkType.VIDEO, ExtractorLinkType.DASH, ExtractorLinkType.M3U8});
    }

    @Override // com.lagradost.cloudstream3.actions.VideoClickAction
    public boolean getOneSource() {
        return this.oneSource;
    }

    @Override // com.lagradost.cloudstream3.actions.VideoClickAction
    public Set<ExtractorLinkType> getSourceTypes() {
        return this.sourceTypes;
    }

    @Override // com.lagradost.cloudstream3.actions.OpenInAppAction
    public Object putExtra(Context context, Intent intent, ResultEpisode resultEpisode, LinkLoadingResult linkLoadingResult, Integer num, Continuation<? super Unit> continuation) {
        ExtractorLink extractorLink = linkLoadingResult.getLinks().get(num != null ? num.intValue() : 0);
        intent.setDataAndType(Uri.parse(extractorLink.getUrl()), "video/*");
        String name = resultEpisode.getName();
        if (name == null) {
            name = resultEpisode.getHeaderName();
        }
        List<SubtitleData> subs = linkLoadingResult.getSubs();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(subs, 10));
        Iterator<T> it = subs.iterator();
        while (it.hasNext()) {
            arrayList.add(Uri.parse(((SubtitleData) it.next()).getUrl()));
        }
        intent.putExtra(CloudStreamPackage.SUBTITLE_EXTRA, (Parcelable[]) arrayList.toArray(new Uri[0]));
        intent.putExtra("title", name);
        String poster = resultEpisode.getPoster();
        if (poster != null) {
            intent.putExtra("poster", poster);
        }
        Bundle bundle = new Bundle();
        if (!StringsKt.isBlank(extractorLink.getReferer())) {
            bundle.putString("Referer", extractorLink.getReferer());
        }
        bundle.putString("User-Agent", MainAPIKt.USER_AGENT);
        for (Map.Entry<String, String> entry : extractorLink.getHeaders().entrySet()) {
            bundle.putString(entry.getKey(), entry.getValue());
        }
        intent.putExtra(MediaControlIntent.EXTRA_ITEM_HTTP_HEADERS, bundle);
        intent.putExtra("secure_uri", true);
        return Unit.INSTANCE;
    }
}
