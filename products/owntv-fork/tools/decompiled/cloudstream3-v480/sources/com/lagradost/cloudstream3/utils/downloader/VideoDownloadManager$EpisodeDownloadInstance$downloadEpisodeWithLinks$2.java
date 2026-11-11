package com.lagradost.cloudstream3.utils.downloader;

import com.lagradost.cloudstream3.ui.player.SubtitleData;
import com.lagradost.cloudstream3.ui.result.ExtractorSubtitleLink;
import com.lagradost.cloudstream3.ui.subtitles.SubtitlesFragment;
import com.lagradost.cloudstream3.utils.SubtitleHelper;
import com.lagradost.cloudstream3.utils.SubtitleUtils;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: DownloadManager.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "it", "Lcom/lagradost/cloudstream3/utils/downloader/VideoDownloadManager$EpisodeDownloadInstance;"}, k = 3, mv = {2, 3, 0}, xi = 48)
@DebugMetadata(c = "com.lagradost.cloudstream3.utils.downloader.VideoDownloadManager$EpisodeDownloadInstance$downloadEpisodeWithLinks$2", f = "DownloadManager.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
final class VideoDownloadManager$EpisodeDownloadInstance$downloadEpisodeWithLinks$2 extends SuspendLambda implements Function3<CoroutineScope, VideoDownloadManager.EpisodeDownloadInstance, Continuation<? super Unit>, Object> {
    final /* synthetic */ String $folder;
    final /* synthetic */ DownloadObjects.DownloadEpisodeMetadata $meta;
    final /* synthetic */ List<SubtitleData> $subs;
    int label;
    final /* synthetic */ VideoDownloadManager.EpisodeDownloadInstance this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    VideoDownloadManager$EpisodeDownloadInstance$downloadEpisodeWithLinks$2(List<SubtitleData> list, VideoDownloadManager.EpisodeDownloadInstance episodeDownloadInstance, DownloadObjects.DownloadEpisodeMetadata downloadEpisodeMetadata, String str, Continuation<? super VideoDownloadManager$EpisodeDownloadInstance$downloadEpisodeWithLinks$2> continuation) {
        super(3, continuation);
        this.$subs = list;
        this.this$0 = episodeDownloadInstance;
        this.$meta = downloadEpisodeMetadata;
        this.$folder = str;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(CoroutineScope coroutineScope, VideoDownloadManager.EpisodeDownloadInstance episodeDownloadInstance, Continuation<? super Unit> continuation) {
        return new VideoDownloadManager$EpisodeDownloadInstance$downloadEpisodeWithLinks$2(this.$subs, this.this$0, this.$meta, this.$folder, continuation).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        try {
            List<String> downloadSubsLanguageTagIETF = SubtitlesFragment.INSTANCE.getDownloadSubsLanguageTagIETF();
            List<SubtitleData> list = this.$subs;
            if (list != null) {
                ArrayList arrayList = new ArrayList();
                for (Object obj2 : list) {
                    SubtitleData subtitleData = (SubtitleData) obj2;
                    List<String> list2 = downloadSubsLanguageTagIETF;
                    if (!(list2 instanceof Collection) || !list2.isEmpty()) {
                        for (String str : list2) {
                            if (!Intrinsics.areEqual(subtitleData.getLanguageCode(), str)) {
                                String originalName = subtitleData.getOriginalName();
                                String strFromTagToEnglishLanguageName = SubtitleHelper.INSTANCE.fromTagToEnglishLanguageName(str);
                                if (strFromTagToEnglishLanguageName != null) {
                                    str = strFromTagToEnglishLanguageName;
                                }
                                if (StringsKt.contains$default((CharSequence) originalName, (CharSequence) str, false, 2, (Object) null)) {
                                }
                            }
                            arrayList.add(obj2);
                        }
                    }
                }
                ArrayList<SubtitleData> arrayList2 = arrayList;
                ArrayList arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList2, 10));
                for (SubtitleData subtitleData2 : arrayList2) {
                    arrayList3.add(new ExtractorSubtitleLink(subtitleData2.getName(), subtitleData2.getUrl(), "", subtitleData2.getHeaders()));
                }
                List listTake = CollectionsKt.take(arrayList3, 3);
                if (listTake != null) {
                    VideoDownloadManager.EpisodeDownloadInstance episodeDownloadInstance = this.this$0;
                    DownloadObjects.DownloadEpisodeMetadata downloadEpisodeMetadata = this.$meta;
                    String str2 = this.$folder;
                    Iterator it = listTake.iterator();
                    while (it.hasNext()) {
                        DownloadUtils.INSTANCE.downloadSubtitle$app(episodeDownloadInstance.getContext(), (ExtractorSubtitleLink) it.next(), DownloadFileManagement.INSTANCE.getFileName$app(episodeDownloadInstance.getContext(), downloadEpisodeMetadata), str2);
                    }
                }
            }
        } catch (CancellationException unused) {
            SubtitleUtils.INSTANCE.deleteMatchingSubtitles(this.this$0.getContext(), new DownloadObjects.DownloadedFileInfo(0L, this.$folder, DownloadFileManagement.INSTANCE.getFileName$app(this.this$0.getContext(), this.$meta), null, DownloadFileManagement.INSTANCE.getBasePath$app(this.this$0.getContext()).getSecond(), null, 40, null));
        }
        return Unit.INSTANCE;
    }
}
