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
@Metadata(d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010%\n\u0000\n\u0002\u0010$\n\u0002\b[\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0086\b\u0018\u00002\u00020\u0001B¥\u0002\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\t\u001a\u00020\n\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000f\u0012\u0010\b\u0002\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0011\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\r\u0012\u000e\b\u0002\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150\u0014\u0012\u0010\b\u0002\u0010\u0016\u001a\n\u0012\u0004\u0012\u00020\u0017\u0018\u00010\u0011\u0012\u0010\b\u0002\u0010\u0018\u001a\n\u0012\u0004\u0012\u00020\u0019\u0018\u00010\u0011\u0012\b\b\u0002\u0010\u001a\u001a\u00020\u001b\u0012\u0014\b\u0002\u0010\u001c\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u001d\u0012\u0016\b\u0002\u0010\u001e\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u001f\u0012\n\b\u0002\u0010 \u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010!\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010#\u001a\u00020\u0003¢\u0006\u0004\b$\u0010%J\t\u0010a\u001a\u00020\u0003HÆ\u0003J\t\u0010b\u001a\u00020\u0003HÆ\u0003J\t\u0010c\u001a\u00020\u0003HÆ\u0003J\u000b\u0010d\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010e\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010g\u001a\u00020\nHÆ\u0003J\u000b\u0010h\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010i\u001a\u0004\u0018\u00010\rHÆ\u0003¢\u0006\u0002\u0010;J\u000b\u0010j\u001a\u0004\u0018\u00010\u000fHÆ\u0003J\u0011\u0010k\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0011HÆ\u0003J\u0010\u0010l\u001a\u0004\u0018\u00010\rHÆ\u0003¢\u0006\u0002\u0010;J\u000f\u0010m\u001a\b\u0012\u0004\u0012\u00020\u00150\u0014HÆ\u0003J\u0011\u0010n\u001a\n\u0012\u0004\u0012\u00020\u0017\u0018\u00010\u0011HÆ\u0003J\u0011\u0010o\u001a\n\u0012\u0004\u0012\u00020\u0019\u0018\u00010\u0011HÆ\u0003J\t\u0010p\u001a\u00020\u001bHÆ\u0003J\u0015\u0010q\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u001dHÆ\u0003J\u0017\u0010r\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u001fHÆ\u0003J\u000b\u0010s\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010t\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010u\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010v\u001a\u00020\u0003HÆ\u0003J¶\u0002\u0010w\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\t\u001a\u00020\n2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0010\b\u0002\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u00112\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\r2\u000e\b\u0002\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150\u00142\u0010\b\u0002\u0010\u0016\u001a\n\u0012\u0004\u0012\u00020\u0017\u0018\u00010\u00112\u0010\b\u0002\u0010\u0018\u001a\n\u0012\u0004\u0012\u00020\u0019\u0018\u00010\u00112\b\b\u0002\u0010\u001a\u001a\u00020\u001b2\u0014\b\u0002\u0010\u001c\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u001d2\u0016\b\u0002\u0010\u001e\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u001f2\n\b\u0002\u0010 \u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010!\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010#\u001a\u00020\u0003HÆ\u0001¢\u0006\u0002\u0010xJ\u0014\u0010y\u001a\u00020\u001b2\b\u0010z\u001a\u0004\u0018\u00010{HÖ\u0083\u0004J\n\u0010|\u001a\u00020\rHÖ\u0081\u0004J\n\u0010}\u001a\u00020\u0003HÖ\u0081\u0004R\u001a\u0010\u0002\u001a\u00020\u0003X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b&\u0010'\"\u0004\b(\u0010)R\u001a\u0010\u0004\u001a\u00020\u0003X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b*\u0010'\"\u0004\b+\u0010)R\u001a\u0010\u0005\u001a\u00020\u0003X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b,\u0010'\"\u0004\b-\u0010)R\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b.\u0010'\"\u0004\b/\u0010)R\u001c\u0010\u0007\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b0\u0010'\"\u0004\b1\u0010)R\u001c\u0010\b\u001a\u0004\u0018\u00010\u0003X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b2\u0010'\"\u0004\b3\u0010)R\u001a\u0010\t\u001a\u00020\nX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b4\u00105\"\u0004\b6\u00107R\u001c\u0010\u000b\u001a\u0004\u0018\u00010\u0003X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b8\u0010'\"\u0004\b9\u0010)R\u001e\u0010\f\u001a\u0004\u0018\u00010\rX\u0096\u000e¢\u0006\u0010\n\u0002\u0010>\u001a\u0004\b:\u0010;\"\u0004\b<\u0010=R\u001c\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b?\u0010@\"\u0004\bA\u0010BR\"\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0011X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bC\u0010D\"\u0004\bE\u0010FR\u001e\u0010\u0012\u001a\u0004\u0018\u00010\rX\u0096\u000e¢\u0006\u0010\n\u0002\u0010>\u001a\u0004\bG\u0010;\"\u0004\bH\u0010=R \u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150\u0014X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bI\u0010D\"\u0004\bJ\u0010FR\"\u0010\u0016\u001a\n\u0012\u0004\u0012\u00020\u0017\u0018\u00010\u0011X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bK\u0010D\"\u0004\bL\u0010FR\"\u0010\u0018\u001a\n\u0012\u0004\u0012\u00020\u0019\u0018\u00010\u0011X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bM\u0010D\"\u0004\bN\u0010FR\u001a\u0010\u001a\u001a\u00020\u001bX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bO\u0010P\"\u0004\bQ\u0010RR&\u0010\u001c\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u001dX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bS\u0010T\"\u0004\bU\u0010VR(\u0010\u001e\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u001fX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bW\u0010T\"\u0004\bX\u0010VR\u001c\u0010 \u001a\u0004\u0018\u00010\u0003X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bY\u0010'\"\u0004\bZ\u0010)R\u001c\u0010!\u001a\u0004\u0018\u00010\u0003X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b[\u0010'\"\u0004\b\\\u0010)R\u001c\u0010\"\u001a\u0004\u0018\u00010\u0003X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b]\u0010'\"\u0004\b^\u0010)R\u001a\u0010#\u001a\u00020\u0003X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b_\u0010'\"\u0004\b`\u0010)¨\u0006~"}, d2 = {"Lcom/lagradost/cloudstream3/TorrentLoadResponse;", "Lcom/lagradost/cloudstream3/LoadResponse;", "name", "", ImagesContract.URL, "apiName", "magnet", "torrent", "plot", "type", "Lcom/lagradost/cloudstream3/TvType;", "posterUrl", "year", "", "score", "Lcom/lagradost/cloudstream3/Score;", "tags", "", TypedValues.TransitionType.S_DURATION, "trailers", "", "Lcom/lagradost/cloudstream3/TrailerData;", "recommendations", "Lcom/lagradost/cloudstream3/SearchResponse;", "actors", "Lcom/lagradost/cloudstream3/ActorData;", "comingSoon", "", "syncData", "", "posterHeaders", "", "backgroundPosterUrl", "logoUrl", "contentRating", "uniqueUrl", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/lagradost/cloudstream3/TvType;Ljava/lang/String;Ljava/lang/Integer;Lcom/lagradost/cloudstream3/Score;Ljava/util/List;Ljava/lang/Integer;Ljava/util/List;Ljava/util/List;Ljava/util/List;ZLjava/util/Map;Ljava/util/Map;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getName", "()Ljava/lang/String;", "setName", "(Ljava/lang/String;)V", "getUrl", "setUrl", "getApiName", "setApiName", "getMagnet", "setMagnet", "getTorrent", "setTorrent", "getPlot", "setPlot", "getType", "()Lcom/lagradost/cloudstream3/TvType;", "setType", "(Lcom/lagradost/cloudstream3/TvType;)V", "getPosterUrl", "setPosterUrl", "getYear", "()Ljava/lang/Integer;", "setYear", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "getScore", "()Lcom/lagradost/cloudstream3/Score;", "setScore", "(Lcom/lagradost/cloudstream3/Score;)V", "getTags", "()Ljava/util/List;", "setTags", "(Ljava/util/List;)V", "getDuration", "setDuration", "getTrailers", "setTrailers", "getRecommendations", "setRecommendations", "getActors", "setActors", "getComingSoon", "()Z", "setComingSoon", "(Z)V", "getSyncData", "()Ljava/util/Map;", "setSyncData", "(Ljava/util/Map;)V", "getPosterHeaders", "setPosterHeaders", "getBackgroundPosterUrl", "setBackgroundPosterUrl", "getLogoUrl", "setLogoUrl", "getContentRating", "setContentRating", "getUniqueUrl", "setUniqueUrl", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "component20", "component21", "component22", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/lagradost/cloudstream3/TvType;Ljava/lang/String;Ljava/lang/Integer;Lcom/lagradost/cloudstream3/Score;Ljava/util/List;Ljava/lang/Integer;Ljava/util/List;Ljava/util/List;Ljava/util/List;ZLjava/util/Map;Ljava/util/Map;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lcom/lagradost/cloudstream3/TorrentLoadResponse;", "equals", "other", "", "hashCode", "toString", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class TorrentLoadResponse implements LoadResponse {
    private List<ActorData> actors;
    private String apiName;
    private String backgroundPosterUrl;
    private boolean comingSoon;
    private String contentRating;
    private Integer duration;
    private String logoUrl;
    private String magnet;
    private String name;
    private String plot;
    private Map<String, String> posterHeaders;
    private String posterUrl;
    private List<? extends SearchResponse> recommendations;
    private Score score;
    private Map<String, String> syncData;
    private List<String> tags;
    private String torrent;
    private List<TrailerData> trailers;
    private TvType type;
    private String uniqueUrl;
    private String url;
    private Integer year;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ TorrentLoadResponse copy$default(TorrentLoadResponse torrentLoadResponse, String str, String str2, String str3, String str4, String str5, String str6, TvType tvType, String str7, Integer num, Score score, List list, Integer num2, List list2, List list3, List list4, boolean z, Map map, Map map2, String str8, String str9, String str10, String str11, int i, Object obj) {
        String str12;
        String str13;
        String str14 = (i & 1) != 0 ? torrentLoadResponse.name : str;
        String str15 = (i & 2) != 0 ? torrentLoadResponse.url : str2;
        String str16 = (i & 4) != 0 ? torrentLoadResponse.apiName : str3;
        String str17 = (i & 8) != 0 ? torrentLoadResponse.magnet : str4;
        String str18 = (i & 16) != 0 ? torrentLoadResponse.torrent : str5;
        String str19 = (i & 32) != 0 ? torrentLoadResponse.plot : str6;
        TvType tvType2 = (i & 64) != 0 ? torrentLoadResponse.type : tvType;
        String str20 = (i & 128) != 0 ? torrentLoadResponse.posterUrl : str7;
        Integer num3 = (i & 256) != 0 ? torrentLoadResponse.year : num;
        Score score2 = (i & 512) != 0 ? torrentLoadResponse.score : score;
        List list5 = (i & 1024) != 0 ? torrentLoadResponse.tags : list;
        Integer num4 = (i & 2048) != 0 ? torrentLoadResponse.duration : num2;
        List list6 = (i & 4096) != 0 ? torrentLoadResponse.trailers : list2;
        List list7 = (i & 8192) != 0 ? torrentLoadResponse.recommendations : list3;
        String str21 = str14;
        List list8 = (i & 16384) != 0 ? torrentLoadResponse.actors : list4;
        boolean z2 = (i & 32768) != 0 ? torrentLoadResponse.comingSoon : z;
        Map map3 = (i & 65536) != 0 ? torrentLoadResponse.syncData : map;
        Map map4 = (i & 131072) != 0 ? torrentLoadResponse.posterHeaders : map2;
        String str22 = (i & 262144) != 0 ? torrentLoadResponse.backgroundPosterUrl : str8;
        String str23 = (i & 524288) != 0 ? torrentLoadResponse.logoUrl : str9;
        String str24 = (i & 1048576) != 0 ? torrentLoadResponse.contentRating : str10;
        if ((i & 2097152) != 0) {
            str13 = str24;
            str12 = torrentLoadResponse.uniqueUrl;
        } else {
            str12 = str11;
            str13 = str24;
        }
        return torrentLoadResponse.copy(str21, str15, str16, str17, str18, str19, tvType2, str20, num3, score2, list5, num4, list6, list7, list8, z2, map3, map4, str22, str23, str13, str12);
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
    public final String getBackgroundPosterUrl() {
        return this.backgroundPosterUrl;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getUrl() {
        return this.url;
    }

    /* JADX INFO: renamed from: component20, reason: from getter */
    public final String getLogoUrl() {
        return this.logoUrl;
    }

    /* JADX INFO: renamed from: component21, reason: from getter */
    public final String getContentRating() {
        return this.contentRating;
    }

    /* JADX INFO: renamed from: component22, reason: from getter */
    public final String getUniqueUrl() {
        return this.uniqueUrl;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getApiName() {
        return this.apiName;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getMagnet() {
        return this.magnet;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getTorrent() {
        return this.torrent;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final String getPlot() {
        return this.plot;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final TvType getType() {
        return this.type;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final String getPosterUrl() {
        return this.posterUrl;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final Integer getYear() {
        return this.year;
    }

    public final TorrentLoadResponse copy(String name, String url, String apiName, String magnet, String torrent, String plot, TvType type, String posterUrl, Integer year, Score score, List<String> tags, Integer duration, List<TrailerData> trailers, List<? extends SearchResponse> recommendations, List<ActorData> actors, boolean comingSoon, Map<String, String> syncData, Map<String, String> posterHeaders, String backgroundPosterUrl, String logoUrl, String contentRating, String uniqueUrl) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(url, "url");
        Intrinsics.checkNotNullParameter(apiName, "apiName");
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(trailers, "trailers");
        Intrinsics.checkNotNullParameter(syncData, "syncData");
        Intrinsics.checkNotNullParameter(uniqueUrl, "uniqueUrl");
        return new TorrentLoadResponse(name, url, apiName, magnet, torrent, plot, type, posterUrl, year, score, tags, duration, trailers, recommendations, actors, comingSoon, syncData, posterHeaders, backgroundPosterUrl, logoUrl, contentRating, uniqueUrl);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TorrentLoadResponse)) {
            return false;
        }
        TorrentLoadResponse torrentLoadResponse = (TorrentLoadResponse) other;
        return Intrinsics.areEqual(this.name, torrentLoadResponse.name) && Intrinsics.areEqual(this.url, torrentLoadResponse.url) && Intrinsics.areEqual(this.apiName, torrentLoadResponse.apiName) && Intrinsics.areEqual(this.magnet, torrentLoadResponse.magnet) && Intrinsics.areEqual(this.torrent, torrentLoadResponse.torrent) && Intrinsics.areEqual(this.plot, torrentLoadResponse.plot) && this.type == torrentLoadResponse.type && Intrinsics.areEqual(this.posterUrl, torrentLoadResponse.posterUrl) && Intrinsics.areEqual(this.year, torrentLoadResponse.year) && Intrinsics.areEqual(this.score, torrentLoadResponse.score) && Intrinsics.areEqual(this.tags, torrentLoadResponse.tags) && Intrinsics.areEqual(this.duration, torrentLoadResponse.duration) && Intrinsics.areEqual(this.trailers, torrentLoadResponse.trailers) && Intrinsics.areEqual(this.recommendations, torrentLoadResponse.recommendations) && Intrinsics.areEqual(this.actors, torrentLoadResponse.actors) && this.comingSoon == torrentLoadResponse.comingSoon && Intrinsics.areEqual(this.syncData, torrentLoadResponse.syncData) && Intrinsics.areEqual(this.posterHeaders, torrentLoadResponse.posterHeaders) && Intrinsics.areEqual(this.backgroundPosterUrl, torrentLoadResponse.backgroundPosterUrl) && Intrinsics.areEqual(this.logoUrl, torrentLoadResponse.logoUrl) && Intrinsics.areEqual(this.contentRating, torrentLoadResponse.contentRating) && Intrinsics.areEqual(this.uniqueUrl, torrentLoadResponse.uniqueUrl);
    }

    public int hashCode() {
        int iHashCode = ((((this.name.hashCode() * 31) + this.url.hashCode()) * 31) + this.apiName.hashCode()) * 31;
        String str = this.magnet;
        int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.torrent;
        int iHashCode3 = (iHashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.plot;
        int iHashCode4 = (((iHashCode3 + (str3 == null ? 0 : str3.hashCode())) * 31) + this.type.hashCode()) * 31;
        String str4 = this.posterUrl;
        int iHashCode5 = (iHashCode4 + (str4 == null ? 0 : str4.hashCode())) * 31;
        Integer num = this.year;
        int iHashCode6 = (iHashCode5 + (num == null ? 0 : num.hashCode())) * 31;
        Score score = this.score;
        int data = (iHashCode6 + (score == null ? 0 : score.getData())) * 31;
        List<String> list = this.tags;
        int iHashCode7 = (data + (list == null ? 0 : list.hashCode())) * 31;
        Integer num2 = this.duration;
        int iHashCode8 = (((iHashCode7 + (num2 == null ? 0 : num2.hashCode())) * 31) + this.trailers.hashCode()) * 31;
        List<? extends SearchResponse> list2 = this.recommendations;
        int iHashCode9 = (iHashCode8 + (list2 == null ? 0 : list2.hashCode())) * 31;
        List<ActorData> list3 = this.actors;
        int iHashCode10 = (((((iHashCode9 + (list3 == null ? 0 : list3.hashCode())) * 31) + NextAiring$$ExternalSyntheticBackport0.m(this.comingSoon)) * 31) + this.syncData.hashCode()) * 31;
        Map<String, String> map = this.posterHeaders;
        int iHashCode11 = (iHashCode10 + (map == null ? 0 : map.hashCode())) * 31;
        String str5 = this.backgroundPosterUrl;
        int iHashCode12 = (iHashCode11 + (str5 == null ? 0 : str5.hashCode())) * 31;
        String str6 = this.logoUrl;
        int iHashCode13 = (iHashCode12 + (str6 == null ? 0 : str6.hashCode())) * 31;
        String str7 = this.contentRating;
        return ((iHashCode13 + (str7 != null ? str7.hashCode() : 0)) * 31) + this.uniqueUrl.hashCode();
    }

    public String toString() {
        return "TorrentLoadResponse(name=" + this.name + ", url=" + this.url + ", apiName=" + this.apiName + ", magnet=" + this.magnet + ", torrent=" + this.torrent + ", plot=" + this.plot + ", type=" + this.type + ", posterUrl=" + this.posterUrl + ", year=" + this.year + ", score=" + this.score + ", tags=" + this.tags + ", duration=" + this.duration + ", trailers=" + this.trailers + ", recommendations=" + this.recommendations + ", actors=" + this.actors + ", comingSoon=" + this.comingSoon + ", syncData=" + this.syncData + ", posterHeaders=" + this.posterHeaders + ", backgroundPosterUrl=" + this.backgroundPosterUrl + ", logoUrl=" + this.logoUrl + ", contentRating=" + this.contentRating + ", uniqueUrl=" + this.uniqueUrl + ')';
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use newTorrentLoadResponse method")
    public TorrentLoadResponse(String name, String url, String apiName, String str, String str2, String str3, TvType type, String str4, Integer num, Score score, List<String> list, Integer num2, List<TrailerData> trailers, List<? extends SearchResponse> list2, List<ActorData> list3, boolean z, Map<String, String> syncData, Map<String, String> map, String str5, String str6, String str7, String uniqueUrl) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(url, "url");
        Intrinsics.checkNotNullParameter(apiName, "apiName");
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(trailers, "trailers");
        Intrinsics.checkNotNullParameter(syncData, "syncData");
        Intrinsics.checkNotNullParameter(uniqueUrl, "uniqueUrl");
        this.name = name;
        this.url = url;
        this.apiName = apiName;
        this.magnet = str;
        this.torrent = str2;
        this.plot = str3;
        this.type = type;
        this.posterUrl = str4;
        this.year = num;
        this.score = score;
        this.tags = list;
        this.duration = num2;
        this.trailers = trailers;
        this.recommendations = list2;
        this.actors = list3;
        this.comingSoon = z;
        this.syncData = syncData;
        this.posterHeaders = map;
        this.backgroundPosterUrl = str5;
        this.logoUrl = str6;
        this.contentRating = str7;
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

    public final String getMagnet() {
        return this.magnet;
    }

    public final void setMagnet(String str) {
        this.magnet = str;
    }

    public final String getTorrent() {
        return this.torrent;
    }

    public final void setTorrent(String str) {
        this.torrent = str;
    }

    @Override // com.lagradost.cloudstream3.LoadResponse
    public String getPlot() {
        return this.plot;
    }

    @Override // com.lagradost.cloudstream3.LoadResponse
    public void setPlot(String str) {
        this.plot = str;
    }

    public /* synthetic */ TorrentLoadResponse(String str, String str2, String str3, String str4, String str5, String str6, TvType tvType, String str7, Integer num, Score score, List list, Integer num2, List list2, List list3, List list4, boolean z, Map map, Map map2, String str8, String str9, String str10, String str11, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, str3, str4, str5, str6, (i & 64) != 0 ? TvType.Torrent : tvType, (i & 128) != 0 ? null : str7, (i & 256) != 0 ? null : num, (i & 512) != 0 ? null : score, (i & 1024) != 0 ? null : list, (i & 2048) != 0 ? null : num2, (i & 4096) != 0 ? new ArrayList() : list2, (i & 8192) != 0 ? null : list3, (i & 16384) != 0 ? null : list4, (32768 & i) != 0 ? false : z, (65536 & i) != 0 ? new LinkedHashMap() : map, (131072 & i) != 0 ? null : map2, (262144 & i) != 0 ? null : str8, (524288 & i) != 0 ? null : str9, (1048576 & i) != 0 ? null : str10, (i & 2097152) != 0 ? str2 : str11);
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
