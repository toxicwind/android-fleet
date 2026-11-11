package com.lagradost.cloudstream3.ui.player;

import com.lagradost.cloudstream3.NextAiring$$ExternalSyntheticBackport0;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: IPlayer.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0005HÆ\u0003J1\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u0005HÆ\u0001J\u0014\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aHÖ\u0083\u0004J\n\u0010\u001b\u001a\u00020\u001cHÖ\u0081\u0004J\n\u0010\u001d\u001a\u00020\u001eHÖ\u0081\u0004R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0011\u0010\u0007\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\rR\u0011\u0010\u0010\u001a\u00020\u00058F¢\u0006\u0006\u001a\u0004\b\u0011\u0010\r¨\u0006\u001f"}, d2 = {"Lcom/lagradost/cloudstream3/ui/player/PositionEvent;", "Lcom/lagradost/cloudstream3/ui/player/PlayerEvent;", "source", "Lcom/lagradost/cloudstream3/ui/player/PlayerEventSource;", "fromMs", "", "toMs", "durationMs", "<init>", "(Lcom/lagradost/cloudstream3/ui/player/PlayerEventSource;JJJ)V", "getSource", "()Lcom/lagradost/cloudstream3/ui/player/PlayerEventSource;", "getFromMs", "()J", "getToMs", "getDurationMs", "seekMs", "getSeekMs", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class PositionEvent extends PlayerEvent {
    private final long durationMs;
    private final long fromMs;
    private final PlayerEventSource source;
    private final long toMs;

    public static /* synthetic */ PositionEvent copy$default(PositionEvent positionEvent, PlayerEventSource playerEventSource, long j, long j2, long j3, int i, Object obj) {
        if ((i & 1) != 0) {
            playerEventSource = positionEvent.source;
        }
        if ((i & 2) != 0) {
            j = positionEvent.fromMs;
        }
        if ((i & 4) != 0) {
            j2 = positionEvent.toMs;
        }
        if ((i & 8) != 0) {
            j3 = positionEvent.durationMs;
        }
        long j4 = j3;
        return positionEvent.copy(playerEventSource, j, j2, j4);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final PlayerEventSource getSource() {
        return this.source;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final long getFromMs() {
        return this.fromMs;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final long getToMs() {
        return this.toMs;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final long getDurationMs() {
        return this.durationMs;
    }

    public final PositionEvent copy(PlayerEventSource source, long fromMs, long toMs, long durationMs) {
        Intrinsics.checkNotNullParameter(source, "source");
        return new PositionEvent(source, fromMs, toMs, durationMs);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PositionEvent)) {
            return false;
        }
        PositionEvent positionEvent = (PositionEvent) other;
        return this.source == positionEvent.source && this.fromMs == positionEvent.fromMs && this.toMs == positionEvent.toMs && this.durationMs == positionEvent.durationMs;
    }

    public int hashCode() {
        return (((((this.source.hashCode() * 31) + NextAiring$$ExternalSyntheticBackport0.m(this.fromMs)) * 31) + NextAiring$$ExternalSyntheticBackport0.m(this.toMs)) * 31) + NextAiring$$ExternalSyntheticBackport0.m(this.durationMs);
    }

    public String toString() {
        return "PositionEvent(source=" + this.source + ", fromMs=" + this.fromMs + ", toMs=" + this.toMs + ", durationMs=" + this.durationMs + ')';
    }

    public PositionEvent(PlayerEventSource source, long j, long j2, long j3) {
        Intrinsics.checkNotNullParameter(source, "source");
        this.source = source;
        this.fromMs = j;
        this.toMs = j2;
        this.durationMs = j3;
    }

    @Override // com.lagradost.cloudstream3.ui.player.PlayerEvent
    public PlayerEventSource getSource() {
        return this.source;
    }

    public final long getFromMs() {
        return this.fromMs;
    }

    public final long getToMs() {
        return this.toMs;
    }

    public final long getDurationMs() {
        return this.durationMs;
    }

    public final long getSeekMs() {
        return this.toMs - this.fromMs;
    }
}
