package com.lagradost.cloudstream3.ui.player;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: IPlayer.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001f\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u000f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0006HÆ\u0003J#\u0010\u000f\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006HÆ\u0001J\u0014\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013HÖ\u0083\u0004J\n\u0010\u0014\u001a\u00020\u0015HÖ\u0081\u0004J\n\u0010\u0016\u001a\u00020\u0017HÖ\u0081\u0004R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0014\u0010\u0005\u001a\u00020\u0006X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0018"}, d2 = {"Lcom/lagradost/cloudstream3/ui/player/EmbeddedSubtitlesFetchedEvent;", "Lcom/lagradost/cloudstream3/ui/player/PlayerEvent;", "tracks", "", "Lcom/lagradost/cloudstream3/ui/player/SubtitleData;", "source", "Lcom/lagradost/cloudstream3/ui/player/PlayerEventSource;", "<init>", "(Ljava/util/List;Lcom/lagradost/cloudstream3/ui/player/PlayerEventSource;)V", "getTracks", "()Ljava/util/List;", "getSource", "()Lcom/lagradost/cloudstream3/ui/player/PlayerEventSource;", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "app"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class EmbeddedSubtitlesFetchedEvent extends PlayerEvent {
    private final PlayerEventSource source;
    private final List<SubtitleData> tracks;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ EmbeddedSubtitlesFetchedEvent copy$default(EmbeddedSubtitlesFetchedEvent embeddedSubtitlesFetchedEvent, List list, PlayerEventSource playerEventSource, int i, Object obj) {
        if ((i & 1) != 0) {
            list = embeddedSubtitlesFetchedEvent.tracks;
        }
        if ((i & 2) != 0) {
            playerEventSource = embeddedSubtitlesFetchedEvent.source;
        }
        return embeddedSubtitlesFetchedEvent.copy(list, playerEventSource);
    }

    public final List<SubtitleData> component1() {
        return this.tracks;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final PlayerEventSource getSource() {
        return this.source;
    }

    public final EmbeddedSubtitlesFetchedEvent copy(List<SubtitleData> tracks, PlayerEventSource source) {
        Intrinsics.checkNotNullParameter(tracks, "tracks");
        Intrinsics.checkNotNullParameter(source, "source");
        return new EmbeddedSubtitlesFetchedEvent(tracks, source);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof EmbeddedSubtitlesFetchedEvent)) {
            return false;
        }
        EmbeddedSubtitlesFetchedEvent embeddedSubtitlesFetchedEvent = (EmbeddedSubtitlesFetchedEvent) other;
        return Intrinsics.areEqual(this.tracks, embeddedSubtitlesFetchedEvent.tracks) && this.source == embeddedSubtitlesFetchedEvent.source;
    }

    public int hashCode() {
        return (this.tracks.hashCode() * 31) + this.source.hashCode();
    }

    public String toString() {
        return "EmbeddedSubtitlesFetchedEvent(tracks=" + this.tracks + ", source=" + this.source + ')';
    }

    public EmbeddedSubtitlesFetchedEvent(List<SubtitleData> tracks, PlayerEventSource source) {
        Intrinsics.checkNotNullParameter(tracks, "tracks");
        Intrinsics.checkNotNullParameter(source, "source");
        this.tracks = tracks;
        this.source = source;
    }

    public final List<SubtitleData> getTracks() {
        return this.tracks;
    }

    public /* synthetic */ EmbeddedSubtitlesFetchedEvent(List list, PlayerEventSource playerEventSource, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(list, (i & 2) != 0 ? PlayerEventSource.Player : playerEventSource);
    }

    @Override // com.lagradost.cloudstream3.ui.player.PlayerEvent
    public PlayerEventSource getSource() {
        return this.source;
    }
}
