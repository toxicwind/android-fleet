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
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b.\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0086\b\u0018\u00002\u00020\u0001B\u0081\u0001\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f\u0012\u0016\b\u0002\u0010\r\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u000e\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0011¢\u0006\u0004\b\u0012\u0010\u0013Bq\b\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f\u0012\u0016\b\u0002\u0010\r\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u000e\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0012\u0010\u0014J\t\u00103\u001a\u00020\u0003HÆ\u0003J\t\u00104\u001a\u00020\u0003HÆ\u0003J\t\u00105\u001a\u00020\u0003HÆ\u0003J\u000b\u00106\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\u000b\u00107\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u00108\u001a\u0004\u0018\u00010\nHÆ\u0003¢\u0006\u0002\u0010!J\u000b\u00109\u001a\u0004\u0018\u00010\fHÆ\u0003J\u0017\u0010:\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u000eHÆ\u0003J\u000b\u0010;\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010<\u001a\u0004\u0018\u00010\u0011HÆ\u0003J\u008c\u0001\u0010=\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f2\u0016\b\u0002\u0010\r\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u000e2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0011HÆ\u0001¢\u0006\u0002\u0010>J\u0014\u0010?\u001a\u00020@2\b\u0010A\u001a\u0004\u0018\u00010BHÖ\u0083\u0004J\n\u0010C\u001a\u00020\nHÖ\u0081\u0004J\n\u0010D\u001a\u00020\u0003HÖ\u0081\u0004R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0014\u0010\u0004\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0016R\u0014\u0010\u0005\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0016R\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\u001c\u0010\b\u001a\u0004\u0018\u00010\u0003X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u0016\"\u0004\b\u001e\u0010\u001fR\u001e\u0010\t\u001a\u0004\u0018\u00010\nX\u0096\u000e¢\u0006\u0010\n\u0002\u0010$\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#R\u001c\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010&\"\u0004\b'\u0010(R(\u0010\r\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u000eX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b)\u0010*\"\u0004\b+\u0010,R\u001c\u0010\u000f\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b-\u0010\u0016\"\u0004\b.\u0010\u001fR\u001c\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b/\u00100\"\u0004\b1\u00102¨\u0006E"}, d2 = {"Lcom/lagradost/cloudstream3/LiveSearchResponse;", "Lcom/lagradost/cloudstream3/SearchResponse;", "name", "", ImagesContract.URL, "apiName", "type", "Lcom/lagradost/cloudstream3/TvType;", "posterUrl", "id", "", "quality", "Lcom/lagradost/cloudstream3/SearchQuality;", "posterHeaders", "", "lang", "score", "Lcom/lagradost/cloudstream3/Score;", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/lagradost/cloudstream3/TvType;Ljava/lang/String;Ljava/lang/Integer;Lcom/lagradost/cloudstream3/SearchQuality;Ljava/util/Map;Ljava/lang/String;Lcom/lagradost/cloudstream3/Score;)V", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/lagradost/cloudstream3/TvType;Ljava/lang/String;Ljava/lang/Integer;Lcom/lagradost/cloudstream3/SearchQuality;Ljava/util/Map;Ljava/lang/String;)V", "getName", "()Ljava/lang/String;", "getUrl", "getApiName", "getType", "()Lcom/lagradost/cloudstream3/TvType;", "setType", "(Lcom/lagradost/cloudstream3/TvType;)V", "getPosterUrl", "setPosterUrl", "(Ljava/lang/String;)V", "getId", "()Ljava/lang/Integer;", "setId", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "getQuality", "()Lcom/lagradost/cloudstream3/SearchQuality;", "setQuality", "(Lcom/lagradost/cloudstream3/SearchQuality;)V", "getPosterHeaders", "()Ljava/util/Map;", "setPosterHeaders", "(Ljava/util/Map;)V", "getLang", "setLang", "getScore", "()Lcom/lagradost/cloudstream3/Score;", "setScore", "(Lcom/lagradost/cloudstream3/Score;)V", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/lagradost/cloudstream3/TvType;Ljava/lang/String;Ljava/lang/Integer;Lcom/lagradost/cloudstream3/SearchQuality;Ljava/util/Map;Ljava/lang/String;Lcom/lagradost/cloudstream3/Score;)Lcom/lagradost/cloudstream3/LiveSearchResponse;", "equals", "", "other", "", "hashCode", "toString", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class LiveSearchResponse implements SearchResponse {
    private final String apiName;
    private Integer id;
    private String lang;
    private final String name;
    private Map<String, String> posterHeaders;
    private String posterUrl;
    private SearchQuality quality;
    private Score score;
    private TvType type;
    private final String url;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ LiveSearchResponse copy$default(LiveSearchResponse liveSearchResponse, String str, String str2, String str3, TvType tvType, String str4, Integer num, SearchQuality searchQuality, Map map, String str5, Score score, int i, Object obj) {
        if ((i & 1) != 0) {
            str = liveSearchResponse.name;
        }
        if ((i & 2) != 0) {
            str2 = liveSearchResponse.url;
        }
        if ((i & 4) != 0) {
            str3 = liveSearchResponse.apiName;
        }
        if ((i & 8) != 0) {
            tvType = liveSearchResponse.type;
        }
        if ((i & 16) != 0) {
            str4 = liveSearchResponse.posterUrl;
        }
        if ((i & 32) != 0) {
            num = liveSearchResponse.id;
        }
        if ((i & 64) != 0) {
            searchQuality = liveSearchResponse.quality;
        }
        if ((i & 128) != 0) {
            map = liveSearchResponse.posterHeaders;
        }
        if ((i & 256) != 0) {
            str5 = liveSearchResponse.lang;
        }
        if ((i & 512) != 0) {
            score = liveSearchResponse.score;
        }
        String str6 = str5;
        Score score2 = score;
        SearchQuality searchQuality2 = searchQuality;
        Map map2 = map;
        String str7 = str4;
        Integer num2 = num;
        return liveSearchResponse.copy(str, str2, str3, tvType, str7, num2, searchQuality2, map2, str6, score2);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* JADX INFO: renamed from: component10, reason: from getter */
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
    public final Integer getId() {
        return this.id;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final SearchQuality getQuality() {
        return this.quality;
    }

    public final Map<String, String> component8() {
        return this.posterHeaders;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final String getLang() {
        return this.lang;
    }

    public final LiveSearchResponse copy(String name, String url, String apiName, TvType type, String posterUrl, Integer id, SearchQuality quality, Map<String, String> posterHeaders, String lang, Score score) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(url, "url");
        Intrinsics.checkNotNullParameter(apiName, "apiName");
        return new LiveSearchResponse(name, url, apiName, type, posterUrl, id, quality, posterHeaders, lang, score);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof LiveSearchResponse)) {
            return false;
        }
        LiveSearchResponse liveSearchResponse = (LiveSearchResponse) other;
        return Intrinsics.areEqual(this.name, liveSearchResponse.name) && Intrinsics.areEqual(this.url, liveSearchResponse.url) && Intrinsics.areEqual(this.apiName, liveSearchResponse.apiName) && this.type == liveSearchResponse.type && Intrinsics.areEqual(this.posterUrl, liveSearchResponse.posterUrl) && Intrinsics.areEqual(this.id, liveSearchResponse.id) && this.quality == liveSearchResponse.quality && Intrinsics.areEqual(this.posterHeaders, liveSearchResponse.posterHeaders) && Intrinsics.areEqual(this.lang, liveSearchResponse.lang) && Intrinsics.areEqual(this.score, liveSearchResponse.score);
    }

    public int hashCode() {
        int iHashCode = ((((this.name.hashCode() * 31) + this.url.hashCode()) * 31) + this.apiName.hashCode()) * 31;
        TvType tvType = this.type;
        int iHashCode2 = (iHashCode + (tvType == null ? 0 : tvType.hashCode())) * 31;
        String str = this.posterUrl;
        int iHashCode3 = (iHashCode2 + (str == null ? 0 : str.hashCode())) * 31;
        Integer num = this.id;
        int iHashCode4 = (iHashCode3 + (num == null ? 0 : num.hashCode())) * 31;
        SearchQuality searchQuality = this.quality;
        int iHashCode5 = (iHashCode4 + (searchQuality == null ? 0 : searchQuality.hashCode())) * 31;
        Map<String, String> map = this.posterHeaders;
        int iHashCode6 = (iHashCode5 + (map == null ? 0 : map.hashCode())) * 31;
        String str2 = this.lang;
        int iHashCode7 = (iHashCode6 + (str2 == null ? 0 : str2.hashCode())) * 31;
        Score score = this.score;
        return iHashCode7 + (score != null ? score.getData() : 0);
    }

    public String toString() {
        return "LiveSearchResponse(name=" + this.name + ", url=" + this.url + ", apiName=" + this.apiName + ", type=" + this.type + ", posterUrl=" + this.posterUrl + ", id=" + this.id + ", quality=" + this.quality + ", posterHeaders=" + this.posterHeaders + ", lang=" + this.lang + ", score=" + this.score + ')';
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use newLiveSearchResponse")
    public LiveSearchResponse(String name, String url, String apiName, TvType tvType, String str, Integer num, SearchQuality searchQuality, Map<String, String> map, String str2, Score score) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(url, "url");
        Intrinsics.checkNotNullParameter(apiName, "apiName");
        this.name = name;
        this.url = url;
        this.apiName = apiName;
        this.type = tvType;
        this.posterUrl = str;
        this.id = num;
        this.quality = searchQuality;
        this.posterHeaders = map;
        this.lang = str2;
        this.score = score;
    }

    public /* synthetic */ LiveSearchResponse(String str, String str2, String str3, TvType tvType, String str4, Integer num, SearchQuality searchQuality, Map map, String str5, Score score, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, str3, (i & 8) != 0 ? null : tvType, (i & 16) != 0 ? null : str4, (i & 32) != 0 ? null : num, (i & 64) != 0 ? null : searchQuality, (i & 128) != 0 ? null : map, (i & 256) != 0 ? null : str5, (i & 512) != 0 ? null : score);
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

    public final String getLang() {
        return this.lang;
    }

    public final void setLang(String str) {
        this.lang = str;
    }

    @Override // com.lagradost.cloudstream3.SearchResponse
    public Score getScore() {
        return this.score;
    }

    @Override // com.lagradost.cloudstream3.SearchResponse
    public void setScore(Score score) {
        this.score = score;
    }

    public /* synthetic */ LiveSearchResponse(String str, String str2, String str3, TvType tvType, String str4, Integer num, SearchQuality searchQuality, Map map, String str5, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, str3, tvType, str4, (i & 32) != 0 ? null : num, (i & 64) != 0 ? null : searchQuality, (i & 128) != 0 ? null : map, (i & 256) != 0 ? null : str5);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    @Deprecated(level = DeprecationLevel.ERROR, message = "Use newLiveSearchResponse")
    public LiveSearchResponse(String name, String url, String apiName, TvType tvType, String str, Integer num, SearchQuality searchQuality, Map<String, String> map, String str2) {
        this(name, url, apiName, tvType, str, num, searchQuality, map, str2, null);
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(url, "url");
        Intrinsics.checkNotNullParameter(apiName, "apiName");
    }
}
