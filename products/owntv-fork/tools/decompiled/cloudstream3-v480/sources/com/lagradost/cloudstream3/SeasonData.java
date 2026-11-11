package com.lagradost.cloudstream3;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MainAPI.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0011\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u000eJ0\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003HÆ\u0001¢\u0006\u0002\u0010\u0014J\u0014\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0018\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010\u0019\u001a\u00020\u0005HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0015\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u000f\u001a\u0004\b\r\u0010\u000e¨\u0006\u001a"}, d2 = {"Lcom/lagradost/cloudstream3/SeasonData;", "", "season", "", "name", "", "displaySeason", "<init>", "(ILjava/lang/String;Ljava/lang/Integer;)V", "getSeason", "()I", "getName", "()Ljava/lang/String;", "getDisplaySeason", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "component1", "component2", "component3", "copy", "(ILjava/lang/String;Ljava/lang/Integer;)Lcom/lagradost/cloudstream3/SeasonData;", "equals", "", "other", "hashCode", "toString", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class SeasonData {
    private final Integer displaySeason;
    private final String name;
    private final int season;

    public static /* synthetic */ SeasonData copy$default(SeasonData seasonData, int i, String str, Integer num, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = seasonData.season;
        }
        if ((i2 & 2) != 0) {
            str = seasonData.name;
        }
        if ((i2 & 4) != 0) {
            num = seasonData.displaySeason;
        }
        return seasonData.copy(i, str, num);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final int getSeason() {
        return this.season;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final Integer getDisplaySeason() {
        return this.displaySeason;
    }

    public final SeasonData copy(int season, String name, Integer displaySeason) {
        return new SeasonData(season, name, displaySeason);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SeasonData)) {
            return false;
        }
        SeasonData seasonData = (SeasonData) other;
        return this.season == seasonData.season && Intrinsics.areEqual(this.name, seasonData.name) && Intrinsics.areEqual(this.displaySeason, seasonData.displaySeason);
    }

    public int hashCode() {
        int i = this.season * 31;
        String str = this.name;
        int iHashCode = (i + (str == null ? 0 : str.hashCode())) * 31;
        Integer num = this.displaySeason;
        return iHashCode + (num != null ? num.hashCode() : 0);
    }

    public String toString() {
        return "SeasonData(season=" + this.season + ", name=" + this.name + ", displaySeason=" + this.displaySeason + ')';
    }

    public SeasonData(int i, String str, Integer num) {
        this.season = i;
        this.name = str;
        this.displaySeason = num;
    }

    public /* synthetic */ SeasonData(int i, String str, Integer num, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, (i2 & 2) != 0 ? null : str, (i2 & 4) != 0 ? null : num);
    }

    public final int getSeason() {
        return this.season;
    }

    public final String getName() {
        return this.name;
    }

    public final Integer getDisplaySeason() {
        return this.displaySeason;
    }
}
