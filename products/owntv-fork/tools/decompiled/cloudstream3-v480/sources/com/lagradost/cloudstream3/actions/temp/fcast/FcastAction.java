package com.lagradost.cloudstream3.actions.temp.fcast;

import android.app.Activity;
import android.content.Context;
import com.lagradost.cloudstream3.CloudStreamApp;
import com.lagradost.cloudstream3.MainAPIKt;
import com.lagradost.cloudstream3.R;
import com.lagradost.cloudstream3.actions.VideoClickAction;
import com.lagradost.cloudstream3.ui.result.LinkLoadingResult;
import com.lagradost.cloudstream3.ui.result.ResultEpisode;
import com.lagradost.cloudstream3.utils.DataStoreHelper;
import com.lagradost.cloudstream3.utils.ExtractorLink;
import com.lagradost.cloudstream3.utils.ExtractorLinkType;
import com.lagradost.cloudstream3.utils.SingleSelectionHelper;
import com.lagradost.cloudstream3.utils.TextUtilKt;
import com.lagradost.cloudstream3.utils.UiText;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.coroutines.Continuation;
import kotlin.jdk7.AutoCloseableKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: FcastAction.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u001c\u0010\u0011\u001a\u00020\t2\b\u0010\u0012\u001a\u0004\u0018\u00010\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0016J2\u0010\u0016\u001a\u00020\u00172\b\u0010\u0012\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u0096@¢\u0006\u0002\u0010\u001cJ)\u0010\u001d\u001a\u00020\u00172\b\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\u0006\u0010 \u001a\u00020!2\b\u0010\"\u001a\u0004\u0018\u00010#H\u0002¢\u0006\u0002\u0010$R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\tX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u001a\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\rX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006%"}, d2 = {"Lcom/lagradost/cloudstream3/actions/temp/fcast/FcastAction;", "Lcom/lagradost/cloudstream3/actions/VideoClickAction;", "<init>", "()V", "name", "Lcom/lagradost/cloudstream3/utils/UiText;", "getName", "()Lcom/lagradost/cloudstream3/utils/UiText;", "oneSource", "", "getOneSource", "()Z", "sourceTypes", "", "Lcom/lagradost/cloudstream3/utils/ExtractorLinkType;", "getSourceTypes", "()Ljava/util/Set;", "shouldShow", "context", "Landroid/content/Context;", "video", "Lcom/lagradost/cloudstream3/ui/result/ResultEpisode;", "runAction", "", "result", "Lcom/lagradost/cloudstream3/ui/result/LinkLoadingResult;", "index", "", "(Landroid/content/Context;Lcom/lagradost/cloudstream3/ui/result/ResultEpisode;Lcom/lagradost/cloudstream3/ui/result/LinkLoadingResult;Ljava/lang/Integer;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "castTo", "device", "Lcom/lagradost/cloudstream3/actions/temp/fcast/PublicDeviceInfo;", "link", "Lcom/lagradost/cloudstream3/utils/ExtractorLink;", "position", "", "(Lcom/lagradost/cloudstream3/actions/temp/fcast/PublicDeviceInfo;Lcom/lagradost/cloudstream3/utils/ExtractorLink;Ljava/lang/Long;)V", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class FcastAction extends VideoClickAction {
    private final UiText name = TextUtilKt.txt("Fcast to device");
    private final boolean oneSource = true;
    private final Set<ExtractorLinkType> sourceTypes = SetsKt.setOf((Object[]) new ExtractorLinkType[]{ExtractorLinkType.VIDEO, ExtractorLinkType.DASH, ExtractorLinkType.M3U8});

    @Override // com.lagradost.cloudstream3.actions.VideoClickAction
    public UiText getName() {
        return this.name;
    }

    @Override // com.lagradost.cloudstream3.actions.VideoClickAction
    public boolean getOneSource() {
        return this.oneSource;
    }

    @Override // com.lagradost.cloudstream3.actions.VideoClickAction
    public Set<ExtractorLinkType> getSourceTypes() {
        return this.sourceTypes;
    }

    @Override // com.lagradost.cloudstream3.actions.VideoClickAction
    public boolean shouldShow(Context context, ResultEpisode video) {
        return !FcastManager.INSTANCE.getCurrentDevices().isEmpty();
    }

    @Override // com.lagradost.cloudstream3.actions.VideoClickAction
    public Object runAction(final Context context, final ResultEpisode resultEpisode, LinkLoadingResult linkLoadingResult, Integer num, Continuation<? super Unit> continuation) {
        final ExtractorLink extractorLink = (ExtractorLink) CollectionsKt.getOrNull(linkLoadingResult.getLinks(), num != null ? num.intValue() : 0);
        if (extractorLink == null) {
            return Unit.INSTANCE;
        }
        final List list = CollectionsKt.toList(FcastManager.INSTANCE.getCurrentDevices());
        return uiThread(new Callable() { // from class: com.lagradost.cloudstream3.actions.temp.fcast.FcastAction$$ExternalSyntheticLambda2
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return FcastAction.runAction$lambda$0(context, list, resultEpisode, this, extractorLink);
            }
        }, continuation);
    }

    static final Unit runAction$lambda$0(Context context, final List list, final ResultEpisode resultEpisode, final FcastAction fcastAction, final ExtractorLink extractorLink) {
        Activity activity;
        if (context == null || (activity = CloudStreamApp.INSTANCE.getActivity(context)) == null) {
            return null;
        }
        SingleSelectionHelper singleSelectionHelper = SingleSelectionHelper.INSTANCE;
        List list2 = list;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
        Iterator it = list2.iterator();
        while (it.hasNext()) {
            arrayList.add(((PublicDeviceInfo) it.next()).getName());
        }
        singleSelectionHelper.showBottomDialog(activity, arrayList, -1, TextUtilKt.txt(R.string.player_settings_select_cast_device, new Object[0]).asString(context), false, new Function0() { // from class: com.lagradost.cloudstream3.actions.temp.fcast.FcastAction$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Unit.INSTANCE;
            }
        }, new Function1() { // from class: com.lagradost.cloudstream3.actions.temp.fcast.FcastAction$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return FcastAction.runAction$lambda$0$2(resultEpisode, fcastAction, list, extractorLink, ((Integer) obj).intValue());
            }
        });
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit runAction$lambda$0$2(ResultEpisode resultEpisode, FcastAction fcastAction, List list, ExtractorLink extractorLink, int i) {
        DataStoreHelper.PosDur viewPos = DataStoreHelper.INSTANCE.getViewPos(Integer.valueOf(resultEpisode.getId()));
        fcastAction.castTo((PublicDeviceInfo) CollectionsKt.getOrNull(list, i), extractorLink, viewPos != null ? Long.valueOf(viewPos.getPosition()) : null);
        return Unit.INSTANCE;
    }

    private final void castTo(PublicDeviceInfo device, ExtractorLink link, Long position) {
        String host;
        if (device == null || (host = device.getHost()) == null) {
            return;
        }
        FcastSession fcastSession = new FcastSession(host);
        try {
            fcastSession.sendMessage(Opcode.Play, new PlayMessage(link.getType().getMimeType(), link.getUrl(), (String) null, position != null ? Double.valueOf(position.longValue() / 1000.0d) : null, (Double) null, MapsKt.plus(MapsKt.mapOf(TuplesKt.to("referer", link.getReferer()), TuplesKt.to("user-agent", MainAPIKt.USER_AGENT)), link.getHeaders()), 20, (DefaultConstructorMarker) null));
            Unit unit = Unit.INSTANCE;
            AutoCloseableKt.closeFinally(fcastSession, null);
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                AutoCloseableKt.closeFinally(fcastSession, th);
                throw th2;
            }
        }
    }
}
