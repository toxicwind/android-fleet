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
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0000\n\u0002\u0018\u0002\n\u0002\b+\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0086\b\u0018\u00002\u00020\u0001Bq\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f\u0012\u0016\b\u0002\u0010\r\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u000e\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0010¢\u0006\u0004\b\u0011\u0010\u0012Be\b\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f\u0012\u0016\b\u0002\u0010\r\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u000e¢\u0006\u0004\b\u0011\u0010\u0013J\t\u00100\u001a\u00020\u0003HÆ\u0003J\t\u00101\u001a\u00020\u0003HÆ\u0003J\t\u00102\u001a\u00020\u0003HÆ\u0003J\u000b\u00103\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\u000b\u00104\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u00105\u001a\u0004\u0018\u00010\nHÆ\u0003¢\u0006\u0002\u0010 J\u000b\u00106\u001a\u0004\u0018\u00010\fHÆ\u0003J\u0017\u00107\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u000eHÆ\u0003J\u000b\u00108\u001a\u0004\u0018\u00010\u0010HÆ\u0003J\u0080\u0001\u00109\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f2\u0016\b\u0002\u0010\r\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u000e2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0010HÆ\u0001¢\u0006\u0002\u0010:J\u0014\u0010;\u001a\u00020<2\b\u0010=\u001a\u0004\u0018\u00010>HÖ\u0083\u0004J\n\u0010?\u001a\u00020\nHÖ\u0081\u0004J\n\u0010@\u001a\u00020\u0003HÖ\u0081\u0004R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0014\u0010\u0004\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0015R\u0014\u0010\u0005\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0015R\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u001c\u0010\b\u001a\u0004\u0018\u00010\u0003X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u0015\"\u0004\b\u001d\u0010\u001eR\u001e\u0010\t\u001a\u0004\u0018\u00010\nX\u0096\u000e¢\u0006\u0010\n\u0002\u0010#\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"R\u001c\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010%\"\u0004\b&\u0010'R(\u0010\r\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u000eX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b(\u0010)\"\u0004\b*\u0010+R\u001c\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b,\u0010-\"\u0004\b.\u0010/¨\u0006A"}, d2 = {"Lcom/lagradost/cloudstream3/TorrentSearchResponse;", "Lcom/lagradost/cloudstream3/SearchResponse;", "name", "", ImagesContract.URL, "apiName", "type", "Lcom/lagradost/cloudstream3/TvType;", "posterUrl", "id", "", "quality", "Lcom/lagradost/cloudstream3/SearchQuality;", "posterHeaders", "", "score", "Lcom/lagradost/cloudstream3/Score;", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/lagradost/cloudstream3/TvType;Ljava/lang/String;Ljava/lang/Integer;Lcom/lagradost/cloudstream3/SearchQuality;Ljava/util/Map;Lcom/lagradost/cloudstream3/Score;)V", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/lagradost/cloudstream3/TvType;Ljava/lang/String;Ljava/lang/Integer;Lcom/lagradost/cloudstream3/SearchQuality;Ljava/util/Map;)V", "getName", "()Ljava/lang/String;", "getUrl", "getApiName", "getType", "()Lcom/lagradost/cloudstream3/TvType;", "setType", "(Lcom/lagradost/cloudstream3/TvType;)V", "getPosterUrl", "setPosterUrl", "(Ljava/lang/String;)V", "getId", "()Ljava/lang/Integer;", "setId", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "getQuality", "()Lcom/lagradost/cloudstream3/SearchQuality;", "setQuality", "(Lcom/lagradost/cloudstream3/SearchQuality;)V", "getPosterHeaders", "()Ljava/util/Map;", "setPosterHeaders", "(Ljava/util/Map;)V", "getScore", "()Lcom/lagradost/cloudstream3/Score;", "setScore", "(Lcom/lagradost/cloudstream3/Score;)V", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/lagradost/cloudstream3/TvType;Ljava/lang/String;Ljava/lang/Integer;Lcom/lagradost/cloudstream3/SearchQuality;Ljava/util/Map;Lcom/lagradost/cloudstream3/Score;)Lcom/lagradost/cloudstream3/TorrentSearchResponse;", "equals", "", "other", "", "hashCode", "toString", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class TorrentSearchResponse implements SearchResponse {
    private final String apiName;
    private Integer id;
    private final String name;
    private Map<String, String> posterHeaders;
    private String posterUrl;
    private SearchQuality quality;
    private Score score;
    private TvType type;
    private final String url;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ TorrentSearchResponse copy$default(TorrentSearchResponse torrentSearchResponse, String str, String str2, String str3, TvType tvType, String str4, Integer num, SearchQuality searchQuality, Map map, Score score, int i, Object obj) {
        if ((i & 1) != 0) {
            str = torrentSearchResponse.name;
        }
        if ((i & 2) != 0) {
            str2 = torrentSearchResponse.url;
        }
        if ((i & 4) != 0) {
            str3 = torrentSearchResponse.apiName;
        }
        if ((i & 8) != 0) {
            tvType = torrentSearchResponse.type;
        }
        if ((i & 16) != 0) {
            str4 = torrentSearchResponse.posterUrl;
        }
        if ((i & 32) != 0) {
            num = torrentSearchResponse.id;
        }
        if ((i & 64) != 0) {
            searchQuality = torrentSearchResponse.quality;
        }
        if ((i & 128) != 0) {
            map = torrentSearchResponse.posterHeaders;
        }
        if ((i & 256) != 0) {
            score = torrentSearchResponse.score;
        }
        Map map2 = map;
        Score score2 = score;
        Integer num2 = num;
        SearchQuality searchQuality2 = searchQuality;
        String str5 = str4;
        String str6 = str3;
        return torrentSearchResponse.copy(str, str2, str6, tvType, str5, num2, searchQuality2, map2, score2);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getName() {
        return this.name;
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
    public final Score getScore() {
        return this.score;
    }

    public final TorrentSearchResponse copy(String name, String url, String apiName, TvType type, String posterUrl, Integer id, SearchQuality quality, Map<String, String> posterHeaders, Score score) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(url, "url");
        Intrinsics.checkNotNullParameter(apiName, "apiName");
        return new TorrentSearchResponse(name, url, apiName, type, posterUrl, id, quality, posterHeaders, score);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TorrentSearchResponse)) {
            return false;
        }
        TorrentSearchResponse torrentSearchResponse = (TorrentSearchResponse) other;
        return Intrinsics.areEqual(this.name, torrentSearchResponse.name) && Intrinsics.areEqual(this.url, torrentSearchResponse.url) && Intrinsics.areEqual(this.apiName, torrentSearchResponse.apiName) && this.type == torrentSearchResponse.type && Intrinsics.areEqual(this.posterUrl, torrentSearchResponse.posterUrl) && Intrinsics.areEqual(this.id, torrentSearchResponse.id) && this.quality == torrentSearchResponse.quality && Intrinsics.areEqual(this.posterHeaders, torrentSearchResponse.posterHeaders) && Intrinsics.areEqual(this.score, torrentSearchResponse.score);
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
        Score score = this.score;
        return iHashCode6 + (score != null ? score.getData() : 0);
    }

    public String toString() {
        return "TorrentSearchResponse(name=" + this.name + ", url=" + this.url + ", apiName=" + this.apiName + ", type=" + this.type + ", posterUrl=" + this.posterUrl + ", id=" + this.id + ", quality=" + this.quality + ", posterHeaders=" + this.posterHeaders + ", score=" + this.score + ')';
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use newTorrentSearchResponse")
    public TorrentSearchResponse(String name, String url, String apiName, TvType tvType, String str, Integer num, SearchQuality searchQuality, Map<String, String> map, Score score) {
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
        this.score = score;
    }

    public /* synthetic */ TorrentSearchResponse(String str, String str2, String str3, TvType tvType, String str4, Integer num, SearchQuality searchQuality, Map map, Score score, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, str3, tvType, str4, (i & 32) != 0 ? null : num, (i & 64) != 0 ? null : searchQuality, (i & 128) != 0 ? null : map, (i & 256) != 0 ? null : score);
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

    @Override // com.lagradost.cloudstream3.SearchResponse
    public Score getScore() {
        return this.score;
    }

    @Override // com.lagradost.cloudstream3.SearchResponse
    public void setScore(Score score) {
        this.score = score;
    }

    public /* synthetic */ TorrentSearchResponse(String str, String str2, String str3, TvType tvType, String str4, Integer num, SearchQuality searchQuality, Map map, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, str3, tvType, str4, (i & 32) != 0 ? null : num, (i & 64) != 0 ? null : searchQuality, (i & 128) != 0 ? null : map);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    @Deprecated(level = DeprecationLevel.ERROR, message = "Use newTorrentSearchResponse")
    public TorrentSearchResponse(String name, String url, String apiName, TvType tvType, String str, Integer num, SearchQuality searchQuality, Map<String, String> map) {
        this(name, url, apiName, tvType, str, num, searchQuality, map, null);
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(url, "url");
        Intrinsics.checkNotNullParameter(apiName, "apiName");
    }
}
