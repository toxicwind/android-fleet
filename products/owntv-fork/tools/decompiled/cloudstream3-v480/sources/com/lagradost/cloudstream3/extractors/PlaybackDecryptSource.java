package com.lagradost.cloudstream3.extractors;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.android.gms.common.internal.ImagesContract;
import com.lagradost.cloudstream3.NextAiring$$ExternalSyntheticBackport0;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ByseSX.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0014\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B=\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0007\u001a\u00020\b\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0001¢\u0006\u0004\b\n\u0010\u000bJ\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0019\u001a\u00020\bHÆ\u0003J\u000b\u0010\u001a\u001a\u0004\u0018\u00010\u0001HÆ\u0003JG\u0010\u001b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0003\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0003\u0010\u0007\u001a\u00020\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0001HÆ\u0001J\u0014\u0010\u001c\u001a\u00020\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u001f\u001a\u00020 HÖ\u0081\u0004J\n\u0010!\u001a\u00020\u0003HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0016\u0010\u0005\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\rR\u0016\u0010\u0007\u001a\u00020\b8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0013\u0010\t\u001a\u0004\u0018\u00010\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014¨\u0006\""}, d2 = {"Lcom/lagradost/cloudstream3/extractors/PlaybackDecryptSource;", "", "quality", "", "label", "mimeType", ImagesContract.URL, "bitrateKbps", "", "height", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;JLjava/lang/Object;)V", "getQuality", "()Ljava/lang/String;", "getLabel", "getMimeType", "getUrl", "getBitrateKbps", "()J", "getHeight", "()Ljava/lang/Object;", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "hashCode", "", "toString", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class PlaybackDecryptSource {

    @JsonProperty("bitrate_kbps")
    private final long bitrateKbps;
    private final Object height;
    private final String label;

    @JsonProperty("mime_type")
    private final String mimeType;
    private final String quality;
    private final String url;

    public static /* synthetic */ PlaybackDecryptSource copy$default(PlaybackDecryptSource playbackDecryptSource, String str, String str2, String str3, String str4, long j, Object obj, int i, Object obj2) {
        if ((i & 1) != 0) {
            str = playbackDecryptSource.quality;
        }
        if ((i & 2) != 0) {
            str2 = playbackDecryptSource.label;
        }
        if ((i & 4) != 0) {
            str3 = playbackDecryptSource.mimeType;
        }
        if ((i & 8) != 0) {
            str4 = playbackDecryptSource.url;
        }
        if ((i & 16) != 0) {
            j = playbackDecryptSource.bitrateKbps;
        }
        if ((i & 32) != 0) {
            obj = playbackDecryptSource.height;
        }
        Object obj3 = obj;
        long j2 = j;
        return playbackDecryptSource.copy(str, str2, str3, str4, j2, obj3);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getQuality() {
        return this.quality;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getLabel() {
        return this.label;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getMimeType() {
        return this.mimeType;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getUrl() {
        return this.url;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final long getBitrateKbps() {
        return this.bitrateKbps;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final Object getHeight() {
        return this.height;
    }

    public final PlaybackDecryptSource copy(String quality, String label, @JsonProperty("mime_type") String mimeType, String url, @JsonProperty("bitrate_kbps") long bitrateKbps, Object height) {
        Intrinsics.checkNotNullParameter(quality, "quality");
        Intrinsics.checkNotNullParameter(label, "label");
        Intrinsics.checkNotNullParameter(mimeType, "mimeType");
        Intrinsics.checkNotNullParameter(url, "url");
        return new PlaybackDecryptSource(quality, label, mimeType, url, bitrateKbps, height);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PlaybackDecryptSource)) {
            return false;
        }
        PlaybackDecryptSource playbackDecryptSource = (PlaybackDecryptSource) other;
        return Intrinsics.areEqual(this.quality, playbackDecryptSource.quality) && Intrinsics.areEqual(this.label, playbackDecryptSource.label) && Intrinsics.areEqual(this.mimeType, playbackDecryptSource.mimeType) && Intrinsics.areEqual(this.url, playbackDecryptSource.url) && this.bitrateKbps == playbackDecryptSource.bitrateKbps && Intrinsics.areEqual(this.height, playbackDecryptSource.height);
    }

    public int hashCode() {
        int iHashCode = ((((((((this.quality.hashCode() * 31) + this.label.hashCode()) * 31) + this.mimeType.hashCode()) * 31) + this.url.hashCode()) * 31) + NextAiring$$ExternalSyntheticBackport0.m(this.bitrateKbps)) * 31;
        Object obj = this.height;
        return iHashCode + (obj == null ? 0 : obj.hashCode());
    }

    public String toString() {
        return "PlaybackDecryptSource(quality=" + this.quality + ", label=" + this.label + ", mimeType=" + this.mimeType + ", url=" + this.url + ", bitrateKbps=" + this.bitrateKbps + ", height=" + this.height + ')';
    }

    public PlaybackDecryptSource(String quality, String label, @JsonProperty("mime_type") String mimeType, String url, @JsonProperty("bitrate_kbps") long j, Object obj) {
        Intrinsics.checkNotNullParameter(quality, "quality");
        Intrinsics.checkNotNullParameter(label, "label");
        Intrinsics.checkNotNullParameter(mimeType, "mimeType");
        Intrinsics.checkNotNullParameter(url, "url");
        this.quality = quality;
        this.label = label;
        this.mimeType = mimeType;
        this.url = url;
        this.bitrateKbps = j;
        this.height = obj;
    }

    public final String getQuality() {
        return this.quality;
    }

    public final String getLabel() {
        return this.label;
    }

    public final String getMimeType() {
        return this.mimeType;
    }

    public final String getUrl() {
        return this.url;
    }

    public final long getBitrateKbps() {
        return this.bitrateKbps;
    }

    public final Object getHeight() {
        return this.height;
    }
}
