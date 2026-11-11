package com.lagradost.cloudstream3.ui.result;

import android.content.Context;
import androidx.core.app.NotificationCompat;
import androidx.tvprovider.media.tv.TvContractCompat;
import com.google.android.gms.common.internal.ImagesContract;
import com.lagradost.cloudstream3.APIHolder;
import com.lagradost.cloudstream3.Actor;
import com.lagradost.cloudstream3.ActorData;
import com.lagradost.cloudstream3.AnimeLoadResponse;
import com.lagradost.cloudstream3.DubStatus;
import com.lagradost.cloudstream3.Episode;
import com.lagradost.cloudstream3.EpisodeResponse;
import com.lagradost.cloudstream3.LoadResponse;
import com.lagradost.cloudstream3.MainAPI;
import com.lagradost.cloudstream3.NextAiring;
import com.lagradost.cloudstream3.ProviderType;
import com.lagradost.cloudstream3.R;
import com.lagradost.cloudstream3.Score;
import com.lagradost.cloudstream3.ShowStatus;
import com.lagradost.cloudstream3.TorrentLoadResponse;
import com.lagradost.cloudstream3.TvSeriesLoadResponse;
import com.lagradost.cloudstream3.TvType;
import com.lagradost.cloudstream3.VPNStatus;
import com.lagradost.cloudstream3.mvvm.DebugException;
import com.lagradost.cloudstream3.syncproviders.AccountManager;
import com.lagradost.cloudstream3.ui.APIRepository;
import com.lagradost.cloudstream3.utils.AppDebug;
import com.lagradost.cloudstream3.utils.TextUtilKt;
import com.lagradost.cloudstream3.utils.UiText;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: ResultViewModel2.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000F\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\u001a\u0012\u0010\u0000\u001a\u0004\u0018\u00010\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u001a\u0012\u0010\u0004\u001a\u00020\u0005*\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b\u001a\n\u0010\t\u001a\u00020\n*\u00020\u0006\u001a\u0018\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\rH\u0002\u001a\u0019\u0010\u000f\u001a\u00020\u0010*\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\n¢\u0006\u0002\u0010\u0013\u001a\u001b\u0010\u0014\u001a\u0004\u0018\u00010\n*\u00020\u00112\b\u0010\u0015\u001a\u0004\u0018\u00010\n¢\u0006\u0002\u0010\u0016\u001a\u0012\u0010\u0017\u001a\u00020\r*\u00020\u00112\u0006\u0010\u0018\u001a\u00020\u0019\u001a\u0018\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\r0\u001b*\u00020\u00112\u0006\u0010\u0018\u001a\u00020\u0019¨\u0006\u001c"}, d2 = {"txt", "Lcom/lagradost/cloudstream3/utils/UiText;", NotificationCompat.CATEGORY_STATUS, "Lcom/lagradost/cloudstream3/DubStatus;", "toResultData", "Lcom/lagradost/cloudstream3/ui/result/ResultData;", "Lcom/lagradost/cloudstream3/LoadResponse;", "repo", "Lcom/lagradost/cloudstream3/ui/APIRepository;", "getId", "", "getLoadResponseIdFromUrl", ImagesContract.URL, "", "apiName", "callback", "", "Lcom/lagradost/cloudstream3/ui/result/SelectPopup;", "index", "(Lcom/lagradost/cloudstream3/ui/result/SelectPopup;Ljava/lang/Integer;)V", "transformResult", TvContractCompat.PARAM_INPUT, "(Lcom/lagradost/cloudstream3/ui/result/SelectPopup;Ljava/lang/Integer;)Ljava/lang/Integer;", "getTitle", "context", "Landroid/content/Context;", "getOptions", "", "app"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class ResultViewModel2Kt {

    /* JADX INFO: compiled from: ResultViewModel2.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;
        public static final /* synthetic */ int[] $EnumSwitchMapping$2;
        public static final /* synthetic */ int[] $EnumSwitchMapping$3;

        static {
            int[] iArr = new int[DubStatus.values().length];
            try {
                iArr[DubStatus.Dubbed.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[DubStatus.Subbed.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[TvType.values().length];
            try {
                iArr2[TvType.Torrent.ordinal()] = 1;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr2[TvType.TvSeries.ordinal()] = 2;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr2[TvType.Anime.ordinal()] = 3;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr2[TvType.OVA.ordinal()] = 4;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                iArr2[TvType.AnimeMovie.ordinal()] = 5;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                iArr2[TvType.Cartoon.ordinal()] = 6;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                iArr2[TvType.Documentary.ordinal()] = 7;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                iArr2[TvType.Movie.ordinal()] = 8;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                iArr2[TvType.AsianDrama.ordinal()] = 9;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                iArr2[TvType.Live.ordinal()] = 10;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                iArr2[TvType.Others.ordinal()] = 11;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                iArr2[TvType.NSFW.ordinal()] = 12;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                iArr2[TvType.Music.ordinal()] = 13;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                iArr2[TvType.AudioBook.ordinal()] = 14;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                iArr2[TvType.CustomMedia.ordinal()] = 15;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                iArr2[TvType.Audio.ordinal()] = 16;
            } catch (NoSuchFieldError unused18) {
            }
            try {
                iArr2[TvType.Podcast.ordinal()] = 17;
            } catch (NoSuchFieldError unused19) {
            }
            try {
                iArr2[TvType.Video.ordinal()] = 18;
            } catch (NoSuchFieldError unused20) {
            }
            $EnumSwitchMapping$1 = iArr2;
            int[] iArr3 = new int[VPNStatus.values().length];
            try {
                iArr3[VPNStatus.None.ordinal()] = 1;
            } catch (NoSuchFieldError unused21) {
            }
            try {
                iArr3[VPNStatus.Torrent.ordinal()] = 2;
            } catch (NoSuchFieldError unused22) {
            }
            try {
                iArr3[VPNStatus.MightBeNeeded.ordinal()] = 3;
            } catch (NoSuchFieldError unused23) {
            }
            $EnumSwitchMapping$2 = iArr3;
            int[] iArr4 = new int[ShowStatus.values().length];
            try {
                iArr4[ShowStatus.Ongoing.ordinal()] = 1;
            } catch (NoSuchFieldError unused24) {
            }
            try {
                iArr4[ShowStatus.Completed.ordinal()] = 2;
            } catch (NoSuchFieldError unused25) {
            }
            $EnumSwitchMapping$3 = iArr4;
        }
    }

    public static final UiText txt(DubStatus dubStatus) {
        Integer numValueOf;
        int i = dubStatus == null ? -1 : WhenMappings.$EnumSwitchMapping$0[dubStatus.ordinal()];
        if (i == 1) {
            numValueOf = Integer.valueOf(R.string.app_dubbed_text);
        } else {
            numValueOf = i != 2 ? null : Integer.valueOf(R.string.app_subbed_text);
        }
        return TextUtilKt.txtNull(numValueOf, new Object[0]);
    }

    static final CharSequence toResultData$lambda$3(ActorData it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return it.getActor().getName();
    }

    public static final int getId(LoadResponse loadResponse) {
        Intrinsics.checkNotNullParameter(loadResponse, "<this>");
        Integer id = loadResponse instanceof ResultViewModel2.LoadResponseFromSearch ? ((ResultViewModel2.LoadResponseFromSearch) loadResponse).getId() : null;
        return id != null ? id.intValue() : getLoadResponseIdFromUrl(loadResponse.getUniqueUrl(), loadResponse.getApiName());
    }

    private static final int getLoadResponseIdFromUrl(String str, String str2) {
        String mainUrl;
        MainAPI apiFromNameNull = APIHolder.INSTANCE.getApiFromNameNull(str2);
        if (apiFromNameNull == null || (mainUrl = apiFromNameNull.getMainUrl()) == null) {
            mainUrl = "";
        }
        return StringsKt.replace$default(StringsKt.replace$default(str, mainUrl, "", false, 4, (Object) null), "/", "", false, 4, (Object) null).hashCode();
    }

    public static final void callback(SelectPopup selectPopup, Integer num) {
        Intrinsics.checkNotNullParameter(selectPopup, "<this>");
        Integer numTransformResult = transformResult(selectPopup, num);
        if (selectPopup instanceof SelectPopup.SelectArray) {
            ((SelectPopup.SelectArray) selectPopup).getCallback().invoke(numTransformResult);
        } else {
            if (!(selectPopup instanceof SelectPopup.SelectText)) {
                throw new NoWhenBranchMatchedException();
            }
            ((SelectPopup.SelectText) selectPopup).getCallback().invoke(numTransformResult);
        }
    }

    public static final Integer transformResult(SelectPopup selectPopup, Integer num) {
        Intrinsics.checkNotNullParameter(selectPopup, "<this>");
        if (num == null) {
            return null;
        }
        if (!(selectPopup instanceof SelectPopup.SelectArray)) {
            if (selectPopup instanceof SelectPopup.SelectText) {
                return num;
            }
            throw new NoWhenBranchMatchedException();
        }
        Pair pair = (Pair) CollectionsKt.getOrNull(((SelectPopup.SelectArray) selectPopup).getOptions(), num.intValue());
        if (pair != null) {
            return (Integer) pair.getSecond();
        }
        return null;
    }

    public static final String getTitle(SelectPopup selectPopup, Context context) {
        Intrinsics.checkNotNullParameter(selectPopup, "<this>");
        Intrinsics.checkNotNullParameter(context, "context");
        if (selectPopup instanceof SelectPopup.SelectArray) {
            return ((SelectPopup.SelectArray) selectPopup).getText().asString(context);
        }
        if (selectPopup instanceof SelectPopup.SelectText) {
            return ((SelectPopup.SelectText) selectPopup).getText().asString(context);
        }
        throw new NoWhenBranchMatchedException();
    }

    public static final List<String> getOptions(SelectPopup selectPopup, Context context) {
        Intrinsics.checkNotNullParameter(selectPopup, "<this>");
        Intrinsics.checkNotNullParameter(context, "context");
        if (selectPopup instanceof SelectPopup.SelectArray) {
            List<Pair<UiText, Integer>> options = ((SelectPopup.SelectArray) selectPopup).getOptions();
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(options, 10));
            Iterator<T> it = options.iterator();
            while (it.hasNext()) {
                arrayList.add(((UiText) ((Pair) it.next()).getFirst()).asString(context));
            }
            return arrayList;
        }
        if (!(selectPopup instanceof SelectPopup.SelectText)) {
            throw new NoWhenBranchMatchedException();
        }
        List<UiText> options2 = ((SelectPopup.SelectText) selectPopup).getOptions();
        ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(options2, 10));
        Iterator<T> it2 = options2.iterator();
        while (it2.hasNext()) {
            arrayList2.add(((UiText) it2.next()).asString(context));
        }
        return arrayList2;
    }

    /* JADX WARN: Code duplicated, block: B:164:0x0380  */
    public static final ResultData toResultData(LoadResponse loadResponse, APIRepository repo) throws DebugException {
        UiText uiText;
        UiText uiText2;
        int i;
        UiText uiTextTxtNull;
        UiText uiTextTxt;
        int i2;
        Integer numValueOf;
        UiText uiTextTxtNull2;
        Integer numValueOf2;
        String stringNull;
        List<ActorData> actors;
        NextAiring nextAiring;
        UiText uiTextTxt2;
        UiText uiTextTxtNull3;
        ActorData actorData;
        Actor actor;
        String image;
        Intrinsics.checkNotNullParameter(loadResponse, "<this>");
        Intrinsics.checkNotNullParameter(repo, "repo");
        if (!AppDebug.INSTANCE.isDebug() || Intrinsics.areEqual(repo.getName(), loadResponse.getApiName())) {
            List<ActorData> actors2 = loadResponse.getActors();
            boolean z = (actors2 == null || (actorData = (ActorData) CollectionsKt.firstOrNull((List) actors2)) == null || (actor = actorData.getActor()) == null || (image = actor.getImage()) == null || !(StringsKt.isBlank(image) ^ true)) ? false : true;
            boolean z2 = loadResponse instanceof EpisodeResponse;
            UiText uiTextTxt3 = null;
            if (!z2 || (nextAiring = ((EpisodeResponse) loadResponse).getNextAiring()) == null || nextAiring.getUnixTime() <= APIHolder.INSTANCE.getUnixTime()) {
                uiText = null;
                uiText2 = null;
            } else {
                long unixTime = nextAiring.getUnixTime() - APIHolder.INSTANCE.getUnixTime();
                TimeUnit timeUnit = TimeUnit.SECONDS;
                long j = unixTime / 86400;
                TimeUnit timeUnit2 = TimeUnit.SECONDS;
                long j2 = unixTime / 3600;
                long j3 = j2 - (24 * j);
                TimeUnit timeUnit3 = TimeUnit.SECONDS;
                TimeUnit timeUnit4 = TimeUnit.SECONDS;
                long j4 = (unixTime / 60) - (j2 * 60);
                if (j > 0) {
                    uiTextTxt2 = TextUtilKt.txt(R.string.next_episode_time_day_format, Long.valueOf(j), Long.valueOf(j3), Long.valueOf(j4));
                } else if (j3 > 0) {
                    uiTextTxt2 = TextUtilKt.txt(R.string.next_episode_time_hour_format, Long.valueOf(j3), Long.valueOf(j4));
                } else {
                    uiTextTxt2 = j4 > 0 ? TextUtilKt.txt(R.string.next_episode_time_min_format, Long.valueOf(j4)) : null;
                }
                if (uiTextTxt2 == null) {
                    uiTextTxtNull3 = null;
                    uiTextTxt2 = null;
                } else if (nextAiring.getSeason() == null) {
                    uiTextTxtNull3 = TextUtilKt.txt(R.string.next_episode_format, Integer.valueOf(nextAiring.getEpisode()));
                } else {
                    uiTextTxtNull3 = TextUtilKt.txtNull(Integer.valueOf(R.string.next_season_episode_format), nextAiring.getSeason(), Integer.valueOf(nextAiring.getEpisode()));
                }
                uiText2 = uiTextTxtNull3;
                uiText = uiTextTxt2;
            }
            Integer duration = loadResponse.getDuration();
            Map<String, String> syncData = loadResponse.getSyncData();
            if (WhenMappings.$EnumSwitchMapping$1[loadResponse.getType().ordinal()] == 1) {
                i = R.string.torrent_plot;
            } else {
                i = R.string.result_plot;
            }
            UiText uiTextTxt4 = TextUtilKt.txt(i, new Object[0]);
            String posterUrl = loadResponse.getPosterUrl();
            if (posterUrl == null) {
                posterUrl = loadResponse.getBackgroundPosterUrl();
            }
            String str = posterUrl;
            Map<String, String> posterHeaders = loadResponse.getPosterHeaders();
            String backgroundPosterUrl = loadResponse.getBackgroundPosterUrl();
            if (backgroundPosterUrl == null) {
                backgroundPosterUrl = loadResponse.getPosterUrl();
            }
            String str2 = backgroundPosterUrl;
            UiText uiTextTxt5 = TextUtilKt.txt(loadResponse.getName());
            String url = loadResponse.getUrl();
            List<String> tags = loadResponse.getTags();
            if (tags == null) {
                tags = CollectionsKt.emptyList();
            }
            List<String> list = tags;
            boolean comingSoon = loadResponse.getComingSoon();
            List<ActorData> actors3 = z ? loadResponse.getActors() : null;
            if (z || (actors = loadResponse.getActors()) == null || actors.isEmpty()) {
                uiTextTxtNull = null;
            } else {
                Integer numValueOf3 = Integer.valueOf(R.string.cast_format);
                List<ActorData> actors4 = loadResponse.getActors();
                uiTextTxtNull = TextUtilKt.txtNull(numValueOf3, actors4 != null ? CollectionsKt.joinToString$default(actors4, null, null, null, 0, null, new Function1() { // from class: com.lagradost.cloudstream3.ui.result.ResultViewModel2Kt$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return ResultViewModel2Kt.toResultData$lambda$3((ActorData) obj);
                    }
                }, 31, null) : null);
            }
            String plot = loadResponse.getPlot();
            if (plot == null || StringsKt.isBlank(plot)) {
                uiTextTxt = TextUtilKt.txt(loadResponse instanceof TorrentLoadResponse ? R.string.torrent_no_plot : R.string.normal_no_plot, new Object[0]);
            } else {
                String plot2 = loadResponse.getPlot();
                Intrinsics.checkNotNull(plot2);
                uiTextTxt = TextUtilKt.txt(plot2);
            }
            UiText uiText3 = uiTextTxt;
            String backgroundPosterUrl2 = loadResponse.getBackgroundPosterUrl();
            String logoUrl = loadResponse.getLogoUrl();
            String name = loadResponse.getName();
            switch (WhenMappings.$EnumSwitchMapping$1[loadResponse.getType().ordinal()]) {
                case 1:
                    i2 = R.string.torrent_singular;
                    break;
                case 2:
                    i2 = R.string.tv_series_singular;
                    break;
                case 3:
                    i2 = R.string.anime_singular;
                    break;
                case 4:
                    i2 = R.string.ova_singular;
                    break;
                case 5:
                    i2 = R.string.movies_singular;
                    break;
                case 6:
                    i2 = R.string.cartoons_singular;
                    break;
                case 7:
                    i2 = R.string.documentaries_singular;
                    break;
                case 8:
                    i2 = R.string.movies_singular;
                    break;
                case 9:
                    i2 = R.string.asian_drama_singular;
                    break;
                case 10:
                    i2 = R.string.live_singular;
                    break;
                case 11:
                    i2 = R.string.other_singular;
                    break;
                case 12:
                    i2 = R.string.nsfw_singular;
                    break;
                case 13:
                    i2 = R.string.music_singular;
                    break;
                case 14:
                    i2 = R.string.audio_book_singular;
                    break;
                case 15:
                    i2 = R.string.custom_media_singular;
                    break;
                case 16:
                    i2 = R.string.audio_singular;
                    break;
                case 17:
                    i2 = R.string.podcast_singular;
                    break;
                case 18:
                    i2 = R.string.video_singular;
                    break;
                default:
                    throw new NoWhenBranchMatchedException();
            }
            UiText uiTextTxt6 = TextUtilKt.txt(i2, new Object[0]);
            Integer year = loadResponse.getYear();
            UiText uiTextTxtNull4 = TextUtilKt.txtNull(year != null ? String.valueOf(year.intValue()) : null);
            UiText uiTextTxt7 = TextUtilKt.txt(loadResponse.getApiName());
            Score score = loadResponse.getScore();
            UiText uiTextTxt8 = (score == null || (stringNull = score.toStringNull(0.1d, 10, 1, false, '.')) == null) ? null : TextUtilKt.txt(R.string.rating_format, stringNull);
            UiText uiTextTxtNull5 = TextUtilKt.txtNull(loadResponse.getContentRating());
            int i3 = WhenMappings.$EnumSwitchMapping$2[repo.getVpnStatus().ordinal()];
            if (i3 == 1) {
                numValueOf = null;
            } else if (i3 == 2) {
                numValueOf = Integer.valueOf(R.string.vpn_torrent);
            } else {
                if (i3 != 3) {
                    throw new NoWhenBranchMatchedException();
                }
                numValueOf = Integer.valueOf(R.string.vpn_might_be_needed);
            }
            UiText uiTextTxtNull6 = TextUtilKt.txtNull(numValueOf, new Object[0]);
            UiText uiTextTxt9 = repo.getProviderType() == ProviderType.MetaProvider ? TextUtilKt.txt(R.string.provider_info_meta, new Object[0]) : null;
            UiText uiTextTxt10 = (duration == null || duration.intValue() <= 0) ? null : TextUtilKt.txt(AccountManager.INSTANCE.secondsToReadable(duration.intValue() * 60, "0 mins"));
            if (z2) {
                ShowStatus showStatus = ((EpisodeResponse) loadResponse).getShowStatus();
                int i4 = showStatus == null ? -1 : WhenMappings.$EnumSwitchMapping$3[showStatus.ordinal()];
                if (i4 == 1) {
                    numValueOf2 = Integer.valueOf(R.string.status_ongoing);
                } else {
                    numValueOf2 = i4 != 2 ? null : Integer.valueOf(R.string.status_completed);
                }
                uiTextTxtNull2 = TextUtilKt.txtNull(numValueOf2, new Object[0]);
            } else {
                uiTextTxtNull2 = null;
            }
            if ((loadResponse instanceof TvSeriesLoadResponse) && ((TvSeriesLoadResponse) loadResponse).getEpisodes().isEmpty()) {
                uiTextTxt3 = TextUtilKt.txt(R.string.no_episodes_found, new Object[0]);
            } else if (loadResponse instanceof AnimeLoadResponse) {
                Map<DubStatus, List<Episode>> episodes = ((AnimeLoadResponse) loadResponse).getEpisodes();
                if (episodes.isEmpty()) {
                    uiTextTxt3 = TextUtilKt.txt(R.string.no_episodes_found, new Object[0]);
                } else {
                    Iterator<Map.Entry<DubStatus, List<Episode>>> it = episodes.entrySet().iterator();
                    while (it.hasNext()) {
                        if (!it.next().getValue().isEmpty()) {
                        }
                    }
                    uiTextTxt3 = TextUtilKt.txt(R.string.no_episodes_found, new Object[0]);
                }
            }
            return new ResultData(url, list, actors3, uiTextTxtNull, comingSoon, backgroundPosterUrl2, name, syncData, str, str2, logoUrl, uiText3, uiTextTxt7, uiTextTxt8, uiTextTxtNull5, uiTextTxtNull6, uiTextTxt9, uiTextTxt10, uiTextTxtNull2, uiTextTxt3, uiTextTxt5, uiTextTxt6, uiTextTxtNull4, uiText, uiText2, uiTextTxt4, posterHeaders);
        }
        throw new DebugException("Api returned wrong apiName");
    }
}
