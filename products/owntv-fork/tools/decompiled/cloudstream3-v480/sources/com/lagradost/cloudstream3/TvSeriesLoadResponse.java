package com.lagradost.cloudstream3;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.google.android.gms.common.internal.ImagesContract;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: MainAPI.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000\u0084\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010%\n\u0000\n\u0002\u0010$\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\bJ\n\u0002\u0018\u0002\n\u0002\b\u001f\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0086\b\u0018\u00002\u00020\u00012\u00020\u0002BÉ\u0002\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0011\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0013\u0012\u0010\b\u0002\u0010\u0014\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\n\u0012\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u000e\u0012\u000e\b\u0002\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00180\u0017\u0012\u0010\b\u0002\u0010\u0019\u001a\n\u0012\u0004\u0012\u00020\u001a\u0018\u00010\n\u0012\u0010\b\u0002\u0010\u001b\u001a\n\u0012\u0004\u0012\u00020\u001c\u0018\u00010\n\u0012\b\b\u0002\u0010\u001d\u001a\u00020\u001e\u0012\u0014\b\u0002\u0010\u001f\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040 \u0012\u0016\b\u0002\u0010!\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\"\u0012\n\b\u0002\u0010#\u001a\u0004\u0018\u00010$\u0012\u0010\b\u0002\u0010%\u001a\n\u0012\u0004\u0012\u00020&\u0018\u00010\n\u0012\n\b\u0002\u0010'\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010(\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010)\u001a\u0004\u0018\u00010\u0004\u0012\b\b\u0002\u0010*\u001a\u00020\u0004¢\u0006\u0004\b+\u0010,J\u0016\u0010p\u001a\u0010\u0012\u0004\u0012\u00020q\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\"H\u0016J\u0018\u0010r\u001a\u00020\u000e2\u0006\u0010s\u001a\u00020\u000e2\u0006\u0010t\u001a\u00020\u000eH\u0016J\t\u0010u\u001a\u00020\u0004HÆ\u0003J\t\u0010v\u001a\u00020\u0004HÆ\u0003J\t\u0010w\u001a\u00020\u0004HÆ\u0003J\t\u0010x\u001a\u00020\bHÆ\u0003J\u000f\u0010y\u001a\b\u0012\u0004\u0012\u00020\u000b0\nHÆ\u0003J\u000b\u0010z\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u0010\u0010{\u001a\u0004\u0018\u00010\u000eHÆ\u0003¢\u0006\u0002\u0010@J\u000b\u0010|\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u000b\u0010}\u001a\u0004\u0018\u00010\u0011HÆ\u0003J\u000b\u0010~\u001a\u0004\u0018\u00010\u0013HÆ\u0003J\u0011\u0010\u007f\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\nHÆ\u0003J\u0011\u0010\u0080\u0001\u001a\u0004\u0018\u00010\u000eHÆ\u0003¢\u0006\u0002\u0010@J\u0010\u0010\u0081\u0001\u001a\b\u0012\u0004\u0012\u00020\u00180\u0017HÆ\u0003J\u0012\u0010\u0082\u0001\u001a\n\u0012\u0004\u0012\u00020\u001a\u0018\u00010\nHÆ\u0003J\u0012\u0010\u0083\u0001\u001a\n\u0012\u0004\u0012\u00020\u001c\u0018\u00010\nHÆ\u0003J\n\u0010\u0084\u0001\u001a\u00020\u001eHÆ\u0003J\u0016\u0010\u0085\u0001\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040 HÆ\u0003J\u0018\u0010\u0086\u0001\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\"HÆ\u0003J\f\u0010\u0087\u0001\u001a\u0004\u0018\u00010$HÆ\u0003J\u0012\u0010\u0088\u0001\u001a\n\u0012\u0004\u0012\u00020&\u0018\u00010\nHÆ\u0003J\f\u0010\u0089\u0001\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\f\u0010\u008a\u0001\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\f\u0010\u008b\u0001\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\n\u0010\u008c\u0001\u001a\u00020\u0004HÆ\u0003JÚ\u0002\u0010\u008d\u0001\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0006\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\b2\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00132\u0010\b\u0002\u0010\u0014\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\n2\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u000e2\u000e\b\u0002\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00180\u00172\u0010\b\u0002\u0010\u0019\u001a\n\u0012\u0004\u0012\u00020\u001a\u0018\u00010\n2\u0010\b\u0002\u0010\u001b\u001a\n\u0012\u0004\u0012\u00020\u001c\u0018\u00010\n2\b\b\u0002\u0010\u001d\u001a\u00020\u001e2\u0014\b\u0002\u0010\u001f\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040 2\u0016\b\u0002\u0010!\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\"2\n\b\u0002\u0010#\u001a\u0004\u0018\u00010$2\u0010\b\u0002\u0010%\u001a\n\u0012\u0004\u0012\u00020&\u0018\u00010\n2\n\b\u0002\u0010'\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010(\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010)\u001a\u0004\u0018\u00010\u00042\b\b\u0002\u0010*\u001a\u00020\u0004HÆ\u0001¢\u0006\u0003\u0010\u008e\u0001J\u0017\u0010\u008f\u0001\u001a\u00020\u001e2\n\u0010\u0090\u0001\u001a\u0005\u0018\u00010\u0091\u0001HÖ\u0083\u0004J\u000b\u0010\u0092\u0001\u001a\u00020\u000eHÖ\u0081\u0004J\u000b\u0010\u0093\u0001\u001a\u00020\u0004HÖ\u0081\u0004R\u001a\u0010\u0003\u001a\u00020\u0004X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b-\u0010.\"\u0004\b/\u00100R\u001a\u0010\u0005\u001a\u00020\u0004X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b1\u0010.\"\u0004\b2\u00100R\u001a\u0010\u0006\u001a\u00020\u0004X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b3\u0010.\"\u0004\b4\u00100R\u001a\u0010\u0007\u001a\u00020\bX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b5\u00106\"\u0004\b7\u00108R \u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b9\u0010:\"\u0004\b;\u0010<R\u001c\u0010\f\u001a\u0004\u0018\u00010\u0004X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b=\u0010.\"\u0004\b>\u00100R\u001e\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0096\u000e¢\u0006\u0010\n\u0002\u0010C\u001a\u0004\b?\u0010@\"\u0004\bA\u0010BR\u001c\u0010\u000f\u001a\u0004\u0018\u00010\u0004X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bD\u0010.\"\u0004\bE\u00100R\u001c\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bF\u0010G\"\u0004\bH\u0010IR\u001c\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bJ\u0010K\"\u0004\bL\u0010MR\"\u0010\u0014\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\nX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bN\u0010:\"\u0004\bO\u0010<R\u001e\u0010\u0015\u001a\u0004\u0018\u00010\u000eX\u0096\u000e¢\u0006\u0010\n\u0002\u0010C\u001a\u0004\bP\u0010@\"\u0004\bQ\u0010BR \u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00180\u0017X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bR\u0010:\"\u0004\bS\u0010<R\"\u0010\u0019\u001a\n\u0012\u0004\u0012\u00020\u001a\u0018\u00010\nX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bT\u0010:\"\u0004\bU\u0010<R\"\u0010\u001b\u001a\n\u0012\u0004\u0012\u00020\u001c\u0018\u00010\nX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bV\u0010:\"\u0004\bW\u0010<R\u001a\u0010\u001d\u001a\u00020\u001eX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bX\u0010Y\"\u0004\bZ\u0010[R&\u0010\u001f\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040 X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\\\u0010]\"\u0004\b^\u0010_R(\u0010!\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\"X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b`\u0010]\"\u0004\ba\u0010_R\u001c\u0010#\u001a\u0004\u0018\u00010$X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bb\u0010c\"\u0004\bd\u0010eR\"\u0010%\u001a\n\u0012\u0004\u0012\u00020&\u0018\u00010\nX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bf\u0010:\"\u0004\bg\u0010<R\u001c\u0010'\u001a\u0004\u0018\u00010\u0004X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bh\u0010.\"\u0004\bi\u00100R\u001c\u0010(\u001a\u0004\u0018\u00010\u0004X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bj\u0010.\"\u0004\bk\u00100R\u001c\u0010)\u001a\u0004\u0018\u00010\u0004X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bl\u0010.\"\u0004\bm\u00100R\u001a\u0010*\u001a\u00020\u0004X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bn\u0010.\"\u0004\bo\u00100¨\u0006\u0094\u0001"}, d2 = {"Lcom/lagradost/cloudstream3/TvSeriesLoadResponse;", "Lcom/lagradost/cloudstream3/LoadResponse;", "Lcom/lagradost/cloudstream3/EpisodeResponse;", "name", "", ImagesContract.URL, "apiName", "type", "Lcom/lagradost/cloudstream3/TvType;", "episodes", "", "Lcom/lagradost/cloudstream3/Episode;", "posterUrl", "year", "", "plot", "showStatus", "Lcom/lagradost/cloudstream3/ShowStatus;", "score", "Lcom/lagradost/cloudstream3/Score;", "tags", TypedValues.TransitionType.S_DURATION, "trailers", "", "Lcom/lagradost/cloudstream3/TrailerData;", "recommendations", "Lcom/lagradost/cloudstream3/SearchResponse;", "actors", "Lcom/lagradost/cloudstream3/ActorData;", "comingSoon", "", "syncData", "", "posterHeaders", "", "nextAiring", "Lcom/lagradost/cloudstream3/NextAiring;", "seasonNames", "Lcom/lagradost/cloudstream3/SeasonData;", "backgroundPosterUrl", "logoUrl", "contentRating", "uniqueUrl", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/lagradost/cloudstream3/TvType;Ljava/util/List;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Lcom/lagradost/cloudstream3/ShowStatus;Lcom/lagradost/cloudstream3/Score;Ljava/util/List;Ljava/lang/Integer;Ljava/util/List;Ljava/util/List;Ljava/util/List;ZLjava/util/Map;Ljava/util/Map;Lcom/lagradost/cloudstream3/NextAiring;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getName", "()Ljava/lang/String;", "setName", "(Ljava/lang/String;)V", "getUrl", "setUrl", "getApiName", "setApiName", "getType", "()Lcom/lagradost/cloudstream3/TvType;", "setType", "(Lcom/lagradost/cloudstream3/TvType;)V", "getEpisodes", "()Ljava/util/List;", "setEpisodes", "(Ljava/util/List;)V", "getPosterUrl", "setPosterUrl", "getYear", "()Ljava/lang/Integer;", "setYear", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "getPlot", "setPlot", "getShowStatus", "()Lcom/lagradost/cloudstream3/ShowStatus;", "setShowStatus", "(Lcom/lagradost/cloudstream3/ShowStatus;)V", "getScore", "()Lcom/lagradost/cloudstream3/Score;", "setScore", "(Lcom/lagradost/cloudstream3/Score;)V", "getTags", "setTags", "getDuration", "setDuration", "getTrailers", "setTrailers", "getRecommendations", "setRecommendations", "getActors", "setActors", "getComingSoon", "()Z", "setComingSoon", "(Z)V", "getSyncData", "()Ljava/util/Map;", "setSyncData", "(Ljava/util/Map;)V", "getPosterHeaders", "setPosterHeaders", "getNextAiring", "()Lcom/lagradost/cloudstream3/NextAiring;", "setNextAiring", "(Lcom/lagradost/cloudstream3/NextAiring;)V", "getSeasonNames", "setSeasonNames", "getBackgroundPosterUrl", "setBackgroundPosterUrl", "getLogoUrl", "setLogoUrl", "getContentRating", "setContentRating", "getUniqueUrl", "setUniqueUrl", "getLatestEpisodes", "Lcom/lagradost/cloudstream3/DubStatus;", "getTotalEpisodeIndex", "episode", "season", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "component20", "component21", "component22", "component23", "component24", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/lagradost/cloudstream3/TvType;Ljava/util/List;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Lcom/lagradost/cloudstream3/ShowStatus;Lcom/lagradost/cloudstream3/Score;Ljava/util/List;Ljava/lang/Integer;Ljava/util/List;Ljava/util/List;Ljava/util/List;ZLjava/util/Map;Ljava/util/Map;Lcom/lagradost/cloudstream3/NextAiring;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lcom/lagradost/cloudstream3/TvSeriesLoadResponse;", "equals", "other", "", "hashCode", "toString", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class TvSeriesLoadResponse implements LoadResponse, EpisodeResponse {
    private List<ActorData> actors;
    private String apiName;
    private String backgroundPosterUrl;
    private boolean comingSoon;
    private String contentRating;
    private Integer duration;
    private List<Episode> episodes;
    private String logoUrl;
    private String name;
    private NextAiring nextAiring;
    private String plot;
    private Map<String, String> posterHeaders;
    private String posterUrl;
    private List<? extends SearchResponse> recommendations;
    private Score score;
    private List<SeasonData> seasonNames;
    private ShowStatus showStatus;
    private Map<String, String> syncData;
    private List<String> tags;
    private List<TrailerData> trailers;
    private TvType type;
    private String uniqueUrl;
    private String url;
    private Integer year;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ TvSeriesLoadResponse copy$default(TvSeriesLoadResponse tvSeriesLoadResponse, String str, String str2, String str3, TvType tvType, List list, String str4, Integer num, String str5, ShowStatus showStatus, Score score, List list2, Integer num2, List list3, List list4, List list5, boolean z, Map map, Map map2, NextAiring nextAiring, List list6, String str6, String str7, String str8, String str9, int i, Object obj) {
        String str10;
        String str11;
        String str12 = (i & 1) != 0 ? tvSeriesLoadResponse.name : str;
        String str13 = (i & 2) != 0 ? tvSeriesLoadResponse.url : str2;
        String str14 = (i & 4) != 0 ? tvSeriesLoadResponse.apiName : str3;
        TvType tvType2 = (i & 8) != 0 ? tvSeriesLoadResponse.type : tvType;
        List list7 = (i & 16) != 0 ? tvSeriesLoadResponse.episodes : list;
        String str15 = (i & 32) != 0 ? tvSeriesLoadResponse.posterUrl : str4;
        Integer num3 = (i & 64) != 0 ? tvSeriesLoadResponse.year : num;
        String str16 = (i & 128) != 0 ? tvSeriesLoadResponse.plot : str5;
        ShowStatus showStatus2 = (i & 256) != 0 ? tvSeriesLoadResponse.showStatus : showStatus;
        Score score2 = (i & 512) != 0 ? tvSeriesLoadResponse.score : score;
        List list8 = (i & 1024) != 0 ? tvSeriesLoadResponse.tags : list2;
        Integer num4 = (i & 2048) != 0 ? tvSeriesLoadResponse.duration : num2;
        List list9 = (i & 4096) != 0 ? tvSeriesLoadResponse.trailers : list3;
        List list10 = (i & 8192) != 0 ? tvSeriesLoadResponse.recommendations : list4;
        String str17 = str12;
        List list11 = (i & 16384) != 0 ? tvSeriesLoadResponse.actors : list5;
        boolean z2 = (i & 32768) != 0 ? tvSeriesLoadResponse.comingSoon : z;
        Map map3 = (i & 65536) != 0 ? tvSeriesLoadResponse.syncData : map;
        Map map4 = (i & 131072) != 0 ? tvSeriesLoadResponse.posterHeaders : map2;
        NextAiring nextAiring2 = (i & 262144) != 0 ? tvSeriesLoadResponse.nextAiring : nextAiring;
        List list12 = (i & 524288) != 0 ? tvSeriesLoadResponse.seasonNames : list6;
        String str18 = (i & 1048576) != 0 ? tvSeriesLoadResponse.backgroundPosterUrl : str6;
        String str19 = (i & 2097152) != 0 ? tvSeriesLoadResponse.logoUrl : str7;
        String str20 = (i & 4194304) != 0 ? tvSeriesLoadResponse.contentRating : str8;
        if ((i & 8388608) != 0) {
            str11 = str20;
            str10 = tvSeriesLoadResponse.uniqueUrl;
        } else {
            str10 = str9;
            str11 = str20;
        }
        return tvSeriesLoadResponse.copy(str17, str13, str14, tvType2, list7, str15, num3, str16, showStatus2, score2, list8, num4, list9, list10, list11, z2, map3, map4, nextAiring2, list12, str18, str19, str11, str10);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* JADX INFO: renamed from: component10, reason: from getter */
    public final Score getScore() {
        return this.score;
    }

    public final List<String> component11() {
        return this.tags;
    }

    /* JADX INFO: renamed from: component12, reason: from getter */
    public final Integer getDuration() {
        return this.duration;
    }

    public final List<TrailerData> component13() {
        return this.trailers;
    }

    public final List<SearchResponse> component14() {
        return this.recommendations;
    }

    public final List<ActorData> component15() {
        return this.actors;
    }

    /* JADX INFO: renamed from: component16, reason: from getter */
    public final boolean getComingSoon() {
        return this.comingSoon;
    }

    public final Map<String, String> component17() {
        return this.syncData;
    }

    public final Map<String, String> component18() {
        return this.posterHeaders;
    }

    /* JADX INFO: renamed from: component19, reason: from getter */
    public final NextAiring getNextAiring() {
        return this.nextAiring;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getUrl() {
        return this.url;
    }

    public final List<SeasonData> component20() {
        return this.seasonNames;
    }

    /* JADX INFO: renamed from: component21, reason: from getter */
    public final String getBackgroundPosterUrl() {
        return this.backgroundPosterUrl;
    }

    /* JADX INFO: renamed from: component22, reason: from getter */
    public final String getLogoUrl() {
        return this.logoUrl;
    }

    /* JADX INFO: renamed from: component23, reason: from getter */
    public final String getContentRating() {
        return this.contentRating;
    }

    /* JADX INFO: renamed from: component24, reason: from getter */
    public final String getUniqueUrl() {
        return this.uniqueUrl;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getApiName() {
        return this.apiName;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final TvType getType() {
        return this.type;
    }

    public final List<Episode> component5() {
        return this.episodes;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final String getPosterUrl() {
        return this.posterUrl;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final Integer getYear() {
        return this.year;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final String getPlot() {
        return this.plot;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final ShowStatus getShowStatus() {
        return this.showStatus;
    }

    public final TvSeriesLoadResponse copy(String name, String url, String apiName, TvType type, List<Episode> episodes, String posterUrl, Integer year, String plot, ShowStatus showStatus, Score score, List<String> tags, Integer duration, List<TrailerData> trailers, List<? extends SearchResponse> recommendations, List<ActorData> actors, boolean comingSoon, Map<String, String> syncData, Map<String, String> posterHeaders, NextAiring nextAiring, List<SeasonData> seasonNames, String backgroundPosterUrl, String logoUrl, String contentRating, String uniqueUrl) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(url, "url");
        Intrinsics.checkNotNullParameter(apiName, "apiName");
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(episodes, "episodes");
        Intrinsics.checkNotNullParameter(trailers, "trailers");
        Intrinsics.checkNotNullParameter(syncData, "syncData");
        Intrinsics.checkNotNullParameter(uniqueUrl, "uniqueUrl");
        return new TvSeriesLoadResponse(name, url, apiName, type, episodes, posterUrl, year, plot, showStatus, score, tags, duration, trailers, recommendations, actors, comingSoon, syncData, posterHeaders, nextAiring, seasonNames, backgroundPosterUrl, logoUrl, contentRating, uniqueUrl);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TvSeriesLoadResponse)) {
            return false;
        }
        TvSeriesLoadResponse tvSeriesLoadResponse = (TvSeriesLoadResponse) other;
        return Intrinsics.areEqual(this.name, tvSeriesLoadResponse.name) && Intrinsics.areEqual(this.url, tvSeriesLoadResponse.url) && Intrinsics.areEqual(this.apiName, tvSeriesLoadResponse.apiName) && this.type == tvSeriesLoadResponse.type && Intrinsics.areEqual(this.episodes, tvSeriesLoadResponse.episodes) && Intrinsics.areEqual(this.posterUrl, tvSeriesLoadResponse.posterUrl) && Intrinsics.areEqual(this.year, tvSeriesLoadResponse.year) && Intrinsics.areEqual(this.plot, tvSeriesLoadResponse.plot) && this.showStatus == tvSeriesLoadResponse.showStatus && Intrinsics.areEqual(this.score, tvSeriesLoadResponse.score) && Intrinsics.areEqual(this.tags, tvSeriesLoadResponse.tags) && Intrinsics.areEqual(this.duration, tvSeriesLoadResponse.duration) && Intrinsics.areEqual(this.trailers, tvSeriesLoadResponse.trailers) && Intrinsics.areEqual(this.recommendations, tvSeriesLoadResponse.recommendations) && Intrinsics.areEqual(this.actors, tvSeriesLoadResponse.actors) && this.comingSoon == tvSeriesLoadResponse.comingSoon && Intrinsics.areEqual(this.syncData, tvSeriesLoadResponse.syncData) && Intrinsics.areEqual(this.posterHeaders, tvSeriesLoadResponse.posterHeaders) && Intrinsics.areEqual(this.nextAiring, tvSeriesLoadResponse.nextAiring) && Intrinsics.areEqual(this.seasonNames, tvSeriesLoadResponse.seasonNames) && Intrinsics.areEqual(this.backgroundPosterUrl, tvSeriesLoadResponse.backgroundPosterUrl) && Intrinsics.areEqual(this.logoUrl, tvSeriesLoadResponse.logoUrl) && Intrinsics.areEqual(this.contentRating, tvSeriesLoadResponse.contentRating) && Intrinsics.areEqual(this.uniqueUrl, tvSeriesLoadResponse.uniqueUrl);
    }

    public int hashCode() {
        int iHashCode = ((((((((this.name.hashCode() * 31) + this.url.hashCode()) * 31) + this.apiName.hashCode()) * 31) + this.type.hashCode()) * 31) + this.episodes.hashCode()) * 31;
        String str = this.posterUrl;
        int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
        Integer num = this.year;
        int iHashCode3 = (iHashCode2 + (num == null ? 0 : num.hashCode())) * 31;
        String str2 = this.plot;
        int iHashCode4 = (iHashCode3 + (str2 == null ? 0 : str2.hashCode())) * 31;
        ShowStatus showStatus = this.showStatus;
        int iHashCode5 = (iHashCode4 + (showStatus == null ? 0 : showStatus.hashCode())) * 31;
        Score score = this.score;
        int data = (iHashCode5 + (score == null ? 0 : score.getData())) * 31;
        List<String> list = this.tags;
        int iHashCode6 = (data + (list == null ? 0 : list.hashCode())) * 31;
        Integer num2 = this.duration;
        int iHashCode7 = (((iHashCode6 + (num2 == null ? 0 : num2.hashCode())) * 31) + this.trailers.hashCode()) * 31;
        List<? extends SearchResponse> list2 = this.recommendations;
        int iHashCode8 = (iHashCode7 + (list2 == null ? 0 : list2.hashCode())) * 31;
        List<ActorData> list3 = this.actors;
        int iHashCode9 = (((((iHashCode8 + (list3 == null ? 0 : list3.hashCode())) * 31) + NextAiring$$ExternalSyntheticBackport0.m(this.comingSoon)) * 31) + this.syncData.hashCode()) * 31;
        Map<String, String> map = this.posterHeaders;
        int iHashCode10 = (iHashCode9 + (map == null ? 0 : map.hashCode())) * 31;
        NextAiring nextAiring = this.nextAiring;
        int iHashCode11 = (iHashCode10 + (nextAiring == null ? 0 : nextAiring.hashCode())) * 31;
        List<SeasonData> list4 = this.seasonNames;
        int iHashCode12 = (iHashCode11 + (list4 == null ? 0 : list4.hashCode())) * 31;
        String str3 = this.backgroundPosterUrl;
        int iHashCode13 = (iHashCode12 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.logoUrl;
        int iHashCode14 = (iHashCode13 + (str4 == null ? 0 : str4.hashCode())) * 31;
        String str5 = this.contentRating;
        return ((iHashCode14 + (str5 != null ? str5.hashCode() : 0)) * 31) + this.uniqueUrl.hashCode();
    }

    public String toString() {
        return "TvSeriesLoadResponse(name=" + this.name + ", url=" + this.url + ", apiName=" + this.apiName + ", type=" + this.type + ", episodes=" + this.episodes + ", posterUrl=" + this.posterUrl + ", year=" + this.year + ", plot=" + this.plot + ", showStatus=" + this.showStatus + ", score=" + this.score + ", tags=" + this.tags + ", duration=" + this.duration + ", trailers=" + this.trailers + ", recommendations=" + this.recommendations + ", actors=" + this.actors + ", comingSoon=" + this.comingSoon + ", syncData=" + this.syncData + ", posterHeaders=" + this.posterHeaders + ", nextAiring=" + this.nextAiring + ", seasonNames=" + this.seasonNames + ", backgroundPosterUrl=" + this.backgroundPosterUrl + ", logoUrl=" + this.logoUrl + ", contentRating=" + this.contentRating + ", uniqueUrl=" + this.uniqueUrl + ')';
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use newTvSeriesLoadResponse method")
    public TvSeriesLoadResponse(String name, String url, String apiName, TvType type, List<Episode> episodes, String str, Integer num, String str2, ShowStatus showStatus, Score score, List<String> list, Integer num2, List<TrailerData> trailers, List<? extends SearchResponse> list2, List<ActorData> list3, boolean z, Map<String, String> syncData, Map<String, String> map, NextAiring nextAiring, List<SeasonData> list4, String str3, String str4, String str5, String uniqueUrl) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(url, "url");
        Intrinsics.checkNotNullParameter(apiName, "apiName");
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(episodes, "episodes");
        Intrinsics.checkNotNullParameter(trailers, "trailers");
        Intrinsics.checkNotNullParameter(syncData, "syncData");
        Intrinsics.checkNotNullParameter(uniqueUrl, "uniqueUrl");
        this.name = name;
        this.url = url;
        this.apiName = apiName;
        this.type = type;
        this.episodes = episodes;
        this.posterUrl = str;
        this.year = num;
        this.plot = str2;
        this.showStatus = showStatus;
        this.score = score;
        this.tags = list;
        this.duration = num2;
        this.trailers = trailers;
        this.recommendations = list2;
        this.actors = list3;
        this.comingSoon = z;
        this.syncData = syncData;
        this.posterHeaders = map;
        this.nextAiring = nextAiring;
        this.seasonNames = list4;
        this.backgroundPosterUrl = str3;
        this.logoUrl = str4;
        this.contentRating = str5;
        this.uniqueUrl = uniqueUrl;
    }

    @Override // com.lagradost.cloudstream3.LoadResponse
    public /* bridge */ Integer getRating() {
        return LoadResponse.CC.$default$getRating(this);
    }

    @Override // com.lagradost.cloudstream3.LoadResponse
    public /* bridge */ void setRating(Integer num) {
        LoadResponse.CC.$default$setRating(this, num);
    }

    @Override // com.lagradost.cloudstream3.LoadResponse
    public String getName() {
        return this.name;
    }

    @Override // com.lagradost.cloudstream3.LoadResponse
    public void setName(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.name = str;
    }

    @Override // com.lagradost.cloudstream3.LoadResponse
    public String getUrl() {
        return this.url;
    }

    @Override // com.lagradost.cloudstream3.LoadResponse
    public void setUrl(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.url = str;
    }

    @Override // com.lagradost.cloudstream3.LoadResponse
    public String getApiName() {
        return this.apiName;
    }

    @Override // com.lagradost.cloudstream3.LoadResponse
    public void setApiName(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.apiName = str;
    }

    @Override // com.lagradost.cloudstream3.LoadResponse
    public TvType getType() {
        return this.type;
    }

    @Override // com.lagradost.cloudstream3.LoadResponse
    public void setType(TvType tvType) {
        Intrinsics.checkNotNullParameter(tvType, "<set-?>");
        this.type = tvType;
    }

    public final List<Episode> getEpisodes() {
        return this.episodes;
    }

    public final void setEpisodes(List<Episode> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.episodes = list;
    }

    @Override // com.lagradost.cloudstream3.LoadResponse
    public String getPosterUrl() {
        return this.posterUrl;
    }

    @Override // com.lagradost.cloudstream3.LoadResponse
    public void setPosterUrl(String str) {
        this.posterUrl = str;
    }

    @Override // com.lagradost.cloudstream3.LoadResponse
    public Integer getYear() {
        return this.year;
    }

    @Override // com.lagradost.cloudstream3.LoadResponse
    public void setYear(Integer num) {
        this.year = num;
    }

    @Override // com.lagradost.cloudstream3.LoadResponse
    public String getPlot() {
        return this.plot;
    }

    @Override // com.lagradost.cloudstream3.LoadResponse
    public void setPlot(String str) {
        this.plot = str;
    }

    @Override // com.lagradost.cloudstream3.EpisodeResponse
    public ShowStatus getShowStatus() {
        return this.showStatus;
    }

    @Override // com.lagradost.cloudstream3.EpisodeResponse
    public void setShowStatus(ShowStatus showStatus) {
        this.showStatus = showStatus;
    }

    @Override // com.lagradost.cloudstream3.LoadResponse
    public Score getScore() {
        return this.score;
    }

    @Override // com.lagradost.cloudstream3.LoadResponse
    public void setScore(Score score) {
        this.score = score;
    }

    @Override // com.lagradost.cloudstream3.LoadResponse
    public List<String> getTags() {
        return this.tags;
    }

    @Override // com.lagradost.cloudstream3.LoadResponse
    public void setTags(List<String> list) {
        this.tags = list;
    }

    @Override // com.lagradost.cloudstream3.LoadResponse
    public Integer getDuration() {
        return this.duration;
    }

    @Override // com.lagradost.cloudstream3.LoadResponse
    public void setDuration(Integer num) {
        this.duration = num;
    }

    public /* synthetic */ TvSeriesLoadResponse(String str, String str2, String str3, TvType tvType, List list, String str4, Integer num, String str5, ShowStatus showStatus, Score score, List list2, Integer num2, List list3, List list4, List list5, boolean z, Map map, Map map2, NextAiring nextAiring, List list6, String str6, String str7, String str8, String str9, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, str3, tvType, list, (i & 32) != 0 ? null : str4, (i & 64) != 0 ? null : num, (i & 128) != 0 ? null : str5, (i & 256) != 0 ? null : showStatus, (i & 512) != 0 ? null : score, (i & 1024) != 0 ? null : list2, (i & 2048) != 0 ? null : num2, (i & 4096) != 0 ? new ArrayList() : list3, (i & 8192) != 0 ? null : list4, (i & 16384) != 0 ? null : list5, (32768 & i) != 0 ? false : z, (65536 & i) != 0 ? new LinkedHashMap() : map, (131072 & i) != 0 ? null : map2, (262144 & i) != 0 ? null : nextAiring, (524288 & i) != 0 ? null : list6, (1048576 & i) != 0 ? null : str6, (2097152 & i) != 0 ? null : str7, (4194304 & i) != 0 ? null : str8, (i & 8388608) != 0 ? str2 : str9);
    }

    @Override // com.lagradost.cloudstream3.LoadResponse
    public List<TrailerData> getTrailers() {
        return this.trailers;
    }

    @Override // com.lagradost.cloudstream3.LoadResponse
    public void setTrailers(List<TrailerData> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.trailers = list;
    }

    @Override // com.lagradost.cloudstream3.LoadResponse
    public List<SearchResponse> getRecommendations() {
        return this.recommendations;
    }

    @Override // com.lagradost.cloudstream3.LoadResponse
    public void setRecommendations(List<? extends SearchResponse> list) {
        this.recommendations = list;
    }

    @Override // com.lagradost.cloudstream3.LoadResponse
    public List<ActorData> getActors() {
        return this.actors;
    }

    @Override // com.lagradost.cloudstream3.LoadResponse
    public void setActors(List<ActorData> list) {
        this.actors = list;
    }

    @Override // com.lagradost.cloudstream3.LoadResponse
    public boolean getComingSoon() {
        return this.comingSoon;
    }

    @Override // com.lagradost.cloudstream3.LoadResponse
    public void setComingSoon(boolean z) {
        this.comingSoon = z;
    }

    @Override // com.lagradost.cloudstream3.LoadResponse
    public Map<String, String> getSyncData() {
        return this.syncData;
    }

    @Override // com.lagradost.cloudstream3.LoadResponse
    public void setSyncData(Map<String, String> map) {
        Intrinsics.checkNotNullParameter(map, "<set-?>");
        this.syncData = map;
    }

    @Override // com.lagradost.cloudstream3.LoadResponse
    public Map<String, String> getPosterHeaders() {
        return this.posterHeaders;
    }

    @Override // com.lagradost.cloudstream3.LoadResponse
    public void setPosterHeaders(Map<String, String> map) {
        this.posterHeaders = map;
    }

    @Override // com.lagradost.cloudstream3.EpisodeResponse
    public NextAiring getNextAiring() {
        return this.nextAiring;
    }

    @Override // com.lagradost.cloudstream3.EpisodeResponse
    public void setNextAiring(NextAiring nextAiring) {
        this.nextAiring = nextAiring;
    }

    @Override // com.lagradost.cloudstream3.EpisodeResponse
    public List<SeasonData> getSeasonNames() {
        return this.seasonNames;
    }

    @Override // com.lagradost.cloudstream3.EpisodeResponse
    public void setSeasonNames(List<SeasonData> list) {
        this.seasonNames = list;
    }

    @Override // com.lagradost.cloudstream3.LoadResponse
    public String getBackgroundPosterUrl() {
        return this.backgroundPosterUrl;
    }

    @Override // com.lagradost.cloudstream3.LoadResponse
    public void setBackgroundPosterUrl(String str) {
        this.backgroundPosterUrl = str;
    }

    @Override // com.lagradost.cloudstream3.LoadResponse
    public String getLogoUrl() {
        return this.logoUrl;
    }

    @Override // com.lagradost.cloudstream3.LoadResponse
    public void setLogoUrl(String str) {
        this.logoUrl = str;
    }

    @Override // com.lagradost.cloudstream3.LoadResponse
    public String getContentRating() {
        return this.contentRating;
    }

    @Override // com.lagradost.cloudstream3.LoadResponse
    public void setContentRating(String str) {
        this.contentRating = str;
    }

    @Override // com.lagradost.cloudstream3.LoadResponse
    public String getUniqueUrl() {
        return this.uniqueUrl;
    }

    @Override // com.lagradost.cloudstream3.LoadResponse
    public void setUniqueUrl(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.uniqueUrl = str;
    }

    @Override // com.lagradost.cloudstream3.EpisodeResponse
    public Map<DubStatus, Integer> getLatestEpisodes() {
        Integer numValueOf;
        Integer numValueOf2;
        Iterator<T> it = this.episodes.iterator();
        if (it.hasNext()) {
            Integer season = ((Episode) it.next()).getSeason();
            numValueOf = Integer.valueOf(season != null ? season.intValue() : Integer.MIN_VALUE);
            while (it.hasNext()) {
                Integer season2 = ((Episode) it.next()).getSeason();
                Integer numValueOf3 = Integer.valueOf(season2 != null ? season2.intValue() : Integer.MIN_VALUE);
                if (numValueOf.compareTo(numValueOf3) < 0) {
                    numValueOf = numValueOf3;
                }
            }
        } else {
            numValueOf = null;
        }
        Integer num = numValueOf;
        if (num != null && num.intValue() == Integer.MIN_VALUE) {
            numValueOf = null;
        }
        Integer num2 = numValueOf;
        List<Episode> list = this.episodes;
        ArrayList arrayList = new ArrayList();
        for (Object obj : list) {
            if (Intrinsics.areEqual(((Episode) obj).getSeason(), num2)) {
                arrayList.add(obj);
            }
        }
        Iterator it2 = arrayList.iterator();
        if (it2.hasNext()) {
            Integer episode = ((Episode) it2.next()).getEpisode();
            numValueOf2 = Integer.valueOf(episode != null ? episode.intValue() : Integer.MIN_VALUE);
            while (it2.hasNext()) {
                Integer episode2 = ((Episode) it2.next()).getEpisode();
                Integer numValueOf4 = Integer.valueOf(episode2 != null ? episode2.intValue() : Integer.MIN_VALUE);
                if (numValueOf2.compareTo(numValueOf4) < 0) {
                    numValueOf2 = numValueOf4;
                }
            }
        } else {
            numValueOf2 = null;
        }
        Integer num3 = numValueOf2;
        return MapsKt.mapOf(TuplesKt.to(DubStatus.None, (num3 == null || num3.intValue() != Integer.MIN_VALUE) ? numValueOf2 : null));
    }

    @Override // com.lagradost.cloudstream3.EpisodeResponse
    public int getTotalEpisodeIndex(int episode, int season) {
        LinkedHashMap linkedHashMapEmptyMap;
        int iIntValue;
        List<SeasonData> seasonNames = getSeasonNames();
        if (seasonNames == null) {
            linkedHashMapEmptyMap = MapsKt.emptyMap();
        } else {
            List<SeasonData> list = seasonNames;
            linkedHashMapEmptyMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(list, 10)), 16));
            for (SeasonData seasonData : list) {
                Pair pair = TuplesKt.to(Integer.valueOf(seasonData.getSeason()), seasonData.getDisplaySeason());
                linkedHashMapEmptyMap.put(pair.getFirst(), pair.getSecond());
            }
        }
        List<Episode> list2 = this.episodes;
        int i = 0;
        if (!(list2 instanceof Collection) || !list2.isEmpty()) {
            for (Episode episode2 : list2) {
                Integer num = (Integer) linkedHashMapEmptyMap.get(episode2.getSeason());
                if (num != null) {
                    iIntValue = num.intValue();
                } else {
                    Integer season2 = episode2.getSeason();
                    iIntValue = season2 != null ? season2.intValue() : Integer.MIN_VALUE;
                }
                if (1 <= iIntValue && iIntValue < season && (i = i + 1) < 0) {
                    CollectionsKt.throwCountOverflow();
                }
            }
        }
        return i + episode;
    }
}
