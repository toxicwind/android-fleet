package com.lagradost.cloudstream3.utils;

import com.lagradost.cloudstream3.AudioFile;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ExtractorApi.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010$\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b&\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0086\b\u0018\u00002\u00020\u0001Bo\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0014\b\u0002\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\f\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u000e\b\u0002\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00110\u0006¢\u0006\u0004\b\u0012\u0010\u0013Bc\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\n\u0012\b\b\u0002\u0010\u0014\u001a\u00020\u0015\u0012\u0014\b\u0002\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\f\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0012\u0010\u0016J\t\u00100\u001a\u00020\u0003HÆ\u0003J\t\u00101\u001a\u00020\u0003HÆ\u0003J\u000f\u00102\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006HÆ\u0003J\t\u00103\u001a\u00020\u0003HÆ\u0003J\t\u00104\u001a\u00020\nHÆ\u0003J\u0015\u00105\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\fHÆ\u0003J\u000b\u00106\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u00107\u001a\u00020\u000fHÆ\u0003J\u000f\u00108\u001a\b\u0012\u0004\u0012\u00020\u00110\u0006HÆ\u0003J}\u00109\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\n2\u0014\b\u0002\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\f2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\u000e\b\u0002\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00110\u0006HÆ\u0001J\u0014\u0010:\u001a\u00020\u00152\b\u0010;\u001a\u0004\u0018\u00010<HÖ\u0083\u0004J\n\u0010=\u001a\u00020\nHÖ\u0081\u0004J\n\u0010>\u001a\u00020\u0003HÖ\u0081\u0004R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0014\u0010\u0004\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0018R\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u001a\u0010\b\u001a\u00020\u0003X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u0018\"\u0004\b\u001d\u0010\u001eR\u001a\u0010\t\u001a\u00020\nX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"R&\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\fX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&R\u001c\u0010\r\u001a\u0004\u0018\u00010\u0003X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b'\u0010\u0018\"\u0004\b(\u0010\u001eR\u001a\u0010\u000e\u001a\u00020\u000fX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b)\u0010*\"\u0004\b+\u0010,R \u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00110\u0006X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b-\u0010\u001b\"\u0004\b.\u0010/¨\u0006?"}, d2 = {"Lcom/lagradost/cloudstream3/utils/ExtractorLinkPlayList;", "Lcom/lagradost/cloudstream3/utils/ExtractorLink;", "source", "", "name", "playlist", "", "Lcom/lagradost/cloudstream3/utils/PlayListItem;", "referer", "quality", "", "headers", "", "extractorData", "type", "Lcom/lagradost/cloudstream3/utils/ExtractorLinkType;", "audioTracks", "Lcom/lagradost/cloudstream3/AudioFile;", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;ILjava/util/Map;Ljava/lang/String;Lcom/lagradost/cloudstream3/utils/ExtractorLinkType;Ljava/util/List;)V", "isM3u8", "", "(Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;IZLjava/util/Map;Ljava/lang/String;)V", "getSource", "()Ljava/lang/String;", "getName", "getPlaylist", "()Ljava/util/List;", "getReferer", "setReferer", "(Ljava/lang/String;)V", "getQuality", "()I", "setQuality", "(I)V", "getHeaders", "()Ljava/util/Map;", "setHeaders", "(Ljava/util/Map;)V", "getExtractorData", "setExtractorData", "getType", "()Lcom/lagradost/cloudstream3/utils/ExtractorLinkType;", "setType", "(Lcom/lagradost/cloudstream3/utils/ExtractorLinkType;)V", "getAudioTracks", "setAudioTracks", "(Ljava/util/List;)V", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", "", "hashCode", "toString", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class ExtractorLinkPlayList extends ExtractorLink {
    private List<AudioFile> audioTracks;
    private String extractorData;
    private Map<String, String> headers;
    private final String name;
    private final List<PlayListItem> playlist;
    private int quality;
    private String referer;
    private final String source;
    private ExtractorLinkType type;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ ExtractorLinkPlayList copy$default(ExtractorLinkPlayList extractorLinkPlayList, String str, String str2, List list, String str3, int i, Map map, String str4, ExtractorLinkType extractorLinkType, List list2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = extractorLinkPlayList.source;
        }
        if ((i2 & 2) != 0) {
            str2 = extractorLinkPlayList.name;
        }
        if ((i2 & 4) != 0) {
            list = extractorLinkPlayList.playlist;
        }
        if ((i2 & 8) != 0) {
            str3 = extractorLinkPlayList.referer;
        }
        if ((i2 & 16) != 0) {
            i = extractorLinkPlayList.quality;
        }
        if ((i2 & 32) != 0) {
            map = extractorLinkPlayList.headers;
        }
        if ((i2 & 64) != 0) {
            str4 = extractorLinkPlayList.extractorData;
        }
        if ((i2 & 128) != 0) {
            extractorLinkType = extractorLinkPlayList.type;
        }
        if ((i2 & 256) != 0) {
            list2 = extractorLinkPlayList.audioTracks;
        }
        ExtractorLinkType extractorLinkType2 = extractorLinkType;
        List list3 = list2;
        Map map2 = map;
        String str5 = str4;
        int i3 = i;
        List list4 = list;
        return extractorLinkPlayList.copy(str, str2, list4, str3, i3, map2, str5, extractorLinkType2, list3);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getSource() {
        return this.source;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getName() {
        return this.name;
    }

    public final List<PlayListItem> component3() {
        return this.playlist;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getReferer() {
        return this.referer;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final int getQuality() {
        return this.quality;
    }

    public final Map<String, String> component6() {
        return this.headers;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final String getExtractorData() {
        return this.extractorData;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final ExtractorLinkType getType() {
        return this.type;
    }

    public final List<AudioFile> component9() {
        return this.audioTracks;
    }

    public final ExtractorLinkPlayList copy(String source, String name, List<PlayListItem> playlist, String referer, int quality, Map<String, String> headers, String extractorData, ExtractorLinkType type, List<AudioFile> audioTracks) {
        Intrinsics.checkNotNullParameter(source, "source");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(playlist, "playlist");
        Intrinsics.checkNotNullParameter(referer, "referer");
        Intrinsics.checkNotNullParameter(headers, "headers");
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(audioTracks, "audioTracks");
        return new ExtractorLinkPlayList(source, name, playlist, referer, quality, headers, extractorData, type, audioTracks);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ExtractorLinkPlayList)) {
            return false;
        }
        ExtractorLinkPlayList extractorLinkPlayList = (ExtractorLinkPlayList) other;
        return Intrinsics.areEqual(this.source, extractorLinkPlayList.source) && Intrinsics.areEqual(this.name, extractorLinkPlayList.name) && Intrinsics.areEqual(this.playlist, extractorLinkPlayList.playlist) && Intrinsics.areEqual(this.referer, extractorLinkPlayList.referer) && this.quality == extractorLinkPlayList.quality && Intrinsics.areEqual(this.headers, extractorLinkPlayList.headers) && Intrinsics.areEqual(this.extractorData, extractorLinkPlayList.extractorData) && this.type == extractorLinkPlayList.type && Intrinsics.areEqual(this.audioTracks, extractorLinkPlayList.audioTracks);
    }

    public int hashCode() {
        int iHashCode = ((((((((((this.source.hashCode() * 31) + this.name.hashCode()) * 31) + this.playlist.hashCode()) * 31) + this.referer.hashCode()) * 31) + this.quality) * 31) + this.headers.hashCode()) * 31;
        String str = this.extractorData;
        return ((((iHashCode + (str == null ? 0 : str.hashCode())) * 31) + this.type.hashCode()) * 31) + this.audioTracks.hashCode();
    }

    @Override // com.lagradost.cloudstream3.utils.ExtractorLink
    public String toString() {
        return "ExtractorLinkPlayList(source=" + this.source + ", name=" + this.name + ", playlist=" + this.playlist + ", referer=" + this.referer + ", quality=" + this.quality + ", headers=" + this.headers + ", extractorData=" + this.extractorData + ", type=" + this.type + ", audioTracks=" + this.audioTracks + ')';
    }

    @Override // com.lagradost.cloudstream3.utils.ExtractorLink
    public String getSource() {
        return this.source;
    }

    @Override // com.lagradost.cloudstream3.utils.ExtractorLink
    public String getName() {
        return this.name;
    }

    public final List<PlayListItem> getPlaylist() {
        return this.playlist;
    }

    @Override // com.lagradost.cloudstream3.utils.ExtractorLink, com.lagradost.cloudstream3.IDownloadableMinimum
    public String getReferer() {
        return this.referer;
    }

    @Override // com.lagradost.cloudstream3.utils.ExtractorLink
    public void setReferer(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.referer = str;
    }

    @Override // com.lagradost.cloudstream3.utils.ExtractorLink
    public int getQuality() {
        return this.quality;
    }

    @Override // com.lagradost.cloudstream3.utils.ExtractorLink
    public void setQuality(int i) {
        this.quality = i;
    }

    public /* synthetic */ ExtractorLinkPlayList(String str, String str2, List list, String str3, int i, Map map, String str4, ExtractorLinkType extractorLinkType, List list2, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, list, str3, i, (i2 & 32) != 0 ? MapsKt.emptyMap() : map, (i2 & 64) != 0 ? null : str4, extractorLinkType, (i2 & 256) != 0 ? CollectionsKt.emptyList() : list2);
    }

    @Override // com.lagradost.cloudstream3.utils.ExtractorLink, com.lagradost.cloudstream3.IDownloadableMinimum
    public Map<String, String> getHeaders() {
        return this.headers;
    }

    @Override // com.lagradost.cloudstream3.utils.ExtractorLink
    public void setHeaders(Map<String, String> map) {
        Intrinsics.checkNotNullParameter(map, "<set-?>");
        this.headers = map;
    }

    @Override // com.lagradost.cloudstream3.utils.ExtractorLink
    public String getExtractorData() {
        return this.extractorData;
    }

    @Override // com.lagradost.cloudstream3.utils.ExtractorLink
    public void setExtractorData(String str) {
        this.extractorData = str;
    }

    @Override // com.lagradost.cloudstream3.utils.ExtractorLink
    public ExtractorLinkType getType() {
        return this.type;
    }

    @Override // com.lagradost.cloudstream3.utils.ExtractorLink
    public void setType(ExtractorLinkType extractorLinkType) {
        Intrinsics.checkNotNullParameter(extractorLinkType, "<set-?>");
        this.type = extractorLinkType;
    }

    @Override // com.lagradost.cloudstream3.utils.ExtractorLink
    public List<AudioFile> getAudioTracks() {
        return this.audioTracks;
    }

    @Override // com.lagradost.cloudstream3.utils.ExtractorLink
    public void setAudioTracks(List<AudioFile> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.audioTracks = list;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ExtractorLinkPlayList(String source, String name, List<PlayListItem> playlist, String referer, int i, Map<String, String> headers, String str, ExtractorLinkType type, List<AudioFile> audioTracks) {
        super(source, name, "", referer, i, headers, str, type, audioTracks);
        Intrinsics.checkNotNullParameter(source, "source");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(playlist, "playlist");
        Intrinsics.checkNotNullParameter(referer, "referer");
        Intrinsics.checkNotNullParameter(headers, "headers");
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(audioTracks, "audioTracks");
        this.source = source;
        this.name = name;
        this.playlist = playlist;
        this.referer = referer;
        this.quality = i;
        this.headers = headers;
        this.extractorData = str;
        this.type = type;
        this.audioTracks = audioTracks;
    }

    public /* synthetic */ ExtractorLinkPlayList(String str, String str2, List list, String str3, int i, boolean z, Map map, String str4, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, list, str3, i, (i2 & 32) != 0 ? false : z, (i2 & 64) != 0 ? MapsKt.emptyMap() : map, (i2 & 128) != 0 ? null : str4);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public ExtractorLinkPlayList(String source, String name, List<PlayListItem> playlist, String referer, int i, boolean z, Map<String, String> headers, String str) {
        this(source, name, playlist, referer, i, headers, str, z ? ExtractorLinkType.M3U8 : ExtractorLinkType.VIDEO, null, 256, null);
        Intrinsics.checkNotNullParameter(source, "source");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(playlist, "playlist");
        Intrinsics.checkNotNullParameter(referer, "referer");
        Intrinsics.checkNotNullParameter(headers, "headers");
    }
}
