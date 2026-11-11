package com.lagradost.cloudstream3.metaproviders;

import com.fasterxml.jackson.annotation.JsonProperty;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: TmdbProvider.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0015\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001BC\u0012\n\b\u0001\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0001\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0003\u0010\b\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\t\u0010\nJ\u000b\u0010\u0013\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\u000eJ\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\u000eJ\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\u000eJ\u000b\u0010\u0017\u001a\u0004\u0018\u00010\u0003HÆ\u0003JJ\u0010\u0018\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\u0007\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\b\u001a\u0004\u0018\u00010\u0003HÆ\u0001¢\u0006\u0002\u0010\u0019J\u0014\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u001d\u001a\u00020\u0005HÖ\u0081\u0004J\n\u0010\u001e\u001a\u00020\u0003HÖ\u0081\u0004R\u0018\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u001a\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\u000f\u001a\u0004\b\r\u0010\u000eR\u001a\u0010\u0006\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\u000f\u001a\u0004\b\u0010\u0010\u000eR\u001a\u0010\u0007\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\u000f\u001a\u0004\b\u0011\u0010\u000eR\u0018\u0010\b\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\f¨\u0006\u001f"}, d2 = {"Lcom/lagradost/cloudstream3/metaproviders/TmdbLink;", "", "imdbID", "", "tmdbID", "", "episode", "season", "movieName", "<init>", "(Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;)V", "getImdbID", "()Ljava/lang/String;", "getTmdbID", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getEpisode", "getSeason", "getMovieName", "component1", "component2", "component3", "component4", "component5", "copy", "(Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;)Lcom/lagradost/cloudstream3/metaproviders/TmdbLink;", "equals", "", "other", "hashCode", "toString", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class TmdbLink {

    @JsonProperty("episode")
    private final Integer episode;

    @JsonProperty("imdbID")
    private final String imdbID;

    @JsonProperty("movieName")
    private final String movieName;

    @JsonProperty("season")
    private final Integer season;

    @JsonProperty("tmdbID")
    private final Integer tmdbID;

    public static /* synthetic */ TmdbLink copy$default(TmdbLink tmdbLink, String str, Integer num, Integer num2, Integer num3, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = tmdbLink.imdbID;
        }
        if ((i & 2) != 0) {
            num = tmdbLink.tmdbID;
        }
        if ((i & 4) != 0) {
            num2 = tmdbLink.episode;
        }
        if ((i & 8) != 0) {
            num3 = tmdbLink.season;
        }
        if ((i & 16) != 0) {
            str2 = tmdbLink.movieName;
        }
        String str3 = str2;
        Integer num4 = num2;
        return tmdbLink.copy(str, num, num4, num3, str3);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getImdbID() {
        return this.imdbID;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final Integer getTmdbID() {
        return this.tmdbID;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final Integer getEpisode() {
        return this.episode;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final Integer getSeason() {
        return this.season;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getMovieName() {
        return this.movieName;
    }

    public final TmdbLink copy(@JsonProperty("imdbID") String imdbID, @JsonProperty("tmdbID") Integer tmdbID, @JsonProperty("episode") Integer episode, @JsonProperty("season") Integer season, @JsonProperty("movieName") String movieName) {
        return new TmdbLink(imdbID, tmdbID, episode, season, movieName);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TmdbLink)) {
            return false;
        }
        TmdbLink tmdbLink = (TmdbLink) other;
        return Intrinsics.areEqual(this.imdbID, tmdbLink.imdbID) && Intrinsics.areEqual(this.tmdbID, tmdbLink.tmdbID) && Intrinsics.areEqual(this.episode, tmdbLink.episode) && Intrinsics.areEqual(this.season, tmdbLink.season) && Intrinsics.areEqual(this.movieName, tmdbLink.movieName);
    }

    public int hashCode() {
        String str = this.imdbID;
        int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
        Integer num = this.tmdbID;
        int iHashCode2 = (iHashCode + (num == null ? 0 : num.hashCode())) * 31;
        Integer num2 = this.episode;
        int iHashCode3 = (iHashCode2 + (num2 == null ? 0 : num2.hashCode())) * 31;
        Integer num3 = this.season;
        int iHashCode4 = (iHashCode3 + (num3 == null ? 0 : num3.hashCode())) * 31;
        String str2 = this.movieName;
        return iHashCode4 + (str2 != null ? str2.hashCode() : 0);
    }

    public String toString() {
        return "TmdbLink(imdbID=" + this.imdbID + ", tmdbID=" + this.tmdbID + ", episode=" + this.episode + ", season=" + this.season + ", movieName=" + this.movieName + ')';
    }

    public TmdbLink(@JsonProperty("imdbID") String str, @JsonProperty("tmdbID") Integer num, @JsonProperty("episode") Integer num2, @JsonProperty("season") Integer num3, @JsonProperty("movieName") String str2) {
        this.imdbID = str;
        this.tmdbID = num;
        this.episode = num2;
        this.season = num3;
        this.movieName = str2;
    }

    public /* synthetic */ TmdbLink(String str, Integer num, Integer num2, Integer num3, String str2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, num, num2, num3, (i & 16) != 0 ? null : str2);
    }

    public final String getImdbID() {
        return this.imdbID;
    }

    public final Integer getTmdbID() {
        return this.tmdbID;
    }

    public final Integer getEpisode() {
        return this.episode;
    }

    public final Integer getSeason() {
        return this.season;
    }

    public final String getMovieName() {
        return this.movieName;
    }
}
