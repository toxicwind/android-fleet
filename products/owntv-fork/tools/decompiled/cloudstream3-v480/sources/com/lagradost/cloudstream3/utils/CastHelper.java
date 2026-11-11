package com.lagradost.cloudstream3.utils;

import android.net.Uri;
import com.google.android.gms.cast.MediaInfo;
import com.google.android.gms.cast.MediaLoadRequestData;
import com.google.android.gms.cast.MediaMetadata;
import com.google.android.gms.cast.MediaTrack;
import com.google.android.gms.cast.framework.CastSession;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.images.WebImage;
import com.lagradost.cloudstream3.mvvm.ArchComponentExtKt;
import com.lagradost.cloudstream3.ui.MetadataHolder;
import com.lagradost.cloudstream3.ui.player.SubtitleData;
import com.lagradost.cloudstream3.ui.result.ResultEpisode;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.Dispatchers;
import org.json.JSONObject;

/* JADX INFO: compiled from: CastHelper.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J6\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\r2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fJ*\u0010\u0011\u001a\u00020\u00122\u000e\u0010\u0013\u001a\n\u0012\u0004\u0012\u00020\u0015\u0018\u00010\u00142\u0012\u0010\u0016\u001a\u000e\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\u00120\u0017J\u007f\u0010\u0019\u001a\u00020\u0018*\u0004\u0018\u00010\u001a2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u00182\b\u0010\u001e\u001a\u0004\u0018\u00010\u001c2\b\u0010\u001f\u001a\u0004\u0018\u00010\u001c2\u0006\u0010 \u001a\u00020\u000b2\f\u0010!\u001a\b\u0012\u0004\u0012\u00020\u00070\u000f2\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020#0\u000f2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f2\n\b\u0002\u0010$\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010%\u001a\u0004\u0018\u00010&¢\u0006\u0002\u0010'¨\u0006("}, d2 = {"Lcom/lagradost/cloudstream3/utils/CastHelper;", "", "<init>", "()V", "getMediaInfo", "Lcom/google/android/gms/cast/MediaInfo;", "epData", "Lcom/lagradost/cloudstream3/ui/result/ResultEpisode;", "holder", "Lcom/lagradost/cloudstream3/ui/MetadataHolder;", "index", "", "data", "Lorg/json/JSONObject;", "subtitles", "", "Lcom/lagradost/cloudstream3/ui/player/SubtitleData;", "awaitLinks", "", "pending", "Lcom/google/android/gms/common/api/PendingResult;", "Lcom/google/android/gms/cast/framework/media/RemoteMediaClient$MediaChannelResult;", "callback", "Lkotlin/Function1;", "", "startCast", "Lcom/google/android/gms/cast/framework/CastSession;", "apiName", "", "isMovie", "title", "poster", "currentEpisodeIndex", "episodes", "currentLinks", "Lcom/lagradost/cloudstream3/utils/ExtractorLink;", "startIndex", "startTime", "", "(Lcom/google/android/gms/cast/framework/CastSession;Ljava/lang/String;ZLjava/lang/String;Ljava/lang/String;ILjava/util/List;Ljava/util/List;Ljava/util/List;Ljava/lang/Integer;Ljava/lang/Long;)Z", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class CastHelper {
    public static final CastHelper INSTANCE = new CastHelper();

    /* JADX INFO: compiled from: CastHelper.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ExtractorLinkType.values().length];
            try {
                iArr[ExtractorLinkType.M3U8.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[ExtractorLinkType.DASH.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    private CastHelper() {
    }

    public final MediaInfo getMediaInfo(ResultEpisode epData, MetadataHolder holder, int index, JSONObject data, List<SubtitleData> subtitles) {
        String string;
        String str;
        Intrinsics.checkNotNullParameter(epData, "epData");
        Intrinsics.checkNotNullParameter(holder, "holder");
        Intrinsics.checkNotNullParameter(subtitles, "subtitles");
        ExtractorLink extractorLink = holder.getCurrentLinks().get(index);
        MediaMetadata mediaMetadata = new MediaMetadata(1);
        if (holder.isMovie()) {
            string = extractorLink.getName() + ' ' + Qualities.INSTANCE.getStringByInt(Integer.valueOf(extractorLink.getQuality()));
        } else {
            StringBuilder sb = new StringBuilder();
            String name = epData.getName();
            if (name == null) {
                name = "Episode " + epData.getEpisode();
            }
            sb.append(name);
            sb.append(" - ");
            sb.append(extractorLink.getName());
            sb.append(' ');
            sb.append(Qualities.INSTANCE.getStringByInt(Integer.valueOf(extractorLink.getQuality())));
            string = sb.toString();
        }
        mediaMetadata.putString(MediaMetadata.KEY_SUBTITLE, string);
        String title = holder.getTitle();
        if (title != null) {
            mediaMetadata.putString(MediaMetadata.KEY_TITLE, title);
        }
        String poster = epData.getPoster();
        if (poster == null) {
            poster = holder.getPoster();
        }
        if (poster != null) {
            mediaMetadata.addImage(new WebImage(Uri.parse(poster)));
        }
        List<SubtitleData> list = subtitles;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        int i = 0;
        for (SubtitleData subtitleData : list) {
            arrayList.add(new MediaTrack.Builder(i, 1).setName(subtitleData.getName()).setSubtype(1).setContentId(subtitleData.getUrl()).build());
            i++;
        }
        ArrayList arrayList2 = arrayList;
        MediaInfo.Builder streamType = new MediaInfo.Builder(extractorLink.getUrl()).setStreamType(1);
        int i2 = WhenMappings.$EnumSwitchMapping$0[extractorLink.getType().ordinal()];
        if (i2 == 1) {
            str = "application/x-mpegURL";
        } else if (i2 == 2) {
            str = "application/dash+xml";
        } else {
            str = "video/mp4";
        }
        MediaInfo.Builder mediaTracks = streamType.setContentType(str).setMetadata(mediaMetadata).setMediaTracks(arrayList2);
        Intrinsics.checkNotNullExpressionValue(mediaTracks, "setMediaTracks(...)");
        if (data != null) {
            mediaTracks.setCustomData(data);
        }
        MediaInfo mediaInfoBuild = mediaTracks.build();
        Intrinsics.checkNotNullExpressionValue(mediaInfoBuild, "build(...)");
        return mediaInfoBuild;
    }

    /* JADX INFO: renamed from: com.lagradost.cloudstream3.utils.CastHelper$awaitLinks$1, reason: invalid class name */
    /* JADX INFO: compiled from: CastHelper.kt */
    @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "it", "Lcom/lagradost/cloudstream3/utils/CastHelper;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.lagradost.cloudstream3.utils.CastHelper$awaitLinks$1", f = "CastHelper.kt", i = {}, l = {78}, m = "invokeSuspend", n = {}, nl = {79}, s = {}, v = 2)
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CastHelper, Continuation<? super Unit>, Object> {
        final /* synthetic */ Function1<Boolean, Unit> $callback;
        final /* synthetic */ PendingResult<RemoteMediaClient.MediaChannelResult> $pending;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        AnonymousClass1(Function1<? super Boolean, Unit> function1, PendingResult<RemoteMediaClient.MediaChannelResult> pendingResult, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$callback = function1;
            this.$pending = pendingResult;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(this.$callback, this.$pending, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CastHelper castHelper, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(castHelper, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                obj = BuildersKt.withContext(Dispatchers.getIO(), new CastHelper$awaitLinks$1$res$1(this.$pending, null), this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            Intrinsics.checkNotNullExpressionValue(obj, "withContext(...)");
            if (((RemoteMediaClient.MediaChannelResult) obj).getStatus().getStatusCode() == 2100) {
                this.$callback.invoke(Boxing.boxBoolean(true));
                System.out.println((Object) "FAILED AND LOAD NEXT");
            }
            return Unit.INSTANCE;
        }
    }

    public final void awaitLinks(PendingResult<RemoteMediaClient.MediaChannelResult> pending, Function1<? super Boolean, Unit> callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        if (pending == null) {
            return;
        }
        Coroutines.INSTANCE.main(this, new AnonymousClass1(callback, pending, null));
    }

    public static /* synthetic */ boolean startCast$default(CastHelper castHelper, CastSession castSession, String str, boolean z, String str2, String str3, int i, List list, List list2, List list3, Integer num, Long l, int i2, Object obj) {
        if ((i2 & 256) != 0) {
            num = null;
        }
        if ((i2 & 512) != 0) {
            l = null;
        }
        return castHelper.startCast(castSession, str, z, str2, str3, i, list, list2, list3, num, l);
    }

    public final boolean startCast(final CastSession castSession, final String apiName, final boolean z, final String str, final String str2, final int i, final List<ResultEpisode> episodes, final List<? extends ExtractorLink> currentLinks, final List<SubtitleData> subtitles, Integer num, final Long l) {
        PendingResult<RemoteMediaClient.MediaChannelResult> pendingResultLoad;
        Intrinsics.checkNotNullParameter(apiName, "apiName");
        Intrinsics.checkNotNullParameter(episodes, "episodes");
        Intrinsics.checkNotNullParameter(currentLinks, "currentLinks");
        Intrinsics.checkNotNullParameter(subtitles, "subtitles");
        if (castSession == null) {
            return false;
        }
        try {
            if (episodes.isEmpty() || i >= episodes.size()) {
                return false;
            }
            ResultEpisode resultEpisode = episodes.get(i);
            MetadataHolder metadataHolder = new MetadataHolder(apiName, z, str, str2, i, episodes, currentLinks, subtitles);
            int iIntValue = (num == null || num.intValue() < 0) ? 0 : num.intValue();
            MediaInfo mediaInfo = getMediaInfo(resultEpisode, metadataHolder, iIntValue, new JSONObject(AppUtils.INSTANCE.toJson(metadataHolder)), subtitles);
            RemoteMediaClient remoteMediaClient = castSession.getRemoteMediaClient();
            if (remoteMediaClient != null) {
                pendingResultLoad = remoteMediaClient.load(new MediaLoadRequestData.Builder().setMediaInfo(mediaInfo).setCurrentTime(l != null ? l.longValue() : 0L).build());
            } else {
                pendingResultLoad = null;
            }
            final int i2 = iIntValue;
            awaitLinks(pendingResultLoad, new Function1() { // from class: com.lagradost.cloudstream3.utils.CastHelper$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return CastHelper.startCast$lambda$0(currentLinks, i2, castSession, apiName, z, str, str2, i, episodes, subtitles, l, ((Boolean) obj).booleanValue());
                }
            });
            return true;
        } catch (Exception e) {
            ArchComponentExtKt.logError(e);
            return false;
        }
    }

    static final Unit startCast$lambda$0(List list, int i, CastSession castSession, String str, boolean z, String str2, String str3, int i2, List list2, List list3, Long l, boolean z2) {
        int i3 = i + 1;
        if (list.size() > i3) {
            INSTANCE.startCast(castSession, str, z, str2, str3, i2, list2, list, list3, Integer.valueOf(i3), l);
        }
        return Unit.INSTANCE;
    }
}
