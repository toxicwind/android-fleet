package com.lagradost.cloudstream3.ui.result;

import com.google.android.gms.common.internal.ImagesContract;
import com.lagradost.cloudstream3.IDownloadableMinimum;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ResultViewModel2.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B5\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0014\b\u0002\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u0007¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\u0015\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u0007HÆ\u0003J=\u0010\u0014\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\u0014\b\u0002\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u0007HÆ\u0001J\u0014\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018HÖ\u0083\u0004J\n\u0010\u0019\u001a\u00020\u001aHÖ\u0081\u0004J\n\u0010\u001b\u001a\u00020\u0003HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\u0004\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u0014\u0010\u0005\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000bR \u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001c"}, d2 = {"Lcom/lagradost/cloudstream3/ui/result/ExtractorSubtitleLink;", "Lcom/lagradost/cloudstream3/IDownloadableMinimum;", "name", "", ImagesContract.URL, "referer", "headers", "", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;)V", "getName", "()Ljava/lang/String;", "getUrl", "getReferer", "getHeaders", "()Ljava/util/Map;", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "", "hashCode", "", "toString", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class ExtractorSubtitleLink implements IDownloadableMinimum {
    private final Map<String, String> headers;
    private final String name;
    private final String referer;
    private final String url;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ ExtractorSubtitleLink copy$default(ExtractorSubtitleLink extractorSubtitleLink, String str, String str2, String str3, Map map, int i, Object obj) {
        if ((i & 1) != 0) {
            str = extractorSubtitleLink.name;
        }
        if ((i & 2) != 0) {
            str2 = extractorSubtitleLink.url;
        }
        if ((i & 4) != 0) {
            str3 = extractorSubtitleLink.referer;
        }
        if ((i & 8) != 0) {
            map = extractorSubtitleLink.headers;
        }
        return extractorSubtitleLink.copy(str, str2, str3, map);
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
    public final String getReferer() {
        return this.referer;
    }

    public final Map<String, String> component4() {
        return this.headers;
    }

    public final ExtractorSubtitleLink copy(String name, String url, String referer, Map<String, String> headers) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(url, "url");
        Intrinsics.checkNotNullParameter(referer, "referer");
        Intrinsics.checkNotNullParameter(headers, "headers");
        return new ExtractorSubtitleLink(name, url, referer, headers);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ExtractorSubtitleLink)) {
            return false;
        }
        ExtractorSubtitleLink extractorSubtitleLink = (ExtractorSubtitleLink) other;
        return Intrinsics.areEqual(this.name, extractorSubtitleLink.name) && Intrinsics.areEqual(this.url, extractorSubtitleLink.url) && Intrinsics.areEqual(this.referer, extractorSubtitleLink.referer) && Intrinsics.areEqual(this.headers, extractorSubtitleLink.headers);
    }

    public int hashCode() {
        return (((((this.name.hashCode() * 31) + this.url.hashCode()) * 31) + this.referer.hashCode()) * 31) + this.headers.hashCode();
    }

    public String toString() {
        return "ExtractorSubtitleLink(name=" + this.name + ", url=" + this.url + ", referer=" + this.referer + ", headers=" + this.headers + ')';
    }

    public ExtractorSubtitleLink(String name, String url, String referer, Map<String, String> headers) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(url, "url");
        Intrinsics.checkNotNullParameter(referer, "referer");
        Intrinsics.checkNotNullParameter(headers, "headers");
        this.name = name;
        this.url = url;
        this.referer = referer;
        this.headers = headers;
    }

    public final String getName() {
        return this.name;
    }

    @Override // com.lagradost.cloudstream3.IDownloadableMinimum
    public String getUrl() {
        return this.url;
    }

    @Override // com.lagradost.cloudstream3.IDownloadableMinimum
    public String getReferer() {
        return this.referer;
    }

    public /* synthetic */ ExtractorSubtitleLink(String str, String str2, String str3, Map map, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, str3, (i & 8) != 0 ? MapsKt.emptyMap() : map);
    }

    @Override // com.lagradost.cloudstream3.IDownloadableMinimum
    public Map<String, String> getHeaders() {
        return this.headers;
    }
}
