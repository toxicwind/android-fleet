package com.lagradost.cloudstream3.ui.result;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.lagradost.cloudstream3.NextAiring$$ExternalSyntheticBackport0;
import com.lagradost.cloudstream3.Score;
import com.lagradost.cloudstream3.SeasonData;
import com.lagradost.cloudstream3.TvType;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ResultFragment.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\bA\b\u0086\b\u0018\u00002\u00020\u0001BÕ\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0007\u0012\u0006\u0010\n\u001a\u00020\u0003\u0012\u0006\u0010\u000b\u001a\u00020\u0003\u0012\u0006\u0010\f\u001a\u00020\u0007\u0012\u0006\u0010\r\u001a\u00020\u0007\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u000f\u0012\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012\u0012\b\u0010\u0013\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015\u0012\u0006\u0010\u0016\u001a\u00020\u0017\u0012\u0006\u0010\u0018\u001a\u00020\u0007\u0012\u0006\u0010\u0019\u001a\u00020\u001a\u0012\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u000f\u0012\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u001f¢\u0006\u0004\b \u0010!J\t\u0010D\u001a\u00020\u0003HÆ\u0003J\u000b\u0010E\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010F\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010G\u001a\u00020\u0007HÆ\u0003J\u0010\u0010H\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u0010)J\u0010\u0010I\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u0010)J\t\u0010J\u001a\u00020\u0003HÆ\u0003J\t\u0010K\u001a\u00020\u0003HÆ\u0003J\t\u0010L\u001a\u00020\u0007HÆ\u0003J\t\u0010M\u001a\u00020\u0007HÆ\u0003J\t\u0010N\u001a\u00020\u000fHÆ\u0003J\t\u0010O\u001a\u00020\u000fHÆ\u0003J\u000b\u0010P\u001a\u0004\u0018\u00010\u0012HÆ\u0003J\u000b\u0010Q\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010R\u001a\u0004\u0018\u00010\u0015HÆ\u0003¢\u0006\u0002\u00106J\t\u0010S\u001a\u00020\u0017HÆ\u0003J\t\u0010T\u001a\u00020\u0007HÆ\u0003J\t\u0010U\u001a\u00020\u001aHÆ\u0003J\u0010\u0010V\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u0010)J\u0010\u0010W\u001a\u0004\u0018\u00010\u000fHÆ\u0003¢\u0006\u0002\u0010?J\u0010\u0010X\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u0010)J\u000b\u0010Y\u001a\u0004\u0018\u00010\u001fHÆ\u0003J\u0080\u0002\u0010Z\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00072\b\b\u0002\u0010\n\u001a\u00020\u00032\b\b\u0002\u0010\u000b\u001a\u00020\u00032\b\b\u0002\u0010\f\u001a\u00020\u00072\b\b\u0002\u0010\r\u001a\u00020\u00072\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u000f2\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00122\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u00152\b\b\u0002\u0010\u0016\u001a\u00020\u00172\b\b\u0002\u0010\u0018\u001a\u00020\u00072\b\b\u0002\u0010\u0019\u001a\u00020\u001a2\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u000f2\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u001fHÆ\u0001¢\u0006\u0002\u0010[J\u0014\u0010\\\u001a\u00020\u00152\b\u0010]\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010^\u001a\u00020\u0007HÖ\u0081\u0004J\n\u0010_\u001a\u00020\u0003HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010#R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b$\u0010#R\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b%\u0010#R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b&\u0010'R\u0015\u0010\b\u001a\u0004\u0018\u00010\u0007¢\u0006\n\n\u0002\u0010*\u001a\u0004\b(\u0010)R\u0015\u0010\t\u001a\u0004\u0018\u00010\u0007¢\u0006\n\n\u0002\u0010*\u001a\u0004\b+\u0010)R\u0011\u0010\n\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b,\u0010#R\u0011\u0010\u000b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b-\u0010#R\u0011\u0010\f\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b.\u0010'R\u0011\u0010\r\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b/\u0010'R\u0011\u0010\u000e\u001a\u00020\u000f¢\u0006\b\n\u0000\u001a\u0004\b0\u00101R\u0011\u0010\u0010\u001a\u00020\u000f¢\u0006\b\n\u0000\u001a\u0004\b2\u00101R\u0013\u0010\u0011\u001a\u0004\u0018\u00010\u0012¢\u0006\b\n\u0000\u001a\u0004\b3\u00104R\u0013\u0010\u0013\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b5\u0010#R\u0015\u0010\u0014\u001a\u0004\u0018\u00010\u0015¢\u0006\n\n\u0002\u00107\u001a\u0004\b\u0014\u00106R\u0011\u0010\u0016\u001a\u00020\u0017¢\u0006\b\n\u0000\u001a\u0004\b8\u00109R\u0011\u0010\u0018\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b:\u0010'R\u0011\u0010\u0019\u001a\u00020\u001a¢\u0006\b\n\u0000\u001a\u0004\b;\u0010<R\u0015\u0010\u001b\u001a\u0004\u0018\u00010\u0007¢\u0006\n\n\u0002\u0010*\u001a\u0004\b=\u0010)R\u0015\u0010\u001c\u001a\u0004\u0018\u00010\u000f¢\u0006\n\n\u0002\u0010@\u001a\u0004\b>\u0010?R\u0015\u0010\u001d\u001a\u0004\u0018\u00010\u0007¢\u0006\n\n\u0002\u0010*\u001a\u0004\bA\u0010)R\u0013\u0010\u001e\u001a\u0004\u0018\u00010\u001f¢\u0006\b\n\u0000\u001a\u0004\bB\u0010C¨\u0006`"}, d2 = {"Lcom/lagradost/cloudstream3/ui/result/ResultEpisode;", "", "headerName", "", "name", "poster", "episode", "", "seasonIndex", "season", "data", "apiName", "id", "index", "position", "", TypedValues.TransitionType.S_DURATION, "score", "Lcom/lagradost/cloudstream3/Score;", "description", "isFiller", "", "tvType", "Lcom/lagradost/cloudstream3/TvType;", "parentId", "videoWatchState", "Lcom/lagradost/cloudstream3/ui/result/VideoWatchState;", "totalEpisodeIndex", "airDate", "runTime", "seasonData", "Lcom/lagradost/cloudstream3/SeasonData;", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;IIJJLcom/lagradost/cloudstream3/Score;Ljava/lang/String;Ljava/lang/Boolean;Lcom/lagradost/cloudstream3/TvType;ILcom/lagradost/cloudstream3/ui/result/VideoWatchState;Ljava/lang/Integer;Ljava/lang/Long;Ljava/lang/Integer;Lcom/lagradost/cloudstream3/SeasonData;)V", "getHeaderName", "()Ljava/lang/String;", "getName", "getPoster", "getEpisode", "()I", "getSeasonIndex", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getSeason", "getData", "getApiName", "getId", "getIndex", "getPosition", "()J", "getDuration", "getScore", "()Lcom/lagradost/cloudstream3/Score;", "getDescription", "()Ljava/lang/Boolean;", "Ljava/lang/Boolean;", "getTvType", "()Lcom/lagradost/cloudstream3/TvType;", "getParentId", "getVideoWatchState", "()Lcom/lagradost/cloudstream3/ui/result/VideoWatchState;", "getTotalEpisodeIndex", "getAirDate", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getRunTime", "getSeasonData", "()Lcom/lagradost/cloudstream3/SeasonData;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "component20", "component21", "component22", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;IIJJLcom/lagradost/cloudstream3/Score;Ljava/lang/String;Ljava/lang/Boolean;Lcom/lagradost/cloudstream3/TvType;ILcom/lagradost/cloudstream3/ui/result/VideoWatchState;Ljava/lang/Integer;Ljava/lang/Long;Ljava/lang/Integer;Lcom/lagradost/cloudstream3/SeasonData;)Lcom/lagradost/cloudstream3/ui/result/ResultEpisode;", "equals", "other", "hashCode", "toString", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class ResultEpisode {
    private final Long airDate;
    private final String apiName;
    private final String data;
    private final String description;
    private final long duration;
    private final int episode;
    private final String headerName;
    private final int id;
    private final int index;
    private final Boolean isFiller;
    private final String name;
    private final int parentId;
    private final long position;
    private final String poster;
    private final Integer runTime;
    private final Score score;
    private final Integer season;
    private final SeasonData seasonData;
    private final Integer seasonIndex;
    private final Integer totalEpisodeIndex;
    private final TvType tvType;
    private final VideoWatchState videoWatchState;

    public static /* synthetic */ ResultEpisode copy$default(ResultEpisode resultEpisode, String str, String str2, String str3, int i, Integer num, Integer num2, String str4, String str5, int i2, int i3, long j, long j2, Score score, String str6, Boolean bool, TvType tvType, int i4, VideoWatchState videoWatchState, Integer num3, Long l, Integer num4, SeasonData seasonData, int i5, Object obj) {
        SeasonData seasonData2;
        Integer num5;
        String str7 = (i5 & 1) != 0 ? resultEpisode.headerName : str;
        String str8 = (i5 & 2) != 0 ? resultEpisode.name : str2;
        String str9 = (i5 & 4) != 0 ? resultEpisode.poster : str3;
        int i6 = (i5 & 8) != 0 ? resultEpisode.episode : i;
        Integer num6 = (i5 & 16) != 0 ? resultEpisode.seasonIndex : num;
        Integer num7 = (i5 & 32) != 0 ? resultEpisode.season : num2;
        String str10 = (i5 & 64) != 0 ? resultEpisode.data : str4;
        String str11 = (i5 & 128) != 0 ? resultEpisode.apiName : str5;
        int i7 = (i5 & 256) != 0 ? resultEpisode.id : i2;
        int i8 = (i5 & 512) != 0 ? resultEpisode.index : i3;
        long j3 = (i5 & 1024) != 0 ? resultEpisode.position : j;
        long j4 = (i5 & 2048) != 0 ? resultEpisode.duration : j2;
        String str12 = str7;
        Score score2 = (i5 & 4096) != 0 ? resultEpisode.score : score;
        String str13 = (i5 & 8192) != 0 ? resultEpisode.description : str6;
        Boolean bool2 = (i5 & 16384) != 0 ? resultEpisode.isFiller : bool;
        TvType tvType2 = (i5 & 32768) != 0 ? resultEpisode.tvType : tvType;
        int i9 = (i5 & 65536) != 0 ? resultEpisode.parentId : i4;
        VideoWatchState videoWatchState2 = (i5 & 131072) != 0 ? resultEpisode.videoWatchState : videoWatchState;
        Integer num8 = (i5 & 262144) != 0 ? resultEpisode.totalEpisodeIndex : num3;
        Long l2 = (i5 & 524288) != 0 ? resultEpisode.airDate : l;
        Integer num9 = (i5 & 1048576) != 0 ? resultEpisode.runTime : num4;
        if ((i5 & 2097152) != 0) {
            num5 = num9;
            seasonData2 = resultEpisode.seasonData;
        } else {
            seasonData2 = seasonData;
            num5 = num9;
        }
        return resultEpisode.copy(str12, str8, str9, i6, num6, num7, str10, str11, i7, i8, j3, j4, score2, str13, bool2, tvType2, i9, videoWatchState2, num8, l2, num5, seasonData2);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getHeaderName() {
        return this.headerName;
    }

    /* JADX INFO: renamed from: component10, reason: from getter */
    public final int getIndex() {
        return this.index;
    }

    /* JADX INFO: renamed from: component11, reason: from getter */
    public final long getPosition() {
        return this.position;
    }

    /* JADX INFO: renamed from: component12, reason: from getter */
    public final long getDuration() {
        return this.duration;
    }

    /* JADX INFO: renamed from: component13, reason: from getter */
    public final Score getScore() {
        return this.score;
    }

    /* JADX INFO: renamed from: component14, reason: from getter */
    public final String getDescription() {
        return this.description;
    }

    /* JADX INFO: renamed from: component15, reason: from getter */
    public final Boolean getIsFiller() {
        return this.isFiller;
    }

    /* JADX INFO: renamed from: component16, reason: from getter */
    public final TvType getTvType() {
        return this.tvType;
    }

    /* JADX INFO: renamed from: component17, reason: from getter */
    public final int getParentId() {
        return this.parentId;
    }

    /* JADX INFO: renamed from: component18, reason: from getter */
    public final VideoWatchState getVideoWatchState() {
        return this.videoWatchState;
    }

    /* JADX INFO: renamed from: component19, reason: from getter */
    public final Integer getTotalEpisodeIndex() {
        return this.totalEpisodeIndex;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* JADX INFO: renamed from: component20, reason: from getter */
    public final Long getAirDate() {
        return this.airDate;
    }

    /* JADX INFO: renamed from: component21, reason: from getter */
    public final Integer getRunTime() {
        return this.runTime;
    }

    /* JADX INFO: renamed from: component22, reason: from getter */
    public final SeasonData getSeasonData() {
        return this.seasonData;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getPoster() {
        return this.poster;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final int getEpisode() {
        return this.episode;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final Integer getSeasonIndex() {
        return this.seasonIndex;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final Integer getSeason() {
        return this.season;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final String getData() {
        return this.data;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final String getApiName() {
        return this.apiName;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final int getId() {
        return this.id;
    }

    public final ResultEpisode copy(String headerName, String name, String poster, int episode, Integer seasonIndex, Integer season, String data, String apiName, int id, int index, long position, long duration, Score score, String description, Boolean isFiller, TvType tvType, int parentId, VideoWatchState videoWatchState, Integer totalEpisodeIndex, Long airDate, Integer runTime, SeasonData seasonData) {
        Intrinsics.checkNotNullParameter(headerName, "headerName");
        Intrinsics.checkNotNullParameter(data, "data");
        Intrinsics.checkNotNullParameter(apiName, "apiName");
        Intrinsics.checkNotNullParameter(tvType, "tvType");
        Intrinsics.checkNotNullParameter(videoWatchState, "videoWatchState");
        return new ResultEpisode(headerName, name, poster, episode, seasonIndex, season, data, apiName, id, index, position, duration, score, description, isFiller, tvType, parentId, videoWatchState, totalEpisodeIndex, airDate, runTime, seasonData);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ResultEpisode)) {
            return false;
        }
        ResultEpisode resultEpisode = (ResultEpisode) other;
        return Intrinsics.areEqual(this.headerName, resultEpisode.headerName) && Intrinsics.areEqual(this.name, resultEpisode.name) && Intrinsics.areEqual(this.poster, resultEpisode.poster) && this.episode == resultEpisode.episode && Intrinsics.areEqual(this.seasonIndex, resultEpisode.seasonIndex) && Intrinsics.areEqual(this.season, resultEpisode.season) && Intrinsics.areEqual(this.data, resultEpisode.data) && Intrinsics.areEqual(this.apiName, resultEpisode.apiName) && this.id == resultEpisode.id && this.index == resultEpisode.index && this.position == resultEpisode.position && this.duration == resultEpisode.duration && Intrinsics.areEqual(this.score, resultEpisode.score) && Intrinsics.areEqual(this.description, resultEpisode.description) && Intrinsics.areEqual(this.isFiller, resultEpisode.isFiller) && this.tvType == resultEpisode.tvType && this.parentId == resultEpisode.parentId && this.videoWatchState == resultEpisode.videoWatchState && Intrinsics.areEqual(this.totalEpisodeIndex, resultEpisode.totalEpisodeIndex) && Intrinsics.areEqual(this.airDate, resultEpisode.airDate) && Intrinsics.areEqual(this.runTime, resultEpisode.runTime) && Intrinsics.areEqual(this.seasonData, resultEpisode.seasonData);
    }

    public int hashCode() {
        int iHashCode = this.headerName.hashCode() * 31;
        String str = this.name;
        int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.poster;
        int iHashCode3 = (((iHashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31) + this.episode) * 31;
        Integer num = this.seasonIndex;
        int iHashCode4 = (iHashCode3 + (num == null ? 0 : num.hashCode())) * 31;
        Integer num2 = this.season;
        int iHashCode5 = (((((((((((((iHashCode4 + (num2 == null ? 0 : num2.hashCode())) * 31) + this.data.hashCode()) * 31) + this.apiName.hashCode()) * 31) + this.id) * 31) + this.index) * 31) + NextAiring$$ExternalSyntheticBackport0.m(this.position)) * 31) + NextAiring$$ExternalSyntheticBackport0.m(this.duration)) * 31;
        Score score = this.score;
        int data = (iHashCode5 + (score == null ? 0 : score.getData())) * 31;
        String str3 = this.description;
        int iHashCode6 = (data + (str3 == null ? 0 : str3.hashCode())) * 31;
        Boolean bool = this.isFiller;
        int iHashCode7 = (((((((iHashCode6 + (bool == null ? 0 : bool.hashCode())) * 31) + this.tvType.hashCode()) * 31) + this.parentId) * 31) + this.videoWatchState.hashCode()) * 31;
        Integer num3 = this.totalEpisodeIndex;
        int iHashCode8 = (iHashCode7 + (num3 == null ? 0 : num3.hashCode())) * 31;
        Long l = this.airDate;
        int iHashCode9 = (iHashCode8 + (l == null ? 0 : l.hashCode())) * 31;
        Integer num4 = this.runTime;
        int iHashCode10 = (iHashCode9 + (num4 == null ? 0 : num4.hashCode())) * 31;
        SeasonData seasonData = this.seasonData;
        return iHashCode10 + (seasonData != null ? seasonData.hashCode() : 0);
    }

    public String toString() {
        return "ResultEpisode(headerName=" + this.headerName + ", name=" + this.name + ", poster=" + this.poster + ", episode=" + this.episode + ", seasonIndex=" + this.seasonIndex + ", season=" + this.season + ", data=" + this.data + ", apiName=" + this.apiName + ", id=" + this.id + ", index=" + this.index + ", position=" + this.position + ", duration=" + this.duration + ", score=" + this.score + ", description=" + this.description + ", isFiller=" + this.isFiller + ", tvType=" + this.tvType + ", parentId=" + this.parentId + ", videoWatchState=" + this.videoWatchState + ", totalEpisodeIndex=" + this.totalEpisodeIndex + ", airDate=" + this.airDate + ", runTime=" + this.runTime + ", seasonData=" + this.seasonData + ')';
    }

    public ResultEpisode(String headerName, String str, String str2, int i, Integer num, Integer num2, String data, String apiName, int i2, int i3, long j, long j2, Score score, String str3, Boolean bool, TvType tvType, int i4, VideoWatchState videoWatchState, Integer num3, Long l, Integer num4, SeasonData seasonData) {
        Intrinsics.checkNotNullParameter(headerName, "headerName");
        Intrinsics.checkNotNullParameter(data, "data");
        Intrinsics.checkNotNullParameter(apiName, "apiName");
        Intrinsics.checkNotNullParameter(tvType, "tvType");
        Intrinsics.checkNotNullParameter(videoWatchState, "videoWatchState");
        this.headerName = headerName;
        this.name = str;
        this.poster = str2;
        this.episode = i;
        this.seasonIndex = num;
        this.season = num2;
        this.data = data;
        this.apiName = apiName;
        this.id = i2;
        this.index = i3;
        this.position = j;
        this.duration = j2;
        this.score = score;
        this.description = str3;
        this.isFiller = bool;
        this.tvType = tvType;
        this.parentId = i4;
        this.videoWatchState = videoWatchState;
        this.totalEpisodeIndex = num3;
        this.airDate = l;
        this.runTime = num4;
        this.seasonData = seasonData;
    }

    public /* synthetic */ ResultEpisode(String str, String str2, String str3, int i, Integer num, Integer num2, String str4, String str5, int i2, int i3, long j, long j2, Score score, String str6, Boolean bool, TvType tvType, int i4, VideoWatchState videoWatchState, Integer num3, Long l, Integer num4, SeasonData seasonData, int i5, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, str3, i, num, num2, str4, str5, i2, i3, j, j2, score, str6, bool, tvType, i4, videoWatchState, (i5 & 262144) != 0 ? null : num3, (i5 & 524288) != 0 ? null : l, (i5 & 1048576) != 0 ? null : num4, (i5 & 2097152) != 0 ? null : seasonData);
    }

    public final String getHeaderName() {
        return this.headerName;
    }

    public final String getName() {
        return this.name;
    }

    public final String getPoster() {
        return this.poster;
    }

    public final int getEpisode() {
        return this.episode;
    }

    public final Integer getSeasonIndex() {
        return this.seasonIndex;
    }

    public final Integer getSeason() {
        return this.season;
    }

    public final String getData() {
        return this.data;
    }

    public final String getApiName() {
        return this.apiName;
    }

    public final int getId() {
        return this.id;
    }

    public final int getIndex() {
        return this.index;
    }

    public final long getPosition() {
        return this.position;
    }

    public final long getDuration() {
        return this.duration;
    }

    public final Score getScore() {
        return this.score;
    }

    public final String getDescription() {
        return this.description;
    }

    public final Boolean isFiller() {
        return this.isFiller;
    }

    public final TvType getTvType() {
        return this.tvType;
    }

    public final int getParentId() {
        return this.parentId;
    }

    public final VideoWatchState getVideoWatchState() {
        return this.videoWatchState;
    }

    public final Integer getTotalEpisodeIndex() {
        return this.totalEpisodeIndex;
    }

    public final Long getAirDate() {
        return this.airDate;
    }

    public final Integer getRunTime() {
        return this.runTime;
    }

    public final SeasonData getSeasonData() {
        return this.seasonData;
    }
}
