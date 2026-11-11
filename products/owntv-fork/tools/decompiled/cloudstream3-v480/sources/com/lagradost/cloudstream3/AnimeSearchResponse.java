package com.lagradost.cloudstream3;

import com.google.android.gms.common.internal.ImagesContract;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MainAPI.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0000\n\u0002\u0018\u0002\n\u0002\b8\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0086\b\u0018\u00002\u00020\u0001Bµ\u0001\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n\u0012\u0010\b\u0002\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\f\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u0003\u0012\u0014\b\u0002\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\n0\u0010\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\n\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0013\u0012\u0016\b\u0002\u0010\u0014\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0015\u0012\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u0017¢\u0006\u0004\b\u0018\u0010\u0019J\t\u0010@\u001a\u00020\u0003HÆ\u0003J\t\u0010A\u001a\u00020\u0003HÆ\u0003J\t\u0010B\u001a\u00020\u0003HÆ\u0003J\u000b\u0010C\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\u000b\u0010D\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010E\u001a\u0004\u0018\u00010\nHÆ\u0003¢\u0006\u0002\u0010&J\u0011\u0010F\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\fHÆ\u0003J\u000b\u0010G\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0015\u0010H\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\n0\u0010HÆ\u0003J\u0010\u0010I\u001a\u0004\u0018\u00010\nHÆ\u0003¢\u0006\u0002\u0010&J\u000b\u0010J\u001a\u0004\u0018\u00010\u0013HÆ\u0003J\u0017\u0010K\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0015HÆ\u0003J\u000b\u0010L\u001a\u0004\u0018\u00010\u0017HÆ\u0003JÀ\u0001\u0010M\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n2\u0010\b\u0002\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\f2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u00032\u0014\b\u0002\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\n0\u00102\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00132\u0016\b\u0002\u0010\u0014\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u00152\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u0017HÆ\u0001¢\u0006\u0002\u0010NJ\u0014\u0010O\u001a\u00020P2\b\u0010Q\u001a\u0004\u0018\u00010RHÖ\u0083\u0004J\n\u0010S\u001a\u00020\nHÖ\u0081\u0004J\n\u0010T\u001a\u00020\u0003HÖ\u0081\u0004R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0014\u0010\u0004\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001bR\u0014\u0010\u0005\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001bR\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!R\u001c\u0010\b\u001a\u0004\u0018\u00010\u0003X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010\u001b\"\u0004\b#\u0010$R\u001e\u0010\t\u001a\u0004\u0018\u00010\nX\u0086\u000e¢\u0006\u0010\n\u0002\u0010)\u001a\u0004\b%\u0010&\"\u0004\b'\u0010(R\"\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b*\u0010+\"\u0004\b,\u0010-R\u001c\u0010\u000e\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b.\u0010\u001b\"\u0004\b/\u0010$R&\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\n0\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b0\u00101\"\u0004\b2\u00103R\u001e\u0010\u0011\u001a\u0004\u0018\u00010\nX\u0096\u000e¢\u0006\u0010\n\u0002\u0010)\u001a\u0004\b4\u0010&\"\u0004\b5\u0010(R\u001c\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b6\u00107\"\u0004\b8\u00109R(\u0010\u0014\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0015X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b:\u00101\"\u0004\b;\u00103R\u001c\u0010\u0016\u001a\u0004\u0018\u00010\u0017X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b<\u0010=\"\u0004\b>\u0010?¨\u0006U"}, d2 = {"Lcom/lagradost/cloudstream3/AnimeSearchResponse;", "Lcom/lagradost/cloudstream3/SearchResponse;", "name", "", ImagesContract.URL, "apiName", "type", "Lcom/lagradost/cloudstream3/TvType;", "posterUrl", "year", "", "dubStatus", "", "Lcom/lagradost/cloudstream3/DubStatus;", "otherName", "episodes", "", "id", "quality", "Lcom/lagradost/cloudstream3/SearchQuality;", "posterHeaders", "", "score", "Lcom/lagradost/cloudstream3/Score;", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/lagradost/cloudstream3/TvType;Ljava/lang/String;Ljava/lang/Integer;Ljava/util/Set;Ljava/lang/String;Ljava/util/Map;Ljava/lang/Integer;Lcom/lagradost/cloudstream3/SearchQuality;Ljava/util/Map;Lcom/lagradost/cloudstream3/Score;)V", "getName", "()Ljava/lang/String;", "getUrl", "getApiName", "getType", "()Lcom/lagradost/cloudstream3/TvType;", "setType", "(Lcom/lagradost/cloudstream3/TvType;)V", "getPosterUrl", "setPosterUrl", "(Ljava/lang/String;)V", "getYear", "()Ljava/lang/Integer;", "setYear", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "getDubStatus", "()Ljava/util/Set;", "setDubStatus", "(Ljava/util/Set;)V", "getOtherName", "setOtherName", "getEpisodes", "()Ljava/util/Map;", "setEpisodes", "(Ljava/util/Map;)V", "getId", "setId", "getQuality", "()Lcom/lagradost/cloudstream3/SearchQuality;", "setQuality", "(Lcom/lagradost/cloudstream3/SearchQuality;)V", "getPosterHeaders", "setPosterHeaders", "getScore", "()Lcom/lagradost/cloudstream3/Score;", "setScore", "(Lcom/lagradost/cloudstream3/Score;)V", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/lagradost/cloudstream3/TvType;Ljava/lang/String;Ljava/lang/Integer;Ljava/util/Set;Ljava/lang/String;Ljava/util/Map;Ljava/lang/Integer;Lcom/lagradost/cloudstream3/SearchQuality;Ljava/util/Map;Lcom/lagradost/cloudstream3/Score;)Lcom/lagradost/cloudstream3/AnimeSearchResponse;", "equals", "", "other", "", "hashCode", "toString", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class AnimeSearchResponse implements SearchResponse {
    private final String apiName;
    private Set<DubStatus> dubStatus;
    private Map<DubStatus, Integer> episodes;
    private Integer id;
    private final String name;
    private String otherName;
    private Map<String, String> posterHeaders;
    private String posterUrl;
    private SearchQuality quality;
    private Score score;
    private TvType type;
    private final String url;
    private Integer year;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ AnimeSearchResponse copy$default(AnimeSearchResponse animeSearchResponse, String str, String str2, String str3, TvType tvType, String str4, Integer num, Set set, String str5, Map map, Integer num2, SearchQuality searchQuality, Map map2, Score score, int i, Object obj) {
        if ((i & 1) != 0) {
            str = animeSearchResponse.name;
        }
        return animeSearchResponse.copy(str, (i & 2) != 0 ? animeSearchResponse.url : str2, (i & 4) != 0 ? animeSearchResponse.apiName : str3, (i & 8) != 0 ? animeSearchResponse.type : tvType, (i & 16) != 0 ? animeSearchResponse.posterUrl : str4, (i & 32) != 0 ? animeSearchResponse.year : num, (i & 64) != 0 ? animeSearchResponse.dubStatus : set, (i & 128) != 0 ? animeSearchResponse.otherName : str5, (i & 256) != 0 ? animeSearchResponse.episodes : map, (i & 512) != 0 ? animeSearchResponse.id : num2, (i & 1024) != 0 ? animeSearchResponse.quality : searchQuality, (i & 2048) != 0 ? animeSearchResponse.posterHeaders : map2, (i & 4096) != 0 ? animeSearchResponse.score : score);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* JADX INFO: renamed from: component10, reason: from getter */
    public final Integer getId() {
        return this.id;
    }

    /* JADX INFO: renamed from: component11, reason: from getter */
    public final SearchQuality getQuality() {
        return this.quality;
    }

    public final Map<String, String> component12() {
        return this.posterHeaders;
    }

    /* JADX INFO: renamed from: component13, reason: from getter */
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

    public final Set<DubStatus> component7() {
        return this.dubStatus;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final String getOtherName() {
        return this.otherName;
    }

    public final Map<DubStatus, Integer> component9() {
        return this.episodes;
    }

    public final AnimeSearchResponse copy(String name, String url, String apiName, TvType type, String posterUrl, Integer year, Set<DubStatus> dubStatus, String otherName, Map<DubStatus, Integer> episodes, Integer id, SearchQuality quality, Map<String, String> posterHeaders, Score score) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(url, "url");
        Intrinsics.checkNotNullParameter(apiName, "apiName");
        Intrinsics.checkNotNullParameter(episodes, "episodes");
        return new AnimeSearchResponse(name, url, apiName, type, posterUrl, year, dubStatus, otherName, episodes, id, quality, posterHeaders, score);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AnimeSearchResponse)) {
            return false;
        }
        AnimeSearchResponse animeSearchResponse = (AnimeSearchResponse) other;
        return Intrinsics.areEqual(this.name, animeSearchResponse.name) && Intrinsics.areEqual(this.url, animeSearchResponse.url) && Intrinsics.areEqual(this.apiName, animeSearchResponse.apiName) && this.type == animeSearchResponse.type && Intrinsics.areEqual(this.posterUrl, animeSearchResponse.posterUrl) && Intrinsics.areEqual(this.year, animeSearchResponse.year) && Intrinsics.areEqual(this.dubStatus, animeSearchResponse.dubStatus) && Intrinsics.areEqual(this.otherName, animeSearchResponse.otherName) && Intrinsics.areEqual(this.episodes, animeSearchResponse.episodes) && Intrinsics.areEqual(this.id, animeSearchResponse.id) && this.quality == animeSearchResponse.quality && Intrinsics.areEqual(this.posterHeaders, animeSearchResponse.posterHeaders) && Intrinsics.areEqual(this.score, animeSearchResponse.score);
    }

    public int hashCode() {
        int iHashCode = ((((this.name.hashCode() * 31) + this.url.hashCode()) * 31) + this.apiName.hashCode()) * 31;
        TvType tvType = this.type;
        int iHashCode2 = (iHashCode + (tvType == null ? 0 : tvType.hashCode())) * 31;
        String str = this.posterUrl;
        int iHashCode3 = (iHashCode2 + (str == null ? 0 : str.hashCode())) * 31;
        Integer num = this.year;
        int iHashCode4 = (iHashCode3 + (num == null ? 0 : num.hashCode())) * 31;
        Set<DubStatus> set = this.dubStatus;
        int iHashCode5 = (iHashCode4 + (set == null ? 0 : set.hashCode())) * 31;
        String str2 = this.otherName;
        int iHashCode6 = (((iHashCode5 + (str2 == null ? 0 : str2.hashCode())) * 31) + this.episodes.hashCode()) * 31;
        Integer num2 = this.id;
        int iHashCode7 = (iHashCode6 + (num2 == null ? 0 : num2.hashCode())) * 31;
        SearchQuality searchQuality = this.quality;
        int iHashCode8 = (iHashCode7 + (searchQuality == null ? 0 : searchQuality.hashCode())) * 31;
        Map<String, String> map = this.posterHeaders;
        int iHashCode9 = (iHashCode8 + (map == null ? 0 : map.hashCode())) * 31;
        Score score = this.score;
        return iHashCode9 + (score != null ? score.getData() : 0);
    }

    public String toString() {
        return "AnimeSearchResponse(name=" + this.name + ", url=" + this.url + ", apiName=" + this.apiName + ", type=" + this.type + ", posterUrl=" + this.posterUrl + ", year=" + this.year + ", dubStatus=" + this.dubStatus + ", otherName=" + this.otherName + ", episodes=" + this.episodes + ", id=" + this.id + ", quality=" + this.quality + ", posterHeaders=" + this.posterHeaders + ", score=" + this.score + ')';
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use newAnimeSearchResponse")
    public AnimeSearchResponse(String name, String url, String apiName, TvType tvType, String str, Integer num, Set<DubStatus> set, String str2, Map<DubStatus, Integer> episodes, Integer num2, SearchQuality searchQuality, Map<String, String> map, Score score) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(url, "url");
        Intrinsics.checkNotNullParameter(apiName, "apiName");
        Intrinsics.checkNotNullParameter(episodes, "episodes");
        this.name = name;
        this.url = url;
        this.apiName = apiName;
        this.type = tvType;
        this.posterUrl = str;
        this.year = num;
        this.dubStatus = set;
        this.otherName = str2;
        this.episodes = episodes;
        this.id = num2;
        this.quality = searchQuality;
        this.posterHeaders = map;
        this.score = score;
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

    public final Set<DubStatus> getDubStatus() {
        return this.dubStatus;
    }

    public final void setDubStatus(Set<DubStatus> set) {
        this.dubStatus = set;
    }

    public final String getOtherName() {
        return this.otherName;
    }

    public final void setOtherName(String str) {
        this.otherName = str;
    }

    public /* synthetic */ AnimeSearchResponse(String str, String str2, String str3, TvType tvType, String str4, Integer num, Set set, String str5, Map map, Integer num2, SearchQuality searchQuality, Map map2, Score score, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, str3, (i & 8) != 0 ? null : tvType, (i & 16) != 0 ? null : str4, (i & 32) != 0 ? null : num, (i & 64) != 0 ? null : set, (i & 128) != 0 ? null : str5, (i & 256) != 0 ? new LinkedHashMap() : map, (i & 512) != 0 ? null : num2, (i & 1024) != 0 ? null : searchQuality, (i & 2048) != 0 ? null : map2, (i & 4096) != 0 ? null : score);
    }

    public final Map<DubStatus, Integer> getEpisodes() {
        return this.episodes;
    }

    public final void setEpisodes(Map<DubStatus, Integer> map) {
        Intrinsics.checkNotNullParameter(map, "<set-?>");
        this.episodes = map;
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
}
