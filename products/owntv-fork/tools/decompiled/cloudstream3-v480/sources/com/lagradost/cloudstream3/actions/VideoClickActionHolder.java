package com.lagradost.cloudstream3.actions;

import android.app.Activity;
import com.lagradost.api.Log;
import com.lagradost.cloudstream3.actions.temp.BiglyBTPackage;
import com.lagradost.cloudstream3.actions.temp.CopyClipboardAction;
import com.lagradost.cloudstream3.actions.temp.JustPlayerPackage;
import com.lagradost.cloudstream3.actions.temp.LibreTorrentPackage;
import com.lagradost.cloudstream3.actions.temp.MpvExPackage;
import com.lagradost.cloudstream3.actions.temp.MpvKtPackage;
import com.lagradost.cloudstream3.actions.temp.MpvKtPreviewPackage;
import com.lagradost.cloudstream3.actions.temp.MpvPackage;
import com.lagradost.cloudstream3.actions.temp.MpvRxPackage;
import com.lagradost.cloudstream3.actions.temp.MpvYTDLPackage;
import com.lagradost.cloudstream3.actions.temp.NextPlayerPackage;
import com.lagradost.cloudstream3.actions.temp.OnlyPlayer;
import com.lagradost.cloudstream3.actions.temp.PlayInBrowserAction;
import com.lagradost.cloudstream3.actions.temp.PlayMirrorAction;
import com.lagradost.cloudstream3.actions.temp.ViewM3U8Action;
import com.lagradost.cloudstream3.actions.temp.VlcNightlyPackage;
import com.lagradost.cloudstream3.actions.temp.VlcPackage;
import com.lagradost.cloudstream3.actions.temp.WebVideoCastPackage;
import com.lagradost.cloudstream3.actions.temp.fcast.FcastAction;
import com.lagradost.cloudstream3.ui.result.ResultEpisode;
import com.lagradost.cloudstream3.utils.AtomicList;
import com.lagradost.cloudstream3.utils.AtomicMutableList;
import com.lagradost.cloudstream3.utils.Coroutines;
import com.lagradost.cloudstream3.utils.UiText;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: VideoClickAction.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J*\u0010\u000b\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\n0\r0\f2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0011\u001a\u00020\u0012J\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0014\u001a\u00020\nJ\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0016\u001a\u00020\u0017J\u0017\u0010\u0018\u001a\u0004\u0018\u00010\n2\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017¢\u0006\u0002\u0010\u0019J\u0018\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00060\u001b2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0010R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u000e\u0010\t\u001a\u00020\nX\u0082T¢\u0006\u0002\n\u0000¨\u0006\u001c"}, d2 = {"Lcom/lagradost/cloudstream3/actions/VideoClickActionHolder;", "", "<init>", "()V", "allVideoClickActions", "Lcom/lagradost/cloudstream3/utils/AtomicMutableList;", "Lcom/lagradost/cloudstream3/actions/VideoClickAction;", "getAllVideoClickActions", "()Lcom/lagradost/cloudstream3/utils/AtomicMutableList;", "ACTION_ID_OFFSET", "", "makeOptionMap", "", "Lkotlin/Pair;", "Lcom/lagradost/cloudstream3/utils/UiText;", "activity", "Landroid/app/Activity;", "video", "Lcom/lagradost/cloudstream3/ui/result/ResultEpisode;", "getActionById", "id", "getByUniqueId", "uniqueId", "", "uniqueIdToId", "(Ljava/lang/String;)Ljava/lang/Integer;", "getPlayers", "Lcom/lagradost/cloudstream3/utils/AtomicList;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class VideoClickActionHolder {
    private static final int ACTION_ID_OFFSET = 1000;
    public static final VideoClickActionHolder INSTANCE = new VideoClickActionHolder();
    private static final AtomicMutableList<VideoClickAction> allVideoClickActions;

    private VideoClickActionHolder() {
    }

    static {
        AtomicMutableList<VideoClickAction> atomicMutableListAtomicListOf = Coroutines.INSTANCE.atomicListOf(new PlayInBrowserAction(), new CopyClipboardAction(), new ViewM3U8Action(), new PlayMirrorAction(), new VlcPackage(), new MpvPackage(null, null, null, 7, null), new MpvExPackage(), new NextPlayerPackage(), new JustPlayerPackage(), new FcastAction(), new LibreTorrentPackage(), new BiglyBTPackage(), new VlcNightlyPackage(), new WebVideoCastPackage(), new MpvYTDLPackage(), new MpvKtPackage(null, null, 3, null), new MpvKtPreviewPackage(), new OnlyPlayer(), new MpvRxPackage(), new AlwaysAskAction());
        allVideoClickActions = atomicMutableListAtomicListOf;
        Log log = Log.INSTANCE;
        StringBuilder sb = new StringBuilder("allVideoClickActions: ");
        AtomicMutableList<VideoClickAction> atomicMutableList = atomicMutableListAtomicListOf;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(atomicMutableList, 10));
        Iterator<VideoClickAction> it = atomicMutableList.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().uniqueId());
        }
        sb.append(arrayList);
        log.d("VideoClickActionHolder", sb.toString());
    }

    public final AtomicMutableList<VideoClickAction> getAllVideoClickActions() {
        return allVideoClickActions;
    }

    public final List<Pair<UiText, Integer>> makeOptionMap(Activity activity, ResultEpisode video) {
        Intrinsics.checkNotNullParameter(video, "video");
        AtomicMutableList<VideoClickAction> atomicMutableList = allVideoClickActions;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(atomicMutableList, 10));
        int i = 0;
        for (VideoClickAction videoClickAction : atomicMutableList) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            arrayList.add(TuplesKt.to(videoClickAction, Integer.valueOf(i + 1000)));
            i = i2;
        }
        ArrayList arrayList2 = new ArrayList();
        for (Object obj : arrayList) {
            if (((VideoClickAction) ((Pair) obj).getFirst()).shouldShowSafe(activity, video)) {
                arrayList2.add(obj);
            }
        }
        ArrayList<Pair> arrayList3 = arrayList2;
        ArrayList arrayList4 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList3, 10));
        for (Pair pair : arrayList3) {
            arrayList4.add(TuplesKt.to(((VideoClickAction) pair.getFirst()).getName(), pair.getSecond()));
        }
        return arrayList4;
    }

    public final VideoClickAction getActionById(int id) {
        return (VideoClickAction) CollectionsKt.getOrNull(allVideoClickActions, id - 1000);
    }

    public final VideoClickAction getByUniqueId(String uniqueId) {
        VideoClickAction next;
        Intrinsics.checkNotNullParameter(uniqueId, "uniqueId");
        Iterator<VideoClickAction> it = allVideoClickActions.iterator();
        while (it.hasNext()) {
            next = it.next();
            if (Intrinsics.areEqual(next.uniqueId(), uniqueId)) {
                return next;
            }
        }
        next = null;
        return next;
    }

    public final Integer uniqueIdToId(String uniqueId) {
        Object next;
        if (uniqueId == null) {
            return null;
        }
        AtomicMutableList<VideoClickAction> atomicMutableList = allVideoClickActions;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(atomicMutableList, 10));
        int i = 0;
        for (VideoClickAction videoClickAction : atomicMutableList) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            arrayList.add(TuplesKt.to(videoClickAction, Integer.valueOf(i + 1000)));
            i = i2;
        }
        Iterator it = arrayList.iterator();
        do {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
        } while (!Intrinsics.areEqual(((VideoClickAction) ((Pair) next).getFirst()).uniqueId(), uniqueId));
        Pair pair = (Pair) next;
        if (pair != null) {
            return (Integer) pair.getSecond();
        }
        return null;
    }

    public static /* synthetic */ AtomicList getPlayers$default(VideoClickActionHolder videoClickActionHolder, Activity activity, int i, Object obj) {
        if ((i & 1) != 0) {
            activity = null;
        }
        return videoClickActionHolder.getPlayers(activity);
    }

    static final boolean getPlayers$lambda$0(Activity activity, VideoClickAction it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return it.getIsPlayer() && it.shouldShowSafe(activity, null);
    }

    public final AtomicList<VideoClickAction> getPlayers(final Activity activity) {
        return allVideoClickActions.filter(new Function1() { // from class: com.lagradost.cloudstream3.actions.VideoClickActionHolder$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Boolean.valueOf(VideoClickActionHolder.getPlayers$lambda$0(activity, (VideoClickAction) obj));
            }
        });
    }
}
