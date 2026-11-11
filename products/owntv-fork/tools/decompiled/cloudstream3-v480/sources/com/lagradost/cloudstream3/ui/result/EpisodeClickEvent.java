package com.lagradost.cloudstream3.ui.result;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: EpisodeAdapter.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B!\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bB\u0019\b\u0016\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\tJ\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u000bJ\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0006HÆ\u0003J.\u0010\u0014\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006HÆ\u0001¢\u0006\u0002\u0010\u0015J\u0014\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0019\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010\u001a\u001a\u00020\u001bHÖ\u0081\u0004R\u0015\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u001c"}, d2 = {"Lcom/lagradost/cloudstream3/ui/result/EpisodeClickEvent;", "", "position", "", "action", "data", "Lcom/lagradost/cloudstream3/ui/result/ResultEpisode;", "<init>", "(Ljava/lang/Integer;ILcom/lagradost/cloudstream3/ui/result/ResultEpisode;)V", "(ILcom/lagradost/cloudstream3/ui/result/ResultEpisode;)V", "getPosition", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getAction", "()I", "getData", "()Lcom/lagradost/cloudstream3/ui/result/ResultEpisode;", "component1", "component2", "component3", "copy", "(Ljava/lang/Integer;ILcom/lagradost/cloudstream3/ui/result/ResultEpisode;)Lcom/lagradost/cloudstream3/ui/result/EpisodeClickEvent;", "equals", "", "other", "hashCode", "toString", "", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class EpisodeClickEvent {
    private final int action;
    private final ResultEpisode data;
    private final Integer position;

    public static /* synthetic */ EpisodeClickEvent copy$default(EpisodeClickEvent episodeClickEvent, Integer num, int i, ResultEpisode resultEpisode, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            num = episodeClickEvent.position;
        }
        if ((i2 & 2) != 0) {
            i = episodeClickEvent.action;
        }
        if ((i2 & 4) != 0) {
            resultEpisode = episodeClickEvent.data;
        }
        return episodeClickEvent.copy(num, i, resultEpisode);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final Integer getPosition() {
        return this.position;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final int getAction() {
        return this.action;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final ResultEpisode getData() {
        return this.data;
    }

    public final EpisodeClickEvent copy(Integer position, int action, ResultEpisode data) {
        Intrinsics.checkNotNullParameter(data, "data");
        return new EpisodeClickEvent(position, action, data);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof EpisodeClickEvent)) {
            return false;
        }
        EpisodeClickEvent episodeClickEvent = (EpisodeClickEvent) other;
        return Intrinsics.areEqual(this.position, episodeClickEvent.position) && this.action == episodeClickEvent.action && Intrinsics.areEqual(this.data, episodeClickEvent.data);
    }

    public int hashCode() {
        Integer num = this.position;
        return ((((num == null ? 0 : num.hashCode()) * 31) + this.action) * 31) + this.data.hashCode();
    }

    public String toString() {
        return "EpisodeClickEvent(position=" + this.position + ", action=" + this.action + ", data=" + this.data + ')';
    }

    public EpisodeClickEvent(Integer num, int i, ResultEpisode data) {
        Intrinsics.checkNotNullParameter(data, "data");
        this.position = num;
        this.action = i;
        this.data = data;
    }

    public final int getAction() {
        return this.action;
    }

    public final ResultEpisode getData() {
        return this.data;
    }

    public final Integer getPosition() {
        return this.position;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public EpisodeClickEvent(int i, ResultEpisode data) {
        this(null, i, data);
        Intrinsics.checkNotNullParameter(data, "data");
    }
}
