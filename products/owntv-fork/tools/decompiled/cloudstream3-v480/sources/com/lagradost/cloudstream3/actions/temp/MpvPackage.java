package com.lagradost.cloudstream3.actions.temp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Parcelable;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.lagradost.api.Log;
import com.lagradost.cloudstream3.actions.OpenInAppAction;
import com.lagradost.cloudstream3.actions.OpenInAppActionKt;
import com.lagradost.cloudstream3.ui.player.SubtitleData;
import com.lagradost.cloudstream3.ui.result.LinkLoadingResult;
import com.lagradost.cloudstream3.ui.result.ResultEpisode;
import com.lagradost.cloudstream3.utils.DataStoreHelper;
import com.lagradost.cloudstream3.utils.ExtractorLink;
import com.lagradost.cloudstream3.utils.TextUtilKt;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MpvPackage.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0016\u0018\u00002\u00020\u0001B%\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003¢\u0006\u0004\b\u0006\u0010\u0007J8\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0096@¢\u0006\u0002\u0010\u0018J\u001a\u0010\u0019\u001a\u00020\r2\u0006\u0010\u001a\u001a\u00020\u001b2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0016R\u0014\u0010\b\u001a\u00020\tX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u001c"}, d2 = {"Lcom/lagradost/cloudstream3/actions/temp/MpvPackage;", "Lcom/lagradost/cloudstream3/actions/OpenInAppAction;", "appName", "", "packageName", "intentClass", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "oneSource", "", "getOneSource", "()Z", "putExtra", "", "context", "Landroid/content/Context;", "intent", "Landroid/content/Intent;", "video", "Lcom/lagradost/cloudstream3/ui/result/ResultEpisode;", "result", "Lcom/lagradost/cloudstream3/ui/result/LinkLoadingResult;", "index", "", "(Landroid/content/Context;Landroid/content/Intent;Lcom/lagradost/cloudstream3/ui/result/ResultEpisode;Lcom/lagradost/cloudstream3/ui/result/LinkLoadingResult;Ljava/lang/Integer;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onResult", "activity", "Landroid/app/Activity;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public class MpvPackage extends OpenInAppAction {
    private final boolean oneSource;

    public MpvPackage() {
        this(null, null, null, 7, null);
    }

    @Override // com.lagradost.cloudstream3.actions.OpenInAppAction
    public Object putExtra(Context context, Intent intent, ResultEpisode resultEpisode, LinkLoadingResult linkLoadingResult, Integer num, Continuation<? super Unit> continuation) {
        return putExtra$suspendImpl(this, context, intent, resultEpisode, linkLoadingResult, num, continuation);
    }

    public /* synthetic */ MpvPackage(String str, String str2, String str3, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? "MPV" : str, (i & 2) != 0 ? "is.xyz.mpv" : str2, (i & 4) != 0 ? "is.xyz.mpv.MPVActivity" : str3);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MpvPackage(String appName, String packageName, String intentClass) {
        super(TextUtilKt.txt(appName), packageName, intentClass, null, 8, null);
        Intrinsics.checkNotNullParameter(appName, "appName");
        Intrinsics.checkNotNullParameter(packageName, "packageName");
        Intrinsics.checkNotNullParameter(intentClass, "intentClass");
        this.oneSource = true;
    }

    @Override // com.lagradost.cloudstream3.actions.VideoClickAction
    public boolean getOneSource() {
        return this.oneSource;
    }

    static /* synthetic */ Object putExtra$suspendImpl(MpvPackage mpvPackage, Context context, Intent intent, ResultEpisode resultEpisode, LinkLoadingResult linkLoadingResult, Integer num, Continuation<? super Unit> continuation) throws IOException {
        String url;
        List<SubtitleData> subs = linkLoadingResult.getSubs();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(subs, 10));
        Iterator<T> it = subs.iterator();
        while (it.hasNext()) {
            arrayList.add(Uri.parse(((SubtitleData) it.next()).getUrl()));
        }
        intent.putExtra(CloudStreamPackage.SUBTITLE_EXTRA, (Parcelable[]) arrayList.toArray(new Uri[0]));
        intent.putExtra("title", resultEpisode.getName());
        if (num != null) {
            ExtractorLink extractorLink = (ExtractorLink) CollectionsKt.getOrNull(linkLoadingResult.getLinks(), num.intValue());
            if (extractorLink == null || (url = extractorLink.getUrl()) == null) {
                return Unit.INSTANCE;
            }
            Intrinsics.checkNotNull(intent.setDataAndType(Uri.parse(url), "video/*"));
        } else {
            OpenInAppActionKt.makeTempM3U8Intent(context, intent, linkLoadingResult);
        }
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
        int intExtra = intent != null ? intent.getIntExtra("position", -1) : -1;
        int intExtra2 = intent != null ? intent.getIntExtra(TypedValues.TransitionType.S_DURATION, -1) : -1;
        Log.INSTANCE.d("MPV", "Position: " + intExtra + ", Duration: " + intExtra2);
        OpenInAppActionKt.updateDurationAndPosition((long) intExtra, (long) intExtra2);
    }
}
