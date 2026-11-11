package com.lagradost.cloudstream3.ui.result;

import kotlin.Metadata;

/* JADX INFO: compiled from: ResultViewModel2.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J1\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0016\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010\u0017\u001a\u00020\u0018HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\nR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\n¨\u0006\u0019"}, d2 = {"Lcom/lagradost/cloudstream3/ui/result/EpisodeRange;", "", "startIndex", "", "length", "startEpisode", "endEpisode", "<init>", "(IIII)V", "getStartIndex", "()I", "getLength", "getStartEpisode", "getEndEpisode", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "toString", "", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class EpisodeRange {
    private final int endEpisode;
    private final int length;
    private final int startEpisode;
    private final int startIndex;

    public static /* synthetic */ EpisodeRange copy$default(EpisodeRange episodeRange, int i, int i2, int i3, int i4, int i5, Object obj) {
        if ((i5 & 1) != 0) {
            i = episodeRange.startIndex;
        }
        if ((i5 & 2) != 0) {
            i2 = episodeRange.length;
        }
        if ((i5 & 4) != 0) {
            i3 = episodeRange.startEpisode;
        }
        if ((i5 & 8) != 0) {
            i4 = episodeRange.endEpisode;
        }
        return episodeRange.copy(i, i2, i3, i4);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final int getStartIndex() {
        return this.startIndex;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final int getLength() {
        return this.length;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final int getStartEpisode() {
        return this.startEpisode;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final int getEndEpisode() {
        return this.endEpisode;
    }

    public final EpisodeRange copy(int startIndex, int length, int startEpisode, int endEpisode) {
        return new EpisodeRange(startIndex, length, startEpisode, endEpisode);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof EpisodeRange)) {
            return false;
        }
        EpisodeRange episodeRange = (EpisodeRange) other;
        return this.startIndex == episodeRange.startIndex && this.length == episodeRange.length && this.startEpisode == episodeRange.startEpisode && this.endEpisode == episodeRange.endEpisode;
    }

    public int hashCode() {
        return (((((this.startIndex * 31) + this.length) * 31) + this.startEpisode) * 31) + this.endEpisode;
    }

    public String toString() {
        return "EpisodeRange(startIndex=" + this.startIndex + ", length=" + this.length + ", startEpisode=" + this.startEpisode + ", endEpisode=" + this.endEpisode + ')';
    }

    public EpisodeRange(int i, int i2, int i3, int i4) {
        this.startIndex = i;
        this.length = i2;
        this.startEpisode = i3;
        this.endEpisode = i4;
    }

    public final int getStartIndex() {
        return this.startIndex;
    }

    public final int getLength() {
        return this.length;
    }

    public final int getStartEpisode() {
        return this.startEpisode;
    }

    public final int getEndEpisode() {
        return this.endEpisode;
    }
}
