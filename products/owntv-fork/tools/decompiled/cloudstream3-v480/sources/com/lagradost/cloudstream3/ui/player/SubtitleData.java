package com.lagradost.cloudstream3.ui.player;

import com.google.android.gms.common.internal.ImagesContract;
import com.lagradost.cloudstream3.utils.SubtitleHelper;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: PlayerSubtitleHelper.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0010\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001BM\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\n\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\f\u0010\rJ\u0006\u0010\u0018\u001a\u00020\u0003J\u000e\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u0003J\b\u0010\u001c\u001a\u0004\u0018\u00010\u0003J\u0006\u0010\u001f\u001a\u00020\u0003J\t\u0010 \u001a\u00020\u0003HÆ\u0003J\t\u0010!\u001a\u00020\u0003HÆ\u0003J\t\u0010\"\u001a\u00020\u0003HÆ\u0003J\t\u0010#\u001a\u00020\u0007HÆ\u0003J\t\u0010$\u001a\u00020\u0003HÆ\u0003J\u0015\u0010%\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\nHÆ\u0003J\u000b\u0010&\u001a\u0004\u0018\u00010\u0003HÆ\u0003J]\u0010'\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00032\u0014\b\u0002\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\n2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0014\u0010(\u001a\u00020\u001a2\b\u0010)\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010*\u001a\u00020+HÖ\u0081\u0004J\n\u0010,\u001a\u00020\u0003HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000fR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000fR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u000fR\u001d\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\n¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0013\u0010\u000b\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u000fR\u0011\u0010\u001d\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u000f¨\u0006-"}, d2 = {"Lcom/lagradost/cloudstream3/ui/player/SubtitleData;", "", "originalName", "", "nameSuffix", ImagesContract.URL, "origin", "Lcom/lagradost/cloudstream3/ui/player/SubtitleOrigin;", "mimeType", "headers", "", "languageCode", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/lagradost/cloudstream3/ui/player/SubtitleOrigin;Ljava/lang/String;Ljava/util/Map;Ljava/lang/String;)V", "getOriginalName", "()Ljava/lang/String;", "getNameSuffix", "getUrl", "getOrigin", "()Lcom/lagradost/cloudstream3/ui/player/SubtitleOrigin;", "getMimeType", "getHeaders", "()Ljava/util/Map;", "getLanguageCode", "getId", "matchesLanguageCode", "", "langCode", "getIETF_tag", "name", "getName", "getFixedUrl", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "other", "hashCode", "", "toString", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class SubtitleData {
    private final Map<String, String> headers;
    private final String languageCode;
    private final String mimeType;
    private final String name;
    private final String nameSuffix;
    private final SubtitleOrigin origin;
    private final String originalName;
    private final String url;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ SubtitleData copy$default(SubtitleData subtitleData, String str, String str2, String str3, SubtitleOrigin subtitleOrigin, String str4, Map map, String str5, int i, Object obj) {
        if ((i & 1) != 0) {
            str = subtitleData.originalName;
        }
        if ((i & 2) != 0) {
            str2 = subtitleData.nameSuffix;
        }
        if ((i & 4) != 0) {
            str3 = subtitleData.url;
        }
        if ((i & 8) != 0) {
            subtitleOrigin = subtitleData.origin;
        }
        if ((i & 16) != 0) {
            str4 = subtitleData.mimeType;
        }
        if ((i & 32) != 0) {
            map = subtitleData.headers;
        }
        if ((i & 64) != 0) {
            str5 = subtitleData.languageCode;
        }
        Map map2 = map;
        String str6 = str5;
        String str7 = str4;
        String str8 = str3;
        return subtitleData.copy(str, str2, str8, subtitleOrigin, str7, map2, str6);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getOriginalName() {
        return this.originalName;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getNameSuffix() {
        return this.nameSuffix;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getUrl() {
        return this.url;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final SubtitleOrigin getOrigin() {
        return this.origin;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getMimeType() {
        return this.mimeType;
    }

    public final Map<String, String> component6() {
        return this.headers;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final String getLanguageCode() {
        return this.languageCode;
    }

    public final SubtitleData copy(String originalName, String nameSuffix, String url, SubtitleOrigin origin, String mimeType, Map<String, String> headers, String languageCode) {
        Intrinsics.checkNotNullParameter(originalName, "originalName");
        Intrinsics.checkNotNullParameter(nameSuffix, "nameSuffix");
        Intrinsics.checkNotNullParameter(url, "url");
        Intrinsics.checkNotNullParameter(origin, "origin");
        Intrinsics.checkNotNullParameter(mimeType, "mimeType");
        Intrinsics.checkNotNullParameter(headers, "headers");
        return new SubtitleData(originalName, nameSuffix, url, origin, mimeType, headers, languageCode);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SubtitleData)) {
            return false;
        }
        SubtitleData subtitleData = (SubtitleData) other;
        return Intrinsics.areEqual(this.originalName, subtitleData.originalName) && Intrinsics.areEqual(this.nameSuffix, subtitleData.nameSuffix) && Intrinsics.areEqual(this.url, subtitleData.url) && this.origin == subtitleData.origin && Intrinsics.areEqual(this.mimeType, subtitleData.mimeType) && Intrinsics.areEqual(this.headers, subtitleData.headers) && Intrinsics.areEqual(this.languageCode, subtitleData.languageCode);
    }

    public int hashCode() {
        int iHashCode = ((((((((((this.originalName.hashCode() * 31) + this.nameSuffix.hashCode()) * 31) + this.url.hashCode()) * 31) + this.origin.hashCode()) * 31) + this.mimeType.hashCode()) * 31) + this.headers.hashCode()) * 31;
        String str = this.languageCode;
        return iHashCode + (str == null ? 0 : str.hashCode());
    }

    public String toString() {
        return "SubtitleData(originalName=" + this.originalName + ", nameSuffix=" + this.nameSuffix + ", url=" + this.url + ", origin=" + this.origin + ", mimeType=" + this.mimeType + ", headers=" + this.headers + ", languageCode=" + this.languageCode + ')';
    }

    public SubtitleData(String originalName, String nameSuffix, String url, SubtitleOrigin origin, String mimeType, Map<String, String> headers, String str) {
        Intrinsics.checkNotNullParameter(originalName, "originalName");
        Intrinsics.checkNotNullParameter(nameSuffix, "nameSuffix");
        Intrinsics.checkNotNullParameter(url, "url");
        Intrinsics.checkNotNullParameter(origin, "origin");
        Intrinsics.checkNotNullParameter(mimeType, "mimeType");
        Intrinsics.checkNotNullParameter(headers, "headers");
        this.originalName = originalName;
        this.nameSuffix = nameSuffix;
        this.url = url;
        this.origin = origin;
        this.mimeType = mimeType;
        this.headers = headers;
        this.languageCode = str;
        this.name = originalName + ' ' + nameSuffix;
    }

    public final String getOriginalName() {
        return this.originalName;
    }

    public final String getNameSuffix() {
        return this.nameSuffix;
    }

    public final String getUrl() {
        return this.url;
    }

    public final SubtitleOrigin getOrigin() {
        return this.origin;
    }

    public final String getMimeType() {
        return this.mimeType;
    }

    public final Map<String, String> getHeaders() {
        return this.headers;
    }

    public final String getLanguageCode() {
        return this.languageCode;
    }

    public final String getId() {
        SubtitleOrigin subtitleOrigin = this.origin;
        SubtitleOrigin subtitleOrigin2 = SubtitleOrigin.EMBEDDED_IN_VIDEO;
        String str = this.url;
        if (subtitleOrigin == subtitleOrigin2) {
            return str;
        }
        return str + '|' + this.name;
    }

    public final boolean matchesLanguageCode(String langCode) {
        Intrinsics.checkNotNullParameter(langCode, "langCode");
        return Intrinsics.areEqual(getIETF_tag(), langCode);
    }

    public final String getIETF_tag() {
        String strFromLanguageToTagIETF$default = SubtitleHelper.fromLanguageToTagIETF$default(SubtitleHelper.INSTANCE, this.languageCode, null, 2, null);
        return strFromLanguageToTagIETF$default == null ? SubtitleHelper.INSTANCE.fromLanguageToTagIETF(this.originalName, true) : strFromLanguageToTagIETF$default;
    }

    public final String getName() {
        return this.name;
    }

    public final String getFixedUrl() {
        boolean zStartsWith$default = StringsKt.startsWith$default(this.url, "//", false, 2, (Object) null);
        String str = this.url;
        if (!zStartsWith$default) {
            return str;
        }
        return "https:" + str;
    }
}
