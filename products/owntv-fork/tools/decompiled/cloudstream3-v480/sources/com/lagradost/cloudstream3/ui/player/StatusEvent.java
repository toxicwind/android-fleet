package com.lagradost.cloudstream3.ui.player;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: IPlayer.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0006HÆ\u0003J'\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006HÆ\u0001J\u0014\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014HÖ\u0083\u0004J\n\u0010\u0015\u001a\u00020\u0016HÖ\u0081\u0004J\n\u0010\u0017\u001a\u00020\u0018HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\nR\u0014\u0010\u0005\u001a\u00020\u0006X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0019"}, d2 = {"Lcom/lagradost/cloudstream3/ui/player/StatusEvent;", "Lcom/lagradost/cloudstream3/ui/player/PlayerEvent;", "wasPlaying", "Lcom/lagradost/cloudstream3/ui/player/CSPlayerLoading;", "isPlaying", "source", "Lcom/lagradost/cloudstream3/ui/player/PlayerEventSource;", "<init>", "(Lcom/lagradost/cloudstream3/ui/player/CSPlayerLoading;Lcom/lagradost/cloudstream3/ui/player/CSPlayerLoading;Lcom/lagradost/cloudstream3/ui/player/PlayerEventSource;)V", "getWasPlaying", "()Lcom/lagradost/cloudstream3/ui/player/CSPlayerLoading;", "getSource", "()Lcom/lagradost/cloudstream3/ui/player/PlayerEventSource;", "component1", "component2", "component3", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class StatusEvent extends PlayerEvent {
    private final CSPlayerLoading isPlaying;
    private final PlayerEventSource source;
    private final CSPlayerLoading wasPlaying;

    public static /* synthetic */ StatusEvent copy$default(StatusEvent statusEvent, CSPlayerLoading cSPlayerLoading, CSPlayerLoading cSPlayerLoading2, PlayerEventSource playerEventSource, int i, Object obj) {
        if ((i & 1) != 0) {
            cSPlayerLoading = statusEvent.wasPlaying;
        }
        if ((i & 2) != 0) {
            cSPlayerLoading2 = statusEvent.isPlaying;
        }
        if ((i & 4) != 0) {
            playerEventSource = statusEvent.source;
        }
        return statusEvent.copy(cSPlayerLoading, cSPlayerLoading2, playerEventSource);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final CSPlayerLoading getWasPlaying() {
        return this.wasPlaying;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final CSPlayerLoading getIsPlaying() {
        return this.isPlaying;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final PlayerEventSource getSource() {
        return this.source;
    }

    public final StatusEvent copy(CSPlayerLoading wasPlaying, CSPlayerLoading isPlaying, PlayerEventSource source) {
        Intrinsics.checkNotNullParameter(wasPlaying, "wasPlaying");
        Intrinsics.checkNotNullParameter(isPlaying, "isPlaying");
        Intrinsics.checkNotNullParameter(source, "source");
        return new StatusEvent(wasPlaying, isPlaying, source);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof StatusEvent)) {
            return false;
        }
        StatusEvent statusEvent = (StatusEvent) other;
        return this.wasPlaying == statusEvent.wasPlaying && this.isPlaying == statusEvent.isPlaying && this.source == statusEvent.source;
    }

    public int hashCode() {
        return (((this.wasPlaying.hashCode() * 31) + this.isPlaying.hashCode()) * 31) + this.source.hashCode();
    }

    public String toString() {
        return "StatusEvent(wasPlaying=" + this.wasPlaying + ", isPlaying=" + this.isPlaying + ", source=" + this.source + ')';
    }

    public StatusEvent(CSPlayerLoading wasPlaying, CSPlayerLoading isPlaying, PlayerEventSource source) {
        Intrinsics.checkNotNullParameter(wasPlaying, "wasPlaying");
        Intrinsics.checkNotNullParameter(isPlaying, "isPlaying");
        Intrinsics.checkNotNullParameter(source, "source");
        this.wasPlaying = wasPlaying;
        this.isPlaying = isPlaying;
        this.source = source;
    }

    public final CSPlayerLoading getWasPlaying() {
        return this.wasPlaying;
    }

    public final CSPlayerLoading isPlaying() {
        return this.isPlaying;
    }

    public /* synthetic */ StatusEvent(CSPlayerLoading cSPlayerLoading, CSPlayerLoading cSPlayerLoading2, PlayerEventSource playerEventSource, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(cSPlayerLoading, cSPlayerLoading2, (i & 4) != 0 ? PlayerEventSource.Player : playerEventSource);
    }

    @Override // com.lagradost.cloudstream3.ui.player.PlayerEvent
    public PlayerEventSource getSource() {
        return this.source;
    }
}
