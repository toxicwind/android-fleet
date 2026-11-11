package com.lagradost.cloudstream3;

import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: compiled from: MainAPI.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b1\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001Bq\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u0006¢\u0006\u0004\b\u000f\u0010\u0010J\t\u00103\u001a\u00020\u0003HÆ\u0003J\u000b\u00104\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u00105\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0002\u0010\u0018J\u0010\u00106\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0002\u0010\u0018J\u000b\u00107\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u00108\u001a\u0004\u0018\u00010\nHÆ\u0003J\u000b\u00109\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010:\u001a\u0004\u0018\u00010\rHÆ\u0003¢\u0006\u0002\u0010'J\u0010\u0010;\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0002\u0010\u0018Jx\u0010<\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u0006HÆ\u0001¢\u0006\u0002\u0010=J\u0014\u0010>\u001a\u00020?2\b\u0010@\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010A\u001a\u00020\u0006HÖ\u0081\u0004J\n\u0010B\u001a\u00020\u0003HÖ\u0081\u0004R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0012\"\u0004\b\u0016\u0010\u0014R\u001e\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u001b\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u001e\u0010\u0007\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u001b\u001a\u0004\b\u001c\u0010\u0018\"\u0004\b\u001d\u0010\u001aR\u001c\u0010\b\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u0012\"\u0004\b\u001f\u0010\u0014R\u001c\u0010\t\u001a\u0004\u0018\u00010\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#R\u001c\u0010\u000b\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010\u0012\"\u0004\b%\u0010\u0014R\u001e\u0010\f\u001a\u0004\u0018\u00010\rX\u0086\u000e¢\u0006\u0010\n\u0002\u0010*\u001a\u0004\b&\u0010'\"\u0004\b(\u0010)R\u001e\u0010\u000e\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u001b\u001a\u0004\b+\u0010\u0018\"\u0004\b,\u0010\u001aR.\u0010.\u001a\u0004\u0018\u00010\u00062\b\u0010-\u001a\u0004\u0018\u00010\u00068F@FX\u0087\u000e¢\u0006\u0012\u0012\u0004\b/\u00100\u001a\u0004\b1\u0010\u0018\"\u0004\b2\u0010\u001a¨\u0006C"}, d2 = {"Lcom/lagradost/cloudstream3/Episode;", "", "data", "", "name", "season", "", "episode", "posterUrl", "score", "Lcom/lagradost/cloudstream3/Score;", "description", "date", "", "runTime", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Lcom/lagradost/cloudstream3/Score;Ljava/lang/String;Ljava/lang/Long;Ljava/lang/Integer;)V", "getData", "()Ljava/lang/String;", "setData", "(Ljava/lang/String;)V", "getName", "setName", "getSeason", "()Ljava/lang/Integer;", "setSeason", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "getEpisode", "setEpisode", "getPosterUrl", "setPosterUrl", "getScore", "()Lcom/lagradost/cloudstream3/Score;", "setScore", "(Lcom/lagradost/cloudstream3/Score;)V", "getDescription", "setDescription", "getDate", "()Ljava/lang/Long;", "setDate", "(Ljava/lang/Long;)V", "Ljava/lang/Long;", "getRunTime", "setRunTime", ES6Iterator.VALUE_PROPERTY, "rating", "getRating$annotations", "()V", "getRating", "setRating", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Lcom/lagradost/cloudstream3/Score;Ljava/lang/String;Ljava/lang/Long;Ljava/lang/Integer;)Lcom/lagradost/cloudstream3/Episode;", "equals", "", "other", "hashCode", "toString", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class Episode {
    private String data;
    private Long date;
    private String description;
    private Integer episode;
    private String name;
    private String posterUrl;
    private Integer runTime;
    private Score score;
    private Integer season;

    public static /* synthetic */ Episode copy$default(Episode episode, String str, String str2, Integer num, Integer num2, String str3, Score score, String str4, Long l, Integer num3, int i, Object obj) {
        if ((i & 1) != 0) {
            str = episode.data;
        }
        if ((i & 2) != 0) {
            str2 = episode.name;
        }
        if ((i & 4) != 0) {
            num = episode.season;
        }
        if ((i & 8) != 0) {
            num2 = episode.episode;
        }
        if ((i & 16) != 0) {
            str3 = episode.posterUrl;
        }
        if ((i & 32) != 0) {
            score = episode.score;
        }
        if ((i & 64) != 0) {
            str4 = episode.description;
        }
        if ((i & 128) != 0) {
            l = episode.date;
        }
        if ((i & 256) != 0) {
            num3 = episode.runTime;
        }
        Long l2 = l;
        Integer num4 = num3;
        Score score2 = score;
        String str5 = str4;
        String str6 = str3;
        Integer num5 = num;
        return episode.copy(str, str2, num5, num2, str6, score2, str5, l2, num4);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "`rating` is the old scoring system, use score instead", replaceWith = @ReplaceWith(expression = "score", imports = {}))
    public static /* synthetic */ void getRating$annotations() {
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getData() {
        return this.data;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final Integer getSeason() {
        return this.season;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final Integer getEpisode() {
        return this.episode;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getPosterUrl() {
        return this.posterUrl;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final Score getScore() {
        return this.score;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final String getDescription() {
        return this.description;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final Long getDate() {
        return this.date;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final Integer getRunTime() {
        return this.runTime;
    }

    public final Episode copy(String data, String name, Integer season, Integer episode, String posterUrl, Score score, String description, Long date, Integer runTime) {
        Intrinsics.checkNotNullParameter(data, "data");
        return new Episode(data, name, season, episode, posterUrl, score, description, date, runTime);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Episode)) {
            return false;
        }
        Episode episode = (Episode) other;
        return Intrinsics.areEqual(this.data, episode.data) && Intrinsics.areEqual(this.name, episode.name) && Intrinsics.areEqual(this.season, episode.season) && Intrinsics.areEqual(this.episode, episode.episode) && Intrinsics.areEqual(this.posterUrl, episode.posterUrl) && Intrinsics.areEqual(this.score, episode.score) && Intrinsics.areEqual(this.description, episode.description) && Intrinsics.areEqual(this.date, episode.date) && Intrinsics.areEqual(this.runTime, episode.runTime);
    }

    public int hashCode() {
        int iHashCode = this.data.hashCode() * 31;
        String str = this.name;
        int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
        Integer num = this.season;
        int iHashCode3 = (iHashCode2 + (num == null ? 0 : num.hashCode())) * 31;
        Integer num2 = this.episode;
        int iHashCode4 = (iHashCode3 + (num2 == null ? 0 : num2.hashCode())) * 31;
        String str2 = this.posterUrl;
        int iHashCode5 = (iHashCode4 + (str2 == null ? 0 : str2.hashCode())) * 31;
        Score score = this.score;
        int data = (iHashCode5 + (score == null ? 0 : score.getData())) * 31;
        String str3 = this.description;
        int iHashCode6 = (data + (str3 == null ? 0 : str3.hashCode())) * 31;
        Long l = this.date;
        int iHashCode7 = (iHashCode6 + (l == null ? 0 : l.hashCode())) * 31;
        Integer num3 = this.runTime;
        return iHashCode7 + (num3 != null ? num3.hashCode() : 0);
    }

    public String toString() {
        return "Episode(data=" + this.data + ", name=" + this.name + ", season=" + this.season + ", episode=" + this.episode + ", posterUrl=" + this.posterUrl + ", score=" + this.score + ", description=" + this.description + ", date=" + this.date + ", runTime=" + this.runTime + ')';
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use newEpisode method")
    public Episode(String data, String str, Integer num, Integer num2, String str2, Score score, String str3, Long l, Integer num3) {
        Intrinsics.checkNotNullParameter(data, "data");
        this.data = data;
        this.name = str;
        this.season = num;
        this.episode = num2;
        this.posterUrl = str2;
        this.score = score;
        this.description = str3;
        this.date = l;
        this.runTime = num3;
    }

    public /* synthetic */ Episode(String str, String str2, Integer num, Integer num2, String str3, Score score, String str4, Long l, Integer num3, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i & 2) != 0 ? null : str2, (i & 4) != 0 ? null : num, (i & 8) != 0 ? null : num2, (i & 16) != 0 ? null : str3, (i & 32) != 0 ? null : score, (i & 64) != 0 ? null : str4, (i & 128) != 0 ? null : l, (i & 256) != 0 ? null : num3);
    }

    public final String getData() {
        return this.data;
    }

    public final void setData(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.data = str;
    }

    public final String getName() {
        return this.name;
    }

    public final void setName(String str) {
        this.name = str;
    }

    public final Integer getSeason() {
        return this.season;
    }

    public final void setSeason(Integer num) {
        this.season = num;
    }

    public final Integer getEpisode() {
        return this.episode;
    }

    public final void setEpisode(Integer num) {
        this.episode = num;
    }

    public final String getPosterUrl() {
        return this.posterUrl;
    }

    public final void setPosterUrl(String str) {
        this.posterUrl = str;
    }

    public final Score getScore() {
        return this.score;
    }

    public final void setScore(Score score) {
        this.score = score;
    }

    public final String getDescription() {
        return this.description;
    }

    public final void setDescription(String str) {
        this.description = str;
    }

    public final Long getDate() {
        return this.date;
    }

    public final void setDate(Long l) {
        this.date = l;
    }

    public final Integer getRunTime() {
        return this.runTime;
    }

    public final void setRunTime(Integer num) {
        this.runTime = num;
    }

    public final void setRating(Integer num) {
        this.score = Score.INSTANCE.from(num, 100);
    }

    public final Integer getRating() {
        Score score = this.score;
        if (score != null) {
            return Integer.valueOf(score.toInt(100));
        }
        return null;
    }
}
