package com.lagradost.cloudstream3.ui;

import com.lagradost.cloudstream3.NextAiring$$ExternalSyntheticBackport0;
import com.lagradost.cloudstream3.ui.player.SubtitleData;
import com.lagradost.cloudstream3.ui.result.ResultEpisode;
import com.lagradost.cloudstream3.utils.ExtractorLink;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ControllerActivity.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u001b\b\u0086\b\u0018\u00002\u00020\u0001B]\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\b\u001a\u00020\t\u0012\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b\u0012\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\u000b\u0012\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\u000b¢\u0006\u0004\b\u0011\u0010\u0012J\t\u0010\u001e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001f\u001a\u00020\u0005HÆ\u0003J\u000b\u0010 \u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010!\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010\"\u001a\u00020\tHÆ\u0003J\u000f\u0010#\u001a\b\u0012\u0004\u0012\u00020\f0\u000bHÆ\u0003J\u000f\u0010$\u001a\b\u0012\u0004\u0012\u00020\u000e0\u000bHÆ\u0003J\u000f\u0010%\u001a\b\u0012\u0004\u0012\u00020\u00100\u000bHÆ\u0003Jo\u0010&\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\b\u001a\u00020\t2\u000e\b\u0002\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\u000e\b\u0002\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\u000b2\u000e\b\u0002\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\u000bHÆ\u0001J\u0014\u0010'\u001a\u00020\u00052\b\u0010(\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010)\u001a\u00020\tHÖ\u0081\u0004J\n\u0010*\u001a\u00020\u0003HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\u0015R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0014R\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0014R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0017\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001bR\u0017\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001b¨\u0006+"}, d2 = {"Lcom/lagradost/cloudstream3/ui/MetadataHolder;", "", "apiName", "", "isMovie", "", "title", "poster", "currentEpisodeIndex", "", "episodes", "", "Lcom/lagradost/cloudstream3/ui/result/ResultEpisode;", "currentLinks", "Lcom/lagradost/cloudstream3/utils/ExtractorLink;", "currentSubtitles", "Lcom/lagradost/cloudstream3/ui/player/SubtitleData;", "<init>", "(Ljava/lang/String;ZLjava/lang/String;Ljava/lang/String;ILjava/util/List;Ljava/util/List;Ljava/util/List;)V", "getApiName", "()Ljava/lang/String;", "()Z", "getTitle", "getPoster", "getCurrentEpisodeIndex", "()I", "getEpisodes", "()Ljava/util/List;", "getCurrentLinks", "getCurrentSubtitles", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "equals", "other", "hashCode", "toString", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class MetadataHolder {
    private final String apiName;
    private final int currentEpisodeIndex;
    private final List<ExtractorLink> currentLinks;
    private final List<SubtitleData> currentSubtitles;
    private final List<ResultEpisode> episodes;
    private final boolean isMovie;
    private final String poster;
    private final String title;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ MetadataHolder copy$default(MetadataHolder metadataHolder, String str, boolean z, String str2, String str3, int i, List list, List list2, List list3, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = metadataHolder.apiName;
        }
        if ((i2 & 2) != 0) {
            z = metadataHolder.isMovie;
        }
        if ((i2 & 4) != 0) {
            str2 = metadataHolder.title;
        }
        if ((i2 & 8) != 0) {
            str3 = metadataHolder.poster;
        }
        if ((i2 & 16) != 0) {
            i = metadataHolder.currentEpisodeIndex;
        }
        if ((i2 & 32) != 0) {
            list = metadataHolder.episodes;
        }
        if ((i2 & 64) != 0) {
            list2 = metadataHolder.currentLinks;
        }
        if ((i2 & 128) != 0) {
            list3 = metadataHolder.currentSubtitles;
        }
        List list4 = list2;
        List list5 = list3;
        int i3 = i;
        List list6 = list;
        return metadataHolder.copy(str, z, str2, str3, i3, list6, list4, list5);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getApiName() {
        return this.apiName;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final boolean getIsMovie() {
        return this.isMovie;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getTitle() {
        return this.title;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getPoster() {
        return this.poster;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final int getCurrentEpisodeIndex() {
        return this.currentEpisodeIndex;
    }

    public final List<ResultEpisode> component6() {
        return this.episodes;
    }

    public final List<ExtractorLink> component7() {
        return this.currentLinks;
    }

    public final List<SubtitleData> component8() {
        return this.currentSubtitles;
    }

    public final MetadataHolder copy(String apiName, boolean isMovie, String title, String poster, int currentEpisodeIndex, List<ResultEpisode> episodes, List<? extends ExtractorLink> currentLinks, List<SubtitleData> currentSubtitles) {
        Intrinsics.checkNotNullParameter(apiName, "apiName");
        Intrinsics.checkNotNullParameter(episodes, "episodes");
        Intrinsics.checkNotNullParameter(currentLinks, "currentLinks");
        Intrinsics.checkNotNullParameter(currentSubtitles, "currentSubtitles");
        return new MetadataHolder(apiName, isMovie, title, poster, currentEpisodeIndex, episodes, currentLinks, currentSubtitles);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof MetadataHolder)) {
            return false;
        }
        MetadataHolder metadataHolder = (MetadataHolder) other;
        return Intrinsics.areEqual(this.apiName, metadataHolder.apiName) && this.isMovie == metadataHolder.isMovie && Intrinsics.areEqual(this.title, metadataHolder.title) && Intrinsics.areEqual(this.poster, metadataHolder.poster) && this.currentEpisodeIndex == metadataHolder.currentEpisodeIndex && Intrinsics.areEqual(this.episodes, metadataHolder.episodes) && Intrinsics.areEqual(this.currentLinks, metadataHolder.currentLinks) && Intrinsics.areEqual(this.currentSubtitles, metadataHolder.currentSubtitles);
    }

    public int hashCode() {
        int iHashCode = ((this.apiName.hashCode() * 31) + NextAiring$$ExternalSyntheticBackport0.m(this.isMovie)) * 31;
        String str = this.title;
        int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.poster;
        return ((((((((iHashCode2 + (str2 != null ? str2.hashCode() : 0)) * 31) + this.currentEpisodeIndex) * 31) + this.episodes.hashCode()) * 31) + this.currentLinks.hashCode()) * 31) + this.currentSubtitles.hashCode();
    }

    public String toString() {
        return "MetadataHolder(apiName=" + this.apiName + ", isMovie=" + this.isMovie + ", title=" + this.title + ", poster=" + this.poster + ", currentEpisodeIndex=" + this.currentEpisodeIndex + ", episodes=" + this.episodes + ", currentLinks=" + this.currentLinks + ", currentSubtitles=" + this.currentSubtitles + ')';
    }

    /* JADX WARN: Multi-variable type inference failed */
    public MetadataHolder(String apiName, boolean z, String str, String str2, int i, List<ResultEpisode> episodes, List<? extends ExtractorLink> currentLinks, List<SubtitleData> currentSubtitles) {
        Intrinsics.checkNotNullParameter(apiName, "apiName");
        Intrinsics.checkNotNullParameter(episodes, "episodes");
        Intrinsics.checkNotNullParameter(currentLinks, "currentLinks");
        Intrinsics.checkNotNullParameter(currentSubtitles, "currentSubtitles");
        this.apiName = apiName;
        this.isMovie = z;
        this.title = str;
        this.poster = str2;
        this.currentEpisodeIndex = i;
        this.episodes = episodes;
        this.currentLinks = currentLinks;
        this.currentSubtitles = currentSubtitles;
    }

    public final String getApiName() {
        return this.apiName;
    }

    public final boolean isMovie() {
        return this.isMovie;
    }

    public final String getTitle() {
        return this.title;
    }

    public final String getPoster() {
        return this.poster;
    }

    public final int getCurrentEpisodeIndex() {
        return this.currentEpisodeIndex;
    }

    public final List<ResultEpisode> getEpisodes() {
        return this.episodes;
    }

    public final List<ExtractorLink> getCurrentLinks() {
        return this.currentLinks;
    }

    public final List<SubtitleData> getCurrentSubtitles() {
        return this.currentSubtitles;
    }
}
