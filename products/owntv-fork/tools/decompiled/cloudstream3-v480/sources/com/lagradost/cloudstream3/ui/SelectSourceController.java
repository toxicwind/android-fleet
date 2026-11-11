package com.lagradost.cloudstream3.ui;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AlertDialog;
import com.fasterxml.jackson.core.type.TypeReference;
import com.google.android.gms.cast.MediaInfo;
import com.google.android.gms.cast.MediaLoadOptions;
import com.google.android.gms.cast.MediaQueueItem;
import com.google.android.gms.cast.MediaStatus;
import com.google.android.gms.cast.MediaTrack;
import com.google.android.gms.cast.TextTrackStyle;
import com.google.android.gms.cast.framework.CastSession;
import com.google.android.gms.cast.framework.media.MediaQueue;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.cast.framework.media.uicontroller.UIController;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.lagradost.cloudstream3.MainAPIKt;
import com.lagradost.cloudstream3.R;
import com.lagradost.cloudstream3.mvvm.ArchComponentExtKt;
import com.lagradost.cloudstream3.mvvm.Resource;
import com.lagradost.cloudstream3.ui.player.RepoLinkGenerator;
import com.lagradost.cloudstream3.ui.player.SubtitleData;
import com.lagradost.cloudstream3.ui.result.ResultEpisode;
import com.lagradost.cloudstream3.ui.subtitles.ChromecastSubtitlesFragment;
import com.lagradost.cloudstream3.ui.subtitles.SaveChromeCaptionStyle;
import com.lagradost.cloudstream3.utils.AppContextUtils;
import com.lagradost.cloudstream3.utils.AppUtils;
import com.lagradost.cloudstream3.utils.CastHelper;
import com.lagradost.cloudstream3.utils.Coroutines;
import com.lagradost.cloudstream3.utils.DataStore;
import com.lagradost.cloudstream3.utils.DataStoreHelper;
import com.lagradost.cloudstream3.utils.ExtractorLink;
import com.lagradost.cloudstream3.utils.Qualities;
import com.lagradost.cloudstream3.utils.UIHelper;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerializationException;
import kotlinx.serialization.SerializersKt;
import kotlinx.serialization.modules.SerializersModule;
import org.json.JSONObject;

/* JADX INFO: compiled from: ControllerActivity.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\n\u0010\f\u001a\u0004\u0018\u00010\rH\u0002J\b\u0010\u0013\u001a\u00020\u0014H\u0016J\u0010\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\u0017H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u001a\u0010\u000e\u001a\u00020\u000fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u0010\"\u0004\b\u0011\u0010\u0012¨\u0006\u0018"}, d2 = {"Lcom/lagradost/cloudstream3/ui/SelectSourceController;", "Lcom/google/android/gms/cast/framework/media/uicontroller/UIController;", "view", "Landroid/widget/ImageView;", "activity", "Lcom/lagradost/cloudstream3/ui/ControllerActivity;", "<init>", "(Landroid/widget/ImageView;Lcom/lagradost/cloudstream3/ui/ControllerActivity;)V", "getView", "()Landroid/widget/ImageView;", "getActivity", "()Lcom/lagradost/cloudstream3/ui/ControllerActivity;", "getCurrentMetaData", "Lcom/lagradost/cloudstream3/ui/MetadataHolder;", "isLoadingMore", "", "()Z", "setLoadingMore", "(Z)V", "onMediaStatusUpdated", "", "onSessionConnected", "castSession", "Lcom/google/android/gms/cast/framework/CastSession;", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class SelectSourceController extends UIController {
    private final ControllerActivity activity;
    private boolean isLoadingMore;
    private final ImageView view;

    public SelectSourceController(ImageView view, ControllerActivity activity) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(activity, "activity");
        this.view = view;
        this.activity = activity;
        view.setImageResource(R.drawable.ic_baseline_playlist_play_24);
        view.setOnClickListener(new View.OnClickListener() { // from class: com.lagradost.cloudstream3.ui.SelectSourceController$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                SelectSourceController._init_$lambda$0(this.f$0, view2);
            }
        });
    }

    public final ControllerActivity getActivity() {
        return this.activity;
    }

    public final ImageView getView() {
        return this.view;
    }

    static final void _init_$lambda$0(final SelectSourceController selectSourceController, View view) {
        final ArrayList arrayList;
        int i;
        MediaStatus mediaStatus;
        String contentId;
        MediaQueueItem currentItem;
        MediaInfo media;
        MediaQueueItem currentItem2;
        MediaInfo media2;
        MediaInfo mediaInfo;
        List<MediaTrack> mediaTracks;
        final MetadataHolder currentMetaData = selectSourceController.getCurrentMetaData();
        if (currentMetaData != null) {
            List<ExtractorLink> currentLinks = currentMetaData.getCurrentLinks();
            if (currentLinks.isEmpty()) {
                return;
            }
            RemoteMediaClient remoteMediaClient = selectSourceController.getRemoteMediaClient();
            if ((remoteMediaClient != null ? remoteMediaClient.getCurrentItem() : null) != null) {
                RemoteMediaClient remoteMediaClient2 = selectSourceController.getRemoteMediaClient();
                if (remoteMediaClient2 == null || (mediaInfo = remoteMediaClient2.getMediaInfo()) == null || (mediaTracks = mediaInfo.getMediaTracks()) == null) {
                    arrayList = new ArrayList();
                } else {
                    ArrayList arrayList2 = new ArrayList();
                    for (Object obj : mediaTracks) {
                        if (((MediaTrack) obj).getType() == 1) {
                            arrayList2.add(obj);
                        }
                    }
                    arrayList = arrayList2;
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(selectSourceController.view.getContext(), R.style.AlertDialogCustomBlack);
                builder.setView(R.layout.sort_bottom_sheet);
                final AlertDialog alertDialogCreate = builder.create();
                Intrinsics.checkNotNullExpressionValue(alertDialogCreate, "create(...)");
                alertDialogCreate.show();
                View viewFindViewById = alertDialogCreate.findViewById(R.id.sort_providers);
                Intrinsics.checkNotNull(viewFindViewById);
                ListView listView = (ListView) viewFindViewById;
                View viewFindViewById2 = alertDialogCreate.findViewById(R.id.sort_subtitles);
                Intrinsics.checkNotNull(viewFindViewById2);
                ListView listView2 = (ListView) viewFindViewById2;
                int i2 = -1;
                int i3 = 0;
                if (arrayList.isEmpty()) {
                    LinearLayout linearLayout = (LinearLayout) alertDialogCreate.findViewById(R.id.sort_subtitles_holder);
                    if (linearLayout != null) {
                        linearLayout.setVisibility(8);
                    }
                } else {
                    ArrayAdapter arrayAdapter = new ArrayAdapter(selectSourceController.view.getContext(), R.layout.sort_bottom_single_choice);
                    arrayAdapter.add(selectSourceController.view.getContext().getString(R.string.no_subtitles));
                    ArrayList arrayList3 = arrayList;
                    ArrayList arrayList4 = new ArrayList();
                    Iterator it = arrayList3.iterator();
                    while (it.hasNext()) {
                        String name = ((MediaTrack) it.next()).getName();
                        if (name != null) {
                            arrayList4.add(name);
                        }
                    }
                    arrayAdapter.addAll(arrayList4);
                    listView2.setChoiceMode(1);
                    listView2.setAdapter((ListAdapter) arrayAdapter);
                    RemoteMediaClient remoteMediaClient3 = selectSourceController.getRemoteMediaClient();
                    long[] activeTrackIds = (remoteMediaClient3 == null || (mediaStatus = remoteMediaClient3.getMediaStatus()) == null) ? null : mediaStatus.getActiveTrackIds();
                    if (activeTrackIds == null) {
                        i = 0;
                    } else {
                        ArrayList arrayList5 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList3, 10));
                        Iterator it2 = arrayList3.iterator();
                        while (it2.hasNext()) {
                            arrayList5.add(Long.valueOf(((MediaTrack) it2.next()).getId()));
                        }
                        Iterator it3 = arrayList5.iterator();
                        int i4 = 0;
                        while (true) {
                            if (!it3.hasNext()) {
                                i4 = -1;
                                break;
                            } else if (ArraysKt.contains(activeTrackIds, ((Number) it3.next()).longValue())) {
                                break;
                            } else {
                                i4++;
                            }
                        }
                        i = i4 + 1;
                    }
                    listView2.setSelection(i);
                    listView2.setItemChecked(i, true);
                    listView2.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.lagradost.cloudstream3.ui.SelectSourceController$$ExternalSyntheticLambda4
                        @Override // android.widget.AdapterView.OnItemClickListener
                        public final void onItemClick(AdapterView adapterView, View view2, int i5, long j) {
                            SelectSourceController.lambda$0$4(this.f$0, arrayList, alertDialogCreate, adapterView, view2, i5, j);
                        }
                    });
                }
                RemoteMediaClient remoteMediaClient4 = selectSourceController.getRemoteMediaClient();
                if (remoteMediaClient4 == null || (currentItem2 = remoteMediaClient4.getCurrentItem()) == null || (media2 = currentItem2.getMedia()) == null || (contentId = media2.getContentUrl()) == null) {
                    RemoteMediaClient remoteMediaClient5 = selectSourceController.getRemoteMediaClient();
                    contentId = (remoteMediaClient5 == null || (currentItem = remoteMediaClient5.getCurrentItem()) == null || (media = currentItem.getMedia()) == null) ? null : media.getContentId();
                }
                List<ExtractorLink> list = currentLinks;
                ArrayList arrayList6 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
                for (ExtractorLink extractorLink : list) {
                    arrayList6.add(extractorLink.getName() + ' ' + Qualities.INSTANCE.getStringByInt(Integer.valueOf(extractorLink.getQuality())));
                }
                String[] strArr = (String[]) arrayList6.toArray(new String[0]);
                Iterator<ExtractorLink> it4 = currentLinks.iterator();
                while (it4.hasNext()) {
                    if (Intrinsics.areEqual(it4.next().getUrl(), contentId)) {
                        i2 = i3;
                        break;
                    }
                    i3++;
                }
                ArrayAdapter arrayAdapter2 = new ArrayAdapter(selectSourceController.view.getContext(), R.layout.sort_bottom_single_choice);
                arrayAdapter2.addAll(ArraysKt.toMutableList(strArr));
                listView.setChoiceMode(1);
                listView.setAdapter((ListAdapter) arrayAdapter2);
                listView.setSelection(i2);
                listView.setItemChecked(i2, true);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.lagradost.cloudstream3.ui.SelectSourceController$$ExternalSyntheticLambda5
                    @Override // android.widget.AdapterView.OnItemClickListener
                    public final void onItemClick(AdapterView adapterView, View view2, int i5, long j) {
                        SelectSourceController.lambda$0$7(currentMetaData, alertDialogCreate, selectSourceController, adapterView, view2, i5, j);
                    }
                });
            }
        }
    }

    static final void lambda$0$4(SelectSourceController selectSourceController, List list, AlertDialog alertDialog, AdapterView adapterView, View view, int i, long j) {
        PendingResult<RemoteMediaClient.MediaChannelResult> activeMediaTracks;
        if (i == 0) {
            RemoteMediaClient remoteMediaClient = selectSourceController.getRemoteMediaClient();
            if (remoteMediaClient != null) {
                remoteMediaClient.setActiveMediaTracks(new long[0]);
            }
        } else {
            SaveChromeCaptionStyle currentSavedStyle = ChromecastSubtitlesFragment.INSTANCE.getCurrentSavedStyle();
            TextTrackStyle textTrackStyle = new TextTrackStyle();
            String fontFamily = currentSavedStyle.getFontFamily();
            if (fontFamily == null) {
                fontFamily = "Google Sans";
            }
            textTrackStyle.setFontFamily(fontFamily);
            Integer fontGenericFamily = currentSavedStyle.getFontGenericFamily();
            if (fontGenericFamily != null) {
                textTrackStyle.setFontGenericFamily(fontGenericFamily.intValue());
            }
            textTrackStyle.setWindowColor(currentSavedStyle.getWindowColor());
            textTrackStyle.setBackgroundColor(currentSavedStyle.getBackgroundColor());
            textTrackStyle.setEdgeColor(currentSavedStyle.getEdgeColor());
            textTrackStyle.setEdgeType(currentSavedStyle.getEdgeType());
            textTrackStyle.setForegroundColor(currentSavedStyle.getForegroundColor());
            textTrackStyle.setFontScale(currentSavedStyle.getFontScale());
            RemoteMediaClient remoteMediaClient2 = selectSourceController.getRemoteMediaClient();
            if (remoteMediaClient2 != null) {
                remoteMediaClient2.setTextTrackStyle(textTrackStyle);
            }
            RemoteMediaClient remoteMediaClient3 = selectSourceController.getRemoteMediaClient();
            if (remoteMediaClient3 != null && (activeMediaTracks = remoteMediaClient3.setActiveMediaTracks(new long[]{((MediaTrack) list.get(i - 1)).getId()})) != null) {
                activeMediaTracks.setResultCallback(new ResultCallback() { // from class: com.lagradost.cloudstream3.ui.SelectSourceController$1$2$2
                    @Override // com.google.android.gms.common.api.ResultCallback
                    public final void onResult(RemoteMediaClient.MediaChannelResult it) {
                        Intrinsics.checkNotNullParameter(it, "it");
                        if (it.getStatus().isSuccess()) {
                            return;
                        }
                        Log.e("CHROMECAST", "Failed with status code:" + it.getStatus().getStatusCode() + " > " + it.getStatus().getStatusMessage());
                    }
                });
            }
        }
        UIHelper.INSTANCE.dismissSafe(alertDialog, selectSourceController.activity);
    }

    static final void lambda$0$7(MetadataHolder metadataHolder, AlertDialog alertDialog, SelectSourceController selectSourceController, AdapterView adapterView, View view, int i, long j) {
        lambda$0$7$loadMirror(metadataHolder, metadataHolder.getEpisodes().get(metadataHolder.getCurrentEpisodeIndex()), selectSourceController, i);
        UIHelper.INSTANCE.dismissSafe(alertDialog, selectSourceController.activity);
    }

    private static final void lambda$0$7$loadMirror(final MetadataHolder metadataHolder, final ResultEpisode resultEpisode, final SelectSourceController selectSourceController, final int i) {
        Integer numValueOf;
        MediaQueue mediaQueue;
        int[] itemIds;
        MediaInfo mediaInfo;
        if (metadataHolder.getCurrentLinks().size() <= i) {
            return;
        }
        CastHelper castHelper = CastHelper.INSTANCE;
        RemoteMediaClient remoteMediaClient = selectSourceController.getRemoteMediaClient();
        MediaInfo mediaInfo2 = castHelper.getMediaInfo(resultEpisode, metadataHolder, i, (remoteMediaClient == null || (mediaInfo = remoteMediaClient.getMediaInfo()) == null) ? null : mediaInfo.getCustomData(), metadataHolder.getCurrentSubtitles());
        RemoteMediaClient remoteMediaClient2 = selectSourceController.getRemoteMediaClient();
        long approximateStreamPosition = remoteMediaClient2 != null ? remoteMediaClient2.getApproximateStreamPosition() : 0L;
        try {
            RemoteMediaClient remoteMediaClient3 = selectSourceController.getRemoteMediaClient();
            Integer itemIndex = remoteMediaClient3 != null ? ControllerActivityKt.getItemIndex(remoteMediaClient3) : null;
            RemoteMediaClient remoteMediaClient4 = selectSourceController.getRemoteMediaClient();
            if (remoteMediaClient4 == null || (mediaQueue = remoteMediaClient4.getMediaQueue()) == null || (itemIds = mediaQueue.getItemIds()) == null) {
                numValueOf = null;
            } else {
                numValueOf = Integer.valueOf(itemIds[itemIndex != null ? itemIndex.intValue() + 1 : 0]);
            }
            if (itemIndex == null && numValueOf != null) {
                CastHelper castHelper2 = CastHelper.INSTANCE;
                Integer num = numValueOf;
                RemoteMediaClient remoteMediaClient5 = selectSourceController.getRemoteMediaClient();
                castHelper2.awaitLinks(remoteMediaClient5 != null ? remoteMediaClient5.queueInsertAndPlayItem(new MediaQueueItem.Builder(mediaInfo2).build(), num.intValue(), approximateStreamPosition, new JSONObject()) : null, new Function1() { // from class: com.lagradost.cloudstream3.ui.SelectSourceController$$ExternalSyntheticLambda1
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return SelectSourceController.lambda$0$7$loadMirror$0(i, metadataHolder, resultEpisode, selectSourceController, ((Boolean) obj).booleanValue());
                    }
                });
                return;
            }
            MediaLoadOptions mediaLoadOptionsBuild = new MediaLoadOptions.Builder().setPlayPosition(approximateStreamPosition).setAutoplay(true).build();
            Intrinsics.checkNotNullExpressionValue(mediaLoadOptionsBuild, "build(...)");
            CastHelper castHelper3 = CastHelper.INSTANCE;
            RemoteMediaClient remoteMediaClient6 = selectSourceController.getRemoteMediaClient();
            castHelper3.awaitLinks(remoteMediaClient6 != null ? remoteMediaClient6.load(mediaInfo2, mediaLoadOptionsBuild) : null, new Function1() { // from class: com.lagradost.cloudstream3.ui.SelectSourceController$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return SelectSourceController.lambda$0$7$loadMirror$1(i, metadataHolder, resultEpisode, selectSourceController, ((Boolean) obj).booleanValue());
                }
            });
        } catch (Exception unused) {
            MediaLoadOptions mediaLoadOptionsBuild2 = new MediaLoadOptions.Builder().setPlayPosition(approximateStreamPosition).setAutoplay(true).build();
            Intrinsics.checkNotNullExpressionValue(mediaLoadOptionsBuild2, "build(...)");
            CastHelper castHelper4 = CastHelper.INSTANCE;
            RemoteMediaClient remoteMediaClient7 = selectSourceController.getRemoteMediaClient();
            castHelper4.awaitLinks(remoteMediaClient7 != null ? remoteMediaClient7.load(mediaInfo2, mediaLoadOptionsBuild2) : null, new Function1() { // from class: com.lagradost.cloudstream3.ui.SelectSourceController$$ExternalSyntheticLambda3
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return SelectSourceController.lambda$0$7$loadMirror$2(i, metadataHolder, resultEpisode, selectSourceController, ((Boolean) obj).booleanValue());
                }
            });
        }
    }

    static final Unit lambda$0$7$loadMirror$0(int i, MetadataHolder metadataHolder, ResultEpisode resultEpisode, SelectSourceController selectSourceController, boolean z) {
        lambda$0$7$loadMirror(metadataHolder, resultEpisode, selectSourceController, i + 1);
        return Unit.INSTANCE;
    }

    static final Unit lambda$0$7$loadMirror$1(int i, MetadataHolder metadataHolder, ResultEpisode resultEpisode, SelectSourceController selectSourceController, boolean z) {
        lambda$0$7$loadMirror(metadataHolder, resultEpisode, selectSourceController, i + 1);
        return Unit.INSTANCE;
    }

    static final Unit lambda$0$7$loadMirror$2(int i, MetadataHolder metadataHolder, ResultEpisode resultEpisode, SelectSourceController selectSourceController, boolean z) {
        lambda$0$7$loadMirror(metadataHolder, resultEpisode, selectSourceController, i + 1);
        return Unit.INSTANCE;
    }

    private final MetadataHolder getCurrentMetaData() {
        Object objM8936constructorimpl;
        Object objDecodeFromString;
        MediaInfo mediaInfo;
        JSONObject customData;
        try {
            RemoteMediaClient remoteMediaClient = getRemoteMediaClient();
            String string = (remoteMediaClient == null || (mediaInfo = remoteMediaClient.getMediaInfo()) == null || (customData = mediaInfo.getCustomData()) == null) ? null : customData.toString();
            if (string == null) {
                return null;
            }
            DataStore dataStore = DataStore.INSTANCE;
            AppUtils appUtils = AppUtils.INSTANCE;
            try {
                Result.Companion companion = Result.INSTANCE;
                objM8936constructorimpl = Result.m8936constructorimpl(SerializersKt.noCompiledSerializer("com.lagradost.cloudstream3.ui.MetadataHolder"));
            } catch (Throwable th) {
                Result.Companion companion2 = Result.INSTANCE;
                objM8936constructorimpl = Result.m8936constructorimpl(ResultKt.createFailure(th));
            }
            if (Result.m8939exceptionOrNullimpl(objM8936constructorimpl) != null) {
                try {
                    Result.Companion companion3 = Result.INSTANCE;
                    objM8936constructorimpl = Result.m8936constructorimpl(SerializersModule.getContextual$default(MainAPIKt.getJson().getSerializersModule(), Reflection.getOrCreateKotlinClass(MetadataHolder.class), null, 2, null));
                } catch (Throwable th2) {
                    Result.Companion companion4 = Result.INSTANCE;
                    objM8936constructorimpl = Result.m8936constructorimpl(ResultKt.createFailure(th2));
                }
            }
            if (Result.m8942isFailureimpl(objM8936constructorimpl)) {
                objM8936constructorimpl = null;
            }
            KSerializer kSerializer = (KSerializer) objM8936constructorimpl;
            if (kSerializer != null) {
                try {
                    objDecodeFromString = MainAPIKt.getJson().decodeFromString(kSerializer, string);
                } catch (SerializationException e) {
                    ArchComponentExtKt.logError(e);
                    objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<MetadataHolder>() { // from class: com.lagradost.cloudstream3.ui.SelectSourceController$getCurrentMetaData$$inlined$toKotlinObject$1
                    });
                } catch (Throwable unused) {
                    objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<MetadataHolder>() { // from class: com.lagradost.cloudstream3.ui.SelectSourceController$getCurrentMetaData$$inlined$toKotlinObject$1
                    });
                }
            } else {
                objDecodeFromString = MainAPIKt.getMapper().readValue(string, new TypeReference<MetadataHolder>() { // from class: com.lagradost.cloudstream3.ui.SelectSourceController$getCurrentMetaData$$inlined$toKotlinObject$1
                });
            }
            return (MetadataHolder) objDecodeFromString;
        } catch (Exception unused2) {
            return null;
        }
    }

    /* JADX INFO: renamed from: isLoadingMore, reason: from getter */
    public final boolean getIsLoadingMore() {
        return this.isLoadingMore;
    }

    public final void setLoadingMore(boolean z) {
        this.isLoadingMore = z;
    }

    @Override // com.google.android.gms.cast.framework.media.uicontroller.UIController
    public void onMediaStatusUpdated() {
        RemoteMediaClient remoteMediaClient;
        Integer itemIndex;
        MediaQueue mediaQueue;
        List<ExtractorLink> currentLinks;
        super.onMediaStatusUpdated();
        MetadataHolder currentMetaData = getCurrentMetaData();
        this.view.setVisibility(((currentMetaData == null || (currentLinks = currentMetaData.getCurrentLinks()) == null) ? 0 : currentLinks.size()) <= 1 ? 4 : 0);
        if (currentMetaData != null) {
            try {
                if (currentMetaData.getEpisodes().size() <= currentMetaData.getCurrentEpisodeIndex() + 1 || (remoteMediaClient = getRemoteMediaClient()) == null || (itemIndex = ControllerActivityKt.getItemIndex(remoteMediaClient)) == null) {
                    return;
                }
                int iIntValue = itemIndex.intValue();
                RemoteMediaClient remoteMediaClient2 = getRemoteMediaClient();
                Integer numValueOf = (remoteMediaClient2 == null || (mediaQueue = remoteMediaClient2.getMediaQueue()) == null) ? null : Integer.valueOf(mediaQueue.getItemCount());
                int currentEpisodeIndex = currentMetaData.getCurrentEpisodeIndex();
                Long lValueOf = null;
                int i = currentEpisodeIndex + 1;
                ResultEpisode resultEpisode = currentMetaData.getEpisodes().get(i);
                try {
                    RemoteMediaClient remoteMediaClient3 = getRemoteMediaClient();
                    Long lValueOf2 = remoteMediaClient3 != null ? Long.valueOf(remoteMediaClient3.getStreamDuration()) : null;
                    RemoteMediaClient remoteMediaClient4 = getRemoteMediaClient();
                    if (remoteMediaClient4 != null) {
                        lValueOf = Long.valueOf(remoteMediaClient4.getApproximateStreamPosition());
                    }
                    if (lValueOf2 != null && lValueOf != null) {
                        DataStoreHelper.INSTANCE.setViewPosAndResume(Integer.valueOf(resultEpisode.getId()), lValueOf.longValue(), lValueOf2.longValue(), resultEpisode, CollectionsKt.getOrNull(currentMetaData.getEpisodes(), currentEpisodeIndex + 2));
                    }
                } catch (Throwable th) {
                    ArchComponentExtKt.logError(th);
                }
                if (numValueOf == null || numValueOf.intValue() - iIntValue != 1 || this.isLoadingMore) {
                    return;
                }
                this.isLoadingMore = true;
                Coroutines.INSTANCE.ioSafe(this, new AnonymousClass1(resultEpisode, currentMetaData, i, this, null));
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.ui.SelectSourceController$onMediaStatusUpdated$1, reason: invalid class name */
    /* JADX INFO: compiled from: ControllerActivity.kt */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "it", "Lcom/lagradost/cloudstream3/ui/SelectSourceController;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.ui.SelectSourceController$onMediaStatusUpdated$1", f = "ControllerActivity.kt", i = {0, 0, 0}, l = {320}, m = "invokeSuspend", n = {"currentLinks", "currentSubs", "generator"}, nl = {336}, s = {"L$0", "L$1", "L$2"}, v = 2)
    static final class AnonymousClass1 extends SuspendLambda implements Function3<CoroutineScope, SelectSourceController, Continuation<? super Unit>, Object> {
        final /* synthetic */ ResultEpisode $epData;
        final /* synthetic */ int $index;
        final /* synthetic */ MetadataHolder $meta;
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        final /* synthetic */ SelectSourceController this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(ResultEpisode resultEpisode, MetadataHolder metadataHolder, int i, SelectSourceController selectSourceController, Continuation<? super AnonymousClass1> continuation) {
            super(3, continuation);
            this.$epData = resultEpisode;
            this.$meta = metadataHolder;
            this.$index = i;
            this.this$0 = selectSourceController;
        }

        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(CoroutineScope coroutineScope, SelectSourceController selectSourceController, Continuation<? super Unit> continuation) {
            return new AnonymousClass1(this.$epData, this.$meta, this.$index, this.this$0, continuation).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            LinkedHashSet linkedHashSet;
            Set<SubtitleData> set;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                linkedHashSet = new LinkedHashSet();
                LinkedHashSet linkedHashSet2 = new LinkedHashSet();
                RepoLinkGenerator repoLinkGenerator = new RepoLinkGenerator(CollectionsKt.listOf(this.$epData), null, 2, 0 == true ? 1 : 0);
                this.L$0 = linkedHashSet;
                this.L$1 = linkedHashSet2;
                this.L$2 = SpillingKt.nullOutSpilledVariable(repoLinkGenerator);
                this.label = 1;
                Object objSafeApiCall = ArchComponentExtKt.safeApiCall(new SelectSourceController$onMediaStatusUpdated$1$isSuccessful$1(repoLinkGenerator, linkedHashSet, linkedHashSet2, null), this);
                if (objSafeApiCall == coroutine_suspended) {
                    return coroutine_suspended;
                }
                set = linkedHashSet2;
                obj = objSafeApiCall;
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                set = (Set) this.L$1;
                linkedHashSet = (Set) this.L$0;
                ResultKt.throwOnFailure(obj);
            }
            List<ExtractorLink> listSortUrls = MainAPIKt.sortUrls(linkedHashSet);
            List<SubtitleData> listSortSubs = AppContextUtils.INSTANCE.sortSubs(set);
            if (Intrinsics.areEqual((Resource) obj, new Resource.Success(Boxing.boxBoolean(true))) && !linkedHashSet.isEmpty()) {
                MetadataHolder metadataHolderCopy$default = MetadataHolder.copy$default(this.$meta, null, false, null, null, this.$index, null, listSortUrls, listSortSubs, 47, null);
                final MediaInfo mediaInfo = CastHelper.INSTANCE.getMediaInfo(this.$epData, metadataHolderCopy$default, 0, new JSONObject(AppUtils.INSTANCE.toJson(metadataHolderCopy$default)), listSortSubs);
                ControllerActivity activity = this.this$0.getActivity();
                final SelectSourceController selectSourceController = this.this$0;
                activity.runOnUiThread(new Runnable() { // from class: com.lagradost.cloudstream3.ui.SelectSourceController$onMediaStatusUpdated$1$$ExternalSyntheticLambda1
                    @Override // java.lang.Runnable
                    public final void run() {
                        SelectSourceController.AnonymousClass1.invokeSuspend$lambda$0(selectSourceController, mediaInfo);
                    }
                });
            }
            return Unit.INSTANCE;
        }

        static final void invokeSuspend$lambda$0(SelectSourceController selectSourceController, MediaInfo mediaInfo) {
            CastHelper castHelper = CastHelper.INSTANCE;
            RemoteMediaClient remoteMediaClient = selectSourceController.getRemoteMediaClient();
            castHelper.awaitLinks(remoteMediaClient != null ? remoteMediaClient.queueAppendItem(new MediaQueueItem.Builder(mediaInfo).build(), new JSONObject()) : null, new Function1() { // from class: com.lagradost.cloudstream3.ui.SelectSourceController$onMediaStatusUpdated$1$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return SelectSourceController.AnonymousClass1.invokeSuspend$lambda$0$0(((Boolean) obj).booleanValue());
                }
            });
            selectSourceController.setLoadingMore(false);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Unit invokeSuspend$lambda$0$0(boolean z) {
            System.out.println((Object) "FAILED TO LOAD NEXT ITEM");
            return Unit.INSTANCE;
        }
    }

    @Override // com.google.android.gms.cast.framework.media.uicontroller.UIController
    public void onSessionConnected(CastSession castSession) {
        Intrinsics.checkNotNullParameter(castSession, "castSession");
        super.onSessionConnected(castSession);
        RemoteMediaClient remoteMediaClient = getRemoteMediaClient();
        if (remoteMediaClient != null) {
            remoteMediaClient.queueSetRepeatMode(0, new JSONObject());
        }
    }
}
