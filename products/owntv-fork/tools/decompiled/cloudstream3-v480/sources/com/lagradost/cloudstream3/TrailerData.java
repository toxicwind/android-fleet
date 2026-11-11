package com.lagradost.cloudstream3;

import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MainAPI.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010$\n\u0002\b\u0011\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B7\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0014\b\u0002\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\b¢\u0006\u0004\b\t\u0010\nJ\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0013\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0006HÆ\u0003J\u0015\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\bHÆ\u0003J?\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\u0014\b\u0002\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\bHÆ\u0001J\u0014\u0010\u0017\u001a\u00020\u00062\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0019\u001a\u00020\u001aHÖ\u0081\u0004J\n\u0010\u001b\u001a\u00020\u0003HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u001d\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\b¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u001c"}, d2 = {"Lcom/lagradost/cloudstream3/TrailerData;", "", "extractorUrl", "", "referer", "raw", "", "headers", "", "<init>", "(Ljava/lang/String;Ljava/lang/String;ZLjava/util/Map;)V", "getExtractorUrl", "()Ljava/lang/String;", "getReferer", "getRaw", "()Z", "getHeaders", "()Ljava/util/Map;", "component1", "component2", "component3", "component4", "copy", "equals", "other", "hashCode", "", "toString", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class TrailerData {
    private final String extractorUrl;
    private final Map<String, String> headers;
    private final boolean raw;
    private final String referer;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ TrailerData copy$default(TrailerData trailerData, String str, String str2, boolean z, Map map, int i, Object obj) {
        if ((i & 1) != 0) {
            str = trailerData.extractorUrl;
        }
        if ((i & 2) != 0) {
            str2 = trailerData.referer;
        }
        if ((i & 4) != 0) {
            z = trailerData.raw;
        }
        if ((i & 8) != 0) {
            map = trailerData.headers;
        }
        return trailerData.copy(str, str2, z, map);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getExtractorUrl() {
        return this.extractorUrl;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getReferer() {
        return this.referer;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final boolean getRaw() {
        return this.raw;
    }

    public final Map<String, String> component4() {
        return this.headers;
    }

    public final TrailerData copy(String extractorUrl, String referer, boolean raw, Map<String, String> headers) {
        Intrinsics.checkNotNullParameter(extractorUrl, "extractorUrl");
        Intrinsics.checkNotNullParameter(headers, "headers");
        return new TrailerData(extractorUrl, referer, raw, headers);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TrailerData)) {
            return false;
        }
        TrailerData trailerData = (TrailerData) other;
        return Intrinsics.areEqual(this.extractorUrl, trailerData.extractorUrl) && Intrinsics.areEqual(this.referer, trailerData.referer) && this.raw == trailerData.raw && Intrinsics.areEqual(this.headers, trailerData.headers);
    }

    public int hashCode() {
        int iHashCode = this.extractorUrl.hashCode() * 31;
        String str = this.referer;
        return ((((iHashCode + (str == null ? 0 : str.hashCode())) * 31) + NextAiring$$ExternalSyntheticBackport0.m(this.raw)) * 31) + this.headers.hashCode();
    }

    public String toString() {
        return "TrailerData(extractorUrl=" + this.extractorUrl + ", referer=" + this.referer + ", raw=" + this.raw + ", headers=" + this.headers + ')';
    }

    public TrailerData(String extractorUrl, String str, boolean z, Map<String, String> headers) {
        Intrinsics.checkNotNullParameter(extractorUrl, "extractorUrl");
        Intrinsics.checkNotNullParameter(headers, "headers");
        this.extractorUrl = extractorUrl;
        this.referer = str;
        this.raw = z;
        this.headers = headers;
    }

    public final String getExtractorUrl() {
        return this.extractorUrl;
    }

    public final String getReferer() {
        return this.referer;
    }

    public final boolean getRaw() {
        return this.raw;
    }

    public /* synthetic */ TrailerData(String str, String str2, boolean z, Map map, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, z, (i & 8) != 0 ? MapsKt.emptyMap() : map);
    }

    public final Map<String, String> getHeaders() {
        return this.headers;
    }
}
