package com.lagradost.cloudstream3.actions.temp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.lagradost.api.Log;
import com.lagradost.cloudstream3.MainAPIKt;
import com.lagradost.cloudstream3.actions.OpenInAppAction;
import com.lagradost.cloudstream3.actions.OpenInAppActionKt;
import com.lagradost.cloudstream3.ui.result.LinkLoadingResult;
import com.lagradost.cloudstream3.ui.result.ResultEpisode;
import com.lagradost.cloudstream3.utils.DataStoreHelper;
import com.lagradost.cloudstream3.utils.ExtractorLink;
import com.lagradost.cloudstream3.utils.TextUtilKt;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MpvRxPackage.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J8\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0096@¢\u0006\u0002\u0010\u0014J\u001a\u0010\u0015\u001a\u00020\t2\u0006\u0010\u0016\u001a\u00020\u00172\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0016R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0018"}, d2 = {"Lcom/lagradost/cloudstream3/actions/temp/MpvRxPackage;", "Lcom/lagradost/cloudstream3/actions/OpenInAppAction;", "<init>", "()V", "oneSource", "", "getOneSource", "()Z", "putExtra", "", "context", "Landroid/content/Context;", "intent", "Landroid/content/Intent;", "video", "Lcom/lagradost/cloudstream3/ui/result/ResultEpisode;", "result", "Lcom/lagradost/cloudstream3/ui/result/LinkLoadingResult;", "index", "", "(Landroid/content/Context;Landroid/content/Intent;Lcom/lagradost/cloudstream3/ui/result/ResultEpisode;Lcom/lagradost/cloudstream3/ui/result/LinkLoadingResult;Ljava/lang/Integer;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onResult", "activity", "Landroid/app/Activity;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class MpvRxPackage extends OpenInAppAction {
    private final boolean oneSource;

    public MpvRxPackage() {
        super(TextUtilKt.txt("mpvRx"), "app.gyrolet.mpvrx", "app.gyrolet.mpvrx.ui.player.PlayerActivity", null, 8, null);
        this.oneSource = true;
    }

    @Override // com.lagradost.cloudstream3.actions.VideoClickAction
    public boolean getOneSource() {
        return this.oneSource;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.lagradost.cloudstream3.actions.OpenInAppAction
    public Object putExtra(Context context, Intent intent, ResultEpisode resultEpisode, LinkLoadingResult linkLoadingResult, Integer num, Continuation<? super Unit> continuation) {
        intent.putExtra("title", resultEpisode.getName());
        List<ExtractorLink> links = linkLoadingResult.getLinks();
        Intrinsics.checkNotNull(num);
        ExtractorLink extractorLink = links.get(num.intValue());
        Map<String, String> headers = extractorLink.getHeaders();
        intent.setData(Uri.parse(extractorLink.getUrl()));
        if (!headers.isEmpty()) {
            Set<Map.Entry<String, String>> setEntrySet = headers.entrySet();
            ArrayList arrayList = new ArrayList();
            Iterator<T> it = setEntrySet.iterator();
            while (it.hasNext()) {
                Map.Entry entry = (Map.Entry) it.next();
                CollectionsKt.addAll(arrayList, CollectionsKt.listOf((Object[]) new String[]{entry.getKey(), entry.getValue()}));
            }
            intent.putExtra("headers", (String[]) arrayList.toArray(new String[0]));
        }
        if (MainAPIKt.isEpisodeBased(resultEpisode.getTvType())) {
            Integer season = resultEpisode.getSeason();
            if (season != null) {
                intent.putExtra("introdb_season", season.intValue());
            }
            intent.putExtra("introdb_episode", resultEpisode.getEpisode());
        }
        DataStoreHelper.PosDur viewPos = DataStoreHelper.INSTANCE.getViewPos(Boxing.boxInt(resultEpisode.getId()));
        Long lBoxLong = viewPos != null ? Boxing.boxLong(viewPos.getPosition()) : null;
        if (lBoxLong != null) {
            intent.putExtra("position", (int) lBoxLong.longValue());
        }
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
