package com.lagradost.cloudstream3.ui.player;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: IPlayer.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001b\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u000b\u0010\f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0005HÆ\u0003J\u001f\u0010\u000e\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0014\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0003HÖ\u0083\u0004J\n\u0010\u0012\u001a\u00020\u0013HÖ\u0081\u0004J\n\u0010\u0014\u001a\u00020\u0015HÖ\u0081\u0004R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0016"}, d2 = {"Lcom/lagradost/cloudstream3/ui/player/PlayerAttachedEvent;", "Lcom/lagradost/cloudstream3/ui/player/PlayerEvent;", "player", "", "source", "Lcom/lagradost/cloudstream3/ui/player/PlayerEventSource;", "<init>", "(Ljava/lang/Object;Lcom/lagradost/cloudstream3/ui/player/PlayerEventSource;)V", "getPlayer", "()Ljava/lang/Object;", "getSource", "()Lcom/lagradost/cloudstream3/ui/player/PlayerEventSource;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class PlayerAttachedEvent extends PlayerEvent {
    private final Object player;
    private final PlayerEventSource source;

    public static /* synthetic */ PlayerAttachedEvent copy$default(PlayerAttachedEvent playerAttachedEvent, Object obj, PlayerEventSource playerEventSource, int i, Object obj2) {
        if ((i & 1) != 0) {
            obj = playerAttachedEvent.player;
        }
        if ((i & 2) != 0) {
            playerEventSource = playerAttachedEvent.source;
        }
        return playerAttachedEvent.copy(obj, playerEventSource);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final Object getPlayer() {
        return this.player;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final PlayerEventSource getSource() {
        return this.source;
    }

    public final PlayerAttachedEvent copy(Object player, PlayerEventSource source) {
        Intrinsics.checkNotNullParameter(source, "source");
        return new PlayerAttachedEvent(player, source);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PlayerAttachedEvent)) {
            return false;
        }
        PlayerAttachedEvent playerAttachedEvent = (PlayerAttachedEvent) other;
        return Intrinsics.areEqual(this.player, playerAttachedEvent.player) && this.source == playerAttachedEvent.source;
    }

    public int hashCode() {
        Object obj = this.player;
        return ((obj == null ? 0 : obj.hashCode()) * 31) + this.source.hashCode();
    }

    public String toString() {
        return "PlayerAttachedEvent(player=" + this.player + ", source=" + this.source + ')';
    }

    public PlayerAttachedEvent(Object obj, PlayerEventSource source) {
        Intrinsics.checkNotNullParameter(source, "source");
        this.player = obj;
        this.source = source;
    }

    public final Object getPlayer() {
        return this.player;
    }

    public /* synthetic */ PlayerAttachedEvent(Object obj, PlayerEventSource playerEventSource, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(obj, (i & 2) != 0 ? PlayerEventSource.Player : playerEventSource);
    }

    @Override // com.lagradost.cloudstream3.ui.player.PlayerEvent
    public PlayerEventSource getSource() {
        return this.source;
    }
}
