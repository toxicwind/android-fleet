package com.lagradost.cloudstream3.ui.player;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: IPlayer.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0011\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0083\u0004J\n\u0010\u000e\u001a\u00020\u000fHÖ\u0081\u0004J\n\u0010\u0010\u001a\u00020\u0011HÖ\u0081\u0004R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0012"}, d2 = {"Lcom/lagradost/cloudstream3/ui/player/TracksChangedEvent;", "Lcom/lagradost/cloudstream3/ui/player/PlayerEvent;", "source", "Lcom/lagradost/cloudstream3/ui/player/PlayerEventSource;", "<init>", "(Lcom/lagradost/cloudstream3/ui/player/PlayerEventSource;)V", "getSource", "()Lcom/lagradost/cloudstream3/ui/player/PlayerEventSource;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class TracksChangedEvent extends PlayerEvent {
    private final PlayerEventSource source;

    /* JADX WARN: Multi-variable type inference failed */
    public TracksChangedEvent() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    public static /* synthetic */ TracksChangedEvent copy$default(TracksChangedEvent tracksChangedEvent, PlayerEventSource playerEventSource, int i, Object obj) {
        if ((i & 1) != 0) {
            playerEventSource = tracksChangedEvent.source;
        }
        return tracksChangedEvent.copy(playerEventSource);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final PlayerEventSource getSource() {
        return this.source;
    }

    public final TracksChangedEvent copy(PlayerEventSource source) {
        Intrinsics.checkNotNullParameter(source, "source");
        return new TracksChangedEvent(source);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof TracksChangedEvent) && this.source == ((TracksChangedEvent) other).source;
    }

    public int hashCode() {
        return this.source.hashCode();
    }

    public String toString() {
        return "TracksChangedEvent(source=" + this.source + ')';
    }

    public TracksChangedEvent(PlayerEventSource source) {
        Intrinsics.checkNotNullParameter(source, "source");
        this.source = source;
    }

    public /* synthetic */ TracksChangedEvent(PlayerEventSource playerEventSource, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? PlayerEventSource.Player : playerEventSource);
    }

    @Override // com.lagradost.cloudstream3.ui.player.PlayerEvent
    public PlayerEventSource getSource() {
        return this.source;
    }
}
