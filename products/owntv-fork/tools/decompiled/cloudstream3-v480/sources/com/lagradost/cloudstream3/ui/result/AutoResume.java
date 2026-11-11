package com.lagradost.cloudstream3.ui.result;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ResultViewModel2.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0013\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B-\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003¢\u0006\u0004\b\u0007\u0010\bJ\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\nJ\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\nJ\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\nJ\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J<\u0010\u0014\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u0003HÆ\u0001¢\u0006\u0002\u0010\u0015J\u0014\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0019\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010\u001a\u001a\u00020\u001bHÖ\u0081\u0004R\u0015\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u000b\u001a\u0004\b\t\u0010\nR\u0015\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u000b\u001a\u0004\b\f\u0010\nR\u0015\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u000b\u001a\u0004\b\r\u0010\nR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001c"}, d2 = {"Lcom/lagradost/cloudstream3/ui/result/AutoResume;", "", "season", "", "episode", "id", "startAction", "<init>", "(Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;I)V", "getSeason", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getEpisode", "getId", "getStartAction", "()I", "component1", "component2", "component3", "component4", "copy", "(Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;I)Lcom/lagradost/cloudstream3/ui/result/AutoResume;", "equals", "", "other", "hashCode", "toString", "", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class AutoResume {
    private final Integer episode;
    private final Integer id;
    private final Integer season;
    private final int startAction;

    public static /* synthetic */ AutoResume copy$default(AutoResume autoResume, Integer num, Integer num2, Integer num3, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            num = autoResume.season;
        }
        if ((i2 & 2) != 0) {
            num2 = autoResume.episode;
        }
        if ((i2 & 4) != 0) {
            num3 = autoResume.id;
        }
        if ((i2 & 8) != 0) {
            i = autoResume.startAction;
        }
        return autoResume.copy(num, num2, num3, i);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final Integer getSeason() {
        return this.season;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final Integer getEpisode() {
        return this.episode;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final Integer getId() {
        return this.id;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final int getStartAction() {
        return this.startAction;
    }

    public final AutoResume copy(Integer season, Integer episode, Integer id, int startAction) {
        return new AutoResume(season, episode, id, startAction);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AutoResume)) {
            return false;
        }
        AutoResume autoResume = (AutoResume) other;
        return Intrinsics.areEqual(this.season, autoResume.season) && Intrinsics.areEqual(this.episode, autoResume.episode) && Intrinsics.areEqual(this.id, autoResume.id) && this.startAction == autoResume.startAction;
    }

    public int hashCode() {
        Integer num = this.season;
        int iHashCode = (num == null ? 0 : num.hashCode()) * 31;
        Integer num2 = this.episode;
        int iHashCode2 = (iHashCode + (num2 == null ? 0 : num2.hashCode())) * 31;
        Integer num3 = this.id;
        return ((iHashCode2 + (num3 != null ? num3.hashCode() : 0)) * 31) + this.startAction;
    }

    public String toString() {
        return "AutoResume(season=" + this.season + ", episode=" + this.episode + ", id=" + this.id + ", startAction=" + this.startAction + ')';
    }

    public AutoResume(Integer num, Integer num2, Integer num3, int i) {
        this.season = num;
        this.episode = num2;
        this.id = num3;
        this.startAction = i;
    }

    public final Integer getSeason() {
        return this.season;
    }

    public final Integer getEpisode() {
        return this.episode;
    }

    public final Integer getId() {
        return this.id;
    }

    public final int getStartAction() {
        return this.startAction;
    }
}
