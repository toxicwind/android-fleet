package com.lagradost.cloudstream3;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MainAPI.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B#\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0005HÆ\u0003J\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u000eJ.\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003HÆ\u0001¢\u0006\u0002\u0010\u0014J\u0014\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0018\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010\u0019\u001a\u00020\u001aHÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0015\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u000f\u001a\u0004\b\r\u0010\u000e¨\u0006\u001b"}, d2 = {"Lcom/lagradost/cloudstream3/NextAiring;", "", "episode", "", "unixTime", "", "season", "<init>", "(IJLjava/lang/Integer;)V", "getEpisode", "()I", "getUnixTime", "()J", "getSeason", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "component1", "component2", "component3", "copy", "(IJLjava/lang/Integer;)Lcom/lagradost/cloudstream3/NextAiring;", "equals", "", "other", "hashCode", "toString", "", "library"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class NextAiring {
    private final int episode;
    private final Integer season;
    private final long unixTime;

    public static /* synthetic */ NextAiring copy$default(NextAiring nextAiring, int i, long j, Integer num, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = nextAiring.episode;
        }
        if ((i2 & 2) != 0) {
            j = nextAiring.unixTime;
        }
        if ((i2 & 4) != 0) {
            num = nextAiring.season;
        }
        return nextAiring.copy(i, j, num);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final int getEpisode() {
        return this.episode;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final long getUnixTime() {
        return this.unixTime;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final Integer getSeason() {
        return this.season;
    }

    public final NextAiring copy(int episode, long unixTime, Integer season) {
        return new NextAiring(episode, unixTime, season);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof NextAiring)) {
            return false;
        }
        NextAiring nextAiring = (NextAiring) other;
        return this.episode == nextAiring.episode && this.unixTime == nextAiring.unixTime && Intrinsics.areEqual(this.season, nextAiring.season);
    }

    public int hashCode() {
        int iM = ((this.episode * 31) + NextAiring$$ExternalSyntheticBackport0.m(this.unixTime)) * 31;
        Integer num = this.season;
        return iM + (num == null ? 0 : num.hashCode());
    }

    public String toString() {
        return "NextAiring(episode=" + this.episode + ", unixTime=" + this.unixTime + ", season=" + this.season + ')';
    }

    public NextAiring(int i, long j, Integer num) {
        this.episode = i;
        this.unixTime = j;
        this.season = num;
    }

    public /* synthetic */ NextAiring(int i, long j, Integer num, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, j, (i2 & 4) != 0 ? null : num);
    }

    public final int getEpisode() {
        return this.episode;
    }

    public final long getUnixTime() {
        return this.unixTime;
    }

    public final Integer getSeason() {
        return this.season;
    }
}
