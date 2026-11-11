package com.lagradost.cloudstream3.ui.player;

import android.net.Uri;
import com.lagradost.cloudstream3.TvType;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LinkGenerator.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b \n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B\u0083\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\n\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\n\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\n\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0010¢\u0006\u0004\b\u0011\u0010\u0012J\t\u0010#\u001a\u00020\u0003HÆ\u0003J\t\u0010$\u001a\u00020\u0005HÆ\u0003J\u000b\u0010%\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010&\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010'\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u0010\u0010(\u001a\u0004\u0018\u00010\nHÆ\u0003¢\u0006\u0002\u0010\u001bJ\u0010\u0010)\u001a\u0004\u0018\u00010\nHÆ\u0003¢\u0006\u0002\u0010\u001bJ\u0010\u0010*\u001a\u0004\u0018\u00010\nHÆ\u0003¢\u0006\u0002\u0010\u001bJ\u0010\u0010+\u001a\u0004\u0018\u00010\nHÆ\u0003¢\u0006\u0002\u0010\u001bJ\u000b\u0010,\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010-\u001a\u0004\u0018\u00010\u0010HÆ\u0003J\u008e\u0001\u0010.\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0010HÆ\u0001¢\u0006\u0002\u0010/J\u0014\u00100\u001a\u0002012\b\u00102\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u00103\u001a\u00020\nHÖ\u0081\u0004J\n\u00104\u001a\u00020\u0005HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0016R\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0016R\u0013\u0010\b\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0016R\u0015\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\n\n\u0002\u0010\u001c\u001a\u0004\b\u001a\u0010\u001bR\u0015\u0010\u000b\u001a\u0004\u0018\u00010\n¢\u0006\n\n\u0002\u0010\u001c\u001a\u0004\b\u001d\u0010\u001bR\u0015\u0010\f\u001a\u0004\u0018\u00010\n¢\u0006\n\n\u0002\u0010\u001c\u001a\u0004\b\u001e\u0010\u001bR\u0015\u0010\r\u001a\u0004\u0018\u00010\n¢\u0006\n\n\u0002\u0010\u001c\u001a\u0004\b\u001f\u0010\u001bR\u0013\u0010\u000e\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u0016R\u0013\u0010\u000f\u001a\u0004\u0018\u00010\u0010¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\"¨\u00065"}, d2 = {"Lcom/lagradost/cloudstream3/ui/player/ExtractorUri;", "", "uri", "Landroid/net/Uri;", "name", "", "basePath", "relativePath", "displayName", "id", "", "parentId", "episode", "season", "headerName", "tvType", "Lcom/lagradost/cloudstream3/TvType;", "<init>", "(Landroid/net/Uri;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Lcom/lagradost/cloudstream3/TvType;)V", "getUri", "()Landroid/net/Uri;", "getName", "()Ljava/lang/String;", "getBasePath", "getRelativePath", "getDisplayName", "getId", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getParentId", "getEpisode", "getSeason", "getHeaderName", "getTvType", "()Lcom/lagradost/cloudstream3/TvType;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "copy", "(Landroid/net/Uri;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Lcom/lagradost/cloudstream3/TvType;)Lcom/lagradost/cloudstream3/ui/player/ExtractorUri;", "equals", "", "other", "hashCode", "toString", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class ExtractorUri {
    private final String basePath;
    private final String displayName;
    private final Integer episode;
    private final String headerName;
    private final Integer id;
    private final String name;
    private final Integer parentId;
    private final String relativePath;
    private final Integer season;
    private final TvType tvType;
    private final Uri uri;

    public static /* synthetic */ ExtractorUri copy$default(ExtractorUri extractorUri, Uri uri, String str, String str2, String str3, String str4, Integer num, Integer num2, Integer num3, Integer num4, String str5, TvType tvType, int i, Object obj) {
        if ((i & 1) != 0) {
            uri = extractorUri.uri;
        }
        if ((i & 2) != 0) {
            str = extractorUri.name;
        }
        if ((i & 4) != 0) {
            str2 = extractorUri.basePath;
        }
        if ((i & 8) != 0) {
            str3 = extractorUri.relativePath;
        }
        if ((i & 16) != 0) {
            str4 = extractorUri.displayName;
        }
        if ((i & 32) != 0) {
            num = extractorUri.id;
        }
        if ((i & 64) != 0) {
            num2 = extractorUri.parentId;
        }
        if ((i & 128) != 0) {
            num3 = extractorUri.episode;
        }
        if ((i & 256) != 0) {
            num4 = extractorUri.season;
        }
        if ((i & 512) != 0) {
            str5 = extractorUri.headerName;
        }
        if ((i & 1024) != 0) {
            tvType = extractorUri.tvType;
        }
        String str6 = str5;
        TvType tvType2 = tvType;
        Integer num5 = num3;
        Integer num6 = num4;
        Integer num7 = num;
        Integer num8 = num2;
        String str7 = str4;
        String str8 = str2;
        return extractorUri.copy(uri, str, str8, str3, str7, num7, num8, num5, num6, str6, tvType2);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final Uri getUri() {
        return this.uri;
    }

    /* JADX INFO: renamed from: component10, reason: from getter */
    public final String getHeaderName() {
        return this.headerName;
    }

    /* JADX INFO: renamed from: component11, reason: from getter */
    public final TvType getTvType() {
        return this.tvType;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getBasePath() {
        return this.basePath;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getRelativePath() {
        return this.relativePath;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getDisplayName() {
        return this.displayName;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final Integer getId() {
        return this.id;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final Integer getParentId() {
        return this.parentId;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final Integer getEpisode() {
        return this.episode;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final Integer getSeason() {
        return this.season;
    }

    public final ExtractorUri copy(Uri uri, String name, String basePath, String relativePath, String displayName, Integer id, Integer parentId, Integer episode, Integer season, String headerName, TvType tvType) {
        Intrinsics.checkNotNullParameter(uri, "uri");
        Intrinsics.checkNotNullParameter(name, "name");
        return new ExtractorUri(uri, name, basePath, relativePath, displayName, id, parentId, episode, season, headerName, tvType);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ExtractorUri)) {
            return false;
        }
        ExtractorUri extractorUri = (ExtractorUri) other;
        return Intrinsics.areEqual(this.uri, extractorUri.uri) && Intrinsics.areEqual(this.name, extractorUri.name) && Intrinsics.areEqual(this.basePath, extractorUri.basePath) && Intrinsics.areEqual(this.relativePath, extractorUri.relativePath) && Intrinsics.areEqual(this.displayName, extractorUri.displayName) && Intrinsics.areEqual(this.id, extractorUri.id) && Intrinsics.areEqual(this.parentId, extractorUri.parentId) && Intrinsics.areEqual(this.episode, extractorUri.episode) && Intrinsics.areEqual(this.season, extractorUri.season) && Intrinsics.areEqual(this.headerName, extractorUri.headerName) && this.tvType == extractorUri.tvType;
    }

    public int hashCode() {
        int iHashCode = ((this.uri.hashCode() * 31) + this.name.hashCode()) * 31;
        String str = this.basePath;
        int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.relativePath;
        int iHashCode3 = (iHashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.displayName;
        int iHashCode4 = (iHashCode3 + (str3 == null ? 0 : str3.hashCode())) * 31;
        Integer num = this.id;
        int iHashCode5 = (iHashCode4 + (num == null ? 0 : num.hashCode())) * 31;
        Integer num2 = this.parentId;
        int iHashCode6 = (iHashCode5 + (num2 == null ? 0 : num2.hashCode())) * 31;
        Integer num3 = this.episode;
        int iHashCode7 = (iHashCode6 + (num3 == null ? 0 : num3.hashCode())) * 31;
        Integer num4 = this.season;
        int iHashCode8 = (iHashCode7 + (num4 == null ? 0 : num4.hashCode())) * 31;
        String str4 = this.headerName;
        int iHashCode9 = (iHashCode8 + (str4 == null ? 0 : str4.hashCode())) * 31;
        TvType tvType = this.tvType;
        return iHashCode9 + (tvType != null ? tvType.hashCode() : 0);
    }

    public String toString() {
        return "ExtractorUri(uri=" + this.uri + ", name=" + this.name + ", basePath=" + this.basePath + ", relativePath=" + this.relativePath + ", displayName=" + this.displayName + ", id=" + this.id + ", parentId=" + this.parentId + ", episode=" + this.episode + ", season=" + this.season + ", headerName=" + this.headerName + ", tvType=" + this.tvType + ')';
    }

    public ExtractorUri(Uri uri, String name, String str, String str2, String str3, Integer num, Integer num2, Integer num3, Integer num4, String str4, TvType tvType) {
        Intrinsics.checkNotNullParameter(uri, "uri");
        Intrinsics.checkNotNullParameter(name, "name");
        this.uri = uri;
        this.name = name;
        this.basePath = str;
        this.relativePath = str2;
        this.displayName = str3;
        this.id = num;
        this.parentId = num2;
        this.episode = num3;
        this.season = num4;
        this.headerName = str4;
        this.tvType = tvType;
    }

    public /* synthetic */ ExtractorUri(Uri uri, String str, String str2, String str3, String str4, Integer num, Integer num2, Integer num3, Integer num4, String str5, TvType tvType, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(uri, str, (i & 4) != 0 ? null : str2, (i & 8) != 0 ? null : str3, (i & 16) != 0 ? null : str4, (i & 32) != 0 ? null : num, (i & 64) != 0 ? null : num2, (i & 128) != 0 ? null : num3, (i & 256) != 0 ? null : num4, (i & 512) != 0 ? null : str5, (i & 1024) != 0 ? null : tvType);
    }

    public final Uri getUri() {
        return this.uri;
    }

    public final String getName() {
        return this.name;
    }

    public final String getBasePath() {
        return this.basePath;
    }

    public final String getRelativePath() {
        return this.relativePath;
    }

    public final String getDisplayName() {
        return this.displayName;
    }

    public final Integer getId() {
        return this.id;
    }

    public final Integer getParentId() {
        return this.parentId;
    }

    public final Integer getEpisode() {
        return this.episode;
    }

    public final Integer getSeason() {
        return this.season;
    }

    public final String getHeaderName() {
        return this.headerName;
    }

    public final TvType getTvType() {
        return this.tvType;
    }
}
