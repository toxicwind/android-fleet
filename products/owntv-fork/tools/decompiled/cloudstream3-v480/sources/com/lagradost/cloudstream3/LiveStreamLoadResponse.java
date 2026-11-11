package com.lagradost.cloudstream3;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.google.android.gms.common.internal.ImagesContract;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MainAPI.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010%\n\u0000\n\u0002\u0010$\n\u0002\bX\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0086\b\u0018\u00002\u00020\u0001B\u009b\u0002\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u000b\u001a\u00020\f\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e\u0012\u0010\b\u0002\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0010\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\t\u0012\u000e\b\u0002\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013\u0012\u0010\b\u0002\u0010\u0015\u001a\n\u0012\u0004\u0012\u00020\u0016\u0018\u00010\u0010\u0012\u0010\b\u0002\u0010\u0017\u001a\n\u0012\u0004\u0012\u00020\u0018\u0018\u00010\u0010\u0012\b\b\u0002\u0010\u0019\u001a\u00020\u001a\u0012\u0014\b\u0002\u0010\u001b\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u001c\u0012\u0016\b\u0002\u0010\u001d\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u001e\u0012\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010 \u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010!\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\"\u001a\u00020\u0003¢\u0006\u0004\b#\u0010$J\t\u0010^\u001a\u00020\u0003HÆ\u0003J\t\u0010_\u001a\u00020\u0003HÆ\u0003J\t\u0010`\u001a\u00020\u0003HÆ\u0003J\t\u0010a\u001a\u00020\u0003HÆ\u0003J\u000b\u0010b\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010c\u001a\u0004\u0018\u00010\tHÆ\u0003¢\u0006\u0002\u00102J\u000b\u0010d\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010e\u001a\u00020\fHÆ\u0003J\u000b\u0010f\u001a\u0004\u0018\u00010\u000eHÆ\u0003J\u0011\u0010g\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0010HÆ\u0003J\u0010\u0010h\u001a\u0004\u0018\u00010\tHÆ\u0003¢\u0006\u0002\u00102J\u000f\u0010i\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013HÆ\u0003J\u0011\u0010j\u001a\n\u0012\u0004\u0012\u00020\u0016\u0018\u00010\u0010HÆ\u0003J\u0011\u0010k\u001a\n\u0012\u0004\u0012\u00020\u0018\u0018\u00010\u0010HÆ\u0003J\t\u0010l\u001a\u00020\u001aHÆ\u0003J\u0015\u0010m\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u001cHÆ\u0003J\u0017\u0010n\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u001eHÆ\u0003J\u000b\u0010o\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010p\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010q\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010r\u001a\u00020\u0003HÆ\u0003J¨\u0002\u0010s\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u000b\u001a\u00020\f2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e2\u0010\b\u0002\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u00102\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\t2\u000e\b\u0002\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u00132\u0010\b\u0002\u0010\u0015\u001a\n\u0012\u0004\u0012\u00020\u0016\u0018\u00010\u00102\u0010\b\u0002\u0010\u0017\u001a\n\u0012\u0004\u0012\u00020\u0018\u0018\u00010\u00102\b\b\u0002\u0010\u0019\u001a\u00020\u001a2\u0014\b\u0002\u0010\u001b\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u001c2\u0016\b\u0002\u0010\u001d\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u001e2\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010 \u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010!\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\"\u001a\u00020\u0003HÆ\u0001¢\u0006\u0002\u0010tJ\u0014\u0010u\u001a\u00020\u001a2\b\u0010v\u001a\u0004\u0018\u00010wHÖ\u0083\u0004J\n\u0010x\u001a\u00020\tHÖ\u0081\u0004J\n\u0010y\u001a\u00020\u0003HÖ\u0081\u0004R\u001a\u0010\u0002\u001a\u00020\u0003X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010&\"\u0004\b'\u0010(R\u001a\u0010\u0004\u001a\u00020\u0003X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b)\u0010&\"\u0004\b*\u0010(R\u001a\u0010\u0005\u001a\u00020\u0003X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b+\u0010&\"\u0004\b,\u0010(R\u001a\u0010\u0006\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b-\u0010&\"\u0004\b.\u0010(R\u001c\u0010\u0007\u001a\u0004\u0018\u00010\u0003X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b/\u0010&\"\u0004\b0\u0010(R\u001e\u0010\b\u001a\u0004\u0018\u00010\tX\u0096\u000e¢\u0006\u0010\n\u0002\u00105\u001a\u0004\b1\u00102\"\u0004\b3\u00104R\u001c\u0010\n\u001a\u0004\u0018\u00010\u0003X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b6\u0010&\"\u0004\b7\u0010(R\u001a\u0010\u000b\u001a\u00020\fX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b8\u00109\"\u0004\b:\u0010;R\u001c\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b<\u0010=\"\u0004\b>\u0010?R\"\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0010X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b@\u0010A\"\u0004\bB\u0010CR\u001e\u0010\u0011\u001a\u0004\u0018\u00010\tX\u0096\u000e¢\u0006\u0010\n\u0002\u00105\u001a\u0004\bD\u00102\"\u0004\bE\u00104R \u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bF\u0010A\"\u0004\bG\u0010CR\"\u0010\u0015\u001a\n\u0012\u0004\u0012\u00020\u0016\u0018\u00010\u0010X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bH\u0010A\"\u0004\bI\u0010CR\"\u0010\u0017\u001a\n\u0012\u0004\u0012\u00020\u0018\u0018\u00010\u0010X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bJ\u0010A\"\u0004\bK\u0010CR\u001a\u0010\u0019\u001a\u00020\u001aX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bL\u0010M\"\u0004\bN\u0010OR&\u0010\u001b\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u001cX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bP\u0010Q\"\u0004\bR\u0010SR(\u0010\u001d\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u001eX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bT\u0010Q\"\u0004\bU\u0010SR\u001c\u0010\u001f\u001a\u0004\u0018\u00010\u0003X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bV\u0010&\"\u0004\bW\u0010(R\u001c\u0010 \u001a\u0004\u0018\u00010\u0003X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bX\u0010&\"\u0004\bY\u0010(R\u001c\u0010!\u001a\u0004\u0018\u00010\u0003X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bZ\u0010&\"\u0004\b[\u0010(R\u001a\u0010\"\u001a\u00020\u0003X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\\\u0010&\"\u0004\b]\u0010(¨\u0006z"}, d2 = {"Lcom/lagradost/cloudstream3/LiveStreamLoadResponse;", "Lcom/lagradost/cloudstream3/LoadResponse;", "name", "", ImagesContract.URL, "apiName", "dataUrl", "posterUrl", "year", "", "plot", "type", "Lcom/lagradost/cloudstream3/TvType;", "score", "Lcom/lagradost/cloudstream3/Score;", "tags", "", TypedValues.TransitionType.S_DURATION, "trailers", "", "Lcom/lagradost/cloudstream3/TrailerData;", "recommendations", "Lcom/lagradost/cloudstream3/SearchResponse;", "actors", "Lcom/lagradost/cloudstream3/ActorData;", "comingSoon", "", "syncData", "", "posterHeaders", "", "backgroundPosterUrl", "logoUrl", "contentRating", "uniqueUrl", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Lcom/lagradost/cloudstream3/TvType;Lcom/lagradost/cloudstream3/Score;Ljava/util/List;Ljava/lang/Integer;Ljava/util/List;Ljava/util/List;Ljava/util/List;ZLjava/util/Map;Ljava/util/Map;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getName", "()Ljava/lang/String;", "setName", "(Ljava/lang/String;)V", "getUrl", "setUrl", "getApiName", "setApiName", "getDataUrl", "setDataUrl", "getPosterUrl", "setPosterUrl", "getYear", "()Ljava/lang/Integer;", "setYear", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "getPlot", "setPlot", "getType", "()Lcom/lagradost/cloudstream3/TvType;", "setType", "(Lcom/lagradost/cloudstream3/TvType;)V", "getScore", "()Lcom/lagradost/cloudstream3/Score;", "setScore", "(Lcom/lagradost/cloudstream3/Score;)V", "getTags", "()Ljava/util/List;", "setTags", "(Ljava/util/List;)V", "getDuration", "setDuration", "getTrailers", "setTrailers", "getRecommendations", "setRecommendations", "getActors", "setActors", "getComingSoon", "()Z", "setComingSoon", "(Z)V", "getSyncData", "()Ljava/util/Map;", "setSyncData", "(Ljava/util/Map;)V", "getPosterHeaders", "setPosterHeaders", "getBackgroundPosterUrl", "setBackgroundPosterUrl", "getLogoUrl", "setLogoUrl", "getContentRating", "setContentRating", "getUniqueUrl", "setUniqueUrl", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "component20", "component21", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Lcom/lagradost/cloudstream3/TvType;Lcom/lagradost/cloudstream3/Score;Ljava/util/List;Ljava/lang/Integer;Ljava/util/List;Ljava/util/List;Ljava/util/List;ZLjava/util/Map;Ljava/util/Map;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lcom/lagradost/cloudstream3/LiveStreamLoadResponse;", "equals", "other", "", "hashCode", "toString", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class LiveStreamLoadResponse implements LoadResponse {
    private List<ActorData> actors;
    private String apiName;
    private String backgroundPosterUrl;
    private boolean comingSoon;
    private String contentRating;
    private String dataUrl;
    private Integer duration;
    private String logoUrl;
    private String name;
    private String plot;
    private Map<String, String> posterHeaders;
    private String posterUrl;
    private List<? extends SearchResponse> recommendations;
    private Score score;
    private Map<String, String> syncData;
    private List<String> tags;
    private List<TrailerData> trailers;
    private TvType type;
    private String uniqueUrl;
    private String url;
    private Integer year;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ LiveStreamLoadResponse copy$default(LiveStreamLoadResponse liveStreamLoadResponse, String str, String str2, String str3, String str4, String str5, Integer num, String str6, TvType tvType, Score score, List list, Integer num2, List list2, List list3, List list4, boolean z, Map map, Map map2, String str7, String str8, String str9, String str10, int i, Object obj) {
        String str11;
        String str12;
        String str13 = (i & 1) != 0 ? liveStreamLoadResponse.name : str;
        String str14 = (i & 2) != 0 ? liveStreamLoadResponse.url : str2;
        String str15 = (i & 4) != 0 ? liveStreamLoadResponse.apiName : str3;
        String str16 = (i & 8) != 0 ? liveStreamLoadResponse.dataUrl : str4;
        String str17 = (i & 16) != 0 ? liveStreamLoadResponse.posterUrl : str5;
        Integer num3 = (i & 32) != 0 ? liveStreamLoadResponse.year : num;
        String str18 = (i & 64) != 0 ? liveStreamLoadResponse.plot : str6;
        TvType tvType2 = (i & 128) != 0 ? liveStreamLoadResponse.type : tvType;
        Score score2 = (i & 256) != 0 ? liveStreamLoadResponse.score : score;
        List list5 = (i & 512) != 0 ? liveStreamLoadResponse.tags : list;
        Integer num4 = (i & 1024) != 0 ? liveStreamLoadResponse.duration : num2;
        List list6 = (i & 2048) != 0 ? liveStreamLoadResponse.trailers : list2;
        List list7 = (i & 4096) != 0 ? liveStreamLoadResponse.recommendations : list3;
        List list8 = (i & 8192) != 0 ? liveStreamLoadResponse.actors : list4;
        String str19 = str13;
        boolean z2 = (i & 16384) != 0 ? liveStreamLoadResponse.comingSoon : z;
        Map map3 = (i & 32768) != 0 ? liveStreamLoadResponse.syncData : map;
        Map map4 = (i & 65536) != 0 ? liveStreamLoadResponse.posterHeaders : map2;
        String str20 = (i & 131072) != 0 ? liveStreamLoadResponse.backgroundPosterUrl : str7;
        String str21 = (i & 262144) != 0 ? liveStreamLoadResponse.logoUrl : str8;
        String str22 = (i & 524288) != 0 ? liveStreamLoadResponse.contentRating : str9;
        if ((i & 1048576) != 0) {
            str12 = str22;
            str11 = liveStreamLoadResponse.uniqueUrl;
        } else {
            str11 = str10;
            str12 = str22;
        }
        return liveStreamLoadResponse.copy(str19, str14, str15, str16, str17, num3, str18, tvType2, score2, list5, num4, list6, list7, list8, z2, map3, map4, str20, str21, str12, str11);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getName() {
        return this.name;
    }

    public final List<String> component10() {
        return this.tags;
    }

    /* JADX INFO: renamed from: component11, reason: from getter */
    public final Integer getDuration() {
        return this.duration;
    }

    public final List<TrailerData> component12() {
        return this.trailers;
    }

    public final List<SearchResponse> component13() {
        return this.recommendations;
    }

    public final List<ActorData> component14() {
        return this.actors;
    }

    /* JADX INFO: renamed from: component15, reason: from getter */
    public final boolean getComingSoon() {
        return this.comingSoon;
    }

    public final Map<String, String> component16() {
        return this.syncData;
    }

    public final Map<String, String> component17() {
        return this.posterHeaders;
    }

    /* JADX INFO: renamed from: component18, reason: from getter */
    public final String getBackgroundPosterUrl() {
        return this.backgroundPosterUrl;
    }

    /* JADX INFO: renamed from: component19, reason: from getter */
    public final String getLogoUrl() {
        return this.logoUrl;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getUrl() {
        return this.url;
    }

    /* JADX INFO: renamed from: component20, reason: from getter */
    public final String getContentRating() {
        return this.contentRating;
    }

    /* JADX INFO: renamed from: component21, reason: from getter */
    public final String getUniqueUrl() {
        return this.uniqueUrl;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getApiName() {
        return this.apiName;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getDataUrl() {
        return this.dataUrl;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getPosterUrl() {
        return this.posterUrl;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final Integer getYear() {
        return this.year;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final String getPlot() {
        return this.plot;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final TvType getType() {
        return this.type;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final Score getScore() {
        return this.score;
    }

    public final LiveStreamLoadResponse copy(String name, String url, String apiName, String dataUrl, String posterUrl, Integer year, String plot, TvType type, Score score, List<String> tags, Integer duration, List<TrailerData> trailers, List<? extends SearchResponse> recommendations, List<ActorData> actors, boolean comingSoon, Map<String, String> syncData, Map<String, String> posterHeaders, String backgroundPosterUrl, String logoUrl, String contentRating, String uniqueUrl) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(url, "url");
        Intrinsics.checkNotNullParameter(apiName, "apiName");
        Intrinsics.checkNotNullParameter(dataUrl, "dataUrl");
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(trailers, "trailers");
        Intrinsics.checkNotNullParameter(syncData, "syncData");
        Intrinsics.checkNotNullParameter(uniqueUrl, "uniqueUrl");
        return new LiveStreamLoadResponse(name, url, apiName, dataUrl, posterUrl, year, plot, type, score, tags, duration, trailers, recommendations, actors, comingSoon, syncData, posterHeaders, backgroundPosterUrl, logoUrl, contentRating, uniqueUrl);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof LiveStreamLoadResponse)) {
            return false;
        }
        LiveStreamLoadResponse liveStreamLoadResponse = (LiveStreamLoadResponse) other;
        return Intrinsics.areEqual(this.name, liveStreamLoadResponse.name) && Intrinsics.areEqual(this.url, liveStreamLoadResponse.url) && Intrinsics.areEqual(this.apiName, liveStreamLoadResponse.apiName) && Intrinsics.areEqual(this.dataUrl, liveStreamLoadResponse.dataUrl) && Intrinsics.areEqual(this.posterUrl, liveStreamLoadResponse.posterUrl) && Intrinsics.areEqual(this.year, liveStreamLoadResponse.year) && Intrinsics.areEqual(this.plot, liveStreamLoadResponse.plot) && this.type == liveStreamLoadResponse.type && Intrinsics.areEqual(this.score, liveStreamLoadResponse.score) && Intrinsics.areEqual(this.tags, liveStreamLoadResponse.tags) && Intrinsics.areEqual(this.duration, liveStreamLoadResponse.duration) && Intrinsics.areEqual(this.trailers, liveStreamLoadResponse.trailers) && Intrinsics.areEqual(this.recommendations, liveStreamLoadResponse.recommendations) && Intrinsics.areEqual(this.actors, liveStreamLoadResponse.actors) && this.comingSoon == liveStreamLoadResponse.comingSoon && Intrinsics.areEqual(this.syncData, liveStreamLoadResponse.syncData) && Intrinsics.areEqual(this.posterHeaders, liveStreamLoadResponse.posterHeaders) && Intrinsics.areEqual(this.backgroundPosterUrl, liveStreamLoadResponse.backgroundPosterUrl) && Intrinsics.areEqual(this.logoUrl, liveStreamLoadResponse.logoUrl) && Intrinsics.areEqual(this.contentRating, liveStreamLoadResponse.contentRating) && Intrinsics.areEqual(this.uniqueUrl, liveStreamLoadResponse.uniqueUrl);
    }

    public int hashCode() {
        int iHashCode = ((((((this.name.hashCode() * 31) + this.url.hashCode()) * 31) + this.apiName.hashCode()) * 31) + this.dataUrl.hashCode()) * 31;
        String str = this.posterUrl;
        int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
        Integer num = this.year;
        int iHashCode3 = (iHashCode2 + (num == null ? 0 : num.hashCode())) * 31;
        String str2 = this.plot;
        int iHashCode4 = (((iHashCode3 + (str2 == null ? 0 : str2.hashCode())) * 31) + this.type.hashCode()) * 31;
        Score score = this.score;
        int data = (iHashCode4 + (score == null ? 0 : score.getData())) * 31;
        List<String> list = this.tags;
        int iHashCode5 = (data + (list == null ? 0 : list.hashCode())) * 31;
        Integer num2 = this.duration;
        int iHashCode6 = (((iHashCode5 + (num2 == null ? 0 : num2.hashCode())) * 31) + this.trailers.hashCode()) * 31;
        List<? extends SearchResponse> list2 = this.recommendations;
        int iHashCode7 = (iHashCode6 + (list2 == null ? 0 : list2.hashCode())) * 31;
        List<ActorData> list3 = this.actors;
        int iHashCode8 = (((((iHashCode7 + (list3 == null ? 0 : list3.hashCode())) * 31) + NextAiring$$ExternalSyntheticBackport0.m(this.comingSoon)) * 31) + this.syncData.hashCode()) * 31;
        Map<String, String> map = this.posterHeaders;
        int iHashCode9 = (iHashCode8 + (map == null ? 0 : map.hashCode())) * 31;
        String str3 = this.backgroundPosterUrl;
        int iHashCode10 = (iHashCode9 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.logoUrl;
        int iHashCode11 = (iHashCode10 + (str4 == null ? 0 : str4.hashCode())) * 31;
        String str5 = this.contentRating;
        return ((iHashCode11 + (str5 != null ? str5.hashCode() : 0)) * 31) + this.uniqueUrl.hashCode();
    }

    public String toString() {
        return "LiveStreamLoadResponse(name=" + this.name + ", url=" + this.url + ", apiName=" + this.apiName + ", dataUrl=" + this.dataUrl + ", posterUrl=" + this.posterUrl + ", year=" + this.year + ", plot=" + this.plot + ", type=" + this.type + ", score=" + this.score + ", tags=" + this.tags + ", duration=" + this.duration + ", trailers=" + this.trailers + ", recommendations=" + this.recommendations + ", actors=" + this.actors + ", comingSoon=" + this.comingSoon + ", syncData=" + this.syncData + ", posterHeaders=" + this.posterHeaders + ", backgroundPosterUrl=" + this.backgroundPosterUrl + ", logoUrl=" + this.logoUrl + ", contentRating=" + this.contentRating + ", uniqueUrl=" + this.uniqueUrl + ')';
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use newLiveStreamLoadResponse method")
    public LiveStreamLoadResponse(String name, String url, String apiName, String dataUrl, String str, Integer num, String str2, TvType type, Score score, List<String> list, Integer num2, List<TrailerData> trailers, List<? extends SearchResponse> list2, List<ActorData> list3, boolean z, Map<String, String> syncData, Map<String, String> map, String str3, String str4, String str5, String uniqueUrl) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(url, "url");
        Intrinsics.checkNotNullParameter(apiName, "apiName");
        Intrinsics.checkNotNullParameter(dataUrl, "dataUrl");
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(trailers, "trailers");
        Intrinsics.checkNotNullParameter(syncData, "syncData");
        Intrinsics.checkNotNullParameter(uniqueUrl, "uniqueUrl");
        this.name = name;
        this.url = url;
        this.apiName = apiName;
        this.dataUrl = dataUrl;
        this.posterUrl = str;
        this.year = num;
        this.plot = str2;
        this.type = type;
        this.score = score;
        this.tags = list;
        this.duration = num2;
        this.trailers = trailers;
        this.recommendations = list2;
        this.actors = list3;
        this.comingSoon = z;
        this.syncData = syncData;
        this.posterHeaders = map;
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

    public final String getDataUrl() {
        return this.dataUrl;
    }

    public final void setDataUrl(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.dataUrl = str;
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

    public /* synthetic */ LiveStreamLoadResponse(String str, String str2, String str3, String str4, String str5, Integer num, String str6, TvType tvType, Score score, List list, Integer num2, List list2, List list3, List list4, boolean z, Map map, Map map2, String str7, String str8, String str9, String str10, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, str3, str4, (i & 16) != 0 ? null : str5, (i & 32) != 0 ? null : num, (i & 64) != 0 ? null : str6, (i & 128) != 0 ? TvType.Live : tvType, (i & 256) != 0 ? null : score, (i & 512) != 0 ? null : list, (i & 1024) != 0 ? null : num2, (i & 2048) != 0 ? new ArrayList() : list2, (i & 4096) != 0 ? null : list3, (i & 8192) != 0 ? null : list4, (i & 16384) != 0 ? false : z, (32768 & i) != 0 ? new LinkedHashMap() : map, (65536 & i) != 0 ? null : map2, (131072 & i) != 0 ? null : str7, (262144 & i) != 0 ? null : str8, (524288 & i) != 0 ? null : str9, (i & 1048576) != 0 ? str2 : str10);
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
}
