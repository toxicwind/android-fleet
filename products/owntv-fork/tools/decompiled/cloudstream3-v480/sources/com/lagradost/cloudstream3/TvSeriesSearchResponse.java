package com.lagradost.cloudstream3;

import com.google.android.gms.common.internal.ImagesContract;
import java.util.Map;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MainAPI.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0000\n\u0002\u0018\u0002\n\u0002\b1\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0086\b\u0018\u00002\u00020\u0001B\u008d\u0001\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\n\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\n\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e\u0012\u0016\b\u0002\u0010\u000f\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0010\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0012¢\u0006\u0004\b\u0013\u0010\u0014B}\b\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\n\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\n\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e\u0012\u0016\b\u0002\u0010\u000f\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0010¢\u0006\u0004\b\u0013\u0010\u0015J\t\u00106\u001a\u00020\u0003HÆ\u0003J\t\u00107\u001a\u00020\u0003HÆ\u0003J\t\u00108\u001a\u00020\u0003HÆ\u0003J\u000b\u00109\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\u000b\u0010:\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010;\u001a\u0004\u0018\u00010\nHÆ\u0003¢\u0006\u0002\u0010\"J\u0010\u0010<\u001a\u0004\u0018\u00010\nHÆ\u0003¢\u0006\u0002\u0010\"J\u0010\u0010=\u001a\u0004\u0018\u00010\nHÆ\u0003¢\u0006\u0002\u0010\"J\u000b\u0010>\u001a\u0004\u0018\u00010\u000eHÆ\u0003J\u0017\u0010?\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0010HÆ\u0003J\u000b\u0010@\u001a\u0004\u0018\u00010\u0012HÆ\u0003J\u0098\u0001\u0010A\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e2\u0016\b\u0002\u0010\u000f\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u00102\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0012HÆ\u0001¢\u0006\u0002\u0010BJ\u0014\u0010C\u001a\u00020D2\b\u0010E\u001a\u0004\u0018\u00010FHÖ\u0083\u0004J\n\u0010G\u001a\u00020\nHÖ\u0081\u0004J\n\u0010H\u001a\u00020\u0003HÖ\u0081\u0004R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0014\u0010\u0004\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0017R\u0014\u0010\u0005\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0017R\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\u001c\u0010\b\u001a\u0004\u0018\u00010\u0003X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u0017\"\u0004\b\u001f\u0010 R\u001e\u0010\t\u001a\u0004\u0018\u00010\nX\u0086\u000e¢\u0006\u0010\n\u0002\u0010%\u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$R\u001e\u0010\u000b\u001a\u0004\u0018\u00010\nX\u0086\u000e¢\u0006\u0010\n\u0002\u0010%\u001a\u0004\b&\u0010\"\"\u0004\b'\u0010$R\u001e\u0010\f\u001a\u0004\u0018\u00010\nX\u0096\u000e¢\u0006\u0010\n\u0002\u0010%\u001a\u0004\b(\u0010\"\"\u0004\b)\u0010$R\u001c\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b*\u0010+\"\u0004\b,\u0010-R(\u0010\u000f\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0010X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b.\u0010/\"\u0004\b0\u00101R\u001c\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b2\u00103\"\u0004\b4\u00105¨\u0006I"}, d2 = {"Lcom/lagradost/cloudstream3/TvSeriesSearchResponse;", "Lcom/lagradost/cloudstream3/SearchResponse;", "name", "", ImagesContract.URL, "apiName", "type", "Lcom/lagradost/cloudstream3/TvType;", "posterUrl", "year", "", "episodes", "id", "quality", "Lcom/lagradost/cloudstream3/SearchQuality;", "posterHeaders", "", "score", "Lcom/lagradost/cloudstream3/Score;", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/lagradost/cloudstream3/TvType;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Lcom/lagradost/cloudstream3/SearchQuality;Ljava/util/Map;Lcom/lagradost/cloudstream3/Score;)V", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/lagradost/cloudstream3/TvType;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Lcom/lagradost/cloudstream3/SearchQuality;Ljava/util/Map;)V", "getName", "()Ljava/lang/String;", "getUrl", "getApiName", "getType", "()Lcom/lagradost/cloudstream3/TvType;", "setType", "(Lcom/lagradost/cloudstream3/TvType;)V", "getPosterUrl", "setPosterUrl", "(Ljava/lang/String;)V", "getYear", "()Ljava/lang/Integer;", "setYear", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "getEpisodes", "setEpisodes", "getId", "setId", "getQuality", "()Lcom/lagradost/cloudstream3/SearchQuality;", "setQuality", "(Lcom/lagradost/cloudstream3/SearchQuality;)V", "getPosterHeaders", "()Ljava/util/Map;", "setPosterHeaders", "(Ljava/util/Map;)V", "getScore", "()Lcom/lagradost/cloudstream3/Score;", "setScore", "(Lcom/lagradost/cloudstream3/Score;)V", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/lagradost/cloudstream3/TvType;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Lcom/lagradost/cloudstream3/SearchQuality;Ljava/util/Map;Lcom/lagradost/cloudstream3/Score;)Lcom/lagradost/cloudstream3/TvSeriesSearchResponse;", "equals", "", "other", "", "hashCode", "toString", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class TvSeriesSearchResponse implements SearchResponse {
    private final String apiName;
    private Integer episodes;
    private Integer id;
    private final String name;
    private Map<String, String> posterHeaders;
    private String posterUrl;
    private SearchQuality quality;
    private Score score;
    private TvType type;
    private final String url;
    private Integer year;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ TvSeriesSearchResponse copy$default(TvSeriesSearchResponse tvSeriesSearchResponse, String str, String str2, String str3, TvType tvType, String str4, Integer num, Integer num2, Integer num3, SearchQuality searchQuality, Map map, Score score, int i, Object obj) {
        if ((i & 1) != 0) {
            str = tvSeriesSearchResponse.name;
        }
        if ((i & 2) != 0) {
            str2 = tvSeriesSearchResponse.url;
        }
        if ((i & 4) != 0) {
            str3 = tvSeriesSearchResponse.apiName;
        }
        if ((i & 8) != 0) {
            tvType = tvSeriesSearchResponse.type;
        }
        if ((i & 16) != 0) {
            str4 = tvSeriesSearchResponse.posterUrl;
        }
        if ((i & 32) != 0) {
            num = tvSeriesSearchResponse.year;
        }
        if ((i & 64) != 0) {
            num2 = tvSeriesSearchResponse.episodes;
        }
        if ((i & 128) != 0) {
            num3 = tvSeriesSearchResponse.id;
        }
        if ((i & 256) != 0) {
            searchQuality = tvSeriesSearchResponse.quality;
        }
        if ((i & 512) != 0) {
            map = tvSeriesSearchResponse.posterHeaders;
        }
        if ((i & 1024) != 0) {
            score = tvSeriesSearchResponse.score;
        }
        Map map2 = map;
        Score score2 = score;
        Integer num4 = num3;
        SearchQuality searchQuality2 = searchQuality;
        Integer num5 = num;
        Integer num6 = num2;
        String str5 = str4;
        String str6 = str3;
        return tvSeriesSearchResponse.copy(str, str2, str6, tvType, str5, num5, num6, num4, searchQuality2, map2, score2);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getName() {
        return this.name;
    }

    public final Map<String, String> component10() {
        return this.posterHeaders;
    }

    /* JADX INFO: renamed from: component11, reason: from getter */
    public final Score getScore() {
        return this.score;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getUrl() {
        return this.url;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getApiName() {
        return this.apiName;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final TvType getType() {
        return this.type;
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
    public final Integer getEpisodes() {
        return this.episodes;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final Integer getId() {
        return this.id;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final SearchQuality getQuality() {
        return this.quality;
    }

    public final TvSeriesSearchResponse copy(String name, String url, String apiName, TvType type, String posterUrl, Integer year, Integer episodes, Integer id, SearchQuality quality, Map<String, String> posterHeaders, Score score) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(url, "url");
        Intrinsics.checkNotNullParameter(apiName, "apiName");
        return new TvSeriesSearchResponse(name, url, apiName, type, posterUrl, year, episodes, id, quality, posterHeaders, score);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TvSeriesSearchResponse)) {
            return false;
        }
        TvSeriesSearchResponse tvSeriesSearchResponse = (TvSeriesSearchResponse) other;
        return Intrinsics.areEqual(this.name, tvSeriesSearchResponse.name) && Intrinsics.areEqual(this.url, tvSeriesSearchResponse.url) && Intrinsics.areEqual(this.apiName, tvSeriesSearchResponse.apiName) && this.type == tvSeriesSearchResponse.type && Intrinsics.areEqual(this.posterUrl, tvSeriesSearchResponse.posterUrl) && Intrinsics.areEqual(this.year, tvSeriesSearchResponse.year) && Intrinsics.areEqual(this.episodes, tvSeriesSearchResponse.episodes) && Intrinsics.areEqual(this.id, tvSeriesSearchResponse.id) && this.quality == tvSeriesSearchResponse.quality && Intrinsics.areEqual(this.posterHeaders, tvSeriesSearchResponse.posterHeaders) && Intrinsics.areEqual(this.score, tvSeriesSearchResponse.score);
    }

    public int hashCode() {
        int iHashCode = ((((this.name.hashCode() * 31) + this.url.hashCode()) * 31) + this.apiName.hashCode()) * 31;
        TvType tvType = this.type;
        int iHashCode2 = (iHashCode + (tvType == null ? 0 : tvType.hashCode())) * 31;
        String str = this.posterUrl;
        int iHashCode3 = (iHashCode2 + (str == null ? 0 : str.hashCode())) * 31;
        Integer num = this.year;
        int iHashCode4 = (iHashCode3 + (num == null ? 0 : num.hashCode())) * 31;
        Integer num2 = this.episodes;
        int iHashCode5 = (iHashCode4 + (num2 == null ? 0 : num2.hashCode())) * 31;
        Integer num3 = this.id;
        int iHashCode6 = (iHashCode5 + (num3 == null ? 0 : num3.hashCode())) * 31;
        SearchQuality searchQuality = this.quality;
        int iHashCode7 = (iHashCode6 + (searchQuality == null ? 0 : searchQuality.hashCode())) * 31;
        Map<String, String> map = this.posterHeaders;
        int iHashCode8 = (iHashCode7 + (map == null ? 0 : map.hashCode())) * 31;
        Score score = this.score;
        return iHashCode8 + (score != null ? score.getData() : 0);
    }

    public String toString() {
        return "TvSeriesSearchResponse(name=" + this.name + ", url=" + this.url + ", apiName=" + this.apiName + ", type=" + this.type + ", posterUrl=" + this.posterUrl + ", year=" + this.year + ", episodes=" + this.episodes + ", id=" + this.id + ", quality=" + this.quality + ", posterHeaders=" + this.posterHeaders + ", score=" + this.score + ')';
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use newTvSeriesSearchResponse")
    public TvSeriesSearchResponse(String name, String url, String apiName, TvType tvType, String str, Integer num, Integer num2, Integer num3, SearchQuality searchQuality, Map<String, String> map, Score score) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(url, "url");
        Intrinsics.checkNotNullParameter(apiName, "apiName");
        this.name = name;
        this.url = url;
        this.apiName = apiName;
        this.type = tvType;
        this.posterUrl = str;
        this.year = num;
        this.episodes = num2;
        this.id = num3;
        this.quality = searchQuality;
        this.posterHeaders = map;
        this.score = score;
    }

    public /* synthetic */ TvSeriesSearchResponse(String str, String str2, String str3, TvType tvType, String str4, Integer num, Integer num2, Integer num3, SearchQuality searchQuality, Map map, Score score, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, str3, (i & 8) != 0 ? null : tvType, (i & 16) != 0 ? null : str4, (i & 32) != 0 ? null : num, (i & 64) != 0 ? null : num2, (i & 128) != 0 ? null : num3, (i & 256) != 0 ? null : searchQuality, (i & 512) != 0 ? null : map, (i & 1024) != 0 ? null : score);
    }

    @Override // com.lagradost.cloudstream3.SearchResponse
    public String getName() {
        return this.name;
    }

    @Override // com.lagradost.cloudstream3.SearchResponse
    public String getUrl() {
        return this.url;
    }

    @Override // com.lagradost.cloudstream3.SearchResponse
    public String getApiName() {
        return this.apiName;
    }

    @Override // com.lagradost.cloudstream3.SearchResponse
    public TvType getType() {
        return this.type;
    }

    @Override // com.lagradost.cloudstream3.SearchResponse
    public void setType(TvType tvType) {
        this.type = tvType;
    }

    @Override // com.lagradost.cloudstream3.SearchResponse
    public String getPosterUrl() {
        return this.posterUrl;
    }

    @Override // com.lagradost.cloudstream3.SearchResponse
    public void setPosterUrl(String str) {
        this.posterUrl = str;
    }

    public final Integer getYear() {
        return this.year;
    }

    public final void setYear(Integer num) {
        this.year = num;
    }

    public final Integer getEpisodes() {
        return this.episodes;
    }

    public final void setEpisodes(Integer num) {
        this.episodes = num;
    }

    @Override // com.lagradost.cloudstream3.SearchResponse
    public Integer getId() {
        return this.id;
    }

    @Override // com.lagradost.cloudstream3.SearchResponse
    public void setId(Integer num) {
        this.id = num;
    }

    @Override // com.lagradost.cloudstream3.SearchResponse
    public SearchQuality getQuality() {
        return this.quality;
    }

    @Override // com.lagradost.cloudstream3.SearchResponse
    public void setQuality(SearchQuality searchQuality) {
        this.quality = searchQuality;
    }

    @Override // com.lagradost.cloudstream3.SearchResponse
    public Map<String, String> getPosterHeaders() {
        return this.posterHeaders;
    }

    @Override // com.lagradost.cloudstream3.SearchResponse
    public void setPosterHeaders(Map<String, String> map) {
        this.posterHeaders = map;
    }

    @Override // com.lagradost.cloudstream3.SearchResponse
    public Score getScore() {
        return this.score;
    }

    @Override // com.lagradost.cloudstream3.SearchResponse
    public void setScore(Score score) {
        this.score = score;
    }

    public /* synthetic */ TvSeriesSearchResponse(String str, String str2, String str3, TvType tvType, String str4, Integer num, Integer num2, Integer num3, SearchQuality searchQuality, Map map, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, str3, tvType, str4, (i & 32) != 0 ? null : num, (i & 64) != 0 ? null : num2, (i & 128) != 0 ? null : num3, (i & 256) != 0 ? null : searchQuality, (i & 512) != 0 ? null : map);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    @Deprecated(level = DeprecationLevel.ERROR, message = "Use newTvSeriesSearchResponse")
    public TvSeriesSearchResponse(String name, String url, String apiName, TvType tvType, String str, Integer num, Integer num2, Integer num3, SearchQuality searchQuality, Map<String, String> map) {
        this(name, url, apiName, tvType, str, num, num2, num3, searchQuality, map, null);
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(url, "url");
        Intrinsics.checkNotNullParameter(apiName, "apiName");
    }
}
