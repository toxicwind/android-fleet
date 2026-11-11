package com.lagradost.cloudstream3.actions.temp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Parcelable;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.lagradost.cloudstream3.actions.OpenInAppAction;
import com.lagradost.cloudstream3.actions.OpenInAppActionKt;
import com.lagradost.cloudstream3.ui.player.SubtitleData;
import com.lagradost.cloudstream3.ui.result.LinkLoadingResult;
import com.lagradost.cloudstream3.ui.result.ResultEpisode;
import com.lagradost.cloudstream3.utils.DataStoreHelper;
import com.lagradost.cloudstream3.utils.ExtractorLink;
import com.lagradost.cloudstream3.utils.ExtractorLinkType;
import com.lagradost.cloudstream3.utils.TextUtilKt;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MpvKtPackage.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0016\u0018\u00002\u00020\u0001B\u001b\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J8\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u0096@¢\u0006\u0002\u0010\u001cJ\u001a\u0010\u001d\u001a\u00020\u00112\u0006\u0010\u001e\u001a\u00020\u001f2\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0016R\u0014\u0010\u0007\u001a\u00020\bX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u001a\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006 "}, d2 = {"Lcom/lagradost/cloudstream3/actions/temp/MpvKtPackage;", "Lcom/lagradost/cloudstream3/actions/OpenInAppAction;", "appName", "", "packageName", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "oneSource", "", "getOneSource", "()Z", "sourceTypes", "", "Lcom/lagradost/cloudstream3/utils/ExtractorLinkType;", "getSourceTypes", "()Ljava/util/Set;", "putExtra", "", "context", "Landroid/content/Context;", "intent", "Landroid/content/Intent;", "video", "Lcom/lagradost/cloudstream3/ui/result/ResultEpisode;", "result", "Lcom/lagradost/cloudstream3/ui/result/LinkLoadingResult;", "index", "", "(Landroid/content/Context;Landroid/content/Intent;Lcom/lagradost/cloudstream3/ui/result/ResultEpisode;Lcom/lagradost/cloudstream3/ui/result/LinkLoadingResult;Ljava/lang/Integer;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onResult", "activity", "Landroid/app/Activity;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public class MpvKtPackage extends OpenInAppAction {
    private final boolean oneSource;
    private final Set<ExtractorLinkType> sourceTypes;

    /* JADX WARN: Multi-variable type inference failed */
    public MpvKtPackage() {
        this(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
    }

    @Override // com.lagradost.cloudstream3.actions.OpenInAppAction
    public Object putExtra(Context context, Intent intent, ResultEpisode resultEpisode, LinkLoadingResult linkLoadingResult, Integer num, Continuation<? super Unit> continuation) {
        return putExtra$suspendImpl(this, context, intent, resultEpisode, linkLoadingResult, num, continuation);
    }

    public /* synthetic */ MpvKtPackage(String str, String str2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? "mpvKt" : str, (i & 2) != 0 ? "live.mehiz.mpvkt" : str2);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MpvKtPackage(String appName, String packageName) {
        super(TextUtilKt.txt(appName), packageName, "live.mehiz.mpvkt.ui.player.PlayerActivity", null, 8, null);
        Intrinsics.checkNotNullParameter(appName, "appName");
        Intrinsics.checkNotNullParameter(packageName, "packageName");
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

    static /* synthetic */ Object putExtra$suspendImpl(MpvKtPackage mpvKtPackage, Context context, Intent intent, ResultEpisode resultEpisode, LinkLoadingResult linkLoadingResult, Integer num, Continuation<? super Unit> continuation) {
        ExtractorLink extractorLink = (ExtractorLink) CollectionsKt.getOrNull(linkLoadingResult.getLinks(), num != null ? num.intValue() : 0);
        if (extractorLink == null) {
            return Unit.INSTANCE;
        }
        List<SubtitleData> subs = linkLoadingResult.getSubs();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(subs, 10));
        Iterator<T> it = subs.iterator();
        while (it.hasNext()) {
            arrayList.add(Uri.parse(((SubtitleData) it.next()).getUrl()));
        }
        intent.putExtra(CloudStreamPackage.SUBTITLE_EXTRA, (Parcelable[]) arrayList.toArray(new Uri[0]));
        intent.setDataAndType(Uri.parse(extractorLink.getUrl()), "video/*");
        DataStoreHelper.PosDur viewPos = DataStoreHelper.INSTANCE.getViewPos(Boxing.boxInt(resultEpisode.getId()));
        Long lBoxLong = viewPos != null ? Boxing.boxLong(viewPos.getPosition()) : null;
        if (lBoxLong != null) {
            intent.putExtra("position", (int) lBoxLong.longValue());
        }
        intent.putExtra("secure_uri", true);
        return Unit.INSTANCE;
    }

    @Override // com.lagradost.cloudstream3.actions.OpenInAppAction
    public void onResult(Activity activity, Intent intent) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        OpenInAppActionKt.updateDurationAndPosition(intent != null ? intent.getIntExtra("position", -1) : -1L, intent != null ? intent.getIntExtra(TypedValues.TransitionType.S_DURATION, -1) : -1L);
    }
}
